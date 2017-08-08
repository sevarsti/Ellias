package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.a.c;
import com.tencent.beacon.a.e;
import com.tencent.beacon.d.b;
import com.tencent.beacon.upload.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class f
{
  private h a;
  private Context b;
  private int c = 20000;
  private int d = 0;
  private Runnable e = new Runnable()
  {
    public final void run()
    {
      try
      {
        f.this.a();
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  };

  public f(Context paramContext)
  {
    this.b = paramContext;
    HashMap localHashMap = new HashMap(2);
    if (com.tencent.beacon.a.a.g(this.b))
      localHashMap.put("A66", "F");
    while (true)
    {
      localHashMap.put("A68", com.tencent.beacon.a.a.h(this.b));
      this.a = a.a(this.b, "rqd_heartbeat", true, 0L, 0L, localHashMap);
      return;
      localHashMap.put("A66", "B");
    }
  }

  public f(Context paramContext, boolean paramBoolean)
  {
    this.b = paramContext;
    HashMap localHashMap = new HashMap(2);
    if (paramBoolean)
      localHashMap.put("A66", "F");
    while (true)
    {
      localHashMap.put("A68", com.tencent.beacon.a.a.h(this.b));
      this.a = a.a(this.b, "rqd_heartbeat", true, 0L, 0L, localHashMap);
      return;
      localHashMap.put("A66", "B");
    }
  }

  private void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.d = paramInt;
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

  private int b()
  {
    monitorenter;
    try
    {
      int i = this.d;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected final void a()
  {
    if (!b.b(this.b));
    do
    {
      return;
      g localg = m.d().j();
      if (localg != null)
      {
        ArrayList localArrayList = new ArrayList(2);
        localArrayList.add(this.a);
        localg.a(new i.a(this.b, localArrayList));
      }
      a(1 + b());
    }
    while (b() % 10 != 0);
    c.a().a(108, this.e, 600000L, this.c);
    a(0);
  }

  public final void a(boolean paramBoolean)
  {
    String str = com.tencent.beacon.a.a.b(this.b, "HEART_DENGTA", "");
    if (e.k().equals(str))
    {
      com.tencent.beacon.d.a.a("heartbeat has been uploaded today!", new Object[0]);
      return;
    }
    if (paramBoolean);
    int j;
    for (int i = 1000 * (int)(60.0D * Math.random()); ; j = 0)
    {
      c.a().a(108, this.e, i, this.c);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.f
 * JD-Core Version:    0.6.0
 */