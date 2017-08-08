package com.saille.milan.action;

import com.saille.milan.MatchPlayer;
import com.saille.milan.dao.MatchPlayerDao;
import com.saille.milan.form.MatchPlayerForm;
import java.util.List;
import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class MatchPlayerAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private MatchPlayerDao dao;

    public MatchPlayerAction() {
        this.dao = (MatchPlayerDao) GlobalContext.getContextBean(MatchPlayerDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MatchPlayerForm form = (MatchPlayerForm) _form;
        List<MatchPlayer> list = dao.getAll();
        form.setMatchPlayers(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MatchPlayerForm form = (MatchPlayerForm) _form;
        if(form.getId() != 0) {
            MatchPlayer obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setMatchId(obj.getMatchId());
                form.setPlayerId(obj.getPlayerId());
                form.setSubstitude(obj.getSubstitude());
                form.setOnTime(obj.getOnTime());
                form.setOffTime(obj.getOffTime());
                form.setGoal(obj.getGoal());
                form.setYellowCard(obj.getYellowCard());
                form.setRedCard(obj.getRedCard());
                form.setOwnGoal(obj.getOwnGoal());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MatchPlayerForm form = (MatchPlayerForm) _form;
        MatchPlayer obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new MatchPlayer();
            }
        } else {
            obj = new MatchPlayer();
        }
        obj.setMatchId(form.getMatchId());
        obj.setPlayerId(form.getPlayerId());
        obj.setSubstitude(form.getSubstitude());
        obj.setOnTime(form.getOnTime());
        obj.setOffTime(form.getOffTime());
        obj.setGoal(form.getGoal());
        obj.setYellowCard(form.getYellowCard());
        obj.setRedCard(form.getRedCard());
        obj.setOwnGoal(form.getOwnGoal());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MatchPlayerForm form = (MatchPlayerForm) _form;
        if(form.getId() != 0) {
            MatchPlayer obj = this.dao.get(form.getId());
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
