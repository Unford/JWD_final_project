package by.epam.bartenderhelper.model.dao.sql.query;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;


/**
 * The interface Common sql query.
 *
 * @param <Q> the type parameter
 */
public interface CommonSqlQuery<Q extends SqlQuery> {

    /**
     * Avg q.
     *
     * @param column the column
     * @param as     the as
     * @return the q
     */
    Q avg(Column column, String as);

    /**
     * Max q.
     *
     * @param column the column
     * @param as     the as
     * @return the q
     */
    Q max(Column column, String as);

    /**
     * Min q.
     *
     * @param column the column
     * @param as     the as
     * @return the q
     */
    Q min(Column column, String as);

    /**
     * Sum q.
     *
     * @param column the column
     * @param as     the as
     * @return the q
     */
    Q sum(Column column, String as);

    /**
     * Count q.
     *
     * @param column   the column
     * @param as       the as
     * @param distinct the distinct
     * @return the q
     */
    Q count(Column column, String as ,boolean distinct);

    /**
     * Count q.
     *
     * @param column the column
     * @param as     the as
     * @return the q
     */
    Q count(Column column, String as);

    /**
     * From q.
     *
     * @param table the table
     * @return the q
     */
    Q from(Table table);

    /**
     * Join q.
     *
     * @param type  the type
     * @param table the table
     * @return the q
     */
    Q join(JoinType type, Table table);

    /**
     * Join q.
     *
     * @param table the table
     * @return the q
     */
    Q join(Table table);

    /**
     * On q.
     *
     * @param leftColumn  the left column
     * @param rightColumn the right column
     * @return the q
     */
    Q on(Column leftColumn, Column rightColumn);

    /**
     * On q.
     *
     * @param leftColumn  the left column
     * @param rightColumn the right column
     * @return the q
     */
    Q on(String leftColumn, String rightColumn);

    /**
     * Using q.
     *
     * @param link the link
     * @return the q
     */
    Q using(Column link);

    /**
     * Using q.
     *
     * @param link the link
     * @return the q
     */
    Q using(String link);

    /**
     * Where q.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     * @return the q
     */
    Q where(Column column, LogicOperator operator, String value);

    /**
     * Where q.
     *
     * @param column   the column
     * @param operator the operator
     * @return the q
     */
    Q where(Column column, LogicOperator operator);

    /**
     * Where q.
     *
     * @param column the column
     * @return the q
     */
    Q where(Column column);

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
     * @return the q
     */
    Q like();

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
