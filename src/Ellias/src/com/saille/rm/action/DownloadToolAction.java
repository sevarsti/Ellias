package com.saille.rm.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.AbstractDispatchAction;
import servlet.GlobalContext;
import com.saille.sys.dao.SettingDao;
import com.saille.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2016-1-1
 * Time: 21:59:05
 * To change this template use File | Settings | File Templates.
 */
public class DownloadToolAction extends AbstractDispatchAction {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        try {
            name = new String(name.getBytes("ISO-8859-1"), "GBK");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(name);
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration(request.getRealPath("\\WEB-INF\\rm_toolpath.properties"));
            String path = conf.getString(name);
            if(path != null) {
                SettingDao dao = (SettingDao) GlobalContext.getContextBean(SettingDao.class);
                String dir = dao.get("RM_TOOLPATH").getStrValue();
                String realpath = dir + File.separator + path;
                File logfile = new File("D:\\log\\rmdownload.txt");
                FileUtils.WriteFile(logfile, "\r\n" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + request.getRemoteAddr() + "\t" + name, true);

                FileInputStream fis = new FileInputStream(realpath);
                response.addHeader("Content-Disposition", "attachment; filename=" + new String(path.getBytes("gbk"), "ISO-8859-1"));
                FileCopyUtils.copy(fis, response.getOutputStream());
                try {
                    fis.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
