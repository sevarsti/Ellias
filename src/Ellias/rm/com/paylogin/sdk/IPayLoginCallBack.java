package com.paylogin.sdk;

public abstract interface IPayLoginCallBack
{
  public abstract void LoginFailCallBack(int paramInt, String paramString);

  public abstract void LoginNeedVerify(byte[] paramArrayOfByte);

  public abstract void LoginSuccCallBack(String paramString1, String paramString2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.paylogin.sdk.IPayLoginCallBack
 * JD-Core Version:    0.6.0
 */