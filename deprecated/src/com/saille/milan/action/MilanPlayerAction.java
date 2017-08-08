package com.saille.milan.action;

import com.saille.milan.MilanPlayer;
import com.saille.milan.dao.MilanPlayerDao;
import com.saille.milan.form.MilanPlayerForm;
import java.util.List;
import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class MilanPlayerAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private MilanPlayerDao dao;

    public MilanPlayerAction() {
        this.dao = (MilanPlayerDao) GlobalContext.getContextBean(MilanPlayerDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanPlayerForm form = (MilanPlayerForm) _form;
        List<MilanPlayer> list = dao.getAll();
        form.setMilanPlayers(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanPlayerForm form = (MilanPlayerForm) _form;
        if(form.getId() != 0) {
            MilanPlayer obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setName(obj.getName());
                form.setNationality(obj.getNationality());
                form.setBirth(obj.getBirth());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanPlayerForm form = (MilanPlayerForm) _form;
        MilanPlayer obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new MilanPlayer();
            }
        } else {
            obj = new MilanPlayer();
        }
        obj.setName(form.getName());
        obj.setNationality(form.getNationality());
        obj.setBirth(form.getBirth());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanPlayerForm form = (MilanPlayerForm) _form;
        if(form.getId() != 0) {
            MilanPlayer obj = this.dao.get(form.getId());
            if(obj != null) {
                this.dao.delete(obj.getId());
                form.setMsg("删除成功");
            } else {
                form.setMsg("没有对应的记录");
            }
        }
        return this.list(mapping, _form, request, response);
    }
}
