package com.tencent.component.plugin.server;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.tencent.component.cache.sp.PreferenceUtil;
import com.tencent.component.plugin.InstallPluginListener;
import com.tencent.component.plugin.PluginHelper;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginManageHandler;
import com.tencent.component.plugin.PluginManageInternalHandler;
import com.tencent.component.plugin.UninstallPluginListener;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.UniqueLock;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;

class g
{
  private static final String a = "PluginManagerServer";
  private static final String b = "plugin_enable_records";
  private static final String c = "plugin_enabled_";
  private final HashMap d = new HashMap();
  private final ArrayList e = new ArrayList();
  private final UniqueLock f = new UniqueLock();
  private final Context g;
  private final c h;
  private PluginManageInternalHandler i;
  private PluginManageHandler j;
  private volatile boolean k;

  g(c paramc)
  {
    this.h = paramc;
    this.g = paramc.a();
  }

  private static void a(IBinder paramIBinder, String paramString)
  {
    if (!(paramIBinder instanceof Binder))
      throw new RuntimeException(paramString);
  }

  private static void a(c paramc)
  {
    Intent localIntent = new Intent("plugin_platform_initialize_start");
    localIntent.putExtra("platform_id", paramc.b());
    paramc.a().sendBroadcast(localIntent);
  }

  private void a(String paramString, int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent("plugin_manager_plugin_changed");
    localIntent.putExtra("plugin_id", paramString);
    localIntent.putExtra("plugin_change", paramInt1);
    localIntent.putExtra("plugin_status", paramInt2);
    localIntent.putExtra("platform_id", this.h.b());
    this.g.sendBroadcast(localIntent);
  }

  private static void b(c paramc)
  {
    Intent localIntent = new Intent("plugin_platform_initialize_finish");
    localIntent.putExtra("platform_id", paramc.b());
    paramc.a().sendBroadcast(localIntent);
  }

  private void b(String paramString, boolean paramBoolean)
  {
    if (!TextUtils.isEmpty(paramString))
      PreferenceUtil.a(this.g, "plugin_enable_records").edit().putBoolean("plugin_enabled_" + paramString, paramBoolean).commit();
  }

  private boolean c()
  {
    try
    {
      a(new h(this));
      return true;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.d("PluginManagerServer", "fail to init plugin service handler", localThrowable);
    }
    return false;
  }

  private boolean c(String paramString, boolean paramBoolean)
  {
    if (!TextUtils.isEmpty(paramString))
      paramBoolean = PreferenceUtil.a(this.g, "plugin_enable_records").getBoolean("plugin_enabled_" + paramString, paramBoolean);
    return paramBoolean;
  }

  private void h(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      PreferenceUtil.a(this.g, "plugin_enable_records").edit().remove("plugin_enabled_" + paramString).commit();
  }

  private i i(String paramString)
  {
    if (!PluginHelper.a(paramString))
      return null;
    synchronized (this.d)
    {
      i locali = (i)this.d.get(paramString);
      return locali;
    }
  }

  private static void j(String paramString)
  {
    if (Binder.getCallingPid() != Process.myPid())
      throw new RuntimeException(paramString);
  }

  public Intent a(String paramString, Uri paramUri)
  {
    PluginManageHandler localPluginManageHandler = this.j;
    Intent localIntent = null;
    if (localPluginManageHandler != null)
    {
      boolean bool = localPluginManageHandler.asBinder().isBinderAlive();
      localIntent = null;
      if (bool)
        localIntent = localPluginManageHandler.a(paramString, paramUri);
    }
    if ((localIntent == null) && (paramUri != null))
    {
      localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(paramUri);
    }
    return localIntent;
  }

  void a()
  {
    if (!this.k)
    {
      this.k = true;
      try
      {
        a(this.h);
        c();
        if (DebugUtil.a())
        {
          this.h.d().a();
          this.h.c().a();
          this.h.e().a();
        }
        while (true)
        {
          b(this.h);
          return;
          this.h.e().a();
          this.h.d().a();
          this.h.c().a();
        }
      }
      catch (Exception localException)
      {
        this.k = false;
        LogUtil.e("PluginManagerServer", localException.getMessage(), localException);
        return;
      }
    }
    LogUtil.i("PluginManagerServer", "ignore init request..");
  }

  public void a(PluginInfo paramPluginInfo, UninstallPluginListener paramUninstallPluginListener)
  {
    if (paramPluginInfo != null)
    {
      boolean bool = this.h.e().a(paramPluginInfo);
      if (paramUninstallPluginListener == null)
        return;
      if (bool);
      try
      {
        paramUninstallPluginListener.a();
        h(paramPluginInfo.pluginId);
        return;
        paramUninstallPluginListener.a("uninstall failed.");
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("PluginManagerServer", localException.getMessage(), localException);
        return;
      }
    }
    if (paramUninstallPluginListener != null)
      paramUninstallPluginListener.a("pluginInfo is null");
  }

  public void a(PluginManageHandler paramPluginManageHandler)
  {
    j("cannot set plugin handler from remote process");
    if (paramPluginManageHandler != null)
      a(paramPluginManageHandler.asBinder(), "only support local process handler");
    this.j = paramPluginManageHandler;
  }

  public void a(PluginManageInternalHandler paramPluginManageInternalHandler)
  {
    j("cannot set plugin internal handler from remote process");
    if (paramPluginManageInternalHandler != null)
      a(paramPluginManageInternalHandler.asBinder(), "only support local process handler");
    this.i = paramPluginManageInternalHandler;
  }

  public void a(String paramString, InstallPluginListener paramInstallPluginListener)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      int m = this.h.e().a(new File(paramString));
      if (paramInstallPluginListener == null)
        return;
      if (m > 0);
      try
      {
        paramInstallPluginListener.a();
        return;
        paramInstallPluginListener.a("errorCode:" + m);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("PluginManagerServer", localException.getMessage(), localException);
        return;
      }
    }
    if (paramInstallPluginListener != null)
      paramInstallPluginListener.a("pluginLocation is empty");
  }

  public void a(String paramString, boolean paramBoolean)
  {
    PluginInfo localPluginInfo = g(paramString);
    if (localPluginInfo != null)
    {
      localPluginInfo.m = paramBoolean;
      LogUtil.i("PluginManagerServer", "mark plugin:" + paramString + " surviveable:" + paramBoolean);
    }
  }

  public boolean a(String paramString)
  {
    if (!PluginHelper.a(paramString))
      return false;
    synchronized (this.d)
    {
      i locali = (i)this.d.remove(paramString);
      if (locali == null)
        return false;
      this.e.remove(locali.a);
      a(paramString, 1, 0);
      return true;
    }
  }

  public boolean a(String paramString, PluginInfo paramPluginInfo)
  {
    if ((!PluginHelper.a(paramString)) || (!PluginHelper.a(paramPluginInfo)))
      return false;
    synchronized (this.d)
    {
      if (this.d.containsKey(paramString))
        return false;
    }
    PluginInfo localPluginInfo = new PluginInfo(paramPluginInfo);
    localPluginInfo.pluginId = paramString;
    i locali = new i();
    locali.a = localPluginInfo;
    localPluginInfo.l = c(paramString, true);
    this.d.put(paramString, locali);
    this.e.add(localPluginInfo);
    monitorexit;
    a(paramString, 1, 1);
    return true;
  }

  public List b()
  {
    synchronized (this.d)
    {
      if (this.e.isEmpty())
      {
        localArrayList = null;
        return localArrayList;
      }
      ArrayList localArrayList = new ArrayList(this.e);
    }
  }

  public boolean b(String paramString)
  {
    return i(paramString) != null;
  }

  public boolean c(String paramString)
  {
    i locali = i(paramString);
    if (locali == null)
      return false;
    Lock localLock = this.f.a(paramString);
    localLock.lock();
    try
    {
      boolean bool = locali.a();
      if (bool)
        return false;
      locali.a(true);
      b(paramString, true);
      localLock.unlock();
      a(paramString, 2, 2);
      return true;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  public boolean d(String paramString)
  {
    i locali = i(paramString);
    if (locali == null)
      return false;
    Lock localLock = this.f.a(paramString);
    localLock.lock();
    try
    {
      boolean bool = locali.a();
      if (!bool)
        return false;
      locali.a(false);
      b(paramString, false);
      localLock.unlock();
      a(paramString, 2, 0);
      return true;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  public boolean e(String paramString)
  {
    i locali = i(paramString);
    return (locali != null) && (locali.a());
  }

  public PluginInfo f(String paramString)
  {
    PluginInfo localPluginInfo1 = g(paramString);
    if (localPluginInfo1 != null)
      return localPluginInfo1;
    if (PluginHelper.a(paramString))
    {
      PluginManageInternalHandler localPluginManageInternalHandler = this.i;
      if (localPluginManageInternalHandler != null)
        try
        {
          if (localPluginManageInternalHandler.a(paramString))
          {
            LogUtil.i("PluginManagerServer", "plugin " + paramString + " not found, try to perform load on demand");
            PluginInfo localPluginInfo2 = g(paramString);
            return localPluginInfo2;
          }
        }
        catch (RemoteException localRemoteException)
        {
          LogUtil.e("PluginManagerServer", localRemoteException.getMessage(), localRemoteException);
        }
    }
    return null;
  }

  public PluginInfo g(String paramString)
  {
    i locali = i(paramString);
    if (locali == null)
      return null;
    return locali.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.g
 * JD-Core Version:    0.6.0
 */