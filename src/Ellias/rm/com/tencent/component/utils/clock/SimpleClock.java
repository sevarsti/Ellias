package com.tencent.component.utils.clock;

import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class SimpleClock extends Clock
{
  private static final String a = "base.clock.service";
  private static final int b = 32;
  private static SimpleClock[] c;
  private static HandlerThread d;
  private static Handler e;
  private volatile boolean f;

  protected SimpleClock(int paramInt, long paramLong, OnClockListener paramOnClockListener)
  {
    super(paramInt, paramLong, paramOnClockListener);
  }

  private static void a(int paramInt, long paramLong)
  {
    if (e != null)
    {
      if (paramLong > 0L)
        e.sendEmptyMessageDelayed(paramInt, paramLong);
    }
    else
      return;
    e.sendEmptyMessage(paramInt);
  }

  private static void b(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= c.length));
    SimpleClock localSimpleClock;
    OnClockListener localOnClockListener;
    do
    {
      do
      {
        return;
        localSimpleClock = c[paramInt];
      }
      while (localSimpleClock == null);
      localOnClockListener = localSimpleClock.c();
    }
    while (localOnClockListener == null);
    if (localOnClockListener.onClockArrived(localSimpleClock))
    {
      a(paramInt, localSimpleClock.a());
      return;
    }
    cancel(localSimpleClock);
  }

  @PluginApi(a=6)
  public static void cancel(SimpleClock paramSimpleClock)
  {
    if (paramSimpleClock == null);
    int i;
    do
    {
      return;
      paramSimpleClock.d();
      i = paramSimpleClock.b();
    }
    while ((i < 0) || (i >= c.length));
    monitorenter;
    try
    {
      SimpleClock localSimpleClock = c[i];
      if ((localSimpleClock != null) && (localSimpleClock == paramSimpleClock))
      {
        c[i] = null;
        e.removeMessages(i);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static void e()
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new SimpleClock[32];
      if (d == null)
        d = new HandlerThread("base.clock.service");
      if (!d.isAlive())
        d.start();
      if ((d.isAlive()) && (e == null))
        e = new a(d.getLooper());
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public static SimpleClock set(long paramLong1, long paramLong2, OnClockListener paramOnClockListener)
  {
    monitorenter;
    while (true)
    {
      int j;
      try
      {
        e();
        int i = -1;
        j = 0;
        if (j >= c.length)
          continue;
        if (c[j] == null)
        {
          i = j;
          if (i < 0)
            return null;
          SimpleClock localSimpleClock = new SimpleClock(i, paramLong1, paramOnClockListener);
          c[i] = localSimpleClock;
          a(i, paramLong2);
          return localSimpleClock;
        }
      }
      finally
      {
        monitorexit;
      }
      j++;
    }
  }

  @PluginApi(a=6)
  public void cancel()
  {
    cancel(this);
  }

  public void d()
  {
    monitorenter;
    try
    {
      this.f = true;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public boolean isCanceled()
  {
    monitorenter;
    try
    {
      boolean bool = this.f;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.clock.SimpleClock
 * JD-Core Version:    0.6.0
 */