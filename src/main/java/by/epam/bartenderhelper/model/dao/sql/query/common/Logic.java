package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;

/**
 * The interface Logic.
 *
 * @param <Q> the type parameter
 */
public interface Logic<Q extends SqlQuery> {
    /**
     * In q.
     *
     * @param values the values
     * @return the q
     */
    Q in(String... values);

    /**
     * Or q.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     * @return the q
     */
    Q or(Column column, LogicOperator operator, String value);

    /**
     * Or q.
     *
     * @param column   the column
     * @param operator the operator
     * @return the q
     */
    Q or(Column column, LogicOperator operator);

    /**
     * Or q.
     *
     * @param column the column
     * @return the q
     */
    Q or(Column column);

    /**
     * And q.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     * @return the q
     */
    Q and(Column column, LogicOperator operator, String value);

    /**
     * And q.
     *
     * @param column   the column
     * @param operator the operator
     * @return the q
     */
    Q and(Column column, LogicOperator operator);

    /**
     * And q.
     *
     * @param column the column
     * @return the q
     */
    Q and(Column column);

    /**
     * Between q.
     *
     * @param from the from
     * @param to   the to
     * @return the q
     */
    Q between(String from, String to);

    /**
     * Like q.
     *
     * @param pattern the pattern
     * @return the q
     */
    Q like(String pattern);

    /**
     * Is null q.
     *
     * @return the q
     */
    Q isNull();

    /**
     * Is not null q.
     *
     * @return the q
     */
    Q isNotNull();

    /**
     * Not q.
     *
     * @return the q
     */
    Q not();
}
