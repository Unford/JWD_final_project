package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.dao.PhotoDao;
import by.epam.bartenderhelper.model.dao.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.entity.Photo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.bartenderhelper.model.dao.sql.Column.*;

public class PhotoDaoImpl extends AbstractDao<Photo> implements PhotoDao {

    private static final String FIND_ALL_PHOTOS_QUERY = SqlBuilderFactory.select()
            .selectColumns(Table.PHOTOS)
            .from(Table.PHOTOS)
            .toString();

    private static final String FIND_BY_PHOTO_ID_QUERY = SqlBuilderFactory.select()
            .selectColumns(Table.PHOTOS)
            .from(Table.PHOTOS)
            .where(PHOTO_ID, LogicOperator.EQUALS)
            .toString();


    private static final String CREATE_PHOTO_QUERY = SqlBuilderFactory.insert(Table.PHOTOS)
            .setColumns(Table.PHOTOS)
            .toString();

    private static final String UPDATE_PHOTO_QUERY = SqlBuilderFactory.update(Table.PHOTOS)
            .setAll(Table.PHOTOS)
            .where(PHOTO_ID, LogicOperator.EQUALS)
            .toString();

    private static final String DELETE_PHOTO_BY_ID_QUERY = SqlBuilderFactory.delete()
            .from(Table.PHOTOS)
            .where(PHOTO_ID, LogicOperator.EQUALS)
            .toString();


    @Override
    public List<Photo> findAll() throws DaoException {
        List<Photo> photos = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_PHOTOS_QUERY)) {
            while (resultSet.next()) {
                Photo photo = mapEntity(resultSet);
                photos.add(photo);
            }
        } catch (SQLException e) {
            logger.error("Find all photos query error", e);
            throw new DaoException("Find all photos query error", e);
        }
        return photos;
    }

    @Override
    public Optional<Photo> findById(long id) throws DaoException {
        Optional<Photo> photo = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_PHOTO_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    photo = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find photo by id query error", e);
            throw new DaoException("Find photo by id query error", e);
        }
        return photo;
    }

    @Override
    public boolean create(Photo entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PHOTO_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(statement, entity);
            result = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Create photo query error", e);
            throw new DaoException("Create photo query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean update(Photo entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PHOTO_QUERY)) {
            setPreparedStatement(statement, entity);
            statement.setLong(3, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update photo query error", e);
            throw new DaoException("Update photo query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PHOTO_BY_ID_QUERY)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Delete photo by id query error", e);
            throw new DaoException("Delete photo by id query error", e);
        }
        return result > 0;
    }

    @Override
    protected Photo mapEntity(ResultSet resultSet) throws DaoException {
        try {
            return new Photo.PhotoBuilder()
                    .photoId(resultSet.getLong(PHOTO_ID.getName()))
                    .name(resultSet.getString(PHOTO_NAME.getName()))
                    .data(resultSet.getString(PHOTO_DATA.getName()))
                    .build();
        } catch (SQLException e) {
            logger.error("building photo has been failed", e);
            throw new DaoException("building photo has been failed", e);
        }
    }

    @Override
    protected void setPreparedStatement(PreparedStatement statement, Photo entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getData());
    }
}
