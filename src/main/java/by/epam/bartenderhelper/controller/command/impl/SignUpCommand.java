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
        if (validator.isFormSignUpValid(parameters)){
            UserService service = UserServiceImpl.getInstance();
            try {
                if (!service.isUniqueEmail(parameters.get(EMAIL))){
                    logger.debug("Email: {} isn't unique", parameters.get(EMAIL));
                }

                if (!service.isUniqueUsername(parameters.get(USERNAME))){
                    logger.debug("Username: {} isn't unique", parameters.get(USERNAME));
                }

                service.createAccount(new User.UserBuilder()
                        .role(UserRole.CLIENT)
                        .email(parameters.get(EMAIL))
                        .firstName(parameters.get(FIRST_NAME))
                        .lastName(parameters.get(LAST_NAME))
                        .username(parameters.get(USERNAME))
                        .status(User.Status.WORKING).build(), parameters.get(PASSWORD));
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }else {
            router.setPage(PagePath.SIGN_UP);
            router.setType(Router.RouterType.REDIRECT);
        }
        return router;
    }
}
