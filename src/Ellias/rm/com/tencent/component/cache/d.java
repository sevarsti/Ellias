package com.tencent.component.cache;

import android.os.Environment;
import java.io.File;

class d
{
  private static final String a = "InnerEnvironment";
  private static final String b = "-ext";
  private static final File c = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");

  public static File a()
  {
    return c;
  }

  // ERROR //
  public static File a(android.content.Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload_2
    //   1: ifne +17 -> 18
    //   4: invokestatic 44	com/tencent/component/utils/PlatformUtil:version	()I
    //   7: bipush 8
    //   9: if_icmplt +9 -> 18
    //   12: aload_0
    //   13: aload_1
    //   14: invokevirtual 50	android/content/Context:getExternalFilesDir	(Ljava/lang/String;)Ljava/io/File;
    //   17: areturn
    //   18: ldc 2
    //   20: monitorenter
    //   21: new 52	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   28: aload_0
    //   29: invokevirtual 57	android/content/Context:getPackageName	()Ljava/lang/String;
    //   32: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: astore 4
    //   37: iload_2
    //   38: ifeq +77 -> 115
    //   41: ldc 11
    //   43: astore 5
    //   45: aload 4
    //   47: aload 5
    //   49: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokestatic 66	com/tencent/component/cache/d:b	(Ljava/lang/String;)Ljava/io/File;
    //   58: astore 6
    //   60: aload 6
    //   62: invokevirtual 70	java/io/File:exists	()Z
    //   65: istore 7
    //   67: iload 7
    //   69: ifne +53 -> 122
    //   72: new 17	java/io/File
    //   75: dup
    //   76: invokestatic 72	com/tencent/component/cache/d:a	()Ljava/io/File;
    //   79: ldc 74
    //   81: invokespecial 29	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   84: invokevirtual 77	java/io/File:createNewFile	()Z
    //   87: pop
    //   88: aload 6
    //   90: invokevirtual 80	java/io/File:mkdirs	()Z
    //   93: ifne +29 -> 122
    //   96: ldc 8
    //   98: ldc 82
    //   100: invokestatic 88	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   103: pop
    //   104: ldc 2
    //   106: monitorexit
    //   107: aconst_null
    //   108: areturn
    //   109: astore_3
    //   110: ldc 2
    //   112: monitorexit
    //   113: aload_3
    //   114: athrow
    //   115: ldc 90
    //   117: astore 5
    //   119: goto -74 -> 45
    //   122: aload_1
    //   123: ifnonnull +9 -> 132
    //   126: ldc 2
    //   128: monitorexit
    //   129: aload 6
    //   131: areturn
    //   132: new 17	java/io/File
    //   135: dup
    //   136: aload 6
    //   138: aload_1
    //   139: invokespecial 29	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   142: astore 8
    //   144: aload 8
    //   146: invokevirtual 70	java/io/File:exists	()Z
    //   149: ifne +42 -> 191
    //   152: aload 8
    //   154: invokevirtual 80	java/io/File:mkdirs	()Z
    //   157: ifne +34 -> 191
    //   160: ldc 8
    //   162: new 52	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   169: ldc 92
    //   171: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload 8
    //   176: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokestatic 88	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   185: pop
    //   186: ldc 2
    //   188: monitorexit
    //   189: aconst_null
    //   190: areturn
    //   191: ldc 2
    //   193: monitorexit
    //   194: aload 8
    //   196: areturn
    //   197: astore 10
    //   199: goto -111 -> 88
    //
    // Exception table:
    //   from	to	target	type
    //   21	37	109	finally
    //   45	67	109	finally
    //   72	88	109	finally
    //   88	107	109	finally
    //   110	113	109	finally
    //   126	129	109	finally
    //   132	189	109	finally
    //   191	194	109	finally
    //   72	88	197	java/io/IOException
  }

  // ERROR //
  public static File a(android.content.Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload_1
    //   1: ifne +16 -> 17
    //   4: invokestatic 44	com/tencent/component/utils/PlatformUtil:version	()I
    //   7: bipush 8
    //   9: if_icmplt +8 -> 17
    //   12: aload_0
    //   13: invokevirtual 99	android/content/Context:getExternalCacheDir	()Ljava/io/File;
    //   16: areturn
    //   17: ldc 2
    //   19: monitorenter
    //   20: new 52	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   27: aload_0
    //   28: invokevirtual 57	android/content/Context:getPackageName	()Ljava/lang/String;
    //   31: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: astore_3
    //   35: iload_1
    //   36: ifeq +76 -> 112
    //   39: ldc 11
    //   41: astore 4
    //   43: aload_3
    //   44: aload 4
    //   46: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokestatic 101	com/tencent/component/cache/d:a	(Ljava/lang/String;)Ljava/io/File;
    //   55: astore 5
    //   57: aload 5
    //   59: invokevirtual 70	java/io/File:exists	()Z
    //   62: istore 6
    //   64: iload 6
    //   66: ifne +63 -> 129
    //   69: new 17	java/io/File
    //   72: dup
    //   73: invokestatic 72	com/tencent/component/cache/d:a	()Ljava/io/File;
    //   76: ldc 74
    //   78: invokespecial 29	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   81: invokevirtual 77	java/io/File:createNewFile	()Z
    //   84: pop
    //   85: aload 5
    //   87: invokevirtual 80	java/io/File:mkdirs	()Z
    //   90: ifne +39 -> 129
    //   93: ldc 8
    //   95: ldc 103
    //   97: invokestatic 88	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   100: pop
    //   101: ldc 2
    //   103: monitorexit
    //   104: aconst_null
    //   105: areturn
    //   106: astore_2
    //   107: ldc 2
    //   109: monitorexit
    //   110: aload_2
    //   111: athrow
    //   112: ldc 90
    //   114: astore 4
    //   116: goto -73 -> 43
    //   119: astore 7
    //   121: aload 7
    //   123: invokevirtual 106	java/io/IOException:printStackTrace	()V
    //   126: goto -41 -> 85
    //   129: ldc 2
    //   131: monitorexit
    //   132: aload 5
    //   134: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   20	35	106	finally
    //   43	64	106	finally
    //   69	85	106	finally
    //   85	104	106	finally
    //   107	110	106	finally
    //   121	126	106	finally
    //   129	132	106	finally
    //   69	85	119	java/io/IOException
  }

  public static File a(String paramString)
  {
    return new File(new File(c, paramString), "cache");
  }

  public static File b(String paramString)
  {
    return new File(new File(c, paramString), "files");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.d
 * JD-Core Version:    0.6.0
 */