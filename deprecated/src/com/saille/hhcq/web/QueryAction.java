package com.saille.hhcq.web;

import com.saille.hhcq.Chushou;
import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Guojia;
import com.saille.hhcq.Jiage;
import com.saille.hhcq.Juli;
import com.saille.hhcq.Leibie;
import com.saille.hhcq.Shangpin;
import com.saille.hhcq.dao.HhcqDao;
import com.saille.hhcq.web.form.QueryForm;
import com.saille.util.SortUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import servlet.AbstractDispatchAction;
import servlet.GlobalContext;

public class QueryAction extends AbstractDispatchAction {
    private HhcqDao dao;

    public QueryAction() {
        this.dao = ((HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class));
    }

    public ActionForward queryGangkou(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        QueryForm form = (QueryForm) _form;
        Gangkou gangkou = this.dao.getGangkou(form.getGangkouId());
        form.setGangkou(gangkou);
        List<Chushou> chushous = this.dao.getChushouByGangkou(gangkou.getId());
        for(Chushou chushou : chushous) {
            List jiages = this.dao.getJiageByShangpin(chushou.getShangpinId());
        }
        return null;
    }

    public ActionForward listAllJuli(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        QueryForm form = (QueryForm) _form;
        List guojias = new ArrayList();
        if((form.getGuojiaIds() != null) && (form.getGuojiaIds().length > 0)) {
            for(int i : form.getGuojiaIds()) {
                guojias.add(this.dao.getGuojia(i));
            }
        } else {
            guojias = this.dao.getAllGuojia();
        }
        SortUtils.sortGuojia(guojias, 0, guojias.size());
        form.setGuojias(guojias);
        int[] guojiaLength = new int[guojias.size()];
        List allGangkous = new ArrayList();
        for(int i = 0; i < guojias.size(); i++) {
            Guojia guojia = (Guojia) guojias.get(i);
            List gangkous = this.dao.getGangkouByGuojia(guojia.getId());
            guojiaLength[i] = gangkous.size();
            allGangkous.addAll(gangkous);
        }
        form.setGangkous(allGangkous);
        form.setGuojiaLength(guojiaLength);
        List allJulis = new ArrayList();
        for(int i = 0; i < allGangkous.size(); i++) {
            Gangkou gangkou = (Gangkou) allGangkous.get(i);
            Juli[] js = new Juli[allGangkous.size()];
            List<Juli> julis = this.dao.getJuliByGangkou(gangkou.getId());
            for(Juli juli : julis) {
                for(int j = 0; j < allGangkous.size(); j++) {
                    if(((Gangkou) allGangkous.get(j)).getId() == juli.getGangkou2Id()) {
                        js[j] = juli;
                        break;
                    }
                }
            }
            allJulis.add(Arrays.asList(js));
        }
        form.setJulis(allJulis);
        return mapping.findForward("listJuli");
    }

    public ActionForward listAllJiage(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        QueryForm form = (QueryForm) _form;

        List leibies = this.dao.getAllLeibie();
        form.setLeibies(leibies);
        SortUtils.sortLeibie(leibies, 0, leibies.size());
        List gangkous = this.dao.getAllGangkou();
        SortUtils.sortGangkou(gangkous, 0, gangkous.size());
        List shangpins = new ArrayList();
        int[] length = new int[leibies.size()];
        for(int i = 0; i < leibies.size(); i++) {
            List s = this.dao.getShangpinByLeibie(((Leibie) leibies.get(i)).getId());
            SortUtils.sortShangpin(s, 0, s.size());
            shangpins.addAll(s);
            length[i] = s.size();
        }
        form.setLeibieLength(length);

        List jiages = this.dao.getAllJiage();
        List chushous = this.dao.getAllChushou();
        form.setJiages(sortJiages(gangkous, shangpins, jiages));
        form.setChushous(sortChushous(gangkous, shangpins, chushous));
        form.setGangkous(gangkous);
        form.setShangpins(shangpins);
        return mapping.findForward("listJiage");
    }

    private List<List<Jiage>> sortJiages(List<Gangkou> gangkous, List<Shangpin> shangpins, List<Jiage> jiages) {
        List ret = new ArrayList();
        for(int i = 0; i < gangkous.size(); i++) {
            List list = new ArrayList();
            for(int j = 0; j < shangpins.size(); j++) {
                list.add(null);
            }
            ret.add(list);
        }
        for(Jiage jiage : jiages) {
            for(int i = 0; i < gangkous.size(); i++) {
                for(int j = 0; j < shangpins.size(); j++) {
                    if((jiage.getGangkouId() == ((Gangkou) gangkous.get(i)).getId()) && (jiage.getShangpinId() == ((Shangpin) shangpins.get(j)).getId())) {
                        ((List) ret.get(i)).set(j, jiage);
                        break;
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
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public ActionForward exportJiage(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        QueryForm form = (QueryForm) _form;
        return null;
    }
}