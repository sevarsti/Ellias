package com.tencent.component.plugin;

public class PluginReporter
{
  public static final String a = "install";
  public static final String b = "load";
  public static final String c = "center_update";
  public static final String d = "center_install";
  private static final PluginReporter.Pool e = new PluginReporter.Pool(16, new am());
  private static volatile PluginReporter.Reporter f;

  private static PluginReporter.Reporter a()
  {
    return f;
  }

  public static void a(PluginReporter.Reporter paramReporter)
  {
    monitorenter;
    try
    {
      f = paramReporter;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void a(String paramString1, boolean paramBoolean, String paramString2, String paramString3, Throwable paramThrowable)
  {
    PluginReporter.Reporter localReporter = a();
    if (localReporter != null)
    {
      PluginReporter.ReportEvent localReportEvent = (PluginReporter.ReportEvent)e.a();
      localReportEvent.a = paramString1;
      localReportEvent.b = paramBoolean;
      localReportEvent.c = paramString2;
      localReportEvent.d = paramString3;
      localReportEvent.e = paramThrowable;
      localReporter.a(localReportEvent);
      e.a(localReportEvent);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginReporter
 * JD-Core Version:    0.6.0
 */