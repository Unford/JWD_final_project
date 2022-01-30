package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public final class PagePath {
    private  PagePath(){}

    public static final String INDEX = "index.jsp";
    public static final String MAIN = "jsp/main.jsp";
    public static final String SIGN_UP = "jsp/registration.jsp";

    private static final String HEADER_REFERER = "referer";

    public static Router getPreviousPage(HttpServletRequest request){
        Router router = new Router();
        router.setPage(request.getHeader(HEADER_REFERER));
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }

}
