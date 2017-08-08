package com.tencent.a.a;

public class d extends c
{
  public static d d = null;

  public d()
  {
    this.c = new a(a);
  }

  public static final void a(String paramString1, String paramString2)
  {
    f().a(1, paramString1, paramString2, null);
  }

  public static final void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    f().a(16, paramString1, paramString2, paramThrowable);
  }

  public static final void b(String paramString1, String paramString2)
  {
    f().a(2, paramString1, paramString2, null);
  }

  public static final void c(String paramString1, String paramString2)
  {
    f().a(4, paramString1, paramString2, null);
  }

  public static final void d(String paramString1, String paramString2)
  {
    f().a(16, paramString1, paramString2, null);
  }

  public static d f()
  {
    if (d == null)
      monitorenter;
    try
    {
      if (d == null)
        d = new d();
      return d;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b()
  {
    monitorenter;
    try
    {
      if (this.c != null)
      {
        this.c.a();
        this.c.b();
        this.c = null;
        d = null;
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.a.a.d
 * JD-Core Version:    0.6.0
 */