package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.android.tpush.data.StorageEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.report.ReportItem;

public class a
{
  private static a a = null;
  private static volatile f b = null;
  private static volatile h c = null;

  public static a a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new a();
      a locala = a;
      return locala;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(int paramInt, TpnsRegisterReq paramTpnsRegisterReq, String paramString1, String paramString2)
  {
    TLog.v("XGService", "@@ broadcastRegisterFeedback(" + paramInt + "," + paramTpnsRegisterReq + "," + paramString2 + ")");
    Intent localIntent = com.tencent.android.tpush.service.c.c.a(paramInt, paramString2, 1);
    localIntent.putExtra("accId", paramTpnsRegisterReq.accessId);
    if ((paramTpnsRegisterReq.account != null) && (paramTpnsRegisterReq.account.length() != 0))
      localIntent.putExtra("account", paramTpnsRegisterReq.account);
    if ((paramString1 != null) && (paramString1.length() != 0))
      localIntent.putExtra("token", paramString1);
    if ((paramTpnsRegisterReq.ticket != null) && (paramTpnsRegisterReq.ticket.length() != 0))
    {
      localIntent.putExtra("ticket", paramTpnsRegisterReq.ticket);
      localIntent.putExtra("ticketType", paramTpnsRegisterReq.ticketType);
    }
    if ((paramTpnsRegisterReq.deviceId != null) && (paramTpnsRegisterReq.deviceId.length() != 0))
      localIntent.putExtra("deviceId", paramTpnsRegisterReq.deviceId);
    i.e().sendBroadcast(localIntent);
  }

  private void a(int paramInt, TpnsRegisterRsp paramTpnsRegisterRsp, TpnsRegisterReq paramTpnsRegisterReq, com.tencent.android.tpush.service.channel.a parama, String paramString)
  {
    TLog.v("XGService", "@@ registerSuccessHandler(" + paramInt + "," + paramTpnsRegisterRsp + "," + paramTpnsRegisterReq + "," + parama + "," + paramString + ")");
    Intent localIntent = new Intent("com.tencent.android.tpush.action.REGISTER.RESULT");
    localIntent.putExtra("data", paramTpnsRegisterRsp.token);
    localIntent.putExtra("flag", 0);
    localIntent.putExtra("code", paramInt);
    localIntent.putExtra("operation", 0);
    StorageEntity[] arrayOfStorageEntity = new StorageEntity[1];
    arrayOfStorageEntity[0] = new StorageEntity("reg", true);
    localIntent.putExtra("storage", arrayOfStorageEntity);
    com.tencent.android.tpush.data.b localb = new com.tencent.android.tpush.data.b();
    localb.a = paramTpnsRegisterReq.accessId;
    localb.b = paramTpnsRegisterReq.accessKey;
    localb.c = paramTpnsRegisterRsp.token;
    localb.d = paramString;
    CacheManager.addRegisterInfo(localb);
    com.tencent.android.tpush.service.b.f.a().a(i.e(), paramTpnsRegisterReq.accessId, paramString);
    CacheManager.setToken(i.e(), paramTpnsRegisterRsp.token);
    if (!com.tencent.android.tpush.service.c.c.a(paramString))
      localIntent.setPackage(paramString);
    TLog.i("XGService", ">> Send Register Success 2 SDK ");
    i.e().sendBroadcast(localIntent);
    a(paramInt, paramTpnsRegisterReq, paramTpnsRegisterRsp.token, paramString);
    l.a().a(parama.b(), paramTpnsRegisterRsp.confVersion);
    ReportItem localReportItem = new ReportItem(paramTpnsRegisterReq.accessId, l.a(parama.b()), 0, paramInt, 0, parama.f(), parama.c(), "");
    com.tencent.android.tpush.service.report.e.a().a(localReportItem);
  }

  private void a(int paramInt, TpnsUnregisterReq paramTpnsUnregisterReq, com.tencent.android.tpush.service.channel.a parama, String paramString)
  {
    TLog.v("XGService", "@@ unregisterSuccessHandler(" + paramInt + "," + paramTpnsUnregisterReq + "," + parama + "," + paramString + ")");
    Intent localIntent = new Intent("com.tencent.android.tpush.action.UNREGISTER.RESULT");
    localIntent.putExtra("flag", 0);
    localIntent.putExtra("operation", 0);
    CacheManager.UnregisterInfoSuccessByPkgName(paramString);
    com.tencent.android.tpush.service.b.f.a().a(i.e(), paramString);
    if (!com.tencent.android.tpush.common.i.a(paramString))
      localIntent.setPackage(paramString);
    i.e().sendBroadcast(localIntent);
    a(paramInt, paramString);
    ReportItem localReportItem = new ReportItem(paramTpnsUnregisterReq.unregInfo.appInfo.accessId, l.a(parama.b()), 0, paramInt, 1, parama.f(), parama.c(), "");
    com.tencent.android.tpush.service.report.e.a().a(localReportItem);
  }

  private void a(int paramInt, String paramString)
  {
    TLog.v("XGService", "@@ broadcastUnregisterFeedback(" + paramInt + "," + paramString + ")");
    Intent localIntent = com.tencent.android.tpush.service.c.c.a(paramInt, paramString, 2);
    i.e().sendBroadcast(localIntent);
  }

  private void a(int paramInt1, String paramString1, int paramInt2, String paramString2)
  {
    TLog.v("XGService", "@@ broadcastTagFeedback(" + paramInt1 + "," + paramString2 + ")");
    Intent localIntent = com.tencent.android.tpush.service.c.c.a(paramInt1, paramString2, 3);
    localIntent.putExtra("tagName", Rijndael.encrypt(paramString1));
    localIntent.putExtra("tagFlag", paramInt2);
    i.e().sendBroadcast(localIntent);
  }

  private void a(int paramInt, String paramString1, TpnsRegisterReq paramTpnsRegisterReq, com.tencent.android.tpush.service.channel.a parama, String paramString2)
  {
    TLog.e("XGService", "@@ registerFailHandler(" + paramInt + "," + paramString1 + "," + paramTpnsRegisterReq + "," + parama + "," + paramString2 + ")");
    Intent localIntent = new Intent("com.tencent.android.tpush.action.REGISTER.RESULT");
    localIntent.putExtra("data", "");
    localIntent.putExtra("code", paramInt);
    localIntent.putExtra("msg", paramString1);
    localIntent.putExtra("flag", 0);
    localIntent.putExtra("operation", 1);
    if (!com.tencent.android.tpush.service.c.c.a(paramString2))
      localIntent.setPackage(paramString2);
    i.e().sendBroadcast(localIntent);
    a(paramInt, paramTpnsRegisterReq, paramTpnsRegisterReq.token, paramString2);
    ReportItem localReportItem = new ReportItem(paramTpnsRegisterReq.accessId, l.a(parama.b()), 1, paramInt, 0, parama.f(), parama.c(), paramString1);
    com.tencent.android.tpush.service.report.e.a().a(localReportItem);
  }

  private void a(int paramInt, String paramString1, TpnsUnregisterReq paramTpnsUnregisterReq, com.tencent.android.tpush.service.channel.a parama, String paramString2)
  {
    TLog.v("XGService", "@@ unregisterFailHandler(" + paramInt + "," + paramString1 + "," + paramTpnsUnregisterReq + "," + parama + "," + paramString2 + ")");
    Intent localIntent = new Intent("com.tencent.android.tpush.action.UNREGISTER.RESULT");
    localIntent.putExtra("flag", 0);
    localIntent.putExtra("code", paramInt);
    localIntent.putExtra("msg", paramString1);
    localIntent.putExtra("operation", 1);
    if (!com.tencent.android.tpush.common.i.a(paramString2))
      localIntent.setPackage(paramString2);
    i.e().sendBroadcast(localIntent);
    a(paramInt, paramString2);
    ReportItem localReportItem = new ReportItem(paramTpnsUnregisterReq.unregInfo.appInfo.accessId, l.a(parama.b()), 1, paramInt, 1, parama.f(), parama.c(), paramString1);
    com.tencent.android.tpush.service.report.e.a().a(localReportItem);
  }

  private void a(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getDataString();
    if ((str != null) && (paramContext != null))
    {
      TLog.v("XGService", "@@ appInstallHandler(" + paramContext.getPackageName() + "," + paramIntent + ")");
      if (com.tencent.android.tpush.service.c.c.b(paramContext, str.substring(8)))
      {
        i.a().d();
        com.tencent.android.tpush.common.c.a().a(new b(this, paramContext), 2000L);
      }
    }
  }

  private void b(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getDataString();
    if ((str1 != null) && (paramContext != null))
    {
      TLog.v("XGService", "@@ appRemoveHandler(" + paramContext.getPackageName() + "," + paramIntent + ")");
      String str2 = str1.substring(8);
      com.tencent.android.tpush.service.b.f.a().a(paramContext, str2);
      l.a().a(str2);
    }
  }

  private void c(Context paramContext, Intent paramIntent)
  {
    String str1;
    String str2;
    String str3;
    String str4;
    String str5;
    int i;
    String str6;
    if ((paramContext != null) && (paramIntent != null))
    {
      TLog.v("XGService", "@@ registerHandler()");
      str1 = Rijndael.decrypt(paramIntent.getStringExtra("accId"));
      str2 = Rijndael.decrypt(paramIntent.getStringExtra("accKey"));
      str3 = Rijndael.decrypt(paramIntent.getStringExtra("packName"));
      str4 = Rijndael.decrypt(paramIntent.getStringExtra("account"));
      str5 = Rijndael.decrypt(paramIntent.getStringExtra("ticket"));
      i = paramIntent.getIntExtra("ticketType", -1);
      str6 = Rijndael.decrypt(paramIntent.getStringExtra("qua"));
      TLog.i("XGService", ">> register[accId:" + str1 + ",packName:" + str3 + "]");
      com.tencent.android.tpush.logging.LogUtil.uin = str4;
    }
    try
    {
      if (!com.tencent.android.tpush.service.c.c.a(str6))
        CacheManager.setQua(paramContext, Long.parseLong(str1), str6);
      String str7 = TpnsSecurity.getEncryptAPKSignature(paramContext.createPackageContext(str3, 0));
      l.a().a(Long.parseLong(str1), str2, com.tencent.android.tpush.service.c.b.a(), str4, str5, i, str7, new c(this, str3));
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", ">> register error " + localException);
    }
  }

  private void d(Context paramContext, Intent paramIntent)
  {
    if ((paramContext != null) && (paramIntent != null))
    {
      TLog.v("XGService", "@@ tagHandler(" + paramContext.getPackageName() + "," + paramIntent + ")");
      long l = paramIntent.getLongExtra("accId", -1L);
      String str1 = Rijndael.decrypt(paramIntent.getStringExtra("packName"));
      int i = paramIntent.getIntExtra("tagFlag", -1);
      String str2 = Rijndael.decrypt(paramIntent.getStringExtra("tagName"));
      TLog.i("XGService", ">> tagHandler[accId:" + l + ",tagtype:" + i + ",tagName:" + str2 + ",packName:" + str1 + "]");
      l.a().a(l, str1, i, str2, new d(this, str2, i, str1));
    }
  }

  private void e(Context paramContext, Intent paramIntent)
  {
    String str1;
    String str2;
    String str3;
    String str4;
    if ((paramContext != null) && (paramIntent != null))
    {
      TLog.v("XGService", "@@ unregisterHandler(" + paramContext.getPackageName() + "," + paramIntent + ")");
      str1 = Rijndael.decrypt(paramIntent.getStringExtra("accId"));
      str2 = Rijndael.decrypt(paramIntent.getStringExtra("accKey"));
      str3 = Rijndael.decrypt(paramIntent.getStringExtra("packName"));
      str4 = Rijndael.decrypt(paramIntent.getStringExtra("token"));
      TLog.i("XGService", ">> unregister[accId:" + str1 + ",packName:" + str3 + ",token:" + str4);
      CacheManager.UnregisterInfoByPkgName(str3);
    }
    try
    {
      l.a().a(str4, com.tencent.android.tpush.service.c.b.a(), Long.parseLong(str1), str2, str3, new e(this, str3));
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", ">>> unregister error " + localException);
    }
  }

  private void f(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent != null) && (paramContext != null))
    {
      TLog.v("XGService", "@@ enableDebugHandler(" + paramContext.getPackageName() + "," + paramIntent + ")");
      boolean bool = paramIntent.getBooleanExtra("debugMode", false);
      TLog.init(paramContext);
      TLog.enable(bool);
    }
  }

  public void a(Context paramContext)
  {
    monitorenter;
    if (paramContext != null);
    try
    {
      TLog.v("XGService", "@@ registerReceiver(" + paramContext.getPackageName() + ")");
      if (b == null)
      {
        TLog.i("XGService", ">> Create package changes receiver instance [" + paramContext.getPackageName() + "].");
        b = new f(this, null);
        IntentFilter localIntentFilter2 = new IntentFilter();
        localIntentFilter2.addDataScheme("package");
        localIntentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        localIntentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        localIntentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
        paramContext.registerReceiver(b, localIntentFilter2);
      }
      if (c == null)
      {
        TLog.i("XGService", ">> Create tpush broadcast receiver instance [" + paramContext.getPackageName() + "].");
        c = new h(this, null);
        IntentFilter localIntentFilter1 = new IntentFilter();
        localIntentFilter1.addAction("com.tencent.android.tpush.action.REGISTER");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.UNREGISTER");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.ENABLE_DEBUG");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.MSG_ACK");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.TAG");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.PUSH_CLICK.RESULT");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT");
        localIntentFilter1.addAction("com.tencent.android.tpush.action.CUSTOM_NOTIFICATION");
        paramContext.registerReceiver(c, localIntentFilter1);
        TLog.i("XGService", ">> Send service started broadcast to register receiver [" + paramContext.getPackageName() + "].");
        Intent localIntent = new Intent("com.tencent.android.tpush.action.SERVICE_START");
        localIntent.putExtra("pkg", i.e().getPackageName());
        localIntent.putExtra("ver", 2.3F);
        i.e().sendBroadcast(localIntent);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ unregisterReceiver(" + paramContext.getPackageName() + ")");
      if (b != null)
      {
        TLog.i("XGService", ">> Unregister package changes receiver instance [" + paramContext.getPackageName() + "].");
        com.tencent.android.tpush.common.i.a(paramContext, b);
        b = null;
      }
      if (c != null)
      {
        TLog.i("XGService", ">> Unregister tpush broadcast receiver instance [" + paramContext.getPackageName() + "].");
        com.tencent.android.tpush.common.i.a(paramContext, c);
        c = null;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.a
 * JD-Core Version:    0.6.0
 */