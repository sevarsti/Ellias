package com.tencent.msdk.weixin;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class MsgWechatWrapper extends JSONObject
{
  private String mAccessToken = "";
  private String mAppId = "";
  private BtnBase mBtn;
  private String mContent;
  private MsgBase mMsg;
  private String mTitle;
  private String mToUser;

  public MsgWechatWrapper(String paramString1, String paramString2, String paramString3, BtnBase paramBtnBase, MsgBase paramMsgBase)
  {
    this.mToUser = paramString1;
    this.mTitle = paramString2;
    this.mContent = paramString3;
    this.mBtn = paramBtnBase;
    this.mMsg = paramMsgBase;
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    if ((localLoginRet.platform == WeGame.QQPLATID) || (localLoginRet.platform == WeGame.QQHALL))
    {
      this.mAccessToken = localLoginRet.getTokenByType(1);
      this.mAppId = WeGame.getInstance().qq_appid;
    }
    do
      return;
    while (localLoginRet.platform != WeGame.WXPLATID);
    this.mAccessToken = localLoginRet.getTokenByType(3);
    this.mAppId = WeGame.getInstance().wx_appid;
  }

  public String toString()
  {
    String str1 = this.mMsg.checkParam();
    String str2 = this.mMsg.checkParam();
    if ((!T.ckIsEmpty(str1)) || (!T.ckIsEmpty(str2)))
    {
      Logger.w(str1);
      Logger.w(str2);
      return "";
    }
    try
    {
      JSONStringer localJSONStringer = new JSONStringer().object();
      localJSONStringer.key("touser").value(this.mToUser);
      localJSONStringer.key("msgtype").value(this.mMsg.mMsgType);
      localJSONStringer.key("title").value(this.mTitle);
      localJSONStringer.key("content").value(this.mContent);
      localJSONStringer.key("access_token").value(this.mAccessToken);
      localJSONStringer.key("appid").value(this.mAppId);
      localJSONStringer.key("button").value(this.mBtn.toString());
      localJSONStringer.key("type_info").value(this.mMsg.toString());
      localJSONStringer.endObject();
      String str3 = localJSONStringer.toString();
      return str3;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.MsgWechatWrapper
 * JD-Core Version:    0.6.0
 */