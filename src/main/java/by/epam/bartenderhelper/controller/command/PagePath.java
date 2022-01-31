package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public final class PagePath {

    private PagePath() {
    }

    public static final String INDEX = "index.jsp";
    public static final String MAIN = "jsp/main.jsp";
    public static final String SIGN_UP = "jsp/guest/registration.jsp";
    public static final String LOG_IN = "jsp/guest/login.jsp";

    public static final String LOG_IN_REDIRECT = "/controller?command=go_to_login&"
            + RequestParameter.LOGIN + "=%s&"
            + RequestParameter.MESSAGE + "=%d";


    private static final String HEADER_REFERER = "referer";

    public static Router getPreviousPage(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(request.getHeader(HEADER_REFERER));
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }

}
