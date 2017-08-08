package com.saille.bwdl.web;

import com.saille.bwdl.Version;
import com.saille.bwdl.service.BwdlService;
import com.saille.bwdl.service.ServiceHelper;
import com.saille.bwdl.web.form.VersionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class VersionAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private BwdlService service;

    public VersionAction() {
        this.service = ServiceHelper.getBwdlService();
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        VersionForm form = (VersionForm) _form;
        Version version = null;
        if(form.getVersionId() != 0) {
            version = this.service.getVersion(form.getVersionId());
        }
        if(version == null) {
            version = new Version();
        }
        version.setName(form.getName());
        this.service.saveVersion(version);
        return mapping.findForward("list");
    }
}