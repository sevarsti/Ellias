package com.tencent.beacon.a;

import android.util.SparseArray;
import com.tencent.beacon.d.a;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class c
{
  public static boolean a = true;
  private static c b;

  public static c a()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new a();
      c localc = b;
      return localc;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public abstract void a(int paramInt, Runnable paramRunnable, long paramLong1, long paramLong2);

  public abstract void a(int paramInt, boolean paramBoolean);

  public abstract void a(Runnable paramRunnable);

  public abstract void a(Runnable paramRunnable, long paramLong);

  static final class a extends c
  {
    private ScheduledExecutorService b = null;
    private SparseArray<ScheduledFuture<?>> c = null;
    private boolean d = false;

    public final void a(int paramInt, Runnable paramRunnable, long paramLong1, long paramLong2)
    {
      long l1 = 0L;
      monitorenter;
      if (paramRunnable == null);
      while (true)
      {
        try
        {
          a.d("task runner should not be null", new Object[0]);
          return;
          if (paramLong1 <= l1)
            continue;
          l1 = paramLong1;
          if (!a)
            break label137;
          if (paramLong2 > 10000L)
          {
            break label144;
            a(paramInt, true);
            Object localObject2;
            ScheduledFuture localScheduledFuture = this.b.scheduleAtFixedRate(paramRunnable, l1, localObject2, TimeUnit.MILLISECONDS);
            if (localScheduledFuture == null)
              continue;
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(paramInt);
            arrayOfObject[1] = Long.valueOf(localObject2);
            a.b("add a new future! taskId: %d , periodTime: %d", arrayOfObject);
            this.c.put(paramInt, localScheduledFuture);
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        paramLong2 = 10000L;
        break label144;
        label137: long l2 = paramLong2;
        continue;
        label144: l2 = paramLong2;
      }
    }

    public final void a(int paramInt, boolean paramBoolean)
    {
      monitorenter;
      try
      {
        ScheduledFuture localScheduledFuture = (ScheduledFuture)this.c.get(paramInt);
        if ((localScheduledFuture != null) && (!localScheduledFuture.isCancelled()))
        {
          a.b("cancel a old future!", new Object[0]);
          localScheduledFuture.cancel(true);
        }
        this.c.remove(paramInt);
        return;
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public final void a(Runnable paramRunnable)
    {
      monitorenter;
      if (paramRunnable == null);
      try
      {
        a.d("task runner should not be null", new Object[0]);
        while (true)
        {
          return;
          this.b.execute(paramRunnable);
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public final void a(Runnable paramRunnable, long paramLong)
    {
      monitorenter;
      if (paramRunnable == null);
      while (true)
      {
        try
        {
          a.d("task runner should not be null", new Object[0]);
          return;
          if (paramLong > 0L)
          {
            this.b.schedule(paramRunnable, paramLong, TimeUnit.MILLISECONDS);
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        paramLong = 0L;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.c
 * JD-Core Version:    0.6.0
 */