package by.epam.bartenderhelper.model.util.sql;

import by.epam.bartenderhelper.model.util.sql.query.Delete;
import by.epam.bartenderhelper.model.util.sql.query.Insert;
import by.epam.bartenderhelper.model.util.sql.query.Select;
import by.epam.bartenderhelper.model.util.sql.query.Update;
import by.epam.bartenderhelper.model.util.sql.query.impl.DeleteQueryImpl;
import by.epam.bartenderhelper.model.util.sql.query.impl.InsertQueryImpl;
import by.epam.bartenderhelper.model.util.sql.query.impl.SelectQueryImpl;
import by.epam.bartenderhelper.model.util.sql.query.impl.UpdateQueryImpl;

public final class SqlBuilderFactory {

    private SqlBuilderFactory(){}

    public static Select select(boolean distinct){
        return new SelectQueryImpl(distinct);
    }
    public static Select select(){
        return select(false);
    }
    public static Insert insert(Table table){
        return new InsertQueryImpl(table);
    }
    public static Delete delete(){
        return new DeleteQueryImpl();
    }

    public static Update update(Table table){
        return new UpdateQueryImpl(table);
    }

}
