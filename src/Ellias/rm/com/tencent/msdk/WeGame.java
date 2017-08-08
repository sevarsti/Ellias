package com.tencent.msdk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;
import com.tencent.beacon.event.UserAction;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.msdk.api.KVPair;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.MsdkBaseInfo;
import com.tencent.msdk.api.ShareRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.api.WGPlatformObserver;
import com.tencent.msdk.api.WGQZonePermissions;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.api.eQQScene;
import com.tencent.msdk.api.eWechatScene;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.consts.EPlatform;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.db.QQLoginModel;
import com.tencent.msdk.db.WxLoginModel;
import com.tencent.msdk.doctor.MsdkDoctor;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.myapp.autoupdate.AutoUpdateManager;
import com.tencent.msdk.myapp.whitelist.WhiteListMng;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.push.PushManager;
import com.tencent.msdk.qq.ApiName;
import com.tencent.msdk.qq.QQLogin;
import com.tencent.msdk.qq.QQVersionApiManager;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.stat.MsdkStat;
import com.tencent.msdk.stat.ReportEvent;
import com.tencent.msdk.stat.Stat;
import com.tencent.msdk.timer.TaskManager;
import com.tencent.msdk.timer.task.NoticeTask;
import com.tencent.msdk.timer.task.WechatTokenRefreshTask;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import com.tencent.msdk.tools.VersionHelper;
import com.tencent.msdk.webview.WebViewManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

public final class WeGame
{
  public static final int DEFAULT_PATYOKEN_EXPIRATION = 518400;
  public static final int DEFAULT_QQ_ACCESSTOKEN_EXPIRATION = 7776000;
  private static final String MSDK_VERSION = "2.0.3a";
  public static final int QQHALL;
  public static final int QQPLATID;
  public static final int WXPLATID = EPlatform.ePlatform_Weixin.val();
  private static volatile WeGame instance;
  private final int IMG_MAX_SIZE = 10000000;
  private final int PAYTOKEN_BUFFER = 43200;
  private final int QQ_ACCESS_TOKEN_BUFFER = 86400;
  private final int REFRESHTOKEN_BUFFER = 86400;
  private final int THUMB_MAX_SIZE = 32000;
  private final int THUMB_SIZE = 200;
  private final int WX_ACCESS_TOKEN_BUFFER = 1800;
  public IWXAPI api;
  private Activity firstGameActivity = null;
  private int flag = 0;
  private Activity mActivity = null;
  private LoginRet mCallbackRet = null;
  public String mPermission = "all";
  private int mPlatId = 0;
  private Stat mStat = new Stat();
  private Tencent mTencent;
  public String offerId = null;
  public String qqAppKey = "";
  public String qq_appid = "";
  private MsdkThreadManager threadMgn;
  public String wxAppKey = "";
  private long wxRequestStartTime = 0L;
  public String wx_appid = "";

  static
  {
    QQPLATID = EPlatform.ePlatform_QQ.val();
    QQHALL = EPlatform.ePlatform_QQHall.val();
  }

  private boolean checkOldTokenIsUserful(String paramString1, String paramString2)
  {
    if (T.ckIsEmpty(paramString2));
    do
    {
      return true;
      if (T.ckIsEmpty(paramString1))
        return false;
    }
    while (paramString1.equals(paramString2));
    return false;
  }

  private int checkWXEnv()
  {
    if (!getInstance().api.isWXAppInstalled())
    {
      Logger.d("weixin not install");
      return 2000;
    }
    if (!getInstance().api.isWXAppSupportAPI())
      Logger.d("weixin not support api");
    return 0;
  }

  public static WeGame getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new WeGame();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void handleHallCallback(Bundle paramBundle, int paramInt)
  {
    if (paramBundle == null)
    {
      Logger.d("handleHallCallback Bundle EMPTY");
      return;
    }
    String str1 = paramBundle.getString("channelId");
    String str2 = paramBundle.getString("OPEN_AUTH_DATA");
    String str3 = paramBundle.getString("OPEN_AUTH_ST");
    Logger.d("From Hall, channelId: " + str1);
    Logger.d("From Hall, openAuthData: " + str2);
    Logger.d("From Hall, openAuthSt: " + str3);
    MsdkThreadManager.getInstance().qqA8Req(str2, str3, 2);
  }

  private void handleQQCallback(Bundle paramBundle, int paramInt)
  {
    Logger.d("handleQQCallback");
    String str = paramBundle.getString("current_uin");
    Logger.d("qq SetOpenid " + str);
    WakeupRet localWakeupRet = new WakeupRet();
    localWakeupRet.platform = QQPLATID;
    localWakeupRet.open_id = str;
    localWakeupRet.flag = paramInt;
    bundleToVector(paramBundle, localWakeupRet);
    MsdkThreadManager.getInstance().getPfKeyReqWithWakeup(2, localWakeupRet);
  }

  private void handleWXCallback(Bundle paramBundle, int paramInt)
  {
    Logger.d("handleWXCallback");
    if (paramBundle == null)
    {
      Logger.d("handleWXCallback Bundle EMPTY");
      return;
    }
    String str1 = paramBundle.getString("wx_callback");
    int i = paramBundle.getInt("wx_errCode");
    String str2 = paramBundle.getString("wx_errStr");
    String str3 = paramBundle.getString("wx_transaction");
    String str4 = paramBundle.getString("wx_openId");
    String str5 = paramBundle.getString("wx_token");
    String str6 = paramBundle.getString("wx_mediaTagName");
    String str7 = paramBundle.getString("messageExt");
    String str8 = paramBundle.getString("country");
    String str9 = paramBundle.getString("lang");
    Logger.d("handleWXCallback errorCode: " + i);
    if ("onReq".equals(str1))
    {
      WakeupRet localWakeupRet = new WakeupRet();
      localWakeupRet.platform = WXPLATID;
      localWakeupRet.open_id = str4;
      localWakeupRet.flag = paramInt;
      if (!T.ckIsEmpty(str6))
      {
        Logger.d("handleWXCallbackmediaTagName : " + str6);
        localWakeupRet.media_tag_name = str6;
      }
      while (true)
      {
        if (!T.ckIsEmpty(str7))
        {
          Logger.d("handleWXCallbackmessageExt : " + str7);
          localWakeupRet.messageExt = str7;
        }
        if (!T.ckIsEmpty(str8))
        {
          Logger.d("handleWXCallbackcountry : " + str7);
          localWakeupRet.country = str8;
        }
        if (!T.ckIsEmpty(str9))
        {
          Logger.d("handleWXCallbacklang : " + str7);
          localWakeupRet.lang = str9;
        }
        bundleToVector(paramBundle, localWakeupRet);
        MsdkThreadManager.getInstance().getPfKeyReqWithWakeup(2, localWakeupRet);
        return;
        Logger.d("handleWXCallbackmediaTagName null or empty");
      }
    }
    if (("appdata".equals(str3)) || ("img".equals(str3)))
    {
      ShareRet localShareRet = new ShareRet();
      localShareRet.flag = i;
      localShareRet.platform = WXPLATID;
      if (!T.ckIsEmpty(str2))
        localShareRet.desc = str2;
      Logger.d("WX Share ErrorCode :" + i);
      switch (i)
      {
      case -4:
      case -3:
      case -1:
      default:
        localShareRet.flag = -1;
      case 0:
      case -2:
      case -5:
      }
      while (true)
      {
        WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
        return;
        localShareRet.flag = 0;
        continue;
        localShareRet.flag = 2002;
        continue;
        localShareRet.flag = 2001;
      }
    }
    LoginRet localLoginRet = new LoginRet();
    localLoginRet.platform = WXPLATID;
    Logger.d("WXEntryActivity errcode:" + i);
    int j = 0;
    int k;
    switch (i)
    {
    case -3:
    case -1:
    default:
      localLoginRet.flag = 2004;
      k = 1005;
    case 0:
    case -2:
    case -4:
    }
    while (k == 0)
    {
      BeaconHelper.reportMSDKEvent("wxEntryFirstLogin", this.wxRequestStartTime, true, null, true);
      return;
      localLoginRet.flag = 0;
      if (str4 != null)
        localLoginRet.open_id = str4;
      TokenRet localTokenRet = new TokenRet();
      localTokenRet.type = 4;
      if (str5 == null);
      for (localTokenRet.value = ""; ; localTokenRet.value = str5)
      {
        localLoginRet.token.add(localTokenRet);
        Logger.d("code: " + localTokenRet.value);
        MsdkThreadManager.getInstance().wxFirstLoginReq(localTokenRet.value, 1);
        k = 0;
        j = 0;
        break;
      }
      localLoginRet.flag = 2002;
      k = 1000;
      j = 1;
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
      continue;
      localLoginRet.flag = 2003;
      k = 1004;
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
      j = 0;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("param_FailCode", "" + k);
    StringBuilder localStringBuilder = new StringBuilder().append("");
    if (j != 0);
    for (int m = 1; ; m = 0)
    {
      localHashMap.put("msdk_logic_error", m);
      BeaconHelper.reportMSDKEvent("wxEntryFirstLogin", this.wxRequestStartTime, false, localHashMap, true);
      return;
    }
  }

  private boolean isTestEnv()
  {
    String str = getApiDomain();
    return (str.contains("test")) || (str.contains("dev"));
  }

  public static String setDescribe(int paramInt1, int paramInt2)
  {
    String str = "";
    if (paramInt1 == 0)
      if ((paramInt2 == QQPLATID) || (paramInt2 == QQHALL))
        str = ":-) , QQ授权成功";
    do
    {
      return str;
      return ":-) , WX授权成功";
      if (paramInt1 == 2005)
        return ":-) , 微信refresh换acctoken 成功";
    }
    while (paramInt1 != 2006);
    return ":-( , 微信refresh换acctoken 失败";
  }

  public void Initialized(Activity paramActivity, MsdkBaseInfo paramMsdkBaseInfo)
  {
    Logger.setLogType(paramActivity);
    Logger.d("Initialized start: " + this.wx_appid + "-" + this.qq_appid);
    ReportEvent.sGameStart = System.currentTimeMillis() / 1000L;
    this.mActivity = paramActivity;
    this.firstGameActivity = paramActivity;
    this.qq_appid = paramMsdkBaseInfo.qqAppId;
    this.qqAppKey = paramMsdkBaseInfo.qqAppKey;
    this.wx_appid = paramMsdkBaseInfo.wxAppId;
    this.wxAppKey = paramMsdkBaseInfo.wxAppKey;
    this.offerId = paramMsdkBaseInfo.offerId;
    this.api = WXAPIFactory.createWXAPI(this.mActivity, this.wx_appid);
    this.api.registerApp(this.wx_appid);
    this.mTencent = Tencent.createInstance(this.qq_appid, paramActivity.getApplicationContext());
    this.mStat.init(paramActivity, paramMsdkBaseInfo.qqAppId, true);
    this.threadMgn = MsdkThreadManager.getInstance();
    this.threadMgn.init();
    this.threadMgn.sendGetPermissionMsg();
    WebViewManager.getInstance().init(this.mActivity, this.qq_appid);
    Logger.d("Initialized end: " + this.wx_appid + "-" + this.qq_appid);
    Logger.d("WeGameSDK Version: " + WGGetVersion());
    if (isTestEnv())
    {
      ArrayList localArrayList = new MsdkDoctor(getInstance().getActivity()).checkAll();
      Iterator localIterator;
      if ((localArrayList != null) && (localArrayList.size() != 0))
      {
        Logger.w("MSDK Config Error!!!!");
        localIterator = localArrayList.iterator();
      }
      while (localIterator.hasNext())
      {
        Logger.w((String)localIterator.next());
        continue;
        Logger.d("All Config OK!!!");
      }
      Toast.makeText(getActivity(), "You are using " + getApiDomain(), 1).show();
    }
    NoticeManager.getInstance().init(this.mActivity);
    ((WhiteListMng)WhiteListMng.gDefault.get()).setmContext(getActivity());
    if ("true".equals(ConfigManager.readValueByKey(getActivity(), "SAVE_UPDATE")))
      AutoUpdateManager.init();
    Logger.d("PUSH: " + ConfigManager.readValueByKey(getActivity(), "PUSH"));
    if ("true".equals(ConfigManager.readValueByKey(getActivity(), "PUSH")))
    {
      Logger.d("PUSH opened!");
      ((PushManager)PushManager.gDefault.get()).registerAppOnce(getActivity(), this.qq_appid, this.wx_appid);
    }
    while (true)
    {
      ((TaskManager)TaskManager.gDefault.get()).startTimer();
      ((TaskManager)TaskManager.gDefault.get()).addTask(new WechatTokenRefreshTask());
      ((TaskManager)TaskManager.gDefault.get()).addTask(new NoticeTask());
      ((MsdkStat)MsdkStat.gDefault.get()).clearReportStatus();
      logPlatformSDKVersion();
      return;
      Logger.d("PUSH closed!");
    }
  }

  public boolean IsDifferentActivity(Activity paramActivity)
  {
    return (this.firstGameActivity != null) && (!this.firstGameActivity.equals(paramActivity));
  }

  public String WGGetVersion()
  {
    Activity localActivity = getInstance().getActivity();
    try
    {
      InputStream localInputStream = localActivity.getResources().getAssets().open("msdkinfo.ini");
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str = localProperties.getProperty("VERSION", "");
      if (!"2.0.3a".equals(str))
        Logger.d("MSDK_VERSION in code is different from VERSION in assets/msdkmetadata.ini");
      if (str.contains("$"))
        str = "2.0.3a";
      return str;
    }
    catch (IOException localIOException)
    {
      Logger.d("MSDK Version does not match, is this a development version?");
    }
    return "2.0.3a";
  }

  public void WGReportEvent(String paramString1, String paramString2, boolean paramBoolean)
  {
    Logger.d("called");
    this.mStat.reportEvent(paramString1, paramString2, this.mPlatId, paramBoolean);
  }

  public void WGReportEvent(String paramString, HashMap<String, String> paramHashMap, boolean paramBoolean)
  {
    if ((paramString == null) || (paramHashMap == null))
    {
      HashMap localHashMap = new HashMap();
      Logger.e("WGReportEvent Error: name=" + "" + "params=" + localHashMap.toString());
      return;
    }
    this.mStat.reportEvent(paramString, paramHashMap, this.mPlatId, paramBoolean);
  }

  public void WGSendToQQ(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2)
  {
    Logger.d("WGSendToQQ ");
    if (checkQQEnv() != 0);
    VersionHelper localVersionHelper;
    do
    {
      return;
      if (this.mTencent == null)
      {
        Logger.d("WGSendToQQmTencent NULL");
        return;
      }
      if (this.mTencent.getOpenId() == null)
        Logger.d("WGSendToQQgetOpenId NULL");
      while (true)
      {
        Logger.d("mTencent.isSessionValid(): " + this.mTencent.isSessionValid());
        if ((paramInt1 == 1) || (paramInt1 == 2))
          break;
        Logger.e("scene error, scene should be QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN or QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE");
        return;
        if (this.mTencent.isSessionValid())
          continue;
        Logger.d("WGSendToQQisSessionValid FALSE");
      }
      if ((paramInt1 == 1) && (paramString3 != null) && (paramString3.length() > 256))
        Logger.w("url is too long(>256), maybe fail to share. it's value:" + paramString3.length());
      localVersionHelper = new VersionHelper(getActivity(), "com.tencent.mobileqq");
      if (this.mTencent == null)
        continue;
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      Logger.d("sendReq to QQ With OpenId: " + this.mTencent.getOpenId() + ";and local openid:" + localLoginRet.open_id);
      Bundle localBundle = new Bundle();
      localBundle.putString("title", paramString1);
      localBundle.putString("summary", paramString2);
      localBundle.putString("targetUrl", paramString3);
      localBundle.putString("imageUrl", paramString4);
      localBundle.putInt("cflag", paramInt1);
      localBundle.putString("appName", getAppName());
      Logger.d(localBundle);
      this.mTencent.shareToQQ(this.mActivity, localBundle, new IUiListener(localVersionHelper)
      {
        public void onCancel()
        {
          Logger.d("WGSendToQQ onCancel");
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            ShareRet localShareRet = new ShareRet();
            localShareRet.flag = 1001;
            localShareRet.platform = WeGame.QQPLATID;
            localShareRet.desc = "use cancel";
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
          }
        }

        public void onComplete(Object paramObject)
        {
          Logger.d("WGSendToQQ onComplete");
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            ShareRet localShareRet = new ShareRet();
            localShareRet.flag = 0;
            localShareRet.platform = WeGame.QQPLATID;
            localShareRet.desc = "success";
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
          }
        }

        public void onError(UiError paramUiError)
        {
          Logger.d("WGSendToQQ onError Code (" + paramUiError.errorCode + "), Message(" + paramUiError.errorMessage + ")");
          ShareRet localShareRet;
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            localShareRet = new ShareRet();
            localShareRet.flag = -1;
            if (paramUiError.errorMessage != null)
              break label97;
          }
          label97: for (String str = ""; ; str = paramUiError.errorMessage)
          {
            localShareRet.desc = str;
            localShareRet.platform = WeGame.QQPLATID;
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
            return;
          }
        }
      });
    }
    while (localVersionHelper.compareVersion("4.5") > 0);
    ShareRet localShareRet = new ShareRet();
    localShareRet.flag = 0;
    localShareRet.platform = QQPLATID;
    WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
  }

  public void WGSendToQQWithMusic(eQQScene parameQQScene, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    Logger.d("WGSendToQQWithMusic ");
    if (checkQQEnv() != 0);
    VersionHelper localVersionHelper;
    do
    {
      return;
      if (this.mTencent == null)
      {
        Logger.d("WGSendToQQWithMusicmTencent NULL");
        return;
      }
      if (this.mTencent.getOpenId() == null)
        Logger.d("WGSendToQQWithMusicgetOpenId NULL");
      while (true)
      {
        Logger.d("mTencent.isSessionValid(): " + this.mTencent.isSessionValid());
        if ((parameQQScene.val() == 1) || (parameQQScene.val() == 2))
          break;
        Logger.e("scene error, scene should be QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN or QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE");
        return;
        if (this.mTencent.isSessionValid())
          continue;
        Logger.d("WGSendToQQWithMusicisSessionValid FALSE");
      }
      if ((paramString3 == null) || (paramString3.trim().length() == 0))
      {
        ShareRet localShareRet1 = new ShareRet();
        localShareRet1.flag = -1;
        localShareRet1.desc = "musicUrl cann't be empty!";
        localShareRet1.platform = QQPLATID;
        WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
        Logger.e("musicUrl cann't be empty");
        return;
      }
      if ((parameQQScene.val() == 1) && (paramString3.length() > 256))
        Logger.e("musicUrl is too long(>256), maybe fail to share.it's length:" + paramString3.length());
      if ((paramString5 == null) || (!paramString5.startsWith("http")))
      {
        ShareRet localShareRet2 = new ShareRet();
        localShareRet2.flag = -1;
        localShareRet2.desc = "Music Image URL must be a http resource!";
        localShareRet2.platform = QQPLATID;
        WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
        Logger.e("Music Image URL must be a http resource");
        return;
      }
      localVersionHelper = new VersionHelper(getActivity(), "com.tencent.mobileqq");
      if (this.mTencent == null)
        continue;
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      Logger.d("sendReq to QQ With OpenId: " + this.mTencent.getOpenId() + ";and local openid:" + localLoginRet.open_id);
      Bundle localBundle = new Bundle();
      localBundle.putInt("req_type", 2);
      localBundle.putString("targetUrl", paramString3);
      localBundle.putString("title", paramString1);
      localBundle.putString("audio_url", paramString4);
      localBundle.putString("imageUrl", paramString5);
      localBundle.putString("summary", paramString2);
      localBundle.putString("appName", getAppName());
      localBundle.putInt("cflag", parameQQScene.val());
      Logger.d("WGSendToQQWithMusic params:");
      Logger.d(localBundle);
      this.mTencent.shareToQQ(this.mActivity, localBundle, new IUiListener(localVersionHelper)
      {
        public void onCancel()
        {
          Logger.d("WGSendToQQWithMusic onCancel");
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            ShareRet localShareRet = new ShareRet();
            localShareRet.flag = 1001;
            localShareRet.platform = WeGame.QQPLATID;
            localShareRet.desc = "use cancel";
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
          }
        }

        public void onComplete(Object paramObject)
        {
          Logger.d("WGSendToQQWithMusic onComplete");
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            ShareRet localShareRet = new ShareRet();
            localShareRet.flag = 0;
            localShareRet.platform = WeGame.QQPLATID;
            localShareRet.desc = "success";
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
          }
        }

        public void onError(UiError paramUiError)
        {
          Logger.d("WGSendToQQWithMusic onError Code (" + paramUiError.errorCode + "), Message(" + paramUiError.errorMessage + ")");
          ShareRet localShareRet;
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            localShareRet = new ShareRet();
            localShareRet.flag = -1;
            if (paramUiError.errorMessage != null)
              break label97;
          }
          label97: for (String str = ""; ; str = paramUiError.errorMessage)
          {
            localShareRet.desc = str;
            localShareRet.platform = WeGame.QQPLATID;
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
            return;
          }
        }
      });
    }
    while (localVersionHelper.compareVersion("4.5") > 0);
    ShareRet localShareRet3 = new ShareRet();
    localShareRet3.flag = 0;
    localShareRet3.platform = QQPLATID;
    WeGameNotifyGame.getInstance().OnShareNotify(localShareRet3);
  }

  public void WGSendToQQWithPhoto(int paramInt, String paramString)
  {
    if (!checkApiSupport(ApiName.WGSendToQQWithPhoto))
    {
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.flag = -1;
      localShareRet1.desc = "分享接口只支持手Q 4.5";
      localShareRet1.platform = QQPLATID;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      Logger.d("WGSendToQQWithPhoto only supported by MobileQQ 4.5 or later");
    }
    VersionHelper localVersionHelper;
    do
    {
      return;
      String str = getAppName();
      if ((paramString == null) || (paramString.length() == 0))
      {
        ShareRet localShareRet2 = new ShareRet();
        localShareRet2.flag = -1;
        localShareRet2.desc = "image file path emypty!";
        localShareRet2.platform = QQPLATID;
        WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
        Logger.w("image file path emypty");
        return;
      }
      if ((str == null) || (str.length() == 0))
      {
        Logger.e("gameName emypty");
        return;
      }
      if ((paramInt != 1) && (paramInt != 2))
      {
        Logger.e("scene error, scene should be Tencent.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN or Tencent.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE");
        return;
      }
      if (!new File(paramString).exists())
      {
        ShareRet localShareRet3 = new ShareRet();
        localShareRet3.flag = -1;
        localShareRet3.desc = "image file path invalid or not exists!";
        localShareRet3.platform = QQPLATID;
        WeGameNotifyGame.getInstance().OnShareNotify(localShareRet3);
        Logger.w("image file path invalid or not exists!");
        return;
      }
      localVersionHelper = new VersionHelper(getActivity(), "com.tencent.mobileqq");
      if (this.mTencent == null)
        continue;
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      Logger.d("sendReq to QQ With OpenId: " + this.mTencent.getOpenId() + ";and local openid:" + localLoginRet.open_id);
      Bundle localBundle = new Bundle();
      localBundle.putInt("req_type", 5);
      localBundle.putInt("cflag", paramInt);
      localBundle.putString("imageLocalUrl", paramString);
      localBundle.putString("appName", str);
      this.mTencent.shareToQQ(this.mActivity, localBundle, new IUiListener(localVersionHelper)
      {
        public void onCancel()
        {
          Logger.d("WGSendToQQWithPhoto onCancel");
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            ShareRet localShareRet = new ShareRet();
            localShareRet.flag = 1001;
            localShareRet.platform = WeGame.QQPLATID;
            localShareRet.desc = "use cancel";
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
          }
        }

        public void onComplete(Object paramObject)
        {
          Logger.d("WGSendToQQWithPhoto onComplete");
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            ShareRet localShareRet = new ShareRet();
            localShareRet.flag = 0;
            localShareRet.platform = WeGame.QQPLATID;
            localShareRet.desc = "success";
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
          }
        }

        public void onError(UiError paramUiError)
        {
          Logger.d("WGSendToQQWithPhoto onError Code (" + paramUiError.errorCode + "), Message(" + paramUiError.errorMessage + ")");
          ShareRet localShareRet;
          if (this.val$vh.compareVersion("4.6") >= 0)
          {
            localShareRet = new ShareRet();
            localShareRet.flag = -1;
            if (paramUiError.errorMessage != null)
              break label97;
          }
          label97: for (String str = ""; ; str = paramUiError.errorMessage)
          {
            localShareRet.desc = str;
            localShareRet.platform = WeGame.QQPLATID;
            WeGameNotifyGame.getInstance().OnShareNotify(localShareRet);
            return;
          }
        }
      });
    }
    while (localVersionHelper.compareVersion("4.5") > 0);
    ShareRet localShareRet4 = new ShareRet();
    localShareRet4.flag = 0;
    localShareRet4.platform = QQPLATID;
    WeGameNotifyGame.getInstance().OnShareNotify(localShareRet4);
  }

  public void WGSendToWeixin(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte, int paramInt2)
  {
    Logger.d("WGSendToWeixin：" + paramInt1);
    switch (checkWXEnv())
    {
    default:
      if ((paramArrayOfByte != null) && (paramInt2 != 0))
        break;
      Logger.e("imgData should NOT BE NULL and imgDataLen !== 0");
      return;
    case 2000:
      ShareRet localShareRet2 = new ShareRet();
      localShareRet2.platform = getInstance().getPlatId();
      localShareRet2.desc = "Weixin NOT Installed";
      localShareRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
      return;
    case 2001:
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.platform = getInstance().getPlatId();
      localShareRet1.desc = "Weixin API NOT Support";
      localShareRet1.flag = 2001;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      return;
    }
    Logger.d("imgData: " + paramArrayOfByte.length + ";imgDataLen: " + paramInt2);
    if (paramArrayOfByte.length > 32000)
      Logger.w("imgData too big, it should be less than 32K");
    WXAppExtendObject localWXAppExtendObject = new WXAppExtendObject();
    if (!T.ckIsEmpty(paramString4));
    for (localWXAppExtendObject.extInfo = paramString4; ; localWXAppExtendObject.extInfo = "wgEmptyMediaTagName")
    {
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.title = paramString1;
      localWXMediaMessage.description = paramString2;
      localWXMediaMessage.mediaObject = localWXAppExtendObject;
      localWXMediaMessage.mediaTagName = paramString4;
      localWXMediaMessage.thumbData = paramArrayOfByte;
      SendMessageToWX.Req localReq = new SendMessageToWX.Req();
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      localReq.openId = localLoginRet.open_id;
      localReq.transaction = "appdata";
      localReq.message = localWXMediaMessage;
      localReq.scene = paramInt1;
      Logger.d("WGSendToWinxinsendReq with openid  " + localReq.openId);
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("WGSendToWeixin isSendReqSucc: " + bool);
      return;
    }
  }

  public void WGSendToWeixin(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, int paramInt, String paramString4)
  {
    Logger.d("WGSendToWeixin ");
    switch (checkWXEnv())
    {
    default:
      if ((paramArrayOfByte != null) && (paramInt != 0))
        break;
      Logger.e("imgData should NOT BE NULL and imgDataLen !== 0");
      return;
    case 2000:
      ShareRet localShareRet2 = new ShareRet();
      localShareRet2.platform = getInstance().getPlatId();
      localShareRet2.desc = "Weixin NOT Installed";
      localShareRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
      return;
    case 2001:
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.platform = getInstance().getPlatId();
      localShareRet1.desc = "Weixin API NOT Support";
      localShareRet1.flag = 2001;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      return;
    }
    Logger.d("imgData: " + paramArrayOfByte.length + ";imgDataLen: " + paramInt);
    if (paramArrayOfByte.length > 32000)
      Logger.w("imgData too big, it should be less than 32K");
    WXAppExtendObject localWXAppExtendObject = new WXAppExtendObject();
    if (!T.ckIsEmpty(paramString3));
    for (localWXAppExtendObject.extInfo = paramString3; ; localWXAppExtendObject.extInfo = "wgEmptyMediaTagName")
    {
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.title = paramString1;
      localWXMediaMessage.description = paramString2;
      localWXMediaMessage.mediaObject = localWXAppExtendObject;
      localWXMediaMessage.mediaTagName = paramString3;
      localWXMediaMessage.thumbData = paramArrayOfByte;
      localWXMediaMessage.messageExt = paramString4;
      SendMessageToWX.Req localReq = new SendMessageToWX.Req();
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      localReq.openId = localLoginRet.open_id;
      localReq.transaction = "appdata";
      localReq.message = localWXMediaMessage;
      localReq.scene = 0;
      Logger.d("WGSendToWinxinsendReq with openid  " + localReq.openId);
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("WGSendToWeixin isSendReqSucc: " + bool);
      return;
    }
  }

  public void WGSendToWeixinWithMusic(eWechatScene parameWechatScene, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, byte[] paramArrayOfByte, int paramInt, String paramString6, String paramString7)
  {
    Logger.d("WGSendToWeixinWithMusic scene: " + parameWechatScene);
    switch (checkWXEnv())
    {
    default:
      if ((paramArrayOfByte != null) && (paramInt != 0))
        break;
      Logger.e("imgData should NOT BE NULL and imgDataLen !== 0");
      return;
    case 2000:
      ShareRet localShareRet2 = new ShareRet();
      localShareRet2.platform = getInstance().getPlatId();
      localShareRet2.desc = "Weixin NOT Installed";
      localShareRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
      return;
    case 2001:
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.platform = getInstance().getPlatId();
      localShareRet1.desc = "Weixin API NOT Support";
      localShareRet1.flag = 2001;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      return;
    }
    Logger.d("imgData: " + paramArrayOfByte.length + ";imgDataLen: " + paramInt);
    if (paramArrayOfByte.length > 10000000)
      Logger.w("imgData too big, it should be less than 10M");
    Bitmap localBitmap1 = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramInt);
    if (localBitmap1 == null)
    {
      Logger.e("imgData decode to bmp error!");
      return;
    }
    float f1 = localBitmap1.getWidth();
    float f2 = localBitmap1.getHeight();
    if (f1 > f2);
    for (Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 200, (int)(200.0F * (f2 / f1)), true); ; localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int)(200.0F * (f1 / f2)), 200, true))
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap2.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
      WXMusicObject localWXMusicObject = new WXMusicObject();
      localWXMusicObject.musicUrl = paramString3;
      localWXMusicObject.musicDataUrl = paramString4;
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.title = paramString1;
      localWXMediaMessage.description = paramString2;
      localWXMediaMessage.mediaObject = localWXMusicObject;
      localWXMediaMessage.thumbData = localByteArrayOutputStream.toByteArray();
      localWXMediaMessage.mediaTagName = paramString5;
      localWXMediaMessage.messageExt = paramString6;
      localWXMediaMessage.messageAction = paramString7;
      Logger.d("imgData " + paramArrayOfByte.length);
      Logger.d("thumbData " + localByteArrayOutputStream.toByteArray().length);
      SendMessageToWX.Req localReq = new SendMessageToWX.Req();
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      localReq.openId = localLoginRet.open_id;
      localReq.transaction = "img";
      localReq.message = localWXMediaMessage;
      localReq.scene = parameWechatScene.val();
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("WGSendToWeixin isSendReqSucc: " + bool);
      return;
    }
  }

  public void WGSendToWeixinWithPhoto(int paramInt1, String paramString, byte[] paramArrayOfByte, int paramInt2)
  {
    Logger.d("WGSendToWeixinWithPhoto scene: " + paramInt1);
    switch (checkWXEnv())
    {
    default:
      if ((paramArrayOfByte != null) && (paramInt2 != 0))
        break;
      Logger.e("imgData should NOT BE NULL and imgDataLen !== 0");
      return;
    case 2000:
      ShareRet localShareRet2 = new ShareRet();
      localShareRet2.platform = getInstance().getPlatId();
      localShareRet2.desc = "Weixin NOT Installed";
      localShareRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
      return;
    case 2001:
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.platform = getInstance().getPlatId();
      localShareRet1.desc = "Weixin API NOT Support";
      localShareRet1.flag = 2001;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      return;
    }
    Logger.d("imgData: " + paramArrayOfByte.length + ";imgDataLen: " + paramInt2);
    if (paramArrayOfByte.length > 10000000)
      Logger.w("imgData too big, it should be less than 10M");
    Bitmap localBitmap1 = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramInt2);
    if (localBitmap1 == null)
    {
      Logger.e("imgData decode to bmp error!");
      return;
    }
    float f1 = localBitmap1.getWidth();
    float f2 = localBitmap1.getHeight();
    if (f1 > f2);
    for (Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 200, (int)(200.0F * (f2 / f1)), true); ; localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int)(200.0F * (f1 / f2)), 200, true))
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap2.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
      WXImageObject localWXImageObject = new WXImageObject(localBitmap1);
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.mediaObject = localWXImageObject;
      localWXMediaMessage.thumbData = localByteArrayOutputStream.toByteArray();
      localWXMediaMessage.mediaTagName = paramString;
      Logger.d("imgData " + paramArrayOfByte.length);
      Logger.d("thumbData " + localByteArrayOutputStream.toByteArray().length);
      SendMessageToWX.Req localReq = new SendMessageToWX.Req();
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      localReq.openId = localLoginRet.open_id;
      localReq.transaction = "img";
      localReq.message = localWXMediaMessage;
      localReq.scene = paramInt1;
      Logger.d("WGSendToWinxinsendReq with openid  " + localReq.openId);
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("WGSendToWeixin isSendReqSucc: " + bool);
      return;
    }
  }

  public void WGSendToWeixinWithPhoto(int paramInt1, String paramString1, byte[] paramArrayOfByte, int paramInt2, String paramString2, String paramString3)
  {
    Logger.d("WGSendToWeixinWithPhoto scene: " + paramInt1);
    switch (checkWXEnv())
    {
    default:
      if ((paramArrayOfByte != null) && (paramInt2 != 0))
        break;
      Logger.e("imgData should NOT BE NULL and imgDataLen !== 0");
      return;
    case 2000:
      ShareRet localShareRet2 = new ShareRet();
      localShareRet2.platform = getInstance().getPlatId();
      localShareRet2.desc = "Weixin NOT Installed";
      localShareRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
      return;
    case 2001:
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.platform = getInstance().getPlatId();
      localShareRet1.desc = "Weixin API NOT Support";
      localShareRet1.flag = 2001;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      return;
    }
    Logger.d("imgData: " + paramArrayOfByte.length + ";imgDataLen: " + paramInt2);
    if (paramArrayOfByte.length > 10000000)
      Logger.w("imgData too big, it should be less than 10M");
    Bitmap localBitmap1 = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramInt2);
    if (localBitmap1 == null)
    {
      Logger.e("imgData decode to bmp error!");
      return;
    }
    float f1 = localBitmap1.getWidth();
    float f2 = localBitmap1.getHeight();
    if (f1 > f2);
    for (Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 200, (int)(200.0F * (f2 / f1)), true); ; localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int)(200.0F * (f1 / f2)), 200, true))
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap2.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
      WXImageObject localWXImageObject = new WXImageObject(localBitmap1);
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.mediaObject = localWXImageObject;
      localWXMediaMessage.thumbData = localByteArrayOutputStream.toByteArray();
      localWXMediaMessage.mediaTagName = paramString1;
      localWXMediaMessage.messageExt = paramString2;
      localWXMediaMessage.messageAction = paramString3;
      Logger.d("imgData " + paramArrayOfByte.length);
      Logger.d("thumbData " + localByteArrayOutputStream.toByteArray().length);
      SendMessageToWX.Req localReq = new SendMessageToWX.Req();
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      localReq.openId = localLoginRet.open_id;
      localReq.transaction = "img";
      localReq.message = localWXMediaMessage;
      localReq.scene = paramInt1;
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("WGSendToWeixin isSendReqSucc: " + bool);
      return;
    }
  }

  public void WGSetPermission(int paramInt)
  {
    Logger.d("WGSetQzonePermission + permissions: " + paramInt);
    ArrayList localArrayList = new ArrayList(Arrays.asList(WGQZonePermissions.getPermissionStr(paramInt)));
    localArrayList.removeAll(Arrays.asList(new String[] { "", null }));
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < localArrayList.size(); i++)
    {
      if (i > 0)
        localStringBuilder.append(", ");
      localStringBuilder.append((String)localArrayList.get(i));
    }
    this.mPermission = localStringBuilder.toString();
    Logger.d("mPermission: " + this.mPermission);
  }

  public void bundleToVector(Bundle paramBundle, WakeupRet paramWakeupRet)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      KVPair localKVPair = new KVPair();
      localKVPair.key = str;
      Object localObject = paramBundle.get(str);
      if (localObject == null)
        continue;
      localKVPair.value = localObject.toString();
      paramWakeupRet.extInfo.add(localKVPair);
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = str;
      arrayOfObject[1] = localObject.toString();
      arrayOfObject[2] = localObject.getClass().getName();
      Logger.d(String.format("%s %s (%s)", arrayOfObject));
    }
  }

  public boolean checkApiSupport(ApiName paramApiName)
  {
    boolean bool = true;
    if (paramApiName.val() < ApiName.MAX_QQ_API)
      bool = QQVersionApiManager.isSupport(this.mActivity, paramApiName);
    do
      return bool;
    while (paramApiName.val() >= ApiName.MAX_WX_API);
    return bool;
  }

  public int checkDiffLogin(LoginRet paramLoginRet)
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    Logger.d("******************local lr start********************");
    Logger.d(localLoginRet);
    Logger.d("******************local lr end*******************");
    if (localLoginRet.open_id.equals(paramLoginRet.open_id))
    {
      Logger.d("local and callback is same user!");
      if (localLoginRet.flag == 0)
      {
        if (QQPLATID == localLoginRet.platform)
          if ((!checkOldTokenIsUserful(localLoginRet.getAccessToken(), paramLoginRet.getAccessToken())) || (!checkOldTokenIsUserful(localLoginRet.getTokenByType(2), paramLoginRet.getTokenByType(2))));
        do
        {
          return 0;
          switchUser(true);
          return 3002;
        }
        while ((WXPLATID != localLoginRet.platform) || ((checkOldTokenIsUserful(localLoginRet.getAccessToken(), paramLoginRet.getAccessToken())) && (checkOldTokenIsUserful(localLoginRet.getTokenByType(5), paramLoginRet.getTokenByType(5)))));
        switchUser(true);
        return 3002;
      }
      if (2007 == localLoginRet.flag)
      {
        refreshWxToken();
        return 3004;
      }
      if (switchUser(true))
        return 3002;
      return 3001;
    }
    if (!T.ckIsEmpty(localLoginRet.open_id))
    {
      Logger.d("local openid:" + localLoginRet.open_id);
      if ((localLoginRet.flag == 0) || (2007 == localLoginRet.flag))
      {
        if (2007 == localLoginRet.flag)
          refreshWxToken();
        Logger.d("local user is valid");
        if (!T.ckIsEmpty(paramLoginRet.open_id))
        {
          Logger.d("call back openid:" + paramLoginRet.open_id);
          return 3003;
        }
        Logger.d("call back without openid");
        return 0;
      }
      Logger.d("local user is invalid");
      if ((!T.ckIsEmpty(paramLoginRet.open_id)) && (!T.ckIsEmpty(paramLoginRet.getAccessToken())))
      {
        Logger.d("call back openid:" + paramLoginRet.open_id);
        return 3003;
      }
      Logger.d("call back without openid");
      return 3001;
    }
    Logger.d("local without openid");
    if ((!T.ckIsEmpty(paramLoginRet.open_id)) && (!T.ckIsEmpty(paramLoginRet.getAccessToken())))
    {
      Logger.d("call back openid:" + paramLoginRet.open_id);
      if (switchUser(true))
        return 3002;
      return 3001;
    }
    Logger.d("call back without openid");
    return 3001;
  }

  public int checkQQEnv()
  {
    return 0;
  }

  public void enableCrashReport(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mStat.enableCrashReport(paramBoolean1, paramBoolean2);
  }

  public void feedback(String paramString)
  {
    if (paramString == null)
      paramString = "";
    WGPlatform.WGGetLoginRecord(new LoginRet());
    MsdkThreadManager.getInstance().sendFeedbackWithAppid(paramString);
  }

  public boolean feedback(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "";
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    MsdkThreadManager.getInstance().sendFeedback(paramString1, paramString2, localLoginRet.platform, localLoginRet.open_id);
    return true;
  }

  public Activity getActivity()
  {
    return this.mActivity;
  }

  public String getApiDomain()
  {
    Logger.d("Use DOMAIN: " + ConfigManager.getApiDomain(this.mActivity));
    return ConfigManager.getApiDomain(this.mActivity).trim();
  }

  public String getAppName()
  {
    try
    {
      String str = this.mActivity.getPackageManager().getPackageInfo(this.mActivity.getPackageName(), 0).applicationInfo.loadLabel(this.mActivity.getPackageManager()).toString();
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }

  protected long getExpiresTime(String paramString)
  {
    return (Long.parseLong(paramString) - System.currentTimeMillis()) / 1000L;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public String getLocalTokenByType(int paramInt)
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    Iterator localIterator = localLoginRet.token.iterator();
    while (localIterator.hasNext())
    {
      TokenRet localTokenRet = (TokenRet)localIterator.next();
      if (localTokenRet.type == paramInt)
        return localTokenRet.value;
    }
    return null;
  }

  public String getMsdkVersion()
  {
    return "2.0.3a";
  }

  public int getPlatId()
  {
    return this.mPlatId;
  }

  public Stat getStat()
  {
    return this.mStat;
  }

  public Tencent getTencent()
  {
    return this.mTencent;
  }

  public void handleCallback(Intent paramIntent)
  {
    Logger.d("handleCallBack");
    if ((paramIntent == null) || (paramIntent.getExtras() == null))
    {
      Logger.d("handleCallBackintent is NULL");
      return;
    }
    Logger.d("INTENT ************************************************");
    Logger.d(paramIntent);
    Logger.d("INTENT ************************************************");
    Bundle localBundle = new Bundle();
    localBundle.putAll(paramIntent.getExtras());
    Iterator localIterator = paramIntent.getExtras().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str6 = (String)localIterator.next();
      Logger.d("remove " + str6);
      paramIntent.removeExtra(str6);
    }
    Logger.d("INTENT start************************************************");
    Logger.d(paramIntent);
    Logger.d("INTENT end************************************************");
    this.mActivity.setIntent(paramIntent);
    Object localObject = localBundle.getString("channel");
    if (T.ckIsEmpty((String)localObject))
      Logger.d("user old channel: " + (String)localObject);
    String str1 = localBundle.getString("platformId");
    if (T.ckIsEmpty(str1))
      str1 = localBundle.getString("platform");
    WGPfManager.getInstance().setPlatformId(str1);
    this.mCallbackRet = new LoginRet();
    String str4;
    String str5;
    label421: int i;
    if (T.ckIsEmpty(str1))
    {
      String str3 = localBundle.getString("current_uin");
      str4 = localBundle.getString("wx_callback");
      str5 = localBundle.getString("KEY_REPORT_CHID");
      if (!T.ckIsEmpty(str3))
      {
        this.mCallbackRet.platform = QQPLATID;
        if (!T.ckIsEmpty(localBundle.getString("openid")))
        {
          this.mCallbackRet.open_id = localBundle.getString("openid");
          if (!T.ckIsEmpty(localBundle.getString("atoken")))
            this.mCallbackRet.token.add(new TokenRet(1, localBundle.getString("atoken"), 7776000L));
          if (!T.ckIsEmpty(localBundle.getString("ptoken")))
            this.mCallbackRet.token.add(new TokenRet(2, localBundle.getString("ptoken"), 518400L));
          if (!T.ckIsEmpty(localBundle.getString("pfKey")))
            this.mCallbackRet.pf_key = localBundle.getString("pfKey");
          Logger.d("******************callback lr start ********************");
          Logger.d(this.mCallbackRet);
          Logger.d("******************callback lr end ********************");
          i = checkDiffLogin(this.mCallbackRet);
          if (QQPLATID != this.mCallbackRet.platform)
            break label935;
          Logger.d("handleQQCallback,diffAccountFlag:" + i);
          handleQQCallback(localBundle, i);
        }
      }
    }
    while (true)
    {
      if (str1 == null)
      {
        String str2 = localBundle.getString("KEY_REPORT_CHID");
        if (str2 != null)
        {
          str1 = "mobile";
          localObject = str2;
        }
      }
      Logger.d("###platformId:" + str1);
      Logger.d("###channel:" + (String)localObject);
      return;
      this.mCallbackRet.open_id = localBundle.getString("current_uin");
      break;
      if (!T.ckIsEmpty(str4))
      {
        this.mCallbackRet.platform = WXPLATID;
        this.mCallbackRet.open_id = localBundle.getString("wx_openId");
        break label421;
      }
      if (!T.ckIsEmpty(str5))
      {
        this.mCallbackRet.platform = QQHALL;
        break label421;
      }
      Logger.d("handdle unknow platformID: " + str1);
      break label421;
      if ("qq_m".equals(str1))
      {
        this.mCallbackRet.platform = QQPLATID;
        if (!T.ckIsEmpty(localBundle.getString("openid")));
        for (this.mCallbackRet.open_id = localBundle.getString("openid"); ; this.mCallbackRet.open_id = localBundle.getString("current_uin"))
        {
          if (!T.ckIsEmpty(localBundle.getString("atoken")))
            this.mCallbackRet.token.add(new TokenRet(1, localBundle.getString("atoken"), 7776000L));
          if (!T.ckIsEmpty(localBundle.getString("ptoken")))
            this.mCallbackRet.token.add(new TokenRet(2, localBundle.getString("ptoken"), 518400L));
          if (T.ckIsEmpty(localBundle.getString("pfKey")))
            break;
          this.mCallbackRet.pf_key = localBundle.getString("pfKey");
          break;
        }
      }
      if ("wechat".equals(str1))
      {
        this.mCallbackRet.platform = WXPLATID;
        this.mCallbackRet.open_id = localBundle.getString("wx_openId");
        break label421;
      }
      if ("mobile".equals(str1))
      {
        this.mCallbackRet.platform = QQHALL;
        break label421;
      }
      Logger.d("handdle unknow platformID: " + str1);
      break label421;
      label935: if (WXPLATID == this.mCallbackRet.platform)
      {
        Logger.d("handleWXCallback,diffAccountFlag:" + i);
        handleWXCallback(localBundle, i);
        continue;
      }
      if (QQHALL == this.mCallbackRet.platform)
      {
        Logger.d("handleHallCallback,diffAccountFlag:" + i);
        handleHallCallback(localBundle, i);
        continue;
      }
      Logger.d("handdle unknow platformID: " + str1);
    }
  }

  public void lauchWXPlatForm()
  {
    Logger.d("lauchWXPlatForm");
    getInstance().mPlatId = WXPLATID;
    switch (checkWXEnv())
    {
    default:
      this.wxRequestStartTime = System.currentTimeMillis();
      SendAuth.Req localReq = new SendAuth.Req();
      localReq.scope = "snsapi_userinfo,snsapi_friend,snsapi_message";
      localReq.state = "none";
      Logger.d("wexin sendReq");
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("lauchWXPlatForm wx SendReqRet: " + bool);
      return;
    case 2000:
      LoginRet localLoginRet2 = new LoginRet();
      localLoginRet2.platform = getInstance().getPlatId();
      localLoginRet2.desc = "Weixin NOT Installed";
      localLoginRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet2);
      return;
    case 2001:
    }
    LoginRet localLoginRet1 = new LoginRet();
    localLoginRet1.platform = getInstance().getPlatId();
    localLoginRet1.desc = "Weixin API NOT Support";
    localLoginRet1.flag = 2001;
    WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet1);
  }

  public void logPlatformSDKVersion()
  {
    Logger.d("OpenSDK: 2.3");
    Logger.d("WeixinSDKVersionName: android 2.1.3");
    Logger.d("WeixinSDKVersionCode: 570490883");
    Logger.d("Mta: 1.6.2");
    Logger.d("Beacon: " + UserAction.getSDKVersion());
    Logger.d("WeixinClient: " + VersionHelper.getAppVersionName(this.mActivity, "com.tencent.mm"));
    Logger.d("QQClient: " + VersionHelper.getAppVersionName(this.mActivity, "com.tencent.mobileqq"));
    Logger.d("QQGameClient: " + VersionHelper.getAppVersionName(this.mActivity, "com.tencent.qqgame"));
  }

  public void login(int paramInt)
  {
    ((WhiteListMng)WhiteListMng.gDefault.get()).cleanWhiteListUserinfoCache();
    ((MsdkStat)MsdkStat.gDefault.get()).clearReportStatus();
    if (paramInt == WXPLATID)
    {
      setPlatId(WXPLATID);
      lauchWXPlatForm();
      return;
    }
    if (paramInt == QQPLATID)
    {
      setPlatId(QQPLATID);
      new QQLogin().lauchQQPlatForm();
      return;
    }
    Logger.e("Wrong platformId");
  }

  public void loginWithLocalInfo()
  {
    LoginRet localLoginRet1 = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet1);
    if (localLoginRet1.platform == QQPLATID)
    {
      switch (localLoginRet1.flag)
      {
      default:
        Logger.d("WGGetLoginRecord return flag: " + this.flag);
        Logger.d(localLoginRet1);
        LoginRet localLoginRet6 = new LoginRet(QQPLATID, -2, "flag: " + localLoginRet1.flag);
        WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet6);
        new QQLoginModel().deleteAll();
        return;
      case 0:
        Logger.d("local token valid");
        QQLoginModel localQQLoginModel = new QQLoginModel().getLastLoginUserInfo();
        MsdkThreadManager.getInstance().verifyLocalQQToken(localQQLoginModel);
        return;
      case 1006:
      case 1007:
      }
      Logger.d("TokenExpired");
      LoginRet localLoginRet5 = new LoginRet(QQPLATID, -2, "pay token expired, let users login again!");
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet5);
      new QQLoginModel().deleteAll();
      return;
    }
    if (localLoginRet1.platform == WXPLATID)
    {
      switch (localLoginRet1.flag)
      {
      default:
        Logger.d("WGGetLoginRecord return invalid flag");
        Logger.d(localLoginRet1);
        LoginRet localLoginRet4 = new LoginRet(WXPLATID, -2, "invalid flag" + localLoginRet1.flag);
        WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet4);
        new WxLoginModel().deleteAll();
        return;
      case 0:
      case 2007:
        Logger.d("local token valid");
        WxLoginModel localWxLoginModel = new WxLoginModel().getLastLoginUserInfo();
        MsdkThreadManager.getInstance().verifyLocalAndRefreshWxToken(localWxLoginModel);
        return;
      case 2008:
      }
      LoginRet localLoginRet3 = new LoginRet(WXPLATID, -2, "refresh token expired");
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet3);
      new WxLoginModel().deleteAll();
      return;
    }
    Logger.d("WGGetLoginRecord return invalid platform " + localLoginRet1.platform);
    LoginRet localLoginRet2 = new LoginRet(QQPLATID, -2, "invalid platform");
    WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet2);
    getInstance().logout();
  }

  public boolean logout()
  {
    Logger.d("logout ");
    this.mStat.resetOpenId();
    WGPfManager.getInstance().clearPfAndPfKey();
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    if (localLoginRet.platform != 0)
    {
      this.mPlatId = localLoginRet.platform;
      LoginInfoManager.getInstance().deleteLoginRecord(localLoginRet.open_id);
    }
    if (this.mPlatId == QQPLATID)
    {
      setPlatId(0);
      if (this.mTencent == null);
      do
        return false;
      while (!this.mTencent.isSessionValid());
      this.mTencent.logout(this.mActivity);
    }
    while (true)
    {
      return true;
      if (this.mPlatId != WXPLATID)
        continue;
      setPlatId(0);
      if (this.api == null)
        break;
      this.api.unregisterApp();
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Logger.d("onActivityResult");
  }

  public void onDestory(Activity paramActivity)
  {
    if ((paramActivity != null) && (paramActivity.equals(this.firstGameActivity)))
      this.firstGameActivity = null;
    ReportEvent.ReportGameFinished();
  }

  public void refreshWxToken()
  {
    MsdkThreadManager.getInstance().wxExpiredLoginReq(1);
  }

  public void sendLogin(int paramInt)
  {
    Logger.d("called");
    MsdkThreadManager.getInstance().sendLoginMsg(paramInt);
  }

  public void sendToWeixinWithUrl(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte, int paramInt2)
  {
    Logger.d("WGSendToWeixinWithUrl " + paramInt1);
    switch (checkWXEnv())
    {
    default:
      if ((paramArrayOfByte != null) && (paramInt2 != 0))
        break;
      Logger.e("imgData should NOT BE NULL and imgDataLen !== 0");
      return;
    case 2000:
      ShareRet localShareRet2 = new ShareRet();
      localShareRet2.platform = getInstance().getPlatId();
      localShareRet2.desc = "Weixin NOT Installed";
      localShareRet2.flag = 2000;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet2);
      return;
    case 2001:
      ShareRet localShareRet1 = new ShareRet();
      localShareRet1.platform = getInstance().getPlatId();
      localShareRet1.desc = "Weixin API NOT Support";
      localShareRet1.flag = 2001;
      WeGameNotifyGame.getInstance().OnShareNotify(localShareRet1);
      return;
    }
    Logger.d("imgData: " + paramArrayOfByte.length + ";imgDataLen: " + paramInt2);
    if (paramArrayOfByte.length > 32000)
      Logger.w("imgData too big, it should be less than 32K");
    Bitmap localBitmap1 = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramInt2);
    if (localBitmap1 == null)
    {
      Logger.e("imgData decode to bmp error!");
      return;
    }
    float f1 = localBitmap1.getWidth();
    float f2 = localBitmap1.getHeight();
    Bitmap localBitmap2;
    WXMediaMessage localWXMediaMessage;
    SendMessageToWX.Req localReq;
    LoginRet localLoginRet;
    if (f1 > f2)
    {
      localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 200, (int)(200.0F * (f2 / f1)), true);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap2.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
      WXWebpageObject localWXWebpageObject = new WXWebpageObject();
      localWXWebpageObject.webpageUrl = paramString3;
      localWXMediaMessage = new WXMediaMessage(localWXWebpageObject);
      localWXMediaMessage.title = paramString1;
      localWXMediaMessage.description = paramString2;
      localWXMediaMessage.thumbData = localByteArrayOutputStream.toByteArray();
      localReq = new SendMessageToWX.Req();
      localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      if (localLoginRet.platform != WXPLATID)
        break label501;
    }
    label501: for (localReq.openId = localLoginRet.open_id; ; localReq.openId = "")
    {
      localReq.transaction = "webpage";
      localReq.message = localWXMediaMessage;
      localReq.scene = paramInt1;
      Logger.d("WGSendToWeixinWithUrlsendReq with openid  " + localReq.openId);
      boolean bool = getInstance().api.sendReq(localReq);
      Logger.d("WGSendToWeixinWithUrl isSendReqSucc: " + bool);
      return;
      localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int)(200.0F * (f1 / f2)), 200, true);
      break;
    }
  }

  protected String setExpiresTime(String paramString)
  {
    return System.currentTimeMillis() + 1000L * Long.parseLong(paramString) + "";
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setObserver(WGPlatformObserver paramWGPlatformObserver)
  {
    WeGameNotifyGame.getInstance().setObserver(paramWGPlatformObserver);
  }

  public void setOpenSdkLoginInfo(String paramString1, String paramString2, long paramLong)
  {
    if (paramString1 == null)
      paramString1 = "";
    if (paramString2 == null)
      paramString2 = "";
    this.mTencent.setOpenId(paramString1);
    this.mTencent.setAccessToken(paramString2, "" + paramLong);
  }

  public void setPlatId(int paramInt)
  {
    this.mPlatId = paramInt;
  }

  public boolean switchUser(boolean paramBoolean)
  {
    Logger.d("switchUser:" + paramBoolean);
    if (paramBoolean)
    {
      Logger.d("login with callback");
      if (QQPLATID == this.mCallbackRet.platform)
      {
        Logger.d("loginWithUrlInfo: qq");
        QQLoginModel localQQLoginModel = new QQLoginModel();
        localQQLoginModel.open_id = this.mCallbackRet.open_id;
        long l = System.currentTimeMillis() / 1000L;
        localQQLoginModel.access_token = this.mCallbackRet.getAccessToken();
        localQQLoginModel.access_token_expire = (l + this.mCallbackRet.getTokenExpireByType(1));
        localQQLoginModel.pay_token = this.mCallbackRet.getTokenByType(2);
        localQQLoginModel.pay_token_expire = (l + this.mCallbackRet.getTokenExpireByType(2));
        localQQLoginModel.pf = this.mCallbackRet.pf;
        localQQLoginModel.pf_key = this.mCallbackRet.pf_key;
        localQQLoginModel.save();
        MsdkThreadManager.getInstance().getQQFirstLoginPfKeyReq(localQQLoginModel);
        return true;
      }
      if (WXPLATID == this.mCallbackRet.platform)
      {
        Logger.d("loginWithUrlInfo: wechat");
        return false;
      }
      if (QQHALL == this.mCallbackRet.platform)
      {
        Logger.d("loginWithUrlInfo: hall");
        return false;
      }
      Logger.w("loginWithUrlInfo: " + this.mCallbackRet.platform);
      return false;
    }
    Logger.d("login with local");
    this.mCallbackRet = new LoginRet();
    return true;
  }

  public void testSpeed(ArrayList<String> paramArrayList)
  {
    this.mStat.speedTest(paramArrayList);
  }

  public int validateAccountToken(LoginRet paramLoginRet)
  {
    long l1 = System.currentTimeMillis() / 1000L;
    if (paramLoginRet.platform == WXPLATID)
    {
      if (l1 > paramLoginRet.getTokenExpireByType(5) - 86400L)
        return 2008;
      if (l1 > paramLoginRet.getTokenExpireByType(3) - 1800L)
        return 2007;
      return 0;
    }
    if (paramLoginRet.platform == QQPLATID)
    {
      long l2 = paramLoginRet.getTokenExpireByType(1);
      long l3 = paramLoginRet.getTokenExpireByType(2);
      if (l1 > l2 - 86400L)
        return 1006;
      if (l1 > l3 - 43200L)
        return 1007;
      return 0;
    }
    return -1;
  }

  public boolean wakeUpFromHall(Intent paramIntent)
  {
    if ((paramIntent == null) || (paramIntent.getExtras() == null))
      Logger.d("wakeUpFromHallintent is NULL");
    do
      return false;
    while (!paramIntent.getExtras().keySet().contains("KEY_START_FROM_HALL"));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.WeGame
 * JD-Core Version:    0.6.0
 */