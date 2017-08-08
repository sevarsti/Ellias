package com.pay.http;

public abstract interface IAPHttpAnsObserver
{
  public abstract void onError(APBaseHttpAns paramAPBaseHttpAns);

  public abstract void onFinish(APBaseHttpAns paramAPBaseHttpAns);

  public abstract void onStop(APBaseHttpAns paramAPBaseHttpAns);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.IAPHttpAnsObserver
 * JD-Core Version:    0.6.0
 */