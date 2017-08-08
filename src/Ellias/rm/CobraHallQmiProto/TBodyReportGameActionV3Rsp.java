package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TBodyReportGameActionV3Rsp extends JceStruct
  implements Cloneable
{
  public int nextReportTimeInterval = 0;

  static
  {
    if (!TBodyReportGameActionV3Rsp.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyReportGameActionV3Rsp()
  {
  }

  public TBodyReportGameActionV3Rsp(int paramInt)
  {
    this.nextReportTimeInterval = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyReportGameActionV3Rsp";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.nextReportTimeInterval, "nextReportTimeInterval");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.nextReportTimeInterval, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyReportGameActionV3Rsp localTBodyReportGameActionV3Rsp = (TBodyReportGameActionV3Rsp)paramObject;
    return JceUtil.equals(this.nextReportTimeInterval, localTBodyReportGameActionV3Rsp.nextReportTimeInterval);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyReportGameActionV3Rsp";
  }

  public int getNextReportTimeInterval()
  {
    return this.nextReportTimeInterval;
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
    this.nextReportTimeInterval = paramJceInputStream.read(this.nextReportTimeInterval, 0, true);
  }

  public void setNextReportTimeInterval(int paramInt)
  {
    this.nextReportTimeInterval = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.nextReportTimeInterval, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyReportGameActionV3Rsp
 * JD-Core Version:    0.6.0
 */