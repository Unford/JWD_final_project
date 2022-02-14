package by.epam.bartenderhelper.model.dao;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.entity.Ingredient;

import java.util.List;
import java.util.Optional;

/**
 * The interface Ingredient dao.
 */
public interface IngredientDao {
    /**
     * Update status boolean.
     *
     * @param id     the id
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateStatus(long id, boolean status) throws DaoException;

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Ingredient> findByName(String name) throws DaoException;

    /**
     * Find part of all verified by name list.
     *
     * @param name the name
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Ingredient> findPartOfAllVerifiedByName(String name, long page) throws DaoException;

    /**
     * Count all verified by name long.
     *
     * @param name the name
     * @return the long
     * @throws DaoException the dao exception
     */
    long countAllVerifiedByName(String name) throws DaoException;

    /**
     * Find part of all by name list.
     *
     * @param name the name
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Ingredient> findPartOfAllByName(String name, long page) throws DaoException;

    /**
     * Count all by name long.
     *
     * @param name the name
     * @return the long
     * @throws DaoException the dao exception
     */
    long countAllByName(String name) throws DaoException;
}
