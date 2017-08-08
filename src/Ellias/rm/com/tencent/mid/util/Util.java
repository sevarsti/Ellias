package com.tencent.mid.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.api.MidService;
import com.tencent.mid.b.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;

public class Util
{
  public static byte[] StringToBytes(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString.getBytes();
  }

  public static String bytesToString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      String str = new String(paramArrayOfByte, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return new String(paramArrayOfByte);
  }

  public static boolean checkPermission(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      int j = 0;
      if (i == 0)
        j = 1;
      return j;
    }
    catch (Throwable localThrowable)
    {
      Log.e("MID", "checkPermission error", localThrowable);
    }
    return false;
  }

  public static void clear(Context paramContext)
  {
    if (paramContext == null)
      return;
    g.a(paramContext).c();
  }

  public static String decode(String paramString)
  {
    if (paramString == null)
      paramString = null;
    do
      return paramString;
    while (Build.VERSION.SDK_INT < 8);
    try
    {
      String str = new String(h.b(a.a(paramString.getBytes("UTF-8"), 0)), "UTF-8").trim().replace("\t", "").replace("\n", "").replace("\r", "");
      return str;
    }
    catch (Throwable localThrowable)
    {
      Log.e("MID", "decode error", localThrowable);
    }
    return paramString;
  }

  public static byte[] deocdeGZipContent(byte[] paramArrayOfByte)
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
    byte[] arrayOfByte1 = new byte[4096];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(2 * paramArrayOfByte.length);
    while (true)
    {
      int i = localGZIPInputStream.read(arrayOfByte1);
      if (i == -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte1, 0, i);
    }
    byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
    localByteArrayInputStream.close();
    localGZIPInputStream.close();
    localByteArrayOutputStream.close();
    return arrayOfByte2;
  }

  public static String encode(String paramString)
  {
    if (paramString == null)
      paramString = null;
    do
      return paramString;
    while (Build.VERSION.SDK_INT < 8);
    try
    {
      String str = new String(a.b(h.a(paramString.getBytes("UTF-8")), 0), "UTF-8").trim().replace("\t", "").replace("\n", "").replace("\r", "");
      return str;
    }
    catch (Throwable localThrowable)
    {
      Log.w("MID", "encode error", localThrowable);
    }
    return paramString;
  }

  public static boolean equal(MidEntity paramMidEntity1, MidEntity paramMidEntity2)
  {
    if ((paramMidEntity1 != null) && (paramMidEntity2 != null))
      if (paramMidEntity1.compairTo(paramMidEntity2) != 0);
    do
    {
      return true;
      return false;
    }
    while ((paramMidEntity1 == null) && (paramMidEntity2 == null));
    return false;
  }

  public static String getDeviceModel()
  {
    return Build.MODEL;
  }

  public static DisplayMetrics getDisplayMetrics(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }

  public static String getExternalStorageInfo(Context paramContext)
  {
    try
    {
      if (checkPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE"))
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
        Log.e("MID", "can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
        return null;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e("MID", "", localThrowable);
    }
    return null;
  }

  public static byte[] getHMAC(String paramString1, String paramString2)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString1.getBytes(), "hmacmd5");
      Mac localMac = Mac.getInstance("HmacSHA1");
      localMac.init(localSecretKeySpec);
      localMac.update(paramString2.getBytes());
      byte[] arrayOfByte = localMac.doFinal();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      logWarn(localException);
    }
    return null;
  }

  public static HttpHost getHttpProxy()
  {
    if (Proxy.getDefaultHost() != null)
      return new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort());
    return null;
  }

  public static HttpHost getHttpProxy(Context paramContext)
  {
    if (paramContext == null)
      return null;
    String str2;
    do
    {
      String str1;
      try
      {
        if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
          return null;
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null)
          return null;
        if ((localNetworkInfo.getTypeName() != null) && (localNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")))
          return null;
        str1 = localNetworkInfo.getExtraInfo();
        logInfo("network type:" + str1);
        if (str1 == null)
          return null;
        if ((str1.equals("cmwap")) || (str1.equals("3gwap")) || (str1.equals("uniwap")))
        {
          HttpHost localHttpHost1 = new HttpHost("10.0.0.172", 80);
          return localHttpHost1;
        }
      }
      catch (Throwable localThrowable)
      {
        logWarn(localThrowable);
        return null;
      }
      if (str1.equals("ctwap"))
        return new HttpHost("10.0.0.200", 80);
      str2 = Proxy.getDefaultHost();
    }
    while ((str2 == null) || (str2.trim().length() <= 0));
    HttpHost localHttpHost2 = new HttpHost(str2, Proxy.getDefaultPort());
    return localHttpHost2;
  }

  public static String getHttpUrl()
  {
    return "http://pingmid.qq.com:80/";
  }

  public static String getImei(Context paramContext)
  {
    try
    {
      if (checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        if (str != null)
          return str;
      }
      else
      {
        logInfo("Could not get permission of android.permission.READ_PHONE_STATE");
      }
      return "";
    }
    catch (Throwable localThrowable)
    {
      while (true)
        logWarn(localThrowable);
    }
  }

  public static String getLinkedWay(Context paramContext)
  {
    String str1;
    String str2;
    try
    {
      if ((checkPermission(paramContext, "android.permission.INTERNET")) && (checkPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
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
              break label129;
            if (str2 != null)
              break label126;
            return "MOBILE";
          }
        }
      }
      else
      {
        Log.e("MID", "can not get the permission of android.permission.ACCESS_WIFI_STATE");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Log.e("MID", "", localThrowable);
    }
    label126: 
    do
      return str2;
    while (str2 != null);
    label129: return str1;
  }

  public static MidEntity getNewerMidEntity(MidEntity paramMidEntity1, MidEntity paramMidEntity2)
  {
    if ((paramMidEntity1 != null) && (paramMidEntity2 != null))
      if (paramMidEntity1.compairTo(paramMidEntity2) < 0);
    do
    {
      return paramMidEntity1;
      return paramMidEntity2;
    }
    while (paramMidEntity1 != null);
    if (paramMidEntity2 != null)
      return paramMidEntity2;
    return null;
  }

  public static String getRemoteUrlIp(String paramString)
  {
    try
    {
      URL localURL = new URL(paramString);
      if (localURL != null)
      {
        InetAddress localInetAddress = InetAddress.getByName(localURL.getHost());
        if (localInetAddress != null)
        {
          String str = localInetAddress.getHostAddress();
          return str;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String getSimOperator(Context paramContext)
  {
    try
    {
      if (checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        if (localTelephonyManager != null)
          return localTelephonyManager.getSimOperator();
      }
      else
      {
        Log.e("MID", "Could not get permission of android.permission.READ_PHONE_STATE");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Log.e("MID", "", localThrowable);
    }
  }

  public static Integer getTelephonyNetworkType(Context paramContext)
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

  public static String getWiFiBBSID(Context paramContext)
  {
    try
    {
      WifiInfo localWifiInfo = getWifiInfo(paramContext);
      if (localWifiInfo != null)
      {
        String str = localWifiInfo.getBSSID();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      logWarn(localThrowable);
    }
    return null;
  }

  public static String getWiFiSSID(Context paramContext)
  {
    try
    {
      WifiInfo localWifiInfo = getWifiInfo(paramContext);
      if (localWifiInfo != null)
      {
        String str = localWifiInfo.getSSID();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      logWarn(localThrowable);
    }
    return null;
  }

  public static WifiInfo getWifiInfo(Context paramContext)
  {
    if (checkPermission(paramContext, "android.permission.ACCESS_WIFI_STATE"))
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getApplicationContext().getSystemService("wifi");
      if (localWifiManager != null)
        return localWifiManager.getConnectionInfo();
    }
    return null;
  }

  public static String getWifiMacAddress(Context paramContext)
  {
    if (checkPermission(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      try
      {
        WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
        if (localWifiManager == null)
          return "";
        String str = localWifiManager.getConnectionInfo().getMacAddress();
        return str;
      }
      catch (Exception localException)
      {
        logInfo("get wifi address error" + localException);
        return "";
      }
    logInfo("Could not get permission of android.permission.ACCESS_WIFI_STATE");
    return "";
  }

  public static JSONArray getWifiTopN(Context paramContext, int paramInt)
  {
    JSONArray localJSONArray;
    try
    {
      if ((checkPermission(paramContext, "android.permission.INTERNET")) && (checkPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
        if (localWifiManager != null)
        {
          List localList = localWifiManager.getScanResults();
          if ((localList != null) && (localList.size() > 0))
          {
            Collections.sort(localList, new k());
            localJSONArray = new JSONArray();
            for (int i = 0; (i < localList.size()) && (i < paramInt); i++)
            {
              ScanResult localScanResult = (ScanResult)localList.get(i);
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("bs", localScanResult.BSSID);
              localJSONObject.put("ss", localScanResult.SSID);
              localJSONArray.put(localJSONObject);
            }
          }
        }
      }
      else
      {
        logInfo("can not get the permisson of android.permission.INTERNET");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        logWarn(localThrowable);
    }
    return localJSONArray;
  }

  public static boolean isMidValid(String paramString)
  {
    return (paramString != null) && (paramString.trim().length() >= 40);
  }

  public static boolean isNetworkAvailable(Context paramContext)
  {
    try
    {
      if ((checkPermission(paramContext, "android.permission.INTERNET")) && (checkPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager != null)
        {
          NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
          if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
          Log.w("MID", "Network error");
          return false;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e("MID", "isNetworkAvailable error", localThrowable);
    }
    return false;
  }

  public static boolean isStringValid(String paramString)
  {
    return (paramString != null) && (paramString.trim().length() != 0);
  }

  public static boolean isWifiNet(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getTypeName() != null) && (localNetworkInfo.getTypeName().equalsIgnoreCase("WIFI"));
  }

  public static void jsonPut(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if (isStringValid(paramString2))
      paramJSONObject.put(paramString1, paramString2);
  }

  public static void logInfo(String paramString)
  {
    if (MidService.isEnableDebug())
      Log.i("MID", paramString);
  }

  public static void logWarn(Throwable paramThrowable)
  {
    if (MidService.isEnableDebug())
      Log.w("MID", paramThrowable);
  }

  public static String md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 0; i < arrayOfByte.length; i++)
        localStringBuffer.append(arrayOfByte[i]);
      String str = localStringBuffer.toString();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      logWarn(localNoSuchAlgorithmException);
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      logWarn(localUnsupportedEncodingException);
    }
    return paramString;
  }

  public static void updateIfLocalInvalid(Context paramContext, String paramString)
  {
    if ((isMidValid(paramString)) && (!isStringValid(MidService.getLocalMidOnly(paramContext))) && (isStringValid(paramString)))
    {
      MidEntity localMidEntity = new MidEntity();
      localMidEntity.setImei(getImei(paramContext));
      localMidEntity.setMac(getWifiMacAddress(paramContext));
      localMidEntity.setMid(paramString);
      g.a(paramContext).a(localMidEntity);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.util.Util
 * JD-Core Version:    0.6.0
 */