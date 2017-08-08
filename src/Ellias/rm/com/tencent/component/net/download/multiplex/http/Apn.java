package com.tencent.component.net.download.multiplex.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.tencent.component.ComponentContext;

public class Apn
{
  public static final String A = "#777";
  public static final int B = 0;
  public static final int C = 1;
  public static final int D = 2;
  public static final int E = 3;
  public static final int F = 4;
  public static final int G = 5;
  public static final int H = 6;
  public static final int I = 7;
  public static final int J = 8;
  public static final int K = 9;
  public static final int L = 10;
  public static final int M = 11;
  public static final int N = 12;
  public static final int O = 13;
  public static final int P = 14;
  public static final int Q = 15;
  private static final String R = "Apn";
  private static String S = "";
  private static String T = "";
  private static int U = 0;
  private static int V = 0;
  private static Apn.ApnProxyInfo W = new Apn.ApnProxyInfo();
  private static final String X = "10.0.0.200";
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 4;
  public static final int e = 8;
  public static final int f = 16;
  public static final int g = 32;
  public static final int h = 64;
  public static final int i = 128;
  public static final int j = 256;
  public static final int k = 512;
  public static final int l = 1024;
  public static final String m = "N/A";
  public static final String n = "Net";
  public static final String o = "Wap";
  public static final String p = "Wlan";
  public static final byte q = 0;
  public static final byte r = 1;
  public static final String s = "cmwap";
  public static final String t = "cmnet";
  public static final String u = "3gwap";
  public static final String v = "3gnet";
  public static final String w = "uniwap";
  public static final String x = "uninet";
  public static final String y = "ctwap";
  public static final String z = "ctnet";

  public static int a()
  {
    monitorenter;
    try
    {
      int i1 = a(true);
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static int a(String paramString)
  {
    boolean bool = TextUtils.isEmpty(paramString);
    int i1 = 0;
    String str;
    if (!bool)
    {
      str = paramString.trim().toLowerCase();
      if ((str.indexOf("wifi") == -1) && (str.indexOf("wlan") == -1))
        break label43;
      i1 = 4;
    }
    label43: int i2;
    do
    {
      return i1;
      if (str.indexOf("net") != -1)
        return 1;
      i2 = str.indexOf("wap");
      i1 = 0;
    }
    while (i2 == -1);
    return 2;
  }

  public static int a(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      o();
      int i1 = U;
      return i1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "N/A";
    case 4:
      return "Wlan";
    case 512:
      return "3gnet";
    case 16:
      return "3gwap";
    case 1024:
      return "cmnet";
    case 8:
      return "cmwap";
    case 128:
      return "ctnet";
    case 64:
      return "ctwap";
    case 256:
      return "uninet";
    case 32:
    }
    return "uniwap";
  }

  public static int b()
  {
    int i1 = 2;
    int i2 = a(true);
    switch (i2)
    {
    case 3:
    default:
      i1 = i2;
    case 1:
    case 2:
      return i1;
    case 0:
      return 0;
    case 4:
    }
    return 3;
  }

  public static int b(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      o();
      int i1 = V;
      return i1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String b(int paramInt)
  {
    if (paramInt != 4)
      return a(paramInt);
    return S;
  }

  public static int c()
  {
    monitorenter;
    try
    {
      int i1 = b(true);
      monitorexit;
      return i1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static boolean c(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 0);
  }

  public static String d()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ComponentContext.a().getSystemService("connectivity");
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return "";
      return localNetworkInfo.getSubtypeName();
    }
    return "";
  }

  public static Apn.ApnProxyInfo e()
  {
    monitorenter;
    try
    {
      o();
      Apn.ApnProxyInfo localApnProxyInfo = W;
      monitorexit;
      return localApnProxyInfo;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean f()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ComponentContext.a().getSystemService("connectivity");
    if (localConnectivityManager == null)
      return false;
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return false;
    return (localNetworkInfo.isConnected()) || (localNetworkInfo.isAvailable());
  }

  public static boolean g()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ComponentContext.a().getSystemService("connectivity");
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1))
        return true;
    }
    return false;
  }

  public static String h()
  {
    if (V == 4)
    {
      WifiManager localWifiManager = (WifiManager)ComponentContext.a().getSystemService("wifi");
      if (localWifiManager != null)
      {
        WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
        if (localWifiInfo != null)
          return localWifiInfo.getBSSID();
      }
    }
    return null;
  }

  public static String i()
  {
    if (V == 4)
    {
      WifiManager localWifiManager = (WifiManager)ComponentContext.a().getSystemService("wifi");
      if (localWifiManager != null)
      {
        WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
        if (localWifiInfo != null)
          return localWifiInfo.getSSID();
      }
    }
    return null;
  }

  public static boolean j()
  {
    return (k()) || (l());
  }

  public static boolean k()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ComponentContext.a().getSystemService("connectivity");
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return false;
      int i1 = localNetworkInfo.getType();
      if (i1 == 1)
        return false;
      if (i1 == 0)
      {
        switch (localNetworkInfo.getSubtype())
        {
        case 3:
        default:
          return true;
        case 1:
        case 2:
        case 4:
        }
        return false;
      }
    }
    return false;
  }

  public static boolean l()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ComponentContext.a().getSystemService("connectivity");
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null)
        return false;
      int i1 = localNetworkInfo.getType();
      if (i1 == 1)
        return false;
      if (i1 == 0)
      {
        switch (localNetworkInfo.getSubtype())
        {
        case 3:
        default:
          return false;
        case 1:
        case 2:
        case 4:
        }
        return true;
      }
    }
    return false;
  }

  public static String m()
  {
    return T;
  }

  public static boolean n()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)ComponentContext.a().getSystemService("connectivity");
    if (localConnectivityManager == null)
      return false;
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return false;
    return (localNetworkInfo.isConnected()) || (localNetworkInfo.isAvailable());
  }

  private static void o()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)ComponentContext.a().getSystemService("connectivity")).getActiveNetworkInfo();
    int i1 = -1;
    while (true)
    {
      String str1;
      try
      {
        U = 0;
        V = 0;
        str1 = null;
        if (localNetworkInfo == null)
          continue;
        i1 = localNetworkInfo.getType();
        str1 = localNetworkInfo.getExtraInfo();
        if (str1 != null)
          continue;
        U = 0;
        V = 0;
        if (i1 != 1)
          continue;
        U = 4;
        V = 4;
        W.d = false;
        S = "Wlan" + h();
        T = "Wlan-unknown";
        return;
        str1 = str1.trim().toLowerCase();
        continue;
        if (str1 == null)
        {
          U = 0;
          V = 0;
          W.d = false;
          if (!c(U))
            continue;
          W.a = Proxy.getDefaultHost();
          W.b = Proxy.getDefaultPort();
          if (W.a == null)
            continue;
          W.a = W.a.trim();
          if (TextUtils.isEmpty(W.a))
            break label500;
          W.d = true;
          U = 2;
          if (!"10.0.0.200".equals(W.a))
            break label490;
          W.c = 1;
          V = 64;
          StringBuilder localStringBuilder = new StringBuilder().append(a(V)).append("-");
          if (localNetworkInfo == null)
            break label533;
          str2 = localNetworkInfo.getSubtypeName();
          T = str2;
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if (str1.contains("cmwap"))
      {
        U = 2;
        V = 8;
        continue;
      }
      if (str1.contains("uniwap"))
      {
        U = 2;
        V = 32;
        continue;
      }
      if (str1.contains("3gwap"))
      {
        U = 2;
        V = 16;
        continue;
      }
      if (str1.contains("ctwap"))
      {
        U = 2;
        V = 64;
        continue;
      }
      if (str1.contains("cmnet"))
      {
        U = 1;
        V = 1024;
        continue;
      }
      if (str1.contains("uninet"))
      {
        U = 1;
        V = 256;
        continue;
      }
      if (str1.contains("3gnet"))
      {
        U = 1;
        V = 512;
        continue;
      }
      if (str1.contains("ctnet"))
      {
        U = 1;
        V = 128;
        continue;
      }
      if (str1.contains("#777"))
      {
        U = 0;
        V = 0;
        continue;
      }
      U = 0;
      V = 0;
      continue;
      label490: W.c = 0;
      continue;
      label500: W.d = false;
      U = 1;
      if ((str1 == null) || (!str1.contains("#777")))
        continue;
      V = 128;
      continue;
      label533: String str2 = "unknown";
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.Apn
 * JD-Core Version:    0.6.0
 */