package by.epam.bartenderhelper.model.service.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.dao.EntityTransaction;
import by.epam.bartenderhelper.model.dao.impl.IngredientDaoImpl;
import by.epam.bartenderhelper.model.dao.impl.MeasureDaoImpl;
import by.epam.bartenderhelper.model.dao.impl.PhotoDaoImpl;
import by.epam.bartenderhelper.model.dao.impl.ReviewDaoImpl;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;
import by.epam.bartenderhelper.model.entity.dto.ReviewDto;
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
}
