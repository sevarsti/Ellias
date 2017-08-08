package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.tencent.stat.common.p;
import java.util.Iterator;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;

public class StatConfig
{
  private static int A;
  private static boolean B;
  private static String C;
  static b a;
  static b b;
  static String c;
  static String d;
  private static StatLogger e = k.b();
  private static StatReportStrategy f;
  private static boolean g;
  private static int h;
  private static int i;
  public static boolean isAutoExceptionCaught;
  private static int j;
  private static int k;
  private static int l;
  private static String m;
  private static String n;
  private static String o;
  private static int p;
  private static int q;
  private static boolean r;
  private static long s;
  private static long t;
  private static String u;
  private static int v;
  private static volatile int w;
  private static int x;
  private static int y;
  private static boolean z;

  static
  {
    a = new b(2);
    b = new b(1);
    f = StatReportStrategy.APP_LAUNCH;
    g = true;
    h = 30000;
    i = 1024;
    j = 30;
    k = 3;
    l = 30;
    c = "__HIBERNATE__";
    m = null;
    d = "";
    p = 1440;
    q = 1024;
    r = true;
    s = 0L;
    t = 300000L;
    isAutoExceptionCaught = true;
    u = "http://pingma.qq.com:80/mstat/report";
    v = 0;
    w = 0;
    x = 20;
    y = 0;
    z = false;
    A = 4096;
    B = false;
    C = null;
  }

  static int a()
  {
    return j;
  }

  static String a(Context paramContext)
  {
    return k.d(p.a(paramContext, "_mta_ky_tag_", null));
  }

  static String a(String paramString1, String paramString2)
  {
    try
    {
      String str = b.b.getString(paramString1);
      if (str != null)
        paramString2 = str;
      return paramString2;
    }
    catch (Throwable localThrowable)
    {
      e.w(localThrowable);
    }
    return paramString2;
  }

  static void a(int paramInt)
  {
    monitorenter;
    try
    {
      w = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  static void a(Context paramContext, String paramString)
  {
    if (paramString != null)
      p.b(paramContext, "_mta_ky_tag_", k.c(paramString));
  }

  static void a(b paramb)
  {
    if (paramb.a == b.a)
    {
      b = paramb;
      b(b.b);
    }
    do
      return;
    while (paramb.a != a.a);
    a = paramb;
  }

  static void a(b paramb, JSONObject paramJSONObject)
  {
    int i1 = 0;
    int i2;
    while (true)
    {
      try
      {
        Iterator localIterator = paramJSONObject.keys();
        if (!localIterator.hasNext())
          continue;
        String str1 = (String)localIterator.next();
        if (!str1.equalsIgnoreCase("v"))
          continue;
        int i3 = paramJSONObject.getInt(str1);
        if (paramb.d != i3)
        {
          i2 = 1;
          paramb.d = i3;
          break;
          if (!str1.equalsIgnoreCase("c"))
            continue;
          String str2 = paramJSONObject.getString("c");
          if (str2.length() <= 0)
            break label218;
          paramb.b = new JSONObject(str2);
          break label218;
          if (!str1.equalsIgnoreCase("m"))
            break label224;
          paramb.c = paramJSONObject.getString("m");
          break label224;
          if (i1 != 1)
            continue;
          n localn = n.a(d.a());
          if (localn == null)
            continue;
          localn.a(paramb);
          if (paramb.a != b.a)
            continue;
          b(paramb.b);
          c(paramb.b);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        e.e(localJSONException);
        return;
      }
      catch (Throwable localThrowable)
      {
        e.e(localThrowable);
        return;
      }
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      break;
      label218: i2 = i1;
      continue;
      label224: i2 = i1;
    }
  }

  static void a(JSONObject paramJSONObject)
  {
    while (true)
    {
      String str;
      try
      {
        Iterator localIterator = paramJSONObject.keys();
        if (localIterator.hasNext())
        {
          str = (String)localIterator.next();
          if (!str.equalsIgnoreCase(Integer.toString(b.a)))
            break label67;
          JSONObject localJSONObject2 = paramJSONObject.getJSONObject(str);
          a(b, localJSONObject2);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        e.e(localJSONException);
      }
      label67: 
      do
      {
        return;
        if (!str.equalsIgnoreCase(Integer.toString(a.a)))
          continue;
        JSONObject localJSONObject1 = paramJSONObject.getJSONObject(str);
        a(a, localJSONObject1);
        break;
      }
      while (!str.equalsIgnoreCase("rs"));
      StatReportStrategy localStatReportStrategy = StatReportStrategy.getStatReportStrategy(paramJSONObject.getInt(str));
      if (localStatReportStrategy == null)
        continue;
      f = localStatReportStrategy;
      e.d("Change to ReportStrategy:" + localStatReportStrategy.name());
    }
  }

  static void a(boolean paramBoolean)
  {
    StatNativeCrashReport.setNativeCrashDebugEnable(paramBoolean);
  }

  static boolean a(int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramInt1 >= paramInt2) && (paramInt1 <= paramInt3);
  }

  private static boolean a(String paramString)
  {
    if (paramString == null);
    do
    {
      return false;
      if (n != null)
        continue;
      n = paramString;
      return true;
    }
    while (n.contains(paramString));
    n = n + "|" + paramString;
    return true;
  }

  static HttpHost b()
  {
    if ((m != null) && (m.length() > 0))
    {
      String str = m;
      String[] arrayOfString = str.split(":");
      int i1 = 80;
      if (arrayOfString.length == 2)
      {
        str = arrayOfString[0];
        i1 = Integer.parseInt(arrayOfString[1]);
      }
      return new HttpHost(str, i1);
    }
    return null;
  }

  static void b(int paramInt)
  {
    if (paramInt < 0)
      return;
    y = paramInt;
  }

  static void b(JSONObject paramJSONObject)
  {
    try
    {
      StatReportStrategy localStatReportStrategy = StatReportStrategy.getStatReportStrategy(paramJSONObject.getInt("rs"));
      if (localStatReportStrategy != null)
        setStatSendStrategy(localStatReportStrategy);
      return;
    }
    catch (JSONException localJSONException)
    {
      e.d("rs not found.");
    }
  }

  static void c()
  {
    monitorenter;
    try
    {
      w = 1 + w;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  static void c(JSONObject paramJSONObject)
  {
    try
    {
      String str = paramJSONObject.getString(c);
      e.d("hibernateVer:" + str + ", current version:" + "1.6.2");
      long l1 = k.b(str);
      if (k.b("1.6.2") <= l1)
      {
        p.b(d.a(), c, l1);
        setEnableStatService(false);
        e.warn("MTA has disable for SDK version of " + str + " or lower.");
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      e.d("__HIBERNATE__ not found.");
    }
  }

  static void d()
  {
    y = 1 + y;
  }

  static int e()
  {
    return y;
  }

  public static String getAppKey(Context paramContext)
  {
    monitorenter;
    try
    {
      if (n != null);
      for (String str = n; ; str = n)
      {
        return str;
        if ((paramContext != null) && (n == null))
          n = k.i(paramContext);
        if ((n != null) && (n.trim().length() != 0))
          continue;
        e.error("AppKey can not be null or empty, please read Developer's Guide first!");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static int getCurSessionStatReportCount()
  {
    return w;
  }

  public static String getCustomProperty(String paramString)
  {
    try
    {
      String str = a.b.getString(paramString);
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.e(localThrowable);
    }
    return null;
  }

  public static String getCustomProperty(String paramString1, String paramString2)
  {
    try
    {
      String str = a.b.getString(paramString1);
      if (str != null)
        paramString2 = str;
      return paramString2;
    }
    catch (Throwable localThrowable)
    {
      e.e(localThrowable);
    }
    return paramString2;
  }

  public static String getCustomUserId(Context paramContext)
  {
    if (paramContext == null)
    {
      e.error("Context for getCustomUid is null.");
      return null;
    }
    if (C == null)
      C = p.a(paramContext, "MTA_CUSTOM_UID", "");
    return C;
  }

  public static DeviceInfo getDeviceInfo(Context paramContext)
  {
    return StatMid.getDeviceInfo(paramContext);
  }

  public static String getInstallChannel(Context paramContext)
  {
    monitorenter;
    try
    {
      if (o != null);
      for (String str = o; ; str = o)
      {
        return str;
        o = k.j(paramContext);
        if ((o != null) && (o.trim().length() != 0))
          continue;
        e.w("installChannel can not be null or empty, please read Developer's Guide first!");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static int getMaxBatchReportCount()
  {
    return l;
  }

  public static int getMaxDaySessionNumbers()
  {
    return x;
  }

  public static int getMaxParallelTimmingEvents()
  {
    return q;
  }

  public static int getMaxReportEventLength()
  {
    return A;
  }

  public static int getMaxSendRetryCount()
  {
    return k;
  }

  public static int getMaxSessionStatReportCount()
  {
    return v;
  }

  public static int getMaxStoreEventCount()
  {
    return i;
  }

  public static String getMid(Context paramContext)
  {
    return StatMid.getMid(paramContext);
  }

  public static String getQQ()
  {
    return d;
  }

  public static int getSendPeriodMinutes()
  {
    return p;
  }

  public static int getSessionTimoutMillis()
  {
    return h;
  }

  public static String getStatReportUrl()
  {
    return u;
  }

  public static StatReportStrategy getStatSendStrategy()
  {
    return f;
  }

  public static void initNativeCrashReport(Context paramContext, String paramString)
  {
    if (!isEnableStatService())
      return;
    if (paramContext == null)
    {
      e.error("The Context of StatConfig.initNativeCrashReport() can not be null!");
      return;
    }
    StatNativeCrashReport.initNativeCrash(paramContext, paramString);
  }

  public static boolean isAutoExceptionCaught()
  {
    return isAutoExceptionCaught;
  }

  public static boolean isDebugEnable()
  {
    return k.b().isDebugEnable();
  }

  public static boolean isEnableConcurrentProcess()
  {
    return B;
  }

  public static boolean isEnableSmartReporting()
  {
    return r;
  }

  public static boolean isEnableStatService()
  {
    return g;
  }

  public static void setAppKey(Context paramContext, String paramString)
  {
    if (paramContext == null)
      e.error("ctx in StatConfig.setAppKey() is null");
    do
    {
      return;
      if ((paramString == null) || (paramString.length() > 256))
      {
        e.error("appkey in StatConfig.setAppKey() is null or exceed 256 bytes");
        return;
      }
      if (n != null)
        continue;
      n = a(paramContext);
    }
    while (!(a(paramString) | a(k.i(paramContext))));
    a(paramContext, n);
  }

  public static void setAppKey(String paramString)
  {
    if (paramString == null)
    {
      e.error("appkey in StatConfig.setAppKey() is null");
      return;
    }
    if (paramString.length() > 256)
    {
      e.error("The length of appkey cann't exceed 256 bytes.");
      return;
    }
    n = paramString;
  }

  public static void setAutoExceptionCaught(boolean paramBoolean)
  {
    isAutoExceptionCaught = paramBoolean;
  }

  public static void setCustomUserId(Context paramContext, String paramString)
  {
    if (paramContext == null)
    {
      e.error("Context for setCustomUid is null.");
      return;
    }
    p.b(paramContext, "MTA_CUSTOM_UID", paramString);
    C = paramString;
  }

  public static void setDebugEnable(boolean paramBoolean)
  {
    k.b().setDebugEnable(paramBoolean);
    a(paramBoolean);
  }

  public static void setEnableConcurrentProcess(boolean paramBoolean)
  {
    B = paramBoolean;
  }

  public static void setEnableSmartReporting(boolean paramBoolean)
  {
    r = paramBoolean;
  }

  public static void setEnableStatService(boolean paramBoolean)
  {
    g = paramBoolean;
    if (!paramBoolean)
      e.warn("!!!!!!MTA StatService has been disabled!!!!!!");
  }

  public static void setInstallChannel(String paramString)
  {
    if (paramString.length() > 128)
    {
      e.error("the length of installChannel can not exceed the range of 128 bytes.");
      return;
    }
    o = paramString;
  }

  public static void setMaxBatchReportCount(int paramInt)
  {
    if (!a(paramInt, 2, 1000))
    {
      e.error("setMaxBatchReportCount can not exceed the range of [2,1000].");
      return;
    }
    l = paramInt;
  }

  public static void setMaxDaySessionNumbers(int paramInt)
  {
    if (paramInt <= 0)
    {
      e.e("maxDaySessionNumbers must be greater than 0.");
      return;
    }
    x = paramInt;
  }

  public static void setMaxParallelTimmingEvents(int paramInt)
  {
    if (!a(paramInt, 1, 4096))
    {
      e.error("setMaxParallelTimmingEvents can not exceed the range of [1, 4096].");
      return;
    }
    q = paramInt;
  }

  public static void setMaxReportEventLength(int paramInt)
  {
    if (paramInt <= 0)
    {
      e.error("maxReportEventLength on setMaxReportEventLength() must greater than 0.");
      return;
    }
    A = paramInt;
  }

  public static void setMaxSendRetryCount(int paramInt)
  {
    if (!a(paramInt, 1, 1000))
    {
      e.error("setMaxSendRetryCount can not exceed the range of [1,1000].");
      return;
    }
    k = paramInt;
  }

  public static void setMaxSessionStatReportCount(int paramInt)
  {
    if (paramInt < 0)
    {
      e.error("maxSessionStatReportCount cannot be less than 0.");
      return;
    }
    v = paramInt;
  }

  public static void setMaxStoreEventCount(int paramInt)
  {
    if (!a(paramInt, 0, 500000))
    {
      e.error("setMaxStoreEventCount can not exceed the range of [0, 500000].");
      return;
    }
    i = paramInt;
  }

  public static void setQQ(Context paramContext, String paramString)
  {
    StatService.reportQQ(paramContext, paramString);
  }

  public static void setSendPeriodMinutes(int paramInt)
  {
    if (!a(paramInt, 1, 10080))
    {
      e.error("setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
      return;
    }
    p = paramInt;
  }

  public static void setSessionTimoutMillis(int paramInt)
  {
    if (!a(paramInt, 1000, 86400000))
    {
      e.error("setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
      return;
    }
    h = paramInt;
  }

  public static void setStatReportUrl(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
    {
      e.error("statReportUrl cannot be null or empty.");
      return;
    }
    u = paramString;
  }

  public static void setStatSendStrategy(StatReportStrategy paramStatReportStrategy)
  {
    f = paramStatReportStrategy;
    e.d("Change to statSendStrategy: " + paramStatReportStrategy);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.StatConfig
 * JD-Core Version:    0.6.0
 */