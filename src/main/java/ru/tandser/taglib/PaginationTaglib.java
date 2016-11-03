package ru.tandser.taglib;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTaglib extends SimpleTagSupport {
    private String uri;
    private int offset;
    private int count;
    private int max = 10;
    private int steps = 10;
    private String previous = "Previous";
    private String next = "Next";

    private Writer getWriter() {
        return getJspContext().getOut();
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();

        try {
            out.write("<nav>");
            out.write("<ul class=\"pagination\">");

            if (offset < steps)
                out.write(constructLink(1, previous, "disabled", true));
            else
                out.write(constructLink(offset - steps, previous, null, false));

            for (int i = 0; i < count; i += steps) {
                if(offset == i)
                    out.write(constructLink((i / 10 + 1) - steps, String.valueOf(i / 10 + 1), "active", true));
                else
                    out.write(constructLink(i / 10 * steps, String.valueOf(i / 10 + 1), null, false));
            }

            if (offset + steps >= count)
                out.write(constructLink(offset + steps, next, "disabled", true));
            else
                out.write(constructLink(offset + steps, next, null, false));

            out.write("</ul>");
            out.write("</nav>");
        } catch (java.io.IOException exc) {
            throw new JspException("Error in Paginator tag", exc);
        }
    }

    private String constructLink(int page, String text, String className, boolean disabled) {
        StringBuilder link = new StringBuilder("<li");

        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }

        if (disabled) {
            link.append(">");
            link.append("<a href=\"#\">");
            link.append(text);
            link.append("</a></li>");
        } else {
            link.append(">");
            link.append("<a href=\"");
            link.append(uri);
            link.append("?offset=");
            link.append(page);
            link.append("\">");
            link.append(text);
            link.append("</a></li>");
        }

        return link.toString();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
