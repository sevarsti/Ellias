package com.tencent.component.net.http.download;

import android.content.Context;
import com.tencent.component.net.NetworkManager;
import com.tencent.component.utils.FixedLinkedList;
import com.tencent.component.utils.NetworkUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProxyStatistics
{
  private final Map a = new HashMap();
  private final Map b = new HashMap();

  private void a(List paramList, String paramString)
  {
    boolean bool1 = true;
    if (paramList == null)
      return;
    while (true)
    {
      e locale1;
      int i;
      int j;
      int k;
      synchronized (this.b)
      {
        locale1 = (e)this.b.get(paramString);
        if (locale1 != null)
          break label218;
        e locale2 = new e();
        this.b.put(paramString, locale2);
        locale3 = locale2;
        Iterator localIterator = paramList.iterator();
        i = 0;
        j = 0;
        k = 0;
        if (localIterator.hasNext())
        {
          e locale4 = (e)localIterator.next();
          if (locale4 == null)
            continue;
          k++;
          if (!locale4.a)
            continue;
          j++;
          if (!locale4.b)
            break label211;
          m = i + 1;
          i = m;
        }
      }
      if (k <= 0)
        break;
      boolean bool2;
      if (j / k > 0.5F)
      {
        bool2 = bool1;
        locale3.a = bool2;
        if (i / k <= 0.5F)
          break label206;
      }
      while (true)
      {
        locale3.b = bool1;
        return;
        bool2 = false;
        break;
        label206: bool1 = false;
      }
      label211: int m = i;
      continue;
      label218: e locale3 = locale1;
    }
  }

  public static ProxyStatistics d()
  {
    return d.a;
  }

  public void a()
  {
  }

  public void a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!NetworkUtil.c(paramContext))
      return;
    e locale = new e();
    locale.a = paramBoolean1;
    locale.b = paramBoolean2;
    synchronized (this.a)
    {
      String str = NetworkManager.a();
      FixedLinkedList localFixedLinkedList = (FixedLinkedList)this.a.get(str);
      if (localFixedLinkedList == null)
      {
        localFixedLinkedList = new FixedLinkedList(3, false);
        this.a.put(str, localFixedLinkedList);
      }
      localFixedLinkedList.add(0, locale);
      a(localFixedLinkedList, str);
      return;
    }
  }

  public boolean b()
  {
    String str = NetworkManager.a();
    synchronized (this.b)
    {
      e locale = (e)this.b.get(str);
      if (locale == null)
      {
        locale = new e();
        this.b.put(str, locale);
      }
      return locale.a;
    }
  }

  public boolean c()
  {
    String str = NetworkManager.a();
    synchronized (this.b)
    {
      e locale = (e)this.b.get(str);
      if (locale == null)
      {
        locale = new e();
        this.b.put(str, locale);
      }
      return locale.b;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.download.ProxyStatistics
 * JD-Core Version:    0.6.0
 */