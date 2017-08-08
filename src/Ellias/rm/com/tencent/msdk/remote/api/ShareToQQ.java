package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.ShareRet;
import com.tencent.msdk.tools.Logger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareToQQ extends RemoteApiBase
{
  private static final String EVENT_NAME = "shareToQQ";
  private static final String PATH = "/share/qq";
  private String mMsdkExtInfo = "";

  public ShareToQQ(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    Logger.d("act" + paramInt);
    Logger.d("friendOpenId" + paramString1);
    Logger.d("title" + paramString2);
    Logger.d("summary" + paramString3);
    Logger.d("previewText" + paramString4);
    Logger.d("target_url" + paramString5);
    Logger.d("image_url" + paramString6);
    Logger.d("game_tag" + paramString7);
    try
    {
      this.jsonBody.put("summary", paramString3);
      this.jsonBody.put("target_url", paramString5);
      this.jsonBody.put("image_url", paramString6);
      this.jsonBody.put("title", paramString2);
      JSONArray localJSONArray = new JSONArray();
      SafeJSONObject localSafeJSONObject = new SafeJSONObject();
      localSafeJSONObject.put("openid", paramString1);
      localSafeJSONObject.put("type", 0);
      localJSONArray.put(localSafeJSONObject);
      this.jsonBody.put("fopenids", localJSONArray.toString());
      this.jsonBody.put("previewText", paramString4);
      this.jsonBody.put("game_tag", paramString7);
      this.jsonBody.put("msdkExtInfo", paramString8);
      this.mMsdkExtInfo = paramString8;
      this.jsonBody.put("oauth_consumer_key", WeGame.getInstance().qq_appid);
      this.jsonBody.put("dst", "1001");
      this.jsonBody.put("flag", "1");
      this.jsonBody.put("access_token", this.accessToken);
      this.jsonBody.put("src", "0");
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  private void callback(int paramInt, String paramString1, String paramString2)
  {
    ShareRet localShareRet = new ShareRet();
    localShareRet.flag = paramInt;
    localShareRet.desc = paramString1;
    localShareRet.platform = WeGame.QQPLATID;
    localShareRet.extInfo = paramString2;
    WeGameNotifyGame.getInstance().OnBackendShareCallback(localShareRet);
  }

  protected String getExtUrlParams()
  {
    return "msdkExtInfo=" + URLEncoder.encode(this.mMsdkExtInfo);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.ShareToQQ.ordinal();
  }

  protected String getPath()
  {
    return "/share/qq";
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    Logger.d("onFailure " + paramString + " statusCode: " + paramInt1);
    reportEventToBeacon("shareToQQ", false, paramInt1, false);
    callback(-1, paramString, this.mMsdkExtInfo);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    int i = -1;
    String str1 = "";
    Object localObject1 = "unknows onSuccess";
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
    {
      Logger.d("shareToQQ onsuccess response data is null");
      reportEventToBeacon("shareToQQ", false, 1002, false);
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
        reportEventToBeacon("shareToQQ", true, 0, false);
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
        label183: Logger.d("shareToQQ onsuccess, ret:" + j);
        reportEventToBeacon("shareToQQ", false, j, true);
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      Logger.d("json error(ShareToQQ): " + paramString + " statusCode: " + paramInt1);
      reportEventToBeacon("shareToQQ", false, 1001, false);
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
 * Qualified Name:     com.tencent.msdk.remote.api.ShareToQQ
 * JD-Core Version:    0.6.0
 */