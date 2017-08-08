package com.tencent.msdk.notice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.db.AppDBModel;
import com.tencent.msdk.db.LoginInfoManager;
import com.tencent.msdk.db.NoticeDBModel;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.stat.ReportEvent;
import com.tencent.msdk.stat.eEVENT_TYPE;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoticeManager
{
  public static final String ALERT_MSG_TYPE = "alertMsg";
  public static final String SCROLL_MSG_TYPE = "rollMsg";
  private static volatile NoticeManager instance;
  public static int sDefaultNoticeTime;
  public static String sNoticePicPath;
  public static int sNoticeTimeLimit;
  public static int sNoticeVersion = 0;
  public static int sRealNoticeTime;
  public Activity mActivity = null;
  private Vector<NoticeInfo> mAlertMsgVector = new Vector();
  private String mAppId = "";
  private String mAppKey = "";
  private String mMatId = "";
  private String mOpenId = "";
  public String mPackageName = null;
  private String mScrollMsg = "";
  public Handler mainHandler = null;

  static
  {
    sNoticeTimeLimit = 5;
    sDefaultNoticeTime = 10;
    sRealNoticeTime = 10;
    sNoticePicPath = "";
    instance = null;
  }

  private NoticeManager()
  {
    setAppinfo();
    String str = WeGame.getInstance().WGGetVersion();
    try
    {
      sNoticeVersion = Integer.parseInt(Pattern.compile("\\.|a").matcher(str).replaceAll(""));
      Logger.d("msdkVersion:" + str + ",noticeVersion:" + sNoticeVersion);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        Logger.e("get Notice version error");
        sNoticeVersion = 0;
      }
    }
  }

  public static NoticeManager getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new NoticeManager();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public Boolean ckShowDbNotice(NoticeInfo paramNoticeInfo)
  {
    Boolean.valueOf(false);
    if (eMSDK_SCREENDIR.eMSDK_SCREENDIR_LANDSCAPE == NoticeRequestPara.SCREEN_DIR)
    {
      if (!T.ckIsEmpty(paramNoticeInfo.mNoticeHImgUrl))
      {
        if (NoticePic.checkNoticePicExist(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeHImgUrl, paramNoticeInfo.mNoticeHImgHash).booleanValue())
          return Boolean.valueOf(true);
        NoticePic.downloadNoticePic(new NoticePic(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeHImgUrl, NoticeRequestPara.SCREEN_DIR, paramNoticeInfo.mNoticeHImgHash));
        Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " himg is not exist!");
        return Boolean.valueOf(false);
      }
      Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " himgUrl is null!");
      return Boolean.valueOf(false);
    }
    if (eMSDK_SCREENDIR.eMSDK_SCREENDIR_PORTRAIT == NoticeRequestPara.SCREEN_DIR)
    {
      if (!T.ckIsEmpty(paramNoticeInfo.mNoticeVImgUrl))
      {
        if (NoticePic.checkNoticePicExist(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeVImgUrl, paramNoticeInfo.mNoticeVImgHash).booleanValue())
          return Boolean.valueOf(true);
        NoticePic.downloadNoticePic(new NoticePic(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeVImgUrl, NoticeRequestPara.SCREEN_DIR, paramNoticeInfo.mNoticeVImgHash));
        Boolean localBoolean2 = Boolean.valueOf(false);
        Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " vimg is not exist!");
        return localBoolean2;
      }
      Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " vimgUrl is null!");
      return Boolean.valueOf(false);
    }
    if (!T.ckIsEmpty(paramNoticeInfo.mNoticeHImgUrl))
    {
      if (NoticePic.checkNoticePicExist(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeHImgUrl, paramNoticeInfo.mNoticeHImgHash).booleanValue())
      {
        if (!T.ckIsEmpty(paramNoticeInfo.mNoticeVImgUrl))
        {
          if (NoticePic.checkNoticePicExist(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeVImgUrl, paramNoticeInfo.mNoticeVImgHash).booleanValue())
            return Boolean.valueOf(true);
          NoticePic.downloadNoticePic(new NoticePic(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeVImgUrl, NoticeRequestPara.SCREEN_DIR, paramNoticeInfo.mNoticeVImgHash));
          Boolean localBoolean1 = Boolean.valueOf(false);
          Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " vimg is not exist!");
          return localBoolean1;
        }
        Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " vimgUrl is null!");
        return Boolean.valueOf(false);
      }
      NoticePic.downloadNoticePic(new NoticePic(paramNoticeInfo.mNoticeId, paramNoticeInfo.mNoticeHImgUrl, NoticeRequestPara.SCREEN_DIR, paramNoticeInfo.mNoticeHImgHash));
      Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " himg is not exist!");
      return Boolean.valueOf(false);
    }
    Logger.d("Notice_" + paramNoticeInfo.mNoticeId + " himgUrl is null!");
    return Boolean.valueOf(false);
  }

  public void closeScrollNotice()
  {
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
    {
      Logger.w("notice module is closed!");
      return;
    }
    this.mScrollMsg = "";
    Intent localIntent = new Intent(this.mActivity, RollFloatService.class);
    this.mActivity.stopService(localIntent);
  }

  public void deleteAlertNoticeItemFromList()
  {
    if ((this.mAlertMsgVector != null) && (this.mAlertMsgVector.size() > 0))
      this.mAlertMsgVector.remove(0);
  }

  public void displayAlertNotice()
  {
    Logger.d("displayAlertNotice intent");
    Intent localIntent = new Intent();
    localIntent.putExtra("alertMsg", getAlertNoticeItemFromList());
    localIntent.setClass(this.mActivity, AlertMsgActivity.class);
    this.mActivity.startActivity(localIntent);
  }

  public void displayRollNotice(String paramString)
  {
    this.mScrollMsg = paramString;
    Intent localIntent = new Intent();
    Logger.d("displayRollNotice intent");
    localIntent.putExtra("rollMsg", paramString);
    localIntent.setClass(this.mActivity, RollFloatService.class);
    this.mActivity.startService(localIntent);
  }

  public NoticeInfo getAlertNoticeItemFromList()
  {
    if ((this.mAlertMsgVector != null) && (this.mAlertMsgVector.size() > 0))
      return (NoticeInfo)this.mAlertMsgVector.firstElement();
    return null;
  }

  public Context getContext()
  {
    return this.mActivity;
  }

  public float getDensity()
  {
    WindowManager localWindowManager = (WindowManager)this.mActivity.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }

  public int getHeight()
  {
    return ((WindowManager)this.mActivity.getSystemService("window")).getDefaultDisplay().getHeight();
  }

  public Vector<NoticeInfo> getNoticeBySceneAndType(eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString)
  {
    Vector localVector2;
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
    {
      Logger.w("notice module is closed!");
      localVector2 = new Vector();
    }
    while (true)
    {
      return localVector2;
      Vector localVector1 = getNoticeFromDBBySceneAndType(parameMSG_NOTICETYPE, paramString);
      localVector2 = new Vector();
      Iterator localIterator = localVector1.iterator();
      while (localIterator.hasNext())
      {
        NoticeInfo localNoticeInfo = (NoticeInfo)localIterator.next();
        if (localNoticeInfo.mNoticeContentType == eMSG_CONTENTTYPE.eMSG_CONTENTTYPE_IMAGE)
        {
          if (!ckShowDbNotice(localNoticeInfo).booleanValue())
            continue;
          localNoticeInfo.mNoticeHImgUrl = NoticePic.getFilePathByNoticeIdAndHashValue(localNoticeInfo.mNoticeId, localNoticeInfo.mNoticeHImgUrl, localNoticeInfo.mNoticeHImgHash);
          localNoticeInfo.mNoticeVImgUrl = NoticePic.getFilePathByNoticeIdAndHashValue(localNoticeInfo.mNoticeId, localNoticeInfo.mNoticeVImgUrl, localNoticeInfo.mNoticeVImgHash);
          if (!T.ckIsEmpty(localNoticeInfo.mNoticeHImgUrl))
          {
            NoticePic localNoticePic1 = new NoticePic(localNoticeInfo.mNoticeId, localNoticeInfo.mNoticeHImgUrl, eMSDK_SCREENDIR.eMSDK_SCREENDIR_LANDSCAPE, localNoticeInfo.mNoticeHImgHash);
            localNoticeInfo.mNoticePics.add(localNoticePic1);
          }
          if (!T.ckIsEmpty(localNoticeInfo.mNoticeVImgUrl))
          {
            NoticePic localNoticePic2 = new NoticePic(localNoticeInfo.mNoticeId, localNoticeInfo.mNoticeVImgUrl, eMSDK_SCREENDIR.eMSDK_SCREENDIR_PORTRAIT, localNoticeInfo.mNoticeVImgHash);
            localNoticeInfo.mNoticePics.add(localNoticePic2);
          }
          localVector2.add(localNoticeInfo);
          continue;
        }
        if (localNoticeInfo.mNoticeContentType == eMSG_CONTENTTYPE.eMSG_CONTENTTYPE_WEB)
        {
          if (T.ckIsEmpty(localNoticeInfo.mNoticeContentWebUrl))
            continue;
          localVector2.add(localNoticeInfo);
          continue;
        }
        localVector2.add(localNoticeInfo);
      }
    }
  }

  public Vector<NoticeInfo> getNoticeFromDBBySceneAndType(eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString)
  {
    Vector localVector1 = new Vector();
    if ((parameMSG_NOTICETYPE == null) || (T.ckIsEmpty(paramString)))
    {
      Logger.w("noticeType or scene is null");
      return localVector1;
    }
    if (!eMSG_NOTICETYPE.checkIsValidType(parameMSG_NOTICETYPE))
    {
      Logger.w("bad noticeType:" + parameMSG_NOTICETYPE);
      return localVector1;
    }
    setAppinfo();
    if (T.ckIsEmpty(this.mAppId))
    {
      Logger.w("appId is null");
      return localVector1;
    }
    Vector localVector2 = new NoticeDBModel().getNoticeRecordBySceneAndType(this.mAppId, this.mOpenId, parameMSG_NOTICETYPE, paramString);
    Logger.d("noticeVector size:" + localVector2.size());
    return localVector2;
  }

  public void getNoticeInfo()
  {
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
    {
      Logger.w("notice module is closed!");
      return;
    }
    Logger.d("getNotice");
    setAppinfo();
    NoticeRequestPara localNoticeRequestPara = new NoticeRequestPara();
    localNoticeRequestPara.mAppId = this.mAppId;
    localNoticeRequestPara.mAppKey = this.mAppKey;
    localNoticeRequestPara.mOpenId = this.mOpenId;
    Logger.d("appid：" + localNoticeRequestPara.mAppId + ";openid:" + localNoticeRequestPara.mOpenId);
    if (T.ckIsEmpty(getmMatId()))
      setmMatId(new AppDBModel().getMatId(this.mAppId));
    localNoticeRequestPara.mMatid = getmMatId();
    Logger.d("Notice Model:mat_id may be null:" + localNoticeRequestPara.mMatid + ";mMatId:" + getmMatId());
    updateAppInfoInDB();
    MsdkThreadManager.getInstance().getNoticeReq(localNoticeRequestPara);
  }

  public Resources getResources()
  {
    return this.mActivity.getApplicationContext().getResources();
  }

  public int getWidth()
  {
    return ((WindowManager)this.mActivity.getSystemService("window")).getDefaultDisplay().getWidth();
  }

  public String getmMatId()
  {
    return this.mMatId;
  }

  public void init(Activity paramActivity)
  {
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
    {
      Logger.w("notice module is closed!");
      return;
    }
    Logger.d("notice module init start!");
    this.mActivity = paramActivity;
    try
    {
      ActivityInfo localActivityInfo = paramActivity.getPackageManager().getActivityInfo(paramActivity.getComponentName(), 128);
      PackageInfo localPackageInfo = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 16384);
      NoticeRequestPara.SCREEN_DIR = eMSDK_SCREENDIR.getEnum(localActivityInfo);
      NoticeRequestPara.sGameVersion = localPackageInfo.versionCode;
      sNoticePicPath = paramActivity.getFilesDir().getAbsolutePath();
      new DisplayMetrics();
      NoticeRequestPara.sScreenDpi = getResources().getDisplayMetrics().densityDpi;
      sRealNoticeTime = ConfigManager.getNoticeTime(WeGame.getInstance().getActivity());
      this.mPackageName = paramActivity.getPackageName();
      NoticeHelper.reqMatid(new NoticeHelper.MatIdCallback()
      {
        public void onSuccess(String paramString)
        {
          NoticeManager.access$002(NoticeManager.this, paramString);
          Logger.d("notice module matId:" + NoticeManager.this.mMatId);
          NoticeManager.this.getNoticeInfo();
        }

        public void onTimeout()
        {
          Logger.w("notice module get matId failed:" + NoticeManager.this.mMatId);
          ReportEvent.ReportBasicClickEvent(eEVENT_TYPE.eEVENT_BASIC_MATIDFAIL);
          NoticeManager.this.getNoticeInfo();
        }
      });
      Logger.d("notice module init finish! matid:" + getmMatId());
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        Logger.e("getComponentName NameNotFoundException");
    }
  }

  public void onPause()
  {
    Logger.d("notice module on pause!");
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
      Logger.w("notice module is closed!");
    do
    {
      return;
      Logger.d("Scroll notice:" + this.mScrollMsg);
    }
    while (T.ckIsEmpty(this.mScrollMsg));
    Intent localIntent = new Intent(this.mActivity, RollFloatService.class);
    this.mActivity.stopService(localIntent);
  }

  public void onResume()
  {
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
      Logger.w("notice module is closed!");
    do
    {
      return;
      if ((this.mAlertMsgVector == null) || (this.mAlertMsgVector.size() <= 0))
        continue;
      Logger.d("onResume show alert notice!");
      displayAlertNotice();
    }
    while (T.ckIsEmpty(this.mScrollMsg));
    Logger.d("onResume show Scroll notice!");
    displayRollNotice(this.mScrollMsg);
  }

  public void setAppinfo()
  {
    LoginRet localLoginRet = LoginInfoManager.getInstance().getLastLoginUserInfo();
    if (localLoginRet.platform == WeGame.WXPLATID)
    {
      this.mAppId = WeGame.getInstance().wx_appid;
      this.mAppKey = WeGame.getInstance().wxAppKey;
    }
    for (this.mOpenId = localLoginRet.open_id; ; this.mOpenId = localLoginRet.open_id)
    {
      Logger.d("mAppId：" + this.mAppId + ";mOpenId:" + this.mOpenId);
      return;
      if (localLoginRet.platform != WeGame.QQPLATID)
        break;
      this.mAppId = WeGame.getInstance().qq_appid;
      this.mAppKey = WeGame.getInstance().qqAppKey;
    }
    String str1 = WeGame.getInstance().qq_appid;
    String str2 = WeGame.getInstance().wx_appid;
    if (T.ckIsEmpty(str1))
      if (T.ckIsEmpty(str2))
      {
        this.mAppId = "";
        this.mAppKey = "";
        Logger.e("appid is null");
      }
    while (true)
    {
      this.mOpenId = "";
      break;
      this.mAppId = WeGame.getInstance().wx_appid;
      this.mAppKey = WeGame.getInstance().wxAppKey;
      continue;
      if (T.ckIsEmpty(str2))
      {
        this.mAppId = WeGame.getInstance().qq_appid;
        this.mAppKey = WeGame.getInstance().qqAppKey;
        continue;
      }
      this.mAppId = (WeGame.getInstance().qq_appid + "|" + WeGame.getInstance().wx_appid);
      this.mAppKey = WeGame.getInstance().qqAppKey;
    }
  }

  public void setmMatId(String paramString)
  {
    this.mMatId = paramString;
  }

  public void showNoticeByScene(eMSG_NOTICETYPE parameMSG_NOTICETYPE, String paramString)
  {
    if (!ConfigManager.needNotice(WeGame.getInstance().getActivity()))
    {
      Logger.w("notice module is closed!");
      return;
    }
    Vector localVector = getNoticeBySceneAndType(parameMSG_NOTICETYPE, paramString);
    if (localVector.size() == 0)
    {
      Logger.d("No notice");
      return;
    }
    if ((eMSG_NOTICETYPE.eMSG_NOTICETYPE_ALERT == parameMSG_NOTICETYPE) || (eMSG_NOTICETYPE.eMSG_NOTICETYPE_ALL == parameMSG_NOTICETYPE))
    {
      this.mAlertMsgVector = localVector;
      displayAlertNotice();
      return;
    }
    if ((eMSG_NOTICETYPE.eMSG_NOTICETYPE_SCROLL == parameMSG_NOTICETYPE) || (eMSG_NOTICETYPE.eMSG_NOTICETYPE_ALL == parameMSG_NOTICETYPE))
    {
      String str = "";
      Iterator localIterator = localVector.iterator();
      while (localIterator.hasNext())
      {
        NoticeInfo localNoticeInfo = (NoticeInfo)localIterator.next();
        str = str + "     " + localNoticeInfo.mNoticeContent;
        Logger.d("Add an Scroll notice id:" + localNoticeInfo.mNoticeId + ",content:" + localNoticeInfo.mNoticeContent);
      }
      Logger.d("Scroll notice content before:" + str);
      this.mScrollMsg = Pattern.compile("\r|\n").matcher(str).replaceAll("");
      Logger.d("Scroll notice content after:" + this.mScrollMsg);
      displayRollNotice(this.mScrollMsg);
      return;
    }
    Logger.e("Error notice type :" + parameMSG_NOTICETYPE);
  }

  public void updateAppInfoInDB()
  {
    AppInfo localAppInfo = new AppInfo();
    localAppInfo.appid = this.mAppId;
    localAppInfo.openid = this.mOpenId;
    localAppInfo.matid = getmMatId();
    localAppInfo.msdkVersion = WeGame.getInstance().getMsdkVersion();
    localAppInfo.packageName = this.mActivity.getApplicationInfo().packageName;
    new AppDBModel().save(localAppInfo);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.NoticeManager
 * JD-Core Version:    0.6.0
 */