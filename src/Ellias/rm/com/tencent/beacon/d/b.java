package com.tencent.beacon.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class b
{
  public static boolean a(Context paramContext)
  {
    NetworkInfo localNetworkInfo = d(paramContext);
    return (localNetworkInfo != null) && (localNetworkInfo.getType() == 1);
  }

  public static boolean b(Context paramContext)
  {
    NetworkInfo localNetworkInfo = d(paramContext);
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }

  public static String c(Context paramContext)
  {
    NetworkInfo localNetworkInfo = d(paramContext);
    if (localNetworkInfo == null)
      return "unknown";
    if (localNetworkInfo.getType() == 1)
      return "wifi";
    String str = localNetworkInfo.getExtraInfo();
    if ((str != null) && (str.length() > 64))
      str = str.substring(0, 64);
    return str;
  }

  private static NetworkInfo d(Context paramContext)
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localConnectivityManager == null)
        return null;
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      return localNetworkInfo;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.d.b
 * JD-Core Version:    0.6.0
 */