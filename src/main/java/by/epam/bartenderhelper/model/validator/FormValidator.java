package by.epam.bartenderhelper.model.validator;

/**
 * The interface Form validator.
 */
public interface FormValidator {
    /**
     * Is integer valid boolean.
     *
     * @param number the number
     * @return the boolean
     */
    boolean isIntegerValid(String number);

    /**
     * Is double valid boolean.
     *
     * @param number the number
     * @return the boolean
     */
    boolean isDoubleValid(String number);

    /**
     * Is boolean valid boolean.
     *
     * @param value the value
     * @return the boolean
     */
    boolean isBooleanValid(String value);

}
