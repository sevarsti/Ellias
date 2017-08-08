package com.tencent.component.image;

public final class Result
{
  private static final int a = 1;
  private static final int b = 2;
  private static final int c = 3;
  private int d = 2;
  private Throwable e;
  private String f;

  final void a()
  {
    this.d = 1;
  }

  final void a(String paramString)
  {
    this.f = paramString;
  }

  final void a(Throwable paramThrowable)
  {
    c();
    this.e = paramThrowable;
  }

  public boolean b()
  {
    return this.d == 1;
  }

  final void c()
  {
    this.d = 2;
  }

  public boolean d()
  {
    return this.d == 2;
  }

  public Throwable e()
  {
    return this.e;
  }

  final void f()
  {
    this.d = 3;
  }

  public boolean g()
  {
    return this.d == 3;
  }

  public String h()
  {
    return this.f;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.Result
 * JD-Core Version:    0.6.0
 */