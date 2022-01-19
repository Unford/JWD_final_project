package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.entity.Review;

import java.util.List;
import java.util.Optional;

public class ReviewDao extends AbstractDao<Review> {
    @Override
    public List<Review> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Review> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Review entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Review entity) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }

}
