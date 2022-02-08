package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.Delete;
import by.epam.bartenderhelper.model.dao.sql.query.JoinType;

/**
 * The type Delete query.
 */
public class DeleteQueryImpl extends CommonSqlQueryImpl<Delete> implements Delete {

    /**
     * Instantiates a new Delete query.
     */
    public DeleteQueryImpl(){
        sqlBuilder.append("DELETE");
    }

    public DeleteQueryImpl(Table table){
        sqlBuilder.append("DELETE ").append(table.toString());
    }

    @Override
    protected DeleteQueryImpl get() {
        return this;
    }



}
