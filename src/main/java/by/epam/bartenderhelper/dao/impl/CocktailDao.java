package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Cocktail;

import java.util.List;

public class CocktailDao extends AbstractDao<Cocktail> {
    @Override
    public List<Cocktail> findAll() {
        return null;
    }

    @Override
    public Cocktail findById(long id) {
        return null;
    }

    @Override
    public boolean create(Cocktail entity) {
        return false;
    }

    @Override
    public Cocktail update(Cocktail entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
