package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsConfigRsp extends JceStruct
  implements Cloneable
{
  public String confContent = "";
  public long confVersion = 0L;

  static
  {
    if (!TpnsConfigRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsConfigRsp()
  {
  }

  public TpnsConfigRsp(long paramLong, String paramString)
  {
    this.confVersion = paramLong;
    this.confContent = paramString;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsConfigRsp";
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
    localJceDisplayer.display(this.confContent, "confContent");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.confVersion, true);
    localJceDisplayer.displaySimple(this.confContent, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsConfigRsp localTpnsConfigRsp;
    do
    {
      return false;
      localTpnsConfigRsp = (TpnsConfigRsp)paramObject;
    }
    while ((!JceUtil.equals(this.confVersion, localTpnsConfigRsp.confVersion)) || (!JceUtil.equals(this.confContent, localTpnsConfigRsp.confContent)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsConfigRsp";
  }

  public String getConfContent()
  {
    return this.confContent;
  }

  public long getConfVersion()
  {
    return this.confVersion;
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
    this.confContent = paramJceInputStream.readString(1, true);
  }

  public void setConfContent(String paramString)
  {
    this.confContent = paramString;
  }

  public void setConfVersion(long paramLong)
  {
    this.confVersion = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.confVersion, 0);
    paramJceOutputStream.write(this.confContent, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp
 * JD-Core Version:    0.6.0
 */