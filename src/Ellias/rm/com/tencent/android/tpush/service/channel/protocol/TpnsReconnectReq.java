package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TpnsReconnectReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_msgClickList;
  static MutableInfo cache_mutableInfo;
  static ArrayList cache_recvMsgList;
  static ArrayList cache_unregInfoList;
  public String deviceId = "";
  public short deviceType = 0;
  public ArrayList msgClickList = null;
  public MutableInfo mutableInfo = null;
  public short networkType = 0;
  public ArrayList recvMsgList = null;
  public String token = "";
  public ArrayList unregInfoList = null;

  static
  {
    if (!TpnsReconnectReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsReconnectReq()
  {
  }

  public TpnsReconnectReq(String paramString1, String paramString2, short paramShort1, ArrayList paramArrayList1, ArrayList paramArrayList2, MutableInfo paramMutableInfo, short paramShort2, ArrayList paramArrayList3)
  {
    this.token = paramString1;
    this.deviceId = paramString2;
    this.networkType = paramShort1;
    this.unregInfoList = paramArrayList1;
    this.recvMsgList = paramArrayList2;
    this.mutableInfo = paramMutableInfo;
    this.deviceType = paramShort2;
    this.msgClickList = paramArrayList3;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsReconnectReq";
  }

  public Object clone()
  {
    try
    {
      Object localObject2 = super.clone();
      localObject1 = localObject2;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      boolean bool;
      do
      {
        bool = $assertionsDisabled;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.token, "token");
    localJceDisplayer.display(this.deviceId, "deviceId");
    localJceDisplayer.display(this.networkType, "networkType");
    localJceDisplayer.display(this.unregInfoList, "unregInfoList");
    localJceDisplayer.display(this.recvMsgList, "recvMsgList");
    localJceDisplayer.display(this.mutableInfo, "mutableInfo");
    localJceDisplayer.display(this.deviceType, "deviceType");
    localJceDisplayer.display(this.msgClickList, "msgClickList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.token, true);
    localJceDisplayer.displaySimple(this.deviceId, true);
    localJceDisplayer.displaySimple(this.networkType, true);
    localJceDisplayer.displaySimple(this.unregInfoList, true);
    localJceDisplayer.displaySimple(this.recvMsgList, true);
    localJceDisplayer.displaySimple(this.mutableInfo, true);
    localJceDisplayer.displaySimple(this.deviceType, true);
    localJceDisplayer.displaySimple(this.msgClickList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsReconnectReq localTpnsReconnectReq;
    do
    {
      return false;
      localTpnsReconnectReq = (TpnsReconnectReq)paramObject;
    }
    while ((!JceUtil.equals(this.token, localTpnsReconnectReq.token)) || (!JceUtil.equals(this.deviceId, localTpnsReconnectReq.deviceId)) || (!JceUtil.equals(this.networkType, localTpnsReconnectReq.networkType)) || (!JceUtil.equals(this.unregInfoList, localTpnsReconnectReq.unregInfoList)) || (!JceUtil.equals(this.recvMsgList, localTpnsReconnectReq.recvMsgList)) || (!JceUtil.equals(this.mutableInfo, localTpnsReconnectReq.mutableInfo)) || (!JceUtil.equals(this.deviceType, localTpnsReconnectReq.deviceType)) || (!JceUtil.equals(this.msgClickList, localTpnsReconnectReq.msgClickList)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsReconnectReq";
  }

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public short getDeviceType()
  {
    return this.deviceType;
  }

  public ArrayList getMsgClickList()
  {
    return this.msgClickList;
  }

  public MutableInfo getMutableInfo()
  {
    return this.mutableInfo;
  }

  public short getNetworkType()
  {
    return this.networkType;
  }

  public ArrayList getRecvMsgList()
  {
    return this.recvMsgList;
  }

  public String getToken()
  {
    return this.token;
  }

  public ArrayList getUnregInfoList()
  {
    return this.unregInfoList;
  }

  public int hashCode()
  {
    try
    {
      throw new Exception("Need define key first!");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public void readFrom(JceInputStream paramJceInputStream)
  {
    this.token = paramJceInputStream.readString(0, true);
    this.deviceId = paramJceInputStream.readString(1, true);
    this.networkType = paramJceInputStream.read(this.networkType, 2, true);
    if (cache_unregInfoList == null)
    {
      cache_unregInfoList = new ArrayList();
      UnregInfo localUnregInfo = new UnregInfo();
      cache_unregInfoList.add(localUnregInfo);
    }
    this.unregInfoList = ((ArrayList)paramJceInputStream.read(cache_unregInfoList, 3, false));
    if (cache_recvMsgList == null)
    {
      cache_recvMsgList = new ArrayList();
      TpnsPushClientReport localTpnsPushClientReport = new TpnsPushClientReport();
      cache_recvMsgList.add(localTpnsPushClientReport);
    }
    this.recvMsgList = ((ArrayList)paramJceInputStream.read(cache_recvMsgList, 4, false));
    if (cache_mutableInfo == null)
      cache_mutableInfo = new MutableInfo();
    this.mutableInfo = ((MutableInfo)paramJceInputStream.read(cache_mutableInfo, 5, false));
    this.deviceType = paramJceInputStream.read(this.deviceType, 6, false);
    if (cache_msgClickList == null)
    {
      cache_msgClickList = new ArrayList();
      TpnsClickClientReport localTpnsClickClientReport = new TpnsClickClientReport();
      cache_msgClickList.add(localTpnsClickClientReport);
    }
    this.msgClickList = ((ArrayList)paramJceInputStream.read(cache_msgClickList, 7, false));
  }

  public void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }

  public void setDeviceType(short paramShort)
  {
    this.deviceType = paramShort;
  }

  public void setMsgClickList(ArrayList paramArrayList)
  {
    this.msgClickList = paramArrayList;
  }

  public void setMutableInfo(MutableInfo paramMutableInfo)
  {
    this.mutableInfo = paramMutableInfo;
  }

  public void setNetworkType(short paramShort)
  {
    this.networkType = paramShort;
  }

  public void setRecvMsgList(ArrayList paramArrayList)
  {
    this.recvMsgList = paramArrayList;
  }

  public void setToken(String paramString)
  {
    this.token = paramString;
  }

  public void setUnregInfoList(ArrayList paramArrayList)
  {
    this.unregInfoList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.token, 0);
    paramJceOutputStream.write(this.deviceId, 1);
    paramJceOutputStream.write(this.networkType, 2);
    if (this.unregInfoList != null)
      paramJceOutputStream.write(this.unregInfoList, 3);
    if (this.recvMsgList != null)
      paramJceOutputStream.write(this.recvMsgList, 4);
    if (this.mutableInfo != null)
      paramJceOutputStream.write(this.mutableInfo, 5);
    paramJceOutputStream.write(this.deviceType, 6);
    if (this.msgClickList != null)
      paramJceOutputStream.write(this.msgClickList, 7);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq
 * JD-Core Version:    0.6.0
 */