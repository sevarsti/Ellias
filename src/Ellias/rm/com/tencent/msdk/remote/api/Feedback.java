package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;
import org.json.JSONException;
import org.json.JSONObject;

class Feedback extends RemoteApiBase
{
  private static final String EVENT_NAME = "feedback";
  private static final String PATH = "/feedback/views/";

  public Feedback(String paramString)
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    if (localLoginRet.platform == 0)
    {
      Logger.e("Feedback called? but not login record");
      return;
    }
    try
    {
      this.jsonBody.put("gameID", WeGame.getInstance().qq_appid);
      this.jsonBody.put("openID", localLoginRet.open_id);
      this.jsonBody.put("question", paramString);
      this.jsonBody.put("device", "android");
      this.jsonBody.put("platID", localLoginRet.platform);
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.d("JSONException");
      localJSONException.printStackTrace();
    }
  }

  private void notifyGame(int paramInt, String paramString)
  {
    WeGameNotifyGame.getInstance().OnFeedbackNotify(paramInt, paramString);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.Feedback.ordinal();
  }

  protected String getPath()
  {
    return "/feedback/views/";
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = ("" + paramString);
    notifyGame(0, String.format("errorCode: %d; errorContent: %s", arrayOfObject));
    Logger.d("onFailure " + paramString + " statusCode: " + paramInt1);
    reportEventToBeacon("feedback", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = ("" + paramString);
    notifyGame(0, String.format("errorCode: %d; errorContent: %s", arrayOfObject));
    Logger.d("onSuccess " + paramString + " statusCode: " + paramInt1);
    reportEventToBeacon("feedback", true, 0, false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.Feedback
 * JD-Core Version:    0.6.0
 */