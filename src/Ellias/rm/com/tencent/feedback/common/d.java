package com.tencent.feedback.common;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;

public final class d
{
  private static d a;

  private d(Context paramContext)
  {
    paramContext.getApplicationContext();
  }

  public static d a(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((a == null) && (paramContext != null))
        a = new d(paramContext.getApplicationContext());
      d locald = a;
      return locald;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String a()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getDeviceName error}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public static String b()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getVersion error}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public static String b(Context paramContext)
  {
    String str1 = "fail";
    if (paramContext == null)
    {
      e.d("rqdp{  getImei but context == null!}", new Object[0]);
      return str1;
    }
    try
    {
      str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if (str1 == null);
      String str2;
      for (localObject1 = "null"; ; localObject1 = str2)
      {
        try
        {
          e.a("rqdp{  IMEI:}" + (String)localObject1, new Object[0]);
          return localObject1;
        }
        catch (Throwable localThrowable2)
        {
        }
        e.d("rqdp{  getImei error!}", new Object[0]);
        localThrowable2.printStackTrace();
        return localObject1;
        str2 = str1.toLowerCase();
      }
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        Object localObject1 = str1;
        Object localObject2 = localThrowable1;
      }
    }
  }

  public static String c()
  {
    try
    {
      String str = Build.VERSION.SDK;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getApiLevel error}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public static String c(Context paramContext)
  {
    String str1 = "fail";
    if (paramContext == null)
    {
      e.d("rqdp{  getImsi but context == null!}", new Object[0]);
      return str1;
    }
    String str2;
    try
    {
      str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      if (str1 == null)
        return "null";
      String str3 = str1.toLowerCase();
      return str3;
    }
    catch (Throwable localThrowable)
    {
      str2 = str1;
      e.d("rqdp{  getImsi error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return str2;
  }

  public static String d()
  {
    try
    {
      String str = System.getProperty("os.arch");
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.c("rqdp{  ge cuabi fa!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  // ERROR //
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: ldc 44
    //   2: astore_1
    //   3: aload_0
    //   4: ifnonnull +14 -> 18
    //   7: ldc 118
    //   9: iconst_0
    //   10: anewarray 4	java/lang/Object
    //   13: invokestatic 39	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: invokevirtual 122	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   22: ldc 124
    //   24: invokestatic 130	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   27: astore_3
    //   28: aload_3
    //   29: ifnonnull +6 -> 35
    //   32: ldc 68
    //   34: areturn
    //   35: aload_3
    //   36: invokevirtual 91	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   39: astore 5
    //   41: aload 5
    //   43: areturn
    //   44: astore_2
    //   45: ldc 132
    //   47: iconst_0
    //   48: anewarray 4	java/lang/Object
    //   51: invokestatic 39	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   54: aload_2
    //   55: invokevirtual 42	java/lang/Throwable:printStackTrace	()V
    //   58: aload_1
    //   59: areturn
    //   60: astore 4
    //   62: aload_3
    //   63: astore_1
    //   64: aload 4
    //   66: astore_2
    //   67: goto -22 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   18	28	44	java/lang/Throwable
    //   35	41	60	java/lang/Throwable
  }

  public static String e(Context paramContext)
  {
    String str1 = "fail";
    if (paramContext == null)
    {
      e.d("rqdp{  getMacAddress but context == null!}", new Object[0]);
      return str1;
    }
    String str2;
    try
    {
      str1 = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (str1 == null)
        return "null";
      String str3 = str1.toLowerCase();
      return str3;
    }
    catch (Throwable localThrowable)
    {
      str2 = str1;
      localThrowable.printStackTrace();
      e.d("rqdp{  getMacAddress error!}", new Object[0]);
    }
    return str2;
  }

  // ERROR //
  public static byte[] e()
  {
    // Byte code:
    //   0: new 155	java/io/File
    //   3: dup
    //   4: ldc 157
    //   6: invokespecial 158	java/io/File:<init>	(Ljava/lang/String;)V
    //   9: astore_0
    //   10: aload_0
    //   11: invokevirtual 162	java/io/File:exists	()Z
    //   14: ifeq +164 -> 178
    //   17: aload_0
    //   18: invokevirtual 165	java/io/File:canRead	()Z
    //   21: ifeq +157 -> 178
    //   24: new 167	java/io/FileInputStream
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 170	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   32: astore_2
    //   33: new 172	java/io/ByteArrayOutputStream
    //   36: dup
    //   37: invokespecial 173	java/io/ByteArrayOutputStream:<init>	()V
    //   40: astore 6
    //   42: sipush 1000
    //   45: newarray byte
    //   47: astore 7
    //   49: lconst_0
    //   50: lstore 8
    //   52: aload_2
    //   53: aload 7
    //   55: invokevirtual 177	java/io/FileInputStream:read	([B)I
    //   58: istore 10
    //   60: iload 10
    //   62: ifle +29 -> 91
    //   65: aload 6
    //   67: aload 7
    //   69: iconst_0
    //   70: iload 10
    //   72: invokevirtual 181	java/io/ByteArrayOutputStream:write	([BII)V
    //   75: aload 6
    //   77: invokevirtual 184	java/io/ByteArrayOutputStream:flush	()V
    //   80: lload 8
    //   82: iload 10
    //   84: i2l
    //   85: ladd
    //   86: lstore 8
    //   88: goto -36 -> 52
    //   91: aload 6
    //   93: invokevirtual 187	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   96: astore 11
    //   98: aload 11
    //   100: arraylength
    //   101: i2l
    //   102: lstore 12
    //   104: iconst_2
    //   105: anewarray 4	java/lang/Object
    //   108: astore 14
    //   110: aload 14
    //   112: iconst_0
    //   113: lload 8
    //   115: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   118: aastore
    //   119: aload 14
    //   121: iconst_1
    //   122: lload 12
    //   124: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   127: aastore
    //   128: ldc 195
    //   130: aload 14
    //   132: invokestatic 84	com/tencent/feedback/common/e:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   135: aload_2
    //   136: invokevirtual 198	java/io/FileInputStream:close	()V
    //   139: aload 11
    //   141: areturn
    //   142: astore 15
    //   144: aload 15
    //   146: invokevirtual 199	java/io/IOException:printStackTrace	()V
    //   149: aload 11
    //   151: areturn
    //   152: astore 4
    //   154: aconst_null
    //   155: astore_2
    //   156: ldc 114
    //   158: iconst_0
    //   159: anewarray 4	java/lang/Object
    //   162: invokestatic 116	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   165: aload 4
    //   167: invokevirtual 42	java/lang/Throwable:printStackTrace	()V
    //   170: aload_2
    //   171: ifnull +7 -> 178
    //   174: aload_2
    //   175: invokevirtual 198	java/io/FileInputStream:close	()V
    //   178: aconst_null
    //   179: areturn
    //   180: astore 5
    //   182: aload 5
    //   184: invokevirtual 199	java/io/IOException:printStackTrace	()V
    //   187: goto -9 -> 178
    //   190: astore_1
    //   191: aconst_null
    //   192: astore_2
    //   193: aload_2
    //   194: ifnull +7 -> 201
    //   197: aload_2
    //   198: invokevirtual 198	java/io/FileInputStream:close	()V
    //   201: aload_1
    //   202: athrow
    //   203: astore_3
    //   204: aload_3
    //   205: invokevirtual 199	java/io/IOException:printStackTrace	()V
    //   208: goto -7 -> 201
    //   211: astore_1
    //   212: goto -19 -> 193
    //   215: astore 4
    //   217: goto -61 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   135	139	142	java/io/IOException
    //   0	33	152	java/lang/Throwable
    //   174	178	180	java/io/IOException
    //   0	33	190	finally
    //   197	201	203	java/io/IOException
    //   33	49	211	finally
    //   52	60	211	finally
    //   65	80	211	finally
    //   91	135	211	finally
    //   156	170	211	finally
    //   33	49	215	java/lang/Throwable
    //   52	60	215	java/lang/Throwable
    //   65	80	215	java/lang/Throwable
    //   91	135	215	java/lang/Throwable
  }

  public static String f()
  {
    try
    {
      Object localObject = Build.class.getField("HARDWARE").get(null);
      if (localObject != null)
      {
        String str = localObject.toString();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      e.c("rqdp{getCpuProductorName error!}", new Object[0]);
    }
    return "fail";
  }

  public static String f(Context paramContext)
  {
    if (paramContext == null)
    {
      e.d("rqdp{  getMacAddress but context == null!}", new Object[0]);
      return "fail";
    }
    try
    {
      String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getBSSID();
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      e.d("rqdp{  getMacAddress error!}", new Object[0]);
    }
    return "fail";
  }

  public static DisplayMetrics g(Context paramContext)
  {
    if (paramContext == null)
    {
      e.d("rqdp{  getDisplayMetrics but context == null!}", new Object[0]);
      return null;
    }
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      return localDisplayMetrics;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getDisplayMetrics error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String g()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l1 = localStatFs.getBlockSize();
      long l2 = localStatFs.getBlockCount();
      String str = l2 * l1 / 1024L / 1024L;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getDisplayMetrics error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public static long h(Context paramContext)
  {
    if (paramContext == null)
    {
      e.d("rqdp{  getFreeMem but context == null!}", new Object[0]);
      return -2L;
    }
    try
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.availMem;
      return l;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getFreeMem error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return -2L;
  }

  // ERROR //
  public static String h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 293	java/io/FileReader
    //   5: dup
    //   6: ldc_w 295
    //   9: invokespecial 296	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore_1
    //   13: new 298	java/io/BufferedReader
    //   16: dup
    //   17: aload_1
    //   18: sipush 8192
    //   21: invokespecial 301	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   24: astore_2
    //   25: aload_2
    //   26: invokevirtual 304	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   29: ldc_w 306
    //   32: iconst_2
    //   33: invokevirtual 310	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   36: iconst_1
    //   37: aaload
    //   38: invokevirtual 91	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   41: ldc_w 312
    //   44: ldc_w 314
    //   47: invokevirtual 318	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   50: invokevirtual 321	java/lang/String:trim	()Ljava/lang/String;
    //   53: invokestatic 325	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   56: ldc2_w 264
    //   59: ldiv
    //   60: lstore 7
    //   62: new 70	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 263	java/lang/StringBuilder:<init>	()V
    //   69: lload 7
    //   71: invokevirtual 268	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   74: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: astore 9
    //   79: aload_2
    //   80: invokevirtual 326	java/io/BufferedReader:close	()V
    //   83: aload_1
    //   84: invokevirtual 327	java/io/FileReader:close	()V
    //   87: aload 9
    //   89: areturn
    //   90: astore 10
    //   92: ldc_w 291
    //   95: iconst_0
    //   96: anewarray 4	java/lang/Object
    //   99: invokestatic 39	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   102: aload 10
    //   104: invokevirtual 42	java/lang/Throwable:printStackTrace	()V
    //   107: aload 9
    //   109: areturn
    //   110: astore_3
    //   111: aconst_null
    //   112: astore_2
    //   113: ldc_w 291
    //   116: iconst_0
    //   117: anewarray 4	java/lang/Object
    //   120: invokestatic 39	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   123: aload_3
    //   124: invokevirtual 42	java/lang/Throwable:printStackTrace	()V
    //   127: aload_2
    //   128: ifnull +7 -> 135
    //   131: aload_2
    //   132: invokevirtual 326	java/io/BufferedReader:close	()V
    //   135: aload_0
    //   136: ifnull +7 -> 143
    //   139: aload_0
    //   140: invokevirtual 327	java/io/FileReader:close	()V
    //   143: ldc_w 329
    //   146: areturn
    //   147: astore 6
    //   149: ldc_w 291
    //   152: iconst_0
    //   153: anewarray 4	java/lang/Object
    //   156: invokestatic 39	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   159: aload 6
    //   161: invokevirtual 42	java/lang/Throwable:printStackTrace	()V
    //   164: goto -21 -> 143
    //   167: astore 4
    //   169: aconst_null
    //   170: astore_2
    //   171: aconst_null
    //   172: astore_1
    //   173: aload_2
    //   174: ifnull +7 -> 181
    //   177: aload_2
    //   178: invokevirtual 326	java/io/BufferedReader:close	()V
    //   181: aload_1
    //   182: ifnull +7 -> 189
    //   185: aload_1
    //   186: invokevirtual 327	java/io/FileReader:close	()V
    //   189: aload 4
    //   191: athrow
    //   192: astore 5
    //   194: ldc_w 291
    //   197: iconst_0
    //   198: anewarray 4	java/lang/Object
    //   201: invokestatic 39	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   204: aload 5
    //   206: invokevirtual 42	java/lang/Throwable:printStackTrace	()V
    //   209: goto -20 -> 189
    //   212: astore 4
    //   214: aconst_null
    //   215: astore_2
    //   216: goto -43 -> 173
    //   219: astore 4
    //   221: goto -48 -> 173
    //   224: astore 4
    //   226: aload_0
    //   227: astore_1
    //   228: goto -55 -> 173
    //   231: astore_3
    //   232: aload_1
    //   233: astore_0
    //   234: aconst_null
    //   235: astore_2
    //   236: goto -123 -> 113
    //   239: astore_3
    //   240: aload_1
    //   241: astore_0
    //   242: goto -129 -> 113
    //
    // Exception table:
    //   from	to	target	type
    //   79	87	90	java/lang/Throwable
    //   2	13	110	java/lang/Throwable
    //   131	135	147	java/lang/Throwable
    //   139	143	147	java/lang/Throwable
    //   2	13	167	finally
    //   177	181	192	java/lang/Throwable
    //   185	189	192	java/lang/Throwable
    //   13	25	212	finally
    //   25	79	219	finally
    //   113	127	224	finally
    //   13	25	231	java/lang/Throwable
    //   25	79	239	java/lang/Throwable
  }

  public static long i()
  {
    try
    {
      StatFs localStatFs = new StatFs("/data");
      int i = localStatFs.getBlockSize();
      int j = localStatFs.getAvailableBlocks();
      return j * i;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getFreeStorage error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return -2L;
  }

  public static String i(Context paramContext)
  {
    String str;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return "unknown";
      if (localNetworkInfo.getType() == 1)
      {
        str = "wifi";
      }
      else if (localNetworkInfo.getType() == 0)
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager != null)
          switch (localTelephonyManager.getNetworkType())
          {
          case 15:
            str = "HSPA+";
          default:
          case 1:
          case 2:
          case 3:
          case 8:
          case 9:
          case 10:
          case 4:
          case 5:
          case 6:
          case 7:
          case 11:
          case 12:
          case 13:
          case 14:
          }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      str = "unknown";
    }
    while (true)
    {
      return str;
      str = "unknown";
      continue;
      str = "GPRS";
      continue;
      str = "EDGE";
      continue;
      str = "UMTS";
      continue;
      str = "HSDPA";
      continue;
      str = "HSUPA";
      continue;
      str = "HSPA";
      continue;
      str = "CDMA";
      continue;
      str = "EVDO_0";
      continue;
      str = "EVDO_A";
      continue;
      str = "1xRTT";
      continue;
      str = "iDen";
      continue;
      str = "EVDO_B";
      continue;
      str = "LTE";
      continue;
      str = "eHRPD";
    }
  }

  public static String k()
  {
    try
    {
      String str = Locale.getDefault().getCountry();
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getCountry error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public static String l()
  {
    try
    {
      String str = Locale.getDefault().getLanguage();
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getLanguage error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public static String m()
  {
    try
    {
      String str = Build.BRAND;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.d("rqdp{  getBrand error!}", new Object[0]);
      localThrowable.printStackTrace();
    }
    return "fail";
  }

  public final long j()
  {
    if (Environment.getExternalStorageState().equals("mounted"));
    for (int i = 1; i == 0; i = 0)
      return 0L;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      int j = localStatFs.getBlockSize();
      int k = localStatFs.getAvailableBlocks();
      return k * j;
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localThrowable.toString();
      e.d("rqdp{  get free sd error %s}", arrayOfObject);
      localThrowable.printStackTrace();
    }
    return -2L;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.d
 * JD-Core Version:    0.6.0
 */