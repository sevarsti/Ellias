package com.tencent.component.plugin.server;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.tencent.component.plugin.IPluginManager.Stub;
import com.tencent.component.plugin.InstallPluginListener;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginManageHandler;
import com.tencent.component.plugin.PluginPlatformConfig;
import com.tencent.component.plugin.UninstallPluginListener;
import com.tencent.component.utils.thread.ThreadPool;
import java.util.List;

class k extends IPluginManager.Stub
{
  private Context p;
  private ThreadPool q;

  public k(Context paramContext, String paramString, ThreadPool paramThreadPool)
  {
    this.p = paramContext.getApplicationContext();
    this.q = paramThreadPool;
  }

  private g b(String paramString)
  {
    return c.a(this.p, paramString).f();
  }

  public Intent a(String paramString1, String paramString2, Uri paramUri)
  {
    return b(paramString1).a(paramString2, paramUri);
  }

  public List a(String paramString)
  {
    return b(paramString).b();
  }

  public void a(PluginPlatformConfig paramPluginPlatformConfig)
  {
    this.q.submit(new l(this, paramPluginPlatformConfig));
  }

  public void a(String paramString, PluginInfo paramPluginInfo, UninstallPluginListener paramUninstallPluginListener)
  {
    b(paramString).a(paramPluginInfo, paramUninstallPluginListener);
  }

  public void a(String paramString, PluginManageHandler paramPluginManageHandler)
  {
    b(paramString).a(paramPluginManageHandler);
  }

  public void a(String paramString1, String paramString2, InstallPluginListener paramInstallPluginListener)
  {
    b(paramString1).a(paramString2, paramInstallPluginListener);
  }

  public void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    b(paramString1).a(paramString2, paramBoolean);
  }

  public boolean a(String paramString1, String paramString2)
  {
    return b(paramString1).a(paramString2);
  }

  public boolean a(String paramString1, String paramString2, PluginInfo paramPluginInfo)
  {
    return b(paramString1).a(paramString2, paramPluginInfo);
  }

  public boolean b(String paramString1, String paramString2)
  {
    return b(paramString1).b(paramString2);
  }

  public boolean c(String paramString1, String paramString2)
  {
    return b(paramString1).c(paramString2);
  }

  public boolean d(String paramString1, String paramString2)
  {
    return b(paramString1).d(paramString2);
  }

  public boolean e(String paramString1, String paramString2)
  {
    return b(paramString1).e(paramString2);
  }

  public PluginInfo f(String paramString1, String paramString2)
  {
    return b(paramString1).f(paramString2);
  }

  public PluginInfo g(String paramString1, String paramString2)
  {
    return b(paramString1).g(paramString2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.k
 * JD-Core Version:    0.6.0
 */