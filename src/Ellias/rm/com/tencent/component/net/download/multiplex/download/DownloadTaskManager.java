package com.tencent.component.net.download.multiplex.download;

import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DownloadTaskManager
{
  private static final String a = "DownloadTaskManager";
  private List b = new LinkedList();
  private HashMap c = new LinkedHashMap();
  private List d = new LinkedList();
  private int e = 2;

  private void c(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null)
      return;
    LogUtil.d("DownloadTaskManager", "Create new download task worker - " + paramDownloadTask);
    paramDownloadTask.aD = 1;
    i locali = new i(this, paramDownloadTask);
    locali.setName("download_task");
    synchronized (this.d)
    {
      this.d.add(locali);
      locali.start();
      return;
    }
  }

  private DownloadTask d()
  {
    while (true)
    {
      int j;
      synchronized (this.b)
      {
        int i = this.b.size();
        j = 0;
        if (j >= i)
          continue;
        DownloadTask localDownloadTask = (DownloadTask)this.c.get(this.b.get(j));
        if ((localDownloadTask.c()) && (localDownloadTask.aD == 0))
        {
          return localDownloadTask;
          return null;
        }
      }
      j++;
    }
  }

  private DownloadTask d(int paramInt)
  {
    synchronized (this.b)
    {
      Integer localInteger = Integer.valueOf(paramInt);
      this.b.remove(localInteger);
      DownloadTask localDownloadTask = (DownloadTask)this.c.remove(localInteger);
      return localDownloadTask;
    }
  }

  private DownloadTask e()
  {
    while (true)
    {
      int j;
      synchronized (this.b)
      {
        int i = this.b.size();
        j = 0;
        if (j >= i)
          continue;
        DownloadTask localDownloadTask = (DownloadTask)this.c.get(this.b.get(j));
        if ((!localDownloadTask.c()) && (localDownloadTask.aD == 0))
        {
          return localDownloadTask;
          return null;
        }
      }
      j++;
    }
  }

  private int f()
  {
    int j;
    for (int i = 0; ; i = j)
    {
      synchronized (this.d)
      {
        Iterator localIterator = this.d.iterator();
        if (localIterator.hasNext())
        {
          DownloadTask localDownloadTask = ((i)localIterator.next()).a();
          if ((localDownloadTask != null) && (localDownloadTask.c()))
          {
            j = i + 1;
            continue;
          }
        }
        else
        {
          return i;
        }
      }
      j = i;
    }
  }

  private int g()
  {
    int j;
    for (int i = 0; ; i = j)
    {
      synchronized (this.d)
      {
        Iterator localIterator = this.d.iterator();
        if (localIterator.hasNext())
        {
          DownloadTask localDownloadTask = ((i)localIterator.next()).a();
          if ((localDownloadTask != null) && (!localDownloadTask.c()))
          {
            j = i + 1;
            continue;
          }
        }
        else
        {
          return i;
        }
      }
      j = i;
    }
  }

  public DownloadTask a(int paramInt)
  {
    DownloadTask localDownloadTask = d(paramInt);
    if (localDownloadTask != null);
    while (true)
    {
      synchronized (this.d)
      {
        Iterator localIterator = this.d.iterator();
        if (!localIterator.hasNext())
          break label98;
        locali = (i)localIterator.next();
        if (!locali.a(localDownloadTask))
          continue;
        if (locali != null)
        {
          bool = locali.b(localDownloadTask);
          if (bool)
            continue;
          localDownloadTask.R();
          return localDownloadTask;
        }
      }
      boolean bool = false;
      continue;
      label98: i locali = null;
    }
  }

  public void a()
  {
    if (g() < this.e)
    {
      DownloadTask localDownloadTask2 = e();
      if (localDownloadTask2 != null)
        c(localDownloadTask2);
    }
    if (f() < this.e)
    {
      DownloadTask localDownloadTask1 = d();
      if (localDownloadTask1 != null)
        c(localDownloadTask1);
    }
  }

  public void a(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null)
      return;
    int i;
    synchronized (this.b)
    {
      i = paramDownloadTask.E();
      if (this.b.contains(Integer.valueOf(i)))
        return;
    }
    int j = this.b.size();
    for (int k = 0; ; k++)
    {
      if ((k < j) && (i >= ((Integer)this.b.get(k)).intValue()))
        continue;
      this.b.add(k, Integer.valueOf(i));
      this.c.put(Integer.valueOf(i), paramDownloadTask);
      monitorexit;
      paramDownloadTask.P();
      a();
      return;
    }
  }

  public void b(int paramInt)
  {
    this.e = paramInt;
  }

  public void b(DownloadTask paramDownloadTask)
  {
    if (!paramDownloadTask.av())
    {
      LogUtil.d("DownloadTaskManager", "Worker - Task not cancelled.");
      while (true)
      {
        synchronized (this.d)
        {
          Iterator localIterator = this.d.iterator();
          if (localIterator.hasNext())
          {
            locali = (i)localIterator.next();
            if (locali.a() != paramDownloadTask)
              continue;
            this.d.remove(locali);
            d(paramDownloadTask.E());
            return;
          }
        }
        i locali = null;
      }
    }
  }

  public boolean b()
  {
    while (true)
    {
      synchronized (this.b)
      {
        if (this.c.size() > 0)
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
  }

  public int c()
  {
    synchronized (this.b)
    {
      int i = this.c.size();
      return i;
    }
  }

  public DownloadTask c(int paramInt)
  {
    synchronized (this.b)
    {
      DownloadTask localDownloadTask = (DownloadTask)this.c.get(Integer.valueOf(paramInt));
      return localDownloadTask;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadTaskManager
 * JD-Core Version:    0.6.0
 */