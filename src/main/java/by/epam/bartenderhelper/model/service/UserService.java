package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;

import java.util.List;
import java.util.Optional;


/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Create account boolean.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createAccount(User user, String password) throws ServiceException;

    /**
     * Is unique username boolean.
     *
     * @param username the username
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUniqueUsername(String username) throws ServiceException;

    /**
     * Is unique email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUniqueEmail(String email) throws ServiceException;

    /**
     * Login optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     * Find user profile optional.
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserProfile(String login) throws ServiceException;

    /**
     * Change user status boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserStatus(long id, User.Status status) throws ServiceException;

    /**
     * Change user role boolean.
     *
     * @param id   the id
     * @param role the role
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserRole(long id, UserRole role) throws ServiceException;

    /**
     * Change password boolean.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changePassword(long id, String password) throws ServiceException;

    /**
     * Delete profile boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteProfile(User user) throws ServiceException;

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUser(User user) throws ServiceException;


    /**
     * Find part of all users list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findPartOfAllUsers(long page) throws ServiceException;

    /**
     * Calculate users size long.
     *
     * @return the long
     * @throws ServiceException the service exception
     */
    long calculateUsersSize() throws ServiceException;

}
