package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TCmdRspHead extends JceStruct
  implements Cloneable
{
  public short cmdId = 0;
  public int cmdResultId = 0;
  public int flag = 0;
  public String reason = "";
  public long seqNo = 0L;
  public int timestamp = 0;

  static
  {
    if (!TCmdRspHead.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TCmdRspHead()
  {
  }

  public TCmdRspHead(short paramShort, int paramInt1, String paramString, int paramInt2, int paramInt3, long paramLong)
  {
    this.cmdId = paramShort;
    this.cmdResultId = paramInt1;
    this.reason = paramString;
    this.timestamp = paramInt2;
    this.flag = paramInt3;
    this.seqNo = paramLong;
  }

  public String className()
  {
    return "CobraHallQmiProto.TCmdRspHead";
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
    localJceDisplayer.display(this.cmdResultId, "cmdResultId");
    localJceDisplayer.display(this.reason, "reason");
    localJceDisplayer.display(this.timestamp, "timestamp");
    localJceDisplayer.display(this.flag, "flag");
    localJceDisplayer.display(this.seqNo, "seqNo");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.cmdId, true);
    localJceDisplayer.displaySimple(this.cmdResultId, true);
    localJceDisplayer.displaySimple(this.reason, true);
    localJceDisplayer.displaySimple(this.timestamp, true);
    localJceDisplayer.displaySimple(this.flag, true);
    localJceDisplayer.displaySimple(this.seqNo, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TCmdRspHead localTCmdRspHead;
    do
    {
      return false;
      localTCmdRspHead = (TCmdRspHead)paramObject;
    }
    while ((!JceUtil.equals(this.cmdId, localTCmdRspHead.cmdId)) || (!JceUtil.equals(this.cmdResultId, localTCmdRspHead.cmdResultId)) || (!JceUtil.equals(this.reason, localTCmdRspHead.reason)) || (!JceUtil.equals(this.timestamp, localTCmdRspHead.timestamp)) || (!JceUtil.equals(this.flag, localTCmdRspHead.flag)) || (!JceUtil.equals(this.seqNo, localTCmdRspHead.seqNo)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TCmdRspHead";
  }

  public short getCmdId()
  {
    return this.cmdId;
  }

  public int getCmdResultId()
  {
    return this.cmdResultId;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public String getReason()
  {
    return this.reason;
  }

  public long getSeqNo()
  {
    return this.seqNo;
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
    this.cmdResultId = paramJceInputStream.read(this.cmdResultId, 1, true);
    this.reason = paramJceInputStream.readString(2, false);
    this.timestamp = paramJceInputStream.read(this.timestamp, 3, false);
    this.flag = paramJceInputStream.read(this.flag, 4, false);
    this.seqNo = paramJceInputStream.read(this.seqNo, 5, false);
  }

  public void setCmdId(short paramShort)
  {
    this.cmdId = paramShort;
  }

  public void setCmdResultId(int paramInt)
  {
    this.cmdResultId = paramInt;
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setReason(String paramString)
  {
    this.reason = paramString;
  }

  public void setSeqNo(long paramLong)
  {
    this.seqNo = paramLong;
  }

  public void setTimestamp(int paramInt)
  {
    this.timestamp = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.cmdId, 0);
    paramJceOutputStream.write(this.cmdResultId, 1);
    if (this.reason != null)
      paramJceOutputStream.write(this.reason, 2);
    paramJceOutputStream.write(this.timestamp, 3);
    paramJceOutputStream.write(this.flag, 4);
    paramJceOutputStream.write(this.seqNo, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TCmdRspHead
 * JD-Core Version:    0.6.0
 */