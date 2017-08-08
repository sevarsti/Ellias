package com.tencent.msdk.api;

import android.app.Activity;
import android.content.Intent;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.consts.EPlatform;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.lbs.Lbs;
import com.tencent.msdk.myapp.autoupdate.AutoUpdateManager;
import com.tencent.msdk.myapp.autoupdate.WGSaveUpdateObserver;
import com.tencent.msdk.myapp.autoupdate.WGSaveUpdateObserverProxy;
import com.tencent.msdk.myapp.whitelist.WhiteListMng;
import com.tencent.msdk.notice.NoticeInfo;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.notice.eMSG_NOTICETYPE;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.qq.ApiName;
import com.tencent.msdk.stat.MsdkStat;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.SharedPreferencesTool;
import com.tencent.msdk.tools.T;
import com.tencent.msdk.tools.WeGameTools;
import com.tencent.msdk.weixin.BtnBase;
import com.tencent.msdk.weixin.MsgBase;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public final class WGPlatform
{
  private static final String TAG = WGPlatform.class.getName();

  public static void DestroyActivity()
  {
  }

  public static void Initialized(Activity paramActivity, MsdkBaseInfo paramMsdkBaseInfo)
  {
    WeGame.getInstance().Initialized(paramActivity, paramMsdkBaseInfo);
  }

  public static Boolean IsDifferentActivity(Activity paramActivity)
  {
    return Boolean.valueOf(WeGame.getInstance().IsDifferentActivity(paramActivity));
  }

  public static boolean WGCheckApiSupport(ApiName paramApiName)
  {
    return WeGame.getInstance().checkApiSupport(paramApiName);
  }

  public static void WGCheckNeedUpdate()
  {
    AutoUpdateManager.checkNeedUpdate();
  }

  public static int WGCheckYYBInstalled()
  {
    return AutoUpdateManager.checkYYBInstalled();
  }

  public static boolean WGCleanLocation()
  {
    Logger.d("called");
    return MsdkThreadManager.getInstance().clearLocation();
  }

  public static void WGEnableCrashReport(boolean paramBoolean1, boolean paramBoolean2)
  {
    WeGame.getInstance().enableCrashReport(paramBoolean1, paramBoolean2);
  }

  public static void WGFeedback(String paramString)
  {
    MsdkThreadManager.getInstance().sendFeedbackWithAppid(paramString);
  }

  public static boolean WGFeedback(String paramString1, String paramString2)
  {
    return WeGame.getInstance().feedback(paramString1, paramString2);
  }

  public static String WGGetChannelId()
  {
    return "" + WGPfManager.getInstance().getChannelId();
  }

  public static int WGGetLoginRecord(LoginRet paramLoginRet)
  {
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    paramLoginRet.platform = localLoginRet.platform;
    paramLoginRet.open_id = localLoginRet.open_id;
    paramLoginRet.flag = WeGame.getInstance().validateAccountToken(localLoginRet);
    if (WeGame.QQPLATID == localLoginRet.platform)
      WeGame.getInstance().setOpenSdkLoginInfo(localLoginRet.open_id, localLoginRet.getTokenByType(1), localLoginRet.getTokenExpireByType(1));
    switch (paramLoginRet.flag)
    {
    default:
      paramLoginRet.desc = "no login record or other error, ask user to login";
    case 0:
    case 1006:
    case 1007:
    case 2007:
    case 2008:
    }
    while (true)
    {
      Logger.d("ret.platform = " + paramLoginRet.platform);
      Logger.d("\n ret.flag = " + paramLoginRet.flag);
      Logger.d("\n ret.open_id = " + paramLoginRet.open_id);
      Logger.d("\n ret.desc = " + paramLoginRet.desc);
      Logger.d("\n ret.pf = " + paramLoginRet.pf);
      Logger.d("\n ret.pf_key = " + paramLoginRet.pf_key);
      MsdkThreadManager.getInstance().sendReportLogin(paramLoginRet.open_id, paramLoginRet.platform);
      return paramLoginRet.platform;
      paramLoginRet.desc = "success";
      paramLoginRet.open_id = localLoginRet.open_id;
      paramLoginRet.pf = localLoginRet.pf;
      paramLoginRet.pf_key = localLoginRet.pf_key;
      paramLoginRet.platform = localLoginRet.platform;
      paramLoginRet.user_id = localLoginRet.user_id;
      Iterator localIterator2 = localLoginRet.token.iterator();
      while (localIterator2.hasNext())
      {
        TokenRet localTokenRet2 = (TokenRet)localIterator2.next();
        paramLoginRet.token.add(localTokenRet2);
      }
      MsdkThreadManager.getInstance().sendReportLogin(paramLoginRet.open_id, paramLoginRet.platform);
      continue;
      paramLoginRet.desc = "qq access token expired, ask user login again!";
      continue;
      paramLoginRet.desc = "qq pay token expired, ask user login again!";
      continue;
      paramLoginRet.desc = "wechat access token expired, try to refresh it using refresh token";
      paramLoginRet.open_id = localLoginRet.open_id;
      paramLoginRet.pf = localLoginRet.pf;
      paramLoginRet.pf_key = localLoginRet.pf_key;
      paramLoginRet.platform = localLoginRet.platform;
      paramLoginRet.user_id = localLoginRet.user_id;
      Iterator localIterator1 = localLoginRet.token.iterator();
      while (localIterator1.hasNext())
      {
        TokenRet localTokenRet1 = (TokenRet)localIterator1.next();
        paramLoginRet.token.add(localTokenRet1);
      }
      paramLoginRet.desc = "wechat refresh token is expired, ask user login again!";
    }
  }

  public static void WGGetNearbyPersonInfo()
  {
    Logger.d("called");
    new Lbs(WeGame.getInstance().getActivity()).getNearbyPlayer();
  }

  public static Vector<NoticeInfo> WGGetNoticeData(eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString)
  {
    return NoticeManager.getInstance().getNoticeBySceneAndType(parameMSG_NOTICETYPE, paramString);
  }

  public static int WGGetPaytokenValidTime()
  {
    return SharedPreferencesTool.getInt(WeGame.getInstance().getActivity(), "paytoken_expire_time", 518400);
  }

  public static String WGGetPf(String paramString)
  {
    return WGPfManager.getInstance().getPf(paramString);
  }

  public static String WGGetPfKey()
  {
    return WGPfManager.getInstance().getPfKey();
  }

  public static String WGGetRegisterChannelId()
  {
    return "" + WGPfManager.getInstance().getRegChannelId();
  }

  public static String WGGetVersion()
  {
    return WeGame.getInstance().WGGetVersion();
  }

  public static void WGHideQMi()
  {
    MsdkThreadManager.getInstance().hideQMi();
  }

  public static void WGHideScrollNotice()
  {
    MsdkThreadManager.getInstance().closeScrollNotice();
  }

  public static boolean WGIsPlatformInstalled(EPlatform paramEPlatform)
  {
    return WeGameTools.isPlatformInstalled(WeGame.getInstance().getActivity(), paramEPlatform.val());
  }

  public static boolean WGIsPlatformSupportApi(EPlatform paramEPlatform)
  {
    return WeGameTools.isPlatformSupportApi(WeGame.getInstance().getActivity(), paramEPlatform.val());
  }

  public static void WGLogPlatformSDKVersion()
  {
    WeGame.getInstance().logPlatformSDKVersion();
  }

  public static void WGLogin(EPlatform paramEPlatform)
  {
    WeGame.getInstance().sendLogin(paramEPlatform.val());
  }

  public static void WGLoginWithLocalInfo()
  {
    Logger.d("WGLoginWithLocalInfo");
    WeGame.getInstance().loginWithLocalInfo();
  }

  public static boolean WGLogout()
  {
    WGHideScrollNotice();
    return WeGame.getInstance().logout();
  }

  public static boolean WGOpenAmsCenter(String paramString)
  {
    if ((paramString == null) || (paramString.length() > 256))
    {
      Logger.d("params to long, maxLength: 256");
      return false;
    }
    for (Object localObject = ConfigManager.readValueByKey(WeGame.getInstance().getActivity(), "AMS_CENTER_URL"); ; localObject = (String)localObject + paramString)
      try
      {
        if (T.ckIsEmpty(new URL((String)localObject).getQuery()))
        {
          String str2 = (String)localObject + "?";
          localObject = str2;
        }
        while (true)
        {
          if (!T.ckIsEmpty(paramString))
          {
            char c = paramString.charAt(0);
            Logger.d("" + c);
            if ((paramString.charAt(0) != '?') && (paramString.charAt(0) != '&'))
              break;
            localObject = (String)localObject + paramString.substring(1);
          }
          Logger.d((String)localObject);
          WGOpenUrl((String)localObject);
          return true;
          if (((String)localObject).endsWith("&"))
            continue;
          String str1 = (String)localObject + "&";
          localObject = str1;
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        Logger.w("this api need a valid url start with http:// or https:// ");
        localMalformedURLException.printStackTrace();
        return false;
      }
  }

  public static void WGOpenUrl(String paramString)
  {
    MsdkThreadManager.getInstance().openUrl(paramString);
  }

  public static boolean WGQueryQQGameFriendsInfo()
  {
    MsdkThreadManager.getInstance().queryQQGameFriendsInfo();
    return true;
  }

  public static boolean WGQueryQQMyInfo()
  {
    MsdkThreadManager.getInstance().queryQQUserInfo();
    return true;
  }

  public static boolean WGQueryWXGameFriendsInfo()
  {
    MsdkThreadManager.getInstance().queryWXGameFriendsInfo();
    return true;
  }

  public static boolean WGQueryWXMyInfo()
  {
    MsdkThreadManager.getInstance().queryWXUserInfo();
    return true;
  }

  public static void WGRefreshWXToken()
  {
    if (PermissionManage.getInstance().isHavePermission("WGRefreshWXToken"))
    {
      WeGame.getInstance().refreshWxToken();
      return;
    }
    Logger.d(TAG, "WGRefreshWXToken function not permission");
  }

  public static void WGReportEvent(String paramString1, String paramString2, boolean paramBoolean)
  {
    WeGame.getInstance().WGReportEvent(paramString1, paramString2, paramBoolean);
  }

  public static void WGReportEvent(String paramString, HashMap<String, String> paramHashMap, boolean paramBoolean)
  {
    WeGame.getInstance().WGReportEvent(paramString, paramHashMap, paramBoolean);
  }

  public static boolean WGSendMessageToWechatGameCenter(String paramString1, String paramString2, String paramString3, MsgBase paramMsgBase, BtnBase paramBtnBase, String paramString4)
  {
    Logger.d("friendOpenId:" + paramString1);
    Logger.d("title:" + paramString2);
    Logger.d("content:" + paramString3);
    Logger.d("pInfo:" + paramMsgBase);
    Logger.d("pButton:" + paramBtnBase);
    Logger.d("msdkExtInfo:" + paramString4);
    if ((T.ckIsEmpty(paramString1)) || (T.ckIsEmpty(paramString2)) || (T.ckIsEmpty(paramString3)) || (paramMsgBase == null) || (paramBtnBase == null))
    {
      Logger.d("friendOpenId, title, content, pInfo, pButton can not be empty");
      return false;
    }
    if (paramString4 == null)
      paramString4 = "";
    MsdkThreadManager.getInstance().sendMessageToWechatGameCenter(paramString1, paramString2, paramString3, paramMsgBase, paramBtnBase, paramString4);
    return true;
  }

  public static void WGSendToQQ(eQQScene parameQQScene, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    if (parameQQScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    if (PermissionManage.getInstance().isHavePermission("WGSendToQQ"))
    {
      WeGame.getInstance().WGSendToQQ(parameQQScene.value, paramString1, paramString2, paramString3, paramString4, paramInt);
      return;
    }
    Logger.d(TAG, "WGSendToQQ function not permission");
  }

  public static boolean WGSendToQQGameFriend(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    return WGSendToQQGameFriend(paramInt, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, "");
  }

  public static boolean WGSendToQQGameFriend(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    if (T.ckNonEmpty(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5 }))
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "friendOpenId, title, summary, targetUrl, imageUrl can not be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.w("friendOpenId, title, summary, targetUrl, imageUrl CAN NOT BE EMPTY");
      return false;
    }
    if (paramString8 == null)
      paramString8 = "";
    MsdkThreadManager.getInstance().sendToQQGameFriend(paramInt, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8);
    return true;
  }

  public static void WGSendToQQWithMusic(eQQScene parameQQScene, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (parameQQScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    if (PermissionManage.getInstance().isHavePermission("WGSendToQQ"))
    {
      WeGame.getInstance().WGSendToQQWithMusic(parameQQScene, paramString1, paramString2, paramString3, paramString4, paramString5);
      return;
    }
    Logger.d(TAG, "WGSendToQQWithMusic function not permission");
  }

  public static void WGSendToQQWithPhoto(eQQScene parameQQScene, String paramString)
  {
    if (parameQQScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    WeGame.getInstance().WGSendToQQWithPhoto(parameQQScene.value, paramString);
  }

  public static boolean WGSendToWXGameFriend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return WGSendToWXGameFriend(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, "");
  }

  public static boolean WGSendToWXGameFriend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (T.ckNonEmpty(new String[] { paramString1, paramString2, paramString3 }))
    {
      Logger.w("fopenid, title, description  CAN NOT BE EMPTY");
      return false;
    }
    if (paramString7 == null)
      paramString7 = "";
    MsdkThreadManager.getInstance().sendToWXGameFriend(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
    return true;
  }

  public static void WGSendToWeixin(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, int paramInt, String paramString4)
  {
    if (PermissionManage.getInstance().isHavePermission("WGSendToWeixin"))
    {
      WeGame.getInstance().WGSendToWeixin(paramString1, paramString2, paramString3, paramArrayOfByte, paramInt, paramString4);
      return;
    }
    Logger.d(TAG, "WGSendToWeixin function not permission");
  }

  public static void WGSendToWeixinWithMusic(eWechatScene parameWechatScene, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, byte[] paramArrayOfByte, int paramInt, String paramString6, String paramString7)
  {
    if (parameWechatScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    if (PermissionManage.getInstance().isHavePermission("WGSendToWeixin"))
    {
      WeGame.getInstance().WGSendToWeixinWithMusic(parameWechatScene, paramString1, paramString2, paramString3, paramString4, paramString5, paramArrayOfByte, paramInt, paramString6, paramString7);
      return;
    }
    Logger.d(TAG, "WGSendToWeixinWithMusic function not permission");
  }

  public static void WGSendToWeixinWithPhoto(eWechatScene parameWechatScene, String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    if (parameWechatScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    if (PermissionManage.getInstance().isHavePermission("WGSendToWeixinWithPhoto"))
    {
      WeGame.getInstance().WGSendToWeixinWithPhoto(parameWechatScene.value, paramString, paramArrayOfByte, paramInt);
      return;
    }
    Logger.d(TAG, "WGSendToWeixinWithPhoto function not permission");
  }

  public static void WGSendToWeixinWithPhoto(eWechatScene parameWechatScene, String paramString1, byte[] paramArrayOfByte, int paramInt, String paramString2, String paramString3)
  {
    if (parameWechatScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    if (PermissionManage.getInstance().isHavePermission("WGSendToWeixinWithPhoto"))
    {
      WeGame.getInstance().WGSendToWeixinWithPhoto(parameWechatScene.value, paramString1, paramArrayOfByte, paramInt, paramString2, paramString3);
      return;
    }
    Logger.d(TAG, "WGSendToWeixinWithPhoto function not permission");
  }

  public static void WGSetGameEngineType(String paramString)
  {
    MsdkThreadManager.getInstance().setGameEngineType(paramString);
  }

  public static void WGSetObserver(WGPlatformObserver paramWGPlatformObserver)
  {
    WeGame.getInstance().setObserver(paramWGPlatformObserver);
  }

  public static void WGSetPermission(int paramInt)
  {
    WeGame.getInstance().WGSetPermission(paramInt);
  }

  public static void WGSetSaveUpdateObserver(WGSaveUpdateObserver paramWGSaveUpdateObserver)
  {
    Logger.d("WGSetSaveUpdateObserver");
    ((WGSaveUpdateObserverProxy)WGSaveUpdateObserverProxy.gDefault.get()).setmJavaObserver(paramWGSaveUpdateObserver);
  }

  public static void WGShowNotice(eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString)
  {
    MsdkThreadManager.getInstance().showNoticeByScene(parameMSG_NOTICETYPE, paramString);
  }

  public static void WGShowQMi()
  {
    MsdkThreadManager.getInstance().showQMi();
  }

  public static void WGStartCommonUpdate()
  {
    Logger.d("WGStartCommonUpdate");
    AutoUpdateManager.startCommonUpdate();
  }

  public static void WGStartSaveUpdate()
  {
    Logger.d("WGStartSaveUpdate");
    AutoUpdateManager.startSaveUpdate();
  }

  public static boolean WGSwitchUser(boolean paramBoolean)
  {
    return WeGame.getInstance().switchUser(paramBoolean);
  }

  public static void WGTestSpeed(ArrayList<String> paramArrayList)
  {
    WeGame.getInstance().testSpeed(paramArrayList);
  }

  public static void handleCallback(Intent paramIntent)
  {
    WeGame.getInstance().handleCallback(paramIntent);
  }

  public static void onDestory(Activity paramActivity)
  {
    Logger.d("WGOnDestory");
    WeGame.getInstance().onDestory(paramActivity);
    AutoUpdateManager.onDestory(paramActivity);
  }

  public static void onPause()
  {
    Logger.d("WGOnPause");
    NoticeManager.getInstance().onPause();
    ((MsdkStat)MsdkStat.gDefault.get()).onPause();
  }

  public static void onResume()
  {
    Logger.d("WGOnResume");
    NoticeManager.getInstance().onResume();
    ((WhiteListMng)WhiteListMng.gDefault.get()).onResume();
    AutoUpdateManager.onResume(WeGame.getInstance().getActivity());
    ((MsdkStat)MsdkStat.gDefault.get()).onResume();
  }

  public static void sendToWeixinWithUrl(eWechatScene parameWechatScene, String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte, int paramInt)
  {
    if (parameWechatScene == null)
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = -1;
      localShareRet.desc = "scene cann't be empty!";
      localShareRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
      Logger.e("scene cann't be empty!");
      return;
    }
    if (PermissionManage.getInstance().isHavePermission("WGSendToWeixin"))
    {
      WeGame.getInstance().sendToWeixinWithUrl(parameWechatScene.value, paramString1, paramString2, paramString3, paramString4, paramArrayOfByte, paramInt);
      return;
    }
    Logger.d(TAG, "WGSendToWeixinWithUrl function not permission");
  }

  public static boolean wakeUpFromHall(Intent paramIntent)
  {
    return WeGame.getInstance().wakeUpFromHall(paramIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.WGPlatform
 * JD-Core Version:    0.6.0
 */