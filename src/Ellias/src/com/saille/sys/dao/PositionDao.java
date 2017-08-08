package com.saille.sys.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import com.saille.sys.Position;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PositionDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public Position get(int id) {
        String sql = "select * from Position where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Position> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(Position.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(Position obj) {
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
        String sql = "update `Position` set removetag = 1 where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<Position> findAll() {
        String sql = "select * from Position where removetag = 0 order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(Position.class));
    }

    public List<Position> findByParentId(Position parent, boolean inherit) {
        if(inherit) {
            List<Position> ret = new ArrayList<Position>();
            List<Position> list = findByParentId(parent, false);
            for(Position p : list) {
                p.setLevel(parent.getLevel() + 1);
            }
            ret.addAll(list);
            for(Position p : list) {
                ret.addAll(ret.indexOf(p) + 1, findByParentId(p, inherit));
            }
            return ret;
        } else {
            String sql = "select * from Position where parentId = ? order by id";
            JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
            List<Position> list = jt.query(sql, new Object[]{parent.getId()}, new ObjectRowMapper(Position.class));
            for(Position p : list) {
                p.setLevel(parent.getLevel() + 1);
            }
            return list;
        }
    }
}
