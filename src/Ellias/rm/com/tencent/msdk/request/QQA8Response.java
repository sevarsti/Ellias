package com.tencent.msdk.request;

import org.json.JSONException;
import org.json.JSONObject;

public class QQA8Response
{
  public String accessToken = "";
  public int expired = 0;
  public int first = 0;
  public String msg = "";
  public String openid = "";
  public String payToken = "";
  public String permission = "";
  public String pf = "";
  public String pfKey = "";
  public String regChannel = "";
  public int ret = -1;

  public void parseJson(JSONObject paramJSONObject)
  {
    try
    {
      this.ret = paramJSONObject.getInt("ret");
      this.msg = paramJSONObject.getString("msg");
      this.accessToken = paramJSONObject.getString("accessToken");
      this.expired = paramJSONObject.getInt("expired");
      this.openid = paramJSONObject.getString("openid");
      this.payToken = paramJSONObject.getString("payToken");
      this.first = paramJSONObject.getInt("first");
      this.regChannel = paramJSONObject.getString("regChannel");
      this.pfKey = paramJSONObject.getString("pfKey");
      this.pf = paramJSONObject.getString("pf");
      this.permission = paramJSONObject.getString("funcs");
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  public void reset()
  {
    this.ret = -1;
    this.msg = "";
    this.accessToken = "";
    this.expired = 0;
    this.openid = "";
    this.payToken = "";
    this.first = 0;
    this.regChannel = "";
    this.pfKey = "";
    this.pf = "";
    this.permission = "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.QQA8Response
 * JD-Core Version:    0.6.0
 */