package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.utils.SystemUtils;
import java.util.Iterator;
import java.util.Set;

public class AppbarAgent extends BaseApi
{
  public static final String TO_APPBAR_DETAIL = "siteIndex";
  public static final String TO_APPBAR_NEWS = "myMessage";
  public static final String TO_APPBAR_SEND_BLOG = "newThread";
  public static String wx_appid = "wx8e8dc60535c9cd93";
  private Activity a;

  public AppbarAgent(Activity paramActivity, QQToken paramQQToken)
  {
    super(paramActivity.getApplicationContext(), paramQQToken);
    this.a = paramActivity;
  }

  private String a()
  {
    Bundle localBundle = new Bundle();
    if ((this.mToken != null) && (this.mToken.getAppId() != null) && (this.mToken.getAccessToken() != null) && (this.mToken.getOpenId() != null))
    {
      localBundle.putString("qOpenAppId", this.mToken.getAppId());
      localBundle.putString("qOpenId", this.mToken.getOpenId());
      localBundle.putString("qAccessToken", this.mToken.getAccessToken());
    }
    localBundle.putString("qPackageName", this.mContext.getPackageName());
    return "&" + a(localBundle);
  }

  private String a(Bundle paramBundle)
  {
    if ((paramBundle == null) || (paramBundle.isEmpty()))
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localStringBuilder.append(str).append("=").append(paramBundle.get(str)).append("&");
    }
    return localStringBuilder.substring(0, -1 + localStringBuilder.length());
  }

  private boolean a(String paramString)
  {
    return ("siteIndex".equals(paramString)) || ("myMessage".equals(paramString)) || ("newThread".equals(paramString));
  }

  private String b()
  {
    PackageInfo localPackageInfo;
    try
    {
      localPackageInfo = this.mContext.getPackageManager().getPackageInfo("com.tencent.android.qqdownloader", 0);
      if (localPackageInfo == null)
        return null;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      return null;
    }
    return localPackageInfo.versionName;
  }

  private void b(String paramString)
  {
    Intent localIntent = new Intent(this.a, AppbarActivity.class);
    localIntent.putExtra("appid", this.mToken.getAppId());
    if ((this.mToken != null) && (this.mToken.getAccessToken() != null) && (this.mToken.getOpenId() != null))
    {
      b.a locala = new b.a();
      locala.b = this.mToken.getAccessToken();
      locala.c = Long.parseLong(this.mToken.getAppId());
      locala.a = this.mToken.getOpenId();
      b.a(this.a, paramString, this.mToken.getOpenId(), this.mToken.getAccessToken(), this.mToken.getAppId());
    }
    localIntent.putExtra("url", paramString);
    localIntent.addFlags(268435456);
    d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar H5 : url = " + paramString);
    try
    {
      this.a.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar : activity not found, start H5");
    }
  }

  private Bundle c(String paramString)
  {
    Bundle localBundle = new Bundle();
    if (("siteIndex".equals(paramString)) || ("newThread".equals(paramString)))
      localBundle.putString("pkgName", this.mContext.getPackageName());
    while (true)
    {
      localBundle.putString("route", paramString);
      return localBundle;
      if (!"myMessage".equals(paramString))
        continue;
      localBundle.putString("source", "myapp");
    }
  }

  private String d(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("http://mq.wsq.qq.com/direct?");
    localStringBuilder.append(a(c(paramString)));
    return localStringBuilder.toString();
  }

  public void startAppbar(String paramString)
  {
    if (!a(paramString))
    {
      Toast.makeText(this.a, "传入参数有误!", 0).show();
      return;
    }
    String str1 = d(paramString);
    String str2 = b();
    if ((!TextUtils.isEmpty(str2)) && (SystemUtils.compareVersion(str2, "4.2") >= 0))
    {
      String str3 = str1 + a();
      d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar : yybUrl = " + str3);
      try
      {
        Intent localIntent = new Intent();
        localIntent.setClassName("com.tencent.android.qqdownloader", "com.tencent.assistant.activity.ExportBrowserActivity");
        localIntent.putExtra("com.tencent.assistant.BROWSER_URL", str3);
        localIntent.addFlags(268435456);
        this.a.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar : ExportBrowserActivity not found, start H5");
        b(str1);
        return;
      }
    }
    b(str1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.AppbarAgent
 * JD-Core Version:    0.6.0
 */