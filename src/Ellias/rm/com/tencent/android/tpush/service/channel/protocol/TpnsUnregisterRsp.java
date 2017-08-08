package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsUnregisterRsp extends JceStruct
  implements Cloneable
{
  public byte unregResult = 0;

  static
  {
    if (!TpnsUnregisterRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsUnregisterRsp()
  {
  }

  public TpnsUnregisterRsp(byte paramByte)
  {
    this.unregResult = paramByte;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsUnregisterRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.unregResult, "unregResult");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.unregResult, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsUnregisterRsp localTpnsUnregisterRsp = (TpnsUnregisterRsp)paramObject;
    return JceUtil.equals(this.unregResult, localTpnsUnregisterRsp.unregResult);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsUnregisterRsp";
  }

  public byte getUnregResult()
  {
    return this.unregResult;
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
    this.unregResult = paramJceInputStream.read(this.unregResult, 0, true);
  }

  public void setUnregResult(byte paramByte)
  {
    this.unregResult = paramByte;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.unregResult, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterRsp
 * JD-Core Version:    0.6.0
 */