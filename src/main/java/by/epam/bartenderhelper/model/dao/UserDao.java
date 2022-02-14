package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Update password boolean.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePassword(long id, String password) throws DaoException;

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByUsername(String username) throws DaoException;

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Find by username or email optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByUsernameOrEmail(String login) throws DaoException;

    /**
     * Find by id or username optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByIdOrUsername(String login) throws DaoException;

    /**
     * Find user password by id string.
     *
     * @param id the id
     * @return the string
     * @throws DaoException the dao exception
     */
    String findUserPasswordById(long id)throws DaoException;

    /**
     * Update status boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateStatus(long id, User.Status status) throws DaoException;

    /**
     * Update role boolean.
     *
     * @param id   the id
     * @param role the role
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateRole(long id, UserRole role) throws DaoException;

    /**
     * Update is deleted boolean.
     *
     * @param id    the id
     * @param value the value
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateIsDeleted(long id, boolean value) throws DaoException;

    /**
     * Create review boolean.
     *
     * @param id       the id
     * @param reviewId the review id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean createReview(long id, long reviewId) throws DaoException;


    /**
     * Find part of all list.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findPartOfAll(long page) throws DaoException;

    /**
     * Count all users long.
     *
     * @return the long
     * @throws DaoException the dao exception
     */
    long countAllUsers() throws DaoException;

}
