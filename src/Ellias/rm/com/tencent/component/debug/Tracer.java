package com.tencent.component.debug;

import com.tencent.component.utils.BitUtils;

public abstract class Tracer
{
  private volatile int a = 63;
  private volatile boolean b = true;
  private TraceFormat c = TraceFormat.i;

  public Tracer()
  {
    this(63, true, TraceFormat.i);
  }

  public Tracer(int paramInt, boolean paramBoolean, TraceFormat paramTraceFormat)
  {
    a(paramInt);
    a(paramBoolean);
    a(paramTraceFormat);
  }

  public void a(int paramInt)
  {
    this.a = paramInt;
  }

  public void a(int paramInt, String paramString)
  {
    if ((e()) && (BitUtils.b(this.a, paramInt)))
      a(paramString);
  }

  protected abstract void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable);

  public void a(TraceFormat paramTraceFormat)
  {
    this.c = paramTraceFormat;
  }

  protected abstract void a(String paramString);

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public void b(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((e()) && (BitUtils.b(this.a, paramInt)))
      a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable);
  }

  public int d()
  {
    return this.a;
  }

  public boolean e()
  {
    return this.b;
  }

  public TraceFormat f()
  {
    return this.c;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.Tracer
 * JD-Core Version:    0.6.0
 */