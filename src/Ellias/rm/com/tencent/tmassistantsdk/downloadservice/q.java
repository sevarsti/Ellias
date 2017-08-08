package com.tencent.tmassistantsdk.downloadservice;

import android.os.RemoteCallbackList;
import com.tencent.tmassistantsdk.a.e;
import com.tencent.tmassistantsdk.c.i;
import com.tencent.tmassistantsdk.downloadservice.a.c;
import com.tencent.tmassistantsdk.g.l;
import java.util.HashMap;
import java.util.Map;

public class q extends e
{
  protected q(TMAssistantDownloadSDKService paramTMAssistantDownloadSDKService)
  {
  }

  public int a()
  {
    return 1;
  }

  public int a(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, Map paramMap)
  {
    l.b("TMAssistantDownloadSDKService", "startDownloadTask，clientkey:" + paramString1 + ",url:" + paramString2 + ",manager:" + this.a.d + ",fileName:" + paramString4);
    if (this.a.d != null)
    {
      com.tencent.tmassistantsdk.f.a.a().a(paramString1, paramString2);
      return this.a.d.a(paramString1, paramString2, paramInt, paramString3, paramString4, paramMap);
    }
    return 3;
  }

  public i a(String paramString1, String paramString2)
  {
    l.b("TMAssistantDownloadSDKService", "getDownloadTaskInfo");
    if (this.a.d != null)
      return this.a.d.a(paramString1, paramString2);
    return null;
  }

  public void a(int paramInt)
  {
    f.a().a(paramInt);
  }

  public void a(String paramString, com.tencent.tmassistantsdk.a.a parama)
  {
    l.b("TMAssistantDownloadSDKService", "registerDownloadTaskCallback,clientKey:" + paramString);
    if ((paramString != null) && (parama != null))
    {
      this.a.b.register(parama);
      monitorenter;
      try
      {
        this.a.c.put(parama, paramString);
        return;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public void a(boolean paramBoolean)
  {
    f.a().a(paramBoolean);
  }

  public void b(String paramString, com.tencent.tmassistantsdk.a.a parama)
  {
    l.b("TMAssistantDownloadSDKService", "unregisterDownloadTaskCallback,clientKey:" + paramString);
    if ((paramString != null) && (parama != null))
    {
      this.a.b.unregister(parama);
      monitorenter;
      try
      {
        this.a.c.remove(parama);
        return;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public void b(String paramString1, String paramString2)
  {
    l.b("TMAssistantDownloadSDKService", "pauseDownloadTask");
    try
    {
      if (this.a.d != null)
        this.a.d.b(paramString1, paramString2);
      l.b("TMAssistantDownloadSDKService", "pauseDownloadTask end");
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void b(boolean paramBoolean)
  {
    f.a().b(paramBoolean);
  }

  public boolean b()
  {
    return a.a().d().booleanValue();
  }

  public void c(String paramString1, String paramString2)
  {
    if (this.a.d != null)
      this.a.d.c(paramString1, paramString2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.q
 * JD-Core Version:    0.6.0
 */