package com.tencent.msdk.pf;

public final class ZipShort
  implements Cloneable
{
  private int value;

  public ZipShort(int paramInt)
  {
    this.value = paramInt;
  }

  public ZipShort(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0);
  }

  public ZipShort(byte[] paramArrayOfByte, int paramInt)
  {
    this.value = (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
    this.value += (0xFF & paramArrayOfByte[paramInt]);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof ZipShort)));
    do
      return false;
    while (this.value != ((ZipShort)paramObject).getValue());
    return true;
  }

  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = (byte)(0xFF & this.value);
    arrayOfByte[1] = (byte)((0xFF00 & this.value) >> 8);
    return arrayOfByte;
  }

  public int getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.pf.ZipShort
 * JD-Core Version:    0.6.0
 */