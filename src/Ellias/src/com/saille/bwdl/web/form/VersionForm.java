package com.saille.bwdl.web.form;

import org.apache.struts.action.ActionForm;

public class VersionForm extends ActionForm {
    private int versionId;
    private String name;

    public int getVersionId() {
        return this.versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}