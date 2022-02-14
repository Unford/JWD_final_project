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
import by.epam.bartenderhelper.model.validator.IngredientFormValidator;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * The type Show administration command.
 */
public class ShowAdministrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String pageText = request.getParameter(RequestParameter.PAGINATION_PAGE);
        IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();
        long page = validator.isIntegerValid(pageText) ? Long.parseLong(pageText) : 1;
        try {
            UserService service = UserServiceImpl.getInstance();
            List<User> users = service.findPartOfAllUsers(page);
            long size = service.calculateUsersSize();
            request.setAttribute(RequestParameter.USERS, users);
            request.setAttribute(RequestParameter.USERS_SIZE, size);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        return new Router(PagePath.ADMINISTRATION, Router.RouterType.FORWARD);
    }
}
