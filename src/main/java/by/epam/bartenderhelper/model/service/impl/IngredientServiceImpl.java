package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.dao.EntityTransaction;
import by.epam.bartenderhelper.model.dao.impl.*;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;
import by.epam.bartenderhelper.model.service.IngredientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class IngredientServiceImpl implements IngredientService {
    private static final Logger logger = LogManager.getLogger();

    private static IngredientServiceImpl instance;

    private IngredientServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static IngredientServiceImpl getInstance() {
        if (instance == null) {
            instance = new IngredientServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createIngredient(Ingredient ingredient) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            PhotoDaoImpl photoDao = new PhotoDaoImpl();
            transaction.initializeTransaction(ingredientDao, photoDao);
            result = photoDao.create(ingredient.getPhoto());
            result &= ingredientDao.create(ingredient);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Measure> findAllMeasures() throws ServiceException {
        List<Measure> measures;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            MeasureDaoImpl measureDao = new MeasureDaoImpl();
            transaction.initialize(measureDao);
            measures = measureDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return measures;
    }

    @Override
    public Optional<Ingredient> findIngredientById(long id) throws ServiceException {
        Optional<Ingredient> ingredient;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl measureDao = new IngredientDaoImpl();
            transaction.initialize(measureDao);
            ingredient = measureDao.findById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return ingredient;
    }

    @Override
    public boolean changeIngredientStatus(long id, boolean status) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            transaction.initialize(ingredientDao);
            result = ingredientDao.updateStatus(id, status);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean isUniqueName(String name) throws ServiceException {
        Optional<Ingredient> ingredient;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            transaction.initialize(ingredientDao);
            ingredient = ingredientDao.findByName(name);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return ingredient.isEmpty();
    }

    @Override
    public boolean isUniqueName(String name, long id) throws ServiceException {
        Optional<Ingredient> ingredient;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            transaction.initialize(ingredientDao);
            ingredient = ingredientDao.findByName(name);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

        return ingredient.isEmpty() || ingredient.get().getId() == id;
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient) throws ServiceException {
        boolean result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            PhotoDaoImpl photoDao = new PhotoDaoImpl();
            transaction.initializeTransaction(ingredientDao, photoDao);

            if (ingredient.getPhoto().getData() != null) {
                Optional<Ingredient> currentIngredient = ingredientDao.findById(ingredient.getId());
                long currentPhotoId = currentIngredient.get().getPhoto().getId();
                ingredient.getPhoto().setId(currentPhotoId);
                photoDao.update(ingredient.getPhoto());
            }
            result = ingredientDao.update(ingredient);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public long calculateIngredientsSize(String name) throws ServiceException {
        long result;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            transaction.initialize(ingredientDao);
            result = ingredientDao.countVerifiedByName(name);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Ingredient> findIngredientsByName(String name, long page) throws ServiceException {
        List<Ingredient> ingredients;
        EntityTransaction transaction = new EntityTransaction();
        try (transaction) {
            IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
            transaction.initialize(ingredientDao);
            ingredients = ingredientDao.findPartOfAllVerifiedByName(name, page);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return ingredients;
    }
}
