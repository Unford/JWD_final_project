package by.epam.bartenderhelper.exception;

/**
 * The type Command exception.
 */
public class CommandException extends Exception{
    /**
     * Instantiates a new Command exception.
     */
    public CommandException() {
        super();
    }

    /**
     * Instantiates a new Command exception.
     *
     * @param message the message
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Command exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Command exception.
     *
     * @param cause the cause
     */
    public CommandException(Throwable cause) {
        super(cause);
    }
}
