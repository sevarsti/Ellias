package com.tencent.mid.api;

public abstract interface MidCallback
{
  public abstract void onFail(int paramInt, String paramString);

  public abstract void onSuccess(Object paramObject);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.api.MidCallback
 * JD-Core Version:    0.6.0
 */