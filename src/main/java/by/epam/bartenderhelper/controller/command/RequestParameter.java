package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public final class RequestParameter {
    public static final Logger logger = LogManager.getLogger();

    public static final String COMMAND = "command";

    //signUp
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String OLD_PARAMETERS = "parameters";

    public static final String NOT_UNIQUE_EMAIL = "unique_email_error";
    public static final String NOT_UNIQUE_USERNAME = "unique_username_error";

    //header
    public static final String NEW_LOCALE = "locale";

    //log in
    public static final String LOGIN = "login";
    public static final String MESSAGE = "m";

    //profile
    public static final String USER_PROFILE = "user";



    private RequestParameter(){}

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
