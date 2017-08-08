package com.tencent.component.plugin;

import android.text.TextUtils;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.NativeLibraryHelper;
import com.tencent.component.utils.UniqueLock;
import java.io.File;
import java.util.concurrent.locks.Lock;

public class PluginNativeHelper
{
  private static final String a = "PluginNativeHelper";
  private static final String b = ".checksum";
  private static final int c = 128;
  private static final int d = 8192;
  private static final int e = 4;
  private static final long f = 20480L;
  private static final UniqueLock g = new UniqueLock();

  public static boolean a(File paramFile)
  {
    return NativeLibraryHelper.a(paramFile);
  }

  public static boolean a(File paramFile1, File paramFile2)
  {
    if ((!j(paramFile1)) || (paramFile2 == null))
      return false;
    Lock localLock = g.a(paramFile2.getAbsolutePath());
    localLock.lock();
    try
    {
      String str1 = c(paramFile1);
      String str2 = b(paramFile2);
      if (str1 != null)
      {
        boolean bool1 = str1.equals(str2);
        if (bool1)
          return true;
      }
      h(paramFile2);
      boolean bool2 = NativeLibraryHelper.a(paramFile1, paramFile2);
      if (bool2)
        a(paramFile2, str1);
      return bool2;
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject;
  }

  private static boolean a(File paramFile, String paramString)
  {
    if (paramFile == null)
      return false;
    return b(new File(paramFile, ".checksum"), paramString);
  }

  public static boolean a(String paramString)
  {
    return a(new File(paramString));
  }

  public static boolean a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    return a(new File(paramString1), new File(paramString2));
  }

  private static String b(File paramFile)
  {
    if (paramFile == null);
    File localFile;
    do
    {
      return null;
      localFile = new File(paramFile, ".checksum");
    }
    while (!i(localFile));
    String str = d(localFile);
    if (!TextUtils.isEmpty(str));
    while (true)
    {
      return str;
      str = null;
    }
  }

  // ERROR //
  private static boolean b(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +6 -> 7
    //   4: ldc 106
    //   6: astore_1
    //   7: aload_0
    //   8: invokevirtual 110	java/io/File:getParentFile	()Ljava/io/File;
    //   11: invokestatic 74	com/tencent/component/plugin/PluginNativeHelper:h	(Ljava/io/File;)Z
    //   14: pop
    //   15: new 112	java/io/FileWriter
    //   18: dup
    //   19: aload_0
    //   20: invokespecial 115	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   23: astore_3
    //   24: aload_3
    //   25: aload_1
    //   26: invokevirtual 120	java/io/Writer:write	(Ljava/lang/String;)V
    //   29: iconst_1
    //   30: istore 6
    //   32: aload_3
    //   33: ifnull +7 -> 40
    //   36: aload_3
    //   37: invokevirtual 123	java/io/Writer:close	()V
    //   40: iload 6
    //   42: ireturn
    //   43: astore 5
    //   45: aconst_null
    //   46: astore_3
    //   47: ldc 8
    //   49: ldc 125
    //   51: aload 5
    //   53: invokestatic 130	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   56: iconst_0
    //   57: istore 6
    //   59: aload_3
    //   60: ifnull -20 -> 40
    //   63: aload_3
    //   64: invokevirtual 123	java/io/Writer:close	()V
    //   67: iconst_0
    //   68: ireturn
    //   69: astore 7
    //   71: iconst_0
    //   72: ireturn
    //   73: astore_2
    //   74: aconst_null
    //   75: astore_3
    //   76: aload_3
    //   77: ifnull +7 -> 84
    //   80: aload_3
    //   81: invokevirtual 123	java/io/Writer:close	()V
    //   84: aload_2
    //   85: athrow
    //   86: astore 9
    //   88: iload 6
    //   90: ireturn
    //   91: astore 4
    //   93: goto -9 -> 84
    //   96: astore_2
    //   97: goto -21 -> 76
    //   100: astore 5
    //   102: goto -55 -> 47
    //
    // Exception table:
    //   from	to	target	type
    //   7	24	43	java/io/IOException
    //   63	67	69	java/io/IOException
    //   7	24	73	finally
    //   36	40	86	java/io/IOException
    //   80	84	91	java/io/IOException
    //   24	29	96	finally
    //   47	56	96	finally
    //   24	29	100	java/io/IOException
  }

  private static String c(File paramFile)
  {
    if (paramFile == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramFile.lastModified());
    localStringBuilder.append(paramFile.length());
    Long localLong = e(paramFile);
    if (localLong != null)
      localStringBuilder.append(localLong);
    return localStringBuilder.toString();
  }

  // ERROR //
  private static String d(File paramFile)
  {
    // Byte code:
    //   0: new 157	java/io/FileReader
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 158	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   8: astore_1
    //   9: sipush 128
    //   12: newarray char
    //   14: astore 9
    //   16: new 132	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   23: astore 10
    //   25: aload_1
    //   26: aload 9
    //   28: invokevirtual 164	java/io/Reader:read	([C)I
    //   31: istore 11
    //   33: iload 11
    //   35: ifle +42 -> 77
    //   38: aload 10
    //   40: aload 9
    //   42: iconst_0
    //   43: iload 11
    //   45: invokevirtual 167	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: goto -24 -> 25
    //   52: astore 7
    //   54: ldc 8
    //   56: ldc 169
    //   58: aload 7
    //   60: invokestatic 130	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   63: aconst_null
    //   64: astore 5
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 170	java/io/Reader:close	()V
    //   74: aload 5
    //   76: areturn
    //   77: aload 10
    //   79: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore 13
    //   84: aload 13
    //   86: astore 5
    //   88: aload_1
    //   89: ifnull -15 -> 74
    //   92: aload_1
    //   93: invokevirtual 170	java/io/Reader:close	()V
    //   96: aload 5
    //   98: areturn
    //   99: astore 14
    //   101: aload 5
    //   103: areturn
    //   104: astore_2
    //   105: aconst_null
    //   106: astore_1
    //   107: ldc 8
    //   109: ldc 172
    //   111: aload_2
    //   112: invokestatic 130	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   115: aconst_null
    //   116: astore 5
    //   118: aload_1
    //   119: ifnull -45 -> 74
    //   122: aload_1
    //   123: invokevirtual 170	java/io/Reader:close	()V
    //   126: aconst_null
    //   127: areturn
    //   128: astore 6
    //   130: aconst_null
    //   131: areturn
    //   132: astore 15
    //   134: aconst_null
    //   135: astore_1
    //   136: aload 15
    //   138: astore_3
    //   139: aload_1
    //   140: ifnull +7 -> 147
    //   143: aload_1
    //   144: invokevirtual 170	java/io/Reader:close	()V
    //   147: aload_3
    //   148: athrow
    //   149: astore 8
    //   151: aconst_null
    //   152: areturn
    //   153: astore 4
    //   155: goto -8 -> 147
    //   158: astore_3
    //   159: goto -20 -> 139
    //   162: astore_2
    //   163: goto -56 -> 107
    //   166: astore 7
    //   168: aconst_null
    //   169: astore_1
    //   170: goto -116 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   9	25	52	java/io/FileNotFoundException
    //   25	33	52	java/io/FileNotFoundException
    //   38	49	52	java/io/FileNotFoundException
    //   77	84	52	java/io/FileNotFoundException
    //   92	96	99	java/io/IOException
    //   0	9	104	java/io/IOException
    //   122	126	128	java/io/IOException
    //   0	9	132	finally
    //   70	74	149	java/io/IOException
    //   143	147	153	java/io/IOException
    //   9	25	158	finally
    //   25	33	158	finally
    //   38	49	158	finally
    //   54	63	158	finally
    //   77	84	158	finally
    //   107	115	158	finally
    //   9	25	162	java/io/IOException
    //   25	33	162	java/io/IOException
    //   38	49	162	java/io/IOException
    //   77	84	162	java/io/IOException
    //   0	9	166	java/io/FileNotFoundException
  }

  // ERROR //
  private static Long e(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 175	com/tencent/component/plugin/PluginNativeHelper:f	(Ljava/io/File;)J
    //   4: lstore 10
    //   6: aload_0
    //   7: invokestatic 177	com/tencent/component/plugin/PluginNativeHelper:g	(Ljava/io/File;)J
    //   10: lstore 12
    //   12: ldc2_w 178
    //   15: aload_0
    //   16: invokevirtual 144	java/io/File:length	()J
    //   19: lcmp
    //   20: ifgt +118 -> 138
    //   23: sipush 8192
    //   26: istore 14
    //   28: new 181	java/io/FileInputStream
    //   31: dup
    //   32: aload_0
    //   33: invokespecial 182	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   36: astore_2
    //   37: new 184	java/util/zip/CRC32
    //   40: dup
    //   41: invokespecial 185	java/util/zip/CRC32:<init>	()V
    //   44: astore 15
    //   46: iload 14
    //   48: newarray byte
    //   50: astore 16
    //   52: iconst_0
    //   53: istore 17
    //   55: aload_2
    //   56: aload 16
    //   58: invokevirtual 188	java/io/FileInputStream:read	([B)I
    //   61: istore 18
    //   63: iload 18
    //   65: ifle +48 -> 113
    //   68: aload 15
    //   70: aload 16
    //   72: iconst_0
    //   73: iload 18
    //   75: invokevirtual 192	java/util/zip/CRC32:update	([BII)V
    //   78: iload 17
    //   80: iload 18
    //   82: iadd
    //   83: istore 17
    //   85: iload 17
    //   87: i2l
    //   88: lload 10
    //   90: lcmp
    //   91: iflt -36 -> 55
    //   94: lload 12
    //   96: lconst_0
    //   97: lcmp
    //   98: ifle +187 -> 285
    //   101: aload_2
    //   102: lload 12
    //   104: invokevirtual 196	java/io/FileInputStream:skip	(J)J
    //   107: lload 12
    //   109: lcmp
    //   110: ifeq +175 -> 285
    //   113: aload 15
    //   115: invokevirtual 199	java/util/zip/CRC32:getValue	()J
    //   118: invokestatic 205	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   121: astore 19
    //   123: aload 19
    //   125: astore 6
    //   127: aload_2
    //   128: ifnull +7 -> 135
    //   131: aload_2
    //   132: invokevirtual 206	java/io/FileInputStream:close	()V
    //   135: aload 6
    //   137: areturn
    //   138: aload_0
    //   139: invokevirtual 144	java/io/File:length	()J
    //   142: lstore 21
    //   144: lload 21
    //   146: l2i
    //   147: istore 14
    //   149: goto -121 -> 28
    //   152: astore 8
    //   154: aconst_null
    //   155: astore_2
    //   156: ldc 8
    //   158: new 132	java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   165: ldc 208
    //   167: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_0
    //   171: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: aload 8
    //   179: invokestatic 213	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   182: aconst_null
    //   183: astore 6
    //   185: aload_2
    //   186: ifnull -51 -> 135
    //   189: aload_2
    //   190: invokevirtual 206	java/io/FileInputStream:close	()V
    //   193: aconst_null
    //   194: areturn
    //   195: astore 9
    //   197: aconst_null
    //   198: areturn
    //   199: astore 5
    //   201: aconst_null
    //   202: astore_2
    //   203: ldc 8
    //   205: new 132	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   212: ldc 215
    //   214: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: aload_0
    //   218: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: aload 5
    //   226: invokestatic 213	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   229: aconst_null
    //   230: astore 6
    //   232: aload_2
    //   233: ifnull -98 -> 135
    //   236: aload_2
    //   237: invokevirtual 206	java/io/FileInputStream:close	()V
    //   240: aconst_null
    //   241: areturn
    //   242: astore 7
    //   244: aconst_null
    //   245: areturn
    //   246: astore_1
    //   247: aconst_null
    //   248: astore_2
    //   249: aload_1
    //   250: astore_3
    //   251: aload_2
    //   252: ifnull +7 -> 259
    //   255: aload_2
    //   256: invokevirtual 206	java/io/FileInputStream:close	()V
    //   259: aload_3
    //   260: athrow
    //   261: astore 20
    //   263: aload 6
    //   265: areturn
    //   266: astore 4
    //   268: goto -9 -> 259
    //   271: astore_3
    //   272: goto -21 -> 251
    //   275: astore 5
    //   277: goto -74 -> 203
    //   280: astore 8
    //   282: goto -126 -> 156
    //   285: iconst_0
    //   286: istore 17
    //   288: goto -233 -> 55
    //
    // Exception table:
    //   from	to	target	type
    //   0	23	152	java/io/FileNotFoundException
    //   28	37	152	java/io/FileNotFoundException
    //   138	144	152	java/io/FileNotFoundException
    //   189	193	195	java/io/IOException
    //   0	23	199	java/io/IOException
    //   28	37	199	java/io/IOException
    //   138	144	199	java/io/IOException
    //   236	240	242	java/io/IOException
    //   0	23	246	finally
    //   28	37	246	finally
    //   138	144	246	finally
    //   131	135	261	java/io/IOException
    //   255	259	266	java/io/IOException
    //   37	52	271	finally
    //   55	63	271	finally
    //   68	78	271	finally
    //   101	113	271	finally
    //   113	123	271	finally
    //   156	182	271	finally
    //   203	229	271	finally
    //   37	52	275	java/io/IOException
    //   55	63	275	java/io/IOException
    //   68	78	275	java/io/IOException
    //   101	113	275	java/io/IOException
    //   113	123	275	java/io/IOException
    //   37	52	280	java/io/FileNotFoundException
    //   55	63	280	java/io/FileNotFoundException
    //   68	78	280	java/io/FileNotFoundException
    //   101	113	280	java/io/FileNotFoundException
    //   113	123	280	java/io/FileNotFoundException
  }

  private static long f(File paramFile)
  {
    return 20480L;
  }

  private static long g(File paramFile)
  {
    long l = paramFile.length() - 20480L * 4;
    if (l > 0L)
      return l / 3;
    return 0L;
  }

  private static boolean h(File paramFile)
  {
    if (paramFile == null)
      return false;
    if ((paramFile.exists()) && (paramFile.isDirectory()))
      return true;
    FileUtil.a(paramFile);
    return paramFile.mkdirs();
  }

  private static boolean i(File paramFile)
  {
    return j(paramFile);
  }

  private static boolean j(File paramFile)
  {
    return (paramFile != null) && (paramFile.exists()) && (paramFile.isFile()) && (paramFile.length() > 0L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginNativeHelper
 * JD-Core Version:    0.6.0
 */