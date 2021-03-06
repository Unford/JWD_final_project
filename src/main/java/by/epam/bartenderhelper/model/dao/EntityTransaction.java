package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.model.pool.ConnectionPool;
import by.epam.bartenderhelper.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Entity transaction.
 */
public class EntityTransaction implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    /**
     * Initialize transaction.
     *
     * @param daos the daos
     */
    public void initializeTransaction(AbstractDao ... daos) {
        try {
            this.initialize(daos);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Error while setting auto commit");
        }
    }

    /**
     * Initialize.
     *
     * @param daos the daos
     */
    public void initialize(AbstractDao ... daos){
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        for (AbstractDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    @Override
    public void close() throws DaoException {
        try{
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connection = null;
        }
    }

    /**
     * Commit.
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("commit has been failed", e);
        }
    }

    /**
     * Rollback.
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("rollback has been failed", e);
        }
    }
}
