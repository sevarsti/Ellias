package com.saille.bwdl.web.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class DiTuForm extends ActionForm {
    List<String> sucais;

    public List<String> getSucais() {
        return this.sucais;
    }

    public void setSucais(List<String> sucais) {
        this.sucais = sucais;
    }
}