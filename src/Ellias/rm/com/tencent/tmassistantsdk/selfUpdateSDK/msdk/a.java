package com.tencent.tmassistantsdk.selfUpdateSDK.msdk;

import java.io.RandomAccessFile;
import java.util.zip.ZipException;

public final class a
{
  private static final b a = new b(101010256L);
  private static final c b = new c(38650);

  // ERROR //
  public static boolean a(java.lang.String paramString1, java.lang.String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokestatic 39	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: istore_3
    //   7: iconst_0
    //   8: istore 4
    //   10: iload_3
    //   11: ifne +17 -> 28
    //   14: aload_1
    //   15: invokestatic 39	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   18: istore 5
    //   20: iconst_0
    //   21: istore 4
    //   23: iload 5
    //   25: ifeq +6 -> 31
    //   28: iload 4
    //   30: ireturn
    //   31: new 41	java/io/RandomAccessFile
    //   34: dup
    //   35: aload_0
    //   36: ldc 43
    //   38: invokespecial 46	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: astore 6
    //   43: new 41	java/io/RandomAccessFile
    //   46: dup
    //   47: aload_1
    //   48: ldc 48
    //   50: invokespecial 46	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   53: astore 7
    //   55: aload 6
    //   57: invokestatic 51	com/tencent/tmassistantsdk/selfUpdateSDK/msdk/a:a	(Ljava/io/RandomAccessFile;)[B
    //   60: astore 16
    //   62: aload 16
    //   64: ifnull +31 -> 95
    //   67: ldc 53
    //   69: new 55	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 57	java/lang/StringBuilder:<init>	()V
    //   76: ldc 59
    //   78: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload 16
    //   83: invokestatic 68	com/tencent/tmassistantsdk/g/g:a	([B)Ljava/lang/String;
    //   86: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokestatic 76	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: aload 7
    //   97: invokevirtual 80	java/io/RandomAccessFile:length	()J
    //   100: lstore 17
    //   102: aload 16
    //   104: ifnonnull +82 -> 186
    //   107: iconst_0
    //   108: istore 19
    //   110: aload 7
    //   112: lload 17
    //   114: iload 19
    //   116: i2l
    //   117: lsub
    //   118: ldc2_w 81
    //   121: lsub
    //   122: invokevirtual 85	java/io/RandomAccessFile:seek	(J)V
    //   125: aload 7
    //   127: new 22	com/tencent/tmassistantsdk/selfUpdateSDK/msdk/c
    //   130: dup
    //   131: aload 16
    //   133: arraylength
    //   134: invokespecial 26	com/tencent/tmassistantsdk/selfUpdateSDK/msdk/c:<init>	(I)V
    //   137: invokevirtual 88	com/tencent/tmassistantsdk/selfUpdateSDK/msdk/c:a	()[B
    //   140: invokevirtual 92	java/io/RandomAccessFile:write	([B)V
    //   143: aload 7
    //   145: aload 16
    //   147: invokevirtual 92	java/io/RandomAccessFile:write	([B)V
    //   150: iconst_1
    //   151: istore 4
    //   153: aload 6
    //   155: ifnull +8 -> 163
    //   158: aload 6
    //   160: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   163: aload 7
    //   165: ifnull -137 -> 28
    //   168: aload 7
    //   170: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   173: iload 4
    //   175: ireturn
    //   176: astore 12
    //   178: aload 12
    //   180: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   183: iload 4
    //   185: ireturn
    //   186: aload 16
    //   188: arraylength
    //   189: istore 19
    //   191: goto -81 -> 110
    //   194: astore 8
    //   196: aconst_null
    //   197: astore 7
    //   199: aload 8
    //   201: invokevirtual 99	java/io/FileNotFoundException:printStackTrace	()V
    //   204: aload_2
    //   205: ifnull +7 -> 212
    //   208: aload_2
    //   209: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   212: iconst_0
    //   213: istore 4
    //   215: aload 7
    //   217: ifnull -189 -> 28
    //   220: aload 7
    //   222: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   225: iconst_0
    //   226: ireturn
    //   227: astore 12
    //   229: iconst_0
    //   230: istore 4
    //   232: goto -54 -> 178
    //   235: astore 14
    //   237: aconst_null
    //   238: astore 7
    //   240: aconst_null
    //   241: astore 6
    //   243: aload 14
    //   245: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   248: aload 6
    //   250: ifnull +8 -> 258
    //   253: aload 6
    //   255: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   258: iconst_0
    //   259: istore 4
    //   261: aload 7
    //   263: ifnull -235 -> 28
    //   266: aload 7
    //   268: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   271: iconst_0
    //   272: ireturn
    //   273: astore 12
    //   275: iconst_0
    //   276: istore 4
    //   278: goto -100 -> 178
    //   281: astore 9
    //   283: aconst_null
    //   284: astore 7
    //   286: aconst_null
    //   287: astore 6
    //   289: aload 6
    //   291: ifnull +8 -> 299
    //   294: aload 6
    //   296: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   299: aload 7
    //   301: ifnull +8 -> 309
    //   304: aload 7
    //   306: invokevirtual 95	java/io/RandomAccessFile:close	()V
    //   309: aload 9
    //   311: athrow
    //   312: astore 11
    //   314: aload 11
    //   316: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   319: goto -20 -> 299
    //   322: astore 10
    //   324: aload 10
    //   326: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   329: goto -20 -> 309
    //   332: astore 13
    //   334: aload 13
    //   336: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   339: goto -127 -> 212
    //   342: astore 15
    //   344: aload 15
    //   346: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   349: goto -91 -> 258
    //   352: astore 20
    //   354: aload 20
    //   356: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   359: goto -196 -> 163
    //   362: astore 9
    //   364: aconst_null
    //   365: astore 7
    //   367: goto -78 -> 289
    //   370: astore 9
    //   372: goto -83 -> 289
    //   375: astore 9
    //   377: aload_2
    //   378: astore 6
    //   380: goto -91 -> 289
    //   383: astore 14
    //   385: aconst_null
    //   386: astore 7
    //   388: goto -145 -> 243
    //   391: astore 14
    //   393: goto -150 -> 243
    //   396: astore 8
    //   398: aload 6
    //   400: astore_2
    //   401: aconst_null
    //   402: astore 7
    //   404: goto -205 -> 199
    //   407: astore 8
    //   409: aload 6
    //   411: astore_2
    //   412: goto -213 -> 199
    //
    // Exception table:
    //   from	to	target	type
    //   168	173	176	java/io/IOException
    //   31	43	194	java/io/FileNotFoundException
    //   220	225	227	java/io/IOException
    //   31	43	235	java/io/IOException
    //   266	271	273	java/io/IOException
    //   31	43	281	finally
    //   294	299	312	java/io/IOException
    //   304	309	322	java/io/IOException
    //   208	212	332	java/io/IOException
    //   253	258	342	java/io/IOException
    //   158	163	352	java/io/IOException
    //   43	55	362	finally
    //   55	62	370	finally
    //   67	95	370	finally
    //   95	102	370	finally
    //   110	150	370	finally
    //   186	191	370	finally
    //   243	248	370	finally
    //   199	204	375	finally
    //   43	55	383	java/io/IOException
    //   55	62	391	java/io/IOException
    //   67	95	391	java/io/IOException
    //   95	102	391	java/io/IOException
    //   110	150	391	java/io/IOException
    //   186	191	391	java/io/IOException
    //   43	55	396	java/io/FileNotFoundException
    //   55	62	407	java/io/FileNotFoundException
    //   67	95	407	java/io/FileNotFoundException
    //   95	102	407	java/io/FileNotFoundException
    //   110	150	407	java/io/FileNotFoundException
    //   186	191	407	java/io/FileNotFoundException
  }

  public static byte[] a(RandomAccessFile paramRandomAccessFile)
  {
    int i = 1;
    long l = paramRandomAccessFile.length() - 22L;
    paramRandomAccessFile.seek(l);
    byte[] arrayOfByte1 = a.a();
    int j = paramRandomAccessFile.read();
    if (j != -1)
      if ((j != arrayOfByte1[0]) || (paramRandomAccessFile.read() != arrayOfByte1[i]) || (paramRandomAccessFile.read() != arrayOfByte1[2]) || (paramRandomAccessFile.read() != arrayOfByte1[3]));
    while (true)
    {
      if (i == 0)
      {
        throw new ZipException("archive is not a ZIP archive");
        l -= 1L;
        paramRandomAccessFile.seek(l);
        j = paramRandomAccessFile.read();
        break;
      }
      paramRandomAccessFile.seek(4L + (16L + l));
      byte[] arrayOfByte2 = new byte[2];
      paramRandomAccessFile.readFully(arrayOfByte2);
      int k = new c(arrayOfByte2).b();
      if (k == 0)
        return null;
      byte[] arrayOfByte3 = new byte[k];
      paramRandomAccessFile.read(arrayOfByte3);
      return arrayOfByte3;
      i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.msdk.a
 * JD-Core Version:    0.6.0
 */