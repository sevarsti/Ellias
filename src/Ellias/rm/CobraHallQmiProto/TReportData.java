package CobraHallQmiProto;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TReportData extends JceStruct
  implements Cloneable
{
  static byte[] cache_content;
  public byte[] content = null;
  public int reportType = 0;

  static
  {
    if (!TReportData.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public TReportData()
  {
  }

  public TReportData(byte[] paramArrayOfByte, int paramInt)
  {
    this.content = paramArrayOfByte;
    this.reportType = paramInt;
  }

  public String className()
  {
    return "CobraHallQmiProto.TReportData";
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
    localJceDisplayer.display(this.content, "content");
    localJceDisplayer.display(this.reportType, "reportType");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.content, true);
    localJceDisplayer.displaySimple(this.reportType, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TReportData localTReportData;
    do
    {
      return false;
      localTReportData = (TReportData)paramObject;
    }
    while ((!JceUtil.equals(this.content, localTReportData.content)) || (!JceUtil.equals(this.reportType, localTReportData.reportType)));
    return true;
  }

  public String fullClassName()
  {
    return "CobraHallQmiProto.TReportData";
  }

  public byte[] getContent()
  {
    return this.content;
  }

  public int getReportType()
  {
    return this.reportType;
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
    if (cache_content == null)
    {
      cache_content = (byte[])new byte[1];
      ((byte[])cache_content)[0] = 0;
    }
    this.content = ((byte[])paramJceInputStream.read(cache_content, 0, true));
    this.reportType = paramJceInputStream.read(this.reportType, 1, true);
  }

  public void setContent(byte[] paramArrayOfByte)
  {
    this.content = paramArrayOfByte;
  }

  public void setReportType(int paramInt)
  {
    this.reportType = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.content, 0);
    paramJceOutputStream.write(this.reportType, 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.TReportData
 * JD-Core Version:    0.6.0
 */