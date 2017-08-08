package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.tools.Logger;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

class QueryWXFriends extends RemoteApiBase
{
  private static final String EVENT_NAME = "queryWXGameFriendsInfo";
  private static final String PATH = "/relation/wxfriends_profile/";

  public QueryWXFriends()
  {
    try
    {
      this.jsonBody.put("openid", this.openId);
      this.jsonBody.put("accessToken", this.accessToken);
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.d("JSONException");
      localJSONException.printStackTrace();
    }
  }

  private void callback(int paramInt, String paramString, Vector<PersonInfo> paramVector)
  {
    RelationRet localRelationRet = new RelationRet();
    localRelationRet.flag = paramInt;
    localRelationRet.desc = paramString;
    if (paramVector != null);
    for (localRelationRet.persons = paramVector; ; localRelationRet.persons = new Vector())
    {
      localRelationRet.platform = WeGame.WXPLATID;
      WeGameNotifyGame.getInstance().OnBackendRelationCallback(localRelationRet);
      return;
    }
  }

  protected int getMyId()
  {
    return RemoteApiWhat.QueryWXFriends.ordinal();
  }

  protected String getPath()
  {
    return "/relation/wxfriends_profile/";
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
    reportEventToBeacon("queryWXGameFriendsInfo", false, paramInt1, false);
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
      Logger.d("queryWXGameFriendsInfo onsuccess response data is null");
      reportEventToBeacon("queryWXGameFriendsInfo", false, 1002, false);
      return;
    }
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      str = getNetDesc(localSafeJSONObject, getClass().getName());
      int j = localSafeJSONObject.getInt("ret");
      Vector localVector2;
      if (j == 0)
      {
        reportEventToBeacon("queryWXGameFriendsInfo", true, 0, false);
        Vector localVector1 = WxInfoFormatter.formatFriends(localSafeJSONObject.getJSONArray("lists"));
        localVector2 = localVector1;
        i = 0;
      }
      while (true)
      {
        callback(i, str, localVector2);
        return;
        Logger.d("queryWXGameFriendsInfo onsuccess, ret:" + j);
        reportEventToBeacon("queryWXGameFriendsInfo", false, j, true);
        localVector2 = null;
      }
    }
    catch (JSONException localJSONException)
    {
      Logger.d("decode json from server failed!");
      reportEventToBeacon("queryWXGameFriendsInfo", false, 1001, false);
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
 * Qualified Name:     com.tencent.msdk.remote.api.QueryWXFriends
 * JD-Core Version:    0.6.0
 */