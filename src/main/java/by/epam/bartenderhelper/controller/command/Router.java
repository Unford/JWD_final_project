package by.epam.bartenderhelper.controller.command;

import static by.epam.bartenderhelper.controller.command.PagePath.INDEX;

/**
 * The type Router.
 */
public class Router {
    /**
     * The enum Router type.
     */
    public enum RouterType {
        /**
         * Forward router type.
         */
        FORWARD,
        /**
         * Redirect router type.
         */
        REDIRECT,
        /**
         * Error router type.
         */
        ERROR
    }

    private String page = INDEX;
    private RouterType type = RouterType.FORWARD;
    private int errorCode = 404;

    /**
     * Instantiates a new Router.
     */
    public Router(){
    }

    /**
     * Instantiates a new Router.
     *
     * @param page the page
     * @param type the type
     */
    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public RouterType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(RouterType type) {
        this.type = type;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
