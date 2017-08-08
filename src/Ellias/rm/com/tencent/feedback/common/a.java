package com.tencent.feedback.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static String a = null;
  private static Boolean b = null;
  private static Boolean c = null;

  // ERROR //
  public static android.util.SparseArray<String> a(int paramInt)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: new 24	android/util/SparseArray
    //   5: dup
    //   6: invokespecial 27	android/util/SparseArray:<init>	()V
    //   9: astore_2
    //   10: invokestatic 33	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: ldc 35
    //   15: invokevirtual 39	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   18: astore 9
    //   20: aload 9
    //   22: ifnull +338 -> 360
    //   25: new 41	java/io/BufferedReader
    //   28: dup
    //   29: new 43	java/io/InputStreamReader
    //   32: dup
    //   33: aload 9
    //   35: invokevirtual 49	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   38: invokespecial 52	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   41: invokespecial 55	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   44: astore 10
    //   46: ldc 57
    //   48: invokestatic 63	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   51: astore 13
    //   53: aload 10
    //   55: invokevirtual 67	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   58: astore 14
    //   60: aload 14
    //   62: ifnull +301 -> 363
    //   65: iinc 1 1
    //   68: iload_1
    //   69: iconst_1
    //   70: if_icmpeq -17 -> 53
    //   73: aload 13
    //   75: aload 14
    //   77: invokevirtual 71	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   80: invokevirtual 77	java/util/regex/Matcher:matches	()Z
    //   83: ifne +33 -> 116
    //   86: ldc 79
    //   88: iconst_1
    //   89: anewarray 4	java/lang/Object
    //   92: dup
    //   93: iconst_0
    //   94: aload 14
    //   96: aastore
    //   97: invokestatic 84	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   100: aload 10
    //   102: invokevirtual 87	java/io/BufferedReader:close	()V
    //   105: aconst_null
    //   106: areturn
    //   107: astore 24
    //   109: aload 24
    //   111: invokevirtual 90	java/io/IOException:printStackTrace	()V
    //   114: aconst_null
    //   115: areturn
    //   116: aload 14
    //   118: invokevirtual 95	java/lang/String:trim	()Ljava/lang/String;
    //   121: ldc 97
    //   123: invokevirtual 101	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   126: astore 16
    //   128: aload 16
    //   130: ifnull +11 -> 141
    //   133: aload 16
    //   135: arraylength
    //   136: bipush 9
    //   138: if_icmpge +33 -> 171
    //   141: ldc 79
    //   143: iconst_1
    //   144: anewarray 4	java/lang/Object
    //   147: dup
    //   148: iconst_0
    //   149: aload 14
    //   151: aastore
    //   152: invokestatic 84	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   155: aload 10
    //   157: invokevirtual 87	java/io/BufferedReader:close	()V
    //   160: aconst_null
    //   161: areturn
    //   162: astore 17
    //   164: aload 17
    //   166: invokevirtual 90	java/io/IOException:printStackTrace	()V
    //   169: aconst_null
    //   170: areturn
    //   171: aload 16
    //   173: iconst_0
    //   174: aaload
    //   175: invokestatic 107	android/os/Process:getUidForName	(Ljava/lang/String;)I
    //   178: iload_0
    //   179: if_icmpne -126 -> 53
    //   182: new 109	java/lang/StringBuffer
    //   185: dup
    //   186: invokespecial 110	java/lang/StringBuffer:<init>	()V
    //   189: astore 18
    //   191: bipush 8
    //   193: istore 19
    //   195: iload 19
    //   197: aload 16
    //   199: arraylength
    //   200: if_icmpge +87 -> 287
    //   203: iload 19
    //   205: bipush 8
    //   207: if_icmple +32 -> 239
    //   210: aload 18
    //   212: new 112	java/lang/StringBuilder
    //   215: dup
    //   216: ldc 114
    //   218: invokespecial 117	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   221: aload 16
    //   223: iload 19
    //   225: aaload
    //   226: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   232: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   235: pop
    //   236: goto +183 -> 419
    //   239: aload 18
    //   241: aload 16
    //   243: iload 19
    //   245: aaload
    //   246: invokevirtual 127	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   249: pop
    //   250: goto +169 -> 419
    //   253: astore 12
    //   255: aload 10
    //   257: astore 4
    //   259: aload 12
    //   261: astore_3
    //   262: aload_3
    //   263: invokevirtual 128	java/lang/Exception:printStackTrace	()V
    //   266: aload 4
    //   268: ifnull -163 -> 105
    //   271: aload 4
    //   273: invokevirtual 87	java/io/BufferedReader:close	()V
    //   276: aconst_null
    //   277: areturn
    //   278: astore 8
    //   280: aload 8
    //   282: invokevirtual 90	java/io/IOException:printStackTrace	()V
    //   285: aconst_null
    //   286: areturn
    //   287: aload 18
    //   289: invokevirtual 129	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   292: astore 22
    //   294: aload_2
    //   295: aload 16
    //   297: iconst_1
    //   298: aaload
    //   299: invokestatic 134	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   302: aload 22
    //   304: invokevirtual 138	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   307: iconst_2
    //   308: anewarray 4	java/lang/Object
    //   311: astore 23
    //   313: aload 23
    //   315: iconst_0
    //   316: aload 16
    //   318: iconst_1
    //   319: aaload
    //   320: aastore
    //   321: aload 23
    //   323: iconst_1
    //   324: aload 22
    //   326: aastore
    //   327: ldc 140
    //   329: aload 23
    //   331: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   334: goto -281 -> 53
    //   337: astore 11
    //   339: aload 10
    //   341: astore 6
    //   343: aload 11
    //   345: astore 5
    //   347: aload 6
    //   349: ifnull +8 -> 357
    //   352: aload 6
    //   354: invokevirtual 87	java/io/BufferedReader:close	()V
    //   357: aload 5
    //   359: athrow
    //   360: aconst_null
    //   361: astore 10
    //   363: aload 10
    //   365: ifnull +8 -> 373
    //   368: aload 10
    //   370: invokevirtual 87	java/io/BufferedReader:close	()V
    //   373: aload_2
    //   374: areturn
    //   375: astore 15
    //   377: aload 15
    //   379: invokevirtual 90	java/io/IOException:printStackTrace	()V
    //   382: goto -9 -> 373
    //   385: astore 7
    //   387: aload 7
    //   389: invokevirtual 90	java/io/IOException:printStackTrace	()V
    //   392: goto -35 -> 357
    //   395: astore 5
    //   397: aconst_null
    //   398: astore 6
    //   400: goto -53 -> 347
    //   403: astore 5
    //   405: aload 4
    //   407: astore 6
    //   409: goto -62 -> 347
    //   412: astore_3
    //   413: aconst_null
    //   414: astore 4
    //   416: goto -154 -> 262
    //   419: iinc 19 1
    //   422: goto -227 -> 195
    //
    // Exception table:
    //   from	to	target	type
    //   100	105	107	java/io/IOException
    //   155	160	162	java/io/IOException
    //   46	53	253	java/lang/Exception
    //   53	60	253	java/lang/Exception
    //   73	100	253	java/lang/Exception
    //   116	128	253	java/lang/Exception
    //   133	141	253	java/lang/Exception
    //   141	155	253	java/lang/Exception
    //   171	191	253	java/lang/Exception
    //   195	203	253	java/lang/Exception
    //   210	236	253	java/lang/Exception
    //   239	250	253	java/lang/Exception
    //   287	334	253	java/lang/Exception
    //   271	276	278	java/io/IOException
    //   46	53	337	finally
    //   53	60	337	finally
    //   73	100	337	finally
    //   116	128	337	finally
    //   133	141	337	finally
    //   141	155	337	finally
    //   171	191	337	finally
    //   195	203	337	finally
    //   210	236	337	finally
    //   239	250	337	finally
    //   287	334	337	finally
    //   368	373	375	java/io/IOException
    //   352	357	385	java/io/IOException
    //   2	20	395	finally
    //   25	46	395	finally
    //   262	266	403	finally
    //   2	20	412	java/lang/Exception
    //   25	46	412	java/lang/Exception
  }

  public static String a(Context paramContext)
  {
    monitorenter;
    Object localObject2;
    if (paramContext == null)
      localObject2 = "";
    while (true)
    {
      monitorexit;
      return localObject2;
      try
      {
        Object localObject3 = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get("APPKEY_DENGTA");
        if (localObject3 != null)
        {
          String str = localObject3.toString();
          localObject2 = str;
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        e.a("rqdp{  no appkey !!}", new Object[0]);
        localObject2 = "";
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject1;
  }

  public static String a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null));
    while (true)
    {
      return null;
      try
      {
        ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
        if (localApplicationInfo == null)
          continue;
        String str = localApplicationInfo.sourceDir;
        return str;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
    return null;
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("DENGTA_META", 0).edit().putString(paramString1, paramString2).commit();
  }

  public static String b(Context paramContext)
  {
    if (paramContext == null)
      return null;
    return paramContext.getPackageName();
  }

  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("DENGTA_META", 0).getString(paramString1, paramString2);
  }

  public static boolean b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.trim().length() <= 0))
      return false;
    try
    {
      List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if ((localList == null) || (localList.size() == 0))
      {
        e.b("rqdp{  no running proc}", new Object[0]);
        return false;
      }
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if ((localRunningAppProcessInfo.importance != 100) || (!localRunningAppProcessInfo.processName.equals(paramString)))
          continue;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localRunningAppProcessInfo.processName;
        e.b("rqdp{  current seen proc:}%s", arrayOfObject2);
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localThrowable.getLocalizedMessage();
      e.d("rqdp{  Failed to judge }[%s]", arrayOfObject1);
      e.b("rqdp{  current unseen proc:}%s", new Object[] { paramString });
    }
    return false;
  }

  public static String c(Context paramContext)
  {
    int i = 0;
    monitorenter;
    Object localObject2;
    if (paramContext == null)
      localObject2 = null;
    while (true)
    {
      String str2;
      try
      {
        PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo((String)localObject2, 0);
        str2 = localPackageInfo.versionName;
        int j = localPackageInfo.versionCode;
        if ((str2 != null) && (str2.length() > 0))
          continue;
        localObject3 = "";
        return localObject3;
        String str1 = paramContext.getPackageName();
        localObject2 = str1;
        continue;
        char[] arrayOfChar = str2.toCharArray();
        int k = 0;
        if (i >= arrayOfChar.length)
          continue;
        if (arrayOfChar[i] != '.')
          break label221;
        k++;
        break label221;
        if (k < 3)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(j);
          e.a("rqdp{  add versionCode} %s", arrayOfObject);
          str3 = str2 + "." + j;
          e.a("rqdp{  version:} %s", new Object[] { str3 });
          localObject3 = str3;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        e.d(localException.toString(), new Object[0]);
        Object localObject3 = null;
        continue;
      }
      finally
      {
        monitorexit;
      }
      String str3 = str2;
      continue;
      label221: i++;
    }
  }

  private static boolean c(Context paramContext, String paramString)
  {
    e.b("rqdp{  AppInfo.isContainReadLogPermission() start}", new Object[0]);
    if ((paramContext == null) || (paramString == null) || (paramString.trim().length() <= 0))
      return false;
    try
    {
      String[] arrayOfString = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      if (arrayOfString != null)
      {
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++)
        {
          boolean bool = paramString.equals(arrayOfString[j]);
          if (bool)
            return true;
        }
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return false;
    }
    finally
    {
      e.b("rqdp{  AppInfo.isContainReadLogPermission() end}", new Object[0]);
    }
    throw localObject;
  }

  // ERROR //
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc_w 315
    //   6: iconst_0
    //   7: anewarray 4	java/lang/Object
    //   10: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   13: aload_0
    //   14: ifnonnull +13 -> 27
    //   17: ldc 147
    //   19: astore 4
    //   21: ldc 2
    //   23: monitorexit
    //   24: aload 4
    //   26: areturn
    //   27: aload_0
    //   28: invokevirtual 153	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   31: aload_0
    //   32: invokevirtual 156	android/content/Context:getPackageName	()Ljava/lang/String;
    //   35: sipush 128
    //   38: invokevirtual 162	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   41: astore 5
    //   43: aload 5
    //   45: ifnull +11 -> 56
    //   48: aload 5
    //   50: getfield 168	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   53: ifnonnull +26 -> 79
    //   56: ldc 147
    //   58: astore 4
    //   60: ldc_w 317
    //   63: iconst_0
    //   64: anewarray 4	java/lang/Object
    //   67: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   70: goto -49 -> 21
    //   73: astore_1
    //   74: ldc 2
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    //   79: aload 5
    //   81: getfield 168	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   84: ldc_w 319
    //   87: invokevirtual 176	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   90: astore 6
    //   92: aload 6
    //   94: ifnull +27 -> 121
    //   97: aload 6
    //   99: invokevirtual 177	java/lang/Object:toString	()Ljava/lang/String;
    //   102: astore 7
    //   104: aload 7
    //   106: astore 4
    //   108: ldc_w 317
    //   111: iconst_0
    //   112: anewarray 4	java/lang/Object
    //   115: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   118: goto -97 -> 21
    //   121: ldc_w 317
    //   124: iconst_0
    //   125: anewarray 4	java/lang/Object
    //   128: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   131: ldc 147
    //   133: astore 4
    //   135: goto -114 -> 21
    //   138: astore_3
    //   139: aload_3
    //   140: invokevirtual 186	java/lang/Throwable:printStackTrace	()V
    //   143: ldc_w 317
    //   146: iconst_0
    //   147: anewarray 4	java/lang/Object
    //   150: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   153: goto -22 -> 131
    //   156: astore_2
    //   157: ldc_w 317
    //   160: iconst_0
    //   161: anewarray 4	java/lang/Object
    //   164: invokestatic 142	com/tencent/feedback/common/e:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   167: aload_2
    //   168: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	13	73	finally
    //   60	70	73	finally
    //   108	118	73	finally
    //   121	131	73	finally
    //   143	153	73	finally
    //   157	169	73	finally
    //   27	43	138	java/lang/Throwable
    //   48	56	138	java/lang/Throwable
    //   79	92	138	java/lang/Throwable
    //   97	104	138	java/lang/Throwable
    //   27	43	156	finally
    //   48	56	156	finally
    //   79	92	156	finally
    //   97	104	156	finally
    //   139	143	156	finally
  }

  public static boolean e(Context paramContext)
  {
    monitorenter;
    try
    {
      e.b("rqdp{  Read Log Permittion! start}", new Object[0]);
      int i = 0;
      if (paramContext == null);
      while (true)
      {
        return i;
        if (b == null)
          b = Boolean.valueOf(c(paramContext, "android.permission.READ_LOGS"));
        boolean bool = b.booleanValue();
        i = bool;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static boolean f(Context paramContext)
  {
    monitorenter;
    try
    {
      e.b("rqdp{  Read write Permittion! start}", new Object[0]);
      int i = 0;
      if (paramContext == null);
      while (true)
      {
        return i;
        if (c == null)
          c = Boolean.valueOf(c(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE"));
        boolean bool = c.booleanValue();
        i = bool;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String g(Context paramContext)
  {
    if (paramContext == null)
      return null;
    String str = a(paramContext, paramContext.getPackageName());
    if (str == null)
    {
      e.d("rqdp{  No found the apk file on the device,please check it!}", new Object[0]);
      return null;
    }
    return com.tencent.feedback.anr.a.a(com.tencent.feedback.anr.a.a(str, "SHA-1"), false);
  }

  public static String h(Context paramContext)
  {
    try
    {
      int i = Process.myPid();
      Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (localRunningAppProcessInfo.pid != i)
          continue;
        String str = localRunningAppProcessInfo.processName;
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.a
 * JD-Core Version:    0.6.0
 */