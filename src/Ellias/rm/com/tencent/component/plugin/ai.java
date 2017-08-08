package com.tencent.component.plugin;

class ai extends PluginManager.Code
{
  ai(PluginManager paramPluginManager, String paramString, InstallPluginListener paramInstallPluginListener)
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
 * Qualified Name:     com.tencent.component.plugin.ai
 * JD-Core Version:    0.6.0
 */