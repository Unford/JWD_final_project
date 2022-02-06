package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.validator.UserFormValidator;

import java.util.Arrays;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;
import static by.epam.bartenderhelper.model.validator.impl.CommonRegex.ENTITY_ID_REGEX;

/**
 * The type User form validator.
 */
public class UserFormValidatorImpl implements UserFormValidator {
    private static final int EMAIL_MAX_LENGTH = 40;
    private static final int DESCRIPTION_MAX_LENGTH = 255;

    private static final String SAFE_SYMBOL_REGEX = "[\\p{Alpha}\\d\\p{Punct}&&[^<>/{}()\\[\\]]]";
    private static final String USERNAME_REGEX = SAFE_SYMBOL_REGEX + "{4,30}";
    private static final String PASSWORD_REGEX = SAFE_SYMBOL_REGEX + "{8,30}";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-я\\p{Lower}]{2,30}";
    private static final String EMAIL_REGEX = "[\\p{Lower}\\d._%+-]+@[\\p{Lower}\\d.-]+\\.[\\p{Lower}]{2,4}$";
    private static final String PROFILE_REGEX = ENTITY_ID_REGEX + "|" + USERNAME_REGEX;

    private static UserFormValidatorImpl instance;


    private UserFormValidatorImpl(){
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserFormValidatorImpl getInstance() {
        if (instance == null){
            instance = new UserFormValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isFormSignUpValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isUsernameValid(parameters.get(USERNAME))) {
            parameters.put(USERNAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isPasswordValid(parameters.get(PASSWORD))) {
            parameters.put(PASSWORD, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isEmailValid(parameters.get(EMAIL))) {
            parameters.put(EMAIL, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isNameValid(parameters.get(FIRST_NAME))) {
            parameters.put(FIRST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isNameValid(parameters.get(LAST_NAME))) {
            parameters.put(LAST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isFormLogInValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isUsernameValid(parameters.get(LOGIN)) && !isEmailValid(parameters.get(LOGIN))) {
            parameters.put(LOGIN, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isPasswordValid(parameters.get(PASSWORD))) {
            parameters.put(PASSWORD, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isFormUpdateValid(Map<String, String> parameters) {
        boolean result = true;
        if (!isNameValid(parameters.get(FIRST_NAME))) {
            parameters.put(FIRST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isNameValid(parameters.get(LAST_NAME))) {
            parameters.put(LAST_NAME, INVALID_VALUE_PARAMETER);
            result = false;
        }
        if (!isDescriptionValid(parameters.get(USER_DESCRIPTION))) {
            parameters.put(USER_DESCRIPTION, INVALID_VALUE_PARAMETER);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isUsernameValid(String username) {
        return username != null && username.matches(USERNAME_REGEX);
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean isEmailValid(String email) {
        return email != null && email.length() <= EMAIL_MAX_LENGTH && email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean isDescriptionValid(String description) {
        return description != null && description.length() <= DESCRIPTION_MAX_LENGTH;
    }


    @Override
    public boolean isIdValid(String id) {
        return id != null && id.matches(ENTITY_ID_REGEX);
    }

    @Override
    public boolean isUserProfileValid(String login) {
        return login != null && login.matches(PROFILE_REGEX);
    }

    @Override
    public boolean isUserStatusValid(String status) {
        return status != null && Arrays.stream(User.Status.values())
                .anyMatch(s -> s.name().equals(status));
    }
}

