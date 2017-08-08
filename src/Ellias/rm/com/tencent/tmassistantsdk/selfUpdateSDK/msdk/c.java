package com.tencent.tmassistantsdk.selfUpdateSDK.msdk;

public final class c
  implements Cloneable
{
  private int a;

  public c(int paramInt)
  {
    this.a = paramInt;
  }

  public c(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0);
  }

  public c(byte[] paramArrayOfByte, int paramInt)
  {
    this.a = (0xFF00 & paramArrayOfByte[(paramInt + 1)] << 8);
    this.a += (0xFF & paramArrayOfByte[paramInt]);
  }

  public byte[] a()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = (byte)(0xFF & this.a);
    arrayOfByte[1] = (byte)((0xFF00 & this.a) >> 8);
    return arrayOfByte;
  }

  public int b()
  {
    return this.a;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof c)));
    do
      return false;
    while (this.a != ((c)paramObject).b());
    return true;
  }

  public int hashCode()
  {
    return this.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.msdk.c
 * JD-Core Version:    0.6.0
 */