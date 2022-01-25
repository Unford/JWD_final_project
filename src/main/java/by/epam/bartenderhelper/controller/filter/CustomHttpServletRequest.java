package by.epam.bartenderhelper.controller.filter;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletRequest extends HttpServletRequestWrapper {
    private final Map<String, String[]> modifiableParameters;
    private Map<String, String[]> allParameters = null;

    public CustomHttpServletRequest(ServletRequest request) {
        super((HttpServletRequest) request);
        modifiableParameters = new HashMap<>();
        modifiableParameters.putAll(request.getParameterMap());
    }

    public void addParameter(String name, String parameter){
        modifiableParameters.put(name, new String[]{parameter});
    }

    @Override
    public String getParameter(String name)
    {
        String[] strings = getParameterMap().get(name);
        if (strings != null)
        {
            return strings[0];
        }
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap()
    {

            allParameters = new HashMap<>();
            allParameters.putAll(super.getParameterMap());
            allParameters.putAll(modifiableParameters);

        return Collections.unmodifiableMap(allParameters);
    }

    @Override
    public Enumeration<String> getParameterNames()
    {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(String name)
    {
        return getParameterMap().get(name);
    }
}
