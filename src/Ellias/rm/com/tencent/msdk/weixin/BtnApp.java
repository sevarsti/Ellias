package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONException;
import org.json.JSONStringer;

public class BtnApp extends BtnBase
{
  private static final String sAppKey = "app";
  private App mApp = new App(null);
  private String sDefaultMsgExt = "";
  private String sDefaultName = "";

  public BtnApp()
  {
    this.mType = "app";
    setmName(this.sDefaultName);
    setmMessageExt(this.sDefaultMsgExt);
  }

  public BtnApp(String paramString1, String paramString2)
  {
    this.mType = "app";
    setmName(paramString1);
    setmMessageExt(paramString2);
  }

  public String checkParam()
  {
    String str = super.checkParam();
    if (T.ckIsEmpty(this.mApp.mMessageExt))
      str = str + "mName cann't be Empty;  ";
    return str;
  }

  public void setmMessageExt(String paramString)
  {
    this.mApp.setmMessageExt(paramString);
  }

  public String toString()
  {
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      localJSONStringer.object().key("type").value(this.mType).key("name").value(this.mName).key("app").object().key("message_ext").value(this.mApp.mMessageExt).endObject().endObject();
      String str = localJSONStringer.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  private class App
  {
    private String mMessageExt = "";

    private App()
    {
    }

    public void setmMessageExt(String paramString)
    {
      this.mMessageExt = paramString;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.BtnApp
 * JD-Core Version:    0.6.0
 */