package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.ShareRet;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.weixin.MsgWechatWrapper;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class SendMessageToWechatGameCenter extends RemoteApiBase
{
  private static final String EVENT_NAME = "sendMsgToWxGameCenter";
  private static final String PATH = "/share/wxgame/";
  private String mMsdkExtInfo = "";

  public SendMessageToWechatGameCenter(MsgWechatWrapper paramMsgWechatWrapper, String paramString)
  {
    this.jsonBody = paramMsgWechatWrapper;
    this.mMsdkExtInfo = paramString;
  }

  private void callback(int paramInt, String paramString1, String paramString2)
  {
    ShareRet localShareRet = new ShareRet();
    localShareRet.flag = paramInt;
    localShareRet.desc = paramString1;
    localShareRet.platform = WeGame.WXPLATID;
    localShareRet.extInfo = paramString2;
    WeGameNotifyGame.getInstance().OnBackendShareCallback(localShareRet);
  }

  protected String getExtUrlParams()
  {
    return "msdkExtInfo=" + URLEncoder.encode(this.mMsdkExtInfo);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.ShareWeChatGameCenter.ordinal();
  }

  protected String getPath()
  {
    return "/share/wxgame/";
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("ShareWeChatGameCenter failed");
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    Logger.d("onFailure " + paramString + " statusCode: " + paramInt1);
    callback(-1, paramString, this.mMsdkExtInfo);
    reportEventToBeacon("sendMsgToWxGameCenter", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("ShareWeChatGameCenter success");
    int i = -1;
    String str1 = "";
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
    {
      Logger.d("ShareWeChatGameCenter onsuccess response data is null");
      reportEventToBeacon("sendMsgToWxGameCenter", false, 1002, false);
      return;
    }
    Logger.d("onSuccess " + paramString);
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      int j = localSafeJSONObject.getInt("ret");
      if (j == 0)
      {
        i = 0;
        reportEventToBeacon("sendMsgToWxGameCenter", true, 0, false);
      }
      while (true)
      {
        str1 = localSafeJSONObject.getString("msdkExtInfo");
        if (str1 != null)
          str1 = URLDecoder.decode(str1);
        Logger.d("extInfo: " + str1);
        String str2 = getNetDesc(localSafeJSONObject, getClass().getName());
        callback(i, str2, str1);
        return;
        Logger.d("ShareWeChatGameCenter onsuccess, ret:" + j);
        reportEventToBeacon("sendMsgToWxGameCenter", false, j, true);
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      Logger.d("json error(ShareToWX): " + paramString + " statusCode: " + paramInt1);
      reportEventToBeacon("sendMsgToWxGameCenter", false, 1001, false);
      return;
    }
    finally
    {
      callback(i, "unknows onSuccess", str1);
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.SendMessageToWechatGameCenter
 * JD-Core Version:    0.6.0
 */