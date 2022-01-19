package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.SqlQueryFactory;
import by.epam.bartenderhelper.model.util.sql.Table;
import by.epam.bartenderhelper.model.util.sql.query.JoinType;
import by.epam.bartenderhelper.model.util.sql.query.LogicOperator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {//todo
    private static final String COCKTAILS_CONCAT = "cocktails";
    private static final String REVIEWS_CONCAT = "reviews";

    private static final String FIND_ALL_USERS_QUERY = SqlQueryFactory.select()
            .selectColumns(Table.USERS)
            .selectColumn(Column.PHOTO_DATA)
            .selectColumn(Column.PHOTO_NAME)
            .groupConcat(Column.USERS_COCKTAILS_COCKTAIL_ID, COCKTAILS_CONCAT)
            .groupConcat(Column.USERS_REVIEWS_USER_ID, REVIEWS_CONCAT)
            .from(Table.USERS)
            .join(JoinType.LEFT, Table.PHOTOS).using(Column.PHOTO_ID)
            .join(JoinType.LEFT, Table.USERS_COCKTAILS).on(Column.USERS_COCKTAILS_USER_ID, Column.USER_ID)
            .join(JoinType.LEFT, Table.USERS_REVIEWS).on(Column.USERS_REVIEWS_USER_ID, Column.USER_ID)
            .groupBy(Column.USER_ID)
            .toString();

    private static final String FIND_BY_USER_ID_QUERY = SqlQueryFactory.select()
            .selectColumns(Table.USERS)
            .selectColumn(Column.PHOTO_DATA)
            .selectColumn(Column.PHOTO_NAME)
            .groupConcat(Column.USERS_COCKTAILS_COCKTAIL_ID, COCKTAILS_CONCAT)
            .groupConcat(Column.USERS_REVIEWS_USER_ID, REVIEWS_CONCAT)
            .from(Table.USERS)
            .join(JoinType.LEFT, Table.PHOTOS).using(Column.PHOTO_ID)
            .join(JoinType.LEFT, Table.USERS_COCKTAILS).on(Column.USERS_COCKTAILS_USER_ID, Column.USER_ID)
            .join(JoinType.LEFT, Table.USERS_REVIEWS).on(Column.USERS_REVIEWS_USER_ID, Column.USER_ID)
            .where(Column.USER_ID, LogicOperator.EQUALS)
            .groupBy(Column.USER_ID)
            .toString();

    private static final String CREATE_USER_QUERY = SqlQueryFactory.insert(Table.USERS)
            .setColumns(Table.USERS)
            .toString();

    private static final String UPDATE_USER_QUERY = SqlQueryFactory.update(Table.USERS)
            .setAll(Table.USERS)
            .where(Column.USER_ID, LogicOperator.EQUALS)
            .toString();

    private static final String DELETE_USER_BY_ID_QUERY = SqlQueryFactory.delete()
            .from(Table.USERS)
            .where(Column.USER_ID, LogicOperator.EQUALS)
            .toString();

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_QUERY)) {
            while (resultSet.next()) {
                User user = mapToUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
           logger.error("Find all users query error", e);
           throw new DaoException("Find all users query error", e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(long id) throws DaoException{
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    user = Optional.ofNullable(mapToUser(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find user by id query error", e);
            throw new DaoException("Find user by id query error", e);
        }
        return user;
    }

    @Override
    public boolean create(User entity) throws DaoException{//todo
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getRole().toString());
            statement.setString(6, entity.getStatus().toString());
            statement.setLong(7, entity.getPhoto().getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create user query has been failed", e);
            throw new DaoException("Create user query has been failed", e);
        }
        return result > 0;
    }

    @Override
    public boolean update(User entity) throws DaoException{
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getRole().toString());
            statement.setString(6, entity.getStatus().toString());
            statement.setLong(7, entity.getPhoto().getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update user query has been failed", e);
            throw new DaoException("Update user query has been failed", e);
        }
        return result > 0;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    private User mapToUser(ResultSet resultSet) {
        return null;
    }
}
