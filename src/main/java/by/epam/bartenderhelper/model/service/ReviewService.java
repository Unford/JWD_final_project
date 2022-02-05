package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Review;

public interface ReviewService {
    boolean checkUserReview(long userId, long authorId) throws ServiceException;
    boolean createUserReview(Review review, long userId) throws ServiceException;

}
