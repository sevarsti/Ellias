package com.tencent.component.debug;

import com.tencent.component.utils.FileUtil;
import java.io.File;

public class FileTracerReader
{
  public static final String a = ".zip";
  private static final int b = 8192;
  private FileTracerConfig c;

  public FileTracerReader(FileTracer paramFileTracer)
  {
    this(paramFileTracer.c());
  }

  public FileTracerReader(FileTracerConfig paramFileTracerConfig)
  {
    a(paramFileTracerConfig);
  }

  // ERROR //
  private File b(long paramLong, File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   4: lload_1
    //   5: invokevirtual 38	com/tencent/component/debug/FileTracerConfig:a	(J)Ljava/io/File;
    //   8: astore 4
    //   10: aload_0
    //   11: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   14: aload 4
    //   16: invokevirtual 41	com/tencent/component/debug/FileTracerConfig:b	(Ljava/io/File;)[Ljava/io/File;
    //   19: astore 5
    //   21: new 43	java/io/File
    //   24: dup
    //   25: aload_3
    //   26: new 45	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 46	java/lang/StringBuilder:<init>	()V
    //   33: aload 4
    //   35: invokevirtual 50	java/io/File:getName	()Ljava/lang/String;
    //   38: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_0
    //   42: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   45: invokevirtual 57	com/tencent/component/debug/FileTracerConfig:j	()Ljava/lang/String;
    //   48: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokespecial 63	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   57: astore 6
    //   59: aload 6
    //   61: invokevirtual 67	java/io/File:exists	()Z
    //   64: ifeq +9 -> 73
    //   67: aload 6
    //   69: invokevirtual 70	java/io/File:delete	()Z
    //   72: pop
    //   73: aload 5
    //   75: ifnonnull +12 -> 87
    //   78: aload 6
    //   80: invokevirtual 73	java/io/File:createNewFile	()Z
    //   83: pop
    //   84: aload 6
    //   86: areturn
    //   87: aload_0
    //   88: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   91: aload 5
    //   93: invokevirtual 76	com/tencent/component/debug/FileTracerConfig:b	([Ljava/io/File;)[Ljava/io/File;
    //   96: pop
    //   97: sipush 8192
    //   100: newarray byte
    //   102: astore 8
    //   104: new 78	java/io/BufferedOutputStream
    //   107: dup
    //   108: new 80	java/io/FileOutputStream
    //   111: dup
    //   112: aload 6
    //   114: iconst_1
    //   115: invokespecial 83	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   118: invokespecial 86	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   121: astore 9
    //   123: aload 5
    //   125: arraylength
    //   126: istore 18
    //   128: iconst_0
    //   129: istore 19
    //   131: aconst_null
    //   132: astore 13
    //   134: iload 19
    //   136: iload 18
    //   138: if_icmpge +96 -> 234
    //   141: aload 5
    //   143: iload 19
    //   145: aaload
    //   146: astore 22
    //   148: aload 13
    //   150: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   153: pop
    //   154: new 93	java/io/BufferedInputStream
    //   157: dup
    //   158: new 95	java/io/FileInputStream
    //   161: dup
    //   162: aload 22
    //   164: invokespecial 98	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   167: invokespecial 101	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   170: astore 11
    //   172: aload 11
    //   174: aload 8
    //   176: iconst_0
    //   177: aload 8
    //   179: arraylength
    //   180: invokevirtual 105	java/io/BufferedInputStream:read	([BII)I
    //   183: istore 24
    //   185: iload 24
    //   187: ifle +37 -> 224
    //   190: aload 9
    //   192: aload 8
    //   194: iconst_0
    //   195: iload 24
    //   197: invokevirtual 109	java/io/BufferedOutputStream:write	([BII)V
    //   200: goto -28 -> 172
    //   203: astore 10
    //   205: aload 10
    //   207: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   210: aload 9
    //   212: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   215: pop
    //   216: aload 11
    //   218: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   221: pop
    //   222: aconst_null
    //   223: areturn
    //   224: iinc 19 1
    //   227: aload 11
    //   229: astore 13
    //   231: goto -97 -> 134
    //   234: aload 9
    //   236: invokevirtual 115	java/io/BufferedOutputStream:flush	()V
    //   239: aload 9
    //   241: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   244: pop
    //   245: aload 13
    //   247: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   250: pop
    //   251: aload 6
    //   253: areturn
    //   254: astore 12
    //   256: aconst_null
    //   257: astore 9
    //   259: aconst_null
    //   260: astore 13
    //   262: aload 9
    //   264: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   267: pop
    //   268: aload 13
    //   270: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   273: pop
    //   274: aload 12
    //   276: athrow
    //   277: astore 25
    //   279: aload 6
    //   281: areturn
    //   282: astore 12
    //   284: aconst_null
    //   285: astore 13
    //   287: goto -25 -> 262
    //   290: astore 12
    //   292: aload 11
    //   294: astore 13
    //   296: goto -34 -> 262
    //   299: astore 12
    //   301: goto -39 -> 262
    //   304: astore 10
    //   306: aconst_null
    //   307: astore 9
    //   309: aconst_null
    //   310: astore 11
    //   312: goto -107 -> 205
    //   315: astore 10
    //   317: aconst_null
    //   318: astore 11
    //   320: goto -115 -> 205
    //   323: astore 10
    //   325: aload 13
    //   327: astore 11
    //   329: goto -124 -> 205
    //
    // Exception table:
    //   from	to	target	type
    //   172	185	203	java/io/IOException
    //   190	200	203	java/io/IOException
    //   104	123	254	finally
    //   78	84	277	java/io/IOException
    //   123	128	282	finally
    //   172	185	290	finally
    //   190	200	290	finally
    //   205	210	290	finally
    //   141	172	299	finally
    //   234	239	299	finally
    //   104	123	304	java/io/IOException
    //   123	128	315	java/io/IOException
    //   141	172	323	java/io/IOException
    //   234	239	323	java/io/IOException
  }

  public FileTracerConfig a()
  {
    return this.c;
  }

  public File a(long paramLong, File paramFile)
  {
    return a(paramLong, paramFile, true);
  }

  public File a(long paramLong, File paramFile, boolean paramBoolean)
  {
    File localFile1 = b(paramLong, paramFile);
    if (localFile1 == null)
      return null;
    if (paramBoolean)
    {
      File localFile2 = new File(localFile1.getAbsolutePath() + ".zip");
      if (FileUtil.b(localFile1, localFile2));
      while (true)
      {
        return localFile2;
        localFile2 = null;
      }
    }
    return localFile1;
  }

  public void a(FileTracerConfig paramFileTracerConfig)
  {
    this.c = paramFileTracerConfig;
  }

  // ERROR //
  @java.lang.Deprecated
  public boolean a(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, FileTracerReader.ReaderCallback paramReaderCallback)
  {
    // Byte code:
    //   0: aload 7
    //   2: ifnonnull +5 -> 7
    //   5: iconst_0
    //   6: ireturn
    //   7: aload_3
    //   8: ifnonnull +9 -> 17
    //   11: sipush 8192
    //   14: newarray byte
    //   16: astore_3
    //   17: iload 6
    //   19: aload_3
    //   20: arraylength
    //   21: if_icmple +6 -> 27
    //   24: aload_3
    //   25: arraylength
    //   26: pop
    //   27: iconst_0
    //   28: istore 8
    //   30: aload_0
    //   31: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   34: lload_1
    //   35: invokevirtual 38	com/tencent/component/debug/FileTracerConfig:a	(J)Ljava/io/File;
    //   38: astore 9
    //   40: aload_0
    //   41: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   44: aload 9
    //   46: invokevirtual 41	com/tencent/component/debug/FileTracerConfig:b	(Ljava/io/File;)[Ljava/io/File;
    //   49: astore 10
    //   51: aload 10
    //   53: ifnonnull +5 -> 58
    //   56: iconst_0
    //   57: ireturn
    //   58: aload_0
    //   59: invokevirtual 33	com/tencent/component/debug/FileTracerReader:a	()Lcom/tencent/component/debug/FileTracerConfig;
    //   62: aload 10
    //   64: invokevirtual 76	com/tencent/component/debug/FileTracerConfig:b	([Ljava/io/File;)[Ljava/io/File;
    //   67: pop
    //   68: aconst_null
    //   69: astore 12
    //   71: iload 4
    //   73: aload 10
    //   75: arraylength
    //   76: if_icmpge +140 -> 216
    //   79: aload 10
    //   81: iload 4
    //   83: aaload
    //   84: astore 18
    //   86: iload 5
    //   88: i2l
    //   89: aload 18
    //   91: invokevirtual 139	java/io/File:length	()J
    //   94: lcmp
    //   95: ifle +17 -> 112
    //   98: iload 5
    //   100: aload 18
    //   102: invokevirtual 139	java/io/File:length	()J
    //   105: l2i
    //   106: isub
    //   107: istore 21
    //   109: goto +148 -> 257
    //   112: aload 12
    //   114: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   117: pop
    //   118: new 93	java/io/BufferedInputStream
    //   121: dup
    //   122: new 95	java/io/FileInputStream
    //   125: dup
    //   126: aload 18
    //   128: invokespecial 98	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   131: invokespecial 101	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   134: astore 20
    //   136: iload 5
    //   138: ifle +112 -> 250
    //   141: iload 5
    //   143: i2l
    //   144: lstore 23
    //   146: aload 20
    //   148: lload 23
    //   150: invokevirtual 143	java/io/BufferedInputStream:skip	(J)J
    //   153: pop2
    //   154: iconst_0
    //   155: istore 21
    //   157: aload 20
    //   159: aload_3
    //   160: iconst_0
    //   161: aload_3
    //   162: arraylength
    //   163: invokevirtual 105	java/io/BufferedInputStream:read	([BII)I
    //   166: istore 22
    //   168: iload 22
    //   170: ifle +36 -> 206
    //   173: aload 7
    //   175: aload_0
    //   176: aload_3
    //   177: iload 22
    //   179: invokeinterface 148 4 0
    //   184: goto -27 -> 157
    //   187: astore 13
    //   189: aload 20
    //   191: astore 12
    //   193: aload 13
    //   195: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   198: aload 12
    //   200: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   203: pop
    //   204: iconst_0
    //   205: ireturn
    //   206: iconst_1
    //   207: istore 8
    //   209: aload 20
    //   211: astore 12
    //   213: goto +44 -> 257
    //   216: aload 12
    //   218: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   221: pop
    //   222: iload 8
    //   224: ireturn
    //   225: astore 14
    //   227: aload 20
    //   229: astore 12
    //   231: aload 12
    //   233: invokestatic 91	com/tencent/component/utils/IOUtils:a	(Ljava/io/Closeable;)Z
    //   236: pop
    //   237: aload 14
    //   239: athrow
    //   240: astore 14
    //   242: goto -11 -> 231
    //   245: astore 13
    //   247: goto -54 -> 193
    //   250: iload 5
    //   252: istore 21
    //   254: goto -97 -> 157
    //   257: iinc 4 1
    //   260: iload 21
    //   262: istore 5
    //   264: goto -193 -> 71
    //
    // Exception table:
    //   from	to	target	type
    //   146	154	187	java/io/IOException
    //   157	168	187	java/io/IOException
    //   173	184	187	java/io/IOException
    //   146	154	225	finally
    //   157	168	225	finally
    //   173	184	225	finally
    //   71	109	240	finally
    //   112	136	240	finally
    //   193	198	240	finally
    //   71	109	245	java/io/IOException
    //   112	136	245	java/io/IOException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.debug.FileTracerReader
 * JD-Core Version:    0.6.0
 */