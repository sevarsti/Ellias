package com.tencent.component.plugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import java.util.concurrent.ConcurrentHashMap;

@PluginApi(a=4)
public class PluginHelper
{
  private static final String a = "PluginHelper";
  private static ConcurrentHashMap d = new ConcurrentHashMap();
  private String b;
  private PluginManager c;

  private PluginHelper(String paramString, PluginManager paramPluginManager)
  {
    this.b = paramString;
    this.c = paramPluginManager;
  }

  private Intent a(Context paramContext, PluginInfo paramPluginInfo, String paramString, Intent paramIntent, boolean paramBoolean)
  {
    Intent localIntent = null;
    if (paramPluginInfo == null);
    do
    {
      do
      {
        return localIntent;
        localIntent = null;
      }
      while (paramContext == null);
      if (TextUtils.isEmpty(paramPluginInfo.installPath))
        return this.c.a(paramPluginInfo.pluginId, paramPluginInfo.j);
      localIntent = new Intent();
      if (!TextUtils.isEmpty(paramString))
        localIntent.putExtra("plugin_fragment", paramString);
      if (paramIntent != null)
      {
        localIntent.putExtra("plugin_data", paramIntent.getExtras());
        localIntent.addFlags(paramIntent.getFlags());
        localIntent.setFlags(0xFBFFFFFF & localIntent.getFlags());
        if (paramPluginInfo.k.d == 0)
          localIntent.setFlags(0xDFFFFFFF & localIntent.getFlags());
      }
      if ((paramContext == paramContext.getApplicationContext()) || (!(paramContext instanceof Activity)))
        localIntent.addFlags(268435456);
      localIntent.setClass(paramContext, PluginShellActivity.class);
      localIntent.putExtra("intent_plugin", paramPluginInfo.pluginId);
      localIntent.putExtra("plugin_platform_id", this.b);
    }
    while (!paramBoolean);
    localIntent.putExtra("plugin_inner", paramPluginInfo);
    return localIntent;
  }

  public static PluginHelper a(String paramString, PluginManager paramPluginManager)
  {
    PluginHelper localPluginHelper1 = (PluginHelper)d.get(paramString);
    if (localPluginHelper1 == null)
    {
      monitorenter;
      try
      {
        PluginHelper localPluginHelper2 = (PluginHelper)d.get(paramString);
        if (localPluginHelper2 == null)
          localPluginHelper2 = new PluginHelper(paramString, paramPluginManager);
        d.put(paramString, localPluginHelper2);
        return localPluginHelper2;
      }
      finally
      {
        monitorexit;
      }
    }
    return localPluginHelper1;
  }

  static Class a(Intent paramIntent, ClassLoader paramClassLoader)
  {
    if (paramClassLoader == null)
      return null;
    ComponentName localComponentName;
    if (paramIntent != null)
      localComponentName = paramIntent.getComponent();
    while (localComponentName != null)
    {
      String str = localComponentName.getClassName();
      if (TextUtils.isEmpty(str))
        break;
      try
      {
        Class localClass = Class.forName(str, false, paramClassLoader);
        return localClass;
        localComponentName = null;
      }
      catch (Throwable localThrowable)
      {
      }
    }
    return null;
  }

  public static boolean a(PluginInfo paramPluginInfo)
  {
    return paramPluginInfo != null;
  }

  public static boolean a(String paramString)
  {
    return !b(paramString);
  }

  private static boolean b(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public Intent a(Context paramContext, Plugin paramPlugin, Intent paramIntent)
  {
    return a(paramContext, paramPlugin, null, paramIntent);
  }

  public Intent a(Context paramContext, Plugin paramPlugin, String paramString, Intent paramIntent)
  {
    return a(paramContext, paramPlugin.a(), paramString, paramIntent);
  }

  Intent a(Context paramContext, PluginInfo paramPluginInfo, String paramString, Intent paramIntent)
  {
    return a(paramContext, paramPluginInfo, paramString, paramIntent, false);
  }

  Intent b(Context paramContext, PluginInfo paramPluginInfo, String paramString, Intent paramIntent)
  {
    return a(paramContext, paramPluginInfo, paramString, paramIntent, true);
  }

  @PluginApi(a=4)
  public int currentVersion()
  {
    return this.c.f();
  }

  @PluginApi(a=4)
  public void startActivity(Context paramContext, Intent paramIntent)
  {
    startActivity(paramContext, null, paramIntent);
  }

  @PluginApi(a=4)
  public void startActivity(Context paramContext, Plugin paramPlugin, Intent paramIntent)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("Context can't be null.");
    if (paramPlugin == null)
      throw new IllegalArgumentException("plugin can't be null.");
    PluginInfo localPluginInfo = paramPlugin.a();
    if ((localPluginInfo == null) || (TextUtils.isEmpty(localPluginInfo.pluginId)))
      throw new IllegalArgumentException("pluginInfo or pluginId is empty.");
    String str = paramPlugin.a().pluginId;
    Class localClass = a(paramIntent, paramPlugin.getClass().getClassLoader());
    if ((localClass != null) && (PluginFragment.class.isAssignableFrom(localClass)))
    {
      startActivity(paramContext, str, localClass, paramIntent);
      return;
    }
    if ((paramContext == paramContext.getApplicationContext()) || (!(paramContext instanceof Activity)))
      paramIntent.addFlags(268435456);
    paramContext.startActivity(paramIntent);
  }

  @PluginApi(a=4)
  public void startActivity(Context paramContext, String paramString, Class paramClass, Intent paramIntent)
  {
    if (paramClass == null)
      return;
    startActivity(paramContext, paramString, paramClass.getName(), paramIntent);
  }

  @PluginApi(a=4)
  public void startActivity(Context paramContext, String paramString1, String paramString2, Intent paramIntent)
  {
    if (TextUtils.isEmpty(paramString2))
      return;
    if (TextUtils.isEmpty(paramString1))
      throw new IllegalArgumentException("pluginId can't be empty");
    if (paramContext == null)
      throw new IllegalArgumentException("Context can't be null");
    this.c.a(paramString1, new j(this, paramString1, paramContext, paramString2, paramIntent));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginHelper
 * JD-Core Version:    0.6.0
 */