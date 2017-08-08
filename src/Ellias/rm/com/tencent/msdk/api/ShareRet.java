package com.tencent.msdk.api;

public class ShareRet extends CallbackRet
{
  public String extInfo = "";

  public String toString()
  {
    return super.toString() + "; extInfo: " + this.extInfo;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.ShareRet
 * JD-Core Version:    0.6.0
 */