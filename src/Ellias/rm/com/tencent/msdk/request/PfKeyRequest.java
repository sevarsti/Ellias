package com.tencent.msdk.request;

import org.json.JSONException;
import org.json.JSONObject;

public class PfKeyRequest extends BaseRequest
{
  private String flag = "";
  private String openId = "";
  private String openKey = "";

  public JSONObject getReqJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt)
  {
    this.openId = paramString1;
    this.openKey = paramString2;
    if (paramInt == 1)
    {
      this.flag = "1";
      setBaseInfo(paramString3, paramString4, paramString5, paramString6 + "_wx");
    }
    JSONObject localJSONObject;
    while (true)
    {
      localJSONObject = getBaseJson();
      try
      {
        localJSONObject.put("userid", this.openId);
        localJSONObject.put("accessToken", this.openKey);
        localJSONObject.put("flag", this.flag);
        return localJSONObject;
        if (paramInt != 2)
          continue;
        this.flag = "2";
        setBaseInfo(paramString3, paramString4, paramString5, paramString6 + "_qq");
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localJSONObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.PfKeyRequest
 * JD-Core Version:    0.6.0
 */