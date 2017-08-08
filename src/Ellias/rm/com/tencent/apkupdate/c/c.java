package com.tencent.apkupdate.c;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public final class c
{
  private static byte[] a = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  private final long[] b = new long[4];
  private final long[] c = new long[2];
  private final byte[] d = new byte[64];
  private final byte[] e = new byte[16];

  public c()
  {
    a();
  }

  private long a(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + (paramLong2 & paramLong3 | paramLong4 & (0xFFFFFFFF ^ paramLong2))));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  public static String a(String paramString)
  {
    String str;
    try
    {
      byte[] arrayOfByte4 = paramString.getBytes("ISO8859_1");
      arrayOfByte1 = arrayOfByte4;
      c localc = new c();
      localc.a();
      localc.a(new ByteArrayInputStream(arrayOfByte1), arrayOfByte1.length);
      byte[] arrayOfByte2 = new byte[8];
      a(arrayOfByte2, localc.c, 8);
      i = 0x3F & (int)(localc.c[0] >>> 3);
      if (i < 56)
      {
        j = 56 - i;
        localc.a(a, j);
        localc.a(arrayOfByte2, 8);
        a(localc.e, localc.b, 16);
        byte[] arrayOfByte3 = localc.e;
        str = "";
        for (int k = 0; k < 16; k++)
        {
          StringBuilder localStringBuilder = new StringBuilder().append(str);
          int m = arrayOfByte3[k];
          char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
          char[] arrayOfChar2 = new char[2];
          arrayOfChar2[0] = arrayOfChar1[(0xF & m >>> 4)];
          arrayOfChar2[1] = arrayOfChar1[(m & 0xF)];
          str = new String(arrayOfChar2);
        }
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        int i;
        byte[] arrayOfByte1 = paramString.getBytes();
        continue;
        int j = 120 - i;
      }
    }
    return str;
  }

  private void a()
  {
    this.c[0] = 0L;
    this.c[1] = 0L;
    this.b[0] = 1732584193L;
    this.b[1] = 4023233417L;
    this.b[2] = 2562383102L;
    this.b[3] = 271733878L;
  }

  private void a(byte[] paramArrayOfByte)
  {
    long l1 = this.b[0];
    long l2 = this.b[1];
    long l3 = this.b[2];
    long l4 = this.b[3];
    long[] arrayOfLong1 = new long[16];
    int i = 0;
    int j = 0;
    if (i < 64)
    {
      int k = paramArrayOfByte[i];
      long l69;
      label70: int m;
      long l70;
      label92: int n;
      long l72;
      label124: long l73;
      int i1;
      long l74;
      if (k < 0)
      {
        l69 = k & 0xFF;
        m = paramArrayOfByte[(i + 1)];
        if (m >= 0)
          break label193;
        l70 = m & 0xFF;
        long l71 = l69 | l70 << 8;
        n = paramArrayOfByte[(i + 2)];
        if (n >= 0)
          break label201;
        l72 = n & 0xFF;
        l73 = l71 | l72 << 16;
        i1 = paramArrayOfByte[(i + 3)];
        if (i1 >= 0)
          break label209;
        l74 = i1 & 0xFF;
      }
      while (true)
      {
        arrayOfLong1[j] = (l73 | l74 << 24);
        int i2 = j + 1;
        i += 4;
        j = i2;
        break;
        l69 = k;
        break label70;
        label193: l70 = m;
        break label92;
        label201: l72 = n;
        break label124;
        label209: l74 = i1;
      }
    }
    long l5 = a(l1, l2, l3, l4, arrayOfLong1[0], 7L, 3614090360L);
    long l6 = a(l4, l5, l2, l3, arrayOfLong1[1], 12L, 3905402710L);
    long l7 = a(l3, l6, l5, l2, arrayOfLong1[2], 17L, 606105819L);
    long l8 = a(l2, l7, l6, l5, arrayOfLong1[3], 22L, 3250441966L);
    long l9 = a(l5, l8, l7, l6, arrayOfLong1[4], 7L, 4118548399L);
    long l10 = a(l6, l9, l8, l7, arrayOfLong1[5], 12L, 1200080426L);
    long l11 = a(l7, l10, l9, l8, arrayOfLong1[6], 17L, 2821735955L);
    long l12 = a(l8, l11, l10, l9, arrayOfLong1[7], 22L, 4249261313L);
    long l13 = a(l9, l12, l11, l10, arrayOfLong1[8], 7L, 1770035416L);
    long l14 = a(l10, l13, l12, l11, arrayOfLong1[9], 12L, 2336552879L);
    long l15 = a(l11, l14, l13, l12, arrayOfLong1[10], 17L, 4294925233L);
    long l16 = a(l12, l15, l14, l13, arrayOfLong1[11], 22L, 2304563134L);
    long l17 = a(l13, l16, l15, l14, arrayOfLong1[12], 7L, 1804603682L);
    long l18 = a(l14, l17, l16, l15, arrayOfLong1[13], 12L, 4254626195L);
    long l19 = a(l15, l18, l17, l16, arrayOfLong1[14], 17L, 2792965006L);
    long l20 = a(l16, l19, l18, l17, arrayOfLong1[15], 22L, 1236535329L);
    long l21 = b(l17, l20, l19, l18, arrayOfLong1[1], 5L, 4129170786L);
    long l22 = b(l18, l21, l20, l19, arrayOfLong1[6], 9L, 3225465664L);
    long l23 = b(l19, l22, l21, l20, arrayOfLong1[11], 14L, 643717713L);
    long l24 = b(l20, l23, l22, l21, arrayOfLong1[0], 20L, 3921069994L);
    long l25 = b(l21, l24, l23, l22, arrayOfLong1[5], 5L, 3593408605L);
    long l26 = b(l22, l25, l24, l23, arrayOfLong1[10], 9L, 38016083L);
    long l27 = b(l23, l26, l25, l24, arrayOfLong1[15], 14L, 3634488961L);
    long l28 = b(l24, l27, l26, l25, arrayOfLong1[4], 20L, 3889429448L);
    long l29 = b(l25, l28, l27, l26, arrayOfLong1[9], 5L, 568446438L);
    long l30 = b(l26, l29, l28, l27, arrayOfLong1[14], 9L, 3275163606L);
    long l31 = b(l27, l30, l29, l28, arrayOfLong1[3], 14L, 4107603335L);
    long l32 = b(l28, l31, l30, l29, arrayOfLong1[8], 20L, 1163531501L);
    long l33 = b(l29, l32, l31, l30, arrayOfLong1[13], 5L, 2850285829L);
    long l34 = b(l30, l33, l32, l31, arrayOfLong1[2], 9L, 4243563512L);
    long l35 = b(l31, l34, l33, l32, arrayOfLong1[7], 14L, 1735328473L);
    long l36 = b(l32, l35, l34, l33, arrayOfLong1[12], 20L, 2368359562L);
    long l37 = c(l33, l36, l35, l34, arrayOfLong1[5], 4L, 4294588738L);
    long l38 = c(l34, l37, l36, l35, arrayOfLong1[8], 11L, 2272392833L);
    long l39 = c(l35, l38, l37, l36, arrayOfLong1[11], 16L, 1839030562L);
    long l40 = c(l36, l39, l38, l37, arrayOfLong1[14], 23L, 4259657740L);
    long l41 = c(l37, l40, l39, l38, arrayOfLong1[1], 4L, 2763975236L);
    long l42 = c(l38, l41, l40, l39, arrayOfLong1[4], 11L, 1272893353L);
    long l43 = c(l39, l42, l41, l40, arrayOfLong1[7], 16L, 4139469664L);
    long l44 = c(l40, l43, l42, l41, arrayOfLong1[10], 23L, 3200236656L);
    long l45 = c(l41, l44, l43, l42, arrayOfLong1[13], 4L, 681279174L);
    long l46 = c(l42, l45, l44, l43, arrayOfLong1[0], 11L, 3936430074L);
    long l47 = c(l43, l46, l45, l44, arrayOfLong1[3], 16L, 3572445317L);
    long l48 = c(l44, l47, l46, l45, arrayOfLong1[6], 23L, 76029189L);
    long l49 = c(l45, l48, l47, l46, arrayOfLong1[9], 4L, 3654602809L);
    long l50 = c(l46, l49, l48, l47, arrayOfLong1[12], 11L, 3873151461L);
    long l51 = c(l47, l50, l49, l48, arrayOfLong1[15], 16L, 530742520L);
    long l52 = c(l48, l51, l50, l49, arrayOfLong1[2], 23L, 3299628645L);
    long l53 = d(l49, l52, l51, l50, arrayOfLong1[0], 6L, 4096336452L);
    long l54 = d(l50, l53, l52, l51, arrayOfLong1[7], 10L, 1126891415L);
    long l55 = d(l51, l54, l53, l52, arrayOfLong1[14], 15L, 2878612391L);
    long l56 = d(l52, l55, l54, l53, arrayOfLong1[5], 21L, 4237533241L);
    long l57 = d(l53, l56, l55, l54, arrayOfLong1[12], 6L, 1700485571L);
    long l58 = d(l54, l57, l56, l55, arrayOfLong1[3], 10L, 2399980690L);
    long l59 = d(l55, l58, l57, l56, arrayOfLong1[10], 15L, 4293915773L);
    long l60 = d(l56, l59, l58, l57, arrayOfLong1[1], 21L, 2240044497L);
    long l61 = d(l57, l60, l59, l58, arrayOfLong1[8], 6L, 1873313359L);
    long l62 = d(l58, l61, l60, l59, arrayOfLong1[15], 10L, 4264355552L);
    long l63 = d(l59, l62, l61, l60, arrayOfLong1[6], 15L, 2734768916L);
    long l64 = d(l60, l63, l62, l61, arrayOfLong1[13], 21L, 1309151649L);
    long l65 = d(l61, l64, l63, l62, arrayOfLong1[4], 6L, 4149444226L);
    long l66 = d(l62, l65, l64, l63, arrayOfLong1[11], 10L, 3174756917L);
    long l67 = d(l63, l66, l65, l64, arrayOfLong1[2], 15L, 718787259L);
    long l68 = d(l64, l67, l66, l65, arrayOfLong1[9], 21L, 3951481745L);
    long[] arrayOfLong2 = this.b;
    arrayOfLong2[0] = (l65 + arrayOfLong2[0]);
    long[] arrayOfLong3 = this.b;
    arrayOfLong3[1] = (l68 + arrayOfLong3[1]);
    long[] arrayOfLong4 = this.b;
    arrayOfLong4[2] = (l67 + arrayOfLong4[2]);
    long[] arrayOfLong5 = this.b;
    arrayOfLong5[3] = (l66 + arrayOfLong5[3]);
  }

  private void a(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    byte[] arrayOfByte1 = new byte[64];
    int j = 0x3F & (int)(this.c[0] >>> 3);
    long[] arrayOfLong1 = this.c;
    long l = arrayOfLong1[0] + (paramInt << 3);
    arrayOfLong1[0] = l;
    if (l < paramInt << 3)
    {
      long[] arrayOfLong3 = this.c;
      arrayOfLong3[1] = (1L + arrayOfLong3[1]);
    }
    long[] arrayOfLong2 = this.c;
    arrayOfLong2[1] += (paramInt >>> 29);
    int k = 64 - j;
    int m;
    if (paramInt >= k)
    {
      byte[] arrayOfByte3 = this.d;
      for (int i1 = 0; i1 < k; i1++)
        arrayOfByte3[(j + i1)] = paramArrayOfByte[(0 + i1)];
      a(this.d);
      for (int i2 = k; i2 + 63 < paramInt; i2 += 64)
      {
        for (int i3 = 0; i3 < 64; i3++)
          arrayOfByte1[(0 + i3)] = paramArrayOfByte[(i2 + i3)];
        a(arrayOfByte1);
      }
      m = i2;
      j = 0;
    }
    while (true)
    {
      byte[] arrayOfByte2 = this.d;
      int n = paramInt - m;
      while (i < n)
      {
        arrayOfByte2[(j + i)] = paramArrayOfByte[(m + i)];
        i++;
      }
      m = 0;
    }
  }

  private static void a(byte[] paramArrayOfByte, long[] paramArrayOfLong, int paramInt)
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

  // ERROR //
  private boolean a(java.io.InputStream paramInputStream, long paramLong)
  {
    // Byte code:
    //   0: bipush 64
    //   2: newarray byte
    //   4: astore 4
    //   6: bipush 63
    //   8: aload_0
    //   9: getfield 24	com/tencent/apkupdate/c/c:c	[J
    //   12: iconst_0
    //   13: laload
    //   14: iconst_3
    //   15: lushr
    //   16: l2i
    //   17: iand
    //   18: istore 5
    //   20: aload_0
    //   21: getfield 24	com/tencent/apkupdate/c/c:c	[J
    //   24: astore 6
    //   26: aload 6
    //   28: iconst_0
    //   29: laload
    //   30: lload_2
    //   31: iconst_3
    //   32: lshl
    //   33: ladd
    //   34: lstore 7
    //   36: aload 6
    //   38: iconst_0
    //   39: lload 7
    //   41: lastore
    //   42: lload 7
    //   44: lload_2
    //   45: iconst_3
    //   46: lshl
    //   47: lcmp
    //   48: ifge +19 -> 67
    //   51: aload_0
    //   52: getfield 24	com/tencent/apkupdate/c/c:c	[J
    //   55: astore 26
    //   57: aload 26
    //   59: iconst_1
    //   60: lconst_1
    //   61: aload 26
    //   63: iconst_1
    //   64: laload
    //   65: ladd
    //   66: lastore
    //   67: aload_0
    //   68: getfield 24	com/tencent/apkupdate/c/c:c	[J
    //   71: astore 9
    //   73: aload 9
    //   75: iconst_1
    //   76: aload 9
    //   78: iconst_1
    //   79: laload
    //   80: lload_2
    //   81: bipush 29
    //   83: lushr
    //   84: ladd
    //   85: lastore
    //   86: bipush 64
    //   88: iload 5
    //   90: isub
    //   91: istore 10
    //   93: lload_2
    //   94: iload 10
    //   96: i2l
    //   97: lcmp
    //   98: iflt +164 -> 262
    //   101: iload 10
    //   103: newarray byte
    //   105: astore 18
    //   107: aload_1
    //   108: aload 18
    //   110: iconst_0
    //   111: iload 10
    //   113: invokevirtual 284	java/io/InputStream:read	([BII)I
    //   116: pop
    //   117: aload_0
    //   118: getfield 26	com/tencent/apkupdate/c/c:d	[B
    //   121: astore 21
    //   123: iconst_0
    //   124: istore 22
    //   126: iload 22
    //   128: iload 10
    //   130: if_icmpge +24 -> 154
    //   133: aload 21
    //   135: iload 5
    //   137: iload 22
    //   139: iadd
    //   140: aload 18
    //   142: iconst_0
    //   143: iload 22
    //   145: iadd
    //   146: baload
    //   147: bastore
    //   148: iinc 22 1
    //   151: goto -25 -> 126
    //   154: aload_0
    //   155: aload_0
    //   156: getfield 26	com/tencent/apkupdate/c/c:d	[B
    //   159: invokespecial 274	com/tencent/apkupdate/c/c:a	([B)V
    //   162: iload 10
    //   164: istore 23
    //   166: iload 23
    //   168: bipush 63
    //   170: iadd
    //   171: i2l
    //   172: lload_2
    //   173: lcmp
    //   174: ifge +22 -> 196
    //   177: aload_1
    //   178: aload 4
    //   180: invokevirtual 287	java/io/InputStream:read	([B)I
    //   183: pop
    //   184: aload_0
    //   185: aload 4
    //   187: invokespecial 274	com/tencent/apkupdate/c/c:a	([B)V
    //   190: iinc 23 64
    //   193: goto -27 -> 166
    //   196: iload 23
    //   198: istore 11
    //   200: iconst_0
    //   201: istore 5
    //   203: lload_2
    //   204: iload 11
    //   206: i2l
    //   207: lsub
    //   208: l2i
    //   209: newarray byte
    //   211: astore 12
    //   213: aload_1
    //   214: aload 12
    //   216: invokevirtual 287	java/io/InputStream:read	([B)I
    //   219: pop
    //   220: aload_0
    //   221: getfield 26	com/tencent/apkupdate/c/c:d	[B
    //   224: astore 15
    //   226: aload 12
    //   228: arraylength
    //   229: istore 16
    //   231: iconst_0
    //   232: istore 17
    //   234: iload 17
    //   236: iload 16
    //   238: if_icmpge +30 -> 268
    //   241: aload 15
    //   243: iload 5
    //   245: iload 17
    //   247: iadd
    //   248: aload 12
    //   250: iconst_0
    //   251: iload 17
    //   253: iadd
    //   254: baload
    //   255: bastore
    //   256: iinc 17 1
    //   259: goto -25 -> 234
    //   262: iconst_0
    //   263: istore 11
    //   265: goto -62 -> 203
    //   268: iconst_1
    //   269: ireturn
    //   270: astore 19
    //   272: iconst_0
    //   273: ireturn
    //   274: astore 24
    //   276: iconst_0
    //   277: ireturn
    //   278: astore 13
    //   280: iconst_0
    //   281: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   107	117	270	java/lang/Exception
    //   177	184	274	java/lang/Exception
    //   213	220	278	java/lang/Exception
  }

  private long b(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + (paramLong2 & paramLong4 | paramLong3 & (0xFFFFFFFF ^ paramLong4))));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  // ERROR //
  public static String b(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: new 289	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 292	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: sipush 1024
    //   14: newarray byte
    //   16: astore 7
    //   18: ldc_w 294
    //   21: invokestatic 300	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   24: astore 8
    //   26: iload_1
    //   27: iconst_m1
    //   28: if_icmpeq +38 -> 66
    //   31: aload_2
    //   32: aload 7
    //   34: invokevirtual 287	java/io/InputStream:read	([B)I
    //   37: istore_1
    //   38: iload_1
    //   39: ifle -13 -> 26
    //   42: aload 8
    //   44: aload 7
    //   46: iconst_0
    //   47: iload_1
    //   48: invokevirtual 304	java/security/MessageDigest:update	([BII)V
    //   51: goto -25 -> 26
    //   54: astore 5
    //   56: aload_2
    //   57: ifnull +7 -> 64
    //   60: aload_2
    //   61: invokevirtual 307	java/io/InputStream:close	()V
    //   64: aconst_null
    //   65: areturn
    //   66: aload 8
    //   68: invokevirtual 310	java/security/MessageDigest:digest	()[B
    //   71: invokestatic 313	com/tencent/apkupdate/c/c:b	([B)Ljava/lang/String;
    //   74: astore 9
    //   76: aload_2
    //   77: invokevirtual 307	java/io/InputStream:close	()V
    //   80: aload 9
    //   82: areturn
    //   83: astore 10
    //   85: aload 9
    //   87: areturn
    //   88: astore 12
    //   90: aconst_null
    //   91: astore_2
    //   92: aload 12
    //   94: astore_3
    //   95: aload_2
    //   96: ifnull +7 -> 103
    //   99: aload_2
    //   100: invokevirtual 307	java/io/InputStream:close	()V
    //   103: aload_3
    //   104: athrow
    //   105: astore 6
    //   107: aconst_null
    //   108: areturn
    //   109: astore 4
    //   111: goto -8 -> 103
    //   114: astore_3
    //   115: goto -20 -> 95
    //   118: astore 11
    //   120: aconst_null
    //   121: astore_2
    //   122: goto -66 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   11	26	54	java/lang/Exception
    //   31	38	54	java/lang/Exception
    //   42	51	54	java/lang/Exception
    //   66	76	54	java/lang/Exception
    //   76	80	83	java/lang/Exception
    //   2	11	88	finally
    //   60	64	105	java/lang/Exception
    //   99	103	109	java/lang/Exception
    //   11	26	114	finally
    //   31	38	114	finally
    //   42	51	114	finally
    //   66	76	114	finally
    //   2	11	118	java/lang/Exception
  }

  private static String b(byte[] paramArrayOfByte)
  {
    String str = "";
    for (int i = 0; i < paramArrayOfByte.length; i++)
      str = str + Integer.toString(256 + (0xFF & paramArrayOfByte[i]), 16).substring(1);
    return str.toUpperCase();
  }

  private long c(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + (paramLong4 ^ (paramLong2 ^ paramLong3))));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }

  private long d(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    long l = paramLong1 + (paramLong7 + (paramLong5 + (paramLong3 ^ (paramLong2 | 0xFFFFFFFF ^ paramLong4))));
    return paramLong2 + ((int)l << (int)paramLong6 | (int)l >>> (int)(32L - paramLong6));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.c.c
 * JD-Core Version:    0.6.0
 */