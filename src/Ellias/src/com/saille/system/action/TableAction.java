package com.saille.system.action;

import com.saille.system.form.TableForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class TableAction extends AbstractDispatchAction {
    public ActionForward addTable(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        TableForm form = (TableForm) _form;
        return mapping.findForward("addTableSuccess");
    }
}