package by.epam.bartenderhelper.model.util.sql.query.common;

import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.query.LogicOperator;
import by.epam.bartenderhelper.model.util.sql.query.SqlQuery;

public interface Where<Q extends SqlQuery> extends Logic<Q>{
    Q where(Column column, LogicOperator operator, String value);
    Q where(Column column, LogicOperator operator);
    Q where(Column column);
}
