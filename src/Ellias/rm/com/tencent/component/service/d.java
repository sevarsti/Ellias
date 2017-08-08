package com.tencent.component.service;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

final class d
{
  private final ILeafServiceConnection a = new g(this);
  private final LeafServiceConnection b;
  private final Looper c;
  private final Handler d;
  private boolean e;
  private boolean f;
  private final HashMap g = new HashMap();

  d(LeafServiceConnection paramLeafServiceConnection, Looper paramLooper)
  {
    this.b = paramLeafServiceConnection;
    this.c = paramLooper;
    if (paramLooper != null);
    for (Handler localHandler = new Handler(paramLooper); ; localHandler = null)
    {
      this.d = localHandler;
      return;
    }
  }

  ILeafServiceConnection a()
  {
    return this.a;
  }

  void a(Looper paramLooper)
  {
    if (this.c != paramLooper)
      throw new RuntimeException("ServiceConnection " + this.b + " registered with differing looper (was " + this.c + " now " + paramLooper + ")");
  }

  void a(String paramString, IBinder paramIBinder)
  {
    if (this.d != null)
    {
      this.d.post(new h(this, paramString, paramIBinder, 0));
      return;
    }
    b(paramString, paramIBinder);
  }

  LeafServiceConnection b()
  {
    return this.b;
  }

  void b(String paramString, IBinder paramIBinder)
  {
    monitorenter;
    e locale1;
    try
    {
      if (this.f)
        return;
      locale1 = (e)this.g.get(paramString);
      if ((locale1 != null) && (locale1.a == paramIBinder))
        return;
    }
    finally
    {
      monitorexit;
    }
    e locale2;
    if (paramIBinder != null)
    {
      this.e = false;
      locale2 = new e(null);
      locale2.a = paramIBinder;
      locale2.b = new f(this, paramString, paramIBinder);
    }
    while (true)
    {
      try
      {
        paramIBinder.linkToDeath(locale2.b, 0);
        this.g.put(paramString, locale2);
        if (locale1 == null)
          continue;
        locale1.a.unlinkToDeath(locale1.b, 0);
        monitorexit;
        if (locale1 == null)
          continue;
        this.b.a(paramString);
        if (paramIBinder == null)
          break;
        this.b.a(paramString, paramIBinder);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        this.g.remove(paramString);
        monitorexit;
        return;
      }
      this.g.remove(paramString);
    }
  }

  void c()
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.g.values().iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        locale.a.unlinkToDeath(locale.b, 0);
      }
    }
    finally
    {
      monitorexit;
    }
    this.g.clear();
    this.f = true;
    monitorexit;
  }

  void c(String paramString, IBinder paramIBinder)
  {
    monitorenter;
    try
    {
      this.e = true;
      e locale = (e)this.g.remove(paramString);
      if ((locale == null) || (locale.a != paramIBinder))
        return;
      locale.a.unlinkToDeath(locale.b, 0);
      monitorexit;
      if (this.d != null)
      {
        this.d.post(new h(this, paramString, paramIBinder, 1));
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    d(paramString, paramIBinder);
  }

  void d(String paramString, IBinder paramIBinder)
  {
    this.b.a(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.d
 * JD-Core Version:    0.6.0
 */