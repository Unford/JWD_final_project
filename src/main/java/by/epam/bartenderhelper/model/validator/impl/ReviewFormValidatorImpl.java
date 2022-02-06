package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.ReviewFormValidator;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;
import static by.epam.bartenderhelper.model.validator.impl.CommonRegex.ENTITY_ID_REGEX;

/**
 * The type Review form validator.
 */
public class ReviewFormValidatorImpl implements ReviewFormValidator {
    private static final int MESSAGE_MAX_LENGTH = 255;
    private static final String RATING_VALUE_REGEX = "^([1-5]|([0-5]\\.((?<!0\\.)0|(?<!5\\.)5)))$";

    private static ReviewFormValidatorImpl instance;

    private ReviewFormValidatorImpl(){
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ReviewFormValidatorImpl getInstance() {
        if (instance == null){
            instance = new ReviewFormValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isReviewFormValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isTargetIdValid(parameters.get(USER))) {
            parameters.put(USER, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isRatingValid(parameters.get(RATING))) {
            parameters.put(RATING, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isMessageValid(parameters.get(REVIEW_MESSAGE))) {
            parameters.put(REVIEW_MESSAGE, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isTargetIdValid(String id) {
        return id != null && id.matches(ENTITY_ID_REGEX);
    }

    @Override
    public boolean isRatingValid(String rating) {
        return rating != null && rating.matches(RATING_VALUE_REGEX);
    }

    @Override
    public boolean isMessageValid(String message) {
        return message != null && message.length() <= MESSAGE_MAX_LENGTH;
    }
}
