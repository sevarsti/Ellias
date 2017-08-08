package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.stat.a.c;
import com.tencent.stat.a.h;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.f;
import com.tencent.stat.common.p;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class StatService
{
  private static Handler a;
  private static volatile Map<c, Long> b = new ConcurrentHashMap();
  private static volatile long c = 0L;
  private static volatile long d = 0L;
  private static volatile int e = 0;
  private static volatile String f = "";
  private static volatile String g = "";
  private static Map<String, Long> h = new ConcurrentHashMap();
  private static StatLogger i = com.tencent.stat.common.k.b();
  private static Thread.UncaughtExceptionHandler j = null;
  private static volatile boolean k = true;

  static int a(Context paramContext, boolean paramBoolean)
  {
    int m = 1;
    long l = System.currentTimeMillis();
    if ((paramBoolean) && (l - c >= StatConfig.getSessionTimoutMillis()));
    for (int n = m; ; n = 0)
    {
      c = l;
      if (d == 0L)
        d = com.tencent.stat.common.k.c();
      if (l >= d)
      {
        d = com.tencent.stat.common.k.c();
        if (n.a(paramContext).b(paramContext).getUserType() != m)
          n.a(paramContext).b(paramContext).b(m);
        StatConfig.b(0);
        StatMid.a(paramContext);
        n = m;
      }
      if (k);
      while (true)
      {
        if (m != 0)
        {
          if (StatConfig.e() >= StatConfig.getMaxDaySessionNumbers())
            break label150;
          com.tencent.stat.common.k.F(paramContext);
          d(paramContext);
        }
        while (true)
        {
          if (k)
          {
            f.b(paramContext);
            testSpeed(paramContext);
            e(paramContext);
            k = false;
          }
          return e;
          label150: i.e("Exceed StatConfig.getMaxDaySessionNumbers().");
        }
        m = n;
      }
    }
  }

  static JSONObject a()
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = new JSONObject();
      if (StatConfig.b.d != 0)
        localJSONObject2.put("v", StatConfig.b.d);
      localJSONObject1.put(Integer.toString(StatConfig.b.a), localJSONObject2);
      JSONObject localJSONObject3 = new JSONObject();
      if (StatConfig.a.d != 0)
        localJSONObject3.put("v", StatConfig.a.d);
      localJSONObject1.put(Integer.toString(StatConfig.a.a), localJSONObject3);
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      i.e(localJSONException);
    }
    return localJSONObject1;
  }

  static void a(Context paramContext)
  {
    monitorenter;
    if (paramContext == null);
    while (true)
    {
      monitorexit;
      return;
      try
      {
        if ((a != null) || (!b(paramContext)))
          continue;
        if (f.a(paramContext))
          break;
        i.e("ooh, Compatibility problem was found in this device!");
        i.e("If you are on debug mode, please delete apk and try again.");
        StatConfig.setEnableStatService(false);
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    HandlerThread localHandlerThread = new HandlerThread("StatService");
    localHandlerThread.start();
    a = new Handler(localHandlerThread.getLooper());
    n.a(paramContext);
    d.a(paramContext);
    d.b();
    StatConfig.getDeviceInfo(paramContext);
    j = Thread.getDefaultUncaughtExceptionHandler();
    if (StatConfig.isAutoExceptionCaught())
      Thread.setDefaultUncaughtExceptionHandler(new g(paramContext.getApplicationContext()));
    while (true)
    {
      if ((StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH) && (com.tencent.stat.common.k.h(paramContext)))
        n.a(paramContext).a(-1);
      i.d("Init MTA StatService success.");
      break;
      i.warn("MTA SDK AutoExceptionCaught is disable");
    }
  }

  static void a(Context paramContext, Throwable paramThrowable)
  {
    try
    {
      if (!StatConfig.isEnableStatService())
        return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.reportSdkSelfException() can not be null!");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      i.e("reportSdkSelfException error: " + localThrowable);
      return;
    }
    com.tencent.stat.a.d locald = new com.tencent.stat.a.d(paramContext, a(paramContext, false), 99, paramThrowable);
    if (c(paramContext) != null)
      c(paramContext).post(new k(locald));
  }

  static void a(Context paramContext, Map<String, ?> paramMap)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.sendAdditionEvent() can not be null!");
        return;
      }
      try
      {
        com.tencent.stat.a.a locala = new com.tencent.stat.a.a(paramContext, a(paramContext, false), paramMap);
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new k(locala));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  static boolean b(Context paramContext)
  {
    long l = p.a(paramContext, StatConfig.c, 0L);
    if (com.tencent.stat.common.k.b("1.6.2") <= l)
    {
      StatConfig.setEnableStatService(false);
      return false;
    }
    return true;
  }

  static Handler c(Context paramContext)
  {
    if (a == null)
      a(paramContext);
    return a;
  }

  public static void commitEvents(Context paramContext, int paramInt)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.commitEvents() can not be null!");
      return;
    }
    if ((paramInt < -1) || (paramInt == 0))
    {
      i.error("The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
      return;
    }
    try
    {
      n.a(paramContext).a(paramInt);
      return;
    }
    catch (Throwable localThrowable)
    {
      i.e(localThrowable);
      a(paramContext, localThrowable);
    }
  }

  static void d(Context paramContext)
  {
    if (c(paramContext) != null)
    {
      i.d("start new session.");
      e = com.tencent.stat.common.k.a();
      StatConfig.a(0);
      StatConfig.d();
      c(paramContext).post(new k(new com.tencent.stat.a.k(paramContext, e, a())));
    }
  }

  static void e(Context paramContext)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.reportNativeCrash() can not be null!");
        return;
      }
      try
      {
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new i(paramContext));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void onPause(Context paramContext)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.onPause() can not be null!");
      return;
    }
    trackEndPage(paramContext, com.tencent.stat.common.k.k(paramContext));
  }

  public static void onResume(Context paramContext)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.onResume() can not be null!");
      return;
    }
    trackBeginPage(paramContext, com.tencent.stat.common.k.k(paramContext));
  }

  public static void reportAppMonitorStat(Context paramContext, StatAppMonitor paramStatAppMonitor)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.reportAppMonitorStat() can not be null!");
        return;
      }
      if (paramStatAppMonitor == null)
      {
        i.error("The StatAppMonitor of StatService.reportAppMonitorStat() can not be null!");
        return;
      }
      if (paramStatAppMonitor.getInterfaceName() == null)
      {
        i.error("The interfaceName of StatAppMonitor on StatService.reportAppMonitorStat() can not be null!");
        return;
      }
      try
      {
        h localh = new h(paramContext, a(paramContext, false), paramStatAppMonitor);
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new k(localh));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void reportError(Context paramContext, String paramString)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.reportError() can not be null!");
        return;
      }
      if (a(paramString))
      {
        i.error("Error message in StatService.reportError() is empty.");
        return;
      }
      try
      {
        com.tencent.stat.a.d locald = new com.tencent.stat.a.d(paramContext, a(paramContext, false), paramString, 0, StatConfig.getMaxReportEventLength());
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new k(locald));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void reportException(Context paramContext, Throwable paramThrowable)
  {
    if (!StatConfig.isEnableStatService());
    com.tencent.stat.a.d locald;
    do
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.reportException() can not be null!");
        return;
      }
      if (paramThrowable == null)
      {
        i.error("The Throwable error message of StatService.reportException() can not be null!");
        return;
      }
      locald = new com.tencent.stat.a.d(paramContext, a(paramContext, false), 1, paramThrowable);
    }
    while (c(paramContext) == null);
    c(paramContext).post(new k(locald));
  }

  public static void reportGameUser(Context paramContext, StatGameUser paramStatGameUser)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.reportGameUser() can not be null!");
        return;
      }
      if (paramStatGameUser == null)
      {
        i.error("The gameUser of StatService.reportGameUser() can not be null!");
        return;
      }
      if ((paramStatGameUser.getAccount() == null) || (paramStatGameUser.getAccount().length() == 0))
      {
        i.error("The account of gameUser on StatService.reportGameUser() can not be null or empty!");
        return;
      }
      try
      {
        com.tencent.stat.a.g localg = new com.tencent.stat.a.g(paramContext, a(paramContext, false), paramStatGameUser);
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new k(localg));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void reportQQ(Context paramContext, String paramString)
  {
    if (paramString == null)
      paramString = "";
    if (!StatConfig.d.equals(paramString))
    {
      StatConfig.d = new String(paramString);
      a(paramContext, null);
    }
  }

  public static void setEnvAttributes(Context paramContext, Map<String, String> paramMap)
  {
    if ((paramMap == null) || (paramMap.size() > 512))
    {
      i.error("The map in setEnvAttributes can't be null or its size can't exceed 512.");
      return;
    }
    try
    {
      com.tencent.stat.common.a.a(paramContext, paramMap);
      return;
    }
    catch (JSONException localJSONException)
    {
      i.e(localJSONException);
    }
  }

  public static void startNewSession(Context paramContext)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.startNewSession() can not be null!");
      return;
    }
    try
    {
      stopSession();
      a(paramContext, true);
      return;
    }
    catch (Throwable localThrowable)
    {
      i.e(localThrowable);
      a(paramContext, localThrowable);
    }
  }

  public static boolean startStatService(Context paramContext, String paramString1, String paramString2)
  {
    if (!StatConfig.isEnableStatService())
    {
      i.error("MTA StatService is disable.");
      return false;
    }
    i.d("MTA SDK version, current: " + "1.6.2" + " ,required: " + paramString2);
    if ((paramContext == null) || (paramString2 == null))
    {
      i.error("Context or mtaSdkVersion in StatService.startStatService() is null, please check it!");
      StatConfig.setEnableStatService(false);
      throw new MtaSDkException("Context or mtaSdkVersion in StatService.startStatService() is null, please check it!");
    }
    if (com.tencent.stat.common.k.b("1.6.2") < com.tencent.stat.common.k.b(paramString2))
    {
      String str2 = "MTA SDK version conflicted, current: " + "1.6.2" + ",required: " + paramString2;
      String str3 = str2 + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/";
      i.error(str3);
      StatConfig.setEnableStatService(false);
      throw new MtaSDkException(str3);
    }
    try
    {
      String str1 = StatConfig.getInstallChannel(paramContext);
      if ((str1 == null) || (str1.length() == 0))
        StatConfig.setInstallChannel("-");
      if (paramString1 != null)
        StatConfig.setAppKey(paramContext, paramString1);
      c(paramContext);
      a(paramContext, false);
      return true;
    }
    catch (Throwable localThrowable)
    {
      i.e(localThrowable);
    }
    return false;
  }

  public static void stopSession()
  {
    c = 0L;
  }

  public static void testSpeed(Context paramContext)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.testSpeed() can not be null!");
        return;
      }
      try
      {
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new j(paramContext, null));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void testSpeed(Context paramContext, Map<String, Integer> paramMap)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.testSpeed() can not be null!");
        return;
      }
      if ((paramMap == null) || (paramMap.size() == 0))
      {
        i.error("The domainMap of StatService.testSpeed() can not be null or empty!");
        return;
      }
      try
      {
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new j(paramContext, paramMap));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void trackBeginPage(Context paramContext, String paramString)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if ((paramContext == null) || (paramString == null) || (paramString.length() == 0))
    {
      i.error("The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
      return;
    }
    try
    {
      synchronized (h)
      {
        if (h.size() >= StatConfig.getMaxParallelTimmingEvents())
        {
          i.error("The number of page events exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
          return;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      i.e(localThrowable);
      a(paramContext, localThrowable);
      return;
    }
    f = paramString;
    if (h.containsKey(f))
    {
      i.e("Duplicate PageID : " + f + ", onResume() repeated?");
      monitorexit;
      return;
    }
    h.put(f, Long.valueOf(System.currentTimeMillis()));
    monitorexit;
    a(paramContext, true);
  }

  public static void trackCustomBeginEvent(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.trackCustomBeginEvent() can not be null!");
      return;
    }
    if (a(paramString))
    {
      i.error("The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
      return;
    }
    c localc;
    try
    {
      localc = new c(paramString, paramArrayOfString, null);
      if (b.containsKey(localc))
      {
        i.error("Duplicate CustomEvent key: " + localc.toString() + ", trackCustomBeginEvent() repeated?");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      i.e(localThrowable);
      a(paramContext, localThrowable);
      return;
    }
    if (b.size() <= StatConfig.getMaxParallelTimmingEvents())
    {
      b.put(localc, Long.valueOf(System.currentTimeMillis()));
      return;
    }
    i.error("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
  }

  public static void trackCustomBeginKVEvent(Context paramContext, String paramString, Properties paramProperties)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.trackCustomBeginEvent() can not be null!");
      return;
    }
    if (a(paramString))
    {
      i.error("The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
      return;
    }
    c localc;
    try
    {
      localc = new c(paramString, null, paramProperties);
      if (b.containsKey(localc))
      {
        i.error("Duplicate CustomEvent key: " + localc.toString() + ", trackCustomBeginKVEvent() repeated?");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      i.e(localThrowable);
      a(paramContext, localThrowable);
      return;
    }
    if (b.size() <= StatConfig.getMaxParallelTimmingEvents())
    {
      b.put(localc, Long.valueOf(System.currentTimeMillis()));
      return;
    }
    i.error("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
  }

  public static void trackCustomEndEvent(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.trackCustomEndEvent() can not be null!");
      return;
    }
    if (a(paramString))
    {
      i.error("The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
      return;
    }
    c localc;
    while (true)
    {
      Long localLong2;
      try
      {
        localc = new c(paramString, paramArrayOfString, null);
        Long localLong1 = (Long)b.remove(localc);
        if (localLong1 == null)
          break label186;
        com.tencent.stat.a.b localb = new com.tencent.stat.a.b(paramContext, a(paramContext, false), paramString);
        localb.a(paramArrayOfString);
        localLong2 = Long.valueOf((System.currentTimeMillis() - localLong1.longValue()) / 1000L);
        if (localLong2.longValue() == 0L)
        {
          l = 1L;
          localb.a(Long.valueOf(l).longValue());
          if (c(paramContext) == null)
            break;
          c(paramContext).post(new k(localb));
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
        return;
      }
      long l = localLong2.longValue();
    }
    label186: i.error("No start time found for custom event: " + localc.toString() + ", lost trackCustomBeginEvent()?");
  }

  public static void trackCustomEndKVEvent(Context paramContext, String paramString, Properties paramProperties)
  {
    if (!StatConfig.isEnableStatService())
      return;
    if (paramContext == null)
    {
      i.error("The Context of StatService.trackCustomEndEvent() can not be null!");
      return;
    }
    if (a(paramString))
    {
      i.error("The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
      return;
    }
    c localc;
    while (true)
    {
      Long localLong2;
      try
      {
        localc = new c(paramString, null, paramProperties);
        Long localLong1 = (Long)b.remove(localc);
        if (localLong1 == null)
          break label186;
        com.tencent.stat.a.b localb = new com.tencent.stat.a.b(paramContext, a(paramContext, false), paramString);
        localb.a(paramProperties);
        localLong2 = Long.valueOf((System.currentTimeMillis() - localLong1.longValue()) / 1000L);
        if (localLong2.longValue() == 0L)
        {
          l = 1L;
          localb.a(Long.valueOf(l).longValue());
          if (c(paramContext) == null)
            break;
          c(paramContext).post(new k(localb));
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
        return;
      }
      long l = localLong2.longValue();
    }
    label186: i.error("No start time found for custom event: " + localc.toString() + ", lost trackCustomBeginKVEvent()?");
  }

  public static void trackCustomEvent(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.trackCustomEvent() can not be null!");
        return;
      }
      if (a(paramString))
      {
        i.error("The event_id of StatService.trackCustomEvent() can not be null or empty.");
        return;
      }
      try
      {
        com.tencent.stat.a.b localb = new com.tencent.stat.a.b(paramContext, a(paramContext, false), paramString);
        localb.a(paramArrayOfString);
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new k(localb));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  public static void trackCustomKVEvent(Context paramContext, String paramString, Properties paramProperties)
  {
    if (!StatConfig.isEnableStatService());
    while (true)
    {
      return;
      if (paramContext == null)
      {
        i.error("The Context of StatService.trackCustomEvent() can not be null!");
        return;
      }
      if (a(paramString))
      {
        i.error("The event_id of StatService.trackCustomEvent() can not be null or empty.");
        return;
      }
      try
      {
        com.tencent.stat.a.b localb = new com.tencent.stat.a.b(paramContext, a(paramContext, false), paramString);
        localb.a(paramProperties);
        if (c(paramContext) == null)
          continue;
        c(paramContext).post(new k(localb));
        return;
      }
      catch (Throwable localThrowable)
      {
        i.e(localThrowable);
        a(paramContext, localThrowable);
      }
    }
  }

  // ERROR //
  public static void trackEndPage(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: invokestatic 255	com/tencent/stat/StatConfig:isEnableStatService	()Z
    //   3: ifne +4 -> 7
    //   6: return
    //   7: aload_0
    //   8: ifnull +14 -> 22
    //   11: aload_1
    //   12: ifnull +10 -> 22
    //   15: aload_1
    //   16: invokevirtual 310	java/lang/String:length	()I
    //   19: ifne +13 -> 32
    //   22: getstatic 55	com/tencent/stat/StatService:i	Lcom/tencent/stat/common/StatLogger;
    //   25: ldc_w 571
    //   28: invokevirtual 260	com/tencent/stat/common/StatLogger:error	(Ljava/lang/Object;)V
    //   31: return
    //   32: getstatic 48	com/tencent/stat/StatService:h	Ljava/util/Map;
    //   35: astore_3
    //   36: aload_3
    //   37: monitorenter
    //   38: getstatic 48	com/tencent/stat/StatService:h	Ljava/util/Map;
    //   41: aload_1
    //   42: invokeinterface 537 2 0
    //   47: checkcast 499	java/lang/Long
    //   50: astore 5
    //   52: aload_3
    //   53: monitorexit
    //   54: aload 5
    //   56: ifnull +151 -> 207
    //   59: invokestatic 67	java/lang/System:currentTimeMillis	()J
    //   62: aload 5
    //   64: invokevirtual 548	java/lang/Long:longValue	()J
    //   67: lsub
    //   68: ldc2_w 549
    //   71: ldiv
    //   72: invokestatic 503	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   75: astore 6
    //   77: aload 6
    //   79: invokevirtual 548	java/lang/Long:longValue	()J
    //   82: lconst_0
    //   83: lcmp
    //   84: ifgt +9 -> 93
    //   87: lconst_1
    //   88: invokestatic 503	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   91: astore 6
    //   93: getstatic 46	com/tencent/stat/StatService:g	Ljava/lang/String;
    //   96: astore 7
    //   98: aload 7
    //   100: ifnull +18 -> 118
    //   103: aload 7
    //   105: aload_1
    //   106: invokevirtual 425	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   109: iconst_1
    //   110: if_icmpne +8 -> 118
    //   113: ldc_w 469
    //   116: astore 7
    //   118: aload_0
    //   119: invokestatic 285	com/tencent/stat/StatService:c	(Landroid/content/Context;)Landroid/os/Handler;
    //   122: ifnull +59 -> 181
    //   125: new 573	com/tencent/stat/a/j
    //   128: dup
    //   129: aload_0
    //   130: aload 7
    //   132: aload_1
    //   133: aload_0
    //   134: iconst_0
    //   135: invokestatic 279	com/tencent/stat/StatService:a	(Landroid/content/Context;Z)I
    //   138: aload 6
    //   140: invokespecial 576	com/tencent/stat/a/j:<init>	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Long;)V
    //   143: astore 8
    //   145: aload_1
    //   146: getstatic 44	com/tencent/stat/StatService:f	Ljava/lang/String;
    //   149: invokevirtual 425	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   152: ifne +12 -> 164
    //   155: getstatic 55	com/tencent/stat/StatService:i	Lcom/tencent/stat/common/StatLogger;
    //   158: ldc_w 578
    //   161: invokevirtual 249	com/tencent/stat/common/StatLogger:warn	(Ljava/lang/Object;)V
    //   164: aload_0
    //   165: invokestatic 285	com/tencent/stat/StatService:c	(Landroid/content/Context;)Landroid/os/Handler;
    //   168: new 287	com/tencent/stat/k
    //   171: dup
    //   172: aload 8
    //   174: invokespecial 290	com/tencent/stat/k:<init>	(Lcom/tencent/stat/a/e;)V
    //   177: invokevirtual 294	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   180: pop
    //   181: aload_1
    //   182: putstatic 46	com/tencent/stat/StatService:g	Ljava/lang/String;
    //   185: return
    //   186: astore_2
    //   187: getstatic 55	com/tencent/stat/StatService:i	Lcom/tencent/stat/common/StatLogger;
    //   190: aload_2
    //   191: invokevirtual 123	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   194: aload_0
    //   195: aload_2
    //   196: invokestatic 304	com/tencent/stat/StatService:a	(Landroid/content/Context;Ljava/lang/Throwable;)V
    //   199: return
    //   200: astore 4
    //   202: aload_3
    //   203: monitorexit
    //   204: aload 4
    //   206: athrow
    //   207: getstatic 55	com/tencent/stat/StatService:i	Lcom/tencent/stat/common/StatLogger;
    //   210: new 262	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 263	java/lang/StringBuilder:<init>	()V
    //   217: ldc_w 580
    //   220: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_1
    //   224: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: ldc_w 582
    //   230: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: invokevirtual 123	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   239: return
    //
    // Exception table:
    //   from	to	target	type
    //   32	38	186	java/lang/Throwable
    //   59	93	186	java/lang/Throwable
    //   93	98	186	java/lang/Throwable
    //   103	113	186	java/lang/Throwable
    //   118	164	186	java/lang/Throwable
    //   164	181	186	java/lang/Throwable
    //   181	185	186	java/lang/Throwable
    //   204	207	186	java/lang/Throwable
    //   207	239	186	java/lang/Throwable
    //   38	54	200	finally
    //   202	204	200	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.StatService
 * JD-Core Version:    0.6.0
 */