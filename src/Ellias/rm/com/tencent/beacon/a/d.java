package com.tencent.beacon.a;

import android.content.Context;

public final class d
{
  private static d n = null;
  private Context a = null;
  private String b = "";
  private String c = "";
  private byte d = -1;
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private long j = 0L;
  private String k = "";
  private String l = "";
  private String m = "";

  private void a(byte paramByte)
  {
    monitorenter;
    try
    {
      this.d = 1;
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

  public static void a(Context paramContext)
  {
    monitorenter;
    if (paramContext != null);
    try
    {
      if (n == null)
        n = new d();
      synchronized (n)
      {
        com.tencent.beacon.d.a.e("init cominfo", new Object[0]);
        n.a = paramContext;
        e.a(paramContext);
        n.b = e.a();
        d locald2 = n;
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Android ");
        localStringBuffer.append(e.b());
        localStringBuffer.append(",level ");
        localStringBuffer.append(e.c());
        locald2.c = localStringBuffer.toString();
        n.a(1);
        n.d(a.e(paramContext));
        n.e(a.f(paramContext));
        n.f("beacon");
        n.g("1.8.10");
        n.a("unknown");
        n.h(e.b(paramContext));
        String str = a.a(paramContext);
        if (!"".equals(str))
        {
          n.c(str);
          monitorexit;
          return;
        }
        n.c(n.n());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  private void d(String paramString)
  {
    monitorenter;
    try
    {
      this.e = paramString;
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

  private void e(String paramString)
  {
    monitorenter;
    try
    {
      this.f = paramString;
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

  private void f(String paramString)
  {
    monitorenter;
    try
    {
      this.g = paramString;
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

  private void g(String paramString)
  {
    monitorenter;
    try
    {
      this.h = paramString;
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

  private void h(String paramString)
  {
    monitorenter;
    try
    {
      this.k = paramString;
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

  public static d m()
  {
    monitorenter;
    try
    {
      d locald = n;
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

  private String n()
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

  public final String a()
  {
    return this.b;
  }

  public final void a(long paramLong)
  {
    monitorenter;
    try
    {
      this.j = paramLong;
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

  public final void a(String paramString)
  {
    monitorenter;
    try
    {
      this.i = paramString;
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

  public final String b()
  {
    return this.c;
  }

  public final void b(String paramString)
  {
    monitorenter;
    try
    {
      this.m = paramString;
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

  public final byte c()
  {
    monitorenter;
    try
    {
      int i1 = this.d;
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void c(String paramString)
  {
    monitorenter;
    try
    {
      this.l = paramString;
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

  public final String e()
  {
    monitorenter;
    try
    {
      String str = this.g;
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

  public final String f()
  {
    monitorenter;
    try
    {
      String str = this.h;
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

  public final String g()
  {
    monitorenter;
    try
    {
      String str = this.i;
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

  public final long h()
  {
    monitorenter;
    try
    {
      long l1 = this.j;
      monitorexit;
      return l1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String i()
  {
    monitorenter;
    try
    {
      String str = this.k;
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

  public final String j()
  {
    monitorenter;
    try
    {
      String str = this.l;
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

  public final String k()
  {
    monitorenter;
    try
    {
      String str = this.m;
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

  public final Context l()
  {
    monitorenter;
    try
    {
      Context localContext = this.a;
      monitorexit;
      return localContext;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("model:").append(this.b).append(" | ");
    localStringBuffer.append("OS:").append(this.c).append(" | ");
    localStringBuffer.append("plat:").append(this.d).append(" | ");
    localStringBuffer.append("APD:").append(this.e).append(" , ");
    localStringBuffer.append(this.f).append(" | ");
    localStringBuffer.append("SPD:").append(this.g).append(" , ");
    localStringBuffer.append(this.h).append(" | ");
    localStringBuffer.append("GIP:").append(this.i).append(" | ");
    localStringBuffer.append("IM:").append(this.k).append(" | ");
    localStringBuffer.append("@").append(super.toString());
    return localStringBuffer.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.d
 * JD-Core Version:    0.6.0
 */