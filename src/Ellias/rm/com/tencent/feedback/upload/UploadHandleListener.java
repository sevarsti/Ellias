package com.tencent.feedback.upload;

public abstract interface UploadHandleListener
{
  public abstract void onUploadEnd(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, String paramString);

  public abstract void onUploadStart(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.upload.UploadHandleListener
 * JD-Core Version:    0.6.0
 */