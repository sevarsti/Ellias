package qimei;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class QIMeiInfoPackage extends JceStruct
  implements Cloneable
{
  public String androidId = "";
  public String imei = "";
  public String imsi = "";
  public String mac = "";
  public String qIMEI = "";

  static
  {
    if (!QIMeiInfoPackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public QIMeiInfoPackage()
  {
    setQIMEI(this.qIMEI);
    setImei(this.imei);
    setMac(this.mac);
    setImsi(this.imsi);
    setAndroidId(this.androidId);
  }

  public QIMeiInfoPackage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    setQIMEI(paramString1);
    setImei(paramString2);
    setMac(paramString3);
    setImsi(paramString4);
    setAndroidId(paramString5);
  }

  public final String className()
  {
    return "qimei.QIMeiInfoPackage";
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
    localJceDisplayer.display(this.imei, "imei");
    localJceDisplayer.display(this.mac, "mac");
    localJceDisplayer.display(this.imsi, "imsi");
    localJceDisplayer.display(this.androidId, "androidId");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    QIMeiInfoPackage localQIMeiInfoPackage;
    do
    {
      return false;
      localQIMeiInfoPackage = (QIMeiInfoPackage)paramObject;
    }
    while ((!JceUtil.equals(this.qIMEI, localQIMeiInfoPackage.qIMEI)) || (!JceUtil.equals(this.imei, localQIMeiInfoPackage.imei)) || (!JceUtil.equals(this.mac, localQIMeiInfoPackage.mac)) || (!JceUtil.equals(this.imsi, localQIMeiInfoPackage.imsi)) || (!JceUtil.equals(this.androidId, localQIMeiInfoPackage.androidId)));
    return true;
  }

  public final String fullClassName()
  {
    return "qimei.QIMeiInfoPackage";
  }

  public final String getAndroidId()
  {
    return this.androidId;
  }

  public final String getImei()
  {
    return this.imei;
  }

  public final String getImsi()
  {
    return this.imsi;
  }

  public final String getMac()
  {
    return this.mac;
  }

  public final String getQIMEI()
  {
    return this.qIMEI;
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
    this.imei = paramJceInputStream.readString(1, false);
    this.mac = paramJceInputStream.readString(2, false);
    this.imsi = paramJceInputStream.readString(3, false);
    this.androidId = paramJceInputStream.readString(4, false);
  }

  public final void setAndroidId(String paramString)
  {
    this.androidId = paramString;
  }

  public final void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public final void setImsi(String paramString)
  {
    this.imsi = paramString;
  }

  public final void setMac(String paramString)
  {
    this.mac = paramString;
  }

  public final void setQIMEI(String paramString)
  {
    this.qIMEI = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.qIMEI != null)
      paramJceOutputStream.write(this.qIMEI, 0);
    if (this.imei != null)
      paramJceOutputStream.write(this.imei, 1);
    if (this.mac != null)
      paramJceOutputStream.write(this.mac, 2);
    if (this.imsi != null)
      paramJceOutputStream.write(this.imsi, 3);
    if (this.androidId != null)
      paramJceOutputStream.write(this.androidId, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     qimei.QIMeiInfoPackage
 * JD-Core Version:    0.6.0
 */