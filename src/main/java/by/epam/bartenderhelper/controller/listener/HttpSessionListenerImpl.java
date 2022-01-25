package by.epam.bartenderhelper.controller.listener;

import by.epam.bartenderhelper.controller.command.SessionAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(SessionAttribute.LOCALE, DEFAULT_LOCALE);
    }
}
