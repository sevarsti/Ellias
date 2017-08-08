package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.text.TextUtils;
import android.util.Log;
import com.tencent.tmassistantsdk.c.f;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;

class c
  implements Runnable
{
  c(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void run()
  {
    try
    {
      if (this.a.getClient(false) != null)
      {
        int i = this.a.getClient(false).a(this.a.yybUrl, "application/vnd.android.package-archive");
        this.a.startResult = i;
        l.b("SelfUpdateSDK", "start Result :" + i);
        if (4 == i)
        {
          com.tencent.tmassistantsdk.c.i locali = this.a.getClient(false).a(this.a.yybUrl);
          if (locali != null)
          {
            String str = locali.b;
            Log.i("SelfUpdateSDK", "yyb apk has yet exists：url:" + this.a.yybUrl + ";  yybpath:" + str);
            if (!TextUtils.isEmpty(str))
            {
              TMSelfUpdateSDK.access$000(this.a, str, "com.tencent.android.qqdownloader", this.a.updateType);
              this.a.isFromStartUpdate = true;
              l.b("SelfUpdateSDK", "isFromStartUpdate;startUpdate():" + this.a.isFromStartUpdate);
            }
          }
          f.b(this.a.mContext);
          return;
        }
        UpdateInfoLog localUpdateInfoLog = com.tencent.tmassistantsdk.d.i.g().a("com.tencent.android.qqdownloader", Integer.parseInt("50801"));
        localUpdateInfoLog.updateType = this.a.updateType;
        localUpdateInfoLog.actionCode = 101;
        localUpdateInfoLog.yybExistFlag = 0;
        com.tencent.tmassistantsdk.d.i.g().a(localUpdateInfoLog);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      f.b(this.a.mContext);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.c
 * JD-Core Version:    0.6.0
 */