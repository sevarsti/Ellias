package com.tencent.stat.common;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.stat.StatConfig;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.json.JSONObject;

public class k
{
  private static String a = null;
  private static String b = null;
  private static String c = null;
  private static String d = null;
  private static Random e = null;
  private static StatLogger f = null;
  private static String g = null;
  private static l h = null;
  private static n i = null;
  private static String j = "__MTA_FIRST_ACTIVATE__";
  private static int k = -1;

  public static String A(Context paramContext)
  {
    while (true)
    {
      int m;
      try
      {
        SensorManager localSensorManager = (SensorManager)paramContext.getSystemService("sensor");
        if (localSensorManager != null)
        {
          List localList = localSensorManager.getSensorList(-1);
          if (localList != null)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            m = 0;
            if (m >= localList.size())
              continue;
            localStringBuilder.append(((Sensor)localList.get(m)).getType());
            if (m == -1 + localList.size())
              break label112;
            localStringBuilder.append(",");
            break label112;
            String str = localStringBuilder.toString();
            return str;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        f.e(localThrowable);
      }
      return "";
      label112: m++;
    }
  }

  public static WifiInfo B(Context paramContext)
  {
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getApplicationContext().getSystemService("wifi");
      if (localWifiManager != null)
        return localWifiManager.getConnectionInfo();
    }
    return null;
  }

  public static String C(Context paramContext)
  {
    try
    {
      WifiInfo localWifiInfo = B(paramContext);
      if (localWifiInfo != null)
      {
        String str = localWifiInfo.getBSSID();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return null;
  }

  public static String D(Context paramContext)
  {
    try
    {
      WifiInfo localWifiInfo = B(paramContext);
      if (localWifiInfo != null)
      {
        String str = localWifiInfo.getSSID();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return null;
  }

  public static int E(Context paramContext)
  {
    monitorenter;
    try
    {
      if (k != -1);
      for (int m = k; ; m = k)
      {
        return m;
        F(paramContext);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void F(Context paramContext)
  {
    k = p.a(paramContext, j, 1);
    f.e(Integer.valueOf(k));
    if (k == 1)
      p.b(paramContext, j, 0);
  }

  private static long G(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }

  public static int a()
  {
    return h().nextInt(2147483647);
  }

  public static Long a(String paramString1, String paramString2, int paramInt1, int paramInt2, Long paramLong)
  {
    if ((paramString1 == null) || (paramString2 == null));
    String[] arrayOfString;
    do
    {
      return paramLong;
      if ((paramString2.equalsIgnoreCase(".")) || (paramString2.equalsIgnoreCase("|")))
        paramString2 = "\\" + paramString2;
      arrayOfString = paramString1.split(paramString2);
    }
    while (arrayOfString.length != paramInt2);
    try
    {
      Object localObject = Long.valueOf(0L);
      int m = 0;
      while (m < arrayOfString.length)
      {
        Long localLong = Long.valueOf(paramInt1 * (((Long)localObject).longValue() + Long.valueOf(arrayOfString[m]).longValue()));
        m++;
        localObject = localLong;
      }
      return localObject;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return (Long)paramLong;
  }

  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyyMMdd").format(new Date(paramLong));
  }

  public static String a(String paramString)
  {
    if (paramString == null)
      return "0";
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer();
      for (int m = 0; m < arrayOfByte.length; m++)
      {
        int n = 0xFF & arrayOfByte[m];
        if (n < 16)
          localStringBuffer.append("0");
        localStringBuffer.append(Integer.toHexString(n));
      }
      String str = localStringBuffer.toString();
      return str;
    }
    catch (Throwable localThrowable)
    {
    }
    return "0";
  }

  public static HttpHost a(Context paramContext)
  {
    if (paramContext == null)
      return null;
    String str;
    do
      try
      {
        if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
          return null;
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null)
          return null;
        if ((localNetworkInfo.getTypeName() != null) && (localNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")))
          return null;
        str = localNetworkInfo.getExtraInfo();
        if (str == null)
          return null;
        if ((!str.equals("cmwap")) && (!str.equals("3gwap")) && (!str.equals("uniwap")))
          continue;
        HttpHost localHttpHost1 = new HttpHost("10.0.0.172", 80);
        return localHttpHost1;
      }
      catch (Throwable localThrowable)
      {
        f.e(localThrowable);
        return null;
      }
    while (!str.equals("ctwap"));
    HttpHost localHttpHost2 = new HttpHost("10.0.0.200", 80);
    return localHttpHost2;
  }

  public static void a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if (paramString2 != null);
    try
    {
      if (paramString2.length() > 0)
        paramJSONObject.put(paramString1, paramString2);
      return;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
  }

  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      int m = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      int n = 0;
      if (m == 0)
        n = 1;
      return n;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return false;
  }

  public static byte[] a(byte[] paramArrayOfByte)
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
    byte[] arrayOfByte1 = new byte[4096];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(2 * paramArrayOfByte.length);
    while (true)
    {
      int m = localGZIPInputStream.read(arrayOfByte1);
      if (m == -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte1, 0, m);
    }
    byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
    localByteArrayInputStream.close();
    localGZIPInputStream.close();
    localByteArrayOutputStream.close();
    return arrayOfByte2;
  }

  public static long b(String paramString)
  {
    return a(paramString, ".", 100, 3, Long.valueOf(0L)).longValue();
  }

  public static StatLogger b()
  {
    monitorenter;
    try
    {
      if (f == null)
      {
        f = new StatLogger("MtaSDK");
        f.setDebugEnable(false);
      }
      StatLogger localStatLogger = f;
      return localStatLogger;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String b(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((a != null) && (a.trim().length() != 0));
      for (String str = a; ; str = a)
      {
        return str;
        a = l(paramContext);
        if ((a != null) && (a.trim().length() != 0))
          continue;
        a = Integer.toString(h().nextInt(2147483647));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String b(Context paramContext, String paramString)
  {
    if (StatConfig.isEnableConcurrentProcess() == true)
    {
      if (g == null)
        g = u(paramContext);
      if (g != null)
        paramString = paramString + "_" + g;
    }
    return paramString;
  }

  public static long c()
  {
    try
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(11, 0);
      localCalendar.set(12, 0);
      localCalendar.set(13, 0);
      localCalendar.set(14, 0);
      long l = localCalendar.getTimeInMillis();
      return l + 86400000L;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return 86400000L + System.currentTimeMillis();
  }

  public static String c(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((c == null) || ("" == c))
        c = f(paramContext);
      String str = c;
      return str;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String c(String paramString)
  {
    if (paramString == null)
      paramString = null;
    do
      return paramString;
    while (Build.VERSION.SDK_INT < 8);
    try
    {
      String str = new String(g.b(e.a(paramString.getBytes("UTF-8")), 0), "UTF-8");
      return str;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return paramString;
  }

  public static int d()
  {
    return Build.VERSION.SDK_INT;
  }

  public static DisplayMetrics d(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }

  public static String d(String paramString)
  {
    if (paramString == null)
      paramString = null;
    do
      return paramString;
    while (Build.VERSION.SDK_INT < 8);
    try
    {
      String str = new String(e.b(g.a(paramString.getBytes("UTF-8"), 0)), "UTF-8");
      return str;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return paramString;
  }

  public static String e()
  {
    long l1 = f() / 1000000L;
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l2 = localStatFs.getBlockSize() * localStatFs.getAvailableBlocks() / 1000000L;
    return String.valueOf(l2) + "/" + String.valueOf(l1);
  }

  public static boolean e(Context paramContext)
  {
    try
    {
      if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
        if (localConnectivityManager == null)
          break label102;
        NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
        if (arrayOfNetworkInfo == null)
          break label102;
        m = 0;
        if (m >= arrayOfNetworkInfo.length)
          break label102;
        if ((arrayOfNetworkInfo[m].getTypeName().equalsIgnoreCase("WIFI")) && (arrayOfNetworkInfo[m].isConnected()))
          return true;
      }
      else
      {
        f.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        int m;
        f.e(localThrowable);
        continue;
        m++;
      }
    }
    label102: return false;
  }

  public static long f()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount();
  }

  public static String f(Context paramContext)
  {
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      try
      {
        WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
        if (localWifiManager == null)
          return "";
        String str = localWifiManager.getConnectionInfo().getMacAddress();
        return str;
      }
      catch (Exception localException)
      {
        f.e(localException);
        return "";
      }
    f.e("Could not get permission of android.permission.ACCESS_WIFI_STATE");
    return "";
  }

  public static boolean g(Context paramContext)
  {
    try
    {
      if ((a(paramContext, "android.permission.INTERNET")) && (a(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager != null)
        {
          NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
          if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()) || (!localNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")))
            break label88;
          return true;
        }
      }
      else
      {
        f.warn("can not get the permisson of android.permission.INTERNET");
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        f.e(localThrowable);
    }
    label88: return false;
  }

  private static Random h()
  {
    monitorenter;
    try
    {
      if (e == null)
        e = new Random();
      Random localRandom = e;
      return localRandom;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static boolean h(Context paramContext)
  {
    try
    {
      if ((a(paramContext, "android.permission.INTERNET")) && (a(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager != null)
        {
          NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
          if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
          f.w("Network error");
          return false;
        }
      }
      else
      {
        f.warn("can not get the permisson of android.permission.INTERNET");
      }
      label73: return false;
    }
    catch (Throwable localThrowable)
    {
      break label73;
    }
  }

  private static long i()
  {
    long l = 0L;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l = 1024 * Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue();
      localBufferedReader.close();
      return l;
    }
    catch (IOException localIOException)
    {
    }
    return l;
  }

  public static String i(Context paramContext)
  {
    if (b != null)
      return b;
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (localApplicationInfo != null)
      {
        String str = localApplicationInfo.metaData.getString("TA_APPKEY");
        if (str == null)
          break label62;
        b = str;
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      f.w("Could not read APPKEY meta-data from AndroidManifest.xml");
    }
    while (true)
    {
      return null;
      label62: f.w("Could not read APPKEY meta-data from AndroidManifest.xml");
    }
  }

  public static String j(Context paramContext)
  {
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (localApplicationInfo != null)
      {
        Object localObject = localApplicationInfo.metaData.get("InstallChannel");
        if (localObject != null)
          return localObject.toString();
        f.w("Could not read InstallChannel meta-data from AndroidManifest.xml");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        f.e("Could not read InstallChannel meta-data from AndroidManifest.xml");
    }
  }

  public static String k(Context paramContext)
  {
    if (paramContext == null)
      return null;
    return paramContext.getClass().getName();
  }

  public static String l(Context paramContext)
  {
    String str;
    try
    {
      if (a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        str = "";
        if (o(paramContext))
          str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      }
      else
      {
        f.e("Could not get permission of android.permission.READ_PHONE_STATE");
        return null;
      }
    }
    catch (Throwable localThrowable)
    {
      do
        while (true)
          f.e(localThrowable);
      while (str == null);
    }
    return str;
  }

  public static String m(Context paramContext)
  {
    try
    {
      if (a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        boolean bool = o(paramContext);
        localObject = null;
        if (!bool)
          break label75;
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager != null)
        {
          str = localTelephonyManager.getSimOperator();
          break label72;
        }
      }
      else
      {
        f.e("Could not get permission of android.permission.READ_PHONE_STATE");
        return null;
      }
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
      return null;
    }
    String str = null;
    label72: Object localObject = str;
    label75: return localObject;
  }

  public static String n(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      if (str == null)
        str = "";
      return str;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return "";
  }

  public static boolean o(Context paramContext)
  {
    return paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName()) == 0;
  }

  public static String p(Context paramContext)
  {
    String str1;
    String str2;
    try
    {
      if ((a(paramContext, "android.permission.INTERNET")) && (a(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
        {
          str1 = localNetworkInfo.getTypeName();
          str2 = localNetworkInfo.getExtraInfo();
          if (str1 != null)
          {
            if (str1.equalsIgnoreCase("WIFI"))
              return "WIFI";
            if (!str1.equalsIgnoreCase("MOBILE"))
              break label118;
            if (str2 != null)
              break label115;
            return "MOBILE";
          }
        }
      }
      else
      {
        f.e("can not get the permission of android.permission.ACCESS_WIFI_STATE");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        f.e(localThrowable);
    }
    label115: label118: 
    do
      return str2;
    while (str2 != null);
    return str1;
  }

  public static Integer q(Context paramContext)
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        Integer localInteger = Integer.valueOf(localTelephonyManager.getNetworkType());
        return localInteger;
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  // ERROR //
  public static String r(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 269	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 274	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 605	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   12: getfield 610	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull +10 -> 27
    //   20: aload_2
    //   21: invokevirtual 323	java/lang/String:length	()I
    //   24: ifne +7 -> 31
    //   27: ldc_w 621
    //   30: astore_2
    //   31: aload_2
    //   32: areturn
    //   33: astore_1
    //   34: ldc 105
    //   36: astore_2
    //   37: aload_1
    //   38: astore_3
    //   39: getstatic 35	com/tencent/stat/common/k:f	Lcom/tencent/stat/common/StatLogger;
    //   42: aload_3
    //   43: invokevirtual 103	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   46: aload_2
    //   47: areturn
    //   48: astore_3
    //   49: goto -10 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   0	16	33	java/lang/Throwable
    //   20	27	48	java/lang/Throwable
  }

  public static int s(Context paramContext)
  {
    try
    {
      boolean bool = o.a();
      if (bool)
        return 1;
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return 0;
  }

  public static String t(Context paramContext)
  {
    try
    {
      if (a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE"))
      {
        String str1 = Environment.getExternalStorageState();
        if ((str1 != null) && (str1.equals("mounted")))
        {
          String str2 = Environment.getExternalStorageDirectory().getPath();
          if (str2 != null)
          {
            StatFs localStatFs = new StatFs(str2);
            long l1 = localStatFs.getBlockCount() * localStatFs.getBlockSize() / 1000000L;
            long l2 = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize() / 1000000L;
            return String.valueOf(l2) + "/" + String.valueOf(l1);
          }
        }
      }
      else
      {
        f.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
        return null;
      }
    }
    catch (Throwable localThrowable)
    {
      f.e(localThrowable);
    }
    return null;
  }

  static String u(Context paramContext)
  {
    try
    {
      int m = Process.myPid();
      Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if (localRunningAppProcessInfo.pid != m)
          continue;
        String str = localRunningAppProcessInfo.processName;
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  public static String v(Context paramContext)
  {
    return b(paramContext, StatConstants.a);
  }

  public static Integer w(Context paramContext)
  {
    int m = 0;
    monitorenter;
    while (true)
    {
      int i1;
      try
      {
        i1 = p.a(paramContext, "MTA_EVENT_INDEX", 0);
        m = 0;
        if (i1 >= 2147483646)
        {
          p.b(paramContext, "MTA_EVENT_INDEX", m + 1);
          int n = m + 1;
          Integer localInteger = Integer.valueOf(n);
          return localInteger;
        }
      }
      catch (Throwable localThrowable)
      {
        f.e(localThrowable);
        continue;
      }
      finally
      {
        monitorexit;
      }
      m = i1;
    }
  }

  public static String x(Context paramContext)
  {
    long l1 = G(paramContext) / 1000000L;
    long l2 = i() / 1000000L;
    return String.valueOf(l1) + "/" + String.valueOf(l2);
  }

  public static l y(Context paramContext)
  {
    monitorenter;
    try
    {
      if (h == null)
        h = new l();
      l locall = h;
      return locall;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static JSONObject z(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      y(paramContext);
      int m = l.b();
      if (m > 0)
        localJSONObject.put("fx", m / 1000000);
      y(paramContext);
      int n = l.c();
      if (n > 0)
        localJSONObject.put("fn", n / 1000000);
      y(paramContext);
      int i1 = l.a();
      if (i1 > 0)
        localJSONObject.put("n", i1);
      y(paramContext);
      String str = l.d();
      if ((str != null) && (str.length() == 0))
      {
        y(paramContext);
        localJSONObject.put("na", l.d());
      }
      return localJSONObject;
    }
    catch (Exception localException)
    {
      f.e(localException);
    }
    return localJSONObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.k
 * JD-Core Version:    0.6.0
 */