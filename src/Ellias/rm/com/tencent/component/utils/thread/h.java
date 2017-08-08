package com.tencent.component.utils.thread;

import android.util.Log;
import com.tencent.component.utils.log.LogUtil;

class h
  implements Future, ThreadPool.JobContext, Runnable
{
  private static final String a = "Worker";
  private ThreadPool.Job c;
  private FutureListener d;
  private Future.CancelListener e;
  private g f;
  private volatile boolean g;
  private boolean h;
  private Object i;
  private int j;

  public h(ThreadPool paramThreadPool, ThreadPool.Job paramJob, FutureListener paramFutureListener)
  {
    this.c = paramJob;
    this.d = paramFutureListener;
  }

  private g a(int paramInt)
  {
    if (paramInt == 1)
      return this.b.b;
    if (paramInt == 2)
      return this.b.c;
    return null;
  }

  // ERROR //
  private boolean a(g paramg)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 54	com/tencent/component/utils/thread/h:g	Z
    //   6: ifeq +12 -> 18
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 56	com/tencent/component/utils/thread/h:f	Lcom/tencent/component/utils/thread/g;
    //   14: aload_0
    //   15: monitorexit
    //   16: iconst_0
    //   17: ireturn
    //   18: aload_0
    //   19: aload_1
    //   20: putfield 56	com/tencent/component/utils/thread/h:f	Lcom/tencent/component/utils/thread/g;
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: monitorenter
    //   27: aload_1
    //   28: getfield 60	com/tencent/component/utils/thread/g:a	I
    //   31: ifle +31 -> 62
    //   34: aload_1
    //   35: iconst_m1
    //   36: aload_1
    //   37: getfield 60	com/tencent/component/utils/thread/g:a	I
    //   40: iadd
    //   41: putfield 60	com/tencent/component/utils/thread/g:a	I
    //   44: aload_1
    //   45: monitorexit
    //   46: aload_0
    //   47: monitorenter
    //   48: aload_0
    //   49: aconst_null
    //   50: putfield 56	com/tencent/component/utils/thread/h:f	Lcom/tencent/component/utils/thread/g;
    //   53: aload_0
    //   54: monitorexit
    //   55: iconst_1
    //   56: ireturn
    //   57: astore_2
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_2
    //   61: athrow
    //   62: aload_1
    //   63: invokevirtual 63	java/lang/Object:wait	()V
    //   66: aload_1
    //   67: monitorexit
    //   68: goto -68 -> 0
    //   71: astore_3
    //   72: aload_1
    //   73: monitorexit
    //   74: aload_3
    //   75: athrow
    //   76: astore 5
    //   78: aload_0
    //   79: monitorexit
    //   80: aload 5
    //   82: athrow
    //   83: astore 4
    //   85: goto -19 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   2	16	57	finally
    //   18	25	57	finally
    //   58	60	57	finally
    //   27	46	71	finally
    //   62	66	71	finally
    //   66	68	71	finally
    //   72	74	71	finally
    //   48	55	76	finally
    //   78	80	76	finally
    //   62	66	83	java/lang/InterruptedException
  }

  private void b(g paramg)
  {
    monitorenter;
    try
    {
      paramg.a = (1 + paramg.a);
      paramg.notifyAll();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(Future.CancelListener paramCancelListener)
  {
    monitorenter;
    try
    {
      this.e = paramCancelListener;
      if ((this.g) && (this.e != null))
        this.e.a();
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

  // ERROR //
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 54	com/tencent/component/utils/thread/h:g	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 54	com/tencent/component/utils/thread/h:g	Z
    //   19: aload_0
    //   20: getfield 56	com/tencent/component/utils/thread/h:f	Lcom/tencent/component/utils/thread/g;
    //   23: ifnull +19 -> 42
    //   26: aload_0
    //   27: getfield 56	com/tencent/component/utils/thread/h:f	Lcom/tencent/component/utils/thread/g;
    //   30: astore_3
    //   31: aload_3
    //   32: monitorenter
    //   33: aload_0
    //   34: getfield 56	com/tencent/component/utils/thread/h:f	Lcom/tencent/component/utils/thread/g;
    //   37: invokevirtual 67	java/lang/Object:notifyAll	()V
    //   40: aload_3
    //   41: monitorexit
    //   42: aload_0
    //   43: getfield 70	com/tencent/component/utils/thread/h:e	Lcom/tencent/component/utils/thread/Future$CancelListener;
    //   46: ifnull -35 -> 11
    //   49: aload_0
    //   50: getfield 70	com/tencent/component/utils/thread/h:e	Lcom/tencent/component/utils/thread/Future$CancelListener;
    //   53: invokeinterface 74 1 0
    //   58: goto -47 -> 11
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    //   66: astore 4
    //   68: aload_3
    //   69: monitorexit
    //   70: aload 4
    //   72: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	61	finally
    //   14	33	61	finally
    //   42	58	61	finally
    //   70	73	61	finally
    //   33	42	66	finally
    //   68	70	66	finally
  }

  public Object get()
  {
    monitorenter;
    try
    {
      while (true)
      {
        boolean bool = this.h;
        if (bool)
          break;
        try
        {
          wait();
        }
        catch (Exception localException)
        {
          Log.w("Worker", "ignore exception", localException);
        }
      }
    }
    finally
    {
      monitorexit;
    }
    Object localObject2 = this.i;
    monitorexit;
    return localObject2;
  }

  public boolean isCancelled()
  {
    return this.g;
  }

  public boolean isDone()
  {
    monitorenter;
    try
    {
      boolean bool = this.h;
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

  public void run()
  {
    if (this.d != null)
      this.d.onFutureBegin(this);
    boolean bool = setMode(1);
    Object localObject1 = null;
    if (bool);
    try
    {
      Object localObject3 = this.c.run(this);
      localObject1 = localObject3;
      monitorenter;
    }
    catch (Throwable localThrowable)
    {
      try
      {
        while (true)
        {
          setMode(0);
          this.i = localObject1;
          this.h = true;
          notifyAll();
          monitorexit;
          if (this.d != null)
            this.d.onFutureDone(this);
          return;
          localThrowable = localThrowable;
          LogUtil.w("Worker", "Exception in running a job", localThrowable);
          localObject1 = null;
        }
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject2;
  }

  public boolean setMode(int paramInt)
  {
    g localg1 = a(this.j);
    if (localg1 != null)
      b(localg1);
    this.j = 0;
    g localg2 = a(paramInt);
    if (localg2 != null)
    {
      if (!a(localg2))
        return false;
      this.j = paramInt;
    }
    return true;
  }

  public void waitDone()
  {
    get();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.h
 * JD-Core Version:    0.6.0
 */