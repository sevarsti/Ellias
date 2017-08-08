package com.saille.hhcq.web.form;

import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Guojia;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class GangkouForm extends ActionForm {
    private String msg;
    private List<Gangkou> gangkous;
    private List<Guojia> guojias;
    private String name;
    private int guojiaId;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Gangkou> getGangkous() {
        return this.gangkous;
    }

    public void setGangkous(List<Gangkou> gangkous) {
        this.gangkous = gangkous;
    }

    public List<Guojia> getGuojias() {
        return this.guojias;
    }

    public void setGuojias(List<Guojia> guojias) {
        this.guojias = guojias;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuojiaId() {
        return this.guojiaId;
    }

    public void setGuojiaId(int guojiaId) {
        this.guojiaId = guojiaId;
    }
}