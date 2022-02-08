package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.Update;

import java.util.Arrays;

/**
 * The type Update query.
 */
public class UpdateQueryImpl extends CommonSqlQueryImpl<Update> implements Update {
    private boolean isFirstColumn = true;

    /**
     * Instantiates a new Update query.
     *
     * @param table the table
     */
    public UpdateQueryImpl(Table table){
        sqlBuilder.append("UPDATE ")
                .append(table);
    }

    @Override
    public Update set(Column column) {
        sqlBuilder.append(isFirstColumn ? " SET " : ',')
                .append(column.getFullName()).append("=?");
        isFirstColumn = false;
        return this;
    }

    @Override
    public Update setAll(Table table) {
        Column[] columns = Arrays.stream(table.getColumns())
                .filter(column -> column.getType() == Column.Type.REQUIRED)
                .toArray(Column[]::new);
        return setAll(columns);
    }

    @Override
    public Update setAll(Column... columns) {
        for (Column col : columns) {
            set(col);
        }
        return this;
    }

    @Override
    protected Update get() {
        return this;
    }
}
