package com.tencent.tauth;

public abstract interface IUiListener
{
  public abstract void onCancel();

  public abstract void onComplete(Object paramObject);

  public abstract void onError(UiError paramUiError);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tauth.IUiListener
 * JD-Core Version:    0.6.0
 */