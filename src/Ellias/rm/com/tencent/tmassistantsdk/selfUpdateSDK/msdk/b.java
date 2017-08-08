package com.tencent.tmassistantsdk.selfUpdateSDK.msdk;

public final class b
  implements Cloneable
{
  private long a;

  public b(long paramLong)
  {
    this.a = paramLong;
  }

  public byte[] a()
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(int)(0xFF & this.a);
    arrayOfByte[1] = (byte)(int)((0xFF00 & this.a) >> 8);
    arrayOfByte[2] = (byte)(int)((0xFF0000 & this.a) >> 16);
    arrayOfByte[3] = (byte)(int)((0xFF000000 & this.a) >> 24);
    return arrayOfByte;
  }

  public long b()
  {
    return this.a;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof b)));
    do
      return false;
    while (this.a != ((b)paramObject).b());
    return true;
  }

  public int hashCode()
  {
    return (int)this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.msdk.b
 * JD-Core Version:    0.6.0
 */