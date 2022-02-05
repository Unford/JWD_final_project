package by.epam.bartenderhelper.model.dao.impl;

import by.epam.bartenderhelper.exception.DaoException;
import by.epam.bartenderhelper.model.dao.AbstractDao;
import by.epam.bartenderhelper.model.dao.ReviewDao;
import by.epam.bartenderhelper.model.dao.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.bartenderhelper.model.dao.sql.Column.*;

public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {

    private static final String FIND_ALL_REVIEWS_QUERY = SqlBuilderFactory.commonSelect(Table.REVIEWS).toString();

    private static final String FIND_BY_REVIEW_ID_QUERY = SqlBuilderFactory
            .commonSelectBy(Table.REVIEWS, REVIEW_ID).toString();

    private static final String CREATE_REVIEW_QUERY = SqlBuilderFactory.commonInsert(Table.REVIEWS).toString();

    private static final String UPDATE_REVIEW_QUERY = SqlBuilderFactory.commonUpdateById(Table.REVIEWS).toString();

    private static final String DELETE_PHOTO_BY_ID_QUERY = SqlBuilderFactory.commonDeleteById(Table.REVIEWS).toString();

    private static final String FIND_ALL_AUTHOR_USERS_REVIEWS_QUERY = SqlBuilderFactory.select()
            .selectColumns(Table.REVIEWS)
            .from(Table.REVIEWS)
            .join(Table.USERS_REVIEWS).using(REVIEW_ID)
            .where(USERS_REVIEWS_USER_ID, LogicOperator.EQUALS)
            .and(REVIEW_AUTHOR_ID, LogicOperator.EQUALS)
            .toString();


    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_REVIEWS_QUERY)) {
            while (resultSet.next()) {
                Review review = mapEntity(resultSet);
                reviews.add(review);
            }
        } catch (SQLException e) {
            logger.error("Find all reviews query error", e);
            throw new DaoException("Find all reviews query error", e);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findById(long id) throws DaoException {
        Optional<Review> review = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_REVIEW_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    review = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find review by id query error", e);
            throw new DaoException("Find review by id query error", e);
        }
        return review;
    }

    @Override
    public boolean create(Review entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_REVIEW_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(statement, entity);
            result = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Create review query error", e);
            throw new DaoException("Create review query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean update(Review entity) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW_QUERY)) {
            setPreparedStatement(statement, entity);
            statement.setLong(5, entity.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update review query error", e);
            throw new DaoException("Update review query error", e);
        }
        return result > 0;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PHOTO_BY_ID_QUERY)) {
            statement.setLong(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Delete review by id query error", e);
            throw new DaoException("Delete review by id query error", e);
        }
        return result > 0;
    }


    @Override
    public Optional<Review> findUsersReviewsByAuthor(long userId, long authorId) throws DaoException{
        Optional<Review> review = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_AUTHOR_USERS_REVIEWS_QUERY)) {
            statement.setLong(1, userId);
            statement.setLong(2, authorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    review = Optional.ofNullable(mapEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Find review by id query error", e);
            throw new DaoException("Find review by id query error", e);
        }
        return review;
    }

    @Override
    protected Review mapEntity(ResultSet resultSet) throws DaoException {
        try {
            return new Review.ReviewBuilder()
                    .reviewId(resultSet.getLong(PHOTO_ID.getName()))
                    .message(resultSet.getString(REVIEW_MESSAGE.getName()))
                    .score(resultSet.getDouble(REVIEW_SCORE.getName()))
                    .timestamp(resultSet.getTimestamp(REVIEW_DATE.getName()).toInstant())
                    .authorId(resultSet.getLong(REVIEW_AUTHOR_ID.getName()))
                    .build();
        } catch (SQLException e) {
            logger.error("building review has been failed", e);
            throw new DaoException("building review has been failed", e);
        }
    }

    @Override
    protected void setPreparedStatement(PreparedStatement statement, Review entity) throws DaoException {
        try {
            statement.setString(1, entity.getMessage());
            statement.setDouble(2, entity.getScore());
            statement.setTimestamp(3, Timestamp.from(entity.getTimestamp()));
            statement.setLong(4, entity.getAuthorId());
        } catch (SQLException e) {
            logger.error("error while setting review statement parameters", e);
            throw new DaoException("error while setting review statement parameters", e);
        }

    }


}
