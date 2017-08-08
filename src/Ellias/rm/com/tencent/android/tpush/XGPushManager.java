package com.tencent.android.tpush;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.tencent.android.tpush.a.b;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.Tools;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.Serializable;
import java.util.Properties;

public class XGPushManager
{
  public static final int OPERATION_FAIL = 1;
  public static final int OPERATION_REQ_REGISTER = 100;
  public static final int OPERATION_REQ_UNREGISTER = 101;
  public static final int OPERATION_SUCCESS;
  private static final String a = XGPushManager.class.getName();
  private static Context b = null;

  private static void a(Context paramContext)
  {
    if ((b == null) && (paramContext != null))
      b = paramContext.getApplicationContext();
  }

  private static void a(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent("com.tencent.android.tpush.action.PUSH_CLICK.RESULT");
    localIntent.putExtras(paramIntent);
    localIntent.putExtra("packName", paramContext.getPackageName());
    localIntent.putExtra("clickTime", System.currentTimeMillis() / 1000L);
    paramContext.sendBroadcast(localIntent);
  }

  private static void a(Context paramContext, com.tencent.android.tpush.a.h paramh)
  {
    if ((XGPro.isEnableXGPro(paramContext)) && (paramh != null))
    {
      TLog.i("XGPro", "reportVerify2Mta BusiMsgId=" + paramh.d());
      Properties localProperties = new Properties();
      localProperties.put("busiMsgId", "" + paramh.d());
      com.tencent.android.tpush.a.a locala = paramh.h();
      if (locala != null)
      {
        localProperties.put("type", "" + locala.b());
        XGPro.a(paramContext).a("xg_verify_all", localProperties);
        if (locala.b() == 1)
          XGPro.a(paramContext).a("xg_verify_notify", localProperties);
      }
    }
  }

  private static void a(Context paramContext, String paramString, int paramInt)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("The context parameter can not be null!");
    if (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext))
      return;
    a(paramContext);
    if (paramString == null)
      throw new IllegalArgumentException("The tagName parameter can not be null!");
    long l = XGPushConfig.getAccessId(paramContext);
    if (l < 0L)
      throw new IllegalArgumentException("The accessId not set!");
    Intent localIntent = new Intent("com.tencent.android.tpush.action.TAG");
    localIntent.putExtra("accId", l);
    localIntent.putExtra("packName", Rijndael.encrypt(paramContext.getPackageName()));
    localIntent.putExtra("tagFlag", paramInt);
    localIntent.putExtra("tagName", Rijndael.encrypt(paramString));
    paramContext.sendBroadcast(localIntent);
  }

  private static void a(Context paramContext, String paramString1, String paramString2, int paramInt, String paramString3, XGIOperateCallback paramXGIOperateCallback)
  {
    try
    {
      String str2 = com.tencent.android.tpush.service.c.c.c(paramContext, "isClearCache.com.tencent.tpush.cache.redirect");
      TLog.e("TPush", "isClearCache:" + str2);
      if ((str2 == null) || (!"memeda1".equals(str2)))
        Tools.clearAll(paramContext);
      if (paramContext == null)
      {
        paramXGIOperateCallback.onFail(null, 10001, "The context parameter can not be null!");
        return;
      }
    }
    catch (Exception localException)
    {
      do
        while (true)
          TLog.e("TPush", localException.toString());
      while (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
      long l = XGPushConfig.getAccessId(paramContext);
      String str1 = XGPushConfig.getAccessKey(paramContext);
      if ((l <= 0L) || (com.tencent.android.tpush.common.i.a(str1)))
      {
        paramXGIOperateCallback.onFail(null, 10001, "The accessId or accessKey is(are) invalid!@accessId:" + l + ", @accessKey:" + str1);
        return;
      }
      Intent localIntent = new Intent("com.tencent.android.tpush.action.REGISTER");
      localIntent.putExtra("accId", Rijndael.encrypt("" + l));
      localIntent.putExtra("accKey", Rijndael.encrypt(str1));
      localIntent.putExtra("packName", Rijndael.encrypt(paramContext.getPackageName()));
      if (paramString1 != null)
        localIntent.putExtra("account", Rijndael.encrypt(paramString1));
      if (paramString2 != null)
        localIntent.putExtra("ticket", Rijndael.encrypt(paramString2));
      if (paramString3 != null)
        localIntent.putExtra("qua", Rijndael.encrypt(paramString3));
      localIntent.putExtra("ticketType", paramInt);
      localIntent.putExtra("operation", 100);
      com.tencent.android.tpush.service.c.c.d("?type=reg&token=" + CacheManager.getToken(paramContext.getApplicationContext()));
      if (com.tencent.android.tpush.common.i.c(paramContext) == 1)
      {
        TLog.d("XGService", ">>> service is running,just regeister");
        com.tencent.android.tpush.common.g.a(paramContext);
        c(paramContext, localIntent, paramXGIOperateCallback);
        return;
      }
      TLog.d("XGService", ">>> start service first,then regeister");
      com.tencent.android.tpush.common.i.d(paramContext);
      paramContext.registerReceiver(new g(localIntent, paramXGIOperateCallback), new IntentFilter("com.tencent.android.tpush.action.SERVICE_START"));
    }
  }

  public static long addLocalNotification(Context paramContext, XGLocalMessage paramXGLocalMessage)
  {
    if ((paramContext == null) || (paramXGLocalMessage == null))
      Log.e("TPush", ">>> addLocalNotification context == null or msg == null");
    do
      return -1L;
    while (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
    Intent localIntent = new Intent("com.tencent.android.tpush.action.CUSTOM_NOTIFICATION");
    localIntent.putExtra("appPkgName", paramContext.getPackageName());
    localIntent.putExtra("accessId", XGPushConfig.getAccessId(paramContext));
    localIntent.putExtra("type", paramXGLocalMessage.getType());
    localIntent.putExtra("title", paramXGLocalMessage.getTitle());
    localIntent.putExtra("content", paramXGLocalMessage.getContent());
    localIntent.putExtra("date", paramXGLocalMessage.getDate());
    localIntent.putExtra("hour", paramXGLocalMessage.getHour());
    localIntent.putExtra("min", paramXGLocalMessage.getMin());
    localIntent.putExtra("builderId", paramXGLocalMessage.getBuilderId());
    long l = System.currentTimeMillis();
    localIntent.putExtra("msgId", -l);
    localIntent.putExtra("timeUs", l);
    paramContext.sendBroadcast(localIntent);
    return -l;
  }

  private static void b(Context paramContext, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      TLog.i("TPush", ">>> broadcast2NotifactionClickedFeedback intent=" + paramIntent);
      Intent localIntent = new Intent("com.tencent.android.tpush.action.FEEDBACK");
      localIntent.setPackage(paramContext.getPackageName());
      localIntent.putExtra("TPUSH.FEEDBACK", 4);
      localIntent.putExtra("TPUSH.ERRORCODE", 0);
      localIntent.putExtras(paramIntent);
      paramContext.sendBroadcast(localIntent);
    }
  }

  private static void c(Context paramContext, Intent paramIntent)
  {
    if ((XGPro.isEnableXGPro(paramContext)) && (paramIntent != null))
    {
      TLog.i("TPush", ">>> reportNotifactionClicked2Mta intent=" + paramIntent);
      long l = paramIntent.getLongExtra("busiMsgId", -1L);
      TLog.i("XGPro", "reportNotifactionClicked2Mta BusiMsgId=" + l);
      Properties localProperties = new Properties();
      localProperties.put("busiMsgId", String.valueOf(l));
      XGPro.a(paramContext).a("xg_click", localProperties);
    }
  }

  private static void c(Context paramContext, Intent paramIntent, XGIOperateCallback paramXGIOperateCallback)
  {
    monitorenter;
    try
    {
      boolean bool = TpnsSecurity.checkTpnsSecurityLibSo(paramContext);
      if (!bool);
      while (true)
      {
        return;
        TLog.init(paramContext);
        a(paramContext);
        String str = com.tencent.android.tpush.common.i.b(paramContext);
        TLog.v("TPush", ">>> register to " + str);
        if (!com.tencent.android.tpush.common.i.a(str))
          paramIntent.setPackage(str);
        if (paramXGIOperateCallback != null)
        {
          com.tencent.android.tpush.common.c.a();
          paramContext.registerReceiver(new h(paramXGIOperateCallback), new IntentFilter("com.tencent.android.tpush.action.REGISTER.RESULT"));
        }
        TLog.v("TPush", ">>> send register intent " + paramIntent);
        paramContext.sendBroadcast(paramIntent);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void clearLocalNotifications(Context paramContext)
  {
    if (paramContext == null)
      TLog.e("TPush", ">>> clearLocalNotifications  context==null.");
    do
      return;
    while (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
    a(paramContext);
    com.tencent.android.tpush.service.b.a.a().d(paramContext);
  }

  private static void d(Context paramContext, Intent paramIntent, XGIOperateCallback paramXGIOperateCallback)
  {
    a(paramContext);
    String str = com.tencent.android.tpush.common.i.b(paramContext);
    if (!com.tencent.android.tpush.common.i.a(str))
      paramIntent.setPackage(str);
    if (paramXGIOperateCallback != null)
    {
      com.tencent.android.tpush.common.c.a();
      paramContext.registerReceiver(new i(paramXGIOperateCallback), new IntentFilter("com.tencent.android.tpush.action.UNREGISTER.RESULT"));
    }
    paramContext.sendBroadcast(paramIntent);
  }

  public static void deleteTag(Context paramContext, String paramString)
  {
    a(paramContext, paramString, 2);
  }

  public static Context getApplicationContext()
  {
    return b;
  }

  public static XGPushNotificationBuilder getDefaultNotificationBuilder(Context paramContext)
  {
    XGPushNotificationBuilder localXGPushNotificationBuilder = getNotificationBuilder(paramContext, 0);
    if (localXGPushNotificationBuilder == null)
      b.a(paramContext);
    return localXGPushNotificationBuilder;
  }

  public static XGPushNotificationBuilder getNotificationBuilder(Context paramContext, int paramInt)
  {
    if (paramContext == null)
    {
      Log.e("TPush", "getNotificationBuilder  context == null");
      return null;
    }
    return b.a(paramContext, paramInt);
  }

  public static int getServiceStatus(Context paramContext)
  {
    if (paramContext != null)
    {
      a(paramContext);
      return com.tencent.android.tpush.common.i.c(paramContext);
    }
    return 0;
  }

  public static void msgAck(Context paramContext, long paramLong)
  {
    if ((paramContext != null) && (paramLong > 0L))
    {
      a(paramContext);
      TLog.i("XGService", "@@ msgAck(" + paramContext.getPackageName() + "," + paramLong + ")");
      Intent localIntent = new Intent("com.tencent.android.tpush.action.MSG_ACK");
      localIntent.putExtra("msgId", paramLong);
      localIntent.putExtra("packName", paramContext.getPackageName());
      paramContext.sendBroadcast(localIntent);
    }
  }

  public static void msgAck(Context paramContext, com.tencent.android.tpush.a.h paramh)
  {
    if (paramh != null)
    {
      msgAck(paramContext, paramh.b());
      a(paramContext, paramh);
    }
  }

  public static XGPushClickedResult onActivityStarted(Activity paramActivity)
  {
    TLog.v("XGPushMessage", ">>> onActivityStarted " + paramActivity);
    if ((paramActivity == null) || (paramActivity.getIntent() == null))
      return null;
    Intent localIntent = paramActivity.getIntent();
    if (localIntent != null)
    {
      Serializable localSerializable = localIntent.getSerializableExtra("tag.tpush.NOTIFIC");
      localIntent.removeExtra("tag.tpush.NOTIFIC");
      if ((localSerializable != null) && ((localSerializable instanceof XGPushClickedResult)))
      {
        XGPushClickedResult localXGPushClickedResult = (XGPushClickedResult)localSerializable;
        localXGPushClickedResult.parseIntent(localIntent);
        return localXGPushClickedResult;
      }
    }
    return null;
  }

  public static void onActivityStoped(Activity paramActivity)
  {
    if (paramActivity == null);
    do
      return;
    while (!TpnsSecurity.checkTpnsSecurityLibSo(paramActivity));
    a(paramActivity);
  }

  public static XGPushClickedResult onClickResult(Activity paramActivity)
  {
    TLog.v("XGPushMessage", ">>> onClickResult " + paramActivity);
    TLog.i("TPush", ">>> onActivityStarted activity=" + paramActivity);
    if ((paramActivity == null) || (paramActivity.getIntent() == null));
    Intent localIntent;
    String str;
    do
    {
      do
        return null;
      while (!TpnsSecurity.checkTpnsSecurityLibSo(paramActivity));
      a(paramActivity);
      localIntent = paramActivity.getIntent();
      localIntent.putExtra("activity", paramActivity.getClass().getName());
      str = localIntent.getStringExtra("tag.tpush.MSG");
    }
    while ((str == null) || (!str.equalsIgnoreCase("true")));
    a(paramActivity, localIntent);
    b(paramActivity, localIntent);
    c(paramActivity, localIntent);
    XGPushClickedResult localXGPushClickedResult = new XGPushClickedResult();
    localXGPushClickedResult.parseIntent(localIntent);
    localIntent.removeExtra("tag.tpush.MSG");
    return localXGPushClickedResult;
  }

  public static void registerPush(Context paramContext)
  {
    registerPush(paramContext, new c());
  }

  public static void registerPush(Context paramContext, XGIOperateCallback paramXGIOperateCallback)
  {
    if (paramXGIOperateCallback == null)
      throw new IllegalArgumentException("The callback parameter can not be null!");
    a(paramContext, null, null, -1, null, paramXGIOperateCallback);
  }

  public static void registerPush(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null))
    {
      TLog.e("TPush", "the parameter context or account of registerPush is invalid.");
      return;
    }
    registerPush(paramContext, paramString, new d());
  }

  public static void registerPush(Context paramContext, String paramString, XGIOperateCallback paramXGIOperateCallback)
  {
    if (paramXGIOperateCallback == null)
      throw new IllegalArgumentException("The callback parameter can not be null!");
    registerPush(paramContext, paramString, "0", 0, null, paramXGIOperateCallback);
  }

  public static void registerPush(Context paramContext, String paramString1, String paramString2, int paramInt, String paramString3, XGIOperateCallback paramXGIOperateCallback)
  {
    if (paramXGIOperateCallback == null)
      throw new IllegalArgumentException("The callback parameter can not be null!");
    if ((paramContext == null) || (com.tencent.android.tpush.common.i.a(paramString1)) || (com.tencent.android.tpush.common.i.a(paramString2)) || (paramInt < 0))
    {
      paramXGIOperateCallback.onFail(null, 10001, "The context, account, ticket or ticketType is(are) invalid!");
      return;
    }
    a(paramContext, paramString1, paramString2, paramInt, paramString3, paramXGIOperateCallback);
  }

  public static void reportNotifactionClear2Mta(Context paramContext, long paramLong)
  {
    if (XGPro.isEnableXGPro(paramContext))
    {
      TLog.i("XGPro", "reportNotifactionClear2Mta BusiMsgId=" + paramLong);
      Properties localProperties = new Properties();
      localProperties.put("busiMsgId", "" + paramLong);
      XGPro.a(paramContext).a("xg_clear", localProperties);
    }
  }

  public static void setDefaultNotificationBuilder(Context paramContext, XGPushNotificationBuilder paramXGPushNotificationBuilder)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("context is null.");
    if (paramXGPushNotificationBuilder == null)
      return;
    a(paramContext);
    b.a(paramContext, 0, paramXGPushNotificationBuilder);
  }

  public static void setPushNotificationBuilder(Context paramContext, int paramInt, XGPushNotificationBuilder paramXGPushNotificationBuilder)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("context is null.");
    if ((paramInt < 1) || (paramInt > 4096))
      throw new IllegalArgumentException("notificationBulderId超过范围[1, 4096].");
    if (paramXGPushNotificationBuilder == null)
      return;
    a(paramContext);
    b.a(paramContext, paramInt, paramXGPushNotificationBuilder);
  }

  public static void setTag(Context paramContext, String paramString)
  {
    a(paramContext, paramString, 1);
  }

  public static void startPushService(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.init(paramContext);
      a(paramContext);
      TLog.d(a, paramContext.getPackageName() + "call start Push Service");
      if (com.tencent.android.tpush.common.i.c(paramContext) == 0)
      {
        TLog.d(a, "Push Service isn't Running, need start!");
        com.tencent.android.tpush.common.i.d(paramContext);
      }
    }
  }

  public static void unregisterPush(Context paramContext)
  {
    if (paramContext == null)
    {
      TLog.e("TPush", "the context of unregisterPush is null");
      return;
    }
    String str = XGPushConfig.getToken(paramContext);
    if ((str == null) || (str.equals("0")))
    {
      TLog.e("TPush", "the token is invalid, please check if register failed?");
      return;
    }
    unregisterPush(paramContext, str, new e());
  }

  public static void unregisterPush(Context paramContext, String paramString, XGIOperateCallback paramXGIOperateCallback)
  {
    if (paramContext == null)
      if (paramXGIOperateCallback != null)
        paramXGIOperateCallback.onFail(null, 10001, "The context parameter can not be null!");
    do
    {
      return;
      throw new IllegalArgumentException("The context parameter can not be null!");
    }
    while (!TpnsSecurity.checkTpnsSecurityLibSo(paramContext));
    long l = XGPushConfig.getAccessId(paramContext);
    String str = XGPushConfig.getAccessKey(paramContext);
    if ((l <= 0L) || (com.tencent.android.tpush.common.i.a(str)) || (com.tencent.android.tpush.common.i.a(paramString)))
    {
      if (paramXGIOperateCallback != null)
      {
        paramXGIOperateCallback.onFail(null, 10001, "The accessId, accessKey or token is invalid!");
        return;
      }
      throw new IllegalArgumentException("accessId, accessKey or token is invalid.");
    }
    Intent localIntent = new Intent("com.tencent.android.tpush.action.UNREGISTER");
    localIntent.putExtra("accId", Rijndael.encrypt("" + l));
    localIntent.putExtra("accKey", Rijndael.encrypt(str));
    localIntent.putExtra("token", Rijndael.encrypt(paramString));
    localIntent.putExtra("packName", Rijndael.encrypt(paramContext.getPackageName()));
    localIntent.putExtra("operation", 101);
    com.tencent.android.tpush.service.c.c.d("?type=unreg&token=" + CacheManager.getToken(paramContext.getApplicationContext()));
    if (com.tencent.android.tpush.common.i.c(paramContext) == 1)
    {
      com.tencent.android.tpush.common.g.a(paramContext);
      d(paramContext, localIntent, paramXGIOperateCallback);
      return;
    }
    TLog.d("TPush", ">>> service is not started, try to start it.");
    com.tencent.android.tpush.common.i.d(paramContext);
    paramContext.registerReceiver(new f(localIntent, paramXGIOperateCallback), new IntentFilter("com.tencent.android.tpush.action.SERVICE_START"));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushManager
 * JD-Core Version:    0.6.0
 */