package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.JoinType;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.Select;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;
import by.epam.bartenderhelper.model.dao.sql.query.common.AggregationFunction;
import by.epam.bartenderhelper.model.dao.sql.query.common.From;

/**
 * The type Common sql query.
 *
 * @param <Q> the type parameter
 */
abstract class CommonSqlQueryImpl<Q extends SqlQuery> implements From<Q>, AggregationFunction<Q> {
    /**
     * The Sql builder.
     */
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
        appendAggregationFunction(" AVG", column.getFullName());
        return get();
    }

    @Override
    public Q max(Column column) {
        appendAggregationFunction(" MAX", column.getFullName());
        return get();
    }

    @Override
    public Q min(Column column) {
        appendAggregationFunction(" MIN", column.getFullName());
        return get();
    }

    @Override
    public Q sum(Column column) {
        appendAggregationFunction(" SUM", column.getFullName());
        return get();
    }


    @Override
    public Q count(Column column, boolean distinct) {
        sqlBuilder.append(" COUNT(")
                .append(distinct ? "DISTINCT " : "")
                .append(column.getFullName()).append("),");
        return get();
    }

    @Override
    public Q count(Column column) {
        return count(column, false);
    }

    @Override
    public Q from(Table table) {
        sqlBuilder.append(" FROM ").append(table);
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
                .append(column.getFullName());
        return get();
    }

    @Override
    public Q join(JoinType type, Table table) {
        sqlBuilder.append(' ').append(type == JoinType.INNER ? "" : type)
                .append(" JOIN ")
                .append(table);
        return get();
    }

    @Override
    public Q join(Table table) {
        return join(JoinType.INNER, table);
    }

    @Override
    public Q on(Column leftColumn, Column rightColumn) {
        return on(leftColumn.getFullName(), rightColumn.getFullName());
    }

    @Override
    public Q on(String leftColumn, String rightColumn) {
        sqlBuilder.append(" ON (").append(leftColumn).append('=').append(rightColumn).append(")");
        return get();
    }

    @Override
    public Q using(Column column) {
        return using(column.getName());
    }

    @Override
    public Q using(String column) {
        sqlBuilder.append(" USING (").append(column).append(')');
        return get();
    }

    @Override
    public String toString() {
        return sqlBuilder.toString();
    }

    private void appendAggregationFunction(String function, String value){
        sqlBuilder.append(function).append('(').append(value).append("),");
    }

    /**
     * Append expression.
     *
     * @param column   the column
     * @param operator the operator
     * @param value    the value
     */
    protected void appendExpression(Column column, LogicOperator operator, String value){
        sqlBuilder.append(column.getFullName())
                .append(operator)
                .append(value);
    }

    /**
     * Get q.
     *
     * @return the q
     */
    protected abstract Q get();
}
