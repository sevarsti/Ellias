package com.saille.sys.action;

import com.saille.sys.Position;
import com.saille.sys.dao.PositionDao;
import com.saille.sys.form.PositionForm;
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

public class PositionAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private PositionDao dao;

    public PositionAction() {
        this.dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        PositionForm form = (PositionForm) _form;
        Position p = dao.get(1);
        List<Position> list = new ArrayList<Position>();
        list.add(p);
        list.addAll(dao.findByParentId(p, true));
//        List<Position> list = dao.getAll();
        form.setPositions(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        PositionForm form = (PositionForm) _form;
        if(form.getId() != 0) {
            Position obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setName(obj.getName());
                form.setParentId(obj.getParentId());
                form.setMemo(obj.getMemo());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        PositionForm form = (PositionForm) _form;
        Position obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new Position();
            }
        } else {
            obj = new Position();
        }
        obj.setName(form.getName());
        obj.setParentId(form.getParentId());
        obj.setMemo(form.getMemo());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        PositionForm form = (PositionForm) _form;
        if(form.getId() != 0) {
            Position obj = this.dao.get(form.getId());
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
