package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TpnsPushClientReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_msgList;
  public ArrayList msgList = null;
  public long timeUs = 0L;

  static
  {
    if (!TpnsPushClientReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsPushClientReq()
  {
  }

  public TpnsPushClientReq(ArrayList paramArrayList, long paramLong)
  {
    this.msgList = paramArrayList;
    this.timeUs = paramLong;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsPushClientReq";
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
    localJceDisplayer.display(this.msgList, "msgList");
    localJceDisplayer.display(this.timeUs, "timeUs");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.msgList, true);
    localJceDisplayer.displaySimple(this.timeUs, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsPushClientReq localTpnsPushClientReq;
    do
    {
      return false;
      localTpnsPushClientReq = (TpnsPushClientReq)paramObject;
    }
    while ((!JceUtil.equals(this.msgList, localTpnsPushClientReq.msgList)) || (!JceUtil.equals(this.timeUs, localTpnsPushClientReq.timeUs)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsPushClientReq";
  }

  public ArrayList getMsgList()
  {
    return this.msgList;
  }

  public long getTimeUs()
  {
    return this.timeUs;
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
    if (cache_msgList == null)
    {
      cache_msgList = new ArrayList();
      TpnsPushMsg localTpnsPushMsg = new TpnsPushMsg();
      cache_msgList.add(localTpnsPushMsg);
    }
    this.msgList = ((ArrayList)paramJceInputStream.read(cache_msgList, 0, true));
    this.timeUs = paramJceInputStream.read(this.timeUs, 1, true);
  }

  public void setMsgList(ArrayList paramArrayList)
  {
    this.msgList = paramArrayList;
  }

  public void setTimeUs(long paramLong)
  {
    this.timeUs = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.msgList, 0);
    paramJceOutputStream.write(this.timeUs, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq
 * JD-Core Version:    0.6.0
 */