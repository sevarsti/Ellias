package com.tencent.component.plugin;

import android.content.Intent;
import android.net.Uri;
import android.os.IInterface;
import java.util.List;

public abstract interface IPluginManager extends IInterface
{
  public abstract Intent a(String paramString1, String paramString2, Uri paramUri);

  public abstract List a(String paramString);

  public abstract void a(PluginPlatformConfig paramPluginPlatformConfig);

  public abstract void a(String paramString, PluginInfo paramPluginInfo, UninstallPluginListener paramUninstallPluginListener);

  public abstract void a(String paramString, PluginManageHandler paramPluginManageHandler);

  public abstract void a(String paramString1, String paramString2, InstallPluginListener paramInstallPluginListener);

  public abstract void a(String paramString1, String paramString2, boolean paramBoolean);

  public abstract boolean a(String paramString1, String paramString2);

  public abstract boolean a(String paramString1, String paramString2, PluginInfo paramPluginInfo);

  public abstract boolean b(String paramString1, String paramString2);

  public abstract boolean c(String paramString1, String paramString2);

  public abstract boolean d(String paramString1, String paramString2);

  public abstract boolean e(String paramString1, String paramString2);

  public abstract PluginInfo f(String paramString1, String paramString2);

  public abstract PluginInfo g(String paramString1, String paramString2);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.IPluginManager
 * JD-Core Version:    0.6.0
 */