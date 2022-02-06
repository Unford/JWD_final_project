package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Review;

import java.util.Optional;

/**
 * The interface Review service.
 */
public interface ReviewService {
    /**
     * Check user review boolean.
     *
     * @param userId   the user id
     * @param authorId the author id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkUserReview(long userId, long authorId) throws ServiceException;

    /**
     * Create user review boolean.
     *
     * @param review the review
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createUserReview(Review review, long userId) throws ServiceException;

    /**
     * Find user review optional.
     *
     * @param userId   the user id
     * @param authorId the author id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Review> findUserReview(long userId, long authorId)throws ServiceException;

}
