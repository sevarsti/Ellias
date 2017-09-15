package com.saille.rm.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * Created by Ellias on 2017-09-11.
 */
public class RMForm extends ActionForm {
    private FormFile uploadXls;

    public FormFile getUploadXls() {
        return uploadXls;
    }

    public void setUploadXls(FormFile uploadXls) {
        this.uploadXls = uploadXls;
    }
}
