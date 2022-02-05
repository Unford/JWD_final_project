package by.epam.bartenderhelper.controller.command;

import by.epam.bartenderhelper.controller.command.impl.admin.ChangeAccountStatusCommand;
import by.epam.bartenderhelper.controller.command.impl.auth.*;
import by.epam.bartenderhelper.controller.command.impl.common.ChangeLocaleCommand;
import by.epam.bartenderhelper.controller.command.impl.common.DefaultCommand;
import by.epam.bartenderhelper.controller.command.impl.common.SearchCommand;
import by.epam.bartenderhelper.controller.command.impl.common.ShowProfileCommand;
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

public enum CommandType {
    GO_TO_LOGIN(new GoToLoginPageCommand(), GUEST),
    GO_TO_MAIN(new GoToMainPageCommand()),
    GO_TO_SIGN_UP(new GoToSignUpPageCommand(), GUEST),
    GO_TO_COCKTAILS(new GoToCocktailsPageCommand()),
    GO_TO_INGREDIENTS(new GoToIngredientsPageCommand()),
    GO_TO_SEARCH(new GoToSearchPageCommand()),
    GO_TO_EDIT_PROFILE(new GoToEditProfilePageCommand(), CLIENT, BARTENDER, ADMIN),

    SHOW_PROFILE(new ShowProfileCommand()),
    SEARCH(new SearchCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    DEFAULT_COMMAND(new DefaultCommand()),

    LOG_IN(new LogInCommand(), GUEST),
    SIGN_UP(new SignUpCommand(), GUEST),

    DELETE_ACCOUNT(new DeleteAccountCommand(), CLIENT, ADMIN, BARTENDER),
    SIGN_OUT(new SignOutCommand(), CLIENT, ADMIN, BARTENDER),//todo all???
    EDIT_PROFILE(new EditProfileCommand(), CLIENT, ADMIN, BARTENDER),
    CHANGE_PASSWORD(new ChangePasswordCommand(), CLIENT, ADMIN, BARTENDER),
    SEND_REVIEW(new SendReviewCommand(), CLIENT, ADMIN, BARTENDER),

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

    public Command getCommand() {
        return command;
    }

    public EnumSet<UserRole> getUserRoles() {
        return userRoles;
    }

    public boolean isAvailableFor(UserRole role) {
        return this.userRoles.contains(role);
    }

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
