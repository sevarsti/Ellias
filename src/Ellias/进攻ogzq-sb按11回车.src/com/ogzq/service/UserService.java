package com.ogzq.service;

import com.ogzq.entity.Result;
import com.ogzq.entity.UserEntity;
import org.apache.http.client.CookieStore;

public abstract interface UserService
{
  public abstract Result login(UserEntity paramUserEntity)
    throws Exception;

  public abstract String getRole(UserEntity paramUserEntity)
    throws Exception;

  public abstract Result getRole2(UserEntity paramUserEntity, String paramString)
    throws Exception;

  public abstract CookieStore getCookieStore();

  public abstract String getAccount();

  public abstract String getAccountName();

  public abstract String getAccountID();

  public abstract String getAccountSign();
}

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.service.UserService
 * JD-Core Version:    0.6.0
 */