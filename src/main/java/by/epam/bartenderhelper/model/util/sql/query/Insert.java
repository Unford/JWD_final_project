package by.epam.bartenderhelper.model.util.sql.query;

import by.epam.bartenderhelper.model.util.sql.Column;
import by.epam.bartenderhelper.model.util.sql.Table;

public interface Insert extends SqlQuery{
    Insert setColumns(Column... columns);
    Insert setColumn(Column column);
    Insert setColumns(Table table);

}
