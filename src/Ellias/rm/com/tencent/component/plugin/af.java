package com.tencent.component.plugin;

class af extends PluginManager.Code
{
  af(PluginManager paramPluginManager, String paramString)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.b);
    if (localIPluginManager != null)
      localIPluginManager.c(PluginManager.g(this.b), this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.af
 * JD-Core Version:    0.6.0
 */