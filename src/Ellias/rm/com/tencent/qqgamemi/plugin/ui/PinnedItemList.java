package com.tencent.qqgamemi.plugin.ui;

import android.content.Context;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.plugin.PluginOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PinnedItemList
{
  private static final String a = "PinnedItemList";
  private static final int b = 7;
  private PinnedItem c = new PinnedItem(0, null);
  private List d = new ArrayList();
  private PinnedItem e = new PinnedItem(2, null);
  private List f = new ArrayList();
  private int g = 0;
  private int h = 1;
  private List i = new ArrayList();
  private List j = new ArrayList();

  public PinnedItemList(Context paramContext)
  {
  }

  private int a(PluginItem paramPluginItem)
  {
    Iterator localIterator1 = this.j.iterator();
    PluginOrder localPluginOrder2;
    do
    {
      if (!localIterator1.hasNext())
        break;
      localPluginOrder2 = (PluginOrder)localIterator1.next();
    }
    while (!localPluginOrder2.pluginName.equals(paramPluginItem.id));
    for (int k = this.j.indexOf(localPluginOrder2); ; k = -1)
    {
      if (k == -1)
        return 0;
      for (int m = k - 1; m >= 0; m--)
      {
        PluginOrder localPluginOrder1 = (PluginOrder)this.j.get(m);
        Iterator localIterator2 = this.f.iterator();
        while (localIterator2.hasNext())
        {
          PinnedItem localPinnedItem = (PinnedItem)localIterator2.next();
          if (localPinnedItem.f.id.equals(localPluginOrder1.pluginName))
            return 1 + this.f.indexOf(localPinnedItem);
        }
      }
      return 0;
    }
  }

  private int b(int paramInt)
  {
    PinnedItem localPinnedItem = (PinnedItem)this.i.get(paramInt);
    if (paramInt > this.h)
      return this.f.indexOf(localPinnedItem);
    if (paramInt < this.h)
      return this.d.indexOf(localPinnedItem);
    return -1;
  }

  private void c(PinnedItem paramPinnedItem)
  {
    int k = a(paramPinnedItem.f);
    TLog.c("PinnedItemList", "addToUninstall:" + k + " ," + paramPinnedItem.f.id);
    this.f.add(k, paramPinnedItem);
  }

  private void d()
  {
    this.i.clear();
    this.g = 0;
    this.i.add(this.c);
    this.i.addAll(this.d);
    this.h = (1 + this.d.size());
    this.i.add(this.e);
    this.i.addAll(this.f);
  }

  public int a()
  {
    return this.i.size();
  }

  public int a(PinnedItem paramPinnedItem)
  {
    return this.i.indexOf(paramPinnedItem);
  }

  public PinnedItem a(int paramInt)
  {
    return (PinnedItem)this.i.get(paramInt);
  }

  public void a(List paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("freshPlugins:");
    int k;
    Iterator localIterator;
    if (paramList != null)
    {
      k = paramList.size();
      TLog.c("PinnedItemList", k);
      this.d.clear();
      this.f.clear();
      if (paramList != null)
        localIterator = paramList.iterator();
    }
    else
    {
      while (true)
      {
        if (!localIterator.hasNext())
          break label200;
        PluginItem localPluginItem = (PluginItem)localIterator.next();
        if (localPluginItem.getStatus() == 7)
        {
          TLog.c("PinnedItemList", "get a install plugin:" + localPluginItem);
          this.d.add(new PinnedItem(1, localPluginItem));
          continue;
          k = -1;
          break;
        }
        TLog.c("PinnedItemList", "get a uninstall plugin:" + localPluginItem);
        this.f.add(new PinnedItem(3, localPluginItem));
      }
    }
    label200: d();
  }

  public boolean a(int paramInt1, int paramInt2)
  {
    TLog.c("PinnedItemList", "swapPlace from:" + paramInt1 + " to:" + paramInt2);
    PinnedItem localPinnedItem1 = (PinnedItem)this.i.get(paramInt1);
    PinnedItem localPinnedItem2 = (PinnedItem)this.i.get(paramInt2);
    if (localPinnedItem1.e == 3)
    {
      if (localPinnedItem1.e != localPinnedItem2.e)
      {
        TLog.c("PinnedItemList", "fromItem.type: PINNED_TYPE_UNINSTALL");
        int m = b(paramInt2);
        TLog.c("PinnedItemList", "installIndex=" + m);
        this.f.remove(localPinnedItem1);
        localPinnedItem1.e = 1;
        if (m != -1)
          this.d.add(m, localPinnedItem1);
        while (true)
        {
          int n = this.d.size();
          PinnedItem localPinnedItem3 = null;
          if (n > 7)
          {
            TLog.c("PinnedItemList", "installPlugins.size()=" + this.d.size());
            localPinnedItem3 = (PinnedItem)this.d.remove(-1 + this.d.size());
            localPinnedItem3.e = 3;
            c(localPinnedItem3);
          }
          d();
          if (localPinnedItem3 != localPinnedItem1)
            break;
          return false;
          this.d.add(localPinnedItem1);
        }
      }
      return false;
    }
    if (localPinnedItem1.e == 1)
    {
      if (localPinnedItem1.e != localPinnedItem2.e)
      {
        TLog.c("PinnedItemList", "fromItem.type: PINNED_TYPE_INSTALL");
        this.d.remove(localPinnedItem1);
        localPinnedItem1.e = 3;
        c(localPinnedItem1);
        d();
      }
    }
    else
      return true;
    int k = b(paramInt2);
    this.d.remove(localPinnedItem1);
    if (k != -1)
      this.d.add(k, localPinnedItem1);
    while (true)
    {
      d();
      break;
      this.d.add(localPinnedItem1);
    }
  }

  public List b()
  {
    return new ArrayList(this.d);
  }

  public void b(PinnedItem paramPinnedItem)
  {
    if (this.d.contains(paramPinnedItem))
    {
      this.d.remove(paramPinnedItem);
      c(paramPinnedItem);
      d();
    }
  }

  public List c()
  {
    return new ArrayList(this.i);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.PinnedItemList
 * JD-Core Version:    0.6.0
 */