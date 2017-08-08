package com.tencent.qqgamemi.report;

import android.content.Context;
import com.tencent.component.db.EntityManager;
import com.tencent.qqgamemi.QMiEntityManagerFactory;
import com.tencent.qqgamemi.common.TLog;
import java.util.List;

public class ReportDataTable
{
  private static final String a = "ReportDataTable";
  private EntityManager b;

  public ReportDataTable(Context paramContext)
  {
    this.b = QMiEntityManagerFactory.a(paramContext).a(ReportDataStruct.class, "reportData");
  }

  public List a()
  {
    return this.b.findAll();
  }

  public void a(List paramList)
  {
    TLog.c("ReportDataTable", "insertRecords datas:" + paramList.size());
    this.b.saveOrUpdateAll(paramList);
  }

  public void b()
  {
    this.b.deleteAll();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.report.ReportDataTable
 * JD-Core Version:    0.6.0
 */