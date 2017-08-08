package com.tencent.android.tpush.horse;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.horse.data.OptStrategyList;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.b.f;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.i;
import java.util.HashSet;
import java.util.Iterator;

public class Tools
{
  public static final String KEY = ".com.tencent.tpush.toolschannel";
  public static final String SHARE_NAME = ".com.tencent.tpush.toolschannel_name";
  public static final String STRATEGY = ".com.tencent.tpush.toolsstrategy";
  public static final String TOOLS_NAME_UNI_SUFFIX = ".com.tencent.tpush.tools";
  public static final int TYPE_DEFAULT = 0;
  public static final int TYPE_HTTP = 2;
  public static final int TYPE_HTTP_WAP = 3;
  public static final int TYPE_TCP = 1;

  public static void clearAll(Context paramContext)
  {
    TLog.e("TPush", ">> clearCache");
    clearOptKeyList(paramContext);
    clearCacheServerItems(paramContext);
    clearOptStrategyItem(paramContext);
    clearMultPkgs(paramContext);
    XGPushManager.clearLocalNotifications(paramContext);
    c.a(paramContext, "tpush_msgId_" + XGPushConfig.getAccessId(paramContext), "");
    c.a(paramContext, "isClearCache.com.tencent.tpush.cache.redirect", "memeda1");
  }

  public static void clearCacheServerItems(Context paramContext)
  {
    if ((paramContext == null) || (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext)))
      return;
    TLog.v("XGService", "@@ clearCacheServerItems(" + paramContext.getPackageName() + ")");
    try
    {
      CacheManager.clearDomainServerItem(paramContext);
      CacheManager.saveDomain(paramContext, "");
      CacheManager.saveDomainKeyList(paramContext, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      TLog.e("XGService", localThrowable.toString());
    }
  }

  public static void clearMultPkgs(Context paramContext)
  {
    if (paramContext != null)
      TLog.v("XGService", "@@ clearMultPkgs(" + paramContext.getPackageName() + ")");
    try
    {
      f.a().a(paramContext);
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public static void clearOptKeyList(Context paramContext)
  {
    if (paramContext != null)
      CacheManager.clearOptKeyList(paramContext);
  }

  public static void clearOptStrategyItem(Context paramContext)
  {
    if ((paramContext == null) || (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext)))
      return;
    TLog.v("XGService", "@@ clearOptStrategyItem(" + paramContext.getPackageName() + ")");
    try
    {
      c.a(paramContext, c.f(i.e()) + ".com.tencent.tpush.cache.redirect", "");
      Iterator localIterator = CacheManager.getOptKeyList(paramContext).iterator();
      while (localIterator.hasNext())
        CacheManager.addOptStrategyList(paramContext, (String)localIterator.next(), new OptStrategyList());
    }
    catch (Exception localException)
    {
      CacheManager.clearOptKeyList(i.e());
      TLog.e("XGService", localException.toString());
      CacheManager.addOptStrategyList(paramContext, c.f(paramContext), new OptStrategyList());
    }
  }

  public static void clearRegisterInfo(Context paramContext, long paramLong)
  {
    if (paramContext != null)
      TLog.v("XGService", "@@ clearRegisterInfo(" + paramContext.getPackageName() + "," + paramLong + ")");
    try
    {
      CacheManager.removeRegisterInfoByPkgName(paramContext.getPackageName());
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public static int getChannelType(Context paramContext)
  {
    int i = 0;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getChannelType(" + paramContext.getPackageName() + ")");
      i = c.b(paramContext, ".com.tencent.tpush.toolschannel", 0);
    }
    return i;
  }

  public static String getCurStrategyItem(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getCurStrategyItem(" + paramContext.getPackageName() + ")");
      return c.c(paramContext, ".com.tencent.tpush.toolsstrategy");
    }
    return "";
  }

  public static void saveCurStrategyItem(Context paramContext, StrategyItem paramStrategyItem)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ saveCurStrategyItem(" + paramContext.getPackageName() + ")");
      if (paramStrategyItem != null)
        break label52;
    }
    label52: for (String str = ""; ; str = paramStrategyItem.toString())
    {
      c.a(paramContext, ".com.tencent.tpush.toolsstrategy", str);
      return;
    }
  }

  public static void sendCurStrategyItem(Context paramContext, StrategyItem paramStrategyItem)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ sendCurStrategyItem(" + paramContext.getPackageName() + ")");
      try
      {
        saveCurStrategyItem(paramContext, paramStrategyItem);
        Intent localIntent = new Intent("com.tencent.android.tpush.horse");
        if (paramStrategyItem == null);
        String str;
        for (Object localObject = "null"; ; localObject = str)
        {
          localIntent.putExtra("strategy", (String)localObject);
          paramContext.sendBroadcast(localIntent);
          return;
          str = paramStrategyItem.toString();
        }
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
      }
    }
  }

  public static void setChannelType(Context paramContext, int paramInt)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ setChannelType(" + paramContext.getPackageName() + "," + paramInt + ")");
      c.a(paramContext, ".com.tencent.tpush.toolschannel", paramInt);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.Tools
 * JD-Core Version:    0.6.0
 */