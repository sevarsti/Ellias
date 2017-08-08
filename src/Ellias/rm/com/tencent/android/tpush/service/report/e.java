package com.tencent.android.tpush.service.report;

import android.content.Context;
import android.os.Bundle;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.a.a;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.i;
import java.util.ArrayList;
import java.util.Random;

public class e
{
  private static e a = null;
  private long b = 0L;
  private int c = a.k;
  private boolean d = false;
  private Random e = new Random();
  private d f = new d();
  private ArrayList g = new ArrayList();
  private ArrayList h = new ArrayList();

  private Bundle a(Context paramContext)
  {
    monitorenter;
    try
    {
      TLog.i("ReportLogTag", "ReportManager prepareData start");
      this.g = this.f.c(paramContext);
      this.f.b(paramContext);
      Object localObject2;
      if ((this.g == null) || (this.g.size() <= 0))
      {
        TLog.i("ReportLogTag", "ReportManager prepareData end  newItems == null");
        localObject2 = null;
      }
      while (true)
      {
        return localObject2;
        this.h = this.f.d(paramContext);
        this.f.a(paramContext);
        Bundle localBundle = new Bundle();
        try
        {
          localBundle.putString("appid", "1000086");
          localBundle.putString("sdkversion", "2.3");
          localBundle.putString("deviceinfo", e());
          localBundle.putString("key", "apn,frequency,commandid,accessid,tmcost,isp,locip,pact,qua,result,resultcode,servicehost,detail,stime");
          for (int i = 0; i < this.g.size(); i++)
          {
            localBundle.putString(i + "_1", "" + ((ReportItem)this.g.get(i)).apn);
            localBundle.putString(i + "_2", "" + ((ReportItem)this.g.get(i)).frequency);
            localBundle.putString(i + "_3", "" + ((ReportItem)this.g.get(i)).commandId);
            localBundle.putString(i + "_4", "" + ((ReportItem)this.g.get(i)).accessId);
            localBundle.putString(i + "_5", "" + ((ReportItem)this.g.get(i)).tmcost);
            localBundle.putString(i + "_6", "" + ((ReportItem)this.g.get(i)).isp);
            localBundle.putString(i + "_7", ((ReportItem)this.g.get(i)).locIp);
            localBundle.putString(i + "_8", "" + ((ReportItem)this.g.get(i)).pact);
            localBundle.putString(i + "_9", ((ReportItem)this.g.get(i)).qua);
            localBundle.putString(i + "_10", "" + ((ReportItem)this.g.get(i)).result);
            localBundle.putString(i + "_11", "" + ((ReportItem)this.g.get(i)).resultCode);
            localBundle.putString(i + "_12", ((ReportItem)this.g.get(i)).serviceHost);
            localBundle.putString(i + "_13", ((ReportItem)this.g.get(i)).detail);
            localBundle.putString(i + "_14", "" + ((ReportItem)this.g.get(i)).stime);
          }
          if (this.h == null)
            break label1698;
          int j = this.h.size();
          int k = 0;
          if (j <= 0)
            break label1698;
          while (k < this.h.size())
          {
            int m = k + this.g.size();
            localBundle.putString(m + "_1", "" + ((ReportItem)this.h.get(k)).apn);
            localBundle.putString(m + "_2", "" + ((ReportItem)this.h.get(k)).frequency);
            localBundle.putString(m + "_3", "" + ((ReportItem)this.h.get(k)).commandId);
            localBundle.putString(m + "_4", "" + ((ReportItem)this.h.get(k)).accessId);
            localBundle.putString(m + "_5", "" + ((ReportItem)this.h.get(k)).tmcost);
            localBundle.putString(m + "_6", "" + ((ReportItem)this.h.get(k)).isp);
            localBundle.putString(m + "_7", ((ReportItem)this.h.get(k)).locIp);
            localBundle.putString(m + "_8", "" + ((ReportItem)this.h.get(k)).pact);
            localBundle.putString(m + "_9", ((ReportItem)this.h.get(k)).qua);
            localBundle.putString(m + "_10", "" + ((ReportItem)this.h.get(k)).result);
            localBundle.putString(m + "_11", "" + ((ReportItem)this.h.get(k)).resultCode);
            localBundle.putString(m + "_12", ((ReportItem)this.h.get(k)).serviceHost);
            localBundle.putString(m + "_13", ((ReportItem)this.h.get(k)).detail);
            localBundle.putString(m + "_14", "" + ((ReportItem)this.h.get(k)).stime);
            k++;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          localObject2 = null;
        }
        continue;
        label1698: TLog.i("ReportLogTag", "ReportManager prepareData end");
        localObject2 = localBundle;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public static e a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new e();
      e locale = a;
      return locale;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(Context paramContext, ReportItem paramReportItem)
  {
    if ((paramContext != null) && (paramReportItem != null))
    {
      paramReportItem.frequency = b(paramReportItem.result);
      paramReportItem.frequency = (100 / paramReportItem.frequency);
      if (paramReportItem.frequency > 0)
        break label54;
      paramReportItem.frequency = 1;
    }
    while (true)
    {
      this.f.a(paramContext, paramReportItem);
      return;
      label54: if (paramReportItem.frequency <= 100)
        continue;
      paramReportItem.frequency = 100;
    }
  }

  private void a(String paramString1, String paramString2, Bundle paramBundle)
  {
    new f(this, paramString1, paramBundle).start();
  }

  private boolean a(int paramInt)
  {
    int i = b(paramInt);
    if (this.e.nextInt(100) < i)
    {
      TLog.i("ReportLogTag", "ReportManager availableForFrequency = ture");
      return true;
    }
    TLog.i("ReportLogTag", "ReportManager availableForFrequency = false");
    return false;
  }

  private int b(int paramInt)
  {
    if (paramInt == 0)
    {
      int j = a.g;
      TLog.d("ReportLogTag", "config 4:Common_CGIReportFrequencySuccess     config_value:" + j);
      return j;
    }
    int i = a.h;
    TLog.d("ReportLogTag", "config 4:Common_CGIReportFrequencyFailed     config_value:" + i);
    return i;
  }

  private boolean b()
  {
    long l1 = a.i;
    TLog.d("ReportLogTag", "config 5:Common_CGIReportTimeinterval     config_value:" + l1);
    long l2 = System.currentTimeMillis() / 1000L;
    if ((this.b == 0L) || (l1 + this.b <= l2))
    {
      this.b = l2;
      TLog.i("ReportLogTag", "ReportManager availableForTime = ture");
      return true;
    }
    TLog.i("ReportLogTag", "ReportManager availableForTime = false");
    return false;
  }

  private boolean c()
  {
    int i = a.j;
    TLog.d("ReportLogTag", "config 6:Common_CGIReportMaxcount     config_value:" + i);
    if (this.f.a() >= i)
    {
      TLog.i("ReportLogTag", "ReportManager availableForCount = ture");
      return true;
    }
    TLog.i("ReportLogTag", "ReportManager availableForCount = false");
    return false;
  }

  private void d()
  {
    TLog.i("ReportLogTag", "ReportManager doUpload start");
    Bundle localBundle = a(i.e());
    if (localBundle == null)
      return;
    this.d = true;
    a("http://wspeed.qq.com/tpns.cgi", "POST", localBundle);
  }

  private String e()
  {
    return c.c(i.e()) + "_" + c.d() + "_" + c.c();
  }

  public void a(ReportItem paramReportItem)
  {
    if (paramReportItem == null);
    do
    {
      do
      {
        do
          return;
        while (a(paramReportItem.result) != true);
        a(i.e(), paramReportItem);
      }
      while (this.d == true);
      if (b() != true)
        continue;
      d();
      return;
    }
    while (c() != true);
    d();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.report.e
 * JD-Core Version:    0.6.0
 */