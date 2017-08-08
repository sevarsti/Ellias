package com.tencent.feedback.anr;

import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Process;
import android.util.Log;
import android.util.SparseArray;
import com.tencent.feedback.common.PlugInInfo;
import com.tencent.feedback.common.e;
import com.tencent.feedback.common.service.RQDService;
import com.tencent.feedback.common.service.RQDServiceTask;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.CrashStrategyBean;
import com.tencent.feedback.eup.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ANRHandleServiceTask
  implements Parcelable, RQDServiceTask
{
  public static final Parcelable.Creator<ANRHandleServiceTask> CREATOR;
  protected static final long PROCESS_ERROR_STATE_WAITING_PERIOD = 500L;
  protected static final long PROCESS_ERROR_STATE_WAITING_TIMEOUT = 10000L;
  protected static final long PROCESS_KILL_WAITING_TIMEOUT = 10000L;
  protected static final String TRACE_FILE_DIR = "/data/anr/";
  protected static final String TRACE_FILE_PATH = "/data/anr/traces.txt";
  public static AtomicBoolean isHandling = new AtomicBoolean(false);
  public static com.tencent.feedback.common.service.a lastHandledANRFirstDump$2e178719 = null;
  private final boolean isUseMerge;
  private final Map<String, PlugInInfo> pluginList;
  private final long serverTimeGap;
  private final String userId;

  static
  {
    CREATOR = new Parcelable.Creator()
    {
    };
  }

  public ANRHandleServiceTask(Parcel paramParcel)
  {
    boolean bool;
    if (paramParcel.readInt() > 0)
      bool = true;
    while (true)
    {
      this.isUseMerge = bool;
      this.userId = paramParcel.readString();
      this.serverTimeGap = paramParcel.readLong();
      int j = paramParcel.readInt();
      if (j > 0)
      {
        this.pluginList = new HashMap();
        while (i < j)
        {
          PlugInInfo localPlugInInfo = new PlugInInfo(paramParcel);
          this.pluginList.put(localPlugInInfo.plugInId, localPlugInInfo);
          i++;
        }
        bool = false;
        continue;
      }
      this.pluginList = null;
    }
  }

  public ANRHandleServiceTask(boolean paramBoolean, String paramString, long paramLong, Map<String, PlugInInfo> paramMap)
  {
    this.isUseMerge = paramBoolean;
    this.userId = paramString;
    this.serverTimeGap = paramLong;
    this.pluginList = paramMap;
  }

  public int describeContents()
  {
    return 0;
  }

  public Map<String, PlugInInfo> getPluginList()
  {
    return this.pluginList;
  }

  public SparseArray<String> getRunningProcessSameUid()
  {
    return com.tencent.feedback.common.a.a(Process.myUid());
  }

  public long getServerTimeGap()
  {
    return this.serverTimeGap;
  }

  public int getTaskId()
  {
    return 1000;
  }

  public String getTaskName()
  {
    return "2000";
  }

  public String getUploadThreadsDump(Map<String, String[]> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      HashMap localHashMap1 = new HashMap();
      HashMap localHashMap2 = new HashMap();
      Pattern localPattern = Pattern.compile("held by tid=\\d+");
      Iterator localIterator1 = paramMap.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        localHashMap1.put(((String[])localEntry.getValue())[2], localEntry.getKey());
        Matcher localMatcher = localPattern.matcher(((String[])localEntry.getValue())[1]);
        if (localMatcher.find())
        {
          localHashMap2.put(((String[])localEntry.getValue())[2], null);
          String str5 = localMatcher.group();
          localHashMap2.put(str5.substring(1 + str5.indexOf("=")), null);
          continue;
        }
        if (!"main".equals(localEntry.getKey()))
          continue;
        localHashMap2.put(((String[])localEntry.getValue())[2], null);
      }
      StringBuffer localStringBuffer = new StringBuffer();
      Iterator localIterator2 = localHashMap2.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str1 = (String)localIterator2.next();
        localHashMap2.put(str1, localHashMap1.get(str1));
        String str2 = (String)localHashMap1.get(str1);
        String str3 = ((String[])paramMap.get(str2))[0];
        String str4 = ((String[])paramMap.get(str2))[1];
        localStringBuffer.append("\"" + str2 + "\" tid=" + str1 + " :\n" + str3 + "\n" + str4 + "\n");
      }
      return localStringBuffer.toString();
    }
    return null;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void handleANRInfo(Context paramContext, int paramInt, String paramString1, String paramString2, long paramLong)
  {
    if ((paramContext == null) || (paramInt <= 0) || (paramString1 == null))
      return;
    a locala = new a();
    locala.a(paramString1);
    locala.a(paramLong);
    if (paramString2 == null)
      paramString2 = "unvisiable ANR";
    locala.b(paramString2);
    handleANRInfo(paramContext, locala);
  }

  public void handleANRInfo(Context paramContext, a parama)
  {
    if ((paramContext == null) || (parama == null))
      return;
    if ((parama.a() == null) || ("/data/anr/traces.txt" == null));
    for (com.tencent.feedback.common.service.a locala = null; ; locala = null)
      do
      {
        if (locala != null)
        {
          String str = getUploadThreadsDump(locala.d);
          if (str == null)
            str = "dump traces fail!";
          parama.c(str);
        }
        recordANR(paramContext, parama);
        return;
        locala = new com.tencent.feedback.common.service.a();
        a.a("/data/anr/traces.txt", new c(locala, true));
      }
      while ((locala.a > 0L) && (locala.c > 0L) && (locala.b != null));
  }

  public boolean isUseMerge()
  {
    return this.isUseMerge;
  }

  public com.tencent.feedback.common.service.a readTraceDumpInfo$37d8951a()
  {
    if ("/data/anr/traces.txt" == null)
    {
      Log.e("path:%s", "/data/anr/traces.txt");
      return null;
    }
    com.tencent.feedback.common.service.a locala = new com.tencent.feedback.common.service.a();
    a.a("/data/anr/traces.txt", new d(locala, false));
    if ((locala.a > 0L) && (locala.c > 0L) && (locala.b != null))
      return locala;
    Log.e("first dump error", locala.a + " " + locala.c + " " + locala.b);
    return null;
  }

  public void recordANR(Context paramContext, a parama)
  {
    CrashStrategyBean localCrashStrategyBean = new CrashStrategyBean();
    localCrashStrategyBean.setMerged(this.isUseMerge);
    com.tencent.feedback.eup.d locald = b.a(paramContext, this.userId, this.serverTimeGap, this.pluginList, parama.a(), "main", "", "ANR_RQD_EXCEPTION", "", parama.e(), parama.b(), parama.d(), null);
    locald.e(true);
    boolean bool = b.a(paramContext).a(locald, localCrashStrategyBean);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = locald.t();
    arrayOfObject1[1] = Integer.valueOf(locald.r());
    e.b("sha1:%s %d", arrayOfObject1);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Boolean.valueOf(bool);
    e.b("handle anr %b", arrayOfObject2);
  }

  public void runInService(RQDService paramRQDService, Intent paramIntent)
  {
    if (CrashReport.getCrashRuntimeStrategy() == null)
    {
      e.b("rqdp{ init service eup}", new Object[0]);
      CrashReport.initCrashReport(paramRQDService.getApplicationContext(), false);
    }
    if (isHandling.get())
    {
      e.b("handling task already exist!", new Object[0]);
      return;
    }
    isHandling.set(true);
    try
    {
      localSparseArray1 = getRunningProcessSameUid();
      if ((localSparseArray1 == null) || (localSparseArray1.size() <= 0))
      {
        e.d("impossible not pid in same app", new Object[0]);
        return;
      }
      com.tencent.feedback.common.service.a locala = readTraceDumpInfo$37d8951a();
      if (locala == null)
      {
        e.d("read dump info fail", new Object[0]);
        return;
      }
      if ((lastHandledANRFirstDump$2e178719 != null) && (lastHandledANRFirstDump$2e178719.a == locala.a) && (lastHandledANRFirstDump$2e178719.b.equals(locala.b)) && (lastHandledANRFirstDump$2e178719.c == locala.c))
      {
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = lastHandledANRFirstDump$2e178719.b;
        arrayOfObject3[1] = Long.valueOf(lastHandledANRFirstDump$2e178719.c);
        e.b("same trace file same anr ,has handled! %s %d", arrayOfObject3);
        return;
      }
      lastHandledANRFirstDump$2e178719 = locala;
      l = locala.c;
      List localList = waitForANRProcessErrorSate(paramRQDService);
      if ((localList != null) && (localList.size() > 0))
      {
        int k = Process.myUid();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          a locala1 = (a)localIterator.next();
          if (k != locala1.c())
            continue;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = locala1.a();
          e.b("has anr in process %s handle it and leave", arrayOfObject2);
          locala1.a(l);
          handleANRInfo(paramRQDService, locala1);
          return;
        }
        e.b("not my anr ignorl ,leave", new Object[0]);
        return;
      }
      try
      {
        Thread.sleep(500L);
        localSparseArray2 = getRunningProcessSameUid();
        if ((localSparseArray2 == null) || (localSparseArray2.size() <= 0))
        {
          e.d("impossible not pid in same app", new Object[0]);
          isHandling.set(false);
          return;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          localInterruptedException.printStackTrace();
      }
    }
    catch (Throwable localThrowable)
    {
      SparseArray localSparseArray1;
      long l;
      SparseArray localSparseArray2;
      e.d("task throw upload by catch", new Object[0]);
      CrashReport.handleCatchException(Thread.currentThread(), localThrowable, null, null);
      localThrowable.printStackTrace();
      return;
      for (int i = 0; i < localSparseArray1.size(); i++)
      {
        int j = localSparseArray1.keyAt(i);
        if (localSparseArray2.get(j) != null)
          continue;
        String str = (String)localSparseArray1.get(j);
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = Integer.valueOf(j);
        arrayOfObject1[1] = str;
        e.b("found process been kill pid:%d pn:%s , it should be anr proc ,handle it and leave", arrayOfObject1);
        handleANRInfo(paramRQDService, j, str, null, l);
        return;
      }
      return;
    }
    finally
    {
      isHandling.set(false);
    }
    throw localObject;
  }

  public List<a> waitForANRProcessErrorSate(Context paramContext)
  {
    if (paramContext == null)
      return null;
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    int i = 0;
    while (true)
    {
      int j = i + 1;
      if (i >= 20L)
        break;
      List localList = localActivityManager.getProcessesInErrorState();
      if (localList != null)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          ActivityManager.ProcessErrorStateInfo localProcessErrorStateInfo = (ActivityManager.ProcessErrorStateInfo)localIterator.next();
          if (localProcessErrorStateInfo.condition != 2)
            continue;
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = localProcessErrorStateInfo.processName;
          arrayOfObject[1] = localProcessErrorStateInfo.longMsg;
          arrayOfObject[2] = localProcessErrorStateInfo.shortMsg;
          e.b("anr error found in %s \n lMsg:%s\n sMsg:%s", arrayOfObject);
          if ((localProcessErrorStateInfo == null) || (localProcessErrorStateInfo.condition != 2));
          a locala;
          for (Object localObject = null; ; localObject = locala)
          {
            localArrayList.add(localObject);
            break;
            locala = new a();
            locala.a(localProcessErrorStateInfo.processName);
            locala.b(localProcessErrorStateInfo.longMsg);
            locala.a(localProcessErrorStateInfo.uid);
          }
        }
        if (localArrayList.size() > 0)
          return localArrayList;
      }
      try
      {
        Thread.sleep(500L);
        i = j;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
        i = j;
      }
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i;
    if (this.isUseMerge)
      i = 1;
    while (true)
    {
      paramParcel.writeInt(i);
      paramParcel.writeString(this.userId);
      paramParcel.writeLong(this.serverTimeGap);
      Iterator localIterator;
      if ((this.pluginList != null) && (this.pluginList.size() > 0))
      {
        paramParcel.writeInt(this.pluginList.size());
        localIterator = this.pluginList.keySet().iterator();
      }
      while (true)
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          paramParcel.writeParcelable((RQDServiceTask)this.pluginList.get(str), 0);
          continue;
          i = -1;
          break;
          paramParcel.writeInt(0);
        }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.anr.ANRHandleServiceTask
 * JD-Core Version:    0.6.0
 */