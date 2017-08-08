package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class Net extends JceStruct
  implements Cloneable
{
  public String extNetworkOperator = "";
  public int extNetworkType = 0;
  public byte isWap = 0;
  public byte netType = 0;

  static
  {
    if (!Net.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public Net()
  {
  }

  public Net(byte paramByte1, String paramString, int paramInt, byte paramByte2)
  {
    this.netType = paramByte1;
    this.extNetworkOperator = paramString;
    this.extNetworkType = paramInt;
    this.isWap = paramByte2;
  }

  public String className()
  {
    return "jce.Net";
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
    localJceDisplayer.display(this.netType, "netType");
    localJceDisplayer.display(this.extNetworkOperator, "extNetworkOperator");
    localJceDisplayer.display(this.extNetworkType, "extNetworkType");
    localJceDisplayer.display(this.isWap, "isWap");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.netType, true);
    localJceDisplayer.displaySimple(this.extNetworkOperator, true);
    localJceDisplayer.displaySimple(this.extNetworkType, true);
    localJceDisplayer.displaySimple(this.isWap, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    Net localNet;
    do
    {
      return false;
      localNet = (Net)paramObject;
    }
    while ((!JceUtil.equals(this.netType, localNet.netType)) || (!JceUtil.equals(this.extNetworkOperator, localNet.extNetworkOperator)) || (!JceUtil.equals(this.extNetworkType, localNet.extNetworkType)) || (!JceUtil.equals(this.isWap, localNet.isWap)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.Net";
  }

  public String getExtNetworkOperator()
  {
    return this.extNetworkOperator;
  }

  public int getExtNetworkType()
  {
    return this.extNetworkType;
  }

  public byte getIsWap()
  {
    return this.isWap;
  }

  public byte getNetType()
  {
    return this.netType;
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
    this.netType = paramJceInputStream.read(this.netType, 0, true);
    this.extNetworkOperator = paramJceInputStream.readString(1, false);
    this.extNetworkType = paramJceInputStream.read(this.extNetworkType, 2, false);
    this.isWap = paramJceInputStream.read(this.isWap, 3, false);
  }

  public void setExtNetworkOperator(String paramString)
  {
    this.extNetworkOperator = paramString;
  }

  public void setExtNetworkType(int paramInt)
  {
    this.extNetworkType = paramInt;
  }

  public void setIsWap(byte paramByte)
  {
    this.isWap = paramByte;
  }

  public void setNetType(byte paramByte)
  {
    this.netType = paramByte;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.netType, 0);
    if (this.extNetworkOperator != null)
      paramJceOutputStream.write(this.extNetworkOperator, 1);
    paramJceOutputStream.write(this.extNetworkType, 2);
    paramJceOutputStream.write(this.isWap, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.Net
 * JD-Core Version:    0.6.0
 */