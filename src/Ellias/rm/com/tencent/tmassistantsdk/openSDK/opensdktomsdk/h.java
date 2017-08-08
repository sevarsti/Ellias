package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import com.tencent.tmassistantsdk.c.c;
import com.tencent.tmassistantsdk.g.l;

class h
  implements Runnable
{
  h(TMOpenSDKToMsdkManager paramTMOpenSDKToMsdkManager, String paramString)
  {
  }

  public void run()
  {
    c localc = this.b.getClient();
    try
    {
      if (localc.a(this.a) != null)
      {
        localc.b(this.a);
        return;
      }
      l.b("OpensdkToMsdkManager", "getDownloadTaskState taskinfo is null!");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.h
 * JD-Core Version:    0.6.0
 */