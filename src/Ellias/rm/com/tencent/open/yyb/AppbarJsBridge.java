package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.tencent.a.a.d;
import com.tencent.utils.Util;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppbarJsBridge
{
  public static final int AUTHORIZE_FAIL = -5;
  public static final String BUTTON_CLICK_CALLBACK_FUNCTION_NAME = "clickCallback";
  public static final String CALLBACK_LOGIN = "loginCallback";
  public static final String CALLBACK_SHARE = "shareCallback";
  public static final int Code_Java_Exception = -3;
  public static final int Code_None = -2;
  public static final int JSBRIDGE_VERSION = 1;
  public static final String JS_BRIDGE_SCHEME = "jsb://";
  public static final String READY_CALLBACK_FUNCTION_NAME = "readyCallback";
  public static final int Result_Fail = -1;
  public static final int Result_OK = 0;
  public static final int SHARE_QQ = 1;
  public static final int SHARE_QZ = 2;
  public static final int SHARE_TIMELINE = 4;
  public static final int SHARE_WX = 3;
  private WebView a;
  private Activity b;

  public AppbarJsBridge(Activity paramActivity, WebView paramWebView)
  {
    this.b = paramActivity;
    this.a = paramWebView;
  }

  private void a(Uri paramUri, String paramString1, int paramInt, String paramString2)
  {
    d.b("openSDK_LOG", "-->callAMethod : uri = " + paramUri);
    if (a(paramString1));
    do
      try
      {
        Class[] arrayOfClass = new Class[4];
        arrayOfClass[0] = Uri.class;
        arrayOfClass[1] = Integer.TYPE;
        arrayOfClass[2] = String.class;
        arrayOfClass[3] = String.class;
        Method localMethod = AppbarJsBridge.class.getDeclaredMethod(paramString1, arrayOfClass);
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramUri;
        arrayOfObject[1] = Integer.valueOf(paramInt);
        arrayOfObject[2] = paramString1;
        arrayOfObject[3] = paramString2;
        localMethod.invoke(this, arrayOfObject);
        return;
      }
      catch (Exception localException)
      {
        do
        {
          d.b("openSDK_LOG", "-->callAMethod : Exception = " + localException.getMessage());
          localException.printStackTrace();
        }
        while (TextUtils.isEmpty(paramString2));
        responseFail(paramString2, paramInt, paramString1, -3);
        return;
      }
    while (TextUtils.isEmpty(paramString2));
    responseFail(paramString2, paramInt, paramString1, -5);
  }

  private boolean a(String paramString)
  {
    return true;
  }

  public void callback(String paramString1, String paramString2)
  {
    if (this.a != null)
    {
      StringBuffer localStringBuffer = new StringBuffer("javascript:");
      localStringBuffer.append("if(!!").append(paramString1).append("){");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("(");
      localStringBuffer.append(paramString2);
      localStringBuffer.append(")}");
      this.a.loadUrl(localStringBuffer.toString());
    }
  }

  public void clickCallback()
  {
    response("clickCallback", 0, null, "");
  }

  public void closeWebView(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    d.b("openSDK_LOG", "-->closeWebView : url = " + paramUri);
    this.b.finish();
  }

  public void getAppInfo(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    String str1 = paramUri.getQueryParameter("packagenames");
    d.b("openSDK_LOG", "-->getAppInfo : packageNames = " + str1);
    if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(paramString2)));
    String[] arrayOfString;
    do
    {
      return;
      arrayOfString = str1.split(",");
    }
    while ((arrayOfString == null) || (arrayOfString.length == 0));
    JSONObject localJSONObject1 = new JSONObject();
    int i = arrayOfString.length;
    int j = 0;
    while (true)
      if (j < i)
      {
        String str2 = arrayOfString[j].trim();
        try
        {
          PackageInfo localPackageInfo2 = this.b.getPackageManager().getPackageInfo(str2, 0);
          localPackageInfo1 = localPackageInfo2;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          try
          {
            PackageInfo localPackageInfo1;
            label123: JSONObject localJSONObject2 = new JSONObject();
            if (localPackageInfo1 != null)
            {
              localJSONObject2.put("install", 1);
              localJSONObject2.put("appName", localPackageInfo1.applicationInfo.name);
              localJSONObject2.put("verCode", localPackageInfo1.versionCode);
              localJSONObject2.put("verName", localPackageInfo1.versionName);
            }
            while (true)
            {
              localJSONObject1.put(str2, localJSONObject2);
              j++;
              break;
              localNameNotFoundException = localNameNotFoundException;
              d.b("openSDK_LOG", "-->getAppInfo : NameNotFoundException e1");
              localPackageInfo1 = null;
              break label123;
              localJSONObject2.put("install", 0);
            }
          }
          catch (Exception localException)
          {
            while (true)
              responseFail(paramString2, paramInt, paramString1, -3);
          }
        }
      }
    d.b("openSDK_LOG", "-->getAppInfo : result = " + localJSONObject1.toString());
    response(paramString2, paramInt, paramString1, localJSONObject1.toString());
  }

  public int getVersion()
  {
    return 1;
  }

  public void invoke(String paramString)
  {
    d.b("openSDK_LOG", "-->invoke : url = " + paramString);
    Uri localUri = Uri.parse(paramString);
    String str1 = localUri.getHost();
    d.b("openSDK_LOG", "-->invoke : hostAsMethodName = " + str1);
    if (TextUtils.isEmpty(str1))
      return;
    List localList = localUri.getPathSegments();
    int i;
    String str2;
    if ((localList != null) && (localList.size() > 0))
    {
      i = Util.parseIntValue((String)localList.get(0));
      int n = localList.size();
      str2 = null;
      if (n > 1)
        str2 = (String)localList.get(1);
    }
    while (true)
    {
      d.b("openSDK_LOG", "-->invoke : seqid = " + i + " callbackName = " + str2);
      if (str1.equals("callBatch"))
        while (true)
        {
          int k;
          String str3;
          int m;
          String str4;
          StringBuilder localStringBuilder1;
          try
          {
            JSONArray localJSONArray = new JSONArray(localUri.getQueryParameter("param"));
            int j = localJSONArray.length();
            k = 0;
            if (k >= j)
              break;
            JSONObject localJSONObject1 = localJSONArray.getJSONObject(k);
            str3 = localJSONObject1.getString("method");
            m = localJSONObject1.getInt("seqid");
            str4 = localJSONObject1.optString("callback");
            JSONObject localJSONObject2 = localJSONObject1.getJSONObject("args");
            localStringBuilder1 = new StringBuilder();
            StringBuilder localStringBuilder2 = localStringBuilder1.append("jsb://").append(str3).append("/").append(m).append("/");
            if (TextUtils.isEmpty(str4))
              break label428;
            str5 = str4;
            localStringBuilder2.append(str5).append("?");
            if (localJSONObject2 == null)
              break label435;
            Iterator localIterator = localJSONObject2.keys();
            if (!localIterator.hasNext())
              break label435;
            String str6 = (String)localIterator.next();
            String str7 = Uri.decode(localJSONObject2.getString(str6));
            localStringBuilder1.append(str6).append("=").append(Uri.encode(str7)).append("&");
            continue;
          }
          catch (Exception localException)
          {
          }
          if (TextUtils.isEmpty(str2))
            break;
          responseFail(str2, i, str1, -5);
          return;
          label428: String str5 = "";
          continue;
          label435: a(Uri.parse(localStringBuilder1.toString()), str3, m, str4);
          k++;
        }
      a(localUri, str1, i, str2);
      return;
      str2 = null;
      i = 0;
    }
  }

  public void openLoginActivity(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    d.b("openSDK_LOG", "-->openLoginActivity : url = " + paramUri);
    ((AppbarActivity)this.b).login();
  }

  public void openNewWindow(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    String str = paramUri.getQueryParameter("url");
    try
    {
      Intent localIntent = new Intent(this.b, AppbarActivity.class);
      localIntent.putExtra("url", str);
      this.b.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void pageControl(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    d.b("openSDK_LOG", "-->pageControl : url = " + paramUri);
    int i = Util.parseIntValue(paramUri.getQueryParameter("type"));
    if (this.a != null)
    {
      if (i != 1)
        break label68;
      this.a.goBack();
    }
    while (true)
    {
      response(paramString2, paramInt, paramString1, "");
      return;
      label68: if (i == 2)
      {
        this.a.goForward();
        continue;
      }
      this.a.reload();
    }
  }

  public void ready()
  {
    response("readyCallback", 1, null, "true");
  }

  public void response(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    response(paramString1, paramInt, paramString2, paramString3, null);
  }

  public void response(String paramString1, int paramInt, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("result", 0);
      localJSONObject.put("data", paramString3);
      if (!TextUtils.isEmpty(paramString2))
        localJSONObject.put("method", paramString2);
      localJSONObject.put("seqid", paramInt);
      if (paramMap != null)
      {
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localJSONObject.put(str, paramMap.get(str));
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    callback(paramString1, localJSONObject.toString());
  }

  public void responseFail(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("result", -1);
      localJSONObject.put("code", paramInt2);
      localJSONObject.put("method", paramString2);
      localJSONObject.put("seqid", paramInt1);
      callback(paramString1, localJSONObject.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void responseShare(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("type", paramInt + "");
    response("shareCallback", 0, null, "0", localHashMap);
  }

  public void responseShareFail(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("type", paramInt + "");
    response("shareCallback", 0, null, "1", localHashMap);
  }

  public void setWebView(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    int i = 1;
    try
    {
      String str = paramUri.getQueryParameter("title");
      int j = Util.parseIntValue(paramUri.getQueryParameter("buttonVisible"), 0);
      if (!TextUtils.isEmpty(str))
        ((AppbarActivity)this.b).setAppbarTitle(str);
      AppbarActivity localAppbarActivity = (AppbarActivity)this.b;
      if (j == i);
      while (true)
      {
        localAppbarActivity.setShareVisibility(i);
        d.b("openSDK_LOG", "-->setWebView : url = " + paramUri + " -- buttonVisiable = " + j);
        response(paramString2, paramInt, paramString1, "");
        return;
        i = 0;
      }
    }
    catch (Exception localException)
    {
      responseFail(paramString2, paramInt, paramString1, -3);
    }
  }

  public void share(Uri paramUri, int paramInt, String paramString1, String paramString2)
  {
    d.b("openSDK_LOG", "-->share : url = " + paramUri);
    String str1 = paramUri.getQueryParameter("title");
    String str2 = paramUri.getQueryParameter("summary");
    String str3 = paramUri.getQueryParameter("iconUrl");
    if (TextUtils.isEmpty(str3))
      str3 = "http://qzs.qq.com/open/mobile/jsbridge/demo.htm";
    String str4 = paramUri.getQueryParameter("jumpUrl");
    d.b("openSDK_LOG", "-->share : title = " + str1);
    d.b("openSDK_LOG", "-->share : summary = " + str2);
    d.b("openSDK_LOG", "-->share : iconUrl = " + str3);
    d.b("openSDK_LOG", "-->share : jumpUrl = " + str4);
    ShareModel localShareModel = new ShareModel();
    localShareModel.a = str1;
    localShareModel.b = str2;
    localShareModel.c = str3;
    localShareModel.d = str4;
    ((AppbarActivity)this.b).setShareModel(localShareModel);
    switch (Util.parseIntValue(paramUri.getQueryParameter("type"), 0))
    {
    default:
      ((AppbarActivity)this.b).showFloatingDialog();
      return;
    case 1:
      ((AppbarActivity)this.b).shareToQQ();
      return;
    case 2:
      ((AppbarActivity)this.b).shareToQzone();
      return;
    case 3:
      ((AppbarActivity)this.b).shareToWX();
      return;
    case 4:
    }
    ((AppbarActivity)this.b).shareToTimeline();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.AppbarJsBridge
 * JD-Core Version:    0.6.0
 */