package by.epam.bartenderhelper.dao;

import by.epam.bartenderhelper.entity.AbstractDaoEntity;
import by.epam.bartenderhelper.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao <T extends AbstractDaoEntity> {
    protected static final Logger logger = LogManager.getLogger();
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;
    public abstract T findById(long id) throws DaoException;
    public abstract boolean create(T entity) throws DaoException;
    public abstract T update(T entity) throws DaoException;
    public abstract boolean deleteById(long id) throws DaoException;

    public boolean delete(T entity) throws DaoException {
        return deleteById(entity.getId());
    }

    public void close(Statement statement) {//todo
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("error while closing statement", e);
        }
    }

    void setConnection (Connection connection){
        this.connection = connection;
    }

}
