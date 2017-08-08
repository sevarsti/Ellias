package com.saille.sys.tag;

import com.saille.core.AbstractTag;
import com.saille.sys.dao.PositionDao;
import com.saille.sys.Position;
import com.saille.util.UtilFunctions;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import servlet.GlobalContext;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-7
 * Time: 23:05:30
 * To change this template use File | Settings | File Templates.
 */
public class MultiPositionSelectionTag extends AbstractTag {
    private int rootId = 1;
    private int size = 5;
    private String select = "";
    private String name;
    private List<Integer> positionIds = new ArrayList<Integer>();

    public int doStartTag() throws JspException {
        if(select != null && this.select.length() > 0) {
            String[] ids = select.split(",");
            for(String id : ids) {
                try {
                    int i = Integer.parseInt(id);
                    positionIds.add(i);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        PositionDao dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
        StringBuffer sb = new StringBuffer("<select multiple=\"true\" id=\"").append(name).append("\" name=\"").append(this.name)
                .append("\" size=\"").append(this.size).append("\">");
        Position rootPos = dao.get(rootId);
        addChild(sb, dao, rootPos);
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
        this.rootId = 1;
        this.size = 5;
        this.select = "";
        this.positionIds = new ArrayList<Integer>();
    }

    private void addChild(StringBuffer sb, PositionDao dao, Position pos) {
        if(pos != null) {
            sb.append("<option value=\"").append(pos.getId()).append("\"");
            if(this.positionIds.indexOf(pos.getId()) >= 0) {
                sb.append(" selected");
            }
            sb.append(">").append(UtilFunctions.reptString("&nbsp;", 2 * pos.getLevel())).append(pos.getName()).append("</option>");
            List<Position> list = dao.findByParentId(pos, false);
            for(Position p : list) {
                addChild(sb, dao, p);
            }
        }
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
