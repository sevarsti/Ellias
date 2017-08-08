package com.saille.hhcq.web;

import com.saille.hhcq.Chushou;
import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Guojia;
import com.saille.hhcq.Jiage;
import com.saille.hhcq.Juli;
import com.saille.hhcq.Leibie;
import com.saille.hhcq.Shangpin;
import com.saille.hhcq.dao.HhcqDao;
import com.saille.hhcq.web.form.JiageForm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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

public class JiageAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private HhcqDao dao;

    public JiageAction() {
        this.dao = ((HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class));
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        try {
            int gangkou = this.dao.getIntBySql("select count(*) from gangkou");
            request.setAttribute("gangkou", Integer.valueOf(gangkou));
            request.setAttribute("maxjuli", Integer.valueOf(gangkou * (gangkou - 1) / 2));
            int juli = this.dao.getIntBySql("select count(*)/2 from juli");
            request.setAttribute("juli", Integer.valueOf(juli));
            int shangpin = this.dao.getIntBySql("select count(*) from shangpin");
            request.setAttribute("shangpin", Integer.valueOf(shangpin));
            int chushou = this.dao.getIntBySql("select count(*) from chushou");
            request.setAttribute("maxjiage", Integer.valueOf(shangpin * gangkou - chushou));
            int jiage = this.dao.getIntBySql("select count(*) from jiage");
            request.setAttribute("jiage", Integer.valueOf(jiage));
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error(sw.toString());
        }
        return mapping.findForward("list");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        JiageForm form = (JiageForm) _form;
        if((form.getGangkouId() != 0) && (form.getShangpinId() != 0) && (form.getJiage() != 0)) {
            List list = this.dao.getJiage(form.getGangkouId(), form.getShangpinId());
            Jiage jiage = list.size() > 0 ? (Jiage) list.get(0) : null;
            if(jiage == null) {
                jiage = new Jiage();
                jiage.setGangkouId(form.getGangkouId());
                jiage.setShangpinId(form.getShangpinId());
            }
            jiage.setJiage(form.getJiage());
            jiage.setWeixian(form.getWeixian());
            this.dao.saveJiage(jiage);
        }
        form.setGangkouId(0);
        form.setShangpinId(0);
        form.setJiage(0);
        return list(mapping, _form, request, response);
    }

    public ActionForward addGuojia(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        Guojia guojia = new Guojia();
        guojia.setName(request.getParameter("guojiaName"));
        guojia.setPinyin(request.getParameter("pinyin"));
        this.dao.saveGuojia(guojia);
        return list(mapping, _form, request, response);
    }

    public ActionForward addGangkou(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        Gangkou gangkou = new Gangkou();
        gangkou.setGuojia(Integer.parseInt(request.getParameter("guojiaId")));
        gangkou.setName(request.getParameter("gangkouName"));
        gangkou.setPinyin(request.getParameter("pinyin"));
        this.dao.saveGangkou(gangkou);
        return list(mapping, _form, request, response);
    }

    public ActionForward addLeibie(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        Leibie leibie = new Leibie();
        leibie.setName(request.getParameter("leibieName"));
        leibie.setPinyin(request.getParameter("pinyin"));
        this.dao.saveLeibie(leibie);
        return list(mapping, _form, request, response);
    }

    public ActionForward addShangpin(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        Shangpin shangpin = new Shangpin();
        shangpin.setLeibie(Integer.parseInt(request.getParameter("leibieId")));
        shangpin.setName(request.getParameter("shangpinName"));
        shangpin.setPinyin(request.getParameter("pinyin"));
        this.dao.saveShangpin(shangpin);
        return list(mapping, _form, request, response);
    }

    public ActionForward addChushou(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        List chushous = this.dao.getChushou(Integer.parseInt(request.getParameter("gangkouId")), Integer.parseInt(request.getParameter("shangpinId")));
        Chushou chushou = chushous.size() > 0 ? (Chushou) chushous.get(0) : null;
        if(chushou == null) {
            chushou = new Chushou();
        }
        chushou.setGangkouId(Integer.parseInt(request.getParameter("gangkouId")));
        chushou.setShangpinId(Integer.parseInt(request.getParameter("shangpinId")));
        chushou.setJiage(Integer.parseInt(request.getParameter("jiage")));
        this.dao.saveChushou(chushou);
        return list(mapping, _form, request, response);
    }

    public ActionForward addJuli(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        int g1 = Integer.parseInt(request.getParameter("gangkou1"));
        int g2 = Integer.parseInt(request.getParameter("gangkou2"));
        int juli = Integer.parseInt(request.getParameter("juli"));

        Juli juli1 = this.dao.getJuliByGangkou(g1, g2);
        Juli juli2 = this.dao.getJuliByGangkou(g2, g1);
        if(juli1 == null) {
            juli1 = new Juli();
        }
        if(juli2 == null) {
            juli2 = new Juli();
        }

        juli1.setGangkou1Id(g1);
        juli1.setGangkou2Id(g2);
        juli2.setGangkou1Id(g2);
        juli2.setGangkou2Id(g1);
        juli1.setJuli(juli);
        juli2.setJuli(juli);
        this.dao.saveJuli(juli1);
        this.dao.saveJuli(juli2);
        return list(mapping, _form, request, response);
    }

    private List<List<Jiage>> sortJiages(List<Gangkou> gangkous, List<Shangpin> shangpins, List<Jiage> jiages) {
        List<List<Jiage>> ret = new ArrayList<List<Jiage>>();
        for(int i = 0; i < gangkous.size(); i++) {
            List<Jiage> list = new ArrayList<Jiage>();
            for(int j = 0; j < shangpins.size(); j++) {
                list.add(null);
            }
            ret.add(list);
        }
        for(Jiage jiage : jiages) {
            for(int i = 0; i < gangkous.size(); i++) {
                for(int j = 0; j < shangpins.size(); j++) {
                    if((jiage.getGangkouId() == (gangkous.get(i)).getId()) && (jiage.getShangpinId() == (shangpins.get(j)).getId())) {
                        ret.get(i).set(j, jiage);
                    }
                }
            }
        }
        return ret;
    }

    private List<List<Chushou>> sortChushous(List<Gangkou> gangkous, List<Shangpin> shangpins, List<Chushou> chushous) {
        List ret = new ArrayList();
        for(int i = 0; i < gangkous.size(); i++) {
            List list = new ArrayList();
            for(int j = 0; j < shangpins.size(); j++) {
                list.add(null);
            }
            ret.add(list);
        }
        for(Chushou chushou : chushous) {
            for(int i = 0; i < gangkous.size(); i++) {
                for(int j = 0; j < shangpins.size(); j++) {
                    if((chushou.getGangkouId() == ((Gangkou) gangkous.get(i)).getId()) && (chushou.getShangpinId() == ((Shangpin) shangpins.get(j)).getId())) {
                        ((List) ret.get(i)).set(j, chushou);
                    }
                }
            }
        }
        return ret;
    }
}