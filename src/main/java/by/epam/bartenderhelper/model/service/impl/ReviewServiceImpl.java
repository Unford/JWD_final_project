package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.dao.EntityTransaction;
import by.epam.bartenderhelper.model.dao.impl.ReviewDaoImpl;
import by.epam.bartenderhelper.model.dao.impl.UserDaoImpl;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.entity.dto.ReviewDto;
import by.epam.bartenderhelper.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The type Review service.
 */
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LogManager.getLogger();

    private static ReviewServiceImpl instance;

    private ReviewServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
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
            review = reviewDao.findUserReviewsByAuthor(userId, authorId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return review.isEmpty();
    }

    @Override
    public boolean createUserReview(Review review, long userId) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            UserDaoImpl userDao = new UserDaoImpl();
            ReviewDaoImpl reviewDao = new ReviewDaoImpl();

            transaction.initializeTransaction(userDao, reviewDao);
            result = reviewDao.create(review);
            result &= userDao.createReview(userId, review.getId());
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<Review> findUserReviewByAuthor(long userId, long authorId) throws ServiceException {
        Optional<Review> review;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            ReviewDaoImpl reviewDao = new ReviewDaoImpl();
            transaction.initialize(reviewDao);
            review = reviewDao.findUserReviewsByAuthor(userId, authorId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return review;
    }

    @Override
    public List<ReviewDto> findUserReviewsPart(long userId, int page) throws ServiceException {
        List<ReviewDto> reviews;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            ReviewDaoImpl reviewDao = new ReviewDaoImpl();
            transaction.initialize(reviewDao);
            reviews = reviewDao.findAllInformationPart(userId, page);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return reviews;
    }
}
