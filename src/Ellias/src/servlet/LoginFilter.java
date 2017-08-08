package servlet;

//import com.saille.sys.dao.ResourceDao;
//import com.saille.sys.Resource;
//import com.saille.sys.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saille.util.UtilFunctions;

public class LoginFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
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
        String url = request.getRequestURI();
        String method = request.getParameter("method");
        Socket s = new Socket();
        String username = String.valueOf(request.getSession().getAttribute("username"));
        if(username.equals("Ellias")) {
            ;
        }
        if(!"127.0.0.1".equals(request.getRemoteAddr())) {
            doLog(request);
        }
        chain.doFilter(srequest, sresponse);
    }

    private void doLog(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String msg = sdf.format(new Date()) + request.getRemoteAddr() + "\t" +request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getRequestURI();
        if(request.getParameterMap() != null && request.getParameterMap().size() > 0) {
            Set keys = request.getParameterMap().keySet();
            for(Object k : keys) {
                msg += "\n\t" + k + "=" + request.getParameter(k.toString());
            }
        }
//        try {
//            BufferedReader r = request.getReader();
//            char[] chars = new char[20];
//            r.mark(chars.length + 1);
//            String queryString = "";
//            int i;
//            i = r.read(chars);
//            queryString += String.valueOf(chars, 0, i);
//            msg += "\n\tqueryString=" + queryString;
//            r.reset();
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
        LOGGER.info(msg);
    }
}