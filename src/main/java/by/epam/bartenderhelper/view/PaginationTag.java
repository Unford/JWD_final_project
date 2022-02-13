package by.epam.bartenderhelper.view;

import by.epam.bartenderhelper.controller.command.ServletContextAttribute;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.decimal4j.util.DoubleRounder;

import java.io.IOException;
import java.util.Arrays;

import static by.epam.bartenderhelper.controller.command.ServletContextAttribute.DEFAULT_PAGINATION_NAV_LENGTH;
import static by.epam.bartenderhelper.controller.command.ServletContextAttribute.DEFAULT_PAGINATION_ONE_PAGE_SIZE;

public class PaginationTag extends TagSupport {
    private long itemsSize;
    private String command;
    private int currentPage;
    private String search;

    public long getItemsSize() {
        return itemsSize;
    }

    public void setItemsSize(long itemsSize) {
        this.itemsSize = itemsSize;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {

            int totalPages = (int) Math.ceil((double) itemsSize / DEFAULT_PAGINATION_ONE_PAGE_SIZE);
            if (totalPages != 1){
                if (currentPage < 1) {
                    currentPage = 1;
                } else if (currentPage > totalPages) {
                    currentPage = totalPages;
                }
                int startPage;
                int endPage;
                if (totalPages <= DEFAULT_PAGINATION_NAV_LENGTH) {
                    startPage = 1;
                    endPage = totalPages;
                } else {
                    int maxPagesBeforeCurrentPage = (int) Math.floor((double) DEFAULT_PAGINATION_NAV_LENGTH / 2);
                    int maxPagesAfterCurrentPage = (int) Math.ceil((double) DEFAULT_PAGINATION_NAV_LENGTH / 2) - 1;
                    if (currentPage <= maxPagesBeforeCurrentPage) {
                        startPage = 1;
                        endPage = DEFAULT_PAGINATION_NAV_LENGTH;
                    } else if (currentPage + maxPagesAfterCurrentPage >= totalPages) {
                        startPage = totalPages - DEFAULT_PAGINATION_NAV_LENGTH + 1;
                        endPage = totalPages;
                    } else {
                        startPage = currentPage - maxPagesBeforeCurrentPage;
                        endPage = currentPage + maxPagesAfterCurrentPage;
                    }
                }

                int[] pages = new int[(endPage + 1) - startPage];
                for (int i = 0; i < pages.length; i++) {
                    pages[i] = startPage + i;
                }
                String contextPath = pageContext.getServletContext().getContextPath();
                StringBuilder queryBuilder = new StringBuilder(contextPath)
                        .append("/controller?command=")
                        .append(command)
                        .append("&page=%d");

                if (!search.isEmpty()){
                    queryBuilder.append("&search=")
                            .append(search);
                }

                out.print("<nav aria-label=\"\">\n");
                out.println("<ul class=\"pagination justify-content-center\" id=\"pagination\">");
                for (int page : pages) {
                    String liItem = "<li class=\"page-item %s\">".formatted(page == currentPage ? "active" : "");
                    out.println(liItem);
                    String refItem = "<a class=\"page-link\" href=\"%s\">%d</a>\n".formatted(queryBuilder , page).formatted(page);
                    out.print(refItem);
                    out.print("</li>");
                }


                out.print("</ul>\n</nav>");
            }

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}
