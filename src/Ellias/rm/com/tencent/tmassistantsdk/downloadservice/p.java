package com.tencent.tmassistantsdk.downloadservice;

import com.tencent.tmassistantsdk.d.c;
import com.tencent.tmassistantsdk.d.f;

class p
  implements Runnable
{
  p(TMAssistantDownloadSDKService paramTMAssistantDownloadSDKService)
  {
  }

  public void run()
  {
    try
    {
      Thread.sleep(10000L);
      c.a().c();
      f.a().c();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localInterruptedException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.p
 * JD-Core Version:    0.6.0
 */