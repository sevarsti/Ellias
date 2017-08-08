package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONException;
import org.json.JSONStringer;

public class BtnWeb extends BtnBase
{
  private static final String sWebKey = "webview";
  private String mType = "";
  private Webview mWebview = new Webview(null);
  private String sDefaultName = "";
  private String sDefaultUrl = "";

  public BtnWeb()
  {
    setmName(this.sDefaultName);
    setmUrl(this.sDefaultUrl);
  }

  public BtnWeb(String paramString1, String paramString2)
  {
    setmName(paramString1);
    setmUrl(paramString2);
  }

  public String checkParam()
  {
    String str = super.checkParam();
    if (T.ckIsEmpty(this.mWebview.mUrl))
      str = str + "mWebview.mUrl cann't be Empty;  ";
    return str.trim();
  }

  public void setmUrl(String paramString)
  {
    this.mWebview.setmUrl(paramString);
  }

  public String toString()
  {
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      localJSONStringer.object().key("type").value(this.mType).key("name").value(this.mName).key("webview").object().key("url").value(this.mWebview.mUrl).endObject().endObject();
      String str = localJSONStringer.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  private class Webview
  {
    private String mUrl = "";

    private Webview()
    {
    }

    public void setmUrl(String paramString)
    {
      this.mUrl = paramString;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.BtnWeb
 * JD-Core Version:    0.6.0
 */