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
    public static final String INDEX_JSP = "index.jsp";

    /**
     * The constant MAIN.
     */
    public static final String MAIN_JSP = "jsp/main.jsp";
    /**
     * The constant COCKTAILS.
     */
    public static final String COCKTAILS_JSP = "jsp/cocktails.jsp";
    /**
     * The constant INGREDIENTS.
     */
    public static final String INGREDIENTS_JSP = "jsp/ingredients.jsp";

    public static final String ADD_INGREDIENT_JSP = "jsp/bartender/addNewIngredient.jsp";

    /**
     * The constant SEARCH.
     */
    public static final String SEARCH_JSP = "jsp/search.jsp";
    /**
     * The constant EDIT_PROFILE.
     */
    public static final String EDIT_PROFILE_JSP = "jsp/client/editProfile.jsp";

    /**
     * The constant SIGN_UP.
     */
    public static final String REGISTRATION_JSP = "jsp/guest/registration.jsp";
    /**
     * The constant LOG_IN.
     */
    public static final String LOGIN_JSP = "jsp/guest/login.jsp";

    /**
     * The constant PROFILE.
     */
    public static final String PROFILE_JSP = "jsp/client/profile.jsp";

    public static final String INGREDIENT_JSP = "jsp/ingredient.jsp";

    public static final String EDIT_INGREDIENT_JSP = "jsp/admin/editIngredient.jsp";


    /**
     * The constant GO_TO_LOG_IN.
     */
    public static final String GO_TO_LOG_IN = "/controller?command=go_to_login&"
            + RequestParameter.LOGIN + "=%s&"
            + RequestParameter.MESSAGE + "=%d";

    public static final String GO_TO_EDIT_PROFILE = "/controller?command=go_to_edit_profile";

    public static final String SHOW_INGREDIENT = "/controller?command=show_ingredient&"
            + RequestParameter.INGREDIENT + "=%s";

    /**
     * The constant GO_TO_MAIN.
     */
    public static final String GO_TO_MAIN = "/controller?command=go_to_main";

    public static final String GO_TO_ADD_INGREDIENT = "/controller?command=go_to_add_ingredient";
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
