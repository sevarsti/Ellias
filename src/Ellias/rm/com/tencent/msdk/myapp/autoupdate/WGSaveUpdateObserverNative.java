package com.tencent.msdk.myapp.autoupdate;

class WGSaveUpdateObserverNative
{
  public static native void OnCheckNeedUpdateInfo(long paramLong1, String paramString1, long paramLong2, int paramInt1, String paramString2, int paramInt2);

  public static native void OnDownloadAppProgressChanged(long paramLong1, long paramLong2);

  public static native void OnDownloadAppStateChanged(int paramInt1, int paramInt2, String paramString);

  public static native void OnDownloadYYBProgressChanged(String paramString, long paramLong1, long paramLong2);

  public static native void OnDownloadYYBStateChanged(String paramString1, int paramInt1, int paramInt2, String paramString2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.myapp.autoupdate.WGSaveUpdateObserverNative
 * JD-Core Version:    0.6.0
 */