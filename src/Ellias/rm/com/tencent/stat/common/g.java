package com.tencent.stat.common;

public class g
{
  static
  {
    if (!g.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    i locali = new i(paramInt3, new byte[paramInt2 * 3 / 4]);
    if (!locali.a(paramArrayOfByte, paramInt1, paramInt2, true))
      throw new IllegalArgumentException("bad base-64");
    if (locali.b == locali.a.length)
      return locali.a;
    byte[] arrayOfByte = new byte[locali.b];
    System.arraycopy(locali.a, 0, arrayOfByte, 0, locali.b);
    return arrayOfByte;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt)
  {
    return b(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    j localj = new j(paramInt3, null);
    int i = 4 * (paramInt2 / 3);
    int j;
    if (localj.d)
    {
      if (paramInt2 % 3 > 0)
        i += 4;
      if ((localj.e) && (paramInt2 > 0))
      {
        j = 1 + (paramInt2 - 1) / 57;
        if (!localj.f)
          break label167;
      }
    }
    label167: for (int k = 2; ; k = 1)
    {
      i += k * j;
      localj.a = new byte[i];
      localj.a(paramArrayOfByte, paramInt1, paramInt2, true);
      if ((a) || (localj.b == i))
        break label173;
      throw new AssertionError();
      switch (paramInt2 % 3)
      {
      case 0:
      default:
        break;
      case 1:
        i += 2;
        break;
      case 2:
        i += 3;
        break;
      }
    }
    label173: return localj.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.g
 * JD-Core Version:    0.6.0
 */