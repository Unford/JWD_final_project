package by.epam.bartenderhelper.controller.command.impl.auth;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Delete account command.
 */
public class DeleteAccountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = PagePath.getPreviousPage(request);

        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        if (user != null) {
            UserService userService = UserServiceImpl.getInstance();
            try {
                if (userService.deleteProfile(user)){
                    router = CommandType.SIGN_OUT.getCommand().execute(request);
                }
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }

        return router;
    }
}
