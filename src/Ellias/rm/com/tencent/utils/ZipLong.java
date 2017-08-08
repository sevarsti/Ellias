package com.tencent.utils;

public final class ZipLong
  implements Cloneable
{
  private long a;

  public ZipLong(long paramLong)
  {
    this.a = paramLong;
  }

  public ZipLong(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0);
  }

  public ZipLong(byte[] paramArrayOfByte, int paramInt)
  {
    this.a = (0xFF000000 & paramArrayOfByte[(paramInt + 3)] << 24);
    this.a += (0xFF0000 & paramArrayOfByte[(paramInt + 2)] << 16);
    this.a += (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
    this.a += (0xFF & paramArrayOfByte[paramInt]);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof ZipLong)));
    do
      return false;
    while (this.a != ((ZipLong)paramObject).getValue());
    return true;
  }

  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(int)(0xFF & this.a);
    arrayOfByte[1] = (byte)(int)((0xFF00 & this.a) >> 8);
    arrayOfByte[2] = (byte)(int)((0xFF0000 & this.a) >> 16);
    arrayOfByte[3] = (byte)(int)((0xFF000000 & this.a) >> 24);
    return arrayOfByte;
  }

  public long getValue()
  {
    return this.a;
  }

  public int hashCode()
  {
    return (int)this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.utils.ZipLong
 * JD-Core Version:    0.6.0
 */