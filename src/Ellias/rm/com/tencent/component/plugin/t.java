package com.tencent.component.plugin;

class t extends PluginManager.Code
{
  t(PluginManager paramPluginManager, PluginManager.GetPluginInfoCallback paramGetPluginInfoCallback, String paramString)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.c);
    if (localIPluginManager != null)
      this.a.a(localIPluginManager.g(PluginManager.g(this.c), this.b));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.t
 * JD-Core Version:    0.6.0
 */