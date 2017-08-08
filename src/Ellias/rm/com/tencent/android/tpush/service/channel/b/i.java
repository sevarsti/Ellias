package com.tencent.android.tpush.service.channel.b;

public abstract class i extends f
{
  protected short d;
  protected int e;
  protected long f;
  protected long g;
  protected short h;
  protected short i;
  protected short k;
  protected short l;
  protected short m;
  protected byte[] n = new byte[0];

  static
  {
    if (!i.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      o = bool;
      return;
    }
  }

  public void a(short paramShort)
  {
    this.h = paramShort;
  }

  public void a(byte[] paramArrayOfByte)
  {
    this.n = paramArrayOfByte;
  }

  public void b(short paramShort)
  {
    this.k = paramShort;
  }

  public boolean e()
  {
    return (0x80 & this.h) != 0;
  }

  public short f()
  {
    return this.h;
  }

  public int g()
  {
    return this.e;
  }

  public short h()
  {
    return this.k;
  }

  public byte[] i()
  {
    return this.n;
  }

  public short j()
  {
    return this.m;
  }

  public String toString()
  {
    return getClass().getSimpleName() + "(p:" + this.k + "|v:" + this.l + "|r:" + this.g + "|s:" + this.e + "|c:" + Integer.toHexString(this.h) + "|r:" + this.m + "|l:" + this.f + ")";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b.i
 * JD-Core Version:    0.6.0
 */