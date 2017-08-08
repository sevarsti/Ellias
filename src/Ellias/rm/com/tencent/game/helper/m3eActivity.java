package com.tencent.game.helper;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import android.widget.ViewFlipper;
import com.tencent.android.tpush.XGBasicPushNotificationBuilder;
import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.MsdkBaseInfo;
import com.tencent.msdk.api.ShareRet;
import com.tencent.msdk.api.TokenRet;
import com.tencent.msdk.api.WGPlatform;
import com.tencent.msdk.api.WGPlatformObserver;
import com.tencent.msdk.api.WakeupRet;
import com.tencent.msdk.api.eQQScene;
import com.tencent.msdk.api.eWechatScene;
import com.tencent.msdk.consts.EPlatform;
import com.tencent.msdk.notice.eMSG_NOTICETYPE;
import com.tencent.msdk.qq.ApiName;
import com.tencent.msdk.remote.api.RelationRet;
import com.tencent.msdk.tools.CommonUtil;
import com.tencent.msdk.tools.Logger;
import com.tencent.qqgamemi.QmiSdkApi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

public class m3eActivity extends Activity
{
  public static final int FIRST_REQUEST_CODE = 1;
  private static final int HANDLER_BEGIN_DOWNLOAD = 6;
  private static final int HANDLER_CALL_PAY = 16;
  private static final int HANDLER_HIDE_PROGRESS = 4;
  private static final int HANDLER_LAUNCHMPSAVECURRENCYVIEW = 17;
  private static final int HANDLER_LAUNCHOPENSERVICEVIEW = 18;
  private static final int HANDLER_OPEN_PLAYMOVIE_ACTIVE = 5;
  private static final int HANDLER_PAYFORMONEY = 19;
  private static final int HANDLER_SHAKE_PHONE = 7;
  private static final int HANDLER_SHOWNOVICE = 15;
  private static final int HANDLER_SHOW_DIALOG = 1;
  private static final int HANDLER_SHOW_DIALOG_NO = 2;
  private static final int HANDLER_SHOW_PROGRESS = 3;
  public static String S_LOGTAG = "m3e";
  public static String S_NULL = "null";
  private static Context context;
  public static Handler handler;
  public static m3eGLSurfaceView mGLView;
  private static ProgressBar mProgressBar;
  private static m3eRenderer mRenderer;
  public static boolean m_bShowLoading;
  private static long m_nWXTokenExpiration;
  private static VideoView m_videoView;
  private static PowerManager.WakeLock m_wakeLock;
  public static boolean s_isPlayMovieFinish;
  public static boolean s_isPogressing;
  public static m3eActivity s_m3eActivity;
  public static int s_screenH;
  public static int s_screenW;
  private static Bitmap s_shotScreenBitmap;
  public static boolean s_showPlayMovie;
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
  private boolean bFirstLaunchResume = true;
  private boolean bFirstLaunchStart = true;
  private boolean bNovice = false;
  private int[] imgs = { 2130837569, 2130837570, 2130837571, 2130837572, 2130837573 };
  public Handler mHandler;
  private RelativeLayout mRootLayout;
  private boolean mVolumeKeyDown = false;
  private boolean mVolumeKeyUp = false;
  public long m_lastShowLoadingTime;
  private ViewFlipper viewFlipper = null;

  static
  {
    mProgressBar = null;
    s_isPogressing = false;
    s_isPlayMovieFinish = false;
    s_showPlayMovie = false;
    m_nWXTokenExpiration = 0L;
    Log.e("leofang", "lib NativeRQD WeGame beging");
    Log.e("leofang", "lib NativeRQD WeGame end");
  }

  public static void AutoLogin()
  {
    Log.e("AutoLogin", "AutoLogin");
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    int i = localLoginRet.platform;
    if (WeGame.WXPLATID == i)
      WXLogin();
    do
      return;
    while (WeGame.QQPLATID != i);
    QQLogin();
  }

  public static int CheckNetworkType()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)s_m3eActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null)
    {
      if (localNetworkInfo.getType() == 1)
        return 1;
      if (localNetworkInfo.getType() == 0)
        return 2;
    }
    else
    {
      return 0;
    }
    return 4;
  }

  public static void ClearXGLocalNotification()
  {
    XGPushManager.clearLocalNotifications(s_m3eActivity.getApplicationContext());
  }

  public static void DeleteXGTag(String paramString)
  {
    XGPushManager.deleteTag(s_m3eActivity.getApplicationContext(), paramString);
  }

  public static void DismissProgress()
  {
    if (handler == null)
      return;
    Message localMessage = new Message();
    localMessage.what = 4;
    handler.sendMessage(localMessage);
    s_m3eActivity.m_lastShowLoadingTime = 0L;
    m_bShowLoading = false;
  }

  public static void ExitGame()
  {
    s_m3eActivity.finish();
  }

  public static boolean Fling(int paramInt1, int paramInt2)
  {
    int i = 1;
    if (!s_m3eActivity.bNovice)
      i = 0;
    while (true)
    {
      return i;
      Log.d("onFling", "onFling");
      int j = s_m3eActivity.viewFlipper.indexOfChild(s_m3eActivity.viewFlipper.getCurrentView());
      if (paramInt2 - paramInt1 > 120)
      {
        if (j == 0)
          continue;
        Animation localAnimation3 = AnimationUtils.loadAnimation(s_m3eActivity, 2130968593);
        Animation localAnimation4 = AnimationUtils.loadAnimation(s_m3eActivity, 2130968594);
        s_m3eActivity.viewFlipper.setInAnimation(localAnimation3);
        s_m3eActivity.viewFlipper.setOutAnimation(localAnimation4);
        s_m3eActivity.viewFlipper.showPrevious();
        return i;
      }
      if (paramInt2 - paramInt1 >= -120)
        break;
      if (j == 4)
        continue;
      Animation localAnimation1 = AnimationUtils.loadAnimation(s_m3eActivity, 2130968591);
      Animation localAnimation2 = AnimationUtils.loadAnimation(s_m3eActivity, 2130968592);
      s_m3eActivity.viewFlipper.setInAnimation(localAnimation1);
      s_m3eActivity.viewFlipper.setOutAnimation(localAnimation2);
      s_m3eActivity.viewFlipper.showNext();
      return i;
    }
    Touch();
    return i;
  }

  public static String GetDeviceName()
  {
    Log.e("android.os.Build.MODEL", Build.MODEL);
    return Build.MODEL;
  }

  public static boolean GetIsShowLoading()
  {
    return m_bShowLoading;
  }

  public static long GetLastShowLoadingTime()
  {
    return s_m3eActivity.m_lastShowLoadingTime;
  }

  public static String GetLocalMacAddress()
  {
    WifiInfo localWifiInfo = ((WifiManager)s_m3eActivity.getSystemService("wifi")).getConnectionInfo();
    Log.e("GetLocalMacAddress", localWifiInfo.getMacAddress());
    return localWifiInfo.getMacAddress();
  }

  public static boolean IsPlayMovieFinish()
  {
    return s_isPlayMovieFinish;
  }

  public static void LaunchMPSaveCurrencyView(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (handler == null)
      return;
    Log.d(S_LOGTAG, "LaunchMPSaveCurrencyView(String discountType,  String discountUrl)");
    Log.d(S_LOGTAG, "discountType: " + paramString3);
    Log.d(S_LOGTAG, "discountUrl: " + paramString4);
    Message localMessage = new Message();
    localMessage.what = 17;
    localMessage.obj = new PayMessage2(paramString1, paramString2, paramString3, paramString4);
    handler.sendMessage(localMessage);
  }

  public static void LaunchOpenServiceView(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt)
  {
    if (handler == null)
      return;
    Log.d(S_LOGTAG, "LaunchOpenServiceView：userId：" + paramString1 + " userKey：" + paramString2 + " sessionId：" + paramString3 + " sessionType：" + paramString4 + " pf：" + paramString5 + " pfKey:" + paramString6 + "type:" + paramInt);
    Message localMessage = new Message();
    localMessage.what = 18;
    localMessage.obj = new OpenServiceMessage(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramInt);
    handler.sendMessage(localMessage);
  }

  public static void LaunchPay(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (handler == null)
      return;
    Log.d("m3ePay", "LaunchPay");
    Message localMessage = new Message();
    localMessage.what = 16;
    localMessage.obj = new PayMessage(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
    handler.sendMessage(localMessage);
  }

  public static void OpenUrl(String paramString)
  {
    Log.v("soccer", paramString);
    WGPlatform.WGOpenUrl(paramString);
  }

  public static void PayForMoney(int paramInt1, String paramString, int paramInt2)
  {
    Message localMessage = new Message();
    if (paramInt2 == 4)
    {
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      String str1 = "";
      String str2 = "";
      if (WeGame.WXPLATID == localLoginRet.platform)
      {
        str1 = "hy_gameid";
        str2 = "wc_actoken";
      }
      while (true)
      {
        LaunchOpenServiceView(localLoginRet.open_id, ((TokenRet)localLoginRet.token.elementAt(0)).value, str1, str2, paramString, WGPlatform.WGGetPfKey(), paramInt2);
        return;
        if (WeGame.QQPLATID != localLoginRet.platform)
          continue;
        str1 = "openid";
        str2 = "kp_actoken";
      }
    }
    localMessage.what = 19;
    localMessage.obj = new PayMoneyMessage(paramInt1, paramString, paramInt2);
    handler.sendMessage(localMessage);
  }

  public static void PlayMovie()
  {
    if (handler == null)
      return;
    Log.d(S_LOGTAG, "m3eActivity::PlayMovie()");
    Message localMessage = new Message();
    localMessage.what = 5;
    handler.sendMessage(localMessage);
  }

  public static void QQLogin()
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    int i = localLoginRet.platform;
    Log.e("qqlogin nPlatform: ", String.valueOf(i));
    if (i == 0)
    {
      Log.e("qqlogin", "noplatform");
      WGPlatform.WGLogin(EPlatform.ePlatform_QQ);
      return;
    }
    if (WeGame.QQPLATID == i)
    {
      Log.e("qqlogin", "hasplatform");
      Log.d("QQLogin", "token.size=" + localLoginRet.token.size());
      if ((1006 != localLoginRet.flag) && (1007 != localLoginRet.flag) && (localLoginRet.open_id.length() > 0) && (localLoginRet.token.size() > 0))
      {
        String str1 = localLoginRet.getTokenByType(1);
        String str2 = localLoginRet.getTokenByType(2);
        nativeWXQQLogin(localLoginRet.open_id.length(), localLoginRet.open_id, str1.length(), str1, localLoginRet.pf.length(), localLoginRet.pf, i, str2.length(), str2);
        return;
      }
      WXQQLogout();
      return;
    }
    WXQQLogout();
    Log.e("qqlogin", "WXPlatform change To QQPlatform");
    WGPlatform.WGLogin(EPlatform.ePlatform_QQ);
  }

  public static void RemovePlayMovie()
  {
  }

  public static void SaveShotSceen(Bitmap paramBitmap)
  {
    s_shotScreenBitmap = paramBitmap;
  }

  public static void SetXGLocalNotification(int paramInt, String paramString)
  {
    try
    {
      str = new String("节奏大师".getBytes("UTF-8"), "UTF-8");
      XGLocalMessage localXGLocalMessage = new XGLocalMessage();
      localXGLocalMessage.setTitle(str);
      Date localDate = new Date();
      localDate.setTime(localDate.getTime() + paramInt * 1000);
      localXGLocalMessage.setDate(sdf.format(localDate));
      localXGLocalMessage.setHour("" + localDate.getHours());
      StringBuilder localStringBuilder = new StringBuilder().append("");
      int i = localDate.getMinutes();
      if (localDate.getSeconds() > 10)
      {
        j = 1;
        localXGLocalMessage.setMin(j + i);
        Log.d("LocalMessage", "date=" + localXGLocalMessage.getDate() + "hour=" + localXGLocalMessage.getHour() + "minute=" + localXGLocalMessage.getMin());
        localXGLocalMessage.setContent(paramString);
        XGPushManager.addLocalNotification(s_m3eActivity.getApplicationContext(), localXGLocalMessage);
        return;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        String str = "节奏大师";
        continue;
        int j = 0;
      }
    }
  }

  public static void SetXGTag(String paramString)
  {
    XGPushManager.setTag(s_m3eActivity.getApplicationContext(), paramString);
  }

  public static void ShakeIphone()
  {
    if (handler == null)
      return;
    Log.d(S_LOGTAG, "m3eActivity::ShakeIphone()");
    Message localMessage = new Message();
    localMessage.what = 7;
    handler.sendMessage(localMessage);
  }

  public static void ShowNovice()
  {
    Message localMessage = new Message();
    localMessage.what = 15;
    handler.sendMessage(localMessage);
    Log.e("m3e", "m3eActivity::ShowNovice");
  }

  public static void ShowProgress()
  {
    if (handler == null)
      return;
    Message localMessage = new Message();
    localMessage.what = 3;
    handler.sendMessage(localMessage);
    s_m3eActivity.m_lastShowLoadingTime = System.currentTimeMillis();
    m_bShowLoading = true;
  }

  public static void Touch()
  {
    if (!s_m3eActivity.bNovice)
      return;
    if (s_m3eActivity.viewFlipper.indexOfChild(s_m3eActivity.viewFlipper.getCurrentView()) == 4)
    {
      s_m3eActivity.bNovice = false;
      nativeSetNovice();
      s_m3eActivity.viewFlipper.setVisibility(8);
      return;
    }
    Animation localAnimation1 = AnimationUtils.loadAnimation(s_m3eActivity, 2130968591);
    Animation localAnimation2 = AnimationUtils.loadAnimation(s_m3eActivity, 2130968592);
    s_m3eActivity.viewFlipper.setInAnimation(localAnimation1);
    s_m3eActivity.viewFlipper.setOutAnimation(localAnimation2);
    s_m3eActivity.viewFlipper.showNext();
  }

  public static int WGGetLoginPlatform()
  {
    return WGPlatform.WGGetLoginRecord(new LoginRet());
  }

  public static String WGGetPayChannelID()
  {
    Log.e("leofang", "WGPlatform.WGGetChannelId():" + WGPlatform.WGGetChannelId());
    return WGPlatform.WGGetChannelId();
  }

  public static void WGHideScrollNotice()
  {
    WGPlatform.WGHideScrollNotice();
  }

  public static int WGIsPlatformInstalled(int paramInt)
  {
    if (WGPlatform.WGIsPlatformInstalled(EPlatform.getEnum(paramInt)))
      return 1;
    return 0;
  }

  public static int WGIsSupportQQWithPhoto()
  {
    boolean bool = WGPlatform.WGCheckApiSupport(ApiName.WGSendToQQWithPhoto);
    if (bool);
    for (String str = "ret: true"; ; str = "ret: false")
    {
      Log.e("WGIsSupportQQWithPhoto", str);
      if (!bool)
        break;
      return 1;
    }
    return 0;
  }

  public static int WGIsSupportShareWithPhoto(int paramInt)
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    int i = localLoginRet.platform;
    if (WeGame.QQPLATID == i)
      if ((!WGPlatform.WGIsPlatformInstalled(EPlatform.ePlatform_QQ)) || (!WGPlatform.WGCheckApiSupport(ApiName.WGSendToQQWithPhoto)));
    while (true)
    {
      return 1;
      return 0;
      if (WeGame.WXPLATID != i)
        break;
      if (!WGPlatform.WGIsPlatformInstalled(EPlatform.ePlatform_Weixin))
        return 0;
    }
    return 0;
  }

  public static void WGRefreshWXToken()
  {
    Log.e("leofang", "RefreshWXToken");
    if (300000L + System.currentTimeMillis() > m_nWXTokenExpiration)
      WGPlatform.WGRefreshWXToken();
  }

  public static void WGShareWithContent(int paramInt, String paramString1, String paramString2)
  {
    String str1 = new String(paramString1);
    String str2 = new String(paramString2);
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    int i = localLoginRet.platform;
    if (WeGame.WXPLATID == i)
    {
      Bitmap localBitmap = BitmapFactory.decodeResource(s_m3eActivity.getResources(), 2130837541);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localBitmap.compress(Bitmap.CompressFormat.JPEG, 90, localByteArrayOutputStream);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      Log.d("imgData.toByteArray().length", "imgData.toByteArray().length: " + arrayOfByte.length);
      WGPlatform.WGSendToWeixin("节奏大师", str1, "MSG_INVITE", arrayOfByte, arrayOfByte.length, "");
    }
    do
      return;
    while (WeGame.QQPLATID != i);
    if (paramInt == 0);
    for (eQQScene localeQQScene = eQQScene.QQScene_Session; ; localeQQScene = eQQScene.QQScene_QZone)
    {
      WGPlatform.WGSendToQQ(localeQQScene, "节奏大师", str1, str2, "http://game.ds.qq.com/res/shareicon/invite.jpg", "http://game.ds.qq.com/res/shareicon/invite.jpg".length());
      return;
    }
  }

  public static void WGShareWithPhoto(int paramInt)
  {
    if (s_shotScreenBitmap == null)
      Log.d("WGShareWithPhoto", "s_shotScreenBitmap == null");
    int i;
    do
    {
      return;
      LoginRet localLoginRet = new LoginRet();
      WGPlatform.WGGetLoginRecord(localLoginRet);
      i = localLoginRet.platform;
      if (WeGame.WXPLATID != i)
        continue;
      byte[] arrayOfByte = CommonUtil.bitmap2Bytes(s_shotScreenBitmap);
      if (paramInt == 0);
      for (eWechatScene localeWechatScene = eWechatScene.WechatScene_Session; ; localeWechatScene = eWechatScene.WechatScene_Timeline)
      {
        WGPlatform.WGSendToWeixinWithPhoto(localeWechatScene, "MSG_INVITE", arrayOfByte, arrayOfByte.length);
        Log.d("WGShareWithPhoto", "imgData.toByteArray().length: " + arrayOfByte.length);
        return;
      }
    }
    while (WeGame.QQPLATID != i);
    String str = m3eFileHelper.getInstance().getUserDirectory();
    m3eFileHelper.getInstance().createDir(str + "share/");
    File localFile = new File(str + "share/" + "share.png");
    if (!localFile.exists());
    try
    {
      localFile.createNewFile();
    }
    catch (IOException localIOException)
    {
      try
      {
        while (true)
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          s_shotScreenBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
          if (paramInt == 0);
          for (eQQScene localeQQScene = eQQScene.QQScene_Session; ; localeQQScene = eQQScene.QQScene_QZone)
          {
            WGPlatform.WGSendToQQWithPhoto(localeQQScene, str + "share/" + "share.png");
            return;
          }
          localIOException = localIOException;
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  public static void WGShotScreen()
  {
    m3eRenderer.isShotScreen = true;
  }

  public static void WGShowNotice(int paramInt, String paramString)
  {
    eMSG_NOTICETYPE localeMSG_NOTICETYPE = eMSG_NOTICETYPE.getEnum(paramInt);
    Log.v("soccer ", paramString + localeMSG_NOTICETYPE);
    WGPlatform.WGShowNotice(localeMSG_NOTICETYPE, paramString);
  }

  public static void WXLogin()
  {
    LoginRet localLoginRet = new LoginRet();
    int i = WGPlatform.WGGetLoginRecord(localLoginRet);
    if (i == 0)
    {
      Log.e("wxlogin1", "noplatform");
      WGPlatform.WGLogin(EPlatform.ePlatform_Weixin);
      return;
    }
    if (i == WeGame.WXPLATID)
    {
      Log.d("WXLogin", "token.size=" + localLoginRet.token.size());
      if ((2007 != localLoginRet.flag) && (2008 != localLoginRet.flag) && (localLoginRet.open_id.length() > 0) && (localLoginRet.token.size() > 0))
      {
        String str = localLoginRet.getTokenByType(3);
        nativeWXQQLogin(localLoginRet.open_id.length(), localLoginRet.open_id, str.length(), str, localLoginRet.pf.length(), localLoginRet.pf, i, str.length(), str);
        return;
      }
      WXQQLogout();
      return;
    }
    WXQQLogout();
    Log.e("wxlogin1", "QQPlatform change To WXPlatform");
    WGPlatform.WGLogin(EPlatform.ePlatform_Weixin);
  }

  public static void WXQQLogout()
  {
    WGPlatform.WGLogout();
  }

  public static int getAPILevel()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (Throwable localThrowable)
    {
      Log.e(S_LOGTAG, localThrowable.toString());
    }
    return -1;
  }

  public static String getPF()
  {
    return WGPlatform.WGGetPf("game_custom_data");
  }

  public static void gotoURL(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    s_m3eActivity.startActivity(localIntent);
  }

  private void initNotificationBuilder(Context paramContext)
  {
    XGBasicPushNotificationBuilder localXGBasicPushNotificationBuilder = new XGBasicPushNotificationBuilder();
    localXGBasicPushNotificationBuilder.setIcon(2130837540);
    XGPushManager.setPushNotificationBuilder(this, 1, localXGBasicPushNotificationBuilder);
  }

  public static native void nativeReEnter(int paramInt);

  public static native void nativeSetNovice();

  public static native void nativeShotscreen(int paramInt, byte[] paramArrayOfByte);

  public static native void nativeWXQQLogin(int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3, String paramString3, int paramInt4, int paramInt5, String paramString4);

  public static native void nativeWXQQOnWakeup(int paramInt1, String paramString, int paramInt2);

  private void showDialog(String paramString1, String paramString2, String paramString3)
  {
    Log.e("m3e", "showDialog");
    new AlertDialog.Builder(this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        m3eActivity.this.nativeAlertListener(0, 1);
      }
    }).create().show();
  }

  private void showDialogYN(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.e("m3e", "showDialogYN");
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setPositiveButton(paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        m3eActivity.this.nativeAlertListener(1, 0);
      }
    });
    localBuilder.setNegativeButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        m3eActivity.this.nativeAlertListener(0, 0);
      }
    });
    localBuilder.create();
    localBuilder.show();
  }

  public static void showMessageBox(String paramString1, String paramString2, String paramString3)
  {
    if (handler == null)
      return;
    Message localMessage = new Message();
    localMessage.what = 1;
    localMessage.obj = new DialogMessage(paramString1, paramString2, paramString3);
    handler.sendMessage(localMessage);
  }

  public static void showMessageBoxYN(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (handler == null)
      return;
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = new DialogMessage(paramString1, paramString2, paramString3, paramString4);
    handler.sendMessage(localMessage);
  }

  public String getDeviceSpecShort()
  {
    String str1 = "";
    String str2;
    try
    {
      str1 = str1 + Build.MODEL;
      str1 = str1 + Build.VERSION.RELEASE;
      String str3 = str1 + Build.VERSION.SDK_INT;
      return str3;
    }
    catch (Throwable localThrowable)
    {
      str2 = str1;
      Log.e(S_LOGTAG, localThrowable.toString());
    }
    return str2;
  }

  public void initNovice()
  {
    this.bNovice = true;
    this.viewFlipper = ((ViewFlipper)findViewById(2131165244));
    for (int i = 0; i < this.imgs.length; i++)
    {
      ImageView localImageView = new ImageView(this);
      localImageView.setImageResource(this.imgs[i]);
      localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
      this.viewFlipper.addView(localImageView, new ViewGroup.LayoutParams(-1, -1));
    }
    this.viewFlipper.setAutoStart(false);
    this.viewFlipper.setFlipInterval(3000);
  }

  public native void nativeAlertListener(int paramInt1, int paramInt2);

  public native void nativeDisableNativeRqd();

  public native void nativeDone();

  public native void nativeDownloadBegin(int paramInt);

  public native void nativeDownloadError(String paramString);

  public native void nativeDownloadFinish(String paramString);

  public native void nativeDownloadUpdate(int paramInt);

  public native byte[] nativeGetString(String paramString);

  public native void nativeInit(int paramInt1, int paramInt2);

  public native void nativeOnRefreshWXToken(String paramString);

  public native void nativePause();

  public native void nativeResume();

  public native void nativeSendInterSign();

  public native void nativeSetScreenWH(int paramInt1, int paramInt2);

  public native void nativeShowFPS();

  public native void nativeStart();

  public native void nativeStop();

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == 1))
    {
      Log.d(S_LOGTAG, "onActivityResult()");
      s_isPlayMovieFinish = true;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    Log.e("m3e", "m3eActivity::onCreate");
    super.onCreate(paramBundle);
    if (WGPlatform.IsDifferentActivity(this).booleanValue())
    {
      Log.d("m3eActivity", "Warning!Reduplicate game activity was detected.Activity will finish immediately.");
      finish();
      return;
    }
    setContentView(2130903050);
    MsdkBaseInfo localMsdkBaseInfo = new MsdkBaseInfo();
    localMsdkBaseInfo.qqAppId = "100666228";
    localMsdkBaseInfo.qqAppKey = "deb6b25a8def2402eb2177b05a462cc2";
    localMsdkBaseInfo.wxAppId = "wx6f15c6c03a84433d";
    localMsdkBaseInfo.wxAppKey = "bf159627552fa6bc8473d492c5b3e06d";
    localMsdkBaseInfo.offerId = "1450000581";
    WGPlatform.Initialized(this, localMsdkBaseInfo);
    WGPlatform.WGSetPermission(8388710);
    Log.d("m3eActivity version", WGPlatform.WGGetVersion());
    s_m3eActivity = this;
    m3eFileHelper.getInstance().setContext(this);
    m3eFontHelper.getInstance().setContext(this);
    m3eHttpHelper.getInstance().setActivity(this);
    m3eHttpDownload.getInstance().setActivity(this);
    m3eMusic.getInstance().setContext(this);
    m3eSound.getInstance().setContext(this);
    m3ePay.getInstance().init(this);
    this.mRootLayout = ((RelativeLayout)findViewById(2131165241));
    mGLView = (m3eGLSurfaceView)findViewById(2131165243);
    mGLView.setTextField((EditText)findViewById(2131165242));
    XGPushManager.registerPush(getApplicationContext());
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    Log.d("m3eActivity:onCreate", "dm.widthPixels=" + localDisplayMetrics.widthPixels + ", dm.heightPixels=" + localDisplayMetrics.heightPixels);
    s_screenW = localDisplayMetrics.widthPixels;
    s_screenH = localDisplayMetrics.heightPixels;
    if (localDisplayMetrics.widthPixels < localDisplayMetrics.heightPixels)
    {
      s_screenW = localDisplayMetrics.heightPixels;
      s_screenH = localDisplayMetrics.widthPixels;
    }
    nativeSetScreenWH(s_screenW, s_screenH);
    mProgressBar = (ProgressBar)findViewById(16842752);
    mProgressBar.setVisibility(4);
    m3eActivity localm3eActivity = s_m3eActivity;
    ((PowerManager)localm3eActivity.getSystemService("power"));
    handler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        super.handleMessage(paramMessage);
        switch (paramMessage.what)
        {
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        default:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      return;
                      m3eActivity.this.showDialog(((m3eActivity.DialogMessage)paramMessage.obj).titile, ((m3eActivity.DialogMessage)paramMessage.obj).message, ((m3eActivity.DialogMessage)paramMessage.obj).button);
                      return;
                      m3eActivity.this.showDialogYN(((m3eActivity.DialogMessage)paramMessage.obj).titile, ((m3eActivity.DialogMessage)paramMessage.obj).message, ((m3eActivity.DialogMessage)paramMessage.obj).button, ((m3eActivity.DialogMessage)paramMessage.obj).buttonNo);
                      return;
                    }
                    while (m3eActivity.mProgressBar.isShown());
                    Log.e("m3e", "mProgressBar, HANDLER_SHOW_PROGRESS");
                  }
                  while ((m3eActivity.mProgressBar == null) || (m3eActivity.mProgressBar.isShown()));
                  m3eActivity.s_isPogressing = true;
                  m3eActivity.mProgressBar.setVisibility(0);
                  m3eActivity.mProgressBar.getParent().requestTransparentRegion(m3eActivity.mGLView);
                  return;
                }
                while (m3eActivity.mProgressBar.isShown() != true);
                Log.e("m3e", "mProgressBar, HANDLER_HIDE_PROGRESS");
              }
              while ((m3eActivity.mProgressBar == null) || (m3eActivity.mProgressBar.isShown() != true));
              m3eActivity.s_isPogressing = false;
              m3eActivity.mProgressBar.setVisibility(4);
              m3eActivity.mProgressBar.getParent().requestTransparentRegion(m3eActivity.mGLView);
              return;
            }
            while (m3eActivity.s_showPlayMovie);
            m3eActivity.s_showPlayMovie = true;
            Log.e("m3e", "mProgressBar, HANDLER_OPEN_PLAYMOVIE_ACTIVE");
            Intent localIntent = new Intent(m3eActivity.s_m3eActivity, playerMovieActivity.class);
            m3eActivity.s_m3eActivity.startActivityForResult(localIntent, 1);
            return;
            new m3eHttpDownloadAsync().execute(new String[0]);
            return;
          }
          while (m3eActivity.s_m3eActivity.getSystemService("vibrator") == null);
          ((Vibrator)m3eActivity.s_m3eActivity.getSystemService("vibrator")).vibrate(800L);
          return;
        case 15:
          m3eActivity.this.initNovice();
          return;
        case 16:
          m3eActivity.PayMessage localPayMessage = (m3eActivity.PayMessage)paramMessage.obj;
          Log.d("m3ePay", "HANDLER_CALL_PAY");
          m3ePay.getInstance().LaunchPayView(localPayMessage.userId, localPayMessage.userKey, localPayMessage.pf, localPayMessage.sessionId, localPayMessage.sessionType, localPayMessage.value, localPayMessage.moneyType);
          return;
        case 17:
          m3eActivity.PayMessage2 localPayMessage2 = (m3eActivity.PayMessage2)paramMessage.obj;
          Log.d("m3ePay", "HANDLER_LAUNCHMPSAVECURRENCYVIEW");
          m3ePay.getInstance().LaunchMPSaveCurrencyView(localPayMessage2.userId, localPayMessage2.pf, localPayMessage2.discountType, localPayMessage2.discountUrl);
          return;
        case 18:
          m3eActivity.OpenServiceMessage localOpenServiceMessage = (m3eActivity.OpenServiceMessage)paramMessage.obj;
          Log.d("m3ePay", "HANDLER_LAUNCHOPENSERVICEVIEW");
          m3ePay.getInstance().LaunchOpenServiceView(localOpenServiceMessage.userId, localOpenServiceMessage.userKey, localOpenServiceMessage.sessionId, localOpenServiceMessage.sessionType, localOpenServiceMessage.pf, localOpenServiceMessage.pfKey, localOpenServiceMessage.iType);
          return;
        case 19:
        }
        m3eActivity.PayMoneyMessage localPayMoneyMessage = (m3eActivity.PayMoneyMessage)paramMessage.obj;
        m3ePay.getInstance().handlePay(localPayMoneyMessage.num, localPayMoneyMessage.pf, localPayMoneyMessage.type);
      }
    };
    WGPlatform.WGSetObserver(new WGPlatformCallback());
    WGPlatform.WGEnableCrashReport(true, false);
    WGPlatform.handleCallback(getIntent());
    Log.e("m3e", "m3eActivity::onCreate end");
    QmiSdkApi.showQMi(this, "Android");
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (WGPlatform.IsDifferentActivity(this).booleanValue())
    {
      Log.d("m3eActivity", "Warning!Reduplicate game activity was detected.Activity will finish immediately.");
      return;
    }
    Log.d(S_LOGTAG, "onDestroy() isFinish: " + isFinishing());
    nativeDone();
    Process.killProcess(Process.myPid());
    QmiSdkApi.hideQMi(this);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.mVolumeKeyUp)
      {
        nativeShowFPS();
        return true;
      }
      try
      {
        String str1 = new String(nativeGetString("AttentionText"), "UTF-8");
        String str2 = new String(nativeGetString("GotoShopYes"), "UTF-8");
        String str3 = new String(nativeGetString("GotoShopNo"), "UTF-8");
        String str4 = new String(nativeGetString("ExitText"), "UTF-8");
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle(str1);
        localBuilder.setMessage(str4);
        localBuilder.setPositiveButton(str2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            m3eActivity.this.finish();
          }
        });
        localBuilder.setNegativeButton(str3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
          }
        });
        localBuilder.create();
        localBuilder.show();
        return true;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        return true;
      }
    }
    if (paramInt == 25)
      this.mVolumeKeyDown = true;
    if (paramInt == 24)
      this.mVolumeKeyUp = true;
    if (((!this.mVolumeKeyDown) || (paramInt != 82)) || ((this.mVolumeKeyUp) && (paramInt == 82)))
      nativeShowFPS();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 25)
      this.mVolumeKeyDown = false;
    if (paramInt == 24)
      this.mVolumeKeyUp = false;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onLowMemory()
  {
    Log.d(S_LOGTAG, "***~~~!!!onLowMemory()!!!~~~***");
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    WGPlatform.handleCallback(paramIntent);
  }

  protected void onPause()
  {
    super.onPause();
    if (WGPlatform.IsDifferentActivity(this).booleanValue())
    {
      Log.d("m3eActivity", "Warning!Reduplicate game activity was detected.Activity will finish immediately.");
      return;
    }
    XGPushManager.onActivityStoped(this);
    Log.d("INT", "onPause()");
    nativePause();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.bFirstLaunchResume)
    {
      this.bFirstLaunchResume = false;
      return;
    }
    XGPushManager.onActivityStarted(this);
    nativeResume();
    Log.d(S_LOGTAG, "onResume()");
  }

  protected void onStart()
  {
    super.onStart();
    if (WGPlatform.IsDifferentActivity(this).booleanValue())
      Log.d("m3eActivity", "Warning!Reduplicate game activity was detected.Activity will finish immediately.");
    do
    {
      return;
      Log.d(S_LOGTAG, "onStart()");
      if (!this.bFirstLaunchStart)
        continue;
      this.bFirstLaunchStart = false;
      nativeStart();
      return;
    }
    while ((getAPILevel() >= 8) || (mGLView == null));
    mGLView.onResume();
  }

  protected void onStop()
  {
    super.onStop();
    if (WGPlatform.IsDifferentActivity(this).booleanValue())
      Log.d("m3eActivity", "Warning!Reduplicate game activity was detected.Activity will finish immediately.");
    do
    {
      return;
      Log.d(S_LOGTAG, "onStop()");
      nativeStop();
    }
    while ((getAPILevel() >= 8) || (mGLView == null));
    mGLView.onPause();
  }

  public static class DialogMessage
  {
    public String button;
    public String buttonNo;
    public String message;
    public String titile;

    public DialogMessage(String paramString1, String paramString2, String paramString3)
    {
      this.titile = paramString1;
      this.message = paramString2;
      this.button = paramString3;
    }

    public DialogMessage(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      this.titile = paramString1;
      this.message = paramString2;
      this.button = paramString3;
      this.buttonNo = paramString4;
    }
  }

  public static class OpenServiceMessage
  {
    public int iType;
    public String pf;
    public String pfKey;
    public String sessionId;
    public String sessionType;
    public String userId;
    public String userKey;

    public OpenServiceMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt)
    {
      this.userId = paramString1;
      this.userKey = paramString2;
      this.sessionId = paramString3;
      this.sessionType = paramString4;
      this.pf = paramString5;
      this.pfKey = paramString6;
      this.iType = paramInt;
    }
  }

  public static class PayMessage
  {
    public String moneyType;
    public String pf;
    public String sessionId;
    public String sessionType;
    public String userId;
    public String userKey;
    public String value;

    public PayMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
    {
      this.userId = paramString1;
      this.userKey = paramString2;
      this.pf = paramString3;
      this.sessionId = paramString4;
      this.sessionType = paramString5;
      this.value = paramString6;
      this.moneyType = paramString7;
    }
  }

  public static class PayMessage2
  {
    public String discountType;
    public String discountUrl;
    public String pf;
    public String userId;

    public PayMessage2(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      this.userId = paramString1;
      this.pf = paramString2;
      this.discountType = paramString3;
      this.discountUrl = paramString4;
    }
  }

  public static class PayMoneyMessage
  {
    int num;
    public String pf;
    int type;

    public PayMoneyMessage(int paramInt1, String paramString, int paramInt2)
    {
      this.num = paramInt1;
      this.pf = paramString;
      this.type = paramInt2;
    }
  }

  public class WGPlatformCallback
    implements WGPlatformObserver
  {
    public WGPlatformCallback()
    {
    }

    public void Init()
    {
    }

    public String OnCrashExtMessageNotify()
    {
      Logger.d(String.format(Locale.CHINA, "OnCrashExtMessageNotify called", new Object[0]));
      Date localDate = new Date();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      return "Upload extra crashing message on " + localSimpleDateFormat.format(localDate);
    }

    public void OnFeedbackNotify(int paramInt, String paramString)
    {
      Locale localLocale = Locale.CHINA;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString;
      Logger.d(String.format(localLocale, "flag: %d; desc: %s;", arrayOfObject));
    }

    public void OnLocationNotify(RelationRet paramRelationRet)
    {
      Logger.d(paramRelationRet);
    }

    public void OnLoginNotify(LoginRet paramLoginRet)
    {
      Log.d("leofang", "ret.flag=" + paramLoginRet.flag);
      Log.d("leofang", "ret.platform=" + paramLoginRet.platform);
      Log.d("leofang", "token.size=" + paramLoginRet.token.size());
      switch (paramLoginRet.flag)
      {
      default:
      case 0:
      case 2005:
        do
        {
          return;
          if (paramLoginRet.token.size() > 1)
          {
            String str1 = "";
            String str2 = "";
            Iterator localIterator2 = paramLoginRet.token.iterator();
            while (localIterator2.hasNext())
            {
              TokenRet localTokenRet2 = (TokenRet)localIterator2.next();
              switch (localTokenRet2.type)
              {
              default:
                break;
              case 1:
                str2 = localTokenRet2.value;
                break;
              case 2:
                str1 = localTokenRet2.value;
                break;
              case 3:
                str1 = localTokenRet2.value;
                str2 = localTokenRet2.value;
                m3eActivity.access$002(localTokenRet2.expiration);
              }
            }
            Log.d("OnLoginNotify payToken", str1);
            Log.d("OnLoginNotify openKey", str2);
            m3eActivity.nativeWXQQLogin(paramLoginRet.open_id.length(), paramLoginRet.open_id, str2.length(), str2, paramLoginRet.pf.length(), paramLoginRet.pf, paramLoginRet.platform, str1.length(), str1);
            return;
          }
          m3eActivity.WXQQLogout();
          return;
        }
        while (WeGame.WXPLATID != paramLoginRet.platform);
        Iterator localIterator1 = paramLoginRet.token.iterator();
        TokenRet localTokenRet1;
        do
        {
          if (!localIterator1.hasNext())
            break;
          localTokenRet1 = (TokenRet)localIterator1.next();
        }
        while (3 != localTokenRet1.type);
        Log.d("leofang", "nativeOnRefreshWXToken " + localTokenRet1.value);
        m3eActivity.access$002(localTokenRet1.expiration);
        m3eActivity.this.nativeOnRefreshWXToken(localTokenRet1.value);
        return;
      case 2000:
        m3eActivity.showMessageBox("提示", "还没有安装微信客户端哦", "知道了");
        return;
      case 1001:
        m3eActivity.showMessageBox("提示", "您取消了QQ授权", "知道了");
        return;
      case 2002:
      case 2003:
        m3eActivity.showMessageBox("提示", "您取消了微信授权", "知道了");
        return;
      case 2001:
      case 2004:
      }
      Log.d("OnLoginNotify", "登陆失败处理");
      Logger.d(paramLoginRet.desc);
    }

    public void OnRelationNotify(RelationRet paramRelationRet)
    {
      Logger.d("OnRelationNotify" + paramRelationRet);
    }

    public void OnShareNotify(ShareRet paramShareRet)
    {
      Log.d("OnShareNotify 1", "ret.errCode=" + paramShareRet.flag);
      m3eActivity.nativeReEnter(paramShareRet.flag);
    }

    public void OnWakeupNotify(WakeupRet paramWakeupRet)
    {
      Log.d("OnWakeupNotify 1", "ret.errCode=" + paramWakeupRet.flag + "ret.open_id=" + paramWakeupRet.open_id + "ret.platform=" + paramWakeupRet.platform);
      if (paramWakeupRet.open_id != null)
        m3eActivity.nativeWXQQOnWakeup(paramWakeupRet.open_id.length(), paramWakeupRet.open_id, paramWakeupRet.platform);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eActivity
 * JD-Core Version:    0.6.0
 */