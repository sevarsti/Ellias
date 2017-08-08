package com.saille.bwdl.web;

import com.saille.bwdl.ChengShi;
import com.saille.bwdl.WuJiang;
import com.saille.bwdl.WuJiangChengShiRela;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.ServiceHelper;
import com.saille.bwdl.web.form.ChengShiForm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class ChengShiAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlService service;

    public ChengShiAction() {
        this.service = ServiceHelper.getBwdlService();
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ChengShiForm form = (ChengShiForm) _form;
        List<ChengShi> chengshis = this.service.findAllChengShisInit(form.getVersion());
        form.setChengshis(chengshis);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ChengShiForm form = (ChengShiForm) _form;
        if(form.getChengshiId() != 0) {
            ChengShi chengshi = this.service.getChengShiInit(form.getChengshiId());
            if(chengshi != null) {
                form.setName(chengshi.getName());
                form.setJin(chengshi.getJin());
                form.setMi(chengshi.getMi());
                form.setBao(chengshi.getBao());
                form.setTongzhi(chengshi.getTongzhi());
                form.setTudi(chengshi.getTudi());
                form.setChanye(chengshi.getChanye());
                form.setRenkou(chengshi.getRenkou());
                form.setFangzai(chengshi.getFangzai());
                form.setShili(chengshi.getShili());
                form.setLocationX(chengshi.getLocationX());
                form.setLocationY(chengshi.getLocationY());
            }
            List wujiangs = this.service.findWuJiangsByChengShiInit(chengshi.getId());
            if(wujiangs != null) {
                int[] wujiangIds = new int[wujiangs.size()];
                for(int i = 0; i < wujiangs.size(); i++) {
                    wujiangIds[i] = ((WuJiang) wujiangs.get(i)).getId();
                }
                form.setWujiangIds(wujiangIds);
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ChengShiForm form = (ChengShiForm) _form;
        ChengShi chengshi = null;
        if(form.getChengshiId() != 0) {
            chengshi = this.service.getChengShiInit(form.getChengshiId());
        }
        if(chengshi == null) {
            chengshi = new ChengShi();
        }
        chengshi.setName(form.getName());
        chengshi.setJin(form.getJin());
        chengshi.setMi(form.getMi());
        chengshi.setBao(form.getBao());
        chengshi.setTongzhi(form.getTongzhi());
        chengshi.setTudi(form.getTudi());
        chengshi.setChanye(form.getChanye());
        chengshi.setRenkou(form.getRenkou());
        chengshi.setFangzai(form.getFangzai());
        chengshi.setShili(form.getShili());
        chengshi.setVersion(form.getVersion());
        chengshi.setLocationX(form.getLocationX());
        chengshi.setLocationY(form.getLocationY());
        chengshi.setInit(true);
        this.service.saveChengShi(chengshi);

        this.LOGGER.info("开始保存城内武将");
        List<WuJiangChengShiRela> currentRelas = this.service.findWuJiangChengShiRelasByChengShiInit(chengshi.getId());
        for(WuJiangChengShiRela r : currentRelas) {
            this.service.deleteWuJiangChengShiRela(r);
        }
        if(form.getWujiangIds() != null) {
            int index = 0;
            for(int i : form.getWujiangIds()) {
                if(i != 0) {
                    WuJiangChengShiRela rela = new WuJiangChengShiRela();
                    rela.setInit(true);
                    rela.setChengshiId(chengshi.getId());
                    rela.setWujiangId(i);
                    rela.setIndex(index);
                    index++;
                    rela.setVersion(form.getVersion());
                    this.service.saveWuJiangChengShiRela(rela);
                }
            }
        }
        return list(mapping, _form, request, response);
    }
}