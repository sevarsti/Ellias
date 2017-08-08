package com.tencent.component.plugin.server;

import android.content.Context;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.UniqueLock;
import java.io.File;

class f
{
  private static final String a = "PluginLoader";
  private final Context b;
  private final c c;
  private final File d;
  private final g e;
  private final UniqueLock f = new UniqueLock();

  public f(c paramc)
  {
    this.c = paramc;
    this.b = paramc.a();
    this.d = PluginConstant.b(paramc);
    this.e = paramc.f();
  }

  private static File a(File[] paramArrayOfFile)
  {
    Object localObject = null;
    if (paramArrayOfFile == null)
      return localObject;
    int i = paramArrayOfFile.length;
    int j = 0;
    label13: File localFile;
    if (j < i)
    {
      localFile = paramArrayOfFile[j];
      if (localFile != null)
        break label34;
    }
    while (true)
    {
      j++;
      break label13;
      break;
      label34: if ((localObject != null) && (localObject.lastModified() >= localFile.lastModified()))
        continue;
      localObject = localFile;
    }
  }

  // ERROR //
  private void a(File paramFile)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 62	com/tencent/component/plugin/server/f:d	(Ljava/io/File;)Z
    //   4: ifne +100 -> 104
    //   7: new 64	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   14: ldc 67
    //   16: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_1
    //   20: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   23: astore 17
    //   25: aload_1
    //   26: ifnull +71 -> 97
    //   29: new 64	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   36: ldc 76
    //   38: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 80	java/io/File:exists	()Z
    //   45: invokevirtual 83	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   48: ldc 85
    //   50: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 88	java/io/File:isFile	()Z
    //   57: invokevirtual 83	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   60: ldc 90
    //   62: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: aload_1
    //   66: invokevirtual 93	java/io/File:length	()J
    //   69: invokevirtual 96	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   72: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: astore 18
    //   77: ldc 102
    //   79: iconst_0
    //   80: ldc 104
    //   82: aload 17
    //   84: aload 18
    //   86: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: aconst_null
    //   93: invokestatic 109	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   96: return
    //   97: ldc 111
    //   99: astore 18
    //   101: goto -24 -> 77
    //   104: aload_1
    //   105: invokevirtual 114	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   108: invokestatic 119	com/tencent/component/plugin/PluginFileLock:a	(Ljava/lang/String;)Ljava/util/concurrent/locks/Lock;
    //   111: astore_2
    //   112: aload_2
    //   113: invokeinterface 124 1 0
    //   118: aload_0
    //   119: getfield 37	com/tencent/component/plugin/server/f:b	Landroid/content/Context;
    //   122: aload_1
    //   123: invokevirtual 114	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   126: iconst_1
    //   127: invokestatic 129	com/tencent/component/plugin/server/PluginParser:a	(Landroid/content/Context;Ljava/lang/String;I)Lcom/tencent/component/plugin/PluginInfo;
    //   130: astore 4
    //   132: aload_2
    //   133: invokeinterface 132 1 0
    //   138: aload_0
    //   139: getfield 37	com/tencent/component/plugin/server/f:b	Landroid/content/Context;
    //   142: invokestatic 137	com/tencent/component/plugin/server/PluginValidator:a	(Landroid/content/Context;)Lcom/tencent/component/plugin/server/PluginValidator;
    //   145: aload 4
    //   147: aload_0
    //   148: getfield 30	com/tencent/component/plugin/server/f:c	Lcom/tencent/component/plugin/server/c;
    //   151: invokevirtual 140	com/tencent/component/plugin/server/PluginValidator:a	(Lcom/tencent/component/plugin/PluginInfo;Lcom/tencent/component/plugin/server/c;)V
    //   154: aload_0
    //   155: getfield 28	com/tencent/component/plugin/server/f:f	Lcom/tencent/component/utils/UniqueLock;
    //   158: aload 4
    //   160: getfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   163: invokevirtual 148	com/tencent/component/utils/UniqueLock:a	(Ljava/lang/Object;)Ljava/util/concurrent/locks/Lock;
    //   166: astore 6
    //   168: aload 6
    //   170: invokeinterface 124 1 0
    //   175: aload_0
    //   176: getfield 49	com/tencent/component/plugin/server/f:e	Lcom/tencent/component/plugin/server/g;
    //   179: aload 4
    //   181: getfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   184: invokevirtual 153	com/tencent/component/plugin/server/g:b	(Ljava/lang/String;)Z
    //   187: istore 8
    //   189: iload 8
    //   191: ifeq +76 -> 267
    //   194: aload 6
    //   196: invokeinterface 132 1 0
    //   201: return
    //   202: astore_3
    //   203: aload_2
    //   204: invokeinterface 132 1 0
    //   209: aload_3
    //   210: athrow
    //   211: astore 5
    //   213: aload_1
    //   214: invokestatic 155	com/tencent/component/plugin/server/f:b	(Ljava/io/File;)V
    //   217: ldc 102
    //   219: iconst_0
    //   220: ldc 157
    //   222: new 64	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   229: ldc 159
    //   231: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: aload 4
    //   236: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   239: ldc 161
    //   241: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: aload_1
    //   245: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   248: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   251: aload 5
    //   253: invokestatic 109	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   256: ldc 8
    //   258: aload 5
    //   260: invokevirtual 164	com/tencent/component/plugin/server/PluginValidator$ValidateException:getMessage	()Ljava/lang/String;
    //   263: invokestatic 170	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   266: return
    //   267: aload_1
    //   268: aload 4
    //   270: invokestatic 173	com/tencent/component/plugin/server/f:a	(Ljava/io/File;Lcom/tencent/component/plugin/PluginInfo;)Z
    //   273: ifne +73 -> 346
    //   276: aload_1
    //   277: invokestatic 155	com/tencent/component/plugin/server/f:b	(Ljava/io/File;)V
    //   280: ldc 102
    //   282: iconst_0
    //   283: ldc 175
    //   285: new 64	java/lang/StringBuilder
    //   288: dup
    //   289: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   292: ldc 67
    //   294: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: aload_1
    //   298: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   301: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: aconst_null
    //   305: invokestatic 109	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   308: ldc 8
    //   310: new 64	java/lang/StringBuilder
    //   313: dup
    //   314: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   317: ldc 177
    //   319: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: aload 4
    //   324: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   327: ldc 179
    //   329: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: invokestatic 170	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   338: aload 6
    //   340: invokeinterface 132 1 0
    //   345: return
    //   346: aload_0
    //   347: getfield 30	com/tencent/component/plugin/server/f:c	Lcom/tencent/component/plugin/server/c;
    //   350: invokevirtual 182	com/tencent/component/plugin/server/c:c	()Lcom/tencent/component/plugin/server/BuiltinPluginLoader;
    //   353: astore 9
    //   355: aload_0
    //   356: getfield 30	com/tencent/component/plugin/server/f:c	Lcom/tencent/component/plugin/server/c;
    //   359: invokevirtual 185	com/tencent/component/plugin/server/c:e	()Lcom/tencent/component/plugin/server/e;
    //   362: astore 10
    //   364: aload 9
    //   366: aload 4
    //   368: invokevirtual 190	com/tencent/component/plugin/server/BuiltinPluginLoader:a	(Lcom/tencent/component/plugin/PluginInfo;)Z
    //   371: ifeq +29 -> 400
    //   374: aload 10
    //   376: aload 4
    //   378: invokevirtual 193	com/tencent/component/plugin/server/e:a	(Lcom/tencent/component/plugin/PluginInfo;)Z
    //   381: pop
    //   382: aload 9
    //   384: aload 4
    //   386: getfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   389: invokevirtual 196	com/tencent/component/plugin/server/BuiltinPluginLoader:a	(Ljava/lang/String;)V
    //   392: aload 6
    //   394: invokeinterface 132 1 0
    //   399: return
    //   400: aload 4
    //   402: getfield 199	com/tencent/component/plugin/PluginInfo:nativeLibraryDir	Ljava/lang/String;
    //   405: astore 11
    //   407: aload 11
    //   409: ifnull +160 -> 569
    //   412: aload_1
    //   413: invokevirtual 114	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   416: aload 11
    //   418: invokestatic 204	com/tencent/component/plugin/PluginNativeHelper:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   421: ifne +148 -> 569
    //   424: new 142	com/tencent/component/plugin/PluginInfo
    //   427: dup
    //   428: invokespecial 205	com/tencent/component/plugin/PluginInfo:<init>	()V
    //   431: astore 12
    //   433: aload 12
    //   435: aload 4
    //   437: getfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   440: putfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   443: aload 4
    //   445: getfield 209	com/tencent/component/plugin/PluginInfo:j	Landroid/net/Uri;
    //   448: ifnull +113 -> 561
    //   451: aload 4
    //   453: getfield 209	com/tencent/component/plugin/PluginInfo:j	Landroid/net/Uri;
    //   456: astore 13
    //   458: aload 12
    //   460: aload 13
    //   462: putfield 209	com/tencent/component/plugin/PluginInfo:j	Landroid/net/Uri;
    //   465: aload 12
    //   467: aload 4
    //   469: getfield 213	com/tencent/component/plugin/PluginInfo:version	I
    //   472: putfield 213	com/tencent/component/plugin/PluginInfo:version	I
    //   475: aload_0
    //   476: getfield 49	com/tencent/component/plugin/server/f:e	Lcom/tencent/component/plugin/server/g;
    //   479: aload 12
    //   481: getfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   484: aload 12
    //   486: invokevirtual 216	com/tencent/component/plugin/server/g:a	(Ljava/lang/String;Lcom/tencent/component/plugin/PluginInfo;)Z
    //   489: pop
    //   490: ldc 102
    //   492: iconst_0
    //   493: ldc 218
    //   495: new 64	java/lang/StringBuilder
    //   498: dup
    //   499: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   502: ldc 159
    //   504: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: aload 4
    //   509: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   512: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   515: aconst_null
    //   516: invokestatic 109	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   519: ldc 8
    //   521: new 64	java/lang/StringBuilder
    //   524: dup
    //   525: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   528: ldc 220
    //   530: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: aload 4
    //   535: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   538: ldc 222
    //   540: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: aload_1
    //   544: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   547: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   550: invokestatic 170	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   553: aload 6
    //   555: invokeinterface 132 1 0
    //   560: return
    //   561: getstatic 225	com/tencent/component/plugin/server/PluginConstant:G	Landroid/net/Uri;
    //   564: astore 13
    //   566: goto -108 -> 458
    //   569: aload_0
    //   570: getfield 49	com/tencent/component/plugin/server/f:e	Lcom/tencent/component/plugin/server/g;
    //   573: aload 4
    //   575: getfield 145	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   578: aload 4
    //   580: invokevirtual 216	com/tencent/component/plugin/server/g:a	(Ljava/lang/String;Lcom/tencent/component/plugin/PluginInfo;)Z
    //   583: pop
    //   584: ldc 102
    //   586: iconst_1
    //   587: ldc 227
    //   589: new 64	java/lang/StringBuilder
    //   592: dup
    //   593: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   596: ldc 159
    //   598: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: aload 4
    //   603: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   606: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   609: aconst_null
    //   610: invokestatic 109	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   613: ldc 8
    //   615: new 64	java/lang/StringBuilder
    //   618: dup
    //   619: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   622: ldc 229
    //   624: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: aload 4
    //   629: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   632: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   635: invokestatic 170	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   638: aload 6
    //   640: invokeinterface 132 1 0
    //   645: return
    //   646: astore 7
    //   648: aload 6
    //   650: invokeinterface 132 1 0
    //   655: aload 7
    //   657: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   118	132	202	finally
    //   138	154	211	com/tencent/component/plugin/server/PluginValidator$ValidateException
    //   175	189	646	finally
    //   267	338	646	finally
    //   346	392	646	finally
    //   400	407	646	finally
    //   412	458	646	finally
    //   458	553	646	finally
    //   561	566	646	finally
    //   569	638	646	finally
  }

  private static boolean a(File paramFile, PluginInfo paramPluginInfo)
  {
    if ((paramFile == null) || (paramPluginInfo == null));
    String str;
    do
    {
      return false;
      str = PluginConstant.a(paramPluginInfo);
    }
    while ((str == null) || (!str.equals(paramFile.getName())));
    return true;
  }

  private static void b(File paramFile)
  {
    if (paramFile == null)
      return;
    FileUtil.a(paramFile);
  }

  private static boolean c(File paramFile)
  {
    return (paramFile != null) && (paramFile.isDirectory()) && (paramFile.exists());
  }

  private static boolean d(File paramFile)
  {
    return (paramFile != null) && (paramFile.isFile()) && (paramFile.length() > 0L);
  }

  final void a()
  {
    File localFile = this.d;
    if (!c(localFile));
    while (true)
    {
      return;
      File[] arrayOfFile = localFile.listFiles();
      if (arrayOfFile == null)
        continue;
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
        a(arrayOfFile[j]);
    }
  }

  final void a(String paramString)
  {
    File localFile1 = this.d;
    if (!c(localFile1));
    File localFile2;
    do
    {
      File[] arrayOfFile;
      do
      {
        return;
        arrayOfFile = localFile1.listFiles(PluginConstant.a(paramString));
      }
      while (arrayOfFile == null);
      localFile2 = a(arrayOfFile);
    }
    while ((!d(localFile2)) || (this.e.b(paramString)));
    a(localFile2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.f
 * JD-Core Version:    0.6.0
 */