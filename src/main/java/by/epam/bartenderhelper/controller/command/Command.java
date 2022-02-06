package by.epam.bartenderhelper.controller.command;

import by.epam.bartenderhelper.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger();

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
