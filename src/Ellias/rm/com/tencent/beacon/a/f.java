package com.tencent.beacon.a;

import android.content.Context;
import com.tencent.beacon.a.a.d;
import com.tencent.beacon.d.b;
import com.tencent.beacon.upload.UploadHandleListener;
import com.tencent.beacon.upload.h;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class f
  implements UploadHandleListener
{
  private static f d = null;
  private d a;
  private d b;
  private Context c = null;

  private f(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    h.a(this.c).a(this);
    c();
    if (d() > 0)
      e();
  }

  private d a()
  {
    monitorenter;
    try
    {
      d locald = this.a;
      monitorexit;
      return locald;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static f a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (d == null)
        d = new f(paramContext);
      f localf = d;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(long paramLong1, long paramLong2, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      long l3;
      try
      {
        long l1 = new Date().getTime();
        l2 = paramLong1 + paramLong2;
        if (!paramBoolean)
          continue;
        l3 = l2;
        break label277;
        if (this.b != null)
          continue;
        this.b = new d(1, l1, 1L, l3, l2, paramLong1, paramLong2);
        if (this.a == null)
        {
          this.a = new d(0, l1, 1L, l3, l2, paramLong1, paramLong2);
          return;
          l3 = 0L;
          break label277;
          long l4 = this.b.a();
          this.b = new d(1, this.b.b, 1L + this.b.c, l3 + this.b.d, l2 + this.b.e, paramLong1 + this.b.f, paramLong2 + this.b.g);
          this.b.a(l4);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      long l5 = this.a.a();
      this.a = new d(0, this.a.b, 1L + this.a.c, l3 + this.a.d, l2 + this.a.e, paramLong1 + this.a.f, paramLong2 + this.a.g);
      this.a.a(l5);
      continue;
      label277: if (!paramBoolean)
        continue;
      long l2 = 0L;
    }
  }

  private void a(d paramd)
  {
    monitorenter;
    try
    {
      this.a = paramd;
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

  private d b()
  {
    monitorenter;
    try
    {
      d();
      d locald = this.b;
      monitorexit;
      return locald;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static d b(Context paramContext)
  {
    return a(paramContext).a();
  }

  private void b(d paramd)
  {
    monitorenter;
    try
    {
      this.b = paramd;
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

  private void c()
  {
    List localList = com.tencent.beacon.applog.a.b(this.c);
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)localIterator.next();
        if (locald.a == 0)
        {
          a(locald);
          continue;
        }
        if (locald.a != 1)
          continue;
        b(locald);
      }
    }
  }

  public static void c(Context paramContext)
  {
    f localf = a(paramContext);
    long l = new Date().getTime();
    d locald = localf.a();
    if ((locald != null) && (locald.a() >= 0L))
      com.tencent.beacon.applog.a.b(localf.c, new d[] { locald });
    localf.a(new d(0, l, 0L, 0L, 0L, 0L, 0L));
  }

  private int d()
  {
    monitorenter;
    while (true)
    {
      int i;
      try
      {
        long l1 = e.j();
        long l2 = new Date().getTime();
        if (this.b == null)
          continue;
        boolean bool = this.b.b < l1;
        i = 0;
        if (!bool)
          continue;
        this.b = new d(1, l2, 0L, 0L, 0L, 0L, 0L);
        i = 1;
        if (this.a == null)
        {
          this.a = new d(0, l2, 0L, 0L, 0L, 0L, 0L);
          j = i + 1;
          return j;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = i;
    }
  }

  public static d d(Context paramContext)
  {
    return a(paramContext).b();
  }

  private void e()
  {
    ArrayList localArrayList = new ArrayList();
    d locald1 = a();
    if (locald1 != null)
      localArrayList.add(locald1);
    d locald2 = b();
    if (locald2 != null)
      localArrayList.add(locald2);
    if (localArrayList.size() > 0)
      com.tencent.beacon.applog.a.a(this.c, (d[])localArrayList.toArray(new d[localArrayList.size()]));
  }

  public final void onUploadEnd(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, String paramString)
  {
    Object[] arrayOfObject1 = new Object[6];
    arrayOfObject1[0] = Integer.valueOf(paramInt1);
    arrayOfObject1[1] = Integer.valueOf(paramInt2);
    arrayOfObject1[2] = Long.valueOf(paramLong1);
    arrayOfObject1[3] = Long.valueOf(paramLong2);
    arrayOfObject1[4] = Boolean.valueOf(paramBoolean);
    arrayOfObject1[5] = paramString;
    com.tencent.beacon.d.a.h(" req:%d  res:%d  send:%d  recv:%d  result: %b  msg:%s", arrayOfObject1);
    c();
    d();
    a(paramLong1, paramLong2, b.a(this.c));
    e();
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = a();
    arrayOfObject2[1] = b();
    com.tencent.beacon.d.a.f(" [total:%s] \n[today:%s]", arrayOfObject2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.f
 * JD-Core Version:    0.6.0
 */