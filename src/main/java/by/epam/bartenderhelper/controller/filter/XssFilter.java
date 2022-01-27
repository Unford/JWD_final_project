package by.epam.bartenderhelper.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@WebFilter(urlPatterns = {"/controller"}, filterName = "filter2")
public class XssFilter implements Filter {
    private static final String XSS_ATTACK_REGEX = "[<>/]";
    private static final String EMPTY_STRING = "";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        class CustomHttpServletRequest extends HttpServletRequestWrapper{
            private final Map<String, String[]> allParameters;

            public CustomHttpServletRequest(ServletRequest request) {
                super((HttpServletRequest) request);
                allParameters = new HashMap<>();
                allParameters.putAll(request.getParameterMap());
            }

            public void addParameter(String name, String parameter) {
                allParameters.put(name, new String[]{parameter});
            }

            @Override
            public String getParameter(String name) {
                String[] strings = getParameterMap().get(name);
                if (strings != null) {
                    return strings[0];
                }
                return super.getParameter(name);
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return Collections.unmodifiableMap(allParameters);
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(getParameterMap().keySet());
            }

            @Override
            public String[] getParameterValues(String name) {
                return getParameterMap().get(name);
            }
        }

        CustomHttpServletRequest httpServletRequest = new CustomHttpServletRequest(servletRequest);

        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        parameterNames.asIterator().forEachRemaining(s -> {
            String safeParameter = servletRequest.getParameter(s).replaceAll(XSS_ATTACK_REGEX, EMPTY_STRING);
            httpServletRequest.addParameter(s, safeParameter);
        });

        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
