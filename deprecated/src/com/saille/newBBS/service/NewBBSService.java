package com.saille.newBBS.service;

import com.saille.newBBS.User;
import com.saille.newBBS.UserInfo;
import com.saille.newBBS.dao.IUserInfoDao;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import servlet.GlobalContext;

public class NewBBSService implements InitializingBean {
    private IUserInfoDao userDao;
    private Map<String, UserInfo> allUsers;
    private static NewBBSService instance;

    public static NewBBSService getInstance() {
        if(instance == null) {
            instance = (NewBBSService) GlobalContext.getSpringContext().getBean("newBBSService", NewBBSService.class);
        }
        return instance;
    }

    public void afterPropertiesSet() throws Exception {
    }

    public void setUserDao(IUserInfoDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(String id, boolean removeIgnore) {
        return this.userDao.getUserById(id, removeIgnore);
    }
}