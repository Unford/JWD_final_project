package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.dao.UserDao;
import by.epam.bartenderhelper.model.dao.sql.query.Select;
import by.epam.bartenderhelper.model.entity.Photo;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.UserRole;
import by.epam.bartenderhelper.model.dao.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.JoinType;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.epam.bartenderhelper.model.dao.sql.Column.*;


/**
 * The type User dao.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String COCKTAILS_CONCAT = "cocktails";
    private static final String REVIEWS_CONCAT = "reviews";
    private static final String USER_RATING = "userRating";


    private static final String FIND_ALL_USERS_QUERY = getUserSelectQuery()
            .groupBy(USER_ID)
            .toString();

    private static final String FIND_BY_USER_ID_QUERY = getUserSelectQuery()
            .where(USER_ID, LogicOperator.EQUALS)
            .groupBy(USER_ID)
            .toString();

    private static final String FIND_USER_BY_USERNAME_OR_EMAIL_QUERY = getUserSelectQuery()
            .where(USER_USERNAME, LogicOperator.EQUALS)
            .or(USER_EMAIL, LogicOperator.EQUALS)
            .groupBy(USER_ID)
            .toString();

    private static final String FIND_USER_BY_USERNAME_OR_ID_QUERY = getUserSelectQuery()
            .where(USER_USERNAME, LogicOperator.EQUALS)
            .or(USER_ID, LogicOperator.EQUALS)
            .groupBy(USER_ID)
            .toString();

    private static final String CREATE_USER_QUERY = SqlBuilderFactory.commonInsert(Table.USERS).toString();

    private static final String UPDATE_USER_QUERY = SqlBuilderFactory.update(Table.USERS)
            .setAll(USER_FIRST_NAME, USER_LAST_NAME, USER_DESCRIPTION, USER_PHOTO_ID)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String UPDATE_USER_PASSWORD_QUERY = SqlBuilderFactory.update(Table.USERS)
            .set(USER_PASSWORD)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String UPDATE_USER_STATUS_QUERY = SqlBuilderFactory.update(Table.USERS)
            .set(USER_STATUS)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String UPDATE_USER_IS_DELETED_QUERY = SqlBuilderFactory.update(Table.USERS)
            .set(USER_IS_DELETED)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String DELETE_USER_BY_ID_QUERY = SqlBuilderFactory.commonDeleteById(Table.USERS).toString();

    private static final String FIND_USER_BY_USERNAME = getUserSelectQuery()
            .where(USER_USERNAME, LogicOperator.EQUALS)
            .groupBy(USER_ID)
            .toString();

    private static final String FIND_USER_BY_EMAIL_QUERY = getUserSelectQuery()
            .where(USER_EMAIL, LogicOperator.EQUALS)
            .groupBy(USER_ID)
            .toString();

    private static final String FIND_USER_PASSWORD_BY_ID_QUERY = SqlBuilderFactory.select()
            .selectColumns(USER_PASSWORD)
            .from(Table.USERS)
            .where(USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String CREATE_USERS_REVIEW_QUERY = SqlBuilderFactory.commonInsert(Table.USERS_REVIEWS).toString();

    private static Select getUserSelectQuery() {
        return SqlBuilderFactory.select()
                .selectColumns(Table.USERS)
                .avg(REVIEW_SCORE, USER_RATING)
                .selectColumn(PHOTO_DATA)
                .selectColumn(PHOTO_NAME)
                .groupConcat(USERS_COCKTAILS_COCKTAIL_ID, COCKTAILS_CONCAT)
                .groupConcat(USERS_REVIEWS_USER_ID, REVIEWS_CONCAT)
                .from(Table.USERS)
                .join(JoinType.LEFT, Table.PHOTOS).using(PHOTO_ID)
                .join(JoinType.LEFT, Table.USERS_COCKTAILS).on(USERS_COCKTAILS_USER_ID, USER_ID)
                .join(JoinType.LEFT, Table.USERS_REVIEWS).on(USERS_REVIEWS_USER_ID, USER_ID)
                .join(JoinType.LEFT, Table.REVIEWS).on(USERS_REVIEWS_REVIEW_ID, REVIEW_ID);
    }

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
    public boolean create(User entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(statement, entity);
            result = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                }
            }
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
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getDescription());
            statement.setObject(4, entity.getPhoto().getId() == 0 ? null : entity.getPhoto().getId());
            statement.setLong(5, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user query error", e);
            throw new DaoException("Update user query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean updatePassword(long id, String password) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_QUERY)) {
            statement.setString(1, password);
            statement.setLong(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user password query error", e);
            throw new DaoException("Update user password query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean updateStatus(long id, User.Status status) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_STATUS_QUERY)) {
            statement.setString(1, status.toString());
            statement.setLong(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user status query error", e);
            throw new DaoException("Update user status query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean updateIsDeleted(long id, boolean value) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_IS_DELETED_QUERY)) {
            statement.setInt(1, value ? 1 : 0);
            statement.setLong(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user is deleted query error", e);
            throw new DaoException("Update user is deleted query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean createReview(long id, long reviewId) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USERS_REVIEW_QUERY)) {
            statement.setLong(1, id);
            statement.setLong(2, reviewId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create users review query error", e);
            throw new DaoException("Create users reviews query error", e);
        }
        return result > 0;
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find user by username query error", e);
            throw new DaoException("Find user by username query error", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {//todo duplicate
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find user by username query error", e);
            throw new DaoException("Find user by username query error", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME_OR_EMAIL_QUERY)) {
            statement.setString(1, login);
            statement.setString(2, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find user by username or email query error", e);
            throw new DaoException("Find user by username or email query error", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByIdOrUsername(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME_OR_ID_QUERY)) {
            statement.setString(1, login);
            statement.setString(2, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find user by username or id query error", e);
            throw new DaoException("Find user by username or id query error", e);
        }
        return user;
    }

    @Override
    public String findUserPasswordById(long id) throws DaoException {
        String passwordHash = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_PASSWORD_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    passwordHash = resultSet.getString(USER_PASSWORD.getName());
                }
            }
        } catch (SQLException e) {
            logger.error("Find user password by id query error", e);
            throw new DaoException("Find user password by id query error", e);
        }
        return passwordHash;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
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
            return new User.UserBuilder()
                    .userId(resultSet.getLong(USER_ID.getName()))
                    .username(resultSet.getString(USER_USERNAME.getName()))
                    .firstName(resultSet.getString(USER_FIRST_NAME.getName()))
                    .lastName(resultSet.getString(USER_LAST_NAME.getName()))
                    .description(resultSet.getString(USER_DESCRIPTION.getName()))
                    .email(resultSet.getString(USER_EMAIL.getName()))
                    .role(UserRole.defineRole(resultSet.getObject(USER_ROLE.getName()).toString()))
                    .status(User.Status.defineStatus(resultSet.getObject(USER_STATUS.getName()).toString()))
                    .userRating(resultSet.getDouble(USER_RATING))
                    .isDeleted(resultSet.getBoolean(USER_IS_DELETED.getName()))
                    .photo(new Photo.PhotoBuilder()
                            .photoId(resultSet.getLong(USER_PHOTO_ID.getName()))
                            .name(resultSet.getString(PHOTO_NAME.getName()))
                            .data(resultSet.getString(PHOTO_DATA.getName()))
                            .build())
                    .reviews(toListId(resultSet.getString(REVIEWS_CONCAT), DEFAULT_CONCAT_DELIMITER))
                    .cocktails(toListId(resultSet.getString(COCKTAILS_CONCAT), DEFAULT_CONCAT_DELIMITER))
                    .build();
        } catch (SQLException | IllegalArgumentException e) {
            logger.error("building user has been failed", e);
            throw new DaoException("building user has been failed", e);
        }
    }

    private List<Long> toListId(String column, String delimiter) {
        List<Long> idList = column != null ? Arrays.stream(column.split(delimiter))
                .map(Long::parseLong)
                .toList() : new ArrayList<>();
        return idList;
    }


    protected void setPreparedStatement(PreparedStatement statement, User entity) throws DaoException {
        try {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getRole().toString());
            statement.setString(6, entity.getStatus().toString());
            statement.setInt(7, entity.isDeleted() ? 1 : 0);
        } catch (SQLException e) {
            logger.error("error while setting user statement parameters", e);
            throw new DaoException("error while setting user statement parameters", e);
        }

    }

}
