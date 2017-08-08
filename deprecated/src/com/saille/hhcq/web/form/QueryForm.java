package com.saille.hhcq.web.form;

import com.saille.hhcq.Chushou;
import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Guojia;
import com.saille.hhcq.Jiage;
import com.saille.hhcq.Juli;
import com.saille.hhcq.Leibie;
import com.saille.hhcq.Shangpin;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class QueryForm extends ActionForm {
    private List<List<Jiage>> jiages;
    private List<List<Chushou>> chushous;
    private List<List<Juli>> julis;
    private List<Leibie> leibies;
    private int[] leibieLength;
    private List<Shangpin> shangpins;
    private List<Gangkou> gangkous;
    private List<Guojia> guojias;
    private int[] guojiaLength;
    private int gangkouId;
    private int[] guojiaIds;
    private Gangkou gangkou;

    public List<List<Jiage>> getJiages() {
        return this.jiages;
    }

    public void setJiages(List<List<Jiage>> jiages) {
        this.jiages = jiages;
    }

    public List<List<Chushou>> getChushous() {
        return this.chushous;
    }

    public void setChushous(List<List<Chushou>> chushous) {
        this.chushous = chushous;
    }

    public List<Leibie> getLeibies() {
        return this.leibies;
    }

    public void setLeibies(List<Leibie> leibies) {
        this.leibies = leibies;
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

    public int[] getLeibieLength() {
        return this.leibieLength;
    }

    public void setLeibieLength(int[] leibieLength) {
        this.leibieLength = leibieLength;
    }

    public int getGangkouId() {
        return this.gangkouId;
    }

    public void setGangkouId(int gangkouId) {
        this.gangkouId = gangkouId;
    }

    public Gangkou getGangkou() {
        return this.gangkou;
    }

    public void setGangkou(Gangkou gangkou) {
        this.gangkou = gangkou;
    }

    public List<Guojia> getGuojias() {
        return this.guojias;
    }

    public void setGuojias(List<Guojia> guojias) {
        this.guojias = guojias;
    }

    public int[] getGuojiaLength() {
        return this.guojiaLength;
    }

    public void setGuojiaLength(int[] guojiaLength) {
        this.guojiaLength = guojiaLength;
    }

    public List<List<Juli>> getJulis() {
        return this.julis;
    }

    public void setJulis(List<List<Juli>> julis) {
        this.julis = julis;
    }

    public int[] getGuojiaIds() {
        return this.guojiaIds;
    }

    public void setGuojiaIds(int[] guojiaIds) {
        this.guojiaIds = guojiaIds;
    }
}