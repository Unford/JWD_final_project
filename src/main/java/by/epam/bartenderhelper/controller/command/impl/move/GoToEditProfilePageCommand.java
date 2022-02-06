package by.epam.bartenderhelper.controller.command.impl.move;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Go to edit profile page command.
 */
public class GoToEditProfilePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.EDIT_PROFILE, Router.RouterType.FORWARD);
    }
}
