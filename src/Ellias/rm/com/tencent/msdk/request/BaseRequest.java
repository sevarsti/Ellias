package com.tencent.msdk.request;

import com.tencent.msdk.remote.api.SafeJSONObject;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseRequest
{
  protected String appId = "";
  protected String channel = "";
  protected String offerId = "";
  protected String os = "android";
  protected String platformId = "";

  protected JSONObject getBaseJson()
  {
    SafeJSONObject localSafeJSONObject = new SafeJSONObject();
    try
    {
      localSafeJSONObject.put("appid", this.appId);
      localSafeJSONObject.put("channel", this.channel);
      localSafeJSONObject.put("offerid", this.offerId);
      localSafeJSONObject.put("platform", this.platformId);
      localSafeJSONObject.put("os", this.os);
      return localSafeJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localSafeJSONObject;
  }

  public void setBaseInfo(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.appId = paramString1;
    this.channel = paramString2;
    this.offerId = paramString3;
    this.platformId = paramString4;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.BaseRequest
 * JD-Core Version:    0.6.0
 */