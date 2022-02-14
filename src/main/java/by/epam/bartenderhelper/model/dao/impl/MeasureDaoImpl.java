package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.dao.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.entity.Measure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.bartenderhelper.model.dao.sql.Column.*;

/**
 * The type Measure dao.
 */
public class MeasureDaoImpl extends AbstractDao<Measure> {
    private static final String FIND_ALL_MEASURES_QUERY = SqlBuilderFactory.commonSelect(Table.MEASURES).toString();

    private static final String FIND_MEASURE_BY_ID_QUERY = SqlBuilderFactory
            .commonSelectBy(Table.MEASURES, MEASURE_ID).toString();

    private static final String CREATE_MEASURE_QUERY = SqlBuilderFactory.commonInsert(Table.MEASURES).toString();

    private static final String UPDATE_MEASURE_QUERY = SqlBuilderFactory.commonUpdateById(Table.MEASURES).toString();

    private static final String DELETE_MEASURE_BY_ID_QUERY = SqlBuilderFactory.commonDeleteById(Table.MEASURES).toString();


    @Override
    public List<Measure> findAll() throws DaoException {
        List<Measure> measures = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_MEASURES_QUERY)) {
            while (resultSet.next()) {
                Measure photo = mapEntity(resultSet);
                measures.add(photo);
            }
        } catch (SQLException e) {
            logger.error("Find all measures query error", e);
            throw new DaoException("Find all measures query error", e);
        }
        return measures;
    }

    @Override
    public Optional<Measure> findById(long id) throws DaoException {
        Optional<Measure> measure = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_MEASURE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    measure = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find measure by id query error", e);
            throw new DaoException("Find measure by id query error", e);
        }
        return measure;
    }

    @Override
    public boolean create(Measure entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_MEASURE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(statement, entity);
            result = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Create measure query error", e);
            throw new DaoException("Create measure query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean update(Measure entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_MEASURE_QUERY)) {
            setPreparedStatement(statement, entity);
            statement.setLong(1, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update measure query error", e);
            throw new DaoException("Update measure query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MEASURE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Delete measure by id query error", e);
            throw new DaoException("Delete measure by id query error", e);
        }
        return result > 0;
    }

    @Override
    protected Measure mapEntity(ResultSet resultSet) throws DaoException {
        try {
            Measure measure = new Measure();
            measure.setId(resultSet.getLong(MEASURE_ID.getName()));
            measure.setName(resultSet.getString(MEASURE_NAME.getName()));
            return measure;
        } catch (SQLException e) {
            logger.error("building measure has been failed", e);
            throw new DaoException("building measure has been failed", e);
        }
    }

    @Override
    protected void setPreparedStatement(PreparedStatement statement, Measure entity) throws DaoException {
        try {
            statement.setString(1, entity.getName());
        } catch (SQLException e) {
            logger.error("error while setting measure statement parameter", e);
            throw new DaoException("error while setting measure statement parameter", e);
        }
    }
}
