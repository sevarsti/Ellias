package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TPkgRspHead extends JceStruct
  implements Cloneable
{
  public short protocolVer = 0;

  static
  {
    if (!TPkgRspHead.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TPkgRspHead()
  {
  }

  public TPkgRspHead(short paramShort)
  {
    this.protocolVer = paramShort;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPkgRspHead";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.protocolVer, "protocolVer");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.protocolVer, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TPkgRspHead localTPkgRspHead = (TPkgRspHead)paramObject;
    return JceUtil.equals(this.protocolVer, localTPkgRspHead.protocolVer);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPkgRspHead";
  }

  public short getProtocolVer()
  {
    return this.protocolVer;
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
    this.protocolVer = paramJceInputStream.read(this.protocolVer, 0, true);
  }

  public void setProtocolVer(short paramShort)
  {
    this.protocolVer = paramShort;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.protocolVer, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPkgRspHead
 * JD-Core Version:    0.6.0
 */