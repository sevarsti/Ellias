package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class AppServerAuthInfo extends JceStruct
  implements Cloneable
{
  public long accessId = 0L;
  public String secretKey = "";

  static
  {
    if (!AppServerAuthInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AppServerAuthInfo()
  {
  }

  public AppServerAuthInfo(long paramLong, String paramString)
  {
    this.accessId = paramLong;
    this.secretKey = paramString;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.AppServerAuthInfo";
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
    localJceDisplayer.display(this.secretKey, "secretKey");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.secretKey, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    AppServerAuthInfo localAppServerAuthInfo;
    do
    {
      return false;
      localAppServerAuthInfo = (AppServerAuthInfo)paramObject;
    }
    while ((!JceUtil.equals(this.accessId, localAppServerAuthInfo.accessId)) || (!JceUtil.equals(this.secretKey, localAppServerAuthInfo.secretKey)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.AppServerAuthInfo";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public String getSecretKey()
  {
    return this.secretKey;
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
    this.secretKey = paramJceInputStream.readString(1, true);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setSecretKey(String paramString)
  {
    this.secretKey = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.accessId, 0);
    paramJceOutputStream.write(this.secretKey, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.AppServerAuthInfo
 * JD-Core Version:    0.6.0
 */