package com.tencent.component.net.http.upload;

public abstract interface IUploadTaskCallback
{
  public abstract void a(UploadTask paramUploadTask, int paramInt);

  public abstract void a(UploadTask paramUploadTask, int paramInt, String paramString);

  public abstract void a(UploadTask paramUploadTask, long paramLong1, long paramLong2);

  public abstract void a(UploadTask paramUploadTask, Object paramObject);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.IUploadTaskCallback
 * JD-Core Version:    0.6.0
 */