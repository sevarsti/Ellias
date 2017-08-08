package common;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class RequestPackage extends JceStruct
  implements Cloneable
{
  static byte[] cache_sBuffer;
  public String bundleId = "";
  public int cmd = 0;
  public byte encryType = 0;
  public String hardware_os = "";
  public byte platformId = 0;
  public String productId = "";
  public String productIdentity = "";
  public String productVersion = "";
  public String qimei = "";
  public String qua = "";
  public String requestId = "";
  public String reserved = "";
  public byte[] sBuffer = null;
  public String sdkId = "";
  public String sdkVersion = "";
  public byte version = 0;
  public byte zipType = 0;

  static
  {
    if (!RequestPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public RequestPackage()
  {
  }

  public RequestPackage(byte paramByte1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, byte[] paramArrayOfByte, String paramString5, String paramString6, byte paramByte2, byte paramByte3, String paramString7, String paramString8, String paramString9, byte paramByte4, String paramString10, String paramString11)
  {
    this.platformId = paramByte1;
    this.productId = paramString1;
    this.productVersion = paramString2;
    this.sdkId = paramString3;
    this.sdkVersion = paramString4;
    this.cmd = paramInt;
    this.sBuffer = paramArrayOfByte;
    this.hardware_os = paramString5;
    this.qua = paramString6;
    this.encryType = paramByte2;
    this.zipType = paramByte3;
    this.productIdentity = paramString7;
    this.reserved = paramString8;
    this.bundleId = paramString9;
    this.version = paramByte4;
    this.qimei = paramString10;
    this.requestId = paramString11;
  }

  public final String className()
  {
    return "common.RequestPackage";
  }

  public final Object clone()
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

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.platformId, "platformId");
    localJceDisplayer.display(this.productId, "productId");
    localJceDisplayer.display(this.productVersion, "productVersion");
    localJceDisplayer.display(this.sdkId, "sdkId");
    localJceDisplayer.display(this.sdkVersion, "sdkVersion");
    localJceDisplayer.display(this.cmd, "cmd");
    localJceDisplayer.display(this.sBuffer, "sBuffer");
    localJceDisplayer.display(this.hardware_os, "hardware_os");
    localJceDisplayer.display(this.qua, "qua");
    localJceDisplayer.display(this.encryType, "encryType");
    localJceDisplayer.display(this.zipType, "zipType");
    localJceDisplayer.display(this.productIdentity, "productIdentity");
    localJceDisplayer.display(this.reserved, "reserved");
    localJceDisplayer.display(this.bundleId, "bundleId");
    localJceDisplayer.display(this.version, "version");
    localJceDisplayer.display(this.qimei, "qimei");
    localJceDisplayer.display(this.requestId, "requestId");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    RequestPackage localRequestPackage;
    do
    {
      return false;
      localRequestPackage = (RequestPackage)paramObject;
    }
    while ((!JceUtil.equals(this.platformId, localRequestPackage.platformId)) || (!JceUtil.equals(this.productId, localRequestPackage.productId)) || (!JceUtil.equals(this.productVersion, localRequestPackage.productVersion)) || (!JceUtil.equals(this.sdkId, localRequestPackage.sdkId)) || (!JceUtil.equals(this.sdkVersion, localRequestPackage.sdkVersion)) || (!JceUtil.equals(this.cmd, localRequestPackage.cmd)) || (!JceUtil.equals(this.sBuffer, localRequestPackage.sBuffer)) || (!JceUtil.equals(this.hardware_os, localRequestPackage.hardware_os)) || (!JceUtil.equals(this.qua, localRequestPackage.qua)) || (!JceUtil.equals(this.encryType, localRequestPackage.encryType)) || (!JceUtil.equals(this.zipType, localRequestPackage.zipType)) || (!JceUtil.equals(this.productIdentity, localRequestPackage.productIdentity)) || (!JceUtil.equals(this.reserved, localRequestPackage.reserved)) || (!JceUtil.equals(this.bundleId, localRequestPackage.bundleId)) || (!JceUtil.equals(this.version, localRequestPackage.version)) || (!JceUtil.equals(this.qimei, localRequestPackage.qimei)) || (!JceUtil.equals(this.requestId, localRequestPackage.requestId)));
    return true;
  }

  public final String fullClassName()
  {
    return "common.RequestPackage";
  }

  public final String getBundleId()
  {
    return this.bundleId;
  }

  public final int getCmd()
  {
    return this.cmd;
  }

  public final byte getEncryType()
  {
    return this.encryType;
  }

  public final String getHardware_os()
  {
    return this.hardware_os;
  }

  public final byte getPlatformId()
  {
    return this.platformId;
  }

  public final String getProductId()
  {
    return this.productId;
  }

  public final String getProductIdentity()
  {
    return this.productIdentity;
  }

  public final String getProductVersion()
  {
    return this.productVersion;
  }

  public final String getQimei()
  {
    return this.qimei;
  }

  public final String getQua()
  {
    return this.qua;
  }

  public final String getRequestId()
  {
    return this.requestId;
  }

  public final String getReserved()
  {
    return this.reserved;
  }

  public final byte[] getSBuffer()
  {
    return this.sBuffer;
  }

  public final String getSdkId()
  {
    return this.sdkId;
  }

  public final String getSdkVersion()
  {
    return this.sdkVersion;
  }

  public final byte getVersion()
  {
    return this.version;
  }

  public final byte getZipType()
  {
    return this.zipType;
  }

  public final int hashCode()
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

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.platformId = paramJceInputStream.read(this.platformId, 0, true);
    this.productId = paramJceInputStream.readString(1, true);
    this.productVersion = paramJceInputStream.readString(2, true);
    this.sdkId = paramJceInputStream.readString(3, true);
    this.sdkVersion = paramJceInputStream.readString(4, true);
    this.cmd = paramJceInputStream.read(this.cmd, 5, true);
    if (cache_sBuffer == null)
    {
      byte[] arrayOfByte = new byte[1];
      cache_sBuffer = arrayOfByte;
      arrayOfByte[0] = 0;
    }
    this.sBuffer = paramJceInputStream.read(cache_sBuffer, 6, true);
    this.hardware_os = paramJceInputStream.readString(7, false);
    this.qua = paramJceInputStream.readString(8, false);
    this.encryType = paramJceInputStream.read(this.encryType, 9, false);
    this.zipType = paramJceInputStream.read(this.zipType, 10, false);
    this.productIdentity = paramJceInputStream.readString(11, false);
    this.reserved = paramJceInputStream.readString(12, false);
    this.bundleId = paramJceInputStream.readString(13, false);
    this.version = paramJceInputStream.read(this.version, 14, false);
    this.qimei = paramJceInputStream.readString(15, false);
    this.requestId = paramJceInputStream.readString(16, false);
  }

  public final void setBundleId(String paramString)
  {
    this.bundleId = paramString;
  }

  public final void setCmd(int paramInt)
  {
    this.cmd = paramInt;
  }

  public final void setEncryType(byte paramByte)
  {
    this.encryType = paramByte;
  }

  public final void setHardware_os(String paramString)
  {
    this.hardware_os = paramString;
  }

  public final void setPlatformId(byte paramByte)
  {
    this.platformId = paramByte;
  }

  public final void setProductId(String paramString)
  {
    this.productId = paramString;
  }

  public final void setProductIdentity(String paramString)
  {
    this.productIdentity = paramString;
  }

  public final void setProductVersion(String paramString)
  {
    this.productVersion = paramString;
  }

  public final void setQimei(String paramString)
  {
    this.qimei = paramString;
  }

  public final void setQua(String paramString)
  {
    this.qua = paramString;
  }

  public final void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }

  public final void setReserved(String paramString)
  {
    this.reserved = paramString;
  }

  public final void setSBuffer(byte[] paramArrayOfByte)
  {
    this.sBuffer = paramArrayOfByte;
  }

  public final void setSdkId(String paramString)
  {
    this.sdkId = paramString;
  }

  public final void setSdkVersion(String paramString)
  {
    this.sdkVersion = paramString;
  }

  public final void setVersion(byte paramByte)
  {
    this.version = paramByte;
  }

  public final void setZipType(byte paramByte)
  {
    this.zipType = paramByte;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.platformId, 0);
    paramJceOutputStream.write(this.productId, 1);
    paramJceOutputStream.write(this.productVersion, 2);
    paramJceOutputStream.write(this.sdkId, 3);
    paramJceOutputStream.write(this.sdkVersion, 4);
    paramJceOutputStream.write(this.cmd, 5);
    paramJceOutputStream.write(this.sBuffer, 6);
    if (this.hardware_os != null)
      paramJceOutputStream.write(this.hardware_os, 7);
    if (this.qua != null)
      paramJceOutputStream.write(this.qua, 8);
    paramJceOutputStream.write(this.encryType, 9);
    paramJceOutputStream.write(this.zipType, 10);
    if (this.productIdentity != null)
      paramJceOutputStream.write(this.productIdentity, 11);
    if (this.reserved != null)
      paramJceOutputStream.write(this.reserved, 12);
    if (this.bundleId != null)
      paramJceOutputStream.write(this.bundleId, 13);
    paramJceOutputStream.write(this.version, 14);
    if (this.qimei != null)
      paramJceOutputStream.write(this.qimei, 15);
    if (this.requestId != null)
      paramJceOutputStream.write(this.requestId, 16);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     common.RequestPackage
 * JD-Core Version:    0.6.0
 */