package by.epam.bartenderhelper.controller.command.impl.common;

import by.epam.bartenderhelper.controller.command.Command;
import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.Router;
import by.epam.bartenderhelper.controller.command.SessionAttribute;
import by.epam.bartenderhelper.exception.CommandException;
import by.epam.bartenderhelper.model.validator.impl.LocaleValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.bartenderhelper.controller.command.RequestParameter.NEW_LOCALE;

/**
 * The type Change locale command.
 */
public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session =  request.getSession();
        Router router = PagePath.getPreviousPage(request);

        String locale = request.getParameter(NEW_LOCALE);

        if (LocaleValidatorImpl.getInstance().isLocaleValid(locale)) {
            session.setAttribute(SessionAttribute.LOCALE, locale);
        }

        return router;
    }
}
