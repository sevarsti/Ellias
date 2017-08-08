package com.tencent.tmassistantsdk.selfUpdateSDK;

import com.tencent.tmassistantsdk.c.c;

class d
  implements Runnable
{
  d(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void run()
  {
    if (this.a.getClient(false) != null);
    try
    {
      this.a.getClient(false).b(this.a.yybUrl);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.d
 * JD-Core Version:    0.6.0
 */