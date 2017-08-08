package com.saille.newBBS.dao.jdbc;

import com.saille.core.rowMapper.ObjectRowMapper;
import com.saille.newBBS.User;
import com.saille.newBBS.dao.IUserInfoDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserInfoDaoImpl extends JdbcDaoSupport implements IUserInfoDao {
    public User getUserById(String id, boolean removeIgnore) {
        StringBuffer sql = new StringBuffer().append("select * from USER where userId = ?");
        if(!removeIgnore) {
            sql.append(" and removeTag = 0");
        }
        List list = getJdbcTemplate().query(sql.toString(), new Object[]{id}, new ObjectRowMapper(User.class));
        return list.size() > 0 ? (User) list.get(0) : null;
    }
}