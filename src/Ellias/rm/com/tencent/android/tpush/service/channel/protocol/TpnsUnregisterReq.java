package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsUnregisterReq extends JceStruct
  implements Cloneable
{
  static UnregInfo cache_unregInfo;
  public short deviceType = 0;
  public UnregInfo unregInfo = null;

  static
  {
    if (!TpnsUnregisterReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsUnregisterReq()
  {
  }

  public TpnsUnregisterReq(UnregInfo paramUnregInfo, short paramShort)
  {
    this.unregInfo = paramUnregInfo;
    this.deviceType = paramShort;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsUnregisterReq";
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
    localJceDisplayer.display(this.unregInfo, "unregInfo");
    localJceDisplayer.display(this.deviceType, "deviceType");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.unregInfo, true);
    localJceDisplayer.displaySimple(this.deviceType, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsUnregisterReq localTpnsUnregisterReq;
    do
    {
      return false;
      localTpnsUnregisterReq = (TpnsUnregisterReq)paramObject;
    }
    while ((!JceUtil.equals(this.unregInfo, localTpnsUnregisterReq.unregInfo)) || (!JceUtil.equals(this.deviceType, localTpnsUnregisterReq.deviceType)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsUnregisterReq";
  }

  public short getDeviceType()
  {
    return this.deviceType;
  }

  public UnregInfo getUnregInfo()
  {
    return this.unregInfo;
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
    if (cache_unregInfo == null)
      cache_unregInfo = new UnregInfo();
    this.unregInfo = ((UnregInfo)paramJceInputStream.read(cache_unregInfo, 0, true));
    this.deviceType = paramJceInputStream.read(this.deviceType, 1, false);
  }

  public void setDeviceType(short paramShort)
  {
    this.deviceType = paramShort;
  }

  public void setUnregInfo(UnregInfo paramUnregInfo)
  {
    this.unregInfo = paramUnregInfo;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.unregInfo, 0);
    paramJceOutputStream.write(this.deviceType, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq
 * JD-Core Version:    0.6.0
 */