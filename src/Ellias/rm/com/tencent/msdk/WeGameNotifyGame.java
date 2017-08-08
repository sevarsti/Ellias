package com.tencent.msdk;

import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.ShareRet;
import com.tencent.msdk.api.WGPlatformObserver;
import com.tencent.msdk.api.WGPlatformObserverForSO;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.push.PushManager;
import com.tencent.msdk.qmi.QmiSdkApiProxy;
import com.tencent.msdk.remote.api.RelationRet;
import com.tencent.msdk.tools.Logger;

public class WeGameNotifyGame
{
  private static volatile WeGameNotifyGame instance = null;
  public WGPlatformObserver mObserver = null;

  private void callbackGameBackendShare(ShareRet paramShareRet)
  {
    Logger.d(paramShareRet);
    if (this.mObserver == null)
    {
      Logger.d("WeGame callbackGameBackendShare C++ callbackGameBackendShare");
      WGPlatformObserverForSO.OnShareNotify(paramShareRet);
      return;
    }
    Logger.d("WeGame callbackGameBackendShare Java callbackGameBackendShare");
    this.mObserver.OnShareNotify(paramShareRet);
  }

  private void callbackGameRelation(RelationRet paramRelationRet)
  {
    if (this.mObserver == null)
    {
      Logger.d("WeGame callbackGameRelation C++ callbackGameRelation");
      WGPlatformObserverForSO.OnRelationNotify(paramRelationRet);
      return;
    }
    Logger.d("WeGame callbackGameRelation Java callbackGameRelation");
    this.mObserver.OnRelationNotify(paramRelationRet);
  }

  public static WeGameNotifyGame getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new WeGameNotifyGame();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void NotifyGameLogin(LoginRet paramLoginRet)
  {
    Logger.d("NotifyGameLogin");
    Logger.d("PUSH: " + ConfigManager.readValueByKey(WeGame.getInstance().getActivity(), "PUSH"));
    if ("true".equals(ConfigManager.readValueByKey(WeGame.getInstance().getActivity(), "PUSH")))
      if (paramLoginRet.platform == WeGame.QQPLATID)
      {
        ((PushManager)PushManager.gDefault.get()).registerAppUserOnce(WeGame.getInstance().getActivity(), WeGame.getInstance().qq_appid, paramLoginRet.open_id);
        if ((paramLoginRet.flag == 0) || (paramLoginRet.flag == 2005))
          MsdkThreadManager.getInstance().sendReportLogin(paramLoginRet.open_id, paramLoginRet.platform);
        if (this.mObserver != null)
          break label194;
        Logger.d("WeGame OnLoginNotify C++ OnLoginNotify");
        WGPlatformObserverForSO.OnLoginNotify(paramLoginRet);
      }
    while (true)
    {
      QmiSdkApiProxy.notifyQmiLogin();
      return;
      if (paramLoginRet.platform != WeGame.WXPLATID)
        break;
      ((PushManager)PushManager.gDefault.get()).registerAppUserOnce(WeGame.getInstance().getActivity(), WeGame.getInstance().wx_appid, paramLoginRet.open_id);
      break;
      Logger.d("PUSH Closed!");
      break;
      label194: Logger.d("WeGame OnLoginNotify Java OnLoginNotify");
      this.mObserver.OnLoginNotify(paramLoginRet);
    }
  }

  public void NotifyGameShare(ShareRet paramShareRet)
  {
    Logger.d("NotifyGameShare");
    if (this.mObserver == null)
    {
      WGPlatformObserverForSO.OnShareNotify(paramShareRet);
      return;
    }
    this.mObserver.OnShareNotify(paramShareRet);
  }

  public void NotifyGameWakeUp(WakeupRet paramWakeupRet)
  {
    Logger.d("NotifyGameWakeUp");
    if (this.mObserver == null)
    {
      WGPlatformObserverForSO.OnWakeupNotify(paramWakeupRet);
      return;
    }
    this.mObserver.OnWakeupNotify(paramWakeupRet);
  }

  public void OnBackendRelationCallback(RelationRet paramRelationRet)
  {
    callbackGameRelation(paramRelationRet);
  }

  public void OnBackendShareCallback(ShareRet paramShareRet)
  {
    callbackGameBackendShare(paramShareRet);
  }

  public void OnClearLocationCallback(RelationRet paramRelationRet)
  {
    if (this.mObserver == null)
    {
      Logger.d("OnClearLocationCallbacknotify to cpp");
      WGPlatformObserverForSO.OnLocationNotify(paramRelationRet);
      return;
    }
    Logger.d("OnClearLocationCallbacknotify to java");
    this.mObserver.OnLocationNotify(paramRelationRet);
  }

  public String OnCrashExtMessageNotify(int paramInt, String paramString)
  {
    Logger.d("OnCrashExtMessageNotify");
    if (this.mObserver == null)
    {
      Logger.d("OnCrashExtMessageNotify to cpp");
      String str = WGPlatformObserverForSO.OnCrashExtMessageNotify();
      Logger.d("OnCrashExtMessageNotify to cpp, value:" + str);
      return str;
    }
    Logger.d("OnCrashExtMessageNotify to java");
    return this.mObserver.OnCrashExtMessageNotify();
  }

  public void OnFeedbackCallback(int paramInt, String paramString)
  {
    Logger.d("OnFeedbackCallback");
    if (this.mObserver == null)
    {
      Logger.d("OnFeedbackCallback to cpp");
      WGPlatformObserverForSO.OnFeedbackNotify(paramInt, paramString);
      return;
    }
    Logger.d("OnFeedbackCallback to java");
    this.mObserver.OnFeedbackNotify(paramInt, paramString);
  }

  public void OnFeedbackNotify(int paramInt, String paramString)
  {
    Logger.d("OnFeedbackNotify");
    if (this.mObserver == null)
    {
      Logger.d("OnFeedbackNotify to cpp");
      WGPlatformObserverForSO.OnFeedbackNotify(paramInt, paramString);
      return;
    }
    Logger.d("OnFeedbackNotify to java");
    this.mObserver.OnFeedbackNotify(paramInt, paramString);
  }

  public void OnGetNearbyPlayerCallback(RelationRet paramRelationRet)
  {
    if (this.mObserver == null)
    {
      Logger.d("OnGetNearbyPlayerCallbacknotify to cpp");
      WGPlatformObserverForSO.OnLocationNotify(paramRelationRet);
      return;
    }
    Logger.d("OnGetNearbyPlayerCallbacknotify to java");
    this.mObserver.OnLocationNotify(paramRelationRet);
  }

  public void OnPlatformLoginNotify(LoginRet paramLoginRet)
  {
    paramLoginRet.toLog();
    Logger.d("OnLoginNotify");
    WeGame.getInstance().setFlag(paramLoginRet.flag);
    WeGame.getInstance().setPlatId(paramLoginRet.platform);
    NoticeManager.getInstance().getNoticeInfo();
    NotifyGameLogin(paramLoginRet);
  }

  public void OnPlatformWakeupNotify(WakeupRet paramWakeupRet)
  {
    Logger.d("OnPlatformWakeupNotify");
    NotifyGameWakeUp(paramWakeupRet);
  }

  public void OnShareNotify(ShareRet paramShareRet)
  {
    Logger.d("OnShareNotify");
    NotifyGameShare(paramShareRet);
  }

  public void setObserver(WGPlatformObserver paramWGPlatformObserver)
  {
    this.mObserver = paramWGPlatformObserver;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.WeGameNotifyGame
 * JD-Core Version:    0.6.0
 */