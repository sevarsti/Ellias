package com.tencent.component.utils.thread;

import android.os.Looper;
import com.tencent.component.annotation.PluginApi;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPool
{

  @PluginApi(a=4)
  public static final int MODE_CPU = 1;

  @PluginApi(a=4)
  public static final int MODE_NETWORK = 2;

  @PluginApi(a=4)
  public static final int MODE_NONE = 0;
  public static final ThreadPool.JobContext a = new e(null);
  static final AtomicLong d = new AtomicLong(0L);
  private static final int e = 4;
  private static final int f = 8;
  private static final int g = 10;
  g b = new g(2);
  g c = new g(2);
  private final Executor h;

  @PluginApi(a=4)
  public ThreadPool()
  {
    this("thread-pool", 4, 8);
  }

  @PluginApi(a=4)
  public ThreadPool(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0);
    for (int i = 1; ; i = paramInt1)
    {
      if (paramInt2 <= i);
      for (int j = i; ; j = paramInt2)
      {
        this.h = new ThreadPoolExecutor(i, j, 10L, TimeUnit.SECONDS, new PriorityBlockingQueue(), new PriorityThreadFactory(paramString, 10));
        return;
      }
    }
  }

  public ThreadPool(String paramString, int paramInt1, int paramInt2, BlockingQueue paramBlockingQueue)
  {
    if (paramInt1 <= 0);
    for (int i = 1; ; i = paramInt1)
    {
      if (paramInt2 <= i);
      for (int j = i; ; j = paramInt2)
      {
        this.h = new ThreadPoolExecutor(i, j, 10L, TimeUnit.SECONDS, paramBlockingQueue, new PriorityThreadFactory(paramString, 10));
        return;
      }
    }
  }

  private h a(ThreadPool.Job paramJob, FutureListener paramFutureListener, ThreadPool.Priority paramPriority)
  {
    switch (c.a[paramPriority.ordinal()])
    {
    default:
      return new f(this, paramJob, paramFutureListener, paramPriority.priorityInt, false);
    case 1:
      return new f(this, paramJob, paramFutureListener, paramPriority.priorityInt, false);
    case 2:
      return new f(this, paramJob, paramFutureListener, paramPriority.priorityInt, false);
    case 3:
    }
    return new f(this, paramJob, paramFutureListener, paramPriority.priorityInt, true);
  }

  @PluginApi(a=4)
  public static ThreadPool getInstance()
  {
    return d.a;
  }

  @PluginApi(a=4)
  public static void runOnNonUIThread(Runnable paramRunnable)
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      getInstance().submit(new b(paramRunnable));
      return;
    }
    paramRunnable.run();
  }

  @PluginApi(a=4)
  public Future submit(ThreadPool.Job paramJob)
  {
    return submit(paramJob, null, ThreadPool.Priority.NORMAL);
  }

  @PluginApi(a=4)
  public Future submit(ThreadPool.Job paramJob, FutureListener paramFutureListener)
  {
    return submit(paramJob, paramFutureListener, ThreadPool.Priority.NORMAL);
  }

  @PluginApi(a=4)
  public Future submit(ThreadPool.Job paramJob, FutureListener paramFutureListener, ThreadPool.Priority paramPriority)
  {
    h localh = a(paramJob, paramFutureListener, paramPriority);
    this.h.execute(localh);
    return localh;
  }

  @PluginApi(a=4)
  public Future submit(ThreadPool.Job paramJob, ThreadPool.Priority paramPriority)
  {
    return submit(paramJob, null, paramPriority);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.ThreadPool
 * JD-Core Version:    0.6.0
 */