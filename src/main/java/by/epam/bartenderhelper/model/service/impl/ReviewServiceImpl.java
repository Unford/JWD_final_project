package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.dao.EntityTransaction;
import by.epam.bartenderhelper.model.dao.ReviewDao;
import by.epam.bartenderhelper.model.dao.impl.ReviewDaoImpl;
import by.epam.bartenderhelper.model.dao.impl.UserDaoImpl;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.ReviewService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LogManager.getLogger();

    private static ReviewServiceImpl instance;

    private ReviewServiceImpl() {
    }

    public static ReviewServiceImpl getInstance() {
        if (instance == null) {
            instance = new ReviewServiceImpl();
        }
        return instance;
    }


    @Override
    public boolean checkUserReview(long userId, long authorId) throws ServiceException {
        Optional<Review> review;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            ReviewDaoImpl reviewDao = new ReviewDaoImpl();
            transaction.initialize(reviewDao);
            review = reviewDao.findUsersReviewsByAuthor(userId, authorId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return review.isEmpty();
    }

    @Override
    public boolean createUserReview(Review review, long userId) throws ServiceException {
        return false;
    }
}
