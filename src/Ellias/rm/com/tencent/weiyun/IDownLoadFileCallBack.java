package com.tencent.weiyun;

import com.tencent.tauth.UiError;

public abstract interface IDownLoadFileCallBack
{
  public abstract void onDownloadProgress(int paramInt);

  public abstract void onDownloadStart();

  public abstract void onDownloadSuccess(String paramString);

  public abstract void onError(UiError paramUiError);

  public abstract void onPrepareStart();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.weiyun.IDownLoadFileCallBack
 * JD-Core Version:    0.6.0
 */