package by.epam.bartenderhelper.dao;

import by.epam.bartenderhelper.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

class DaoInitializer implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public void transactionInitialize(AbstractDao ... daos) {
        try {
            this.initialize(daos);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Error while setting auto commit");
        }
    }

    public void initialize(AbstractDao ... daos){
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        for (AbstractDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    @Override
    public void close() throws SQLException {
        try{
            connection.close();
        }finally {
            connection = null;
        }
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}
