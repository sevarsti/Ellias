package com.saille.bwdl.web;

import com.saille.bwdl.WuJiang;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.ServiceHelper;
import com.saille.bwdl.web.form.WuJiangForm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class WuJiangAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlService service;

    public WuJiangAction() {
        this.service = ServiceHelper.getBwdlService();
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        WuJiangForm form = (WuJiangForm) _form;
        List wujiangs = this.service.findAllWuJiangsInit(form.getVersion());
        form.setWujiangs(wujiangs);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        WuJiangForm form = (WuJiangForm) _form;
        if(form.getWujiangId() != 0) {
            WuJiang wujiang = this.service.getWuJiangInit(form.getWujiangId());
            if(wujiang != null) {
                form.setName(wujiang.getName());
                form.setTi(wujiang.getTi());
                form.setWu(wujiang.getWu());
                form.setZhi(wujiang.getZhi());
                form.setZhong(wujiang.getZhong());
                form.setDe(wujiang.getDe());
                form.setJing(wujiang.getJing());
                form.setBingzhong(wujiang.getBingzhong());
                form.setWuqi(wujiang.getWuqi());
                form.setFangju(wujiang.getFangju());
                form.setShibing(wujiang.getShibing());
            }
        }
        return mapping.findForward("edit");
    }
}