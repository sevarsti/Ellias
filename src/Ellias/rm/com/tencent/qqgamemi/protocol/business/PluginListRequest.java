package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyQmiGamePageListReq;
import CobraHallQmiProto.TBodyQmiGamePageListRsp;
import CobraHallQmiProto.TQmiUnitBaseInfo;
import android.os.Handler;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiApplication;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PluginListRequest extends QMiProtocolRequest
{
  private static final String y = "PluginListRequest";

  public PluginListRequest(Handler paramHandler, int paramInt, Object[] paramArrayOfObject)
  {
    super(1002, paramHandler, paramInt, paramArrayOfObject);
    setNeedDeviceInfo(true);
  }

  public Class getResponseClass()
  {
    return TBodyQmiGamePageListRsp.class;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("PluginListRequest", "request plugin failed:" + paramProtocolResponse.getResultCode());
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), paramProtocolResponse.getResultMsg());
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
    TLog.c("PluginListRequest", "request plugins success");
    TBodyQmiGamePageListRsp localTBodyQmiGamePageListRsp = (TBodyQmiGamePageListRsp)paramProtocolResponse.getBusiResponse();
    if (DebugUtil.a())
    {
      Iterator localIterator = localTBodyQmiGamePageListRsp.gameList.iterator();
      while (localIterator.hasNext())
      {
        TQmiUnitBaseInfo localTQmiUnitBaseInfo = (TQmiUnitBaseInfo)localIterator.next();
        LogUtil.d("PluginListRequest", "get a plugin online:" + localTQmiUnitBaseInfo.pluginName);
      }
    }
    sendMessage(getMsg(), paramProtocolResponse.getResultCode(), e(), localTBodyQmiGamePageListRsp);
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyQmiGamePageListReq localTBodyQmiGamePageListReq = new TBodyQmiGamePageListReq();
    localTBodyQmiGamePageListReq.pageNo = ((Integer)paramArrayOfObject[0]).intValue();
    localTBodyQmiGamePageListReq.pageSize = ((Integer)paramArrayOfObject[1]).intValue();
    localTBodyQmiGamePageListReq.gamePkgName = ((String)paramArrayOfObject[2]);
    localTBodyQmiGamePageListReq.gameVersion = QMiCommon.a(QMiApplication.a(), localTBodyQmiGamePageListReq.gamePkgName);
    localTBodyQmiGamePageListReq.plugInVerInfos = ((ArrayList)paramArrayOfObject[3]);
    if (QMiConfig.b())
    {
      localTBodyQmiGamePageListReq.categoryId = 17;
      if (DebugUtil.a())
        TLog.c("PluginListRequest", "request qmi sdk plugin list:" + localTBodyQmiGamePageListReq.gamePkgName + " " + localTBodyQmiGamePageListReq.gameVersion + " " + localTBodyQmiGamePageListReq.getPlugInVerInfos());
    }
    do
    {
      return localTBodyQmiGamePageListReq;
      localTBodyQmiGamePageListReq.categoryId = 16;
    }
    while (!DebugUtil.a());
    TLog.c("PluginListRequest", "request qmi plugin list:" + localTBodyQmiGamePageListReq.gamePkgName + " " + localTBodyQmiGamePageListReq.gameVersion + " " + localTBodyQmiGamePageListReq.getPlugInVerInfos());
    return localTBodyQmiGamePageListReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.PluginListRequest
 * JD-Core Version:    0.6.0
 */