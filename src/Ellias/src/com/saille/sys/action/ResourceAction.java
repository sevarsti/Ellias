package com.saille.sys.action;

import com.saille.sys.Resource;
import com.saille.sys.dao.ResourceDao;
import com.saille.sys.dao.RightDao;
import com.saille.sys.form.ResourceForm;
import java.util.List;
import java.util.ArrayList;

import servlet.GlobalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import servlet.AbstractDispatchAction;

public class ResourceAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private ResourceDao dao;

    public ResourceAction() {
        this.dao = (ResourceDao) GlobalContext.getContextBean(ResourceDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
//        ResourceForm form = (ResourceForm) _form;
//        List<Resource> list = dao.findAll();
//        form.setResources(list);
//        return mapping.findForward("list");

        ResourceForm form = (ResourceForm) _form;
        Resource r = dao.get(1);
        List<Resource> list = new ArrayList<Resource>();
        list.add(r);
        list.addAll(dao.findByParentId(r, true));
//        List<Position> list = dao.getAll();
        form.setResources(list);
        for(Resource res : list) {
            res.setRights(RightDao.getCachedRightByResourceId(res.getId()));
        }
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ResourceForm form = (ResourceForm) _form;
        if(form.getId() != 0) {
            Resource obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setName(obj.getName());
                form.setParentId(obj.getParentId());
                form.setUrl(obj.getUrl());
                form.setMethodname(obj.getMethodname());
                form.setMemo(obj.getMemo());
            }
        }
        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ResourceForm form = (ResourceForm) _form;
        Resource obj;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new Resource();
            }
        } else {
            obj = new Resource();
        }
        obj.setName(form.getName());
        obj.setParentId(form.getParentId());
        obj.setUrl(form.getUrl());
        obj.setMethodname(form.getMethodname());
        obj.setMemo(form.getMemo());
        this.dao.save(obj);
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        ResourceForm form = (ResourceForm) _form;
        if(form.getId() != 0) {
            Resource obj = this.dao.get(form.getId());
            if(obj != null) {
                this.dao.remove(obj.getId());
                form.setMsg("删除成功");
            } else {
                form.setMsg("没有对应的记录");
            }
        }
        return this.list(mapping, _form, request, response);
    }
}
