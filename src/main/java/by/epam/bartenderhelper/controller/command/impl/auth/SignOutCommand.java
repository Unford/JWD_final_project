package by.epam.bartenderhelper.controller.command.impl.auth;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.bartenderhelper.controller.command.SessionAttribute.USER;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(USER, new User.UserBuilder().role(UserRole.GUEST).build());
        return new Router();
    }
}
