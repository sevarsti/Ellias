package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsRedirectReq extends JceStruct
  implements Cloneable
{
  public byte network = 0;
  public byte op = 0;

  static
  {
    if (!TpnsRedirectReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsRedirectReq()
  {
  }

  public TpnsRedirectReq(byte paramByte1, byte paramByte2)
  {
    this.network = paramByte1;
    this.op = paramByte2;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsRedirectReq";
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
    localJceDisplayer.display(this.network, "network");
    localJceDisplayer.display(this.op, "op");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.network, true);
    localJceDisplayer.displaySimple(this.op, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsRedirectReq localTpnsRedirectReq;
    do
    {
      return false;
      localTpnsRedirectReq = (TpnsRedirectReq)paramObject;
    }
    while ((!JceUtil.equals(this.network, localTpnsRedirectReq.network)) || (!JceUtil.equals(this.op, localTpnsRedirectReq.op)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsRedirectReq";
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
    this.network = paramJceInputStream.read(this.network, 0, false);
    this.op = paramJceInputStream.read(this.op, 1, false);
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
    paramJceOutputStream.write(this.network, 0);
    paramJceOutputStream.write(this.op, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq
 * JD-Core Version:    0.6.0
 */