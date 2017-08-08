package servlet;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class AbstractDispatchAction extends DispatchAction {
    private final Logger LOGGER = Logger.getLogger(AbstractDispatchAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.LOGGER.info(request.getRequestURI());
        this.LOGGER.info("method: " + request.getParameter("method"));

        if(request.getParameterMap() != null) {
            for(Iterator i$ = request.getParameterMap().keySet().iterator(); i$.hasNext();) {
                Object o = i$.next();
                if(o.toString().equals("method")) {
                    continue;
                }
                this.LOGGER.info(o.toString() + ":" + request.getParameter(o.toString()));
            }
        }

        return super.execute(mapping, form, request, response);
    }

    protected void copyProperties(Object dest, Object src) {
        try {
            PropertyUtils.copyProperties(dest, src);
        } catch(Exception ex) {
        }
    }

    protected Object getBeanFromSpring(String beanId) {
        return getSpringContext().getBean(beanId);
    }

    protected ApplicationContext getSpringContext() {
        return WebApplicationContextUtils.getWebApplicationContext(getServlet().getServletContext());
    }
}