package by.epam.bartenderhelper.controller.command.impl.move;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.exception.ServiceException;
import by.epam.bartenderhelper.model.entity.Measure;
import by.epam.bartenderhelper.model.service.impl.IngredientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * The type Go to add ingredient page command.
 */
public class GoToAddIngredientPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            IngredientServiceImpl service = IngredientServiceImpl.getInstance();
            List<Measure> measures = service.findAllMeasures();
            request.setAttribute(RequestParameter.MEASURES, measures);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        return new Router(PagePath.ADD_INGREDIENT_JSP, Router.RouterType.FORWARD);

    }
}
