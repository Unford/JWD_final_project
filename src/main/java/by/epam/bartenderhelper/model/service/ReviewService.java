package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.entity.dto.ReviewDto;

import java.util.List;
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
    boolean isUniqueUserReview(long userId, long authorId) throws ServiceException;

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
    Optional<Review> findUserReviewByAuthor(long userId, long authorId) throws ServiceException;

    /**
     * Find user reviews part list.
     *
     * @param userId the user id
     * @param page   the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ReviewDto> findUserReviewsPart(long userId, int page) throws ServiceException;

    /**
     * Delete user review by author boolean.
     *
     * @param userId   the user id
     * @param authorId the author id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteUserReviewByAuthor(long userId, long authorId) throws ServiceException;

    /**
     * Update user review boolean.
     *
     * @param review the review
     * @param userId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserReview(Review review, long userId) throws ServiceException;
}
