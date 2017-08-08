package common;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class ResponsePackage extends JceStruct
  implements Cloneable
{
  static byte[] cache_sBuffer;
  public int cmd = 0;
  public byte encryType = 0;
  public String requestId = "";
  public byte result = 0;
  public byte[] sBuffer = null;
  public long serverTime = 0L;
  public String srcGatewayIp = "";
  public byte zipType = 0;

  static
  {
    if (!ResponsePackage.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ResponsePackage()
  {
  }

  public ResponsePackage(byte paramByte1, int paramInt, byte[] paramArrayOfByte, String paramString1, byte paramByte2, byte paramByte3, long paramLong, String paramString2)
  {
    this.result = paramByte1;
    this.cmd = paramInt;
    this.sBuffer = paramArrayOfByte;
    this.srcGatewayIp = paramString1;
    this.encryType = paramByte2;
    this.zipType = paramByte3;
    this.serverTime = paramLong;
    this.requestId = paramString2;
  }

  public final String className()
  {
    return "common.ResponsePackage";
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
    localJceDisplayer.display(this.result, "result");
    localJceDisplayer.display(this.cmd, "cmd");
    localJceDisplayer.display(this.sBuffer, "sBuffer");
    localJceDisplayer.display(this.srcGatewayIp, "srcGatewayIp");
    localJceDisplayer.display(this.encryType, "encryType");
    localJceDisplayer.display(this.zipType, "zipType");
    localJceDisplayer.display(this.serverTime, "serverTime");
    localJceDisplayer.display(this.requestId, "requestId");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ResponsePackage localResponsePackage;
    do
    {
      return false;
      localResponsePackage = (ResponsePackage)paramObject;
    }
    while ((!JceUtil.equals(this.result, localResponsePackage.result)) || (!JceUtil.equals(this.cmd, localResponsePackage.cmd)) || (!JceUtil.equals(this.sBuffer, localResponsePackage.sBuffer)) || (!JceUtil.equals(this.srcGatewayIp, localResponsePackage.srcGatewayIp)) || (!JceUtil.equals(this.encryType, localResponsePackage.encryType)) || (!JceUtil.equals(this.zipType, localResponsePackage.zipType)) || (!JceUtil.equals(this.serverTime, localResponsePackage.serverTime)) || (!JceUtil.equals(this.requestId, localResponsePackage.requestId)));
    return true;
  }

  public final String fullClassName()
  {
    return "common.ResponsePackage";
  }

  public final int getCmd()
  {
    return this.cmd;
  }

  public final byte getEncryType()
  {
    return this.encryType;
  }

  public final String getRequestId()
  {
    return this.requestId;
  }

  public final byte getResult()
  {
    return this.result;
  }

  public final byte[] getSBuffer()
  {
    return this.sBuffer;
  }

  public final long getServerTime()
  {
    return this.serverTime;
  }

  public final String getSrcGatewayIp()
  {
    return this.srcGatewayIp;
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
    this.result = paramJceInputStream.read(this.result, 0, true);
    this.cmd = paramJceInputStream.read(this.cmd, 1, true);
    if (cache_sBuffer == null)
    {
      byte[] arrayOfByte = new byte[1];
      cache_sBuffer = arrayOfByte;
      arrayOfByte[0] = 0;
    }
    this.sBuffer = paramJceInputStream.read(cache_sBuffer, 2, true);
    this.srcGatewayIp = paramJceInputStream.readString(3, true);
    this.encryType = paramJceInputStream.read(this.encryType, 4, false);
    this.zipType = paramJceInputStream.read(this.zipType, 5, false);
    this.serverTime = paramJceInputStream.read(this.serverTime, 6, false);
    this.requestId = paramJceInputStream.readString(7, false);
  }

  public final void setCmd(int paramInt)
  {
    this.cmd = paramInt;
  }

  public final void setEncryType(byte paramByte)
  {
    this.encryType = paramByte;
  }

  public final void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }

  public final void setResult(byte paramByte)
  {
    this.result = paramByte;
  }

  public final void setSBuffer(byte[] paramArrayOfByte)
  {
    this.sBuffer = paramArrayOfByte;
  }

  public final void setServerTime(long paramLong)
  {
    this.serverTime = paramLong;
  }

  public final void setSrcGatewayIp(String paramString)
  {
    this.srcGatewayIp = paramString;
  }

  public final void setZipType(byte paramByte)
  {
    this.zipType = paramByte;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.result, 0);
    paramJceOutputStream.write(this.cmd, 1);
    paramJceOutputStream.write(this.sBuffer, 2);
    paramJceOutputStream.write(this.srcGatewayIp, 3);
    paramJceOutputStream.write(this.encryType, 4);
    paramJceOutputStream.write(this.zipType, 5);
    paramJceOutputStream.write(this.serverTime, 6);
    if (this.requestId != null)
      paramJceOutputStream.write(this.requestId, 7);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     common.ResponsePackage
 * JD-Core Version:    0.6.0
 */