package com.tencent.msdk.myapp.autoupdate;

import com.tencent.msdk.Singleton;

public class WGSaveUpdateObserverProxy extends WGSaveUpdateObserver
{
  public static final Singleton<WGSaveUpdateObserverProxy> gDefault = new Singleton()
  {
    protected WGSaveUpdateObserverProxy create()
    {
      return new WGSaveUpdateObserverProxy(null);
    }
  };
  private WGSaveUpdateObserver mJavaObserver = null;

  public void OnCheckNeedUpdateInfo(long paramLong1, String paramString1, long paramLong2, int paramInt1, String paramString2, int paramInt2)
  {
    if (this.mJavaObserver != null)
    {
      this.mJavaObserver.OnCheckNeedUpdateInfo(paramLong1, paramString1, paramLong2, paramInt1, paramString2, paramInt2);
      return;
    }
    WGSaveUpdateObserverNative.OnCheckNeedUpdateInfo(paramLong1, paramString1, paramLong2, paramInt1, paramString2, paramInt2);
  }

  public void OnDownloadAppProgressChanged(long paramLong1, long paramLong2)
  {
    if (this.mJavaObserver != null)
    {
      this.mJavaObserver.OnDownloadAppProgressChanged(paramLong1, paramLong2);
      return;
    }
    WGSaveUpdateObserverNative.OnDownloadAppProgressChanged(paramLong1, paramLong2);
  }

  public void OnDownloadAppStateChanged(int paramInt1, int paramInt2, String paramString)
  {
    if (this.mJavaObserver != null)
    {
      this.mJavaObserver.OnDownloadAppStateChanged(paramInt1, paramInt2, paramString);
      return;
    }
    WGSaveUpdateObserverNative.OnDownloadAppStateChanged(paramInt1, paramInt2, paramString);
  }

  public void OnDownloadYYBProgressChanged(String paramString, long paramLong1, long paramLong2)
  {
    if (this.mJavaObserver != null)
    {
      this.mJavaObserver.OnDownloadYYBProgressChanged(paramString, paramLong1, paramLong2);
      return;
    }
    WGSaveUpdateObserverNative.OnDownloadYYBProgressChanged(paramString, paramLong1, paramLong2);
  }

  public void OnDownloadYYBStateChanged(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if (this.mJavaObserver != null)
    {
      this.mJavaObserver.OnDownloadYYBStateChanged(paramString1, paramInt1, paramInt2, paramString2);
      return;
    }
    WGSaveUpdateObserverNative.OnDownloadYYBStateChanged(paramString1, paramInt1, paramInt2, paramString2);
  }

  public WGSaveUpdateObserver getmJavaObserver()
  {
    return this.mJavaObserver;
  }

  public void setmJavaObserver(WGSaveUpdateObserver paramWGSaveUpdateObserver)
  {
    this.mJavaObserver = paramWGSaveUpdateObserver;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.myapp.autoupdate.WGSaveUpdateObserverProxy
 * JD-Core Version:    0.6.0
 */