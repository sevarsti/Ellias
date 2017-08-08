package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyAddPostRsp extends JceStruct
  implements Cloneable
{
  public String tid = "";

  static
  {
    if (!TBodyAddPostRsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyAddPostRsp()
  {
  }

  public TBodyAddPostRsp(String paramString)
  {
    this.tid = paramString;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyAddPostRsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.tid, "tid");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.tid, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyAddPostRsp localTBodyAddPostRsp = (TBodyAddPostRsp)paramObject;
    return JceUtil.equals(this.tid, localTBodyAddPostRsp.tid);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyAddPostRsp";
  }

  public String getTid()
  {
    return this.tid;
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
    this.tid = paramJceInputStream.readString(0, true);
  }

  public void setTid(String paramString)
  {
    this.tid = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.tid, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyAddPostRsp
 * JD-Core Version:    0.6.0
 */