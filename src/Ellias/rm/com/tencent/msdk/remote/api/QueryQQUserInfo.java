package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.tools.Logger;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

class QueryQQUserInfo extends RemoteApiBase
{
  private static final String EVENT_NAME = "queryQQUserInfo";
  private static final String PATH = "/relation/qqprofile";

  public QueryQQUserInfo()
  {
    try
    {
      this.jsonBody.put("appid", this.appId);
      this.jsonBody.put("accessToken", this.accessToken);
      this.jsonBody.put("openid", this.openId);
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.d("JsonExcption in QueryQQMyInfo");
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
    localRelationRet.platform = WeGame.QQPLATID;
    WeGameNotifyGame.getInstance().OnBackendRelationCallback(localRelationRet);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.QueryQQMyInfo.ordinal();
  }

  protected String getPath()
  {
    return "/relation/qqprofile";
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
    reportEventToBeacon("queryQQUserInfo", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    PersonInfo localPersonInfo = null;
    int i = -1;
    String str = " unknow error onSuccess";
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
    {
      Logger.d("queryQQUserInfo onsuccess response data is null");
      reportEventToBeacon("queryQQUserInfo", false, 1002, false);
      return;
    }
    Logger.d("onSuccess " + paramString);
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(new String(paramString));
      str = getNetDesc(localSafeJSONObject, getClass().getName());
      int j = localSafeJSONObject.getInt("ret");
      localPersonInfo = null;
      if (j == 0)
      {
        localPersonInfo = QQInfoFormatter.formatMyInfo(this.openId, new SafeJSONObject(paramString));
        reportEventToBeacon("queryQQUserInfo", true, 0, false);
        i = 0;
      }
      while (true)
      {
        return;
        Logger.d("queryQQUserInfo onsuccess, ret:" + j);
        reportEventToBeacon("queryQQUserInfo", false, j, true);
        localPersonInfo = null;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      Logger.d("json error(QueryQQMyInfo): " + paramString + " statusCode: " + paramInt1);
      reportEventToBeacon("queryQQUserInfo", false, 1001, false);
      return;
    }
    finally
    {
      callback(i, str, localPersonInfo);
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.QueryQQUserInfo
 * JD-Core Version:    0.6.0
 */