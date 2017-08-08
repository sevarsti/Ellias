package com.tencent.beacon.a;

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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static String a = null;

  public static String a()
  {
    String str1 = "";
    try
    {
      int i = Process.myPid();
      str1 = str1 + i + "_";
      String str2 = str1 + new Date().getTime() / 1000L;
      return str2;
    }
    catch (Exception localException)
    {
    }
    return str1;
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
          String str = localObject3.toString().trim();
          localObject2 = str;
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        com.tencent.beacon.d.a.d("no appkey !! ", new Object[0]);
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

  public static void a(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("DENGTA_META", 0).edit().putString("key_channelpath", paramString).commit();
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("DENGTA_META", 0).edit().putString(paramString1, paramString2).commit();
  }

  // ERROR //
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnonnull +13 -> 17
    //   7: ldc 15
    //   9: astore 12
    //   11: ldc 2
    //   13: monitorexit
    //   14: aload 12
    //   16: areturn
    //   17: aconst_null
    //   18: astore_1
    //   19: aload_0
    //   20: invokevirtual 129	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   23: astore_3
    //   24: aload_0
    //   25: ldc 99
    //   27: iconst_0
    //   28: invokevirtual 103	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   31: ldc 111
    //   33: ldc 15
    //   35: invokeinterface 133 3 0
    //   40: astore 17
    //   42: aload 17
    //   44: ldc 15
    //   46: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   49: ifeq +34 -> 83
    //   52: ldc 139
    //   54: astore 17
    //   56: aload_0
    //   57: ldc 99
    //   59: iconst_0
    //   60: invokevirtual 103	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   63: invokeinterface 109 1 0
    //   68: ldc 111
    //   70: aload 17
    //   72: invokeinterface 117 3 0
    //   77: invokeinterface 121 1 0
    //   82: pop
    //   83: new 23	java/lang/StringBuilder
    //   86: dup
    //   87: ldc 141
    //   89: invokespecial 144	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   92: aload 17
    //   94: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: iconst_0
    //   101: anewarray 4	java/lang/Object
    //   104: invokestatic 146	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   107: aload 17
    //   109: ldc 15
    //   111: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   114: ifne +365 -> 479
    //   117: aload_3
    //   118: aload 17
    //   120: invokevirtual 152	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   123: astore 21
    //   125: aload 21
    //   127: astore 5
    //   129: new 154	java/util/Properties
    //   132: dup
    //   133: invokespecial 155	java/util/Properties:<init>	()V
    //   136: astore 22
    //   138: aload 22
    //   140: aload 5
    //   142: invokevirtual 159	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   145: aload 22
    //   147: ldc 161
    //   149: ldc 15
    //   151: invokevirtual 164	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   154: astore 24
    //   156: aload 24
    //   158: astore 12
    //   160: new 23	java/lang/StringBuilder
    //   163: dup
    //   164: ldc 166
    //   166: invokespecial 144	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   169: aload 12
    //   171: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: iconst_0
    //   178: anewarray 4	java/lang/Object
    //   181: invokestatic 146	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   184: ldc 15
    //   186: aload 12
    //   188: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   191: istore 27
    //   193: iload 27
    //   195: ifne +32 -> 227
    //   198: aload 5
    //   200: ifnull -189 -> 11
    //   203: aload 5
    //   205: invokevirtual 171	java/io/InputStream:close	()V
    //   208: goto -197 -> 11
    //   211: astore 29
    //   213: aload 29
    //   215: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   218: goto -207 -> 11
    //   221: astore_2
    //   222: ldc 2
    //   224: monitorexit
    //   225: aload_2
    //   226: athrow
    //   227: aload 5
    //   229: astore 28
    //   231: aload 12
    //   233: astore 9
    //   235: aload 28
    //   237: astore 19
    //   239: aload 19
    //   241: ifnull +231 -> 472
    //   244: aload 19
    //   246: invokevirtual 171	java/io/InputStream:close	()V
    //   249: aload 9
    //   251: astore 12
    //   253: aload_0
    //   254: invokevirtual 59	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   257: aload_0
    //   258: invokevirtual 62	android/content/Context:getPackageName	()Ljava/lang/String;
    //   261: sipush 128
    //   264: invokevirtual 68	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   267: getfield 74	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   270: ldc 176
    //   272: invokevirtual 82	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   275: astore 14
    //   277: aload 14
    //   279: ifnull -268 -> 11
    //   282: aload 14
    //   284: invokevirtual 83	java/lang/Object:toString	()Ljava/lang/String;
    //   287: astore 15
    //   289: aload 15
    //   291: astore 12
    //   293: goto -282 -> 11
    //   296: astore 20
    //   298: aload 20
    //   300: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   303: aload 9
    //   305: astore 12
    //   307: goto -54 -> 253
    //   310: astore 8
    //   312: ldc 15
    //   314: astore 9
    //   316: aload_0
    //   317: ldc 99
    //   319: iconst_0
    //   320: invokevirtual 103	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   323: invokeinterface 109 1 0
    //   328: ldc 111
    //   330: ldc 15
    //   332: invokeinterface 117 3 0
    //   337: invokeinterface 121 1 0
    //   342: pop
    //   343: ldc 178
    //   345: iconst_0
    //   346: anewarray 4	java/lang/Object
    //   349: invokestatic 181	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   352: aload_1
    //   353: ifnull +119 -> 472
    //   356: aload_1
    //   357: invokevirtual 171	java/io/InputStream:close	()V
    //   360: aload 9
    //   362: astore 12
    //   364: goto -111 -> 253
    //   367: astore 16
    //   369: aload 16
    //   371: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   374: aload 9
    //   376: astore 12
    //   378: goto -125 -> 253
    //   381: astore 4
    //   383: aconst_null
    //   384: astore 5
    //   386: aload 4
    //   388: astore 6
    //   390: aload 5
    //   392: ifnull +8 -> 400
    //   395: aload 5
    //   397: invokevirtual 171	java/io/InputStream:close	()V
    //   400: aload 6
    //   402: athrow
    //   403: astore 7
    //   405: aload 7
    //   407: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   410: goto -10 -> 400
    //   413: astore 13
    //   415: ldc 183
    //   417: iconst_0
    //   418: anewarray 4	java/lang/Object
    //   421: invokestatic 146	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   424: goto -413 -> 11
    //   427: astore 6
    //   429: goto -39 -> 390
    //   432: astore 10
    //   434: aload_1
    //   435: astore 5
    //   437: aload 10
    //   439: astore 6
    //   441: goto -51 -> 390
    //   444: astore 23
    //   446: aload 5
    //   448: astore_1
    //   449: ldc 15
    //   451: astore 9
    //   453: goto -137 -> 316
    //   456: astore 25
    //   458: aload 5
    //   460: astore 26
    //   462: aload 12
    //   464: astore 9
    //   466: aload 26
    //   468: astore_1
    //   469: goto -153 -> 316
    //   472: aload 9
    //   474: astore 12
    //   476: goto -223 -> 253
    //   479: ldc 15
    //   481: astore 9
    //   483: aconst_null
    //   484: astore 19
    //   486: goto -247 -> 239
    //
    // Exception table:
    //   from	to	target	type
    //   203	208	211	java/io/IOException
    //   19	24	221	finally
    //   203	208	221	finally
    //   213	218	221	finally
    //   244	249	221	finally
    //   253	277	221	finally
    //   282	289	221	finally
    //   298	303	221	finally
    //   356	360	221	finally
    //   369	374	221	finally
    //   395	400	221	finally
    //   400	403	221	finally
    //   405	410	221	finally
    //   415	424	221	finally
    //   244	249	296	java/io/IOException
    //   24	52	310	java/lang/Exception
    //   56	83	310	java/lang/Exception
    //   83	125	310	java/lang/Exception
    //   356	360	367	java/io/IOException
    //   24	52	381	finally
    //   56	83	381	finally
    //   83	125	381	finally
    //   395	400	403	java/io/IOException
    //   253	277	413	java/lang/Throwable
    //   282	289	413	java/lang/Throwable
    //   129	156	427	finally
    //   160	193	427	finally
    //   316	352	432	finally
    //   129	156	444	java/lang/Exception
    //   160	193	456	java/lang/Exception
  }

  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("DENGTA_META", 0).getString(paramString1, paramString2);
  }

  public static void b(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("DENGTA_META", 0).edit().putString("key_initsdktimes", paramString).commit();
  }

  public static void c(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("DENGTA_META", 0).edit().putString("key_initsdkdate", paramString).commit();
  }

  public static boolean c(Context paramContext)
  {
    int i = 0;
    monitorenter;
    if (paramContext == null);
    try
    {
      com.tencent.beacon.d.a.d("context == null return null", new Object[0]);
      while (true)
      {
        return i;
        try
        {
          String str1 = paramContext.getApplicationContext().getSharedPreferences("DENGTA_META", 0).getString("APPKEY_DENGTA", null);
          String str2 = a(paramContext);
          i = 0;
          if (str1 != null)
          {
            boolean bool = str1.equals(str2);
            i = 0;
            if (bool)
              continue;
          }
          i = 1;
          SharedPreferences.Editor localEditor = paramContext.getApplicationContext().getSharedPreferences("DENGTA_META", 0).edit();
          localEditor.putString("APPKEY_DENGTA", str2);
          localEditor.commit();
        }
        catch (Exception localException)
        {
          com.tencent.beacon.d.a.b("updateLocalAPPKEY fail!", new Object[0]);
          localException.printStackTrace();
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public static boolean d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +14 -> 15
    //   4: ldc 191
    //   6: iconst_0
    //   7: anewarray 4	java/lang/Object
    //   10: invokestatic 96	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   13: iconst_0
    //   14: ireturn
    //   15: aload_0
    //   16: invokevirtual 195	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   19: ldc 99
    //   21: iconst_0
    //   22: invokevirtual 103	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   25: ldc 204
    //   27: aconst_null
    //   28: invokeinterface 133 3 0
    //   33: astore 4
    //   35: aload_0
    //   36: invokestatic 207	com/tencent/beacon/a/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   39: astore 5
    //   41: aload 4
    //   43: ifnull +17 -> 60
    //   46: aload 4
    //   48: aload 5
    //   50: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   53: istore 6
    //   55: iload 6
    //   57: ifne -44 -> 13
    //   60: iconst_1
    //   61: istore_3
    //   62: aload_0
    //   63: invokevirtual 195	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   66: ldc 99
    //   68: iconst_0
    //   69: invokevirtual 103	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   72: invokeinterface 109 1 0
    //   77: astore 7
    //   79: aload 7
    //   81: ldc 204
    //   83: aload 5
    //   85: invokeinterface 117 3 0
    //   90: pop
    //   91: aload 7
    //   93: invokeinterface 121 1 0
    //   98: pop
    //   99: iload_3
    //   100: ireturn
    //   101: astore_1
    //   102: aload_1
    //   103: astore_2
    //   104: iconst_0
    //   105: istore_3
    //   106: ldc 199
    //   108: iconst_0
    //   109: anewarray 4	java/lang/Object
    //   112: invokestatic 201	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   115: aload_2
    //   116: invokevirtual 202	java/lang/Exception:printStackTrace	()V
    //   119: iload_3
    //   120: ireturn
    //   121: astore_2
    //   122: goto -16 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   15	41	101	java/lang/Exception
    //   46	55	101	java/lang/Exception
    //   62	99	121	java/lang/Exception
  }

  private static boolean d(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.trim().length() <= 0))
      return false;
    try
    {
      List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if ((localList == null) || (localList.size() == 0))
      {
        com.tencent.beacon.d.a.b("no running proc", new Object[0]);
        return false;
      }
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (localRunningAppProcessInfo.importance != 100)
          continue;
        String[] arrayOfString = localRunningAppProcessInfo.pkgList;
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++)
        {
          boolean bool = paramString.equals(arrayOfString[j]);
          if (bool)
            return true;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.getLocalizedMessage();
      com.tencent.beacon.d.a.d("Failed to judge }[%s]", arrayOfObject);
    }
    return false;
  }

  public static String e(Context paramContext)
  {
    if (paramContext == null)
      return null;
    return paramContext.getPackageName();
  }

  public static String f(Context paramContext)
  {
    int i = 0;
    monitorenter;
    Object localObject2;
    if (paramContext == null)
      localObject2 = null;
    while (true)
    {
      String str4;
      try
      {
        PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo((String)localObject2, 0);
        String str2 = localPackageInfo.versionName;
        int j = localPackageInfo.versionCode;
        if ((str2 != null) && (str2.trim().length() > 0))
          continue;
        String str3 = j;
        localObject3 = str3;
        return localObject3;
        String str1 = paramContext.getPackageName();
        localObject2 = str1;
        continue;
        str4 = str2.trim().replace('\n', ' ').replace('\r', ' ').replace("|", "%7C");
        char[] arrayOfChar = str4.toCharArray();
        int k = 0;
        if (i >= arrayOfChar.length)
          continue;
        if (arrayOfChar[i] != '.')
          break label268;
        k++;
        break label268;
        if (k < 3)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(j);
          com.tencent.beacon.d.a.a("add versionCode: %s", arrayOfObject);
          localObject3 = str4 + "." + j;
          com.tencent.beacon.d.a.a("version: %s", new Object[] { localObject3 });
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        com.tencent.beacon.d.a.d(localException.toString(), new Object[0]);
        localObject3 = "";
        continue;
      }
      finally
      {
        monitorexit;
      }
      Object localObject3 = str4;
      continue;
      label268: i++;
    }
  }

  public static boolean g(Context paramContext)
  {
    return d(paramContext, paramContext.getPackageName());
  }

  public static int h(Context paramContext)
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
        int j = localRunningAppProcessInfo.importance;
        return j;
      }
    }
    catch (Exception localException)
    {
    }
    return 0;
  }

  public static String i(Context paramContext)
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
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.a
 * JD-Core Version:    0.6.0
 */