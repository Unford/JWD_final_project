package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    boolean createIngredient(Ingredient ingredient) throws ServiceException;
    List<Measure> findAllMeasures() throws ServiceException;

    Optional<Ingredient> findIngredientById(long id) throws ServiceException;

    boolean changeIngredientStatus(long id, boolean status) throws ServiceException;

    boolean isUniqueName(String name) throws ServiceException;
    boolean isUniqueName(String name, long id) throws ServiceException;

    boolean updateIngredient(Ingredient ingredient) throws ServiceException;

    long calculateIngredientsSize(String name) throws ServiceException;

    List<Ingredient> findIngredientsByName(String name, long page) throws ServiceException;
}
