package com.tencent.connect.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.b;
import com.tencent.open.g;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.Util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseApi
{
  protected static final String ACTION_CHECK_TOKEN = "action_check_token";
  protected static final String ACTIVITY_AGENT = "com.tencent.open.agent.AgentActivity";
  protected static final String ACTIVITY_ENCRY_TOKEN = "com.tencent.open.agent.EncryTokenActivity";
  protected static final String DEFAULT_PF = "openmobile_android";
  private static final String KEY_REQUEST_CODE = "key_request_code";
  private static final int MSG_COMPLETE = 0;
  protected static final String PARAM_ENCRY_EOKEN = "encry_token";
  protected static final String PLATFORM = "desktop_m_qq";
  protected static final String PREFERENCE_PF = "pfStore";
  private static final String TAG = BaseApi.class.getName();
  protected static final String VERSION = "android";
  public static String businessId;
  public static String installChannel;
  public static boolean isOEM;
  public static String registerChannel;
  protected static int sRequestCode = 1000;
  protected Intent mActivityIntent = null;
  protected Context mContext;
  protected ProgressDialog mProgressDialog;
  protected QQAuth mQQAuth;
  protected List<ApiTask> mTaskList = null;
  protected QQToken mToken;
  protected IUiListener mUiListener = null;

  static
  {
    registerChannel = null;
    installChannel = null;
    businessId = null;
    isOEM = false;
  }

  public BaseApi(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    this.mContext = paramContext;
    this.mQQAuth = paramQQAuth;
    this.mToken = paramQQToken;
    this.mTaskList = new ArrayList();
  }

  public BaseApi(Context paramContext, QQToken paramQQToken)
  {
    this(paramContext, null, paramQQToken);
  }

  private Intent getAssitIntent()
  {
    return new Intent(this.mContext, AssistActivity.class);
  }

  protected Bundle composeActivityParams()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("appid", this.mToken.getAppId());
    if (this.mToken.isSessionValid())
    {
      localBundle.putString("keystr", this.mToken.getAccessToken());
      localBundle.putString("keytype", "0x80");
    }
    String str = this.mToken.getOpenId();
    if (str != null)
      localBundle.putString("hopenid", str);
    localBundle.putString("platform", "androidqz");
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("pfStore", 0);
    if (isOEM)
      localBundle.putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
    while (true)
    {
      localBundle.putString("sdkv", "2.3");
      localBundle.putString("sdkp", "a");
      return localBundle;
      localBundle.putString("pf", localSharedPreferences.getString("pf", "openmobile_android"));
      localBundle.putString("pf", "openmobile_android");
    }
  }

  protected Bundle composeCGIParams()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("format", "json");
    localBundle.putString("status_os", Build.VERSION.RELEASE);
    localBundle.putString("status_machine", Build.MODEL);
    localBundle.putString("status_version", Build.VERSION.SDK);
    localBundle.putString("sdkv", "2.3");
    localBundle.putString("sdkp", "a");
    if ((this.mToken != null) && (this.mToken.isSessionValid()))
    {
      localBundle.putString("access_token", this.mToken.getAccessToken());
      localBundle.putString("oauth_consumer_key", this.mToken.getAppId());
      localBundle.putString("openid", this.mToken.getOpenId());
    }
    localBundle.putString("appid_for_getting_config", this.mToken.getAppId());
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("pfStore", 0);
    if (isOEM)
    {
      localBundle.putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
      return localBundle;
    }
    localBundle.putString("pf", localSharedPreferences.getString("pf", "openmobile_android"));
    return localBundle;
  }

  Intent getActivityIntent()
  {
    return this.mActivityIntent;
  }

  protected Intent getAgentIntent()
  {
    return getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
  }

  protected Intent getAgentIntentWithTarget(String paramString)
  {
    Intent localIntent1 = new Intent();
    Intent localIntent2 = getTargetActivityIntent(paramString);
    if (localIntent2 == null);
    do
      return null;
    while (localIntent2.getComponent() == null);
    localIntent1.setClassName(localIntent2.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
    return localIntent1;
  }

  protected Intent getTargetActivityIntent(String paramString)
  {
    Object localObject = new Intent();
    ((Intent)localObject).setClassName(Constants.PACKAGE_QZONE, paramString);
    Intent localIntent = new Intent();
    localIntent.setClassName(Constants.PACKAGE_QQ, paramString);
    if (SystemUtils.isActivityExist(this.mContext, localIntent))
      localObject = localIntent;
    while (true)
    {
      return localObject;
      if ((!SystemUtils.isActivityExist(this.mContext, (Intent)localObject)) || (SystemUtils.compareVersion(SystemUtils.getAppVersionName(this.mContext, Constants.PACKAGE_QZONE), "3.4") < 0))
        break;
      if (!SystemUtils.isAppSignatureValid(this.mContext, ((Intent)localObject).getComponent().getPackageName(), Constants.SIGNATRUE_QZONE))
        return null;
    }
    return (Intent)null;
  }

  protected void handleDownloadLastestQQ(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    Bundle localBundle = new Bundle(paramBundle);
    b localb = new b(paramActivity);
    localb.a("请安装最新版QQ");
    localb.b("无法使用该功能，是否下载QQ手机版？");
    localb.d("下载");
    localb.setCancelable(true);
    localb.c("取消");
    localb.a(new View.OnClickListener(localb, paramActivity, localBundle, paramIUiListener, paramActivity)
    {
      public void onClick(View paramView)
      {
        this.val$dialog.dismiss();
        BaseApi.this.showProgressDialog(this.val$activity, "", "正在获取下载地址...");
        this.val$p.putString("getinfo_mask", "1");
        g localg = new g(new IUiListener()
        {
          public void onCancel()
          {
            d.b(BaseApi.TAG, "-->handleDownloadLastestQQ onCancel");
            if (BaseApi.this.mProgressDialog != null)
              BaseApi.this.mProgressDialog.dismiss();
            BaseApi.1.this.val$listener.onCancel();
          }

          public void onComplete(Object paramObject)
          {
            d.b(BaseApi.TAG, "-->handleDownloadLastestQQ onComplete =" + paramObject);
            if (BaseApi.this.mProgressDialog != null)
              BaseApi.this.mProgressDialog.dismiss();
            JSONObject localJSONObject = (JSONObject)paramObject;
            try
            {
              String str2 = localJSONObject.getString("fixpackageurl");
              str1 = str2;
              Uri localUri = Uri.parse(str1);
              BaseApi.1.this.val$activity.startActivity(new Intent("android.intent.action.VIEW", localUri));
              return;
            }
            catch (JSONException localJSONException)
            {
              while (true)
              {
                localJSONException.printStackTrace();
                String str1 = null;
              }
            }
          }

          public void onError(UiError paramUiError)
          {
            d.b(BaseApi.TAG, "-->handleDownloadLastestQQ onError =" + paramUiError.toString());
            if (BaseApi.this.mProgressDialog != null)
              BaseApi.this.mProgressDialog.dismiss();
            BaseApi.1.this.val$listener.onError(paramUiError);
          }
        });
        this.val$p.putString("appid", "100686848");
        HttpUtils.requestAsync(BaseApi.this.mToken, BaseApi.this.mContext, ServerSetting.getInstance().getEnvUrl(this.val$a, "http://fusion.qq.com/cgi-bin/qzapps/mapp_getappinfo.cgi"), this.val$p, "GET", localg);
      }
    });
    localb.b(new View.OnClickListener(localb)
    {
      public void onClick(View paramView)
      {
        this.val$dialog.dismiss();
      }
    });
    localb.show();
  }

  protected boolean hasActivityForIntent()
  {
    if (this.mActivityIntent != null)
      return SystemUtils.isActivityExist(this.mContext, this.mActivityIntent);
    return false;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Iterator localIterator = this.mTaskList.iterator();
    IUiListener localIUiListener;
    while (true)
    {
      boolean bool = localIterator.hasNext();
      localIUiListener = null;
      if (!bool)
        break;
      ApiTask localApiTask = (ApiTask)localIterator.next();
      if (localApiTask.mRequestCode != paramInt1)
        continue;
      localIUiListener = localApiTask.mListener;
      this.mTaskList.remove(localApiTask);
    }
    if (localIUiListener == null)
      return;
    int i;
    String str;
    if (paramInt2 == -1)
    {
      i = paramIntent.getIntExtra("key_error_code", 0);
      if (i == 0)
      {
        str = paramIntent.getStringExtra("key_response");
        if (str == null);
      }
    }
    while (true)
    {
      try
      {
        localIUiListener.onComplete(Util.parseJson(str));
        d.f().b();
        return;
      }
      catch (JSONException localJSONException)
      {
        localIUiListener.onError(new UiError(-4, "服务器返回数据格式有误!", str));
        d.a("openSDK_LOG", "OpenUi, onActivityResult, json error", localJSONException);
        continue;
      }
      d.b("openSDK_LOG", "OpenUi, onActivityResult, onComplete");
      localIUiListener.onComplete(new JSONObject());
      continue;
      d.d("openSDK_LOG", "OpenUi, onActivityResult, onError = " + i + "");
      localIUiListener.onError(new UiError(i, paramIntent.getStringExtra("key_error_msg"), paramIntent.getStringExtra("key_error_detail")));
      continue;
      d.b("openSDK_LOG", "OpenUi, onActivityResult, Constants.ACTIVITY_CANCEL");
      localIUiListener.onCancel();
    }
  }

  protected void showProgressDialog(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      paramString1 = "请稍候";
    if (TextUtils.isEmpty(paramString2))
      paramString2 = "正在加载...";
    this.mProgressDialog = ProgressDialog.show(paramContext, paramString1, paramString2);
    this.mProgressDialog.setCancelable(true);
  }

  protected void startAssitActivity(Activity paramActivity, IUiListener paramIUiListener)
  {
    AssistActivity.setApiObject(this);
    int i = sRequestCode;
    sRequestCode = i + 1;
    this.mActivityIntent.putExtra("key_request_code", i);
    this.mTaskList.add(new ApiTask(i, paramIUiListener));
    paramActivity.startActivity(getAssitIntent());
  }

  public class ApiTask
  {
    public IUiListener mListener;
    public int mRequestCode;

    public ApiTask(int paramIUiListener, IUiListener arg3)
    {
      this.mRequestCode = paramIUiListener;
      Object localObject;
      this.mListener = localObject;
    }
  }

  public class TempRequestListener
    implements IRequestListener
  {
    private Handler mHandler;
    private IUiListener mListener;

    public TempRequestListener(IUiListener arg2)
    {
      Object localObject;
      this.mListener = localObject;
      this.mHandler = new Handler(BaseApi.this.mContext.getMainLooper(), BaseApi.this)
      {
        public void handleMessage(Message paramMessage)
        {
          if (paramMessage.what == 0)
          {
            BaseApi.TempRequestListener.this.mListener.onComplete((JSONObject)paramMessage.obj);
            return;
          }
          BaseApi.TempRequestListener.this.mListener.onError(new UiError(paramMessage.what, (String)paramMessage.obj, null));
        }
      };
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramJSONObject;
      localMessage.what = 0;
      this.mHandler.sendMessage(localMessage);
    }

    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramConnectTimeoutException.getMessage();
      localMessage.what = -7;
      this.mHandler.sendMessage(localMessage);
    }

    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramHttpStatusException.getMessage();
      localMessage.what = -9;
      this.mHandler.sendMessage(localMessage);
    }

    public void onIOException(IOException paramIOException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramIOException.getMessage();
      localMessage.what = -2;
      this.mHandler.sendMessage(localMessage);
    }

    public void onJSONException(JSONException paramJSONException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramJSONException.getMessage();
      localMessage.what = -4;
      this.mHandler.sendMessage(localMessage);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramMalformedURLException.getMessage();
      localMessage.what = -3;
      this.mHandler.sendMessage(localMessage);
    }

    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramNetworkUnavailableException.getMessage();
      localMessage.what = -10;
      this.mHandler.sendMessage(localMessage);
    }

    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramSocketTimeoutException.getMessage();
      localMessage.what = -8;
      this.mHandler.sendMessage(localMessage);
    }

    public void onUnknowException(Exception paramException)
    {
      Message localMessage = this.mHandler.obtainMessage();
      localMessage.obj = paramException.getMessage();
      localMessage.what = -6;
      this.mHandler.sendMessage(localMessage);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.common.BaseApi
 * JD-Core Version:    0.6.0
 */