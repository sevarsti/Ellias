package com.tencent.qqgamemi.business;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.net.download.multiplex.FileDownload;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.NetworkUtil;
import com.tencent.component.utils.SecurityUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QmiSdkApi;
import com.tencent.qqgamemi.common.CommonData;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.protocol.MsgHandle;
import java.io.File;

public class AutoDownLoadGameJoy
{
  private static final String a = AutoDownLoadGameJoy.class.getSimpleName();
  private static long b = 0L;
  private static final String c = "gamejoy";
  private static final String d = ".apk";
  private static final int g = 1;
  private static final int h = 2;
  private Context e;
  private AutoDownLoadInfo f;
  private Handler i = new a(this);
  private TaskObserver j = new b(this);

  public AutoDownLoadGameJoy(Context paramContext)
  {
    this.e = paramContext;
    if (DebugUtil.a())
      b = 5000L;
  }

  private void a(Task paramTask)
  {
    if ((this.f != null) && (this.f.a))
    {
      if (((DownloadTask)paramTask).x() == this.f.d)
      {
        d();
        QmiSdkApi.setPreDownloadApkPath(this.e, this.f.e);
      }
    }
    else
      return;
    LogUtil.d(a, "download size is not match");
  }

  private void a(AutoDownLoadInfo paramAutoDownLoadInfo)
  {
    LogUtil.d(a, "sendAutoDownLoadInfo success:" + paramAutoDownLoadInfo);
    this.f = paramAutoDownLoadInfo;
    a(paramAutoDownLoadInfo.c);
  }

  private void a(String paramString)
  {
    if ((this.f != null) && (this.f.a))
    {
      Message localMessage = new Message();
      localMessage.what = 2;
      localMessage.obj = paramString;
      this.i.sendMessageDelayed(localMessage, b);
      LogUtil.d(a, "sendDownLoadMsg delay " + b + "ms");
    }
  }

  private void b(String paramString)
  {
    if (NetworkUtil.b(this.e))
    {
      c(paramString);
      return;
    }
    LogUtil.i(a, "not wifi then download later.");
    a(paramString);
  }

  private void c(String paramString)
  {
    LogUtil.d(a, "startDownLoad:" + paramString);
    DownloadTask localDownloadTask = FileDownload.a(paramString);
    if (localDownloadTask != null)
    {
      LogUtil.d(a, "startDownLoad continue");
      FileDownload.a(localDownloadTask);
      return;
    }
    String str1 = CacheManager.a(this.e, "downloadHall", true);
    if (TextUtils.isEmpty(str1))
      str1 = CommonData.a();
    String str2 = "gamejoy_" + System.currentTimeMillis() + ".apk";
    this.f.e = (str1 + File.separator + SecurityUtil.a(str2));
    LogUtil.d(a, "startDownLoad to " + this.f.e);
    FileDownload.a(paramString, str1, SecurityUtil.a(str2), this.j);
  }

  private boolean c()
  {
    boolean bool = this.e.getSharedPreferences("isDownLoadedGameJoy", 0).getBoolean("isDownLoaded", false);
    LogUtil.d(a, "isDownLoadedGameJoy:" + bool);
    return bool;
  }

  private void d()
  {
    LogUtil.d(a, "setDownLoadedGameJoy");
    SharedPreferences.Editor localEditor = this.e.getSharedPreferences("isDownLoadedGameJoy", 2).edit();
    localEditor.putBoolean("isDownLoaded", true);
    localEditor.commit();
  }

  private void e()
  {
    String str = this.e.getPackageName();
    LogUtil.d(a, "sendAutoDownLoadInfo:" + str);
    MsgHandle.a(this.i, 1, str);
  }

  public void a()
  {
    LogUtil.d(a, "startAutoDownLoadGameJoy");
    if (QMiConfig.b())
    {
      if (QMiCommon.c(this.e))
        LogUtil.d(a, "return for gameJoy is install.");
    }
    else
      return;
    if (c())
    {
      LogUtil.d(a, "return for gameJoy is download.");
      return;
    }
    e();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.AutoDownLoadGameJoy
 * JD-Core Version:    0.6.0
 */