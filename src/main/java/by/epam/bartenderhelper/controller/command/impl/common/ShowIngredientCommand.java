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

import java.util.Optional;

public class ShowIngredientCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.INGREDIENT, Router.RouterType.ERROR);

        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String ingredientIdText = request.getParameter(RequestParameter.INGREDIENT);
        IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();

        if (validator.isIntegerValid(ingredientIdText)) {
            IngredientService service = IngredientServiceImpl.getInstance();
            try {
                long ingredientId = Long.parseLong(ingredientIdText);
                Optional<Ingredient> ingredient = service.findIngredientById(ingredientId);
                if (ingredient.isPresent()) {
                    if (ingredient.get().isVerified()
                            || currentUser.getRole() == UserRole.ADMIN
                            || currentUser.getRole() == UserRole.BARTENDER) {
                        request.setAttribute(RequestParameter.INGREDIENT, ingredient.get());
                        router.setType(Router.RouterType.FORWARD);
                    }

                }
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }

        }
        return router;
    }
}
