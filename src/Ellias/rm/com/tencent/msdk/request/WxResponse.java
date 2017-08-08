package com.tencent.msdk.request;

import com.tencent.msdk.tools.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class WxResponse
{
  public String accessToken = "";
  public int expired;
  public int first;
  public String msg = "";
  public String openid = "";
  public String permission = "";
  public String pf = "";
  public String pfKey = "";
  public String refreshToken = "";
  public String regChannel = "";
  public int ret;
  public String scope = "";

  public void parseJson(JSONObject paramJSONObject)
  {
    try
    {
      this.ret = paramJSONObject.getInt("ret");
      this.msg = paramJSONObject.getString("msg");
      this.accessToken = paramJSONObject.getString("accessToken");
      this.expired = paramJSONObject.getInt("expired");
      this.refreshToken = paramJSONObject.getString("refreshToken");
      this.openid = paramJSONObject.getString("openid");
      this.scope = paramJSONObject.getString("scope");
      this.first = paramJSONObject.getInt("first");
      this.regChannel = paramJSONObject.getString("regChannel");
      this.pfKey = paramJSONObject.getString("pfKey");
      this.pf = paramJSONObject.getString("pf");
      this.permission = paramJSONObject.getString("funcs");
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.d("JSONException : " + paramJSONObject.toString());
      localJSONException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.WxResponse
 * JD-Core Version:    0.6.0
 */