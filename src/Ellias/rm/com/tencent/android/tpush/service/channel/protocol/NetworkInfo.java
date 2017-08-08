package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class NetworkInfo extends JceStruct
  implements Cloneable
{
  public int ip = 0;
  public byte network = 0;
  public byte op = 0;

  static
  {
    if (!NetworkInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public NetworkInfo()
  {
  }

  public NetworkInfo(int paramInt, byte paramByte1, byte paramByte2)
  {
    this.ip = paramInt;
    this.network = paramByte1;
    this.op = paramByte2;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.NetworkInfo";
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
    localJceDisplayer.display(this.ip, "ip");
    localJceDisplayer.display(this.network, "network");
    localJceDisplayer.display(this.op, "op");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.ip, true);
    localJceDisplayer.displaySimple(this.network, true);
    localJceDisplayer.displaySimple(this.op, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    NetworkInfo localNetworkInfo;
    do
    {
      return false;
      localNetworkInfo = (NetworkInfo)paramObject;
    }
    while ((!JceUtil.equals(this.ip, localNetworkInfo.ip)) || (!JceUtil.equals(this.network, localNetworkInfo.network)) || (!JceUtil.equals(this.op, localNetworkInfo.op)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.NetworkInfo";
  }

  public int getIp()
  {
    return this.ip;
  }

  public byte getNetwork()
  {
    return this.network;
  }

  public byte getOp()
  {
    return this.op;
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
    this.ip = paramJceInputStream.read(this.ip, 0, true);
    this.network = paramJceInputStream.read(this.network, 1, true);
    this.op = paramJceInputStream.read(this.op, 2, false);
  }

  public void setIp(int paramInt)
  {
    this.ip = paramInt;
  }

  public void setNetwork(byte paramByte)
  {
    this.network = paramByte;
  }

  public void setOp(byte paramByte)
  {
    this.op = paramByte;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ip, 0);
    paramJceOutputStream.write(this.network, 1);
    paramJceOutputStream.write(this.op, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.NetworkInfo
 * JD-Core Version:    0.6.0
 */