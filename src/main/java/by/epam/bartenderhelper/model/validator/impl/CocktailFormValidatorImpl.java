package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.CocktailFormValidator;

import java.util.Map;

public class CocktailFormValidatorImpl extends AbstractFormValidator implements CocktailFormValidator {
    private static final int DESCRIPTION_MAX_LENGTH = 255;
    private static final int DESCRIPTION_MIN_LENGTH = 10;

    private static final int COCKTAIL_NAME_MAX_LENGTH = 30;
    private static final String COCKTAIL_NAME_REGEX = "^([\\p{Upper}А-Я][\\p{Lower}а-я]+)+(?=( ?))(?:\\2[\\p{Lower}а-я]+)+$";
    private static final String AMOUNT_REGEX = "^\\d{1,4}(\\.\\d{1,2})?$";

    private static CocktailFormValidatorImpl instance;

    private CocktailFormValidatorImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CocktailFormValidatorImpl getInstance() {
        if (instance == null) {
            instance = new CocktailFormValidatorImpl();
        }
        return instance;
    }


    @Override
    public boolean isCreateCocktailFormValid(Map<String, String> parameters) {
        return false;
    }

    @Override
    public boolean isDescriptionValid(String description) {
        return description != null
                && description.length() <= DESCRIPTION_MAX_LENGTH
                && description.length() >= DESCRIPTION_MIN_LENGTH
                && !description.isBlank();
    }

    @Override
    public boolean isNameValid(String name) {
        return name != null && name.length() <= COCKTAIL_NAME_MAX_LENGTH && name.matches(COCKTAIL_NAME_REGEX);

    }

    @Override
    public boolean isRecipeValid(String recipe) {
        return recipe != null
                && recipe.length() <= DESCRIPTION_MAX_LENGTH
                && recipe.length() >= DESCRIPTION_MIN_LENGTH
                && !recipe.isBlank();
    }

    @Override
    public boolean isIngredientIdValid(String id) {
        return isIntegerValid(id);
    }

    @Override
    public boolean isIngredientAmountValid(String amount) {
        return amount != null && amount.matches(AMOUNT_REGEX);
    }
}
