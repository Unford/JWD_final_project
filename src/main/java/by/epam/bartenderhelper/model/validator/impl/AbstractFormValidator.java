package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.FormValidator;

/**
 * The type Common regex.
 */
public abstract class AbstractFormValidator implements FormValidator {
    /**
     * The constant ENTITY_ID_REGEX.
     */
    static final String INTEGER_REGEX = "\\d+";
    private static final String DOUBLE_REGEX = "^\\d+\\.?\\d+$";

    public boolean isIntegerValid(String number) {
        return number != null && number.matches(INTEGER_REGEX);
    }

    @Override
    public boolean isDoubleValid(String number) {
        return number != null && number.matches(DOUBLE_REGEX);
    }
}
