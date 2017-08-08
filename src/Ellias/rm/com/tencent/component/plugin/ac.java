package com.tencent.component.plugin;

import android.os.RemoteException;
import com.tencent.component.utils.log.LogUtil;
import java.util.List;

class ac extends PluginManager.Code
{
  ac(PluginManager paramPluginManager)
  {
    super(paramPluginManager);
  }

  public void a()
  {
    IPluginManager localIPluginManager = PluginManager.d(this.a);
    if (localIPluginManager != null)
      try
      {
        List localList2 = localIPluginManager.a(PluginManager.g(this.a));
        localList1 = localList2;
        PluginManager.a(this.a, localList1);
        PluginManager.b(this.a, localList1);
        PluginManager.c(this.a, localList1);
        PluginManager.a(this.a, true);
        PluginManager.h(this.a);
        LogUtil.i("PluginManager", "say hello finished (platformId:" + PluginManager.g(this.a) + ").");
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
        {
          LogUtil.e("PluginManager", localRemoteException.getMessage(), localRemoteException);
          List localList1 = null;
        }
      }
    PluginManager.a(this.a, false);
    LogUtil.i("PluginManager", "say hello failed as pluginManager binder is null (platformId:" + PluginManager.g(this.a) + ").");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ac
 * JD-Core Version:    0.6.0
 */