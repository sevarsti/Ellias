package com.saille.hhcq.web;

import com.saille.hhcq.Shangpin;
import com.saille.hhcq.dao.HhcqDao;
import com.saille.hhcq.web.form.ShangpinForm;
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

public class ShangpinAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private HhcqDao dao;

    public ShangpinAction() {
        this.dao = ((HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class));
    }

    public ActionForward listShangpin(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ShangpinForm form = (ShangpinForm) _form;
        List shangpins = this.dao.getAllShangpin();
        SortUtils.sortShangpin(shangpins, 0, shangpins.size());
        form.setShangpins(shangpins);

        List leibies = this.dao.getAllLeibie();
        SortUtils.sortLeibie(leibies, 0, leibies.size());
        form.setLeibies(leibies);

        return mapping.findForward("list");
    }

    public ActionForward saveShangpin(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ShangpinForm form = (ShangpinForm) _form;
        Shangpin shangpin = new Shangpin();
        shangpin.setName(form.getName());
        shangpin.setLeibie(form.getLeibieId());
        this.dao.saveShangpin(shangpin);
        return listShangpin(mapping, _form, request, response);
    }
}