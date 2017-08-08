package com.tencent.msdk.request;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.communicator.HttpRequestManager;
import com.tencent.msdk.communicator.IHttpRequestListener;
import com.tencent.msdk.communicator.UrlManager;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.db.QQLoginModel;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class QQA8RequestMng
  implements IHttpRequestListener
{
  private static volatile QQA8RequestMng instance = null;
  private String callbackMsg = "";
  private int notifyState = 0;
  private long refreshStartTime = 0L;
  QQA8Response rspFromA8 = new QQA8Response();

  public static QQA8RequestMng getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new QQA8RequestMng();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void notify(boolean paramBoolean, String paramString, int paramInt)
  {
    this.callbackMsg = (" content: " + paramString + " statusCode: " + paramInt);
    if (this.notifyState == 1)
      notifyLogin(paramBoolean);
    do
      return;
    while (this.notifyState != 2);
    notifyWakup(paramBoolean);
  }

  private void notifyLogin(boolean paramBoolean)
  {
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    localLoginRet.flag = -1;
    localLoginRet.desc = ":-( , MSDK 数据失败";
    if (true == paramBoolean)
    {
      localLoginRet.flag = 0;
      localLoginRet.desc = WeGame.setDescribe(0, localLoginRet.platform);
    }
    localLoginRet.desc = (localLoginRet.desc + " cb:" + this.callbackMsg);
    Logger.d("notifyLogin flag = " + localLoginRet.flag + " desc = " + localLoginRet.desc);
    WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
  }

  private void notifyWakup(boolean paramBoolean)
  {
    WakeupRet localWakeupRet = new WakeupRet();
    if (true == paramBoolean)
    {
      LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
      localWakeupRet.platform = WeGame.QQHALL;
      localWakeupRet.open_id = localLoginRet.open_id;
      localWakeupRet.flag = 0;
    }
    for (localWakeupRet.desc = WeGame.setDescribe(0, WeGame.QQHALL); ; localWakeupRet.desc = "大厅拉起失败")
    {
      Logger.d("notifyWakup flag = " + localWakeupRet.flag + " desc = " + localWakeupRet.desc);
      localWakeupRet.desc = (localWakeupRet.desc + " cb:" + this.callbackMsg);
      WeGameNotifyGame.getInstance().OnPlatformWakeupNotify(localWakeupRet);
      return;
      localWakeupRet.flag = -1;
    }
  }

  private void reportEventToBeacon(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      BeaconHelper.reportMSDKEvent("qqA8Req", this.refreshStartTime, true, null, true);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("param_FailCode", "" + paramInt);
    StringBuilder localStringBuilder = new StringBuilder().append("");
    if (paramBoolean2);
    for (int i = 1; ; i = 0)
    {
      localHashMap.put("msdk_logic_error", i);
      BeaconHelper.reportMSDKEvent("qqA8Req", this.refreshStartTime, false, localHashMap, true);
      return;
    }
  }

  private void updateA8Info(QQA8Response paramQQA8Response)
  {
    WeGame.getInstance().setPlatId(WeGame.QQPLATID);
    long l = System.currentTimeMillis() / 1000L;
    QQLoginModel localQQLoginModel = new QQLoginModel();
    localQQLoginModel.open_id = paramQQA8Response.openid;
    localQQLoginModel.access_token = paramQQA8Response.accessToken;
    localQQLoginModel.access_token_expire = (l + paramQQA8Response.expired);
    localQQLoginModel.pay_token = paramQQA8Response.payToken;
    localQQLoginModel.pay_token_expire = (172800L + l);
    localQQLoginModel.pf = paramQQA8Response.pf;
    localQQLoginModel.pf_key = paramQQA8Response.pfKey;
    localQQLoginModel.save();
    WeGame.getInstance().setOpenSdkLoginInfo(paramQQA8Response.openid, paramQQA8Response.accessToken, paramQQA8Response.expired);
    WGPfManager.getInstance().setRegChannelId(paramQQA8Response.regChannel);
    PermissionManage.getInstance().updateDataFromNet(paramQQA8Response.permission);
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("errorContent:" + paramString + " statusCode: " + paramInt1);
    reportEventToBeacon(false, paramInt1, false);
    notify(false, paramString, paramInt2);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    String str = "";
    if (paramInt2 == 2011)
    {
      if (paramString == null)
      {
        Logger.d("server response data is NULL; statusCode: " + paramInt1);
        notify(false, "server response data is NULL", paramInt1);
        reportEventToBeacon(false, 1002, false);
      }
    }
    else
      return;
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      this.rspFromA8.reset();
      this.rspFromA8.parseJson(localSafeJSONObject);
      str = this.rspFromA8.msg;
      if (this.rspFromA8.ret == 0)
      {
        reportEventToBeacon(true, 0, false);
        bool = true;
        updateA8Info(this.rspFromA8);
      }
      while (true)
      {
        return;
        reportEventToBeacon(false, this.rspFromA8.ret, true);
        Logger.d("onSuccess error ret:" + this.rspFromA8.ret + " ret: " + this.rspFromA8.msg);
        bool = false;
      }
    }
    catch (JSONException localJSONException)
    {
      bool = false;
      localJSONException.printStackTrace();
      reportEventToBeacon(false, 1001, false);
      notify(false, str, paramInt1);
      return;
    }
    finally
    {
      notify(bool, str, paramInt1);
    }
    throw localObject;
  }

  public void qqA8Req(String paramString1, String paramString2, int paramInt)
  {
    this.notifyState = paramInt;
    if ((T.ckIsEmpty(paramString1)) || (T.ckIsEmpty(paramString2)))
    {
      Logger.d("qqA8Req params error !!!");
      notify(false, "qqA8Req params error !!!", -1);
      return;
    }
    this.refreshStartTime = System.currentTimeMillis();
    QQA8Request localQQA8Request = new QQA8Request();
    String str1 = WeGame.getInstance().qq_appid;
    String str2 = WGPfManager.getInstance().getChannelId();
    String str3 = WGPfManager.getInstance().getPlatformId();
    JSONObject localJSONObject = localQQA8Request.getReqJson(paramString1, paramString2, str1, str2, WeGame.getInstance().offerId, str3);
    String str4 = UrlManager.getUrl("/auth/qqa8_login/", WeGame.QQPLATID);
    new HttpRequestManager(this).postTextAsync(str4, localJSONObject.toString(), 2011);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.QQA8RequestMng
 * JD-Core Version:    0.6.0
 */