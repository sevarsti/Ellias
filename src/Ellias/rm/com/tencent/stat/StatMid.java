package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class StatMid
{
  private static StatLogger a = k.b();
  private static DeviceInfo b = null;

  static DeviceInfo a(Context paramContext)
  {
    monitorenter;
    try
    {
      a locala = a.a(paramContext);
      DeviceInfo localDeviceInfo2 = a(locala.d("__MTA_DEVICE_INFO__", null));
      a.d("get device info from internal storage:" + localDeviceInfo2);
      DeviceInfo localDeviceInfo3 = a(locala.f("__MTA_DEVICE_INFO__", null));
      a.d("get device info from setting.system:" + localDeviceInfo3);
      DeviceInfo localDeviceInfo4 = a(locala.b("__MTA_DEVICE_INFO__", null));
      a.d("get device info from SharedPreference:" + localDeviceInfo4);
      b = a(localDeviceInfo4, localDeviceInfo3, localDeviceInfo2);
      if (b == null)
        b = new DeviceInfo();
      DeviceInfo localDeviceInfo5 = n.a(paramContext).b(paramContext);
      if (localDeviceInfo5 != null)
      {
        b.d(localDeviceInfo5.getImei());
        b.e(localDeviceInfo5.getMac());
        b.b(localDeviceInfo5.getUserType());
      }
      DeviceInfo localDeviceInfo1 = b;
      return localDeviceInfo1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        a.e(localThrowable);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  static DeviceInfo a(DeviceInfo paramDeviceInfo1, DeviceInfo paramDeviceInfo2)
  {
    if ((paramDeviceInfo1 != null) && (paramDeviceInfo2 != null))
      if (paramDeviceInfo1.a(paramDeviceInfo2) < 0);
    do
    {
      return paramDeviceInfo1;
      return paramDeviceInfo2;
    }
    while (paramDeviceInfo1 != null);
    if (paramDeviceInfo2 != null)
      return paramDeviceInfo2;
    return null;
  }

  static DeviceInfo a(DeviceInfo paramDeviceInfo1, DeviceInfo paramDeviceInfo2, DeviceInfo paramDeviceInfo3)
  {
    return a(a(paramDeviceInfo1, paramDeviceInfo2), a(paramDeviceInfo2, paramDeviceInfo3));
  }

  private static DeviceInfo a(String paramString)
  {
    if (paramString != null)
      return DeviceInfo.a(k.d(paramString));
    return null;
  }

  public static DeviceInfo getDeviceInfo(Context paramContext)
  {
    if (paramContext == null)
    {
      a.error("Context for StatConfig.getDeviceInfo is null.");
      return null;
    }
    if (b == null)
      a(paramContext);
    return b;
  }

  public static String getMid(Context paramContext)
  {
    if (b == null)
      getDeviceInfo(paramContext);
    return b.getMid();
  }

  public static void updateDeviceInfo(Context paramContext, String paramString)
  {
    try
    {
      getDeviceInfo(paramContext);
      b.c(paramString);
      b.a(1 + b.a());
      b.a(System.currentTimeMillis());
      String str1 = b.c().toString();
      a.d("save DeviceInfo:" + str1);
      String str2 = k.c(str1).replace("\n", "");
      a locala = a.a(paramContext);
      locala.c("__MTA_DEVICE_INFO__", str2);
      locala.e("__MTA_DEVICE_INFO__", str2);
      locala.a("__MTA_DEVICE_INFO__", str2);
      return;
    }
    catch (Throwable localThrowable)
    {
      a.e(localThrowable);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.StatMid
 * JD-Core Version:    0.6.0
 */