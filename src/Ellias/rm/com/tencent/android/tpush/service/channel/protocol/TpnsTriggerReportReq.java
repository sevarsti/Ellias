package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsTriggerReportReq extends JceStruct
  implements Cloneable
{
  public long timeEnd = 0L;
  public long timeStart = 0L;

  static
  {
    if (!TpnsTriggerReportReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsTriggerReportReq()
  {
  }

  public TpnsTriggerReportReq(long paramLong1, long paramLong2)
  {
    this.timeStart = paramLong1;
    this.timeEnd = paramLong2;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsTriggerReportReq";
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
    localJceDisplayer.display(this.timeStart, "timeStart");
    localJceDisplayer.display(this.timeEnd, "timeEnd");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.timeStart, true);
    localJceDisplayer.displaySimple(this.timeEnd, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsTriggerReportReq localTpnsTriggerReportReq;
    do
    {
      return false;
      localTpnsTriggerReportReq = (TpnsTriggerReportReq)paramObject;
    }
    while ((!JceUtil.equals(this.timeStart, localTpnsTriggerReportReq.timeStart)) || (!JceUtil.equals(this.timeEnd, localTpnsTriggerReportReq.timeEnd)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsTriggerReportReq";
  }

  public long getTimeEnd()
  {
    return this.timeEnd;
  }

  public long getTimeStart()
  {
    return this.timeStart;
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
    this.timeStart = paramJceInputStream.read(this.timeStart, 0, true);
    this.timeEnd = paramJceInputStream.read(this.timeEnd, 1, true);
  }

  public void setTimeEnd(long paramLong)
  {
    this.timeEnd = paramLong;
  }

  public void setTimeStart(long paramLong)
  {
    this.timeStart = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.timeStart, 0);
    paramJceOutputStream.write(this.timeEnd, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsTriggerReportReq
 * JD-Core Version:    0.6.0
 */