package by.epam.bartenderhelper.model.dao.sql.query.impl;

import by.epam.bartenderhelper.model.dao.sql.query.Delete;

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

    @Override
    protected DeleteQueryImpl get() {
        return this;
    }
}
