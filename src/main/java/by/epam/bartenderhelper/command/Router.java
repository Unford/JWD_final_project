package by.epam.bartenderhelper.command;

public class Router {
    enum DispathType {
        FORWARD, REDIRECT
    }
    //private String page = INDEX;todo
    private String page = "index.jsp";

    private DispathType type = DispathType.FORWARD;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public DispathType getType() {
        return type;
    }

    public void setRedirect() {
        this.type = DispathType.REDIRECT;
    }
}
