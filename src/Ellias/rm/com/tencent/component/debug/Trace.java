package com.tencent.component.debug;

public class Trace
  implements TraceLevel
{
  private static volatile Tracer o = new LogcatTracer();

  public static Tracer a()
  {
    return o;
  }

  public static void a(Tracer paramTracer)
  {
    o = paramTracer;
  }

  public static void a(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, null);
  }

  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (o != null)
      o.b(1, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }

  public static void b(String paramString1, String paramString2)
  {
    b(paramString1, paramString2, null);
  }

  public static void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (o != null)
      o.b(2, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }

  public static void c(String paramString1, String paramString2)
  {
    c(paramString1, paramString2, null);
  }

  public static void c(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (o != null)
      o.b(4, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }

  public static void d(String paramString1, String paramString2)
  {
    d(paramString1, paramString2, null);
  }

  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (o != null)
      o.b(8, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }

  public static void e(String paramString1, String paramString2)
  {
    e(paramString1, paramString2, null);
  }

  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (o != null)
      o.b(16, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.Trace
 * JD-Core Version:    0.6.0
 */