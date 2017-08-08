package com.tencent.msdk.weixin;

import com.tencent.msdk.tools.T;
import org.json.JSONObject;

public abstract class MsgBase extends JSONObject
{
  String mMsgType;

  public MsgBase(String paramString)
  {
    this.mMsgType = paramString;
  }

  public String checkParam()
  {
    String str = "";
    if (T.ckIsEmpty(this.mMsgType))
      str = str + "mMsgType cann't be Empty;  ";
    return str.trim();
  }

  protected abstract String getMsgKey();

  protected String getMsgType()
  {
    return this.mMsgType;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.MsgBase
 * JD-Core Version:    0.6.0
 */