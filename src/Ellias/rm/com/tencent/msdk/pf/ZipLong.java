package com.tencent.msdk.pf;

public final class ZipLong
  implements Cloneable
{
  private long value;

  public ZipLong(long paramLong)
  {
    this.value = paramLong;
  }

  public ZipLong(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0);
  }

  public ZipLong(byte[] paramArrayOfByte, int paramInt)
  {
    this.value = (0xFF000000 & paramArrayOfByte[(paramInt + 3)] << 24);
    this.value += (0xFF0000 & paramArrayOfByte[(paramInt + 2)] << 16);
    this.value += (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
    this.value += (0xFF & paramArrayOfByte[paramInt]);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof ZipLong)));
    do
      return false;
    while (this.value != ((ZipLong)paramObject).getValue());
    return true;
  }

  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(int)(0xFF & this.value);
    arrayOfByte[1] = (byte)(int)((0xFF00 & this.value) >> 8);
    arrayOfByte[2] = (byte)(int)((0xFF0000 & this.value) >> 16);
    arrayOfByte[3] = (byte)(int)((0xFF000000 & this.value) >> 24);
    return arrayOfByte;
  }

  public long getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return (int)this.value;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.pf.ZipLong
 * JD-Core Version:    0.6.0
 */