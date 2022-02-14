package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.*;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import by.epam.bartenderhelper.model.service.IngredientService;
import by.epam.bartenderhelper.model.service.impl.IngredientServiceImpl;
import by.epam.bartenderhelper.model.validator.IngredientFormValidator;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * The type Go to ingredients page command.
 */
public class ShowIngredientsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        IngredientService service = IngredientServiceImpl.getInstance();
        String pageText = request.getParameter(RequestParameter.PAGINATION_PAGE);
        String searchParameter = request.getParameter(RequestParameter.SEARCH);

        IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();
        long page = validator.isIntegerValid(pageText) ? Long.parseLong(pageText) : 1;

            try {
                User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
                boolean status = user.getRole() == UserRole.CLIENT || user.getRole() == UserRole.GUEST;

                List<Ingredient> ingredients = service.findIngredientsByNameAndStatus(searchParameter, status, page);
                long size = service.calculateIngredientsByNameAndStatus(searchParameter, status);
                request.setAttribute(RequestParameter.INGREDIENTS, ingredients);
                request.setAttribute(RequestParameter.INGREDIENTS_SIZE, size);
            } catch (ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }


        return new Router(PagePath.INGREDIENTS_JSP, Router.RouterType.FORWARD);
    }
}
