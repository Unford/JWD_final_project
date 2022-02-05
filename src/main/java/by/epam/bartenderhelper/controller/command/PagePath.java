package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.http.HttpServletRequest;

import static by.epam.bartenderhelper.controller.command.SessionAttribute.CURRENT_PAGE;

public final class PagePath {
    public static final String INDEX = "index.jsp";

    public static final String MAIN = "jsp/main.jsp";
    public static final String COCKTAILS = "jsp/cocktails.jsp";
    public static final String INGREDIENTS = "jsp/ingredients.jsp";
    public static final String SEARCH = "jsp/search.jsp";
    public static final String EDIT_PROFILE = "jsp/client/editProfile.jsp";

    public static final String SIGN_UP = "jsp/guest/registration.jsp";
    public static final String LOG_IN = "jsp/guest/login.jsp";

    public static final String PROFILE = "jsp/client/profile.jsp";

    public static final String GO_TO_LOG_IN = "/controller?command=go_to_login&"
            + RequestParameter.LOGIN + "=%s&"
            + RequestParameter.MESSAGE + "=%d";

    public static final String GO_TO_MAIN = "/controller?command=go_to_main";

    public static final String REVIEW_AJAX = "jsp/include/review.jsp";




    private PagePath() {
    }

    public static Router getPreviousPage(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(request.getSession().getAttribute(CURRENT_PAGE).toString());
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }



}
