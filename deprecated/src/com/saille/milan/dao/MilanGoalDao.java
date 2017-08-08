package com.saille.milan.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.milan.MilanGoal;
import java.util.Date;
import java.util.List;

public class MilanGoalDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public MilanGoal get(int id) {
        String sql = "select * from MilanGoal where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<MilanGoal> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(MilanGoal.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(MilanGoal obj) {
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

    public void delete(int id) {
        String sql = "delete from MilanGoal where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<MilanGoal> getAll() {
        String sql = "select * from MilanGoal order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(MilanGoal.class));
    }
}
