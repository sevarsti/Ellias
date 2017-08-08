package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyGetGameExtendInfoReq;
import CobraHallQmiProto.TBodyGetGameExtendInfoRsp;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;
import java.util.ArrayList;

public class GameItemRequest extends QMiProtocolRequest
{
  private static final String y = "GameItemRequest";

  public GameItemRequest(Handler paramHandler, int paramInt, Object[] paramArrayOfObject)
  {
    super(1004, paramHandler, paramInt, paramArrayOfObject);
    setNeedDeviceInfo(true);
  }

  public Class getResponseClass()
  {
    return TBodyGetGameExtendInfoRsp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("GameItemRequest", "request gameItem failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("GameItemRequest", "request gameItem success");
    TBodyGetGameExtendInfoRsp localTBodyGetGameExtendInfoRsp = (TBodyGetGameExtendInfoRsp)paramProtocolResponse.getBusiResponse();
    ArrayList localArrayList = localTBodyGetGameExtendInfoRsp.vGameExtendInfo;
    TLog.c("GameItemRequest", "get games:" + localArrayList);
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), localTBodyGetGameExtendInfoRsp);
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyGetGameExtendInfoReq localTBodyGetGameExtendInfoReq = new TBodyGetGameExtendInfoReq();
    localTBodyGetGameExtendInfoReq.vGamePkgName = ((ArrayList)paramArrayOfObject[0]);
    return localTBodyGetGameExtendInfoReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.GameItemRequest
 * JD-Core Version:    0.6.0
 */