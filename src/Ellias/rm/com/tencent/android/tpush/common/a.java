package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.mid.util.Util;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static Object a(Context paramContext, String paramString, Object paramObject)
  {
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (localApplicationInfo != null)
      {
        Object localObject = localApplicationInfo.metaData.get(paramString);
        if (localObject != null)
          paramObject = localObject;
      }
      return paramObject;
    }
    catch (Throwable localThrowable)
    {
      TLog.e("TPush", "", localThrowable);
    }
    return paramObject;
  }

  public static JSONArray a(Context paramContext, int paramInt)
  {
    JSONArray localJSONArray;
    try
    {
      if ((Util.checkPermission(paramContext, "android.permission.INTERNET")) && (Util.checkPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
        if (localWifiManager != null)
        {
          List localList = localWifiManager.getScanResults();
          if ((localList != null) && (localList.size() > 0))
          {
            Collections.sort(localList, new b());
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
        TLog.w("TPush", "can not get the permisson of android.permission.INTERNET");
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        TLog.w("TPush", localThrowable.toString());
    }
    return localJSONArray;
  }

  public static boolean a(JSONObject paramJSONObject, String paramString, Object paramObject)
  {
    if (paramObject != null)
      try
      {
        paramJSONObject.put(paramString, paramObject);
        return true;
      }
      catch (JSONException localJSONException)
      {
      }
    return false;
  }

  public static Object b(JSONObject paramJSONObject, String paramString, Object paramObject)
  {
    try
    {
      if (paramJSONObject.has(paramString))
      {
        Object localObject = paramJSONObject.get(paramString);
        paramObject = localObject;
      }
      return paramObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return paramObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.a
 * JD-Core Version:    0.6.0
 */