package com.saille.sys.tag;

import com.saille.sys.Resource;
import com.saille.sys.Employee;
import com.saille.sys.dao.ResourceDao;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspException;

import servlet.GlobalContext;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-2-1
 * Time: 1:33:13
 * To change this template use File | Settings | File Templates.
 */
public class CheckMenuRightTag extends TagSupport {
    private int resourceId;
    private String resourcePath;
    
    public int doStartTag() throws JspException {
        ResourceDao dao = (ResourceDao) GlobalContext.getContextBean(ResourceDao.class);
        Resource res = null;
        if(this.resourceId != 0) {
            res = dao.get(this.resourceId);
        } else {
            int parentId = Resource.ROOT_ID;
            String[] names = this.resourcePath.split("\\/");
            for(String name : names) {
                res = dao.findByNameParentId(name, parentId);
                if(res == null) {
                    return BodyTagSupport.SKIP_BODY;
                }
                parentId = res.getId();
            }
        }
        if(res == null) {
            return BodyTagSupport.SKIP_BODY;
        }
        Employee emp = (Employee) this.pageContext.getSession().getAttribute("employee");
        try {
            String path = this.getClass().getResource("/").getPath();
            path = path.substring(0, path.length() - 1);
            path = path.substring(0, path.lastIndexOf("/"));
            path = path.substring(0, path.lastIndexOf("/"));
            path = path + "/cache/" + res.getId() + ".txt";
            File f = new File(path);
            if(f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String in = br.readLine();
                boolean found = false;
                if(in != null && in.length() > 0) {
                    String[] emps = in.split(",");
                    for(String s : emps) {
                        if(s.equals("" + emp.getId())) {
                            found = true;
                            break;
                        }
                    }
                }
                if(!found) {
                    return BodyTagSupport.SKIP_BODY;
                } else {
                    return BodyTagSupport.EVAL_BODY_INCLUDE;
                }
            } else {
                return BodyTagSupport.EVAL_BODY_INCLUDE;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return BodyTagSupport.SKIP_BODY;
        }
    }

    public void release() {
        this.resourceId = 0;
        this.resourcePath = null;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
