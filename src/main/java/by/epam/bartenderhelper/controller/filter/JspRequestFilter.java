package by.epam.bartenderhelper.controller.filter;

import by.epam.bartenderhelper.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The type Jsp request filter.
 */
@WebFilter(urlPatterns = { "/jsp/*" }, filterName="filter1", dispatcherTypes = DispatcherType.REQUEST)
public class JspRequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        var session = httpServletRequest.getSession();

        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        httpServletResponse.sendRedirect(currentPage);


    }
}
