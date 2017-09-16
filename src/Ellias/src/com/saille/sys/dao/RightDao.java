package com.saille.sys.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.sys.Right;
import com.saille.sys.Employee;
import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;

import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;

import servlet.GlobalContext;

public class RightDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public static RightDao getInstance() {
        return (RightDao)GlobalContext.getContextBean(RightDao.class);
    }

    public Right get(int id) {
        String sql = "select * from `SYS_Right` where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Right> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(Right.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(Right obj) {
        obj.setUpdateTime(new Date());
        if(obj.getId() > 0) {
            update(obj);
        } else {
            obj.setCreateTime(new Date());
            obj.setId(getId(MapperUtils.getTableNameFromAnnotation(obj.getClass())));
            insert(obj);
        }
//        updateRight(obj.getResourceId(), obj.getOrgId(), obj.getOrgType(), obj.getAuth());
        updateRight(obj.getResourceId());
        return obj.getId();
    }

    public static String getCachedRightByResourceId(int resId) {
        String path = ResourceDao.class.getResource("/").getPath();
        path = path.substring(0, path.length() - 1);
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.substring(0, path.lastIndexOf("/"));
        path = path + "/cache";
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }
        f = new File(path + "/" + resId + ".txt");
        if(f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String in = br.readLine();
                br.close();
                fr.close();
                if(in == null) {
                    return "0";
                }
                return in;
            } catch(Exception ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void updateAllRight() {
        String sql = "SELECT ID AS RESOURCEID FROM `SYS_RESOURCE` WHERE REMOVETAG = 0";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Map<String, Integer>> list = jt.queryForList(sql);
        for(Map<String, Integer> map : list) {
            int resId = map.get("ID").intValue();
            updateRight(resId);
        }
    }

    private void updateRight(int resId) {
        String path = this.getClass().getResource("/").getPath();
        if(path.startsWith("/")) {
            path = path.substring(1);
        }
        path = path.substring(0, path.length() - 1);
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.substring(0, path.lastIndexOf("/"));
        path = path + "/cache";
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }
        f = new File(path + "/" + resId + ".txt");

        RightDao rightDao = (RightDao) GlobalContext.getContextBean(RightDao.class);
        List<Right> rights = rightDao.findByResourceId(resId);
        EmployeeDao empDao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
//        List<Employee> allEmployees = empDao.getAll();
        List<String> toAddEmployees = new ArrayList<String>();
//        if(rights.size() == 0) {
//            if(f.exists()) {
//                f.delete();
//            }
//            return;
//        }
        for(Right r : rights) {
            int orgType = r.getOrgType();
            int orgId = r.getOrgId();
            if(orgType == Right.ORGTYPE_EMPLOYEE) {
                if(toAddEmployees.indexOf(orgId) < 0) {
                    toAddEmployees.add(orgId + "");
                }
            } else {
                List<Employee> emps = empDao.findEmployeesByPositionId(orgId, true);
                for(Employee e : emps) {
                    if(toAddEmployees.indexOf(e.getId() + "") < 0) {
                        toAddEmployees.add("" + e.getId());
                    }
                }
            }
        }
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String in = br.readLine();
            br.close();
            fr.close();
            if(in == null || in.length() == 0) {
                FileOutputStream fos = new FileOutputStream(f, true);
                for(int i = 0; i < toAddEmployees.size(); i++) {
                    if(i > 0) {
                        fos.write(",".getBytes());
                    }
                    fos.write((toAddEmployees.get(i) + "").getBytes());
                }
                fos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f, false);
                for(int i = 0; i < toAddEmployees.size(); i++) {
                    if(i > 0) {
                        fos.write(",".getBytes());
                    }
                    fos.write(toAddEmployees.get(i).getBytes());
                }
                fos.close();
            }
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
        }
    }

    private List<Right> findByResourceId(int resourceId) {
        String sql = "SELECT * FROM `SYS_RIGHT` WHERE REMOVETAG = 0 AND RESOURCEID = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new Object[]{resourceId}, new ObjectRowMapper(Right.class));
    }

    public void remove(int id) {
        Right r = this.get(id);
        String sql = "update `SYS_Right` set removetag = 1 where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
        if(r != null) {
            updateRight(r.getResourceId());
        }
    }

    public List<Right> findAll() {
        String sql = "select * from `SYS_Right` where removetag = 0 order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(Right.class));
    }

    public boolean hasRight(int resId, int empId) {
        String path = this.getClass().getResource("/").getPath();
        path = path.substring(0, path.length() - 1);
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.substring(0, path.lastIndexOf("/"));
        path = path + "/cache";
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }
        f = new File(path + "/" + resId + ".txt");
        if(!f.exists()) {
            return false;
        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String ids = br.readLine();
            if(!StringUtils.isEmpty(ids)) {
                String[] empIds = ids.split(",");
                List<String> list = Arrays.asList(empIds);
                return list.indexOf(empId + "") >= 0;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
