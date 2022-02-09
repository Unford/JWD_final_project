package by.epam.bartenderhelper.model.validator;

import java.util.Map;

public interface IngredientFormValidator extends FormValidator{
    boolean isCreateIngredientFormValid(Map<String, String> parameters);
    boolean isNameValid(String name);
    boolean isDescriptionValid(String description);
    boolean isMeasureIdValid(String measure);
    boolean isPriceValid(String price);
    boolean isCalorieValid(String calorie);


}
