package by.epam.bartenderhelper.model.dao.sql.query;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.common.Where;

public interface Update extends SqlQuery, Where<Update> {
    Update set(Column column);
    Update setAll(Table table);
    Update setAll(Column... columns);
}
