package com.saille.bwdl.web;

import com.saille.bwdl.FangJu;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.ServiceHelper;
import com.saille.bwdl.web.form.FangJuForm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class FangJuAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlService service;

    public FangJuAction() {
        this.service = ServiceHelper.getBwdlService();
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        FangJuForm form = (FangJuForm) _form;
        List fangjus = this.service.findAllFangJus(form.getVersion());
        form.setFangjus(fangjus);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        FangJuForm form = (FangJuForm) _form;
        if(form.getFangjuId() != 0) {
            FangJu fangju = this.service.getFangJu(form.getFangjuId());
            if(fangju != null) {
                form.setName(fangju.getName());
                form.setFangyu(fangju.getFangyu());
                form.setWeight(fangju.getWeight());
                form.setPrice(fangju.getPrice());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        FangJuForm form = (FangJuForm) _form;
        FangJu fangju = null;
        if(form.getFangjuId() != 0) {
            fangju = this.service.getFangJu(form.getFangjuId());
        }
        if(fangju == null) {
            fangju = new FangJu();
        }
        fangju.setName(form.getName());
        fangju.setFangyu(form.getFangyu());
        fangju.setWeight(form.getWeight());
        fangju.setPrice(form.getPrice());
        fangju.setVersion(form.getVersion());
        this.service.saveFangJu(fangju);
        return list(mapping, _form, request, response);
    }
}