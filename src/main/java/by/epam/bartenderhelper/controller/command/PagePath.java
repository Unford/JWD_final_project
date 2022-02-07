package by.epam.bartenderhelper.controller.command;

import jakarta.servlet.http.HttpServletRequest;

import static by.epam.bartenderhelper.controller.command.SessionAttribute.CURRENT_PAGE;

/**
 * The type Page path.
 */
public final class PagePath {
    /**
     * The constant INDEX.
     */
    public static final String INDEX = "index.jsp";

    /**
     * The constant MAIN.
     */
    public static final String MAIN = "jsp/main.jsp";
    /**
     * The constant COCKTAILS.
     */
    public static final String COCKTAILS = "jsp/cocktails.jsp";
    /**
     * The constant INGREDIENTS.
     */
    public static final String INGREDIENTS = "jsp/ingredients.jsp";
    /**
     * The constant SEARCH.
     */
    public static final String SEARCH = "jsp/search.jsp";
    /**
     * The constant EDIT_PROFILE.
     */
    public static final String EDIT_PROFILE = "jsp/client/editProfile.jsp";

    /**
     * The constant SIGN_UP.
     */
    public static final String SIGN_UP = "jsp/guest/registration.jsp";
    /**
     * The constant LOG_IN.
     */
    public static final String LOG_IN = "jsp/guest/login.jsp";

    /**
     * The constant PROFILE.
     */
    public static final String PROFILE = "jsp/client/profile.jsp";

    /**
     * The constant GO_TO_LOG_IN.
     */
    public static final String GO_TO_LOG_IN = "/controller?command=go_to_login&"
            + RequestParameter.LOGIN + "=%s&"
            + RequestParameter.MESSAGE + "=%d";

    /**
     * The constant GO_TO_MAIN.
     */
    public static final String GO_TO_MAIN = "/controller?command=go_to_main";

    /**
     * The constant REVIEW_AJAX.
     */
    public static final String REVIEW_AJAX = "jsp/include/review_response.jsp";




    private PagePath() {
    }

    /**
     * Gets previous page.
     *
     * @param request the request
     * @return the previous page
     */
    public static Router getPreviousPage(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(request.getSession().getAttribute(CURRENT_PAGE).toString());
        router.setType(Router.RouterType.REDIRECT);
        return router;
    }



}
