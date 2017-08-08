package com.saille.sys.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.sys.EmpPosition;
import java.util.Date;
import java.util.List;

public class EmpPositionDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public EmpPosition get(int id) {
        String sql = "select * from `EmpPosition` where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<EmpPosition> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(EmpPosition.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(EmpPosition obj) {
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
        String sql = "update `EmpPosition` set `removeTag` = 1 where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<EmpPosition> findAll() {
        String sql = "select * from `EmpPosition` where `removeTag` = 0 order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(EmpPosition.class));
    }

    public List<EmpPosition> findByEmpId(int empId) {
        String sql = "select * from `EmpPosition` where `removeTag` = 0 and empId = ? order by positionId";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new Object[]{empId}, new ObjectRowMapper(EmpPosition.class));
    }

    public void removeByEmpId(int empId) {
        String sql = "update `EmpPosition` set `removeTag` = 1 where `removeTag` = 0 and empId = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{empId});
    }
}
