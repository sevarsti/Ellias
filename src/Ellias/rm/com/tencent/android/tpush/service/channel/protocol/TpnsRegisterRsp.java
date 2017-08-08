package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsRegisterRsp extends JceStruct
  implements Cloneable
{
  public long confVersion = 0L;
  public String token = "";

  static
  {
    if (!TpnsRegisterRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsRegisterRsp()
  {
  }

  public TpnsRegisterRsp(long paramLong, String paramString)
  {
    this.confVersion = paramLong;
    this.token = paramString;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsRegisterRsp";
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
    localJceDisplayer.display(this.token, "token");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.confVersion, true);
    localJceDisplayer.displaySimple(this.token, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsRegisterRsp localTpnsRegisterRsp;
    do
    {
      return false;
      localTpnsRegisterRsp = (TpnsRegisterRsp)paramObject;
    }
    while ((!JceUtil.equals(this.confVersion, localTpnsRegisterRsp.confVersion)) || (!JceUtil.equals(this.token, localTpnsRegisterRsp.token)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsRegisterRsp";
  }

  public long getConfVersion()
  {
    return this.confVersion;
  }

  public String getToken()
  {
    return this.token;
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
    this.token = paramJceInputStream.readString(1, true);
  }

  public void setConfVersion(long paramLong)
  {
    this.confVersion = paramLong;
  }

  public void setToken(String paramString)
  {
    this.token = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.confVersion, 0);
    paramJceOutputStream.write(this.token, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp
 * JD-Core Version:    0.6.0
 */