package com.tencent.msdk.tools;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.Locale;

public class DeviceUtils
{
  public static final String APN_PROP_PROXY = "proxy";
  public static final int MPROXYTYPE_3GNET = 11;
  public static final int MPROXYTYPE_3GWAP = 10;
  public static final int MPROXYTYPE_CMNET = 1;
  public static final int MPROXYTYPE_CMWAP = 2;
  public static final int MPROXYTYPE_CTNET = 8;
  public static final int MPROXYTYPE_CTWAP = 9;
  public static final int MPROXYTYPE_DEFAULT = 0;
  public static final int MPROXYTYPE_NET = 6;
  public static final int MPROXYTYPE_UNINET = 4;
  public static final int MPROXYTYPE_UNIWAP = 5;
  public static final int MPROXYTYPE_WAP = 7;
  public static final int MPROXYTYPE_WIFI = 3;
  private static Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
  Class<Build> clz = Build.class;

  public static String getApnProxy(Context paramContext)
  {
    Cursor localCursor = paramContext.getContentResolver().query(PREFERRED_APN_URI, null, null, null, null);
    localCursor.moveToFirst();
    if (localCursor.isAfterLast())
    {
      localCursor.close();
      return null;
    }
    String str = localCursor.getString(localCursor.getColumnIndex("proxy"));
    localCursor.close();
    return str;
  }

  public static String getBrand()
  {
    return Build.BRAND;
  }

  public static String getManufacturer()
  {
    return Build.MANUFACTURER;
  }

  public static int getNetworkType(Context paramContext)
  {
    if (paramContext == null);
    while (true)
    {
      return 0;
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager == null)
          continue;
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if (localNetworkInfo == null)
          continue;
        String str1 = localNetworkInfo.getTypeName();
        Logger.d("typeName:" + str1);
        if (str1.toUpperCase(Locale.CHINESE).equals("WIFI"))
          return 3;
        String str2 = localNetworkInfo.getExtraInfo().toLowerCase(Locale.CHINESE);
        Logger.d("extraInfo:" + str2);
        if (str2.startsWith("cmwap"))
          return 2;
        if ((str2.startsWith("cmnet")) || (str2.startsWith("epc.tmobile.com")))
          break;
        if (str2.startsWith("uniwap"))
          return 5;
        if (str2.startsWith("uninet"))
          return 4;
        if (str2.startsWith("wap"))
          return 7;
        if (str2.startsWith("net"))
          return 6;
        if (str2.startsWith("ctwap"))
          return 9;
        if (str2.startsWith("ctnet"))
          return 8;
        if (str2.startsWith("3gwap"))
          return 10;
        if (str2.startsWith("3gnet"))
          return 11;
        if (!str2.startsWith("#777"))
          continue;
        String str3 = getApnProxy(paramContext);
        if (str3 != null)
        {
          int i = str3.length();
          if (i > 0)
            return 9;
        }
        return 8;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return 0;
      }
    }
    return 1;
  }

  public static String getScreenResolution(Activity paramActivity)
  {
    if (paramActivity == null)
      return "";
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels + "*" + localDisplayMetrics.widthPixels;
  }

  public static String getVersionRelease()
  {
    return Build.VERSION.RELEASE;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.DeviceUtils
 * JD-Core Version:    0.6.0
 */