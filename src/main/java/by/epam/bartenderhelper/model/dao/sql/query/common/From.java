package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.JoinType;
import by.epam.bartenderhelper.model.dao.sql.query.Select;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;

/**
 * The interface From.
 *
 * @param <Q> the type parameter
 */
public interface From<Q extends SqlQuery> extends Where<Q> {
    /**
     * From q.
     *
     * @param table the table
     * @return the q
     */
    Q from(Table table);

    /**
     * Join select.
     *
     * @param type  the type
     * @param table the table
     * @return the select
     */
    Q join(JoinType type, Table table);

    /**
     * Join select.
     *
     * @param table the table
     * @return the select
     */
    Q join(Table table);

    /**
     * On select.
     *
     * @param leftColumn  the left column
     * @param rightColumn the right column
     * @return the select
     */
    Q on(Column leftColumn, Column rightColumn);

    /**
     * On select.
     *
     * @param leftColumn  the left column
     * @param rightColumn the right column
     * @return the select
     */
    Q on(String leftColumn, String rightColumn);

    /**
     * Using select.
     *
     * @param link the link
     * @return the select
     */
    Q using(Column link);

    /**
     * Using select.
     *
     * @param link the link
     * @return the select
     */
    Q using(String link);
}
