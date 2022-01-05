package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.User;
import by.epam.bartenderhelper.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.bartenderhelper.dao.ColumnName.*;

public class UserDao extends AbstractDao<User> {//todo
    private static final String SQL_FIND_ALL_USERS_QUERY = """
            SELECT %s, %s, %s, %s, %s, %s, %s, %s
            """
            .formatted(USER_ID, USER_LOGIN, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE, USER_STATUS);


    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS_QUERY)) {
            while (resultSet.next()) {
                User user = mapToUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
           logger.error("Can't find all users", e);
           throw new DaoException("Can't find all users", e);
        }
        return users;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    private User mapToUser(ResultSet resultSet) {
        return null;
    }
}
