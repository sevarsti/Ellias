package com.tencent.msdk.weixin;

public class MsgText extends MsgBase
{
  private static final String MSG_TYPE = "text";
  private final String MSG_KEY = "type_info";

  public MsgText()
  {
    super("text");
  }

  public String checkParam()
  {
    return super.checkParam();
  }

  protected String getMsgKey()
  {
    return "type_info";
  }

  public String toString()
  {
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.MsgText
 * JD-Core Version:    0.6.0
 */