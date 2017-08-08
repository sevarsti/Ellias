package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.ShareRet;
import com.tencent.msdk.tools.Logger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

class ShareToWX extends RemoteApiBase
{
  private static final String EVENT_NAME = "shareToWx";
  private static final String PATH = "/share/wx/";
  private String mMsdkExtInfo = "";

  public ShareToWX(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    try
    {
      this.jsonBody.put("fopenid", paramString1);
      this.jsonBody.put("title", paramString2);
      this.jsonBody.put("description", paramString3);
      this.jsonBody.put("extinfo", paramString4);
      this.jsonBody.put("media_tag_name", paramString5);
      this.jsonBody.put("thumb_media_id", paramString6);
      this.jsonBody.put("msdkExtInfo", paramString7);
      this.mMsdkExtInfo = paramString7;
      Logger.d(this.mMsdkExtInfo);
      this.jsonBody.put("openid", this.openId);
      this.jsonBody.put("access_token", this.accessToken);
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.d("JSONException ShareToWx<init>");
      localJSONException.printStackTrace();
    }
  }

  private void callback(int paramInt, String paramString1, String paramString2)
  {
    ShareRet localShareRet = new ShareRet();
    localShareRet.flag = paramInt;
    localShareRet.desc = paramString1;
    localShareRet.platform = WeGame.WXPLATID;
    localShareRet.extInfo = paramString2;
    Logger.d(this.mMsdkExtInfo);
    WeGameNotifyGame.getInstance().OnBackendShareCallback(localShareRet);
  }

  protected String getExtUrlParams()
  {
    return "msdkExtInfo=" + URLEncoder.encode(this.mMsdkExtInfo);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.ShareToWx.ordinal();
  }

  protected String getPath()
  {
    return "/share/wx/";
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    Logger.d("onFailure " + paramString + " statusCode: " + paramInt1);
    callback(-1, paramString, this.mMsdkExtInfo);
    reportEventToBeacon("shareToWx", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    int i = -1;
    Object localObject1 = "unknows onSuccess";
    String str1 = "";
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
    {
      Logger.d("shareToWX onsuccess response data is null");
      reportEventToBeacon("shareToWx", false, 1002, false);
      return;
    }
    Logger.d("onSuccess " + paramString);
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      int j;
      if (localSafeJSONObject != null)
      {
        j = localSafeJSONObject.getInt("ret");
        if (j != 0)
          break label183;
        i = 0;
        reportEventToBeacon("shareToWx", true, 0, false);
      }
      while (true)
      {
        str1 = localSafeJSONObject.getString("msdkExtInfo");
        if (str1 != null)
          str1 = URLDecoder.decode(str1);
        Logger.d("extInfo: " + str1);
        String str2 = getNetDesc(localSafeJSONObject, getClass().getName());
        localObject1 = str2;
        return;
        label183: Logger.d("shareToWx onsuccess, ret:" + j);
        reportEventToBeacon("shareToWx", false, j, true);
      }
    }
    catch (JSONException localJSONException)
    {
      reportEventToBeacon("shareToWx", false, 1001, false);
      localJSONException.printStackTrace();
      Logger.d("json error(ShareToWX): " + paramString + " statusCode: " + paramInt1);
      return;
    }
    finally
    {
      callback(i, (String)localObject1, str1);
    }
    throw localObject2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.ShareToWX
 * JD-Core Version:    0.6.0
 */