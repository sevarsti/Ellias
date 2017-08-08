package com.tencent.msdk.push.req;

import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.PushHelper;
import com.tencent.msdk.push.PushHelper.MatIdCallback;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.tools.Logger;
import org.json.JSONException;

public class PushStateReq extends BaseReq
{
  private void initJsonBody(String paramString)
  {
    SafeJSONObject localSafeJSONObject = new SafeJSONObject();
    try
    {
      localSafeJSONObject.put("62", "20008");
      localSafeJSONObject.put("3", paramString);
      localSafeJSONObject.put("37", PushClientDbModel.getMatKey());
      localSafeJSONObject.put("38", PushClientDbModel.getLastMsgId());
      localSafeJSONObject.put("40", PushClientDbModel.getLastMatKeyVersion());
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
    return MsgId.PushStateReq.ordinal();
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onFailure");
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onSuccess");
  }

  public void send()
  {
    PushHelper.reqMatid(new PushHelper.MatIdCallback()
    {
      public void onSuccess(String paramString)
      {
        Logger.d("onSuccess");
        PushStateReq.this.initJsonBody(paramString);
        PushStateReq.this.superSendProxy();
      }

      public void onTimeout()
      {
        Logger.d("onTimeout");
        PushStateReq.this.initJsonBody("");
        PushStateReq.this.superSendProxy();
      }
    });
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.PushStateReq
 * JD-Core Version:    0.6.0
 */