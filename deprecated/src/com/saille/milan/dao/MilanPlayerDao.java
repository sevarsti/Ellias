package com.saille.milan.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.milan.MilanPlayer;
import java.util.Date;
import java.util.List;

public class MilanPlayerDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public MilanPlayer get(int id) {
        String sql = "select * from MilanPlayer where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<MilanPlayer> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(MilanPlayer.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(MilanPlayer obj) {
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
        String sql = "delete from MilanPlayer where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<MilanPlayer> getAll() {
        String sql = "select * from MilanPlayer order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(MilanPlayer.class));
    }
}
