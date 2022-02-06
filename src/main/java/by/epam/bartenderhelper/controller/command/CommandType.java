package by.epam.bartenderhelper.controller.command;

import by.epam.bartenderhelper.controller.command.impl.admin.ChangeAccountStatusCommand;
import by.epam.bartenderhelper.controller.command.impl.auth.*;
import by.epam.bartenderhelper.controller.command.impl.common.*;
import by.epam.bartenderhelper.controller.command.impl.guest.LogInCommand;
import by.epam.bartenderhelper.controller.command.impl.guest.SignUpCommand;
import by.epam.bartenderhelper.controller.command.impl.move.*;

import by.epam.bartenderhelper.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.EnumSet;

import static by.epam.bartenderhelper.model.entity.UserRole.*;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Go to login.
     */
    GO_TO_LOGIN(new GoToLoginPageCommand(), GUEST),
    /**
     * The Go to main.
     */
    GO_TO_MAIN(new GoToMainPageCommand()),
    /**
     * The Go to sign up.
     */
    GO_TO_SIGN_UP(new GoToSignUpPageCommand(), GUEST),
    /**
     * The Go to cocktails.
     */
    GO_TO_COCKTAILS(new GoToCocktailsPageCommand()),
    /**
     * The Go to ingredients.
     */
    GO_TO_INGREDIENTS(new GoToIngredientsPageCommand()),
    /**
     * The Go to search.
     */
    GO_TO_SEARCH(new GoToSearchPageCommand()),
    /**
     * The Go to edit profile.
     */
    GO_TO_EDIT_PROFILE(new GoToEditProfilePageCommand(), CLIENT, BARTENDER, ADMIN),

    /**
     * The Show user reviews.
     */
    SHOW_USER_REVIEWS(new GetUserReviewsCommand()),
    /**
     * The Show profile.
     */
    SHOW_PROFILE(new ShowProfileCommand()),
    /**
     * The Search.
     */
    SEARCH(new SearchCommand()),
    /**
     * The Change locale.
     */
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    /**
     * The Default command.
     */
    DEFAULT_COMMAND(new DefaultCommand()),

    /**
     * The Log in.
     */
    LOG_IN(new LogInCommand(), GUEST),
    /**
     * The Sign up.
     */
    SIGN_UP(new SignUpCommand(), GUEST),

    /**
     * The Delete account.
     */
    DELETE_ACCOUNT(new DeleteAccountCommand(), CLIENT, ADMIN, BARTENDER),
    /**
     * The Sign out.
     */
    SIGN_OUT(new SignOutCommand(), CLIENT, ADMIN, BARTENDER),//todo all???
    /**
     * The Edit profile.
     */
    EDIT_PROFILE(new EditProfileCommand(), CLIENT, ADMIN, BARTENDER),
    /**
     * The Change password.
     */
    CHANGE_PASSWORD(new ChangePasswordCommand(), CLIENT, ADMIN, BARTENDER),
    /**
     * The Send review.
     */
    SEND_REVIEW(new SendReviewCommand(), CLIENT, ADMIN, BARTENDER),

    /**
     * The Change account status.
     */
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand(), ADMIN);


    private static final Logger logger = LogManager.getLogger();

    private final Command command;
    private final EnumSet<UserRole> userRoles;

    CommandType(Command command) {
        this.command = command;
        this.userRoles = EnumSet.allOf(UserRole.class);
    }

    CommandType(Command command, UserRole... roles) {
        this.command = command;
        this.userRoles = EnumSet.noneOf(UserRole.class);
        this.userRoles.addAll(Arrays.asList(roles));
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Gets user roles.
     *
     * @return the user roles
     */
    public EnumSet<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * Is available for boolean.
     *
     * @param role the role
     * @return the boolean
     */
    public boolean isAvailableFor(UserRole role) {
        return this.userRoles.contains(role);
    }

    /**
     * Define command command.
     *
     * @param command the command
     * @return the command
     */
    public static Command defineCommand(String command) {
        CommandType commandType = CommandType.DEFAULT_COMMAND;
        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Unknown command {}", command, e);
        }
        logger.log(Level.DEBUG, commandType);
        return commandType.getCommand();
    }
}
