package com.pay.broadcast;

import android.content.Context;
import android.content.IntentFilter;
import java.lang.ref.WeakReference;

public class HomeWatcher
{
  private static Object e = new Object();
  private static HomeWatcher f;
  private WeakReference a;
  private IntentFilter b;
  private HomeWatcher.OnHomePressedListener c;
  private b d;
  private boolean g = false;

  private HomeWatcher()
  {
  }

  private HomeWatcher(Context paramContext)
  {
    this.a = new WeakReference(paramContext);
    this.b = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    this.d = new b(this);
    monitorenter;
    try
    {
      if (!this.g)
      {
        Context localContext = (Context)this.a.get();
        if (localContext != null)
          localContext.registerReceiver(this.d, this.b);
        this.g = true;
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void release()
  {
    if (f != null)
    {
      f.unregisterReceiver();
      f = null;
    }
  }

  public static HomeWatcher singleton(Context paramContext)
  {
    if (f == null);
    synchronized (e)
    {
      if (f == null)
        f = new HomeWatcher(paramContext);
      return f;
    }
  }

  public void setOnHomePressedListener(HomeWatcher.OnHomePressedListener paramOnHomePressedListener)
  {
    this.c = paramOnHomePressedListener;
  }

  public void unregisterReceiver()
  {
    monitorenter;
    try
    {
      if ((this.g) && (this.a != null))
      {
        this.g = false;
        Context localContext = (Context)this.a.get();
        if (localContext != null)
          localContext.unregisterReceiver(this.d);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.broadcast.HomeWatcher
 * JD-Core Version:    0.6.0
 */