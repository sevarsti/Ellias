package com.tencent.msdk.myapp.whitelist;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.db.QQLoginModel;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.request.WxRequestMng;
import com.tencent.msdk.request.WxResponse;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.VersionHelper;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.ITMOpenSDKToMsdkListener;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.TMOpenSDKAuthorizedInfo;
import com.tencent.tmassistantsdk.openSDK.opensdktomsdk.TMOpenSDKToMsdkManager;

public class WhiteListMng
{
  public static final Singleton<WhiteListMng> gDefault = new Singleton()
  {
    protected WhiteListMng create()
    {
      Logger.d("WhiteListMng create");
      WhiteListMng localWhiteListMng = new WhiteListMng();
      WhiteListMng.access$002(localWhiteListMng, WeGame.getInstance().qq_appid);
      WhiteListMng.access$102(localWhiteListMng, WeGame.getInstance().wx_appid);
      localWhiteListMng.setmContext(WeGame.getInstance().getActivity());
      return localWhiteListMng;
    }
  };
  ITMOpenSDKToMsdkListener listener = new ITMOpenSDKToMsdkListener()
  {
    public void onAuthorizedFinished(boolean paramBoolean, TMOpenSDKAuthorizedInfo paramTMOpenSDKAuthorizedInfo)
    {
      Logger.d("onAuthorizedFinished");
      if (paramBoolean)
      {
        if (WhiteListMng.this.getTmpQQLoginInfo() != null)
        {
          WhiteListMng.this.getTmpQQLoginInfo().save();
          WhiteListMng.this.setTmpQQLoginInfo(null);
          LoginRet localLoginRet3 = new LoginRet();
          WGPlatform.WGGetLoginRecord(localLoginRet3);
          WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet3);
        }
        if (WhiteListMng.this.getTmpWXLoginInfo() != null)
        {
          WxRequestMng.updateWxInfo(WhiteListMng.this.getTmpWXLoginInfo());
          WhiteListMng.this.setTmpWXLoginInfo(null);
          LoginRet localLoginRet2 = new LoginRet();
          WGPlatform.WGGetLoginRecord(localLoginRet2);
          WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet2);
        }
        Toast.makeText(WeGame.getInstance().getActivity(), "授权成功!", 0).show();
        Logger.d(" isAuthorizeSucceed: " + paramBoolean + "  authorizedInfo:" + paramTMOpenSDKAuthorizedInfo);
        WhiteListMng.this.onDestory();
        label165: return;
      }
      LoginRet localLoginRet1 = new LoginRet();
      localLoginRet1.flag = -3;
      localLoginRet1.open_id = paramTMOpenSDKAuthorizedInfo.userId;
      if (WhiteListMng.this.mQQAppId.equals(paramTMOpenSDKAuthorizedInfo.appId))
        localLoginRet1.platform = WeGame.QQPLATID;
      for (localLoginRet1.desc = "qq user not in white list"; ; localLoginRet1.desc = "wx user not in white list")
      {
        Logger.d("notifyLogin flag = " + localLoginRet1.flag + " desc = " + localLoginRet1.desc);
        WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet1);
        break;
        if (!WhiteListMng.this.mWxAppId.equals(paramTMOpenSDKAuthorizedInfo.appId))
          break label165;
        localLoginRet1.platform = WeGame.WXPLATID;
      }
    }
  };
  private Activity mContext;
  private String mQQAppId = "";
  private String mWxAppId = "";
  private QQLoginModel tmpQQLoginInfo;
  private WxResponse tmpWXLoginInfo;
  private TMOpenSDKToMsdkManager whiteListManager;

  public void cleanWhiteListUserinfoCache()
  {
    setTmpQQLoginInfo(null);
    setTmpWXLoginInfo(null);
  }

  public QQLoginModel getTmpQQLoginInfo()
  {
    return this.tmpQQLoginInfo;
  }

  public WxResponse getTmpWXLoginInfo()
  {
    return this.tmpWXLoginInfo;
  }

  public Activity getmContext()
  {
    return this.mContext;
  }

  public boolean needQueryWhiteList()
  {
    if ((PermissionManage.getInstance().isHavePermission("SkipWhitelist")) || (!ConfigManager.isBeta(getmContext())));
    for (boolean bool = true; ; bool = false)
    {
      Logger.d("skipWhitelist: " + bool);
      if (bool)
        break;
      return true;
    }
    return false;
  }

  public void onDestory()
  {
    if (this.whiteListManager != null)
    {
      this.whiteListManager.onDestroy();
      this.whiteListManager = null;
    }
  }

  public void onResume()
  {
    if (this.whiteListManager != null)
      this.whiteListManager.onResume();
  }

  public void queryUserWhiteListAsync(int paramInt, String paramString1, String paramString2)
  {
    if (this.whiteListManager == null)
      this.whiteListManager = TMOpenSDKToMsdkManager.getInstance(getmContext());
    Logger.d("OpensdkToMsdkManager MSDK", "" + this.listener);
    this.whiteListManager.initOpenSDK(this.listener);
    TMOpenSDKAuthorizedInfo localTMOpenSDKAuthorizedInfo = new TMOpenSDKAuthorizedInfo();
    localTMOpenSDKAuthorizedInfo.actionFlag = "2";
    localTMOpenSDKAuthorizedInfo.actionType = 1;
    if (paramInt == WeGame.QQPLATID)
    {
      Logger.d(this.mQQAppId);
      localTMOpenSDKAuthorizedInfo.appId = this.mQQAppId;
      localTMOpenSDKAuthorizedInfo.gameChannelId = "6633";
    }
    for (localTMOpenSDKAuthorizedInfo.verifyType = "2"; ; localTMOpenSDKAuthorizedInfo.verifyType = "1")
    {
      localTMOpenSDKAuthorizedInfo.gamePackageName = getmContext().getApplicationInfo().packageName;
      localTMOpenSDKAuthorizedInfo.gameVersionCode = ("" + VersionHelper.getAppVersionCode(getmContext(), getmContext().getApplicationInfo().packageName));
      localTMOpenSDKAuthorizedInfo.identityInfo = paramString2;
      localTMOpenSDKAuthorizedInfo.identityType = "1";
      localTMOpenSDKAuthorizedInfo.userId = paramString1;
      localTMOpenSDKAuthorizedInfo.userIdType = "game_openId";
      localTMOpenSDKAuthorizedInfo.via = "via";
      Logger.d("OpensdkToMsdkManager", "getUserAuthorizedInfo getmContext()" + getmContext());
      this.whiteListManager.getUserAuthorizedInfo(localTMOpenSDKAuthorizedInfo, getmContext());
      return;
      if (paramInt != WeGame.WXPLATID)
        break;
      Logger.d(this.mWxAppId);
      localTMOpenSDKAuthorizedInfo.appId = this.mWxAppId;
      localTMOpenSDKAuthorizedInfo.gameChannelId = "10910";
    }
    Logger.d("invalid platform");
  }

  public void setTmpQQLoginInfo(QQLoginModel paramQQLoginModel)
  {
    this.tmpQQLoginInfo = paramQQLoginModel;
  }

  public void setTmpWXLoginInfo(WxResponse paramWxResponse)
  {
    this.tmpWXLoginInfo = paramWxResponse;
  }

  public void setmContext(Activity paramActivity)
  {
    this.mContext = paramActivity;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.myapp.whitelist.WhiteListMng
 * JD-Core Version:    0.6.0
 */