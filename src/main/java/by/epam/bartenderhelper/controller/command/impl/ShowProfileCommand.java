package by.epam.bartenderhelper.controller.command.impl;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import by.epam.bartenderhelper.model.service.UserService;
import by.epam.bartenderhelper.model.service.impl.UserServiceImpl;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ShowProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setType(Router.RouterType.ERROR);

        String user = request.getParameter(RequestParameter.USER_PROFILE);
        User userProfile = null;
        UserFormValidator validator = UserFormValidatorImpl.getInstance();

        if (user != null) {
            if (validator.isUserProfileValid(user)) {
                UserService service = UserServiceImpl.getInstance();
                try {
                    Optional<User> optionalUser = service.findUserProfile(user);
                    if (optionalUser.isPresent()) {
                        userProfile = optionalUser.get();
                    }
                } catch (ServiceException e) {
                    logger.error(e);
                    throw new CommandException(e);
                }
            }
        } else {
            User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
            userProfile = currentUser.getRole() != UserRole.GUEST ? currentUser : null;
        }

        if (userProfile != null) {
            request.setAttribute(RequestParameter.USER_PROFILE, userProfile);
            router.setPage(PagePath.PROFILE);
            router.setType(Router.RouterType.FORWARD);
        }

        return router;
    }
}
