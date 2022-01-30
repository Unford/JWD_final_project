package by.epam.bartenderhelper.controller.command.impl;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String, String> parameters = extractParameters(request);//todo
        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        if (validator.isFormSignUpValid(parameters)) {
            UserService service = UserServiceImpl.getInstance();
            try {
                String email = parameters.get(EMAIL);
                String username = parameters.get(USERNAME);
                boolean uniqueParameters = true;

                if (!service.isUniqueEmail(email)) {
                    logger.debug("Email: {} isn't unique", email);
                    request.setAttribute(NOT_UNIQUE_EMAIL, true);
                    uniqueParameters = false;
                }
                if (!service.isUniqueUsername(username)) {
                    logger.debug("Username: {} isn't unique", username);
                    request.setAttribute(NOT_UNIQUE_USERNAME, true);
                    uniqueParameters = false;
                }

                if (uniqueParameters) {
                    service.createAccount(new User.UserBuilder()
                                    .role(UserRole.CLIENT)
                                    .email(parameters.get(EMAIL))
                                    .firstName(parameters.get(FIRST_NAME))
                                    .lastName(parameters.get(LAST_NAME))
                                    .username(parameters.get(USERNAME))
                                    .status(User.Status.WORKING).build(),
                            parameters.get(PASSWORD));

                    router.setPage(PagePath.MAIN);//todo
                    router.setType(Router.RouterType.REDIRECT);
                    return router;
                }

            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }

        }
        request.setAttribute(OLD_PARAMETERS, parameters);
        router.setPage(PagePath.SIGN_UP);

        return router;
    }
}
