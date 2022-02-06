package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;

/**
 * The interface Aggregation function.
 *
 * @param <Q> the type parameter
 */
public interface AggregationFunction<Q extends SqlQuery> {
    /**
     * Avg q.
     *
     * @param column the column
     * @return the q
     */
    Q avg(Column column);

    /**
     * Max q.
     *
     * @param column the column
     * @return the q
     */
    Q max(Column column);

    /**
     * Min q.
     *
     * @param column the column
     * @return the q
     */
    Q min(Column column);

    /**
     * Sum q.
     *
     * @param column the column
     * @return the q
     */
    Q sum(Column column);

    /**
     * Count q.
     *
     * @param column   the column
     * @param distinct the distinct
     * @return the q
     */
    Q count(Column column, boolean distinct);

    /**
     * Count q.
     *
     * @param column the column
     * @return the q
     */
    Q count(Column column);
}
