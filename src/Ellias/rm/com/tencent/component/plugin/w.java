package com.tencent.component.plugin;

import android.content.Intent;

class w
  implements PluginManager.LoadPluginInfoCallback
{
  w(PluginManager paramPluginManager, Intent paramIntent)
  {
  }

  public void a(PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo != null)
      PluginManager.a(this.b, paramPluginInfo, this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.w
 * JD-Core Version:    0.6.0
 */