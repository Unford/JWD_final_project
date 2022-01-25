package by.epam.bartenderhelper.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.Enumeration;


@WebFilter(urlPatterns = {"/controller"}, filterName = "filter2")
public class XssFilter implements Filter {
    private static final String XSS_ATTACK_REGEX = "[<>/]";
    private static final String EMPTY_STRING = "";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        CustomHttpServletRequest httpServletRequest = new CustomHttpServletRequest(servletRequest);
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        parameterNames.asIterator().forEachRemaining(s -> {
            String safeAttribute = servletRequest.getParameter(s).replaceAll(XSS_ATTACK_REGEX, EMPTY_STRING);
            httpServletRequest.addParameter(s, safeAttribute);
        });
        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
