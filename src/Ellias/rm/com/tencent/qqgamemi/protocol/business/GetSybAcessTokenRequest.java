package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyGetSdkSybAccessTokenReq;
import CobraHallQmiProto.TBodyGetSdkSybAccessTokenResp;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;

public class GetSybAcessTokenRequest extends QMiProtocolRequest
{
  private static final String y = "GetSybAcessTokenRequest";

  public GetSybAcessTokenRequest(Handler paramHandler, int paramInt, Object[] paramArrayOfObject)
  {
    super(1009, paramHandler, paramInt, paramArrayOfObject);
    setNeedDeviceInfo(true);
    setNeedLoginStatus(true);
  }

  public Class getResponseClass()
  {
    return TBodyGetSdkSybAccessTokenResp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("GetSybAcessTokenRequest", "request SybAcessToken failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("GetSybAcessTokenRequest", "request SybAcessToken success");
    TBodyGetSdkSybAccessTokenResp localTBodyGetSdkSybAccessTokenResp = (TBodyGetSdkSybAccessTokenResp)paramProtocolResponse.getBusiResponse();
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), localTBodyGetSdkSybAccessTokenResp);
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyGetSdkSybAccessTokenReq localTBodyGetSdkSybAccessTokenReq = new TBodyGetSdkSybAccessTokenReq();
    localTBodyGetSdkSybAccessTokenReq.appId = ((String)paramArrayOfObject[0]);
    localTBodyGetSdkSybAccessTokenReq.openId = ((String)paramArrayOfObject[1]);
    localTBodyGetSdkSybAccessTokenReq.accessToken = ((byte[])(byte[])paramArrayOfObject[2]);
    localTBodyGetSdkSybAccessTokenReq.tokenType = ((Integer)paramArrayOfObject[3]).intValue();
    return localTBodyGetSdkSybAccessTokenReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.GetSybAcessTokenRequest
 * JD-Core Version:    0.6.0
 */