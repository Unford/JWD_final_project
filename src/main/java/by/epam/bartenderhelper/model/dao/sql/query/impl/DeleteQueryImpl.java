package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.query.Delete;

public class DeleteQueryImpl extends CommonSqlQueryImpl<Delete> implements Delete {

    public DeleteQueryImpl(){
        sqlBuilder.append("DELETE");
    }

    @Override
    protected DeleteQueryImpl get() {
        return this;
    }
}