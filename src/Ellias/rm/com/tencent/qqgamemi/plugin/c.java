package com.tencent.qqgamemi.plugin;

import CobraHallQmiProto.TQmiPlugInVerInfo;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginManager.GetPluginListCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class c
  implements PluginManager.GetPluginListCallback
{
  c(QMiPluginManager paramQMiPluginManager, String paramString)
  {
  }

  public void a(List paramList)
  {
    if (paramList != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        PluginInfo localPluginInfo = (PluginInfo)localIterator.next();
        localArrayList.add(new TQmiPlugInVerInfo(localPluginInfo.pluginId, localPluginInfo.version));
      }
      QMiPluginManager.a(this.b, this.a, localArrayList);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.c
 * JD-Core Version:    0.6.0
 */