package by.epam.bartenderhelper.model.validator;

import java.util.Map;

/**
 * The interface User form validator.
 */
public interface UserFormValidator extends FormValidator{
    /**
     * Is form sign up valid boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    boolean isFormSignUpValid(Map<String, String> parameters);

    /**
     * Is form log in valid boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    boolean isFormLogInValid(Map<String, String> parameters);

    /**
     * Is form update valid boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     */
    boolean isFormUpdateValid(Map<String, String> parameters);

    /**
     * Is username valid boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean isUsernameValid(String username);

    /**
     * Is password valid boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isPasswordValid(String password);

    /**
     * Is name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isNameValid(String name);

    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isEmailValid(String email);

    /**
     * Is id valid boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean isIdValid(String id);

    /**
     * Is description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    boolean isDescriptionValid(String description);

    /**
     * Is user profile valid boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean isUserProfileValid(String login);

    /**
     * Is user status valid boolean.
     *
     * @param status the status
     * @return the boolean
     */
    boolean isUserStatusValid(String status);

}
