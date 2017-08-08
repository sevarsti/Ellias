package com.tencent.android.tpush.logging.a;

public class h extends i
{
  private static h c;

  public h()
  {
    if (this.a == null)
      d();
    this.b = new a(this.a);
    if (com.tencent.android.tpush.service.a.a.r == 0);
    for (int i = 63; ; i = com.tencent.android.tpush.service.a.a.r)
    {
      this.b.a(i);
      return;
    }
  }

  public static h a()
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new h();
      h localh = c;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static final void a(String paramString1, String paramString2)
  {
    a().a(1, paramString1, paramString2, null);
  }

  public static final void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(1, paramString1, paramString2, paramThrowable);
  }

  public static final void b(String paramString1, String paramString2)
  {
    a().a(2, paramString1, paramString2, null);
  }

  public static final void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(2, paramString1, paramString2, paramThrowable);
  }

  public static final void c(String paramString1, String paramString2)
  {
    a().a(4, paramString1, paramString2, null);
  }

  public static final void c(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(4, paramString1, paramString2, paramThrowable);
  }

  public static final void d(String paramString1, String paramString2)
  {
    a().a(8, paramString1, paramString2, null);
  }

  public static final void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(8, paramString1, paramString2, paramThrowable);
  }

  public static final void e(String paramString1, String paramString2)
  {
    a().a(16, paramString1, paramString2, null);
  }

  public static final void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(16, paramString1, paramString2, paramThrowable);
  }

  public void b()
  {
    monitorenter;
    try
    {
      if (this.b != null)
      {
        this.b.a();
        this.b.b();
        this.b = null;
        c = null;
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
 * Qualified Name:     com.tencent.android.tpush.logging.a.h
 * JD-Core Version:    0.6.0
 */