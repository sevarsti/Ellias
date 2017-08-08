package com.tencent.msdk.db;

import com.tencent.msdk.api.LoginRet;

public class LoginInfoManager
{
  private static volatile LoginInfoManager instance;

  public static LoginInfoManager getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new LoginInfoManager();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void deleteAllLoginRecord()
  {
    new QQLoginModel().deleteAll();
    new WxLoginModel().deleteAll();
  }

  public void deleteLoginRecord(String paramString)
  {
    new QQLoginModel(paramString).delete();
    new WxLoginModel(paramString).delete();
  }

  public LoginRet getLastLoginUserInfo()
  {
    QQLoginModel localQQLoginModel = new QQLoginModel().getLastLoginUserInfo();
    WxLoginModel localWxLoginModel = new WxLoginModel().getLastLoginUserInfo();
    if (localQQLoginModel == null)
    {
      if (localWxLoginModel == null)
        return new LoginRet();
      return localWxLoginModel.convertToLoginRet();
    }
    if (localWxLoginModel == null)
      return localQQLoginModel.convertToLoginRet();
    if (localQQLoginModel.create_at > localWxLoginModel.create_at)
      return localQQLoginModel.convertToLoginRet();
    return localWxLoginModel.convertToLoginRet();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.db.LoginInfoManager
 * JD-Core Version:    0.6.0
 */