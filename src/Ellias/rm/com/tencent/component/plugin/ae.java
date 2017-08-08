package com.tencent.component.plugin;

import com.tencent.component.utils.log.LogUtil;

class ae
  implements Runnable
{
  ae(PluginManager paramPluginManager, PluginInfo paramPluginInfo)
  {
  }

  public void run()
  {
    Plugin localPlugin = this.b.a(this.a);
    if (localPlugin != null)
    {
      BootCompleteReceiver localBootCompleteReceiver = BootCompleteReceiver.a(localPlugin.getContext(), this.a);
      if (localBootCompleteReceiver != null)
      {
        LogUtil.i("PluginManager", "notify plugin:" + this.a.pluginId + " boot complete.");
        localBootCompleteReceiver.onBootComplete(localPlugin.getContext());
      }
    }
    else
    {
      return;
    }
    LogUtil.i("PluginManager", "notify plugin:" + this.a.pluginId + " boot complete failed.");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ae
 * JD-Core Version:    0.6.0
 */