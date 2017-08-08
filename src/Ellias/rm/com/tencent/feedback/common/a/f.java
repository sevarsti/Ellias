package com.tencent.feedback.common.a;

import java.io.Serializable;

public class f
  implements Serializable, Comparable<f>
{
  private long a;
  private long b;
  private int c;
  private String d;
  private String e;

  private int a(f paramf)
  {
    monitorenter;
    try
    {
      long l1 = this.b;
      long l2 = paramf.b;
      int i = (int)(l1 - l2);
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

  public final long a()
  {
    monitorenter;
    try
    {
      long l = this.b;
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

  public final void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.c = paramInt;
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

  public final void a(long paramLong)
  {
    monitorenter;
    try
    {
      this.b = paramLong;
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

  public final int b()
  {
    monitorenter;
    try
    {
      int i = this.c;
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

  public final void b(long paramLong)
  {
    monitorenter;
    try
    {
      this.a = paramLong;
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

  public final long d()
  {
    monitorenter;
    try
    {
      long l = this.a;
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

  public final String e()
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.a.f
 * JD-Core Version:    0.6.0
 */