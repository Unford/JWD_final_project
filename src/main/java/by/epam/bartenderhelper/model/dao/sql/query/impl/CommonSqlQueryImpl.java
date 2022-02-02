package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;
import by.epam.bartenderhelper.model.dao.sql.query.common.AggregationFunction;
import by.epam.bartenderhelper.model.dao.sql.query.common.From;

abstract class CommonSqlQueryImpl<Q extends SqlQuery> implements From<Q>, AggregationFunction<Q> {
    protected StringBuilder sqlBuilder = new StringBuilder();

    @Override
    public Q in(String... values) {
        sqlBuilder.append(" IN (");
        for (String value : values) {
            sqlBuilder.append(value).append(", ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());
        sqlBuilder.append(") ");
        return get();
    }



    @Override
    public Q between(String from, String to) {
        sqlBuilder.append(" BETWEEN ").append(from).append(" AND ").append(to);
        return get();
    }

    @Override
    public Q like(String pattern) {
        sqlBuilder.append(" LIKE ").append(pattern);
        return get();
    }

    @Override
    public Q isNull() {
        sqlBuilder.append(" IS NULL");
        return get();
    }

    @Override
    public Q isNotNull() {
        sqlBuilder.append(" IS NOT NULL");
        return get();
    }

    @Override
    public Q avg(Column column) {
        appendAggregationFunction(" AVG", column.getShortName());
        return get();
    }

    @Override
    public Q max(Column column) {
        appendAggregationFunction(" MAX", column.getShortName());
        return get();
    }

    @Override
    public Q min(Column column) {
        appendAggregationFunction(" MIN", column.getShortName());
        return get();
    }

    @Override
    public Q sum(Column column) {
        appendAggregationFunction(" SUM", column.getShortName());
        return get();
    }


    @Override
    public Q count(Column column, boolean distinct) {
        sqlBuilder.append(" COUNT(")
                .append(distinct ? "DISTINCT " : "")
                .append(column.getShortName()).append("),");
        return get();
    }

    @Override
    public Q count(Column column) {
        return count(column, false);
    }

    @Override
    public Q from(Table table) {
        sqlBuilder.append(" FROM ").append(table).append(" AS ").append(table.getShortName());
        return get();
    }

    @Override
    public Q or(Column column, LogicOperator operator, String value) {
        sqlBuilder.append(" OR ");
        appendExpression(column, operator, value);
        return get();
    }

    @Override
    public Q or(Column column, LogicOperator operator) {
        return or(column, operator, "?");
    }

    @Override
    public Q or(Column column) {
        sqlBuilder.append(" OR ").append(column);
        return get();
    }



    @Override
    public Q and(Column column, LogicOperator operator, String value) {
        sqlBuilder.append(" AND ");
        appendExpression(column, operator, value);
        return get();
    }

    @Override
    public Q and(Column column, LogicOperator operator) {
        return and(column, operator, "?");
    }

    @Override
    public Q and(Column column) {
        sqlBuilder.append(" AND ").append(column).append(' ');
        return get();
    }

    @Override
    public Q not() {
        sqlBuilder.append(" NOT");
        return get();
    }

    @Override
    public Q where(Column column, LogicOperator operator, String value) {
        sqlBuilder.append(" WHERE ");
        appendExpression(column, operator, value);
        return get();
    }

    @Override
    public Q where(Column column, LogicOperator operator) {
        return where(column, operator, "?");
    }

    @Override
    public Q where(Column column) {
        sqlBuilder.append(" WHERE ")
                .append(column.getShortName());
        return get();
    }

    @Override
    public String toString() {
        return sqlBuilder.toString();
    }

    private void appendAggregationFunction(String function, String value){
        sqlBuilder.append(function).append('(').append(value).append("),");
    }

    protected void appendExpression(Column column, LogicOperator operator, String value){
        sqlBuilder.append(column.getShortName())
                .append(operator)
                .append(value);
    }

    protected abstract Q get();
}
