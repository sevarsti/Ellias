package com.tencent.component.plugin.server;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.plugin.PluginFileLock;
import com.tencent.component.plugin.PluginInfo;
import com.tencent.component.plugin.PluginNativeHelper;
import com.tencent.component.plugin.PluginReporter;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.FileUtil;
import com.tencent.component.utils.UniqueLock;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

class e
{
  public static int a = 0;
  public static int b = 0;
  public static int c = 0;
  public static int d = 0;
  public static int e = 0;
  public static int f = 0;
  public static int g = 0;
  public static int h = 0;
  private static final String i = "PluginInstaller";
  private final Context j;
  private final g k;
  private final c l;
  private final File m;
  private final File n;
  private final File o;
  private final UniqueLock p = new UniqueLock();
  private final UniqueLock q = new UniqueLock();

  e(c paramc)
  {
    this.l = paramc;
    this.j = paramc.a();
    this.k = paramc.f();
    this.m = PluginConstant.b(paramc);
    this.n = PluginConstant.c(paramc);
    this.o = PluginConstant.d(paramc);
  }

  // ERROR //
  private int a(File paramFile, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 95	com/tencent/component/plugin/server/e:i	(Ljava/io/File;)Z
    //   4: ifne +68 -> 72
    //   7: aload_1
    //   8: invokestatic 98	com/tencent/component/plugin/server/e:g	(Ljava/io/File;)V
    //   11: ldc 100
    //   13: iconst_0
    //   14: ldc 102
    //   16: new 104	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   23: ldc 107
    //   25: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_1
    //   29: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: aconst_null
    //   36: invokestatic 123	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   39: ldc 18
    //   41: new 104	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   48: ldc 125
    //   50: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: ldc 127
    //   59: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokestatic 132	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: getstatic 39	com/tencent/component/plugin/server/e:c	I
    //   71: ireturn
    //   72: aload_0
    //   73: monitorenter
    //   74: aload_0
    //   75: invokespecial 135	com/tencent/component/plugin/server/e:b	()Z
    //   78: ifne +35 -> 113
    //   81: ldc 100
    //   83: iconst_0
    //   84: ldc 137
    //   86: aconst_null
    //   87: aconst_null
    //   88: invokestatic 123	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   91: ldc 18
    //   93: ldc 139
    //   95: invokestatic 132	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: getstatic 41	com/tencent/component/plugin/server/e:d	I
    //   101: istore 16
    //   103: aload_0
    //   104: monitorexit
    //   105: iload 16
    //   107: ireturn
    //   108: astore_3
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_3
    //   112: athrow
    //   113: aload_0
    //   114: monitorexit
    //   115: aload_0
    //   116: getfield 69	com/tencent/component/plugin/server/e:j	Landroid/content/Context;
    //   119: aload_1
    //   120: invokevirtual 144	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   123: iconst_1
    //   124: invokestatic 149	com/tencent/component/plugin/server/PluginParser:a	(Landroid/content/Context;Ljava/lang/String;I)Lcom/tencent/component/plugin/PluginInfo;
    //   127: astore 4
    //   129: aload_0
    //   130: getfield 69	com/tencent/component/plugin/server/e:j	Landroid/content/Context;
    //   133: invokestatic 154	com/tencent/component/plugin/server/PluginValidator:a	(Landroid/content/Context;)Lcom/tencent/component/plugin/server/PluginValidator;
    //   136: aload 4
    //   138: aload_0
    //   139: getfield 62	com/tencent/component/plugin/server/e:l	Lcom/tencent/component/plugin/server/c;
    //   142: invokevirtual 157	com/tencent/component/plugin/server/PluginValidator:a	(Lcom/tencent/component/plugin/PluginInfo;Lcom/tencent/component/plugin/server/c;)V
    //   145: aload_0
    //   146: getfield 58	com/tencent/component/plugin/server/e:p	Lcom/tencent/component/utils/UniqueLock;
    //   149: aload 4
    //   151: getfield 162	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   154: invokevirtual 165	com/tencent/component/utils/UniqueLock:a	(Ljava/lang/Object;)Ljava/util/concurrent/locks/Lock;
    //   157: astore 6
    //   159: aload 6
    //   161: invokeinterface 170 1 0
    //   166: aload_0
    //   167: aload 4
    //   169: invokespecial 173	com/tencent/component/plugin/server/e:b	(Lcom/tencent/component/plugin/PluginInfo;)Ljava/io/File;
    //   172: astore 8
    //   174: aload 8
    //   176: ifnonnull +128 -> 304
    //   179: aload_1
    //   180: invokestatic 98	com/tencent/component/plugin/server/e:g	(Ljava/io/File;)V
    //   183: ldc 100
    //   185: iconst_0
    //   186: ldc 175
    //   188: new 104	java/lang/StringBuilder
    //   191: dup
    //   192: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   195: ldc 177
    //   197: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: aload 4
    //   202: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   205: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: aconst_null
    //   209: invokestatic 123	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   212: ldc 18
    //   214: new 104	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   221: ldc 179
    //   223: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: aload 4
    //   228: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: invokestatic 132	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: getstatic 49	com/tencent/component/plugin/server/e:h	I
    //   240: istore 9
    //   242: aload 6
    //   244: invokeinterface 182 1 0
    //   249: iload 9
    //   251: ireturn
    //   252: astore 5
    //   254: aload_1
    //   255: invokestatic 98	com/tencent/component/plugin/server/e:g	(Ljava/io/File;)V
    //   258: ldc 100
    //   260: iconst_0
    //   261: ldc 184
    //   263: new 104	java/lang/StringBuilder
    //   266: dup
    //   267: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   270: ldc 177
    //   272: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 4
    //   277: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   280: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   283: aload 5
    //   285: invokestatic 123	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   288: ldc 18
    //   290: aload 5
    //   292: invokevirtual 187	com/tencent/component/plugin/server/PluginValidator$ValidateException:getMessage	()Ljava/lang/String;
    //   295: aload 5
    //   297: invokestatic 190	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   300: getstatic 43	com/tencent/component/plugin/server/e:e	I
    //   303: ireturn
    //   304: aload_0
    //   305: getfield 74	com/tencent/component/plugin/server/e:k	Lcom/tencent/component/plugin/server/g;
    //   308: aload 4
    //   310: getfield 162	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   313: invokevirtual 195	com/tencent/component/plugin/server/g:b	(Ljava/lang/String;)Z
    //   316: ifeq +23 -> 339
    //   319: aload_0
    //   320: aload_0
    //   321: getfield 74	com/tencent/component/plugin/server/e:k	Lcom/tencent/component/plugin/server/g;
    //   324: aload 4
    //   326: getfield 162	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   329: invokevirtual 198	com/tencent/component/plugin/server/g:g	(Ljava/lang/String;)Lcom/tencent/component/plugin/PluginInfo;
    //   332: iconst_1
    //   333: invokespecial 201	com/tencent/component/plugin/server/e:a	(Lcom/tencent/component/plugin/PluginInfo;Z)Z
    //   336: pop
    //   337: iconst_1
    //   338: istore_2
    //   339: aload 8
    //   341: invokevirtual 144	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   344: invokestatic 206	com/tencent/component/plugin/PluginFileLock:b	(Ljava/lang/String;)Ljava/util/concurrent/locks/Lock;
    //   347: astore 10
    //   349: aload 10
    //   351: invokeinterface 170 1 0
    //   356: aload_0
    //   357: aload_1
    //   358: aload 8
    //   360: invokespecial 209	com/tencent/component/plugin/server/e:a	(Ljava/io/File;Ljava/io/File;)V
    //   363: aload 10
    //   365: invokeinterface 182 1 0
    //   370: aload_1
    //   371: invokestatic 98	com/tencent/component/plugin/server/e:g	(Ljava/io/File;)V
    //   374: aload 8
    //   376: invokestatic 95	com/tencent/component/plugin/server/e:i	(Ljava/io/File;)Z
    //   379: ifeq +220 -> 599
    //   382: getstatic 35	com/tencent/component/plugin/server/e:a	I
    //   385: istore 12
    //   387: iload 12
    //   389: getstatic 35	com/tencent/component/plugin/server/e:a	I
    //   392: if_icmpne +68 -> 460
    //   395: aload 4
    //   397: getfield 212	com/tencent/component/plugin/PluginInfo:nativeLibraryDir	Ljava/lang/String;
    //   400: astore 14
    //   402: aload 14
    //   404: ifnull +56 -> 460
    //   407: aload 8
    //   409: invokevirtual 144	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   412: aload 14
    //   414: invokestatic 217	com/tencent/component/plugin/PluginNativeHelper:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   417: ifne +43 -> 460
    //   420: getstatic 37	com/tencent/component/plugin/server/e:b	I
    //   423: istore 12
    //   425: ldc 18
    //   427: new 104	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   434: ldc 219
    //   436: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: aload 4
    //   441: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   444: ldc 221
    //   446: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: aload 8
    //   451: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   454: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   457: invokestatic 132	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   460: iload_2
    //   461: ifeq +36 -> 497
    //   464: iload 12
    //   466: getstatic 35	com/tencent/component/plugin/server/e:a	I
    //   469: if_icmpne +28 -> 497
    //   472: aload 4
    //   474: aload 8
    //   476: invokevirtual 144	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   479: putfield 224	com/tencent/component/plugin/PluginInfo:installPath	Ljava/lang/String;
    //   482: aload_0
    //   483: getfield 74	com/tencent/component/plugin/server/e:k	Lcom/tencent/component/plugin/server/g;
    //   486: aload 4
    //   488: getfield 162	com/tencent/component/plugin/PluginInfo:pluginId	Ljava/lang/String;
    //   491: aload 4
    //   493: invokevirtual 227	com/tencent/component/plugin/server/g:a	(Ljava/lang/String;Lcom/tencent/component/plugin/PluginInfo;)Z
    //   496: pop
    //   497: iload 12
    //   499: ifge +108 -> 607
    //   502: ldc 18
    //   504: new 104	java/lang/StringBuilder
    //   507: dup
    //   508: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   511: ldc 229
    //   513: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload 4
    //   518: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   521: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   524: invokestatic 132	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   527: ldc 100
    //   529: iconst_0
    //   530: ldc 231
    //   532: new 104	java/lang/StringBuilder
    //   535: dup
    //   536: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   539: ldc 233
    //   541: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: aload_1
    //   545: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   548: ldc 235
    //   550: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: aload 8
    //   555: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   558: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: aconst_null
    //   562: invokestatic 123	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   565: aload 6
    //   567: invokeinterface 182 1 0
    //   572: iload 12
    //   574: ireturn
    //   575: astore 11
    //   577: aload 10
    //   579: invokeinterface 182 1 0
    //   584: aload 11
    //   586: athrow
    //   587: astore 7
    //   589: aload 6
    //   591: invokeinterface 182 1 0
    //   596: aload 7
    //   598: athrow
    //   599: getstatic 45	com/tencent/component/plugin/server/e:f	I
    //   602: istore 12
    //   604: goto -217 -> 387
    //   607: ldc 18
    //   609: new 104	java/lang/StringBuilder
    //   612: dup
    //   613: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   616: ldc 237
    //   618: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: aload 4
    //   623: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   626: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   629: invokestatic 132	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   632: ldc 100
    //   634: iconst_1
    //   635: ldc 239
    //   637: new 104	java/lang/StringBuilder
    //   640: dup
    //   641: invokespecial 105	java/lang/StringBuilder:<init>	()V
    //   644: ldc 107
    //   646: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: aload_1
    //   650: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   653: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   656: aconst_null
    //   657: invokestatic 123	com/tencent/component/plugin/PluginReporter:a	(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   660: goto -95 -> 565
    //
    // Exception table:
    //   from	to	target	type
    //   74	105	108	finally
    //   109	111	108	finally
    //   113	115	108	finally
    //   129	145	252	com/tencent/component/plugin/server/PluginValidator$ValidateException
    //   356	363	575	finally
    //   166	174	587	finally
    //   179	242	587	finally
    //   304	337	587	finally
    //   339	356	587	finally
    //   363	387	587	finally
    //   387	402	587	finally
    //   407	460	587	finally
    //   464	497	587	finally
    //   502	565	587	finally
    //   577	587	587	finally
    //   599	604	587	finally
    //   607	660	587	finally
  }

  private File a(boolean paramBoolean)
  {
    String str1 = UUID.randomUUID().toString();
    String str2 = CacheManager.a(this.j, true).a(str1, paramBoolean);
    if (str2 != null)
      return new File(str2);
    return null;
  }

  private void a(File paramFile1, File paramFile2)
  {
    if ((paramFile1 == null) || (paramFile2 == null));
    do
    {
      do
        return;
      while (!i(paramFile1));
      b(paramFile1, paramFile2);
    }
    while ((i(paramFile2)) || (((!k(paramFile1)) || (!k(paramFile2))) && ((k(paramFile1)) || (k(paramFile2)))));
    if (!k(paramFile2));
    for (boolean bool = true; ; bool = false)
    {
      File localFile = a(bool);
      if (localFile == null)
        break;
      b(paramFile1, localFile);
      g(paramFile1);
      b(localFile, paramFile2);
      g(localFile);
      return;
    }
  }

  private boolean a(PluginInfo paramPluginInfo, boolean paramBoolean)
  {
    if (paramPluginInfo == null)
      return false;
    Lock localLock1 = this.p.a(paramPluginInfo.pluginId);
    localLock1.lock();
    try
    {
      LogUtil.i("PluginInstaller", "performUninstall id:" + paramPluginInfo.pluginId + ",pluginInfo.isInternal:" + paramPluginInfo.a());
      String str1;
      Lock localLock2;
      if (!paramPluginInfo.a())
      {
        str1 = paramPluginInfo.installPath;
        if (str1 != null)
        {
          localLock2 = PluginFileLock.b(str1);
          localLock2.lock();
        }
      }
      try
      {
        g(new File(str1));
        localLock2.unlock();
        String str2 = paramPluginInfo.dexOptimizeDir;
        if (str2 != null)
        {
          String str3 = PluginConstant.b(paramPluginInfo);
          LogUtil.i("PluginInstaller", "remove " + paramPluginInfo.pluginId + " dexOpt :" + str2 + "/" + str3);
          if (str3 != null)
            g(new File(str2, str3));
        }
        String str4 = paramPluginInfo.nativeLibraryDir;
        if ((str4 != null) && (!PluginNativeHelper.a(str4)))
          LogUtil.i("PluginInstaller", "cannot remove native libraries for plugin " + paramPluginInfo + ", file " + str1);
        if ((paramBoolean) && (!TextUtils.isEmpty(paramPluginInfo.pluginId)))
          this.k.a(paramPluginInfo.pluginId);
        return true;
      }
      finally
      {
        localLock2.unlock();
      }
    }
    finally
    {
      localLock1.unlock();
    }
    throw localObject1;
  }

  private File b(PluginInfo paramPluginInfo)
  {
    String str = PluginConstant.a(paramPluginInfo);
    if (TextUtils.isEmpty(str))
      return null;
    return new File(this.m, str);
  }

  private static void b(File paramFile1, File paramFile2)
  {
    FileUtil.a(paramFile1, paramFile2);
  }

  private boolean b()
  {
    return j(this.m);
  }

  private void c(File paramFile)
  {
    if (!h(paramFile));
    while (true)
    {
      return;
      File[] arrayOfFile = paramFile.listFiles();
      if (arrayOfFile == null)
        continue;
      int i1 = arrayOfFile.length;
      for (int i2 = 0; i2 < i1; i2++)
        a(arrayOfFile[i2], false);
    }
  }

  private boolean c()
  {
    return j(this.n);
  }

  private int d(File paramFile)
  {
    if (!i(paramFile))
    {
      g(paramFile);
      PluginReporter.a("install", false, "invalid file", "file:" + paramFile, null);
      LogUtil.i("PluginInstaller", "file " + paramFile + " is not valid");
      return c;
    }
    monitorenter;
    try
    {
      if (!c())
      {
        PluginReporter.a("install", false, "invalid pending dir", null, null);
        int i3 = d;
        return i3;
      }
    }
    finally
    {
      monitorexit;
    }
    monitorexit;
    File localFile = e(paramFile);
    if (localFile == null)
    {
      PluginReporter.a("install", false, "cannot generate pending path", "file:" + paramFile, null);
      LogUtil.i("PluginInstaller", "cannot generate pending file for file " + paramFile);
      return h;
    }
    Lock localLock = this.q.a(localFile.getAbsolutePath());
    localLock.lock();
    try
    {
      if (f(paramFile))
      {
        int i2 = g;
        return i2;
      }
      a(paramFile, localFile);
      g(paramFile);
      int i1;
      if (i(localFile))
      {
        i1 = a;
        if (i1 >= 0)
          break label302;
        PluginReporter.a("install", false, "cannot copy file", "srcFile:" + paramFile + ", dstFile:" + localFile, null);
      }
      while (true)
      {
        return i1;
        i1 = f;
        break;
        label302: PluginReporter.a("install", true, "succeed to install", "file:" + paramFile, null);
      }
    }
    finally
    {
      localLock.unlock();
    }
    throw localObject2;
  }

  private File e(File paramFile)
  {
    if (paramFile == null)
      return null;
    return new File(this.n, paramFile.getName());
  }

  private boolean f(File paramFile)
  {
    return paramFile.getAbsolutePath().startsWith(this.n.getAbsolutePath());
  }

  private static void g(File paramFile)
  {
    FileUtil.a(paramFile);
  }

  private static boolean h(File paramFile)
  {
    return (paramFile != null) && (paramFile.isDirectory()) && (paramFile.exists());
  }

  private static boolean i(File paramFile)
  {
    return (paramFile != null) && (paramFile.isFile()) && (paramFile.length() > 0L);
  }

  private static boolean j(File paramFile)
  {
    if (!h(paramFile))
    {
      FileUtil.a(paramFile);
      return paramFile.mkdirs();
    }
    return true;
  }

  private static boolean k(File paramFile)
  {
    if (paramFile == null);
    for (String str1 = null; ; str1 = paramFile.getAbsolutePath())
    {
      String str2 = Environment.getDataDirectory().getAbsolutePath();
      if ((str1 == null) || (!str1.startsWith(str2)))
        break;
      return true;
    }
    return false;
  }

  final int a(File paramFile)
  {
    return a(paramFile, true);
  }

  final void a()
  {
    c(this.n);
    if (DebugUtil.a())
      c(this.o);
  }

  final boolean a(PluginInfo paramPluginInfo)
  {
    return a(paramPluginInfo, true);
  }

  final int b(File paramFile)
  {
    return d(paramFile);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.e
 * JD-Core Version:    0.6.0
 */