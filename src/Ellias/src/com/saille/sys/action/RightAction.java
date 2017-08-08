package com.saille.sys.action;

import com.saille.sys.Right;
import com.saille.sys.Resource;
import com.saille.sys.Employee;
import com.saille.sys.dao.RightDao;
import com.saille.sys.dao.ResourceDao;
import com.saille.sys.dao.EmployeeDao;
import com.saille.sys.form.RightForm;
import java.util.List;
import java.util.ArrayList;

import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class RightAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private RightDao dao;
    private ResourceDao resourceDao;
    private EmployeeDao employeeDao;

    public RightAction() {
        this.dao = (RightDao) GlobalContext.getContextBean(RightDao.class);
        this.resourceDao = (ResourceDao) GlobalContext.getContextBean(ResourceDao.class);
        this.employeeDao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        RightForm form = (RightForm) _form;
        List<Right> list = dao.findAll();
        form.setRights(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        RightForm form = (RightForm) _form;
        if(form.getId() != 0) {
            Right obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setResourceId(obj.getResourceId());
                form.setOrgId(obj.getOrgId());
                form.setOrgType(obj.getOrgType());
                form.setAuth(obj.getAuth());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        RightForm form = (RightForm) _form;
        Right obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new Right();
            }
        } else {
            obj = new Right();
        }
        obj.setResourceId(form.getResourceId());
        obj.setOrgId(form.getOrgId());
        obj.setOrgType(form.getOrgType());
        obj.setAuth(form.getAuth());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        RightForm form = (RightForm) _form;
        if(form.getId() != 0) {
            Right obj = this.dao.get(form.getId());
            if(obj != null) {
                this.dao.remove(obj.getId());
                form.setMsg("删除成功");
            } else {
                form.setMsg("没有对应的记录");
            }
        }
        return this.list(mapping, _form, request, response);
    }

    public ActionForward table(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        RightForm form = (RightForm) _form;
        Resource r = this.resourceDao.get(1);
        List<Resource> resources = new ArrayList<Resource>();
        resources.add(r);
        resources.addAll(this.resourceDao.findByParentId(r, true));
//        List<Position> list = dao.getAll();
        for(Resource res : resources) {
            res.setRights(RightDao.getCachedRightByResourceId(res.getId()));
        }
        List<Employee> employees = this.employeeDao.findAll();
        int[][] rights = new int[resources.size()][];
        for(int i = 0; i < resources.size(); i++) {
            rights[i] = new int[employees.size()];
            String right = RightDao.getCachedRightByResourceId(resources.get(i).getId());
            if(right == null) {
                for(int j = 0; j < employees.size(); j++) {
                    rights[i][j] = 1;
                }
            } else {
                String[] right2 = right.split(",");
                for(String r2 : right2) {
                    for(int j = 0; j < employees.size(); j++) {
                        if(r2.equals(employees.get(j).getId() + "")) {
                            rights[i][j] = 1;
                        }
                    }
                }
            }
        }
        request.setAttribute("resources", resources);
        request.setAttribute("employees", employees);
        request.setAttribute("rights", rights);
        return mapping.findForward("table");
    }

    public ActionForward refresh(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        RightForm form = (RightForm) _form;
        this.dao.updateAllRight();
        form.setMsg("刷新成功");
        return this.list(mapping, _form, request, response);
    }
}
