package by.epam.bartenderhelper.controller.filter;

import by.epam.bartenderhelper.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebFilter(urlPatterns = {"/jsp/*"}, filterName = "filter3",
        dispatcherTypes = {
                DispatcherType.REQUEST,
                DispatcherType.FORWARD,
        })
public class CurrentPageFilter implements Filter {
    public static final Logger logger = LogManager.getLogger();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        String currentPagePath = httpServletRequest.getServletPath();
        logger.debug("Current page is - {}", currentPagePath);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPagePath);

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
