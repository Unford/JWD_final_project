package by.epam.bartenderhelper.model.util.sql.query.common;

import by.epam.bartenderhelper.model.util.sql.Table;
import by.epam.bartenderhelper.model.util.sql.query.SqlQuery;

public interface From<Q extends SqlQuery> extends Where<Q> {
    Q from(Table table);
}
