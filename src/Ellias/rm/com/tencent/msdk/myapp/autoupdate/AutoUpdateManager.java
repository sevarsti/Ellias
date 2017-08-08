package com.tencent.msdk.myapp.autoupdate;

import android.app.Activity;
import android.content.Context;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.tools.Logger;
import com.tencent.tmassistantsdk.selfUpdateSDK.ITMSelfUpdateSDKListener;
import com.tencent.tmassistantsdk.selfUpdateSDK.TMSelfUpdateSDKUpdateInfo;
import com.tencent.tmassistantsdk.selfUpdateSDK.msdk.TMSelfUpdateMSDK;

public class AutoUpdateManager
{
  private static final String CLOSED_WARNNING = "SAVE_UPDATE is closed, open it in msdkconfig.ini";
  private static Context ctx;
  private static AutoUpdateListener mListener = new AutoUpdateListener();

  public static void checkNeedUpdate()
  {
    if (isOpen())
    {
      TMSelfUpdateMSDK.getInstance().checkNeedUpdate();
      return;
    }
    Logger.w("SAVE_UPDATE is closed, open it in msdkconfig.ini");
  }

  public static int checkYYBInstalled()
  {
    return TMSelfUpdateMSDK.getInstance().checkYYBInstalled();
  }

  public static void init()
  {
    ctx = WeGame.getInstance().getActivity();
    TMSelfUpdateMSDK.getInstance().initTMSelfUpdateSDK(ctx.getApplicationContext(), mListener);
    TMSelfUpdateMSDK.about();
  }

  public static boolean isOpen()
  {
    return "true".equals(ConfigManager.readValueByKey(WeGame.getInstance().getActivity(), "SAVE_UPDATE"));
  }

  public static void onDestory(Activity paramActivity)
  {
    if (isOpen())
    {
      TMSelfUpdateMSDK.getInstance().destroySelfUpdateSDK(mListener);
      return;
    }
    Logger.w("SAVE_UPDATE is closed, open it in msdkconfig.ini");
  }

  public static void onResume(Activity paramActivity)
  {
    if (isOpen())
    {
      TMSelfUpdateMSDK.getInstance().onResume(paramActivity);
      return;
    }
    Logger.w("SAVE_UPDATE is closed, open it in msdkconfig.ini");
  }

  public static void startCommonUpdate()
  {
    if (isOpen())
    {
      TMSelfUpdateMSDK.getInstance().startCommonUpdate();
      return;
    }
    Logger.w("SAVE_UPDATE is closed, open it in msdkconfig.ini");
  }

  public static void startSaveUpdate()
  {
    if (isOpen())
    {
      TMSelfUpdateMSDK.getInstance().startSaveUpdate(WeGame.getInstance().getActivity());
      return;
    }
    Logger.w("SAVE_UPDATE is closed, open it in msdkconfig.ini");
  }

  static class AutoUpdateListener
    implements ITMSelfUpdateSDKListener
  {
    public void OnCheckNeedUpdateInfo(TMSelfUpdateSDKUpdateInfo paramTMSelfUpdateSDKUpdateInfo)
    {
      Logger.d("called");
      ((WGSaveUpdateObserverProxy)WGSaveUpdateObserverProxy.gDefault.get()).OnCheckNeedUpdateInfo(paramTMSelfUpdateSDKUpdateInfo.getNewApkSize(), paramTMSelfUpdateSDKUpdateInfo.getNewFeature(), paramTMSelfUpdateSDKUpdateInfo.getPatchSize(), paramTMSelfUpdateSDKUpdateInfo.getStatus(), paramTMSelfUpdateSDKUpdateInfo.getUpdateDownloadUrl(), paramTMSelfUpdateSDKUpdateInfo.getUpdateMethod());
    }

    public void OnDownloadAppProgressChanged(long paramLong1, long paramLong2)
    {
      Logger.d("called");
      ((WGSaveUpdateObserverProxy)WGSaveUpdateObserverProxy.gDefault.get()).OnDownloadAppProgressChanged(paramLong1, paramLong2);
    }

    public void OnDownloadAppStateChanged(int paramInt1, int paramInt2, String paramString)
    {
      Logger.d("called");
      ((WGSaveUpdateObserverProxy)WGSaveUpdateObserverProxy.gDefault.get()).OnDownloadAppStateChanged(paramInt1, paramInt2, paramString);
    }

    public void OnDownloadYYBProgressChanged(String paramString, long paramLong1, long paramLong2)
    {
      Logger.d("called");
      ((WGSaveUpdateObserverProxy)WGSaveUpdateObserverProxy.gDefault.get()).OnDownloadYYBProgressChanged(paramString, paramLong1, paramLong2);
    }

    public void OnDownloadYYBStateChanged(String paramString1, int paramInt1, int paramInt2, String paramString2)
    {
      Logger.d("called");
      ((WGSaveUpdateObserverProxy)WGSaveUpdateObserverProxy.gDefault.get()).OnDownloadYYBStateChanged(paramString1, paramInt1, paramInt2, paramString2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.myapp.autoupdate.AutoUpdateManager
 * JD-Core Version:    0.6.0
 */