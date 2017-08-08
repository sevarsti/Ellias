package com.tencent.msdk.api;

public abstract class CallbackRet
{
  public String desc = "";
  public int flag = 0;
  public int platform = 0;

  public CallbackRet()
  {
  }

  public CallbackRet(int paramInt1, int paramInt2, String paramString)
  {
    this.platform = paramInt1;
    this.flag = paramInt2;
    this.desc = paramString;
  }

  public String toString()
  {
    String str1 = "flag: " + this.flag + ";";
    String str2 = str1 + "desc: " + this.desc + ";";
    return str2 + "platform: " + this.platform + ";";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.CallbackRet
 * JD-Core Version:    0.6.0
 */