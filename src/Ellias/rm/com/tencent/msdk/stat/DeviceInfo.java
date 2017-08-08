package com.tencent.msdk.stat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.tencent.beacon.event.UserAction;
import com.tencent.mid.api.MidService;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo
{
  public static final String APN_PROP_PROXY = "proxy";
  private static Uri PREFERRED_APN_URI;
  private Context mCtx;
  private PackageManager mPm;

  static
  {
    if (!DeviceInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
      return;
    }
  }

  public DeviceInfo(Context paramContext)
  {
    this.mCtx = paramContext;
    this.mPm = paramContext.getPackageManager();
  }

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

  private String getRAMInfo()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      String str1 = localBufferedReader.readLine();
      if (str1 == null)
      {
        localBufferedReader.close();
        return "";
      }
      long l = 1024 * Integer.valueOf(str1.split("\\s+")[1]).intValue();
      localBufferedReader.close();
      String str2 = "" + l;
      return str2;
    }
    catch (IOException localIOException)
    {
    }
    return "";
  }

  private String getROMInfo()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l1 = localStatFs.getBlockSize();
    long l2 = localStatFs.getBlockCount();
    return "" + l2 * l1;
  }

  public JSONObject getAllDeviceInfo()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("mid", getMid());
      localJSONObject.put("qImei", getQImei());
      localJSONObject.put("appVersion", getVersionName());
      localJSONObject.put("appVersionCode", getVersionCode());
      localJSONObject.put("osSystem", "android");
      localJSONObject.put("deviceResolution", getResolution());
      localJSONObject.put("deviceApn", getApn());
      localJSONObject.put("mobileService", getProvidersName());
      localJSONObject.put("deviceTradeMark", getBrand());
      localJSONObject.put("deviceManufacturer", getManufacturer());
      localJSONObject.put("deviceModel", getModel());
      localJSONObject.put("deviceImei", getImei());
      localJSONObject.put("deviceName", getModel());
      localJSONObject.put("deviceRom", getROMInfo());
      localJSONObject.put("deviceRam", getRAMInfo());
      localJSONObject.put("deviceCPU", getCpuInfo());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }

  public String getApn()
  {
    String str1 = "";
    ConnectivityManager localConnectivityManager = (ConnectivityManager)WeGame.getInstance().getActivity().getSystemService("connectivity");
    if (localConnectivityManager == null)
      return "";
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return "";
    String str2 = localNetworkInfo.getTypeName();
    if (str2 == null)
      return "";
    Logger.d("typeName:" + str2);
    if (str2.toUpperCase(Locale.CHINA).equals("WIFI"))
      str1 = "wifi";
    while (true)
    {
      return str1;
      if (localNetworkInfo.getExtraInfo() == null)
        return "";
      String str3 = localNetworkInfo.getExtraInfo().toLowerCase(Locale.CHINA);
      Logger.d("extraInfo:" + str3);
      if (str3.startsWith("cmwap"))
      {
        str1 = "cmwap";
        continue;
      }
      if ((str3.startsWith("cmnet")) || (str3.startsWith("epc.tmobile.com")))
      {
        str1 = "cmnet";
        continue;
      }
      if (str3.startsWith("uniwap"))
      {
        str1 = "uniwap";
        continue;
      }
      if (str3.startsWith("uninet"))
      {
        str1 = "uninet";
        continue;
      }
      if (str3.startsWith("wap"))
      {
        str1 = "wap";
        continue;
      }
      if (str3.startsWith("net"))
      {
        str1 = "net";
        continue;
      }
      if (str3.startsWith("ctwap"))
      {
        str1 = "ctwap";
        continue;
      }
      if (str3.startsWith("ctnet"))
      {
        str1 = "ctnet";
        continue;
      }
      if (str3.startsWith("3gwap"))
      {
        str1 = "3gwap";
        continue;
      }
      if (str3.startsWith("3gnet"))
      {
        str1 = "3gnet";
        continue;
      }
      if (!str3.startsWith("#777"))
        continue;
      String str4 = getApnProxy(WeGame.getInstance().getActivity());
      if ((str4 != null) && (str4.length() > 0))
      {
        str1 = "cmda wap";
        continue;
      }
      str1 = "cmda net";
    }
  }

  public String getBrand()
  {
    return Build.BRAND;
  }

  public String getCpuInfo()
  {
    int i = -1;
    try
    {
      RandomAccessFile localRandomAccessFile = new RandomAccessFile("/sys/devices/system/cpu/cpu0/cpufreq/stats/time_in_state", "r");
      while (true)
      {
        if (0 == 0);
        String[] arrayOfString;
        while (true)
        {
          try
          {
            String str = localRandomAccessFile.readLine();
            if (str != null)
              continue;
            localRandomAccessFile.close();
            return "" + i;
            arrayOfString = str.split("\\s+");
            if (($assertionsDisabled) || (arrayOfString.length == 2))
              break;
            throw new AssertionError();
          }
          catch (IOException localIOException1)
          {
          }
          label86: localIOException1.printStackTrace();
        }
        if (Integer.parseInt(arrayOfString[1]) <= 0)
          continue;
        int j = Integer.parseInt(arrayOfString[0]) / 1000;
        if (j <= i)
          continue;
        i = j;
      }
    }
    catch (IOException localIOException2)
    {
      break label86;
    }
  }

  public String getImei()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)WeGame.getInstance().getActivity().getSystemService("phone");
    String str;
    if (localTelephonyManager == null)
      str = "";
    do
    {
      return str;
      str = localTelephonyManager.getDeviceId();
    }
    while (!T.ckIsEmpty(str));
    return "";
  }

  public String getManufacturer()
  {
    return Build.MANUFACTURER;
  }

  public String getMid()
  {
    return MidService.getMid(this.mCtx.getApplicationContext());
  }

  public String getModel()
  {
    return Build.MODEL;
  }

  public String getNetwork()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)this.mCtx.getSystemService("connectivity");
    String str;
    if (localConnectivityManager == null)
      str = "";
    NetworkInfo localNetworkInfo;
    do
    {
      return str;
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      str = "";
    }
    while (localNetworkInfo == null);
    return localNetworkInfo.getExtraInfo().toLowerCase(Locale.CHINESE);
  }

  public String getOs()
  {
    return "android";
  }

  public String getPhoneName()
  {
    String str = Build.MODEL;
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter != null)
      str = localBluetoothAdapter.getName();
    return str;
  }

  public String getProvidersName()
  {
    String str1 = ((TelephonyManager)this.mCtx.getSystemService("phone")).getSubscriberId();
    if (str1 == null)
      return "";
    String str2;
    if ((str1.startsWith("46000")) || (str1.startsWith("46002")))
      str2 = "中国移动";
    while (true)
    {
      return str2;
      if (str1.startsWith("46001"))
      {
        str2 = "中国联通";
        continue;
      }
      boolean bool = str1.startsWith("46003");
      str2 = null;
      if (!bool)
        continue;
      str2 = "中国电信";
    }
  }

  public String getQImei()
  {
    try
    {
      String str2 = UserAction.getQIMEI();
      str1 = str2;
      if (T.ckIsEmpty(str1))
        str1 = "";
      return str1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        String str1 = null;
      }
    }
  }

  public String getResolution()
  {
    DisplayMetrics localDisplayMetrics = this.mCtx.getResources().getDisplayMetrics();
    if (localDisplayMetrics == null)
      return "";
    return localDisplayMetrics.widthPixels + "*" + localDisplayMetrics.heightPixels;
  }

  public int getVersionCode()
  {
    try
    {
      int i = this.mPm.getPackageInfo(this.mCtx.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return -1;
  }

  public String getVersionName()
  {
    try
    {
      String str = this.mPm.getPackageInfo(this.mCtx.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.stat.DeviceInfo
 * JD-Core Version:    0.6.0
 */