package by.epam.bartenderhelper.controller.command.impl.admin;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.USER;
import static by.epam.bartenderhelper.controller.command.RequestParameter.STATUS;

/**
 * The type Change account status command.
 */
public class ChangeAccountStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = PagePath.getPreviousPage(request);
        String idParameter = request.getParameter(USER);
        String statusParameter = request.getParameter(STATUS).toUpperCase();

        UserService userService = UserServiceImpl.getInstance();
        UserFormValidator validator = UserFormValidatorImpl.getInstance();

        if (validator.isIdValid(idParameter) && validator.isUserStatusValid(statusParameter)) {
            try {
                long id = Long.parseLong(idParameter);
                User.Status status = User.Status.valueOf(statusParameter);
                userService.changeUserStatus(id, status);
            } catch (IllegalArgumentException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }


        return router;
    }
}
