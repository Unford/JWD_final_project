package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Table;
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
}
