package com.saille.bwdl.web;

import com.saille.bwdl.WuQi;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.ServiceHelper;
import com.saille.bwdl.web.form.WuQiForm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class WuQiAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlService service;

    public WuQiAction() {
        this.service = ServiceHelper.getBwdlService();
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        WuQiForm form = (WuQiForm) _form;
        List wuqis = this.service.findAllWuQis(form.getVersion());
        form.setWuqis(wuqis);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        WuQiForm form = (WuQiForm) _form;
        if(form.getWuqiId() != 0) {
            WuQi wuqi = this.service.getWuQi(form.getWuqiId());
            if(wuqi != null) {
                form.setName(wuqi.getName());
                form.setGongji(wuqi.getGongji());
                form.setType(wuqi.getType());
                form.setWeight(wuqi.getWeight());
                form.setPrice(wuqi.getPrice());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        WuQiForm form = (WuQiForm) _form;
        WuQi wuqi = null;
        if(form.getWuqiId() != 0) {
            wuqi = this.service.getWuQi(form.getWuqiId());
        }
        if(wuqi == null) {
            wuqi = new WuQi();
        }
        wuqi.setName(form.getName());
        wuqi.setGongji(form.getGongji());
        wuqi.setType(form.getType());
        wuqi.setWeight(form.getWeight());
        wuqi.setPrice(form.getPrice());
        wuqi.setVersion(form.getVersion());
        this.service.saveWuQi(wuqi);
        return list(mapping, _form, request, response);
    }
}