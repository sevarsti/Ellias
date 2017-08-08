package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyGetUserInfoV2Resp;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;

public class UserInfoRequest extends QMiProtocolRequest
{
  private static final String y = "UserInfoRequest";

  public UserInfoRequest(Handler paramHandler, int paramInt)
  {
    super(300, paramHandler, paramInt, new Object[0]);
    setNeedLoginStatus(true);
    setNeedDeviceInfo(true);
  }

  public Class getResponseClass()
  {
    return TBodyGetUserInfoV2Resp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("UserInfoRequest", "request user info failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("UserInfoRequest", "request user info success");
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getBusiResponse());
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.UserInfoRequest
 * JD-Core Version:    0.6.0
 */