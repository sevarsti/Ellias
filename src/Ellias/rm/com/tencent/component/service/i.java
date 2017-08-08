package com.tencent.component.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;

final class i
  implements ILeafServiceManager
{
  private static final String a = "ServiceManagerClient";
  private static volatile Handler b;
  private Context c;
  private volatile Object d = new Object();
  private volatile ILeafServiceManager e;
  private final HashMap f = new HashMap();

  i(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    b();
  }

  private static Handler a()
  {
    if (b == null)
      monitorenter;
    try
    {
      if (b == null)
      {
        HandlerThread localHandlerThread = new HandlerThread("service_mgr", 10);
        localHandlerThread.start();
        b = new Handler(localHandlerThread.getLooper());
      }
      return b;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static boolean a(IBinder paramIBinder)
  {
    return (paramIBinder != null) && (paramIBinder.isBinderAlive()) && (paramIBinder.pingBinder());
  }

  private boolean b()
  {
    return this.c.bindService(new Intent(this.c, TreeService.class), new j(this), 1);
  }

  private ILeafServiceManager c()
  {
    int i;
    if (!d())
      i = 0;
    while (true)
    {
      if (!d())
      {
        i++;
        if (i > 3)
          try
          {
            throw new IllegalStateException("failed to bind TreeService(reach max retry times).");
          }
          catch (Exception localException)
          {
            LogUtil.e("ServiceManagerClient", "startService(Reason.Restart) exception  :" + localException.getMessage());
            throw new IllegalStateException("failed to bind TreeService(by exception).", localException);
          }
        if (!b());
      }
      try
      {
        label97: synchronized (this.d)
        {
          this.d.wait(5000L);
        }
        SystemClock.sleep(1000L);
        continue;
        return this.e;
      }
      catch (InterruptedException localInterruptedException)
      {
        break label97;
      }
    }
  }

  private boolean d()
  {
    return (this.e != null) && (this.e.asBinder().isBinderAlive());
  }

  public IBinder a(String paramString)
  {
    synchronized (this.f)
    {
      IBinder localIBinder1 = (IBinder)this.f.get(paramString);
      if ((localIBinder1 != null) && (a(localIBinder1)))
        return localIBinder1;
      IBinder localIBinder2 = c().a(paramString);
      if (localIBinder2 != null)
        this.f.put(paramString, localIBinder2);
      return localIBinder2;
    }
  }

  public void a(String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
    IBinder localIBinder;
    synchronized (this.f)
    {
      localIBinder = (IBinder)this.f.get(paramString);
      if ((localIBinder == null) || (!a(localIBinder)));
    }
    try
    {
      paramILeafServiceConnection.a(paramString, localIBinder);
      return;
      localObject = finally;
      monitorexit;
      throw localObject;
      if (d())
      {
        c().a(paramString, paramILeafServiceConnection);
        return;
      }
      a().post(new k(this, paramString, paramILeafServiceConnection));
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
  }

  public boolean a(String paramString, IBinder paramIBinder)
  {
    return c().a(paramString, paramIBinder);
  }

  public IBinder asBinder()
  {
    return c().asBinder();
  }

  public IBinder b(String paramString)
  {
    synchronized (this.f)
    {
      IBinder localIBinder1 = (IBinder)this.f.get(paramString);
      if ((localIBinder1 != null) && (a(localIBinder1)))
        return localIBinder1;
      IBinder localIBinder2 = c().b(paramString);
      if (localIBinder2 != null)
        this.f.put(paramString, localIBinder2);
      return localIBinder2;
    }
  }

  public void b(String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
    IBinder localIBinder;
    synchronized (this.f)
    {
      localIBinder = (IBinder)this.f.get(paramString);
      if ((localIBinder == null) || (!a(localIBinder)));
    }
    try
    {
      paramILeafServiceConnection.a(paramString, localIBinder);
      return;
      localObject = finally;
      monitorexit;
      throw localObject;
      if (d())
      {
        c().b(paramString, paramILeafServiceConnection);
        return;
      }
      a().post(new l(this, paramString, paramILeafServiceConnection));
      return;
    }
    catch (RemoteException localRemoteException)
    {
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.i
 * JD-Core Version:    0.6.0
 */