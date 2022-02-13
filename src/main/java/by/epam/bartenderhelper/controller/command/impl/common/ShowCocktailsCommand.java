package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Go to cocktails page command.
 */
public class ShowCocktailsCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.COCKTAILS_JSP, Router.RouterType.FORWARD);
    }
}
