package by.epam.bartenderhelper.model.validator;

import java.util.Map;

/**
 * The interface Review form validator.
 */
public interface ReviewFormValidator extends FormValidator{
    /**
     * Is review form valid boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    boolean isReviewFormValid(Map<String, String> parameters);

    /**
     * Is target id valid boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean isTargetIdValid(String id);

    /**
     * Is rating valid boolean.
     *
     * @param rating the rating
     * @return the boolean
     */
    boolean isRatingValid(String rating);

    /**
     * Is message valid boolean.
     *
     * @param message the message
     * @return the boolean
     */
    boolean isMessageValid(String message);


}
