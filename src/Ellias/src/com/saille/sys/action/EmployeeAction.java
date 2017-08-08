package com.saille.sys.action;

import com.saille.sys.Employee;
import com.saille.sys.EmpPosition;
import com.saille.sys.util.SysUtils;
import com.saille.sys.dao.EmployeeDao;
import com.saille.sys.dao.EmpPositionDao;
import com.saille.sys.dao.RightDao;
import com.saille.sys.form.EmployeeForm;
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

public class EmployeeAction extends AbstractDispatchAction {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private EmployeeDao dao;
    private EmpPositionDao empPositionDao;

    public EmployeeAction() {
        this.dao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        this.empPositionDao = (EmpPositionDao) GlobalContext.getContextBean(EmpPositionDao.class);
    }

    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmployeeForm form = (EmployeeForm) _form;
        List<Employee> list = dao.findAll();
        form.setEmployees(list);
        for(Employee emp : list) {
            List<EmpPosition> empPositions = this.empPositionDao.findByEmpId(emp.getId());
            StringBuffer sb = new StringBuffer();
            for(EmpPosition empPos : empPositions) {
                if(sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(empPos.getPositionId());
            }
            emp.setPositionNames(SysUtils.getPositionNames(sb.toString()));
        }
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmployeeForm form = (EmployeeForm) _form;
        if(form.getId() != 0) {
            Employee obj = this.dao.get(form.getId());
            if(obj != null) {
                form.setName(obj.getName());
                form.setPwd(obj.getPwd());
                form.setLoginname(obj.getLoginname());
//                form.setPositionId(obj.getPositionId());
                form.setGender(obj.getGender());
                form.setBirth(obj.getBirth());
                form.setMobile(obj.getMobile());
                form.setEmail(obj.getEmail());
                form.setMemo(obj.getMemo());
            }
        }
        /* 找到员工所属岗位 */
        List<EmpPosition> positions = this.empPositionDao.findByEmpId(form.getId());
        if(positions.size() > 0) {
            StringBuffer positionIds = new StringBuffer();
            for(EmpPosition p : positions) {
                if(positionIds.length() > 0) {
                    positionIds.append(",");
                }
                positionIds.append(p.getPositionId());
            }
            form.setPositionIds(positionIds.toString());
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmployeeForm form = (EmployeeForm) _form;
        Employee obj;
        boolean needRefreshRight = false;
        if(form.getId() != 0) {
            obj = this.dao.get(form.getId());
            if(obj == null) {
                obj = new Employee();
            }
        } else {
            needRefreshRight = true;
            obj = new Employee();
        }
        obj.setName(form.getName());
        obj.setPwd(form.getPwd());
        obj.setLoginname(form.getLoginname());
//        obj.setPositionId(form.getPositionId());
        obj.setGender(form.getGender());
        obj.setBirth(form.getBirth());
        obj.setMobile(form.getMobile());
        obj.setEmail(form.getEmail());
        obj.setMemo(form.getMemo());
        this.dao.save(obj);

        /* 保存所属岗位 */
        if(form.getPositionIds() == null || form.getPositionIds().length() == 0) {
            this.empPositionDao.removeByEmpId(obj.getId());
        } else {
            List<EmpPosition> positions = this.empPositionDao.findByEmpId(obj.getId());
            List<Integer> ids = new ArrayList<Integer>();
            String[] positionIds = form.getPositionIds().split(",");
            for(String id : positionIds) {
                try {
                    int i = Integer.parseInt(id);
                    ids.add(i);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            for(Integer id : ids) {
                boolean found = false;
                for(EmpPosition empPosition : positions) {
                    if(empPosition.getPositionId() == id.intValue()) {
                        found = true;
                        positions.remove(empPosition);
                        break;
                    }
                }
                if(!found) {
                    EmpPosition empPosition = new EmpPosition();
                    empPosition.setEmpId(obj.getId());
                    empPosition.setPositionId(id.intValue());
                    empPosition.setRemoveTag(0);
                    this.empPositionDao.save(empPosition);
                }
            }
            if(positions.size() > 0) {
                for(EmpPosition position : positions) {
                    this.empPositionDao.remove(position.getId());
                }
            }
        }
        if(needRefreshRight) {
            RightDao rightDao = (RightDao) GlobalContext.getContextBean(RightDao.class);
            rightDao.updateAllRight();
        }
        form.setMsg("保存成功");
        return this.list(mapping, _form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
        EmployeeForm form = (EmployeeForm) _form;
        if(form.getId() != 0) {
            Employee obj = this.dao.get(form.getId());
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
