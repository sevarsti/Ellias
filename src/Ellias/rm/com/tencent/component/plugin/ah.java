package com.tencent.component.plugin;

import com.tencent.component.utils.FileUtil;
import java.io.File;

class ah extends PluginManager.Code
{
  ah(PluginManager paramPluginManager, String paramString)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    File localFile = new File(this.a);
    if (localFile.isFile())
      FileUtil.a(localFile, new File(PluginManager.a(this.b, PluginManager.f(this.b)), localFile.getName()));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ah
 * JD-Core Version:    0.6.0
 */