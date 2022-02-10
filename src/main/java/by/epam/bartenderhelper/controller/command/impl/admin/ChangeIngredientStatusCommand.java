package by.epam.bartenderhelper.controller.command.impl.admin;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.service.IngredientService;
import by.epam.bartenderhelper.model.service.impl.IngredientServiceImpl;
import by.epam.bartenderhelper.model.validator.IngredientFormValidator;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeIngredientStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String ingredientIdText = request.getParameter(RequestParameter.INGREDIENT);
        String valueText = request.getParameter(RequestParameter.VALUE);
        IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();
        if (validator.isIntegerValid(ingredientIdText) && validator.isBooleanValid(valueText)) {
            try {
                long ingredientId = Long.parseLong(ingredientIdText);
                boolean status = Boolean.parseBoolean(valueText);
                IngredientService service = IngredientServiceImpl.getInstance();
                service.changeIngredientStatus(ingredientId, status);
            } catch (NumberFormatException | ServiceException e) {
                logger.error(e);
                throw new CommandException(e);
            }
        }
        return PagePath.getPreviousPage(request);
    }
}
