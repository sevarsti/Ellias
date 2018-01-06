package servlet;

//import com.saille.sys.dao.ResourceDao;
//import com.saille.sys.Resource;
//import com.saille.sys.Employee;

import com.saille.sys.Employee;
import com.saille.sys.Resource;
import com.saille.sys.dao.ResourceDao;
import com.saille.sys.dao.RightDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.saille.util.UtilFunctions;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
    private final static Pattern pattern = Pattern.compile("[a-zA-Z]+://[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}.+");
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
        Object empObj = request.getSession().getAttribute("employee");
        String remoteAddr = request.getRemoteAddr();
        String localAddr = request.getRequestURL().toString();
        boolean needReject = checkBlacklist(remoteAddr, localAddr);
        if(!needReject) {
            return;
        }
        if(!"127.0.0.1".equals(request.getRemoteAddr())) {
            doLog(request);
        }
        boolean hasRight = checkRight(url, empObj == null ? 0 : ((Employee)empObj).getId());
        if(!hasRight) {
            response.sendRedirect("/error.jsp?errormsg=noright");
            return;
        }
        chain.doFilter(srequest, sresponse);
    }

    private boolean checkBlacklist(String remoteip, String requesturl) {
        Matcher m = pattern.matcher(requesturl);
        if(m.matches()) {
            LOGGER.info(remoteip + "：禁止使用IP访问");
            return false;
        }
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        int count = jt.queryForInt("select count(1) from sys_blackip where ip = ?", new Object[]{remoteip});
        if(count > 0) {
            LOGGER.info(remoteip + "：黑名单禁止访问");
            return false;
        }
        return true;
    }

    private boolean checkRight(String url, int empId) {
        ResourceDao resourceDao = ResourceDao.getInstance();
        List<Resource> resources = resourceDao.findByUrl(url);
        if(resources.size() == 0) {
            return true;
        }
        RightDao rightDao = RightDao.getInstance();
        for(int i = 0; i < resources.size(); i++) {
            if(rightDao.hasRight(resources.get(i).getId(), empId)) {
                return true;
            }
        }
        return false;
    }

    private void doLog(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        if(request.getRequestURI().endsWith("/dwr/call/plaincall/RMDwr.fromServer.dwr")) {
            return;
        }
        if(request.getRequestURI().endsWith(".ico")) {
            return;
        }
        if(request.getRequestURI().endsWith(".gif")) {
            return;
        }
        if(request.getRequestURI().endsWith(".js")) {
            return;
        }
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