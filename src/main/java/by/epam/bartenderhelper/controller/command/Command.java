package by.epam.bartenderhelper.controller.command;

import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Command {
    Logger logger = LogManager.getLogger();
    Router execute(HttpServletRequest request) throws CommandException;
}
