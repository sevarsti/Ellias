package com.tencent.component.plugin;

import android.content.Context;
import android.content.Intent;

class j
  implements PluginManager.GetPluginInfoCallback
{
  j(PluginHelper paramPluginHelper, String paramString1, Context paramContext, String paramString2, Intent paramIntent)
  {
  }

  public void a(PluginInfo paramPluginInfo)
  {
    if (paramPluginInfo == null)
      throw new IllegalStateException("Plugin(pluginId:" + this.a + ") not prepared");
    Intent localIntent = this.e.b(this.b, paramPluginInfo, this.c, this.d);
    if (localIntent == null)
      return;
    this.b.startActivity(localIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.j
 * JD-Core Version:    0.6.0
 */