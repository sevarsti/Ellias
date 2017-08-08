package com.tencent.feedback.common.b;

import android.content.Context;
import com.tencent.feedback.common.b;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import java.util.Calendar;
import java.util.Date;

public final class h
  implements Runnable
{
  private static h a;
  private Context b;
  private b c;
  private com.tencent.feedback.upload.f d;
  private boolean e = false;
  private long f = 60000L;
  private int g = 10;
  private boolean h = true;
  private final String i;
  private int j = 0;
  private long k = 0L;

  private h(Context paramContext, b paramb, com.tencent.feedback.upload.f paramf)
  {
    this.b = paramContext;
    this.c = paramb;
    this.d = paramf;
    this.i = com.tencent.feedback.common.a.h(this.b);
    this.k = d();
  }

  public static h a()
  {
    monitorenter;
    try
    {
      h localh = a;
      monitorexit;
      return localh;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static h a(Context paramContext, b paramb, com.tencent.feedback.upload.f paramf)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new h(paramContext.getApplicationContext(), paramb, paramf);
      h localh = a;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private boolean a(int paramInt)
  {
    int m = 1;
    monitorenter;
    try
    {
      c localc = c.p();
      if (localc != null)
      {
        com.tencent.feedback.common.a.f localf = new com.tencent.feedback.common.a.f();
        localf.a(paramInt);
        localf.a(new Date().getTime());
        localf.a(this.i);
        localf.b(localc.g());
        int n = com.tencent.feedback.anr.a.a(this.b, new com.tencent.feedback.common.a.f[] { localf });
        if (n <= 0);
      }
      while (true)
      {
        return m;
        m = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private int b(String paramString)
  {
    monitorenter;
    try
    {
      int m = com.tencent.feedback.anr.a.b(this.b, paramString);
      monitorexit;
      return m;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void b(int paramInt)
  {
    monitorenter;
    try
    {
      this.j = paramInt;
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

  private boolean h()
  {
    int m = 0;
    monitorenter;
    try
    {
      if (!a(2))
        e.c("rqdp{ resume record fail}", new Object[0]);
      while (true)
      {
        return m;
        e.e("rqdp{ resume state record true}", new Object[0]);
        if (b(this.i) >= this.g)
        {
          e.e("rqdp{ state max upload}", new Object[0]);
          b localb = f();
          if (localb != null)
            localb.a(new Runnable(2)
            {
              public final void run()
              {
                com.tencent.feedback.upload.f localf = h.this.g();
                if (localf != null)
                  localf.a(new j(h.a(h.this), h.a(), this.a));
              }
            });
        }
        m = 1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private boolean i()
  {
    monitorenter;
    try
    {
      boolean bool = a(1);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(bool);
      e.e("rqdp{ launch state record %b}", arrayOfObject);
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

  private int j()
  {
    monitorenter;
    try
    {
      int m = this.j;
      monitorexit;
      return m;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final int a(com.tencent.feedback.common.a.f[] paramArrayOff)
  {
    monitorenter;
    try
    {
      int m = com.tencent.feedback.anr.a.b(this.b, paramArrayOff);
      monitorexit;
      return m;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final boolean a(long paramLong)
  {
    monitorenter;
    try
    {
      b localb = this.c;
      int m = 0;
      long l1;
      if (localb != null)
      {
        if (60000L <= 30000L)
          break label167;
        l1 = 60000L;
        if (this.e)
          break label175;
        this.f = l1;
        this.e = true;
        this.c.a(19, this, 0L, this.f);
        long l2 = e();
        long l3 = new Date().getTime();
        if (l2 <= l3)
        {
          b(d());
          l2 = e();
        }
        if (l2 > l3)
        {
          this.c.a(new Runnable()
          {
            public final void run()
            {
              long l1 = h.this.e();
              long l2 = new Date().getTime();
              long l3 = l1 - l2;
              b localb1 = h.this.f();
              if (localb1 != null)
              {
                if (l3 <= 0L)
                  break label59;
                localb1.a(this, l3 + 1000L);
              }
              label59: h localh;
              b localb2;
              do
              {
                return;
                e.e("rqdp{  next day to upload}", new Object[0]);
                h.this.c();
                long l4 = h.this.d();
                h.this.b(l4);
                localb1.a(this, l4 - l2);
                Object[] arrayOfObject = new Object[1];
                arrayOfObject[0] = Long.valueOf(l4 - l2);
                e.b("rqdp{ next day %d}", arrayOfObject);
                localh = h.this;
                localb2 = localh.f();
              }
              while (localb2 == null);
              localb2.a(new h.2(localh, 2));
            }
          }
          , l2 - l3);
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Long.valueOf(l2 - l3);
          e.b("rqdp{ next day %d}", arrayOfObject);
        }
      }
      while (true)
      {
        m = 1;
        return m;
        label167: l1 = 30000L;
        break;
        label175: if (l1 == this.f)
          continue;
        this.f = l1;
        this.c.a(19, true);
        this.c.a(19, this, 0L, this.f);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final com.tencent.feedback.common.a.f[] a(String paramString)
  {
    monitorenter;
    try
    {
      com.tencent.feedback.common.a.f[] arrayOff = com.tencent.feedback.anr.a.a(this.b, paramString);
      monitorexit;
      return arrayOff;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected final void b(long paramLong)
  {
    monitorenter;
    try
    {
      this.k = paramLong;
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

  public final boolean b()
  {
    monitorenter;
    try
    {
      boolean bool = this.e;
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

  public final boolean c()
  {
    monitorenter;
    try
    {
      boolean bool = a(3);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(bool);
      e.e("rqdp{ next day state record %b}", arrayOfObject);
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

  public final long d()
  {
    monitorenter;
    try
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(11, 0);
      localCalendar.set(12, 0);
      localCalendar.set(13, 0);
      localCalendar.add(6, 1);
      long l3 = localCalendar.getTime().getTime();
      l2 = l3;
      return l2;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        long l1 = new Date().getTime();
        long l2 = l1 + 86400000L;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final long e()
  {
    monitorenter;
    try
    {
      long l = this.k;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final b f()
  {
    monitorenter;
    try
    {
      b localb = this.c;
      monitorexit;
      return localb;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final com.tencent.feedback.upload.f g()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.upload.f localf = this.d;
      monitorexit;
      return localf;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void run()
  {
    int m = 1 + j();
    b(m);
    if (m == 1)
    {
      this.h = com.tencent.feedback.common.a.b(this.b, this.i);
      i();
      return;
    }
    boolean bool = com.tencent.feedback.common.a.b(this.b, this.i);
    monitorenter;
    while (true)
    {
      try
      {
        if (bool != this.h)
        {
          this.h = bool;
          if (bool)
          {
            n = 1;
            monitorexit;
            if (n == 0)
              break;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = this.i;
            e.b("process:%s is resumed!", arrayOfObject);
            h();
            return;
          }
        }
      }
      finally
      {
        monitorexit;
      }
      int n = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.b.h
 * JD-Core Version:    0.6.0
 */