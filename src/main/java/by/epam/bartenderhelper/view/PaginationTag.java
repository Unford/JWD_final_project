package by.epam.bartenderhelper.view;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

import static by.epam.bartenderhelper.controller.command.ServletContextAttribute.DEFAULT_PAGINATION_NAV_LENGTH;
import static by.epam.bartenderhelper.controller.command.ServletContextAttribute.DEFAULT_PAGINATION_ONE_PAGE_SIZE;

/**
 * The type Pagination tag.
 */
public class PaginationTag extends TagSupport {
    private long itemsSize;
    private String command;
    private int currentPage;
    private String search;

    /**
     * Gets items size.
     *
     * @return the items size
     */
    public long getItemsSize() {
        return itemsSize;
    }

    /**
     * Sets items size.
     *
     * @param itemsSize the items size
     */
    public void setItemsSize(long itemsSize) {
        this.itemsSize = itemsSize;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets current page.
     *
     * @param currentPage the current page
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets search.
     *
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * Sets search.
     *
     * @param search the search
     */
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
