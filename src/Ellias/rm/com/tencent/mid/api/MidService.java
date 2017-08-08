package com.tencent.mid.api;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.tencent.mid.a.h;
import com.tencent.mid.b.g;
import com.tencent.mid.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MidService
{
  private static Context a = null;
  private static Handler b = null;
  private static MidService c = null;
  private static final List<String> d = new ArrayList(Arrays.asList(new String[] { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE" }));
  private static boolean e = false;

  private MidService(Context paramContext)
  {
    HandlerThread localHandlerThread = new HandlerThread(getClass().getSimpleName());
    localHandlerThread.start();
    b = new Handler(localHandlerThread.getLooper());
    a = paramContext.getApplicationContext();
  }

  static MidService a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new MidService(paramContext);
      MidService localMidService = c;
      return localMidService;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private static boolean a(Context paramContext, MidCallback paramMidCallback)
  {
    Iterator localIterator = d.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (Util.checkPermission(paramContext, str))
        continue;
      paramMidCallback.onFail(-10001, "permission :" + str + " is denyed, please set it on AndroidManifest.xml first");
      return false;
    }
    if ((!Util.checkPermission(paramContext, "android.permission.WRITE_SETTINGS")) && (!Util.checkPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE")))
    {
      paramMidCallback.onFail(-10001, "failed to get permission either permission android.permission.WRITE_SETTINGS or android.permission.WRITE_EXTERNAL_STORAGE");
      return false;
    }
    if (!Util.checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
      Log.e("MID", "android.permission.READ_PHONE_STATE is denyed.");
    return true;
  }

  private static void b(Context paramContext, MidCallback paramMidCallback)
  {
    if (!a(paramContext, paramMidCallback));
    do
    {
      while (true)
      {
        return;
        a(paramContext);
        MidEntity localMidEntity = g.a(paramContext).a();
        if ((localMidEntity == null) || (!localMidEntity.isMidValid()))
          break;
        Util.logInfo("get local mid entity:" + localMidEntity.toString());
        paramMidCallback.onSuccess(localMidEntity.toString());
        if (b == null)
          continue;
        b.post(new h(paramContext, 2, paramMidCallback));
        return;
      }
      Util.logInfo("request new mid entity.");
    }
    while (b == null);
    b.post(new h(paramContext, 1, paramMidCallback));
  }

  public static void enableDebug(boolean paramBoolean)
  {
    e = paramBoolean;
  }

  public static String getLocalMidOnly(Context paramContext)
  {
    MidEntity localMidEntity = g.a(paramContext).a();
    if ((localMidEntity != null) && (localMidEntity.isMidValid()))
      return localMidEntity.getMid();
    return "";
  }

  public static String getMid(Context paramContext)
  {
    if (paramContext == null)
    {
      Log.e("MID", "context==null in getMid()");
      return null;
    }
    a(paramContext);
    MidEntity localMidEntity = g.a(paramContext).a();
    if (localMidEntity == null)
      localMidEntity = new MidEntity();
    if (!isMidValid(localMidEntity.getMid()))
    {
      Util.logInfo("request new mid entity.");
      if (b != null)
        b.post(new h(paramContext, 1, new b()));
    }
    while (true)
    {
      return localMidEntity.getMid();
      if (b == null)
        continue;
      b.post(new h(paramContext, 2, new c()));
    }
  }

  public static boolean isEnableDebug()
  {
    return e;
  }

  public static boolean isMidValid(String paramString)
  {
    return Util.isMidValid(paramString);
  }

  public static void requestMid(Context paramContext, MidCallback paramMidCallback)
  {
    if (paramMidCallback == null)
      throw new IllegalArgumentException("error, callback is null!");
    if (paramContext == null)
    {
      paramMidCallback.onFail(-10000, "content is null!");
      return;
    }
    b(paramContext, new a(paramMidCallback));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.api.MidService
 * JD-Core Version:    0.6.0
 */