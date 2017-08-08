package com.tencent.qqgamemi.plugin;

import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class f
  implements ThreadPool.Job
{
  f(QMiPluginManager paramQMiPluginManager)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    List localList = QMiPluginManager.d(this.a);
    if (localList != null)
    {
      Iterator localIterator = new ArrayList(localList).iterator();
      while (localIterator.hasNext())
      {
        PluginItem localPluginItem = (PluginItem)localIterator.next();
        if (localPluginItem == null)
          continue;
        if (localPluginItem.getPluginInfo() == null)
        {
          if (!localPluginItem.isAutoUpgrade())
            continue;
          if (!TextUtils.isEmpty(localPluginItem.pkgUrl))
          {
            QMiPluginManager.a(this.a, localPluginItem);
            continue;
          }
          LogUtil.e(QMiPluginManager.m(), "Auto update plugin(" + localPluginItem.id + ",v:" + localPluginItem.version + ",lastV:" + localPluginItem.lastVersion + ") failed as pkgUrl is empty!");
          continue;
        }
        if ((!localPluginItem.needUpdate) || (!localPluginItem.isAutoUpgrade()) || (localPluginItem.getStatus() != 7))
          continue;
        if (!TextUtils.isEmpty(localPluginItem.pkgUrl))
        {
          QMiPluginManager.a(this.a, localPluginItem);
          continue;
        }
        LogUtil.e(QMiPluginManager.m(), "Auto update plugin(" + localPluginItem.id + ",v:" + localPluginItem.version + ",lastV:" + localPluginItem.lastVersion + ") failed as pkgUrl is empty!");
      }
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.f
 * JD-Core Version:    0.6.0
 */