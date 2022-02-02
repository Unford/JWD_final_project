package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;

public interface Logic<Q extends SqlQuery> {
    Q in(String... values);

    Q or(Column column, LogicOperator operator, String value);
    Q or(Column column, LogicOperator operator);
    Q or(Column column);
    Q and(Column column, LogicOperator operator, String value);
    Q and(Column column, LogicOperator operator);
    Q and(Column column);

    Q between(String from, String to);
    Q like(String pattern);
    Q isNull();
    Q isNotNull();
    Q not();
}
