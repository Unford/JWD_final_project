package by.epam.bartenderhelper.controller.command.impl.auth;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Change password command.
 */
public class ChangePasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String password = request.getParameter(RequestParameter.PASSWORD);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        int message = MessageCode.INVALID_DATA;

        UserFormValidator validator = UserFormValidatorImpl.getInstance();
        if (validator.isPasswordValid(password)) {
            UserService service = UserServiceImpl.getInstance();
            try {
                service.changePassword(user.getId(), password);
                message = MessageCode.UPDATE_PASSWORD_SUCCESS;
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }

        request.setAttribute(RequestParameter.MESSAGE, message);
        return new Router(PagePath.EDIT_PROFILE, Router.RouterType.FORWARD);
    }
}
