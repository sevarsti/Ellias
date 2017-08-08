package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TPackageRsp extends JceStruct
  implements Cloneable
{
  static TCmdRsp cache_cmdRsp;
  static TPkgRspHead cache_pkgRspHead;
  public TCmdRsp cmdRsp = null;
  public TPkgRspHead pkgRspHead = null;

  static
  {
    if (!TPackageRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TPackageRsp()
  {
  }

  public TPackageRsp(TPkgRspHead paramTPkgRspHead, TCmdRsp paramTCmdRsp)
  {
    this.pkgRspHead = paramTPkgRspHead;
    this.cmdRsp = paramTCmdRsp;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPackageRsp";
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
    localJceDisplayer.display(this.pkgRspHead, "pkgRspHead");
    localJceDisplayer.display(this.cmdRsp, "cmdRsp");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.pkgRspHead, true);
    localJceDisplayer.displaySimple(this.cmdRsp, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TPackageRsp localTPackageRsp;
    do
    {
      return false;
      localTPackageRsp = (TPackageRsp)paramObject;
    }
    while ((!JceUtil.equals(this.pkgRspHead, localTPackageRsp.pkgRspHead)) || (!JceUtil.equals(this.cmdRsp, localTPackageRsp.cmdRsp)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPackageRsp";
  }

  public TCmdRsp getCmdRsp()
  {
    return this.cmdRsp;
  }

  public TPkgRspHead getPkgRspHead()
  {
    return this.pkgRspHead;
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
    if (cache_pkgRspHead == null)
      cache_pkgRspHead = new TPkgRspHead();
    this.pkgRspHead = ((TPkgRspHead)paramJceInputStream.read(cache_pkgRspHead, 0, true));
    if (cache_cmdRsp == null)
      cache_cmdRsp = new TCmdRsp();
    this.cmdRsp = ((TCmdRsp)paramJceInputStream.read(cache_cmdRsp, 1, true));
  }

  public void setCmdRsp(TCmdRsp paramTCmdRsp)
  {
    this.cmdRsp = paramTCmdRsp;
  }

  public void setPkgRspHead(TPkgRspHead paramTPkgRspHead)
  {
    this.pkgRspHead = paramTPkgRspHead;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pkgRspHead, 0);
    paramJceOutputStream.write(this.cmdRsp, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPackageRsp
 * JD-Core Version:    0.6.0
 */