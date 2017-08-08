package qimei;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class IOSQIMeiPackage extends JceStruct
  implements Cloneable
{
  public String advId = "";
  public String guid = "";
  public String mac = "";
  public String qIMEI = "";
  public String uuidOfSDK = "";
  public String vendorId = "";

  static
  {
    if (!IOSQIMeiPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public IOSQIMeiPackage()
  {
  }

  public IOSQIMeiPackage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.qIMEI = paramString1;
    this.vendorId = paramString2;
    this.mac = paramString3;
    this.advId = paramString4;
    this.guid = paramString5;
    this.uuidOfSDK = paramString6;
  }

  public final String className()
  {
    return "qimei.IOSQIMeiPackage";
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
    localJceDisplayer.display(this.qIMEI, "qIMEI");
    localJceDisplayer.display(this.vendorId, "vendorId");
    localJceDisplayer.display(this.mac, "mac");
    localJceDisplayer.display(this.advId, "advId");
    localJceDisplayer.display(this.guid, "guid");
    localJceDisplayer.display(this.uuidOfSDK, "uuidOfSDK");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    IOSQIMeiPackage localIOSQIMeiPackage;
    do
    {
      return false;
      localIOSQIMeiPackage = (IOSQIMeiPackage)paramObject;
    }
    while ((!JceUtil.equals(this.qIMEI, localIOSQIMeiPackage.qIMEI)) || (!JceUtil.equals(this.vendorId, localIOSQIMeiPackage.vendorId)) || (!JceUtil.equals(this.mac, localIOSQIMeiPackage.mac)) || (!JceUtil.equals(this.advId, localIOSQIMeiPackage.advId)) || (!JceUtil.equals(this.guid, localIOSQIMeiPackage.guid)) || (!JceUtil.equals(this.uuidOfSDK, localIOSQIMeiPackage.uuidOfSDK)));
    return true;
  }

  public final String fullClassName()
  {
    return "qimei.IOSQIMeiPackage";
  }

  public final String getAdvId()
  {
    return this.advId;
  }

  public final String getGuid()
  {
    return this.guid;
  }

  public final String getMac()
  {
    return this.mac;
  }

  public final String getQIMEI()
  {
    return this.qIMEI;
  }

  public final String getUuidOfSDK()
  {
    return this.uuidOfSDK;
  }

  public final String getVendorId()
  {
    return this.vendorId;
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
    this.qIMEI = paramJceInputStream.readString(0, false);
    this.vendorId = paramJceInputStream.readString(1, false);
    this.mac = paramJceInputStream.readString(2, false);
    this.advId = paramJceInputStream.readString(3, false);
    this.guid = paramJceInputStream.readString(4, false);
    this.uuidOfSDK = paramJceInputStream.readString(5, false);
  }

  public final void setAdvId(String paramString)
  {
    this.advId = paramString;
  }

  public final void setGuid(String paramString)
  {
    this.guid = paramString;
  }

  public final void setMac(String paramString)
  {
    this.mac = paramString;
  }

  public final void setQIMEI(String paramString)
  {
    this.qIMEI = paramString;
  }

  public final void setUuidOfSDK(String paramString)
  {
    this.uuidOfSDK = paramString;
  }

  public final void setVendorId(String paramString)
  {
    this.vendorId = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.qIMEI != null)
      paramJceOutputStream.write(this.qIMEI, 0);
    if (this.vendorId != null)
      paramJceOutputStream.write(this.vendorId, 1);
    if (this.mac != null)
      paramJceOutputStream.write(this.mac, 2);
    if (this.advId != null)
      paramJceOutputStream.write(this.advId, 3);
    if (this.guid != null)
      paramJceOutputStream.write(this.guid, 4);
    if (this.uuidOfSDK != null)
      paramJceOutputStream.write(this.uuidOfSDK, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     qimei.IOSQIMeiPackage
 * JD-Core Version:    0.6.0
 */