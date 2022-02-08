package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Request parameter.
 */
public final class RequestParameter {
    /**
     * The constant logger.
     */
    public static final Logger logger = LogManager.getLogger();

    /**
     * The constant COMMAND.
     */
    public static final String COMMAND = "command";
    /**
     * The constant INVALID_VALUE_PARAMETER.
     */
    public static final String INVALID_VALUE_PARAMETER = "";

    /**
     * The constant FIRST_NAME.
     */
//signUp
    public static final String FIRST_NAME = "first_name";
    /**
     * The constant LAST_NAME.
     */
    public static final String LAST_NAME = "last_name";
    /**
     * The constant EMAIL.
     */
    public static final String EMAIL = "email";
    /**
     * The constant USERNAME.
     */
    public static final String USERNAME = "username";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "password";
    /**
     * The constant OLD_PARAMETERS.
     */
    public static final String OLD_PARAMETERS = "parameters";

    /**
     * The constant NOT_UNIQUE_EMAIL.
     */
    public static final String NOT_UNIQUE_EMAIL = "unique_email_error";
    /**
     * The constant NOT_UNIQUE_USERNAME.
     */
    public static final String NOT_UNIQUE_USERNAME = "unique_username_error";

    /**
     * The constant NEW_LOCALE.
     */
//header
    public static final String NEW_LOCALE = "locale";

    /**
     * The constant LOGIN.
     */
//log in
    public static final String LOGIN = "login";
    /**
     * The constant MESSAGE.
     */
    public static final String MESSAGE = "m";

    /**
     * The constant USER.
     */
//profile
    public static final String USER = "user";

    /**
     * The constant STATUS.
     */
    public static final String STATUS = "status";
    /**
     * The constant AVATAR.
     */
    public static final String AVATAR = "avatar";
    /**
     * The constant USER_DESCRIPTION.
     */
    public static final String USER_DESCRIPTION = "about_me";

    /**
     * The constant RATING.
     */
//rating
    public static final String RATING = "rating";
    /**
     * The constant COMMENT.
     */
    public static final String COMMENT = "comment";
    /**
     * The constant REVIEW_MESSAGE.
     */
    public static final String REVIEW_MESSAGE = "review_message";
    /**
     * The constant MY_REVIEW.
     */
    public static final String MY_REVIEW = "my_review";
    /**
     * The constant REVIEWS.
     */
    public static final String REVIEWS = "reviews";

    public static final String PAGINATION_PAGE = "page";

    private RequestParameter(){}

    /**
     * Extract parameters map.
     *
     * @param request the request
     * @return the map
     */
    public static Map<String, String> extractParameters(HttpServletRequest request){
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        parameterNames.asIterator().forEachRemaining(s -> {
            parameters.put(s, request.getParameter(s));
        });
        logger.debug("{} request parameters {}",request.getParameter(COMMAND) ,parameters);
        return parameters;
    }

}
