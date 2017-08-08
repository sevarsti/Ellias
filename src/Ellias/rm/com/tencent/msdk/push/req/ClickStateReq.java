package com.tencent.msdk.push.req;

import com.tencent.msdk.SimpleCallback;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.MsgEntry;
import com.tencent.msdk.push.PushHelper;
import com.tencent.msdk.push.PushHelper.MatIdCallback;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.tools.Logger;
import org.json.JSONException;

public class ClickStateReq extends BaseReq
{
  private Callback mCallback = null;
  private MsgEntry mMsg = new MsgEntry();
  private int sSuccess = 0;

  public ClickStateReq(MsgEntry paramMsgEntry)
  {
    this.mMsg = paramMsgEntry;
  }

  private void initJsonBody(String paramString)
  {
    SafeJSONObject localSafeJSONObject = new SafeJSONObject();
    try
    {
      localSafeJSONObject.put("62", "20009");
      localSafeJSONObject.put("3", paramString);
      localSafeJSONObject.put("25", "" + this.mMsg.getId());
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

  public void bubbleOnFail()
  {
    if (this.mCallback != null)
    {
      this.mCallback.onFail();
      return;
    }
    Logger.d("onFail no callback set");
  }

  public void bubbleOnSuccess()
  {
    if (this.mCallback != null)
    {
      this.mCallback.onSuccess();
      return;
    }
    Logger.d("onSuccess no callback set");
  }

  protected String getCustomDomain()
  {
    return ConfigManager.getPushMsgDomain();
  }

  protected int getMyId()
  {
    return MsgId.ClickStateReq.ordinal();
  }

  public Callback getmCallback()
  {
    return this.mCallback;
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onFailure");
    Logger.d("errorConten: " + paramString);
    Logger.d("statusCode: " + paramInt1);
    Logger.d("what: " + paramInt2);
    bubbleOnFail();
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      int i = localSafeJSONObject.getInt("51");
      Logger.d(localSafeJSONObject.getString("52"));
      if (i == this.sSuccess)
        bubbleOnSuccess();
      return;
    }
    catch (JSONException localJSONException)
    {
      Logger.w("decode json from server failed!");
      bubbleOnFail();
    }
  }

  public void send()
  {
    PushHelper.reqMatid(new PushHelper.MatIdCallback()
    {
      public void onSuccess(String paramString)
      {
        Logger.d("onSuccess");
        ClickStateReq.this.initJsonBody(paramString);
        ClickStateReq.this.superSendProxy();
      }

      public void onTimeout()
      {
        Logger.d("onTimeout");
        ClickStateReq.this.initJsonBody("");
        ClickStateReq.this.superSendProxy();
      }
    });
  }

  public void setmCallback(Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }

  public static abstract interface Callback extends SimpleCallback
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.ClickStateReq
 * JD-Core Version:    0.6.0
 */