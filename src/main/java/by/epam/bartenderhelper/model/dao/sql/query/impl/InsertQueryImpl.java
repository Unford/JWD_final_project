package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.Insert;

import java.util.Arrays;

public class InsertQueryImpl extends CommonSqlQueryImpl<Insert> implements Insert {
    private boolean isFirstValue = true;
    private int columnCount;

    public InsertQueryImpl(Table table){
        sqlBuilder.append("INSERT INTO ").append(table).append('(');
    }


    @Override
    public Insert setColumns(Column... columns) {
        for (Column col : columns) {
            setColumn(col);
        }
        return this;
    }

    @Override
    public Insert setColumn(Column column) {
        sqlBuilder.append(!isFirstValue ? ',' : "").append(column.getName());
        if (isFirstValue){
            isFirstValue = false;
        }
        columnCount++;
        return this;
    }

    @Override
    public Insert setColumns(Table table) {
        Column[] columns = Arrays.stream(table.getColumns())
                .filter(column -> column.getType() == Column.Type.REQUIRED)
                .toArray(Column[]::new);
        return setColumns(columns);
    }

    @Override
    public String toString(){
        sqlBuilder.append(") VALUES(");
        for (int i = 0; i < columnCount; i++) {
            sqlBuilder.append("?,");
        }
        sqlBuilder.replace(sqlBuilder.length() - 1, sqlBuilder.length(), ")");
        return super.toString();
    }

    @Override
    protected InsertQueryImpl get() {
        return this;
    }
}
