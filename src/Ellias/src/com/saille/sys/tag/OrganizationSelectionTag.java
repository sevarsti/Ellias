package com.saille.sys.tag;

import com.saille.core.AbstractTag;
import com.saille.sys.dao.EmployeeDao;
import com.saille.sys.dao.PositionDao;
import com.saille.sys.Position;
import com.saille.sys.Employee;
import com.saille.util.UtilFunctions;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import servlet.GlobalContext;

import java.util.List;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-12-21
 * Time: 0:02:42
 * To change this template use File | Settings | File Templates.
 */
public class OrganizationSelectionTag extends AbstractTag {
    private boolean includeEmployee = false;
    private int rootId = 1;
    private String name = "";
    private String value = "0";

    public int doStartTag() throws JspException {
        PositionDao dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
        EmployeeDao empDao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        StringBuffer sb = new StringBuffer("<select id=\"").append(name).append("\" name=\"").append(this.name).append("\">");
        sb.append("<option value=\"0\">--ѡ--</option>");
        Position rootPos = dao.get(rootId);
        addChild(sb, dao, empDao, rootPos);
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

    private void addChild(StringBuffer sb, PositionDao dao, EmployeeDao empDao, Position pos) {
//        Position pos = dao.get(posId);
        if(pos != null) {
//            addChild(sb, dao, posId);
            sb.append("<option value=\"").append(includeEmployee ? ("1-" + pos.getId()) : (pos.getId())).append("\"");
            if(this.value.equals(pos.getId() + "") || this.value.equals("1-" + pos.getId())) {
                sb.append(" selected");
            }
            sb.append(">").append(UtilFunctions.reptString("&nbsp;", 2 * pos.getLevel())).append(pos.getName()).append("</option>");
            if(this.includeEmployee) {
                List<Employee> emps = empDao.findEmployeesByPositionId(pos.getId(), false);
                for(Employee emp : emps) {
                    sb.append("<option value=\"").append(includeEmployee ? ("2-" + emp.getId()) : emp.getId()).append("\"");
                    if(this.value.equals(emp.getId() + "") || this.value.equals("2-" + emp.getId())) {
                        sb.append(" selected");
                    }
                    sb.append(">").append(UtilFunctions.reptString("&nbsp;", 2 * pos.getLevel())).append("->").append(emp.getName()).append("</option>");
                }
            }
//            pos.setLevel(0);
            List<Position> list = dao.findByParentId(pos, false);
            for(Position p : list) {
                addChild(sb, dao, empDao, p);
            }
        }
    }

    public boolean isIncludeEmployee() {
        return includeEmployee;
    }

    public void setIncludeEmployee(boolean includeEmployee) {
        this.includeEmployee = includeEmployee;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
