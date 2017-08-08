package com.tencent.mid.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;

public class i
{
  public static DisplayMetrics a(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }

  public static String a()
  {
    try
    {
      long l1 = b() / 1000000L;
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2 = localStatFs.getBlockSize() * localStatFs.getAvailableBlocks() / 1000000L;
      String str = String.valueOf(l2) + "/" + String.valueOf(l1);
      return str;
    }
    catch (Throwable localThrowable)
    {
      Util.logWarn(localThrowable);
    }
    return "";
  }

  public static long b()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount();
  }

  public static String b(Context paramContext)
  {
    try
    {
      if (Util.checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager != null)
          return localTelephonyManager.getSimOperator();
      }
      else
      {
        Util.logInfo("Could not get permission of android.permission.READ_PHONE_STATE");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Util.logWarn(localThrowable);
    }
  }

  public static String c(Context paramContext)
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
      Util.logWarn(localThrowable);
    }
    return "";
  }

  public static String d(Context paramContext)
  {
    String str1;
    String str2;
    try
    {
      if ((Util.checkPermission(paramContext, "android.permission.INTERNET")) && (Util.checkPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
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
              break label104;
            if (str2 != null)
              break label101;
            return "MOBILE";
          }
        }
      }
      else
      {
        Util.logInfo("can not get the permission of android.permission.ACCESS_WIFI_STATE");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Util.logWarn(localThrowable);
    }
    label101: label104: 
    do
      return str2;
    while (str2 != null);
    return str1;
  }

  public static Integer e(Context paramContext)
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

  public static String f(Context paramContext)
  {
    try
    {
      if (Util.checkPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE"))
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
        Util.logInfo("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
        return null;
      }
    }
    catch (Throwable localThrowable)
    {
      Util.logWarn(localThrowable);
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.util.i
 * JD-Core Version:    0.6.0
 */