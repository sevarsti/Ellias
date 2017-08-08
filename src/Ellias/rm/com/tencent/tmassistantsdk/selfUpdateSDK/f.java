package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.os.Handler;
import com.tencent.tmassistantsdk.c.b;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.d.i;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

class f
  implements b
{
  f(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void a(c paramc)
  {
  }

  public void a(c paramc, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    l.b("SelfUpdateSDK", "OnDownloadSDKTaskStateChanged,clientKey:" + paramc + ",state:" + paramInt1 + ",url:" + paramString1);
    Iterator localIterator = this.a.mWeakListenerArrayList.iterator();
    while (localIterator.hasNext())
    {
      ITMSelfUpdateSDKListener localITMSelfUpdateSDKListener = (ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get();
      if (localITMSelfUpdateSDKListener == null)
        l.b("SelfUpdateSDK", "OnDownloadSDKTaskStateChanged listener = null");
      localITMSelfUpdateSDKListener.OnDownloadYYBStateChanged(paramString1, paramInt1, paramInt2, paramString2);
    }
    if (paramInt1 == 4)
    {
      UpdateInfoLog localUpdateInfoLog = i.g().a("com.tencent.android.qqdownloader", this.a.mAppId);
      localUpdateInfoLog.updateType = this.a.updateType;
      localUpdateInfoLog.actionCode = 102;
      localUpdateInfoLog.yybExistFlag = 0;
      i.g().a(localUpdateInfoLog);
      this.a.mSDKHandler.post(new g(this, paramString1, paramInt1));
    }
  }

  public void a(c paramc, String paramString, long paramLong1, long paramLong2)
  {
    Iterator localIterator = this.a.mWeakListenerArrayList.iterator();
    while (localIterator.hasNext())
    {
      ITMSelfUpdateSDKListener localITMSelfUpdateSDKListener = (ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get();
      if (localITMSelfUpdateSDKListener == null)
        l.b("SelfUpdateSDK", "OnDownloadSDKTaskProgressChanged listener = null");
      localITMSelfUpdateSDKListener.OnDownloadYYBProgressChanged(paramString, paramLong1, paramLong2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.f
 * JD-Core Version:    0.6.0
 */