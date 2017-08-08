package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class AppInfo extends JceStruct
  implements Cloneable
{
  public long accessId = 0L;
  public String accessKey = "";
  public String appCert = "";
  public byte keyEncrypted = 0;

  static
  {
    if (!AppInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AppInfo()
  {
  }

  public AppInfo(long paramLong, String paramString1, String paramString2, byte paramByte)
  {
    this.accessId = paramLong;
    this.accessKey = paramString1;
    this.appCert = paramString2;
    this.keyEncrypted = paramByte;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.AppInfo";
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
    localJceDisplayer.display(this.accessKey, "accessKey");
    localJceDisplayer.display(this.appCert, "appCert");
    localJceDisplayer.display(this.keyEncrypted, "keyEncrypted");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.accessKey, true);
    localJceDisplayer.displaySimple(this.appCert, true);
    localJceDisplayer.displaySimple(this.keyEncrypted, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    AppInfo localAppInfo;
    do
    {
      return false;
      localAppInfo = (AppInfo)paramObject;
    }
    while ((!JceUtil.equals(this.accessId, localAppInfo.accessId)) || (!JceUtil.equals(this.accessKey, localAppInfo.accessKey)) || (!JceUtil.equals(this.appCert, localAppInfo.appCert)) || (!JceUtil.equals(this.keyEncrypted, localAppInfo.keyEncrypted)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.AppInfo";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public String getAccessKey()
  {
    return this.accessKey;
  }

  public String getAppCert()
  {
    return this.appCert;
  }

  public byte getKeyEncrypted()
  {
    return this.keyEncrypted;
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
    this.accessKey = paramJceInputStream.readString(1, true);
    this.appCert = paramJceInputStream.readString(2, true);
    this.keyEncrypted = paramJceInputStream.read(this.keyEncrypted, 3, false);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setAccessKey(String paramString)
  {
    this.accessKey = paramString;
  }

  public void setAppCert(String paramString)
  {
    this.appCert = paramString;
  }

  public void setKeyEncrypted(byte paramByte)
  {
    this.keyEncrypted = paramByte;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.accessId, 0);
    paramJceOutputStream.write(this.accessKey, 1);
    paramJceOutputStream.write(this.appCert, 2);
    paramJceOutputStream.write(this.keyEncrypted, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.AppInfo
 * JD-Core Version:    0.6.0
 */