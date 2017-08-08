package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
    FilterConfig config = null;

    private String targetEncoding = "GBK";

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.targetEncoding = config.getInitParameter("encoding");
    }

    public void destroy() {
        this.config = null;
        this.targetEncoding = null;
    }

    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        request.setCharacterEncoding(this.targetEncoding);
        chain.doFilter(srequest, sresponse);
    }
}