package com.tencent.smtt.sdk.tips;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.text.TextUtils;

public class Apn
{
  private static final String APN_3GNET = "3gnet";
  private static final String APN_3GWAP = "3gwap";
  private static final String APN_777 = "#777";
  private static String APN_AND_NETWORK_NAME;
  private static final String APN_CMNET = "cmnet";
  private static final String APN_CMWAP = "cmwap";
  private static final String APN_CTNET = "ctnet";
  private static final String APN_CTWAP = "ctwap";
  private static final String APN_NET = "Net";
  private static final String APN_UNINET = "uninet";
  private static final String APN_UNIWAP = "uniwap";
  private static final String APN_UNKNOWN = "N/A";
  private static final String APN_WAP = "Wap";
  private static final String APN_WIFI = "Wlan";
  private static String APN_WIFI_NAME_WITH_SSID = "Wlan";
  private static final String PROXY_CTWAP = "10.0.0.200";
  private static final byte PROXY_TYPE_CM = 0;
  private static final byte PROXY_TYPE_CT = 1;
  private static final int TYPE_NET = 1;
  private static final int TYPE_UNKNOWN = 0;
  private static final int TYPE_WAP = 2;
  private static final int TYPE_WIFI = 4;
  private static final int T_APN_3GNET = 512;
  private static final int T_APN_3GWAP = 16;
  private static final int T_APN_CMNET = 1024;
  private static final int T_APN_CMWAP = 8;
  private static final int T_APN_CTNET = 128;
  private static final int T_APN_CTWAP = 64;
  private static final int T_APN_UNINET = 256;
  private static final int T_APN_UNIWAP = 32;
  private static ApnProxyInfo sApnProxyInfo;
  private static int sApnType;
  private static int sApnTypeS;

  static
  {
    APN_AND_NETWORK_NAME = "";
    sApnType = 4;
    sApnTypeS = 4;
    sApnProxyInfo = new ApnProxyInfo(null);
  }

  private static NetworkInfo getActiveNetworkInfo()
  {
    try
    {
      NetworkInfo localNetworkInfo = ContextHolder.getConnectivityManager().getActiveNetworkInfo();
      return localNetworkInfo;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  static String getApnName(int paramInt)
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

  static int getApnTypeS()
  {
    monitorenter;
    try
    {
      int i = getApnTypeS(true);
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static int getApnTypeS(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      init();
      int i = sApnTypeS;
      return i;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static void init()
  {
    NetworkInfo localNetworkInfo = getActiveNetworkInfo();
    int i = -1;
    while (true)
    {
      String str1;
      try
      {
        sApnType = 0;
        sApnTypeS = 0;
        str1 = null;
        if (localNetworkInfo == null)
          continue;
        i = localNetworkInfo.getType();
        str1 = localNetworkInfo.getExtraInfo();
        if (str1 != null)
          continue;
        sApnType = 0;
        sApnTypeS = 0;
        if (i != 1)
          continue;
        sApnType = 4;
        sApnTypeS = 4;
        ApnProxyInfo.access$102(sApnProxyInfo, false);
        APN_AND_NETWORK_NAME = "Wlan-unknown";
        return;
        str1 = str1.trim().toLowerCase();
        continue;
        if (str1 == null)
        {
          sApnType = 0;
          sApnTypeS = 0;
          ApnProxyInfo.access$102(sApnProxyInfo, false);
          if (!isProxyMode(sApnType))
            continue;
          ApnProxyInfo.access$202(sApnProxyInfo, Proxy.getDefaultHost());
          ApnProxyInfo.access$302(sApnProxyInfo, Proxy.getDefaultPort());
          if (sApnProxyInfo.apnProxy == null)
            continue;
          ApnProxyInfo.access$202(sApnProxyInfo, sApnProxyInfo.apnProxy.trim());
          if (TextUtils.isEmpty(sApnProxyInfo.apnProxy))
            break label472;
          ApnProxyInfo.access$102(sApnProxyInfo, true);
          sApnType = 2;
          if (!"10.0.0.200".equals(sApnProxyInfo.apnProxy))
            break label461;
          ApnProxyInfo.access$402(sApnProxyInfo, 1);
          sApnTypeS = 64;
          StringBuilder localStringBuilder = new StringBuilder().append(getApnName(sApnTypeS)).append("-");
          if (localNetworkInfo == null)
            break label506;
          str2 = localNetworkInfo.getSubtypeName();
          APN_AND_NETWORK_NAME = str2;
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
        sApnType = 2;
        sApnTypeS = 8;
        continue;
      }
      if (str1.contains("uniwap"))
      {
        sApnType = 2;
        sApnTypeS = 32;
        continue;
      }
      if (str1.contains("3gwap"))
      {
        sApnType = 2;
        sApnTypeS = 16;
        continue;
      }
      if (str1.contains("ctwap"))
      {
        sApnType = 2;
        sApnTypeS = 64;
        continue;
      }
      if (str1.contains("cmnet"))
      {
        sApnType = 1;
        sApnTypeS = 1024;
        continue;
      }
      if (str1.contains("uninet"))
      {
        sApnType = 1;
        sApnTypeS = 256;
        continue;
      }
      if (str1.contains("3gnet"))
      {
        sApnType = 1;
        sApnTypeS = 512;
        continue;
      }
      if (str1.contains("ctnet"))
      {
        sApnType = 1;
        sApnTypeS = 128;
        continue;
      }
      if (str1.contains("#777"))
      {
        sApnType = 0;
        sApnTypeS = 0;
        continue;
      }
      sApnType = 0;
      sApnTypeS = 0;
      continue;
      label461: ApnProxyInfo.access$402(sApnProxyInfo, 0);
      continue;
      label472: ApnProxyInfo.access$102(sApnProxyInfo, false);
      sApnType = 1;
      if ((str1 == null) || (!str1.contains("#777")))
        continue;
      sApnTypeS = 128;
      continue;
      label506: String str2 = "unknown";
    }
  }

  static boolean isNeedWIFILogin()
  {
    return false;
  }

  private static boolean isProxyMode(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 0);
  }

  public static boolean isWifiMode()
  {
    NetworkInfo localNetworkInfo = getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getType() == 1);
  }

  private static class ApnProxyInfo
  {
    private int apnPort = 80;
    private String apnProxy = null;
    private byte apnProxyType = 0;
    private boolean apnUseProxy = false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.tips.Apn
 * JD-Core Version:    0.6.0
 */