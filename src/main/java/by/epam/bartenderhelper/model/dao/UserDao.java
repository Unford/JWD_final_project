package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean updatePassword(long id, String password) throws DaoException;
    Optional<User> findByUsername(String username) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    Optional<User> findByUsernameOrEmail(String login) throws DaoException;
    Optional<User> findByIdOrUsername(String login) throws DaoException;

    String findUserPasswordById(long id)throws DaoException;
    boolean updateStatus(long id, User.Status status) throws DaoException;
    boolean updateIsDeleted(long id, boolean value) throws DaoException;
    boolean createReview(long id, long reviewId) throws DaoException;


}
