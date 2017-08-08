package com.tencent.msdk.request;

import org.json.JSONException;
import org.json.JSONObject;

public class PfKeyResponse
{
  public int first;
  public String msg = "";
  public long paytokenExpire = 0L;
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
      this.first = paramJSONObject.getInt("first");
      this.regChannel = paramJSONObject.getString("regChannel");
      this.pfKey = paramJSONObject.getString("pfKey");
      this.pf = paramJSONObject.getString("pf");
      this.permission = paramJSONObject.getString("funcs");
      if (paramJSONObject.has("15004"))
        this.paytokenExpire = paramJSONObject.getLong("15004");
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.PfKeyResponse
 * JD-Core Version:    0.6.0
 */