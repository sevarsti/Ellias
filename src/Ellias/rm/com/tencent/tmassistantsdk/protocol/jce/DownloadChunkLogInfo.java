package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class DownloadChunkLogInfo extends JceStruct
  implements Cloneable
{
  public String UUID = "";
  public String appId = "";
  public byte currentRetryCnt = 0;
  public long endTime = 0L;
  public int errorCode = 0;
  public String flag = "";
  public byte isWap = 0;
  public String networkOperator = "";
  public int networkType = 0;
  public long readHeaderTime = 0L;
  public long receiveDataSize = 0L;
  public long requestRanagePosition = 0L;
  public long requestRanageSize = 0L;
  public String requestUrl = "";
  public long responseContentLength = 0L;
  public int responseHttpCode = 0;
  public long responseRangeLength = 0L;
  public long responseRangePosition = 0L;
  public int resultState = 0;
  public long startTime = 0L;
  public byte type = 0;
  public String via = "";

  static
  {
    if (!DownloadChunkLogInfo.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public DownloadChunkLogInfo()
  {
  }

  public DownloadChunkLogInfo(String paramString1, byte paramByte1, String paramString2, String paramString3, int paramInt1, byte paramByte2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, int paramInt2, long paramLong6, long paramLong7, long paramLong8, long paramLong9, int paramInt3, int paramInt4, byte paramByte3, String paramString4, String paramString5, String paramString6)
  {
    this.UUID = paramString1;
    this.type = paramByte1;
    this.requestUrl = paramString2;
    this.networkOperator = paramString3;
    this.networkType = paramInt1;
    this.isWap = paramByte2;
    this.requestRanagePosition = paramLong1;
    this.requestRanageSize = paramLong2;
    this.responseRangePosition = paramLong3;
    this.responseRangeLength = paramLong4;
    this.responseContentLength = paramLong5;
    this.responseHttpCode = paramInt2;
    this.receiveDataSize = paramLong6;
    this.startTime = paramLong7;
    this.readHeaderTime = paramLong8;
    this.endTime = paramLong9;
    this.errorCode = paramInt3;
    this.resultState = paramInt4;
    this.currentRetryCnt = paramByte3;
    this.via = paramString4;
    this.appId = paramString5;
    this.flag = paramString6;
  }

  public String className()
  {
    return "jce.DownloadChunkLogInfo";
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
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.display(this.UUID, "UUID");
    localJceDisplayer.display(this.type, "type");
    localJceDisplayer.display(this.requestUrl, "requestUrl");
    localJceDisplayer.display(this.networkOperator, "networkOperator");
    localJceDisplayer.display(this.networkType, "networkType");
    localJceDisplayer.display(this.isWap, "isWap");
    localJceDisplayer.display(this.requestRanagePosition, "requestRanagePosition");
    localJceDisplayer.display(this.requestRanageSize, "requestRanageSize");
    localJceDisplayer.display(this.responseRangePosition, "responseRangePosition");
    localJceDisplayer.display(this.responseRangeLength, "responseRangeLength");
    localJceDisplayer.display(this.responseContentLength, "responseContentLength");
    localJceDisplayer.display(this.responseHttpCode, "responseHttpCode");
    localJceDisplayer.display(this.receiveDataSize, "receiveDataSize");
    localJceDisplayer.display(this.startTime, "startTime");
    localJceDisplayer.display(this.readHeaderTime, "readHeaderTime");
    localJceDisplayer.display(this.endTime, "endTime");
    localJceDisplayer.display(this.errorCode, "errorCode");
    localJceDisplayer.display(this.resultState, "resultState");
    localJceDisplayer.display(this.currentRetryCnt, "currentRetryCnt");
    localJceDisplayer.display(this.via, "via");
    localJceDisplayer.display(this.appId, "appId");
    localJceDisplayer.display(this.flag, "flag");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.UUID, true);
    localJceDisplayer.displaySimple(this.type, true);
    localJceDisplayer.displaySimple(this.requestUrl, true);
    localJceDisplayer.displaySimple(this.networkOperator, true);
    localJceDisplayer.displaySimple(this.networkType, true);
    localJceDisplayer.displaySimple(this.isWap, true);
    localJceDisplayer.displaySimple(this.requestRanagePosition, true);
    localJceDisplayer.displaySimple(this.requestRanageSize, true);
    localJceDisplayer.displaySimple(this.responseRangePosition, true);
    localJceDisplayer.displaySimple(this.responseRangeLength, true);
    localJceDisplayer.displaySimple(this.responseContentLength, true);
    localJceDisplayer.displaySimple(this.responseHttpCode, true);
    localJceDisplayer.displaySimple(this.receiveDataSize, true);
    localJceDisplayer.displaySimple(this.startTime, true);
    localJceDisplayer.displaySimple(this.readHeaderTime, true);
    localJceDisplayer.displaySimple(this.endTime, true);
    localJceDisplayer.displaySimple(this.errorCode, true);
    localJceDisplayer.displaySimple(this.resultState, true);
    localJceDisplayer.displaySimple(this.currentRetryCnt, true);
    localJceDisplayer.displaySimple(this.via, true);
    localJceDisplayer.displaySimple(this.appId, true);
    localJceDisplayer.displaySimple(this.flag, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    DownloadChunkLogInfo localDownloadChunkLogInfo;
    do
    {
      return false;
      localDownloadChunkLogInfo = (DownloadChunkLogInfo)paramObject;
    }
    while ((!JceUtil.equals(this.UUID, localDownloadChunkLogInfo.UUID)) || (!JceUtil.equals(this.type, localDownloadChunkLogInfo.type)) || (!JceUtil.equals(this.requestUrl, localDownloadChunkLogInfo.requestUrl)) || (!JceUtil.equals(this.networkOperator, localDownloadChunkLogInfo.networkOperator)) || (!JceUtil.equals(this.networkType, localDownloadChunkLogInfo.networkType)) || (!JceUtil.equals(this.isWap, localDownloadChunkLogInfo.isWap)) || (!JceUtil.equals(this.requestRanagePosition, localDownloadChunkLogInfo.requestRanagePosition)) || (!JceUtil.equals(this.requestRanageSize, localDownloadChunkLogInfo.requestRanageSize)) || (!JceUtil.equals(this.responseRangePosition, localDownloadChunkLogInfo.responseRangePosition)) || (!JceUtil.equals(this.responseRangeLength, localDownloadChunkLogInfo.responseRangeLength)) || (!JceUtil.equals(this.responseContentLength, localDownloadChunkLogInfo.responseContentLength)) || (!JceUtil.equals(this.responseHttpCode, localDownloadChunkLogInfo.responseHttpCode)) || (!JceUtil.equals(this.receiveDataSize, localDownloadChunkLogInfo.receiveDataSize)) || (!JceUtil.equals(this.startTime, localDownloadChunkLogInfo.startTime)) || (!JceUtil.equals(this.readHeaderTime, localDownloadChunkLogInfo.readHeaderTime)) || (!JceUtil.equals(this.endTime, localDownloadChunkLogInfo.endTime)) || (!JceUtil.equals(this.errorCode, localDownloadChunkLogInfo.errorCode)) || (!JceUtil.equals(this.resultState, localDownloadChunkLogInfo.resultState)) || (!JceUtil.equals(this.currentRetryCnt, localDownloadChunkLogInfo.currentRetryCnt)) || (!JceUtil.equals(this.via, localDownloadChunkLogInfo.via)) || (!JceUtil.equals(this.appId, localDownloadChunkLogInfo.appId)) || (!JceUtil.equals(this.flag, localDownloadChunkLogInfo.flag)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.DownloadChunkLogInfo";
  }

  public String getAppId()
  {
    return this.appId;
  }

  public byte getCurrentRetryCnt()
  {
    return this.currentRetryCnt;
  }

  public long getEndTime()
  {
    return this.endTime;
  }

  public int getErrorCode()
  {
    return this.errorCode;
  }

  public String getFlag()
  {
    return this.flag;
  }

  public byte getIsWap()
  {
    return this.isWap;
  }

  public String getNetworkOperator()
  {
    return this.networkOperator;
  }

  public int getNetworkType()
  {
    return this.networkType;
  }

  public long getReadHeaderTime()
  {
    return this.readHeaderTime;
  }

  public long getReceiveDataSize()
  {
    return this.receiveDataSize;
  }

  public long getRequestRanagePosition()
  {
    return this.requestRanagePosition;
  }

  public long getRequestRanageSize()
  {
    return this.requestRanageSize;
  }

  public String getRequestUrl()
  {
    return this.requestUrl;
  }

  public long getResponseContentLength()
  {
    return this.responseContentLength;
  }

  public int getResponseHttpCode()
  {
    return this.responseHttpCode;
  }

  public long getResponseRangeLength()
  {
    return this.responseRangeLength;
  }

  public long getResponseRangePosition()
  {
    return this.responseRangePosition;
  }

  public int getResultState()
  {
    return this.resultState;
  }

  public long getStartTime()
  {
    return this.startTime;
  }

  public byte getType()
  {
    return this.type;
  }

  public String getUUID()
  {
    return this.UUID;
  }

  public String getVia()
  {
    return this.via;
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
    this.UUID = paramJceInputStream.readString(0, false);
    this.type = paramJceInputStream.read(this.type, 1, false);
    this.requestUrl = paramJceInputStream.readString(2, false);
    this.networkOperator = paramJceInputStream.readString(3, false);
    this.networkType = paramJceInputStream.read(this.networkType, 4, false);
    this.isWap = paramJceInputStream.read(this.isWap, 5, false);
    this.requestRanagePosition = paramJceInputStream.read(this.requestRanagePosition, 6, false);
    this.requestRanageSize = paramJceInputStream.read(this.requestRanageSize, 7, false);
    this.responseRangePosition = paramJceInputStream.read(this.responseRangePosition, 9, false);
    this.responseRangeLength = paramJceInputStream.read(this.responseRangeLength, 10, false);
    this.responseContentLength = paramJceInputStream.read(this.responseContentLength, 11, false);
    this.responseHttpCode = paramJceInputStream.read(this.responseHttpCode, 12, false);
    this.receiveDataSize = paramJceInputStream.read(this.receiveDataSize, 13, false);
    this.startTime = paramJceInputStream.read(this.startTime, 14, false);
    this.readHeaderTime = paramJceInputStream.read(this.readHeaderTime, 15, false);
    this.endTime = paramJceInputStream.read(this.endTime, 16, false);
    this.errorCode = paramJceInputStream.read(this.errorCode, 17, false);
    this.resultState = paramJceInputStream.read(this.resultState, 18, false);
    this.currentRetryCnt = paramJceInputStream.read(this.currentRetryCnt, 19, false);
    this.via = paramJceInputStream.readString(20, false);
    this.appId = paramJceInputStream.readString(21, false);
    this.flag = paramJceInputStream.readString(22, false);
  }

  public void setAppId(String paramString)
  {
    this.appId = paramString;
  }

  public void setCurrentRetryCnt(byte paramByte)
  {
    this.currentRetryCnt = paramByte;
  }

  public void setEndTime(long paramLong)
  {
    this.endTime = paramLong;
  }

  public void setErrorCode(int paramInt)
  {
    this.errorCode = paramInt;
  }

  public void setFlag(String paramString)
  {
    this.flag = paramString;
  }

  public void setIsWap(byte paramByte)
  {
    this.isWap = paramByte;
  }

  public void setNetworkOperator(String paramString)
  {
    this.networkOperator = paramString;
  }

  public void setNetworkType(int paramInt)
  {
    this.networkType = paramInt;
  }

  public void setReadHeaderTime(long paramLong)
  {
    this.readHeaderTime = paramLong;
  }

  public void setReceiveDataSize(long paramLong)
  {
    this.receiveDataSize = paramLong;
  }

  public void setRequestRanagePosition(long paramLong)
  {
    this.requestRanagePosition = paramLong;
  }

  public void setRequestRanageSize(long paramLong)
  {
    this.requestRanageSize = paramLong;
  }

  public void setRequestUrl(String paramString)
  {
    this.requestUrl = paramString;
  }

  public void setResponseContentLength(long paramLong)
  {
    this.responseContentLength = paramLong;
  }

  public void setResponseHttpCode(int paramInt)
  {
    this.responseHttpCode = paramInt;
  }

  public void setResponseRangeLength(long paramLong)
  {
    this.responseRangeLength = paramLong;
  }

  public void setResponseRangePosition(long paramLong)
  {
    this.responseRangePosition = paramLong;
  }

  public void setResultState(int paramInt)
  {
    this.resultState = paramInt;
  }

  public void setStartTime(long paramLong)
  {
    this.startTime = paramLong;
  }

  public void setType(byte paramByte)
  {
    this.type = paramByte;
  }

  public void setUUID(String paramString)
  {
    this.UUID = paramString;
  }

  public void setVia(String paramString)
  {
    this.via = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.UUID != null)
      paramJceOutputStream.write(this.UUID, 0);
    paramJceOutputStream.write(this.type, 1);
    if (this.requestUrl != null)
      paramJceOutputStream.write(this.requestUrl, 2);
    if (this.networkOperator != null)
      paramJceOutputStream.write(this.networkOperator, 3);
    paramJceOutputStream.write(this.networkType, 4);
    paramJceOutputStream.write(this.isWap, 5);
    paramJceOutputStream.write(this.requestRanagePosition, 6);
    paramJceOutputStream.write(this.requestRanageSize, 7);
    paramJceOutputStream.write(this.responseRangePosition, 9);
    paramJceOutputStream.write(this.responseRangeLength, 10);
    paramJceOutputStream.write(this.responseContentLength, 11);
    paramJceOutputStream.write(this.responseHttpCode, 12);
    paramJceOutputStream.write(this.receiveDataSize, 13);
    paramJceOutputStream.write(this.startTime, 14);
    paramJceOutputStream.write(this.readHeaderTime, 15);
    paramJceOutputStream.write(this.endTime, 16);
    paramJceOutputStream.write(this.errorCode, 17);
    paramJceOutputStream.write(this.resultState, 18);
    paramJceOutputStream.write(this.currentRetryCnt, 19);
    if (this.via != null)
      paramJceOutputStream.write(this.via, 20);
    if (this.appId != null)
      paramJceOutputStream.write(this.appId, 21);
    if (this.flag != null)
      paramJceOutputStream.write(this.flag, 22);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.DownloadChunkLogInfo
 * JD-Core Version:    0.6.0
 */