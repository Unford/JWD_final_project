package by.epam.bartenderhelper.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The type Encoding filter.
 */
@WebFilter(urlPatterns = { "/*" }, filterName="filter2",initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter implements Filter {
    private static final String REQUEST_ENCODING_PARAM = "encoding";
    private String encoding;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter(REQUEST_ENCODING_PARAM);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);

        String requestEncoding = servletRequest.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
