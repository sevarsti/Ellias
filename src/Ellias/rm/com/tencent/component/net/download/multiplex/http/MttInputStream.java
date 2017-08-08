package com.tencent.component.net.download.multiplex.http;

import java.io.InputStream;

public class MttInputStream
{
  private InputStream a;
  private boolean b;
  private int c;

  public MttInputStream(InputStream paramInputStream)
  {
    this.a = paramInputStream;
    this.b = true;
    this.c = 0;
  }

  public MttInputStream(InputStream paramInputStream, boolean paramBoolean)
  {
    this.a = paramInputStream;
    this.b = paramBoolean;
    this.c = 0;
  }

  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = this.a.read(paramArrayOfByte, paramInt1, paramInt2);
    if (this.b)
      this.c = (i + this.c);
    return i;
  }

  public InputStream a()
  {
    return this.a;
  }

  public int b()
  {
    return this.c;
  }

  public void c()
  {
    this.a.close();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.MttInputStream
 * JD-Core Version:    0.6.0
 */