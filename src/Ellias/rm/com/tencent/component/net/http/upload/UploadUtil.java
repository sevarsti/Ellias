package com.tencent.component.net.http.upload;

import java.io.File;

public class UploadUtil
{
  private static final String a = "UploadUtil";
  private static final char[] b = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };

  // ERROR //
  public static UploadUtil.RetrieveSendDataResult a(UploadTask paramUploadTask, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ifnull +60 -> 63
    //   6: aload_0
    //   7: invokevirtual 44	com/tencent/component/net/http/upload/UploadTask:haslegalFile	()Z
    //   10: istore 4
    //   12: aconst_null
    //   13: astore_3
    //   14: iload 4
    //   16: ifeq +47 -> 63
    //   19: new 46	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult
    //   22: dup
    //   23: invokespecial 47	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:<init>	()V
    //   26: astore_3
    //   27: new 49	java/io/File
    //   30: dup
    //   31: aload_0
    //   32: getfield 52	com/tencent/component/net/http/upload/UploadTask:uploadFilePath	Ljava/lang/String;
    //   35: invokespecial 55	java/io/File:<init>	(Ljava/lang/String;)V
    //   38: astore 5
    //   40: aload 5
    //   42: invokevirtual 59	java/io/File:length	()J
    //   45: lconst_0
    //   46: lcmp
    //   47: ifne +18 -> 65
    //   50: aload_3
    //   51: sipush -701
    //   54: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   57: aload_3
    //   58: ldc 64
    //   60: putfield 66	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:b	Ljava/lang/String;
    //   63: aload_3
    //   64: areturn
    //   65: aload 5
    //   67: invokevirtual 59	java/io/File:length	()J
    //   70: iload_1
    //   71: iload_2
    //   72: iadd
    //   73: i2l
    //   74: lcmp
    //   75: ifge +13 -> 88
    //   78: aload 5
    //   80: invokevirtual 59	java/io/File:length	()J
    //   83: iload_1
    //   84: i2l
    //   85: lsub
    //   86: l2i
    //   87: istore_2
    //   88: iload_2
    //   89: ifge +18 -> 107
    //   92: aload_3
    //   93: sipush -703
    //   96: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   99: aload_3
    //   100: ldc 68
    //   102: putfield 66	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:b	Ljava/lang/String;
    //   105: aload_3
    //   106: areturn
    //   107: iload_2
    //   108: newarray byte
    //   110: astore 6
    //   112: new 70	java/io/FileInputStream
    //   115: dup
    //   116: aload 5
    //   118: invokespecial 73	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   121: astore 7
    //   123: lconst_0
    //   124: lstore 8
    //   126: iconst_0
    //   127: istore 10
    //   129: iload_1
    //   130: ifle +33 -> 163
    //   133: iload_1
    //   134: i2l
    //   135: lload 8
    //   137: lsub
    //   138: lstore 21
    //   140: aload 7
    //   142: lload 21
    //   144: invokevirtual 77	java/io/FileInputStream:skip	(J)J
    //   147: lstore 23
    //   149: lload 23
    //   151: lconst_0
    //   152: lcmp
    //   153: istore 25
    //   155: iconst_0
    //   156: istore 10
    //   158: iload 25
    //   160: ifgt +105 -> 265
    //   163: lload 8
    //   165: iload_1
    //   166: i2l
    //   167: lcmp
    //   168: ifne +20 -> 188
    //   171: aload 7
    //   173: aload 6
    //   175: iload 10
    //   177: iload_2
    //   178: invokevirtual 81	java/io/FileInputStream:read	([BII)I
    //   181: istore 19
    //   183: iload 19
    //   185: ifgt +101 -> 286
    //   188: aload 7
    //   190: ifnull +8 -> 198
    //   193: aload 7
    //   195: invokevirtual 84	java/io/FileInputStream:close	()V
    //   198: iload 10
    //   200: iload_2
    //   201: if_icmpeq +293 -> 494
    //   204: aload_3
    //   205: sipush -703
    //   208: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   211: aload_3
    //   212: new 86	java/lang/StringBuilder
    //   215: dup
    //   216: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   219: ldc 89
    //   221: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: iload 10
    //   226: invokevirtual 96	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   229: ldc 98
    //   231: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: iload_2
    //   235: invokevirtual 96	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   238: ldc 100
    //   240: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: lload 8
    //   245: invokevirtual 103	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   248: ldc 105
    //   250: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: iload_1
    //   254: invokevirtual 96	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   257: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   260: putfield 66	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:b	Ljava/lang/String;
    //   263: aload_3
    //   264: areturn
    //   265: lload 8
    //   267: lload 23
    //   269: ladd
    //   270: lstore 8
    //   272: lload 8
    //   274: iload_1
    //   275: i2l
    //   276: lcmp
    //   277: ifne -151 -> 126
    //   280: iconst_0
    //   281: istore 10
    //   283: goto -120 -> 163
    //   286: iload 10
    //   288: iload 19
    //   290: iadd
    //   291: istore 10
    //   293: iload 10
    //   295: iload_2
    //   296: if_icmpne -133 -> 163
    //   299: goto -111 -> 188
    //   302: astore 20
    //   304: ldc 8
    //   306: aload 20
    //   308: invokevirtual 110	java/io/IOException:toString	()Ljava/lang/String;
    //   311: invokestatic 116	com/tencent/component/net/http/upload/UploadLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   314: goto -116 -> 198
    //   317: astore 11
    //   319: aconst_null
    //   320: astore 7
    //   322: ldc 8
    //   324: aload 11
    //   326: invokevirtual 117	java/io/FileNotFoundException:toString	()Ljava/lang/String;
    //   329: invokestatic 116	com/tencent/component/net/http/upload/UploadLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   332: aload_3
    //   333: sipush -702
    //   336: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   339: new 86	java/lang/StringBuilder
    //   342: dup
    //   343: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   346: ldc 119
    //   348: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: astore 14
    //   353: aload 5
    //   355: ifnull +50 -> 405
    //   358: aload 5
    //   360: invokevirtual 120	java/io/File:toString	()Ljava/lang/String;
    //   363: astore 15
    //   365: aload_3
    //   366: aload 14
    //   368: aload 15
    //   370: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   376: putfield 66	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:b	Ljava/lang/String;
    //   379: aload 7
    //   381: ifnull -318 -> 63
    //   384: aload 7
    //   386: invokevirtual 84	java/io/FileInputStream:close	()V
    //   389: aload_3
    //   390: areturn
    //   391: astore 16
    //   393: ldc 8
    //   395: aload 16
    //   397: invokevirtual 110	java/io/IOException:toString	()Ljava/lang/String;
    //   400: invokestatic 116	com/tencent/component/net/http/upload/UploadLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   403: aload_3
    //   404: areturn
    //   405: ldc 122
    //   407: astore 15
    //   409: goto -44 -> 365
    //   412: ldc 8
    //   414: aload 17
    //   416: invokevirtual 110	java/io/IOException:toString	()Ljava/lang/String;
    //   419: invokestatic 116	com/tencent/component/net/http/upload/UploadLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   422: aload_3
    //   423: sipush -702
    //   426: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   429: aload_3
    //   430: ldc 124
    //   432: putfield 66	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:b	Ljava/lang/String;
    //   435: aload 7
    //   437: ifnull -374 -> 63
    //   440: aload 7
    //   442: invokevirtual 84	java/io/FileInputStream:close	()V
    //   445: aload_3
    //   446: areturn
    //   447: astore 18
    //   449: ldc 8
    //   451: aload 18
    //   453: invokevirtual 110	java/io/IOException:toString	()Ljava/lang/String;
    //   456: invokestatic 116	com/tencent/component/net/http/upload/UploadLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: aload_3
    //   460: areturn
    //   461: astore 12
    //   463: aconst_null
    //   464: astore 7
    //   466: aload 7
    //   468: ifnull +8 -> 476
    //   471: aload 7
    //   473: invokevirtual 84	java/io/FileInputStream:close	()V
    //   476: aload 12
    //   478: athrow
    //   479: astore 13
    //   481: ldc 8
    //   483: aload 13
    //   485: invokevirtual 110	java/io/IOException:toString	()Ljava/lang/String;
    //   488: invokestatic 116	com/tencent/component/net/http/upload/UploadLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   491: goto -15 -> 476
    //   494: aload_3
    //   495: iload_2
    //   496: putfield 126	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:d	I
    //   499: aload 6
    //   501: ifnonnull +18 -> 519
    //   504: aload_3
    //   505: sipush -703
    //   508: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   511: aload_3
    //   512: ldc 128
    //   514: putfield 66	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:b	Ljava/lang/String;
    //   517: aload_3
    //   518: areturn
    //   519: aload_3
    //   520: iconst_0
    //   521: putfield 62	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:a	I
    //   524: aload_3
    //   525: aload 6
    //   527: putfield 132	com/tencent/component/net/http/upload/UploadUtil$RetrieveSendDataResult:c	[B
    //   530: aload_3
    //   531: areturn
    //   532: astore 12
    //   534: goto -68 -> 466
    //   537: astore 17
    //   539: goto -127 -> 412
    //   542: astore 11
    //   544: goto -222 -> 322
    //   547: astore 17
    //   549: aconst_null
    //   550: astore 7
    //   552: goto -140 -> 412
    //
    // Exception table:
    //   from	to	target	type
    //   193	198	302	java/io/IOException
    //   112	123	317	java/io/FileNotFoundException
    //   384	389	391	java/io/IOException
    //   440	445	447	java/io/IOException
    //   112	123	461	finally
    //   471	476	479	java/io/IOException
    //   140	149	532	finally
    //   171	183	532	finally
    //   322	353	532	finally
    //   358	365	532	finally
    //   365	379	532	finally
    //   412	435	532	finally
    //   140	149	537	java/io/IOException
    //   171	183	537	java/io/IOException
    //   140	149	542	java/io/FileNotFoundException
    //   171	183	542	java/io/FileNotFoundException
    //   112	123	547	java/io/IOException
  }

  public static String a(File paramFile)
  {
    if (paramFile == null)
      return null;
    if (paramFile.length() < 204800L);
    for (String str = b(paramFile); ; str = c(paramFile))
    {
      if ("".equals(str))
        str = null;
      return str;
    }
  }

  // ERROR //
  public static String a(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: new 70	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 149	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: sipush 1024
    //   14: newarray byte
    //   16: astore 8
    //   18: ldc 151
    //   20: invokestatic 157	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   23: astore 9
    //   25: iload_1
    //   26: iconst_m1
    //   27: if_icmpeq +42 -> 69
    //   30: aload_2
    //   31: aload 8
    //   33: invokevirtual 162	java/io/InputStream:read	([B)I
    //   36: istore_1
    //   37: iload_1
    //   38: ifle -13 -> 25
    //   41: aload 9
    //   43: aload 8
    //   45: iconst_0
    //   46: iload_1
    //   47: invokevirtual 166	java/security/MessageDigest:update	([BII)V
    //   50: goto -25 -> 25
    //   53: astore 5
    //   55: aconst_null
    //   56: astore 6
    //   58: aload_2
    //   59: ifnull +7 -> 66
    //   62: aload_2
    //   63: invokevirtual 167	java/io/InputStream:close	()V
    //   66: aload 6
    //   68: areturn
    //   69: aload 9
    //   71: invokevirtual 171	java/security/MessageDigest:digest	()[B
    //   74: invokestatic 174	com/tencent/component/net/http/upload/UploadUtil:b	([B)Ljava/lang/String;
    //   77: astore 10
    //   79: aload 10
    //   81: astore 6
    //   83: aload_2
    //   84: ifnull -18 -> 66
    //   87: aload_2
    //   88: invokevirtual 167	java/io/InputStream:close	()V
    //   91: aload 6
    //   93: areturn
    //   94: astore 11
    //   96: aload 6
    //   98: areturn
    //   99: astore 13
    //   101: aconst_null
    //   102: astore_2
    //   103: aload 13
    //   105: astore_3
    //   106: aload_2
    //   107: ifnull +7 -> 114
    //   110: aload_2
    //   111: invokevirtual 167	java/io/InputStream:close	()V
    //   114: aload_3
    //   115: athrow
    //   116: astore 7
    //   118: aconst_null
    //   119: areturn
    //   120: astore 4
    //   122: goto -8 -> 114
    //   125: astore_3
    //   126: goto -20 -> 106
    //   129: astore 12
    //   131: aconst_null
    //   132: astore_2
    //   133: goto -78 -> 55
    //
    // Exception table:
    //   from	to	target	type
    //   11	25	53	java/lang/Exception
    //   30	37	53	java/lang/Exception
    //   41	50	53	java/lang/Exception
    //   69	79	53	java/lang/Exception
    //   87	91	94	java/lang/Exception
    //   2	11	99	finally
    //   62	66	116	java/lang/Exception
    //   110	114	120	java/lang/Exception
    //   11	25	125	finally
    //   30	37	125	finally
    //   41	50	125	finally
    //   69	79	125	finally
    //   2	11	129	java/lang/Exception
  }

  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(b[((0xF0 & paramArrayOfByte[i]) >>> 4)]);
      localStringBuilder.append(b[(0xF & paramArrayOfByte[i])]);
    }
    return localStringBuilder.toString();
  }

  // ERROR //
  private static String b(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 184
    //   4: invokestatic 157	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   7: astore 12
    //   9: aload 12
    //   11: invokevirtual 187	java/security/MessageDigest:reset	()V
    //   14: new 70	java/io/FileInputStream
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 73	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   22: astore_3
    //   23: aload 12
    //   25: aload_3
    //   26: invokevirtual 191	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   29: getstatic 197	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   32: lconst_0
    //   33: aload_0
    //   34: invokevirtual 59	java/io/File:length	()J
    //   37: invokevirtual 203	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   40: invokevirtual 206	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   43: aload 12
    //   45: invokevirtual 171	java/security/MessageDigest:digest	()[B
    //   48: invokestatic 208	com/tencent/component/net/http/upload/UploadUtil:a	([B)Ljava/lang/String;
    //   51: astore 16
    //   53: aload_3
    //   54: ifnull +7 -> 61
    //   57: aload_3
    //   58: invokevirtual 84	java/io/FileInputStream:close	()V
    //   61: aload 16
    //   63: areturn
    //   64: astore 10
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 84	java/io/FileInputStream:close	()V
    //   74: ldc 122
    //   76: areturn
    //   77: astore 8
    //   79: aconst_null
    //   80: astore_3
    //   81: aload_3
    //   82: ifnull -8 -> 74
    //   85: aload_3
    //   86: invokevirtual 84	java/io/FileInputStream:close	()V
    //   89: goto -15 -> 74
    //   92: astore 9
    //   94: goto -20 -> 74
    //   97: astore 6
    //   99: aconst_null
    //   100: astore_3
    //   101: aload_3
    //   102: ifnull -28 -> 74
    //   105: aload_3
    //   106: invokevirtual 84	java/io/FileInputStream:close	()V
    //   109: goto -35 -> 74
    //   112: astore 7
    //   114: goto -40 -> 74
    //   117: astore_2
    //   118: aconst_null
    //   119: astore_3
    //   120: aload_2
    //   121: astore 4
    //   123: aload_3
    //   124: ifnull +7 -> 131
    //   127: aload_3
    //   128: invokevirtual 84	java/io/FileInputStream:close	()V
    //   131: aload 4
    //   133: athrow
    //   134: astore 17
    //   136: aload 16
    //   138: areturn
    //   139: astore 11
    //   141: goto -67 -> 74
    //   144: astore 5
    //   146: goto -15 -> 131
    //   149: astore 4
    //   151: goto -28 -> 123
    //   154: astore 15
    //   156: goto -55 -> 101
    //   159: astore 14
    //   161: goto -80 -> 81
    //   164: astore 13
    //   166: aload_3
    //   167: astore_1
    //   168: goto -102 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   2	23	64	java/security/NoSuchAlgorithmException
    //   2	23	77	java/io/FileNotFoundException
    //   85	89	92	java/io/IOException
    //   2	23	97	java/io/IOException
    //   105	109	112	java/io/IOException
    //   2	23	117	finally
    //   57	61	134	java/io/IOException
    //   70	74	139	java/io/IOException
    //   127	131	144	java/io/IOException
    //   23	53	149	finally
    //   23	53	154	java/io/IOException
    //   23	53	159	java/io/FileNotFoundException
    //   23	53	164	java/security/NoSuchAlgorithmException
  }

  private static String b(byte[] paramArrayOfByte)
  {
    String str = "";
    for (int i = 0; i < paramArrayOfByte.length; i++)
      str = str + Integer.toString(256 + (0xFF & paramArrayOfByte[i]), 16).substring(1);
    return str.toLowerCase();
  }

  // ERROR //
  private static String c(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnull +10 -> 13
    //   6: aload_0
    //   7: invokevirtual 223	java/io/File:exists	()Z
    //   10: ifne +8 -> 18
    //   13: ldc 122
    //   15: astore_2
    //   16: aload_2
    //   17: areturn
    //   18: aload_0
    //   19: invokevirtual 59	java/io/File:length	()J
    //   22: lstore_3
    //   23: lload_3
    //   24: ldc2_w 224
    //   27: lcmp
    //   28: ifle +160 -> 188
    //   31: lload_3
    //   32: ldc2_w 226
    //   35: ldiv
    //   36: lstore 21
    //   38: bipush 32
    //   40: newarray byte
    //   42: astore 23
    //   44: new 70	java/io/FileInputStream
    //   47: dup
    //   48: aload_0
    //   49: invokespecial 73	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   52: astore 24
    //   54: aload 24
    //   56: lload 21
    //   58: invokevirtual 77	java/io/FileInputStream:skip	(J)J
    //   61: pop2
    //   62: iconst_0
    //   63: istore 33
    //   65: iload 33
    //   67: iconst_4
    //   68: if_icmpge +36 -> 104
    //   71: aload 24
    //   73: aload 23
    //   75: iload 33
    //   77: bipush 8
    //   79: imul
    //   80: bipush 8
    //   82: invokevirtual 81	java/io/FileInputStream:read	([BII)I
    //   85: pop
    //   86: aload 24
    //   88: lload 21
    //   90: ldc2_w 228
    //   93: lsub
    //   94: invokevirtual 77	java/io/FileInputStream:skip	(J)J
    //   97: pop2
    //   98: iinc 33 1
    //   101: goto -36 -> 65
    //   104: aload 23
    //   106: invokestatic 208	com/tencent/component/net/http/upload/UploadUtil:a	([B)Ljava/lang/String;
    //   109: astore 37
    //   111: aload 37
    //   113: astore_2
    //   114: aload 24
    //   116: ifnull -100 -> 16
    //   119: aload 24
    //   121: invokevirtual 84	java/io/FileInputStream:close	()V
    //   124: aload_2
    //   125: areturn
    //   126: astore 38
    //   128: aload_2
    //   129: areturn
    //   130: astore 41
    //   132: aload_1
    //   133: ifnull +7 -> 140
    //   136: aload_1
    //   137: invokevirtual 84	java/io/FileInputStream:close	()V
    //   140: ldc 122
    //   142: areturn
    //   143: astore 40
    //   145: aconst_null
    //   146: astore 24
    //   148: aload 24
    //   150: ifnull -10 -> 140
    //   153: aload 24
    //   155: invokevirtual 84	java/io/FileInputStream:close	()V
    //   158: goto -18 -> 140
    //   161: astore 28
    //   163: goto -23 -> 140
    //   166: astore 39
    //   168: aconst_null
    //   169: astore 24
    //   171: aload 39
    //   173: astore 29
    //   175: aload 24
    //   177: ifnull +8 -> 185
    //   180: aload 24
    //   182: invokevirtual 84	java/io/FileInputStream:close	()V
    //   185: aload 29
    //   187: athrow
    //   188: ldc 184
    //   190: invokestatic 157	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   193: astore 15
    //   195: aload 15
    //   197: invokevirtual 187	java/security/MessageDigest:reset	()V
    //   200: new 70	java/io/FileInputStream
    //   203: dup
    //   204: aload_0
    //   205: invokespecial 73	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   208: astore 6
    //   210: aload 15
    //   212: aload 6
    //   214: invokevirtual 191	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   217: getstatic 197	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   220: lconst_0
    //   221: aload_0
    //   222: invokevirtual 59	java/io/File:length	()J
    //   225: invokevirtual 203	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   228: invokevirtual 206	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   231: aload 15
    //   233: invokevirtual 171	java/security/MessageDigest:digest	()[B
    //   236: invokestatic 208	com/tencent/component/net/http/upload/UploadUtil:a	([B)Ljava/lang/String;
    //   239: astore 19
    //   241: aload 19
    //   243: astore_2
    //   244: aload 6
    //   246: ifnull -230 -> 16
    //   249: aload 6
    //   251: invokevirtual 84	java/io/FileInputStream:close	()V
    //   254: aload_2
    //   255: areturn
    //   256: astore 20
    //   258: aload_2
    //   259: areturn
    //   260: astore 13
    //   262: aload_1
    //   263: ifnull -123 -> 140
    //   266: aload_1
    //   267: invokevirtual 84	java/io/FileInputStream:close	()V
    //   270: goto -130 -> 140
    //   273: astore 14
    //   275: goto -135 -> 140
    //   278: astore 11
    //   280: aconst_null
    //   281: astore 6
    //   283: aload 6
    //   285: ifnull -145 -> 140
    //   288: aload 6
    //   290: invokevirtual 84	java/io/FileInputStream:close	()V
    //   293: goto -153 -> 140
    //   296: astore 12
    //   298: goto -158 -> 140
    //   301: astore 9
    //   303: aconst_null
    //   304: astore 6
    //   306: aload 6
    //   308: ifnull -168 -> 140
    //   311: aload 6
    //   313: invokevirtual 84	java/io/FileInputStream:close	()V
    //   316: goto -176 -> 140
    //   319: astore 10
    //   321: goto -181 -> 140
    //   324: astore 5
    //   326: aconst_null
    //   327: astore 6
    //   329: aload 5
    //   331: astore 7
    //   333: aload 6
    //   335: ifnull +8 -> 343
    //   338: aload 6
    //   340: invokevirtual 84	java/io/FileInputStream:close	()V
    //   343: aload 7
    //   345: athrow
    //   346: astore 26
    //   348: goto -208 -> 140
    //   351: astore 30
    //   353: goto -168 -> 185
    //   356: astore 8
    //   358: goto -15 -> 343
    //   361: astore 7
    //   363: goto -30 -> 333
    //   366: astore 18
    //   368: goto -62 -> 306
    //   371: astore 17
    //   373: goto -90 -> 283
    //   376: astore 16
    //   378: aload 6
    //   380: astore_1
    //   381: goto -119 -> 262
    //   384: astore 29
    //   386: goto -211 -> 175
    //   389: astore 27
    //   391: goto -243 -> 148
    //   394: astore 25
    //   396: aload 24
    //   398: astore_1
    //   399: goto -267 -> 132
    //
    // Exception table:
    //   from	to	target	type
    //   119	124	126	java/io/IOException
    //   44	54	130	java/io/FileNotFoundException
    //   44	54	143	java/io/IOException
    //   153	158	161	java/io/IOException
    //   44	54	166	finally
    //   249	254	256	java/io/IOException
    //   188	210	260	java/security/NoSuchAlgorithmException
    //   266	270	273	java/io/IOException
    //   188	210	278	java/io/FileNotFoundException
    //   288	293	296	java/io/IOException
    //   188	210	301	java/io/IOException
    //   311	316	319	java/io/IOException
    //   188	210	324	finally
    //   136	140	346	java/io/IOException
    //   180	185	351	java/io/IOException
    //   338	343	356	java/io/IOException
    //   210	241	361	finally
    //   210	241	366	java/io/IOException
    //   210	241	371	java/io/FileNotFoundException
    //   210	241	376	java/security/NoSuchAlgorithmException
    //   54	62	384	finally
    //   71	98	384	finally
    //   104	111	384	finally
    //   54	62	389	java/io/IOException
    //   71	98	389	java/io/IOException
    //   104	111	389	java/io/IOException
    //   54	62	394	java/io/FileNotFoundException
    //   71	98	394	java/io/FileNotFoundException
    //   104	111	394	java/io/FileNotFoundException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.UploadUtil
 * JD-Core Version:    0.6.0
 */