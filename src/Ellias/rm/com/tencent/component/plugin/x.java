package com.tencent.component.plugin;

import android.content.Intent;
import com.tencent.component.utils.log.LogUtil;

class x
  implements Runnable
{
  x(PluginManager paramPluginManager, PluginInfo paramPluginInfo, Intent paramIntent)
  {
  }

  public void run()
  {
    Plugin localPlugin = this.c.a(this.a);
    if (localPlugin != null)
    {
      localPlugin.a(localPlugin.getContext(), this.b);
      return;
    }
    LogUtil.w("PluginManager", "fail to start plugin:" + this.a.pluginId);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.x
 * JD-Core Version:    0.6.0
 */