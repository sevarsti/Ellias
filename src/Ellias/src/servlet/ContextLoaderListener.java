package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    private XNContextLoader contextLoader;

    public void contextInitialized(ServletContextEvent event) {
        this.contextLoader = createContextLoader();
        try {
            this.contextLoader.initWebApplicationContext(event.getServletContext());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    protected XNContextLoader createContextLoader() {
        return new XNContextLoader();
    }

    public XNContextLoader getContextLoader() {
        return this.contextLoader;
    }

    public void contextDestroyed(ServletContextEvent event) {
        if(this.contextLoader != null) {
            this.contextLoader.closeWebApplicationContext(event.getServletContext());
        }
    }
}