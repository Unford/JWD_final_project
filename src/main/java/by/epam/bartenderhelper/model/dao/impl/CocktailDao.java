package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.entity.Cocktail;

import java.util.List;
import java.util.Optional;

public class CocktailDao extends AbstractDao<Cocktail> {
    @Override
    public List<Cocktail> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Cocktail> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Cocktail entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Cocktail entity) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }
}
