package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TpnsClientReportReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_reportMsgs;
  public ArrayList reportMsgs = null;

  static
  {
    if (!TpnsClientReportReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsClientReportReq()
  {
  }

  public TpnsClientReportReq(ArrayList paramArrayList)
  {
    this.reportMsgs = paramArrayList;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsClientReportReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.reportMsgs, "reportMsgs");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.reportMsgs, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsClientReportReq localTpnsClientReportReq = (TpnsClientReportReq)paramObject;
    return JceUtil.equals(this.reportMsgs, localTpnsClientReportReq.reportMsgs);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsClientReportReq";
  }

  public ArrayList getReportMsgs()
  {
    return this.reportMsgs;
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
    if (cache_reportMsgs == null)
    {
      cache_reportMsgs = new ArrayList();
      TpnsClientReport localTpnsClientReport = new TpnsClientReport();
      cache_reportMsgs.add(localTpnsClientReport);
    }
    this.reportMsgs = ((ArrayList)paramJceInputStream.read(cache_reportMsgs, 1, false));
  }

  public void setReportMsgs(ArrayList paramArrayList)
  {
    this.reportMsgs = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.reportMsgs != null)
      paramJceOutputStream.write(this.reportMsgs, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq
 * JD-Core Version:    0.6.0
 */