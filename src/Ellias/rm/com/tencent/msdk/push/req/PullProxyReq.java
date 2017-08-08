package com.tencent.msdk.push.req;

import android.content.Context;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.push.PushHelper;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.tools.DeviceUtils;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;

public class PullProxyReq extends BaseReq
{
  private String body = "";
  private Callback mCallback;

  private void initJsonBody(String paramString)
  {
    Context localContext = PushHelper.getContext();
    this.body = ("20001," + paramString + "," + DeviceUtils.getNetworkType(localContext));
  }

  private boolean isMsgReady(String paramString)
  {
    if (T.ckIsEmpty(paramString))
      return false;
    return "1".equals(paramString.split(",")[0].trim());
  }

  private void superSendProxy()
  {
    super.send(this.body);
  }

  protected String getCustomDomain()
  {
    return ConfigManager.getPushPollingDomain();
  }

  protected int getMyId()
  {
    return MsgId.PullProxyReq.ordinal();
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    if (this.mCallback != null)
      this.mCallback.onFail(paramString);
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("netContent: " + paramString);
    if (this.mCallback != null)
    {
      if (isMsgReady(paramString))
      {
        this.mCallback.onMsgReady(paramString);
        return;
      }
    }
    else
    {
      Logger.d("no Callback set");
      return;
    }
    this.mCallback.onNoMsg(paramString);
  }

  public void send()
  {
    String str = PushClientDbModel.getMatKey();
    if (!T.ckIsEmpty(str))
    {
      initJsonBody(str);
      superSendProxy();
      return;
    }
    Logger.d("matKey Empty");
  }

  public void setmCallback(Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }

  public static abstract interface Callback
  {
    public abstract void onFail(String paramString);

    public abstract void onMsgReady(String paramString);

    public abstract void onNoMsg(String paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.PullProxyReq
 * JD-Core Version:    0.6.0
 */