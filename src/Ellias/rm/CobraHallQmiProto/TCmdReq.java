package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TCmdReq extends JceStruct
  implements Cloneable
{
  static byte[] cache_cmdReqBody;
  static TCmdReqHead cache_cmdReqHead;
  public byte[] cmdReqBody = null;
  public TCmdReqHead cmdReqHead = null;

  static
  {
    if (!TCmdReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TCmdReq()
  {
  }

  public TCmdReq(TCmdReqHead paramTCmdReqHead, byte[] paramArrayOfByte)
  {
    this.cmdReqHead = paramTCmdReqHead;
    this.cmdReqBody = paramArrayOfByte;
  }

  public String className()
  {
    return "CobraHallQmiProto.TCmdReq";
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
    localJceDisplayer.display(this.cmdReqHead, "cmdReqHead");
    localJceDisplayer.display(this.cmdReqBody, "cmdReqBody");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.cmdReqHead, true);
    localJceDisplayer.displaySimple(this.cmdReqBody, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TCmdReq localTCmdReq;
    do
    {
      return false;
      localTCmdReq = (TCmdReq)paramObject;
    }
    while ((!JceUtil.equals(this.cmdReqHead, localTCmdReq.cmdReqHead)) || (!JceUtil.equals(this.cmdReqBody, localTCmdReq.cmdReqBody)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TCmdReq";
  }

  public byte[] getCmdReqBody()
  {
    return this.cmdReqBody;
  }

  public TCmdReqHead getCmdReqHead()
  {
    return this.cmdReqHead;
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
    if (cache_cmdReqHead == null)
      cache_cmdReqHead = new TCmdReqHead();
    this.cmdReqHead = ((TCmdReqHead)paramJceInputStream.read(cache_cmdReqHead, 0, true));
    if (cache_cmdReqBody == null)
    {
      cache_cmdReqBody = (byte[])new byte[1];
      ((byte[])cache_cmdReqBody)[0] = 0;
    }
    this.cmdReqBody = ((byte[])paramJceInputStream.read(cache_cmdReqBody, 1, true));
  }

  public void setCmdReqBody(byte[] paramArrayOfByte)
  {
    this.cmdReqBody = paramArrayOfByte;
  }

  public void setCmdReqHead(TCmdReqHead paramTCmdReqHead)
  {
    this.cmdReqHead = paramTCmdReqHead;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.cmdReqHead, 0);
    paramJceOutputStream.write(this.cmdReqBody, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TCmdReq
 * JD-Core Version:    0.6.0
 */