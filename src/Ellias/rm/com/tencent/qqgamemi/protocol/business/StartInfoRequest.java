package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyQmiStartReq;
import CobraHallQmiProto.TBodyQmiStartRsp;
import CobraHallQmiProto.TCmdTimeStampInfo;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;
import java.util.ArrayList;

public class StartInfoRequest extends QMiProtocolRequest
{
  private static final String y = "StartInfoRequest";

  public StartInfoRequest(Handler paramHandler, int paramInt, Object[] paramArrayOfObject)
  {
    super(1001, paramHandler, paramInt, paramArrayOfObject);
    setNeedDeviceInfo(true);
  }

  public Class getResponseClass()
  {
    return TBodyQmiStartRsp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("StartInfoRequest", "request start info failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("StartInfoRequest", "request start info success");
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getBusiResponse());
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyQmiStartReq localTBodyQmiStartReq = new TBodyQmiStartReq();
    localTBodyQmiStartReq.cmdTimestampInfos = new ArrayList();
    int i = ((Integer)paramArrayOfObject[0]).intValue();
    localTBodyQmiStartReq.cmdTimestampInfos.add(new TCmdTimeStampInfo(1001, i));
    return localTBodyQmiStartReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.StartInfoRequest
 * JD-Core Version:    0.6.0
 */