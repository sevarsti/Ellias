package com.tencent.tmassistantsdk.selfUpdateSDK;

import com.tencent.tmassistantsdk.d.i;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;

class k
  implements b
{
  k(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void a(String paramString, int paramInt)
  {
    if ((paramInt == 1) && (paramString != null) && ((paramString.equals(this.a.hostPackageName)) || (paramString.equals("com.tencent.android.qqdownloader"))))
    {
      UpdateInfoLog localUpdateInfoLog = i.g().a(paramString, this.a.mAppId);
      localUpdateInfoLog.updateType = this.a.updateType;
      localUpdateInfoLog.actionCode = 103;
      localUpdateInfoLog.yybExistFlag = 0;
      i.g().a(localUpdateInfoLog);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.k
 * JD-Core Version:    0.6.0
 */