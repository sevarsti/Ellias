package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class UnregInfo extends JceStruct
  implements Cloneable
{
  static AppInfo cache_appInfo;
  public AppInfo appInfo = null;
  public byte isUninstall = 0;
  public long timestamp = 0L;

  static
  {
    if (!UnregInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public UnregInfo()
  {
  }

  public UnregInfo(AppInfo paramAppInfo, byte paramByte, long paramLong)
  {
    this.appInfo = paramAppInfo;
    this.isUninstall = paramByte;
    this.timestamp = paramLong;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.UnregInfo";
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
    localJceDisplayer.display(this.appInfo, "appInfo");
    localJceDisplayer.display(this.isUninstall, "isUninstall");
    localJceDisplayer.display(this.timestamp, "timestamp");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.appInfo, true);
    localJceDisplayer.displaySimple(this.isUninstall, true);
    localJceDisplayer.displaySimple(this.timestamp, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    UnregInfo localUnregInfo;
    do
    {
      return false;
      localUnregInfo = (UnregInfo)paramObject;
    }
    while ((!JceUtil.equals(this.appInfo, localUnregInfo.appInfo)) || (!JceUtil.equals(this.isUninstall, localUnregInfo.isUninstall)) || (!JceUtil.equals(this.timestamp, localUnregInfo.timestamp)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.UnregInfo";
  }

  public AppInfo getAppInfo()
  {
    return this.appInfo;
  }

  public byte getIsUninstall()
  {
    return this.isUninstall;
  }

  public long getTimestamp()
  {
    return this.timestamp;
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
    if (cache_appInfo == null)
      cache_appInfo = new AppInfo();
    this.appInfo = ((AppInfo)paramJceInputStream.read(cache_appInfo, 0, true));
    this.isUninstall = paramJceInputStream.read(this.isUninstall, 1, true);
    this.timestamp = paramJceInputStream.read(this.timestamp, 2, false);
  }

  public void setAppInfo(AppInfo paramAppInfo)
  {
    this.appInfo = paramAppInfo;
  }

  public void setIsUninstall(byte paramByte)
  {
    this.isUninstall = paramByte;
  }

  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.appInfo, 0);
    paramJceOutputStream.write(this.isUninstall, 1);
    paramJceOutputStream.write(this.timestamp, 2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.UnregInfo
 * JD-Core Version:    0.6.0
 */