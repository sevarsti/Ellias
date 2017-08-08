package com.tencent.component.plugin;

class v extends PluginManager.Code
{
  v(PluginManager paramPluginManager, PluginManageHandler paramPluginManageHandler)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.b);
    if (localIPluginManager != null)
      localIPluginManager.a(PluginManager.g(this.b), this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.v
 * JD-Core Version:    0.6.0
 */