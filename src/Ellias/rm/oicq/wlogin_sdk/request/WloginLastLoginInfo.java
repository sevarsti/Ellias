package oicq.wlogin_sdk.request;

import java.io.Serializable;

public class WloginLastLoginInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public String mAccount = new String();

  public WloginLastLoginInfo()
  {
    this.mAccount = "";
  }

  public WloginLastLoginInfo(String paramString)
  {
    this.mAccount = paramString;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.WloginLastLoginInfo
 * JD-Core Version:    0.6.0
 */