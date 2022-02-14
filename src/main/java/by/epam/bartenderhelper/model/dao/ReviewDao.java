package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.entity.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Review dao.
 */
public interface ReviewDao {
    /**
     * Find users reviews by author optional.
     *
     * @param userId   the user id
     * @param authorId the author id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Review> findUserReviewsByAuthor(long userId, long authorId) throws DaoException;

    /**
     * Find all information part list.
     *
     * @param userId the user id
     * @param limit  the limit
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ReviewDto> findAllInformationPart(long userId, int limit) throws DaoException;

    /**
     * Delete by author id boolean.
     *
     * @param userId   the user id
     * @param authorId the author id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteByAuthorId(long userId, long authorId) throws DaoException;

    /**
     * Update user review boolean.
     *
     * @param review the review
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserReview(Review review, long userId) throws DaoException;
}
