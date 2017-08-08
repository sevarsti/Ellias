package userinfo;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class SummaryInfo extends JceStruct
  implements Cloneable
{
  public String proceName = "";
  public String qua = "";
  public long startTime = 0L;
  public byte startType = 0;

  static
  {
    if (!SummaryInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public SummaryInfo()
  {
  }

  public SummaryInfo(long paramLong, byte paramByte, String paramString1, String paramString2)
  {
    this.startTime = paramLong;
    this.startType = paramByte;
    this.qua = paramString1;
    this.proceName = paramString2;
  }

  public final String className()
  {
    return "userinfo.SummaryInfo";
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
    localJceDisplayer.display(this.startTime, "startTime");
    localJceDisplayer.display(this.startType, "startType");
    localJceDisplayer.display(this.qua, "qua");
    localJceDisplayer.display(this.proceName, "proceName");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    SummaryInfo localSummaryInfo;
    do
    {
      return false;
      localSummaryInfo = (SummaryInfo)paramObject;
    }
    while ((!JceUtil.equals(this.startTime, localSummaryInfo.startTime)) || (!JceUtil.equals(this.startType, localSummaryInfo.startType)) || (!JceUtil.equals(this.qua, localSummaryInfo.qua)) || (!JceUtil.equals(this.proceName, localSummaryInfo.proceName)));
    return true;
  }

  public final String fullClassName()
  {
    return "userinfo.SummaryInfo";
  }

  public final String getProceName()
  {
    return this.proceName;
  }

  public final String getQua()
  {
    return this.qua;
  }

  public final long getStartTime()
  {
    return this.startTime;
  }

  public final byte getStartType()
  {
    return this.startType;
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
    this.startTime = paramJceInputStream.read(this.startTime, 0, true);
    this.startType = paramJceInputStream.read(this.startType, 1, true);
    this.qua = paramJceInputStream.readString(2, false);
    this.proceName = paramJceInputStream.readString(3, false);
  }

  public final void setProceName(String paramString)
  {
    this.proceName = paramString;
  }

  public final void setQua(String paramString)
  {
    this.qua = paramString;
  }

  public final void setStartTime(long paramLong)
  {
    this.startTime = paramLong;
  }

  public final void setStartType(byte paramByte)
  {
    this.startType = paramByte;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.startTime, 0);
    paramJceOutputStream.write(this.startType, 1);
    if (this.qua != null)
      paramJceOutputStream.write(this.qua, 2);
    if (this.proceName != null)
      paramJceOutputStream.write(this.proceName, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     userinfo.SummaryInfo
 * JD-Core Version:    0.6.0
 */