package com.saille.sys.action;

import com.saille.sys.EmpPosition;
import com.saille.sys.dao.EmpPositionDao;
import com.saille.sys.form.EmpPositionForm;
import java.util.List;
import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class EmpPositionAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private EmpPositionDao dao;

    public EmpPositionAction() {
        this.dao = (EmpPositionDao) GlobalContext.getContextBean(EmpPositionDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmpPositionForm form = (EmpPositionForm) _form;
        List<EmpPosition> list = dao.findAll();
        form.setEmpPositions(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmpPositionForm form = (EmpPositionForm) _form;
        if(form.getId() != 0) {
            EmpPosition obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setEmpId(obj.getEmpId());
                form.setPositionId(obj.getPositionId());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmpPositionForm form = (EmpPositionForm) _form;
        EmpPosition obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new EmpPosition();
            }
        } else {
            obj = new EmpPosition();
        }
        obj.setEmpId(form.getEmpId());
        obj.setPositionId(form.getPositionId());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmpPositionForm form = (EmpPositionForm) _form;
        if(form.getId() != 0) {
            EmpPosition obj = this.dao.get(form.getId());
            if(obj != null) {
                this.dao.remove(obj.getId());
                form.setMsg("删除成功");
            } else {
                form.setMsg("没有对应的记录");
            }
        }
        return this.list(mapping, _form, request, response);
    }
}
