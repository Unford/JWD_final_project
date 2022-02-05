package by.epam.bartenderhelper.model.validator;

import java.util.Map;

public interface ReviewFormValidator {
    boolean isReviewFormValid(Map<String, String> parameters);
    boolean isTargetIdValid(String id);
    boolean isRatingValid(String rating);
    boolean isMessageValid(String message);


}
