package com.saille.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;
import servlet.GlobalContext;
import com.saille.sys.dao.EmployeeDao;
import com.saille.sys.Employee;

public class LoginAction extends AbstractDispatchAction {
    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        EmployeeDao dao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        Employee emp = dao.checkPwd(name, pwd);
        if(emp != null) {
            request.getSession().setAttribute("employee", emp);
            try {
                response.sendRedirect("/index.jsp");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return null;
//            return mapping.findForward("success");
        } else {
            return mapping.findForward("fail");
        }
    }

    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("employee");
        try {
            response.sendRedirect("/login.jsp");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}