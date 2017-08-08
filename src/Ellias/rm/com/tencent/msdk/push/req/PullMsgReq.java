package com.tencent.msdk.push.req;

import com.tencent.msdk.WeGame;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.PushHelper;
import com.tencent.msdk.push.PushHelper.MatIdCallback;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.remote.api.SafeJSONObject;
import com.tencent.msdk.tools.DeviceUtils;
import com.tencent.msdk.tools.Logger;
import org.json.JSONArray;
import org.json.JSONException;

public class PullMsgReq extends BaseReq
{
  private static final int RSP_ERROR_CODE_SUCCESS;
  private Callback mCallback = null;

  public PullMsgReq(Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }

  private void initJsonBody(String paramString)
  {
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject();
      localSafeJSONObject.put("62", "20007");
      Logger.d("matId: " + paramString);
      localSafeJSONObject.put("3", paramString);
      localSafeJSONObject.put("37", PushClientDbModel.getMatKey());
      localSafeJSONObject.put("3", paramString);
      localSafeJSONObject.put("4", "a");
      localSafeJSONObject.put("5", DeviceUtils.getVersionRelease());
      localSafeJSONObject.put("6", DeviceUtils.getBrand());
      localSafeJSONObject.put("7", "");
      localSafeJSONObject.put("8", "" + DeviceUtils.getNetworkType(WeGame.getInstance().getActivity()));
      localSafeJSONObject.put("9", "");
      localSafeJSONObject.put("10", "");
      localSafeJSONObject.put("38", PushClientDbModel.getLastMsgId());
      localSafeJSONObject.put("40", "1");
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

  public void bubbleOnFail()
  {
    if (this.mCallback != null)
      this.mCallback.onFail();
  }

  public void bubbleOnSuccess(JSONArray paramJSONArray)
  {
    if (this.mCallback != null)
      this.mCallback.onSuccess(paramJSONArray);
  }

  protected String getCustomDomain()
  {
    return ConfigManager.getPushMsgDomain();
  }

  protected int getMyId()
  {
    return MsgId.PullMsgReq.ordinal();
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    bubbleOnFail();
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      SafeJSONObject localSafeJSONObject = new SafeJSONObject(paramString);
      int i = Integer.parseInt(localSafeJSONObject.getString("51"));
      String str1 = localSafeJSONObject.getString("52");
      JSONArray localJSONArray = localSafeJSONObject.getJSONArray("22");
      String str2 = localSafeJSONObject.getString("40");
      Logger.d(str1);
      Logger.d(localJSONArray);
      Logger.d(str2);
      if (i == 0)
      {
        Logger.d("called");
        bubbleOnSuccess(localJSONArray);
        return;
      }
      bubbleOnFail();
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
        PullMsgReq.this.initJsonBody(paramString);
        PullMsgReq.this.superSendProxy();
      }

      public void onTimeout()
      {
        Logger.d("onTimeout");
        PullMsgReq.this.initJsonBody("");
        PullMsgReq.this.superSendProxy();
      }
    });
  }

  public static abstract interface Callback
  {
    public abstract void onFail();

    public abstract void onSuccess(JSONArray paramJSONArray);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.PullMsgReq
 * JD-Core Version:    0.6.0
 */