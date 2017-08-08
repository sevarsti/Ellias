package com.tencent.tmassistantsdk.protocol.jce;

import com.qq.taf.jce.JceDisplayer;
import com.qq.taf.jce.JceInputStream;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.JceUtil;

public final class TipsInfoLog extends JceStruct
  implements Cloneable
{
  public int authorizedBtnClickCount = 0;
  public int authorizedTipsCount = 0;
  public int cancelBtnClickCount = 0;
  public int downloadBtnClickCount = 0;
  public int downloadTipsCount = 0;
  public String gameChannelId = "";
  public String gamePackageName = "";
  public int gameVersionCode = 0;
  public int installBtnClickCount = 0;
  public int installTipsCount = 0;
  public int networkErrorTipsCount = 0;
  public String userId = "";
  public String userIdType = "";

  static
  {
    if (!TipsInfoLog.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public TipsInfoLog()
  {
  }

  public TipsInfoLog(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    this.userId = paramString1;
    this.userIdType = paramString2;
    this.gamePackageName = paramString3;
    this.gameVersionCode = paramInt1;
    this.gameChannelId = paramString4;
    this.authorizedTipsCount = paramInt2;
    this.downloadTipsCount = paramInt3;
    this.installTipsCount = paramInt4;
    this.networkErrorTipsCount = paramInt5;
    this.cancelBtnClickCount = paramInt6;
    this.downloadBtnClickCount = paramInt7;
    this.installBtnClickCount = paramInt8;
    this.authorizedBtnClickCount = paramInt9;
  }

  public String className()
  {
    return "jce.TipsInfoLog";
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
    localJceDisplayer.display(this.userId, "userId");
    localJceDisplayer.display(this.userIdType, "userIdType");
    localJceDisplayer.display(this.gamePackageName, "gamePackageName");
    localJceDisplayer.display(this.gameVersionCode, "gameVersionCode");
    localJceDisplayer.display(this.gameChannelId, "gameChannelId");
    localJceDisplayer.display(this.authorizedTipsCount, "authorizedTipsCount");
    localJceDisplayer.display(this.downloadTipsCount, "downloadTipsCount");
    localJceDisplayer.display(this.installTipsCount, "installTipsCount");
    localJceDisplayer.display(this.networkErrorTipsCount, "networkErrorTipsCount");
    localJceDisplayer.display(this.cancelBtnClickCount, "cancelBtnClickCount");
    localJceDisplayer.display(this.downloadBtnClickCount, "downloadBtnClickCount");
    localJceDisplayer.display(this.installBtnClickCount, "installBtnClickCount");
    localJceDisplayer.display(this.authorizedBtnClickCount, "authorizedBtnClickCount");
  }

  public void displaySimple(StringBuilder paramStringBuilder, int paramInt)
  {
    JceDisplayer localJceDisplayer = new JceDisplayer(paramStringBuilder, paramInt);
    localJceDisplayer.displaySimple(this.userId, true);
    localJceDisplayer.displaySimple(this.userIdType, true);
    localJceDisplayer.displaySimple(this.gamePackageName, true);
    localJceDisplayer.displaySimple(this.gameVersionCode, true);
    localJceDisplayer.displaySimple(this.gameChannelId, true);
    localJceDisplayer.displaySimple(this.authorizedTipsCount, true);
    localJceDisplayer.displaySimple(this.downloadTipsCount, true);
    localJceDisplayer.displaySimple(this.installTipsCount, true);
    localJceDisplayer.displaySimple(this.networkErrorTipsCount, true);
    localJceDisplayer.displaySimple(this.cancelBtnClickCount, true);
    localJceDisplayer.displaySimple(this.downloadBtnClickCount, true);
    localJceDisplayer.displaySimple(this.installBtnClickCount, true);
    localJceDisplayer.displaySimple(this.authorizedBtnClickCount, false);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    TipsInfoLog localTipsInfoLog;
    do
    {
      return false;
      localTipsInfoLog = (TipsInfoLog)paramObject;
    }
    while ((!JceUtil.equals(this.userId, localTipsInfoLog.userId)) || (!JceUtil.equals(this.userIdType, localTipsInfoLog.userIdType)) || (!JceUtil.equals(this.gamePackageName, localTipsInfoLog.gamePackageName)) || (!JceUtil.equals(this.gameVersionCode, localTipsInfoLog.gameVersionCode)) || (!JceUtil.equals(this.gameChannelId, localTipsInfoLog.gameChannelId)) || (!JceUtil.equals(this.authorizedTipsCount, localTipsInfoLog.authorizedTipsCount)) || (!JceUtil.equals(this.downloadTipsCount, localTipsInfoLog.downloadTipsCount)) || (!JceUtil.equals(this.installTipsCount, localTipsInfoLog.installTipsCount)) || (!JceUtil.equals(this.networkErrorTipsCount, localTipsInfoLog.networkErrorTipsCount)) || (!JceUtil.equals(this.cancelBtnClickCount, localTipsInfoLog.cancelBtnClickCount)) || (!JceUtil.equals(this.downloadBtnClickCount, localTipsInfoLog.downloadBtnClickCount)) || (!JceUtil.equals(this.installBtnClickCount, localTipsInfoLog.installBtnClickCount)) || (!JceUtil.equals(this.authorizedBtnClickCount, localTipsInfoLog.authorizedBtnClickCount)));
    return true;
  }

  public String fullClassName()
  {
    return "com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog";
  }

  public int getAuthorizedBtnClickCount()
  {
    return this.authorizedBtnClickCount;
  }

  public int getAuthorizedTipsCount()
  {
    return this.authorizedTipsCount;
  }

  public int getCancelBtnClickCount()
  {
    return this.cancelBtnClickCount;
  }

  public int getDownloadBtnClickCount()
  {
    return this.downloadBtnClickCount;
  }

  public int getDownloadTipsCount()
  {
    return this.downloadTipsCount;
  }

  public String getGameChannelId()
  {
    return this.gameChannelId;
  }

  public String getGamePackageName()
  {
    return this.gamePackageName;
  }

  public int getGameVersionCode()
  {
    return this.gameVersionCode;
  }

  public int getInstallBtnClickCount()
  {
    return this.installBtnClickCount;
  }

  public int getInstallTipsCount()
  {
    return this.installTipsCount;
  }

  public int getNetworkErrorTipsCount()
  {
    return this.networkErrorTipsCount;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public String getUserIdType()
  {
    return this.userIdType;
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
    this.userId = paramJceInputStream.readString(0, false);
    this.userIdType = paramJceInputStream.readString(1, false);
    this.gamePackageName = paramJceInputStream.readString(2, false);
    this.gameVersionCode = paramJceInputStream.read(this.gameVersionCode, 3, false);
    this.gameChannelId = paramJceInputStream.readString(4, false);
    this.authorizedTipsCount = paramJceInputStream.read(this.authorizedTipsCount, 5, false);
    this.downloadTipsCount = paramJceInputStream.read(this.downloadTipsCount, 6, false);
    this.installTipsCount = paramJceInputStream.read(this.installTipsCount, 7, false);
    this.networkErrorTipsCount = paramJceInputStream.read(this.networkErrorTipsCount, 8, false);
    this.cancelBtnClickCount = paramJceInputStream.read(this.cancelBtnClickCount, 9, false);
    this.downloadBtnClickCount = paramJceInputStream.read(this.downloadBtnClickCount, 10, false);
    this.installBtnClickCount = paramJceInputStream.read(this.installBtnClickCount, 11, false);
    this.authorizedBtnClickCount = paramJceInputStream.read(this.authorizedBtnClickCount, 12, false);
  }

  public void setAuthorizedBtnClickCount(int paramInt)
  {
    this.authorizedBtnClickCount = paramInt;
  }

  public void setAuthorizedTipsCount(int paramInt)
  {
    this.authorizedTipsCount = paramInt;
  }

  public void setCancelBtnClickCount(int paramInt)
  {
    this.cancelBtnClickCount = paramInt;
  }

  public void setDownloadBtnClickCount(int paramInt)
  {
    this.downloadBtnClickCount = paramInt;
  }

  public void setDownloadTipsCount(int paramInt)
  {
    this.downloadTipsCount = paramInt;
  }

  public void setGameChannelId(String paramString)
  {
    this.gameChannelId = paramString;
  }

  public void setGamePackageName(String paramString)
  {
    this.gamePackageName = paramString;
  }

  public void setGameVersionCode(int paramInt)
  {
    this.gameVersionCode = paramInt;
  }

  public void setInstallBtnClickCount(int paramInt)
  {
    this.installBtnClickCount = paramInt;
  }

  public void setInstallTipsCount(int paramInt)
  {
    this.installTipsCount = paramInt;
  }

  public void setNetworkErrorTipsCount(int paramInt)
  {
    this.networkErrorTipsCount = paramInt;
  }

  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }

  public void setUserIdType(String paramString)
  {
    this.userIdType = paramString;
  }

  public void writeTo(JceOutputStream paramJceOutputStream)
  {
    if (this.userId != null)
      paramJceOutputStream.write(this.userId, 0);
    if (this.userIdType != null)
      paramJceOutputStream.write(this.userIdType, 1);
    if (this.gamePackageName != null)
      paramJceOutputStream.write(this.gamePackageName, 2);
    paramJceOutputStream.write(this.gameVersionCode, 3);
    if (this.gameChannelId != null)
      paramJceOutputStream.write(this.gameChannelId, 4);
    paramJceOutputStream.write(this.authorizedTipsCount, 5);
    paramJceOutputStream.write(this.downloadTipsCount, 6);
    paramJceOutputStream.write(this.installTipsCount, 7);
    paramJceOutputStream.write(this.networkErrorTipsCount, 8);
    paramJceOutputStream.write(this.cancelBtnClickCount, 9);
    paramJceOutputStream.write(this.downloadBtnClickCount, 10);
    paramJceOutputStream.write(this.installBtnClickCount, 11);
    paramJceOutputStream.write(this.authorizedBtnClickCount, 12);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog
 * JD-Core Version:    0.6.0
 */