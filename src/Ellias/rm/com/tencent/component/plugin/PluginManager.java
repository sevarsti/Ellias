package com.tencent.component.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.plugin.server.PluginService;
import com.tencent.component.utils.ApkUtil;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.UniqueLock;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class PluginManager
{
  public static final String a = "plugin_manager_plugin_changed";
  public static final String b = "plugin_manager_plugin_created";
  public static final String c = "plugin_id";
  public static final String d = "platform_id";
  public static final String e = "plugin_status";
  public static final String f = "plugin_change";
  public static final int g = 1;
  public static final int h = 2;
  private static final String i = "PluginManager";
  private static final String j = "plugin_manager";
  private static ConcurrentHashMap x = new ConcurrentHashMap();
  private IPluginManager k;
  private Context l;
  private final HashMap m = new HashMap();
  private final HashSet n = new HashSet();
  private PluginManager.PluginMonitor o;
  private final Object p = new Object();
  private final UniqueLock q = new UniqueLock();
  private final String r;
  private final PluginPlatformConfig s;
  private volatile ServiceConnection t;
  private volatile boolean u;
  private Handler v;
  private ThreadPool w;

  private PluginManager(Context paramContext, PluginPlatformConfig paramPluginPlatformConfig)
  {
    this.l = paramContext.getApplicationContext();
    this.s = paramPluginPlatformConfig;
    this.r = paramPluginPlatformConfig.a();
    this.v = new Handler(Looper.getMainLooper());
    this.w = new ThreadPool("plugin-thread-pool", 1, 2);
    g();
    m();
  }

  public static PluginManager a(Context paramContext, PluginPlatformConfig paramPluginPlatformConfig)
  {
    String str = paramPluginPlatformConfig.a();
    PluginManager localPluginManager1 = (PluginManager)x.get(str);
    if (localPluginManager1 == null)
    {
      monitorenter;
      try
      {
        PluginManager localPluginManager2 = (PluginManager)x.get(str);
        if (localPluginManager2 == null)
        {
          localPluginManager2 = new PluginManager(paramContext, paramPluginPlatformConfig);
          x.put(str, localPluginManager2);
        }
        return localPluginManager2;
      }
      finally
      {
        monitorexit;
      }
    }
    return localPluginManager1;
  }

  public static PluginManager a(Context paramContext, String paramString)
  {
    PluginPlatformConfig localPluginPlatformConfig = new PluginPlatformConfig();
    localPluginPlatformConfig.a(paramString);
    return a(paramContext, localPluginPlatformConfig);
  }

  private File a(Context paramContext)
  {
    String str = "plugins_pending_" + this.r;
    if (!DebugUtil.a(paramContext));
    for (int i1 = 0; ; i1 = 2)
      return paramContext.getDir(str, i1);
  }

  private void a(PluginInfo paramPluginInfo, Intent paramIntent)
  {
    if (paramPluginInfo != null)
    {
      a(new x(this, paramPluginInfo, paramIntent));
      return;
    }
    LogUtil.w("PluginManager", "fail to start plugin (pluginInfo is null)");
  }

  private void a(PluginManager.Code paramCode)
  {
    this.w.submit(paramCode);
  }

  private void a(Runnable paramRunnable)
  {
    if (Looper.myLooper() != Looper.getMainLooper())
    {
      this.v.post(paramRunnable);
      return;
    }
    paramRunnable.run();
  }

  private void a(String paramString, int paramInt1, int paramInt2)
  {
    PluginManager.PluginMonitor localPluginMonitor = this.o;
    if (localPluginMonitor != null)
      localPluginMonitor.a(paramString, paramInt1, paramInt2);
    PluginManager.PluginListener[] arrayOfPluginListener = p();
    if (arrayOfPluginListener != null)
    {
      int i1 = arrayOfPluginListener.length;
      int i2 = 0;
      if (i2 < i1)
      {
        PluginManager.PluginListener localPluginListener = arrayOfPluginListener[i2];
        if (localPluginListener == null);
        while (true)
        {
          i2++;
          break;
          localPluginListener.a(paramString, paramInt1, paramInt2);
        }
      }
    }
  }

  private void a(List paramList)
  {
    CountDownLatch localCountDownLatch;
    if (paramList != null)
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        PluginInfo localPluginInfo = (PluginInfo)localIterator1.next();
        if ((localPluginInfo != null) && (localPluginInfo.l) && (!TextUtils.isEmpty(localPluginInfo.c)))
        {
          localArrayList1.add(localPluginInfo);
          continue;
        }
        localArrayList2.add(localPluginInfo);
      }
      d(localArrayList2);
      if (localArrayList1.size() > 0)
      {
        localCountDownLatch = new CountDownLatch(localArrayList1.size());
        Iterator localIterator2 = localArrayList1.iterator();
        while (localIterator2.hasNext())
          a(new ad(this, (PluginInfo)localIterator2.next(), localCountDownLatch));
      }
    }
    try
    {
      LogUtil.i("PluginManager", "start to wait check plugin survive result.");
      localCountDownLatch.await();
      LogUtil.i("PluginManager", "finish to wait check plugin survive.");
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      LogUtil.e("PluginManager", localInterruptedException.getMessage(), localInterruptedException);
    }
  }

  private aj b(String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    synchronized (this.m)
    {
      aj localaj = (aj)this.m.get(paramString);
      if ((paramBoolean) && (localaj == null))
      {
        localaj = new aj();
        this.m.put(paramString, localaj);
      }
      return localaj;
    }
  }

  private void b(List paramList)
  {
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        PluginInfo localPluginInfo = (PluginInfo)localIterator.next();
        if ((localPluginInfo == null) || (!localPluginInfo.l) || (TextUtils.isEmpty(localPluginInfo.b)))
          continue;
        c(localPluginInfo);
      }
    }
  }

  private void c(PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null);
    do
      return;
    while (TextUtils.isEmpty(paramPluginInfo.b));
    a(new ae(this, paramPluginInfo));
  }

  private void c(List paramList)
  {
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        PluginInfo localPluginInfo = (PluginInfo)localIterator.next();
        if ((localPluginInfo == null) || (!localPluginInfo.l) || (!localPluginInfo.k.g))
          continue;
        a(localPluginInfo, null);
      }
    }
  }

  private Plugin d(PluginInfo paramPluginInfo)
  {
    if (!PluginHelper.a(paramPluginInfo))
      return null;
    if (paramPluginInfo.a())
      try
      {
        Plugin localPlugin2 = Plugin.a(this.l, paramPluginInfo);
        return localPlugin2;
      }
      catch (Throwable localThrowable2)
      {
        LogUtil.e("PluginManager", "fail to generate plugin for " + paramPluginInfo, localThrowable2);
        return null;
      }
    Lock localLock = PluginFileLock.a(paramPluginInfo.installPath);
    localLock.lock();
    try
    {
      Plugin localPlugin1 = Plugin.a(this.l, paramPluginInfo);
      return localPlugin1;
    }
    catch (Throwable localThrowable1)
    {
      LogUtil.e("PluginManager", "fail to generate plugin for " + paramPluginInfo, localThrowable1);
      return null;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  private void d(List paramList)
  {
    PluginManager.PluginListener[] arrayOfPluginListener = p();
    if (arrayOfPluginListener != null)
    {
      int i1 = arrayOfPluginListener.length;
      int i2 = 0;
      if (i2 < i1)
      {
        PluginManager.PluginListener localPluginListener = arrayOfPluginListener[i2];
        if (localPluginListener == null);
        while (true)
        {
          i2++;
          break;
          localPluginListener.a(paramList);
        }
      }
    }
  }

  private Resources e(PluginInfo paramPluginInfo)
  {
    if (!PluginHelper.a(paramPluginInfo))
      return null;
    String str = paramPluginInfo.installPath;
    if ((!paramPluginInfo.a()) && (!n(str)))
    {
      Lock localLock = PluginFileLock.a(paramPluginInfo.pluginId);
      localLock.lock();
      try
      {
        Resources localResources = ApkUtil.a(this.l, str);
        return localResources;
      }
      finally
      {
        localLock.unlock();
      }
    }
    return getGlobalResources();
  }

  private void g()
  {
    if (this.t == null)
      monitorenter;
    try
    {
      if (this.t == null)
        this.t = new p(this);
      monitorexit;
      LogUtil.i("PluginManager", "try to bind service (platformId:" + this.r + ")");
      PluginService.a(this.l, this.t, this.r);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void h()
  {
    this.l.unbindService(this.t);
    this.l.stopService(new Intent(this.l, PluginService.class));
    synchronized (this.p)
    {
      this.k = null;
      return;
    }
  }

  private IPluginManager i()
  {
    if (!j())
    {
      IPluginManager localIPluginManager = this.k;
      int i1 = 0;
      if (localIPluginManager != null)
        g();
      if (!j())
      {
        i1++;
        if (i1 > 10)
          try
          {
            throw new IllegalStateException("failed to bind PluginService(reach max retry times).");
          }
          catch (Exception localException)
          {
            LogUtil.e("PluginManager", "startService(Reason.Restart) exception  :" + localException.getMessage());
            throw new IllegalStateException("failed to bind PluginService(by exception).", localException);
          }
      }
    }
    try
    {
      label110: synchronized (this.p)
      {
        this.p.wait(300L);
      }
      return this.k;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label110;
    }
  }

  private boolean j()
  {
    return (this.k != null) && (this.k.asBinder().isBinderAlive()) && (this.k.asBinder().pingBinder());
  }

  private void k()
  {
    a(new aa(this));
  }

  private void k(String paramString)
  {
    synchronized (this.m)
    {
      aj localaj = (aj)this.m.remove(paramString);
      if ((localaj != null) && (localaj.a != null))
      {
        PluginInfo localPluginInfo = localaj.a.a();
        LogUtil.i("PluginManager", "remove pluginrecord :" + paramString);
        if (localPluginInfo != null)
          PluginClassLoader.a(localPluginInfo);
      }
      return;
    }
  }

  private void l()
  {
    a(new ac(this));
  }

  private void l(String paramString)
  {
    Intent localIntent = new Intent("plugin_manager_plugin_created");
    localIntent.putExtra("plugin_id", paramString);
    this.l.sendBroadcast(localIntent);
  }

  private void m()
  {
    IntentFilter localIntentFilter1 = new IntentFilter();
    localIntentFilter1.addAction("plugin_manager_plugin_created");
    localIntentFilter1.addAction("plugin_manager_plugin_changed");
    this.l.registerReceiver(new z(this), localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter();
    localIntentFilter2.addAction("plugin_platform_initialize_start");
    localIntentFilter2.addAction("plugin_platform_initialize_finish");
    this.l.registerReceiver(new ab(this), localIntentFilter2);
  }

  private void m(String paramString)
  {
    PluginManager.PluginMonitor localPluginMonitor = this.o;
    if (localPluginMonitor != null)
      localPluginMonitor.a(paramString);
  }

  private void n()
  {
    PluginManager.PluginListener[] arrayOfPluginListener = p();
    if (arrayOfPluginListener != null)
    {
      int i1 = arrayOfPluginListener.length;
      int i2 = 0;
      if (i2 < i1)
      {
        PluginManager.PluginListener localPluginListener = arrayOfPluginListener[i2];
        if (localPluginListener == null);
        while (true)
        {
          i2++;
          break;
          localPluginListener.a();
        }
      }
    }
  }

  private static boolean n(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  private void o()
  {
    PluginManager.PluginListener[] arrayOfPluginListener = p();
    if (arrayOfPluginListener != null)
    {
      int i1 = arrayOfPluginListener.length;
      int i2 = 0;
      if (i2 < i1)
      {
        PluginManager.PluginListener localPluginListener = arrayOfPluginListener[i2];
        if (localPluginListener == null);
        while (true)
        {
          i2++;
          break;
          localPluginListener.b();
        }
      }
    }
  }

  private PluginManager.PluginListener[] p()
  {
    synchronized (this.n)
    {
      if (this.n.isEmpty())
      {
        localObject2 = null;
        if (localObject2 != null)
          localObject2 = (PluginManager.PluginListener[])this.n.toArray(localObject2);
        return localObject2;
      }
      Object localObject2 = new PluginManager.PluginListener[this.n.size()];
    }
  }

  Intent a(String paramString, Uri paramUri)
  {
    IPluginManager localIPluginManager = i();
    if (localIPluginManager != null)
      try
      {
        Intent localIntent = localIPluginManager.a(this.r, paramString, paramUri);
        return localIntent;
      }
      catch (RemoteException localRemoteException)
      {
        LogUtil.e("PluginManager", "handlePluginUri", localRemoteException);
      }
    return null;
  }

  Plugin a(PluginInfo paramPluginInfo)
  {
    return a(paramPluginInfo, true);
  }

  Plugin a(PluginInfo paramPluginInfo, boolean paramBoolean)
  {
    boolean bool = true;
    Plugin localPlugin3;
    if (paramPluginInfo == null)
    {
      localPlugin3 = null;
      return localPlugin3;
    }
    aj localaj = b(paramPluginInfo.pluginId, bool);
    Plugin localPlugin1 = localaj.a;
    if (localPlugin1 != null)
      return localPlugin1;
    Lock localLock = this.q.a(paramPluginInfo.pluginId);
    localLock.lock();
    while (true)
    {
      try
      {
        Plugin localPlugin2 = localaj.a;
        i1 = 0;
        if (localPlugin2 != null)
          continue;
        i1 = 0;
        if (!paramBoolean)
          continue;
        localaj.a = d(paramPluginInfo);
        if (localaj.a == null)
          continue;
        localaj.a.a(this.l, this, PluginHelper.a(this.r, this), paramPluginInfo);
        if (localaj.a == null)
          continue;
        break label197;
        localPlugin3 = localaj.a;
        localLock.unlock();
        if ((i1 == 0) || (localPlugin3 == null))
          break;
        a(new y(this, localPlugin3));
        l(paramPluginInfo.pluginId);
        return localPlugin3;
        bool = false;
      }
      finally
      {
        localLock.unlock();
      }
      label197: int i1 = bool;
    }
  }

  public void a()
  {
  }

  public void a(int paramInt, Object paramObject)
  {
    IPluginManager localIPluginManager;
    if (j())
    {
      localIPluginManager = i();
      if (localIPluginManager == null);
    }
    else
    {
      while (true)
      {
        PluginInfo localPluginInfo;
        try
        {
          List localList = localIPluginManager.a(this.r);
          if (localList != null)
          {
            Iterator localIterator = localList.iterator();
            if (localIterator.hasNext())
            {
              localPluginInfo = (PluginInfo)localIterator.next();
              Plugin localPlugin = a(localPluginInfo, false);
              if (localPlugin == null)
                break label101;
              localPlugin.onBusinessLifeCycle(paramInt, paramObject);
              continue;
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          LogUtil.w("PluginManager", "fail to transparent lifecycle (remote exception)", localRemoteException);
        }
        return;
        label101: LogUtil.w("PluginManager", "fail to transparent lifecycle :" + localPluginInfo + " (no record)");
      }
    }
    LogUtil.w("PluginManager", "cannot get remote service, fail to transparent lifecycle ");
  }

  public void a(PluginInfo paramPluginInfo, UninstallPluginListener paramUninstallPluginListener)
  {
    if ((paramPluginInfo == null) || (TextUtils.isEmpty(paramPluginInfo.pluginId)))
    {
      if (paramUninstallPluginListener != null);
      try
      {
        paramUninstallPluginListener.a("pluginInfo/pluginId is empty");
        return;
      }
      catch (RemoteException localRemoteException)
      {
        LogUtil.e("PluginManager", localRemoteException.getMessage(), localRemoteException);
        return;
      }
    }
    a(new q(this, paramPluginInfo, paramUninstallPluginListener));
  }

  public void a(PluginManageHandler paramPluginManageHandler)
  {
    a(new v(this, paramPluginManageHandler));
  }

  public void a(PluginManager.GetPluginListCallback paramGetPluginListCallback)
  {
    if (paramGetPluginListCallback != null)
      a(new u(this, paramGetPluginListCallback));
  }

  public void a(PluginManager.PluginListener paramPluginListener)
  {
    if (paramPluginListener == null)
      return;
    synchronized (this.n)
    {
      this.n.add(paramPluginListener);
      return;
    }
  }

  public void a(PluginManager.PluginMonitor paramPluginMonitor)
  {
    this.o = paramPluginMonitor;
  }

  public void a(String paramString, Intent paramIntent)
  {
    a(paramString, new w(this, paramIntent));
  }

  public void a(String paramString, InstallPluginListener paramInstallPluginListener)
  {
    a(new ai(this, paramString, paramInstallPluginListener));
  }

  public void a(String paramString, PluginManager.GetPluginInfoCallback paramGetPluginInfoCallback)
  {
    if ((paramGetPluginInfoCallback != null) && (!TextUtils.isEmpty(paramString)))
      a(new t(this, paramGetPluginInfoCallback, paramString));
  }

  public void a(String paramString, PluginManager.LoadPluginInfoCallback paramLoadPluginInfoCallback)
  {
    if ((paramLoadPluginInfoCallback != null) && (!TextUtils.isEmpty(paramString)))
      a(new s(this, paramLoadPluginInfoCallback, paramString));
  }

  public void a(String paramString, UninstallPluginListener paramUninstallPluginListener)
  {
    if (TextUtils.isEmpty(paramString))
    {
      if (paramUninstallPluginListener != null);
      try
      {
        paramUninstallPluginListener.a("pluginId is empty");
        return;
      }
      catch (RemoteException localRemoteException)
      {
        LogUtil.e("PluginManager", localRemoteException.getMessage(), localRemoteException);
        return;
      }
    }
    a(paramString, new r(this, paramUninstallPluginListener));
  }

  void a(String paramString, boolean paramBoolean)
  {
    if (!PluginHelper.a(paramString));
    IPluginManager localIPluginManager;
    do
    {
      return;
      localIPluginManager = i();
    }
    while (localIPluginManager == null);
    try
    {
      localIPluginManager.a(this.r, paramString, paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.e("PluginManager", "markPluginSurviveable", localRemoteException);
    }
  }

  boolean a(String paramString)
  {
    if (!PluginHelper.a(paramString));
    IPluginManager localIPluginManager;
    do
    {
      return false;
      localIPluginManager = i();
    }
    while (localIPluginManager == null);
    try
    {
      boolean bool = localIPluginManager.a(this.r, paramString);
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.e("PluginManager", "unregisterPlugin", localRemoteException);
    }
    return false;
  }

  boolean a(String paramString, PluginInfo paramPluginInfo)
  {
    if ((!PluginHelper.a(paramString)) || (!PluginHelper.a(paramPluginInfo)));
    IPluginManager localIPluginManager;
    do
    {
      return false;
      localIPluginManager = i();
    }
    while (localIPluginManager == null);
    try
    {
      boolean bool = localIPluginManager.a(this.r, paramString, paramPluginInfo);
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      LogUtil.e("PluginManager", "registerPlugin", localRemoteException);
    }
    return false;
  }

  Resources b(PluginInfo paramPluginInfo)
  {
    Resources localResources1;
    if (paramPluginInfo == null)
    {
      localResources1 = null;
      return localResources1;
    }
    aj localaj = b(paramPluginInfo.pluginId, true);
    ak localak1;
    if (localaj == null)
      localak1 = null;
    while (true)
    {
      label33: Lock localLock;
      ak localak2;
      label65: Resources localResources2;
      if (localak1 == null)
      {
        localResources1 = null;
        if (localResources1 != null)
          break;
        localLock = this.q.a(paramPluginInfo.pluginId);
        localLock.lock();
        if (localaj != null)
          break label137;
        localak2 = null;
        if (localak2 != null)
          break label146;
        localResources2 = null;
        label73: if (localResources2 != null);
      }
      try
      {
        localResources2 = e(paramPluginInfo);
        if ((localResources2 != null) && (localaj != null))
          localaj.b = new ak(localResources2);
        return localResources2;
        localak1 = localaj.b;
        continue;
        localResources1 = (Resources)localak1.get();
        break label33;
        label137: localak2 = localaj.b;
        break label65;
        label146: localResources2 = (Resources)localak2.get();
        break label73;
      }
      finally
      {
        localLock.unlock();
      }
    }
    throw localObject;
  }

  public List b()
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (this.m)
    {
      Iterator localIterator = this.m.entrySet().iterator();
      while (localIterator.hasNext())
      {
        aj localaj = (aj)((Map.Entry)localIterator.next()).getValue();
        if ((localaj == null) || (localaj.a == null))
          continue;
        localArrayList.add(localaj.a);
      }
    }
    monitorexit;
    return localArrayList;
  }

  public void b(PluginManager.PluginListener paramPluginListener)
  {
    if (paramPluginListener == null)
      return;
    synchronized (this.n)
    {
      this.n.remove(paramPluginListener);
      return;
    }
  }

  boolean b(String paramString)
  {
    IPluginManager localIPluginManager = i();
    if (localIPluginManager != null)
      try
      {
        boolean bool = localIPluginManager.b(this.r, paramString);
        return bool;
      }
      catch (RemoteException localRemoteException)
      {
        LogUtil.e("PluginManager", "isPluginRegistered", localRemoteException);
      }
    return false;
  }

  public void c()
  {
    IPluginManager localIPluginManager;
    if (j())
    {
      localIPluginManager = i();
      if (localIPluginManager == null);
    }
    else
    {
      while (true)
      {
        PluginInfo localPluginInfo;
        try
        {
          List localList = localIPluginManager.a(this.r);
          if (localList != null)
          {
            Iterator localIterator = localList.iterator();
            if (localIterator.hasNext())
            {
              localPluginInfo = (PluginInfo)localIterator.next();
              Plugin localPlugin = a(localPluginInfo, false);
              if (localPlugin == null)
                break label94;
              localPlugin.c();
              continue;
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          LogUtil.w("PluginManager", "fail to stop all plugin (remote exception)", localRemoteException);
        }
        return;
        label94: LogUtil.w("PluginManager", "fail to stop plugin:" + localPluginInfo + " (no record)");
      }
    }
    LogUtil.w("PluginManager", "cannot get remote service, stop all plugin failed!");
  }

  public void c(String paramString)
  {
    a(new af(this, paramString));
  }

  public void d()
  {
    IPluginManager localIPluginManager;
    if (j())
    {
      localIPluginManager = i();
      if (localIPluginManager == null);
    }
    else
    {
      while (true)
      {
        PluginInfo localPluginInfo;
        try
        {
          List localList = localIPluginManager.a(this.r);
          if (localList != null)
          {
            Iterator localIterator = localList.iterator();
            if (localIterator.hasNext())
            {
              localPluginInfo = (PluginInfo)localIterator.next();
              Plugin localPlugin = a(localPluginInfo, false);
              if (localPlugin == null)
                break label94;
              localPlugin.d();
              continue;
            }
          }
        }
        catch (RemoteException localRemoteException)
        {
          LogUtil.w("PluginManager", "fail to move all plugin to background (remote exception)", localRemoteException);
        }
        return;
        label94: LogUtil.w("PluginManager", "fail to stop plugin:" + localPluginInfo + " (no record)");
      }
    }
    LogUtil.w("PluginManager", "cannot get remote service, move all plugin to background failed!");
  }

  public void d(String paramString)
  {
    a(new ag(this, paramString));
  }

  public Context e()
  {
    return this.l;
  }

  public void e(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      a(new ah(this, paramString));
  }

  public int f()
  {
    return this.s.c();
  }

  PluginInfo f(String paramString)
  {
    IPluginManager localIPluginManager = i();
    if (localIPluginManager != null)
      try
      {
        PluginInfo localPluginInfo = localIPluginManager.f(this.r, paramString);
        return localPluginInfo;
      }
      catch (RemoteException localRemoteException)
      {
        LogUtil.e("PluginManager", "getPluginInfo", localRemoteException);
      }
    return null;
  }

  PluginInfo g(String paramString)
  {
    IPluginManager localIPluginManager = i();
    if (localIPluginManager != null)
      try
      {
        PluginInfo localPluginInfo = localIPluginManager.g(this.r, paramString);
        return localPluginInfo;
      }
      catch (RemoteException localRemoteException)
      {
        LogUtil.e("PluginManager", "getPluginInfo", localRemoteException);
      }
    return null;
  }

  @PluginApi(a=4)
  public Resources getGlobalResources()
  {
    return this.l.getResources();
  }

  public void h(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (j()))
    {
      IPluginManager localIPluginManager = i();
      if (localIPluginManager != null)
        try
        {
          Plugin localPlugin = a(localIPluginManager.g(this.r, paramString), false);
          if (localPlugin != null)
          {
            localPlugin.c();
            return;
          }
          LogUtil.w("PluginManager", "fail to stop plugin:" + paramString + " (no record)");
          return;
        }
        catch (RemoteException localRemoteException)
        {
          LogUtil.w("PluginManager", "fail to stop plugin:" + paramString + "(remote exception)", localRemoteException);
          return;
        }
      LogUtil.w("PluginManager", "cannot get remote service, stop plugin:" + paramString + " failed!");
    }
  }

  public void i(String paramString)
  {
    a(paramString, null);
  }

  public void j(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (j()))
    {
      IPluginManager localIPluginManager = i();
      if (localIPluginManager != null)
        try
        {
          Plugin localPlugin = a(localIPluginManager.g(this.r, paramString), false);
          if (localPlugin != null)
          {
            localPlugin.d();
            return;
          }
          LogUtil.w("PluginManager", "fail to move plugin to background :" + paramString + " (no record)");
          return;
        }
        catch (RemoteException localRemoteException)
        {
          LogUtil.w("PluginManager", "fail to move plugin to background:" + paramString + "(remote exception)", localRemoteException);
          return;
        }
      LogUtil.w("PluginManager", "cannot get remote service, fail to move plugin to background:" + paramString);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginManager
 * JD-Core Version:    0.6.0
 */