package by.epam.bartenderhelper.view;

import by.epam.bartenderhelper.controller.command.SessionAttribute;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.decimal4j.util.DoubleRounder;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * The type Date format tag.
 */
public class DateFormatTag extends TagSupport {
    private Instant value;

    /**
     * Gets value.
     *
     * @return the value
     */
    public Instant getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Instant value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            String localeStr = pageContext.getSession().getAttribute(SessionAttribute.LOCALE).toString().toUpperCase();
            Locale locale = Locale.forLanguageTag(localeStr);

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                            .withLocale(locale)
                            .withZone(ZoneId.systemDefault());
            out.print(formatter.format(value));

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
