package by.epam.bartenderhelper.model.validator;

import java.util.Map;

public interface UserFormValidator {
    boolean isFormSignUpValid(Map<String, String> parameters);
    boolean isFormLogInValid(Map<String, String> parameters);
    boolean isFormUpdateValid(Map<String, String> parameters);
    boolean isUsernameValid(String username);
    boolean isPasswordValid(String password);
    boolean isNameValid(String name);
    boolean isEmailValid(String email);
    boolean isIdValid(String id);
    boolean isDescriptionValid(String description);
    boolean isUserProfileValid(String login);
    boolean isUserStatusValid(String status);

}
