package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.a.d;
import com.tencent.beacon.a.e;

public final class c
{
  private static c a = null;
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";

  private c(Context paramContext)
  {
    if (paramContext == null)
      com.tencent.beacon.d.a.d(" DetailUserInfo context == null? pls check!", new Object[0]);
    com.tencent.beacon.d.a.b(" start to create DetailUserInfo}", new Object[0]);
    long l1 = System.currentTimeMillis();
    e.a(paramContext);
    this.b = e.b(paramContext);
    com.tencent.beacon.d.a.b(" imei:" + this.b, new Object[0]);
    this.c = e.e(paramContext);
    this.d = e.c(paramContext);
    this.e = e.d(paramContext);
    String str = d.m().k();
    if ((str != null) && (!"".equals(str)))
      this.f = com.tencent.beacon.applog.a.d(str);
    while (true)
    {
      long l2 = System.currentTimeMillis() - l1;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(l2);
      arrayOfObject[1] = toString();
      com.tencent.beacon.d.a.b(" detail create cost: %d  values:\n %s", arrayOfObject);
      return;
      this.f = com.tencent.beacon.applog.a.d(com.tencent.beacon.a.a.b(paramContext));
      d.m().b(this.f);
    }
  }

  public static c a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new c(paramContext);
      c localc = a;
      return localc;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final String a()
  {
    monitorenter;
    try
    {
      String str = this.c;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String b()
  {
    monitorenter;
    try
    {
      String str = this.d;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String c()
  {
    monitorenter;
    try
    {
      String str = this.e;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String d()
  {
    monitorenter;
    try
    {
      String str = this.f;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.c
 * JD-Core Version:    0.6.0
 */