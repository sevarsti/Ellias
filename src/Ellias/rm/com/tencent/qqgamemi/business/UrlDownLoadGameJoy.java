package com.tencent.qqgamemi.business;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.net.download.multiplex.FileDownload;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiApplication;
import com.tencent.qqgamemi.common.CommonData;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.report.UserAccessStatics;
import com.tencent.qqgamemi.ui.QmiSpecialDownloadDialog;
import com.tencent.qqgamemi.ui.QmiSpecialDownloadDialog.Builder;
import com.tencent.qqgamemi.view.QMiToast;
import java.io.File;

public class UrlDownLoadGameJoy
{

  @PluginApi(a=11)
  public static final String MODULE_FRIEND_SHARE = "friendshare";

  @PluginApi(a=11)
  public static final String MODULE_ME = "me";

  @PluginApi(a=11)
  public static final String MODULE_SCREENCAP = "screencap";

  @PluginApi(a=11)
  public static final String MODULE_SCREENCAP_SHARE = "screencapshare";

  @PluginApi(a=11)
  public static final String MODULE_SCREENSHOT = "screenshot";

  @PluginApi(a=11)
  public static final String MODULE_SCREENSHOT_SHARE = "screenshotshare";
  private static final String b = UrlDownLoadGameJoy.class.getSimpleName();
  private static final String c = "QmiDownload";
  private static final String d = "qmi_download_ing";
  private static final String e = "qmi_download_path";
  private static final String f = "http://andfcg.qq.com/fcg-bin/mobile/android/and_syb_download_report";
  private static final String g = "?channel=";
  private static final String h = "&module=";
  private static final String i = "gamejoy";
  private static final String j = ".apk";
  private static UrlDownLoadGameJoy n = null;
  private static final int p = 1;
  private static final int q = 2;
  private static final int r = 3;
  private static final int s = 4;
  Handler a = new f(this, Looper.getMainLooper());
  private Context k;
  private NotificationManager l;
  private NotificationCompat.Builder m;
  private TaskObserver o = new e(this);

  private UrlDownLoadGameJoy(Context paramContext)
  {
    this.k = paramContext;
    this.l = ((NotificationManager)paramContext.getSystemService("notification"));
    this.m = new NotificationCompat.Builder(paramContext);
  }

  public static UrlDownLoadGameJoy a()
  {
    if (n == null)
      n = new UrlDownLoadGameJoy(QMiApplication.a());
    return n;
  }

  public static String a(Context paramContext)
  {
    String str = paramContext.getSharedPreferences("QmiDownload", 0).getString("qmi_download_path", "");
    LogUtil.d(b, "getDownloadApkPath:" + str);
    return str;
  }

  private void a(int paramInt)
  {
    String str = this.k.getResources().getString(paramInt);
    QMiToast.a(this.k, str, 1000).show();
  }

  public static void a(Context paramContext, String paramString)
  {
    a(paramContext, paramString, paramContext.getResources().getString(ResourceUtil.b("qmi_download_dialog_info")));
  }

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext, paramString1, paramContext.getPackageName(), paramString2);
  }

  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (new File(a(paramContext)).exists())
    {
      new QmiSpecialDownloadDialog.Builder(paramContext).a().show();
      UserAccessStatics.getInstance(paramContext).addQMiAction(301, System.currentTimeMillis(), QMiCommon.a(paramContext), null);
      LogUtil.d(b, "return for gamejoy is install");
      return;
    }
    if (!b(paramContext))
    {
      UrlDownloadGameJoyDialog localUrlDownloadGameJoyDialog = new UrlDownloadGameJoyDialog(paramContext);
      localUrlDownloadGameJoyDialog.a(paramString1);
      localUrlDownloadGameJoyDialog.b(paramString2);
      localUrlDownloadGameJoyDialog.c(paramString3);
      localUrlDownloadGameJoyDialog.show();
      return;
    }
    QMiToast.a(paramContext, "已经在下载了，请稍候", 1000).show();
  }

  private static void a(Context paramContext, boolean paramBoolean)
  {
    LogUtil.d(b, "set downloading " + paramBoolean);
    paramContext.getSharedPreferences("QmiDownload", 0).edit().putBoolean("qmi_download_ing", paramBoolean).commit();
  }

  private void a(Task paramTask)
  {
    b(((DownloadTask)paramTask).u());
  }

  private String b(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer("http://andfcg.qq.com/fcg-bin/mobile/android/and_syb_download_report");
    localStringBuffer.append("?channel=").append(paramString1).append("&module=").append(paramString2);
    return localStringBuffer.toString();
  }

  private void b(int paramInt)
  {
    LogUtil.d(b, "updateNotify:" + paramInt);
    this.m.setProgress(100, paramInt, false);
    this.l.notify(ResourceUtil.b("qmi_download_notify_content"), this.m.build());
  }

  public static void b(Context paramContext, String paramString)
  {
    LogUtil.d(b, "setDownloadApkPath:" + paramString);
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("QmiDownload", 2).edit();
    localEditor.putString("qmi_download_path", paramString);
    localEditor.commit();
  }

  private void b(Task paramTask)
  {
    e();
    a(this.k, false);
    a(ResourceUtil.b("qmi_download_notify_done_content"));
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    String str = localDownloadTask.t() + File.separator + localDownloadTask.q();
    b(this.k, str);
    QMiCommon.b(this.k, str);
  }

  private void b(String paramString)
  {
    LogUtil.d(b, "startDownLoadGameJoyTurely:" + paramString);
    DownloadTask localDownloadTask = FileDownload.a(paramString);
    if (localDownloadTask != null)
    {
      LogUtil.d(b, "startDownLoad continue");
      FileDownload.a(localDownloadTask);
    }
    while (true)
    {
      c();
      return;
      String str1 = CacheManager.a(this.k, "downloadHall", true);
      if (TextUtils.isEmpty(str1))
        str1 = CommonData.a();
      String str2 = "gamejoy_" + System.currentTimeMillis() + ".apk";
      LogUtil.d(b, "startDownLoad to " + str1);
      FileDownload.a(paramString, str1, str2, this.o);
    }
  }

  private static boolean b(Context paramContext)
  {
    boolean bool = paramContext.getSharedPreferences("QmiDownload", 0).getBoolean("qmi_download_ing", false);
    LogUtil.d(b, "checkDownLoading:" + bool);
    return bool;
  }

  private void c()
  {
    d();
    a(this.k, true);
    a(ResourceUtil.b("qmi_download_start_msg"));
  }

  private void c(Task paramTask)
  {
    e();
    a(this.k, false);
    a(ResourceUtil.b("qmi_download_failed"));
  }

  private void d()
  {
    LogUtil.d(b, "addNotify");
    this.m.setSmallIcon(ResourceUtil.c("qmi_stand1"));
    String str1 = this.k.getResources().getString(ResourceUtil.b("qmi_download_dialog_title"));
    this.m.setContentTitle(str1);
    String str2 = this.k.getResources().getString(ResourceUtil.b("qmi_download_notify_content"));
    this.m.setContentText(str2);
    this.m.setContentIntent(PendingIntent.getService(this.k, 0, new Intent(), 0));
    this.m.setTicker(str1);
    this.m.setAutoCancel(true);
    this.m.setProgress(100, 0, false);
    this.l.notify(ResourceUtil.b("qmi_download_notify_content"), this.m.build());
  }

  private void e()
  {
    this.l.cancel(ResourceUtil.b("qmi_download_notify_content"));
  }

  void a(String paramString)
  {
    a(this.k.getPackageName(), paramString);
  }

  void a(String paramString1, String paramString2)
  {
    if (!QMiConfig.b())
    {
      LogUtil.d(b, "return for not sdk");
      return;
    }
    if (QMiCommon.c(this.k))
    {
      LogUtil.d(b, "return for install gamejoy already");
      return;
    }
    b(b(paramString1, paramString2));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.UrlDownLoadGameJoy
 * JD-Core Version:    0.6.0
 */