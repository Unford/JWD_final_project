package by.epam.bartenderhelper.model.service;

import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;

import java.util.List;
import java.util.Optional;

/**
 * The interface Ingredient service.
 */
public interface IngredientService {
    /**
     * Create ingredient boolean.
     *
     * @param ingredient the ingredient
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createIngredient(Ingredient ingredient) throws ServiceException;

    /**
     * Find all measures list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Measure> findAllMeasures() throws ServiceException;

    /**
     * Find ingredient by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Ingredient> findIngredientById(long id) throws ServiceException;

    /**
     * Change ingredient status boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeIngredientStatus(long id, boolean status) throws ServiceException;

    /**
     * Is unique name boolean.
     *
     * @param name the name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUniqueName(String name) throws ServiceException;

    /**
     * Is unique name boolean.
     *
     * @param name the name
     * @param id   the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUniqueName(String name, long id) throws ServiceException;

    /**
     * Update ingredient boolean.
     *
     * @param ingredient the ingredient
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateIngredient(Ingredient ingredient) throws ServiceException;

    /**
     * Calculate ingredients by name and status long.
     *
     * @param name   the name
     * @param status the status
     * @return the long
     * @throws ServiceException the service exception
     */
    long calculateIngredientsByNameAndStatus(String name, boolean status) throws ServiceException;

    /**
     * Find ingredients by name and status list.
     *
     * @param name   the name
     * @param status the status
     * @param page   the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Ingredient> findIngredientsByNameAndStatus(String name, boolean status, long page) throws ServiceException;
}
