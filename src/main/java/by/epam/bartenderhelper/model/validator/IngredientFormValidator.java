package by.epam.bartenderhelper.model.validator;

import java.util.Map;

/**
 * The interface Ingredient form validator.
 */
public interface IngredientFormValidator extends FormValidator{
    /**
     * Is create ingredient form valid boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    boolean isCreateIngredientFormValid(Map<String, String> parameters);

    /**
     * Is edit ingredient form valid boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    boolean isEditIngredientFormValid(Map<String, String> parameters);

    /**
     * Is name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isNameValid(String name);

    /**
     * Is description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    boolean isDescriptionValid(String description);

    /**
     * Is measure id valid boolean.
     *
     * @param measure the measure
     * @return the boolean
     */
    boolean isMeasureIdValid(String measure);

    /**
     * Is price valid boolean.
     *
     * @param price the price
     * @return the boolean
     */
    boolean isPriceValid(String price);

    /**
     * Is calorie valid boolean.
     *
     * @param calorie the calorie
     * @return the boolean
     */
    boolean isCalorieValid(String calorie);


}
