package com.tencent.android.tpush.service.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.i;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  public static Intent a(int paramInt1, String paramString, int paramInt2)
  {
    Intent localIntent = new Intent("com.tencent.android.tpush.action.FEEDBACK");
    if ((paramString != null) && (paramString.length() != 0))
      localIntent.setPackage(paramString);
    localIntent.putExtra("TPUSH.FEEDBACK", paramInt2);
    localIntent.putExtra("TPUSH.ERRORCODE", paramInt1);
    return localIntent;
  }

  public static String a()
  {
    TLog.v("XGService", "@@ getSocketName()");
    try
    {
      String str = TpnsSecurity.generateLocalSocketServieName(i.e());
      return str;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return null;
  }

  public static String a(long paramLong)
  {
    StringBuffer localStringBuffer = new StringBuffer("");
    localStringBuffer.append(String.valueOf(0xFF & paramLong));
    localStringBuffer.append(".");
    localStringBuffer.append(String.valueOf((0xFFFF & paramLong) >>> 8));
    localStringBuffer.append(".");
    localStringBuffer.append(String.valueOf((0xFFFFFF & paramLong) >>> 16));
    localStringBuffer.append(".");
    localStringBuffer.append(String.valueOf(paramLong >>> 24));
    return localStringBuffer.toString();
  }

  public static List a(Context paramContext)
  {
    Object localObject = null;
    Intent localIntent;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getLocalPushAppsInfo(" + paramContext.getPackageName() + ")");
      localIntent = new Intent("com.tencent.android.tpush.action.SDK");
    }
    try
    {
      List localList = paramContext.getPackageManager().queryBroadcastReceivers(localIntent, 0);
      localObject = localList;
      return localObject;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return null;
  }

  public static boolean a(Context paramContext, String paramString)
  {
    TLog.v("XGService", "@@ isAppInstalled(" + paramContext.getPackageName() + "," + paramString + ")");
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return false;
  }

  public static boolean a(Context paramContext, String paramString, float paramFloat)
  {
    try
    {
      boolean bool = Settings.System.putFloat(paramContext.getContentResolver(), e(paramString), paramFloat);
      return bool;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return false;
  }

  public static boolean a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      boolean bool = Settings.System.putInt(paramContext.getContentResolver(), e(paramString), paramInt);
      return bool;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return false;
  }

  public static boolean a(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      boolean bool = Settings.System.putLong(paramContext.getContentResolver(), e(paramString), paramLong);
      return bool;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return false;
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      boolean bool = Settings.System.putString(paramContext.getContentResolver(), e(paramString1), paramString2);
      return bool;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return false;
  }

  public static boolean a(Intent paramIntent)
  {
    TLog.v("XGService", "@@ checkAcceptTime(" + paramIntent + ")");
    try
    {
      JSONObject localJSONObject1 = new JSONObject(Rijndael.decrypt(paramIntent.getStringExtra("content")));
      if (localJSONObject1.isNull("accept_time"))
        return true;
      String str = localJSONObject1.getString("accept_time");
      TLog.i("XGService", ">> accept time:" + str);
      JSONArray localJSONArray = new JSONArray(str);
      if (localJSONArray.length() != 0)
      {
        Calendar localCalendar = Calendar.getInstance();
        int i = 60 * localCalendar.get(11) + localCalendar.get(12);
        j = 0;
        if (j < localJSONArray.length())
        {
          JSONObject localJSONObject2 = new JSONObject(localJSONArray.getString(j));
          JSONObject localJSONObject3 = new JSONObject(localJSONObject2.getString("start"));
          int k = 60 * Integer.valueOf(localJSONObject3.getString("hour")).intValue() + Integer.valueOf(localJSONObject3.getString("min")).intValue();
          JSONObject localJSONObject4 = new JSONObject(localJSONObject2.getString("end"));
          int m = 60 * Integer.valueOf(localJSONObject4.getString("hour")).intValue() + Integer.valueOf(localJSONObject4.getString("min")).intValue();
          TLog.i("XGService", ">> check accept time, current:" + i + ", startTime:" + k + ", endTime:" + m);
          if ((k <= i) && (i <= m))
          {
            TLog.i("XGService", ">> time accepted!");
            return true;
          }
        }
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        int j;
        TLog.e("XGService", localJSONException.toString());
        return true;
        j++;
      }
      TLog.e("XGService", ">> time not accepted!");
      return false;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      TLog.e("XGService", localNumberFormatException.toString());
      return true;
    }
    catch (Throwable localThrowable)
    {
      TLog.e("XGService", localThrowable.toString());
    }
    return true;
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static float b(Context paramContext, String paramString, float paramFloat)
  {
    try
    {
      float f = Settings.System.getFloat(paramContext.getContentResolver(), e(paramString), paramFloat);
      return f;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return 0.0F;
  }

  public static int b()
  {
    return Build.VERSION.SDK_INT;
  }

  public static int b(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), e(paramString), paramInt);
      return i;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return 0;
  }

  public static long b(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      long l = Settings.System.getLong(paramContext.getContentResolver(), e(paramString), paramLong);
      return l;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return 0L;
  }

  public static long b(String paramString)
  {
    if ((paramString == null) || (paramString.equals("0")))
      return 0L;
    String str = paramString.trim();
    long[] arrayOfLong = new long[4];
    int i = str.indexOf(".");
    int j = str.indexOf(".", i + 1);
    int k = str.indexOf(".", j + 1);
    try
    {
      arrayOfLong[3] = Long.parseLong(str.substring(0, i));
      arrayOfLong[2] = Long.parseLong(str.substring(i + 1, j));
      arrayOfLong[1] = Long.parseLong(str.substring(j + 1, k));
      arrayOfLong[0] = Long.parseLong(str.substring(k + 1));
      return (arrayOfLong[0] << 24) + (arrayOfLong[1] << 16) + (arrayOfLong[2] << 8) + arrayOfLong[3];
    }
    catch (Exception localException)
    {
      while (true)
      {
        arrayOfLong[4] = 0L;
        arrayOfLong[3] = 0L;
        arrayOfLong[2] = 0L;
        arrayOfLong[0] = 0L;
        TLog.e("TPush", "service Util@@parseIpAddress(" + str + ")", localException);
      }
    }
  }

  public static boolean b(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ setServicePackageName(" + paramContext.getPackageName() + ")");
      return a(paramContext, "tpush.running.service.name", Rijndael.encrypt(paramContext.getPackageName()));
    }
    return false;
  }

  public static boolean b(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ isTPushApp(" + paramContext.getPackageName() + "," + paramString + ")");
      List localList = a(paramContext);
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          if ((!((ResolveInfo)localIterator.next()).activityInfo.packageName.equals(paramString)) || (paramContext.getPackageName().equals(paramString)))
            continue;
          TLog.i("XGService", ">> Is tpush app [ true ] @" + paramString + "," + paramContext.getPackageName());
          return true;
        }
      }
      TLog.i("XGService", ">> Is tpush app [ false ]@" + paramString + "," + paramContext.getPackageName());
    }
    return false;
  }

  public static String c()
  {
    return Build.VERSION.RELEASE;
  }

  public static String c(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        return str;
      }
      catch (Exception localException)
      {
        TLog.w("Util", ">>get imei err", localException);
      }
    return "";
  }

  public static String c(Context paramContext, String paramString)
  {
    try
    {
      String str = Settings.System.getString(paramContext.getContentResolver(), e(paramString));
      return str;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return "";
  }

  public static String c(String paramString)
  {
    if (i.e() != null)
      try
      {
        String str = TpnsSecurity.getEncryptAPKSignature(i.e().createPackageContext(paramString, 0));
        return str;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        TLog.e("TPush", "+++ getAppCert exception.", localNameNotFoundException);
      }
    return "";
  }

  public static byte d(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        TLog.v("XGService", "@@ getNetworkType(" + paramContext.getPackageName() + ")");
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager == null)
          return 0;
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if (localNetworkInfo == null)
          return -1;
        if ((!localNetworkInfo.isAvailable()) || (!localNetworkInfo.isConnected()))
          break label202;
        if (localNetworkInfo.getType() == 1)
          return 1;
        if (localNetworkInfo.getType() == 0)
        {
          int i = localNetworkInfo.getSubtype();
          switch (i)
          {
          case 12:
          case 14:
          default:
            return 0;
          case 1:
          case 2:
          case 4:
          case 7:
          case 11:
            return 2;
          case 3:
          case 5:
          case 6:
          case 8:
          case 9:
          case 10:
          case 15:
            return 3;
          case 13:
          }
          return 4;
        }
        return 0;
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
      }
    else
      return -1;
    label202: return -1;
  }

  public static String d()
  {
    return Build.MODEL;
  }

  public static void d(String paramString)
  {
    int i = com.tencent.android.tpush.service.a.a.a("rptLive", 0);
    TLog.v("XGService", "@@ RptLive(" + i + ")");
    HttpGet localHttpGet;
    DefaultHttpClient localDefaultHttpClient;
    if (i > 0)
    {
      localHttpGet = new HttpGet("http://report.tpns.qq.com/report" + paramString);
      localDefaultHttpClient = new DefaultHttpClient();
    }
    try
    {
      localDefaultHttpClient.execute(localHttpGet);
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public static byte e(Context paramContext)
  {
    int i = 0;
    if (paramContext != null)
    {
      try
      {
        String str = ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
        if (str != null)
        {
          if ((str.equals("46000")) || (str.equals("46002")) || (str.equals("46007")) || (str.equals("46020")))
            break label138;
          if ((str.equals("46001")) || (str.equals("46006")))
            break label146;
          if (!str.equals("46003"))
          {
            boolean bool = str.equals("46005");
            if (!bool);
          }
          else
          {
            j = 1;
          }
        }
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
        return 0;
      }
      j = 0;
    }
    label138: label146: for (int j = 3; ; j = 2)
    {
      i = j;
      return i;
    }
  }

  private static String e(String paramString)
  {
    return com.tencent.android.tpush.encrypt.a.a(paramString);
  }

  public static boolean e()
  {
    List localList = CacheManager.getRegisterInfo();
    return (localList != null) && (localList.size() > 0);
  }

  public static String f()
  {
    TLog.v("XGService", "@@ getLocalIpAddress()");
    try
    {
      if (NetworkInterface.getNetworkInterfaces() == null)
        return "0";
      while (true)
      {
        Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        while (true)
          if (localIterator1.hasNext())
          {
            Iterator localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
            if (!localIterator2.hasNext())
              continue;
            InetAddress localInetAddress = (InetAddress)localIterator2.next();
            if (localInetAddress.isLoopbackAddress())
              break;
            String str = localInetAddress.getHostAddress();
            boolean bool = InetAddressUtils.isIPv4Address(str);
            if (!bool)
              break;
            return str;
          }
      }
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return "0";
  }

  public static String f(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getKey(" + paramContext.getPackageName() + ")");
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1))
          return g(paramContext);
        String str = "" + e(paramContext) + d(paramContext);
        return str;
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
      }
    }
    return "";
  }

  public static String g(Context paramContext)
  {
    String str = h(paramContext);
    if ((str == null) || (str.equals("0")))
      str = f();
    return str;
  }

  public static String h(Context paramContext)
  {
    TLog.v("XGService", "@@ getRouteMac(" + paramContext.getPackageName() + ")");
    try
    {
      WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if (localWifiInfo == null)
        return "0";
      String str = localWifiInfo.getBSSID();
      return str;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return "0";
  }

  public static String i(Context paramContext)
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
      TLog.w("XGService", "get app versino error", localThrowable);
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.c.c
 * JD-Core Version:    0.6.0
 */