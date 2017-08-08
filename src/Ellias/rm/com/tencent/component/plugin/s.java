package com.tencent.component.plugin;

class s extends PluginManager.Code
{
  s(PluginManager paramPluginManager, PluginManager.LoadPluginInfoCallback paramLoadPluginInfoCallback, String paramString)
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
 * Qualified Name:     com.tencent.component.plugin.s
 * JD-Core Version:    0.6.0
 */