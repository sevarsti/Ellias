package com.tencent.component.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import java.util.Iterator;
import java.util.List;

public class ProcessUtils
{
  private static volatile String a;

  public static String a(Context paramContext)
  {
    if (a != null)
      return a;
    monitorenter;
    try
    {
      if (a != null)
      {
        String str2 = a;
        return str2;
      }
    }
    finally
    {
      monitorexit;
    }
    String str1 = c(paramContext);
    a = str1;
    monitorexit;
    return str1;
  }

  public static boolean b(Context paramContext)
  {
    String str = a(paramContext);
    return (str != null) && (str.equals(paramContext.getApplicationInfo().processName));
  }

  private static String c(Context paramContext)
  {
    int i = Process.myPid();
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if ((localList != null) && (localList.size() > 0))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
        if ((localRunningAppProcessInfo != null) && (localRunningAppProcessInfo.pid == i))
          return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.ProcessUtils
 * JD-Core Version:    0.6.0
 */