package com.tencent.qqgamemi.protocol.business;

import CobraHallQmiProto.TBodyGetPluginNoticeReq;
import CobraHallQmiProto.TBodyGetPluginNoticeRsp;
import com.qq.taf.jce.JceStruct;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.qqgamemi.protocol.QMiProtocolRequest;
import java.util.ArrayList;

public class PluginUndealCountRequest extends QMiProtocolRequest
{
  private ArrayList y;

  public PluginUndealCountRequest(ArrayList paramArrayList)
  {
    super(115, null, 0, new Object[0]);
    this.y = paramArrayList;
  }

  public Class getResponseClass()
  {
    return TBodyGetPluginNoticeRsp.class;
  }

  public ArrayList m()
  {
    return this.y;
  }

  public void onRequestFailed(ProtocolResponse paramProtocolResponse)
  {
  }

  public void onRequestSuccess(ProtocolResponse paramProtocolResponse)
  {
  }

  protected JceStruct packageJceStruct(Object[] paramArrayOfObject)
  {
    TBodyGetPluginNoticeReq localTBodyGetPluginNoticeReq = new TBodyGetPluginNoticeReq();
    localTBodyGetPluginNoticeReq.setPluginPkgNameList(this.y);
    return localTBodyGetPluginNoticeReq;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.protocol.business.PluginUndealCountRequest
 * JD-Core Version:    0.6.0
 */