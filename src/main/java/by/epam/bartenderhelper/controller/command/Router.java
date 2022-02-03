package by.epam.bartenderhelper.controller.command;

import static by.epam.bartenderhelper.controller.command.PagePath.INDEX;

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT, ERROR
    }

    private String page = INDEX;
    private RouterType type = RouterType.FORWARD;
    private int errorCode = 404;

    public Router(){
    }

    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public RouterType getType() {
        return type;
    }

    public void setType(RouterType type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
