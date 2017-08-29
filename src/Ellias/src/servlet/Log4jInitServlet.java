package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class Log4jInitServlet extends HttpServlet {
    private static String DEFAULT_PATH = File.separator + "WEB-INF" + File.separator + "log4j.properties";

    public void init() throws ServletException {
        super.init();

        String prefix = getServletContext().getRealPath("/");

        String path = getInitParameter("log4j-path");
        path.replaceAll("/", File.separator + File.separator);

        if((null == path) || (path.length() == 0)) {
            path = DEFAULT_PATH;
        }
        path = prefix + path;

        PropertyConfigurator.configure(path);
    }
}