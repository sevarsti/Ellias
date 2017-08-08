package com.saille.milan.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.milan.MilanMatch;
import java.util.Date;
import java.util.List;

public class MilanMatchDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public MilanMatch get(int id) {
        String sql = "select * from MilanMatch where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<MilanMatch> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(MilanMatch.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(MilanMatch obj) {
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
        String sql = "delete from MilanMatch where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<MilanMatch> getAll() {
        String sql = "select * from MilanMatch order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(MilanMatch.class));
    }
}
