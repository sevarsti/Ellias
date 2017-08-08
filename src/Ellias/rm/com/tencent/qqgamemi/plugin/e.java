package com.tencent.qqgamemi.plugin;

import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginManager.GetPluginListCallback;
import com.tencent.qqgamemi.QMiServiceLogic;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.GameItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class e
  implements PluginManager.GetPluginListCallback
{
  e(QMiPluginManager paramQMiPluginManager)
  {
  }

  public void a(List paramList)
  {
    int i = 0;
    if (paramList == null)
      return;
    TLog.b(QMiPluginManager.m(), "defaultPluginCallback onGetPluginList:" + paramList);
    ArrayList localArrayList1 = new ArrayList();
    List localList1 = QMiPluginManager.b(this.a, QMiServiceLogic.b.packageName);
    localArrayList1.addAll(localList1);
    if (paramList != null)
    {
      ArrayList localArrayList2 = new ArrayList();
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        PluginInfo localPluginInfo = (PluginInfo)localIterator1.next();
        PluginItem localPluginItem4 = QMiPluginManager.a(this.a, localList1, localPluginInfo.pluginId);
        if (localPluginItem4 == null)
          continue;
        localArrayList2.add(localPluginInfo);
        localPluginItem4.setPluginInfo(localPluginInfo);
      }
      paramList.removeAll(localArrayList2);
      for (int j = 0; j < paramList.size(); j++)
      {
        PluginItem localPluginItem1 = new PluginItem();
        localPluginItem1.setPluginInfo((PluginInfo)paramList.get(j));
        localArrayList1.add(j, localPluginItem1);
      }
    }
    QMiPluginManager.a(this.a).clear();
    QMiPluginManager.b(this.a).clear();
    Iterator localIterator2 = localArrayList1.iterator();
    while (localIterator2.hasNext())
    {
      PluginItem localPluginItem3 = (PluginItem)localIterator2.next();
      if (localPluginItem3.isLocal())
      {
        QMiPluginManager.a(this.a).add(new PluginOrder(localPluginItem3.id, true));
        QMiPluginManager.b(this.a).add(new PluginOrder(localPluginItem3.id, true));
        continue;
      }
      QMiPluginManager.a(this.a).add(new PluginOrder(localPluginItem3.id, false));
    }
    TLog.c(QMiPluginManager.m(), "orderPlugins:" + QMiPluginManager.a(this.a));
    TLog.c(QMiPluginManager.m(), "installOrderPlugins:" + QMiPluginManager.b(this.a));
    List localList2 = QMiPluginManager.c(this.a);
    TLog.c(QMiPluginManager.m(), "orders=" + localList2);
    List localList3;
    int k;
    int m;
    label600: ArrayList localArrayList3;
    label731: Iterator localIterator4;
    if (localList2 == null)
    {
      localList3 = QMiPluginManager.b(this.a);
      TLog.c(QMiPluginManager.m(), "orders:" + localList3);
      if (localList3 != null)
        for (k = 0; i < localList3.size(); k = m)
        {
          if (!((PluginOrder)localList3.get(i)).isEnable)
            break label996;
          PluginItem localPluginItem2 = QMiPluginManager.a(this.a, localArrayList1, ((PluginOrder)localList3.get(i)).pluginName);
          if ((localPluginItem2 == null) || (!localPluginItem2.isLocal()))
            break label996;
          localArrayList1.remove(localPluginItem2);
          m = k + 1;
          localArrayList1.add(k, localPluginItem2);
          localPluginItem2.setStatus(7);
          i++;
        }
    }
    else
    {
      localArrayList3 = new ArrayList();
      Iterator localIterator3 = localList2.iterator();
      while (true)
      {
        if (!localIterator3.hasNext())
          break label731;
        PluginOrder localPluginOrder2 = (PluginOrder)localIterator3.next();
        Iterator localIterator6 = QMiPluginManager.b(this.a).iterator();
        if (!localIterator6.hasNext())
          continue;
        PluginOrder localPluginOrder3 = (PluginOrder)localIterator6.next();
        if ((localPluginOrder3.pluginName == null) || (localPluginOrder2.pluginName == null) || (!localPluginOrder3.pluginName.equals(localPluginOrder2.pluginName)))
          break;
        localArrayList3.add(localPluginOrder2);
      }
      TLog.c(QMiPluginManager.m(), "newOrders:" + localArrayList3);
      if ((localList2.size() != QMiPluginManager.b(this.a).size()) || (localList2.size() != localArrayList3.size()))
      {
        TLog.c(QMiPluginManager.m(), "orders size change");
        localIterator4 = QMiPluginManager.b(this.a).iterator();
      }
    }
    label818: label996: label1007: 
    while (true)
    {
      PluginOrder localPluginOrder1;
      if (localIterator4.hasNext())
      {
        localPluginOrder1 = (PluginOrder)localIterator4.next();
        Iterator localIterator5 = localList2.iterator();
        do
          if (!localIterator5.hasNext())
            break;
        while (!((PluginOrder)localIterator5.next()).pluginName.equals(localPluginOrder1.pluginName));
      }
      for (int n = 1; ; n = 0)
      {
        if (n != 0)
          break label1007;
        TLog.c(QMiPluginManager.m(), "add:" + localPluginOrder1);
        localArrayList3.add(localPluginOrder1);
        break label818;
        localList2.clear();
        localList2.addAll(localArrayList3);
        PluginOrderManager.a().a(localList2);
        localList3 = localList2;
        break;
        QMiPluginManager.d(this.a).clear();
        QMiPluginManager.d(this.a).addAll(localArrayList1);
        QMiPluginManager.e(this.a);
        return;
        m = k;
        break label600;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.e
 * JD-Core Version:    0.6.0
 */