package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;

import java.util.Optional;


public interface UserService {
    boolean createAccount(User user, String password) throws ServiceException;
    boolean isUniqueUsername(String username) throws ServiceException;
    boolean isUniqueEmail(String email) throws ServiceException;
    Optional<User> login(String login, String password) throws ServiceException;
    Optional<User> findUserProfile(String login) throws ServiceException;
    boolean changeUserStatus(long id, User.Status status) throws ServiceException;
    boolean changePassword(long id, String password) throws ServiceException;
    boolean deleteProfile(User user) throws ServiceException;
    boolean updateUser(User user) throws ServiceException;



}
