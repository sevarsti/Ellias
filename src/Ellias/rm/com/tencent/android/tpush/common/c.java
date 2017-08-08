package com.tencent.android.tpush.common;

import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.android.tpush.logging.TLog;

public class c
{
  private static HandlerThread a = null;
  private static Handler b = null;

  public static c a()
  {
    c();
    return e.a;
  }

  private static void c()
  {
    if ((a == null) || (!a.isAlive()) || (a.isInterrupted()) || (a.getState() == Thread.State.TERMINATED))
    {
      TLog.v("TPush", ">>> Create working thread.");
      a = new HandlerThread("tpush.working.thread");
      a.start();
    }
    if ((a != null) && (a.getLooper() != null) && (b == null))
      b = new Handler(a.getLooper());
  }

  public boolean a(Runnable paramRunnable)
  {
    if (b != null)
      return b.post(paramRunnable);
    return false;
  }

  public boolean a(Runnable paramRunnable, long paramLong)
  {
    if (b != null)
      return b.postDelayed(paramRunnable, paramLong);
    return false;
  }

  public Handler b()
  {
    return b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.c
 * JD-Core Version:    0.6.0
 */