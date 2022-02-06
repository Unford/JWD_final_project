package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The type Default command.
 */
public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router();
    }
}
