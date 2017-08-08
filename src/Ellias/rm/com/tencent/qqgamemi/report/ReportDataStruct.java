package com.tencent.qqgamemi.report;

import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;

@Table(name="ReportDataTable", version=2)
public class ReportDataStruct
{

  @Id(strategy=3)
  public int qmiId;

  @Column
  public String reportContent;

  @Column
  public int reportId;

  @Column
  public int reportType;
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.report.ReportDataStruct
 * JD-Core Version:    0.6.0
 */