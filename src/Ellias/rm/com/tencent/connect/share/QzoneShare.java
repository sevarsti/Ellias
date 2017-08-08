package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.AsynLoadImgBack;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.TemporaryStorage;
import com.tencent.utils.Util;
import java.net.URLEncoder;
import java.util.ArrayList;

public class QzoneShare extends BaseApi
{
  public static final String SHARE_TO_QQ_APP_NAME = "appName";
  public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
  public static final String SHARE_TO_QQ_EXT_INT = "cflag";
  public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
  public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
  public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
  public static final String SHARE_TO_QQ_SITE = "site";
  public static final String SHARE_TO_QQ_SUMMARY = "summary";
  public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
  public static final String SHARE_TO_QQ_TITLE = "title";
  public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
  public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
  public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
  public static final int SHARE_TO_QZONE_TYPE_NO_TYPE;
  private boolean a = true;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;

  public QzoneShare(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  private StringBuffer a(StringBuffer paramStringBuffer, Bundle paramBundle)
  {
    d.a("openSDK_LOG", "fillShareToQQParams() --start");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str1 = paramBundle.getString("appName");
    int i = paramBundle.getInt("req_type", 1);
    String str2 = paramBundle.getString("title");
    String str3 = paramBundle.getString("summary");
    paramBundle.putString("appId", this.mToken.getAppId());
    paramBundle.putString("sdkp", "a");
    paramBundle.putString("sdkv", "2.3");
    paramBundle.putString("status_os", Build.VERSION.RELEASE);
    paramBundle.putString("status_machine", Build.MODEL);
    if ((!Util.isEmpty(str2)) && (str2.length() > 40))
      paramBundle.putString("title", str2.substring(0, 40) + "...");
    if ((!Util.isEmpty(str3)) && (str3.length() > 80))
      paramBundle.putString("summary", str3.substring(0, 80) + "...");
    if (!TextUtils.isEmpty(str1))
      paramBundle.putString("site", str1);
    if (localArrayList != null)
    {
      int j = localArrayList.size();
      String[] arrayOfString = new String[j];
      for (int k = 0; k < j; k++)
        arrayOfString[k] = ((String)localArrayList.get(k));
      paramBundle.putStringArray("imageUrl", arrayOfString);
    }
    paramBundle.putString("type", String.valueOf(i));
    String str4 = Util.encodeUrl(paramBundle);
    paramStringBuffer.append("&" + str4.replaceAll("\\+", "%20"));
    d.a("openSDK_LOG", "fillShareToQQParams() --end");
    return paramStringBuffer;
  }

  private void a(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    d.a("openSDK_LOG", "doShareToQQ() --start");
    StringBuffer localStringBuffer1 = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str1 = paramBundle.getString("title");
    String str2 = paramBundle.getString("summary");
    String str3 = paramBundle.getString("targetUrl");
    String str4 = paramBundle.getString("audio_url");
    int i = paramBundle.getInt("req_type", 1);
    String str5 = paramBundle.getString("appName");
    int j = paramBundle.getInt("cflag", 0);
    String str6 = paramBundle.getString("share_qq_ext_str");
    String str7 = this.mToken.getAppId();
    String str8 = this.mToken.getOpenId();
    Log.v("shareToQQ", "openId:" + str8);
    if (localArrayList != null)
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      if (localArrayList.size() > 9);
      for (int k = 9; ; k = localArrayList.size())
        for (int m = 0; m < k; m++)
        {
          localStringBuffer2.append(URLEncoder.encode((String)localArrayList.get(m)));
          if (m == k - 1)
            continue;
          localStringBuffer2.append(";");
        }
      localStringBuffer1.append("&image_url=" + Base64.encodeToString(localStringBuffer2.toString().getBytes(), 2));
    }
    if (!TextUtils.isEmpty(str1))
      localStringBuffer1.append("&title=" + Base64.encodeToString(str1.getBytes(), 2));
    if (!TextUtils.isEmpty(str2))
      localStringBuffer1.append("&description=" + Base64.encodeToString(str2.getBytes(), 2));
    if (!TextUtils.isEmpty(str7))
      localStringBuffer1.append("&share_id=" + str7);
    if (!TextUtils.isEmpty(str3))
      localStringBuffer1.append("&url=" + Base64.encodeToString(str3.getBytes(), 2));
    if (!TextUtils.isEmpty(str5))
      localStringBuffer1.append("&app_name=" + Base64.encodeToString(str5.getBytes(), 2));
    if (!Util.isEmpty(str8))
      localStringBuffer1.append("&open_id=" + Base64.encodeToString(str8.getBytes(), 2));
    if (!Util.isEmpty(str4))
      localStringBuffer1.append("&audioUrl=" + Base64.encodeToString(str4.getBytes(), 2));
    localStringBuffer1.append("&req_type=" + Base64.encodeToString(String.valueOf(i).getBytes(), 2));
    if (!Util.isEmpty(str6))
      localStringBuffer1.append("&share_qq_ext_str=" + Base64.encodeToString(str6.getBytes(), 2));
    localStringBuffer1.append("&cflag=" + Base64.encodeToString(String.valueOf(j).getBytes(), 2));
    Log.v("shareToQQ", localStringBuffer1.toString());
    com.tencent.connect.a.a.a(this.mContext, this.mToken, "requireApi", new String[] { "shareToNativeQQ" });
    this.mActivityIntent = new Intent("android.intent.action.VIEW");
    this.mActivityIntent.setData(Uri.parse(localStringBuffer1.toString()));
    if (SystemUtils.compareQQVersion(paramActivity, "4.6.0") < 0)
      if (hasActivityForIntent())
        startAssitActivity(paramActivity, paramIUiListener);
    while (true)
    {
      d.a("openSDK_LOG", "doShareToQQ() --end");
      return;
      Object localObject = TemporaryStorage.set("shareToQzone", paramIUiListener);
      if (localObject != null)
        ((IUiListener)localObject).onCancel();
      if (!hasActivityForIntent())
        continue;
      paramActivity.startActivityForResult(this.mActivityIntent, 0);
    }
  }

  private void a(Context paramContext, Bundle paramBundle, IUiListener paramIUiListener)
  {
    Object localObject = TemporaryStorage.set("shareToQzone", paramIUiListener);
    if (localObject != null)
      ((IUiListener)localObject).onCancel();
    d.a("openSDK_LOG", "shareToH5Qzone() --start");
    StringBuffer localStringBuffer1 = new StringBuffer("http://openmobile.qq.com/api/check2?page=qzshare.html&loginpage=loginindex.html&logintype=qzone");
    if (paramBundle == null)
      paramBundle = new Bundle();
    StringBuffer localStringBuffer2 = a(localStringBuffer1, paramBundle);
    com.tencent.connect.a.a.a(this.mContext, this.mToken, "requireApi", new String[] { "shareToH5QQ" });
    if ((!Util.openBrowser(paramContext, localStringBuffer2.toString())) && (paramIUiListener != null))
      paramIUiListener.onError(new UiError(-6, "打开浏览器失败!", null));
    d.a("openSDK_LOG", "shareToH5QQ() --end");
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public void shareToQzone(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (paramBundle == null)
    {
      paramIUiListener.onError(new UiError(-6, "传入参数不可以为空", null));
      return;
    }
    String str1 = paramBundle.getString("title");
    String str2 = paramBundle.getString("summary");
    String str3 = paramBundle.getString("targetUrl");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str4 = Util.getApplicationLable(paramActivity);
    int i;
    if (str4 == null)
    {
      str4 = paramBundle.getString("appName");
      i = paramBundle.getInt("req_type");
      switch (i)
      {
      default:
        if ((Util.isEmpty(str1)) && (Util.isEmpty(str2)))
        {
          if ((localArrayList == null) || (localArrayList.size() == 0))
            break;
          this.a = false;
        }
      case 1:
      case 5:
      }
    }
    while (true)
    {
      this.b = false;
      this.c = true;
      for (this.d = false; ; this.d = false)
      {
        if ((Util.hasSDCard()) || (SystemUtils.compareQQVersion(paramActivity, "4.5.0") >= 0))
          break label342;
        paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
        d.a("openSDK_LOG", "shareToQzone() sdcard is null--end");
        return;
        if (str4.length() <= 20)
          break;
        str4 = str4.substring(0, 20) + "...";
        break;
        this.a = true;
        this.b = false;
        this.c = true;
      }
      paramIUiListener.onError(new UiError(-5, "暂不支持纯图片分享到空间，建议使用图文分享", null));
      d.a("openSDK_LOG", "shareToQzone() error--end暂不支持纯图片分享到空间，建议使用图文分享");
      return;
      str1 = "来自" + str4 + "的分享";
      this.a = true;
      continue;
      this.a = true;
    }
    label342: if (this.a)
    {
      if (TextUtils.isEmpty(str3))
      {
        paramIUiListener.onError(new UiError(-5, "targetUrl为必填项，请补充后分享", null));
        d.a("openSDK_LOG", "shareToQzone() targetUrl null error--end");
        return;
      }
      if (!Util.isValidUrl(str3))
      {
        paramIUiListener.onError(new UiError(-5, "targetUrl有误", null));
        d.a("openSDK_LOG", "shareToQzone() targetUrl error--end");
        return;
      }
    }
    if (this.b)
    {
      paramBundle.putString("title", "");
      paramBundle.putString("summary", "");
    }
    while (true)
    {
      if (!TextUtils.isEmpty(str4))
        paramBundle.putString("appName", str4);
      if ((localArrayList == null) || ((localArrayList != null) && (localArrayList.size() == 0)))
      {
        if (!this.d)
          break;
        paramIUiListener.onError(new UiError(-6, "纯图分享，imageUrl 不能为空", null));
        d.a("openSDK_LOG", "shareToQzone() imageUrl is null--end");
        return;
        if ((this.c) && (Util.isEmpty(str1)))
        {
          paramIUiListener.onError(new UiError(-6, "title不能为空!", null));
          d.a("openSDK_LOG", "shareToQzone() title is null--end");
          return;
        }
        if ((!Util.isEmpty(str1)) && (str1.length() > 200))
          paramBundle.putString("title", Util.subString(str1, 200, null, null));
        if ((Util.isEmpty(str2)) || (str2.length() <= 600))
          continue;
        paramBundle.putString("summary", Util.subString(str2, 600, null, null));
        continue;
      }
      else
      {
        for (int j = 0; j < localArrayList.size(); j++)
        {
          String str6 = (String)localArrayList.get(j);
          if ((Util.isValidUrl(str6)) || (Util.isValidPath(str6)))
            continue;
          localArrayList.remove(j);
        }
        if (localArrayList.size() == 0)
        {
          paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
          d.a("openSDK_LOG", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
          return;
        }
        paramBundle.putStringArrayList("imageUrl", localArrayList);
      }
    }
    if (SystemUtils.compareQQVersion(paramActivity, "4.6.0") >= 0)
      a.a(paramActivity, localArrayList, new AsynLoadImgBack(paramBundle, paramActivity, paramIUiListener)
      {
        public void batchSaved(int paramInt, ArrayList<String> paramArrayList)
        {
          if (paramInt == 0)
            this.a.putStringArrayList("imageUrl", paramArrayList);
          QzoneShare.a(QzoneShare.this, this.b, this.a, this.c);
        }

        public void saved(int paramInt, String paramString)
        {
        }
      });
    while (true)
    {
      d.a("openSDK_LOG", "shareToQzone() --end");
      return;
      if ((SystemUtils.compareQQVersion(paramActivity, "4.2.0") >= 0) && (SystemUtils.compareQQVersion(paramActivity, "4.6.0") < 0))
      {
        QQShare localQQShare = new QQShare(paramActivity, this.mToken);
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          String str5 = (String)localArrayList.get(0);
          if ((i == 5) && (!Util.fileExists(str5)))
          {
            paramIUiListener.onError(new UiError(-6, "手Q版本过低，纯图分享不支持网路图片", null));
            d.a("openSDK_LOG", "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
            return;
          }
          paramBundle.putString("imageLocalUrl", str5);
        }
        if (SystemUtils.compareQQVersion(paramActivity, "4.5.0") >= 0)
          paramBundle.putInt("cflag", 1);
        localQQShare.shareToQQ(paramActivity, paramBundle, paramIUiListener);
        continue;
      }
      a(paramActivity, paramBundle, paramIUiListener);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.share.QzoneShare
 * JD-Core Version:    0.6.0
 */