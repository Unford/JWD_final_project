package by.epam.bartenderhelper.controller.filter;

import by.epam.bartenderhelper.controller.command.*;
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


/**
 * The type Command access filter.
 */
@WebFilter(urlPatterns = {"/controller"}, filterName = "filter3")
public class CommandAccessFilter implements Filter {
    /**
     * The constant logger.
     */
    public static final Logger logger = LogManager.getLogger();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        User user = (User) session.getAttribute(SessionAttribute.USER);
        UserRole userRole = user.getRole();

        String commandString = httpServletRequest.getParameter(RequestParameter.COMMAND);
        CommandType command = CommandType.valueOf(commandString.toUpperCase());

        logger.debug("Check access: user role - {}, command - {}", userRole, command);

        if (command.isAvailableFor(userRole)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String redirect = httpServletRequest.getContextPath() + PagePath.GO_TO_MAIN;//todo goto
            httpServletResponse.sendRedirect(redirect);
        }


    }
}
