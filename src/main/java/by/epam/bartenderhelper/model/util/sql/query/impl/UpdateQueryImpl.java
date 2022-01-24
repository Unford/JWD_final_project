package by.epam.bartenderhelper.model.util.sql.query.impl;

import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.Table;
import by.epam.bartenderhelper.model.util.sql.query.Update;

import java.util.Arrays;

public class UpdateQueryImpl extends CommonSqlQueryImpl<Update> implements Update {
    private boolean isFirstColumn = true;

    public UpdateQueryImpl(Table table){
        sqlBuilder.append("UPDATE ")
                .append(table).append(" AS ")
                .append(table.getShortName())
                .append(" SET");
    }

    @Override
    public Update set(Column column) {
        sqlBuilder.append(isFirstColumn ? ' ' : ',')
                .append(column.getShortName()).append("=?");
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
