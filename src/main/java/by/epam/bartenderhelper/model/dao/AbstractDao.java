package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.model.entity.AbstractDaoEntity;
import by.epam.bartenderhelper.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractDao <T extends AbstractDaoEntity> {
    /**
     * The constant logger.
     */
    protected static final Logger logger = LogManager.getLogger();
    /**
     * The constant DEFAULT_CONCAT_DELIMITER.
     */
    protected static final String DEFAULT_CONCAT_DELIMITER = ",";

    /**
     * The Connection.
     */
    protected Connection connection;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<T> findById(long id) throws DaoException;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean create(T entity) throws DaoException;

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean update(T entity) throws DaoException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean deleteById(long id) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean delete(T entity) throws DaoException {
        return deleteById(entity.getId());
    }

    /**
     * Map entity t.
     *
     * @param resultSet the result set
     * @return the t
     * @throws DaoException the dao exception
     */
    protected abstract T mapEntity(ResultSet resultSet) throws DaoException;

    /**
     * Sets prepared statement.
     *
     * @param statement the statement
     * @param entity    the entity
     * @throws DaoException the dao exception
     */
    protected abstract void setPreparedStatement(PreparedStatement statement, T entity) throws DaoException;

    /**
     * Set connection.
     *
     * @param connection the connection
     */
    void setConnection (Connection connection){
        this.connection = connection;
    }

}
