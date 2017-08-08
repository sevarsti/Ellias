package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyAddPostReq;
import CobraHallQmiProto.TBodyAddPostRsp;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;

public class FeedbackRequest extends QMiProtocolRequest
{
  private static final String y = "FeedbackRequest";

  public FeedbackRequest(Handler paramHandler, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    super(113, paramHandler, paramInt, new Object[] { paramString1, paramString2, paramString3 });
  }

  public Class getResponseClass()
  {
    return TBodyAddPostRsp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("FeedbackRequest", "request feedback info failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("FeedbackRequest", "request feedback info success");
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getBusiResponse());
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyAddPostReq localTBodyAddPostReq = new TBodyAddPostReq();
    localTBodyAddPostReq.title = ((String)paramArrayOfObject[0]);
    localTBodyAddPostReq.text = ((String)paramArrayOfObject[1]);
    localTBodyAddPostReq.info = ((String)paramArrayOfObject[2]);
    return localTBodyAddPostReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.FeedbackRequest
 * JD-Core Version:    0.6.0
 */