package com.tencent.component.plugin;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;

public abstract class Plugin
{
  private static final String a = "Plugin";
  private Context b;
  private PluginInfo c;
  private PluginManager d;
  private PluginHelper e;
  private Handler f = new Handler(Looper.getMainLooper());
  private volatile boolean g;
  private volatile boolean h;

  static Plugin a(Context paramContext, PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null);
    String str;
    while (true)
    {
      return null;
      str = paramPluginInfo.a;
      if (TextUtils.isEmpty(str))
        continue;
      try
      {
        Class localClass = PluginClassLoader.a(paramContext, paramPluginInfo).a(str);
        LogUtil.d("Plugin", "new plugin for " + paramPluginInfo.pluginId + " " + paramPluginInfo.installPath);
        if (localClass == null)
          continue;
        Plugin localPlugin = (Plugin)localClass.newInstance();
        return localPlugin;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new Plugin.InstantiationException("Unable to instantiate plugin " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localClassNotFoundException);
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new Plugin.InstantiationException("Unable to instantiate plugin " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
    }
    throw new Plugin.InstantiationException("Unable to instantiate plugin " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", localIllegalAccessException);
  }

  private void a(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      return;
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      paramRunnable.run();
      return;
    }
    this.f.post(paramRunnable);
  }

  private void a(String paramString)
  {
  }

  private void a(boolean paramBoolean)
  {
    if ((paramBoolean) || (!this.g))
    {
      this.g = true;
      a(new e(this));
    }
  }

  private void f()
  {
    this.h = false;
    onCreate();
    a("onCreate()");
  }

  private boolean g()
  {
    return DebugUtil.a();
  }

  private void h()
  {
    this.h = false;
    onStart();
    a("onStart()");
  }

  private void i()
  {
    this.h = false;
    onStop();
    a("onStop()");
  }

  private void j()
  {
    this.h = false;
    onEnterBackground();
    a("onEnterBackground()");
  }

  public final PluginInfo a()
  {
    return this.c;
  }

  public final void a(Context paramContext, Intent paramIntent)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("context cannot be null.");
    PluginInfo localPluginInfo = this.c;
    if ((localPluginInfo != null) && (!TextUtils.isEmpty(localPluginInfo.i)))
    {
      Intent localIntent = this.e.a(paramContext, this, paramIntent);
      if (localIntent != null)
        paramContext.startActivity(localIntent);
    }
    a(true);
  }

  final void a(Context paramContext, PluginManager paramPluginManager, PluginHelper paramPluginHelper, PluginInfo paramPluginInfo)
  {
    this.c = paramPluginInfo;
    this.d = paramPluginManager;
    this.e = paramPluginHelper;
    this.b = new PluginContextWrapper(paramContext.getApplicationContext(), paramPluginInfo, this);
  }

  final void b()
  {
    a(false);
  }

  final void c()
  {
    this.g = false;
    a(new f(this));
  }

  final void d()
  {
    a(new g(this));
  }

  void e()
  {
    a(new h(this));
  }

  @PluginApi(a=4)
  public final Context getContext()
  {
    return this.b;
  }

  @PluginApi(a=4)
  public final PluginHelper getPluginHelper()
  {
    return this.e;
  }

  @PluginApi(a=4)
  public final PluginManager getPluginManager()
  {
    return this.d;
  }

  @PluginApi(a=4)
  public final Resources getResources()
  {
    return this.b.getResources();
  }

  @PluginApi(a=6)
  protected final PluginInfo myPluginInfo()
  {
    return this.c;
  }

  @PluginApi(a=7)
  public void onBusinessLifeCycle(int paramInt, Object paramObject)
  {
  }

  @PluginApi(a=4)
  public void onCreate()
  {
    this.h = true;
  }

  @PluginApi(a=4)
  public void onEnterBackground()
  {
    this.h = true;
  }

  @PluginApi(a=4)
  public void onStart()
  {
    this.h = true;
  }

  @PluginApi(a=4)
  public void onStop()
  {
    this.h = true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.Plugin
 * JD-Core Version:    0.6.0
 */