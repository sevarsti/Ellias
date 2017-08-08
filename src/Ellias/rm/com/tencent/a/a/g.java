package com.tencent.a.a;

import com.tencent.a.c.c.a;

public abstract class g
{
  private volatile int a = 63;
  private volatile boolean b = true;
  private b c = b.a;

  public g()
  {
    this(63, true, b.a);
  }

  public g(int paramInt, boolean paramBoolean, b paramb)
  {
    a(paramInt);
    a(paramBoolean);
    a(paramb);
  }

  public void a(int paramInt)
  {
    this.a = paramInt;
  }

  protected abstract void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable);

  public void a(b paramb)
  {
    this.c = paramb;
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public void b(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((d()) && (c.a.a(this.a, paramInt)))
      a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable);
  }

  public boolean d()
  {
    return this.b;
  }

  public b e()
  {
    return this.c;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.a.a.g
 * JD-Core Version:    0.6.0
 */