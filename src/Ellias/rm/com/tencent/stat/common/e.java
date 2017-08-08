package com.tencent.stat.common;

public class e
{
  static final byte[] a = "03a976511e2cbe3a7f26808fb7af3c05".getBytes();

  public static byte[] a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, a);
  }

  static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    int[] arrayOfInt1 = new int[256];
    int[] arrayOfInt2 = new int[256];
    int j = paramArrayOfByte2.length;
    if ((j < 1) || (j > 256))
      throw new IllegalArgumentException("key must be between 1 and 256 bytes");
    for (int k = 0; k < 256; k++)
    {
      arrayOfInt1[k] = k;
      arrayOfInt2[k] = paramArrayOfByte2[(k % j)];
    }
    int m = 0;
    int n = 0;
    while (m < 256)
    {
      n = 0xFF & n + arrayOfInt1[m] + arrayOfInt2[m];
      int i4 = arrayOfInt1[m];
      arrayOfInt1[m] = arrayOfInt1[n];
      arrayOfInt1[n] = i4;
      m++;
    }
    byte[] arrayOfByte = new byte[paramArrayOfByte1.length];
    int i1 = 0;
    int i2 = 0;
    while (i < paramArrayOfByte1.length)
    {
      i2 = 0xFF & i2 + 1;
      i1 = 0xFF & i1 + arrayOfInt1[i2];
      int i3 = arrayOfInt1[i2];
      arrayOfInt1[i2] = arrayOfInt1[i1];
      arrayOfInt1[i1] = i3;
      arrayOfByte[i] = (byte)(arrayOfInt1[(0xFF & arrayOfInt1[i2] + arrayOfInt1[i1])] ^ paramArrayOfByte1[i]);
      i++;
    }
    return arrayOfByte;
  }

  public static byte[] b(byte[] paramArrayOfByte)
  {
    return b(paramArrayOfByte, a);
  }

  static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return a(paramArrayOfByte1, paramArrayOfByte2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.e
 * JD-Core Version:    0.6.0
 */