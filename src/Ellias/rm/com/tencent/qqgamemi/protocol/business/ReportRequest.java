package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyReportReq;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;
import java.util.ArrayList;

public class ReportRequest extends QMiProtocolRequest
{
  private static final String y = "ReportRequest";

  public ReportRequest(Handler paramHandler, int paramInt, Object[] paramArrayOfObject)
  {
    super(100, paramHandler, paramInt, paramArrayOfObject);
    setNeedDeviceInfo(true);
    setNeedLoginStatus(false);
  }

  public Class getResponseClass()
  {
    return null;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyReportReq localTBodyReportReq = new TBodyReportReq();
    localTBodyReportReq.reportData = ((ArrayList)paramArrayOfObject[0]);
    return localTBodyReportReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.ReportRequest
 * JD-Core Version:    0.6.0
 */