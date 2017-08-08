package com.tencent.component.plugin;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.tencent.component.utils.log.LogUtil;

class p
  implements ServiceConnection
{
  p(PluginManager paramPluginManager)
  {
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    LogUtil.i("PluginManager", "pluginService connected.");
    synchronized (PluginManager.a(this.a))
    {
      PluginManager.a(this.a, IPluginManager.Stub.a(paramIBinder));
      PluginManager.a(this.a).notifyAll();
      if (!PluginManager.b(this.a))
      {
        PluginManager.a(this.a, true);
        PluginManager.c(this.a);
      }
      return;
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    PluginManager.a(this.a, false);
    LogUtil.i("PluginManager", "pluginService disconnected.");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.p
 * JD-Core Version:    0.6.0
 */