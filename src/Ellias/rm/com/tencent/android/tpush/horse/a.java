package com.tencent.android.tpush.horse;

import android.annotation.SuppressLint;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class a
{
  private static final Object a = new Object();
  private LinkedBlockingQueue b = new LinkedBlockingQueue();

  @SuppressLint({"UseSparseArrays"})
  private volatile HashMap c = new HashMap();
  private b d;
  private AtomicInteger e = new AtomicInteger(0);
  private volatile boolean f = false;

  public int a()
  {
    return this.e.get();
  }

  public void a(int paramInt)
  {
    TLog.v("XGHorse", "@@ stopOtherHorse(" + paramInt + ")");
    try
    {
      if (!this.c.isEmpty())
      {
        Iterator localIterator = this.c.keySet().iterator();
        while (localIterator.hasNext())
        {
          int i = ((Integer)localIterator.next()).intValue();
          if (i == paramInt)
            continue;
          c localc = (c)this.c.get(Integer.valueOf(i));
          if ((localc == null) || (localc.a() == null))
            continue;
          localc.a().c();
        }
      }
    }
    catch (Exception localException)
    {
      TLog.e("XGHorse", localException.toString());
    }
    return;
    ((c)this.c.remove(Integer.valueOf(paramInt))).interrupt();
  }

  public void a(b paramb)
  {
    this.d = paramb;
  }

  void a(List paramList)
  {
    monitorenter;
    try
    {
      TLog.v("XGHorse", "@@ addStrategyItem()");
      if ((paramList == null) || (1 > paramList.size()))
        if ((this.d != null) && (!c()))
          this.d.a(null);
      while (true)
      {
        return;
        this.b.clear();
        this.f = false;
        b();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          StrategyItem localStrategyItem = (StrategyItem)localIterator.next();
          if (this.b.contains(localStrategyItem))
            continue;
          this.b.add(localStrategyItem);
          this.e.incrementAndGet();
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b()
  {
    TLog.v("XGHorse", "@@ reSetRemain()");
    this.e.set(0);
  }

  public boolean c()
  {
    return this.e.get() > 0;
  }

  public boolean d()
  {
    return this.f;
  }

  public LinkedBlockingQueue e()
  {
    return this.b;
  }

  public abstract void f();

  public abstract void g();

  public void h()
  {
    TLog.v("XGHorse", "@@ startTask()");
    int i = 0;
    if (i < 4)
    {
      if ((this.c.get(Integer.valueOf(i)) == null) || (((c)this.c.get(Integer.valueOf(i))).getState() == Thread.State.TERMINATED))
      {
        c localc = new c(this, i);
        localc.start();
        this.c.put(Integer.valueOf(i), localc);
      }
      while (true)
      {
        i++;
        break;
        if (((c)this.c.get(Integer.valueOf(i))).isAlive())
          continue;
        ((c)this.c.get(Integer.valueOf(i))).start();
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.a
 * JD-Core Version:    0.6.0
 */