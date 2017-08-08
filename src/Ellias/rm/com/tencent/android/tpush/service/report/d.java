package com.tencent.android.tpush.service.report;

import android.content.Context;
import com.tencent.android.tpush.common.f;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.i;
import java.util.ArrayList;
import java.util.List;

public class d
{
  private int a;

  public d()
  {
    ArrayList localArrayList = c(i.e());
    if (localArrayList != null);
    for (int i = localArrayList.size(); ; i = 0)
    {
      this.a = i;
      TLog.d("ReportLogTag", "### mTotalCount = " + this.a);
      return;
    }
  }

  private boolean a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!c.a(paramString)))
      return c.a(paramContext, paramString, "");
    return false;
  }

  private boolean a(Context paramContext, String paramString, ArrayList paramArrayList)
  {
    monitorenter;
    if (paramContext != null);
    try
    {
      if ((!c.a(paramString)) && (paramArrayList != null) && (paramArrayList.size() > 0))
        TLog.d("ReportLogTag", ">>> update report list by key @" + paramString);
      while (true)
      {
        try
        {
          boolean bool2 = c.a(paramContext, paramString, f.a(paramArrayList));
          bool1 = bool2;
          monitorexit;
          return bool1;
        }
        catch (Exception localException)
        {
          TLog.e("ReportLogTag", ">>> update report exception @" + paramString, localException);
        }
        boolean bool1 = false;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private String b()
  {
    return "com.tencent.tpush.cache.report.cache.new";
  }

  private ArrayList b(Context paramContext, String paramString)
  {
    monitorenter;
    try
    {
      TLog.v("ReportLogTag", ">>> get report by key " + paramString);
      if ((paramContext != null) && (paramString != null));
      while (true)
      {
        try
        {
          localArrayList = (ArrayList)f.a(c.c(paramContext, paramString));
          TLog.v("ReportLogTag", ">>> get report by key " + localArrayList);
          monitorexit;
          return localArrayList;
        }
        catch (Exception localException)
        {
          TLog.e("ReportLogTag", "+++ get report exception @" + paramString, localException);
        }
        ArrayList localArrayList = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private String c()
  {
    return "com.tencent.tpush.cache.report.cache.old";
  }

  public int a()
  {
    TLog.i("ReportLogTag", "ReportDataModal getTotalCount count = " + this.a);
    return this.a;
  }

  public boolean a(Context paramContext)
  {
    monitorenter;
    while (true)
    {
      try
      {
        TLog.d("ReportLogTag", "ReportDataModal deleteOldItems start");
        if (paramContext != null)
        {
          bool = a(paramContext, c());
          if (!bool)
            continue;
          TLog.i("ReportLogTag", "ReportDataModal deleteOldItems success");
          return bool;
          TLog.w("ReportLogTag", "ReportDataModal deleteOldItems fail!");
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      boolean bool = false;
    }
  }

  public boolean a(Context paramContext, ReportItem paramReportItem)
  {
    monitorenter;
    try
    {
      TLog.d("ReportLogTag", ">>> add new item @" + paramReportItem);
      boolean bool = false;
      if (paramReportItem != null)
      {
        bool = false;
        if (paramContext != null)
        {
          ArrayList localArrayList = b(i.e(), b());
          if (localArrayList == null)
            localArrayList = new ArrayList();
          localArrayList.add(paramReportItem);
          bool = a(paramContext, b(), localArrayList);
          if (bool)
            this.a = (1 + this.a);
        }
      }
      return bool;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean a(Context paramContext, ArrayList paramArrayList)
  {
    monitorenter;
    try
    {
      TLog.i("ReportLogTag", "ReportDataModal backupOldItems " + paramArrayList);
      boolean bool2;
      if ((paramContext != null) && (paramArrayList != null) && (paramArrayList.size() > 0))
      {
        TLog.i("ReportLogTag", "ReportDataModal backupOldItems count = " + paramArrayList.size());
        ArrayList localArrayList = b(i.e(), c());
        if (localArrayList == null)
          localArrayList = new ArrayList();
        localArrayList.addAll(paramArrayList);
        bool2 = a(paramContext, c(), localArrayList);
      }
      for (boolean bool1 = bool2; ; bool1 = false)
      {
        return bool1;
        TLog.w("ReportLogTag", "ReportDataModal backupOldItems fail!");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean b(Context paramContext)
  {
    monitorenter;
    try
    {
      TLog.i("ReportLogTag", "ReportDataModal deleteNewItems start");
      boolean bool = false;
      if (paramContext != null)
      {
        bool = a(paramContext, b());
        if (!bool)
          break label45;
        this.a = 0;
        TLog.i("ReportLogTag", "ReportDataModal deleteNewItems success");
      }
      while (true)
      {
        return bool;
        label45: TLog.w("ReportLogTag", "ReportDataModal deleteNewItems fail!");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public ArrayList c(Context paramContext)
  {
    monitorenter;
    try
    {
      TLog.i("ReportLogTag", "ReportDataModal getNewItems start");
      ArrayList localArrayList = b(paramContext, b());
      if (localArrayList != null)
        TLog.i("ReportLogTag", "ReportDataModal getNewItems end, count = " + localArrayList.size());
      while (true)
      {
        return localArrayList;
        TLog.w("ReportLogTag", "ReportDataModal getNewItems end, count = 0");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public ArrayList d(Context paramContext)
  {
    monitorenter;
    try
    {
      TLog.i("ReportLogTag", "ReportDataModal getOldItems start");
      ArrayList localArrayList = b(paramContext, c());
      if (localArrayList != null)
        TLog.i("ReportLogTag", "ReportDataModal getOldItems end, count = " + localArrayList.size());
      while (true)
      {
        return localArrayList;
        TLog.w("ReportLogTag", "ReportDataModal getOldItems end, count = 0");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.report.d
 * JD-Core Version:    0.6.0
 */