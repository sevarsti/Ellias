package com.pay.api;

public abstract interface IAPPayGameServiceCallBack
{
  public abstract void PayGameNeedLogin();

  public abstract void PayGameServiceCallBack(APPayResponseInfo paramAPPayResponseInfo);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.api.IAPPayGameServiceCallBack
 * JD-Core Version:    0.6.0
 */