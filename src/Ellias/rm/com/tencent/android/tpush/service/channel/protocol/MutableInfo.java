package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class MutableInfo extends JceStruct
  implements Cloneable
{
  public String bssid = "";
  public String mac = "";
  public String ssid = "";
  public String wflist = "";

  static
  {
    if (!MutableInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public MutableInfo()
  {
  }

  public MutableInfo(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.ssid = paramString1;
    this.bssid = paramString2;
    this.mac = paramString3;
    this.wflist = paramString4;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.MutableInfo";
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
    localJceDisplayer.display(this.ssid, "ssid");
    localJceDisplayer.display(this.bssid, "bssid");
    localJceDisplayer.display(this.mac, "mac");
    localJceDisplayer.display(this.wflist, "wflist");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.ssid, true);
    localJceDisplayer.displaySimple(this.bssid, true);
    localJceDisplayer.displaySimple(this.mac, true);
    localJceDisplayer.displaySimple(this.wflist, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    MutableInfo localMutableInfo;
    do
    {
      return false;
      localMutableInfo = (MutableInfo)paramObject;
    }
    while ((!JceUtil.equals(this.ssid, localMutableInfo.ssid)) || (!JceUtil.equals(this.bssid, localMutableInfo.bssid)) || (!JceUtil.equals(this.mac, localMutableInfo.mac)) || (!JceUtil.equals(this.wflist, localMutableInfo.wflist)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.MutableInfo";
  }

  public String getBssid()
  {
    return this.bssid;
  }

  public String getMac()
  {
    return this.mac;
  }

  public String getSsid()
  {
    return this.ssid;
  }

  public String getWflist()
  {
    return this.wflist;
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
    this.ssid = paramJceInputStream.readString(0, false);
    this.bssid = paramJceInputStream.readString(1, false);
    this.mac = paramJceInputStream.readString(2, false);
    this.wflist = paramJceInputStream.readString(3, false);
  }

  public void setBssid(String paramString)
  {
    this.bssid = paramString;
  }

  public void setMac(String paramString)
  {
    this.mac = paramString;
  }

  public void setSsid(String paramString)
  {
    this.ssid = paramString;
  }

  public void setWflist(String paramString)
  {
    this.wflist = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.ssid != null)
      paramJceOutputStream.write(this.ssid, 0);
    if (this.bssid != null)
      paramJceOutputStream.write(this.bssid, 1);
    if (this.mac != null)
      paramJceOutputStream.write(this.mac, 2);
    if (this.wflist != null)
      paramJceOutputStream.write(this.wflist, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.MutableInfo
 * JD-Core Version:    0.6.0
 */