package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;


public interface UserService {
    void createAccount(User user, String password) throws ServiceException;
    boolean isUniqueUsername(String username) throws ServiceException;
    boolean isUniqueEmail(String email) throws ServiceException;

}
