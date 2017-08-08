package com.saille.msn.action;

import com.saille.msn.MsnMain;
import com.saille.msn.form.MsnForm;
import com.saille.msn.utils.MsnUtils;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class MsnAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(MsnAction.class);

    public ActionForward logon(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        MsnForm form = (MsnForm) _form;

        MsnMain msnMain = new MsnMain();
        form.setMain(msnMain);
        this.LOGGER.info(Boolean.valueOf(msnMain.login(form.getUsername(), form.getPassword())));
        MsnUtils.messengers.put(form.getUsername(), msnMain);
        return mapping.findForward("msnMain");
    }
}