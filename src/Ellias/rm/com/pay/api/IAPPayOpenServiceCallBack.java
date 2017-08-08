package com.pay.api;

public abstract interface IAPPayOpenServiceCallBack
{
  public abstract void PayOpenServiceCallBack(APPayResponseInfo paramAPPayResponseInfo);

  public abstract void PayOpenServiceNeedLogin();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.api.IAPPayOpenServiceCallBack
 * JD-Core Version:    0.6.0
 */