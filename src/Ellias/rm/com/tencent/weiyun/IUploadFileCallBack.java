package com.tencent.weiyun;

import com.tencent.tauth.UiError;

public abstract interface IUploadFileCallBack
{
  public abstract void onError(UiError paramUiError);

  public abstract void onPrepareStart();

  public abstract void onUploadProgress(int paramInt);

  public abstract void onUploadStart();

  public abstract void onUploadSuccess();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.weiyun.IUploadFileCallBack
 * JD-Core Version:    0.6.0
 */