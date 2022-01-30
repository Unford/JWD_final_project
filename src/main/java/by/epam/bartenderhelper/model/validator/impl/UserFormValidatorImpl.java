package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.UserFormValidator;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

public class UserFormValidatorImpl implements UserFormValidator {

    private static final String INVALID_VALUE_PARAMETER = "";
    private static final String SAFE_SYMBOL_REGEX = "[\\p{Alpha}\\d\\p{Punct}&&[^<>/{}()\\[\\]]]";
    private static final String USERNAME_REGEX = SAFE_SYMBOL_REGEX + "{4,30}";
    private static final String PASSWORD_REGEX = SAFE_SYMBOL_REGEX + "{8,30}";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-я\\p{Lower}]{2,30}";
    private static final String EMAIL_REGEX = "[\\p{Lower}\\d._%+-]+@[\\p{Lower}\\d.-]+\\.[\\p{Lower}]{2,4}$";

    private static UserFormValidatorImpl instance;


    private UserFormValidatorImpl(){
    }

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
        return email != null && email.length() <= 40 && email.matches(EMAIL_REGEX);
    }
}

