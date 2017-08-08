package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import com.tencent.component.annotation.PluginApi;

@PluginApi
public final class TCmdReqHead extends JceStruct
  implements Cloneable
{
  static byte[] cache_encData;
  static byte[] cache_sign;
  static byte[] cache_sybStData;
  public int appCap = 0;
  public short cmdId = 0;
  public byte[] encData = null;
  public int lastTimestamp = 0;
  public String openId = "";
  public long seqNo = 0L;
  public byte[] sign = null;
  public short svcType = 0;
  public byte[] sybStData = null;
  public short sybStType = 0;

  static
  {
    if (!TCmdReqHead.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  @PluginApi
  public TCmdReqHead()
  {
  }

  @PluginApi
  public TCmdReqHead(short paramShort1, int paramInt1, short paramShort2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong, int paramInt2, short paramShort3, byte[] paramArrayOfByte3, String paramString)
  {
    this.cmdId = paramShort1;
    this.lastTimestamp = paramInt1;
    this.svcType = paramShort2;
    this.sign = paramArrayOfByte1;
    this.encData = paramArrayOfByte2;
    this.seqNo = paramLong;
    this.appCap = paramInt2;
    this.sybStType = paramShort3;
    this.sybStData = paramArrayOfByte3;
    this.openId = paramString;
  }

  public String className()
  {
    return "CobraHallQmiProto.TCmdReqHead";
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
    localJceDisplayer.display(this.cmdId, "cmdId");
    localJceDisplayer.display(this.lastTimestamp, "lastTimestamp");
    localJceDisplayer.display(this.svcType, "svcType");
    localJceDisplayer.display(this.sign, "sign");
    localJceDisplayer.display(this.encData, "encData");
    localJceDisplayer.display(this.seqNo, "seqNo");
    localJceDisplayer.display(this.appCap, "appCap");
    localJceDisplayer.display(this.sybStType, "sybStType");
    localJceDisplayer.display(this.sybStData, "sybStData");
    localJceDisplayer.display(this.openId, "openId");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.cmdId, true);
    localJceDisplayer.displaySimple(this.lastTimestamp, true);
    localJceDisplayer.displaySimple(this.svcType, true);
    localJceDisplayer.displaySimple(this.sign, true);
    localJceDisplayer.displaySimple(this.encData, true);
    localJceDisplayer.displaySimple(this.seqNo, true);
    localJceDisplayer.displaySimple(this.appCap, true);
    localJceDisplayer.displaySimple(this.sybStType, true);
    localJceDisplayer.displaySimple(this.sybStData, true);
    localJceDisplayer.displaySimple(this.openId, false);
  }

  @PluginApi
  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TCmdReqHead localTCmdReqHead;
    do
    {
      return false;
      localTCmdReqHead = (TCmdReqHead)paramObject;
    }
    while ((!JceUtil.equals(this.cmdId, localTCmdReqHead.cmdId)) || (!JceUtil.equals(this.lastTimestamp, localTCmdReqHead.lastTimestamp)) || (!JceUtil.equals(this.svcType, localTCmdReqHead.svcType)) || (!JceUtil.equals(this.sign, localTCmdReqHead.sign)) || (!JceUtil.equals(this.encData, localTCmdReqHead.encData)) || (!JceUtil.equals(this.seqNo, localTCmdReqHead.seqNo)) || (!JceUtil.equals(this.appCap, localTCmdReqHead.appCap)) || (!JceUtil.equals(this.sybStType, localTCmdReqHead.sybStType)) || (!JceUtil.equals(this.sybStData, localTCmdReqHead.sybStData)) || (!JceUtil.equals(this.openId, localTCmdReqHead.openId)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TCmdReqHead";
  }

  @PluginApi
  public int getAppCap()
  {
    return this.appCap;
  }

  @PluginApi
  public short getCmdId()
  {
    return this.cmdId;
  }

  @PluginApi
  public byte[] getEncData()
  {
    return this.encData;
  }

  @PluginApi
  public int getLastTimestamp()
  {
    return this.lastTimestamp;
  }

  @PluginApi
  public String getOpenId()
  {
    return this.openId;
  }

  @PluginApi
  public long getSeqNo()
  {
    return this.seqNo;
  }

  @PluginApi
  public byte[] getSign()
  {
    return this.sign;
  }

  @PluginApi
  public short getSvcType()
  {
    return this.svcType;
  }

  @PluginApi
  public byte[] getSybStData()
  {
    return this.sybStData;
  }

  @PluginApi
  public short getSybStType()
  {
    return this.sybStType;
  }

  @PluginApi
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
    this.cmdId = paramJceInputStream.read(this.cmdId, 0, true);
    this.lastTimestamp = paramJceInputStream.read(this.lastTimestamp, 1, false);
    this.svcType = paramJceInputStream.read(this.svcType, 2, false);
    if (cache_sign == null)
    {
      cache_sign = (byte[])new byte[1];
      ((byte[])cache_sign)[0] = 0;
    }
    this.sign = ((byte[])paramJceInputStream.read(cache_sign, 3, false));
    if (cache_encData == null)
    {
      cache_encData = (byte[])new byte[1];
      ((byte[])cache_encData)[0] = 0;
    }
    this.encData = ((byte[])paramJceInputStream.read(cache_encData, 4, false));
    this.seqNo = paramJceInputStream.read(this.seqNo, 5, false);
    this.appCap = paramJceInputStream.read(this.appCap, 6, false);
    this.sybStType = paramJceInputStream.read(this.sybStType, 7, false);
    if (cache_sybStData == null)
    {
      cache_sybStData = (byte[])new byte[1];
      ((byte[])cache_sybStData)[0] = 0;
    }
    this.sybStData = ((byte[])paramJceInputStream.read(cache_sybStData, 8, false));
    this.openId = paramJceInputStream.readString(9, false);
  }

  @PluginApi
  public void setAppCap(int paramInt)
  {
    this.appCap = paramInt;
  }

  @PluginApi
  public void setCmdId(short paramShort)
  {
    this.cmdId = paramShort;
  }

  @PluginApi
  public void setEncData(byte[] paramArrayOfByte)
  {
    this.encData = paramArrayOfByte;
  }

  @PluginApi
  public void setLastTimestamp(int paramInt)
  {
    this.lastTimestamp = paramInt;
  }

  @PluginApi
  public void setOpenId(String paramString)
  {
    this.openId = paramString;
  }

  @PluginApi
  public void setSeqNo(long paramLong)
  {
    this.seqNo = paramLong;
  }

  @PluginApi
  public void setSign(byte[] paramArrayOfByte)
  {
    this.sign = paramArrayOfByte;
  }

  @PluginApi
  public void setSvcType(short paramShort)
  {
    this.svcType = paramShort;
  }

  @PluginApi
  public void setSybStData(byte[] paramArrayOfByte)
  {
    this.sybStData = paramArrayOfByte;
  }

  @PluginApi
  public void setSybStType(short paramShort)
  {
    this.sybStType = paramShort;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.cmdId, 0);
    paramJceOutputStream.write(this.lastTimestamp, 1);
    paramJceOutputStream.write(this.svcType, 2);
    if (this.sign != null)
      paramJceOutputStream.write(this.sign, 3);
    if (this.encData != null)
      paramJceOutputStream.write(this.encData, 4);
    paramJceOutputStream.write(this.seqNo, 5);
    paramJceOutputStream.write(this.appCap, 6);
    paramJceOutputStream.write(this.sybStType, 7);
    if (this.sybStData != null)
      paramJceOutputStream.write(this.sybStData, 8);
    if (this.openId != null)
      paramJceOutputStream.write(this.openId, 9);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TCmdReqHead
 * JD-Core Version:    0.6.0
 */