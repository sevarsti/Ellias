package com.tencent.apkupdate.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.apkupdate.logic.protocol.jce.Terminal;

public class b
{
  private static b a = null;
  private static String c = "";
  private static int d = 0;
  private static Terminal e = null;
  private Context b;

  public static int a(Context paramContext, String paramString)
  {
    int i = 0;
    PackageManager localPackageManager;
    if (paramContext != null)
    {
      boolean bool = TextUtils.isEmpty(paramString);
      i = 0;
      if (!bool)
        localPackageManager = paramContext.getPackageManager();
    }
    try
    {
      i = localPackageManager.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0;
  }

  public static b a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new b();
      b localb = a;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static byte c()
  {
    if (!TextUtils.isEmpty(Proxy.getDefaultHost()));
    for (int i = 1; ; i = 0)
      return (byte)i;
  }

  public static String f()
  {
    return c;
  }

  public static int i()
  {
    monitorenter;
    try
    {
      int i = d;
      d = i + 1;
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

  public final int a(String paramString)
  {
    if (this.b == null);
    while (true)
    {
      return 0;
      try
      {
        ApplicationInfo localApplicationInfo = this.b.getPackageManager().getApplicationInfo(paramString, 128);
        if ((localApplicationInfo == null) || (localApplicationInfo.metaData == null))
          continue;
        int i = localApplicationInfo.metaData.getInt("com.tencent.android.qqdownloader.GRAY_CODE");
        return i;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return 0;
  }

  public final void a(Context paramContext)
  {
    this.b = paramContext;
    c = new e(paramContext).a();
  }

  public final Context b()
  {
    return this.b;
  }

  public final void b(String paramString)
  {
    if (paramString != null)
    {
      SharedPreferences localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
      if (localSharedPreferences != null)
        localSharedPreferences.edit().putString("TMAssistantSDKPhoneGUID", paramString).commit();
    }
  }

  public final String d()
  {
    if (this.b == null)
      return "";
    return ((TelephonyManager)this.b.getSystemService("phone")).getNetworkOperator();
  }

  public final int e()
  {
    String str2;
    if (this.b == null)
      str2 = "";
    while (!TextUtils.isEmpty(str2))
    {
      if (str2.equals("WIFI"))
      {
        return 1;
        if (this.b.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1)
        {
          str2 = "";
          continue;
        }
        NetworkInfo localNetworkInfo = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null)
        {
          str2 = "";
          continue;
        }
        if (localNetworkInfo.getType() == 1)
        {
          str2 = "WIFI";
          continue;
        }
        String str1 = localNetworkInfo.getExtraInfo();
        if (str1 == null)
        {
          str2 = "";
          continue;
        }
        str2 = str1.toUpperCase();
        continue;
      }
      if (str2.equals("UN_DETECT"))
        return 0;
      if (str2.equals("CMWAP"))
        return 2;
      if (str2.equals("CMNET"))
        return 3;
      if (str2.equals("UNIWAP"))
        return 4;
      if (str2.equals("UNINET"))
        return 5;
      if (str2.equals("WAP3G"))
        return 6;
      if (str2.equals("NET3G"))
        return 7;
      if (str2.equals("CTWAP"))
        return 8;
      if (!str2.equals("CTNET"))
        break;
      return 9;
    }
    return 10;
  }

  public final Terminal g()
  {
    monitorenter;
    try
    {
      String str1;
      String str2;
      if (e == null)
      {
        str1 = Settings.Secure.getString(this.b.getContentResolver(), "android_id");
        SharedPreferences localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
        if (localSharedPreferences == null)
          break label176;
        str2 = localSharedPreferences.getString("TMAssistantSDKPhoneGUID", "");
      }
      while (true)
      {
        monitorenter;
        try
        {
          Terminal localTerminal2 = new Terminal();
          e = localTerminal2;
          localTerminal2.androidId = str1;
          e.androidIdSdCard = str2;
          e.imei = ((TelephonyManager)this.b.getSystemService("phone")).getDeviceId();
          e.imsi = ((TelephonyManager)this.b.getSystemService("phone")).getSubscriberId();
          Terminal localTerminal3 = e;
          WifiInfo localWifiInfo = ((WifiManager)this.b.getSystemService("wifi")).getConnectionInfo();
          if (localWifiInfo != null);
          for (String str3 = localWifiInfo.getMacAddress(); ; str3 = "")
          {
            localTerminal3.macAdress = str3;
            monitorexit;
            Terminal localTerminal1 = e;
            return localTerminal1;
            label176: str2 = "";
            break;
          }
        }
        finally
        {
          monitorexit;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final String h()
  {
    SharedPreferences localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
    if (localSharedPreferences != null)
      return localSharedPreferences.getString("TMAssistantSDKPhoneGUID", "");
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.c.b
 * JD-Core Version:    0.6.0
 */