package com.tencent.tmassistantsdk.selfUpdateSDK;

public class TMSelfUpdateSDKUpdateInfo
{
  public static final int STATUS_CHECKUPDATE_FAILURE = 1;
  public static final int STATUS_CHECKUPDATE_RESPONSE_IS_NULL = 2;
  public static final int STATUS_OK = 0;
  public static final int UpdateMethod_ByPatch = 2;
  public static final int UpdateMethod_NoUpdate = 0;
  public static final int UpdateMethod_Normal = 1;
  private long newApkSize;
  private String newFeature;
  private long patchSize;
  private int status = 0;
  private String updateDownloadUrl;
  private int updateMethod;

  public TMSelfUpdateSDKUpdateInfo(int paramInt1, int paramInt2, long paramLong1, long paramLong2, String paramString1, String paramString2)
  {
    this.status = paramInt1;
    this.updateMethod = paramInt2;
    this.newApkSize = paramLong1;
    this.patchSize = paramLong2;
    this.newFeature = paramString1;
    this.updateDownloadUrl = paramString2;
  }

  public long getNewApkSize()
  {
    return this.newApkSize;
  }

  public String getNewFeature()
  {
    return this.newFeature;
  }

  public long getPatchSize()
  {
    return this.patchSize;
  }

  public int getStatus()
  {
    return this.status;
  }

  public String getUpdateDownloadUrl()
  {
    return this.updateDownloadUrl;
  }

  public int getUpdateMethod()
  {
    return this.updateMethod;
  }

  public void setNewApkSize(long paramLong)
  {
    this.newApkSize = paramLong;
  }

  public void setNewFeature(String paramString)
  {
    this.newFeature = paramString;
  }

  public void setPatchSize(long paramLong)
  {
    this.patchSize = paramLong;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setUpdateDownloadUrl(String paramString)
  {
    this.updateDownloadUrl = paramString;
  }

  public void setUpdateMethod(int paramInt)
  {
    this.updateMethod = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.TMSelfUpdateSDKUpdateInfo
 * JD-Core Version:    0.6.0
 */