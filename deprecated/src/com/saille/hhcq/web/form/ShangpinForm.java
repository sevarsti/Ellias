package com.saille.hhcq.web.form;

import com.saille.hhcq.Leibie;
import com.saille.hhcq.Shangpin;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ShangpinForm extends ActionForm {
    private String msg;
    private List<Shangpin> shangpins;
    private List<Leibie> leibies;
    private String name;
    private int leibieId;

    public List<Shangpin> getShangpins() {
        return this.shangpins;
    }

    public void setShangpins(List<Shangpin> shangpins) {
        this.shangpins = shangpins;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Leibie> getLeibies() {
        return this.leibies;
    }

    public void setLeibies(List<Leibie> leibies) {
        this.leibies = leibies;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeibieId() {
        return this.leibieId;
    }

    public void setLeibieId(int leibieId) {
        this.leibieId = leibieId;
    }
}