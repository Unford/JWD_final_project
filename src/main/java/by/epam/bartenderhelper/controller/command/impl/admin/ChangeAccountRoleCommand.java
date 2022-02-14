package by.epam.bartenderhelper.controller.command.impl.admin;

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

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

/**
 * The type Change account role command.
 */
public class ChangeAccountRoleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = PagePath.getPreviousPage(request);
        String idParameter = request.getParameter(USER);
        String roleParameter = request.getParameter(ROLE).toUpperCase();

        UserService userService = UserServiceImpl.getInstance();
        UserFormValidator validator = UserFormValidatorImpl.getInstance();

        if (validator.isIdValid(idParameter) && validator.isUserRoleValid(roleParameter)) {
            try {
                long id = Long.parseLong(idParameter);
                UserRole role = UserRole.defineRole(roleParameter);
                userService.changeUserRole(id, role);
            } catch (IllegalArgumentException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        return router;
    }
}
