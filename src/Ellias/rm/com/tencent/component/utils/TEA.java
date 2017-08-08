package com.tencent.component.utils;

public class TEA
{
  private static final int b = -1640531527;
  private static final int c = 32;
  private static final int d = -957401312;
  private int[] e = new int[4];

  static
  {
    if (!TEA.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public TEA(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new RuntimeException("Invalid key: Key was null");
    if (paramArrayOfByte.length < 16)
      throw new RuntimeException("Invalid key: Length was less than 16 bytes");
    int j = 0;
    while (i < 4)
    {
      int[] arrayOfInt = this.e;
      int k = j + 1;
      int m = 0xFF & paramArrayOfByte[j];
      int n = k + 1;
      int i1 = m | (0xFF & paramArrayOfByte[k]) << 8;
      int i2 = n + 1;
      int i3 = i1 | (0xFF & paramArrayOfByte[n]) << 16;
      j = i2 + 1;
      arrayOfInt[i] = (i3 | (0xFF & paramArrayOfByte[i2]) << 24);
      i++;
    }
  }

  public TEA(int[] paramArrayOfInt)
  {
    this.e = paramArrayOfInt;
  }

  void a(byte[] paramArrayOfByte, int[] paramArrayOfInt, int paramInt)
  {
    if ((!a) && (paramInt + paramArrayOfByte.length / 4 > paramArrayOfInt.length))
      throw new AssertionError();
    paramArrayOfInt[paramInt] = 0;
    int i = 24;
    int j = 0;
    if (j < paramArrayOfByte.length)
    {
      paramArrayOfInt[paramInt] |= (0xFF & paramArrayOfByte[j]) << i;
      if (i == 0)
      {
        paramInt++;
        if (paramInt >= paramArrayOfInt.length)
          break label96;
        paramArrayOfInt[paramInt] = 0;
        i = 24;
      }
    }
    while (true)
    {
      j++;
      break;
      i -= 8;
      continue;
      return;
      label96: i = 24;
    }
  }

  void a(int[] paramArrayOfInt)
  {
    if ((!a) && (paramArrayOfInt.length % 2 != 1))
      throw new AssertionError();
    for (int i = 1; i < paramArrayOfInt.length; i += 2)
    {
      int j = 32;
      int k = paramArrayOfInt[i];
      int m = paramArrayOfInt[(i + 1)];
      int n = k;
      int i1 = 0;
      while (true)
      {
        int i2 = j - 1;
        if (j <= 0)
          break;
        int i3 = i1 - 1640531527;
        n += (m ^ (m << 4) + this.e[0]) + (i3 ^ m >>> 5) + this.e[1];
        m += (n ^ (n << 4) + this.e[2]) + (i3 ^ n >>> 5) + this.e[3];
        i1 = i3;
        j = i2;
      }
      paramArrayOfInt[i] = n;
      paramArrayOfInt[(i + 1)] = m;
    }
  }

  public byte[] a(String paramString)
  {
    return a(paramString.getBytes());
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length / 8;
    if (paramArrayOfByte.length % 8 == 0);
    for (int j = 0; ; j = 1)
    {
      int[] arrayOfInt = new int[1 + 2 * (j + i)];
      arrayOfInt[0] = paramArrayOfByte.length;
      a(paramArrayOfByte, arrayOfInt, 1);
      a(arrayOfInt);
      return a(arrayOfInt, 0, 4 * arrayOfInt.length);
    }
  }

  byte[] a(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    if ((!a) && (paramInt2 > 4 * (paramArrayOfInt.length - paramInt1)))
      throw new AssertionError();
    byte[] arrayOfByte = new byte[paramInt2];
    int i = 0;
    int j = 0;
    int k = paramInt1;
    while (i < paramInt2)
    {
      arrayOfByte[i] = (byte)(0xFF & paramArrayOfInt[k] >> 24 - j * 8);
      j++;
      if (j == 4)
      {
        k++;
        j = 0;
      }
      i++;
    }
    return arrayOfByte;
  }

  void b(int[] paramArrayOfInt)
  {
    if ((!a) && (paramArrayOfInt.length % 2 != 1))
      throw new AssertionError();
    for (int i = 1; i < paramArrayOfInt.length; i += 2)
    {
      int j = 32;
      int k = paramArrayOfInt[i];
      int m = paramArrayOfInt[(i + 1)];
      int n = k;
      int i1 = m;
      int i2 = -957401312;
      while (true)
      {
        int i3 = j - 1;
        if (j <= 0)
          break;
        i1 -= (n ^ (n << 4) + this.e[2]) + (i2 ^ n >>> 5) + this.e[3];
        n -= (i1 ^ (i1 << 4) + this.e[0]) + (i2 ^ i1 >>> 5) + this.e[1];
        i2 = 1640531527 + i2;
        j = i3;
      }
      paramArrayOfInt[i] = n;
      paramArrayOfInt[(i + 1)] = i1;
    }
  }

  public byte[] b(byte[] paramArrayOfByte)
  {
    if ((!a) && (paramArrayOfByte.length % 4 != 0))
      throw new AssertionError();
    if ((!a) && (paramArrayOfByte.length / 4 % 2 != 1))
      throw new AssertionError();
    int[] arrayOfInt = new int[paramArrayOfByte.length / 4];
    a(paramArrayOfByte, arrayOfInt, 0);
    b(arrayOfInt);
    return a(arrayOfInt, 1, arrayOfInt[0]);
  }

  public String c(byte[] paramArrayOfByte)
  {
    return new String(b(paramArrayOfByte));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.TEA
 * JD-Core Version:    0.6.0
 */