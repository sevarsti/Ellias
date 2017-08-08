package com.tencent.msdk.push.req;

import com.tencent.msdk.SimpleCallback;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.PushHelper;
import com.tencent.msdk.push.PushHelper.MatIdCallback;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.push.db.PushClientDbModel.Entry;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.tools.DeviceUtils;
import com.tencent.msdk.tools.Logger;
import org.json.JSONException;

public class ClientRegReq extends BaseReq
  implements SimpleCallback
{
  private static final int sRegSuccess;
  private SimpleCallback mCallback;
  private String mMatId = "";
  private String mOpenId = "";
  private String mPushAppId = "";

  public ClientRegReq(String paramString)
  {
    this.mPushAppId = paramString;
  }

  public ClientRegReq(String paramString1, String paramString2)
  {
    this.mPushAppId = paramString1;
    this.mOpenId = paramString2;
  }

  private void initJsonBody(String paramString)
  {
    SafeJSONObject localSafeJSONObject = new SafeJSONObject();
    try
    {
      localSafeJSONObject.put("62", "20005");
      localSafeJSONObject.put("1", this.mPushAppId);
      localSafeJSONObject.put("2", this.mOpenId);
      localSafeJSONObject.put("3", paramString);
      localSafeJSONObject.put("4", "a");
      localSafeJSONObject.put("5", DeviceUtils.getVersionRelease());
      localSafeJSONObject.put("6", DeviceUtils.getBrand());
      localSafeJSONObject.put("7", DeviceUtils.getScreenResolution(WeGame.getInstance().getActivity()));
      localSafeJSONObject.put("8", "" + DeviceUtils.getNetworkType(PushHelper.getContext()));
      localSafeJSONObject.put("9", WGPlatform.WGGetVersion());
      localSafeJSONObject.put("10", "");
      localSafeJSONObject.put("60", "1.0");
      this.jsonBody = localSafeJSONObject;
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  private void superSendProxy()
  {
    super.send();
  }

  protected String getCustomDomain()
  {
    return ConfigManager.getPushMsgDomain();
  }

  protected int getMyId()
  {
    return MsgId.ClientRegReq.ordinal();
  }

  public void onFail()
  {
    if (this.mCallback != null)
      this.mCallback.onFail();
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onFailure");
    onFail();
  }

  public void onSuccess()
  {
    if (this.mCallback != null)
      this.mCallback.onSuccess();
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onSuccess");
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      int i = localSafeJSONObject.getInt("51");
      String str1 = localSafeJSONObject.getString("52");
      String str2 = localSafeJSONObject.getString("37");
      String str3 = localSafeJSONObject.getString("40");
      Logger.d(str1);
      Logger.d(str2);
      Logger.d(str3);
      if (i == 0)
      {
        PushClientDbModel localPushClientDbModel = new PushClientDbModel();
        localPushClientDbModel.deleteAll();
        localPushClientDbModel.mData.setmMatId(this.mMatId);
        localPushClientDbModel.mData.setmMatKey(str2);
        localPushClientDbModel.mData.setmMatKeyVersion(str3);
        localPushClientDbModel.mData.setmMatKeyCreateAt(System.currentTimeMillis());
        localPushClientDbModel.mData.setmLastMsgId("0");
        localPushClientDbModel.mData.setmResolution(DeviceUtils.getScreenResolution(WeGame.getInstance().getActivity()));
        localPushClientDbModel.create();
        onSuccess();
        return;
      }
      onFail();
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.w("decode json from server failed!");
      localJSONException.printStackTrace();
      onFail();
    }
  }

  public void send()
  {
    PushHelper.reqMatid(new PushHelper.MatIdCallback()
    {
      public void onSuccess(String paramString)
      {
        Logger.d("onSuccess");
        ClientRegReq.access$002(ClientRegReq.this, paramString);
        ClientRegReq.this.initJsonBody(ClientRegReq.this.mMatId);
        ClientRegReq.this.superSendProxy();
      }

      public void onTimeout()
      {
        Logger.d("onTimeout");
        ClientRegReq.access$002(ClientRegReq.this, "");
        ClientRegReq.this.initJsonBody(ClientRegReq.this.mMatId);
        ClientRegReq.this.superSendProxy();
      }
    });
  }

  public void setmCallback(SimpleCallback paramSimpleCallback)
  {
    this.mCallback = paramSimpleCallback;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.ClientRegReq
 * JD-Core Version:    0.6.0
 */