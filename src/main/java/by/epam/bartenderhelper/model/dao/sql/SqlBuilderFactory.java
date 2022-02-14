package by.epam.bartenderhelper.model.dao.sql;

import by.epam.bartenderhelper.model.dao.sql.query.*;
import by.epam.bartenderhelper.model.dao.sql.query.impl.DeleteQueryImpl;
import by.epam.bartenderhelper.model.dao.sql.query.impl.InsertQueryImpl;
import by.epam.bartenderhelper.model.dao.sql.query.impl.SelectQueryImpl;
import by.epam.bartenderhelper.model.dao.sql.query.impl.UpdateQueryImpl;


/**
 * The type Sql builder factory.
 */
public final class SqlBuilderFactory {
    private SqlBuilderFactory() {
    }

    /**
     * Select select.
     *
     * @param distinct the distinct
     * @return the select
     */
    public static Select select(boolean distinct) {
        return new SelectQueryImpl(distinct);
    }

    /**
     * Select select.
     *
     * @return the select
     */
    public static Select select() {
        return select(false);
    }

    /**
     * Insert insert.
     *
     * @param table the table
     * @return the insert
     */
    public static Insert insert(Table table) {
        return new InsertQueryImpl(table);
    }

    /**
     * Delete delete.
     *
     * @return the delete
     */
    public static Delete delete() {
        return new DeleteQueryImpl();
    }

    /**
     * Delete delete.
     *
     * @param table the table
     * @return the delete
     */
    public static Delete delete(Table table) {
        return new DeleteQueryImpl(table);
    }

    /**
     * Update update.
     *
     * @param table the table
     * @return the update
     */
    public static Update update(Table table) {
        return new UpdateQueryImpl(table);
    }

    /**
     * Common select select.
     *
     * @param table the table
     * @return the select
     */
    public static Select commonSelect(Table table) {
        return select().selectColumns(table).from(table);
    }

    /**
     * Common select by select.
     *
     * @param table  the table
     * @param column the column
     * @return the select
     */
    public static Select commonSelectBy(Table table, Column column) {
        return commonSelect(table).where(column, LogicOperator.EQUALS);
    }

    /**
     * Common insert insert.
     *
     * @param table the table
     * @return the insert
     */
    public static Insert commonInsert(Table table) {
        return insert(table).setColumns(table);
    }

    /**
     * Common update by id update.
     *
     * @param table the table
     * @return the update
     */
    public static Update commonUpdateById(Table table) {
        return update(table).setAll(table).where(table.getIdColumn(), LogicOperator.EQUALS);
    }

    /**
     * Common delete by id delete.
     *
     * @param table the table
     * @return the delete
     */
    public static Delete commonDeleteById(Table table) {
        return delete().from(table).where(table.getIdColumn(), LogicOperator.EQUALS);
    }
}