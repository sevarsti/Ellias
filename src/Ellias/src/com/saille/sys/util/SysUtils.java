package com.saille.sys.util;

import com.saille.sys.Resource;
import com.saille.sys.Position;
import com.saille.sys.Employee;
import com.saille.sys.dao.ResourceDao;
import com.saille.sys.dao.PositionDao;
import com.saille.sys.dao.EmployeeDao;
import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-12-24
 * Time: 23:00:53
 * To change this template use File | Settings | File Templates.
 */
public class SysUtils {
    public static Position getPosition(int posId) {
        PositionDao dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
        return dao.get(posId);
    }

    public static Employee getEmployee(int empId) {
        EmployeeDao dao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        return dao.get(empId);
    }

    public static Resource getResource(int resId) {
        ResourceDao dao = (ResourceDao) GlobalContext.getContextBean(ResourceDao.class);
        return dao.get(resId);
    }

    public static String getPositionNames(String in) {
        PositionDao dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
        StringBuffer sb = new StringBuffer();
        if(in == null || in.length() == 0) {
            return "";
        }
        String[] ids = in.split(",");
        for(String id : ids) {
            try {
                int i = Integer.parseInt(id);
                Position p = dao.get(i);
                if(p != null) {
                    if(sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(p.getName()).append("(").append(p.getId()).append(")");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getEmployeeNames(String in) {
        EmployeeDao dao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        StringBuffer sb = new StringBuffer();
        if(in == null || in.length() == 0) {
            return "";
        }
        String[] ids = in.split(",");
        for(String id : ids) {
            try {
                int i = Integer.parseInt(id);
                Employee e = dao.get(i);
                if(e != null) {
                    if(sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(e.getName()).append("(").append(e.getId()).append(")");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
}
