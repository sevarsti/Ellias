package com.tencent.component.net.download.multiplex.http;

public abstract class Requester
{
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 30000;
  protected int e = 30000;
  protected int f = 20000;
  protected String g;
  protected int h;
  protected boolean i = true;
  public boolean j = false;
  public boolean k = false;
  protected boolean l = false;

  public abstract MttResponse a(MttRequest paramMttRequest);

  public void a()
  {
  }

  public void a(int paramInt)
  {
    this.e = paramInt;
  }

  public void a(String paramString)
  {
    this.g = paramString;
  }

  public void a(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }

  public MttResponse b()
  {
    return null;
  }

  public void b(int paramInt)
  {
    this.f = paramInt;
  }

  public void b(boolean paramBoolean)
  {
    this.k = paramBoolean;
  }

  public abstract void c();

  public void c(int paramInt)
  {
    this.h = paramInt;
  }

  public void c(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }

  public abstract void d();

  public String f()
  {
    return this.g;
  }

  public boolean g()
  {
    return this.k;
  }

  public int h()
  {
    return this.h;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.Requester
 * JD-Core Version:    0.6.0
 */