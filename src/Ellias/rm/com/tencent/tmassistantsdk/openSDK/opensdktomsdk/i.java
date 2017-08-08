package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.c.b;
import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.g.l;

class i
  implements b
{
  i(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public void a(c paramc)
  {
  }

  public void a(c paramc, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    l.b("OpensdkToMsdkManager", "OnDownloadSDKTaskStateChanged client:" + paramc + " | state:" + paramInt1 + " | errorcode:" + paramInt2 + " | errorMsg:" + paramString2);
    if ((paramc == null) || (TextUtils.isEmpty(paramString1)));
    do
    {
      return;
      if (4 != paramInt1)
        continue;
      this.a.mSubMessageHandler.post(new j(this, paramc, paramString1));
      return;
    }
    while (5 != paramInt1);
    Message localMessage = new Message();
    localMessage.what = 3;
    this.a.mMainMessageHandler.sendMessage(localMessage);
  }

  public void a(c paramc, String paramString, long paramLong1, long paramLong2)
  {
    Message localMessage = new Message();
    localMessage.what = 1;
    Bundle localBundle = new Bundle();
    localBundle.putLong("receiveDataLen", paramLong1);
    localBundle.putLong("totalDataLen", paramLong2);
    localMessage.setData(localBundle);
    this.a.mMainMessageHandler.sendMessage(localMessage);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.i
 * JD-Core Version:    0.6.0
 */