package by.epam.bartenderhelper.controller.filter;

import by.epam.bartenderhelper.controller.command.CommandType;
import by.epam.bartenderhelper.controller.command.RequestParameter;
import by.epam.bartenderhelper.controller.command.SessionAttribute;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebFilter(urlPatterns = {"/controller"}, filterName = "filter4")
public class CurrentPageFilter implements Filter {
    public static final Logger logger = LogManager.getLogger();
    private static final char QUESTION_MARK = '?';
    private static final String GET_METHOD = "GET";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        String queryString = httpServletRequest.getQueryString();
        String servletPath = httpServletRequest.getServletPath();
        String contextPath = httpServletRequest.getContextPath();

        String command = servletRequest.getParameter(RequestParameter.COMMAND);
        String localeCommand = CommandType.CHANGE_LOCALE.toString().toLowerCase();

        if (httpServletRequest.getMethod().equals(GET_METHOD) && !command.equals(localeCommand)) {
            StringBuilder builder = new StringBuilder();
            builder.append(contextPath).append(servletPath).append(QUESTION_MARK).append(queryString);
            logger.debug("Current page is - {}", builder);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, builder.toString());
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
