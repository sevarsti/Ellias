package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.c.c;

class j
  implements Runnable
{
  j(i parami, c paramc, String paramString)
  {
  }

  public void run()
  {
    try
    {
      String str = this.a.a(this.b).b;
      if (!TextUtils.isEmpty(str))
      {
        Message localMessage = new Message();
        localMessage.what = 0;
        localMessage.obj = str;
        this.c.a.mMainMessageHandler.sendMessage(localMessage);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.j
 * JD-Core Version:    0.6.0
 */