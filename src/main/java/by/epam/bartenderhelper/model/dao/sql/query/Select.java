package by.epam.bartenderhelper.model.dao.sql.query;


import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.common.From;

/**
 * The interface Select.
 */
public interface Select extends SqlQuery, From<Select> {
    /**
     * Select columns select.
     *
     * @param table the table
     * @return the select
     */
    Select selectColumns(Table table);

    /**
     * Select columns select.
     *
     * @param columns the columns
     * @return the select
     */
    Select selectColumns(Column... columns);

    /**
     * Select column select.
     *
     * @param column the column
     * @param as     the as
     * @return the select
     */
    Select selectColumn(String column, String as);

    /**
     * Select column select.
     *
     * @param column the column
     * @return the select
     */
    Select selectColumn(String column);

    /**
     * Select column select.
     *
     * @param column the column
     * @return the select
     */
    Select selectColumn(Column column);

    /**
     * Group concat select.
     *
     * @param column the column
     * @param as     the as
     * @return the select
     */
    Select groupConcat(String column, String as);

    /**
     * Group concat select.
     *
     * @param column the column
     * @param as     the as
     * @return the select
     */
    Select groupConcat(Column column, String as);

    /**
     * Group by select.
     *
     * @param column the column
     * @return the select
     */
    Select groupBy(String column);

    /**
     * Group by select.
     *
     * @param column the column
     * @return the select
     */
    Select groupBy(Column column);

    /**
     * Having select.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     * @return the select
     */
    Select having(Column column, LogicOperator operator, String value);

    /**
     * Having select.
     *
     * @param column the column
     * @return the select
     */
    Select having(Column column);

    /**
     * Order by select.
     *
     * @param column the column
     * @return the select
     */
    Select orderBy(String column);

    /**
     * Order by select.
     *
     * @param column the column
     * @param type   the type
     * @return the select
     */
    Select orderBy(String column, OrderType type);

    /**
     * Order by select.
     *
     * @param column the column
     * @return the select
     */
    Select orderBy(Column column);

    /**
     * Order by select.
     *
     * @param column the column
     * @param type   the type
     * @return the select
     */
    Select orderBy(Column column, OrderType type);

    /**
     * Limit select.
     *
     * @param from the from
     * @param to   the to
     * @return the select
     */
    Select limit(long from, long to);

    /**
     * Limit select.
     *
     * @param limit the limit
     * @return the select
     */
    Select limit(long limit);

    /**
     * Join select.
     *
     * @param type  the type
     * @param table the table
     * @return the select
     */
    Select join(JoinType type, Table table);

    /**
     * Join select.
     *
     * @param table the table
     * @return the select
     */
    Select join(Table table);

    /**
     * On select.
     *
     * @param leftColumn  the left column
     * @param rightColumn the right column
     * @return the select
     */
    Select on(Column leftColumn, Column rightColumn);

    /**
     * On select.
     *
     * @param leftColumn  the left column
     * @param rightColumn the right column
     * @return the select
     */
    Select on(String leftColumn, String rightColumn);

    /**
     * Using select.
     *
     * @param link the link
     * @return the select
     */
    Select using(Column link);

    /**
     * Using select.
     *
     * @param link the link
     * @return the select
     */
    Select using(String link);

}
