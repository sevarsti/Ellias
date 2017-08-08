package com.tencent.component.plugin;

class q extends PluginManager.Code
{
  q(PluginManager paramPluginManager, PluginInfo paramPluginInfo, UninstallPluginListener paramUninstallPluginListener)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.c);
    if (localIPluginManager != null)
      localIPluginManager.a(PluginManager.g(this.c), this.a, this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.q
 * JD-Core Version:    0.6.0
 */