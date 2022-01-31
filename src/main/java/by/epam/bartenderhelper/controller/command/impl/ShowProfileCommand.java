package by.epam.bartenderhelper.controller.command.impl;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class ShowProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
