package by.epam.bartenderhelper.model.validator;

import java.util.Map;

public interface CocktailFormValidator extends FormValidator {
    boolean isCreateCocktailFormValid(Map<String, String> parameters);

    boolean isDescriptionValid(String description);

    boolean isNameValid(String name);

    boolean isRecipeValid(String description);

    boolean isIngredientIdValid(String id);

    boolean isIngredientAmountValid(String amount);


}
