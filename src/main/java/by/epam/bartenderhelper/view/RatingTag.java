package by.epam.bartenderhelper.view;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import org.decimal4j.util.DoubleRounder;

import java.io.IOException;

public class RatingTag extends TagSupport {
    private double value;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = DoubleRounder.round(value, 2);
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            int integralPart = (int) value;

            for (int i = 0; i < integralPart; i++) {
                out.print("<i class=\"fa fa-star rating-color\"></i>");
            }
            if (DoubleRounder.round(value, 0) > value){
                out.print("<i class=\"fa fa-star-half-o\" aria-hidden=\"true\"></i>");
            }
            out.print("(%.1f)".formatted(value));



        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
