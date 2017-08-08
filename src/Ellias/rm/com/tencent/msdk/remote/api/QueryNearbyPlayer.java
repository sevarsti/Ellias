package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.lbs.LocationInfo;
import com.tencent.msdk.tools.Logger;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

class QueryNearbyPlayer extends RemoteApiBase
{
  private static final String EVENT_NAME = "getNearbyPlayer";
  private static final String PATH = "/relation/nearby/";
  private LocationInfo location = new LocationInfo();

  public QueryNearbyPlayer(LocationInfo paramLocationInfo)
  {
    this.location = paramLocationInfo;
    try
    {
      this.jsonBody.put("location", this.location);
      return;
    }
    catch (JSONException localJSONException)
    {
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
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      localRelationRet.platform = localLoginRet.platform;
      WeGameNotifyGame.getInstance().OnGetNearbyPlayerCallback(localRelationRet);
      return;
    }
  }

  protected int getMyId()
  {
    return RemoteApiWhat.QueryNearbyPlayer.ordinal();
  }

  protected String getPath()
  {
    return "/relation/nearby/";
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
    reportEventToBeacon("getNearbyPlayer", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    Vector localVector = new Vector();
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
    {
      Logger.d("getNearbyPlayer onsuccess response data is null");
      reportEventToBeacon("getNearbyPlayer", false, 1002, false);
      return;
    }
    String str = "";
    int i;
    while (true)
    {
      SafeJSONObject localSafeJSONObject;
      LoginRet localLoginRet;
      try
      {
        str = getNetDesc(new SafeJSONObject(paramString), getClass().getName());
        localSafeJSONObject = new SafeJSONObject(paramString);
        i = localSafeJSONObject.getInt("ret");
        if (i != 0)
          break;
        reportEventToBeacon("getNearbyPlayer", true, 0, false);
        localLoginRet = new LoginRet();
        WGPlatform.WGGetLoginRecord(localLoginRet);
        if (localLoginRet.platform == WeGame.QQPLATID)
        {
          localVector = QQInfoFormatter.formatNearby(localSafeJSONObject.getJSONArray("lists"));
          callback(0, str, localVector);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        reportEventToBeacon("getNearbyPlayer", false, 1001, false);
        Logger.d("decode json from server failed!");
        localJSONException.printStackTrace();
        callback(-1, str, localVector);
        return;
      }
      if (localLoginRet.platform != WeGame.WXPLATID)
        continue;
      localVector = WxInfoFormatter.formatNearby(localSafeJSONObject.getJSONArray("lists"));
    }
    Logger.d("getNearbyPlayer onsuccess, ret:" + i);
    reportEventToBeacon("getNearbyPlayer", false, i, true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.QueryNearbyPlayer
 * JD-Core Version:    0.6.0
 */