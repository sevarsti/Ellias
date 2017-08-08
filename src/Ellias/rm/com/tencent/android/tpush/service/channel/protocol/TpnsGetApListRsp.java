package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsGetApListRsp extends JceStruct
  implements Cloneable
{
  static ApList cache_apList;
  public ApList apList = null;

  static
  {
    if (!TpnsGetApListRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsGetApListRsp()
  {
  }

  public TpnsGetApListRsp(ApList paramApList)
  {
    this.apList = paramApList;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsGetApListRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.apList, "apList");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.apList, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TpnsGetApListRsp localTpnsGetApListRsp = (TpnsGetApListRsp)paramObject;
    return JceUtil.equals(this.apList, localTpnsGetApListRsp.apList);
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsGetApListRsp";
  }

  public ApList getApList()
  {
    return this.apList;
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
    if (cache_apList == null)
      cache_apList = new ApList();
    this.apList = ((ApList)paramJceInputStream.read(cache_apList, 0, true));
  }

  public void setApList(ApList paramApList)
  {
    this.apList = paramApList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.apList, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp
 * JD-Core Version:    0.6.0
 */