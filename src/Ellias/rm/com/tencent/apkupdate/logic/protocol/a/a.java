package com.tencent.apkupdate.logic.protocol.a;

import android.os.Message;
import com.qq.taf.jce.JceStruct;
import com.tencent.apkupdate.c.f;
import com.tencent.apkupdate.logic.data.ApkUpdateDetail;
import com.tencent.apkupdate.logic.protocol.jce.ApkDownUrl;
import com.tencent.apkupdate.logic.protocol.jce.AppInfoForUpdate;
import com.tencent.apkupdate.logic.protocol.jce.AppUpdateInfo;
import com.tencent.apkupdate.logic.protocol.jce.GetAppUpdateRequest;
import com.tencent.apkupdate.logic.protocol.jce.GetAppUpdateResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class a extends com.tencent.apkupdate.logic.protocol.a
{
  private static String a = "CheckAppUpdateHttpRequest";
  private final List b;

  public a(List paramList)
  {
    this.b = paramList;
  }

  private static ApkUpdateDetail a(AppUpdateInfo paramAppUpdateInfo)
  {
    if (paramAppUpdateInfo != null)
    {
      ApkUpdateDetail localApkUpdateDetail = new ApkUpdateDetail();
      localApkUpdateDetail.packageName = paramAppUpdateInfo.packageName;
      localApkUpdateDetail.versionname = paramAppUpdateInfo.versionName;
      localApkUpdateDetail.versioncode = paramAppUpdateInfo.versionCode;
      ArrayList localArrayList = paramAppUpdateInfo.apkDownUrl;
      String str3;
      String str1;
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        Iterator localIterator2 = localArrayList.iterator();
        while (true)
          if (localIterator2.hasNext())
          {
            ApkDownUrl localApkDownUrl2 = (ApkDownUrl)localIterator2.next();
            if ((localApkDownUrl2 == null) || (localApkDownUrl2.type != 2))
              continue;
            if (localApkDownUrl2.urlList == null)
              break;
            str3 = (String)localApkDownUrl2.urlList.get(0);
            str1 = str3;
          }
      }
      for (int i = 1; ; i = 0)
      {
        Object localObject = null;
        String str2;
        if (i == 0)
        {
          localObject = null;
          if (localArrayList != null)
          {
            int j = localArrayList.size();
            localObject = null;
            if (j > 0)
            {
              Iterator localIterator1 = localArrayList.iterator();
              while (true)
              {
                boolean bool = localIterator1.hasNext();
                localObject = null;
                if (!bool)
                  break;
                ApkDownUrl localApkDownUrl1 = (ApkDownUrl)localIterator1.next();
                if ((localApkDownUrl1 == null) || (localApkDownUrl1.type != 1))
                  continue;
                if (localApkDownUrl1.urlList == null)
                  break label292;
                str2 = (String)localApkDownUrl1.urlList.get(0);
                label222: localObject = str2;
              }
            }
          }
        }
        if (i != 0)
          localApkUpdateDetail.updatemethod = 4;
        for (localApkUpdateDetail.url = str1; ; localApkUpdateDetail.url = localObject)
        {
          localApkUpdateDetail.newapksize = (int)paramAppUpdateInfo.fileSize;
          localApkUpdateDetail.patchsize = (int)paramAppUpdateInfo.diffFileSize;
          localApkUpdateDetail.fileMd5 = paramAppUpdateInfo.diffApkMd5;
          localApkUpdateDetail.sigMd5 = paramAppUpdateInfo.signatureMd5;
          localApkUpdateDetail.newFeature = paramAppUpdateInfo.newFeature;
          return localApkUpdateDetail;
          str3 = "";
          break;
          label292: str2 = "";
          break label222;
          localApkUpdateDetail.updatemethod = 2;
        }
        str1 = null;
      }
    }
    return null;
  }

  protected final void a()
  {
    super.a();
    if (this.b != null)
    {
      GetAppUpdateRequest localGetAppUpdateRequest = new GetAppUpdateRequest();
      localGetAppUpdateRequest.appInfoForUpdateList = ((ArrayList)this.b);
      localGetAppUpdateRequest.flag = 0;
      StringBuffer localStringBuffer = new StringBuffer("CheckAppUpdateHttpRequest:prepareData to server; appInfoForUpdateList= [");
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
      {
        AppInfoForUpdate localAppInfoForUpdate = (AppInfoForUpdate)localIterator.next();
        localStringBuffer.append("pkgname=" + localAppInfoForUpdate.packageName + ";versionCode=" + localAppInfoForUpdate.versionCode + ";targetVersionCode=" + localAppInfoForUpdate.targetVersionCode + ";targetGrayVersionCode=" + localAppInfoForUpdate.targetGrayVersionCode + "| \n\r");
      }
      f.a(a, localStringBuffer.toString() + "]");
      a(localGetAppUpdateRequest);
    }
  }

  protected final void a(JceStruct paramJceStruct1, JceStruct paramJceStruct2)
  {
    StringBuffer localStringBuffer1;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    label178: int m;
    label218: boolean bool;
    label279: ApkUpdateDetail localApkUpdateDetail4;
    String str4;
    if ((paramJceStruct2 != null) && ((paramJceStruct2 instanceof GetAppUpdateResponse)))
    {
      GetAppUpdateResponse localGetAppUpdateResponse = (GetAppUpdateResponse)paramJceStruct2;
      localStringBuffer1 = new StringBuffer("CheckAppUpdateHttpRequest:checkupdate from server; response=  [");
      localStringBuffer1.append("ret=" + localGetAppUpdateResponse.ret + "| ");
      if (localGetAppUpdateResponse.ret != 0)
        break label1071;
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      Map localMap = localGetAppUpdateResponse.appUpdateInfoGroup;
      if ((localMap != null) && (localMap.size() > 0))
      {
        Collection localCollection = localMap.values();
        if ((localCollection != null) && (localCollection.size() > 0))
        {
          Iterator localIterator5 = localCollection.iterator();
          while (localIterator5.hasNext())
          {
            ArrayList localArrayList4 = (ArrayList)localIterator5.next();
            if ((localArrayList4 == null) || (localArrayList4.size() <= 0))
              continue;
            Iterator localIterator6 = localArrayList4.iterator();
            while (localIterator6.hasNext())
            {
              AppUpdateInfo localAppUpdateInfo2 = (AppUpdateInfo)localIterator6.next();
              int k = localAppUpdateInfo2.flag;
              if ((k & 0x1) != 1)
                break label528;
              m = 1;
              if (m != 0)
                localArrayList2.add(localAppUpdateInfo2);
              StringBuilder localStringBuilder = new StringBuilder("pkgname=").append(localAppUpdateInfo2.packageName).append(";versionCode=").append(localAppUpdateInfo2.versionCode).append(";uploadFlag=");
              if ((k & 0x1) != 1)
                break label534;
              bool = true;
              localStringBuffer1.append(bool + "| \n\r");
              localApkUpdateDetail4 = a(localAppUpdateInfo2);
              if (localApkUpdateDetail4 == null)
                continue;
              str4 = com.tencent.apkupdate.logic.protocol.b.a().b(localApkUpdateDetail4.packageName);
              if ((com.tencent.apkupdate.c.b.a().b() == null) || (com.tencent.apkupdate.c.b.a(com.tencent.apkupdate.c.b.a().b(), localApkUpdateDetail4.packageName) != localApkUpdateDetail4.versioncode))
                break label1100;
            }
          }
        }
      }
    }
    label528: label534: label1071: label1100: for (int n = 1; ; n = 0)
    {
      if ((str4.compareToIgnoreCase(localApkUpdateDetail4.sigMd5) != 0) || (n != 0))
        localApkUpdateDetail4.updatemethod = 1;
      f.a(a, "packageName: " + localApkUpdateDetail4.packageName);
      f.a(a, "updatemethod: " + localApkUpdateDetail4.updatemethod);
      f.a(a, "newapksize: " + localApkUpdateDetail4.newapksize);
      f.a(a, "patchsize: " + localApkUpdateDetail4.patchsize);
      f.a(a, "url: " + localApkUpdateDetail4.url);
      localArrayList1.add(localApkUpdateDetail4);
      break label178;
      break;
      m = 0;
      break label218;
      bool = false;
      break label279;
      f.a(a, localStringBuffer1.toString() + "]");
      ArrayList localArrayList3 = new ArrayList();
      String str1;
      int i;
      String str2;
      ApkUpdateDetail localApkUpdateDetail2;
      if (this.b != null)
      {
        Iterator localIterator3 = this.b.iterator();
        if (localIterator3.hasNext())
        {
          AppInfoForUpdate localAppInfoForUpdate = (AppInfoForUpdate)localIterator3.next();
          str1 = localAppInfoForUpdate.packageName;
          i = localAppInfoForUpdate.versionCode;
          str2 = localAppInfoForUpdate.versionName;
          Iterator localIterator4 = localArrayList1.iterator();
          String str3;
          do
          {
            if (!localIterator4.hasNext())
              break;
            localApkUpdateDetail2 = (ApkUpdateDetail)localIterator4.next();
            str3 = localApkUpdateDetail2.packageName;
          }
          while ((str1 == null) || (str3 == null) || (!str1.equals(str3)));
        }
      }
      for (int j = 1; ; j = 0)
      {
        if (j != 0)
        {
          localArrayList3.add(localApkUpdateDetail2);
          break;
        }
        ApkUpdateDetail localApkUpdateDetail3 = new ApkUpdateDetail();
        localApkUpdateDetail3.packageName = str1;
        localApkUpdateDetail3.versionname = str2;
        localApkUpdateDetail3.versioncode = i;
        localApkUpdateDetail3.updatemethod = 1;
        localArrayList3.add(localApkUpdateDetail3);
        break;
        StringBuffer localStringBuffer2 = new StringBuffer("CheckAppUpdateHttpRequest:checkupdate from server; returnSuceessList response= [");
        Iterator localIterator1 = localArrayList3.iterator();
        while (localIterator1.hasNext())
        {
          ApkUpdateDetail localApkUpdateDetail1 = (ApkUpdateDetail)localIterator1.next();
          localStringBuffer2.append("pkgname=" + localApkUpdateDetail1.packageName + ";versionCode=" + localApkUpdateDetail1.versioncode + "| \n\r");
        }
        f.a(a, localStringBuffer2.toString() + "]");
        Message localMessage1 = com.tencent.apkupdate.logic.a.a().obtainMessage();
        localMessage1.what = 1;
        localMessage1.obj = localArrayList3;
        localMessage1.sendToTarget();
        StringBuffer localStringBuffer3 = new StringBuffer("CheckAppUpdateHttpRequest:NeedUpdateApk from server; needUpdateApkInfos response= [");
        Iterator localIterator2 = localArrayList2.iterator();
        while (localIterator2.hasNext())
        {
          AppUpdateInfo localAppUpdateInfo1 = (AppUpdateInfo)localIterator2.next();
          localStringBuffer3.append("pkgname=" + localAppUpdateInfo1.packageName + ";versionCode=" + localAppUpdateInfo1.versionCode + ";grayVersionCode=" + localAppUpdateInfo1.grayVersionCode + "| \n\r");
        }
        f.a(a, localStringBuffer3.toString() + "]");
        if (localArrayList2.size() > 0)
        {
          Message localMessage2 = com.tencent.apkupdate.logic.a.a().obtainMessage();
          localMessage2.what = 7;
          localMessage2.obj = localArrayList2;
          localMessage2.sendToTarget();
        }
        return;
        Message localMessage3 = com.tencent.apkupdate.logic.a.a().obtainMessage();
        localMessage3.what = 2;
        localMessage3.sendToTarget();
        return;
        localApkUpdateDetail2 = null;
      }
    }
  }

  protected final void b()
  {
    Message localMessage = com.tencent.apkupdate.logic.a.a().obtainMessage();
    localMessage.what = 2;
    localMessage.sendToTarget();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.a.a
 * JD-Core Version:    0.6.0
 */