package com.tencent.android.tpush.service.c;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import com.tencent.android.tpush.logging.TLog;
import java.util.Locale;

public class a
{
  private static final String a = a.class.getName();
  private static Uri b = Uri.parse("content://telephony/carriers/preferapn");

  public static byte a(Context paramContext)
  {
    int i = 2;
    int j = f(paramContext);
    if (j == i)
      i = 3;
    do
      return i;
    while (j == 1);
    if (j == 4)
      return 1;
    if (j == 16)
      return 5;
    if (j == 8)
      return 4;
    if (j == 64)
      return 7;
    if (j == 32)
      return 6;
    if (j == 512)
      return 9;
    if (j == 256)
      return 8;
    if (j == 1024)
      return 10;
    if (j == 2048)
      return 11;
    return 0;
  }

  public static String b(Context paramContext)
  {
    int i = a(paramContext);
    if ((i == 2) || (i == 5) || (i == 10))
      return "10.0.0.172";
    if (i == 9)
      return "10.0.0.200";
    return c(paramContext);
  }

  public static String c(Context paramContext)
  {
    String str;
    try
    {
      Cursor localCursor = paramContext.getContentResolver().query(b, null, null, null, null);
      if (localCursor == null)
        return null;
      localCursor.moveToFirst();
      if (localCursor.isAfterLast())
      {
        if (localCursor == null)
          break label83;
        localCursor.close();
        break label83;
      }
      str = localCursor.getString(localCursor.getColumnIndex("proxy"));
      if (localCursor != null)
      {
        localCursor.close();
        return str;
      }
    }
    catch (Exception localException)
    {
      str = "";
    }
    return str;
    label83: return null;
  }

  public static String d(Context paramContext)
  {
    String str;
    try
    {
      Cursor localCursor = paramContext.getContentResolver().query(b, null, null, null, null);
      if (localCursor == null)
        return null;
      localCursor.moveToFirst();
      if (localCursor.isAfterLast())
      {
        if (localCursor == null)
          break label90;
        localCursor.close();
        break label90;
      }
      str = localCursor.getString(localCursor.getColumnIndex("port"));
      if (str == null)
        str = "80";
      if (localCursor != null)
      {
        localCursor.close();
        return str;
      }
    }
    catch (Exception localException)
    {
      str = "80";
    }
    return str;
    label90: return "80";
  }

  public static boolean e(Context paramContext)
  {
    int i = f(paramContext);
    return (i == 1) || (i == 16) || (i == 64) || (i == 512) || (i == 1024);
  }

  public static int f(Context paramContext)
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localConnectivityManager == null)
        return 128;
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return 128;
      if (localNetworkInfo.getTypeName().toUpperCase(Locale.US).equals("WIFI"))
        return 2;
      String str1 = localNetworkInfo.getExtraInfo().toLowerCase(Locale.US);
      if (str1.startsWith("cmwap"))
        return 1;
      if ((str1.startsWith("cmnet")) || (str1.startsWith("epc.tmobile.com")))
        break label261;
      if (str1.startsWith("uniwap"))
        return 16;
      if (str1.startsWith("uninet"))
        return 8;
      if (str1.startsWith("wap"))
        return 64;
      if (str1.startsWith("net"))
        return 32;
      if (str1.startsWith("ctwap"))
        return 512;
      if (str1.startsWith("ctnet"))
        return 256;
      if (str1.startsWith("3gwap"))
        return 1024;
      if (str1.startsWith("3gnet"))
        return 2048;
      if (str1.startsWith("#777"))
      {
        String str2 = c(paramContext);
        if (str2 != null)
        {
          int i = str2.length();
          if (i > 0)
            return 512;
        }
        return 256;
      }
    }
    catch (Exception localException)
    {
      TLog.e(a, "getMProxyType>>> ", localException);
    }
    return 128;
    label261: return 4;
  }

  public static boolean g(Context paramContext)
  {
    if (paramContext == null)
    {
      TLog.i("XGService", "@@ APNUtil @@ checkNetWork >>> context is null!");
      return false;
    }
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localConnectivityManager != null)
      try
      {
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()) && (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED))
        {
          TLog.i("XGService", ">> APNUtil @@ checkNetWork >>> true");
          return true;
        }
      }
      catch (Exception localException)
      {
        TLog.e("XGService", ">> APNUtil @@ checkNetWork >>> ", localException);
      }
    TLog.i("XGService", ">> APNUtil @@ checkNetWork >>> false");
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.c.a
 * JD-Core Version:    0.6.0
 */