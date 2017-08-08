package servlet;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class XNContextLoader extends ContextLoader {
    public static final String CONTEXT_WRAPPER_CLASS_NAME = "";

    protected WebApplicationContext createWebApplicationContext(ServletContext servletContext, ApplicationContext parent) throws BeansException {
        Class contextClass = determineContextClass(servletContext);
        if(!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
            throw new ApplicationContextException("Custom context class [" + contextClass.getName() + "] is not of type ConfigurableWebApplicationContext");
        }

        ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

        wac.setParent(parent);
        wac.setServletContext(servletContext);
        String configLocationPath = servletContext.getInitParameter("contextConfigLocation");
        String configLocation = "";
        File fld = new File(servletContext.getRealPath(configLocationPath));
        File[] files = fld.listFiles();
        if(files == null) {
            wac.refresh();
            return wac;
        }

        configLocationPath = configLocationPath + "/";
        System.setProperty(servletContext.getInitParameter("webAppRootKey"), servletContext.getRealPath(""));
        for(int i = 0; i < files.length; i++) {
            if(configLocation.length() > 0) {
                configLocation = configLocation + ", ";
            }
            File file = files[i];
            if(!file.getName().toUpperCase().endsWith(".XML")) {
                continue;
            }
            configLocation = configLocation + configLocationPath + file.getName();
        }

        if(configLocation != null) {
            String[] ss = StringUtils.tokenizeToStringArray(configLocation, ",; \t\n");
            System.out.println(Arrays.toString(ss));
            wac.setConfigLocations(ss);
        }

        wac.refresh();
        return wac;
    }

    protected WebApplicationContext createWebApplicationContext_h(ServletContext servletContext, ApplicationContext parent) throws BeansException {
        String contextClassName = servletContext.getInitParameter("contextClass");
        System.out.println("CONTEXT_CLASS_PARAM:contextClass");
        System.out.println("contextClassName:" + contextClassName);
        Class contextClass = XmlWebApplicationContext.class;
        System.out.println("contextClass:" + contextClass);
        if(contextClassName != null) {
            try {
                contextClass = Class.forName(contextClassName, true, Thread.currentThread().getContextClassLoader());
            } catch(ClassNotFoundException ex) {
                throw new ApplicationContextException("Failed to load context class [" + contextClassName + "]", ex);
            }
            if(!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
                throw new ApplicationContextException("Custom context class [" + contextClassName + "] is not of type ConfigurableWebApplicationContext");
            }
        }

        ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

        wac.setParent(parent);
        wac.setServletContext(servletContext);
        String configPath = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("configPath:" + configPath);
        String configLocation = "";
        File fld = new File(servletContext.getRealPath(configPath));
        File[] files = fld.listFiles();
        if(files == null) {
            wac.refresh();
            return wac;
        }

        configPath = configPath + "/";
        for(int i = 0; i < files.length; i++) {
            if(configLocation.length() > 0) {
                configLocation = configLocation + ", ";
            }
            File file = files[i];
            if(!file.getName().toUpperCase().endsWith(".XML")) {
                continue;
            }
            configLocation = configLocation + configPath + file.getName();
        }

        if(configLocation != null) {
            String[] ss = StringUtils.tokenizeToStringArray(configLocation, ",; \t\n");
            System.out.println(Arrays.toString(ss));
            wac.setConfigLocations(ss);
        }

        wac.refresh();
        return wac;
    }

    private static class ErrorInitFloderBeansException extends BeansException {
        public ErrorInitFloderBeansException(String s) {
            super(s);
        }

        public ErrorInitFloderBeansException(String s, Throwable throwable) {
            super(s, throwable);
        }
    }
}