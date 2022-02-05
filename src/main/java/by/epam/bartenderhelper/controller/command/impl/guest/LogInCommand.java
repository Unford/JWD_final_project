package by.epam.bartenderhelper.controller.command.impl.guest;

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
import org.apache.logging.log4j.Level;

import java.util.Map;
import java.util.Optional;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

public class LogInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = PagePath.getPreviousPage(request);

        Map<String, String> parameters = extractParameters(request);
        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        int messageNumber = MessageCode.INVALID_DATA;

        if (validator.isFormLogInValid(parameters)) {
            HttpSession session = request.getSession();
            UserService userService = UserServiceImpl.getInstance();
            try {
                Optional<User> user = userService.login(parameters.get(LOGIN), parameters.get(PASSWORD));
                if (user.isPresent()) {
                    if (user.get().getStatus() == User.Status.WORKING && !user.get().isDeleted()) {
                        logger.log(Level.INFO, "User: {} log in", parameters.get(LOGIN));
                        session.setAttribute(SessionAttribute.USER, user.get());
                        return router;
                    } else {
                        messageNumber = MessageCode.USER_DELETED_OR_BANNED;
                    }

                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        router.setType(Router.RouterType.REDIRECT);
        router.setPage(request.getContextPath() + PagePath.GO_TO_LOG_IN.formatted(parameters.get(LOGIN), messageNumber));
        return router;
    }
}
