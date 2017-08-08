package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TCmdTimeStampInfo extends JceStruct
  implements Cloneable
{
  public short cmdId = 0;
  public int timestamp = 0;

  static
  {
    if (!TCmdTimeStampInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TCmdTimeStampInfo()
  {
  }

  public TCmdTimeStampInfo(short paramShort, int paramInt)
  {
    this.cmdId = paramShort;
    this.timestamp = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TCmdTimeStampInfo";
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
    localJceDisplayer.display(this.timestamp, "timestamp");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.cmdId, true);
    localJceDisplayer.displaySimple(this.timestamp, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TCmdTimeStampInfo localTCmdTimeStampInfo;
    do
    {
      return false;
      localTCmdTimeStampInfo = (TCmdTimeStampInfo)paramObject;
    }
    while ((!JceUtil.equals(this.cmdId, localTCmdTimeStampInfo.cmdId)) || (!JceUtil.equals(this.timestamp, localTCmdTimeStampInfo.timestamp)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TCmdTimeStampInfo";
  }

  public short getCmdId()
  {
    return this.cmdId;
  }

  public int getTimestamp()
  {
    return this.timestamp;
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
    this.cmdId = paramJceInputStream.read(this.cmdId, 0, true);
    this.timestamp = paramJceInputStream.read(this.timestamp, 1, true);
  }

  public void setCmdId(short paramShort)
  {
    this.cmdId = paramShort;
  }

  public void setTimestamp(int paramInt)
  {
    this.timestamp = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.cmdId, 0);
    paramJceOutputStream.write(this.timestamp, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TCmdTimeStampInfo
 * JD-Core Version:    0.6.0
 */