package com.pay.buyManager;

public abstract interface IAPGetKeyCallBack
{
  public abstract void onGetKeyCancel();

  public abstract void onGetKeyFail(int paramInt, String paramString);

  public abstract void onGetKeySucc(String paramString);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.IAPGetKeyCallBack
 * JD-Core Version:    0.6.0
 */