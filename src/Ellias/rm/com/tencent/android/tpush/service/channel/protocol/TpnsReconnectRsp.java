package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TpnsReconnectRsp extends JceStruct
  implements Cloneable
{
  static ArrayList cache_appOfflinePushMsgList;
  public ArrayList appOfflinePushMsgList = null;
  public long confVersion = 0L;
  public long timeUs = 0L;

  static
  {
    if (!TpnsReconnectRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsReconnectRsp()
  {
  }

  public TpnsReconnectRsp(long paramLong1, ArrayList paramArrayList, long paramLong2)
  {
    this.confVersion = paramLong1;
    this.appOfflinePushMsgList = paramArrayList;
    this.timeUs = paramLong2;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsReconnectRsp";
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
    localJceDisplayer.display(this.confVersion, "confVersion");
    localJceDisplayer.display(this.appOfflinePushMsgList, "appOfflinePushMsgList");
    localJceDisplayer.display(this.timeUs, "timeUs");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.confVersion, true);
    localJceDisplayer.displaySimple(this.appOfflinePushMsgList, true);
    localJceDisplayer.displaySimple(this.timeUs, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsReconnectRsp localTpnsReconnectRsp;
    do
    {
      return false;
      localTpnsReconnectRsp = (TpnsReconnectRsp)paramObject;
    }
    while ((!JceUtil.equals(this.confVersion, localTpnsReconnectRsp.confVersion)) || (!JceUtil.equals(this.appOfflinePushMsgList, localTpnsReconnectRsp.appOfflinePushMsgList)) || (!JceUtil.equals(this.timeUs, localTpnsReconnectRsp.timeUs)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsReconnectRsp";
  }

  public ArrayList getAppOfflinePushMsgList()
  {
    return this.appOfflinePushMsgList;
  }

  public long getConfVersion()
  {
    return this.confVersion;
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
    this.confVersion = paramJceInputStream.read(this.confVersion, 0, true);
    if (cache_appOfflinePushMsgList == null)
    {
      cache_appOfflinePushMsgList = new ArrayList();
      TpnsPushMsg localTpnsPushMsg = new TpnsPushMsg();
      cache_appOfflinePushMsgList.add(localTpnsPushMsg);
    }
    this.appOfflinePushMsgList = ((ArrayList)paramJceInputStream.read(cache_appOfflinePushMsgList, 1, false));
    this.timeUs = paramJceInputStream.read(this.timeUs, 2, false);
  }

  public void setAppOfflinePushMsgList(ArrayList paramArrayList)
  {
    this.appOfflinePushMsgList = paramArrayList;
  }

  public void setConfVersion(long paramLong)
  {
    this.confVersion = paramLong;
  }

  public void setTimeUs(long paramLong)
  {
    this.timeUs = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.confVersion, 0);
    if (this.appOfflinePushMsgList != null)
      paramJceOutputStream.write(this.appOfflinePushMsgList, 1);
    paramJceOutputStream.write(this.timeUs, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp
 * JD-Core Version:    0.6.0
 */