package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.tencent.android.tpush.logging.TLog;

public class b
{
  public static c a(Context paramContext)
  {
    if (paramContext != null)
    {
      String str = paramContext.getPackageName();
      float f = com.tencent.android.tpush.service.c.c.b(paramContext, str + ".com.tencent.tpush.cache.ver", 0.0F);
      int i = com.tencent.android.tpush.service.c.c.b(paramContext, str + ".com.tencent.tpush.cache.pri", 0);
      Log.i("TPush", ">>> Setting info from " + str + ". ver:" + f + ",pri:" + i);
      return new c(f, i);
    }
    TLog.e("TPush", ">>> get version and priority from Settings error");
    return new c(0.0F, 0);
  }

  public static c a(Context paramContext, String paramString)
  {
    Object localObject = null;
    if (paramContext != null);
    try
    {
      c localc = a(paramContext.createPackageContext(paramString, 2));
      localObject = localc;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      TLog.e("TPush", "Create package context exception:" + paramString, localNameNotFoundException);
    }
    return null;
  }

  public static void a(Context paramContext, c paramc)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ setSetting(" + paramContext.getPackageName() + "," + paramc.a + "," + paramc.b + ")");
      c localc = a(paramContext);
      String str = paramContext.getPackageName();
      if ((localc.a != paramc.a) || (localc.b != paramc.b));
      try
      {
        com.tencent.android.tpush.service.c.c.a(paramContext, str + ".com.tencent.tpush.cache.ver", paramc.a);
        com.tencent.android.tpush.service.c.c.a(paramContext, str + ".com.tencent.tpush.cache.pri", paramc.b);
        return;
      }
      catch (Throwable localThrowable)
      {
        TLog.e("XGService", localThrowable.toString());
        return;
      }
    }
    TLog.e("XGService", ">> context is null");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.a.b
 * JD-Core Version:    0.6.0
 */