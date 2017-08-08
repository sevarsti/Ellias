package com.tencent.msdk.communicator;

public abstract interface IHttpRequestListener
{
  public abstract void onFailure(String paramString, int paramInt1, int paramInt2);

  public abstract void onSuccess(String paramString, int paramInt1, int paramInt2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.communicator.IHttpRequestListener
 * JD-Core Version:    0.6.0
 */