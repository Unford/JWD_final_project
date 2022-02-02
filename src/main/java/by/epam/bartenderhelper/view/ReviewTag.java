package by.epam.bartenderhelper.view;


import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class ReviewTag extends TagSupport {
    private long reviewId;//todo


    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(reviewId +  getClass().toString() + reviewId);


        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
