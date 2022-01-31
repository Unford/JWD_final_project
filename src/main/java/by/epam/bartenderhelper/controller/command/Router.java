package by.epam.bartenderhelper.controller.command;

import static by.epam.bartenderhelper.controller.command.PagePath.INDEX;

public class Router {
    public Router(){
    }

    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    public enum RouterType {
        FORWARD, REDIRECT
    }
    private String page = INDEX;

    private RouterType type = RouterType.FORWARD;

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
}
