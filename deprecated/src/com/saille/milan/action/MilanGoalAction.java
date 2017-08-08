package com.saille.milan.action;

import com.saille.milan.MilanGoal;
import com.saille.milan.dao.MilanGoalDao;
import com.saille.milan.form.MilanGoalForm;
import java.util.List;
import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class MilanGoalAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private MilanGoalDao dao;

    public MilanGoalAction() {
        this.dao = (MilanGoalDao) GlobalContext.getContextBean(MilanGoalDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanGoalForm form = (MilanGoalForm) _form;
        List<MilanGoal> list = dao.getAll();
        form.setMilanGoals(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanGoalForm form = (MilanGoalForm) _form;
        if(form.getId() != 0) {
            MilanGoal obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setMatchId(obj.getMatchId());
                form.setMinute(obj.getMinute());
                form.setPlayerId(obj.getPlayerId());
                form.setPlayerName(obj.getPlayerName());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanGoalForm form = (MilanGoalForm) _form;
        MilanGoal obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new MilanGoal();
            }
        } else {
            obj = new MilanGoal();
        }
        obj.setMatchId(form.getMatchId());
        obj.setMinute(form.getMinute());
        obj.setPlayerId(form.getPlayerId());
        obj.setPlayerName(form.getPlayerName());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanGoalForm form = (MilanGoalForm) _form;
        if(form.getId() != 0) {
            MilanGoal obj = this.dao.get(form.getId());
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
