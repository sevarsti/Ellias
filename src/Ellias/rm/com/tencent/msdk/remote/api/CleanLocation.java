package com.tencent.msdk.remote.api;

import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.tools.Logger;

public class CleanLocation extends RemoteApiBase
{
  private static final String EVENT_NAME = "cleanLocation";
  private static final String PATH = "/relation/clear_location/";

  private void callback(int paramInt, String paramString)
  {
    RelationRet localRelationRet = new RelationRet();
    localRelationRet.flag = paramInt;
    localRelationRet.desc = paramString;
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    localRelationRet.platform = localLoginRet.platform;
    WeGameNotifyGame.getInstance().OnClearLocationCallback(localRelationRet);
  }

  protected int getMyId()
  {
    return RemoteApiWhat.CleanLocation.ordinal();
  }

  protected String getPath()
  {
    return "/relation/clear_location/";
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    if (paramString == null)
      paramString = "";
    callback(-1, paramString);
    reportEventToBeacon("cleanLocation", false, paramInt1, false);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 != getMyId())
    {
      Logger.d("a wrong callback");
      return;
    }
    reportEventToBeacon("cleanLocation", true, 0, false);
    if (paramString == null)
      paramString = "";
    callback(0, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.CleanLocation
 * JD-Core Version:    0.6.0
 */