package servlet;

import com.saille.weixin.WeixinHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Socket;

public class WeixinFilter implements Filter {
    String login;
    String[] excludes;

    public void init(FilterConfig config) throws ServletException {
        System.out.println("start............");
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        HttpServletResponse response = (HttpServletResponse) sresponse;
        Socket s = new Socket();
        String username = String.valueOf(request.getSession().getAttribute("username"));
        if(request.getRequestURI().equals("/weixinapi/")) {
            WeixinHandler.processRequest(request, response);
            return;
        }
        chain.doFilter(srequest, sresponse);
    }
}
