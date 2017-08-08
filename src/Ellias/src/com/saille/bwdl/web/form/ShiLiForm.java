package com.saille.bwdl.web.form;

import com.saille.bwdl.ShiLi;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ShiLiForm extends ActionForm {
    private List<ShiLi> shilis;
    private int shiliId;
    private String name;
    private int version;

    public int getShiliId() {
        return this.shiliId;
    }

    public void setShiliId(int shiliId) {
        this.shiliId = shiliId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<ShiLi> getShilis() {
        return this.shilis;
    }

    public void setShilis(List<ShiLi> shilis) {
        this.shilis = shilis;
    }
}