package by.epam.bartenderhelper.controller.filter;

import by.epam.bartenderhelper.controller.command.PagePath;
import by.epam.bartenderhelper.controller.command.SessionAttribute;
import by.epam.bartenderhelper.model.entity.User;
import by.epam.bartenderhelper.model.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebFilter(urlPatterns = {"/jsp/*"}, filterName = "filter4",//todo url pattern
        dispatcherTypes = {
        DispatcherType.REQUEST//todo forward??
})
public class PageAccessFilter implements Filter {
    public static final Logger logger = LogManager.getLogger();
    private static final String URL_DELIMITER = "/";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        String page = httpServletRequest.getServletPath().split(URL_DELIMITER)[2];
        UserRole pageRole;
        try {
            pageRole = UserRole.defineRole(page);
        }catch (IllegalArgumentException e){
            logger.debug("Check access: common page path");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        User user = (User) session.getAttribute(SessionAttribute.USER);
        UserRole userRole = user.getRole();

        logger.debug("Check access: user role - {}, page role - {}", userRole, pageRole);
        if (userRole == pageRole) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String redirect = httpServletRequest.getContextPath()+ URL_DELIMITER + PagePath.MAIN;
            httpServletResponse.sendRedirect(redirect);
        }
    }
}
