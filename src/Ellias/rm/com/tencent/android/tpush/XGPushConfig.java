package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.android.tpush.common.a;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

public class XGPushConfig
{
  public static final String TPUSH_ACCESS_ID = "XG_V2_ACCESS_ID";
  public static final String TPUSH_ACCESS_KEY = "XG_V2_ACCESS_KEY";
  private static long a = -1L;
  private static String b = "";
  private static boolean c = false;

  public static void enableDebug(Context paramContext, boolean paramBoolean)
  {
    if ((paramContext == null) || (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext)))
      return;
    c = paramBoolean;
    TLog.init(paramContext);
    TLog.enable(paramBoolean);
    String str = "com.tencent.android.tpush.debug," + paramContext.getPackageName();
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      c.a(paramContext, str, i);
      Intent localIntent = new Intent("com.tencent.android.tpush.action.ENABLE_DEBUG");
      localIntent.putExtra("debugMode", paramBoolean);
      paramContext.sendBroadcast(localIntent);
      return;
    }
  }

  public static long getAccessId(Context paramContext)
  {
    monitorenter;
    if (paramContext == null);
    try
    {
      long l = a;
      while (true)
      {
        return l;
        if (a != -1L)
        {
          l = a;
          continue;
        }
        if (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext))
        {
          l = a;
          continue;
        }
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
        String str2;
        if (localSharedPreferences != null)
        {
          String str1 = localSharedPreferences.getString("XG_V2_ACCESS_ID", null);
          if (str1 != null)
            str2 = Rijndael.decrypt(str1);
        }
        try
        {
          a = Long.valueOf(str2).longValue();
          if (a == -1L)
          {
            Object localObject2 = a.a(paramContext, "XG_V2_ACCESS_ID", null);
            if (localObject2 != null)
              a = Long.valueOf(localObject2.toString()).longValue();
          }
          if (a == -1L)
            TLog.e("TPush", "accessId没有初始化");
          l = a;
        }
        catch (Exception localException)
        {
          while (true)
            a = -1L;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public static String getAccessKey(Context paramContext)
  {
    monitorenter;
    try
    {
      String str1;
      if (!c.a(b))
        str1 = b;
      while (true)
      {
        return str1;
        boolean bool = TpnsSecurity.checkTpnsSecurityLibSo(paramContext);
        str1 = null;
        if (!bool)
          continue;
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
        if (localSharedPreferences != null)
        {
          String str2 = localSharedPreferences.getString("XG_V2_ACCESS_KEY", null);
          if (c.a(str2))
            b = Rijndael.decrypt(str2);
        }
        if (c.a(b))
        {
          Object localObject2 = a.a(paramContext, "XG_V2_ACCESS_KEY", null);
          if (localObject2 != null)
            b = localObject2.toString();
        }
        if (c.a(b))
          TLog.e("TPush", "accessKey为空");
        str1 = b;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public static String getToken(Context paramContext)
  {
    if (paramContext == null)
    {
      TLog.e("TPush", "参数context不能为空");
      return null;
    }
    return CacheManager.getToken(paramContext);
  }

  public static boolean isEnableDebug()
  {
    return c;
  }

  public static boolean setAccessId(Context paramContext, long paramLong)
  {
    if (paramContext == null)
      TLog.e("TPush", "参数context不能为空");
    do
      return false;
    while (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
    a = paramLong;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (localSharedPreferences != null)
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putString("XG_V2_ACCESS_ID", Rijndael.encrypt(String.valueOf(paramLong)));
      localEditor.commit();
    }
    return true;
  }

  public static boolean setAccessKey(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null))
      TLog.e("TPush", "参数context或accessKey不能为空");
    do
      return false;
    while (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
    b = paramString;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (localSharedPreferences != null)
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putString("XG_V2_ACCESS_KEY", Rijndael.encrypt(paramString));
      localEditor.commit();
    }
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushConfig
 * JD-Core Version:    0.6.0
 */