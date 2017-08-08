package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsRedirectRsp extends JceStruct
  implements Cloneable
{
  public long ip = 0L;
  public int port = 0;

  static
  {
    if (!TpnsRedirectRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsRedirectRsp()
  {
  }

  public TpnsRedirectRsp(long paramLong, int paramInt)
  {
    this.ip = paramLong;
    this.port = paramInt;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsRedirectRsp";
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
    localJceDisplayer.display(this.port, "port");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.ip, true);
    localJceDisplayer.displaySimple(this.port, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsRedirectRsp localTpnsRedirectRsp;
    do
    {
      return false;
      localTpnsRedirectRsp = (TpnsRedirectRsp)paramObject;
    }
    while ((!JceUtil.equals(this.ip, localTpnsRedirectRsp.ip)) || (!JceUtil.equals(this.port, localTpnsRedirectRsp.port)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsRedirectRsp";
  }

  public long getIp()
  {
    return this.ip;
  }

  public int getPort()
  {
    return this.port;
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
    this.ip = paramJceInputStream.read(this.ip, 0, false);
    this.port = paramJceInputStream.read(this.port, 1, false);
  }

  public void setIp(long paramLong)
  {
    this.ip = paramLong;
  }

  public void setPort(int paramInt)
  {
    this.port = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ip, 0);
    paramJceOutputStream.write(this.port, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp
 * JD-Core Version:    0.6.0
 */