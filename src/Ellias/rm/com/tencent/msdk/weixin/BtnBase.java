package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONObject;

public abstract class BtnBase extends JSONObject
{
  protected static final String TYPE_APP = "app";
  protected static final String TYPE_RANK = "rank";
  protected static final String TYPE_WEB = "web";
  protected static String sButtonKey = "button";
  protected String mName = "";
  protected String mType = "";

  public String checkParam()
  {
    String str = "";
    if (T.ckIsEmpty(this.mName))
      str = str + "mName cann't be Empty;  ";
    if (T.ckIsEmpty(this.mType))
      str = str + "mType cann't be Empty;  ";
    return str.trim();
  }

  public String getBtnType()
  {
    return this.mType;
  }

  public String getmName()
  {
    return this.mName;
  }

  public void setmName(String paramString)
  {
    this.mName = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.BtnBase
 * JD-Core Version:    0.6.0
 */