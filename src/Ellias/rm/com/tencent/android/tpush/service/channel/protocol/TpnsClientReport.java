package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsClientReport extends JceStruct
  implements Cloneable
{
  static int cache_commandId;
  public long acceptTime = 0L;
  public long accip = 0L;
  public byte apn = 0;
  public byte available = 0;
  public int commandId = 0;
  public long connectTime = 0L;
  public String domain = "";
  public long downstreamTime = 0L;
  public byte isp = 0;
  public String lbs = "";
  public byte pack = 0;
  public int port = 0;
  public String qua = "";
  public byte result = 0;
  public long resultCode = 0L;
  public String signal = "";
  public long tmcost = 0L;
  public long upstreamTime = 0L;

  static
  {
    if (!TpnsClientReport.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsClientReport()
  {
  }

  public TpnsClientReport(int paramInt1, byte paramByte1, int paramInt2, long paramLong1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong2, byte paramByte5, long paramLong3, String paramString1, String paramString2, long paramLong4, long paramLong5, long paramLong6, long paramLong7, String paramString3, String paramString4)
  {
    this.commandId = paramInt1;
    this.isp = paramByte1;
    this.port = paramInt2;
    this.accip = paramLong1;
    this.apn = paramByte2;
    this.pack = paramByte3;
    this.available = paramByte4;
    this.tmcost = paramLong2;
    this.result = paramByte5;
    this.resultCode = paramLong3;
    this.domain = paramString1;
    this.qua = paramString2;
    this.connectTime = paramLong4;
    this.upstreamTime = paramLong5;
    this.downstreamTime = paramLong6;
    this.acceptTime = paramLong7;
    this.signal = paramString3;
    this.lbs = paramString4;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsClientReport";
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
    localJceDisplayer.display(this.commandId, "commandId");
    localJceDisplayer.display(this.isp, "isp");
    localJceDisplayer.display(this.port, "port");
    localJceDisplayer.display(this.accip, "accip");
    localJceDisplayer.display(this.apn, "apn");
    localJceDisplayer.display(this.pack, "pack");
    localJceDisplayer.display(this.available, "available");
    localJceDisplayer.display(this.tmcost, "tmcost");
    localJceDisplayer.display(this.result, "result");
    localJceDisplayer.display(this.resultCode, "resultCode");
    localJceDisplayer.display(this.domain, "domain");
    localJceDisplayer.display(this.qua, "qua");
    localJceDisplayer.display(this.connectTime, "connectTime");
    localJceDisplayer.display(this.upstreamTime, "upstreamTime");
    localJceDisplayer.display(this.downstreamTime, "downstreamTime");
    localJceDisplayer.display(this.acceptTime, "acceptTime");
    localJceDisplayer.display(this.signal, "signal");
    localJceDisplayer.display(this.lbs, "lbs");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.commandId, true);
    localJceDisplayer.displaySimple(this.isp, true);
    localJceDisplayer.displaySimple(this.port, true);
    localJceDisplayer.displaySimple(this.accip, true);
    localJceDisplayer.displaySimple(this.apn, true);
    localJceDisplayer.displaySimple(this.pack, true);
    localJceDisplayer.displaySimple(this.available, true);
    localJceDisplayer.displaySimple(this.tmcost, true);
    localJceDisplayer.displaySimple(this.result, true);
    localJceDisplayer.displaySimple(this.resultCode, true);
    localJceDisplayer.displaySimple(this.domain, true);
    localJceDisplayer.displaySimple(this.qua, true);
    localJceDisplayer.displaySimple(this.connectTime, true);
    localJceDisplayer.displaySimple(this.upstreamTime, true);
    localJceDisplayer.displaySimple(this.downstreamTime, true);
    localJceDisplayer.displaySimple(this.acceptTime, true);
    localJceDisplayer.displaySimple(this.signal, true);
    localJceDisplayer.displaySimple(this.lbs, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsClientReport localTpnsClientReport;
    do
    {
      return false;
      localTpnsClientReport = (TpnsClientReport)paramObject;
    }
    while ((!JceUtil.equals(this.commandId, localTpnsClientReport.commandId)) || (!JceUtil.equals(this.isp, localTpnsClientReport.isp)) || (!JceUtil.equals(this.port, localTpnsClientReport.port)) || (!JceUtil.equals(this.accip, localTpnsClientReport.accip)) || (!JceUtil.equals(this.apn, localTpnsClientReport.apn)) || (!JceUtil.equals(this.pack, localTpnsClientReport.pack)) || (!JceUtil.equals(this.available, localTpnsClientReport.available)) || (!JceUtil.equals(this.tmcost, localTpnsClientReport.tmcost)) || (!JceUtil.equals(this.result, localTpnsClientReport.result)) || (!JceUtil.equals(this.resultCode, localTpnsClientReport.resultCode)) || (!JceUtil.equals(this.domain, localTpnsClientReport.domain)) || (!JceUtil.equals(this.qua, localTpnsClientReport.qua)) || (!JceUtil.equals(this.connectTime, localTpnsClientReport.connectTime)) || (!JceUtil.equals(this.upstreamTime, localTpnsClientReport.upstreamTime)) || (!JceUtil.equals(this.downstreamTime, localTpnsClientReport.downstreamTime)) || (!JceUtil.equals(this.acceptTime, localTpnsClientReport.acceptTime)) || (!JceUtil.equals(this.signal, localTpnsClientReport.signal)) || (!JceUtil.equals(this.lbs, localTpnsClientReport.lbs)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsClientReport";
  }

  public long getAcceptTime()
  {
    return this.acceptTime;
  }

  public long getAccip()
  {
    return this.accip;
  }

  public byte getApn()
  {
    return this.apn;
  }

  public byte getAvailable()
  {
    return this.available;
  }

  public int getCommandId()
  {
    return this.commandId;
  }

  public long getConnectTime()
  {
    return this.connectTime;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public long getDownstreamTime()
  {
    return this.downstreamTime;
  }

  public byte getIsp()
  {
    return this.isp;
  }

  public String getLbs()
  {
    return this.lbs;
  }

  public byte getPack()
  {
    return this.pack;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getQua()
  {
    return this.qua;
  }

  public byte getResult()
  {
    return this.result;
  }

  public long getResultCode()
  {
    return this.resultCode;
  }

  public String getSignal()
  {
    return this.signal;
  }

  public long getTmcost()
  {
    return this.tmcost;
  }

  public long getUpstreamTime()
  {
    return this.upstreamTime;
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
    this.commandId = paramJceInputStream.read(this.commandId, 0, false);
    this.isp = paramJceInputStream.read(this.isp, 1, false);
    this.port = paramJceInputStream.read(this.port, 2, false);
    this.accip = paramJceInputStream.read(this.accip, 3, false);
    this.apn = paramJceInputStream.read(this.apn, 4, false);
    this.pack = paramJceInputStream.read(this.pack, 5, false);
    this.available = paramJceInputStream.read(this.available, 6, false);
    this.tmcost = paramJceInputStream.read(this.tmcost, 7, false);
    this.result = paramJceInputStream.read(this.result, 8, false);
    this.resultCode = paramJceInputStream.read(this.resultCode, 9, false);
    this.domain = paramJceInputStream.readString(10, false);
    this.qua = paramJceInputStream.readString(11, false);
    this.connectTime = paramJceInputStream.read(this.connectTime, 12, false);
    this.upstreamTime = paramJceInputStream.read(this.upstreamTime, 13, false);
    this.downstreamTime = paramJceInputStream.read(this.downstreamTime, 14, false);
    this.acceptTime = paramJceInputStream.read(this.acceptTime, 15, false);
    this.signal = paramJceInputStream.readString(16, false);
    this.lbs = paramJceInputStream.readString(17, false);
  }

  public void setAcceptTime(long paramLong)
  {
    this.acceptTime = paramLong;
  }

  public void setAccip(long paramLong)
  {
    this.accip = paramLong;
  }

  public void setApn(byte paramByte)
  {
    this.apn = paramByte;
  }

  public void setAvailable(byte paramByte)
  {
    this.available = paramByte;
  }

  public void setCommandId(int paramInt)
  {
    this.commandId = paramInt;
  }

  public void setConnectTime(long paramLong)
  {
    this.connectTime = paramLong;
  }

  public void setDomain(String paramString)
  {
    this.domain = paramString;
  }

  public void setDownstreamTime(long paramLong)
  {
    this.downstreamTime = paramLong;
  }

  public void setIsp(byte paramByte)
  {
    this.isp = paramByte;
  }

  public void setLbs(String paramString)
  {
    this.lbs = paramString;
  }

  public void setPack(byte paramByte)
  {
    this.pack = paramByte;
  }

  public void setPort(int paramInt)
  {
    this.port = paramInt;
  }

  public void setQua(String paramString)
  {
    this.qua = paramString;
  }

  public void setResult(byte paramByte)
  {
    this.result = paramByte;
  }

  public void setResultCode(long paramLong)
  {
    this.resultCode = paramLong;
  }

  public void setSignal(String paramString)
  {
    this.signal = paramString;
  }

  public void setTmcost(long paramLong)
  {
    this.tmcost = paramLong;
  }

  public void setUpstreamTime(long paramLong)
  {
    this.upstreamTime = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.commandId, 0);
    paramJceOutputStream.write(this.isp, 1);
    paramJceOutputStream.write(this.port, 2);
    paramJceOutputStream.write(this.accip, 3);
    paramJceOutputStream.write(this.apn, 4);
    paramJceOutputStream.write(this.pack, 5);
    paramJceOutputStream.write(this.available, 6);
    paramJceOutputStream.write(this.tmcost, 7);
    paramJceOutputStream.write(this.result, 8);
    paramJceOutputStream.write(this.resultCode, 9);
    if (this.domain != null)
      paramJceOutputStream.write(this.domain, 10);
    if (this.qua != null)
      paramJceOutputStream.write(this.qua, 11);
    paramJceOutputStream.write(this.connectTime, 12);
    paramJceOutputStream.write(this.upstreamTime, 13);
    paramJceOutputStream.write(this.downstreamTime, 14);
    paramJceOutputStream.write(this.acceptTime, 15);
    if (this.signal != null)
      paramJceOutputStream.write(this.signal, 16);
    if (this.lbs != null)
      paramJceOutputStream.write(this.lbs, 17);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsClientReport
 * JD-Core Version:    0.6.0
 */