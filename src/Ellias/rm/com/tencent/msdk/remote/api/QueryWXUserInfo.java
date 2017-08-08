package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.tools.Logger;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class QueryWXUserInfo extends RemoteApiBase
{
  private static final String EVENT_NAME = "queryWXUserInfo";
  private static final String PATH = "/relation/wxprofile";

  public QueryWXUserInfo()
  {
    try
    {
      this.jsonBody.put("accessToken", this.accessToken);
      JSONArray localJSONArray = new JSONArray();
      localJSONArray.put(this.openId);
      this.jsonBody.put("openids", localJSONArray);
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  private void callback(int paramInt, String paramString, PersonInfo paramPersonInfo)
  {
    RelationRet localRelationRet = new RelationRet();
    localRelationRet.flag = paramInt;
    localRelationRet.desc = paramString;
    Vector localVector = new Vector();
    if (paramPersonInfo != null)
      localVector.add(paramPersonInfo);
    localRelationRet.persons = localVector;
    localRelationRet.platform = WeGame.WXPLATID;
    WeGameNotifyGame.getInstance().OnBackendRelationCallback(localRelationRet);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.QueryWXMyInfo.ordinal();
  }

  protected String getPath()
  {
    return "/relation/wxprofile";
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    Logger.d("onFailure " + paramString + " statusCode: " + paramInt1);
    callback(-1, paramString, null);
    reportEventToBeacon("queryWXUserInfo", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    int i = -1;
    String str = " unknow error onSuccess";
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
    {
      Logger.d("queryWXUserInfo onsuccess response data is null");
      reportEventToBeacon("queryWXUserInfo", false, 1002, false);
      return;
    }
    Logger.d("onSuccess " + paramString);
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      str = getNetDesc(localSafeJSONObject, getClass().getName());
      int j = localSafeJSONObject.getInt("ret");
      PersonInfo localPersonInfo;
      if (j == 0)
      {
        reportEventToBeacon("queryWXUserInfo", true, 0, false);
        localPersonInfo = (PersonInfo)WxInfoFormatter.formatFriends(localSafeJSONObject.getJSONArray("lists")).get(0);
        i = 0;
      }
      while (true)
      {
        callback(i, str, localPersonInfo);
        return;
        Logger.d("queryWXUserInfo onsuccess, ret:" + j);
        reportEventToBeacon("queryWXUserInfo", false, j, true);
        localPersonInfo = null;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      Logger.d("json error(QueryWxMyInfo): " + paramString + " statusCode: " + paramInt1);
      reportEventToBeacon("queryWXUserInfo", false, 1001, false);
      return;
    }
    finally
    {
      callback(i, str, null);
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.QueryWXUserInfo
 * JD-Core Version:    0.6.0
 */