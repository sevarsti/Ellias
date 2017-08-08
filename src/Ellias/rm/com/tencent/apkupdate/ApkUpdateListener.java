package com.tencent.apkupdate;

import java.util.ArrayList;

public abstract interface ApkUpdateListener
{
  public abstract void onCheckUpdateFailed(String paramString);

  public abstract void onCheckUpdateSucceed(ArrayList paramArrayList);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.ApkUpdateListener
 * JD-Core Version:    0.6.0
 */