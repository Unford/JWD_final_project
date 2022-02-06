package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;

/**
 * The interface Where.
 *
 * @param <Q> the type parameter
 */
public interface Where<Q extends SqlQuery> extends Logic<Q>{
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
}
