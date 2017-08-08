package com.tencent.tmassistantsdk.openSDK.opensdktomsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tencent.tmassistantsdk.g.k;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.TipsInfoLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class TMOpenSDKToMsdkManager
{
  protected static final String CLIENT_NAME = "downloadSDKClient";
  protected static final String TAG = "OpensdkToMsdkManager";
  protected static TMOpenSDKToMsdkManager mInstance = null;
  protected int authorizedState = 0;
  protected com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a.a dialog = null;
  protected boolean hasNotify = false;
  protected long insertActionId = -1L;
  protected boolean isInstallFinished = false;
  protected TMOpenSDKAuthorizedInfo mAuthorizedInfo = null;
  protected com.tencent.tmassistantsdk.c.c mClient = null;
  protected Context mContext = null;
  protected com.tencent.tmassistantsdk.c.b mDownloadSDKListener = new i(this);
  protected String mDownloadUrl = null;
  protected com.tencent.tmassistantsdk.e.a mHttpRequest = null;
  protected Handler mMainMessageHandler = null;
  protected com.tencent.tmassistantsdk.openSDK.f mOpenSDK = null;
  protected com.tencent.tmassistantsdk.e.e mRequestListener = new b(this);
  protected Handler mSubMessageHandler = null;
  protected HandlerThread mSubMessagehandlerThread = null;
  protected int mSupportVersionCode = 0;
  protected ITMOpenSDKToMsdkListener mToMsdkListener = null;
  protected View.OnClickListener negativeBtnListenner = new e(this);
  protected View.OnClickListener positiveBtnListener = new f(this);
  protected View.OnClickListener retryBtnListener = new d(this);
  protected com.tencent.tmassistantsdk.b.b sdkChannel = null;

  private TMOpenSDKToMsdkManager(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static String about()
  {
    return "TMOpenSDKToMsdkManager_2014_04_01_19_51_release_25434";
  }

  public static TMOpenSDKToMsdkManager getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (mInstance == null)
        mInstance = new TMOpenSDKToMsdkManager(paramContext);
      TMOpenSDKToMsdkManager localTMOpenSDKToMsdkManager = mInstance;
      return localTMOpenSDKToMsdkManager;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void continueDownload()
  {
    this.mSubMessageHandler.post(new c(this));
  }

  protected com.tencent.tmassistantsdk.c.c getClient()
  {
    if (this.mContext != null)
    {
      if (this.mClient == null)
      {
        com.tencent.tmassistantsdk.c.c localc = com.tencent.tmassistantsdk.c.f.a(this.mContext).a("downloadSDKClient");
        localc.a(this.mDownloadSDKListener);
        this.mClient = localc;
        return localc;
      }
      return this.mClient;
    }
    return null;
  }

  public void getUserAuthorizedInfo(TMOpenSDKAuthorizedInfo paramTMOpenSDKAuthorizedInfo, Context paramContext)
  {
    if (paramContext != null)
      this.mContext = paramContext;
    l.b("OpensdkToMsdkManager", "getUserAuthorizedInfo method called!");
    this.hasNotify = false;
    if (this.mHttpRequest != null)
      l.b("OpensdkToMsdkManager", "mHttpRequest != null, request didn't finish!");
    while (true)
    {
      return;
      if (paramTMOpenSDKAuthorizedInfo == null)
        break;
      this.mAuthorizedInfo = paramTMOpenSDKAuthorizedInfo;
      if (this.mHttpRequest != null)
        continue;
      this.authorizedState = 1;
      this.mHttpRequest = new com.tencent.tmassistantsdk.e.a();
      this.mHttpRequest.a(this.mRequestListener);
      this.mHttpRequest.a(paramTMOpenSDKAuthorizedInfo);
      return;
    }
    l.b("OpensdkToMsdkManager", "AuthorizedInfo object is null!");
  }

  protected void handleDownloadContinue(long paramLong1, long paramLong2)
  {
    if (this.mContext == null)
      l.b("OpensdkToMsdkManager", "handleDownloading context = null!");
    k localk;
    do
    {
      return;
      localk = new k(this.mContext);
      String str1 = this.mContext.getString(localk.a("white_list_positive_continue"));
      String str2 = "(" + (int)((0.0F + (float)(100L * paramLong1)) / (float)paramLong2) + "%)";
      this.dialog.i.setText(str1 + str2);
      this.dialog.h.setProgress((int)((0.0F + (float)paramLong1) / (float)paramLong2 * this.dialog.h.getMax()));
    }
    while (this.dialog.h.getProgress() <= 0);
    this.dialog.a(localk.b("com_tencent_tmassistant_sdk_button_bg"));
  }

  protected void handleDownloadFailed()
  {
    if (this.mContext == null)
      l.b("OpensdkToMsdkManager", "handleDownloading context = null!");
    k localk;
    do
    {
      return;
      localk = new k(this.mContext);
      this.dialog.a(true);
      this.dialog.i.setText(this.mContext.getString(localk.a("white_list_positive_retry")));
    }
    while (this.dialog.h.getProgress() <= 0);
    this.dialog.a(localk.b("com_tencent_tmassistant_sdk_button_bg"));
  }

  protected void handleDownloading(long paramLong1, long paramLong2)
  {
    if (this.mContext == null)
    {
      l.b("OpensdkToMsdkManager", "handleDownloading context = null!");
      return;
    }
    k localk = new k(this.mContext);
    String str1 = this.mContext.getString(localk.a("white_list_positive_downloading"));
    String str2 = "(" + (int)((0.0F + (float)(100L * paramLong1)) / (float)paramLong2) + "%)";
    this.dialog.i.setText(str1 + str2);
    this.dialog.h.setProgress((int)((0.0F + (float)paramLong1) / (float)paramLong2 * this.dialog.h.getMax()));
    l.b("OpensdkToMsdkManager", "handleDownloading : receivedlen:" + paramLong1 + " | totalLen:" + paramLong2);
  }

  protected void handleInstall(String paramString, int paramInt)
  {
    if (this.mContext == null);
    do
    {
      return;
      k localk = new k(this.mContext);
      this.dialog.a(true);
      this.dialog.i.setText(this.mContext.getString(localk.a("white_list_positive_install")));
      if (this.mContext == null)
      {
        l.b("OpensdkToMsdkManager", "handleDownloading context = null!");
        return;
      }
      this.isInstallFinished = true;
    }
    while (paramInt == 1);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    this.mContext.startActivity(localIntent);
  }

  public void initOpenSDK(ITMOpenSDKToMsdkListener paramITMOpenSDKToMsdkListener)
  {
    this.mOpenSDK = com.tencent.tmassistantsdk.openSDK.f.b();
    this.mOpenSDK.b(this.mContext);
    this.mToMsdkListener = paramITMOpenSDKToMsdkListener;
    this.sdkChannel = new com.tencent.tmassistantsdk.b.b();
    this.mSubMessagehandlerThread = new HandlerThread("OpenSDKToMsdkManager");
    this.mSubMessagehandlerThread.start();
    this.mSubMessageHandler = new Handler(this.mSubMessagehandlerThread.getLooper());
    this.mMainMessageHandler = new Handler(Looper.getMainLooper(), new a(this));
  }

  protected boolean isExitsAction(long paramLong)
  {
    if (paramLong < 0L)
      return false;
    if (this.sdkChannel == null)
      return false;
    Iterator localIterator = this.sdkChannel.a().iterator();
    while (localIterator.hasNext())
    {
      com.tencent.tmassistantsdk.b.c localc = (com.tencent.tmassistantsdk.b.c)localIterator.next();
      if ((localc.a == paramLong) && (localc.h - localc.g <= 300000L))
        return true;
    }
    return false;
  }

  protected void notifyAuthorizedFinished(boolean paramBoolean, TMOpenSDKAuthorizedInfo paramTMOpenSDKAuthorizedInfo)
  {
    l.b("OpensdkToMsdkManager", "before realy notifyAuthorizedFinished: TMOpenSDKAuthorizedInfo:" + this.mAuthorizedInfo);
    if (this.mToMsdkListener == null)
      l.b("OpensdkToMsdkManager", "before notifyAuthorizedFinished: mToMsdkListener = null !");
    if ((this.mToMsdkListener != null) && (this.mAuthorizedInfo != null))
    {
      l.b("OpensdkToMsdkManager", "notifyAuthorizedFinished: result:" + paramBoolean);
      this.mToMsdkListener.onAuthorizedFinished(paramBoolean, this.mAuthorizedInfo);
      this.mHttpRequest = null;
      this.hasNotify = true;
    }
  }

  public void onDestroy()
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
    if (this.mOpenSDK != null)
      this.mOpenSDK.c();
    this.mToMsdkListener = null;
    this.hasNotify = false;
    this.isInstallFinished = false;
    this.authorizedState = 0;
    this.mHttpRequest = null;
    this.mAuthorizedInfo = null;
    this.sdkChannel = null;
    this.insertActionId = -1L;
    if (this.mContext != null)
      com.tencent.tmassistantsdk.c.f.b(this.mContext);
    this.mClient = null;
  }

  protected void onNetworkException(int paramInt)
  {
    if (this.mContext == null)
      return;
    k localk = new k(this.mContext);
    if (1 == paramInt);
    for (String str1 = this.mContext.getString(localk.a("white_list_network_not_connected")); ; str1 = this.mContext.getString(localk.a("white_list_network_error")))
    {
      com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.c localc = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.c();
      localc.a = this.mContext.getString(localk.a("white_list_dlg_title"));
      localc.b = str1;
      ArrayList localArrayList = new ArrayList();
      com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a locala = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a();
      locala.c = 3;
      localArrayList.add(locala);
      String str2 = this.mContext.getString(localk.a("white_list_negtive_known"));
      locala.a = str2;
      locala.b = str2;
      localc.c = localArrayList;
      showDialog(localc);
      this.mHttpRequest = null;
      this.mDownloadUrl = "";
      this.authorizedState = 3;
      l.b("OpensdkToMsdkManager", "network error happened!");
      TipsInfoLog localTipsInfoLog = com.tencent.tmassistantsdk.d.h.g().a(this.mAuthorizedInfo);
      localTipsInfoLog.networkErrorTipsCount = (1 + localTipsInfoLog.networkErrorTipsCount);
      com.tencent.tmassistantsdk.d.h.g().a(localTipsInfoLog);
      return;
    }
  }

  protected void onNetworkFinishedFailed(int paramInt)
  {
    if ((paramInt == 606) || (paramInt == 602) || (paramInt == 601) || (paramInt == 704))
    {
      onServerException(paramInt);
      return;
    }
    onNetworkException(paramInt);
  }

  protected void onNetworkFinishedSuccess(com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.b paramb)
  {
    this.mDownloadUrl = paramb.d;
    this.mSupportVersionCode = paramb.e;
    this.mHttpRequest = null;
    l.b("OpensdkToMsdkManager", "onNetworkFinishedSuccess! authorizedResult.hasAuthoried=" + paramb.a + "  listenner:" + this.mToMsdkListener + "  authroizedinfo:" + this.mAuthorizedInfo);
    if (paramb.a == 1)
    {
      this.authorizedState = 2;
      if (paramb.b != null)
      {
        showDialog(paramb.b);
        return;
      }
      notifyAuthorizedFinished(true, this.mAuthorizedInfo);
      return;
    }
    this.authorizedState = 3;
    if (paramb.b != null)
    {
      showDialog(paramb.b);
      return;
    }
    onServerException(paramb.c);
    l.b("OpensdkToMsdkManager", "not in white list and no tips!");
  }

  public void onResume()
  {
    tryToCloseDialog();
    if (this.hasNotify);
    do
      return;
    while ((this.authorizedState == 2) || ((this.authorizedState != 3) && (this.authorizedState != 0)) || (this.mContext == null));
    getUserAuthorizedInfo(this.mAuthorizedInfo, this.mContext);
  }

  protected void onServerException(int paramInt)
  {
    if (this.mContext == null)
      return;
    k localk = new k(this.mContext);
    com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.c localc = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.c();
    localc.a = this.mContext.getString(localk.a("white_list_dlg_title"));
    if ((paramInt == 602) || (paramInt == 601));
    for (localc.b = this.mContext.getString(localk.a("white_list_network_error")); ; localc.b = this.mContext.getString(localk.a("white_list_server_error")))
    {
      ArrayList localArrayList = new ArrayList();
      com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a locala1 = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a();
      locala1.c = 4;
      String str1 = this.mContext.getString(localk.a("white_list_positive_retry_again"));
      locala1.a = str1;
      locala1.b = str1;
      com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a locala2 = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a();
      locala2.c = 3;
      String str2 = this.mContext.getString(localk.a("white_list_negtive"));
      locala2.a = str2;
      locala2.b = str2;
      localArrayList.add(locala1);
      localArrayList.add(locala2);
      localc.c = localArrayList;
      showDialog(localc);
      this.mHttpRequest = null;
      this.mDownloadUrl = "";
      this.authorizedState = 3;
      TipsInfoLog localTipsInfoLog = com.tencent.tmassistantsdk.d.h.g().a(this.mAuthorizedInfo);
      localTipsInfoLog.networkErrorTipsCount = (1 + localTipsInfoLog.networkErrorTipsCount);
      com.tencent.tmassistantsdk.d.h.g().a(localTipsInfoLog);
      return;
    }
  }

  protected void pauseDownloadTask(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    do
      return;
    while (this.mAuthorizedInfo == null);
    this.mSubMessageHandler.post(new h(this, paramString));
  }

  protected void showDialog(com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.c paramc)
  {
    if (this.mContext == null);
    k localk;
    ArrayList localArrayList;
    do
    {
      do
      {
        do
          return;
        while ((this.dialog != null) && (this.dialog.isShowing()));
        if (((this.mContext instanceof Activity)) && (((Activity)this.mContext).isFinishing()))
        {
          l.b("OpensdkToMsdkManager", "context is finishing!  context" + this.mContext);
          return;
        }
        localk = new k(this.mContext);
      }
      while (paramc == null);
      localArrayList = paramc.c;
    }
    while ((localArrayList == null) || (localArrayList.size() <= 0));
    this.dialog = new com.tencent.tmassistantsdk.openSDK.opensdktomsdk.a.a(this.mContext, localk.e("while_list_dialog"), localArrayList.size());
    this.dialog.show();
    this.dialog.a(paramc.a);
    this.dialog.b(paramc.b);
    int i = 0;
    label167: com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a locala;
    if (i < localArrayList.size())
    {
      locala = (com.tencent.tmassistantsdk.openSDK.opensdktomsdk.b.a)localArrayList.get(i);
      if (locala.c != 3)
        break label225;
      this.dialog.d(locala.a);
      this.dialog.b(this.negativeBtnListenner);
    }
    while (true)
    {
      i++;
      break label167;
      break;
      label225: if (locala.c == 4)
      {
        this.dialog.c(locala.b);
        this.dialog.a(this.retryBtnListener);
        continue;
      }
      try
      {
        int j = this.mOpenSDK.a(this.mSupportVersionCode);
        switch (j)
        {
        default:
        case 0:
          while (true)
          {
            this.dialog.a(locala);
            this.dialog.a(this.positiveBtnListener);
            break;
            this.dialog.c(locala.a);
            TipsInfoLog localTipsInfoLog = com.tencent.tmassistantsdk.d.h.g().a(this.mAuthorizedInfo);
            localTipsInfoLog.authorizedTipsCount = (1 + localTipsInfoLog.authorizedTipsCount);
            com.tencent.tmassistantsdk.d.h.g().a(localTipsInfoLog);
          }
        case 2:
        case 1:
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          continue;
          this.dialog.c(this.mContext.getString(localk.a("white_list_positive_update")));
          continueDownload();
          continue;
          this.dialog.c(locala.b);
          continueDownload();
        }
      }
    }
  }

  protected void startDownloadTask(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    this.dialog.a(false);
    this.mSubMessageHandler.post(new g(this, paramString2, paramString1));
  }

  protected void startToQQDownloaderAuthorized(String paramString)
  {
    if (this.dialog.isShowing())
      this.dialog.dismiss();
    this.mHttpRequest = null;
    this.isInstallFinished = false;
    if (TextUtils.isEmpty(paramString));
    do
    {
      return;
      TipsInfoLog localTipsInfoLog = com.tencent.tmassistantsdk.d.h.g().a(this.mAuthorizedInfo);
      localTipsInfoLog.authorizedBtnClickCount = (1 + localTipsInfoLog.authorizedBtnClickCount);
      com.tencent.tmassistantsdk.d.h.g().a(localTipsInfoLog);
    }
    while ((this.mContext == null) || (this.mOpenSDK == null));
    this.mOpenSDK.a(this.mContext, paramString);
  }

  protected void tryToCloseDialog()
  {
    if (this.mOpenSDK == null);
    while (true)
    {
      return;
      if (this.mContext == null)
        continue;
      try
      {
        int i = this.mOpenSDK.a(this.mSupportVersionCode);
        if ((!this.isInstallFinished) || (i != 0))
          continue;
        this.mHttpRequest = null;
        this.isInstallFinished = false;
        this.mDownloadUrl = null;
        if (this.dialog.isShowing())
          this.dialog.dismiss();
        com.tencent.tmassistantsdk.c.f.b(this.mContext);
        this.mClient = null;
        if (!isExitsAction(this.insertActionId))
          continue;
        this.mOpenSDK.a(this.mContext);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.opensdktomsdk.TMOpenSDKToMsdkManager
 * JD-Core Version:    0.6.0
 */