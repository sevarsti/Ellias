package com.tencent.android.tpush.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.tencent.android.tpush.XGBasicPushNotificationBuilder;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotificationBuilder;
import com.tencent.android.tpush.common.a;
import com.tencent.android.tpush.common.i;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class b
{
  static String a;
  private static XGPushNotificationBuilder b = null;

  static
  {
    a = "";
  }

  public static Intent a(Context paramContext, d paramd, boolean paramBoolean)
  {
    Intent localIntent1 = null;
    switch (paramd.a)
    {
    default:
      TLog.e("TPush", "未知的操作类型:" + paramd.a);
      return null;
    case 1:
      Intent localIntent3 = new Intent("com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE");
      String str = paramd.b;
      if (i.a(str))
        str = b(paramContext);
      int i = 538968064;
      if ((paramd.c == null) || (paramd.c.a <= 0))
      {
        if (paramBoolean)
          i = 268435456;
        localIntent3.addFlags(i);
        localIntent3.addFlags(268435456);
        localIntent3.setFlags(67239936);
      }
      while (true)
      {
        TLog.i("TPush", "notifaction intent flag:" + i + ",activity for open:" + str);
        localIntent3.putExtra("activity", str);
        localIntent3.setClassName(paramContext.getApplicationContext(), "com.tencent.android.tpush.XGPushActivity");
        return localIntent3;
        i = paramd.c.a;
        localIntent3.setFlags(i);
      }
    case 2:
      Intent localIntent2 = new Intent("android.intent.action.VIEW", Uri.parse(paramd.f));
      localIntent2.addFlags(268435456);
      return localIntent2;
    case 3:
    }
    try
    {
      localIntent1 = Intent.parseUri(paramd.d, 1);
      localIntent1.addFlags(268435456);
      return localIntent1;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      TLog.e("TPush", "打开intent异常", localURISyntaxException);
    }
    return localIntent1;
  }

  public static XGPushNotificationBuilder a(Context paramContext)
  {
    monitorenter;
    try
    {
      XGPushNotificationBuilder localXGPushNotificationBuilder = new XGBasicPushNotificationBuilder().setIcon(paramContext.getApplicationInfo().icon).setDefaults(-1).setFlags(16);
      monitorexit;
      return localXGPushNotificationBuilder;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public static XGPushNotificationBuilder a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnonnull +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: iload_1
    //   10: invokestatic 165	com/tencent/android/tpush/a/b:a	(I)Ljava/lang/String;
    //   13: aconst_null
    //   14: invokestatic 170	com/tencent/android/tpush/common/h:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   17: astore_3
    //   18: aload_3
    //   19: ifnull +143 -> 162
    //   22: ldc 26
    //   24: new 28	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 31	java/lang/StringBuilder:<init>	()V
    //   31: ldc 172
    //   33: invokevirtual 37	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_3
    //   37: invokevirtual 37	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokestatic 91	com/tencent/android/tpush/logging/TLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   46: new 174	org/json/JSONObject
    //   49: dup
    //   50: aload_3
    //   51: invokespecial 175	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   54: astore 5
    //   56: aload 5
    //   58: ldc 177
    //   60: invokevirtual 180	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   63: istore 6
    //   65: aconst_null
    //   66: astore_2
    //   67: iload 6
    //   69: ifeq +49 -> 118
    //   72: new 138	com/tencent/android/tpush/XGBasicPushNotificationBuilder
    //   75: dup
    //   76: invokespecial 139	com/tencent/android/tpush/XGBasicPushNotificationBuilder:<init>	()V
    //   79: astore 7
    //   81: aload 5
    //   83: ldc 177
    //   85: invokevirtual 184	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: astore 9
    //   90: aload 7
    //   92: astore_2
    //   93: aload 9
    //   95: astore 10
    //   97: aload_2
    //   98: aload 10
    //   100: invokevirtual 187	com/tencent/android/tpush/XGPushNotificationBuilder:decode	(Ljava/lang/String;)V
    //   103: aload_2
    //   104: areturn
    //   105: astore 4
    //   107: ldc 26
    //   109: ldc 14
    //   111: aload 4
    //   113: invokestatic 135	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   116: aload_2
    //   117: areturn
    //   118: aload 5
    //   120: ldc 189
    //   122: invokevirtual 180	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   125: istore 11
    //   127: aconst_null
    //   128: astore_2
    //   129: iload 11
    //   131: ifeq -125 -> 6
    //   134: new 191	com/tencent/android/tpush/XGCustomPushNotificationBuilder
    //   137: dup
    //   138: invokespecial 192	com/tencent/android/tpush/XGCustomPushNotificationBuilder:<init>	()V
    //   141: astore 12
    //   143: aload 5
    //   145: ldc 189
    //   147: invokevirtual 184	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   150: astore 14
    //   152: aload 12
    //   154: astore_2
    //   155: aload 14
    //   157: astore 10
    //   159: goto -62 -> 97
    //   162: ldc 26
    //   164: new 28	java/lang/StringBuilder
    //   167: dup
    //   168: invokespecial 31	java/lang/StringBuilder:<init>	()V
    //   171: ldc 194
    //   173: invokevirtual 37	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: iload_1
    //   177: invokevirtual 40	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   180: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 197	com/tencent/android/tpush/logging/TLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   186: aconst_null
    //   187: areturn
    //   188: astore 8
    //   190: aload 7
    //   192: astore_2
    //   193: aload 8
    //   195: astore 4
    //   197: goto -90 -> 107
    //   200: astore 13
    //   202: aload 12
    //   204: astore_2
    //   205: aload 13
    //   207: astore 4
    //   209: goto -102 -> 107
    //
    // Exception table:
    //   from	to	target	type
    //   22	65	105	org/json/JSONException
    //   72	81	105	org/json/JSONException
    //   97	103	105	org/json/JSONException
    //   118	127	105	org/json/JSONException
    //   134	143	105	org/json/JSONException
    //   81	90	188	org/json/JSONException
    //   143	152	200	org/json/JSONException
  }

  private static String a(int paramInt)
  {
    return "TPUSH_NOTIF_BUILDID_" + String.valueOf(paramInt);
  }

  public static void a(Context paramContext, int paramInt, XGPushNotificationBuilder paramXGPushNotificationBuilder)
  {
    String str = a(paramInt);
    JSONObject localJSONObject1 = new JSONObject();
    paramXGPushNotificationBuilder.encode(localJSONObject1);
    JSONObject localJSONObject2 = new JSONObject();
    a.a(localJSONObject2, paramXGPushNotificationBuilder.getType(), localJSONObject1.toString());
    com.tencent.android.tpush.common.h.b(paramContext, str, localJSONObject2.toString());
  }

  public static void a(Context paramContext, h paramh)
  {
    TLog.i("TPush", "@openActivityOrUrl:" + paramh.g());
    c localc = (c)paramh.h();
    d locald = localc.l();
    XGPushNotificationBuilder localXGPushNotificationBuilder1 = a(paramContext, localc.g());
    int m;
    label103: int n;
    label116: XGPushNotificationBuilder localXGPushNotificationBuilder2;
    if (localXGPushNotificationBuilder1 == null)
    {
      localXGPushNotificationBuilder1 = XGPushManager.getDefaultNotificationBuilder(paramContext);
      if (localXGPushNotificationBuilder1 == null)
        localXGPushNotificationBuilder1 = a(paramContext);
      if (localc.j() != 0)
      {
        localXGPushNotificationBuilder1.setFlags(0x10 | localXGPushNotificationBuilder1.getFlags());
        if (localc.h() == 0)
          break label322;
        m = -1;
        if (localc.i() == 0)
          break label329;
        n = m | 0x2;
        localXGPushNotificationBuilder1.setDefaults(n);
      }
    }
    else
    {
      localXGPushNotificationBuilder2 = localXGPushNotificationBuilder1;
      int i = localXGPushNotificationBuilder2.getIcon();
      if (localc.m() > 0)
        i = localc.m();
      if (i <= 0)
        i = paramContext.getApplicationInfo().icon;
      TLog.i("TPush", "notifaction icon=" + i + ",title=" + localc.d() + ",content=" + localc.e() + " , BuilderId = " + localc.g());
      localXGPushNotificationBuilder2.setIcon(i);
      localXGPushNotificationBuilder2.setTitle(localc.d());
      localXGPushNotificationBuilder2.setTickerText(localc.e());
      String str = localc.f();
      if ((i.a(str)) || ("{}".equalsIgnoreCase(str)))
        break label829;
    }
    label829: for (boolean bool = true; ; bool = false)
    {
      Intent localIntent1 = a(paramContext, locald, bool);
      if (localIntent1 == null)
      {
        TLog.e("TPush", "intent is null");
        return;
        localXGPushNotificationBuilder1.setFlags(0x20 | localXGPushNotificationBuilder1.getFlags());
        break;
        label322: m = -2;
        break label103;
        label329: n = m & 0xFFFFFFFD;
        break label116;
      }
      TLog.i("TPush", "intent is " + localIntent1.toUri(1));
      if (bool)
        localIntent1.putExtra("custom_content", localc.f());
      localIntent1.putExtra("tag.tpush.MSG", "true");
      localIntent1.putExtra("title", Rijndael.encrypt(localc.d()));
      localIntent1.putExtra("content", Rijndael.encrypt(localc.e()));
      if (localc.f() != null)
        localIntent1.putExtra("custom_content", Rijndael.encrypt(localc.f()));
      localIntent1.putExtra("msgId", paramh.b());
      localIntent1.putExtra("accId", paramh.c());
      localIntent1.putExtra("busiMsgId", paramh.d());
      localIntent1.putExtra("timestamps", paramh.e());
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      int j = localc.k();
      if (j <= 0)
        j = b(paramContext, localc.g());
      if (localc.k() == -1)
        localNotificationManager.cancelAll();
      int k = 134217728;
      if ((locald.c != null) && (locald.c.b > 0))
        k = locald.c.b;
      Intent localIntent2 = new Intent("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT");
      localIntent2.putExtra("packName", paramContext.getPackageName());
      localIntent2.putExtra("action", 1);
      localIntent2.putExtras(localIntent1);
      PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext.getApplicationContext(), j, localIntent1, k);
      localXGPushNotificationBuilder2.setContentIntent(localPendingIntent);
      TLog.i("TPush", "Notification @ PendingIntent" + localPendingIntent + ",pendintIntentFlag:" + k + ",intent flag:" + localIntent1.getFlags());
      Notification localNotification = localXGPushNotificationBuilder2.buildNotification(paramContext);
      localNotification.deleteIntent = PendingIntent.getBroadcast(paramContext.getApplicationContext(), j, localIntent2, k);
      localNotificationManager.notify(j, localNotification);
      TLog.i("TPush", "Notification @" + localNotification.toString());
      Intent localIntent3 = new Intent("com.tencent.android.tpush.action.FEEDBACK");
      localIntent3.putExtra("TPUSH.ERRORCODE", 0);
      localIntent3.setPackage(paramContext.getPackageName());
      localIntent3.putExtras(localIntent1);
      localIntent3.putExtra("TPUSH.FEEDBACK", 5);
      TLog.i("TPush", "send FEEDBACK_NOTIFACTION_SHOWED");
      paramContext.sendBroadcast(localIntent3);
      return;
    }
  }

  // ERROR //
  private static int b(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 28	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 31	java/lang/StringBuilder:<init>	()V
    //   10: ldc_w 435
    //   13: invokevirtual 37	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: iload_1
    //   17: invokestatic 204	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   20: invokevirtual 37	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: astore 6
    //   28: aload_0
    //   29: aload 6
    //   31: iconst_0
    //   32: invokestatic 438	com/tencent/android/tpush/common/h:a	(Landroid/content/Context;Ljava/lang/String;I)I
    //   35: istore 7
    //   37: iload 7
    //   39: istore 4
    //   41: iload 4
    //   43: ldc_w 439
    //   46: if_icmplt +6 -> 52
    //   49: iconst_0
    //   50: istore 4
    //   52: iload 4
    //   54: iconst_1
    //   55: iadd
    //   56: istore 8
    //   58: aload_0
    //   59: aload 6
    //   61: iload 8
    //   63: invokestatic 442	com/tencent/android/tpush/common/h:b	(Landroid/content/Context;Ljava/lang/String;I)V
    //   66: ldc 2
    //   68: monitorexit
    //   69: iload 4
    //   71: ireturn
    //   72: astore_3
    //   73: iconst_0
    //   74: istore 4
    //   76: aload_3
    //   77: astore 5
    //   79: ldc 26
    //   81: ldc 14
    //   83: aload 5
    //   85: invokestatic 135	com/tencent/android/tpush/logging/TLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   88: goto -22 -> 66
    //   91: astore_2
    //   92: ldc 2
    //   94: monitorexit
    //   95: aload_2
    //   96: athrow
    //   97: astore 5
    //   99: goto -20 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   3	37	72	java/lang/Throwable
    //   3	37	91	finally
    //   58	66	91	finally
    //   79	88	91	finally
    //   58	66	97	java/lang/Throwable
  }

  public static String b(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN", null);
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setPackage(paramContext.getPackageName());
      Iterator localIterator = paramContext.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        if (localResolveInfo.activityInfo == null)
          continue;
        String str = localResolveInfo.activityInfo.name;
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      TLog.e("TPush", "查找主Activity出错", localThrowable);
    }
    return null;
  }

  public static void b(Context paramContext, h paramh)
  {
    if ((paramh.h() instanceof c))
    {
      TLog.i("TPush", "showNotification @" + paramh.f());
      c localc = (c)paramh.h();
      if ((localc == null) || (localc.l() == null))
        TLog.e("TPush", "showNotification holder == " + localc + " @" + localc.l());
    }
    else
    {
      return;
    }
    a(paramContext, paramh);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.a.b
 * JD-Core Version:    0.6.0
 */