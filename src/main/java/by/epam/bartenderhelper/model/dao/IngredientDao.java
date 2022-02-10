package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.Ingredient;

import java.util.Optional;

public interface IngredientDao {
    boolean updateStatus(long id, boolean status) throws DaoException;
    Optional<Ingredient> findByName(String name) throws DaoException;
}
