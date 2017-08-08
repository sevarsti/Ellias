package com.tencent.apkupdate.logic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.apkupdate.ApkUpdateListener;
import com.tencent.apkupdate.ApkUpdateParam;
import com.tencent.apkupdate.logic.protocol.jce.ApkFileInfo;
import com.tencent.apkupdate.logic.protocol.jce.AppInfoForUpdate;
import com.tencent.apkupdate.logic.protocol.jce.AppUpdateInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class a extends Handler
{
  private static HandlerThread a;
  private static a b;
  private final ArrayList c = new ArrayList();
  private ConcurrentHashMap d = new ConcurrentHashMap();
  private final HashMap e = new HashMap();

  private a(Looper paramLooper)
  {
    super(paramLooper);
  }

  private int a(List paramList)
  {
    monitorenter;
    int i = 0;
    if (paramList != null);
    while (true)
    {
      try
      {
        int j = paramList.size();
        i = 0;
        if (j <= 0)
          return i;
        PackageManager localPackageManager = com.tencent.apkupdate.logic.protocol.b.a().b().getPackageManager();
        Iterator localIterator = paramList.iterator();
        if (localIterator.hasNext())
        {
          AppUpdateInfo localAppUpdateInfo = (AppUpdateInfo)localIterator.next();
          boolean bool = this.e.containsKey(localAppUpdateInfo.packageName);
          if (bool)
            continue;
          try
          {
            PackageInfo localPackageInfo = localPackageManager.getPackageInfo(localAppUpdateInfo.packageName, 0);
            com.tencent.apkupdate.a.a locala = new com.tencent.apkupdate.a.a();
            locala.a(localPackageInfo.applicationInfo.sourceDir);
            ApkFileInfo localApkFileInfo = new ApkFileInfo();
            localApkFileInfo.packageName = localPackageInfo.packageName;
            localApkFileInfo.apkId = localAppUpdateInfo.apkId;
            String str = (String)this.d.get(localPackageInfo.packageName);
            if (!TextUtils.isEmpty(str))
              continue;
            str = com.tencent.apkupdate.logic.protocol.b.a(localPackageInfo.packageName).toLowerCase();
            localApkFileInfo.manifestMd5 = str;
            localApkFileInfo.fileCRC32 = locala.a();
            this.e.put(localApkFileInfo.packageName, localApkFileInfo);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            localNameNotFoundException.printStackTrace();
          }
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      int k = this.e.size();
      i = k;
    }
  }

  public static a a()
  {
    monitorenter;
    try
    {
      if (b == null)
      {
        HandlerThread localHandlerThread = new HandlerThread("apkupdate_asyctask");
        a = localHandlerThread;
        localHandlerThread.setPriority(10);
        a.start();
        b = new a(a.getLooper());
      }
      a locala = b;
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b()
  {
    monitorenter;
    try
    {
      int i = this.e.size();
      if (i <= 0);
      while (true)
      {
        return;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add((ApkFileInfo)this.e.get(this.e.keySet().iterator().next()));
        com.tencent.apkupdate.logic.protocol.b.a().a(new com.tencent.apkupdate.logic.protocol.a.b(localArrayList));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void a(ApkUpdateListener paramApkUpdateListener)
  {
    if (!this.c.contains(paramApkUpdateListener))
      this.c.add(paramApkUpdateListener);
  }

  public final void b(ApkUpdateListener paramApkUpdateListener)
  {
    if (paramApkUpdateListener == null);
    while (true)
    {
      return;
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        if ((ApkUpdateListener)localIterator.next() != paramApkUpdateListener)
          continue;
        localIterator.remove();
      }
    }
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    case 3:
    case 4:
    default:
    case 1:
    case 2:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    label413: 
    do
    {
      do
      {
        List localList;
        do
        {
          while (true)
          {
            return;
            Iterator localIterator4 = this.c.iterator();
            while (localIterator4.hasNext())
              ((ApkUpdateListener)localIterator4.next()).onCheckUpdateSucceed((ArrayList)paramMessage.obj);
            continue;
            Iterator localIterator3 = this.c.iterator();
            while (localIterator3.hasNext())
              ((ApkUpdateListener)localIterator3.next()).onCheckUpdateFailed("UNKOWN");
          }
          localList = (List)paramMessage.obj;
        }
        while (localList == null);
        ArrayList localArrayList2 = new ArrayList();
        PackageManager localPackageManager = com.tencent.apkupdate.logic.protocol.b.a().b().getPackageManager();
        long l1 = System.currentTimeMillis();
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          ApkUpdateParam localApkUpdateParam = (ApkUpdateParam)localIterator2.next();
          String str2 = localApkUpdateParam.packageName;
          int i = localApkUpdateParam.actionFlag;
          int j = localApkUpdateParam.targetVersionCode;
          int k = localApkUpdateParam.targetGrayVersionCode;
          if (TextUtils.isEmpty(str2))
            continue;
          while (true)
          {
            AppInfoForUpdate localAppInfoForUpdate;
            try
            {
              PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str2, 0);
              if (localPackageInfo == null)
                break;
              localAppInfoForUpdate = new AppInfoForUpdate();
              localAppInfoForUpdate.packageName = str2;
              localAppInfoForUpdate.versionCode = localPackageInfo.versionCode;
              localAppInfoForUpdate.signatureMd5 = com.tencent.apkupdate.logic.protocol.b.a().b(str2);
              localAppInfoForUpdate.manifestMd5 = com.tencent.apkupdate.logic.protocol.b.a(str2).toLowerCase();
              this.d.put(str2, localAppInfoForUpdate.manifestMd5);
              if ((0x1 & localPackageInfo.applicationInfo.flags) > 0)
                break label413;
              localAppInfoForUpdate.appType = 1;
              localAppInfoForUpdate.versionName = localPackageInfo.versionName;
              localAppInfoForUpdate.actionFlag = (byte)i;
              localAppInfoForUpdate.grayVersionCode = com.tencent.apkupdate.c.b.a().a(str2);
              localAppInfoForUpdate.targetVersionCode = j;
              localAppInfoForUpdate.targetGrayVersionCode = k;
              localArrayList2.add(localAppInfoForUpdate);
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              localNameNotFoundException.printStackTrace();
            }
            break;
            localAppInfoForUpdate.appType = 2;
          }
        }
        long l2 = System.currentTimeMillis();
        Log.i("updatecost----------", "updatecost=" + (l2 - l1));
        if (localArrayList2.size() > 0)
        {
          Message localMessage3 = a().obtainMessage();
          localMessage3.what = 6;
          localMessage3.obj = localArrayList2;
          localMessage3.sendToTarget();
          return;
        }
        Message localMessage2 = a().obtainMessage();
        localMessage2.what = 2;
        localMessage2.sendToTarget();
        return;
        ArrayList localArrayList1 = (ArrayList)paramMessage.obj;
        com.tencent.apkupdate.logic.protocol.b.a().a(new com.tencent.apkupdate.logic.protocol.a.a(localArrayList1));
        return;
      }
      while (a((ArrayList)paramMessage.obj) <= 0);
      Message localMessage1 = a().obtainMessage();
      localMessage1.what = 8;
      localMessage1.obj = null;
      localMessage1.sendToTarget();
      return;
      if (paramMessage.obj == null)
      {
        b();
        return;
      }
      Iterator localIterator1 = ((ArrayList)paramMessage.obj).iterator();
      while (localIterator1.hasNext())
      {
        String str1 = (String)localIterator1.next();
        a().e.remove(str1);
      }
    }
    while (a().e.isEmpty());
    b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.a
 * JD-Core Version:    0.6.0
 */