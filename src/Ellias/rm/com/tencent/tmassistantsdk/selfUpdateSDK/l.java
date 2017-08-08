package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.text.TextUtils;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.c.f;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;

class l
  implements Runnable
{
  l(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void run()
  {
    try
    {
      if (this.a.mCheckUpdateMethod != 4)
        break label264;
      if (this.a.getClient(true) != null)
      {
        int j = this.a.getClient(true).a(this.a.mCheckUpdateDownurl, "application/tm.android.apkdiff");
        com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "apkPatch start download Result :" + j);
        if (4 == j)
        {
          com.tencent.tmassistantsdk.c.i locali2 = this.a.getClient(true).a(this.a.mCheckUpdateDownurl);
          if (locali2 != null)
          {
            String str2 = locali2.b;
            com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "apkPatch has yet exists：url:" + this.a.mCheckUpdateDownurl + ";  patchPath:" + str2);
            TMSelfUpdateSDK.access$100(this.a, str2);
          }
          f.b(this.a.mContext);
          return;
        }
        UpdateInfoLog localUpdateInfoLog2 = com.tencent.tmassistantsdk.d.i.g().a(this.a.hostPackageName, this.a.mAppId);
        localUpdateInfoLog2.updateType = this.a.updateType;
        localUpdateInfoLog2.actionCode = 101;
        localUpdateInfoLog2.yybExistFlag = 0;
        com.tencent.tmassistantsdk.d.i.g().a(localUpdateInfoLog2);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      this.a.onStateChanged(2, -20, "SelfUpdate failure, UNKNOWN EXCEPTION!");
      localThrowable.printStackTrace();
      f.b(this.a.mContext);
      return;
    }
    this.a.onStateChanged(2, -18, "SelfUpdate failure, TMAssistantDownloadSDKClient_IS_NULL!");
    return;
    label264: if (this.a.mCheckUpdateMethod == 2)
    {
      if (this.a.getClient(true) != null)
      {
        int i = this.a.getClient(true).a(this.a.mCheckUpdateDownurl, "application/vnd.android.package-archive");
        com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "newapk start download Result :" + i);
        if (4 == i)
        {
          com.tencent.tmassistantsdk.c.i locali1 = this.a.getClient(true).a(this.a.mCheckUpdateDownurl);
          if (locali1 != null)
          {
            String str1 = locali1.b;
            com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "newapk has yet exists：url:" + this.a.mCheckUpdateDownurl + "; apkPath:" + str1);
            if (!TextUtils.isEmpty(str1))
            {
              TMSelfUpdateSDK.access$000(this.a, str1, this.a.hostPackageName, this.a.updateType);
              this.a.onStateChanged(0, 0, "SelfUpdate success !");
            }
          }
          while (true)
          {
            f.b(this.a.mContext);
            return;
            this.a.onStateChanged(2, -19, "SelfUpdate failure, SelfUpdateSDKErrorCode_getSavePath_IS_NULL!");
            continue;
            this.a.onStateChanged(2, -19, "SelfUpdate failure, SelfUpdateSDKErrorCode_GetDownloadTaskState_IS_NULL!");
          }
        }
        UpdateInfoLog localUpdateInfoLog1 = com.tencent.tmassistantsdk.d.i.g().a(this.a.hostPackageName, this.a.mAppId);
        localUpdateInfoLog1.updateType = this.a.updateType;
        localUpdateInfoLog1.actionCode = 101;
        localUpdateInfoLog1.yybExistFlag = 0;
        com.tencent.tmassistantsdk.d.i.g().a(localUpdateInfoLog1);
        return;
      }
      this.a.onStateChanged(2, -18, "SelfUpdate failure, TMAssistantDownloadSDKClient_IS_NULL!");
      return;
    }
    if (this.a.mCheckUpdateMethod == 1)
    {
      this.a.onStateChanged(0, -15, "SelfUpdate success, NO Update!");
      return;
    }
    this.a.onStateChanged(2, -20, "SelfUpdate failure, UNKNOWN EXCEPTION!");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.l
 * JD-Core Version:    0.6.0
 */