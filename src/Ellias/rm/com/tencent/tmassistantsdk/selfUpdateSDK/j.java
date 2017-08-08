package com.tencent.tmassistantsdk.selfUpdateSDK;

import com.tencent.apkupdate.ApkUpdateListener;
import com.tencent.apkupdate.logic.data.ApkUpdateDetail;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;

class j
  implements ApkUpdateListener
{
  j(TMSelfUpdateSDK paramTMSelfUpdateSDK)
  {
  }

  public void onCheckUpdateFailed(String paramString)
  {
    if (TMSelfUpdateSDK.access$200())
    {
      TMSelfUpdateSDK localTMSelfUpdateSDK = this.a;
      StringBuilder localStringBuilder = new StringBuilder().append("onCheckUpdateFailed; message=");
      if (paramString != null);
      while (true)
      {
        localTMSelfUpdateSDK.onStateChanged(2, -12, paramString);
        return;
        paramString = "";
      }
    }
    TMSelfUpdateSDKUpdateInfo localTMSelfUpdateSDKUpdateInfo = new TMSelfUpdateSDKUpdateInfo(1, 0, 0L, 0L, "", "");
    TMSelfUpdateSDK.access$300(this.a, localTMSelfUpdateSDKUpdateInfo);
  }

  public void onCheckUpdateSucceed(ArrayList paramArrayList)
  {
    int i = 1;
    ApkUpdateDetail localApkUpdateDetail;
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      localApkUpdateDetail = (ApkUpdateDetail)paramArrayList.get(0);
      if (TMSelfUpdateSDK.access$200())
      {
        if (localApkUpdateDetail != null)
        {
          StringBuilder localStringBuilder1 = new StringBuilder().append("onCheckUpdateSucceed(),selfUpdateDetail:pakgname=");
          String str1;
          StringBuilder localStringBuilder2;
          if (localApkUpdateDetail.packageName != null)
          {
            str1 = localApkUpdateDetail.packageName;
            localStringBuilder2 = localStringBuilder1.append(str1).append("; versioncode=").append(localApkUpdateDetail.versioncode).append("; updatemethod=").append(localApkUpdateDetail.updatemethod).append("; url=");
            if (localApkUpdateDetail.url == null)
              break label172;
          }
          label172: for (String str2 = localApkUpdateDetail.url; ; str2 = "")
          {
            l.b("SelfUpdateSDK", str2);
            this.a.mCheckUpdateMethod = localApkUpdateDetail.updatemethod;
            this.a.mCheckUpdateDownurl = localApkUpdateDetail.url;
            this.a.genNewPkgProcess();
            return;
            str1 = "";
            break;
          }
        }
        this.a.onStateChanged(2, -13, "onCheckUpdateSucceed,but apkUpdateDetailList is null!");
        return;
      }
      if (localApkUpdateDetail != null)
        if (localApkUpdateDetail.updatemethod == i)
          i = 0;
    }
    while (true)
    {
      TMSelfUpdateSDKUpdateInfo localTMSelfUpdateSDKUpdateInfo3 = new TMSelfUpdateSDKUpdateInfo(0, i, localApkUpdateDetail.newapksize, localApkUpdateDetail.patchsize, localApkUpdateDetail.newFeature, localApkUpdateDetail.url);
      TMSelfUpdateSDK.access$300(this.a, localTMSelfUpdateSDKUpdateInfo3);
      return;
      if (localApkUpdateDetail.updatemethod == 2)
        continue;
      if (localApkUpdateDetail.updatemethod == 4)
      {
        i = 2;
        continue;
        TMSelfUpdateSDKUpdateInfo localTMSelfUpdateSDKUpdateInfo2 = new TMSelfUpdateSDKUpdateInfo(2, 0, 0L, 0L, "", "");
        TMSelfUpdateSDK.access$300(this.a, localTMSelfUpdateSDKUpdateInfo2);
        return;
        if (TMSelfUpdateSDK.access$200())
        {
          this.a.onStateChanged(0, -15, "SelfUpdate success, NO Update!");
          return;
        }
        TMSelfUpdateSDKUpdateInfo localTMSelfUpdateSDKUpdateInfo1 = new TMSelfUpdateSDKUpdateInfo(0, 0, 0L, 0L, "", "");
        TMSelfUpdateSDK.access$300(this.a, localTMSelfUpdateSDKUpdateInfo1);
        return;
      }
      i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.j
 * JD-Core Version:    0.6.0
 */