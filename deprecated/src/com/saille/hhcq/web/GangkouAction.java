package com.saille.hhcq.web;

import com.saille.hhcq.Gangkou;
import com.saille.hhcq.dao.HhcqDao;
import com.saille.hhcq.web.form.GangkouForm;
import com.saille.util.SortUtils;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import servlet.AbstractDispatchAction;
import servlet.GlobalContext;

public class GangkouAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private HhcqDao dao;

    public GangkouAction() {
        this.dao = ((HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class));
    }

    public ActionForward listGangkou(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        GangkouForm form = (GangkouForm) _form;
        List gangkous = this.dao.getAllGangkou();
        SortUtils.sortGangkou(gangkous, 0, gangkous.size());
        form.setGangkous(gangkous);

        List guojias = this.dao.getAllGuojia();
        SortUtils.sortGuojia(guojias, 0, guojias.size());
        form.setGuojias(guojias);

        return mapping.findForward("list");
    }

    public ActionForward saveGangkou(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        GangkouForm form = (GangkouForm) _form;
        Gangkou gangkou = new Gangkou();
        gangkou.setName(form.getName());
        gangkou.setGuojia(form.getGuojiaId());
        this.dao.saveGangkou(gangkou);
        return listGangkou(mapping, _form, request, response);
    }
}