package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Review;
import by.epam.bartenderhelper.model.service.ReviewService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        return false;
    }

    @Override
    public boolean createUserReview(Review review, long userId) throws ServiceException {
        return false;
    }
}
