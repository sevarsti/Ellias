package com.pay.login;

public abstract interface IAPLoginCallBack
{
  public abstract void onLoginFail();

  public abstract void onLoginSucc(String paramString1, String paramString2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.IAPLoginCallBack
 * JD-Core Version:    0.6.0
 */