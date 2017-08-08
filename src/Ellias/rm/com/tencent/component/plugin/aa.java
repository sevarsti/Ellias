package com.tencent.component.plugin;

import com.tencent.component.utils.log.LogUtil;

class aa extends PluginManager.Code
{
  aa(PluginManager paramPluginManager)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.a);
    if (localIPluginManager != null)
      try
      {
        localIPluginManager.a(PluginManager.e(this.a));
        PluginPlatform.a(PluginManager.f(this.a));
        return;
      }
      catch (Throwable localThrowable)
      {
        PluginManager.a(this.a, false);
        LogUtil.e("PluginManager", "say hello failed by exception (platformId:" + PluginManager.g(this.a) + ")", localThrowable);
        return;
      }
    PluginManager.a(this.a, false);
    LogUtil.i("PluginManager", "say hello failed as pluginManager binder is null (platformId:" + PluginManager.g(this.a) + ").");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.aa
 * JD-Core Version:    0.6.0
 */