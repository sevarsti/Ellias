package com.tencent.msdk.myapp.autoupdate;

public abstract class WGSaveUpdateObserver
{
  public abstract void OnCheckNeedUpdateInfo(long paramLong1, String paramString1, long paramLong2, int paramInt1, String paramString2, int paramInt2);

  public abstract void OnDownloadAppProgressChanged(long paramLong1, long paramLong2);

  public abstract void OnDownloadAppStateChanged(int paramInt1, int paramInt2, String paramString);

  public abstract void OnDownloadYYBProgressChanged(String paramString, long paramLong1, long paramLong2);

  public abstract void OnDownloadYYBStateChanged(String paramString1, int paramInt1, int paramInt2, String paramString2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.myapp.autoupdate.WGSaveUpdateObserver
 * JD-Core Version:    0.6.0
 */