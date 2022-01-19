package by.epam.bartenderhelper.model.util.sql.query;

import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.Table;
import by.epam.bartenderhelper.model.util.sql.query.common.Where;

public interface Update extends SqlQuery, Where<Update> {
    Update set(Column column);
    Update setAll(Table table);
    Update setAll(Column... columns);
}
