package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.dao.UserDao;
import by.epam.bartenderhelper.model.entity.Photo;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.UserRole;
import by.epam.bartenderhelper.model.util.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.util.sql.Table;
import by.epam.bartenderhelper.model.util.sql.query.JoinType;
import by.epam.bartenderhelper.model.util.sql.query.LogicOperator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.epam.bartenderhelper.model.util.sql.Column.*;


public class UserDaoImpl extends AbstractDao<User> implements UserDao {//todo
    private static final String COCKTAILS_CONCAT = "cocktails";
    private static final String REVIEWS_CONCAT = "reviews";
    private static final String DEFAULT_CONCAT_DELIMITER = ",";


    private static final String FIND_ALL_USERS_QUERY = SqlBuilderFactory.select()
            .selectColumns(Table.USERS)
            .selectColumn(PHOTO_DATA)
            .selectColumn(PHOTO_NAME)
            .groupConcat(USERS_COCKTAILS_COCKTAIL_ID, COCKTAILS_CONCAT)
            .groupConcat(USERS_REVIEWS_REVIEW_ID, REVIEWS_CONCAT)
            .from(Table.USERS)
            .join(JoinType.LEFT, Table.PHOTOS).using(PHOTO_ID)
            .join(JoinType.LEFT, Table.USERS_COCKTAILS).on(USERS_COCKTAILS_USER_ID, USER_ID)
            .join(JoinType.LEFT, Table.USERS_REVIEWS).on(USERS_REVIEWS_USER_ID, USER_ID)
            .groupBy(USER_ID)
            .toString();

    private static final String FIND_BY_USER_ID_QUERY = SqlBuilderFactory.select()
            .selectColumns(Table.USERS)
            .selectColumn(PHOTO_DATA)
            .selectColumn(PHOTO_NAME)
            .groupConcat(USERS_COCKTAILS_COCKTAIL_ID, COCKTAILS_CONCAT)
            .groupConcat(USERS_REVIEWS_USER_ID, REVIEWS_CONCAT)
            .from(Table.USERS)
            .join(JoinType.LEFT, Table.PHOTOS).using(PHOTO_ID)
            .join(JoinType.LEFT, Table.USERS_COCKTAILS).on(USERS_COCKTAILS_USER_ID, USER_ID)
            .join(JoinType.LEFT, Table.USERS_REVIEWS).on(USERS_REVIEWS_USER_ID, USER_ID)
            .where(USER_ID, LogicOperator.EQUALS)
            .groupBy(USER_ID)
            .toString();

    private static final String CREATE_USER_QUERY = SqlBuilderFactory.insert(Table.USERS)
            .setColumns(Table.USERS)
            .toString();

    private static final String UPDATE_USER_QUERY = SqlBuilderFactory.update(Table.USERS)
            .setAll(Table.USERS)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String UPDATE_USER_PASSWORD_QUERY = SqlBuilderFactory.update(Table.USERS)
            .set(USER_PASSWORD)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String DELETE_USER_BY_ID_QUERY = SqlBuilderFactory.delete()
            .from(Table.USERS)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_QUERY)) {
            while (resultSet.next()) {
                User user = mapEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Find all users query error", e);
            throw new DaoException("Find all users query error", e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find user by id query error", e);
            throw new DaoException("Find user by id query error", e);
        }
        return user;
    }

    @Override
    public boolean create(User entity) throws DaoException {//todo
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {
            setPreparedStatement(statement, entity);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create user query error", e);
            throw new DaoException("Create user query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean update(User entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            setPreparedStatement(statement, entity);
            statement.setLong(8, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user query error", e);
            throw new DaoException("Update user query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean updatePassword(long id, byte[] password) throws DaoException{
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_QUERY)) {
            statement.setBytes(1, password);
            statement.setLong(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user password query error", e);
            throw new DaoException("Update user password query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean deleteById(long id) throws DaoException{
        int result;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Delete user by id query error", e);
            throw new DaoException("Delete user by id query error", e);
        }
        return result > 0;
    }

    @Override
    protected User mapEntity(ResultSet resultSet) throws DaoException {
        try {
            Blob photoBlob = resultSet.getBlob(PHOTO_DATA.getName());
            return new User.UserBuilder()
                    .userId(resultSet.getLong(USER_ID.getName()))
                    .login(resultSet.getString(USER_LOGIN.getName()))
                    .firstName(resultSet.getString(USER_FIRST_NAME.getName()))
                    .lastName(resultSet.getString(USER_LAST_NAME.getName()))
                    .email(resultSet.getString(USER_EMAIL.getName()))
                    .role(UserRole.defineRole(resultSet.getObject(USER_ROLE.getName())))
                    .status(User.Status.defineStatus(resultSet.getObject(USER_STATUS.getName())))
                    .photo(new Photo.PhotoBuilder()
                            .photoId(resultSet.getLong(USER_PHOTO_ID.getName()))
                            .name(resultSet.getString(PHOTO_NAME.getName()))
                            .data(photoBlob.getBytes(1, (int) photoBlob.length()))
                            .build())
                    .reviews(toListId(resultSet.getString(REVIEWS_CONCAT), DEFAULT_CONCAT_DELIMITER))
                    .cocktails(toListId(resultSet.getString(COCKTAILS_CONCAT), DEFAULT_CONCAT_DELIMITER))
                    .build();
        } catch (SQLException | IllegalArgumentException e) {
            logger.error("building user has been failed", e);
            throw new DaoException("building user has been failed", e);
        }
    }

    private List<Long> toListId(String column, String delimiter) {//todo rename
        List<Long> idList = column != null ? Arrays.stream(column.split(delimiter))
                .map(Long::parseLong)
                .toList() : new ArrayList<>();
        return idList;
    }

    private void setPreparedStatement(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getEmail());
        statement.setString(5, entity.getRole().toString());
        statement.setString(6, entity.getStatus().toString());
        statement.setLong(7, entity.getPhoto().getId());
    }

}
