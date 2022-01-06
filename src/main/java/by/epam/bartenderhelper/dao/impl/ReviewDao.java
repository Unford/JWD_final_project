package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Review;

import java.util.List;

public class ReviewDao extends AbstractDao<Review> {
    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public Review findById(long id) {
        return null;
    }

    @Override
    public boolean create(Review entity) {
        return false;
    }

    @Override
    public Review update(Review entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
