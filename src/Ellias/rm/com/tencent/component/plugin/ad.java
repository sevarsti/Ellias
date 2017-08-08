package com.tencent.component.plugin;

import com.tencent.component.utils.log.LogUtil;
import java.util.concurrent.CountDownLatch;

class ad
  implements Runnable
{
  ad(PluginManager paramPluginManager, PluginInfo paramPluginInfo, CountDownLatch paramCountDownLatch)
  {
  }

  public void run()
  {
    try
    {
      LogUtil.i("PluginManager", "start to check plugin:" + this.a.pluginId + " is surviveable");
      Plugin localPlugin = this.c.a(this.a);
      if (localPlugin != null)
      {
        PluginSurviveDetector localPluginSurviveDetector = PluginSurviveDetector.a(localPlugin.getContext(), this.a);
        if (localPluginSurviveDetector != null)
        {
          if (localPluginSurviveDetector.isSurvivable())
            break label203;
          LogUtil.i("PluginManager", "pluginId:" + this.a.pluginId + " not surviveable.");
          this.c.a(this.a.pluginId, false);
          this.a.b = null;
          this.a.k.g = false;
          PluginManager.a(this.c, this.a.pluginId);
        }
      }
      while (true)
      {
        return;
        label203: LogUtil.i("PluginManager", "pluginId:" + this.a.pluginId + " can survive.");
      }
    }
    catch (Exception localException)
    {
      LogUtil.e("PluginManager", localException.getMessage(), localException);
      return;
    }
    finally
    {
      this.b.countDown();
      LogUtil.i("PluginManager", "plugin:" + this.a.pluginId + " check survive countDown.");
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ad
 * JD-Core Version:    0.6.0
 */