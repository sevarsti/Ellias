package com.tencent.feedback.anr;

import android.content.Context;
import android.content.Intent;
import android.os.FileObserver;
import android.os.Process;
import com.tencent.feedback.common.a;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.common.service.RQDService;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.CrashStrategyBean;
import java.util.Date;
import java.util.Map;

public class b extends FileObserver
{
  private static b c = null;
  private long a = -1L;
  private Context b;

  private b(Context paramContext)
  {
    super("/data/anr/", 8);
    a.h(paramContext);
    Process.myPid();
    this.b = paramContext.getApplicationContext();
  }

  public static b a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (c == null)
        c = new b(paramContext.getApplicationContext());
      b localb = c;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private boolean a()
  {
    int i = 0;
    monitorenter;
    long l1;
    CrashStrategyBean localCrashStrategyBean;
    try
    {
      e.b("onTraceFileCloseWrite", new Object[0]);
      l1 = new Date().getTime();
      if (l1 < 5000L + this.a)
        e.b("already done", new Object[0]);
      while (true)
      {
        return i;
        localCrashStrategyBean = CrashReport.getCrashRuntimeStrategy();
        if (localCrashStrategyBean != null)
          break;
        e.c("not strategy ? init eup ?", new Object[0]);
        i = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    boolean bool = localCrashStrategyBean.isMerged();
    c localc = c.p();
    String str;
    label103: long l2;
    if (localc != null)
    {
      str = localc.g();
      if (localc == null)
        break label209;
      l2 = localc.i();
      label115: if (localc == null)
        break label215;
    }
    label209: label215: for (Map localMap = localc.n(); ; localMap = null)
    {
      this.a = l1;
      Intent localIntent = new Intent(this.b, RQDService.class);
      localIntent.setAction("com.tencent.feedback.10");
      localIntent.putExtra("com.tencent.feedback.104", new ANRHandleServiceTask(bool, str, l2, localMap));
      this.b.startService(localIntent);
      e.b("start service", new Object[0]);
      i = 1;
      break;
      str = "10000";
      break label103;
      l2 = 0L;
      break label115;
    }
  }

  public void onEvent(int paramInt, String paramString)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = paramString;
    e.c("received event %d %s", arrayOfObject);
    if (!"/data/anr/traces.txt".equals("/data/anr/" + paramString))
    {
      e.c("not anr file %s", new Object[] { paramString });
      return;
    }
    a();
  }

  public void stopWatching()
  {
    monitorenter;
    try
    {
      c = null;
      monitorexit;
      super.stopWatching();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.anr.b
 * JD-Core Version:    0.6.0
 */