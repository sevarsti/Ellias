package com.tencent.msdk.qq;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.db.QQLoginModel;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.tools.Logger;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

public class QQLogin
{
  private long requestStartTime = 0L;

  public void lauchQQPlatForm()
  {
    Logger.d("lauchQQPlatForm");
    Tencent localTencent = WeGame.getInstance().getTencent();
    if (localTencent == null);
    do
    {
      return;
      WeGame.getInstance().setPlatId(WeGame.QQPLATID);
    }
    while (WeGame.getInstance().checkQQEnv() != 0);
    this.requestStartTime = System.currentTimeMillis();
    localTencent.setOpenId("");
    localTencent.setAccessToken("", "0");
    localTencent.login(WeGame.getInstance().getActivity(), WeGame.getInstance().mPermission, new QQLoginCallback("loginAction"));
  }

  private class QQLoginCallback
    implements IUiListener
  {
    public QQLoginCallback()
    {
    }

    public QQLoginCallback(String arg2)
    {
      this();
      String str;
      Logger.d(str);
    }

    public void onCancel()
    {
      Logger.d("qq login onCancel");
      HashMap localHashMap = new HashMap();
      localHashMap.put("param_FailCode", "1000");
      localHashMap.put("msdk_logic_error", "1");
      BeaconHelper.reportMSDKEvent("qqEntryFirstLogin", this.this$0.requestStartTime, false, localHashMap, true);
      LoginRet localLoginRet = new LoginRet();
      localLoginRet.flag = 1001;
      localLoginRet.platform = WeGame.QQPLATID;
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
    }

    public void onComplete(Object paramObject)
    {
      Logger.d("qq login onComplete");
      if (!(paramObject instanceof JSONObject))
      {
        Logger.d("not JSONObject" + paramObject);
        return;
      }
      JSONObject localJSONObject = (JSONObject)paramObject;
      while (true)
      {
        LoginRet localLoginRet2;
        try
        {
          WeGame.getInstance().setPlatId(WeGame.QQPLATID);
          if (localJSONObject.has("pay_token"))
            continue;
          localJSONObject.put("pay_token", "");
          localLoginRet2 = new LoginRet();
          localLoginRet2.platform = WeGame.QQPLATID;
          localLoginRet2.open_id = localJSONObject.getString("openid");
          String str = localJSONObject.getString("access_token");
          if ((str == null) || (str.equals("")))
          {
            localLoginRet2.flag = 1000;
            TokenRet localTokenRet1 = new TokenRet();
            localTokenRet1.type = 1;
            localTokenRet1.value = str;
            localTokenRet1.expiration = Integer.valueOf(localJSONObject.getString("expires_in")).intValue();
            localLoginRet2.token.add(localTokenRet1);
            TokenRet localTokenRet2 = new TokenRet();
            localTokenRet2.type = 2;
            localTokenRet2.value = localJSONObject.getString("pay_token");
            localTokenRet2.expiration = 518400L;
            localLoginRet2.token.add(localTokenRet2);
            localLoginRet2.pf = localJSONObject.getString("pf");
            Logger.d("QQEntryActivity pf: " + localLoginRet2.pf);
            localLoginRet2.pf_key = localJSONObject.getString("pfkey");
            Logger.d("QQEntryActivity pfkey: " + localLoginRet2.pf_key);
            long l = System.currentTimeMillis() / 1000L;
            QQLoginModel localQQLoginModel = new QQLoginModel();
            localQQLoginModel.open_id = localLoginRet2.open_id;
            localQQLoginModel.access_token = localTokenRet1.value;
            localQQLoginModel.access_token_expire = (l + localTokenRet1.expiration);
            localQQLoginModel.pay_token = localTokenRet2.value;
            localQQLoginModel.pay_token_expire = (l + localTokenRet2.expiration);
            localQQLoginModel.pf = localLoginRet2.pf;
            localQQLoginModel.pf_key = localLoginRet2.pf_key;
            MsdkThreadManager.getInstance().getQQFirstLoginPfKeyReq(localQQLoginModel);
            BeaconHelper.reportMSDKEvent("qqEntryFirstLogin", this.this$0.requestStartTime, true, null, true);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          LoginRet localLoginRet1 = new LoginRet();
          localLoginRet1.flag = 1003;
          WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet1);
          localJSONException.printStackTrace();
          HashMap localHashMap = new HashMap();
          localHashMap.put("param_FailCode", "1001");
          localHashMap.put("msdk_logic_error", "0");
          BeaconHelper.reportMSDKEvent("qqEntryFirstLogin", this.this$0.requestStartTime, false, localHashMap, true);
          WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet1);
          return;
        }
        localLoginRet2.flag = 0;
      }
    }

    public void onError(UiError paramUiError)
    {
      Logger.d("qq login onError arg0 errorCode:" + paramUiError.errorCode);
      Logger.d("qq login onError arg0 errorDetail:" + paramUiError.errorDetail);
      LoginRet localLoginRet = new LoginRet();
      localLoginRet.platform = WeGame.QQPLATID;
      localLoginRet.flag = 1003;
      HashMap localHashMap = new HashMap();
      localHashMap.put("param_FailCode", "1003");
      localHashMap.put("msdk_logic_error", "0");
      BeaconHelper.reportMSDKEvent("qqEntryFirstLogin", this.this$0.requestStartTime, false, localHashMap, true);
      WeGameNotifyGame.getInstance().OnPlatformLoginNotify(localLoginRet);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.qq.QQLogin
 * JD-Core Version:    0.6.0
 */