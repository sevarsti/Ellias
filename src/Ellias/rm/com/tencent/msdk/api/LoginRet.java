package com.tencent.msdk.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.tools.Logger;
import java.util.Vector;

public class LoginRet extends CallbackRet
{
  public String open_id = "";
  public String pf = "";
  public String pf_key = "";
  public Vector<TokenRet> token = new Vector();
  public String user_id = "";

  public LoginRet()
  {
  }

  public LoginRet(int paramInt1, int paramInt2, String paramString)
  {
    super(paramInt1, paramInt2, paramString);
  }

  public String getAccessToken()
  {
    int i = this.platform;
    String str = "";
    if ((i == WeGame.QQPLATID) || (i == WeGame.QQHALL))
      str = getTokenByType(1);
    do
      return str;
    while (i != WeGame.WXPLATID);
    return getTokenByType(3);
  }

  public String getTokenByType(int paramInt)
  {
    for (int i = 0; i < this.token.size(); i++)
      if (((TokenRet)this.token.get(i)).type == paramInt)
        return ((TokenRet)this.token.get(i)).value;
    return "";
  }

  public long getTokenExpireByType(int paramInt)
  {
    for (int i = 0; i < this.token.size(); i++)
      if (((TokenRet)this.token.get(i)).type == paramInt)
        return ((TokenRet)this.token.get(i)).expiration;
    return 0L;
  }

  public void toLog()
  {
    Logger.d("***********************LoginInfo***********************");
    Logger.d("desc: " + this.desc);
    Logger.d("open_id: " + this.open_id);
    Logger.d("pf: " + this.pf);
    Logger.d("pf_key: " + this.pf_key);
    Logger.d("user_id: " + this.user_id);
    Logger.d("flag: " + this.flag);
    Logger.d("platform: " + this.platform);
    int i = 0;
    if (i < this.token.size())
    {
      String str;
      switch (((TokenRet)this.token.get(i)).type)
      {
      default:
        str = "errorType";
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        Logger.d(str + ":" + ((TokenRet)this.token.get(i)).value);
        Logger.d(str + ":" + ((TokenRet)this.token.get(i)).expiration);
        i++;
        break;
        str = "eToken_QQ_Access";
        continue;
        str = "eToken_QQ_Pay";
        continue;
        str = "eToken_WX_Access";
        continue;
        str = "eToken_WX_Code";
        continue;
        str = "eToken_WX_Refresh";
      }
    }
    Logger.d("***********************LoginInfo***********************");
  }

  public String toString()
  {
    toLog();
    return "LoginRet";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.api.LoginRet
 * JD-Core Version:    0.6.0
 */