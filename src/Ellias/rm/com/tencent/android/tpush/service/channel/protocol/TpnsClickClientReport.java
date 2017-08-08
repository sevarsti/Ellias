package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsClickClientReport extends JceStruct
  implements Cloneable
{
  public long accessId = 0L;
  public long action = 0L;
  public long broadcastId = 0L;
  public long clickTime = 0L;
  public long msgId = 0L;
  public long timestamp = 0L;
  public long type = 0L;

  static
  {
    if (!TpnsClickClientReport.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsClickClientReport()
  {
  }

  public TpnsClickClientReport(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    this.msgId = paramLong1;
    this.accessId = paramLong2;
    this.broadcastId = paramLong3;
    this.timestamp = paramLong4;
    this.type = paramLong5;
    this.clickTime = paramLong6;
    this.action = paramLong7;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsClickClientReport";
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
    localJceDisplayer.display(this.broadcastId, "broadcastId");
    localJceDisplayer.display(this.timestamp, "timestamp");
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.clickTime, "clickTime");
    localJceDisplayer.display(this.action, "action");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.msgId, true);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.broadcastId, true);
    localJceDisplayer.displaySimple(this.timestamp, true);
    localJceDisplayer.displaySimple(this.type, true);
    localJceDisplayer.displaySimple(this.clickTime, true);
    localJceDisplayer.displaySimple(this.action, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsClickClientReport localTpnsClickClientReport;
    do
    {
      return false;
      localTpnsClickClientReport = (TpnsClickClientReport)paramObject;
    }
    while ((!JceUtil.equals(this.msgId, localTpnsClickClientReport.msgId)) || (!JceUtil.equals(this.accessId, localTpnsClickClientReport.accessId)) || (!JceUtil.equals(this.broadcastId, localTpnsClickClientReport.broadcastId)) || (!JceUtil.equals(this.timestamp, localTpnsClickClientReport.timestamp)) || (!JceUtil.equals(this.type, localTpnsClickClientReport.type)) || (!JceUtil.equals(this.clickTime, localTpnsClickClientReport.clickTime)) || (!JceUtil.equals(this.action, localTpnsClickClientReport.action)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsClickClientReport";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public long getAction()
  {
    return this.action;
  }

  public long getBroadcastId()
  {
    return this.broadcastId;
  }

  public long getClickTime()
  {
    return this.clickTime;
  }

  public long getMsgId()
  {
    return this.msgId;
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
    this.broadcastId = paramJceInputStream.read(this.broadcastId, 2, false);
    this.timestamp = paramJceInputStream.read(this.timestamp, 3, false);
    this.type = paramJceInputStream.read(this.type, 4, false);
    this.clickTime = paramJceInputStream.read(this.clickTime, 5, false);
    this.action = paramJceInputStream.read(this.action, 6, false);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setAction(long paramLong)
  {
    this.action = paramLong;
  }

  public void setBroadcastId(long paramLong)
  {
    this.broadcastId = paramLong;
  }

  public void setClickTime(long paramLong)
  {
    this.clickTime = paramLong;
  }

  public void setMsgId(long paramLong)
  {
    this.msgId = paramLong;
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
    paramJceOutputStream.write(this.broadcastId, 2);
    paramJceOutputStream.write(this.timestamp, 3);
    paramJceOutputStream.write(this.type, 4);
    paramJceOutputStream.write(this.clickTime, 5);
    paramJceOutputStream.write(this.action, 6);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport
 * JD-Core Version:    0.6.0
 */