package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class Terminal extends JceStruct
  implements Cloneable
{
  public String androidId = "";
  public String androidIdSdCard = "";
  public String imei = "";
  public String imsi = "";
  public String macAdress = "";

  static
  {
    if (!Terminal.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public Terminal()
  {
  }

  public Terminal(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.imei = paramString1;
    this.macAdress = paramString2;
    this.androidId = paramString3;
    this.androidIdSdCard = paramString4;
    this.imsi = paramString5;
  }

  public String className()
  {
    return "jce.Terminal";
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
        bool = a;
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
    localJceDisplayer.display(this.macAdress, "macAdress");
    localJceDisplayer.display(this.androidId, "androidId");
    localJceDisplayer.display(this.androidIdSdCard, "androidIdSdCard");
    localJceDisplayer.display(this.imsi, "imsi");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.imei, true);
    localJceDisplayer.displaySimple(this.macAdress, true);
    localJceDisplayer.displaySimple(this.androidId, true);
    localJceDisplayer.displaySimple(this.androidIdSdCard, true);
    localJceDisplayer.displaySimple(this.imsi, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    Terminal localTerminal;
    do
    {
      return false;
      localTerminal = (Terminal)paramObject;
    }
    while ((!JceUtil.equals(this.imei, localTerminal.imei)) || (!JceUtil.equals(this.macAdress, localTerminal.macAdress)) || (!JceUtil.equals(this.androidId, localTerminal.androidId)) || (!JceUtil.equals(this.androidIdSdCard, localTerminal.androidIdSdCard)) || (!JceUtil.equals(this.imsi, localTerminal.imsi)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.Terminal";
  }

  public String getAndroidId()
  {
    return this.androidId;
  }

  public String getAndroidIdSdCard()
  {
    return this.androidIdSdCard;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getImsi()
  {
    return this.imsi;
  }

  public String getMacAdress()
  {
    return this.macAdress;
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
    this.macAdress = paramJceInputStream.readString(1, false);
    this.androidId = paramJceInputStream.readString(2, false);
    this.androidIdSdCard = paramJceInputStream.readString(3, false);
    this.imsi = paramJceInputStream.readString(4, false);
  }

  public void setAndroidId(String paramString)
  {
    this.androidId = paramString;
  }

  public void setAndroidIdSdCard(String paramString)
  {
    this.androidIdSdCard = paramString;
  }

  public void setImei(String paramString)
  {
    this.imei = paramString;
  }

  public void setImsi(String paramString)
  {
    this.imsi = paramString;
  }

  public void setMacAdress(String paramString)
  {
    this.macAdress = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.imei != null)
      paramJceOutputStream.write(this.imei, 0);
    if (this.macAdress != null)
      paramJceOutputStream.write(this.macAdress, 1);
    if (this.androidId != null)
      paramJceOutputStream.write(this.androidId, 2);
    if (this.androidIdSdCard != null)
      paramJceOutputStream.write(this.androidIdSdCard, 3);
    if (this.imsi != null)
      paramJceOutputStream.write(this.imsi, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.Terminal
 * JD-Core Version:    0.6.0
 */