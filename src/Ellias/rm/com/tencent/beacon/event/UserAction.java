package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.a.b.e;
import com.tencent.beacon.a.f;
import com.tencent.beacon.upload.UploadHandleListener;
import com.tencent.beacon.upload.g;
import java.util.List;
import java.util.Map;

public class UserAction
{
  protected static Map<String, String> a;
  private static Context b = null;
  private static String c = "";
  private static String d = "10000";

  static
  {
    a = null;
  }

  private static void a(Context paramContext, UploadHandleListener paramUploadHandleListener, boolean paramBoolean, long paramLong)
  {
    b = paramContext.getApplicationContext();
    if (paramLong > 0L)
    {
      if (paramLong > 10000L)
        paramLong = 10000L;
      com.tencent.beacon.a.b.d.a(paramLong);
    }
    g localg = m.a(b, paramBoolean);
    m.a(b, true, localg, paramUploadHandleListener, null);
    com.tencent.beacon.b.d.a(b, localg);
  }

  public static void clearAppTotalConsume(Context paramContext)
  {
    f.c(paramContext);
  }

  public static void clearSDKTotalConsume(Context paramContext)
  {
    f.c(paramContext);
  }

  public static void closeUseInfoEvent()
  {
    m localm = m.d();
    if (localm != null)
      localm.f();
  }

  public static void doUploadRecords()
  {
    m.g();
  }

  public static String getCloudParas(String paramString)
  {
    Map localMap = e.a().d();
    String str = null;
    if (localMap != null)
      str = (String)localMap.get(paramString);
    return str;
  }

  public static String getGatewayIP()
  {
    com.tencent.beacon.a.d locald = com.tencent.beacon.a.d.m();
    if (locald != null)
      return locald.g();
    return "unknown";
  }

  public static String getQIMEI()
    throws Exception
  {
    if ((b == null) || (m.d() == null))
    {
      com.tencent.beacon.d.a.d("please initUserAction first!", new Object[0]);
      throw new RuntimeException("please initUserAction first!");
    }
    if (m.d().l() <= 0)
    {
      com.tencent.beacon.d.a.d("call this function getQIMEI untimely!", new Object[0]);
      return "";
    }
    return com.tencent.beacon.b.a.a(b).a();
  }

  public static String getQQ()
  {
    return c;
  }

  public static long getSDKTotalConsume(Context paramContext, boolean paramBoolean)
  {
    com.tencent.beacon.a.a.d locald = f.b(paramContext);
    if (locald != null)
    {
      if (paramBoolean)
        return locald.e;
      return locald.d + locald.e;
    }
    return -1L;
  }

  public static String getSDKVersion()
  {
    return "1.8.10";
  }

  public static d getUserActionRuntimeStrategy()
  {
    try
    {
      d locald = m.d().k();
      return locald;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }

  public static String getUserID()
  {
    return d;
  }

  @Deprecated
  public static boolean heartbeatEvent()
  {
    com.tencent.beacon.d.a.c(" heartbeatEvent is Deprecated !", new Object[0]);
    return true;
  }

  public static void initUserAction(Context paramContext)
  {
    a(paramContext, null, true, 0L);
  }

  public static void initUserAction(Context paramContext, boolean paramBoolean)
  {
    a(paramContext, null, paramBoolean, 0L);
  }

  public static void initUserAction(Context paramContext, boolean paramBoolean, long paramLong)
  {
    a(paramContext, null, paramBoolean, paramLong);
  }

  public static void initUserAction(Context paramContext, boolean paramBoolean, long paramLong, UploadHandleListener paramUploadHandleListener)
  {
    a(paramContext, paramUploadHandleListener, paramBoolean, paramLong);
  }

  public static boolean loginEvent(boolean paramBoolean, long paramLong, Map<String, String> paramMap)
  {
    return m.a("wgLogin", paramBoolean, paramLong, 0L, paramMap, true);
  }

  public static void onAppExited()
  {
    m.e();
  }

  public static boolean onMergeUserAction(String paramString, boolean paramBoolean1, long paramLong1, long paramLong2, boolean paramBoolean2, CountItem[] paramArrayOfCountItem)
  {
    if ((paramString == null) || ("".equals(paramString.trim())))
      com.tencent.beacon.d.a.c("param eventName is null or \"\", please check it, return false! ", new Object[0]);
    do
      return false;
    while (com.tencent.beacon.applog.a.b(paramString) == null);
    return m.a(paramString, paramBoolean1, paramLong1, paramLong2, paramBoolean2, paramArrayOfCountItem);
  }

  public static boolean onUserAction(String paramString, boolean paramBoolean1, long paramLong1, long paramLong2, Map<String, String> paramMap, boolean paramBoolean2)
  {
    if ((paramString == null) || ("".equals(paramString.trim())))
    {
      com.tencent.beacon.d.a.c("param eventName is null or \"\", please check it, return false! ", new Object[0]);
      return false;
    }
    String str = com.tencent.beacon.applog.a.b(paramString);
    if (str == null)
      return false;
    return m.a(str, paramBoolean1, paramLong1, paramLong2, paramMap, paramBoolean2);
  }

  public static void setAdditionalInfo(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() <= 10))
      a = paramMap;
  }

  public static void setAppKey(Context paramContext, String paramString)
    throws Exception
  {
    com.tencent.beacon.d.a.a(" setAppKey:" + paramString, new Object[0]);
    b = paramContext.getApplicationContext();
    if ((paramString != null) && (paramString.trim().length() > 0) && (paramString.trim().length() < 20))
    {
      com.tencent.beacon.a.d locald = com.tencent.beacon.a.d.m();
      if (locald == null)
      {
        com.tencent.beacon.a.d.a(b);
        locald = com.tencent.beacon.a.d.m();
      }
      while (true)
      {
        if (locald != null)
          locald.c(paramString);
        return;
        locald.c(paramString);
      }
    }
    com.tencent.beacon.d.a.c(" setAppKey: appkey is null or not available!", new Object[0]);
    throw new RuntimeException("appkey is null or not available! please check it!");
  }

  @Deprecated
  public static void setAutoLaunchEventUsable(boolean paramBoolean)
  {
  }

  public static void setChannelID(String paramString)
  {
    com.tencent.beacon.a.d locald = com.tencent.beacon.a.d.m();
    if (locald == null)
    {
      com.tencent.beacon.a.d.a(b);
      locald = com.tencent.beacon.a.d.m();
    }
    if (locald == null)
    {
      com.tencent.beacon.d.a.d("please set the channelID after call initUserAction!", new Object[0]);
      return;
    }
    locald.b(com.tencent.beacon.applog.a.d(paramString));
  }

  public static void setInitChannelPath(Context paramContext, String paramString)
    throws Exception
  {
    if (b == null)
    {
      com.tencent.beacon.a.a.a(paramContext, paramString);
      return;
    }
    com.tencent.beacon.d.a.d("please set the channel path before call initUserAction!", new Object[0]);
    throw new RuntimeException("please set the channel path before call initUserAction!");
  }

  public static void setLogAble(boolean paramBoolean1, boolean paramBoolean2)
  {
    com.tencent.beacon.d.a.a = paramBoolean1;
    com.tencent.beacon.d.a.b = paramBoolean2;
  }

  @Deprecated
  public static void setNetSpeedMonitorUsable(boolean paramBoolean)
  {
    com.tencent.beacon.d.a.c(" SpeedMonitorModule is Deprecated !", new Object[0]);
  }

  public static void setQQ(String paramString)
  {
    if (paramString != null)
      try
      {
        if (Long.parseLong(paramString) > 10000L)
          c = paramString;
        return;
      }
      catch (Exception localException)
      {
        com.tencent.beacon.d.a.c(" setQQ: set qq is not available !", new Object[0]);
        return;
      }
    com.tencent.beacon.d.a.c(" setQQ: set qq is null !", new Object[0]);
  }

  public static void setUploadMode(boolean paramBoolean)
  {
    m localm = m.d();
    if (localm != null)
    {
      localm.b(paramBoolean);
      return;
    }
    com.tencent.beacon.d.a.c(" UserActionRecord.getInstance is null, please initUserAction first!", new Object[0]);
  }

  public static void setUserActionUsable(boolean paramBoolean)
  {
    m localm = m.d();
    if (localm != null)
    {
      localm.a(paramBoolean);
      return;
    }
    com.tencent.beacon.d.a.c(" UserActionRecord.getInstance is null, please initUserAction first!", new Object[0]);
  }

  public static void setUserID(String paramString)
  {
    com.tencent.beacon.d.a.a(" setUserID:" + paramString, new Object[0]);
    if ((paramString != null) && (paramString.trim().length() > 0) && (!"10000".equals(paramString)) && (!"10000".equals(com.tencent.beacon.applog.a.c(paramString))))
      d = paramString;
  }

  public static boolean testSpeedDomain(List<String> paramList)
  {
    m localm = m.d();
    if (localm != null)
      return localm.b(paramList);
    return false;
  }

  public static boolean testSpeedIp(List<String> paramList)
  {
    m localm = m.d();
    if (localm != null)
      return localm.a(paramList);
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.UserAction
 * JD-Core Version:    0.6.0
 */