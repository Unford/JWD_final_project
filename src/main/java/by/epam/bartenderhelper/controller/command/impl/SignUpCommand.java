package by.epam.bartenderhelper.controller.command.impl;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static by.epam.bartenderhelper.controller.command.RequestParameter.*;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String, String> parameters = extractParameters(request);

        return router;
    }
}
