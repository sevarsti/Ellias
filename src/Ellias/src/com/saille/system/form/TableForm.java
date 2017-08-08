package com.saille.system.form;

import org.apache.struts.action.ActionForm;

public class TableForm extends ActionForm {
    private String tableName;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}