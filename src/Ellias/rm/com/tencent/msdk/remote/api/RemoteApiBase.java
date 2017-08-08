package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.communicator.HttpRequestManager;
import com.tencent.msdk.communicator.IHttpRequestListener;
import com.tencent.msdk.stat.BeaconHelper;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.MsdkSig;
import com.tencent.msdk.tools.T;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RemoteApiBase
  implements IHttpRequestListener
{
  protected String accessToken = "";
  protected String appId = "";
  protected String appKey = "";
  protected JSONObject jsonBody = new SafeJSONObject();
  protected String openId = "";
  protected String pf = "";
  protected String pfKey = "";
  private String platform = "";
  private long requestStartTime = 0L;

  public RemoteApiBase()
  {
    LoginRet localLoginRet = new LoginRet();
    int i = WGPlatform.WGGetLoginRecord(localLoginRet);
    if (localLoginRet.flag == 0)
    {
      this.openId = localLoginRet.open_id;
      this.pf = localLoginRet.pf;
      this.pfKey = localLoginRet.pf_key;
      if ((i == WeGame.QQPLATID) || (i == WeGame.QQHALL))
      {
        this.appId = WeGame.getInstance().qq_appid;
        this.appKey = WeGame.getInstance().qqAppKey;
        this.accessToken = localLoginRet.getTokenByType(1);
        this.platform = ("" + WeGame.QQPLATID);
      }
      try
      {
        while (true)
        {
          this.jsonBody.put("appid", this.appId);
          this.jsonBody.put("openid", this.openId);
          this.jsonBody.put("accessToken", this.accessToken);
          this.jsonBody.put("platform", this.platform);
          return;
          if (i != WeGame.WXPLATID)
            continue;
          this.appId = WeGame.getInstance().wx_appid;
          this.appKey = WeGame.getInstance().wxAppKey;
          this.accessToken = localLoginRet.getTokenByType(3);
          this.platform = ("" + WeGame.WXPLATID);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
    }
    Logger.d("no login record");
  }

  protected String getCustomDomain()
  {
    return WeGame.getInstance().getApiDomain();
  }

  protected String getExtUrlParams()
  {
    return "";
  }

  protected abstract int getMyId();

  public String getNetDesc(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    if (paramJSONObject != null);
    try
    {
      String str = paramString + " ret: " + paramJSONObject.getInt("ret") + " msg: " + paramJSONObject.getString("msg");
      localObject = str;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return (String)localObject;
  }

  protected abstract String getPath();

  protected void reportEventToBeacon(String paramString, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      BeaconHelper.reportMSDKEvent(paramString, this.requestStartTime, true, null, true);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("param_FailCode", "" + paramInt);
    StringBuilder localStringBuilder = new StringBuilder().append("");
    if (paramBoolean2);
    for (int i = 1; ; i = 0)
    {
      localHashMap.put("msdk_logic_error", i);
      BeaconHelper.reportMSDKEvent(paramString, this.requestStartTime, false, localHashMap, true);
      return;
    }
  }

  public void send()
  {
    send(this.jsonBody.toString());
  }

  protected void send(String paramString)
  {
    this.requestStartTime = System.currentTimeMillis();
    String str1 = "" + this.requestStartTime;
    String str2 = MsdkSig.make(str1, this.appKey);
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = str1;
    arrayOfObject[1] = this.appId;
    arrayOfObject[2] = str2;
    arrayOfObject[3] = this.openId;
    String str3 = String.format("?timestamp=%s&appid=%s&sig=%s&openid=%s&encode=1", arrayOfObject);
    String str4 = getExtUrlParams();
    if (!T.ckIsEmpty(str4))
      str3 = str3 + "&" + str4;
    String str5 = getCustomDomain() + getPath() + str3;
    Logger.d(paramString);
    Logger.d(str5);
    new HttpRequestManager(this).postTextAsync(str5, paramString, getMyId());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.RemoteApiBase
 * JD-Core Version:    0.6.0
 */