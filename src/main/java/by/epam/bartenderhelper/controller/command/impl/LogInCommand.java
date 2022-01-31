package by.epam.bartenderhelper.controller.command.impl;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

public class LogInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = PagePath.getPreviousPage(request);
        Map<String, String> parameters = extractParameters(request);
        logger.debug("log in request parameters {}", parameters);
        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        if (validator.isFormLogInValid(parameters)) {
            HttpSession session = request.getSession();
            UserService userService = UserServiceImpl.getInstance();
            try {
                Optional<User> user = userService.checkUser(parameters.get(LOGIN), parameters.get(PASSWORD));
                if (user.isPresent()){
                    session.setAttribute(SessionAttribute.USER, user.get());
                    return router;
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }

        router.setPage(PagePath.LOG_IN);
        router.setType(Router.RouterType.FORWARD);
        request.setAttribute(LOGIN, parameters.get(LOGIN));
        return router;
    }
}
