package by.epam.bartenderhelper.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.bartenderhelper.model.util.ResourceManager.APP_CONFIG;

/**
 * The type Connection pool.
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String POOL_SIZE_PROPERTY_NAME = "pool.size";
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final long DEFAULT_TIMER_DELAY = TimeUnit.MINUTES.toMillis(90);

    private static ConnectionPool instance;

    private static final AtomicBoolean initCheck = new AtomicBoolean(false);
    private static final ReentrantLock initLock = new ReentrantLock(true);

    private static final Timer timer = new Timer(true);
    private static final ReentrantLock timerLock = new ReentrantLock(true);
    private static final Condition timerCondition = timerLock.newCondition();

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> busyConnections;

    private int poolSize;

    private ConnectionPool(){
        availableConnections = new LinkedBlockingQueue<>();
        busyConnections = new LinkedBlockingQueue<>();

        try {
            poolSize = Integer.parseInt(APP_CONFIG.getString(POOL_SIZE_PROPERTY_NAME));
        } catch (NumberFormatException | MissingResourceException e){
            poolSize = DEFAULT_POOL_SIZE;
            logger.log(Level.ERROR, "Can't get pool size from property file, default pool size = {}", poolSize);
        }

        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection proxyConnection = ProxyConnectionCreator.create();
                availableConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "{} proxy connection wasn't created", i, e);
            }
        }

        if (availableConnections.isEmpty()){
            logger.log(Level.FATAL, "Connection pool is empty");
            throw new RuntimeException("Connection pool is empty");
        }
        timer.schedule(new TimeController(), DEFAULT_TIMER_DELAY);

        logger.info("Connection pool created expected size = {}, actual = {}", availableConnections.size(), poolSize);
    }

    /**
     * Get instance connection pool.
     *
     * @return the connection pool
     */
    public static ConnectionPool getInstance(){
        if (!initCheck.get()){
            try {
                initLock.lock();
                if (instance == null){
                    instance = new ConnectionPool();
                    initCheck.set(true);
                }
            } finally {
                initLock.unlock();
            }
        }
        return instance;
    }

    /**
     * Take connection connection.
     *
     * @return the connection
     */
    public Connection takeConnection(){

        ProxyConnection connection = null;
        try {
            while (timerLock.isLocked()){
                timerCondition.await();
            }
           connection = availableConnections.take();
           busyConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "taking connection was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Release connection boolean.
     *
     * @param connection the connection
     * @return the boolean
     */
    public boolean releaseConnection(Connection connection){
        boolean result = false;
        if (connection instanceof ProxyConnection proxyConnection){
            try {
                while (timerLock.isLocked()){
                    timerCondition.await();
                }
               result = busyConnections.remove(proxyConnection);
               if (result){
                   availableConnections.put(proxyConnection);
               }
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "releasing connection was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        logger.log(Level.DEBUG, "Destroying pool is started");

        timer.cancel();
        timer.purge();
        for (int i = 0; i < poolSize; i++) {
            try {
                availableConnections.take().realClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQL error while close {} connection", i, e);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "destroying pool was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();

        logger.log(Level.DEBUG, "Destroying pool is finished, actual size = {}", availableConnections.size());
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQL error while deregister driver", e);
            }
        });
    }

    private class TimeController extends TimerTask {
        @Override
        public void run() {
            try {

                timerLock.lock();
                int currentPoolSize = busyConnections.size() + availableConnections.size();
                logger.debug("Time controller run, actual = {}", currentPoolSize);

                if (currentPoolSize != poolSize){
                    logger.warn("Time controller found connection leak, expected size = {}, actual = {}", poolSize, currentPoolSize);
                    for (int i = currentPoolSize; i <= poolSize; i++) {
                        try {
                            ProxyConnection proxyConnection = ProxyConnectionCreator.create();
                            availableConnections.offer(proxyConnection);
                        } catch (SQLException e) {
                            logger.log(Level.ERROR, "{} proxy connection wasn't created", i, e);
                        }
                    }
                    if (availableConnections.isEmpty() && busyConnections.isEmpty()){
                        logger.log(Level.FATAL, "Connection pool is empty");
                        throw new RuntimeException("Connection pool is empty");
                    }
                }
                timerCondition.signalAll();
            }finally {
                timerLock.unlock();
            }
        }
    }
}
