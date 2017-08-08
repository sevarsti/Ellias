package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TpnsPushVerifyReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_msgReportList;
  public ArrayList msgReportList = null;

  static
  {
    if (!TpnsPushVerifyReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsPushVerifyReq()
  {
  }

  public TpnsPushVerifyReq(ArrayList paramArrayList)
  {
    this.msgReportList = paramArrayList;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsPushVerifyReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.msgReportList, "msgReportList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.msgReportList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsPushVerifyReq localTpnsPushVerifyReq = (TpnsPushVerifyReq)paramObject;
    return JceUtil.equals(this.msgReportList, localTpnsPushVerifyReq.msgReportList);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsPushVerifyReq";
  }

  public ArrayList getMsgReportList()
  {
    return this.msgReportList;
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
    if (cache_msgReportList == null)
    {
      cache_msgReportList = new ArrayList();
      TpnsPushClientReport localTpnsPushClientReport = new TpnsPushClientReport();
      cache_msgReportList.add(localTpnsPushClientReport);
    }
    this.msgReportList = ((ArrayList)paramJceInputStream.read(cache_msgReportList, 1, true));
  }

  public void setMsgReportList(ArrayList paramArrayList)
  {
    this.msgReportList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.msgReportList, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq
 * JD-Core Version:    0.6.0
 */