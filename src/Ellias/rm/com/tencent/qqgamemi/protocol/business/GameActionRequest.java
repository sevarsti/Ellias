package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyReportGameActionV3Req;
import CobraHallQmiProto.TBodyReportGameActionV3Rsp;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;

public class GameActionRequest extends QMiProtocolRequest
{
  public static final int A = 2;
  private static final String B = GameActionRequest.class.getSimpleName();
  public static final int y = 0;
  public static final int z = 1;

  public GameActionRequest(Handler paramHandler, int paramInt, Object[] paramArrayOfObject)
  {
    super(114, paramHandler, paramInt, paramArrayOfObject);
    setNeedDeviceInfo(true);
    setNeedLoginStatus(true);
  }

  public Class getResponseClass()
  {
    return TBodyReportGameActionV3Rsp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), Integer.valueOf(0));
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TBodyReportGameActionV3Rsp localTBodyReportGameActionV3Rsp = (TBodyReportGameActionV3Rsp)paramProtocolResponse.getBusiResponse();
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), Integer.valueOf(localTBodyReportGameActionV3Rsp.nextReportTimeInterval));
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyReportGameActionV3Req localTBodyReportGameActionV3Req = new TBodyReportGameActionV3Req();
    localTBodyReportGameActionV3Req.gamePackageName = ((String)paramArrayOfObject[0]);
    localTBodyReportGameActionV3Req.gameActionType = ((Integer)paramArrayOfObject[1]).intValue();
    return localTBodyReportGameActionV3Req;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.GameActionRequest
 * JD-Core Version:    0.6.0
 */