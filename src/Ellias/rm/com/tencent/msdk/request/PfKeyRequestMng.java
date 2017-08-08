package com.tencent.msdk.request;

import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.communicator.HttpRequestManager;
import com.tencent.msdk.communicator.IHttpRequestListener;
import com.tencent.msdk.communicator.UrlManager;
import com.tencent.msdk.consts.EPlatform;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.db.QQLoginModel;
import com.tencent.msdk.db.WxLoginModel;
import com.tencent.msdk.myapp.whitelist.WhiteListMng;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.stat.MsdkStat;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.SharedPreferencesTool;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

public class PfKeyRequestMng
  implements IHttpRequestListener
{
  public static final String PT_EXPIRED_KEY = "paytoken_expire_time";
  private String callbackMsg = "";
  private WakeupRet currentWakeUp = new WakeupRet();
  private int notifyState = 0;
  private long refreshStartTime = 0L;
  private QQLoginModel tmpLoginInfoFromClient;

  private void notify(boolean paramBoolean, String paramString, int paramInt1, int paramInt2)
  {
    this.callbackMsg = (" content: " + paramString + " statusCode: " + paramInt1);
    if (this.notifyState == 1)
      notifyLogin(paramBoolean, paramInt2);
    do
      return;
    while (this.notifyState != 2);
    notifyWakup(paramBoolean);
  }

  private void notifyLogin(boolean paramBoolean, int paramInt)
  {
    LoginRet localLoginRet = new LoginRet();
    if (true == paramBoolean)
    {
      localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
      localLoginRet.flag = 0;
      localLoginRet.desc = WeGame.setDescribe(0, localLoginRet.platform);
    }
    while (true)
    {
      localLoginRet.desc = (localLoginRet.desc + " cb:" + this.callbackMsg);
      Logger.d("notifyLogin flag = " + localLoginRet.flag + " desc = " + localLoginRet.desc);
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
      return;
      localLoginRet.platform = LoginInfoManager.getInstance().getLastLoginUserInfo().platform;
      if (paramInt == 2017)
      {
        localLoginRet.flag = -2;
        localLoginRet.desc = "check token failed, let user login again";
        LoginInfoManager.getInstance().deleteAllLoginRecord();
        continue;
      }
      localLoginRet.flag = -1;
      localLoginRet.desc = "check token failed, let user login again";
    }
  }

  private void notifyWakup(boolean paramBoolean)
  {
    WeGameNotifyGame.getInstance().OnPlatformWakeupNotify(this.currentWakeUp);
  }

  private void reportEventToBeacon(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    if (paramBoolean1)
      if (2016 == paramInt1)
        BeaconHelper.reportMSDKEvent("qqFirstLoginPfKeyReq", this.refreshStartTime, true, null, true);
    do
    {
      do
        return;
      while (2010 != paramInt1);
      BeaconHelper.reportMSDKEvent("getPfKeyReqWithWakeup", this.refreshStartTime, true, null, true);
      return;
      if (2016 != paramInt1)
        continue;
      HashMap localHashMap1 = new HashMap();
      localHashMap1.put("param_FailCode", "" + paramInt2);
      StringBuilder localStringBuilder1 = new StringBuilder().append("");
      if (paramBoolean2);
      for (int i = 1; ; i = 0)
      {
        localHashMap1.put("msdk_logic_error", i);
        BeaconHelper.reportMSDKEvent("qqFirstLoginPfKeyReq", this.refreshStartTime, false, localHashMap1, true);
        return;
      }
    }
    while (2010 != paramInt1);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("param_FailCode", "" + paramInt2);
    StringBuilder localStringBuilder2 = new StringBuilder().append("");
    if (paramBoolean2);
    for (int j = 1; ; j = 0)
    {
      localHashMap2.put("msdk_logic_error", j);
      BeaconHelper.reportMSDKEvent("getPfKeyReqWithWakeup", this.refreshStartTime, false, localHashMap2, true);
      return;
    }
  }

  private void updateLoginInfo(PfKeyResponse paramPfKeyResponse)
  {
    long l = System.currentTimeMillis() / 1000L;
    Logger.d("updateLoginInfo:" + WeGame.getInstance().getPlatId());
    if (WeGame.QQPLATID == WeGame.getInstance().getPlatId())
    {
      if (this.tmpLoginInfoFromClient == null)
      {
        Logger.d("updateLoginInfo error null == qqloginRecord !!!");
        return;
      }
      this.tmpLoginInfoFromClient.pf = paramPfKeyResponse.pf;
      this.tmpLoginInfoFromClient.pf_key = paramPfKeyResponse.pfKey;
      Logger.d("rspFromLogin.paytokenExpire: " + paramPfKeyResponse.paytokenExpire);
      if (paramPfKeyResponse.paytokenExpire > 0L)
      {
        this.tmpLoginInfoFromClient.pay_token_expire = (l + paramPfKeyResponse.paytokenExpire);
        SharedPreferencesTool.putInt(WeGame.getInstance().getActivity(), "paytoken_expire_time", (int)paramPfKeyResponse.paytokenExpire);
      }
      this.tmpLoginInfoFromClient.save();
      WeGame.getInstance().setOpenSdkLoginInfo(this.tmpLoginInfoFromClient.open_id, this.tmpLoginInfoFromClient.access_token, this.tmpLoginInfoFromClient.access_token_expire);
    }
    while (true)
    {
      WGPfManager.getInstance().setRegChannelId(paramPfKeyResponse.regChannel);
      PermissionManage.getInstance().updateDataFromNet(paramPfKeyResponse.permission);
      return;
      if (WeGame.WXPLATID != WeGame.getInstance().getPlatId())
        continue;
      WxLoginModel localWxLoginModel = new WxLoginModel().getLastLoginUserInfo();
      if (localWxLoginModel == null)
      {
        Logger.d("updateLoginInfo error null == qqloginRecord !!!");
        return;
      }
      localWxLoginModel.pf = paramPfKeyResponse.pf;
      localWxLoginModel.pf_key = paramPfKeyResponse.pfKey;
      localWxLoginModel.save();
    }
  }

  public String getLocalTokenByType(int paramInt, Vector<TokenRet> paramVector)
  {
    Iterator localIterator = paramVector.iterator();
    while (localIterator.hasNext())
    {
      TokenRet localTokenRet = (TokenRet)localIterator.next();
      if (localTokenRet.type == paramInt)
        return localTokenRet.value;
    }
    return null;
  }

  public void getPfKeyReq(int paramInt)
  {
    getPfKeyReq(paramInt, 2010);
  }

  public void getPfKeyReq(int paramInt1, int paramInt2)
  {
    this.notifyState = paramInt1;
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    if (localLoginRet.platform == EPlatform.ePlatform_None.val())
    {
      Logger.d("getPfKeyReq no record in db\n");
      notify(false, "getPfKeyReq no record in db", -1, paramInt2);
      return;
    }
    this.refreshStartTime = System.currentTimeMillis();
    PfKeyRequest localPfKeyRequest = new PfKeyRequest();
    String str1 = WGPfManager.getInstance().getChannelId();
    String str2 = localLoginRet.open_id;
    int i;
    String str3;
    if (localLoginRet.platform == WeGame.QQPLATID)
    {
      i = WeGame.QQPLATID;
      str3 = getLocalTokenByType(2, localLoginRet.token);
    }
    for (String str4 = WeGame.getInstance().qq_appid; ; str4 = WeGame.getInstance().wx_appid)
    {
      WeGame.getInstance().setPlatId(i);
      String str5 = WGPfManager.getInstance().getPlatformId();
      JSONObject localJSONObject = localPfKeyRequest.getReqJson(str2, str3, str4, str1, WeGame.getInstance().offerId, str5, i);
      String str6 = UrlManager.getUrl("/auth/getlogin_info/", i);
      Logger.d(str6);
      new HttpRequestManager(this).postTextAsync(str6, localJSONObject.toString(), paramInt2);
      return;
      if (localLoginRet.platform != WeGame.WXPLATID)
        break;
      i = WeGame.WXPLATID;
      str3 = getLocalTokenByType(3, localLoginRet.token);
    }
    Logger.w("no login record");
  }

  public void getPfKeyReq(int paramInt, WakeupRet paramWakeupRet)
  {
    this.currentWakeUp = paramWakeupRet;
    getPfKeyReq(paramInt);
  }

  public void getQQFirstLoginPfKeyReq(QQLoginModel paramQQLoginModel)
  {
    if (paramQQLoginModel == null)
    {
      notify(false, "QQ Client Login Return NULL", -1, 2016);
      return;
    }
    this.tmpLoginInfoFromClient = paramQQLoginModel;
    this.notifyState = 1;
    this.refreshStartTime = System.currentTimeMillis();
    PfKeyRequest localPfKeyRequest = new PfKeyRequest();
    String str1 = WeGame.getInstance().qq_appid;
    String str2 = WGPfManager.getInstance().getChannelId();
    String str3 = paramQQLoginModel.open_id;
    String str4 = paramQQLoginModel.pay_token;
    int i = WeGame.QQPLATID;
    WeGame.getInstance().setPlatId(i);
    Logger.d("updateLoginInfo1:" + WeGame.getInstance().getPlatId());
    String str5 = WGPfManager.getInstance().getPlatformId();
    JSONObject localJSONObject = localPfKeyRequest.getReqJson(str3, str4, str1, str2, WeGame.getInstance().offerId, str5, i);
    ((MsdkStat)MsdkStat.gDefault.get()).addLoginLog(localJSONObject, true);
    String str6 = UrlManager.getUrl("/auth/getlogin_info/", i);
    Logger.d(str6);
    new HttpRequestManager(this).postTextAsync(str6, localJSONObject.toString(), 2016);
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    reportEventToBeacon(paramInt2, false, paramInt1, false);
    if (paramString != null)
      Logger.d("errorContent:" + paramString + " statusCode: " + paramInt1);
    notify(false, paramString, paramInt1, paramInt2);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    String str = "";
    PfKeyResponse localPfKeyResponse;
    if ((paramInt2 == 2010) || (2016 == paramInt2) || (2017 == paramInt2))
    {
      localPfKeyResponse = new PfKeyResponse();
      if (paramString == null)
      {
        Logger.d("PfKeyRequestMng what:" + paramInt2 + ", http onSuccess but response no params, statusCode :" + paramInt1);
        notify(false, "response no params", paramInt1, paramInt2);
        reportEventToBeacon(paramInt2, false, 1002, false);
      }
    }
    else
    {
      return;
    }
    try
    {
      localPfKeyResponse.parseJson(new SafeJSONObject(paramString));
      str = localPfKeyResponse.msg;
      if (localPfKeyResponse.ret != 0)
        break label291;
      reportEventToBeacon(paramInt2, true, 0, false);
      PermissionManage.getInstance().updateDataFromNet(localPfKeyResponse.permission);
      if ((2016 != paramInt2) || (!((WhiteListMng)WhiteListMng.gDefault.get()).needQueryWhiteList()))
        break label275;
      if (this.tmpLoginInfoFromClient == null)
      {
        notify(false, str, paramInt1, paramInt2);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      notify(false, str, paramInt1, paramInt2);
      reportEventToBeacon(paramInt2, false, 1001, false);
      return;
    }
    this.tmpLoginInfoFromClient.pf = localPfKeyResponse.pf;
    this.tmpLoginInfoFromClient.pf_key = localPfKeyResponse.pfKey;
    ((WhiteListMng)WhiteListMng.gDefault.get()).setTmpQQLoginInfo(this.tmpLoginInfoFromClient);
    ((WhiteListMng)WhiteListMng.gDefault.get()).queryUserWhiteListAsync(WeGame.QQPLATID, this.tmpLoginInfoFromClient.open_id, this.tmpLoginInfoFromClient.access_token);
    return;
    label275: updateLoginInfo(localPfKeyResponse);
    notify(true, str, paramInt1, paramInt2);
    return;
    label291: Logger.d("onSuccess error ret:" + localPfKeyResponse.ret + " ret: " + localPfKeyResponse.msg);
    reportEventToBeacon(paramInt2, false, localPfKeyResponse.ret, true);
    if ((2017 == paramInt2) && ((localPfKeyResponse.ret == -10000) || (localPfKeyResponse.ret == -20000)))
      new QQLoginModel().deleteAll();
    notify(false, str, paramInt1, paramInt2);
  }

  public void verifyLocalQQToken(QQLoginModel paramQQLoginModel)
  {
    if (paramQQLoginModel == null)
    {
      notify(false, "QQ Client Login Return NULL", 404, 2017);
      return;
    }
    this.tmpLoginInfoFromClient = paramQQLoginModel;
    this.notifyState = 1;
    PfKeyRequest localPfKeyRequest = new PfKeyRequest();
    String str1 = WeGame.getInstance().qq_appid;
    String str2 = WGPfManager.getInstance().getChannelId();
    String str3 = paramQQLoginModel.open_id;
    String str4 = paramQQLoginModel.pay_token;
    int i = WeGame.QQPLATID;
    WeGame.getInstance().setPlatId(i);
    Logger.d("updateLoginInfo1:" + WeGame.getInstance().getPlatId());
    String str5 = WGPfManager.getInstance().getPlatformId();
    JSONObject localJSONObject = localPfKeyRequest.getReqJson(str3, str4, str1, str2, WeGame.getInstance().offerId, str5, i);
    try
    {
      localJSONObject.put("isCheckQQToken", "1");
      localJSONObject.put("isCheckPayToken", "1");
      localJSONObject.put("qqAccessToken", paramQQLoginModel.access_token);
      ((MsdkStat)MsdkStat.gDefault.get()).addLoginLog(localJSONObject, false);
      String str6 = UrlManager.getUrl("/auth/getlogin_info/", i);
      Logger.d("url: " + str6);
      Logger.d("req: " + localJSONObject.toString());
      new HttpRequestManager(this).postTextAsync(str6, localJSONObject.toString(), 2017);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.PfKeyRequestMng
 * JD-Core Version:    0.6.0
 */