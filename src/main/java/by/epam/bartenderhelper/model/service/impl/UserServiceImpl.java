package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.dao.EntityTransaction;
import by.epam.bartenderhelper.model.dao.impl.UserDaoImpl;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.util.PasswordHash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();

    private static UserServiceImpl instance;

    private UserServiceImpl(){
    }

    public static UserServiceImpl getInstance() {
        if (instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void createAccount(User user, String password) throws ServiceException {
        EntityTransaction transaction = new EntityTransaction();
        try (transaction){
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initializeTransaction(userDao);
            userDao.create(user);
            userDao.updatePassword(user.getId(), PasswordHash.hash(password));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isUniqueUsername(String username) throws ServiceException {
        boolean result = true;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction){
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            Optional<User> user = userDao.findByUsername(username);
            if (user.isPresent()){
                result = false;
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean isUniqueEmail(String email) throws ServiceException {
        boolean result = true;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction){
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            Optional<User> user = userDao.findByEmail(email);
            if (user.isPresent()){
                result = false;
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }
}
