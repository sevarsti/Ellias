package com.tencent.component.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.view.LayoutInflater;
import com.tencent.component.utils.log.LogUtil;

public final class PluginContextWrapper extends ContextWrapper
{
  private static final String a = "PluginContextWrapper";
  private final PluginInfo b;
  private final LayoutInflater c;
  private final ClassLoader d;
  private final Plugin e;
  private Resources f;
  private Resources.Theme g;
  private Context h;

  public PluginContextWrapper(Context paramContext, PluginInfo paramPluginInfo, Plugin paramPlugin)
  {
    super(paramContext);
    this.h = paramContext;
    this.b = paramPluginInfo;
    this.e = paramPlugin;
    this.c = new m(this, paramPlugin);
    this.d = paramPlugin.getClass().getClassLoader();
    this.f = paramPlugin.getPluginManager().b(paramPluginInfo);
    if (this.f != null)
    {
      this.g = this.f.newTheme();
      int i = paramPluginInfo.g;
      Resources.Theme localTheme = getBaseContext().getTheme();
      if (localTheme != null)
        this.g.setTo(localTheme);
      if (i != 0)
        this.g.applyStyle(i, true);
      return;
    }
    LogUtil.i("PluginContextWrapper", "fail to init plugin resources for " + paramPluginInfo);
  }

  public static Context a(Context paramContext)
  {
    if ((paramContext instanceof PluginContextWrapper))
      paramContext = ((PluginContextWrapper)paramContext).a();
    do
      return paramContext;
    while (!(paramContext instanceof PluginShellActivity));
    return ((PluginShellActivity)paramContext).e();
  }

  private Intent a(Intent paramIntent)
  {
    ComponentName localComponentName = paramIntent.getComponent();
    if (localComponentName != null)
      paramIntent.setComponent(new ComponentName(getBaseContext(), localComponentName.getClassName()));
    return paramIntent;
  }

  public Context a()
  {
    return this.h;
  }

  public LayoutInflater b()
  {
    return this.c;
  }

  public PluginInfo c()
  {
    return this.b;
  }

  public AssetManager getAssets()
  {
    Resources localResources = getResources();
    if (localResources != null);
    for (AssetManager localAssetManager = localResources.getAssets(); localAssetManager != null; localAssetManager = null)
      return localAssetManager;
    return super.getAssets();
  }

  public ClassLoader getClassLoader()
  {
    return this.d;
  }

  public String getPackageName()
  {
    return this.e.a().pluginId;
  }

  public Resources getResources()
  {
    Resources localResources = this.e.getPluginManager().b(this.b);
    if (localResources != null)
      return localResources;
    return super.getResources();
  }

  public Object getSystemService(String paramString)
  {
    if ("layout_inflater".equals(paramString))
      return b();
    return super.getSystemService(paramString);
  }

  public Resources.Theme getTheme()
  {
    if (this.g != null)
      return this.g;
    return super.getTheme();
  }

  public void setTheme(int paramInt)
  {
    if (this.g != null)
    {
      this.g.applyStyle(paramInt, true);
      return;
    }
    super.setTheme(paramInt);
  }

  public void startActivity(Intent paramIntent)
  {
    super.startActivity(a(paramIntent));
  }

  public ComponentName startService(Intent paramIntent)
  {
    return super.startService(a(paramIntent));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginContextWrapper
 * JD-Core Version:    0.6.0
 */