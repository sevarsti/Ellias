package com.saille.milan.action;

import com.saille.milan.MilanMatch;
import com.saille.milan.MatchPlayer;
import com.saille.milan.MilanGoal;
import com.saille.milan.dao.MilanMatchDao;
import com.saille.milan.dao.MatchPlayerDao;
import com.saille.milan.dao.MilanGoalDao;
import com.saille.milan.form.MilanMatchForm;
import java.util.List;
import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class MilanMatchAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private MilanMatchDao dao;

    public MilanMatchAction() {
        this.dao = (MilanMatchDao) GlobalContext.getContextBean(MilanMatchDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanMatchForm form = (MilanMatchForm) _form;
        List<MilanMatch> list = dao.getAll();
        form.setMilanMatchs(list);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanMatchForm form = (MilanMatchForm) _form;
        if(form.getId() != 0) {
            MilanMatch obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setAgainst(obj.getAgainst());
                form.setStatium(obj.getStatium());
                form.setCity(obj.getCity());
                form.setType(obj.getType());
                form.setRound(obj.getRound());
                form.setGoal(obj.getGoal());
                form.setGoaled(obj.getGoaled());
                form.setYear(obj.getYear());
                form.setDate(obj.getDate());
                form.setHomeawy(obj.getHomeawy());
                form.setTime(obj.getTime());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanMatchForm form = (MilanMatchForm) _form;
        MilanMatch obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new MilanMatch();
            }
        } else {
            obj = new MilanMatch();
        }
        obj.setAgainst(form.getAgainst());
        obj.setStatium(form.getStatium());
        obj.setCity(form.getCity());
        obj.setType(form.getType());
        obj.setRound(form.getRound());
        obj.setGoal(form.getGoal());
        obj.setGoaled(form.getGoaled());
        obj.setYear(form.getYear());
        obj.setDate(form.getDate());
        obj.setHomeawy(form.getHomeawy());
        obj.setTime(form.getTime());
        this.dao.save(obj);

        if(form.getPlayerId() != null) {
            MatchPlayerDao matchPlayerDao = (MatchPlayerDao) GlobalContext.getContextBean(MatchPlayerDao.class);
            for(int i = 0; i < form.getPlayerId().length; i++) {
                MatchPlayer p = new MatchPlayer();
                p.setMatchId(obj.getId());
                p.setPlayerId(form.getPlayerId()[i]);
                p.setSubstitude(form.getSubstitude()[i]);
                p.setOnTime(form.getOnTime()[i]);
                p.setOffTime(form.getOffTime()[i]);
                p.setGoal(form.getGoals()[i]);
                p.setOwnGoal(form.getOwnGoal()[i]);
                p.setYellowCard(form.getYellowCard()[i]);
                p.setRedCard(form.getRedCard()[i]);
                matchPlayerDao.save(p);
            }
        }
        if(form.getGoalMinute() != null) {
            MilanGoalDao milanGoalDao = (MilanGoalDao) GlobalContext.getContextBean(MilanGoalDao.class);
            for(int i = 0; i < form.getGoalMinute().length; i++) {
                if(form.getGoalMinute()[i] == 0) {
                    continue;
                }
                MilanGoal g = new MilanGoal();
                g.setMatchId(obj.getId());
                g.setMinute(form.getGoalMinute()[i]);
                g.setPlayerId(form.getGoalPlayerId()[i]);
                g.setPlayerName(form.getGoalPlayerName()[i]);
                milanGoalDao.save(g);
            }
        }
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MilanMatchForm form = (MilanMatchForm) _form;
        if(form.getId() != 0) {
            MilanMatch obj = this.dao.get(form.getId());
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
