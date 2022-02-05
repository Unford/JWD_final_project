package by.epam.bartenderhelper.model.dao.sql;

import by.epam.bartenderhelper.model.dao.sql.query.*;
import by.epam.bartenderhelper.model.dao.sql.query.impl.DeleteQueryImpl;
import by.epam.bartenderhelper.model.dao.sql.query.impl.InsertQueryImpl;
import by.epam.bartenderhelper.model.dao.sql.query.impl.SelectQueryImpl;
import by.epam.bartenderhelper.model.dao.sql.query.impl.UpdateQueryImpl;

import static by.epam.bartenderhelper.model.dao.sql.Column.REVIEW_ID;

public final class SqlBuilderFactory {
    //todo singletone
    private SqlBuilderFactory() {
    }

    public static Select select(boolean distinct) {
        return new SelectQueryImpl(distinct);
    }

    public static Select select() {
        return select(false);
    }

    public static Insert insert(Table table) {
        return new InsertQueryImpl(table);
    }

    public static Delete delete() {
        return new DeleteQueryImpl();
    }

    public static Update update(Table table) {
        return new UpdateQueryImpl(table);
    }

    public static Select commonSelect(Table table) {
        return select().selectColumns(table).from(table);
    }

    public static Select commonSelectBy(Table table, Column column) {
        return commonSelect(table).where(column, LogicOperator.EQUALS);
    }

    public static Insert commonInsert(Table table) {
        return insert(table).setColumns(table);
    }

    public static Update commonUpdateById(Table table) {
        return update(table).setAll(table).where(table.getIdColumn(), LogicOperator.EQUALS);
    }

    public static Delete commonDeleteById(Table table) {
        return delete().from(table).where(table.getIdColumn());
    }
}