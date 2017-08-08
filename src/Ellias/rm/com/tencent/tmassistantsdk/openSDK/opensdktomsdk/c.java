package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.tencent.tmassistantsdk.c.i;
import com.tencent.tmassistantsdk.d.h;
import com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog;

class c
  implements Runnable
{
  c(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public void run()
  {
    try
    {
      i locali = this.a.getClient().a(this.a.mDownloadUrl);
      if (locali != null)
      {
        if (locali.c != 3)
          break label141;
        Message localMessage1 = new Message();
        localMessage1.what = 6;
        Bundle localBundle = new Bundle();
        localBundle.putLong("receiveDataLen", locali.d);
        localBundle.putLong("totalDataLen", locali.e);
        localMessage1.setData(localBundle);
        this.a.mMainMessageHandler.sendMessage(localMessage1);
      }
      while ((locali != null) && (locali.c == 4))
      {
        TipsInfoLog localTipsInfoLog2 = h.g().a(this.a.mAuthorizedInfo);
        localTipsInfoLog2.installTipsCount = (1 + localTipsInfoLog2.installTipsCount);
        h.g().a(localTipsInfoLog2);
        return;
        label141: if (locali.c != 4)
          continue;
        Message localMessage2 = new Message();
        localMessage2.what = 0;
        localMessage2.arg1 = 1;
        localMessage2.obj = locali.b;
        this.a.mMainMessageHandler.sendMessage(localMessage2);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    TipsInfoLog localTipsInfoLog1 = h.g().a(this.a.mAuthorizedInfo);
    localTipsInfoLog1.downloadTipsCount = (1 + localTipsInfoLog1.downloadTipsCount);
    h.g().a(localTipsInfoLog1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.c
 * JD-Core Version:    0.6.0
 */