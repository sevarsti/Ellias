package com.tencent.msdk.request;

import com.tencent.msdk.tools.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class WxRequest
{
  public class WxExpiredLoginReq extends BaseRequest
  {
    private String accessToken = "";
    private String grantType = "";
    private String openId = "";
    private String refreshToken = "";

    public WxExpiredLoginReq()
    {
    }

    public JSONObject getReqJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
    {
      this.grantType = paramString1;
      this.accessToken = paramString2;
      this.refreshToken = paramString3;
      this.openId = paramString8;
      setBaseInfo(paramString4, paramString5, paramString6, paramString7 + "_wx");
      JSONObject localJSONObject = getBaseJson();
      try
      {
        localJSONObject.put("grantType", this.grantType);
        localJSONObject.put("accessToken", this.accessToken);
        localJSONObject.put("refreshToken", this.refreshToken);
        localJSONObject.put("userid", this.openId);
        return localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        Logger.d("JSONException : " + localJSONObject);
        localJSONException.printStackTrace();
      }
      return localJSONObject;
    }
  }

  public class WxFirstLoginReq extends BaseRequest
  {
    private String grantType = "";
    private String wxCode = "";

    public WxFirstLoginReq()
    {
    }

    public JSONObject getReqJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    {
      this.grantType = paramString1;
      this.wxCode = paramString2;
      setBaseInfo(paramString3, paramString4, paramString5, paramString6 + "_wx");
      JSONObject localJSONObject = getBaseJson();
      try
      {
        localJSONObject.put("grantType", this.grantType);
        localJSONObject.put("code", this.wxCode);
        return localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return localJSONObject;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.WxRequest
 * JD-Core Version:    0.6.0
 */