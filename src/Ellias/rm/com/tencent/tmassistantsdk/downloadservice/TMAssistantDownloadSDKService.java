package com.tencent.tmassistantsdk.downloadservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import com.tencent.tmassistantsdk.g.l;
import java.util.HashMap;

public class TMAssistantDownloadSDKService extends Service
  implements com.tencent.tmassistantsdk.downloadservice.a.a
{
  protected final q a = new q(this);
  protected final RemoteCallbackList b = new RemoteCallbackList();
  protected final HashMap c = new HashMap();
  com.tencent.tmassistantsdk.downloadservice.a.c d = null;

  public void a(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3)
  {
    try
    {
      int i = this.b.beginBroadcast();
      int j = 0;
      while (true)
      {
        if (j < i);
        try
        {
          com.tencent.tmassistantsdk.a.a locala = (com.tencent.tmassistantsdk.a.a)this.b.getBroadcastItem(j);
          String str = (String)this.c.get(locala);
          if ((str != null) && (str.equals(paramString1) == true))
          {
            l.b("TMAssistantDownloadSDKService", "OnDownloadStateChanged,clientKey:" + paramString1 + ",state:" + paramInt1 + ",url:" + paramString2);
            locala.a(paramString1, paramString2, paramInt1, paramInt2, paramString3);
            this.b.finishBroadcast();
            return;
          }
        }
        catch (RemoteException localRemoteException)
        {
          j++;
        }
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void a(String paramString1, String paramString2, long paramLong1, long paramLong2)
  {
    try
    {
      int i = this.b.beginBroadcast();
      int j = 0;
      while (true)
      {
        if (j < i);
        try
        {
          com.tencent.tmassistantsdk.a.a locala = (com.tencent.tmassistantsdk.a.a)this.b.getBroadcastItem(j);
          String str = (String)this.c.get(locala);
          if ((str != null) && (str.equals(paramString1) == true))
          {
            l.b("TMAssistantDownloadSDKService", "OnDownloadProgressChanged,clientKey:" + paramString1 + ",receivedLen:" + paramLong1 + ",url:" + paramString2);
            locala.a(paramString1, paramString2, paramLong1, paramLong2);
            this.b.finishBroadcast();
            return;
          }
        }
        catch (RemoteException localRemoteException)
        {
          j++;
        }
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    l.b("TMAssistantDownloadSDKService", "onBind ,intent:" + paramIntent);
    return this.a;
  }

  public void onCreate()
  {
    super.onCreate();
    com.tencent.tmassistantsdk.g.f.a().a(this);
    NetworkMonitorReceiver.a().b();
    this.d = new com.tencent.tmassistantsdk.downloadservice.a.c(com.tencent.tmassistantsdk.f.a.a().c());
    this.d.a(this);
    this.d.a();
    a.a().b();
    new Thread(new p(this)).start();
    l.b("TMAssistantDownloadSDKService", "onCreate");
  }

  public void onDestroy()
  {
    l.b("TMAssistantDownloadSDKService", "onDestroy");
    super.onDestroy();
    a.a().c();
    com.tencent.tmassistantsdk.d.c.a().b();
    com.tencent.tmassistantsdk.d.f.a().d();
    com.tencent.tmassistantsdk.d.f.a().b();
    this.d.b();
    this.d.a(null);
    this.d = null;
    NetworkMonitorReceiver.a().c();
    com.tencent.tmassistantsdk.g.f.a().c();
    SystemClock.sleep(300L);
  }

  public boolean onUnbind(Intent paramIntent)
  {
    l.b("TMAssistantDownloadSDKService", "onUnbind ,intent:" + paramIntent);
    return super.onUnbind(paramIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.TMAssistantDownloadSDKService
 * JD-Core Version:    0.6.0
 */