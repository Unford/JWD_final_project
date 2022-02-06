package by.epam.bartenderhelper.controller.listener;

import by.epam.bartenderhelper.controller.command.SessionAttribute;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * The type Http session listener.
 */
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        session.setAttribute(SessionAttribute.LOCALE, DEFAULT_LOCALE);
        session.setAttribute(SessionAttribute.USER, new User.UserBuilder().role(UserRole.GUEST).build());
    }


}
