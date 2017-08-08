package com.tencent.qqgamemi;

import android.content.Context;
import android.util.Log;
import com.tencent.component.ComponentContext;
import com.tencent.component.ComponentInitializer;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.plugin.api.QMiApi;

public class QmiSdkApi
{
  public static final String a = "unity";
  public static final String b = "cocos2d";
  private static final String c = "QmiSdkApi";
  private static volatile String d;
  private static volatile String e;
  private static boolean f = false;
  private static boolean g = false;

  public static int beginDraw()
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke beginDraw failed (context is null).");
      return 0;
    }
    if (!f)
    {
      Log.i("QmiSdkApi", "beginDraw by game with result.");
      f = true;
    }
    return QMiApi.getInstance(localContext).a();
  }

  public static int endDraw()
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke endDraw failed (context is null).");
      return 0;
    }
    if (!g)
    {
      Log.i("QmiSdkApi", "endDraw by game with result.");
      g = true;
    }
    return QMiApi.getInstance(localContext).b();
  }

  public static String getExtraSettingDescription()
  {
    return e;
  }

  public static String getGameEngineType()
  {
    return d;
  }

  public static int getVersionCode()
  {
    return 150;
  }

  public static String getVersionName()
  {
    return "2.2.1.25";
  }

  public static void hideQMi(Context paramContext)
  {
    Log.i("QmiSdkApi", "hide qmi (context:" + paramContext + ")");
    if (paramContext != null)
    {
      QMiService.a(paramContext, 101);
      QMiApi.getInstance(paramContext).d();
    }
  }

  public static void onGameEnterBackground()
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke onGameEnterBackground failed (context is null).");
      return;
    }
    Log.i("QmiSdkApi", "onGameEnterBackground by game.");
    QMiApi.getInstance(localContext).g();
  }

  public static void onGameEnterForeground()
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke onGameEnterForeground failed (context is null).");
      return;
    }
    Log.i("QmiSdkApi", "onGameEnterForeground by game.");
    QMiApi.getInstance(localContext).f();
  }

  public static void onStartRecordVideo()
  {
    onStartRecordVideo(0L);
  }

  public static void onStartRecordVideo(long paramLong)
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke onStartRecordVideo failed (context is null).");
      return;
    }
    Log.i("QmiSdkApi", "onStartRecordVideo by game.");
    if (paramLong <= 0L)
    {
      QMiApi.getInstance(localContext).c();
      return;
    }
    QMiApi.getInstance(localContext).a(paramLong);
  }

  public static void onStopRecordVideo()
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke onStopRecordVideo failed (context is null).");
      return;
    }
    Log.i("QmiSdkApi", "onStopRecordVideo by game.");
    QMiApi.getInstance(localContext).d();
  }

  public static void onUpdateVideoFrame()
  {
    Context localContext = ComponentContext.a();
    if (localContext == null)
      return;
    QMiApi.getInstance(localContext).e();
  }

  public static void scollToSide(Context paramContext)
  {
    Log.i("QmiSdkApi", "scoll to side (context:" + paramContext + ")");
    if (paramContext != null)
      QMiService.a(paramContext, 102);
  }

  public static void setExtraSettingDescription(String paramString)
  {
    e = paramString;
  }

  public static void setGameEngineType(String paramString)
  {
    Log.i("QmiSdkApi", "setGameEngineType:" + paramString);
    d = paramString;
    Context localContext = ComponentContext.a();
    if (localContext == null)
    {
      Log.i("QmiSdkApi", "invoke onQueryGameEngineTypeResult failed (context is null).");
      return;
    }
    QMiApi.getInstance(localContext).a(paramString);
  }

  public static void setPreDownloadApkPath(Context paramContext, String paramString)
  {
    UrlDownLoadGameJoy.b(paramContext, paramString);
  }

  public static void showQMi(Context paramContext, String paramString)
  {
    Log.i("QmiSdkApi", "show qmi (version:" + getVersionCode() + " |gameEngineType:" + paramString + " |context:" + paramContext + ")");
    if (paramContext != null)
    {
      Log.i("QmiSdkApi", "context not null ,try to start qmi service.");
      ComponentInitializer.a(paramContext);
      d = paramString;
      QMiService.a(paramContext, 100);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QmiSdkApi
 * JD-Core Version:    0.6.0
 */