package com.tencent.component.plugin;

class ag extends PluginManager.Code
{
  ag(PluginManager paramPluginManager, String paramString)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.b);
    if (localIPluginManager != null)
      localIPluginManager.d(PluginManager.g(this.b), this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ag
 * JD-Core Version:    0.6.0
 */