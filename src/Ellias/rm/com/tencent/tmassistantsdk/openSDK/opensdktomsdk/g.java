package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.c.i;
import com.tencent.tmassistantsdk.d.h;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.openSDK.f;
import com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog;

class g
  implements Runnable
{
  g(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager, String paramString1, String paramString2)
  {
  }

  public void run()
  {
    if ((this.c.mContext != null) && (this.c.mOpenSDK != null) && (!TextUtils.isEmpty(this.a)))
      this.c.insertActionId = this.c.mOpenSDK.b(this.a);
    c localc = this.c.getClient();
    if (localc == null)
      return;
    while (true)
    {
      int i;
      try
      {
        i = localc.a(this.b, "application/vnd.android.package-archive");
        if (4 == i)
        {
          String str = localc.a(this.b).b;
          Message localMessage1 = new Message();
          localMessage1.what = 0;
          localMessage1.obj = str;
          this.c.mMainMessageHandler.sendMessage(localMessage1);
          if (i != 4)
            break;
          TipsInfoLog localTipsInfoLog1 = h.g().a(this.c.mAuthorizedInfo);
          localTipsInfoLog1.installBtnClickCount = (1 + localTipsInfoLog1.installBtnClickCount);
          h.g().a(localTipsInfoLog1);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if (i == 0)
      {
        l.b("OpensdkToMsdkManager", "start success!");
        continue;
      }
      if (1 == i)
      {
        Message localMessage2 = new Message();
        localMessage2.what = 5;
        localMessage2.obj = Integer.valueOf(1);
        this.c.mMainMessageHandler.sendMessage(localMessage2);
        continue;
      }
      Message localMessage3 = new Message();
      localMessage3.what = 3;
      this.c.mMainMessageHandler.sendMessage(localMessage3);
    }
    TipsInfoLog localTipsInfoLog2 = h.g().a(this.c.mAuthorizedInfo);
    localTipsInfoLog2.downloadBtnClickCount = (1 + localTipsInfoLog2.downloadBtnClickCount);
    h.g().a(localTipsInfoLog2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.g
 * JD-Core Version:    0.6.0
 */