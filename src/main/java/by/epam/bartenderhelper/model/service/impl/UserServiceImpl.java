package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.dao.EntityTransaction;

import by.epam.bartenderhelper.model.dao.impl.PhotoDaoImpl;
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

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createAccount(User user, String password) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initializeTransaction(userDao);
            result = userDao.create(user);
            result &= userDao.updatePassword(user.getId(), PasswordHash.hash(password));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean isUniqueUsername(String username) throws ServiceException {
        Optional<User> user;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            user = userDao.findByUsername(username);

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user.isEmpty();
    }

    @Override
    public boolean isUniqueEmail(String email) throws ServiceException {
        Optional<User> user;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            user = userDao.findByEmail(email);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user.isEmpty();
    }

    @Override
    public Optional<User> login(String username, String password) throws ServiceException {
        Optional<User> user;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            user = userDao.findByUsernameOrEmail(username);
            if (user.isPresent()) {
                String dbPassword = userDao.findUserPasswordById(user.get().getId());
                user = PasswordHash.checkPassword(password, dbPassword) ? user : Optional.empty();
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserProfile(String info) throws ServiceException {
        Optional<User> user;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            user = userDao.findByIdOrUsername(info);

        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean changeUserStatus(long id, User.Status status) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            result = userDao.updateStatus(id, status);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean changePassword(long id, String password) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            result = userDao.updatePassword(id, PasswordHash.hash(password));
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean deleteProfile(User user) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            transaction.initialize(userDao);
            result = userDao.updateIsDeleted(user.getId(), true);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            PhotoDaoImpl photoDao = new PhotoDaoImpl();
            transaction.initializeTransaction(userDao, photoDao);

            if (user.getPhoto().getData() != null) {
                if (photoDao.findById(user.getPhoto().getId()).isPresent()) {
                    photoDao.update(user.getPhoto());
                } else {
                    photoDao.create(user.getPhoto());
                }
            }
            result = userDao.update(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }
}
