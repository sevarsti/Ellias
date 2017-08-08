package com.tencent.beacon.event;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.beacon.a.e;
import com.tencent.beacon.d.a;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public final class k
{
  private static k a = null;
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private String j = "";
  private String k = "";
  private String l = "";
  private String m = "";
  private String n = "";
  private String o = "";
  private String p = "";
  private String q = "";
  private String r = "";

  private k(Context paramContext)
  {
    if (paramContext == null)
      a.d(" UADeviceInfo context == null? pls check!", new Object[0]);
    a.b(" start to create UADeviceInfo", new Object[0]);
    long l1 = System.currentTimeMillis();
    e.a(paramContext);
    this.b = e.a();
    new StringBuilder().append(e.b()).toString();
    DisplayMetrics localDisplayMetrics = e.g(paramContext);
    String str1;
    String str2;
    label449: int i1;
    if (localDisplayMetrics == null)
    {
      str1 = "null";
      this.c = str1;
      this.d = e.d();
      this.e = "";
      this.f = (e.e() + "m");
      this.g = (e.f() + "m");
      this.h = e.g();
      this.i = e.h();
      this.j = e.i(paramContext);
      this.k = e.i();
      this.l = c(paramContext);
      this.m = (s() + "m");
      this.n = v();
      this.o = r();
      if (!t())
        break label570;
      str2 = "Y";
      this.p = str2;
      this.q = e(paramContext);
      if ((!d(paramContext)) || (!u()))
        break label577;
      i1 = 1;
      label481: if (i1 == 0)
        break label583;
    }
    label570: label577: label583: for (String str3 = "Y"; ; str3 = "N")
    {
      this.r = str3;
      long l2 = System.currentTimeMillis() - l1;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(l2);
      arrayOfObject[1] = toString();
      a.b(" detail create cost} %d  values:\n} %s", arrayOfObject);
      return;
      str1 = localDisplayMetrics.widthPixels + "*" + localDisplayMetrics.heightPixels;
      break;
      str2 = "N";
      break label449;
      i1 = 0;
      break label481;
    }
  }

  public static k a(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((a == null) && (paramContext != null))
        a = new k(paramContext);
      k localk = a;
      return localk;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString)))
      return false;
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static String b(Context paramContext)
  {
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    if (localLocationManager == null)
      return "N";
    List localList = localLocationManager.getAllProviders();
    if (localList == null)
      return "N";
    if (localList.contains("gps"))
      return "Y";
    return "N";
  }

  private static int c(Context paramContext)
  {
    if (paramContext == null)
      return 160;
    while (true)
    {
      try
      {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        if (localDisplayMetrics.density == 1.0F)
          break;
        if (localDisplayMetrics.density <= 0.75D)
          return 120;
        if (localDisplayMetrics.density == 1.5D)
          return 240;
        float f1 = localDisplayMetrics.density;
        if (f1 == 2.0D)
        {
          i1 = 320;
          return i1;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return 160;
      }
      int i1 = 160;
    }
  }

  private boolean d(Context paramContext)
  {
    int i1 = 1;
    if (paramContext == null)
      return false;
    File localFile = new File("/data/data/root");
    try
    {
      localFile.createNewFile();
      if (localFile.exists())
        localFile.delete();
      return i1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        String[] arrayOfString = new String[4];
        arrayOfString[0] = "com.noshufou.android.su";
        arrayOfString[i1] = "com.miui.uac";
        arrayOfString[2] = "eu.chainfire.supersu";
        arrayOfString[3] = "com.lbe.security.miui";
        int i2 = arrayOfString.length;
        for (int i3 = 0; ; i3++)
        {
          if (i3 >= i2)
            break label107;
          if (a(arrayOfString[i3], paramContext))
            break;
        }
        label107: i1 = 0;
      }
    }
  }

  private String e(Context paramContext)
  {
    if (paramContext == null)
    {
      a.d("getSensor2 but context == null!", new Object[0]);
      return null;
    }
    a.a("getSensor2 start", new Object[0]);
    String str1 = "X";
    String str2 = "X";
    String str3 = "X";
    int i1;
    String str4;
    if ((WifiManager)paramContext.getSystemService("wifi") == null)
    {
      i1 = 0;
      if (i1 == 0)
        break label190;
      str4 = "Y";
      label64: if (Integer.parseInt(Build.VERSION.SDK) < 10)
        break label254;
    }
    while (true)
    {
      try
      {
        SensorManager localSensorManager = (SensorManager)paramContext.getSystemService("sensor");
        if (localSensorManager.getDefaultSensor(1) == null)
          continue;
        str1 = "Y";
        if (localSensorManager.getDefaultSensor(5) == null)
          break label272;
        str2 = "Y";
        if (BluetoothAdapter.getDefaultAdapter() != null)
          break label278;
        str3 = "N";
        if (!paramContext.getPackageManager().hasSystemFeature("android.hardware.nfc"))
          continue;
        str5 = "Y";
        localObject = str3;
        str6 = str2;
        str7 = str1;
        return str4 + str7 + str6 + (String)localObject + str5;
        i1 = 1;
        break;
        label190: str4 = "N";
        break label64;
        str1 = "N";
        continue;
        str5 = "N";
        localObject = str3;
        str6 = str2;
        str7 = str1;
        continue;
      }
      catch (Throwable localThrowable)
      {
        String str8 = str3;
        str6 = str2;
        str7 = str1;
        a.d("getSensor2 error!", new Object[0]);
        localObject = str8;
        str5 = "X";
        continue;
      }
      label254: String str5 = "X";
      Object localObject = str3;
      String str6 = str2;
      String str7 = str1;
      continue;
      label272: str2 = "N";
      continue;
      label278: str3 = "Y";
    }
  }

  // ERROR //
  private static String r()
  {
    // Byte code:
    //   0: ldc 34
    //   2: astore_0
    //   3: iconst_2
    //   4: anewarray 185	java/lang/String
    //   7: dup
    //   8: iconst_0
    //   9: ldc_w 325
    //   12: aastore
    //   13: dup
    //   14: iconst_1
    //   15: ldc_w 327
    //   18: aastore
    //   19: astore_1
    //   20: new 329	java/io/BufferedReader
    //   23: dup
    //   24: new 331	java/io/InputStreamReader
    //   27: dup
    //   28: invokestatic 337	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   31: aload_1
    //   32: invokevirtual 341	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   35: invokevirtual 347	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   38: invokespecial 350	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   41: invokespecial 353	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   44: astore_2
    //   45: aload_2
    //   46: invokevirtual 356	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   49: astore 9
    //   51: aload 9
    //   53: ifnull +37 -> 90
    //   56: aload 9
    //   58: ldc_w 358
    //   61: invokevirtual 361	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   64: ifeq -19 -> 45
    //   67: aload 9
    //   69: iconst_1
    //   70: aload 9
    //   72: ldc_w 363
    //   75: invokevirtual 366	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   78: iadd
    //   79: invokevirtual 370	java/lang/String:substring	(I)Ljava/lang/String;
    //   82: invokevirtual 373	java/lang/String:trim	()Ljava/lang/String;
    //   85: astore 10
    //   87: aload 10
    //   89: astore_0
    //   90: aload_2
    //   91: invokevirtual 376	java/io/BufferedReader:close	()V
    //   94: aload_0
    //   95: areturn
    //   96: astore 11
    //   98: aload 11
    //   100: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   103: aload_0
    //   104: areturn
    //   105: astore_3
    //   106: aconst_null
    //   107: astore_2
    //   108: aload_3
    //   109: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   112: aload_2
    //   113: ifnull -19 -> 94
    //   116: aload_2
    //   117: invokevirtual 376	java/io/BufferedReader:close	()V
    //   120: aload_0
    //   121: areturn
    //   122: astore 6
    //   124: aload 6
    //   126: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   129: aload_0
    //   130: areturn
    //   131: astore 7
    //   133: aconst_null
    //   134: astore_2
    //   135: aload 7
    //   137: invokevirtual 250	java/lang/Throwable:printStackTrace	()V
    //   140: aload_2
    //   141: ifnull -47 -> 94
    //   144: aload_2
    //   145: invokevirtual 376	java/io/BufferedReader:close	()V
    //   148: aload_0
    //   149: areturn
    //   150: astore 8
    //   152: aload 8
    //   154: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   157: aload_0
    //   158: areturn
    //   159: astore 4
    //   161: aconst_null
    //   162: astore_2
    //   163: aload_2
    //   164: ifnull +7 -> 171
    //   167: aload_2
    //   168: invokevirtual 376	java/io/BufferedReader:close	()V
    //   171: aload 4
    //   173: athrow
    //   174: astore 5
    //   176: aload 5
    //   178: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   181: goto -10 -> 171
    //   184: astore 4
    //   186: goto -23 -> 163
    //   189: astore 7
    //   191: goto -56 -> 135
    //   194: astore_3
    //   195: goto -87 -> 108
    //
    // Exception table:
    //   from	to	target	type
    //   90	94	96	java/io/IOException
    //   20	45	105	java/io/IOException
    //   116	120	122	java/io/IOException
    //   20	45	131	java/lang/Throwable
    //   144	148	150	java/io/IOException
    //   20	45	159	finally
    //   167	171	174	java/io/IOException
    //   45	51	184	finally
    //   56	87	184	finally
    //   108	112	184	finally
    //   135	140	184	finally
    //   45	51	189	java/lang/Throwable
    //   56	87	189	java/lang/Throwable
    //   45	51	194	java/io/IOException
    //   56	87	194	java/io/IOException
  }

  // ERROR //
  private static int s()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: iconst_2
    //   3: anewarray 185	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc_w 325
    //   11: aastore
    //   12: dup
    //   13: iconst_1
    //   14: ldc_w 379
    //   17: aastore
    //   18: astore_1
    //   19: new 329	java/io/BufferedReader
    //   22: dup
    //   23: new 331	java/io/InputStreamReader
    //   26: dup
    //   27: invokestatic 337	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   30: aload_1
    //   31: invokevirtual 341	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   34: invokevirtual 347	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   37: invokespecial 350	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   40: invokespecial 353	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   43: astore_2
    //   44: aload_2
    //   45: invokevirtual 356	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   48: astore 10
    //   50: iconst_0
    //   51: istore 6
    //   53: aload 10
    //   55: ifnull +17 -> 72
    //   58: aload 10
    //   60: invokevirtual 373	java/lang/String:trim	()Ljava/lang/String;
    //   63: invokestatic 301	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   66: sipush 1000
    //   69: idiv
    //   70: istore 6
    //   72: aload_2
    //   73: invokevirtual 376	java/io/BufferedReader:close	()V
    //   76: iload 6
    //   78: ireturn
    //   79: astore 11
    //   81: aload 11
    //   83: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   86: iload 6
    //   88: ireturn
    //   89: astore_3
    //   90: aconst_null
    //   91: astore_2
    //   92: aload_3
    //   93: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   96: iconst_0
    //   97: istore 6
    //   99: aload_2
    //   100: ifnull -24 -> 76
    //   103: aload_2
    //   104: invokevirtual 376	java/io/BufferedReader:close	()V
    //   107: iconst_0
    //   108: ireturn
    //   109: astore 7
    //   111: aload 7
    //   113: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   116: iconst_0
    //   117: ireturn
    //   118: astore 8
    //   120: aload 8
    //   122: invokevirtual 250	java/lang/Throwable:printStackTrace	()V
    //   125: iconst_0
    //   126: istore 6
    //   128: aload_0
    //   129: ifnull -53 -> 76
    //   132: aload_0
    //   133: invokevirtual 376	java/io/BufferedReader:close	()V
    //   136: iconst_0
    //   137: ireturn
    //   138: astore 9
    //   140: aload 9
    //   142: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   145: iconst_0
    //   146: ireturn
    //   147: astore 4
    //   149: aload_0
    //   150: ifnull +7 -> 157
    //   153: aload_0
    //   154: invokevirtual 376	java/io/BufferedReader:close	()V
    //   157: aload 4
    //   159: athrow
    //   160: astore 5
    //   162: aload 5
    //   164: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   167: goto -10 -> 157
    //   170: astore 4
    //   172: aload_2
    //   173: astore_0
    //   174: goto -25 -> 149
    //   177: astore 8
    //   179: aload_2
    //   180: astore_0
    //   181: goto -61 -> 120
    //   184: astore_3
    //   185: goto -93 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   72	76	79	java/io/IOException
    //   19	44	89	java/io/IOException
    //   103	107	109	java/io/IOException
    //   19	44	118	java/lang/Throwable
    //   132	136	138	java/io/IOException
    //   19	44	147	finally
    //   120	125	147	finally
    //   153	157	160	java/io/IOException
    //   44	50	170	finally
    //   58	72	170	finally
    //   92	96	170	finally
    //   44	50	177	java/lang/Throwable
    //   58	72	177	java/lang/Throwable
    //   44	50	184	java/io/IOException
    //   58	72	184	java/io/IOException
  }

  // ERROR //
  private static boolean t()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: iconst_2
    //   3: anewarray 185	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc_w 325
    //   11: aastore
    //   12: dup
    //   13: iconst_1
    //   14: ldc_w 327
    //   17: aastore
    //   18: astore_1
    //   19: new 329	java/io/BufferedReader
    //   22: dup
    //   23: new 331	java/io/InputStreamReader
    //   26: dup
    //   27: invokestatic 337	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   30: aload_1
    //   31: invokevirtual 341	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   34: invokevirtual 347	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   37: invokespecial 350	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   40: invokespecial 353	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   43: astore_2
    //   44: aload_2
    //   45: invokevirtual 356	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   48: astore 10
    //   50: iconst_0
    //   51: istore 6
    //   53: aload 10
    //   55: ifnull +25 -> 80
    //   58: aload 10
    //   60: invokevirtual 382	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   63: ldc_w 384
    //   66: invokevirtual 366	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   69: istore 11
    //   71: iconst_m1
    //   72: iload 11
    //   74: if_icmpeq -30 -> 44
    //   77: iconst_1
    //   78: istore 6
    //   80: aload_2
    //   81: invokevirtual 376	java/io/BufferedReader:close	()V
    //   84: iload 6
    //   86: ireturn
    //   87: astore 12
    //   89: aload 12
    //   91: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   94: iload 6
    //   96: ireturn
    //   97: astore_3
    //   98: aconst_null
    //   99: astore_2
    //   100: aload_3
    //   101: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   104: iconst_0
    //   105: istore 6
    //   107: aload_2
    //   108: ifnull -24 -> 84
    //   111: aload_2
    //   112: invokevirtual 376	java/io/BufferedReader:close	()V
    //   115: iconst_0
    //   116: ireturn
    //   117: astore 7
    //   119: aload 7
    //   121: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   124: iconst_0
    //   125: ireturn
    //   126: astore 8
    //   128: aload 8
    //   130: invokevirtual 250	java/lang/Throwable:printStackTrace	()V
    //   133: iconst_0
    //   134: istore 6
    //   136: aload_0
    //   137: ifnull -53 -> 84
    //   140: aload_0
    //   141: invokevirtual 376	java/io/BufferedReader:close	()V
    //   144: iconst_0
    //   145: ireturn
    //   146: astore 9
    //   148: aload 9
    //   150: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   153: iconst_0
    //   154: ireturn
    //   155: astore 4
    //   157: aload_0
    //   158: ifnull +7 -> 165
    //   161: aload_0
    //   162: invokevirtual 376	java/io/BufferedReader:close	()V
    //   165: aload 4
    //   167: athrow
    //   168: astore 5
    //   170: aload 5
    //   172: invokevirtual 377	java/io/IOException:printStackTrace	()V
    //   175: goto -10 -> 165
    //   178: astore 4
    //   180: aload_2
    //   181: astore_0
    //   182: goto -25 -> 157
    //   185: astore 8
    //   187: aload_2
    //   188: astore_0
    //   189: goto -61 -> 128
    //   192: astore_3
    //   193: goto -93 -> 100
    //
    // Exception table:
    //   from	to	target	type
    //   80	84	87	java/io/IOException
    //   19	44	97	java/io/IOException
    //   111	115	117	java/io/IOException
    //   19	44	126	java/lang/Throwable
    //   140	144	146	java/io/IOException
    //   19	44	155	finally
    //   128	133	155	finally
    //   161	165	168	java/io/IOException
    //   44	50	178	finally
    //   58	71	178	finally
    //   100	104	178	finally
    //   44	50	185	java/lang/Throwable
    //   58	71	185	java/lang/Throwable
    //   44	50	192	java/io/IOException
    //   58	71	192	java/io/IOException
  }

  private static boolean u()
  {
    a.a("getIsRootByFile", new Object[0]);
    String[] arrayOfString = { "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/" };
    int i1 = 0;
    try
    {
      while (i1 < arrayOfString.length)
      {
        boolean bool = new File(arrayOfString[i1] + "su").exists();
        if (bool)
          return true;
        i1++;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private int v()
  {
    try
    {
      File[] arrayOfFile = new File("/sys/devices/system/cpu/").listFiles(new a());
      if (arrayOfFile == null)
        return 1;
      int i1 = arrayOfFile.length;
      return i1;
    }
    catch (Exception localException)
    {
      a.d("CPU Count: Failed.", new Object[0]);
      localException.printStackTrace();
    }
    return 1;
  }

  public final String a()
  {
    monitorenter;
    try
    {
      String str = this.b;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String b()
  {
    monitorenter;
    try
    {
      String str = this.c;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String c()
  {
    monitorenter;
    try
    {
      String str = this.d;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String d()
  {
    monitorenter;
    try
    {
      String str = this.e;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String e()
  {
    monitorenter;
    try
    {
      String str = this.f;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String f()
  {
    monitorenter;
    try
    {
      String str = this.g;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String g()
  {
    monitorenter;
    try
    {
      String str = this.h;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String h()
  {
    monitorenter;
    try
    {
      String str = this.i;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String i()
  {
    monitorenter;
    try
    {
      String str = this.j;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String j()
  {
    monitorenter;
    try
    {
      String str = this.k;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final String k()
  {
    return this.l;
  }

  public final String l()
  {
    return this.m;
  }

  public final String m()
  {
    return this.n;
  }

  public final String n()
  {
    return this.o;
  }

  public final String o()
  {
    return this.p;
  }

  public final String p()
  {
    return this.q;
  }

  public final String q()
  {
    return this.r;
  }

  final class a
    implements FileFilter
  {
    a()
    {
    }

    public final boolean accept(File paramFile)
    {
      return Pattern.matches("cpu[0-9]", paramFile.getName());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.k
 * JD-Core Version:    0.6.0
 */