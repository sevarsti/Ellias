package com.tencent.qqgamemi.report;

import android.content.Context;
import android.os.Handler;
import com.tencent.component.annotation.PluginApi;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.plugin.api.QMiApi;
import java.util.ArrayList;

public class UserAccessStatics
{
  private static final String b = "QMiReport";
  private static final long e = 300000L;
  private static final int f = 10;
  private static volatile boolean h = false;
  private static UserAccessStatics i = null;
  private static final int j = 1;
  c a = new c(this, null);
  private Context c;
  private ReportDataTable d;
  private ArrayList g = new ArrayList();
  private Handler k = new a(this);

  private UserAccessStatics(Context paramContext)
  {
    this.c = paramContext;
    this.d = new ReportDataTable(paramContext);
  }

  private String a(int paramInt, long paramLong, String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt).append("|").append(paramLong).append("|").append(paramString1).append("|").append(paramString2).append("|").append(paramString3);
    return localStringBuilder.toString();
  }

  @PluginApi(a=6)
  public static void addQMiAction(int paramInt, Context paramContext)
  {
    String str1 = QMiApi.getInstance(paramContext).getCurrentPackageName();
    String str2 = paramContext.getPackageName();
    getInstance(paramContext).addQMiAction(paramInt, System.currentTimeMillis(), str1, str2);
  }

  private boolean c()
  {
    if (this.g.size() > 0)
    {
      b localb = (b)this.g.get(0);
      if (System.currentTimeMillis() - localb.b > 300000L)
        return true;
    }
    return false;
  }

  private void d()
  {
    ArrayList localArrayList = new ArrayList();
    for (int m = 0; m < this.g.size(); m++)
    {
      b localb = (b)this.g.get(m);
      ReportDataStruct localReportDataStruct = new ReportDataStruct();
      localReportDataStruct.reportType = 404;
      localReportDataStruct.reportContent = a(localb.a, localb.b, localb.c, localb.d, localb.e);
      TLog.c("QMiReport", "saveAndClearQMiData" + localReportDataStruct.reportContent);
      localArrayList.add(localReportDataStruct);
    }
    this.d.a(localArrayList);
    this.g.clear();
    e();
  }

  private void e()
  {
    monitorenter;
    try
    {
      TLog.c("QMiReport", "sendData");
      if (!h)
      {
        h = true;
        this.k.post(this.a);
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public static UserAccessStatics getInstance(Context paramContext)
  {
    if (i == null)
      i = new UserAccessStatics(paramContext);
    return i;
  }

  public void a()
  {
    TLog.c("QMiReport", "freshQMiData");
    d();
  }

  @PluginApi(a=6)
  public void addQMiAction(int paramInt, long paramLong, String paramString1, String paramString2)
  {
    ArrayList localArrayList = this.g;
    monitorenter;
    String str1;
    if (paramString1 == null)
      str1 = "";
    while (true)
    {
      b localb;
      int m;
      try
      {
        localb = new b(null);
        m = QMiConfig.c();
        if (m != 1)
          continue;
        localb.d = "sdk";
        localb.a = paramInt;
        localb.b = paramLong;
        localb.c = str1;
        localb.e = str2;
        this.g.add(localb);
        boolean bool = c();
        TLog.c("QMiReport", "qmi action" + a(paramInt, paramLong, str1, localb.d, str2) + " timeout=" + bool);
        if ((this.g.size() <= 10) && (!bool))
          continue;
        d();
        return;
        if (m == 2)
        {
          localb.d = "hall";
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (m != 3)
        continue;
      localb.d = "shouyoubao";
      continue;
      while (true)
      {
        if (paramString2 != null)
          break label223;
        str2 = "";
        break;
        str1 = paramString1;
      }
      label223: String str2 = paramString2;
    }
  }

  public void b()
  {
    TLog.c("QMiReport", "saveAll");
    d();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.report.UserAccessStatics
 * JD-Core Version:    0.6.0
 */