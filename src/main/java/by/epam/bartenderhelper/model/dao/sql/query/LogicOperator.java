package by.epam.bartenderhelper.model.dao.sql.query;


public enum LogicOperator {
    EQUALS,
    GREATER,
    LESS,
    GREATER_OR_EQUALS,
    LESS_OR_EQUALS,
    NOT_EQUALS;

    public String toString(){
        String result = null;
        switch (this){
            case EQUALS -> result = "=";
            case GREATER -> result = ">";
            case LESS -> result = "<";
            case GREATER_OR_EQUALS -> result = ">=";
            case LESS_OR_EQUALS -> result = "<=";
            case NOT_EQUALS -> result = "<>";
        }
        return result;
    }
}
