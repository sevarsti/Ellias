package exceptionupload;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class AssertDetailInfo extends JceStruct
  implements Cloneable
{
  static RunInfo cache_runInfo;
  public String apn = "";
  public long assertTime = 0L;
  public String message = "";
  public RunInfo runInfo = null;
  public String threadStacks = "";

  static
  {
    if (!AssertDetailInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AssertDetailInfo()
  {
  }

  public AssertDetailInfo(long paramLong, String paramString1, RunInfo paramRunInfo, String paramString2, String paramString3)
  {
    this.assertTime = paramLong;
    this.message = paramString1;
    this.runInfo = paramRunInfo;
    this.threadStacks = paramString2;
    this.apn = paramString3;
  }

  public final String className()
  {
    return "exceptionupload.AssertDetailInfo";
  }

  public final Object clone()
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

  public final void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.assertTime, "assertTime");
    localJceDisplayer.display(this.message, "message");
    localJceDisplayer.display(this.runInfo, "runInfo");
    localJceDisplayer.display(this.threadStacks, "threadStacks");
    localJceDisplayer.display(this.apn, "apn");
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == null);
    AssertDetailInfo localAssertDetailInfo;
    do
    {
      return false;
      localAssertDetailInfo = (AssertDetailInfo)paramObject;
    }
    while ((!JceUtil.equals(this.assertTime, localAssertDetailInfo.assertTime)) || (!JceUtil.equals(this.message, localAssertDetailInfo.message)) || (!JceUtil.equals(this.runInfo, localAssertDetailInfo.runInfo)) || (!JceUtil.equals(this.threadStacks, localAssertDetailInfo.threadStacks)) || (!JceUtil.equals(this.apn, localAssertDetailInfo.apn)));
    return true;
  }

  public final String fullClassName()
  {
    return "exceptionupload.AssertDetailInfo";
  }

  public final String getApn()
  {
    return this.apn;
  }

  public final long getAssertTime()
  {
    return this.assertTime;
  }

  public final String getMessage()
  {
    return this.message;
  }

  public final RunInfo getRunInfo()
  {
    return this.runInfo;
  }

  public final String getThreadStacks()
  {
    return this.threadStacks;
  }

  public final int hashCode()
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

  public final void readFrom(JceInputStream paramJceInputStream)
  {
    this.assertTime = paramJceInputStream.read(this.assertTime, 0, true);
    this.message = paramJceInputStream.readString(1, true);
    if (cache_runInfo == null)
      cache_runInfo = new RunInfo();
    this.runInfo = ((RunInfo)paramJceInputStream.read(cache_runInfo, 2, false));
    this.threadStacks = paramJceInputStream.readString(3, false);
    this.apn = paramJceInputStream.readString(4, false);
  }

  public final void setApn(String paramString)
  {
    this.apn = paramString;
  }

  public final void setAssertTime(long paramLong)
  {
    this.assertTime = paramLong;
  }

  public final void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public final void setRunInfo(RunInfo paramRunInfo)
  {
    this.runInfo = paramRunInfo;
  }

  public final void setThreadStacks(String paramString)
  {
    this.threadStacks = paramString;
  }

  public final void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.assertTime, 0);
    paramJceOutputStream.write(this.message, 1);
    if (this.runInfo != null)
      paramJceOutputStream.write(this.runInfo, 2);
    if (this.threadStacks != null)
      paramJceOutputStream.write(this.threadStacks, 3);
    if (this.apn != null)
      paramJceOutputStream.write(this.apn, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     exceptionupload.AssertDetailInfo
 * JD-Core Version:    0.6.0
 */