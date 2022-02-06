package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.Review;

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
    Optional<Review> findUsersReviewsByAuthor(long userId, long authorId) throws DaoException;
}
