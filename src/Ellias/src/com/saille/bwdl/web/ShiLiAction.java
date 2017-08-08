package com.saille.bwdl.web;

import com.saille.bwdl.ShiLi;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.ServiceHelper;
import com.saille.bwdl.web.form.ShiLiForm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class ShiLiAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlService service;

    public ShiLiAction() {
        this.service = ServiceHelper.getBwdlService();
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ShiLiForm form = (ShiLiForm) _form;
        List shilis = this.service.findAllShilisInit(form.getVersion());
        form.setShilis(shilis);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ShiLiForm form = (ShiLiForm) _form;
        if(form.getShiliId() != 0) {
            ShiLi shili = this.service.getShiLiInit(form.getShiliId());
            if(shili != null) {
                form.setName(shili.getName());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ShiLiForm form = (ShiLiForm) _form;
        ShiLi shili = null;
        if(form.getShiliId() != 0) {
            shili = this.service.getShiLiInit(form.getShiliId());
        }
        if(shili == null) {
            shili = new ShiLi();
        }
        shili.setName(form.getName());
        shili.setVersion(form.getVersion());
        shili.setInit(true);
        this.service.saveShili(shili);

        return list(mapping, _form, request, response);
    }
}