package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.Review;

import java.util.Optional;

public interface ReviewDao {
    Optional<Review> findUsersReviewsByAuthor(long userId, long authorId) throws DaoException;
}
