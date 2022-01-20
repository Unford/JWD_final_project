package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.entity.Ingredient;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class IngredientDao extends AbstractDao<Ingredient> {
    @Override
    public List<Ingredient> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Ingredient> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Ingredient entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Ingredient entity) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }

    @Override
    protected Ingredient mapEntity(ResultSet resultSet) {
        return null;
    }
}
