package by.epam.bartenderhelper.model.dao.sql.query.common;

import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.SqlQuery;

public interface From<Q extends SqlQuery> extends Where<Q> {
    Q from(Table table);
}
