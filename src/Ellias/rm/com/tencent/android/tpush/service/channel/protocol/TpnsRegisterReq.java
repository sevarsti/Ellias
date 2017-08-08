package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TpnsRegisterReq extends JceStruct
  implements Cloneable
{
  static DeviceInfo cache_deviceInfo;
  static MutableInfo cache_mutableInfo;
  public long accessId = 0L;
  public String accessKey = "";
  public String account = "";
  public String appCert = "";
  public String appVersion = "";
  public String deviceId = "";
  public DeviceInfo deviceInfo = null;
  public short deviceType = 0;
  public byte keyEncrypted = 0;
  public MutableInfo mutableInfo = null;
  public String ticket = "";
  public short ticketType = 0;
  public String token = "";
  public short updateAutoTag = 0;
  public short version = 0;

  static
  {
    if (!TpnsRegisterReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TpnsRegisterReq()
  {
  }

  public TpnsRegisterReq(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, short paramShort1, short paramShort2, DeviceInfo paramDeviceInfo, String paramString6, short paramShort3, byte paramByte, MutableInfo paramMutableInfo, short paramShort4, String paramString7)
  {
    this.accessId = paramLong;
    this.accessKey = paramString1;
    this.deviceId = paramString2;
    this.appCert = paramString3;
    this.account = paramString4;
    this.ticket = paramString5;
    this.ticketType = paramShort1;
    this.deviceType = paramShort2;
    this.deviceInfo = paramDeviceInfo;
    this.token = paramString6;
    this.version = paramShort3;
    this.keyEncrypted = paramByte;
    this.mutableInfo = paramMutableInfo;
    this.updateAutoTag = paramShort4;
    this.appVersion = paramString7;
  }

  public String className()
  {
    return "TPNS_CLIENT_PROTOCOL.TpnsRegisterReq";
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
    localJceDisplayer.display(this.deviceId, "deviceId");
    localJceDisplayer.display(this.appCert, "appCert");
    localJceDisplayer.display(this.account, "account");
    localJceDisplayer.display(this.ticket, "ticket");
    localJceDisplayer.display(this.ticketType, "ticketType");
    localJceDisplayer.display(this.deviceType, "deviceType");
    localJceDisplayer.display(this.deviceInfo, "deviceInfo");
    localJceDisplayer.display(this.token, "token");
    localJceDisplayer.display(this.version, "version");
    localJceDisplayer.display(this.keyEncrypted, "keyEncrypted");
    localJceDisplayer.display(this.mutableInfo, "mutableInfo");
    localJceDisplayer.display(this.updateAutoTag, "updateAutoTag");
    localJceDisplayer.display(this.appVersion, "appVersion");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.accessId, true);
    localJceDisplayer.displaySimple(this.accessKey, true);
    localJceDisplayer.displaySimple(this.deviceId, true);
    localJceDisplayer.displaySimple(this.appCert, true);
    localJceDisplayer.displaySimple(this.account, true);
    localJceDisplayer.displaySimple(this.ticket, true);
    localJceDisplayer.displaySimple(this.ticketType, true);
    localJceDisplayer.displaySimple(this.deviceType, true);
    localJceDisplayer.displaySimple(this.deviceInfo, true);
    localJceDisplayer.displaySimple(this.token, true);
    localJceDisplayer.displaySimple(this.version, true);
    localJceDisplayer.displaySimple(this.keyEncrypted, true);
    localJceDisplayer.displaySimple(this.mutableInfo, true);
    localJceDisplayer.displaySimple(this.updateAutoTag, true);
    localJceDisplayer.displaySimple(this.appVersion, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TpnsRegisterReq localTpnsRegisterReq;
    do
    {
      return false;
      localTpnsRegisterReq = (TpnsRegisterReq)paramObject;
    }
    while ((!JceUtil.equals(this.accessId, localTpnsRegisterReq.accessId)) || (!JceUtil.equals(this.accessKey, localTpnsRegisterReq.accessKey)) || (!JceUtil.equals(this.deviceId, localTpnsRegisterReq.deviceId)) || (!JceUtil.equals(this.appCert, localTpnsRegisterReq.appCert)) || (!JceUtil.equals(this.account, localTpnsRegisterReq.account)) || (!JceUtil.equals(this.ticket, localTpnsRegisterReq.ticket)) || (!JceUtil.equals(this.ticketType, localTpnsRegisterReq.ticketType)) || (!JceUtil.equals(this.deviceType, localTpnsRegisterReq.deviceType)) || (!JceUtil.equals(this.deviceInfo, localTpnsRegisterReq.deviceInfo)) || (!JceUtil.equals(this.token, localTpnsRegisterReq.token)) || (!JceUtil.equals(this.version, localTpnsRegisterReq.version)) || (!JceUtil.equals(this.keyEncrypted, localTpnsRegisterReq.keyEncrypted)) || (!JceUtil.equals(this.mutableInfo, localTpnsRegisterReq.mutableInfo)) || (!JceUtil.equals(this.updateAutoTag, localTpnsRegisterReq.updateAutoTag)) || (!JceUtil.equals(this.appVersion, localTpnsRegisterReq.appVersion)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.android.tpush.service.channel.protocol.TPNS_CLIENT_PROTOCOL.TpnsRegisterReq";
  }

  public long getAccessId()
  {
    return this.accessId;
  }

  public String getAccessKey()
  {
    return this.accessKey;
  }

  public String getAccount()
  {
    return this.account;
  }

  public String getAppCert()
  {
    return this.appCert;
  }

  public String getAppVersion()
  {
    return this.appVersion;
  }

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public DeviceInfo getDeviceInfo()
  {
    return this.deviceInfo;
  }

  public short getDeviceType()
  {
    return this.deviceType;
  }

  public byte getKeyEncrypted()
  {
    return this.keyEncrypted;
  }

  public MutableInfo getMutableInfo()
  {
    return this.mutableInfo;
  }

  public String getTicket()
  {
    return this.ticket;
  }

  public short getTicketType()
  {
    return this.ticketType;
  }

  public String getToken()
  {
    return this.token;
  }

  public short getUpdateAutoTag()
  {
    return this.updateAutoTag;
  }

  public short getVersion()
  {
    return this.version;
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
    this.deviceId = paramJceInputStream.readString(2, true);
    this.appCert = paramJceInputStream.readString(3, true);
    this.account = paramJceInputStream.readString(4, false);
    this.ticket = paramJceInputStream.readString(5, false);
    this.ticketType = paramJceInputStream.read(this.ticketType, 6, false);
    this.deviceType = paramJceInputStream.read(this.deviceType, 7, false);
    if (cache_deviceInfo == null)
      cache_deviceInfo = new DeviceInfo();
    this.deviceInfo = ((DeviceInfo)paramJceInputStream.read(cache_deviceInfo, 8, false));
    this.token = paramJceInputStream.readString(9, false);
    this.version = paramJceInputStream.read(this.version, 10, false);
    this.keyEncrypted = paramJceInputStream.read(this.keyEncrypted, 11, false);
    if (cache_mutableInfo == null)
      cache_mutableInfo = new MutableInfo();
    this.mutableInfo = ((MutableInfo)paramJceInputStream.read(cache_mutableInfo, 12, false));
    this.updateAutoTag = paramJceInputStream.read(this.updateAutoTag, 13, false);
    this.appVersion = paramJceInputStream.readString(14, false);
  }

  public void setAccessId(long paramLong)
  {
    this.accessId = paramLong;
  }

  public void setAccessKey(String paramString)
  {
    this.accessKey = paramString;
  }

  public void setAccount(String paramString)
  {
    this.account = paramString;
  }

  public void setAppCert(String paramString)
  {
    this.appCert = paramString;
  }

  public void setAppVersion(String paramString)
  {
    this.appVersion = paramString;
  }

  public void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }

  public void setDeviceInfo(DeviceInfo paramDeviceInfo)
  {
    this.deviceInfo = paramDeviceInfo;
  }

  public void setDeviceType(short paramShort)
  {
    this.deviceType = paramShort;
  }

  public void setKeyEncrypted(byte paramByte)
  {
    this.keyEncrypted = paramByte;
  }

  public void setMutableInfo(MutableInfo paramMutableInfo)
  {
    this.mutableInfo = paramMutableInfo;
  }

  public void setTicket(String paramString)
  {
    this.ticket = paramString;
  }

  public void setTicketType(short paramShort)
  {
    this.ticketType = paramShort;
  }

  public void setToken(String paramString)
  {
    this.token = paramString;
  }

  public void setUpdateAutoTag(short paramShort)
  {
    this.updateAutoTag = paramShort;
  }

  public void setVersion(short paramShort)
  {
    this.version = paramShort;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.accessId, 0);
    paramJceOutputStream.write(this.accessKey, 1);
    paramJceOutputStream.write(this.deviceId, 2);
    paramJceOutputStream.write(this.appCert, 3);
    if (this.account != null)
      paramJceOutputStream.write(this.account, 4);
    if (this.ticket != null)
      paramJceOutputStream.write(this.ticket, 5);
    paramJceOutputStream.write(this.ticketType, 6);
    paramJceOutputStream.write(this.deviceType, 7);
    if (this.deviceInfo != null)
      paramJceOutputStream.write(this.deviceInfo, 8);
    if (this.token != null)
      paramJceOutputStream.write(this.token, 9);
    paramJceOutputStream.write(this.version, 10);
    paramJceOutputStream.write(this.keyEncrypted, 11);
    if (this.mutableInfo != null)
      paramJceOutputStream.write(this.mutableInfo, 12);
    paramJceOutputStream.write(this.updateAutoTag, 13);
    if (this.appVersion != null)
      paramJceOutputStream.write(this.appVersion, 14);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq
 * JD-Core Version:    0.6.0
 */