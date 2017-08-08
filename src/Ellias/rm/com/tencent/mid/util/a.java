package com.tencent.mid.util;

public class a
{
  static
  {
    if (!a.class.desiredAssertionStatus());
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
    c localc = new c(paramInt3, new byte[paramInt2 * 3 / 4]);
    if (!localc.a(paramArrayOfByte, paramInt1, paramInt2, true))
      throw new IllegalArgumentException("bad base-64");
    if (localc.b == localc.a.length)
      return localc.a;
    byte[] arrayOfByte = new byte[localc.b];
    System.arraycopy(localc.a, 0, arrayOfByte, 0, localc.b);
    return arrayOfByte;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt)
  {
    return b(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    d locald = new d(paramInt3, null);
    int i = 4 * (paramInt2 / 3);
    int j;
    if (locald.d)
    {
      if (paramInt2 % 3 > 0)
        i += 4;
      if ((locald.e) && (paramInt2 > 0))
      {
        j = 1 + (paramInt2 - 1) / 57;
        if (!locald.f)
          break label167;
      }
    }
    label167: for (int k = 2; ; k = 1)
    {
      i += k * j;
      locald.a = new byte[i];
      locald.a(paramArrayOfByte, paramInt1, paramInt2, true);
      if ((a) || (locald.b == i))
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
    label173: return locald.a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.util.a
 * JD-Core Version:    0.6.0
 */