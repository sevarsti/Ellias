package com.tencent.beacon.a.b;

import android.util.SparseArray;
import com.tencent.beacon.d.c;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class e
{
  private static e a = null;
  private String b = "http://strategy.beacon.qq.com/analytics/upload?mType=beacon";
  private int c = 360;
  private SparseArray<a> d = null;
  private Map<String, String> e = null;
  private byte f = 1;
  private byte g = 1;
  private String h = "*^@K#K@!";

  private e()
  {
    this.d.put(1, new a(1));
    this.d.put(2, new a(2));
    this.d.put(3, new a(3));
  }

  public static e a()
  {
    if (a == null)
      monitorenter;
    try
    {
      if (a == null)
        a = new e();
      return a;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void a(byte paramByte)
  {
    monitorenter;
    try
    {
      this.f = paramByte;
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

  public final void a(int paramInt)
  {
    this.c = paramInt;
  }

  public final void a(String paramString)
  {
    this.b = paramString;
  }

  public final void a(Map<String, String> paramMap)
  {
    this.e = paramMap;
  }

  public final a b(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.d != null)
      {
        locala = (a)this.d.get(paramInt);
        return locala;
      }
      a locala = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public final String b()
  {
    return this.b;
  }

  public final void b(byte paramByte)
  {
    monitorenter;
    try
    {
      this.g = paramByte;
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

  public final void b(String paramString)
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

  public final int c()
  {
    return this.c;
  }

  public final boolean c(int paramInt)
  {
    monitorenter;
    try
    {
      SparseArray localSparseArray = this.d;
      int i = 0;
      if (localSparseArray != null)
      {
        boolean bool = a.a((a)this.d.get(2));
        i = bool;
      }
      return i;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final Map<String, String> d()
  {
    return this.e;
  }

  public final SparseArray<a> e()
  {
    monitorenter;
    try
    {
      if (this.d != null)
      {
        new c();
        SparseArray localSparseArray1 = c.a(this.d);
        localSparseArray2 = localSparseArray1;
        return localSparseArray2;
      }
      SparseArray localSparseArray2 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public final byte f()
  {
    monitorenter;
    try
    {
      int i = this.f;
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

  public final byte g()
  {
    monitorenter;
    try
    {
      int i = this.g;
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

  public final String h()
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

  public static final class a
  {
    private final int a;
    private boolean b = false;
    private String c = "";
    private Map<String, String> d = new HashMap(10);
    private Set<String> e = new HashSet(10);
    private com.tencent.beacon.c.e.e f = null;

    public a(int paramInt)
    {
      this.a = paramInt;
    }

    public final void a(com.tencent.beacon.c.e.e parame)
    {
      this.f = parame;
    }

    public final void a(String paramString)
    {
      this.c = paramString;
    }

    public final void a(Map<String, String> paramMap)
    {
      this.d = paramMap;
    }

    public final void a(Set<String> paramSet)
    {
      this.e = paramSet;
    }

    public final void a(boolean paramBoolean)
    {
      this.b = paramBoolean;
    }

    public final boolean a()
    {
      return this.b;
    }

    public final String b()
    {
      return this.c;
    }

    public final Map<String, String> c()
    {
      return this.d;
    }

    public final Set<String> d()
    {
      return this.e;
    }

    public final com.tencent.beacon.c.e.e e()
    {
      return this.f;
    }

    public final int f()
    {
      return this.a;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.b.e
 * JD-Core Version:    0.6.0
 */