package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.os.Handler;
import com.tencent.tmassistantsdk.c.b;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

class h
  implements b
{
  h(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void a(c paramc)
  {
    this.a.onStateChanged(2, -16, "SelfUpdate DwonloadSDKServiceInvalid!");
  }

  public void a(c paramc, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    l.b("SelfUpdateSDK", "mClientSDKListener.OnDownloadSDKTaskStateChanged,clientKey:" + paramc + ",state:" + paramInt1 + ",url:" + paramString1);
    if (paramInt1 == 2)
      this.a.onStateChanged(1, 0, "SelfUpdate DownloadSDKTaskState_DOWNLOADING!");
    do
    {
      return;
      if (paramInt1 != 4)
        continue;
      UpdateInfoLog localUpdateInfoLog = com.tencent.tmassistantsdk.d.i.g().a(this.a.hostPackageName, this.a.mAppId);
      localUpdateInfoLog.updateType = this.a.updateType;
      localUpdateInfoLog.actionCode = 102;
      localUpdateInfoLog.yybExistFlag = 0;
      com.tencent.tmassistantsdk.d.i.g().a(localUpdateInfoLog);
      this.a.mSDKHandler.post(new i(this, paramString1, paramInt1));
      return;
    }
    while (paramInt1 != 5);
    this.a.onStateChanged(2, -17, "mClientSDKListener,OnDownloadSDKTaskStateChanged,DownloadSDKTaskState_FAILED!");
  }

  public void a(c paramc, String paramString, long paramLong1, long paramLong2)
  {
    Iterator localIterator = this.a.mWeakListenerArrayList.iterator();
    while (localIterator.hasNext())
    {
      ITMSelfUpdateSDKListener localITMSelfUpdateSDKListener = (ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get();
      if (localITMSelfUpdateSDKListener == null)
        l.b("SelfUpdateSDK", "OnDownloadSDKTaskProgressChanged listener = null");
      localITMSelfUpdateSDKListener.OnDownloadAppProgressChanged(paramLong1, paramLong2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.h
 * JD-Core Version:    0.6.0
 */