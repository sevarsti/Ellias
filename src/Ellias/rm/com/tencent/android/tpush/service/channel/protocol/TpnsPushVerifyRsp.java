package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsPushVerifyRsp extends JceStruct
  implements Cloneable
{
  public byte padding = 0;

  static
  {
    if (!TpnsPushVerifyRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsPushVerifyRsp()
  {
  }

  public TpnsPushVerifyRsp(byte paramByte)
  {
    this.padding = paramByte;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsPushVerifyRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.padding, "padding");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.padding, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsPushVerifyRsp localTpnsPushVerifyRsp = (TpnsPushVerifyRsp)paramObject;
    return JceUtil.equals(this.padding, localTpnsPushVerifyRsp.padding);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsPushVerifyRsp";
  }

  public byte getPadding()
  {
    return this.padding;
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
    this.padding = paramJceInputStream.read(this.padding, 0, true);
  }

  public void setPadding(byte paramByte)
  {
    this.padding = paramByte;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.padding, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyRsp
 * JD-Core Version:    0.6.0
 */