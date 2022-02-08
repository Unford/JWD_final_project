package by.epam.bartenderhelper.model.dao.sql.query;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.common.From;
import by.epam.bartenderhelper.model.dao.sql.query.common.Where;

/**
 * The interface Update.
 */
public interface Update extends SqlQuery, From<Update> {
    /**
     * Set update.
     *
     * @param column the column
     * @return the update
     */
    Update set(Column column);

    /**
     * Sets all.
     *
     * @param table the table
     * @return the all
     */
    Update setAll(Table table);

    /**
     * Sets all.
     *
     * @param columns the columns
     * @return the all
     */
    Update setAll(Column... columns);
}
