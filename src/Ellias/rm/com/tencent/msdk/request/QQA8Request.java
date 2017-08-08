package com.tencent.msdk.request;

import org.json.JSONException;
import org.json.JSONObject;

public class QQA8Request extends BaseRequest
{
  private String A8 = "";
  private String st = "";

  public JSONObject getReqJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.A8 = paramString1;
    this.st = paramString2;
    setBaseInfo(paramString3, paramString4, paramString5, paramString6 + "_qq");
    JSONObject localJSONObject = getBaseJson();
    try
    {
      localJSONObject.put("A8", this.A8);
      localJSONObject.put("st", this.st);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.request.QQA8Request
 * JD-Core Version:    0.6.0
 */