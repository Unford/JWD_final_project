package by.epam.bartenderhelper.controller.command;

import by.epam.bartenderhelper.controller.command.impl.ChangeLocaleCommand;
import by.epam.bartenderhelper.controller.command.impl.DefaultCommand;
import by.epam.bartenderhelper.controller.command.impl.LogInCommand;
import by.epam.bartenderhelper.controller.command.impl.SignUpCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {
    LOG_IN(new LogInCommand()),
    SIGN_UP(new SignUpCommand()),
    DEFAULT_COMMAND(new DefaultCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand());

    private static final Logger logger = LogManager.getLogger();

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command defineCommand(String command) {
        CommandType commandType = CommandType.DEFAULT_COMMAND;
        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Unknown command {}", command, e);
        }
        return commandType.getCommand();
    }
}
