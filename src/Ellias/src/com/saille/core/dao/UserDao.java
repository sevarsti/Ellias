package com.saille.core.dao;

import com.saille.core.Userinfo;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport {
    public Userinfo get(int id) {
        return (Userinfo) getHibernateTemplate().get(Userinfo.class, Integer.valueOf(id));
    }

    public Userinfo findByUserid(String userid) {
        List list = getHibernateTemplate().find(" from Userinfo where userid = ?", new Object[]{userid});
        return list.size() > 0 ? (Userinfo) list.get(0) : null;
    }
}