package com.tencent.component.plugin;

import java.util.List;

class u extends PluginManager.Code
{
  u(PluginManager paramPluginManager, PluginManager.GetPluginListCallback paramGetPluginListCallback)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.b);
    if (localIPluginManager != null)
    {
      List localList = localIPluginManager.a(PluginManager.g(this.b));
      this.a.a(localList);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.u
 * JD-Core Version:    0.6.0
 */