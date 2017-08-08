package com.tencent.tmassistantsdk.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.downloadservice.c;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class f
{
  protected static f a = null;
  protected static int d = 0;
  protected Context b;
  public String c = "";
  public final int e = 0;
  public final int f = 1;
  public final int g = 2;
  public final int h = 3;
  public final int i = 4;
  public final int j = 5;
  public HashMap k = null;

  protected f()
  {
    this.k.put(Integer.valueOf(1), "ReportLog");
    this.k.put(Integer.valueOf(2), "GetSettings");
    this.k.put(Integer.valueOf(3), "GetAppUpdate");
    this.k.put(Integer.valueOf(4), "GetAuthorized");
    this.k.put(Integer.valueOf(5), "GetAppSimpleDetail");
  }

  public static int a(int paramInt)
  {
    switch (paramInt)
    {
    case 5:
    case 7:
    case 8:
    default:
      return 0;
    case 1:
      return 2;
    case 6:
      return 1;
    case 2:
      return 3;
    case 4:
      return 4;
    case 3:
      return 5;
    case 9:
    }
    return 6;
  }

  public static f a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new f();
      f localf = a;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static int b(int paramInt)
  {
    switch (paramInt)
    {
    case -1000:
    case -26:
    case -24:
    default:
      return 604;
    case 0:
      return 0;
    case -1:
      return 709;
    case -11:
      return 708;
    case -12:
      return 730;
    case -13:
      return 703;
    case -15:
      return 1;
    case -16:
      return 731;
    case -21:
      return 700;
    case -22:
      return 732;
    case -23:
      return 601;
    case -25:
      return 602;
    case -27:
      return 606;
    case -28:
    }
    return 701;
  }

  public static String b(Context paramContext)
  {
    if (paramContext != null)
      return paramContext.getPackageName();
    return "";
  }

  public static int c(Context paramContext)
  {
    int m = 0;
    PackageManager localPackageManager;
    if (paramContext != null)
      localPackageManager = paramContext.getPackageManager();
    try
    {
      m = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return m;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0;
  }

  public static String c(String paramString)
  {
    int m = 0;
    Object localObject = "";
    byte[] arrayOfByte1;
    if (!TextUtils.isEmpty(paramString))
      arrayOfByte1 = paramString.getBytes();
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(arrayOfByte1, 0, arrayOfByte1.length);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer();
      while (m < arrayOfByte2.length)
      {
        localStringBuffer.append(Integer.toHexString(0xFF & arrayOfByte2[m]));
        m++;
      }
      String str = localStringBuffer.toString();
      localObject = str;
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return (String)localObject;
  }

  public static void d(String paramString)
  {
    File localFile = new File(paramString);
    String str1 = localFile.getParent();
    String str2 = new File(str1).getParent();
    String str3 = new File(str2).getParent();
    try
    {
      String str4 = "chmod 777 " + localFile.getAbsolutePath();
      Runtime.getRuntime().exec(str4);
      String str5 = "chmod 777 " + str1;
      Runtime.getRuntime().exec(str5);
      String str6 = "chmod 777 " + str2;
      Runtime.getRuntime().exec(str6);
      String str7 = "chmod 777" + str3;
      Runtime.getRuntime().exec(str7);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public static boolean e(String paramString)
  {
    return (a().b() != null) && (a().b().getDatabasePath(paramString).exists());
  }

  public static void f(String paramString)
  {
    File localFile;
    if (a().b() != null)
    {
      localFile = a().b().getDatabasePath(paramString);
      if (localFile.exists() != true);
    }
    try
    {
      localFile.delete();
      l.b("GlobalUtil", "deleteDB");
      return;
    }
    catch (Exception localException)
    {
      l.b("GlobalUtil", "deleteDB failed");
    }
  }

  public static int k()
  {
    monitorenter;
    try
    {
      int m = d;
      d = m + 1;
      monitorexit;
      return m;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int a(String paramString)
  {
    if (paramString == null)
      return 0;
    if (this.k != null)
    {
      Iterator localIterator = this.k.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (localEntry == null)
          continue;
        Integer localInteger = (Integer)localEntry.getKey();
        String str = (String)localEntry.getValue();
        if ((str != null) && (str.equals(paramString)))
          return localInteger.intValue();
      }
    }
    return 0;
  }

  public void a(byte paramByte)
  {
    if (this.b == null);
    SharedPreferences localSharedPreferences;
    do
    {
      return;
      localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
    }
    while ((localSharedPreferences == null) || (Byte.parseByte(localSharedPreferences.getString("TMAssistantSDKNetType", "0")) == paramByte));
    localSharedPreferences.edit().putString("TMAssistantSDKNetType", paramByte + "").commit();
  }

  public void a(Context paramContext)
  {
    this.b = paramContext;
    this.c = new j(paramContext).a();
  }

  public Context b()
  {
    return this.b;
  }

  public void b(String paramString)
  {
    if (this.b == null);
    SharedPreferences localSharedPreferences;
    do
    {
      do
        return;
      while (paramString == null);
      localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
    }
    while (localSharedPreferences == null);
    localSharedPreferences.edit().putString("TMAssistantSDKPhoneGUID", paramString).commit();
  }

  public void c()
  {
    this.b = null;
    a = null;
  }

  public String d()
  {
    if (this.b == null)
      return "";
    return ((TelephonyManager)this.b.getSystemService("phone")).getNetworkOperator();
  }

  public int e()
  {
    if (this.b == null)
      return 0;
    return ((TelephonyManager)this.b.getSystemService("phone")).getNetworkType();
  }

  public String f()
  {
    if (this.b == null)
      return null;
    return Settings.Secure.getString(b().getContentResolver(), "android_id");
  }

  public String g()
  {
    if (this.b == null)
      return "";
    SharedPreferences localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
    if (localSharedPreferences != null)
      return localSharedPreferences.getString("TMAssistantSDKPhoneGUID", "");
    return "";
  }

  public String h()
  {
    if (this.b == null)
      return null;
    return ((TelephonyManager)b().getSystemService("phone")).getDeviceId();
  }

  public String i()
  {
    if (this.b == null)
      return null;
    return ((TelephonyManager)b().getSystemService("phone")).getSubscriberId();
  }

  public String j()
  {
    if (this.b == null)
      return null;
    try
    {
      WifiInfo localWifiInfo = ((WifiManager)b().getSystemService("wifi")).getConnectionInfo();
      if (localWifiInfo != null)
        return localWifiInfo.getMacAddress();
      return "";
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public boolean l()
  {
    if ((!"wifi".equals(c.b())) || (this.b == null));
    while (true)
    {
      return false;
      SharedPreferences localSharedPreferences = this.b.getSharedPreferences("TMAssistantSDKSharedPreference", 0);
      if (localSharedPreferences != null);
      for (int m = Byte.parseByte(localSharedPreferences.getString("TMAssistantSDKNetType", "0")); (m & 0x4) == 4; m = 0)
        return true;
    }
  }

  public int m()
  {
    if (this.b == null);
    while (true)
    {
      return 0;
      try
      {
        ApplicationInfo localApplicationInfo = this.b.getPackageManager().getApplicationInfo("com.tencent.android.qqdownloader", 128);
        if ((localApplicationInfo == null) || (localApplicationInfo.metaData == null))
          continue;
        int m = localApplicationInfo.metaData.getInt("com.tencent.android.qqdownloader.sdk.apilevel");
        return m;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return 0;
  }

  public int n()
  {
    if (this.b == null);
    while (true)
    {
      return 0;
      PackageManager localPackageManager = this.b.getPackageManager();
      if (localPackageManager == null)
        continue;
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo("com.tencent.android.qqdownloader", 0);
        if (localPackageInfo == null)
          continue;
        int m = localPackageInfo.versionCode;
        return m;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.g.f
 * JD-Core Version:    0.6.0
 */