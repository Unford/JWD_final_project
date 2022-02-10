package by.epam.bartenderhelper.controller.command.impl.move;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Go to ingredients page command.
 */
public class GoToIngredientsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.INGREDIENTS_JSP, Router.RouterType.FORWARD);
    }
}
