package com.tencent.msdk.permission;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.db.PermissionModel;
import com.tencent.msdk.remote.api.SafeJSONObject;
import org.json.JSONException;
import org.json.JSONObject;

public class PermissionManage
{
  private static volatile PermissionManage instance = null;
  public static final String skipWhitelist = "SkipWhitelist";
  public static final String wgLoginQQ = "WGLoginQQ";
  public static final String wgLoginWX = "WGLoginWX";
  public static final String wgRefreshWXToken = "WGRefreshWXToken";
  public static final String wgSendToQQ = "WGSendToQQ";
  public static final String wgSendToWeixin = "WGSendToWeixin";
  public static final String wgSendToWeixinWithPhoto = "WGSendToWeixinWithPhoto";
  private PermissionModel model = new PermissionModel();
  private JSONObject permissionJson = null;

  public static PermissionManage getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new PermissionManage();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void readPermissionFromDb()
  {
    this.model.getRecord();
    setPermission(this.model.permission);
  }

  private void saveDefaultData()
  {
    this.model.permission = "{ \"WGLoginQQ\" : 0,\"WGLoginWX\" : 0 ,\"WGSendToQQ\" : 0 ,\"WGSendToWeixin\" : 0 ,\"WGSendToWeixinWithPhoto\" : 0 ,\"WGRefreshWXToken\" : 0 }";
    this.model.qqAppId = WeGame.getInstance().qq_appid;
    this.model.wxAppId = WeGame.getInstance().wx_appid;
    this.model.firstTimeSave();
  }

  private void savePermissionToDb(String paramString)
  {
    if (paramString == null)
      return;
    this.model.permission = paramString;
    this.model.qqAppId = WeGame.getInstance().qq_appid;
    this.model.wxAppId = WeGame.getInstance().wx_appid;
    this.model.save();
  }

  private void setPermission(String paramString)
  {
    if (paramString != null);
    try
    {
      this.permissionJson = new SafeJSONObject(paramString);
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  public void init()
  {
    saveDefaultData();
    readPermissionFromDb();
  }

  public boolean isHavePermission(String paramString)
  {
    try
    {
      if ((this.permissionJson != null) && (this.permissionJson.has(paramString)))
      {
        int i = this.permissionJson.getInt(paramString);
        if (1 == i)
          return true;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return false;
    }
    return false;
  }

  public void updateDataFromNet(String paramString)
  {
    savePermissionToDb(paramString);
    setPermission(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.permission.PermissionManage
 * JD-Core Version:    0.6.0
 */