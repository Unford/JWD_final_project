package by.epam.bartenderhelper.model.dao.sql.query;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;

/**
 * The interface Insert.
 */
public interface Insert extends SqlQuery{
    /**
     * Sets columns.
     *
     * @param columns the columns
     * @return the columns
     */
    Insert setColumns(Column... columns);

    /**
     * Sets column.
     *
     * @param column the column
     * @return the column
     */
    Insert setColumn(Column column);

    /**
     * Sets columns.
     *
     * @param table the table
     * @return the columns
     */
    Insert setColumns(Table table);

}
