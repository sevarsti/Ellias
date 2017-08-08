package com.tencent.msdk.request;

import android.os.CountDownTimer;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.communicator.HttpRequestManager;
import com.tencent.msdk.communicator.IHttpRequestListener;
import com.tencent.msdk.communicator.UrlManager;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.db.WxLoginModel;
import com.tencent.msdk.myapp.whitelist.WhiteListMng;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.permission.PermissionManage;
import com.tencent.msdk.pf.WGPfManager;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.stat.MsdkStat;
import com.tencent.msdk.tools.Logger;
import com.tencent.stat.StatAppMonitor;
import com.tencent.stat.StatService;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

public class WxRequestMng
  implements IHttpRequestListener
{
  private final String REFRESH_RETRY_TIME_EVENT_NAME = "wgSecondRefreshTime";
  private final String REFRESH_TIME_EVENT_NAME = "wgFirstRefreshTime";
  private final int SAMPLE_RATE = 1;
  private String callbackMsg = "";
  private int notifyState = 0;
  private long refreshRetryStartTime = 0L;
  private long refreshStartTime = 0L;
  private WxRequest req = new WxRequest();

  private void notifyLogin(boolean paramBoolean, int paramInt)
  {
    LoginRet localLoginRet = new LoginRet();
    localLoginRet.platform = WeGame.WXPLATID;
    if (true == paramBoolean)
    {
      localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
      if (paramInt == 2013)
      {
        localLoginRet.flag = 2005;
        localLoginRet.desc = WeGame.setDescribe(2005, WeGame.WXPLATID);
      }
    }
    while (true)
    {
      localLoginRet.desc = (localLoginRet.desc + " cb:" + this.callbackMsg);
      Logger.d("notifyLogin flag = " + localLoginRet.flag + " desc = " + localLoginRet.desc);
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
      return;
      if (paramInt == 2012)
      {
        localLoginRet.flag = 0;
        localLoginRet.desc = WeGame.setDescribe(0, WeGame.WXPLATID);
        continue;
      }
      if ((paramInt == 2014) || (paramInt == 2015))
      {
        localLoginRet.flag = 0;
        localLoginRet.desc = WeGame.setDescribe(0, WeGame.WXPLATID);
        continue;
      }
      if (paramInt != 2018)
        continue;
      localLoginRet.flag = 0;
      localLoginRet.desc = WeGame.setDescribe(0, WeGame.WXPLATID);
      NoticeManager.getInstance().getNoticeInfo();
      continue;
      if (paramInt == 2013)
      {
        localLoginRet.flag = 2006;
        localLoginRet.desc = WeGame.setDescribe(2006, WeGame.WXPLATID);
        continue;
      }
      if (paramInt == 2012)
      {
        localLoginRet.flag = -1;
        localLoginRet.desc = "MSDK用Code换取accessToken失败";
        continue;
      }
      if ((paramInt == 2014) || (paramInt == 2015))
      {
        localLoginRet.flag = -2;
        localLoginRet.desc = "自动登录用refreshToken换取accessToken失败";
        continue;
      }
      if (paramInt != 2018)
        continue;
      localLoginRet.flag = -2;
      localLoginRet.desc = "local AccessToken & RefreshToken is expired!";
      new WxLoginModel().deleteAll();
    }
  }

  private void notifyWakup(boolean paramBoolean)
  {
    WakeupRet localWakeupRet = new WakeupRet();
    localWakeupRet.platform = WeGame.WXPLATID;
    if (true == paramBoolean)
    {
      WxLoginModel localWxLoginModel = new WxLoginModel().getLastLoginUserInfo();
      if (localWxLoginModel == null)
        localWxLoginModel = new WxLoginModel();
      localWakeupRet.flag = 0;
      localWakeupRet.desc = WeGame.setDescribe(0, WeGame.WXPLATID);
      localWakeupRet.open_id = localWxLoginModel.open_id;
    }
    while (true)
    {
      Logger.d("notifyWakup flag = " + localWakeupRet.flag + " desc = " + localWakeupRet.desc);
      WeGameNotifyGame.getInstance().OnPlatformWakeupNotify(localWakeupRet);
      return;
      localWakeupRet.flag = -1;
      localWakeupRet.desc = "MSDK 数据失败";
    }
  }

  private void reportEventToBeacon(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    if (paramBoolean1)
      if (paramInt1 == 2014)
        BeaconHelper.reportMSDKEvent("wxExpiredLoginReq", this.refreshStartTime, true, null, true);
    do
    {
      do
      {
        return;
        if (paramInt1 != 2015)
          continue;
        BeaconHelper.reportMSDKEvent("wxExpiredLoginReq", this.refreshRetryStartTime, true, null, true);
        return;
      }
      while (paramInt1 != 2012);
      BeaconHelper.reportMSDKEvent("wxFirstLoginReq", this.refreshStartTime, true, null, true);
      return;
      if (paramInt1 == 2014)
      {
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("param_FailCode", "" + paramInt2);
        StringBuilder localStringBuilder1 = new StringBuilder().append("");
        if (paramBoolean2);
        for (int i = 1; ; i = 0)
        {
          localHashMap1.put("msdk_logic_error", i);
          BeaconHelper.reportMSDKEvent("wxExpiredLoginReq", this.refreshStartTime, false, localHashMap1, true);
          return;
        }
      }
      if (paramInt1 != 2015)
        continue;
      HashMap localHashMap2 = new HashMap();
      localHashMap2.put("param_FailCode", "" + paramInt2);
      StringBuilder localStringBuilder2 = new StringBuilder().append("");
      if (paramBoolean2);
      for (int j = 1; ; j = 0)
      {
        localHashMap2.put("msdk_logic_error", j);
        BeaconHelper.reportMSDKEvent("wxExpiredLoginReq", this.refreshRetryStartTime, false, localHashMap2, true);
        return;
      }
    }
    while (paramInt1 != 2012);
    HashMap localHashMap3 = new HashMap();
    localHashMap3.put("param_FailCode", "" + paramInt2);
    StringBuilder localStringBuilder3 = new StringBuilder().append("");
    if (paramBoolean2);
    for (int k = 1; ; k = 0)
    {
      localHashMap3.put("msdk_logic_error", k);
      BeaconHelper.reportMSDKEvent("wxFirstLoginReq", this.refreshStartTime, false, localHashMap3, true);
      return;
    }
  }

  public static void updateWxInfo(WxResponse paramWxResponse)
  {
    WxLoginModel localWxLoginModel = new WxLoginModel();
    long l = System.currentTimeMillis() / 1000L;
    localWxLoginModel.open_id = paramWxResponse.openid;
    localWxLoginModel.access_token = paramWxResponse.accessToken;
    if (paramWxResponse.expired != 0)
    {
      localWxLoginModel.access_token_expire = (l + paramWxResponse.expired);
      localWxLoginModel.refresh_token_expire = (2592000L + l);
    }
    localWxLoginModel.refresh_token = paramWxResponse.refreshToken;
    localWxLoginModel.pf = paramWxResponse.pf;
    localWxLoginModel.pf_key = paramWxResponse.pfKey;
    localWxLoginModel.save();
    WGPfManager.getInstance().setRegChannelId(paramWxResponse.regChannel);
    PermissionManage.getInstance().updateDataFromNet(paramWxResponse.permission);
  }

  private void wxExpiredLoginReq(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    new Properties().put("startTime", "" + System.currentTimeMillis());
    if (paramInt2 == 2014)
      this.refreshStartTime = System.currentTimeMillis();
    WxLoginModel localWxLoginModel;
    while (true)
    {
      Logger.d("wxExpiredLoginReq");
      this.notifyState = paramInt1;
      localWxLoginModel = new WxLoginModel().getLastLoginUserInfo();
      if ((localWxLoginModel != null) && (localWxLoginModel.isExisted()))
        break;
      Logger.d("wxUserInfo is not Existed");
      notify(false, "wxUserInfo is not Existed", -1, paramInt2);
      return;
      if (paramInt2 != 2015)
        continue;
      this.refreshRetryStartTime = System.currentTimeMillis();
    }
    WxRequest localWxRequest = this.req;
    localWxRequest.getClass();
    WxRequest.WxExpiredLoginReq localWxExpiredLoginReq = new WxRequest.WxExpiredLoginReq(localWxRequest);
    String str1 = WeGame.getInstance().wx_appid;
    String str2 = WGPfManager.getInstance().getChannelId();
    String str3 = localWxLoginModel.access_token;
    String str4 = localWxLoginModel.refresh_token;
    String str5 = WGPfManager.getInstance().getPlatformId();
    String str6 = WeGame.getInstance().offerId;
    WeGame.getInstance().setPlatId(WeGame.WXPLATID);
    JSONObject localJSONObject = localWxExpiredLoginReq.getReqJson("", str3, str4, str1, str2, str6, str5, localWxLoginModel.open_id);
    if (paramBoolean);
    try
    {
      localJSONObject.put("isCheckToken", "1");
      ((MsdkStat)MsdkStat.gDefault.get()).addLoginLog(localJSONObject, false);
      String str7 = UrlManager.getUrl("/auth/wxexpired_login/", WeGame.WXPLATID);
      Logger.d("json: " + localJSONObject.toString());
      Logger.d("url: " + str7);
      new HttpRequestManager(this).postTextAsync(str7, localJSONObject.toString(), paramInt2);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public void notify(boolean paramBoolean, String paramString, int paramInt1, int paramInt2)
  {
    this.callbackMsg = (" content: " + paramString + " statusCode: " + paramInt1);
    if (this.notifyState == 1)
      notifyLogin(paramBoolean, paramInt2);
    do
      return;
    while (this.notifyState != 2);
    notifyWakup(paramBoolean);
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    StatAppMonitor localStatAppMonitor1;
    if (paramInt2 == 2014)
    {
      localStatAppMonitor1 = new StatAppMonitor("wgFirstRefreshTime");
      localStatAppMonitor1.setSampling(1);
      localStatAppMonitor1.setMillisecondsConsume(System.currentTimeMillis() - this.refreshStartTime);
      if (paramInt1 == 3001)
      {
        localStatAppMonitor1.setResultType(2);
        StatService.reportAppMonitorStat(WeGame.getInstance().getActivity(), localStatAppMonitor1);
      }
    }
    do
    {
      reportEventToBeacon(paramInt2, false, paramInt1, false);
      Logger.d("errorContent:" + paramString + " statusCode: " + paramInt1);
      if ((paramInt2 != 2014) || (paramInt1 == 3001))
        break label217;
      new CountDownTimer(3000L, 1000L)
      {
        public void onFinish()
        {
          WxRequestMng.this.wxExpiredLoginReq(1, 2015, false);
        }

        public void onTick(long paramLong)
        {
        }
      }
      .start();
      return;
      localStatAppMonitor1.setResultType(1);
      break;
    }
    while (paramInt2 != 2015);
    StatAppMonitor localStatAppMonitor2 = new StatAppMonitor("wgSecondRefreshTime");
    localStatAppMonitor2.setSampling(1);
    localStatAppMonitor2.setMillisecondsConsume(System.currentTimeMillis() - this.refreshRetryStartTime);
    if (paramInt1 == 3001)
      localStatAppMonitor2.setResultType(2);
    while (true)
    {
      StatService.reportAppMonitorStat(WeGame.getInstance().getActivity(), localStatAppMonitor2);
      break;
      localStatAppMonitor2.setResultType(1);
    }
    label217: notify(false, paramString, paramInt1, paramInt2);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 2014)
    {
      StatAppMonitor localStatAppMonitor1 = new StatAppMonitor("wgFirstRefreshTime");
      localStatAppMonitor1.setSampling(1);
      localStatAppMonitor1.setMillisecondsConsume(System.currentTimeMillis() - this.refreshStartTime);
      localStatAppMonitor1.setResultType(0);
      StatService.reportAppMonitorStat(WeGame.getInstance().getActivity(), localStatAppMonitor1);
    }
    String str;
    WxResponse localWxResponse;
    while (true)
    {
      str = "";
      localWxResponse = new WxResponse();
      if (paramString != null)
        break;
      Logger.d("onSuccess: statusCode :" + paramInt1);
      notify(false, "response no params", paramInt1, paramInt2);
      reportEventToBeacon(paramInt2, false, 1002, false);
      return;
      if (paramInt2 != 2015)
        continue;
      StatAppMonitor localStatAppMonitor2 = new StatAppMonitor("wgSecondRefreshTime");
      localStatAppMonitor2.setSampling(1);
      localStatAppMonitor2.setMillisecondsConsume(System.currentTimeMillis() - this.refreshRetryStartTime);
      localStatAppMonitor2.setResultType(0);
      StatService.reportAppMonitorStat(WeGame.getInstance().getActivity(), localStatAppMonitor2);
    }
    try
    {
      localWxResponse.parseJson(new SafeJSONObject(paramString));
      str = localWxResponse.msg;
      if (localWxResponse.ret != 0)
        break label346;
      PermissionManage.getInstance().updateDataFromNet(localWxResponse.permission);
      reportEventToBeacon(paramInt2, true, 0, false);
      if ((paramInt2 == 2012) && (((WhiteListMng)WhiteListMng.gDefault.get()).needQueryWhiteList()))
      {
        ((WhiteListMng)WhiteListMng.gDefault.get()).setTmpWXLoginInfo(localWxResponse);
        ((WhiteListMng)WhiteListMng.gDefault.get()).queryUserWhiteListAsync(WeGame.WXPLATID, localWxResponse.openid, localWxResponse.accessToken);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      Logger.d("JSONException json: " + paramString);
      localJSONException.printStackTrace();
      notify(false, str, paramInt1, paramInt2);
      reportEventToBeacon(paramInt2, false, 1001, false);
      return;
    }
    updateWxInfo(localWxResponse);
    notify(true, str, paramInt1, paramInt2);
    return;
    label346: Logger.d("onSuccess error ret:" + localWxResponse.ret + " ret: " + localWxResponse.msg);
    notify(false, str, paramInt1, paramInt2);
    reportEventToBeacon(paramInt2, false, localWxResponse.ret, true);
  }

  public void verifyLocalAndRefreshWxToken(WxLoginModel paramWxLoginModel)
  {
    wxExpiredLoginReq(1, 2018, true);
  }

  public void wxExpiredLoginReq(int paramInt)
  {
    Logger.d("wxExpiredLoginReq(int notifyState)");
    wxExpiredLoginReq(paramInt, 2013, false);
  }

  public void wxFirstLoginReq(String paramString, int paramInt)
  {
    this.refreshStartTime = System.currentTimeMillis();
    this.notifyState = paramInt;
    if ((paramString == null) || (paramString.equals("")))
    {
      notify(false, "wxFirstLoginReq wxCode is empty", -1, 2013);
      Logger.e("wxFirstLoginReq wxCode is empty");
      return;
    }
    WxRequest localWxRequest = this.req;
    localWxRequest.getClass();
    WxRequest.WxFirstLoginReq localWxFirstLoginReq = new WxRequest.WxFirstLoginReq(localWxRequest);
    String str1 = WeGame.getInstance().wx_appid;
    String str2 = WGPfManager.getInstance().getChannelId();
    String str3 = WeGame.getInstance().offerId;
    String str4 = WGPfManager.getInstance().getPlatformId();
    WeGame.getInstance().setPlatId(WeGame.WXPLATID);
    JSONObject localJSONObject = localWxFirstLoginReq.getReqJson("", paramString, str1, str2, str3, str4);
    ((MsdkStat)MsdkStat.gDefault.get()).addLoginLog(localJSONObject, true);
    String str5 = UrlManager.getUrl("/auth/wxfirst_login/", WeGame.WXPLATID);
    new HttpRequestManager(this).postTextAsync(str5, localJSONObject.toString(), 2012);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.WxRequestMng
 * JD-Core Version:    0.6.0
 */