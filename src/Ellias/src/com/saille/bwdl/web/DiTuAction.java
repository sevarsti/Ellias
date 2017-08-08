package com.saille.bwdl.web;

import com.saille.bwdl.web.form.DiTuForm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class DiTuAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        DiTuForm form = (DiTuForm) _form;
        String fileaddress = request.getRealPath("bwdl\\image\\zc");
        File file = new File(fileaddress);
        File[] files = file.listFiles();
        if(files != null) {
            List filepaths = new ArrayList();
            for(File f : files) {
                filepaths.add("image\\zc\\" + f.getName());
            }
            form.setSucais(filepaths);
        }
        return mapping.findForward("edit");
    }
}