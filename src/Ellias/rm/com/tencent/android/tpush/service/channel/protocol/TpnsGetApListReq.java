package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsGetApListReq extends JceStruct
  implements Cloneable
{
  static NetworkInfo cache_netInfo;
  public NetworkInfo netInfo = null;

  static
  {
    if (!TpnsGetApListReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsGetApListReq()
  {
  }

  public TpnsGetApListReq(NetworkInfo paramNetworkInfo)
  {
    this.netInfo = paramNetworkInfo;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsGetApListReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.netInfo, "netInfo");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.netInfo, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsGetApListReq localTpnsGetApListReq = (TpnsGetApListReq)paramObject;
    return JceUtil.equals(this.netInfo, localTpnsGetApListReq.netInfo);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsGetApListReq";
  }

  public NetworkInfo getNetInfo()
  {
    return this.netInfo;
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
    if (cache_netInfo == null)
      cache_netInfo = new NetworkInfo();
    this.netInfo = ((NetworkInfo)paramJceInputStream.read(cache_netInfo, 0, true));
  }

  public void setNetInfo(NetworkInfo paramNetworkInfo)
  {
    this.netInfo = paramNetworkInfo;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.netInfo, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq
 * JD-Core Version:    0.6.0
 */