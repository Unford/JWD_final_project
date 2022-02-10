package by.epam.bartenderhelper.controller.command.impl.move;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Ingredient;
import by.epam.bartenderhelper.model.entity.Measure;
import by.epam.bartenderhelper.model.service.IngredientService;
import by.epam.bartenderhelper.model.service.impl.IngredientServiceImpl;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public class GoToEditIngredientPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.EDIT_INGREDIENT_JSP, Router.RouterType.ERROR);
        String ingredientIdText = request.getParameter(RequestParameter.INGREDIENT);
        IngredientFormValidatorImpl validator = IngredientFormValidatorImpl.getInstance();
        if (validator.isIntegerValid(ingredientIdText)){
            IngredientService service = IngredientServiceImpl.getInstance();
            try {
                long ingredientId = Long.parseLong(ingredientIdText);
                Optional<Ingredient> ingredient = service.findIngredientById(ingredientId);
                if (ingredient.isPresent()){
                    List<Measure> measures = service.findAllMeasures();
                    router.setType(Router.RouterType.FORWARD);
                    request.setAttribute(RequestParameter.INGREDIENT, ingredient.get());
                    request.setAttribute(RequestParameter.MEASURES, measures);
                }
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        return router;
    }
}
