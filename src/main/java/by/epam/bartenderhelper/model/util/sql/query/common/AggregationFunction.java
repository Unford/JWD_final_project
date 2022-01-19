package by.epam.bartenderhelper.model.util.sql.query.common;

import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.query.SqlQuery;

public interface AggregationFunction<Q extends SqlQuery> {
    Q avg(Column column);
    Q max(Column column);
    Q min(Column column);
    Q sum(Column column);
    Q count(Column column, boolean distinct);
    Q count(Column column);
}
