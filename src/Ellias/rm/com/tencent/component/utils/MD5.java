package com.tencent.component.utils;

import com.tencent.component.annotation.PluginApi;
import java.io.UnsupportedEncodingException;

@PluginApi(a=7)
public final class MD5
{
  static final int[][] a = { { 7, 12, 17, 22 }, { 5, 9, 14, 20 }, { 4, 11, 16, 23 }, { 6, 10, 15, 21 } };
  static final byte[] b = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  public String c;
  private long[] d = new long[4];
  private long[] e = new long[2];
  private byte[] f = new byte[64];
  private byte[] g = new byte[16];

  public MD5()
  {
    a();
  }

  @PluginApi(a=7)
  public static String StrMD5(String paramString)
  {
    byte[] arrayOfByte = toMD5(paramString);
    String str = "";
    for (int i = 0; i < 16; i++)
      str = str + b(arrayOfByte[i]);
    return str;
  }

  public static long a(byte paramByte)
  {
    if (paramByte < 0)
      return paramByte & 0xFF;
    return paramByte;
  }

  private long a(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong1 & paramLong2 | paramLong3 & (0xFFFFFFFF ^ paramLong1);
  }

  private long a(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + a(paramLong2, paramLong3, paramLong4)));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  private void a()
  {
    this.e[0] = 0L;
    this.e[1] = 0L;
    this.d[0] = 1732584193L;
    this.d[1] = 4023233417L;
    this.d[2] = 2562383102L;
    this.d[3] = 271733878L;
  }

  private void a(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[64];
    int i = 0x3F & (int)(this.e[0] >>> 3);
    long[] arrayOfLong1 = this.e;
    long l = arrayOfLong1[0] + (paramInt << 3);
    arrayOfLong1[0] = l;
    if (l < paramInt << 3)
    {
      long[] arrayOfLong3 = this.e;
      arrayOfLong3[1] = (1L + arrayOfLong3[1]);
    }
    long[] arrayOfLong2 = this.e;
    arrayOfLong2[1] += (paramInt >>> 29);
    int j = 64 - i;
    int k = 0;
    if (paramInt >= j)
    {
      a(this.f, paramArrayOfByte, i, 0, j);
      b(this.f);
      while (j + 63 < paramInt)
      {
        a(arrayOfByte, paramArrayOfByte, 0, j, 64);
        b(arrayOfByte);
        j += 64;
      }
      i = 0;
      k = j;
    }
    a(this.f, paramArrayOfByte, i, k, paramInt - k);
  }

  private void a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
  {
    for (int i = 0; i < paramInt3; i++)
      paramArrayOfByte1[(paramInt1 + i)] = paramArrayOfByte2[(paramInt2 + i)];
  }

  private void a(byte[] paramArrayOfByte, long[] paramArrayOfLong, int paramInt)
  {
    int i = 0;
    int j = 0;
    while (i < paramInt)
    {
      paramArrayOfByte[i] = (byte)(int)(0xFF & paramArrayOfLong[j]);
      paramArrayOfByte[(i + 1)] = (byte)(int)(0xFF & paramArrayOfLong[j] >>> 8);
      paramArrayOfByte[(i + 2)] = (byte)(int)(0xFF & paramArrayOfLong[j] >>> 16);
      paramArrayOfByte[(i + 3)] = (byte)(int)(0xFF & paramArrayOfLong[j] >>> 24);
      j++;
      i += 4;
    }
  }

  private void a(long[] paramArrayOfLong, byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    int j = 0;
    while (i < paramInt)
    {
      paramArrayOfLong[j] = (a(paramArrayOfByte[i]) | a(paramArrayOfByte[(i + 1)]) << 8 | a(paramArrayOfByte[(i + 2)]) << 16 | a(paramArrayOfByte[(i + 3)]) << 24);
      j++;
      i += 4;
    }
  }

  private long b(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong1 & paramLong3 | paramLong2 & (0xFFFFFFFF ^ paramLong3);
  }

  private long b(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + b(paramLong2, paramLong3, paramLong4)));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  public static String b(byte paramByte)
  {
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    char[] arrayOfChar2 = new char[2];
    arrayOfChar2[0] = arrayOfChar1[(0xF & paramByte >>> 4)];
    arrayOfChar2[1] = arrayOfChar1[(paramByte & 0xF)];
    return new String(arrayOfChar2);
  }

  private void b()
  {
    byte[] arrayOfByte = new byte[8];
    a(arrayOfByte, this.e, 8);
    int i = 0x3F & (int)(this.e[0] >>> 3);
    if (i < 56);
    for (int j = 56 - i; ; j = 120 - i)
    {
      a(b, j);
      a(arrayOfByte, 8);
      a(this.g, this.d, 16);
      return;
    }
  }

  private void b(byte[] paramArrayOfByte)
  {
    long l1 = this.d[0];
    long l2 = this.d[1];
    long l3 = this.d[2];
    long l4 = this.d[3];
    long[] arrayOfLong1 = new long[16];
    a(arrayOfLong1, paramArrayOfByte, 64);
    long l5 = a(l1, l2, l3, l4, arrayOfLong1[0], a[0][0], 3614090360L);
    long l6 = a(l4, l5, l2, l3, arrayOfLong1[1], a[0][1], 3905402710L);
    long l7 = a(l3, l6, l5, l2, arrayOfLong1[2], a[0][2], 606105819L);
    long l8 = a(l2, l7, l6, l5, arrayOfLong1[3], a[0][3], 3250441966L);
    long l9 = a(l5, l8, l7, l6, arrayOfLong1[4], a[0][0], 4118548399L);
    long l10 = a(l6, l9, l8, l7, arrayOfLong1[5], a[0][1], 1200080426L);
    long l11 = a(l7, l10, l9, l8, arrayOfLong1[6], a[0][2], 2821735955L);
    long l12 = a(l8, l11, l10, l9, arrayOfLong1[7], a[0][3], 4249261313L);
    long l13 = a(l9, l12, l11, l10, arrayOfLong1[8], a[0][0], 1770035416L);
    long l14 = a(l10, l13, l12, l11, arrayOfLong1[9], a[0][1], 2336552879L);
    long l15 = a(l11, l14, l13, l12, arrayOfLong1[10], a[0][2], 4294925233L);
    long l16 = a(l12, l15, l14, l13, arrayOfLong1[11], a[0][3], 2304563134L);
    long l17 = a(l13, l16, l15, l14, arrayOfLong1[12], a[0][0], 1804603682L);
    long l18 = a(l14, l17, l16, l15, arrayOfLong1[13], a[0][1], 4254626195L);
    long l19 = a(l15, l18, l17, l16, arrayOfLong1[14], a[0][2], 2792965006L);
    long l20 = a(l16, l19, l18, l17, arrayOfLong1[15], a[0][3], 1236535329L);
    long l21 = b(l17, l20, l19, l18, arrayOfLong1[1], a[1][0], 4129170786L);
    long l22 = b(l18, l21, l20, l19, arrayOfLong1[6], a[1][1], 3225465664L);
    long l23 = b(l19, l22, l21, l20, arrayOfLong1[11], a[1][2], 643717713L);
    long l24 = b(l20, l23, l22, l21, arrayOfLong1[0], a[1][3], 3921069994L);
    long l25 = b(l21, l24, l23, l22, arrayOfLong1[5], a[1][0], 3593408605L);
    long l26 = b(l22, l25, l24, l23, arrayOfLong1[10], a[1][1], 38016083L);
    long l27 = b(l23, l26, l25, l24, arrayOfLong1[15], a[1][2], 3634488961L);
    long l28 = b(l24, l27, l26, l25, arrayOfLong1[4], a[1][3], 3889429448L);
    long l29 = b(l25, l28, l27, l26, arrayOfLong1[9], a[1][0], 568446438L);
    long l30 = b(l26, l29, l28, l27, arrayOfLong1[14], a[1][1], 3275163606L);
    long l31 = b(l27, l30, l29, l28, arrayOfLong1[3], a[1][2], 4107603335L);
    long l32 = b(l28, l31, l30, l29, arrayOfLong1[8], a[1][3], 1163531501L);
    long l33 = b(l29, l32, l31, l30, arrayOfLong1[13], a[1][0], 2850285829L);
    long l34 = b(l30, l33, l32, l31, arrayOfLong1[2], a[1][1], 4243563512L);
    long l35 = b(l31, l34, l33, l32, arrayOfLong1[7], a[1][2], 1735328473L);
    long l36 = b(l32, l35, l34, l33, arrayOfLong1[12], a[1][3], 2368359562L);
    long l37 = c(l33, l36, l35, l34, arrayOfLong1[5], a[2][0], 4294588738L);
    long l38 = c(l34, l37, l36, l35, arrayOfLong1[8], a[2][1], 2272392833L);
    long l39 = c(l35, l38, l37, l36, arrayOfLong1[11], a[2][2], 1839030562L);
    long l40 = c(l36, l39, l38, l37, arrayOfLong1[14], a[2][3], 4259657740L);
    long l41 = c(l37, l40, l39, l38, arrayOfLong1[1], a[2][0], 2763975236L);
    long l42 = c(l38, l41, l40, l39, arrayOfLong1[4], a[2][1], 1272893353L);
    long l43 = c(l39, l42, l41, l40, arrayOfLong1[7], a[2][2], 4139469664L);
    long l44 = c(l40, l43, l42, l41, arrayOfLong1[10], a[2][3], 3200236656L);
    long l45 = c(l41, l44, l43, l42, arrayOfLong1[13], a[2][0], 681279174L);
    long l46 = c(l42, l45, l44, l43, arrayOfLong1[0], a[2][1], 3936430074L);
    long l47 = c(l43, l46, l45, l44, arrayOfLong1[3], a[2][2], 3572445317L);
    long l48 = c(l44, l47, l46, l45, arrayOfLong1[6], a[2][3], 76029189L);
    long l49 = c(l45, l48, l47, l46, arrayOfLong1[9], a[2][0], 3654602809L);
    long l50 = c(l46, l49, l48, l47, arrayOfLong1[12], a[2][1], 3873151461L);
    long l51 = c(l47, l50, l49, l48, arrayOfLong1[15], a[2][2], 530742520L);
    long l52 = c(l48, l51, l50, l49, arrayOfLong1[2], a[2][3], 3299628645L);
    long l53 = d(l49, l52, l51, l50, arrayOfLong1[0], a[3][0], 4096336452L);
    long l54 = d(l50, l53, l52, l51, arrayOfLong1[7], a[3][1], 1126891415L);
    long l55 = d(l51, l54, l53, l52, arrayOfLong1[14], a[3][2], 2878612391L);
    long l56 = d(l52, l55, l54, l53, arrayOfLong1[5], a[3][3], 4237533241L);
    long l57 = d(l53, l56, l55, l54, arrayOfLong1[12], a[3][0], 1700485571L);
    long l58 = d(l54, l57, l56, l55, arrayOfLong1[3], a[3][1], 2399980690L);
    long l59 = d(l55, l58, l57, l56, arrayOfLong1[10], a[3][2], 4293915773L);
    long l60 = d(l56, l59, l58, l57, arrayOfLong1[1], a[3][3], 2240044497L);
    long l61 = d(l57, l60, l59, l58, arrayOfLong1[8], a[3][0], 1873313359L);
    long l62 = d(l58, l61, l60, l59, arrayOfLong1[15], a[3][1], 4264355552L);
    long l63 = d(l59, l62, l61, l60, arrayOfLong1[6], a[3][2], 2734768916L);
    long l64 = d(l60, l63, l62, l61, arrayOfLong1[13], a[3][3], 1309151649L);
    long l65 = d(l61, l64, l63, l62, arrayOfLong1[4], a[3][0], 4149444226L);
    long l66 = d(l62, l65, l64, l63, arrayOfLong1[11], a[3][1], 3174756917L);
    long l67 = d(l63, l66, l65, l64, arrayOfLong1[2], a[3][2], 718787259L);
    long l68 = d(l64, l67, l66, l65, arrayOfLong1[9], a[3][3], 3951481745L);
    long[] arrayOfLong2 = this.d;
    arrayOfLong2[0] = (l65 + arrayOfLong2[0]);
    long[] arrayOfLong3 = this.d;
    arrayOfLong3[1] = (l68 + arrayOfLong3[1]);
    long[] arrayOfLong4 = this.d;
    arrayOfLong4[2] = (l67 + arrayOfLong4[2]);
    long[] arrayOfLong5 = this.d;
    arrayOfLong5[3] = (l66 + arrayOfLong5[3]);
  }

  private long c(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong3 ^ (paramLong1 ^ paramLong2);
  }

  private long c(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + c(paramLong2, paramLong3, paramLong4)));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  private long d(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong2 ^ (paramLong1 | 0xFFFFFFFF ^ paramLong3);
  }

  private long d(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + d(paramLong2, paramLong3, paramLong4)));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  @PluginApi(a=7)
  public static String md5BytesTOHexStr(byte[] paramArrayOfByte)
  {
    Object localObject;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 16))
      localObject = "";
    while (true)
    {
      return localObject;
      localObject = "";
      int i = 0;
      while (i < 16)
      {
        String str = (String)localObject + b(paramArrayOfByte[i]);
        i++;
        localObject = str;
      }
    }
  }

  @PluginApi(a=7)
  public static byte[] toMD5(String paramString)
  {
    return new MD5().a(paramString);
  }

  @PluginApi(a=7)
  public static byte[] toMD5(byte[] paramArrayOfByte)
  {
    return new MD5().a(paramArrayOfByte);
  }

  public byte[] a(String paramString)
  {
    a();
    try
    {
      byte[] arrayOfByte = paramString.getBytes("ISO8859_1");
      a(arrayOfByte, arrayOfByte.length);
      b();
      return this.g;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        a(paramString.getBytes(), paramString.length());
    }
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    a();
    a(paramArrayOfByte, paramArrayOfByte.length);
    b();
    return this.g;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.MD5
 * JD-Core Version:    0.6.0
 */