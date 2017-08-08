package com.saille.newBBS.dao;

import com.saille.newBBS.User;

public abstract interface IUserInfoDao {
    public abstract User getUserById(String paramString, boolean paramBoolean);
}