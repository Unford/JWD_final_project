package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.OrderType;
import by.epam.bartenderhelper.model.dao.sql.query.Select;

/**
 * The type Select query.
 */
public class SelectQueryImpl extends CommonSqlQueryImpl<Select> implements Select {

    /**
     * Instantiates a new Select query.
     *
     * @param distinct the distinct
     */
    public SelectQueryImpl(boolean distinct){
        sqlBuilder.append("SELECT");
        if (distinct){
            sqlBuilder.append(" DISTINCT");
        }
    }

    /**
     * Instantiates a new Select query.
     */
    public SelectQueryImpl(){
        this(false);
    }

    @Override
    public Select selectColumns(Table table) {
        return selectColumns(table.getColumns());
    }

    @Override
    public Select selectColumns(Column... columns) {
        for (Column col : columns) {
            selectColumn(col);
        }
        return this;
    }

    @Override
    public Select selectColumn(String column, String as) {
        sqlBuilder.append(' ').append(column).append(" AS ").append(as);
        return this;
    }

    @Override
    public Select selectColumn(String column) {
        sqlBuilder.append(' ').append(column).append(',');
        return this;
    }

    @Override
    public Select selectColumn(Column column) {
        return selectColumn(column.getFullName());
    }

    @Override
    public Select groupConcat(String column, String as) {
        sqlBuilder.append(" GROUP_CONCAT(")
                .append(column).append(')')
                .append(" AS ").append(as)
                .append(",");
        return this;
    }

    @Override
    public Select groupConcat(Column column, String as) {
        return groupConcat(column.getFullName(), as);
    }

    @Override
    public Select groupBy(String column) {
        sqlBuilder.append(" GROUP BY ").append(column);
        return this;
    }

    @Override
    public Select groupBy(Column column) {
        return groupBy(column.getFullName());
    }


    @Override
    public Select having(Column column, LogicOperator operator, String value) {
        sqlBuilder.append(" HAVING ");
        appendExpression(column, operator, value);
        return this;
    }

    @Override
    public Select having(Column column) {
        sqlBuilder.append(" HAVING ")
                .append(column.getFullName());
        return this;
    }

    @Override
    public Select orderBy(String col) {
        return orderBy(col, OrderType.ASC);
    }

    @Override
    public Select orderBy(String col, OrderType type) {
        sqlBuilder.append(" ORDER BY ").append(col).append(' ')
                .append(type == OrderType.DESC ? type : "");
        return this;
    }

    @Override
    public Select orderBy(Column column) {
        return orderBy(column.getFullName(), OrderType.ASC);
    }

    @Override
    public Select orderBy(Column column, OrderType type) {
        return orderBy(column.getFullName(), type);
    }

    @Override
    public Select limit(long from, long to) {
        sqlBuilder.append(" LIMIT ").append(from);
        if (to > 0){
            sqlBuilder.append(", ").append(to);
        }
        return this;
    }

    @Override
    public Select limit(long limit) {
        return limit(limit, Long.MIN_VALUE);
    }

    @Override
    public Select limit() {
        sqlBuilder.append(" LIMIT ?, ?");
        return this;
    }

    @Override
    public Select from(Table table){
        sqlBuilder.delete(sqlBuilder.length() - 1, sqlBuilder.length());
        return super.from(table);
    }

    @Override
    protected Select get() {
        return this;
    }


}
