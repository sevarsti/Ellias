package servlet;

import java.io.File;
import java.io.PrintStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.digester.Digester;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;

public class XNActionServlet extends ActionServlet {
    protected ModuleConfig initModuleConfig(String prefix, String paths) throws ServletException {
        System.out.println("Initializing module path '" + prefix + "' configuration from '" + paths + "'");

        ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
        ModuleConfig config = factoryObject.createModuleConfig(prefix);

        Digester digester = initConfigDigester();

        while(paths.length() > 0) {
            digester.push(config);
            String path = null;
            int comma = paths.indexOf(',');
            if(comma >= 0) {
                path = paths.substring(0, comma).trim();
                paths = paths.substring(comma + 1);
            } else {
                path = paths.trim();
                paths = "";
            }

            if(path.length() < 1) {
                break;
            }

            String realPath = getServletContext().getRealPath(path);
            File folder = new File(realPath);

            String filePath = path;
            if(folder.isDirectory()) {
                File[] files = folder.listFiles();
                for(File file : files) {
                    if(!file.getName().toLowerCase().endsWith(".xml")) {
                        continue;
                    }
                    if((!path.endsWith("/")) && (!path.endsWith("\\"))) {
                        path = path + "/";
                    }
                    path = path + file.getName();

                    parseModuleConfigFile(digester, path);
                    digester.push(config);
                    path = filePath;
                }

            } else {
                parseModuleConfigFile(digester, path);
            }
        }

        getServletContext().setAttribute("org.apache.struts.action.MODULE" + config.getPrefix(), config);

        FormBeanConfig[] fbs = config.findFormBeanConfigs();
        for(int i = 0; i < fbs.length; i++) {
            if(fbs[i].getDynamic()) {
                fbs[i].getDynaActionFormClass();
            }
        }

        return config;
    }
}