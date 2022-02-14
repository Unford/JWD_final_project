package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.IngredientFormValidator;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

/**
 * The type Ingredient form validator.
 */
public class IngredientFormValidatorImpl extends AbstractFormValidator implements IngredientFormValidator {
    private static final int DESCRIPTION_MAX_LENGTH = 255;
    private static final int DESCRIPTION_MIN_LENGTH = 10;

    private static final int INGREDIENT_NAME_MAX_LENGTH = 40;
    private static final String INGREDIENT_NAME_REGEX = "^([\\p{Upper}А-Я][\\p{Lower}а-я]+)+(?=( ?))(?:\\2[\\p{Lower}а-я]+)+$";
    private static final String PRICE_REGEX = "^\\d{1,4}(\\.\\d{1,2})?$";

    private static IngredientFormValidatorImpl instance;

    private IngredientFormValidatorImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static IngredientFormValidatorImpl getInstance() {
        if (instance == null) {
            instance = new IngredientFormValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isCreateIngredientFormValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isPriceValid(parameters.get(PRICE))) {
            parameters.put(PRICE, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isCalorieValid(parameters.get(CALORIE))) {
            parameters.put(CALORIE, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isNameValid(parameters.get(NAME))) {
            parameters.put(NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isMeasureIdValid(parameters.get(MEASURE))) {
            parameters.put(MEASURE, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isDescriptionValid(parameters.get(INGREDIENT_DESCRIPTION))) {
            parameters.put(INGREDIENT_DESCRIPTION, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isEditIngredientFormValid(Map<String, String> parameters) {
        boolean result = isCreateIngredientFormValid(parameters);
        if (!isPriceValid(parameters.get(INGREDIENT))) {
            parameters.put(INGREDIENT, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isNameValid(String name) {
        return name != null && name.length() <= INGREDIENT_NAME_MAX_LENGTH && name.matches(INGREDIENT_NAME_REGEX);
    }

    @Override
    public boolean isDescriptionValid(String description) {
        return description != null
                && description.length() <= DESCRIPTION_MAX_LENGTH
                && description.length() >= DESCRIPTION_MIN_LENGTH
                && !description.isBlank();
    }

    @Override
    public boolean isMeasureIdValid(String measure) {
        return this.isIntegerValid(measure);
    }

    @Override
    public boolean isPriceValid(String price) {
        return price != null && price.matches(PRICE_REGEX);
    }

    @Override
    public boolean isCalorieValid(String calorie) {
        return isIntegerValid(calorie);
    }


}
