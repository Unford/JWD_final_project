package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.entity.Photo;

import java.util.List;
import java.util.Optional;

public class PhotoDao extends AbstractDao<Photo> {
    @Override
    public List<Photo> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Photo> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Photo entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Photo entity) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }
}
