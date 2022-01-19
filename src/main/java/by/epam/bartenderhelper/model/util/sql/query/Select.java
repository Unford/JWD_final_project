package by.epam.bartenderhelper.model.util.sql.query;


import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.Table;
import by.epam.bartenderhelper.model.util.sql.query.common.From;

public interface Select extends SqlQuery, From<Select> {
    Select selectColumns(Table table);
    Select selectColumns(Column... columns);
    Select selectColumn(String column, String as);
    Select selectColumn(String column);
    Select selectColumn(Column column);

    Select groupConcat(String column, String as);
    Select groupConcat(Column column, String as);

    Select groupBy(String column);
    Select groupBy(Column column);

    Select having(Column column, LogicOperator operator, String value);
    Select having(Column column);

    Select orderBy(String column);
    Select orderBy(String column, OrderType type);

    Select orderBy(Column column);
    Select orderBy(Column column, OrderType type);

    Select limit(long from, long to);
    Select limit(long limit);

    Select join(JoinType type, Table table);
    Select join(Table table);

    Select on(Column leftColumn, Column rightColumn);
    Select on(String leftColumn, String rightColumn);

    Select using(Column link);
    Select using(String link);

}
