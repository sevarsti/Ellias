package com.tencent.android.tpush.logging.a;

import com.tencent.android.tpush.logging.c.f;

public abstract class k
{
  private volatile int a = 63;
  private volatile boolean b = true;
  private j c = j.a;

  public k()
  {
    this(63, true, j.a);
  }

  public k(int paramInt, boolean paramBoolean, j paramj)
  {
    a(paramInt);
    a(paramBoolean);
    a(paramj);
  }

  public void a(int paramInt)
  {
    this.a = paramInt;
  }

  protected abstract void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable);

  public void a(j paramj)
  {
    this.c = paramj;
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public void b(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((d()) && (f.a(this.a, paramInt)))
      a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable);
  }

  public boolean d()
  {
    return this.b;
  }

  public j e()
  {
    return this.c;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.k
 * JD-Core Version:    0.6.0
 */