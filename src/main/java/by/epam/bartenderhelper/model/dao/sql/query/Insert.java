package by.epam.bartenderhelper.model.dao.sql.query;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;

public interface Insert extends SqlQuery{
    Insert setColumns(Column... columns);
    Insert setColumn(Column column);
    Insert setColumns(Table table);

}
