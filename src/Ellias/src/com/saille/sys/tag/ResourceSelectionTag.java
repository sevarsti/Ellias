package com.saille.sys.tag;

import com.saille.core.AbstractTag;
import com.saille.sys.dao.ResourceDao;
import com.saille.sys.Resource;
import com.saille.util.UtilFunctions;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import servlet.GlobalContext;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-12-22
 * Time: 22:31:28
 * To change this template use File | Settings | File Templates.
 */
public class ResourceSelectionTag extends AbstractTag {
    private int rootId = 1;
    private int value = 0;
    private String name = "";

    public int doStartTag() throws JspException {
        ResourceDao dao = (ResourceDao) GlobalContext.getContextBean(ResourceDao.class);
        StringBuffer sb = new StringBuffer("<select id=\"").append(name).append("\" name=\"").append(this.name).append("\">");
        sb.append("<option value=\"0\">--ѡ--</option>");
        Resource res = dao.get(this.rootId);
        addChild(sb, dao, res);
        sb.append("</select>");
        JspWriter _writer = this.pageContext.getOut();
        try {
            _writer.println(sb.toString());
        } catch(IOException e) {
            throw new JspException(e);
        }
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {
        return BodyTagSupport.EVAL_PAGE;
    }

    public void release() {
        this.name = null;
    }

    private void addChild(StringBuffer sb, ResourceDao dao, Resource res) {
//        Resource res = dao.get(resId);
        if(res != null) {
//            addChild(sb, dao, posId);
            sb.append("<option value=\"").append(res.getId()).append("\"").append((res.getId() == this.value) ? " selected" : "").append(">").append(UtilFunctions.reptString("&nbsp;", 2 * res.getLevel())).append(res.getName()).append("</option>");
            List<Resource> list = dao.findByParentId(res, false);
            for(Resource r : list) {
                addChild(sb, dao, r);
            }
        }
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
