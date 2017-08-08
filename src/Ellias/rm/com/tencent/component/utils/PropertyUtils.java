package com.tencent.component.utils;

import android.text.TextUtils;
import java.lang.reflect.Method;

public class PropertyUtils
{
  public static final String a = "net.dns1";
  public static final String b = "net.dns2";
  private static final String c = "getprop";
  private static Class d;
  private static Method e;

  static
  {
    try
    {
      d = Class.forName("android.os.SystemProperties");
      e = d.getDeclaredMethod("get", new Class[] { String.class, String.class });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  public static String a(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1));
    String str;
    do
    {
      return paramString2;
      str = c(paramString1, null);
      if (!TextUtils.isEmpty(str))
        continue;
      str = d(paramString1, null);
    }
    while (TextUtils.isEmpty(str));
    return str;
  }

  public static String b(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return paramString2;
    return c(paramString1, paramString2);
  }

  private static String c(String paramString1, String paramString2)
  {
    if ((d == null) || (e == null))
      return paramString2;
    try
    {
      String str = (String)e.invoke(d, new Object[] { paramString1, paramString2 });
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return paramString2;
  }

  // ERROR //
  private static String d(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: invokestatic 73	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   3: new 75	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   10: ldc 78
    //   12: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_0
    //   16: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokevirtual 90	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   25: astore 5
    //   27: new 92	java/io/BufferedReader
    //   30: dup
    //   31: new 94	java/io/InputStreamReader
    //   34: dup
    //   35: aload 5
    //   37: invokevirtual 100	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   40: invokespecial 103	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   43: invokespecial 106	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   46: astore 6
    //   48: new 75	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   55: astore 7
    //   57: aload 6
    //   59: invokevirtual 109	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   62: astore 12
    //   64: aload 12
    //   66: ifnull +37 -> 103
    //   69: aload 7
    //   71: aload 12
    //   73: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: goto -20 -> 57
    //   80: astore 11
    //   82: aload 6
    //   84: ifnull +108 -> 192
    //   87: aload 6
    //   89: invokevirtual 112	java/io/BufferedReader:close	()V
    //   92: aload_1
    //   93: astore 4
    //   95: aload 5
    //   97: invokevirtual 115	java/lang/Process:destroy	()V
    //   100: aload 4
    //   102: areturn
    //   103: aload 7
    //   105: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: astore 14
    //   110: aload 14
    //   112: invokestatic 55	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   115: istore 15
    //   117: iload 15
    //   119: ifne +6 -> 125
    //   122: aload 14
    //   124: astore_1
    //   125: aload 6
    //   127: ifnull +65 -> 192
    //   130: aload 6
    //   132: invokevirtual 112	java/io/BufferedReader:close	()V
    //   135: aload_1
    //   136: astore 4
    //   138: goto -43 -> 95
    //   141: aload 9
    //   143: ifnull +8 -> 151
    //   146: aload 9
    //   148: invokevirtual 112	java/io/BufferedReader:close	()V
    //   151: aload 10
    //   153: athrow
    //   154: astore_2
    //   155: aload_2
    //   156: astore_3
    //   157: aload_1
    //   158: astore 4
    //   160: aload_3
    //   161: invokevirtual 45	java/lang/Throwable:printStackTrace	()V
    //   164: aload 4
    //   166: areturn
    //   167: astore_3
    //   168: goto -8 -> 160
    //   171: astore 8
    //   173: aload 6
    //   175: astore 9
    //   177: aload 8
    //   179: astore 10
    //   181: goto -40 -> 141
    //   184: astore 16
    //   186: aconst_null
    //   187: astore 6
    //   189: goto -107 -> 82
    //   192: aload_1
    //   193: astore 4
    //   195: goto -100 -> 95
    //   198: astore 10
    //   200: aconst_null
    //   201: astore 9
    //   203: goto -62 -> 141
    //
    // Exception table:
    //   from	to	target	type
    //   48	57	80	java/io/IOException
    //   57	64	80	java/io/IOException
    //   69	77	80	java/io/IOException
    //   103	117	80	java/io/IOException
    //   0	27	154	java/lang/Throwable
    //   87	92	154	java/lang/Throwable
    //   130	135	154	java/lang/Throwable
    //   146	151	154	java/lang/Throwable
    //   151	154	154	java/lang/Throwable
    //   95	100	167	java/lang/Throwable
    //   48	57	171	finally
    //   57	64	171	finally
    //   69	77	171	finally
    //   103	117	171	finally
    //   27	48	184	java/io/IOException
    //   27	48	198	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.PropertyUtils
 * JD-Core Version:    0.6.0
 */