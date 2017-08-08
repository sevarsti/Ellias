package com.tencent.msdk.tools;

import java.security.MessageDigest;

public class MD5Util
{
  public static byte[] encode(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static String encode2Base64(byte[] paramArrayOfByte)
  {
    return Base64Util.encode(encode(paramArrayOfByte));
  }

  public static String encode2HexStr(byte[] paramArrayOfByte)
  {
    return HexUtil.bytes2HexStr(encode(paramArrayOfByte));
  }

  // ERROR //
  public static byte[] encodeFile(String paramString)
  {
    // Byte code:
    //   0: ldc 14
    //   2: invokestatic 20	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   5: astore_3
    //   6: new 52	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 55	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   14: astore 4
    //   16: sipush 1024
    //   19: newarray byte
    //   21: astore 5
    //   23: aload 4
    //   25: aload 5
    //   27: invokevirtual 59	java/io/FileInputStream:read	([B)I
    //   30: istore 10
    //   32: iload 10
    //   34: iconst_m1
    //   35: if_icmpeq +50 -> 85
    //   38: aload_3
    //   39: aload 5
    //   41: iconst_0
    //   42: iload 10
    //   44: invokevirtual 62	java/security/MessageDigest:update	([BII)V
    //   47: goto -24 -> 23
    //   50: astore 8
    //   52: new 64	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   59: ldc 67
    //   61: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: aload 8
    //   66: invokevirtual 75	java/io/IOException:getMessage	()Ljava/lang/String;
    //   69: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokestatic 83	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   78: aload 4
    //   80: invokevirtual 86	java/io/FileInputStream:close	()V
    //   83: aconst_null
    //   84: areturn
    //   85: aload_3
    //   86: invokevirtual 28	java/security/MessageDigest:digest	()[B
    //   89: astore 11
    //   91: aload 4
    //   93: invokevirtual 86	java/io/FileInputStream:close	()V
    //   96: aload 11
    //   98: areturn
    //   99: astore 12
    //   101: new 64	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   108: ldc 88
    //   110: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: aload 12
    //   115: invokevirtual 75	java/io/IOException:getMessage	()Ljava/lang/String;
    //   118: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: invokestatic 83	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   127: aload 11
    //   129: areturn
    //   130: astore_2
    //   131: new 64	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   138: ldc 90
    //   140: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload_2
    //   144: invokevirtual 91	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   147: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 83	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   156: aconst_null
    //   157: areturn
    //   158: astore 9
    //   160: new 64	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   167: ldc 88
    //   169: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload 9
    //   174: invokevirtual 75	java/io/IOException:getMessage	()Ljava/lang/String;
    //   177: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 83	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   186: aconst_null
    //   187: areturn
    //   188: astore_1
    //   189: new 64	java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   196: ldc 93
    //   198: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: aload_1
    //   202: invokevirtual 94	java/security/NoSuchAlgorithmException:getMessage	()Ljava/lang/String;
    //   205: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: invokestatic 83	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   214: aconst_null
    //   215: areturn
    //   216: astore 6
    //   218: aload 4
    //   220: invokevirtual 86	java/io/FileInputStream:close	()V
    //   223: aload 6
    //   225: athrow
    //   226: astore 7
    //   228: new 64	java/lang/StringBuilder
    //   231: dup
    //   232: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   235: ldc 88
    //   237: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: aload 7
    //   242: invokevirtual 75	java/io/IOException:getMessage	()Ljava/lang/String;
    //   245: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   251: invokestatic 83	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   254: goto -31 -> 223
    //
    // Exception table:
    //   from	to	target	type
    //   23	32	50	java/io/IOException
    //   38	47	50	java/io/IOException
    //   85	91	50	java/io/IOException
    //   91	96	99	java/io/IOException
    //   0	23	130	java/io/FileNotFoundException
    //   78	83	130	java/io/FileNotFoundException
    //   91	96	130	java/io/FileNotFoundException
    //   101	127	130	java/io/FileNotFoundException
    //   160	186	130	java/io/FileNotFoundException
    //   218	223	130	java/io/FileNotFoundException
    //   223	226	130	java/io/FileNotFoundException
    //   228	254	130	java/io/FileNotFoundException
    //   78	83	158	java/io/IOException
    //   0	23	188	java/security/NoSuchAlgorithmException
    //   78	83	188	java/security/NoSuchAlgorithmException
    //   91	96	188	java/security/NoSuchAlgorithmException
    //   101	127	188	java/security/NoSuchAlgorithmException
    //   160	186	188	java/security/NoSuchAlgorithmException
    //   218	223	188	java/security/NoSuchAlgorithmException
    //   223	226	188	java/security/NoSuchAlgorithmException
    //   228	254	188	java/security/NoSuchAlgorithmException
    //   23	32	216	finally
    //   38	47	216	finally
    //   52	78	216	finally
    //   85	91	216	finally
    //   218	223	226	java/io/IOException
  }

  public static String encodeFile2Base64(String paramString)
  {
    byte[] arrayOfByte = encodeFile(paramString);
    if (arrayOfByte == null)
      return null;
    return Base64Util.encode(arrayOfByte);
  }

  public static String encodeFile2HexStr(String paramString)
  {
    return HexUtil.bytes2HexStr(encodeFile(paramString));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.MD5Util
 * JD-Core Version:    0.6.0
 */