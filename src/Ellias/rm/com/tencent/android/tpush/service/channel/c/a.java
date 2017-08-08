package com.tencent.android.tpush.service.channel.c;

import java.io.InputStream;
import java.io.OutputStream;

public class a
{
  protected byte[] a;
  protected volatile int b = 0;
  protected volatile int c = 0;
  protected volatile int d = 0;
  protected volatile int e = 0;
  protected volatile boolean f = false;
  protected boolean g = true;
  protected InputStream h = new b(this);
  protected boolean i = false;
  protected OutputStream j = new c(this);
  protected boolean k = false;

  public a()
  {
    this(4096, true);
  }

  public a(int paramInt, boolean paramBoolean)
  {
    if (paramInt == -1)
      this.a = new byte[4096];
    for (this.f = true; ; this.f = false)
    {
      this.g = paramBoolean;
      return;
      this.a = new byte[paramInt];
    }
  }

  private void e()
  {
    byte[] arrayOfByte = new byte[2 * this.a.length];
    int m = h();
    int n = g();
    if (this.d <= this.c)
    {
      int i3 = this.c - this.d;
      System.arraycopy(this.a, this.d, arrayOfByte, 0, i3);
    }
    while (true)
    {
      this.a = arrayOfByte;
      this.d = 0;
      this.b = m;
      this.c = (m + n);
      return;
      int i1 = this.a.length - this.d;
      System.arraycopy(this.a, this.d, arrayOfByte, 0, i1);
      int i2 = this.c;
      System.arraycopy(this.a, 0, arrayOfByte, i1, i2);
    }
  }

  private int f()
  {
    if (this.c < this.d)
      return -1 + (this.d - this.c);
    return -1 + this.a.length - (this.c - this.d);
  }

  private int g()
  {
    if (this.b <= this.c)
      return this.c - this.b;
    return this.a.length - (this.b - this.c);
  }

  private int h()
  {
    if (this.d <= this.b)
      return this.b - this.d;
    return this.a.length - (this.d - this.b);
  }

  private void i()
  {
    if (h() >= this.e)
    {
      this.d = this.b;
      this.e = 0;
    }
  }

  public OutputStream a()
  {
    return this.j;
  }

  public InputStream b()
  {
    return this.h;
  }

  public int c()
  {
    monitorenter;
    try
    {
      int m = g();
      return m;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int d()
  {
    monitorenter;
    try
    {
      int m = f();
      return m;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.c.a
 * JD-Core Version:    0.6.0
 */