package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.tencent.apkupdate.ApkUpdateListener;
import com.tencent.apkupdate.ApkUpdateSDK;
import com.tencent.tmassistantsdk.d.i;
import com.tencent.tmassistantsdk.openSDK.g;
import com.tencent.tmassistantsdk.protocol.jce.UpdateInfoLog;
import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TMSelfUpdateSDK
{
  protected static final String TAG = "SelfUpdateSDK";
  private static boolean isGenApk;
  protected static TMSelfUpdateSDK mInstance = null;
  private final String YYB_APPID = "50801";
  protected String hostPackageName = null;
  boolean isFromStartUpdate = false;
  private ApkUpdateListener mApkUpdateListener = new j(this);
  protected long mAppId = 0L;
  protected String mChannelId = "";
  String mCheckUpdateDownurl = "";
  int mCheckUpdateMethod;
  private com.tencent.tmassistantsdk.c.b mClientListener = new f(this);
  private com.tencent.tmassistantsdk.c.b mClientSDKListener = new h(this);
  protected Context mContext = null;
  HandlerThread mHandlerThread = new HandlerThread("selfUpdateSDK_call_thread");
  protected String mInstallYYB_via = "ANDROID.YYB.UPDATE";
  ReferenceQueue mListenerQueue = new ReferenceQueue();
  protected com.tencent.tmassistantsdk.openSDK.f mOpenSDKInstance = null;
  private com.tencent.tmassistantsdk.openSDK.d mOpenSDKYYBStateListener = new e(this);
  private b mPackageInstallListener = new k(this);
  Handler mSDKHandler;
  protected String mUnInstallYYB_via = "ANDROID.NEWYYB.UPDATE";
  ArrayList mWeakListenerArrayList = new ArrayList();
  int startResult = -1;
  protected byte updateType = 0;
  String yybUrl = "";

  static
  {
    isGenApk = true;
  }

  protected TMSelfUpdateSDK()
  {
    this.mHandlerThread.start();
    this.mSDKHandler = new Handler(this.mHandlerThread.getLooper());
  }

  public static String about()
  {
    return "TMAssistantSDK_MSDK_SelfUpdateSDK_20140715105038_debug_41732";
  }

  public static TMSelfUpdateSDK getInstance()
  {
    monitorenter;
    try
    {
      if (mInstance == null)
        mInstance = new TMSelfUpdateSDK();
      TMSelfUpdateSDK localTMSelfUpdateSDK = mInstance;
      return localTMSelfUpdateSDK;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void onCheckNeedUpdateInfo(TMSelfUpdateSDKUpdateInfo paramTMSelfUpdateSDKUpdateInfo)
  {
    if ((this.mWeakListenerArrayList != null) && (this.mWeakListenerArrayList.size() > 0))
    {
      Iterator localIterator = this.mWeakListenerArrayList.iterator();
      while (localIterator.hasNext())
      {
        ITMSelfUpdateSDKListener localITMSelfUpdateSDKListener = (ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get();
        if (localITMSelfUpdateSDKListener == null)
          com.tencent.tmassistantsdk.g.l.e("SelfUpdateSDK", "onCheckNeedUpdateInfo listener = null");
        localITMSelfUpdateSDKListener.OnCheckNeedUpdateInfo(paramTMSelfUpdateSDKUpdateInfo);
      }
    }
  }

  private void patchGenInstall(String paramString)
  {
    String str;
    if (!TextUtils.isEmpty(paramString))
    {
      str = paramString + "_" + this.hostPackageName + "_new.apk";
      if (new File(str).exists())
      {
        com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "new apk has yet exists：url:" + this.mCheckUpdateDownurl + ";  newPath:" + str);
        startInstall(str, this.hostPackageName, this.updateType);
        onStateChanged(0, -14, "SelfUpdate success,New Pakage is exists!");
      }
    }
    else
    {
      return;
    }
    int i = ApkUpdateSDK.getInstance().patchNewApk(this.hostPackageName, paramString, str);
    com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "now begin gen New apk; result=" + i + "; packageName=" + this.hostPackageName + "; patchPath=" + paramString + "; newGenApkPath=" + str);
    if (i == 0)
    {
      afterPatchNewApk(str);
      startInstall(str, this.hostPackageName, this.updateType);
      onStateChanged(0, 0, "SelfUpdate success !");
      return;
    }
    onStateChanged(2, i, "SelfUpdate failure,genNewApk failure!");
  }

  private boolean registerListener(ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    if (paramITMSelfUpdateSDKListener == null)
      return false;
    while (true)
    {
      Reference localReference = this.mListenerQueue.poll();
      if (localReference == null)
        break;
      this.mWeakListenerArrayList.remove(localReference);
    }
    Iterator localIterator = this.mWeakListenerArrayList.iterator();
    while (localIterator.hasNext())
      if ((ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get() == paramITMSelfUpdateSDKListener)
        return true;
    WeakReference localWeakReference = new WeakReference(paramITMSelfUpdateSDKListener, this.mListenerQueue);
    this.mWeakListenerArrayList.add(localWeakReference);
    return true;
  }

  private void startInstall(String paramString1, String paramString2, byte paramByte)
  {
    UpdateInfoLog localUpdateInfoLog = i.g().a(paramString2, this.mAppId);
    localUpdateInfoLog.updateType = paramByte;
    localUpdateInfoLog.actionCode = 104;
    localUpdateInfoLog.yybExistFlag = 0;
    i.g().a(localUpdateInfoLog);
    Uri localUri = Uri.fromFile(new File(paramString1));
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(localUri, "application/vnd.android.package-archive");
    localIntent.addFlags(268435456);
    this.mContext.startActivity(localIntent);
  }

  private boolean unregisterListener(ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    if (paramITMSelfUpdateSDKListener == null)
      return false;
    Iterator localIterator = this.mWeakListenerArrayList.iterator();
    while (localIterator.hasNext())
    {
      if ((ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get() != paramITMSelfUpdateSDKListener)
        continue;
      localIterator.remove();
      return true;
    }
    return false;
  }

  protected void afterPatchNewApk(String paramString)
  {
  }

  public void cancelUpdate()
  {
    this.mSDKHandler.post(new d(this));
  }

  public void checkNeedUpdate()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.hostPackageName);
    isGenApk = false;
    ApkUpdateSDK.getInstance().checkUpdate(localArrayList);
  }

  public int checkYYBInstalled()
  {
    int i = this.mOpenSDKInstance.a();
    if (i == 0);
    for (byte b = 1; ; b = 2)
    {
      UpdateInfoLog localUpdateInfoLog = i.g().a(this.hostPackageName, this.mAppId);
      localUpdateInfoLog.updateType = this.updateType;
      localUpdateInfoLog.actionCode = 100;
      localUpdateInfoLog.yybExistFlag = b;
      i.g().a(localUpdateInfoLog);
      return i;
    }
  }

  public void destroySelfUpdateSDK(ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    unregisterListener(paramITMSelfUpdateSDKListener);
    com.tencent.tmassistantsdk.c.f.b(this.mContext);
    this.mOpenSDKInstance.b(this.mOpenSDKYYBStateListener);
    this.mOpenSDKInstance.c();
    this.isFromStartUpdate = false;
    ApkUpdateSDK.getInstance().destory();
    PackageInstallReceiver.a().b(this.mContext);
    PackageInstallReceiver.a().b(this.mPackageInstallListener);
  }

  public void downloadGenApk(TMSelfUpdateSDKUpdateInfo paramTMSelfUpdateSDKUpdateInfo)
  {
    if ((paramTMSelfUpdateSDKUpdateInfo != null) && (!TextUtils.isEmpty(paramTMSelfUpdateSDKUpdateInfo.getUpdateDownloadUrl())))
    {
      if (paramTMSelfUpdateSDKUpdateInfo.getUpdateMethod() != 0)
        break label39;
      this.mCheckUpdateMethod = 1;
    }
    while (true)
    {
      this.mCheckUpdateDownurl = paramTMSelfUpdateSDKUpdateInfo.getUpdateDownloadUrl();
      genNewPkgProcess();
      return;
      label39: if (paramTMSelfUpdateSDKUpdateInfo.getUpdateMethod() == 1)
      {
        this.mCheckUpdateMethod = 2;
        continue;
      }
      if (paramTMSelfUpdateSDKUpdateInfo.getUpdateMethod() != 2)
        continue;
      this.mCheckUpdateMethod = 4;
    }
  }

  void genNewPkgProcess()
  {
    this.mSDKHandler.post(new l(this));
  }

  com.tencent.tmassistantsdk.c.c getClient(boolean paramBoolean)
  {
    com.tencent.tmassistantsdk.c.c localc;
    if (paramBoolean)
    {
      localc = com.tencent.tmassistantsdk.c.f.a(this.mContext).a("selfUpdateSDK_client_sdkupdate");
      if (localc != null)
        localc.a(this.mClientSDKListener);
    }
    do
    {
      return localc;
      localc = com.tencent.tmassistantsdk.c.f.a(this.mContext).a("selfUpdateSDK_client_yybupdate");
    }
    while (localc == null);
    localc.a(this.mClientListener);
    return localc;
  }

  public void initTMSelfUpdateSDK(Context paramContext, long paramLong, String paramString, ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    this.mAppId = paramLong;
    this.mInstallYYB_via = ("ANDROIDYYBSDK.YYB.SELFUPDATE." + paramLong);
    this.mUnInstallYYB_via = ("ANDROIDYYBSDK.NEWYYB.SELFUPDATE." + paramLong);
    initTMSelfUpdateSDK(paramContext, false, paramString, null, paramITMSelfUpdateSDKListener);
  }

  protected void initTMSelfUpdateSDK(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    this.mInstallYYB_via = paramString3;
    this.mUnInstallYYB_via = paramString4;
    initTMSelfUpdateSDK(paramContext, true, paramString1, paramString2, paramITMSelfUpdateSDKListener);
  }

  protected void initTMSelfUpdateSDK(Context paramContext, boolean paramBoolean, String paramString1, String paramString2, ITMSelfUpdateSDKListener paramITMSelfUpdateSDKListener)
  {
    if (!(paramContext instanceof Application))
      throw new Exception("you must input an application context!");
    this.mContext = paramContext;
    if (TextUtils.isEmpty(paramString2));
    for (this.mChannelId = null; ; this.mChannelId = paramString2)
    {
      this.yybUrl = ("http://www.myapp.com/downcenter/a/50801?g_f=" + paramString1);
      this.mOpenSDKInstance = com.tencent.tmassistantsdk.openSDK.f.b();
      this.mOpenSDKInstance.b(this.mContext);
      this.mOpenSDKInstance.a(this.mOpenSDKYYBStateListener);
      this.hostPackageName = this.mContext.getPackageName();
      if (paramBoolean)
      {
        this.mInstallYYB_via = (this.mInstallYYB_via + "." + this.hostPackageName);
        this.mUnInstallYYB_via = (this.mUnInstallYYB_via + "." + this.hostPackageName);
      }
      ApkUpdateSDK.getInstance().init(this.mContext);
      ApkUpdateSDK.getInstance().addListener(this.mApkUpdateListener);
      registerListener(paramITMSelfUpdateSDKListener);
      PackageInstallReceiver.a().a(this.mPackageInstallListener);
      PackageInstallReceiver.a().a(this.mContext);
      com.tencent.tmassistantsdk.d.f.a().c();
      return;
    }
  }

  public void onResume(Context paramContext)
  {
    onResume(paramContext, null, true, true);
  }

  protected void onResume(Context paramContext, g paramg, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramg == null);
    for (g localg = new g(); ; localg = paramg)
    {
      if (this.isFromStartUpdate);
      try
      {
        int i = this.mOpenSDKInstance.a();
        if (i == 0)
        {
          localg.a = "";
          localg.f = this.hostPackageName;
          localg.i = this.mChannelId;
          localg.e = this.mUnInstallYYB_via;
          startSaveUpdateToWhere(paramContext, localg, paramBoolean1, paramBoolean2, i);
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        throw localThrowable;
      }
      finally
      {
        this.isFromStartUpdate = false;
      }
    }
  }

  void onStateChanged(int paramInt1, int paramInt2, String paramString)
  {
    Iterator localIterator = this.mWeakListenerArrayList.iterator();
    while (localIterator.hasNext())
    {
      ITMSelfUpdateSDKListener localITMSelfUpdateSDKListener = (ITMSelfUpdateSDKListener)((WeakReference)localIterator.next()).get();
      if (localITMSelfUpdateSDKListener == null)
        com.tencent.tmassistantsdk.g.l.b("SelfUpdateSDK", "onDownloadStateChanged listener = null");
      localITMSelfUpdateSDKListener.OnDownloadAppStateChanged(paramInt1, paramInt2, paramString);
    }
  }

  public void startCommonUpdate()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.hostPackageName);
    isGenApk = true;
    this.updateType = 1;
    ApkUpdateSDK.getInstance().checkUpdate(localArrayList);
  }

  public int startSaveUpdate(Context paramContext)
  {
    this.updateType = 2;
    return startSaveUpdate(paramContext, null, true, true);
  }

  protected int startSaveUpdate(Context paramContext, g paramg, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramContext == null)
      throw new Exception("you must input an application or activity context!");
    if (paramg == null);
    for (g localg = new g(); ; localg = paramg)
    {
      localg.a = "1234";
      localg.f = this.hostPackageName;
      localg.i = this.mChannelId;
      int i = checkYYBInstalled();
      if (i == 0)
      {
        localg.e = this.mInstallYYB_via;
        startSaveUpdateToWhere(paramContext, localg, paramBoolean1, paramBoolean2, i);
        return 0;
      }
      localg.e = this.mUnInstallYYB_via;
      startSaveUpdateToWhere(paramContext, localg, paramBoolean1, paramBoolean2, i);
      this.mSDKHandler.post(new c(this));
      return this.startResult;
    }
  }

  protected void startSaveUpdateToWhere(Context paramContext, g paramg, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    if (paramInt == 0)
    {
      if (paramBoolean1)
      {
        UpdateInfoLog localUpdateInfoLog = i.g().a(paramg.f, this.mAppId);
        localUpdateInfoLog.updateType = 2;
        localUpdateInfoLog.actionCode = 101;
        localUpdateInfoLog.yybExistFlag = 0;
        i.g().a(localUpdateInfoLog);
      }
      this.mOpenSDKInstance.a(paramContext, paramg, paramBoolean1, paramBoolean2);
      return;
    }
    this.mOpenSDKInstance.b(paramg, paramBoolean1, paramBoolean2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.TMSelfUpdateSDK
 * JD-Core Version:    0.6.0
 */