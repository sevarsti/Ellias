package com.tencent.tmassistantsdk.selfUpdateSDK;

import com.tencent.tmassistantsdk.d.i;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.openSDK.d;
import com.tencent.tmassistantsdk.openSDK.g;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;

class e
  implements d
{
  e(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void a(g paramg, int paramInt1, int paramInt2, String paramString)
  {
    if (paramg != null)
    {
      this.a.isFromStartUpdate = false;
      l.b("SelfUpdateSDK", "mOpenSDKYYBStateListener;isFromStartUpdate:" + this.a.isFromStartUpdate);
      if ((paramInt1 == 4) && (paramg.f != null) && ((paramg.f.equals(this.a.hostPackageName)) || (paramg.f.equals("com.tencent.android.qqdownloader"))))
      {
        UpdateInfoLog localUpdateInfoLog = i.g().a(paramg.f, this.a.mAppId);
        localUpdateInfoLog.updateType = 2;
        localUpdateInfoLog.actionCode = 102;
        localUpdateInfoLog.yybExistFlag = 0;
        i.g().a(localUpdateInfoLog);
      }
      this.a.onStateChanged(paramInt1, paramInt2, paramString);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.e
 * JD-Core Version:    0.6.0
 */