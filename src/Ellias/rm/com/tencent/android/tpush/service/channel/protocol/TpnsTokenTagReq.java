package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsTokenTagReq extends JceStruct
  implements Cloneable
{
  public long accessId = 0L;
  public int flag = 0;
  public String tag = "";

  static
  {
    if (!TpnsTokenTagReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsTokenTagReq()
  {
  }

  public TpnsTokenTagReq(long paramLong, String paramString, int paramInt)
  {
    this.accessId = paramLong;
    this.tag = paramString;
    this.flag = paramInt;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsTokenTagReq";
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
    localJceDisplayer.display(this.accessId, "accessId");
    localJceDisplayer.display(this.tag, "tag");
    localJceDisplayer.display(this.flag, "flag");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.tag, true);
    localJceDisplayer.displaySimple(this.flag, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsTokenTagReq localTpnsTokenTagReq;
    do
    {
      return false;
      localTpnsTokenTagReq = (TpnsTokenTagReq)paramObject;
    }
    while ((!JceUtil.equals(this.accessId, localTpnsTokenTagReq.accessId)) || (!JceUtil.equals(this.tag, localTpnsTokenTagReq.tag)) || (!JceUtil.equals(this.flag, localTpnsTokenTagReq.flag)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsTokenTagReq";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public String getTag()
  {
    return this.tag;
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
    this.accessId = paramJceInputStream.read(this.accessId, 0, true);
    this.tag = paramJceInputStream.readString(1, true);
    this.flag = paramJceInputStream.read(this.flag, 2, true);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setTag(String paramString)
  {
    this.tag = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.accessId, 0);
    paramJceOutputStream.write(this.tag, 1);
    paramJceOutputStream.write(this.flag, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq
 * JD-Core Version:    0.6.0
 */