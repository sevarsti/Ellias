package com.tencent.qqgamemi.plugin.api;

import android.app.Service;
import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.qqgamemi.Cocos2dxGameJoyAssistant;
import com.tencent.qqgamemi.QMiApplication;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.QmiSdkApi;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.data.GameItem;
import com.tencent.qqgamemi.login.LoginCallBack;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;
import com.unity3d.player.UnityPlayer;
import java.util.HashSet;
import java.util.Iterator;

public class QMiApi
{

  @PluginApi(a=150)
  public static final int NOTIFY_GAME_ENGINE_QUERY_TYPE = 3;

  @PluginApi(a=15)
  public static final int NOTIFY_GAME_ENGINE_START_RECORD = 1;

  @PluginApi(a=15)
  public static final int NOTIFY_GAME_ENGINE_STOP_RECORD = 2;
  private static final String a = "QMiApi";
  private static volatile QMiApi c;
  private Context b;
  private HashSet d = new HashSet();
  private HashSet e = new HashSet();

  private QMiApi(Context paramContext)
  {
    this.b = paramContext;
  }

  private void a(int paramInt, Object paramObject)
  {
    if (isEmbedSDK())
    {
      TLog.b("QMiApi", "game engine :" + getGameEngineType());
      if (isUnity3d())
      {
        TLog.b("QMiApi", "notifygameengine msg = " + paramInt);
        switch (paramInt)
        {
        default:
        case 1:
        case 2:
        case 3:
        }
      }
      do
      {
        do
        {
          return;
          UnityPlayer.UnitySendMessage("GameJoyPrefab", "StartRecord", "");
          return;
          UnityPlayer.UnitySendMessage("GameJoyPrefab", "StopRecord", "");
          return;
          UnityPlayer.UnitySendMessage("GameJoyPrefab", "GetGameEngineType", "");
          return;
        }
        while (!isCocos2dx());
        switch (paramInt)
        {
        default:
          return;
        case 1:
        case 2:
        case 3:
        }
      }
      while (paramObject == null);
      try
      {
        Object[] arrayOfObject = (Object[])(Object[])paramObject;
        Cocos2dxGameJoyAssistant.StartRecord(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Float)arrayOfObject[2]).floatValue());
        return;
      }
      catch (Exception localException)
      {
        TLog.c("QMiApi", localException.getMessage(), localException);
        return;
      }
      Cocos2dxGameJoyAssistant.StopRecord();
      return;
      Cocos2dxGameJoyAssistant.queryGameEngineType();
      return;
    }
    TLog.b("QMiApi", "non embed sdk.");
  }

  @PluginApi(a=6)
  public static byte[] getEncData()
  {
    return QMiLoginManager.a().a(0, 0);
  }

  @PluginApi(a=9)
  public static String getGameApkPath()
  {
    return UrlDownLoadGameJoy.a(QMiApplication.a());
  }

  @PluginApi(a=9)
  public static String getGameEngineType()
  {
    return QmiSdkApi.getGameEngineType();
  }

  @PluginApi(a=4)
  public static QMiApi getInstance(Context paramContext)
  {
    if (c == null)
      monitorenter;
    try
    {
      if (c == null)
        c = new QMiApi(paramContext);
      return c;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public static byte[] getSign()
  {
    return QMiLoginManager.a().k();
  }

  @PluginApi(a=8)
  public static boolean isLogined()
  {
    return QMiLoginManager.a().m();
  }

  @PluginApi(a=11)
  public static void startDownLoadGameJoy(String paramString)
  {
    UrlDownLoadGameJoy.a(QMiApplication.a(), paramString);
  }

  @PluginApi(a=16)
  public static void syncLoginCookies(Context paramContext, String paramString)
  {
    QMiCommon.d(paramContext, paramString);
  }

  public int a()
  {
    Iterator localIterator = this.d.iterator();
    int i = 0;
    QMiApi.GameEngineCallback localGameEngineCallback;
    if (localIterator.hasNext())
    {
      localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        break label49;
    }
    label49: for (int j = localGameEngineCallback.onBeginDraw(); ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  public void a(long paramLong)
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.GameEngineCallback localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        continue;
      localGameEngineCallback.onStartRecordVideo(paramLong);
    }
  }

  public void a(String paramString)
  {
    Iterator localIterator = this.e.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.QueryGameEngineTypeCallback localQueryGameEngineTypeCallback = (QMiApi.QueryGameEngineTypeCallback)localIterator.next();
      if (localQueryGameEngineTypeCallback == null)
        continue;
      localQueryGameEngineTypeCallback.onQueryGameEngineType(paramString);
    }
  }

  public int b()
  {
    Iterator localIterator = this.d.iterator();
    int i = 0;
    QMiApi.GameEngineCallback localGameEngineCallback;
    if (localIterator.hasNext())
    {
      localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        break label49;
    }
    label49: for (int j = localGameEngineCallback.onEndDraw(); ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  public void c()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.GameEngineCallback localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        continue;
      localGameEngineCallback.onStartRecordVideo();
    }
  }

  public void d()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.GameEngineCallback localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        continue;
      localGameEngineCallback.onStopRecordVideo();
    }
  }

  @PluginApi(a=4)
  public void downloadHall(String paramString)
  {
    UrlDownLoadGameJoy.a(QMiApplication.a(), paramString);
  }

  public void e()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.GameEngineCallback localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        continue;
      localGameEngineCallback.onUpdateVideoFrame();
    }
  }

  public void f()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.GameEngineCallback localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        continue;
      localGameEngineCallback.onGameEnterForeground();
    }
  }

  @PluginApi(a=4)
  public void finish(String paramString)
  {
    QMiPluginManager.a().e(paramString);
  }

  public void g()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      QMiApi.GameEngineCallback localGameEngineCallback = (QMiApi.GameEngineCallback)localIterator.next();
      if (localGameEngineCallback == null)
        continue;
      localGameEngineCallback.onGameEnterBackground();
    }
  }

  @PluginApi(a=4)
  public String getCurrentGameName()
  {
    GameItem localGameItem = DataModel.a(this.b).a(QMiCommon.a(this.b));
    if (localGameItem != null)
      return localGameItem.name;
    return null;
  }

  @PluginApi(a=4)
  public String getCurrentPackageName()
  {
    GameItem localGameItem = DataModel.a(this.b).a(QMiCommon.a(this.b));
    if (localGameItem != null)
      return localGameItem.packageName;
    return null;
  }

  @PluginApi(a=8)
  public int getEmbedType()
  {
    return QMiConfig.c();
  }

  @PluginApi(a=4)
  public String getForumUrl()
  {
    GameItem localGameItem = DataModel.a(this.b).a(QMiCommon.a(this.b));
    if (localGameItem != null)
      return localGameItem.forumUrl;
    return "";
  }

  @PluginApi(a=16)
  public String getOpenId()
  {
    return QMiLoginManager.a().h();
  }

  @PluginApi(a=16)
  public String getOpenToken()
  {
    return QMiLoginManager.a().f();
  }

  @PluginApi(a=4)
  public String getRaidersUrl()
  {
    GameItem localGameItem = DataModel.a(this.b).a(QMiCommon.a(this.b));
    if (localGameItem != null)
      return localGameItem.strategyUrl;
    return "";
  }

  @PluginApi(a=16)
  public String getSDKAppID()
  {
    return QMiLoginManager.a().g();
  }

  @PluginApi(a=6)
  public Service getService()
  {
    return QMiService.a();
  }

  @PluginApi(a=4)
  public String getSid()
  {
    return QMiLoginManager.a().j();
  }

  @PluginApi(a=16)
  public String getSybAccount()
  {
    return QMiLoginManager.a().c();
  }

  @PluginApi(a=16)
  public byte[] getSybStData()
  {
    return QMiLoginManager.a().e();
  }

  @PluginApi(a=16)
  public int getSybStType()
  {
    return QMiLoginManager.a().d();
  }

  @PluginApi(a=4)
  public String getUUID()
  {
    return QMiJceCommonData.i();
  }

  @PluginApi(a=4)
  public long getUin()
  {
    return QMiLoginManager.a().i();
  }

  @PluginApi(a=4)
  public void hideQMi()
  {
    QMiService.a(this.b, 101);
  }

  @PluginApi(a=6)
  public void hideScrollQMi()
  {
    QMiService.a(this.b, 102);
  }

  @PluginApi(a=9)
  public boolean isCocos2dx()
  {
    String str = QmiSdkApi.getGameEngineType();
    return (!TextUtils.isEmpty(str)) && (str.toLowerCase().startsWith("cocos2d"));
  }

  @PluginApi(a=4)
  public boolean isEmbedSDK()
  {
    return QMiConfig.b();
  }

  @PluginApi(a=9)
  public boolean isSpecialGame()
  {
    return QMiCommon.a(this.b).equalsIgnoreCase("com.tencent.ttx5");
  }

  @PluginApi(a=9)
  public boolean isUnity3d()
  {
    String str = QmiSdkApi.getGameEngineType();
    return (!TextUtils.isEmpty(str)) && (str.toLowerCase().startsWith("unity"));
  }

  @PluginApi(a=4)
  public void login(LoginCallBack paramLoginCallBack)
  {
    TLog.c("QMiApi", "login");
    if (!QMiLoginManager.a().m())
      QMiLoginManager.a(this.b, paramLoginCallBack);
  }

  @PluginApi(a=9)
  public void notifyGameEngine(int paramInt)
  {
    notifyGameEngine(paramInt, null);
  }

  @PluginApi(a=15)
  public void notifyGameEngine(int paramInt, Object paramObject)
  {
    try
    {
      a(paramInt, paramObject);
      return;
    }
    catch (Exception localException)
    {
      TLog.c("QMiApi", localException.getMessage(), localException);
    }
  }

  @PluginApi(a=9)
  public void registerGameEngineCallback(QMiApi.GameEngineCallback paramGameEngineCallback)
  {
    if (paramGameEngineCallback != null)
      this.d.add(paramGameEngineCallback);
  }

  @PluginApi(a=150)
  public void registerQueryGameEngineTypeCallback(QMiApi.QueryGameEngineTypeCallback paramQueryGameEngineTypeCallback)
  {
    if (paramQueryGameEngineTypeCallback != null)
      this.e.add(paramQueryGameEngineTypeCallback);
  }

  @PluginApi(a=4)
  public void showQMi()
  {
    QMiService.a(this.b, 100);
  }

  @PluginApi(a=9)
  public void unregisterGameEngineCallback(QMiApi.GameEngineCallback paramGameEngineCallback)
  {
    if (paramGameEngineCallback != null)
      this.d.remove(paramGameEngineCallback);
  }

  @PluginApi(a=150)
  public void unregisterQueryGameEngineTypeCallback(QMiApi.QueryGameEngineTypeCallback paramQueryGameEngineTypeCallback)
  {
    if (paramQueryGameEngineTypeCallback != null)
      this.e.remove(paramQueryGameEngineTypeCallback);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.api.QMiApi
 * JD-Core Version:    0.6.0
 */