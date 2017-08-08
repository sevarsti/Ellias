package com.tencent.android.tpush.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Parcelable;
import com.tencent.android.tpush.data.StorageEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.c.c;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

public class i
{
  private static Timer a = new Timer();
  private static k b;

  public static String a(Context paramContext)
  {
    return paramContext.getPackageName() + ".tpush.local.setting.private";
  }

  public static void a(Context paramContext, Parcelable[] paramArrayOfParcelable)
  {
    SharedPreferences.Editor localEditor;
    int i;
    if ((paramContext != null) && (paramArrayOfParcelable != null) && (paramArrayOfParcelable.length > 0))
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(a(paramContext), 0);
      if (localSharedPreferences != null)
      {
        localEditor = localSharedPreferences.edit();
        i = 0;
      }
    }
    while (true)
    {
      if (i < paramArrayOfParcelable.length);
      try
      {
        StorageEntity localStorageEntity = (StorageEntity)paramArrayOfParcelable[i];
        TLog.i("TPush", ">>> update storage " + localStorageEntity);
        if ((localStorageEntity == null) || (a(localStorageEntity.a)));
        while (true)
        {
          label94: i++;
          break;
          switch (localStorageEntity.b)
          {
          default:
            break;
          case 0:
            localEditor.putString(localStorageEntity.a, localStorageEntity.d);
            break;
          case 1:
            localEditor.putBoolean(localStorageEntity.a, localStorageEntity.c);
            break;
          case 2:
            localEditor.putInt(localStorageEntity.a, localStorageEntity.e);
            break;
          case 3:
            localEditor.putFloat(localStorageEntity.a, localStorageEntity.f);
            break;
          case 4:
            localEditor.putLong(localStorageEntity.a, localStorageEntity.g);
          }
        }
        localEditor.commit();
        return;
      }
      catch (Exception localException)
      {
        break label94;
      }
    }
  }

  public static boolean a(Context paramContext, BroadcastReceiver paramBroadcastReceiver)
  {
    try
    {
      paramContext.unregisterReceiver(paramBroadcastReceiver);
      return true;
    }
    catch (Exception localException)
    {
      TLog.e("TPush", localException.toString());
    }
    return false;
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static String b(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ setServicePackageName(" + paramContext.getPackageName() + ")");
      String str = c.c(paramContext, "tpush.running.service.name");
      if ((str != null) && (str.length() > 0))
        return Rijndael.decrypt(str);
    }
    return "";
  }

  private static void b(long paramLong)
  {
    TLog.v("XGService", "@@ timeLock(" + paramLong + ")");
    a.purge();
    a.schedule(new j(), paramLong);
  }

  public static int c(Context paramContext)
  {
    if (paramContext != null)
    {
      List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647);
      if ((localList != null) && (localList.size() > 0))
      {
        String str = XGPushService.class.getName();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
          if (!str.equals(localRunningServiceInfo.service.getClassName()))
            continue;
          if (localRunningServiceInfo.pid != 0)
            return 1;
          return 2;
        }
      }
    }
    return 0;
  }

  public static void d(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ StartService()");
      if (c.a(paramContext).size() < 2)
        com.tencent.android.tpush.service.i.a(paramContext, new Intent(paramContext, XGPushService.class));
    }
    else
    {
      return;
    }
    b = new k(null);
    paramContext.registerReceiver(b, new IntentFilter("com.tencent.android.tpush.action.BROADCAST_ACK"));
    paramContext.sendBroadcast(new Intent("com.tencent.android.tpush.action.SDK"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.i
 * JD-Core Version:    0.6.0
 */