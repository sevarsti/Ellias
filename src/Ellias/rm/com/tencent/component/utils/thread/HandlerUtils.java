package com.tencent.component.utils.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class HandlerUtils
{
  private static HandlerThread a = null;
  private static Handler b = new Handler(Looper.getMainLooper());

  public static Looper a()
  {
    if (a == null)
      monitorenter;
    try
    {
      if (a == null)
      {
        a = new HandlerThread("TheadUtils.handerThread");
        a.start();
      }
      return a.getLooper();
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void a(Runnable paramRunnable)
  {
    b.post(paramRunnable);
  }

  public static void a(Runnable paramRunnable, long paramLong)
  {
    b.postDelayed(paramRunnable, paramLong);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.HandlerUtils
 * JD-Core Version:    0.6.0
 */