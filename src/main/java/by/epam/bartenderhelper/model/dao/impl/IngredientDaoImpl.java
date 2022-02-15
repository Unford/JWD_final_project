package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.dao.IngredientDao;
import by.epam.bartenderhelper.model.dao.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.JoinType;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.Select;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;
import by.epam.bartenderhelper.model.entity.Photo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.ServletContextAttribute.DEFAULT_PAGINATION_ONE_PAGE_SIZE;
import static by.epam.bartenderhelper.model.dao.sql.Column.*;
import static by.epam.bartenderhelper.model.dao.sql.Column.PHOTO_DATA;

/**
 * The type Ingredient dao.
 */
public class IngredientDaoImpl extends AbstractDao<Ingredient> implements IngredientDao {
    private static final String INGREDIENTS_SIZE = "size";

    private static final String FIND_ALL_INGREDIENTS_QUERY = getIngredientSelectQuery()
            .where(INGREDIENT_STATUS, LogicOperator.EQUALS, "1").toString();

    private static final String FIND_PART_OF_ALL_INGREDIENTS_QUERY = getIngredientSelectQuery()
            .where(INGREDIENT_NAME).like()
            .limit()
            .toString();

    private static final String FIND_PART_OF_ALL_VERIFIED_INGREDIENTS_QUERY = getIngredientSelectQuery()
            .where(INGREDIENT_STATUS, LogicOperator.EQUALS, "1")
            .and(INGREDIENT_NAME).like()
            .limit()
            .toString();

    private static final String FIND_INGREDIENT_BY_ID_QUERY = getIngredientSelectQuery()
            .where(INGREDIENT_ID, LogicOperator.EQUALS).toString();

    private static final String FIND_INGREDIENT_BY_NAME_QUERY = getIngredientSelectQuery()
            .where(INGREDIENT_NAME, LogicOperator.EQUALS).toString();

    private static final String COUNT_VERIFIED_INGREDIENTS_QUERY = SqlBuilderFactory.select()
            .count(INGREDIENT_ID, INGREDIENTS_SIZE)
            .from(Table.INGREDIENTS)
            .where(INGREDIENT_STATUS, LogicOperator.EQUALS, "1")
            .and(INGREDIENT_NAME).like()
            .toString();

    private static final String COUNT_ALL_INGREDIENTS_QUERY = SqlBuilderFactory.select()
            .count(INGREDIENT_ID, INGREDIENTS_SIZE)
            .from(Table.INGREDIENTS)
            .where(INGREDIENT_NAME).like()
            .toString();

    private static final String CREATE_INGREDIENT_QUERY = SqlBuilderFactory.commonInsert(Table.INGREDIENTS).toString();

    private static final String UPDATE_INGREDIENT_QUERY = SqlBuilderFactory
            .update(Table.INGREDIENTS)
            .setAll(INGREDIENT_NAME, INGREDIENT_DESCRIPTION, INGREDIENT_PRICE, INGREDIENT_CALORIE, INGREDIENT_MEASURE_ID)
            .where(INGREDIENT_ID, LogicOperator.EQUALS)
            .toString();

    private static final String UPDATE_INGREDIENT_STATUS_QUERY = SqlBuilderFactory
            .update(Table.INGREDIENTS).set(INGREDIENT_STATUS).where(INGREDIENT_ID, LogicOperator.EQUALS).toString();

    private static final String DELETE_INGREDIENT_BY_ID_QUERY = SqlBuilderFactory.commonDeleteById(Table.INGREDIENTS).toString();

    private static Select getIngredientSelectQuery() {
        return SqlBuilderFactory.select()
                .selectColumns(Table.INGREDIENTS)
                .selectColumns(Table.PHOTOS)
                .selectColumns(Table.MEASURES)
                .from(Table.INGREDIENTS)
                .join(JoinType.LEFT, Table.PHOTOS).using(PHOTO_ID)
                .join(Table.MEASURES).using(MEASURE_ID);
    }

    @Override
    public List<Ingredient> findAll() throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_INGREDIENTS_QUERY)) {
            while (resultSet.next()) {
                Ingredient ingredient = mapEntity(resultSet);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            logger.error("Find all ingredients query error", e);
            throw new DaoException("Find all ingredients query error", e);
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> findPartOfAllVerifiedByName(String name, long page) throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_PART_OF_ALL_VERIFIED_INGREDIENTS_QUERY)) {
            statement.setString(1, "%" +(name == null ? "" : name) + "%");
            statement.setLong(2, (page - 1) * DEFAULT_PAGINATION_ONE_PAGE_SIZE);
            statement.setLong(3, DEFAULT_PAGINATION_ONE_PAGE_SIZE);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ingredient ingredient = mapEntity(resultSet);
                    ingredients.add(ingredient);
                }
            }
        } catch (SQLException e) {
            logger.error("Find part of all verified ingredients query error", e);
            throw new DaoException("Find part of all verified ingredients query error", e);
        }
        return ingredients;
    }

    @Override
    public long countAllVerifiedByName(String name) throws DaoException {
        long result = 0;
        try (PreparedStatement statement = connection.prepareStatement(COUNT_VERIFIED_INGREDIENTS_QUERY)) {
            statement.setString(1, "%" +(name == null ? "" : name) + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Count verified ingredients query error", e);
            throw new DaoException("Count verified ingredients query error", e);
        }
        return result;
    }

    @Override
    public List<Ingredient> findPartOfAllByName(String name, long page) throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_PART_OF_ALL_INGREDIENTS_QUERY)) {
            statement.setString(1, "%" +(name == null ? "" : name) + "%");
            statement.setLong(2, (page - 1) * DEFAULT_PAGINATION_ONE_PAGE_SIZE);
            statement.setLong(3, DEFAULT_PAGINATION_ONE_PAGE_SIZE);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ingredient ingredient = mapEntity(resultSet);
                    ingredients.add(ingredient);
                }
            }
        } catch (SQLException e) {
            logger.error("Find part of all ingredients query error", e);
            throw new DaoException("Find part of all ingredients query error", e);
        }
        return ingredients;
    }

    @Override
    public long countAllByName(String name) throws DaoException {
        long result = 0;
        try (PreparedStatement statement = connection.prepareStatement(COUNT_ALL_INGREDIENTS_QUERY)) {
            statement.setString(1, "%" +(name == null ? "" : name) + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            logger.error("Count all ingredients query error", e);
            throw new DaoException("Count all ingredients query error", e);
        }
        return result;
    }

    @Override
    public Optional<Ingredient> findById(long id) throws DaoException {
        Optional<Ingredient> ingredient = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_INGREDIENT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ingredient = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find ingredient by id query error", e);
            throw new DaoException("Find ingredient by id query error", e);
        }
        return ingredient;
    }

    @Override
    public boolean create(Ingredient entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_INGREDIENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(statement, entity);
            result = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Create ingredient query error", e);
            throw new DaoException("Create ingredient query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean update(Ingredient entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_INGREDIENT_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setBigDecimal(3, entity.getPrice());
            statement.setLong(4, entity.getCalorie());
            statement.setLong(5, entity.getMeasure().getId());
            statement.setLong(6, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update ingredient query error", e);
            throw new DaoException("Update ingredient query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_INGREDIENT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Delete ingredient by id query error", e);
            throw new DaoException("Delete ingredient by id query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean updateStatus(long id, boolean status) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_INGREDIENT_STATUS_QUERY)) {
            statement.setInt(1, status ? 1 : 0);
            statement.setLong(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update ingredient status query error", e);
            throw new DaoException("Update ingredient status query error", e);
        }
        return result > 0;
    }

    @Override
    public Optional<Ingredient> findByName(String name) throws DaoException {
        Optional<Ingredient> ingredient = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_INGREDIENT_BY_NAME_QUERY)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ingredient = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find ingredient by name query error", e);
            throw new DaoException("Find ingredient by name query error", e);
        }
        return ingredient;
    }


    @Override
    protected Ingredient mapEntity(ResultSet resultSet) throws DaoException {
        try {
            Measure measure = new Measure(resultSet.getLong(MEASURE_ID.getName()),
                    resultSet.getString(MEASURE_NAME.getFullName()));
            return new Ingredient.IngredientBuilder()
                    .ingredientId(resultSet.getLong(INGREDIENT_ID.getName()))
                    .name(resultSet.getString(INGREDIENT_NAME.getName()))
                    .description(resultSet.getString(INGREDIENT_DESCRIPTION.getName()))
                    .verified(resultSet.getBoolean(INGREDIENT_STATUS.getName()))
                    .price(resultSet.getBigDecimal(INGREDIENT_PRICE.getName()))
                    .calorie(resultSet.getLong(INGREDIENT_CALORIE.getName()))
                    .photo(new Photo.PhotoBuilder()
                            .photoId(resultSet.getLong(PHOTO_ID.getName()))
                            .name(resultSet.getString(PHOTO_NAME.getFullName()))
                            .data(resultSet.getString(PHOTO_DATA.getName()))
                            .build())
                    .measure(measure)
                    .build();
        } catch (SQLException e) {
            logger.error("building ingredient has been failed", e);
            throw new DaoException("building ingredient has been failed", e);
        }
    }

    @Override
    protected void setPreparedStatement(PreparedStatement statement, Ingredient entity) throws DaoException {
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.isVerified() ? 1 : 0);
            statement.setBigDecimal(4, entity.getPrice());
            statement.setLong(5, entity.getCalorie());
            statement.setLong(6, entity.getPhoto().getId());
            statement.setLong(7, entity.getMeasure().getId());
        } catch (SQLException e) {
            logger.error("error while setting ingredient statement parameters", e);
            throw new DaoException("error while setting ingredient statement parameters", e);
        }
    }


}
