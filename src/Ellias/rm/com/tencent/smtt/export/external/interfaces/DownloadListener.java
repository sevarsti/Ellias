package com.tencent.smtt.export.external.interfaces;

public abstract interface DownloadListener
{
  public abstract void onDownloadStart(String paramString1, String paramString2, byte[] paramArrayOfByte, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6, String paramString7);

  public abstract void onDownloadVideo(String paramString, long paramLong, int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.export.external.interfaces.DownloadListener
 * JD-Core Version:    0.6.0
 */