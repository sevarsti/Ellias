package com.tencent.component.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiManager;
import com.tencent.component.utils.log.LogUtil;
import java.util.HashMap;

public class NetworkUtil
{
  public static final String a = "wifi";
  private static final String b = "NetworkUtil";
  private static final Uri c = Uri.parse("content://telephony/carriers/preferapn");
  private static final HashMap d = new HashMap();

  static
  {
    d.put("cmwap", new NetworkUtil.NetworkProxy("10.0.0.172", 80));
    d.put("3gwap", new NetworkUtil.NetworkProxy("10.0.0.172", 80));
    d.put("uniwap", new NetworkUtil.NetworkProxy("10.0.0.172", 80));
    d.put("ctwap", new NetworkUtil.NetworkProxy("10.0.0.200", 80));
  }

  public static NetworkUtil.NetworkProxy a(Context paramContext, boolean paramBoolean)
  {
    if (!paramBoolean)
      return e(paramContext);
    return f(paramContext);
  }

  private static String a(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramInt & 0xFF).append(".");
    localStringBuffer.append(0xFF & paramInt >> 8).append(".");
    localStringBuffer.append(0xFF & paramInt >> 16).append(".");
    localStringBuffer.append(0xFF & paramInt >> 24);
    return localStringBuffer.toString();
  }

  public static boolean a(Context paramContext)
  {
    NetworkInfo localNetworkInfo = d(paramContext);
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }

  private static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static boolean b(Context paramContext)
  {
    int i = 1;
    if (paramContext == null)
      return false;
    NetworkInfo localNetworkInfo = d(paramContext);
    if ((localNetworkInfo != null) && (localNetworkInfo.getType() == i));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public static boolean c(Context paramContext)
  {
    if (paramContext == null);
    NetworkInfo localNetworkInfo;
    do
    {
      return false;
      localNetworkInfo = d(paramContext);
    }
    while ((localNetworkInfo == null) || (localNetworkInfo.getType() != 0));
    return true;
  }

  public static NetworkInfo d(Context paramContext)
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return localNetworkInfo;
    }
    catch (Throwable localThrowable)
    {
      LogUtil.e("NetworkUtil", "fail to get active network info", localThrowable);
    }
    return null;
  }

  public static NetworkUtil.NetworkProxy e(Context paramContext)
  {
    if (!c(paramContext));
    String str;
    int i;
    do
    {
      return null;
      str = i(paramContext);
      i = j(paramContext);
    }
    while ((a(str)) || (i < 0));
    return new NetworkUtil.NetworkProxy(str, i);
  }

  public static NetworkUtil.NetworkProxy f(Context paramContext)
  {
    if (!c(paramContext))
      return null;
    String str = g(paramContext);
    NetworkUtil.NetworkProxy localNetworkProxy1 = (NetworkUtil.NetworkProxy)d.get(str);
    if (localNetworkProxy1 == null);
    for (NetworkUtil.NetworkProxy localNetworkProxy2 = null; ; localNetworkProxy2 = localNetworkProxy1.a())
      return localNetworkProxy2;
  }

  // ERROR //
  public static String g(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: invokestatic 85	com/tencent/component/utils/NetworkUtil:d	(Landroid/content/Context;)Landroid/net/NetworkInfo;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: aload_2
    //   14: invokevirtual 101	android/net/NetworkInfo:getType	()I
    //   17: iconst_1
    //   18: if_icmpne +17 -> 35
    //   21: ldc 8
    //   23: astore_3
    //   24: aload_3
    //   25: ifnull +8 -> 33
    //   28: aload_3
    //   29: invokevirtual 149	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   32: astore_3
    //   33: aload_3
    //   34: areturn
    //   35: aload_2
    //   36: invokevirtual 101	android/net/NetworkInfo:getType	()I
    //   39: ifne +171 -> 210
    //   42: invokestatic 154	com/tencent/component/utils/PlatformUtil:version	()I
    //   45: bipush 17
    //   47: if_icmpge +158 -> 205
    //   50: aload_0
    //   51: invokevirtual 158	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   54: getstatic 27	com/tencent/component/utils/NetworkUtil:c	Landroid/net/Uri;
    //   57: aconst_null
    //   58: aconst_null
    //   59: aconst_null
    //   60: aconst_null
    //   61: invokevirtual 164	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   64: astore 8
    //   66: aload 8
    //   68: astore 5
    //   70: aconst_null
    //   71: astore_3
    //   72: aload 5
    //   74: ifnull +37 -> 111
    //   77: aload 5
    //   79: invokeinterface 169 1 0
    //   84: ifeq +27 -> 111
    //   87: aload 5
    //   89: aload 5
    //   91: ldc 171
    //   93: invokeinterface 175 2 0
    //   98: invokeinterface 178 2 0
    //   103: astore 9
    //   105: aload 9
    //   107: astore_3
    //   108: goto -36 -> 72
    //   111: aload 5
    //   113: ifnull +10 -> 123
    //   116: aload 5
    //   118: invokeinterface 181 1 0
    //   123: aload_3
    //   124: invokestatic 187	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   127: ifeq -103 -> 24
    //   130: aload_2
    //   131: invokevirtual 190	android/net/NetworkInfo:getExtraInfo	()Ljava/lang/String;
    //   134: astore_3
    //   135: goto -111 -> 24
    //   138: astore 6
    //   140: aload 6
    //   142: astore 7
    //   144: aconst_null
    //   145: astore_3
    //   146: aload 7
    //   148: invokevirtual 193	java/lang/Throwable:printStackTrace	()V
    //   151: aload_1
    //   152: ifnull -29 -> 123
    //   155: aload_1
    //   156: invokeinterface 181 1 0
    //   161: goto -38 -> 123
    //   164: astore 4
    //   166: aconst_null
    //   167: astore 5
    //   169: aload 5
    //   171: ifnull +10 -> 181
    //   174: aload 5
    //   176: invokeinterface 181 1 0
    //   181: aload 4
    //   183: athrow
    //   184: astore 4
    //   186: goto -17 -> 169
    //   189: astore 4
    //   191: aload_1
    //   192: astore 5
    //   194: goto -25 -> 169
    //   197: astore 7
    //   199: aload 5
    //   201: astore_1
    //   202: goto -56 -> 146
    //   205: aconst_null
    //   206: astore_3
    //   207: goto -84 -> 123
    //   210: aconst_null
    //   211: astore_3
    //   212: goto -188 -> 24
    //
    // Exception table:
    //   from	to	target	type
    //   50	66	138	java/lang/Throwable
    //   50	66	164	finally
    //   77	105	184	finally
    //   146	151	189	finally
    //   77	105	197	java/lang/Throwable
  }

  public static NetworkUtil.DNS h(Context paramContext)
  {
    NetworkUtil.DNS localDNS = new NetworkUtil.DNS();
    if ((paramContext != null) && (b(paramContext)))
    {
      DhcpInfo localDhcpInfo = ((WifiManager)paramContext.getSystemService("wifi")).getDhcpInfo();
      if (localDhcpInfo != null)
      {
        localDNS.a = a(localDhcpInfo.dns1);
        localDNS.b = a(localDhcpInfo.dns2);
      }
    }
    if ((localDNS.a == null) && (localDNS.b == null))
    {
      localDNS.a = PropertyUtils.a("net.dns1", null);
      localDNS.b = PropertyUtils.a("net.dns2", null);
    }
    return localDNS;
  }

  private static String i(Context paramContext)
  {
    if (PlatformUtil.version() < 11)
      return Proxy.getDefaultHost();
    return System.getProperty("http.proxyHost");
  }

  private static int j(Context paramContext)
  {
    if (PlatformUtil.version() < 11);
    for (int i = Proxy.getDefaultPort(); ; i = -1)
      while (true)
      {
        if ((i < 0) || (i > 65535))
          i = -1;
        return i;
        String str = System.getProperty("http.proxyPort");
        if (!a(str))
          try
          {
            int j = Integer.parseInt(str);
            i = j;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.NetworkUtil
 * JD-Core Version:    0.6.0
 */