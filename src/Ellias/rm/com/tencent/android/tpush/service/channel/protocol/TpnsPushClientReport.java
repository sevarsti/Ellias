package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsPushClientReport extends JceStruct
  implements Cloneable
{
  public long accessId = 0L;
  public byte apn = 0;
  public long broadcastId = 0L;
  public long confirmMs = 0L;
  public byte isp = 0;
  public long locip = 0L;
  public int locport = 0;
  public long msgId = 0L;
  public byte pack = 0;
  public String qua = "";
  public long receiveTime = 0L;
  public String serviceHost = "";
  public long timeUs = 0L;
  public long timestamp = 0L;
  public long type = 0L;

  static
  {
    if (!TpnsPushClientReport.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsPushClientReport()
  {
  }

  public TpnsPushClientReport(long paramLong1, long paramLong2, byte paramByte1, byte paramByte2, byte paramByte3, String paramString1, long paramLong3, int paramInt, String paramString2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9)
  {
    this.msgId = paramLong1;
    this.accessId = paramLong2;
    this.isp = paramByte1;
    this.apn = paramByte2;
    this.pack = paramByte3;
    this.qua = paramString1;
    this.locip = paramLong3;
    this.locport = paramInt;
    this.serviceHost = paramString2;
    this.timeUs = paramLong4;
    this.confirmMs = paramLong5;
    this.broadcastId = paramLong6;
    this.timestamp = paramLong7;
    this.type = paramLong8;
    this.receiveTime = paramLong9;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsPushClientReport";
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
    localJceDisplayer.display(this.msgId, "msgId");
    localJceDisplayer.display(this.accessId, "accessId");
    localJceDisplayer.display(this.isp, "isp");
    localJceDisplayer.display(this.apn, "apn");
    localJceDisplayer.display(this.pack, "pack");
    localJceDisplayer.display(this.qua, "qua");
    localJceDisplayer.display(this.locip, "locip");
    localJceDisplayer.display(this.locport, "locport");
    localJceDisplayer.display(this.serviceHost, "serviceHost");
    localJceDisplayer.display(this.timeUs, "timeUs");
    localJceDisplayer.display(this.confirmMs, "confirmMs");
    localJceDisplayer.display(this.broadcastId, "broadcastId");
    localJceDisplayer.display(this.timestamp, "timestamp");
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.receiveTime, "receiveTime");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.msgId, true);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.isp, true);
    localJceDisplayer.displaySimple(this.apn, true);
    localJceDisplayer.displaySimple(this.pack, true);
    localJceDisplayer.displaySimple(this.qua, true);
    localJceDisplayer.displaySimple(this.locip, true);
    localJceDisplayer.displaySimple(this.locport, true);
    localJceDisplayer.displaySimple(this.serviceHost, true);
    localJceDisplayer.displaySimple(this.timeUs, true);
    localJceDisplayer.displaySimple(this.confirmMs, true);
    localJceDisplayer.displaySimple(this.broadcastId, true);
    localJceDisplayer.displaySimple(this.timestamp, true);
    localJceDisplayer.displaySimple(this.type, true);
    localJceDisplayer.displaySimple(this.receiveTime, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsPushClientReport localTpnsPushClientReport;
    do
    {
      return false;
      localTpnsPushClientReport = (TpnsPushClientReport)paramObject;
    }
    while ((!JceUtil.equals(this.msgId, localTpnsPushClientReport.msgId)) || (!JceUtil.equals(this.accessId, localTpnsPushClientReport.accessId)) || (!JceUtil.equals(this.isp, localTpnsPushClientReport.isp)) || (!JceUtil.equals(this.apn, localTpnsPushClientReport.apn)) || (!JceUtil.equals(this.pack, localTpnsPushClientReport.pack)) || (!JceUtil.equals(this.qua, localTpnsPushClientReport.qua)) || (!JceUtil.equals(this.locip, localTpnsPushClientReport.locip)) || (!JceUtil.equals(this.locport, localTpnsPushClientReport.locport)) || (!JceUtil.equals(this.serviceHost, localTpnsPushClientReport.serviceHost)) || (!JceUtil.equals(this.timeUs, localTpnsPushClientReport.timeUs)) || (!JceUtil.equals(this.confirmMs, localTpnsPushClientReport.confirmMs)) || (!JceUtil.equals(this.broadcastId, localTpnsPushClientReport.broadcastId)) || (!JceUtil.equals(this.timestamp, localTpnsPushClientReport.timestamp)) || (!JceUtil.equals(this.type, localTpnsPushClientReport.type)) || (!JceUtil.equals(this.receiveTime, localTpnsPushClientReport.receiveTime)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsPushClientReport";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public byte getApn()
  {
    return this.apn;
  }

  public long getBroadcastId()
  {
    return this.broadcastId;
  }

  public long getConfirmMs()
  {
    return this.confirmMs;
  }

  public byte getIsp()
  {
    return this.isp;
  }

  public long getLocip()
  {
    return this.locip;
  }

  public int getLocport()
  {
    return this.locport;
  }

  public long getMsgId()
  {
    return this.msgId;
  }

  public byte getPack()
  {
    return this.pack;
  }

  public String getQua()
  {
    return this.qua;
  }

  public long getReceiveTime()
  {
    return this.receiveTime;
  }

  public String getServiceHost()
  {
    return this.serviceHost;
  }

  public long getTimeUs()
  {
    return this.timeUs;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public long getType()
  {
    return this.type;
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
    this.msgId = paramJceInputStream.read(this.msgId, 0, true);
    this.accessId = paramJceInputStream.read(this.accessId, 1, true);
    this.isp = paramJceInputStream.read(this.isp, 2, false);
    this.apn = paramJceInputStream.read(this.apn, 3, false);
    this.pack = paramJceInputStream.read(this.pack, 4, false);
    this.qua = paramJceInputStream.readString(5, false);
    this.locip = paramJceInputStream.read(this.locip, 6, false);
    this.locport = paramJceInputStream.read(this.locport, 7, false);
    this.serviceHost = paramJceInputStream.readString(8, false);
    this.timeUs = paramJceInputStream.read(this.timeUs, 9, false);
    this.confirmMs = paramJceInputStream.read(this.confirmMs, 10, false);
    this.broadcastId = paramJceInputStream.read(this.broadcastId, 11, false);
    this.timestamp = paramJceInputStream.read(this.timestamp, 12, false);
    this.type = paramJceInputStream.read(this.type, 13, false);
    this.receiveTime = paramJceInputStream.read(this.receiveTime, 14, false);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setApn(byte paramByte)
  {
    this.apn = paramByte;
  }

  public void setBroadcastId(long paramLong)
  {
    this.broadcastId = paramLong;
  }

  public void setConfirmMs(long paramLong)
  {
    this.confirmMs = paramLong;
  }

  public void setIsp(byte paramByte)
  {
    this.isp = paramByte;
  }

  public void setLocip(long paramLong)
  {
    this.locip = paramLong;
  }

  public void setLocport(int paramInt)
  {
    this.locport = paramInt;
  }

  public void setMsgId(long paramLong)
  {
    this.msgId = paramLong;
  }

  public void setPack(byte paramByte)
  {
    this.pack = paramByte;
  }

  public void setQua(String paramString)
  {
    this.qua = paramString;
  }

  public void setReceiveTime(long paramLong)
  {
    this.receiveTime = paramLong;
  }

  public void setServiceHost(String paramString)
  {
    this.serviceHost = paramString;
  }

  public void setTimeUs(long paramLong)
  {
    this.timeUs = paramLong;
  }

  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }

  public void setType(long paramLong)
  {
    this.type = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.msgId, 0);
    paramJceOutputStream.write(this.accessId, 1);
    paramJceOutputStream.write(this.isp, 2);
    paramJceOutputStream.write(this.apn, 3);
    paramJceOutputStream.write(this.pack, 4);
    if (this.qua != null)
      paramJceOutputStream.write(this.qua, 5);
    paramJceOutputStream.write(this.locip, 6);
    paramJceOutputStream.write(this.locport, 7);
    if (this.serviceHost != null)
      paramJceOutputStream.write(this.serviceHost, 8);
    paramJceOutputStream.write(this.timeUs, 9);
    paramJceOutputStream.write(this.confirmMs, 10);
    paramJceOutputStream.write(this.broadcastId, 11);
    paramJceOutputStream.write(this.timestamp, 12);
    paramJceOutputStream.write(this.type, 13);
    paramJceOutputStream.write(this.receiveTime, 14);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport
 * JD-Core Version:    0.6.0
 */