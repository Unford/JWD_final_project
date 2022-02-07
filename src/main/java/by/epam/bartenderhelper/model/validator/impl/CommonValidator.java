package by.epam.bartenderhelper.model.validator.impl;

/**
 * The type Common regex.
 */
public class CommonValidator {
    /**
     * The constant ENTITY_ID_REGEX.
     */
    static final String INTEGER_REGEX = "\\d+";

    private static CommonValidator instance;

    private CommonValidator() {
    }

    public static CommonValidator getInstance() {
        if (instance == null) {
            instance = new CommonValidator();
        }
        return instance;
    }

    public boolean isIntegerValid(String number) {
        return number != null && number.matches(INTEGER_REGEX);
    }
}
