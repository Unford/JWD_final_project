package by.epam.bartenderhelper.model.dao.sql.query;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;


public interface CommonSqlQuery<Q extends SqlQuery> {

    Q avg(Column column, String as);
    Q max(Column column, String as);
    Q min(Column column, String as);
    Q sum(Column column, String as);
    Q count(Column column, String as ,boolean distinct);
    Q count(Column column, String as);

    Q from(Table table);
    Q join(JoinType type, Table table);
    Q join(Table table);
    Q on(Column leftColumn, Column rightColumn);
    Q on(String leftColumn, String rightColumn);
    Q using(Column link);
    Q using(String link);

    Q where(Column column, LogicOperator operator, String value);
    Q where(Column column, LogicOperator operator);
    Q where(Column column);

    Q in(String... values);
    Q or(Column column, LogicOperator operator, String value);
    Q or(Column column, LogicOperator operator);
    Q or(Column column);
    Q and(Column column, LogicOperator operator, String value);
    Q and(Column column, LogicOperator operator);
    Q and(Column column);

    Q between(String from, String to);
    Q like();
    Q isNull();
    Q isNotNull();
    Q not();


}
