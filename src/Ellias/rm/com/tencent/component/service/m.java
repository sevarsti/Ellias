package com.tencent.component.service;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;

final class m extends ILeafServiceManager.Stub
{
  private static final String f = "ServiceManagerServer";
  private static final HashMap g = new HashMap();
  private static final LeafServiceProvider h = new n();
  private final Context i;
  private final HashMap j = new HashMap();

  m(Context paramContext)
  {
    this.i = paramContext.getApplicationContext();
  }

  private static void a(IBinder paramIBinder, String paramString)
  {
    if (!(paramIBinder instanceof Binder))
      throw new RuntimeException(paramString);
  }

  private static LeafServiceProvider c(String paramString)
  {
    if (f(paramString))
      return null;
    try
    {
      localLeafServiceProvider = (LeafServiceProvider)Class.forName(paramString).newInstance();
      return localLeafServiceProvider;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        LogUtil.w("ServiceManagerServer", "fail to generate provider for " + paramString, localThrowable);
        LeafServiceProvider localLeafServiceProvider = null;
      }
    }
  }

  private static void d(String paramString)
  {
    if (Binder.getCallingPid() != Process.myPid())
      throw new RuntimeException(paramString);
  }

  private static boolean e(String paramString)
  {
    return !f(paramString);
  }

  private static boolean f(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public IBinder a(String paramString)
  {
    synchronized (this.j)
    {
      IBinder localIBinder = (IBinder)this.j.get(paramString);
      return localIBinder;
    }
  }

  public void a(String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
    IBinder localIBinder = a(paramString);
    if ((localIBinder != null) && (paramILeafServiceConnection != null))
      paramILeafServiceConnection.a(paramString, localIBinder);
  }

  public boolean a(String paramString, IBinder paramIBinder)
  {
    d("cannot register service from remote process: " + paramString);
    a(paramIBinder, "only support local process service: " + paramString);
    if (!e(paramString))
      return false;
    synchronized (this.j)
    {
      if (this.j.containsKey(paramString))
        throw new RuntimeException("cannot register duplicated service " + paramString);
    }
    this.j.put(paramString, paramIBinder);
    LogUtil.i("ServiceManagerServer", "service registered: " + paramString);
    monitorexit;
    return true;
  }

  public IBinder b(String paramString)
  {
    IBinder localIBinder1;
    if (f(paramString))
      localIBinder1 = null;
    IBinder localIBinder2;
    while (true)
    {
      return localIBinder1;
      synchronized (this.j)
      {
        localIBinder1 = (IBinder)this.j.get(paramString);
        if (localIBinder1 != null)
          return localIBinder1;
      }
      monitorexit;
      synchronized (g)
      {
        LeafServiceProvider localLeafServiceProvider = (LeafServiceProvider)g.get(paramString);
        if (localLeafServiceProvider == null)
        {
          localLeafServiceProvider = c(paramString);
          if (localLeafServiceProvider == null)
            localLeafServiceProvider = h;
          g.put(paramString, localLeafServiceProvider);
        }
        if (localLeafServiceProvider == null)
          continue;
        localIBinder2 = localLeafServiceProvider.a(this.i);
        if (localIBinder2 == null)
          break;
        a(localIBinder2, "only support local process service: " + paramString);
        synchronized (this.j)
        {
          IBinder localIBinder3 = (IBinder)this.j.get(paramString);
          if (localIBinder3 == null)
          {
            this.j.put(paramString, localIBinder2);
            localIBinder3 = localIBinder2;
          }
          return localIBinder3;
        }
      }
    }
    return localIBinder2;
  }

  public void b(String paramString, ILeafServiceConnection paramILeafServiceConnection)
  {
    IBinder localIBinder = b(paramString);
    if ((localIBinder != null) && (paramILeafServiceConnection != null))
      paramILeafServiceConnection.a(paramString, localIBinder);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.m
 * JD-Core Version:    0.6.0
 */