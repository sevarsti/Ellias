package com.tencent.qqgamemi.report;

import CobraHallQmiProto.TReportData;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.protocol.MsgHandle;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

class c
  implements Runnable
{
  private c(UserAccessStatics paramUserAccessStatics)
  {
  }

  public void run()
  {
    List localList = UserAccessStatics.a(this.a).a();
    TLog.c("QMiReport", "上报:" + localList.size());
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
      if (i < localList.size())
        try
        {
          ReportDataStruct localReportDataStruct = (ReportDataStruct)localList.get(i);
          TReportData localTReportData = new TReportData();
          if (localReportDataStruct.reportContent != null)
            localTReportData.content = localReportDataStruct.reportContent.getBytes("utf-8");
          localTReportData.reportType = localReportDataStruct.reportType;
          localArrayList.add(localTReportData);
          TLog.c("QMiReport", "type:" + localReportDataStruct.reportType + " " + localReportDataStruct.reportContent);
          i++;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          while (true)
            localUnsupportedEncodingException.printStackTrace();
        }
    if (localArrayList.size() > 0)
    {
      MsgHandle.b(UserAccessStatics.b(this.a), 1, localArrayList);
      return;
    }
    UserAccessStatics.a(false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.report.c
 * JD-Core Version:    0.6.0
 */