package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class DeviceInfo extends JceStruct
  implements Cloneable
{
  public String apiLevel = "";
  public String imei = "";
  public String manu = "";
  public String model = "";
  public String network = "";
  public String os = "";
  public String resolution = "";
  public String sdCard = "";
  public String sdDouble = "";
  public String sdkVersion = "";

  static
  {
    if (!DeviceInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public DeviceInfo()
  {
  }

  public DeviceInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
  {
    this.imei = paramString1;
    this.model = paramString2;
    this.os = paramString3;
    this.network = paramString4;
    this.sdCard = paramString5;
    this.sdDouble = paramString6;
    this.resolution = paramString7;
    this.manu = paramString8;
    this.apiLevel = paramString9;
    this.sdkVersion = paramString10;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.DeviceInfo";
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
    localJceDisplayer.display(this.imei, "imei");
    localJceDisplayer.display(this.model, "model");
    localJceDisplayer.display(this.os, "os");
    localJceDisplayer.display(this.network, "network");
    localJceDisplayer.display(this.sdCard, "sdCard");
    localJceDisplayer.display(this.sdDouble, "sdDouble");
    localJceDisplayer.display(this.resolution, "resolution");
    localJceDisplayer.display(this.manu, "manu");
    localJceDisplayer.display(this.apiLevel, "apiLevel");
    localJceDisplayer.display(this.sdkVersion, "sdkVersion");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.imei, true);
    localJceDisplayer.displaySimple(this.model, true);
    localJceDisplayer.displaySimple(this.os, true);
    localJceDisplayer.displaySimple(this.network, true);
    localJceDisplayer.displaySimple(this.sdCard, true);
    localJceDisplayer.displaySimple(this.sdDouble, true);
    localJceDisplayer.displaySimple(this.resolution, true);
    localJceDisplayer.displaySimple(this.manu, true);
    localJceDisplayer.displaySimple(this.apiLevel, true);
    localJceDisplayer.displaySimple(this.sdkVersion, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    DeviceInfo localDeviceInfo;
    do
    {
      return false;
      localDeviceInfo = (DeviceInfo)paramObject;
    }
    while ((!JceUtil.equals(this.imei, localDeviceInfo.imei)) || (!JceUtil.equals(this.model, localDeviceInfo.model)) || (!JceUtil.equals(this.os, localDeviceInfo.os)) || (!JceUtil.equals(this.network, localDeviceInfo.network)) || (!JceUtil.equals(this.sdCard, localDeviceInfo.sdCard)) || (!JceUtil.equals(this.sdDouble, localDeviceInfo.sdDouble)) || (!JceUtil.equals(this.resolution, localDeviceInfo.resolution)) || (!JceUtil.equals(this.manu, localDeviceInfo.manu)) || (!JceUtil.equals(this.apiLevel, localDeviceInfo.apiLevel)) || (!JceUtil.equals(this.sdkVersion, localDeviceInfo.sdkVersion)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.DeviceInfo";
  }

  public String getApiLevel()
  {
    return this.apiLevel;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getManu()
  {
    return this.manu;
  }

  public String getModel()
  {
    return this.model;
  }

  public String getNetwork()
  {
    return this.network;
  }

  public String getOs()
  {
    return this.os;
  }

  public String getResolution()
  {
    return this.resolution;
  }

  public String getSdCard()
  {
    return this.sdCard;
  }

  public String getSdDouble()
  {
    return this.sdDouble;
  }

  public String getSdkVersion()
  {
    return this.sdkVersion;
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
    this.imei = paramJceInputStream.readString(0, false);
    this.model = paramJceInputStream.readString(1, false);
    this.os = paramJceInputStream.readString(2, false);
    this.network = paramJceInputStream.readString(3, false);
    this.sdCard = paramJceInputStream.readString(4, false);
    this.sdDouble = paramJceInputStream.readString(5, false);
    this.resolution = paramJceInputStream.readString(6, false);
    this.manu = paramJceInputStream.readString(7, false);
    this.apiLevel = paramJceInputStream.readString(8, false);
    this.sdkVersion = paramJceInputStream.readString(9, false);
  }

  public void setApiLevel(String paramString)
  {
    this.apiLevel = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setManu(String paramString)
  {
    this.manu = paramString;
  }

  public void setModel(String paramString)
  {
    this.model = paramString;
  }

  public void setNetwork(String paramString)
  {
    this.network = paramString;
  }

  public void setOs(String paramString)
  {
    this.os = paramString;
  }

  public void setResolution(String paramString)
  {
    this.resolution = paramString;
  }

  public void setSdCard(String paramString)
  {
    this.sdCard = paramString;
  }

  public void setSdDouble(String paramString)
  {
    this.sdDouble = paramString;
  }

  public void setSdkVersion(String paramString)
  {
    this.sdkVersion = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.imei != null)
      paramJceOutputStream.write(this.imei, 0);
    if (this.model != null)
      paramJceOutputStream.write(this.model, 1);
    if (this.os != null)
      paramJceOutputStream.write(this.os, 2);
    if (this.network != null)
      paramJceOutputStream.write(this.network, 3);
    if (this.sdCard != null)
      paramJceOutputStream.write(this.sdCard, 4);
    if (this.sdDouble != null)
      paramJceOutputStream.write(this.sdDouble, 5);
    if (this.resolution != null)
      paramJceOutputStream.write(this.resolution, 6);
    if (this.manu != null)
      paramJceOutputStream.write(this.manu, 7);
    if (this.apiLevel != null)
      paramJceOutputStream.write(this.apiLevel, 8);
    if (this.sdkVersion != null)
      paramJceOutputStream.write(this.sdkVersion, 9);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.DeviceInfo
 * JD-Core Version:    0.6.0
 */