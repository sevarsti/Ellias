package com.tencent.tmassistantsdk.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.tencent.tmassistantsdk.g.l;

public abstract class e
  implements ServiceConnection
{
  protected Context c;
  public String d;
  protected String e = null;
  protected String f = "INIT";
  protected IInterface g = null;
  protected IInterface h = null;
  protected final Object i = new Object();

  public e(Context paramContext, String paramString1, String paramString2)
  {
    this.c = paramContext;
    this.d = paramString1;
    this.e = paramString2;
  }

  protected abstract void a();

  protected abstract void a(IBinder paramIBinder);

  protected abstract void b();

  protected abstract Intent c();

  protected abstract void d();

  public boolean e()
  {
    boolean bool1 = true;
    monitorenter;
    while (true)
    {
      try
      {
        String str1 = this.f;
        if (str1 != "INIT")
          return bool1;
        l.b("TMAssistantDownloadSDKClient", "initTMAssistantDownloadSDK,clientKey:" + this.d + ",mServiceInterface:" + this.g + ",threadId:" + Thread.currentThread().getId());
        this.f = "INIT";
        if (this.g != null)
        {
          this.f = "FINISH";
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Context localContext = this.c;
      bool1 = false;
      if (localContext == null)
        continue;
      String str2 = this.e;
      bool1 = false;
      if (str2 == null)
        continue;
      try
      {
        Intent localIntent = c();
        boolean bool2 = this.c.bindService(localIntent, this, 1);
        bool1 = bool2;
        l.b("TMAssistantDownloadSDKClient", "initTMAssistantDownloadSDK bindResult:" + bool1);
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          bool1 = false;
        }
      }
    }
  }

  public void f()
  {
    monitorenter;
    try
    {
      l.b("TMAssistantDownloadSDKClient", "unInitTMAssistantDownloadSDK,clientKey:" + this.d + ",mServiceInterface:" + this.g + ",threadId:" + Thread.currentThread().getId());
      if (this.g != null)
      {
        IInterface localIInterface = this.h;
        if (localIInterface == null);
      }
      try
      {
        d();
        label75: if ((this.c != null) && (this != null) && (this.g != null))
          this.c.unbindService(this);
        this.g = null;
        this.h = null;
        this.f = "INIT";
        monitorexit;
        return;
      }
      catch (RemoteException localRemoteException)
      {
        break label75;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected IInterface g()
  {
    if ((this.c != null) && (this.c.getMainLooper().getThread().getId() == Thread.currentThread().getId()))
      throw new Exception("TMAssistantDownloadSDKClient must be called in other Thread(no MainThread)");
    if (this.g == null)
    {
      e();
      this.f = "CONNECTING";
    }
    synchronized (this.i)
    {
      this.i.wait(10000L);
      if (this.g == null)
        throw new Exception("TMAssistantDownloadSDKClient ServiceInterface is null");
    }
    return this.g;
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    a(paramIBinder);
    this.f = "FINISH";
    synchronized (this.i)
    {
      this.i.notifyAll();
      l.b("TMAssistantDownloadSDKClient", "onServiceConnected,clientKey:" + this.d + ",mServiceInterface:" + this.g + ",IBinder:" + paramIBinder + ",threadId:" + Thread.currentThread().getId());
    }
    try
    {
      if ((this.g != null) && (this.h != null))
        b();
      return;
      localObject2 = finally;
      monitorexit;
      throw localObject2;
    }
    catch (RemoteException localRemoteException)
    {
      a();
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    l.b("TMAssistantDownloadSDKClient", "onServiceDisconnected,clientKey:" + this.d);
    monitorenter;
    try
    {
      this.g = null;
      this.f = "INIT";
      synchronized (this.i)
      {
        this.i.notifyAll();
        a();
        monitorexit;
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.c.e
 * JD-Core Version:    0.6.0
 */