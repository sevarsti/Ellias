package com.tencent.apkupdate;

public class ApkUpdateParam
{
  public int actionFlag;
  public String packageName;
  public int targetGrayVersionCode;
  public int targetVersionCode;

  public ApkUpdateParam(String paramString, int paramInt1, int paramInt2)
  {
    this.packageName = paramString;
    this.actionFlag = paramInt1;
    this.targetVersionCode = paramInt2;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.ApkUpdateParam
 * JD-Core Version:    0.6.0
 */