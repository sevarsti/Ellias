package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TCmdRsp extends JceStruct
  implements Cloneable
{
  static byte[] cache_cmdRspBody;
  static TCmdRspHead cache_cmdRspHead;
  public byte[] cmdRspBody = null;
  public TCmdRspHead cmdRspHead = null;

  static
  {
    if (!TCmdRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TCmdRsp()
  {
  }

  public TCmdRsp(TCmdRspHead paramTCmdRspHead, byte[] paramArrayOfByte)
  {
    this.cmdRspHead = paramTCmdRspHead;
    this.cmdRspBody = paramArrayOfByte;
  }

  public String className()
  {
    return "CobraHallQmiProto.TCmdRsp";
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
    localJceDisplayer.display(this.cmdRspHead, "cmdRspHead");
    localJceDisplayer.display(this.cmdRspBody, "cmdRspBody");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.cmdRspHead, true);
    localJceDisplayer.displaySimple(this.cmdRspBody, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TCmdRsp localTCmdRsp;
    do
    {
      return false;
      localTCmdRsp = (TCmdRsp)paramObject;
    }
    while ((!JceUtil.equals(this.cmdRspHead, localTCmdRsp.cmdRspHead)) || (!JceUtil.equals(this.cmdRspBody, localTCmdRsp.cmdRspBody)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TCmdRsp";
  }

  public byte[] getCmdRspBody()
  {
    return this.cmdRspBody;
  }

  public TCmdRspHead getCmdRspHead()
  {
    return this.cmdRspHead;
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
    if (cache_cmdRspHead == null)
      cache_cmdRspHead = new TCmdRspHead();
    this.cmdRspHead = ((TCmdRspHead)paramJceInputStream.read(cache_cmdRspHead, 0, true));
    if (cache_cmdRspBody == null)
    {
      cache_cmdRspBody = (byte[])new byte[1];
      ((byte[])cache_cmdRspBody)[0] = 0;
    }
    this.cmdRspBody = ((byte[])paramJceInputStream.read(cache_cmdRspBody, 1, true));
  }

  public void setCmdRspBody(byte[] paramArrayOfByte)
  {
    this.cmdRspBody = paramArrayOfByte;
  }

  public void setCmdRspHead(TCmdRspHead paramTCmdRspHead)
  {
    this.cmdRspHead = paramTCmdRspHead;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.cmdRspHead, 0);
    paramJceOutputStream.write(this.cmdRspBody, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TCmdRsp
 * JD-Core Version:    0.6.0
 */