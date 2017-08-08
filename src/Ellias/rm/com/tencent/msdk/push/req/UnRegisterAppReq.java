package com.tencent.msdk.push.req;

import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.tools.Logger;

public class UnRegisterAppReq extends BaseReq
{
  protected String getCustomDomain()
  {
    return ConfigManager.getPushMsgDomain();
  }

  protected int getMyId()
  {
    return MsgId.UnRegisterAppReq.ordinal();
  }

  public void onFailure(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onFailure");
  }

  public void onSuccess(String paramString, int paramInt1, int paramInt2)
  {
    Logger.d("onSuccess");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.req.UnRegisterAppReq
 * JD-Core Version:    0.6.0
 */