package com.tencent.feedback.common;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public final class c
{
  private static c p = null;
  private Context a = null;
  private String b = "";
  private byte c = -1;
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private long j = 0L;
  private String k = "";
  private String l = "";
  private String m = "";
  private String n = "";
  private Map<String, PlugInInfo> o = null;

  private void a(byte paramByte)
  {
    monitorenter;
    try
    {
      this.c = 1;
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

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    monitorenter;
    if (paramContext != null);
    try
    {
      if (p == null)
        p = new c();
      synchronized (p)
      {
        e.e("rqdp{  init cominfo}", new Object[0]);
        p.a = paramContext;
        c localc2 = p;
        d.a(paramContext);
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(d.a());
        localStringBuffer.append(";Android ");
        localStringBuffer.append(d.b());
        localStringBuffer.append(",level ");
        localStringBuffer.append(d.c());
        localc2.e(localStringBuffer.toString());
        p.a(1);
        p.f(a.b(paramContext));
        p.g(a.c(paramContext));
        p.h("com.tencent.feedback");
        p.i("1.8.0");
        p.a(paramString1);
        p.b(paramString2);
        p.c(a.d(paramContext));
        c localc3 = p;
        d.a(paramContext);
        localc3.j(d.b(paramContext));
        String str = a.a(paramContext);
        if (!"".equals(str))
        {
          p.k(str);
          monitorexit;
          return;
        }
        p.k(p.c());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  private void e(String paramString)
  {
    monitorenter;
    try
    {
      this.b = paramString;
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
      this.d = paramString;
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

  private void h(String paramString)
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

  private void i(String paramString)
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

  private void j(String paramString)
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

  private void k(String paramString)
  {
    monitorenter;
    try
    {
      this.n = paramString;
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

  public static c p()
  {
    monitorenter;
    try
    {
      c localc = p;
      monitorexit;
      return localc;
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
    monitorenter;
    try
    {
      String str = this.b;
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
    if (paramString == null)
      paramString = "10000";
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

  public final boolean a(String paramString1, String paramString2, String paramString3)
  {
    int i1 = 1;
    monitorenter;
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null))
      i1 = 0;
    while (true)
    {
      monitorexit;
      return i1;
      try
      {
        if (this.o == null)
          this.o = new HashMap();
        this.o.put(paramString1, new PlugInInfo(paramString1, paramString2, paramString3));
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString2;
        arrayOfObject[2] = paramString3;
        e.a("add %s %s %s", arrayOfObject);
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public final byte b()
  {
    monitorenter;
    try
    {
      int i1 = this.c;
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

  public final void b(String paramString)
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

  public final String c()
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

  public final void c(String paramString)
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

  public final String d()
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

  public final void d(String paramString)
  {
    monitorenter;
    if (paramString != null);
    try
    {
      Map localMap = this.o;
      if (localMap == null);
      while (true)
      {
        return;
        this.o.remove(paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final String e()
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

  public final String f()
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

  public final String g()
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

  public final String h()
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

  public final long i()
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

  public final String j()
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

  public final String k()
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

  public final String l()
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

  public final String m()
  {
    monitorenter;
    try
    {
      String str = this.n;
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

  public final Map<String, PlugInInfo> n()
  {
    monitorenter;
    try
    {
      HashMap localHashMap;
      if (this.o != null)
      {
        int i1 = this.o.size();
        if (i1 > 0);
      }
      else
      {
        localHashMap = null;
      }
      while (true)
      {
        return localHashMap;
        localHashMap = new HashMap(this.o.size());
        localHashMap.putAll(this.o);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final Context o()
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
    localStringBuffer.append("OS:").append(this.b).append(" | ");
    localStringBuffer.append("plat:").append(this.c).append(" | ");
    localStringBuffer.append("APD:").append(this.d).append(" , ");
    localStringBuffer.append(this.e).append(" | ");
    localStringBuffer.append("SPD:").append(this.f).append(" , ");
    localStringBuffer.append(this.g).append(" | ");
    localStringBuffer.append("UD:").append(this.h).append(" | ");
    localStringBuffer.append("GIP:").append(this.i).append(" | ");
    localStringBuffer.append("UUID:").append(this.k).append(" | ");
    localStringBuffer.append("IM:").append(this.m).append(" | ");
    localStringBuffer.append("@").append(super.toString());
    return localStringBuffer.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.c
 * JD-Core Version:    0.6.0
 */