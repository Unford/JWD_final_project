package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;

public interface UserDao {
    boolean updatePassword(long id, byte[] password) throws DaoException;
}
