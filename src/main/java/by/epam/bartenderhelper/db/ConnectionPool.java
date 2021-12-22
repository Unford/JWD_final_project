package by.epam.bartenderhelper.db;

import by.epam.bartenderhelper.util.PropertyManager;
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
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String POOL_SIZE_PROPERTY_NAME = "pool.size";

    private static ConnectionPool instance;

    private static final AtomicBoolean initCheck = new AtomicBoolean(false);
    private static final ReentrantLock initLock = new ReentrantLock(true);
    private static final Timer timer = new Timer(true);

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> busyConnections;
    private int poolSize = 8;

    private ConnectionPool(){
        availableConnections = new LinkedBlockingQueue<>();
        busyConnections = new LinkedBlockingQueue<>();

        try {//todo
            poolSize = Integer.parseInt(PropertyManager.APP_CONFIG.getString(POOL_SIZE_PROPERTY_NAME));
        } catch (NumberFormatException e){
            logger.log(Level.ERROR, "Can't get pool size from property file, default pool size = {}", poolSize);
        }

        for (int i = 0; i < poolSize; i++) {
            try {
                availableConnections.offer(ProxyConnectionCreator.create());
            } catch (SQLException e) {
                logger.log(Level.ERROR, "{} proxy connection wasn't created", i, e);
            }
        }
        if (availableConnections.isEmpty()){
            logger.log(Level.FATAL, "Connection pool is empty");
            throw new RuntimeException("Connection pool is empty");
        }

        timer.schedule(new TimeController(new ArrayList<>(availableConnections)),//todo
                TimeUnit.HOURS.toMillis(1),
                TimeUnit.MINUTES.toMillis(30));

        logger.info("Connection pool created expected size = {}, actual = {}", availableConnections.size(), poolSize);
    }

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

    public Connection takeConnection(){
        ProxyConnection connection = null;
        try {
           connection = availableConnections.take();
           busyConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "taking connection was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection){
        boolean result = false;
        if (connection instanceof ProxyConnection proxyConnection){
            try {
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

    public void destroyPool() {//todo
        logger.log(Level.INFO, "Destroying pool is started");

        timer.cancel();
        timer.purge();
        for (int i = 0; i < poolSize; i++) {
            try {
                availableConnections.take().realClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQL error while destroy {} connection", i, e);
                throw new RuntimeException("SQL error while destroy connection pool", e);//todo
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "destroying pool was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();

        instance = null;//todo
        initCheck.set(false);

        logger.log(Level.INFO, "Destroying pool is finished");
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
        private List<ProxyConnection> connections;

        public TimeController(Collection<ProxyConnection> connections){
            this.connections = new ArrayList<>(connections);
        }

        @Override
        public void run() {//todo
            if (busyConnections.size() + availableConnections.size() != connections.size()){
                logger.log(Level.WARN, "Time controller found connection leak!");
                connections.stream()
                        .dropWhile(c -> busyConnections.contains(c) || availableConnections.contains(c))
                        .forEach(c -> {
                            try {
                                availableConnections.put(c);
                            } catch (InterruptedException e) {
                                logger.log(Level.ERROR, "Time controller was interrupted", e);
                                Thread.currentThread().interrupt();
                            }
                        });
            }
        }
    }
}
