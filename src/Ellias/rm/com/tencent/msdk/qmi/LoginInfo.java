package com.tencent.msdk.qmi;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.consts.EPlatform;

public class LoginInfo
{
  public String accessToken = "";
  public long accessTokenExpireTime = 0L;
  public String appId = "";
  public String appKey = "";
  public String openId = "";
  public String payToken = "";
  public long payTokenExpireTime = 0L;
  public int platform = 0;
  public String refreshToken = "";
  public long refreshTokenExpireTime = 0L;

  public LoginInfo(LoginRet paramLoginRet)
  {
    this.platform = paramLoginRet.platform;
    this.openId = paramLoginRet.open_id;
    if (this.platform == EPlatform.ePlatform_QQ.val())
    {
      this.appId = WeGame.getInstance().qq_appid;
      this.appKey = WeGame.getInstance().qqAppKey;
      this.accessToken = paramLoginRet.getTokenByType(1);
      this.accessTokenExpireTime = paramLoginRet.getTokenExpireByType(1);
      this.payToken = paramLoginRet.getTokenByType(2);
      this.payTokenExpireTime = paramLoginRet.getTokenExpireByType(2);
    }
    do
      return;
    while (this.platform != EPlatform.ePlatform_Weixin.val());
    this.appId = WeGame.getInstance().wx_appid;
    this.appKey = WeGame.getInstance().wxAppKey;
    this.accessToken = paramLoginRet.getTokenByType(3);
    this.accessTokenExpireTime = paramLoginRet.getTokenExpireByType(3);
    this.refreshToken = paramLoginRet.getTokenByType(5);
    this.refreshTokenExpireTime = paramLoginRet.getTokenExpireByType(5);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("platform: " + this.platform + "; ");
    localStringBuilder.append("appId: " + this.appId + "; ");
    localStringBuilder.append("appKey: " + this.appKey + "; ");
    localStringBuilder.append("openId: " + this.openId + "; ");
    localStringBuilder.append("accessToken: " + this.accessToken + "; ");
    localStringBuilder.append("accessTokenExpireTime: " + this.accessTokenExpireTime + "; ");
    localStringBuilder.append("refreshToken: " + this.refreshToken + "; ");
    localStringBuilder.append("refreshTokenExpireTime: " + this.refreshTokenExpireTime + "; ");
    localStringBuilder.append("payToken: " + this.payToken + "; ");
    localStringBuilder.append("payTokenExpireTime: " + this.payTokenExpireTime + "; ");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.qmi.LoginInfo
 * JD-Core Version:    0.6.0
 */