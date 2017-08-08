package com.saille.core.service;

import com.saille.core.Userinfo;
import com.saille.core.dao.UserDao;

public class UserService {
    private UserDao dao;

    public Userinfo get(int id) {
        return this.dao.get(id);
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }
}