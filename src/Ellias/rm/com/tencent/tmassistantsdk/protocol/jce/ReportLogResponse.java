package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class ReportLogResponse extends JceStruct
  implements Cloneable
{
  public int ret = 0;

  static
  {
    if (!ReportLogResponse.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public ReportLogResponse()
  {
  }

  public ReportLogResponse(int paramInt)
  {
    this.ret = paramInt;
  }

  public String className()
  {
    return "jce.ReportLogResponse";
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
        bool = a;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).display(this.ret, "ret");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    new JceDisplayer(paramStringBuilder, paramInt).displaySimple(this.ret, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    ReportLogResponse localReportLogResponse = (ReportLogResponse)paramObject;
    return JceUtil.equals(this.ret, localReportLogResponse.ret);
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.ReportLogResponse";
  }

  public int getRet()
  {
    return this.ret;
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
    this.ret = paramJceInputStream.read(this.ret, 0, true);
  }

  public void setRet(int paramInt)
  {
    this.ret = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.ret, 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.ReportLogResponse
 * JD-Core Version:    0.6.0
 */