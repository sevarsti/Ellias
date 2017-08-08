package com.saille.pampers;

import com.saille.core.dao.BaseJdbcDao;

import java.util.Map;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-17
 * Time: 23:32:22
 * To change this template use File | Settings | File Templates.
 */
public class PampersDao extends BaseJdbcDao {
    public Map queryItem(int id) {
        String sql = "select * from pampers where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Map> list = jt.queryForList(sql, new Object[]{id});
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Map<String, Object>> queryAllItems() {
        String sql = "select * from pampers order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Map<String, Object>> list = jt.queryForList(sql);
        return list;
    }
}
