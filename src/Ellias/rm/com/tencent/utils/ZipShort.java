package com.tencent.utils;

public final class ZipShort
  implements Cloneable
{
  private int a;

  public ZipShort(int paramInt)
  {
    this.a = paramInt;
  }

  public ZipShort(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0);
  }

  public ZipShort(byte[] paramArrayOfByte, int paramInt)
  {
    this.a = (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
    this.a += (0xFF & paramArrayOfByte[paramInt]);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof ZipShort)));
    do
      return false;
    while (this.a != ((ZipShort)paramObject).getValue());
    return true;
  }

  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = (byte)(0xFF & this.a);
    arrayOfByte[1] = (byte)((0xFF00 & this.a) >> 8);
    return arrayOfByte;
  }

  public int getValue()
  {
    return this.a;
  }

  public int hashCode()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.utils.ZipShort
 * JD-Core Version:    0.6.0
 */