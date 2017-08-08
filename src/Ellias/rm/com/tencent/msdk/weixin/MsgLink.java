package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONException;
import org.json.JSONStringer;

public class MsgLink extends MsgBase
{
  private static String sDefaultIconUrl = "";
  private static String sDefaultUrl = "";
  private static final String sMSG_TYPE = "link";
  private static final String sMsgKey = "type_info";
  private Link mLink = new Link();

  public MsgLink()
  {
    super("link");
    setmUrl(sDefaultUrl);
    setmIconUrl(sDefaultIconUrl);
  }

  public MsgLink(String paramString1, String paramString2)
  {
    this();
    setmIconUrl(paramString1);
    setmUrl(paramString2);
  }

  public String checkParam()
  {
    String str = super.checkParam();
    if (T.ckIsEmpty(this.mLink.mIconUrl))
      str = str + "mIconUrl cann't be Empty;";
    if (T.ckIsEmpty(this.mLink.mUrl))
      str = str + "mUrl cann't be Empty;";
    return str.trim();
  }

  protected String getMsgKey()
  {
    return "type_info";
  }

  public void setmIconUrl(String paramString)
  {
    this.mLink.setmIconUrl(paramString);
  }

  public void setmUrl(String paramString)
  {
    this.mLink.setmUrl(paramString);
  }

  public String toString()
  {
    try
    {
      JSONStringer localJSONStringer = new JSONStringer();
      localJSONStringer.object().key("url").value(this.mLink.mUrl).key("iconurl").value(this.mLink.mIconUrl).endObject();
      String str = localJSONStringer.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  class Link
  {
    private String mIconUrl = "";
    private String mUrl = "";

    Link()
    {
    }

    public void setmIconUrl(String paramString)
    {
      this.mIconUrl = paramString;
    }

    public void setmUrl(String paramString)
    {
      this.mUrl = paramString;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.MsgLink
 * JD-Core Version:    0.6.0
 */