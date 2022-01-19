package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.entity.Measure;

import java.util.List;
import java.util.Optional;

public class MeasureDao extends AbstractDao<Measure> {
    @Override
    public List<Measure> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Measure> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Measure entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Measure entity) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }
}
