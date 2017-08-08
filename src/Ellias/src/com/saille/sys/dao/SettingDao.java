package com.saille.sys.dao;

import servlet.GlobalContext;
import com.saille.sys.Setting;
import com.saille.sys.dao.mapper.SettingRowMapper;
import com.saille.core.dao.BaseJdbcDao;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-13
 * Time: 23:28:38
 * To change this template use File | Settings | File Templates.
 */
public class SettingDao extends JdbcDaoSupport {
//    private static SettingDao instance = null;
//
//    public static synchronized SettingDao getInstance() {
//        if(instance == null) {
//            instance = (SettingDao) GlobalContext.getContextBean(SettingDao.class);
//        }
//        return instance;
//    }

    public void save(Setting setting) {
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        String sql = "select count(*) from `setting` where `setting` = ?";
        int count = jt.queryForInt(sql, new Object[]{setting.getSetting()});
        if(count > 0) {
            sql = "update `setting` set `group` = ?, `name` = ?, `memo` = ?, `type` = ?";
            List<Object> params = new ArrayList<Object>();
            params.add(setting.getGroup());
            params.add(setting.getName());
            params.add(setting.getMemo());
            params.add(setting.getType());
            if(setting.getType() == Setting.TYPE_INT) {
                sql += ", intvalue = ?";
                params.add(setting.getIntValue());
            } else if(setting.getType() == Setting.TYPE_STRING) {
                sql += ", strvalue = ?";
                params.add(setting.getStrValue());
            } else if(setting.getType() == Setting.TYPE_NUMBER) {
                sql += ", numbervalue = ?, pattern = ?";
                params.add(setting.getNumberValue());
                params.add(setting.getPattern());
            } else if(setting.getType() == Setting.TYPE_DATE) {
                sql += ", datevalue = ?, pattern = ?";
                params.add(setting.getDateValue());
                params.add(setting.getPattern());
            }
            sql += " where `setting` = ?";
            params.add(setting.getSetting());
            jt.update(sql, params.toArray());
        } else {
            sql = "insert into `setting`(`setting`, `group`, `name`, `memo`, `type`,";
            String sql2 = "(?, ?, ?, ?, ?,";
            List<Object> params = new ArrayList<Object>();
            params.add(setting.getSetting());
            params.add(setting.getGroup());
            params.add(setting.getName());
            params.add(setting.getMemo());
            params.add(setting.getType());
            if(setting.getType() == Setting.TYPE_INT) {
                sql += "`intvalue`";
                sql2 += "?";
                params.add(setting.getIntValue());
            } else if(setting.getType() == Setting.TYPE_STRING) {
                sql += "`strvalue`";
                sql2 += "?";
                params.add(setting.getStrValue());
            } else if(setting.getType() == Setting.TYPE_NUMBER) {
                sql += "`numbervalue`,`pattern`";
                sql2 += "?, ?";
                params.add(setting.getStrValue());
                params.add(setting.getPattern());
            } else if(setting.getType() == Setting.TYPE_DATE) {
                sql += "`datevalue`,`pattern`";
                sql2 += "?, ?";
                params.add(setting.getDateValue());
                params.add(setting.getPattern());
            }
            sql += ") values" + sql2 + ")";
            jt.update(sql, params.toArray());
        }
    }

    public Setting get(String setting) {
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Setting> list = jt.query("select * from `setting` where `setting` = ?", new Object[]{setting}, new SettingRowMapper());
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Setting> findAll() {
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Setting> list = jt.query("select * from `setting` order by `setting`", new SettingRowMapper());
        return list;
//        HibernateTemplate _ht = getHibernateTemplate();
//        String _sql = "from Setting m order by m.setting";
//        _ht.setMaxResults(-1);
//        return _ht.find(_sql);
    }
}
