package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyGetGameJoyDLInfoReq;
import CobraHallQmiProto.TBodyGetGameJoyDLInfoRsp;
import CobraHallQmiProto.TGameJoyDLInfo;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.business.AutoDownLoadInfo;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;

public class AutoDownloadGameJoyRequest extends QMiProtocolRequest
{
  private static final String y = AutoDownloadGameJoyRequest.class.getSimpleName();

  public AutoDownloadGameJoyRequest(Handler paramHandler, int paramInt, String paramString)
  {
    super(1005, paramHandler, paramInt, new Object[] { paramString });
    setNeedLoginStatus(true);
    setNeedDeviceInfo(true);
  }

  public Class getResponseClass()
  {
    return TBodyGetGameJoyDLInfoRsp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c(y, "request auto download failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c(y, "request auto download success");
    TBodyGetGameJoyDLInfoRsp localTBodyGetGameJoyDLInfoRsp = (TBodyGetGameJoyDLInfoRsp)paramProtocolResponse.getBusiResponse();
    AutoDownLoadInfo localAutoDownLoadInfo = new AutoDownLoadInfo();
    localAutoDownLoadInfo.a = true;
    localAutoDownLoadInfo.c = localTBodyGetGameJoyDLInfoRsp.getGameJoyDLInfo().downloadUrl;
    localAutoDownLoadInfo.d = localTBodyGetGameJoyDLInfoRsp.getGameJoyDLInfo().fileSize;
    localAutoDownLoadInfo.b = localTBodyGetGameJoyDLInfoRsp.getGameJoyDLInfo().hallPkgName;
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), localAutoDownLoadInfo);
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyGetGameJoyDLInfoReq localTBodyGetGameJoyDLInfoReq = new TBodyGetGameJoyDLInfoReq();
    localTBodyGetGameJoyDLInfoReq.gamePkgName = ((String)paramArrayOfObject[0]);
    return localTBodyGetGameJoyDLInfoReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.AutoDownloadGameJoyRequest
 * JD-Core Version:    0.6.0
 */