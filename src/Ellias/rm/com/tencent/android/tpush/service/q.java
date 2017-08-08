package com.tencent.android.tpush.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

public class q
{
  private static q a = new q();
  private static AlarmManager b = null;

  public static q a()
  {
    if (b == null)
      b();
    return a;
  }

  private static void b()
  {
    monitorenter;
    try
    {
      if ((b == null) && (i.e() != null))
        b = (AlarmManager)i.e().getSystemService("alarm");
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

  public void a(int paramInt, long paramLong, PendingIntent paramPendingIntent)
  {
    if (b != null)
      b.set(paramInt, paramLong, paramPendingIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.q
 * JD-Core Version:    0.6.0
 */