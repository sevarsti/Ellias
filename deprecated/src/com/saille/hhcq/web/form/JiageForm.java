package com.saille.hhcq.web.form;

import com.saille.hhcq.Chushou;
import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Jiage;
import com.saille.hhcq.Shangpin;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class JiageForm extends ActionForm {
    private int gangkouId;
    private int shangpinId;
    private int jiage;
    private int weixian;
    private List<List<Jiage>> jiages;
    private List<List<Chushou>> chushous;
    private List<Shangpin> shangpins;
    private List<Gangkou> gangkous;

    public int getGangkouId() {
        return this.gangkouId;
    }

    public void setGangkouId(int gangkouId) {
        this.gangkouId = gangkouId;
    }

    public int getShangpinId() {
        return this.shangpinId;
    }

    public void setShangpinId(int shangpinId) {
        this.shangpinId = shangpinId;
    }

    public List<List<Jiage>> getJiages() {
        return this.jiages;
    }

    public void setJiages(List<List<Jiage>> jiages) {
        this.jiages = jiages;
    }

    public int getJiage() {
        return this.jiage;
    }

    public void setJiage(int jiage) {
        this.jiage = jiage;
    }

    public List<Shangpin> getShangpins() {
        return this.shangpins;
    }

    public void setShangpins(List<Shangpin> shangpins) {
        this.shangpins = shangpins;
    }

    public List<Gangkou> getGangkous() {
        return this.gangkous;
    }

    public void setGangkous(List<Gangkou> gangkous) {
        this.gangkous = gangkous;
    }

    public List<List<Chushou>> getChushous() {
        return this.chushous;
    }

    public void setChushous(List<List<Chushou>> chushous) {
        this.chushous = chushous;
    }

    public int getWeixian() {
        return this.weixian;
    }

    public void setWeixian(int weixian) {
        this.weixian = weixian;
    }
}