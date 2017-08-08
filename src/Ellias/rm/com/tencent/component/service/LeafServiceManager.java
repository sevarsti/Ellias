package com.tencent.component.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;

public final class LeafServiceManager
{
  private static final String a = "LeafServiceManager";
  private static volatile LeafServiceManager d;
  private final ILeafServiceManager b;
  private final HashMap c = new HashMap();

  private LeafServiceManager(Context paramContext)
  {
    this.b = new i(paramContext);
  }

  public static LeafServiceManager a(Context paramContext)
  {
    if (d != null)
      return d;
    monitorenter;
    try
    {
      if (d != null)
      {
        LeafServiceManager localLeafServiceManager2 = d;
        return localLeafServiceManager2;
      }
    }
    finally
    {
      monitorexit;
    }
    LeafServiceManager localLeafServiceManager1 = new LeafServiceManager(paramContext);
    d = localLeafServiceManager1;
    monitorexit;
    return localLeafServiceManager1;
  }

  private d a(LeafServiceConnection paramLeafServiceConnection, Looper paramLooper)
  {
    synchronized (this.c)
    {
      d locald = (d)this.c.get(paramLeafServiceConnection);
      if (locald == null)
      {
        locald = new d(paramLeafServiceConnection, paramLooper);
        this.c.put(paramLeafServiceConnection, locald);
        return locald;
      }
      locald.a(paramLooper);
    }
  }

  public IBinder a(Class paramClass)
  {
    try
    {
      IBinder localIBinder = this.b.b(paramClass.getName());
      return localIBinder;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.w("LeafServiceManager", "fail to get service " + paramClass, localRemoteException);
    }
    return null;
  }

  public IBinder a(String paramString)
  {
    try
    {
      IBinder localIBinder = this.b.a(paramString);
      return localIBinder;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.w("LeafServiceManager", "fail to get service " + paramString, localRemoteException);
    }
    return null;
  }

  public void a(LeafServiceConnection paramLeafServiceConnection)
  {
    synchronized (this.c)
    {
      d locald = (d)this.c.remove(paramLeafServiceConnection);
      if (locald != null)
        locald.c();
      return;
    }
  }

  public void a(String paramString, IBinder paramIBinder)
  {
    try
    {
      this.b.a(paramString, paramIBinder);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.w("LeafServiceManager", "fail to register service", localRemoteException);
    }
  }

  public boolean a(Class paramClass, LeafServiceConnection paramLeafServiceConnection)
  {
    return a(paramClass, paramLeafServiceConnection, null);
  }

  public boolean a(Class paramClass, LeafServiceConnection paramLeafServiceConnection, Looper paramLooper)
  {
    ILeafServiceConnection localILeafServiceConnection = a(paramLeafServiceConnection, paramLooper).a();
    int i = 0;
    if (localILeafServiceConnection != null);
    try
    {
      this.b.b(paramClass.getName(), localILeafServiceConnection);
      i = 1;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.w("LeafServiceManager", "fail to bind service " + paramClass, localRemoteException);
    }
    return false;
  }

  public boolean a(String paramString, LeafServiceConnection paramLeafServiceConnection)
  {
    return a(paramString, paramLeafServiceConnection, null);
  }

  public boolean a(String paramString, LeafServiceConnection paramLeafServiceConnection, Looper paramLooper)
  {
    ILeafServiceConnection localILeafServiceConnection = a(paramLeafServiceConnection, paramLooper).a();
    int i = 0;
    if (localILeafServiceConnection != null);
    try
    {
      this.b.a(paramString, localILeafServiceConnection);
      i = 1;
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.w("LeafServiceManager", "fail to bind service " + paramString, localRemoteException);
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.service.LeafServiceManager
 * JD-Core Version:    0.6.0
 */