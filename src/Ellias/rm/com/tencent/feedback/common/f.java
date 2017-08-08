package com.tencent.feedback.common;

import android.content.Context;
import com.tencent.feedback.anr.a;
import com.tencent.feedback.upload.UploadHandleListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class f
  implements UploadHandleListener
{
  private static f d = null;
  private com.tencent.feedback.common.a.e a;
  private com.tencent.feedback.common.a.e b;
  private Context c = null;

  private f(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    com.tencent.feedback.upload.g.a(this.c).a(this);
    c();
    if (d() > 0)
      e();
  }

  private com.tencent.feedback.common.a.e a()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.common.a.e locale = this.a;
      monitorexit;
      return locale;
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
        this.b = new com.tencent.feedback.common.a.e(1, l1, 1L, l3, l2, paramLong1, paramLong2);
        if (this.a == null)
        {
          this.a = new com.tencent.feedback.common.a.e(0, l1, 1L, l3, l2, paramLong1, paramLong2);
          return;
          l3 = 0L;
          break label277;
          long l4 = this.b.a();
          this.b = new com.tencent.feedback.common.a.e(1, this.b.b, 1L + this.b.c, l3 + this.b.d, l2 + this.b.e, paramLong1 + this.b.f, paramLong2 + this.b.g);
          this.b.a(l4);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      long l5 = this.a.a();
      this.a = new com.tencent.feedback.common.a.e(0, this.a.b, 1L + this.a.c, l3 + this.a.d, l2 + this.a.e, paramLong1 + this.a.f, paramLong2 + this.a.g);
      this.a.a(l5);
      continue;
      label277: if (!paramBoolean)
        continue;
      long l2 = 0L;
    }
  }

  private void a(com.tencent.feedback.common.a.e parame)
  {
    monitorenter;
    try
    {
      this.a = parame;
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

  private com.tencent.feedback.common.a.e b()
  {
    monitorenter;
    try
    {
      d();
      com.tencent.feedback.common.a.e locale = this.b;
      monitorexit;
      return locale;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static com.tencent.feedback.common.a.e b(Context paramContext)
  {
    return a(paramContext).a();
  }

  private void b(com.tencent.feedback.common.a.e parame)
  {
    monitorenter;
    try
    {
      this.b = parame;
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
    List localList = a.a(this.c);
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        com.tencent.feedback.common.a.e locale = (com.tencent.feedback.common.a.e)localIterator.next();
        if (locale.a == 0)
        {
          a(locale);
          continue;
        }
        if (locale.a != 1)
          continue;
        b(locale);
      }
    }
  }

  public static void c(Context paramContext)
  {
    f localf = a(paramContext);
    long l = new Date().getTime();
    com.tencent.feedback.common.a.e locale = localf.a();
    if ((locale != null) && (locale.a() >= 0L))
      a.b(localf.c, new com.tencent.feedback.common.a.e[] { locale });
    localf.a(new com.tencent.feedback.common.a.e(0, l, 0L, 0L, 0L, 0L, 0L));
  }

  private int d()
  {
    monitorenter;
    while (true)
    {
      int i;
      try
      {
        long l1 = a.f();
        long l2 = new Date().getTime();
        if (this.b == null)
          continue;
        boolean bool = this.b.b < l1;
        i = 0;
        if (!bool)
          continue;
        this.b = new com.tencent.feedback.common.a.e(1, l2, 0L, 0L, 0L, 0L, 0L);
        i = 1;
        if (this.a == null)
        {
          this.a = new com.tencent.feedback.common.a.e(0, l2, 0L, 0L, 0L, 0L, 0L);
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

  private void e()
  {
    ArrayList localArrayList = new ArrayList();
    com.tencent.feedback.common.a.e locale1 = a();
    if (locale1 != null)
      localArrayList.add(locale1);
    com.tencent.feedback.common.a.e locale2 = b();
    if (locale2 != null)
      localArrayList.add(locale2);
    if (localArrayList.size() > 0)
      a.a(this.c, (com.tencent.feedback.common.a.e[])localArrayList.toArray(new com.tencent.feedback.common.a.e[localArrayList.size()]));
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
    e.h("rqdp{  req:}%d rqdp{  res:}%d rqdp{  send:}%d rqdp{  recv:}%d rqdp{  result:}%b rqdp{  msg:}%s", arrayOfObject1);
    c();
    d();
    a(paramLong1, paramLong2, g.a(this.c));
    e();
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = a();
    arrayOfObject2[1] = b();
    e.f("rqdp{  [total:}%s]rqdp{  \n[today:}%s]", arrayOfObject2);
  }

  public final void onUploadStart(int paramInt)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.f
 * JD-Core Version:    0.6.0
 */