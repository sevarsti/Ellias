package com.tencent.qqgamemi.protocol;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiApplication;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;

public class QMiJceCommonData
{
  public static final long a = 2L;
  public static int b = 0;
  public static String c = "http://gamehall.3g.qq.com";
  public static String d = "http://gamehall.3g.qq.com/cobrahall/m";
  public static String e = "http://gamehall.3g.qq.com/gamejoy/m";
  public static String f = "1213621180";
  public static short g = 0;
  public static int h = 0;
  public static byte i = 0;
  public static String j = "";
  public static String k = "0";
  public static String l = "";
  public static long m = 0L;
  public static Long n = Long.valueOf(0xFFFFFFFF & m);
  public static long o = 0L;
  public static String p = "";
  public static short q = 0;
  public static String r = Build.BRAND + "%" + Build.MANUFACTURER + "%" + Build.MODEL;
  public static String s = Build.VERSION.RELEASE;
  public static int t = 0;
  public static String u = "";
  public static String v = "";
  public static String w = "0";
  public static String x = "0";
  public static String y = "0";
  private static final String z = "QMiJceCommonData";

  public static String a()
  {
    return c;
  }

  public static String a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "";
    case 1:
      return "test";
    case 2:
      return "prepublic";
    case 3:
      return "public";
    case 4:
    }
    return "develop";
  }

  public static void a(long paramLong)
  {
    m = paramLong;
  }

  public static void a(Context paramContext)
  {
    try
    {
      c(150);
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      if (paramContext != null)
      {
        WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
        if ((localWindowManager != null) && (localWindowManager.getDefaultDisplay() != null))
          localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        b(localDisplayMetrics.densityDpi);
        e(localDisplayMetrics.widthPixels + "_" + localDisplayMetrics.heightPixels);
        a(ServerType.a(paramContext));
      }
      if (QMiConfig.b())
        q = 3;
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("QMiJceCommonData", "init QMiJceCommonData fail");
      localException.printStackTrace();
    }
  }

  private static void a(ServerType paramServerType)
  {
    b(paramServerType.B);
    c(paramServerType.C);
    f(paramServerType.D);
    d(paramServerType.E);
  }

  public static void a(String paramString)
  {
    TLog.c("QMiJceCommonData", "setServerType:" + paramString);
    ServerType localServerType = ServerType.a(paramString);
    if (localServerType != null)
      a(localServerType);
  }

  public static String b()
  {
    if (QMiConfig.c() == 2)
      return d;
    return c();
  }

  public static void b(int paramInt)
  {
    t = paramInt;
  }

  public static void b(String paramString)
  {
    TLog.c("QMiJceCommonData", "setServerAddress:" + paramString);
    c = paramString;
  }

  public static String c()
  {
    return e;
  }

  public static void c(int paramInt)
  {
    h = paramInt;
  }

  public static void c(String paramString)
  {
    TLog.c("QMiJceCommonData", "setServerUrl:" + paramString);
    d = paramString;
  }

  public static int d()
  {
    return t;
  }

  public static void d(String paramString)
  {
    TLog.c("QMiJceCommonData", "setServerNewUrl:" + paramString);
    e = paramString;
  }

  public static String e()
  {
    return l;
  }

  public static void e(String paramString)
  {
    l = paramString;
  }

  public static int f()
  {
    return h;
  }

  public static void f(String paramString)
  {
    f = paramString;
  }

  public static String g()
  {
    return f;
  }

  public static void g(String paramString)
  {
    j = paramString;
  }

  public static String h()
  {
    return j;
  }

  public static void h(String paramString)
  {
    k = paramString;
  }

  public static String i()
  {
    return k;
  }

  public static void i(String paramString)
  {
    u = paramString;
  }

  public static long j()
  {
    return m;
  }

  public static void j(String paramString)
  {
    v = paramString;
  }

  public static long k()
  {
    return m;
  }

  public static void k(String paramString)
  {
    w = paramString;
  }

  public static String l()
  {
    if (TextUtils.isEmpty(u))
    {
      u = m();
      TLog.c("QMiJceCommonData", "getImei:" + u);
    }
    return u;
  }

  public static void l(String paramString)
  {
    x = paramString;
  }

  public static String m()
  {
    if (QMiApplication.a().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0)
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)QMiApplication.a().getSystemService("phone");
        if ((localTelephonyManager != null) && (localTelephonyManager.getDeviceId() != null))
          return localTelephonyManager.getDeviceId();
        String str = o();
        return str;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    return "0";
  }

  public static void m(String paramString)
  {
    y = paramString;
  }

  public static String n()
  {
    if (TextUtils.isEmpty(v))
      v = o();
    return v;
  }

  public static String o()
  {
    if (QMiApplication.a().checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0)
      try
      {
        WifiInfo localWifiInfo = ((WifiManager)QMiApplication.a().getSystemService("wifi")).getConnectionInfo();
        if (localWifiInfo != null)
        {
          String str = localWifiInfo.getMacAddress();
          if (str != null)
          {
            int i1 = str.length();
            if (i1 > 0)
              return str;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    return "00-00-00-00-00-00";
  }

  public static String p()
  {
    return w;
  }

  public static String q()
  {
    if (x.length() < 2)
      x = r();
    return x;
  }

  // ERROR //
  public static String r()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 326	java/io/BufferedReader
    //   5: dup
    //   6: new 328	java/io/FileReader
    //   9: dup
    //   10: ldc_w 330
    //   13: invokespecial 332	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   16: sipush 8192
    //   19: invokespecial 335	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 338	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore 5
    //   29: aload_1
    //   30: invokevirtual 341	java/io/BufferedReader:close	()V
    //   33: aload_1
    //   34: ifnull +7 -> 41
    //   37: aload_1
    //   38: invokevirtual 341	java/io/BufferedReader:close	()V
    //   41: aload 5
    //   43: invokevirtual 344	java/lang/String:trim	()Ljava/lang/String;
    //   46: areturn
    //   47: astore 7
    //   49: aload 7
    //   51: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   54: goto -13 -> 41
    //   57: astore_2
    //   58: aconst_null
    //   59: astore_1
    //   60: aload_2
    //   61: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   64: ldc 75
    //   66: astore 5
    //   68: aload_1
    //   69: ifnull -28 -> 41
    //   72: aload_1
    //   73: invokevirtual 341	java/io/BufferedReader:close	()V
    //   76: goto -35 -> 41
    //   79: astore 6
    //   81: aload 6
    //   83: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   86: goto -45 -> 41
    //   89: astore_3
    //   90: aload_0
    //   91: ifnull +7 -> 98
    //   94: aload_0
    //   95: invokevirtual 341	java/io/BufferedReader:close	()V
    //   98: aload_3
    //   99: athrow
    //   100: astore 4
    //   102: aload 4
    //   104: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   107: goto -9 -> 98
    //   110: astore_3
    //   111: aload_1
    //   112: astore_0
    //   113: goto -23 -> 90
    //   116: astore_2
    //   117: goto -57 -> 60
    //
    // Exception table:
    //   from	to	target	type
    //   37	41	47	java/io/IOException
    //   2	23	57	java/io/IOException
    //   72	76	79	java/io/IOException
    //   2	23	89	finally
    //   94	98	100	java/io/IOException
    //   23	33	110	finally
    //   60	64	110	finally
    //   23	33	116	java/io/IOException
  }

  // ERROR //
  public static int s()
  {
    // Byte code:
    //   0: invokestatic 283	com/tencent/qqgamemi/QMiApplication:a	()Landroid/content/Context;
    //   3: ldc_w 347
    //   6: invokevirtual 172	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 349	android/app/ActivityManager
    //   12: invokevirtual 353	android/app/ActivityManager:getDeviceConfigurationInfo	()Landroid/content/pm/ConfigurationInfo;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull +77 -> 94
    //   20: aload_3
    //   21: getfield 358	android/content/pm/ConfigurationInfo:reqGlEsVersion	I
    //   24: istore 4
    //   26: iload 4
    //   28: ldc_w 359
    //   31: iand
    //   32: bipush 16
    //   34: ishr
    //   35: istore_1
    //   36: ldc 43
    //   38: new 99	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   45: ldc_w 361
    //   48: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_3
    //   52: invokevirtual 364	android/content/pm/ConfigurationInfo:getGlEsVersion	()Ljava/lang/String;
    //   55: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: ldc_w 366
    //   61: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: aload_3
    //   65: getfield 358	android/content/pm/ConfigurationInfo:reqGlEsVersion	I
    //   68: invokevirtual 195	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   71: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokestatic 368	com/tencent/component/utils/log/LogUtil:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   77: iload_1
    //   78: ireturn
    //   79: astore_0
    //   80: iconst_0
    //   81: istore_1
    //   82: aload_0
    //   83: astore_2
    //   84: aload_2
    //   85: invokevirtual 225	java/lang/Exception:printStackTrace	()V
    //   88: iload_1
    //   89: ireturn
    //   90: astore_2
    //   91: goto -7 -> 84
    //   94: iconst_0
    //   95: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	16	79	java/lang/Exception
    //   20	26	79	java/lang/Exception
    //   36	77	90	java/lang/Exception
  }

  public static String t()
  {
    if (y.length() < 2)
      y = u();
    return y;
  }

  // ERROR //
  public static String u()
  {
    // Byte code:
    //   0: new 328	java/io/FileReader
    //   3: dup
    //   4: ldc_w 372
    //   7: invokespecial 332	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 326	java/io/BufferedReader
    //   14: dup
    //   15: aload_0
    //   16: sipush 8192
    //   19: invokespecial 335	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 338	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore 5
    //   29: aload_1
    //   30: invokevirtual 341	java/io/BufferedReader:close	()V
    //   33: aload_0
    //   34: invokevirtual 373	java/io/FileReader:close	()V
    //   37: aload_1
    //   38: ifnull +11 -> 49
    //   41: aload_1
    //   42: invokevirtual 341	java/io/BufferedReader:close	()V
    //   45: aload_0
    //   46: invokevirtual 373	java/io/FileReader:close	()V
    //   49: aload 5
    //   51: ldc_w 375
    //   54: invokevirtual 379	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   57: astore 6
    //   59: aload 6
    //   61: ifnull +147 -> 208
    //   64: aload 6
    //   66: arraylength
    //   67: iconst_1
    //   68: if_icmple +140 -> 208
    //   71: aload 6
    //   73: iconst_1
    //   74: aaload
    //   75: astore 7
    //   77: ldc 43
    //   79: new 99	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   86: ldc_w 381
    //   89: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload 7
    //   94: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   100: invokestatic 251	com/tencent/qqgamemi/common/TLog:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   103: aload 7
    //   105: invokevirtual 344	java/lang/String:trim	()Ljava/lang/String;
    //   108: areturn
    //   109: astore 9
    //   111: aload 9
    //   113: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   116: goto -67 -> 49
    //   119: astore_2
    //   120: aconst_null
    //   121: astore_1
    //   122: aconst_null
    //   123: astore_0
    //   124: aload_2
    //   125: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   128: aload_1
    //   129: ifnull +86 -> 215
    //   132: aload_1
    //   133: invokevirtual 341	java/io/BufferedReader:close	()V
    //   136: aload_0
    //   137: invokevirtual 373	java/io/FileReader:close	()V
    //   140: aconst_null
    //   141: astore 5
    //   143: goto -94 -> 49
    //   146: astore 8
    //   148: aload 8
    //   150: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   153: aconst_null
    //   154: astore 5
    //   156: goto -107 -> 49
    //   159: astore_3
    //   160: aconst_null
    //   161: astore_1
    //   162: aconst_null
    //   163: astore_0
    //   164: aload_1
    //   165: ifnull +11 -> 176
    //   168: aload_1
    //   169: invokevirtual 341	java/io/BufferedReader:close	()V
    //   172: aload_0
    //   173: invokevirtual 373	java/io/FileReader:close	()V
    //   176: aload_3
    //   177: athrow
    //   178: astore 4
    //   180: aload 4
    //   182: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   185: goto -9 -> 176
    //   188: astore_3
    //   189: aconst_null
    //   190: astore_1
    //   191: goto -27 -> 164
    //   194: astore_3
    //   195: goto -31 -> 164
    //   198: astore_2
    //   199: aconst_null
    //   200: astore_1
    //   201: goto -77 -> 124
    //   204: astore_2
    //   205: goto -81 -> 124
    //   208: ldc 75
    //   210: astore 7
    //   212: goto -135 -> 77
    //   215: aconst_null
    //   216: astore 5
    //   218: goto -169 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   41	49	109	java/io/IOException
    //   0	11	119	java/io/IOException
    //   132	140	146	java/io/IOException
    //   0	11	159	finally
    //   168	176	178	java/io/IOException
    //   11	23	188	finally
    //   23	37	194	finally
    //   124	128	194	finally
    //   11	23	198	java/io/IOException
    //   23	37	204	java/io/IOException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.QMiJceCommonData
 * JD-Core Version:    0.6.0
 */