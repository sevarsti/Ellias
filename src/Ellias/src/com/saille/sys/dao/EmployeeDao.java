package com.saille.sys.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.sys.Employee;
import com.saille.sys.Position;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import servlet.GlobalContext;

public class EmployeeDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public Employee get(int id) {
        String sql = "select * from Employee where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Employee> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(Employee.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public Employee checkPwd(String loginname, String pwd) {
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Employee> list = jt.query("select * from employee where removetag = 0 and loginname = ? and pwd = ?", new Object[]{loginname, pwd}, new ObjectRowMapper(Employee.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Employee> findEmployeesByPositionId(int posId, boolean inherit) {
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        if(inherit) {
            List<Employee> ret = new ArrayList<Employee>();
            List<Position> pos = new ArrayList<Position>();
            PositionDao posDao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
            Position p = posDao.get(posId);
            if(p == null) {
                return ret;
            }
            p.setLevel(0);
            pos.add(p);
            pos.addAll(posDao.findByParentId(p, true));
            for(Position pp : pos) {
                ret.addAll(this.findEmployeesByPositionId(pp.getId(), false));
            }
            return ret;
        } else {
//            List<Employee> list = jt.query("select * from employee where positionId = ?", new Object[]{posId}, new ObjectRowMapper(Employee.class));
            List<Employee> list = jt.query("select * from employee where id in(select empid from `empposition` where positionid = ? and removetag = 0) and removetag = 0", new Object[]{posId}, new ObjectRowMapper(Employee.class));
            return list;
        }
    }

    public int save(Employee obj) {
        obj.setUpdateTime(new Date());
        if(obj.getId() > 0) {
            update(obj);
        } else {
            obj.setCreateTime(new Date());
            obj.setId(getId(MapperUtils.getTableNameFromAnnotation(obj.getClass())));
            insert(obj);
        }
        return obj.getId();
    }

    public void remove(int id) {
        String sql = "update `Employee` set removetag = 1 where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<Employee> findAll() {
        String sql = "select * from Employee where removetag = 0 order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(Employee.class));
    }
}
