package com.tencent.qqgamemi.plugin;

import com.tencent.component.plugin.PluginInfo;
import com.tencent.qqgamemi.business.PluginUndealCountManager;

class i
  implements PluginUndealCountManagerProxy
{
  i(QmiPlugin paramQmiPlugin)
  {
  }

  public void clearUndealCount()
  {
    PluginUndealCountManager.a().b(QmiPlugin.b(this.a).pluginId);
  }

  public void setUndealCount(int paramInt, boolean paramBoolean)
  {
    PluginUndealCountManager localPluginUndealCountManager = PluginUndealCountManager.a();
    String str = QmiPlugin.a(this.a).pluginId;
    if (paramBoolean);
    for (short s = 0; ; s = 1)
    {
      localPluginUndealCountManager.a(str, s, paramInt);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.i
 * JD-Core Version:    0.6.0
 */