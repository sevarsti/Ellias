package com.tencent.feedback.common;

import android.util.SparseArray;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class b
{
  public static boolean a = true;
  private static b b;

  public static b a(ScheduledExecutorService paramScheduledExecutorService)
  {
    return new a(paramScheduledExecutorService);
  }

  public static void a(b paramb)
  {
    monitorenter;
    try
    {
      e.a("rqdp{  AsyncTaskHandlerAbs setInstance} " + paramb, new Object[0]);
      if ((b != null) && (b != paramb))
        b.a();
      b = paramb;
      e.a("rqdp{  AsyncTaskHandlerAbs setInstance end}", new Object[0]);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static b b()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new a();
      b localb = b;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public abstract boolean a();

  public abstract boolean a(int paramInt, Runnable paramRunnable, long paramLong1, long paramLong2);

  public abstract boolean a(int paramInt, boolean paramBoolean);

  public abstract boolean a(Runnable paramRunnable);

  public abstract boolean a(Runnable paramRunnable, long paramLong);

  static final class a extends b
  {
    private ScheduledExecutorService b = null;
    private SparseArray<ScheduledFuture<?>> c = null;

    public a()
    {
      this(Executors.newScheduledThreadPool(3));
    }

    public a(ScheduledExecutorService paramScheduledExecutorService)
    {
      if ((paramScheduledExecutorService == null) || (paramScheduledExecutorService.isShutdown()))
        throw new IllegalArgumentException("ScheduledExecutorService is not valiable!");
      this.b = paramScheduledExecutorService;
      this.c = new SparseArray();
    }

    private boolean c()
    {
      monitorenter;
      try
      {
        if (this.b != null)
        {
          boolean bool = this.b.isShutdown();
          if (!bool)
          {
            i = 1;
            return i;
          }
        }
        int i = 0;
      }
      finally
      {
        monitorexit;
      }
    }

    public final boolean a()
    {
      int i = 0;
      monitorenter;
      try
      {
        e.a("rqdp{  stopAllScheduleTasks start}", new Object[0]);
        if (!c())
          e.d("rqdp{  ScheduleTaskHandlerImp was closed , should all stopped!}", new Object[0]);
        while (true)
        {
          return i;
          e.b("rqdp{  stop All ScheduleTasks!}", new Object[0]);
          this.b.shutdown();
          this.b = null;
          this.c.clear();
          this.c = null;
          e.a("rqdp{  stopAllScheduleTasks end}", new Object[0]);
          i = 1;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public final boolean a(int paramInt, Runnable paramRunnable, long paramLong1, long paramLong2)
    {
      monitorenter;
      while (true)
      {
        try
        {
          if (c())
            continue;
          e.d("rqdp{  ScheduleTaskHandlerImp was closed , should not post!}", new Object[0]);
          int i = 0;
          return i;
          if (paramRunnable != null)
            continue;
          e.d("rqdp{  task runner should not be null}", new Object[0]);
          i = 0;
          continue;
          if (a)
          {
            if (paramLong2 <= 10000L)
              continue;
            break label164;
            a(19, true);
            Object localObject2;
            ScheduledFuture localScheduledFuture = this.b.scheduleAtFixedRate(paramRunnable, 0L, localObject2, TimeUnit.MILLISECONDS);
            if (localScheduledFuture == null)
              continue;
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(19);
            arrayOfObject[1] = Long.valueOf(localObject2);
            e.b("rqdp{  add a new future! taskId} %d ,rqdp{   periodTime} %d", arrayOfObject);
            this.c.put(19, localScheduledFuture);
            i = 1;
            continue;
            paramLong2 = 10000L;
          }
        }
        finally
        {
          monitorexit;
        }
        long l = paramLong2;
        continue;
        label164: l = paramLong2;
      }
    }

    public final boolean a(int paramInt, boolean paramBoolean)
    {
      int i = 0;
      monitorenter;
      try
      {
        if (!c())
          e.d("rqdp{  ScheduleTaskHandlerImp was closed , should all stopped}!", new Object[0]);
        while (true)
        {
          return i;
          ScheduledFuture localScheduledFuture = (ScheduledFuture)this.c.get(paramInt);
          if ((localScheduledFuture != null) && (!localScheduledFuture.isCancelled()))
          {
            e.b("cancel a old future!", new Object[0]);
            localScheduledFuture.cancel(true);
          }
          this.c.remove(paramInt);
          i = 1;
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public final boolean a(Runnable paramRunnable)
    {
      int i = 0;
      monitorenter;
      while (true)
      {
        try
        {
          if (c())
            continue;
          e.d("rqdp{  ScheduleTaskHandlerImp was closed , should not post!}", new Object[0]);
          return i;
          if (paramRunnable == null)
          {
            e.d("rqdp{  task runner should not be null}", new Object[0]);
            i = 0;
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        this.b.execute(paramRunnable);
        i = 1;
      }
    }

    public final boolean a(Runnable paramRunnable, long paramLong)
    {
      int i = 0;
      monitorenter;
      try
      {
        if (!c())
          e.d("rqdp{  ScheduleTaskHandlerImp was closed , should not post!}", new Object[0]);
        while (true)
        {
          return i;
          if (paramRunnable != null)
            break;
          e.d("rqdp{  task runner should not be null}", new Object[0]);
          i = 0;
        }
      }
      finally
      {
        monitorexit;
      }
      if (paramLong > 0L);
      while (true)
      {
        this.b.schedule(paramRunnable, paramLong, TimeUnit.MILLISECONDS);
        i = 1;
        break;
        paramLong = 0L;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.b
 * JD-Core Version:    0.6.0
 */