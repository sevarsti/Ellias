package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TpnsPushClickReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_msgClickList;
  public ArrayList msgClickList = null;

  static
  {
    if (!TpnsPushClickReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsPushClickReq()
  {
  }

  public TpnsPushClickReq(ArrayList paramArrayList)
  {
    this.msgClickList = paramArrayList;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsPushClickReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.msgClickList, "msgClickList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.msgClickList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsPushClickReq localTpnsPushClickReq = (TpnsPushClickReq)paramObject;
    return JceUtil.equals(this.msgClickList, localTpnsPushClickReq.msgClickList);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsPushClickReq";
  }

  public ArrayList getMsgClickList()
  {
    return this.msgClickList;
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
    if (cache_msgClickList == null)
    {
      cache_msgClickList = new ArrayList();
      TpnsClickClientReport localTpnsClickClientReport = new TpnsClickClientReport();
      cache_msgClickList.add(localTpnsClickClientReport);
    }
    this.msgClickList = ((ArrayList)paramJceInputStream.read(cache_msgClickList, 1, true));
  }

  public void setMsgClickList(ArrayList paramArrayList)
  {
    this.msgClickList = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.msgClickList, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq
 * JD-Core Version:    0.6.0
 */