package com.tencent.tmassistantsdk.downloadservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class h
{
  protected static h g = null;
  protected final Comparator a = new i(this);
  final PriorityQueue b = new PriorityQueue(16, this.a);
  final ArrayList c = new ArrayList();
  final ArrayList d = new ArrayList();
  final Object e = new Object();
  final Object f = new Object();

  private h()
  {
    int i = f.a().c();
    for (int j = 0; j < i; j++)
    {
      j localj = new j(this, j);
      this.d.add(localj);
    }
  }

  public static h a()
  {
    if (g == null)
      g = new h();
    return g;
  }

  int a(g paramg)
  {
    synchronized (this.f)
    {
      this.b.add(paramg);
      synchronized (this.e)
      {
        this.e.notifyAll();
        int i = paramg.a();
        return i;
      }
    }
  }

  public g a(String paramString)
  {
    synchronized (this.f)
    {
      Iterator localIterator1 = this.c.iterator();
      while (localIterator1.hasNext())
      {
        g localg2 = (g)localIterator1.next();
        if (localg2.c().equals(paramString))
          return localg2;
      }
      Iterator localIterator2 = this.b.iterator();
      while (localIterator2.hasNext())
      {
        g localg1 = (g)localIterator2.next();
        if (localg1.c().equals(paramString))
          return localg1;
      }
    }
    monitorexit;
    return null;
  }

  void a(int paramInt)
  {
    synchronized (this.f)
    {
      Iterator localIterator1 = this.c.iterator();
      while (localIterator1.hasNext())
      {
        g localg2 = (g)localIterator1.next();
        if (localg2.a() != paramInt)
          continue;
        localg2.b();
        this.c.remove(localg2);
        return;
      }
      Iterator localIterator2 = this.b.iterator();
      while (localIterator2.hasNext())
      {
        g localg1 = (g)localIterator2.next();
        if (localg1.a() != paramInt)
          continue;
        localg1.b();
        this.b.remove(localg1);
        return;
      }
    }
    monitorexit;
  }

  boolean b()
  {
    while (true)
    {
      synchronized (this.f)
      {
        if (this.b.size() > 0)
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.h
 * JD-Core Version:    0.6.0
 */