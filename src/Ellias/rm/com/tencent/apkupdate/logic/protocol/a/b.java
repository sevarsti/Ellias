package com.tencent.apkupdate.logic.protocol.a;

import android.os.Message;
import com.qq.taf.jce.JceStruct;
import com.tencent.apkupdate.c.f;
import com.tencent.apkupdate.logic.protocol.jce.ApkFileInfo;
import com.tencent.apkupdate.logic.protocol.jce.ReportApkFileInfoRequest;
import com.tencent.apkupdate.logic.protocol.jce.ReportApkFileInfoResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class b extends com.tencent.apkupdate.logic.protocol.a
{
  private final List a;

  public b(List paramList)
  {
    this.a = paramList;
  }

  protected final void a()
  {
    super.a();
    ReportApkFileInfoRequest localReportApkFileInfoRequest = new ReportApkFileInfoRequest();
    localReportApkFileInfoRequest.apkFileInfoList = ((ArrayList)this.a);
    a(localReportApkFileInfoRequest);
    StringBuffer localStringBuffer = new StringBuffer("UploadApkHttpRequest:prepareData;apkFileInfoList= [");
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      ApkFileInfo localApkFileInfo = (ApkFileInfo)localIterator.next();
      localStringBuffer.append("pkgname=" + localApkFileInfo.packageName + ";apkId=" + localApkFileInfo.apkId + "| \n\r");
    }
    f.a("UploadApkHttpRequest", localStringBuffer.toString() + "]");
  }

  protected final void a(JceStruct paramJceStruct1, JceStruct paramJceStruct2)
  {
    if ((paramJceStruct2 != null) && ((paramJceStruct2 instanceof ReportApkFileInfoResponse)))
    {
      ReportApkFileInfoResponse localReportApkFileInfoResponse = (ReportApkFileInfoResponse)paramJceStruct2;
      f.a("UploadApkHttpRequest", "UploadApkHttpRequest:onFinished; ret=" + localReportApkFileInfoResponse.ret);
      if (localReportApkFileInfoResponse.ret == 0)
      {
        Message localMessage1 = com.tencent.apkupdate.logic.a.a().obtainMessage();
        localMessage1.what = 3;
        localMessage1.sendToTarget();
        if (paramJceStruct1 != null)
        {
          ReportApkFileInfoRequest localReportApkFileInfoRequest = (ReportApkFileInfoRequest)paramJceStruct1;
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = localReportApkFileInfoRequest.apkFileInfoList.iterator();
          while (localIterator.hasNext())
            localArrayList.add(((ApkFileInfo)localIterator.next()).packageName);
          if (localArrayList.size() > 0)
          {
            Message localMessage2 = com.tencent.apkupdate.logic.a.a().obtainMessage();
            localMessage2.what = 8;
            localMessage2.obj = localArrayList;
            localMessage2.sendToTarget();
          }
        }
      }
    }
  }

  protected final void b()
  {
    Message localMessage = com.tencent.apkupdate.logic.a.a().obtainMessage();
    localMessage.what = 4;
    localMessage.sendToTarget();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.a.b
 * JD-Core Version:    0.6.0
 */