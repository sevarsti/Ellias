package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class ReportLogRequest extends JceStruct
  implements Cloneable
{
  static byte[] a;
  public String hostAppPackageName = "";
  public int hostAppVersion = 0;
  public String hostUserId = "";
  public byte[] logData = null;
  public int logType = 0;

  static
  {
    if (!ReportLogRequest.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      b = bool;
      return;
    }
  }

  public ReportLogRequest()
  {
  }

  public ReportLogRequest(int paramInt1, byte[] paramArrayOfByte, String paramString1, String paramString2, int paramInt2)
  {
    this.logType = paramInt1;
    this.logData = paramArrayOfByte;
    this.hostUserId = paramString1;
    this.hostAppPackageName = paramString2;
    this.hostAppVersion = paramInt2;
  }

  public String className()
  {
    return "jce.ReportLogRequest";
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
        bool = b;
        Object localObject1 = null;
      }
      while (bool);
    }
    throw new AssertionError();
  }

  public void display(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.logType, "logType");
    localJceDisplayer.display(this.logData, "logData");
    localJceDisplayer.display(this.hostUserId, "hostUserId");
    localJceDisplayer.display(this.hostAppPackageName, "hostAppPackageName");
    localJceDisplayer.display(this.hostAppVersion, "hostAppVersion");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.logType, true);
    localJceDisplayer.displaySimple(this.logData, true);
    localJceDisplayer.displaySimple(this.hostUserId, true);
    localJceDisplayer.displaySimple(this.hostAppPackageName, true);
    localJceDisplayer.displaySimple(this.hostAppVersion, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    ReportLogRequest localReportLogRequest;
    do
    {
      return false;
      localReportLogRequest = (ReportLogRequest)paramObject;
    }
    while ((!JceUtil.equals(this.logType, localReportLogRequest.logType)) || (!JceUtil.equals(this.logData, localReportLogRequest.logData)) || (!JceUtil.equals(this.hostUserId, localReportLogRequest.hostUserId)) || (!JceUtil.equals(this.hostAppPackageName, localReportLogRequest.hostAppPackageName)) || (!JceUtil.equals(this.hostAppVersion, localReportLogRequest.hostAppVersion)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.ReportLogRequest";
  }

  public String getHostAppPackageName()
  {
    return this.hostAppPackageName;
  }

  public int getHostAppVersion()
  {
    return this.hostAppVersion;
  }

  public String getHostUserId()
  {
    return this.hostUserId;
  }

  public byte[] getLogData()
  {
    return this.logData;
  }

  public int getLogType()
  {
    return this.logType;
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
    this.logType = paramJceInputStream.read(this.logType, 0, false);
    if (a == null)
    {
      a = (byte[])new byte[1];
      ((byte[])a)[0] = 0;
    }
    this.logData = ((byte[])paramJceInputStream.read(a, 1, false));
    this.hostUserId = paramJceInputStream.readString(2, false);
    this.hostAppPackageName = paramJceInputStream.readString(3, false);
    this.hostAppVersion = paramJceInputStream.read(this.hostAppVersion, 4, false);
  }

  public void setHostAppPackageName(String paramString)
  {
    this.hostAppPackageName = paramString;
  }

  public void setHostAppVersion(int paramInt)
  {
    this.hostAppVersion = paramInt;
  }

  public void setHostUserId(String paramString)
  {
    this.hostUserId = paramString;
  }

  public void setLogData(byte[] paramArrayOfByte)
  {
    this.logData = paramArrayOfByte;
  }

  public void setLogType(int paramInt)
  {
    this.logType = paramInt;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    paramJceOutputStream.write(this.logType, 0);
    if (this.logData != null)
      paramJceOutputStream.write(this.logData, 1);
    if (this.hostUserId != null)
      paramJceOutputStream.write(this.hostUserId, 2);
    if (this.hostAppPackageName != null)
      paramJceOutputStream.write(this.hostAppPackageName, 3);
    paramJceOutputStream.write(this.hostAppVersion, 4);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.ReportLogRequest
 * JD-Core Version:    0.6.0
 */