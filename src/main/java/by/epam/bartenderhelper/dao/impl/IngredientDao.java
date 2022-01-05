package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Ingredient;

import java.util.List;

public class IngredientDao extends AbstractDao<Ingredient> {
    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    @Override
    public Ingredient findById(long id) {
        return null;
    }

    @Override
    public boolean create(Ingredient entity) {
        return false;
    }

    @Override
    public Ingredient update(Ingredient entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
