package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;
import java.util.ArrayList;

public final class TBodyReportReq extends JceStruct
  implements Cloneable
{
  static ArrayList cache_reportData;
  public ArrayList reportData = null;

  static
  {
    if (!TBodyReportReq.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TBodyReportReq()
  {
  }

  public TBodyReportReq(ArrayList paramArrayList)
  {
    this.reportData = paramArrayList;
  }

  public String className()
  {
    return "CobraHallQmiProto.TBodyReportReq";
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
    new JceDisplayer(paramStringBuilder, paramInt).display(this.reportData, "reportData");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.reportData, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    TBodyReportReq localTBodyReportReq = (TBodyReportReq)paramObject;
    return JceUtil.equals(this.reportData, localTBodyReportReq.reportData);
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TBodyReportReq";
  }

  public ArrayList getReportData()
  {
    return this.reportData;
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
    if (cache_reportData == null)
    {
      cache_reportData = new ArrayList();
      TReportData localTReportData = new TReportData();
      cache_reportData.add(localTReportData);
    }
    this.reportData = ((ArrayList)paramJceInputStream.read(cache_reportData, 0, true));
  }

  public void setReportData(ArrayList paramArrayList)
  {
    this.reportData = paramArrayList;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.reportData, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TBodyReportReq
 * JD-Core Version:    0.6.0
 */