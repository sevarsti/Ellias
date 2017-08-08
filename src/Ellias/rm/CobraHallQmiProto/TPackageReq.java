package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TPackageReq extends JceStruct
  implements Cloneable
{
  static TCmdReq cache_cmdReq;
  static TPkgReqHead cache_pkgReqHead;
  public TCmdReq cmdReq = null;
  public TPkgReqHead pkgReqHead = null;

  static
  {
    if (!TPackageReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TPackageReq()
  {
  }

  public TPackageReq(TPkgReqHead paramTPkgReqHead, TCmdReq paramTCmdReq)
  {
    this.pkgReqHead = paramTPkgReqHead;
    this.cmdReq = paramTCmdReq;
  }

  public String className()
  {
    return "CobraHallQmiProto.TPackageReq";
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
    localJceDisplayer.display(this.pkgReqHead, "pkgReqHead");
    localJceDisplayer.display(this.cmdReq, "cmdReq");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.pkgReqHead, true);
    localJceDisplayer.displaySimple(this.cmdReq, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TPackageReq localTPackageReq;
    do
    {
      return false;
      localTPackageReq = (TPackageReq)paramObject;
    }
    while ((!JceUtil.equals(this.pkgReqHead, localTPackageReq.pkgReqHead)) || (!JceUtil.equals(this.cmdReq, localTPackageReq.cmdReq)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TPackageReq";
  }

  public TCmdReq getCmdReq()
  {
    return this.cmdReq;
  }

  public TPkgReqHead getPkgReqHead()
  {
    return this.pkgReqHead;
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
    if (cache_pkgReqHead == null)
      cache_pkgReqHead = new TPkgReqHead();
    this.pkgReqHead = ((TPkgReqHead)paramJceInputStream.read(cache_pkgReqHead, 0, true));
    if (cache_cmdReq == null)
      cache_cmdReq = new TCmdReq();
    this.cmdReq = ((TCmdReq)paramJceInputStream.read(cache_cmdReq, 1, true));
  }

  public void setCmdReq(TCmdReq paramTCmdReq)
  {
    this.cmdReq = paramTCmdReq;
  }

  public void setPkgReqHead(TPkgReqHead paramTPkgReqHead)
  {
    this.pkgReqHead = paramTPkgReqHead;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.pkgReqHead, 0);
    paramJceOutputStream.write(this.cmdReq, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TPackageReq
 * JD-Core Version:    0.6.0
 */