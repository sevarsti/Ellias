package com.tencent.tmassistantsdk.protocol;

public class b
{
  // ERROR //
  public static byte[] a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 10	java/util/zip/Deflater
    //   3: dup
    //   4: invokespecial 14	java/util/zip/Deflater:<init>	()V
    //   7: astore_1
    //   8: new 16	java/io/ByteArrayOutputStream
    //   11: dup
    //   12: aload_0
    //   13: arraylength
    //   14: invokespecial 19	java/io/ByteArrayOutputStream:<init>	(I)V
    //   17: astore_2
    //   18: aload_1
    //   19: bipush 9
    //   21: invokevirtual 22	java/util/zip/Deflater:setLevel	(I)V
    //   24: aload_1
    //   25: aload_0
    //   26: invokevirtual 26	java/util/zip/Deflater:setInput	([B)V
    //   29: aload_1
    //   30: invokevirtual 29	java/util/zip/Deflater:finish	()V
    //   33: sipush 1024
    //   36: newarray byte
    //   38: astore 5
    //   40: aload_1
    //   41: invokevirtual 33	java/util/zip/Deflater:finished	()Z
    //   44: ifne +34 -> 78
    //   47: aload_2
    //   48: aload 5
    //   50: iconst_0
    //   51: aload_1
    //   52: aload 5
    //   54: invokevirtual 37	java/util/zip/Deflater:deflate	([B)I
    //   57: invokevirtual 41	java/io/ByteArrayOutputStream:write	([BII)V
    //   60: goto -20 -> 40
    //   63: astore_3
    //   64: aload_1
    //   65: invokevirtual 44	java/util/zip/Deflater:end	()V
    //   68: aload_2
    //   69: ifnull +7 -> 76
    //   72: aload_2
    //   73: invokevirtual 47	java/io/ByteArrayOutputStream:close	()V
    //   76: aload_3
    //   77: athrow
    //   78: aload_1
    //   79: invokevirtual 44	java/util/zip/Deflater:end	()V
    //   82: aload_2
    //   83: ifnull +7 -> 90
    //   86: aload_2
    //   87: invokevirtual 47	java/io/ByteArrayOutputStream:close	()V
    //   90: aload_2
    //   91: invokevirtual 51	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   94: areturn
    //   95: astore 4
    //   97: aload 4
    //   99: invokevirtual 54	java/io/IOException:printStackTrace	()V
    //   102: goto -26 -> 76
    //   105: astore 6
    //   107: aload 6
    //   109: invokevirtual 54	java/io/IOException:printStackTrace	()V
    //   112: goto -22 -> 90
    //   115: astore_3
    //   116: aconst_null
    //   117: astore_2
    //   118: goto -54 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   18	40	63	finally
    //   40	60	63	finally
    //   72	76	95	java/io/IOException
    //   86	90	105	java/io/IOException
    //   8	18	115	finally
  }

  // ERROR //
  public static byte[] b(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 59	java/util/zip/Inflater
    //   3: dup
    //   4: invokespecial 60	java/util/zip/Inflater:<init>	()V
    //   7: astore_1
    //   8: aconst_null
    //   9: astore_2
    //   10: new 16	java/io/ByteArrayOutputStream
    //   13: dup
    //   14: aload_0
    //   15: arraylength
    //   16: invokespecial 19	java/io/ByteArrayOutputStream:<init>	(I)V
    //   19: astore_3
    //   20: sipush 1024
    //   23: newarray byte
    //   25: astore 9
    //   27: aload_1
    //   28: aload_0
    //   29: invokevirtual 61	java/util/zip/Inflater:setInput	([B)V
    //   32: aload_1
    //   33: invokevirtual 62	java/util/zip/Inflater:finished	()Z
    //   36: ifne +70 -> 106
    //   39: aload_3
    //   40: aload 9
    //   42: iconst_0
    //   43: aload_1
    //   44: aload 9
    //   46: invokevirtual 65	java/util/zip/Inflater:inflate	([B)I
    //   49: invokevirtual 41	java/io/ByteArrayOutputStream:write	([BII)V
    //   52: goto -20 -> 32
    //   55: astore 7
    //   57: aload 7
    //   59: invokevirtual 66	java/util/zip/DataFormatException:printStackTrace	()V
    //   62: aload_1
    //   63: invokevirtual 67	java/util/zip/Inflater:end	()V
    //   66: aload_3
    //   67: ifnull +7 -> 74
    //   70: aload_3
    //   71: invokevirtual 47	java/io/ByteArrayOutputStream:close	()V
    //   74: aload_3
    //   75: invokevirtual 51	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   78: areturn
    //   79: astore 5
    //   81: aload_1
    //   82: invokevirtual 67	java/util/zip/Inflater:end	()V
    //   85: aload_2
    //   86: ifnull +7 -> 93
    //   89: aload_2
    //   90: invokevirtual 47	java/io/ByteArrayOutputStream:close	()V
    //   93: aload 5
    //   95: athrow
    //   96: astore 6
    //   98: goto -5 -> 93
    //   101: astore 8
    //   103: goto -29 -> 74
    //   106: aload_1
    //   107: invokevirtual 67	java/util/zip/Inflater:end	()V
    //   110: aload_3
    //   111: ifnull -37 -> 74
    //   114: aload_3
    //   115: invokevirtual 47	java/io/ByteArrayOutputStream:close	()V
    //   118: goto -44 -> 74
    //   121: astore 4
    //   123: aload_3
    //   124: astore_2
    //   125: aload 4
    //   127: astore 5
    //   129: goto -48 -> 81
    //   132: astore 10
    //   134: aload 10
    //   136: astore 7
    //   138: aconst_null
    //   139: astore_3
    //   140: goto -83 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   20	32	55	java/util/zip/DataFormatException
    //   32	52	55	java/util/zip/DataFormatException
    //   10	20	79	finally
    //   89	93	96	java/io/IOException
    //   70	74	101	java/io/IOException
    //   114	118	101	java/io/IOException
    //   20	32	121	finally
    //   32	52	121	finally
    //   57	62	121	finally
    //   10	20	132	java/util/zip/DataFormatException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.b
 * JD-Core Version:    0.6.0
 */