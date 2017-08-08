package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.os.Handler;
import android.os.Message;
import com.tencent.tmassistantsdk.e.e;

class b
  implements e
{
  b(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager)
  {
  }

  public void a(com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b paramb, int paramInt)
  {
    Message localMessage = new Message();
    if (paramInt == 0)
    {
      if (paramb == null)
        return;
      localMessage.what = 4;
    }
    for (localMessage.obj = paramb; ; localMessage.obj = Integer.valueOf(paramInt))
    {
      this.a.mMainMessageHandler.sendMessage(localMessage);
      return;
      localMessage.what = 5;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b
 * JD-Core Version:    0.6.0
 */