package by.epam.bartenderhelper.model.dao.sql.query;


/**
 * The enum Logic operator.
 */
public enum LogicOperator {
    /**
     * Equals logic operator.
     */
    EQUALS,
    /**
     * Greater logic operator.
     */
    GREATER,
    /**
     * Less logic operator.
     */
    LESS,
    /**
     * Greater or equals logic operator.
     */
    GREATER_OR_EQUALS,
    /**
     * Less or equals logic operator.
     */
    LESS_OR_EQUALS,
    /**
     * Not equals logic operator.
     */
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
