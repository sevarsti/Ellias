package com.tencent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerSetting
{
  public static final String CGI_FETCH_QQ_URL = "http://fusion.qq.com/cgi-bin/qzapps/mapp_getappinfo.cgi";
  public static final String DEFAULT_CGI_AUTHORIZE = "https://openmobile.qq.com/oauth2.0/m_authorize?";
  public static final String DEFAULT_LOCAL_STORAGE_URI = "http://qzs.qq.com";
  public static final String DEFAULT_REDIRECT_URI = "auth://tauth.qq.com/";
  public static final String DEFAULT_URL_ASK = "http://qzs.qq.com/open/mobile/request/sdk_request.html?";
  public static final String DEFAULT_URL_BRAG = "http://qzs.qq.com/open/mobile/brag/sdk_brag.html?";
  public static final String DEFAULT_URL_GIFT = "http://qzs.qq.com/open/mobile/request/sdk_request.html?";
  public static final String DEFAULT_URL_GRAPH_BASE = "https://openmobile.qq.com/";
  public static final String DEFAULT_URL_INVITE = "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?";
  public static final String DEFAULT_URL_REACTIVE = "http://qzs.qq.com/open/mobile/reactive/sdk_reactive.html?";
  public static final String DEFAULT_URL_REPORT = "http://wspeed.qq.com/w.cgi";
  public static final String DEFAULT_URL_SEND_STORY = "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?";
  public static final String DEFAULT_URL_VOICE = "http://qzs.qq.com/open/mobile/not_support.html?";
  public static final int ENVIRONMENT_EXPERIENCE = 1;
  public static final int ENVIRONMENT_NORMOL = 0;
  public static final String KEY_HOST_ANALY = "analy.qq.com";
  public static final String KEY_HOST_APPIC = "appic.qq.com";
  public static final String KEY_HOST_APP_SUPPORT = "appsupport.qq.com";
  public static final String KEY_HOST_FUSION = "fusion.qq.com";
  public static final String KEY_HOST_I_GTIMG = "i.gtimg.cn";
  public static final String KEY_HOST_MAPP_QZONE = "mapp.qzone.qq.com";
  public static final String KEY_HOST_OPEN_MOBILE = "openmobile.qq.com";
  public static final String KEY_HOST_QZAPP_QLOGO = "qzapp.qlogo.cn";
  public static final String KEY_HOST_QZS_QQ = "qzs.qq.com";
  public static final String KEY_OPEN_ENV = "OpenEnvironment";
  public static final String KEY_OPEN_SETTING = "OpenSettings";
  private static final String a = ServerSetting.class.getName();
  private static ServerSetting b = null;
  private volatile WeakReference<SharedPreferences> c = null;

  public static ServerSetting getInstance()
  {
    monitorenter;
    try
    {
      if (b == null)
        b = new ServerSetting();
      ServerSetting localServerSetting = b;
      return localServerSetting;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void changeServer()
  {
    this.c = null;
  }

  public String getEnvUrl(Context paramContext, String paramString)
  {
    if ((this.c == null) || (this.c.get() == null))
      this.c = new WeakReference(paramContext.getSharedPreferences("ServerPrefs", 0));
    String str1;
    String str2;
    try
    {
      str1 = new URL(paramString).getHost();
      if (str1 == null)
      {
        Log.e(a, "Get host error. url=" + paramString);
        return paramString;
      }
      str2 = ((SharedPreferences)this.c.get()).getString(str1, null);
      if ((str2 == null) || (str1.equals(str2)))
      {
        Log.d(a, "host=" + str1 + ", envHost=" + str2);
        return paramString;
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      localMalformedURLException.printStackTrace();
      Log.e(a, "getEnvUrl error. url=" + paramString);
      return paramString;
    }
    paramString = paramString.replace(str1, str2);
    Log.d(a, "return environment url : " + paramString);
    return paramString;
  }

  public void setEnvironment(Context paramContext, int paramInt)
  {
    if ((paramContext != null) && ((this.c == null) || (this.c.get() == null)))
      this.c = new WeakReference(paramContext.getSharedPreferences("ServerPrefs", 0));
    if ((paramInt != 0) && (paramInt != 1))
    {
      Log.e(a, "切换环境参数错误，正式环境为0，体验环境为1");
      return;
    }
    switch (paramInt)
    {
    default:
      return;
    case 0:
      SharedPreferences.Editor localEditor2 = ((SharedPreferences)this.c.get()).edit();
      localEditor2.putInt("ServerType", 0);
      localEditor2.putString("OpenEnvironment", "formal");
      localEditor2.putString("qzs.qq.com", "qzs.qq.com");
      localEditor2.putString("openmobile.qq.com", "openmobile.qq.com");
      localEditor2.commit();
      changeServer();
      Toast.makeText(paramContext, "已切换到正式环境", 0).show();
      return;
    case 1:
    }
    SharedPreferences.Editor localEditor1 = ((SharedPreferences)this.c.get()).edit();
    localEditor1.putInt("ServerType", 1);
    localEditor1.putString("OpenEnvironment", "exp");
    localEditor1.putString("qzs.qq.com", "testmobile.qq.com");
    localEditor1.putString("openmobile.qq.com", "test.openmobile.qq.com");
    localEditor1.commit();
    changeServer();
    Toast.makeText(paramContext, "已切换到体验环境", 0).show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.utils.ServerSetting
 * JD-Core Version:    0.6.0
 */