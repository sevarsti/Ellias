package com.tencent.open.yyb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AppbarActivity extends Activity
  implements View.OnClickListener
{
  private static final int FLOATING_DIALOG_HEIGHT = 100;
  public static final String MYAPP_CACHE_PATH = "/tencent/tassistant";
  private static final String UA_PREFIX = "qqdownloader/";
  private static final String WEBVIEW_PATH = "/webview_cache";
  private static ArrayList<String> specialModel = new ArrayList();
  private String appid;
  private AppbarJsBridge jsBridge;
  private final DownloadListener mDownloadListener = new c(this);
  private MoreFloatingDialog mFloatingDialog;
  protected ProgressDialog mProgressDialog;
  private LinearLayout mRootView;
  private TitleBar mTitleBar;
  private QQToken mToken;
  private WebView mWebView;
  private ShareModel model;
  private Tencent tencent;
  private int titlebarTop;
  private String url;

  static
  {
    specialModel.add("MT870");
    specialModel.add("XT910");
    specialModel.add("XT928");
    specialModel.add("MT917");
    specialModel.add("Lenovo A60");
  }

  private String buildTransaction(String paramString)
  {
    if (paramString == null)
      return String.valueOf(System.currentTimeMillis());
    return paramString + System.currentTimeMillis();
  }

  private void createViews()
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    this.mWebView.setLayoutParams(localLayoutParams);
    this.mRootView = new LinearLayout(this);
    localLayoutParams.gravity = 17;
    this.mRootView.setLayoutParams(localLayoutParams);
    this.mRootView.setOrientation(1);
    this.mTitleBar = new TitleBar(this);
    this.mTitleBar.getBackBtn().setOnClickListener(this);
    this.mTitleBar.getSharBtn().setOnClickListener(this);
    this.mRootView.addView(this.mTitleBar);
    this.mRootView.addView(this.mWebView);
    setContentView(this.mRootView);
  }

  private String getCommonPath(String paramString)
  {
    String str = getCommonRootDir();
    if (!TextUtils.isEmpty(paramString))
      str = str + paramString;
    return getPath(str, false);
  }

  private String getCommonRootDir()
  {
    if (isSDCardExistAndCanWrite());
    for (String str = Environment.getExternalStorageDirectory().getPath() + "/tencent/tassistant"; ; str = getFilesDir().getAbsolutePath() + "/tencent/tassistant")
    {
      File localFile = new File(str);
      if (!localFile.exists())
        localFile.mkdirs();
      return localFile.getAbsolutePath();
    }
  }

  private MoreFloatingDialog getFloatingDialg()
  {
    if (this.mFloatingDialog == null)
    {
      this.mFloatingDialog = new MoreFloatingDialog(this);
      this.mFloatingDialog.setCanceledOnTouchOutside(true);
      this.mFloatingDialog.getQQItem().setOnClickListener(this);
      this.mFloatingDialog.getQzoneItem().setOnClickListener(this);
    }
    return this.mFloatingDialog;
  }

  private String getPath(String paramString, boolean paramBoolean)
  {
    File localFile1 = new File(paramString);
    File localFile2;
    if (!localFile1.exists())
    {
      localFile1.mkdirs();
      if (paramBoolean)
        localFile2 = new File(paramString + File.separator + ".nomedia");
    }
    try
    {
      localFile2.createNewFile();
      return localFile1.getAbsolutePath();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  private Tencent getTencent()
  {
    if (this.tencent == null)
      this.tencent = Tencent.createInstance(this.appid, this);
    return this.tencent;
  }

  private int getTitbarTop()
  {
    Rect localRect = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.titlebarTop = (localDisplayMetrics.heightPixels - localRect.height());
    return this.titlebarTop;
  }

  private QQToken getToken()
  {
    if (this.mToken == null)
      this.mToken = getTencent().getQQToken();
    return this.mToken;
  }

  private String getWebViewCacheDir()
  {
    return getCommonPath("/webview_cache");
  }

  // ERROR //
  private void initViews()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   4: invokevirtual 344	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   7: astore_1
    //   8: aload_1
    //   9: iconst_1
    //   10: invokevirtual 349	android/webkit/WebSettings:setBuiltInZoomControls	(Z)V
    //   13: aload_1
    //   14: new 116	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   21: aload_1
    //   22: invokevirtual 352	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   25: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: ldc_w 354
    //   31: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: ldc 16
    //   36: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_0
    //   40: getfield 90	com/tencent/open/yyb/AppbarActivity:jsBridge	Lcom/tencent/open/yyb/AppbarJsBridge;
    //   43: invokevirtual 359	com/tencent/open/yyb/AppbarJsBridge:getVersion	()I
    //   46: invokevirtual 362	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   49: ldc_w 364
    //   52: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokevirtual 367	android/webkit/WebSettings:setUserAgentString	(Ljava/lang/String;)V
    //   61: aload_1
    //   62: iconst_1
    //   63: invokevirtual 370	android/webkit/WebSettings:setJavaScriptEnabled	(Z)V
    //   66: aload_1
    //   67: invokevirtual 376	java/lang/Object:getClass	()Ljava/lang/Class;
    //   70: astore_2
    //   71: iconst_1
    //   72: anewarray 378	java/lang/Class
    //   75: astore 32
    //   77: aload 32
    //   79: iconst_0
    //   80: getstatic 384	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   83: aastore
    //   84: aload_2
    //   85: ldc_w 386
    //   88: aload 32
    //   90: invokevirtual 390	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   93: astore 33
    //   95: aload 33
    //   97: ifnull +26 -> 123
    //   100: iconst_1
    //   101: anewarray 372	java/lang/Object
    //   104: astore 34
    //   106: aload 34
    //   108: iconst_0
    //   109: iconst_1
    //   110: invokestatic 393	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   113: aastore
    //   114: aload 33
    //   116: aload_1
    //   117: aload 34
    //   119: invokevirtual 399	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   122: pop
    //   123: iconst_1
    //   124: anewarray 378	java/lang/Class
    //   127: astore 27
    //   129: aload 27
    //   131: iconst_0
    //   132: getstatic 384	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   135: aastore
    //   136: aload_2
    //   137: ldc_w 401
    //   140: aload 27
    //   142: invokevirtual 390	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   145: astore 28
    //   147: aload 28
    //   149: ifnull +26 -> 175
    //   152: iconst_1
    //   153: anewarray 372	java/lang/Object
    //   156: astore 29
    //   158: aload 29
    //   160: iconst_0
    //   161: iconst_1
    //   162: invokestatic 393	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   165: aastore
    //   166: aload 28
    //   168: aload_1
    //   169: aload 29
    //   171: invokevirtual 399	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   174: pop
    //   175: aload_2
    //   176: ldc_w 403
    //   179: iconst_1
    //   180: anewarray 378	java/lang/Class
    //   183: dup
    //   184: iconst_0
    //   185: ldc 110
    //   187: aastore
    //   188: invokevirtual 390	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   191: astore 21
    //   193: aload 21
    //   195: ifnull +23 -> 218
    //   198: aload 21
    //   200: aload_0
    //   201: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   204: iconst_1
    //   205: anewarray 372	java/lang/Object
    //   208: dup
    //   209: iconst_0
    //   210: ldc_w 405
    //   213: aastore
    //   214: invokevirtual 399	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   217: pop
    //   218: aload_1
    //   219: aload_0
    //   220: invokespecial 407	com/tencent/open/yyb/AppbarActivity:getWebViewCacheDir	()Ljava/lang/String;
    //   223: invokevirtual 410	android/webkit/WebSettings:setAppCachePath	(Ljava/lang/String;)V
    //   226: aload_1
    //   227: aload_0
    //   228: invokespecial 407	com/tencent/open/yyb/AppbarActivity:getWebViewCacheDir	()Ljava/lang/String;
    //   231: invokevirtual 413	android/webkit/WebSettings:setDatabasePath	(Ljava/lang/String;)V
    //   234: aload_1
    //   235: iconst_1
    //   236: invokevirtual 416	android/webkit/WebSettings:setDatabaseEnabled	(Z)V
    //   239: aload_1
    //   240: iconst_1
    //   241: invokevirtual 419	android/webkit/WebSettings:setAppCacheEnabled	(Z)V
    //   244: aload_0
    //   245: invokespecial 422	com/tencent/open/yyb/AppbarActivity:supportWebViewFullScreen	()Z
    //   248: ifeq +127 -> 375
    //   251: aload_1
    //   252: iconst_1
    //   253: invokevirtual 425	android/webkit/WebSettings:setUseWideViewPort	(Z)V
    //   256: getstatic 430	android/os/Build$VERSION:SDK_INT	I
    //   259: bipush 7
    //   261: if_icmplt +50 -> 311
    //   264: iconst_1
    //   265: anewarray 378	java/lang/Class
    //   268: astore 17
    //   270: aload 17
    //   272: iconst_0
    //   273: getstatic 384	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   276: aastore
    //   277: aload_2
    //   278: ldc_w 432
    //   281: aload 17
    //   283: invokevirtual 390	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   286: astore 18
    //   288: iconst_1
    //   289: anewarray 372	java/lang/Object
    //   292: astore 19
    //   294: aload 19
    //   296: iconst_0
    //   297: iconst_1
    //   298: invokestatic 393	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   301: aastore
    //   302: aload 18
    //   304: aload_1
    //   305: aload 19
    //   307: invokevirtual 399	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   310: pop
    //   311: invokestatic 437	com/tencent/utils/SystemUtils:isSupportMultiTouch	()Z
    //   314: ifeq +61 -> 375
    //   317: invokestatic 440	com/tencent/utils/SystemUtils:getAndroidSDKVersion	()I
    //   320: bipush 11
    //   322: if_icmpge +146 -> 468
    //   325: ldc 136
    //   327: ldc_w 442
    //   330: invokevirtual 446	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   333: astore 14
    //   335: aload 14
    //   337: iconst_1
    //   338: invokevirtual 451	java/lang/reflect/Field:setAccessible	(Z)V
    //   341: new 453	android/widget/ZoomButtonsController
    //   344: dup
    //   345: aload_0
    //   346: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   349: invokespecial 455	android/widget/ZoomButtonsController:<init>	(Landroid/view/View;)V
    //   352: astore 15
    //   354: aload 15
    //   356: invokevirtual 458	android/widget/ZoomButtonsController:getZoomControls	()Landroid/view/View;
    //   359: bipush 8
    //   361: invokevirtual 461	android/view/View:setVisibility	(I)V
    //   364: aload 14
    //   366: aload_0
    //   367: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   370: aload 15
    //   372: invokevirtual 465	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   375: aload_0
    //   376: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   379: new 467	com/tencent/open/yyb/AppbarActivity$d
    //   382: dup
    //   383: aload_0
    //   384: aconst_null
    //   385: invokespecial 470	com/tencent/open/yyb/AppbarActivity$d:<init>	(Lcom/tencent/open/yyb/AppbarActivity;Lcom/tencent/open/yyb/AppbarActivity$2;)V
    //   388: invokevirtual 474	android/webkit/WebView:setWebViewClient	(Landroid/webkit/WebViewClient;)V
    //   391: aload_0
    //   392: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   395: new 476	com/tencent/open/yyb/AppbarActivity$c
    //   398: dup
    //   399: aload_0
    //   400: aconst_null
    //   401: invokespecial 477	com/tencent/open/yyb/AppbarActivity$c:<init>	(Lcom/tencent/open/yyb/AppbarActivity;Lcom/tencent/open/yyb/AppbarActivity$2;)V
    //   404: invokevirtual 481	android/webkit/WebView:setWebChromeClient	(Landroid/webkit/WebChromeClient;)V
    //   407: aload_0
    //   408: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   411: aload_0
    //   412: getfield 76	com/tencent/open/yyb/AppbarActivity:mDownloadListener	Landroid/webkit/DownloadListener;
    //   415: invokevirtual 485	android/webkit/WebView:setDownloadListener	(Landroid/webkit/DownloadListener;)V
    //   418: aload_0
    //   419: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   422: aload_0
    //   423: getfield 487	com/tencent/open/yyb/AppbarActivity:url	Ljava/lang/String;
    //   426: invokevirtual 490	android/webkit/WebView:loadUrl	(Ljava/lang/String;)V
    //   429: return
    //   430: astore 31
    //   432: aload 31
    //   434: invokevirtual 491	java/lang/NoSuchMethodException:printStackTrace	()V
    //   437: goto -314 -> 123
    //   440: astore_3
    //   441: aload_3
    //   442: invokevirtual 492	java/lang/Throwable:printStackTrace	()V
    //   445: goto -322 -> 123
    //   448: astore 26
    //   450: aload 26
    //   452: invokevirtual 493	java/lang/SecurityException:printStackTrace	()V
    //   455: goto -280 -> 175
    //   458: astore 5
    //   460: aload 5
    //   462: invokevirtual 494	java/lang/Exception:printStackTrace	()V
    //   465: goto -247 -> 218
    //   468: aload_0
    //   469: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   472: invokevirtual 344	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   475: invokevirtual 376	java/lang/Object:getClass	()Ljava/lang/Class;
    //   478: astore 7
    //   480: iconst_1
    //   481: anewarray 378	java/lang/Class
    //   484: astore 8
    //   486: aload 8
    //   488: iconst_0
    //   489: getstatic 384	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   492: aastore
    //   493: aload 7
    //   495: ldc_w 496
    //   498: aload 8
    //   500: invokevirtual 390	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   503: astore 9
    //   505: aload_0
    //   506: getfield 100	com/tencent/open/yyb/AppbarActivity:mWebView	Landroid/webkit/WebView;
    //   509: invokevirtual 344	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   512: astore 10
    //   514: iconst_1
    //   515: anewarray 372	java/lang/Object
    //   518: astore 11
    //   520: aload 11
    //   522: iconst_0
    //   523: iconst_0
    //   524: invokestatic 393	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   527: aastore
    //   528: aload 9
    //   530: aload 10
    //   532: aload 11
    //   534: invokevirtual 399	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   537: pop
    //   538: goto -163 -> 375
    //   541: astore 6
    //   543: goto -168 -> 375
    //   546: astore 13
    //   548: goto -173 -> 375
    //   551: astore 16
    //   553: goto -242 -> 311
    //   556: astore 25
    //   558: goto -383 -> 175
    //   561: astore 24
    //   563: goto -388 -> 175
    //   566: astore 23
    //   568: goto -393 -> 175
    //   571: astore 4
    //   573: goto -398 -> 175
    //
    // Exception table:
    //   from	to	target	type
    //   71	95	430	java/lang/NoSuchMethodException
    //   100	123	430	java/lang/NoSuchMethodException
    //   71	95	440	java/lang/Throwable
    //   100	123	440	java/lang/Throwable
    //   123	147	448	java/lang/SecurityException
    //   152	175	448	java/lang/SecurityException
    //   175	193	458	java/lang/Exception
    //   198	218	458	java/lang/Exception
    //   468	538	541	java/lang/Exception
    //   325	375	546	java/lang/Exception
    //   264	311	551	java/lang/Exception
    //   123	147	556	java/lang/reflect/InvocationTargetException
    //   152	175	556	java/lang/reflect/InvocationTargetException
    //   123	147	561	java/lang/IllegalAccessException
    //   152	175	561	java/lang/IllegalAccessException
    //   123	147	566	java/lang/IllegalArgumentException
    //   152	175	566	java/lang/IllegalArgumentException
    //   123	147	571	java/lang/NoSuchMethodException
    //   152	175	571	java/lang/NoSuchMethodException
  }

  private boolean isSDCardExistAndCanWrite()
  {
    try
    {
      boolean bool1 = "mounted".equals(Environment.getExternalStorageState());
      int i = 0;
      if (bool1)
      {
        boolean bool2 = Environment.getExternalStorageDirectory().canWrite();
        i = 0;
        if (bool2)
          i = 1;
      }
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private void setSupportZoom(boolean paramBoolean)
  {
    if (this.mWebView != null)
      this.mWebView.getSettings().setSupportZoom(paramBoolean);
  }

  private void shareToWX(boolean paramBoolean)
  {
    d.b("openSDK_LOG", "-->shareToWX : wx_appid = " + AppbarAgent.wx_appid);
    if (!TextUtils.isEmpty(this.model.c))
    {
      showProgressDialog(this, "", "");
      a locala = new a(new b()
      {
        public void a(byte[] paramArrayOfByte)
        {
          d.b("openSDK_LOG", "-->onImageDownloaded : result = " + paramArrayOfByte);
          AppbarActivity.this.mProgressDialog.dismiss();
        }
      });
      String[] arrayOfString = new String[1];
      arrayOfString[0] = this.model.c;
      locala.execute(arrayOfString);
    }
  }

  private boolean supportWebViewFullScreen()
  {
    String str = Build.MODEL;
    return (!str.contains("vivo")) && (!specialModel.contains(str));
  }

  public void login()
  {
    d.b("openSDK_LOG", "-->login : activity~~~");
    getTencent().login(this, "all", new IUiListener()
    {
      public void onCancel()
      {
        d.b("openSDK_LOG", "-->(AppbarJsBridge)openLoginActivity onCancel");
        AppbarActivity.this.jsBridge.responseFail("loginCallback", 0, null, -2);
      }

      // ERROR //
      public void onComplete(Object paramObject)
      {
        // Byte code:
        //   0: ldc 22
        //   2: ldc 48
        //   4: invokestatic 30	com/tencent/a/a/d:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   7: aload_1
        //   8: checkcast 50	org/json/JSONObject
        //   11: astore_2
        //   12: aload_2
        //   13: ldc 52
        //   15: iconst_m1
        //   16: invokevirtual 56	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
        //   19: ifeq +20 -> 39
        //   22: aload_0
        //   23: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   26: invokestatic 34	com/tencent/open/yyb/AppbarActivity:access$400	(Lcom/tencent/open/yyb/AppbarActivity;)Lcom/tencent/open/yyb/AppbarJsBridge;
        //   29: ldc 36
        //   31: iconst_0
        //   32: aconst_null
        //   33: bipush 251
        //   35: invokevirtual 42	com/tencent/open/yyb/AppbarJsBridge:responseFail	(Ljava/lang/String;ILjava/lang/String;I)V
        //   38: return
        //   39: aload_2
        //   40: ldc 58
        //   42: invokevirtual 62	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   45: astore 4
        //   47: aload_2
        //   48: ldc 64
        //   50: invokevirtual 62	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   53: astore 5
        //   55: aload_0
        //   56: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   59: invokestatic 68	com/tencent/open/yyb/AppbarActivity:access$500	(Lcom/tencent/open/yyb/AppbarActivity;)Lcom/tencent/connect/auth/QQToken;
        //   62: invokevirtual 74	com/tencent/connect/auth/QQToken:getAppId	()Ljava/lang/String;
        //   65: astore 6
        //   67: aload_0
        //   68: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   71: aload_0
        //   72: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   75: invokestatic 78	com/tencent/open/yyb/AppbarActivity:access$600	(Lcom/tencent/open/yyb/AppbarActivity;)Landroid/webkit/WebView;
        //   78: invokevirtual 83	android/webkit/WebView:getUrl	()Ljava/lang/String;
        //   81: aload 4
        //   83: aload 5
        //   85: aload 6
        //   87: invokestatic 88	com/tencent/open/yyb/b:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   90: new 50	org/json/JSONObject
        //   93: dup
        //   94: invokespecial 89	org/json/JSONObject:<init>	()V
        //   97: astore 7
        //   99: aload 7
        //   101: ldc 91
        //   103: ldc 93
        //   105: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   108: pop
        //   109: aload 7
        //   111: ldc 58
        //   113: aload 4
        //   115: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   118: pop
        //   119: aload 7
        //   121: ldc 99
        //   123: aload 5
        //   125: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   128: pop
        //   129: aload_0
        //   130: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   133: invokestatic 34	com/tencent/open/yyb/AppbarActivity:access$400	(Lcom/tencent/open/yyb/AppbarActivity;)Lcom/tencent/open/yyb/AppbarJsBridge;
        //   136: ldc 36
        //   138: iconst_0
        //   139: aconst_null
        //   140: aload 7
        //   142: invokevirtual 102	org/json/JSONObject:toString	()Ljava/lang/String;
        //   145: invokevirtual 106	com/tencent/open/yyb/AppbarJsBridge:response	(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
        //   148: return
        //   149: astore_3
        //   150: aload_0
        //   151: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   154: invokestatic 34	com/tencent/open/yyb/AppbarActivity:access$400	(Lcom/tencent/open/yyb/AppbarActivity;)Lcom/tencent/open/yyb/AppbarJsBridge;
        //   157: ldc 36
        //   159: iconst_0
        //   160: aconst_null
        //   161: bipush 251
        //   163: invokevirtual 42	com/tencent/open/yyb/AppbarJsBridge:responseFail	(Ljava/lang/String;ILjava/lang/String;I)V
        //   166: ldc 22
        //   168: ldc 108
        //   170: invokestatic 30	com/tencent/a/a/d:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   173: return
        //   174: astore 8
        //   176: aload_0
        //   177: getfield 17	com/tencent/open/yyb/AppbarActivity$2:a	Lcom/tencent/open/yyb/AppbarActivity;
        //   180: invokestatic 34	com/tencent/open/yyb/AppbarActivity:access$400	(Lcom/tencent/open/yyb/AppbarActivity;)Lcom/tencent/open/yyb/AppbarJsBridge;
        //   183: ldc 36
        //   185: iconst_0
        //   186: aconst_null
        //   187: bipush 251
        //   189: invokevirtual 42	com/tencent/open/yyb/AppbarJsBridge:responseFail	(Ljava/lang/String;ILjava/lang/String;I)V
        //   192: ldc 22
        //   194: ldc 110
        //   196: invokestatic 30	com/tencent/a/a/d:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   199: return
        //
        // Exception table:
        //   from	to	target	type
        //   39	67	149	org/json/JSONException
        //   99	129	174	org/json/JSONException
      }

      public void onError(UiError paramUiError)
      {
        d.b("openSDK_LOG", "-->(AppbarJsBridge)openLoginActivity onError");
        AppbarActivity.this.jsBridge.responseFail("loginCallback", 0, null, -5);
      }
    });
  }

  public void onBackPressed()
  {
    MoreFloatingDialog localMoreFloatingDialog = getFloatingDialg();
    if ((localMoreFloatingDialog != null) && (localMoreFloatingDialog.isShowing()))
    {
      localMoreFloatingDialog.dismiss();
      return;
    }
    super.onBackPressed();
  }

  public void onClick(View paramView)
  {
    MoreFloatingDialog localMoreFloatingDialog = getFloatingDialg();
    if (paramView == this.mTitleBar.getSharBtn())
      this.jsBridge.clickCallback();
    do
    {
      return;
      if (paramView == localMoreFloatingDialog.getQQItem())
      {
        shareToQQ();
        return;
      }
      if (paramView == localMoreFloatingDialog.getQzoneItem())
      {
        shareToQzone();
        return;
      }
      if (paramView == localMoreFloatingDialog.getWXItem())
      {
        shareToWX();
        return;
      }
      if (paramView != localMoreFloatingDialog.getTimelineItem())
        continue;
      shareToTimeline();
      return;
    }
    while (paramView != this.mTitleBar.getBackBtn());
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.appid = getIntent().getStringExtra("appid");
    this.url = getIntent().getStringExtra("url");
    d.b("openSDK_LOG", "-->(AppbarActivity)onCreate : appid = " + this.appid + " url = " + this.url);
    this.mWebView = new WebView(this);
    this.jsBridge = new AppbarJsBridge(this, this.mWebView);
    createViews();
    initViews();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mWebView != null)
    {
      this.mWebView.removeAllViews();
      this.mWebView.setVisibility(8);
      this.mWebView.stopLoading();
      this.mWebView.clearHistory();
      this.mWebView.destroy();
    }
  }

  protected void onPause()
  {
    super.onPause();
    MoreFloatingDialog localMoreFloatingDialog = getFloatingDialg();
    if ((localMoreFloatingDialog != null) && (localMoreFloatingDialog.isShowing()))
      localMoreFloatingDialog.dismiss();
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void setAppbarTitle(String paramString)
  {
    this.mTitleBar.setTitle(paramString);
  }

  public void setShareModel(ShareModel paramShareModel)
  {
    this.model = paramShareModel;
  }

  public void setShareVisibility(boolean paramBoolean)
  {
    ImageView localImageView = this.mTitleBar.getSharBtn();
    if (paramBoolean);
    for (int i = 0; ; i = 4)
    {
      localImageView.setVisibility(i);
      return;
    }
  }

  public void shareToQQ()
  {
    QQToken localQQToken = getToken();
    if (localQQToken == null)
      return;
    QQShare localQQShare = new QQShare(this, localQQToken);
    Bundle localBundle = new Bundle();
    localBundle.putString("title", this.model.a);
    localBundle.putString("targetUrl", this.model.d);
    localBundle.putString("summary", this.model.b);
    localBundle.putString("imageUrl", this.model.c);
    d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ : model.mTitle = " + this.model.a);
    d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ : model.mTargetUrl = " + this.model.d);
    d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ : model.mDescription = " + this.model.b);
    d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ : model.mIconUrl = " + this.model.c);
    localQQShare.shareToQQ(this, localBundle, new IUiListener(localQQToken)
    {
      public void onCancel()
      {
        d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ onCancel");
        AppbarActivity.this.jsBridge.responseShareFail(1);
      }

      public void onComplete(Object paramObject)
      {
        d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ onComplete");
        AppbarActivity.this.jsBridge.responseShare(1);
        b.a(this.a.getAppId(), "400", "SDK.APPBAR.HOME.SHARE.QQ");
      }

      public void onError(UiError paramUiError)
      {
        d.b("openSDK_LOG", "-->(AppbarActivity)shareToQQ onError");
        AppbarActivity.this.jsBridge.responseShareFail(1);
      }
    });
    b.a(localQQToken.getAppId(), "200", "SDK.APPBAR.HOME.SHARE.QQ");
  }

  public void shareToQzone()
  {
    QQToken localQQToken = getToken();
    if (localQQToken == null)
      return;
    QzoneShare localQzoneShare = new QzoneShare(this, localQQToken);
    Bundle localBundle = new Bundle();
    localBundle.putInt("req_type", 1);
    localBundle.putString("title", this.model.a);
    localBundle.putString("summary", this.model.b);
    localBundle.putString("targetUrl", this.model.d);
    ArrayList localArrayList = new ArrayList();
    d.b("openSDK_LOG", "-->shareToQzone : mIconUrl = " + this.model.c);
    localArrayList.add(this.model.c);
    localBundle.putStringArrayList("imageUrl", localArrayList);
    localQzoneShare.shareToQzone(this, localBundle, new IUiListener(localQQToken)
    {
      public void onCancel()
      {
        d.b("openSDK_LOG", "-->(AppbarActivity)shareToQzone onCancel");
        AppbarActivity.this.jsBridge.responseShareFail(2);
      }

      public void onComplete(Object paramObject)
      {
        d.b("openSDK_LOG", "-->(AppbarActivity)shareToQzone onComplete");
        AppbarActivity.this.jsBridge.responseShare(2);
        b.a(this.a.getAppId(), "400", "SDK.APPBAR.HOME.SHARE.QZ");
      }

      public void onError(UiError paramUiError)
      {
        d.b("openSDK_LOG", "-->(AppbarActivity)shareToQzone onError");
        AppbarActivity.this.jsBridge.responseShareFail(2);
      }
    });
    b.a(localQQToken.getAppId(), "200", "SDK.APPBAR.HOME.SHARE.QZ");
  }

  public void shareToTimeline()
  {
    shareToWX(true);
  }

  public void shareToWX()
  {
    shareToWX(false);
  }

  public void showFloatingDialog()
  {
    MoreFloatingDialog localMoreFloatingDialog = getFloatingDialg();
    localMoreFloatingDialog.show();
    Window localWindow = localMoreFloatingDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.gravity = 48;
    localLayoutParams.y = (getTitbarTop() + this.mTitleBar.getHeight());
    Display localDisplay = localMoreFloatingDialog.getWindow().getWindowManager().getDefaultDisplay();
    localLayoutParams.height = localMoreFloatingDialog.dip2px(100.0F);
    localLayoutParams.width = ((int)(0.95D * localDisplay.getWidth()) / 2);
    localLayoutParams.x = (localLayoutParams.width / 2);
    d.b("openSDK_LOG", "-->(AppbarDialog)showFloatingDialog : params.x = " + localLayoutParams.x);
    localWindow.setAttributes(localLayoutParams);
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

  private static class a extends AsyncTask<String, Void, byte[]>
  {
    private AppbarActivity.b a;

    public a(AppbarActivity.b paramb)
    {
      this.a = paramb;
    }

    protected void a(byte[] paramArrayOfByte)
    {
      super.onPostExecute(paramArrayOfByte);
      this.a.a(paramArrayOfByte);
    }

    // ERROR //
    protected byte[] a(String[] paramArrayOfString)
    {
      // Byte code:
      //   0: new 32	java/net/URL
      //   3: dup
      //   4: aload_1
      //   5: iconst_0
      //   6: aaload
      //   7: invokespecial 35	java/net/URL:<init>	(Ljava/lang/String;)V
      //   10: astore_2
      //   11: aload_2
      //   12: invokevirtual 39	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   15: checkcast 41	java/net/HttpURLConnection
      //   18: astore 4
      //   20: aload 4
      //   22: sipush 5000
      //   25: invokevirtual 45	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   28: aload 4
      //   30: ldc 47
      //   32: invokevirtual 50	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   35: aload 4
      //   37: invokevirtual 54	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   40: astore 7
      //   42: aload 4
      //   44: invokevirtual 58	java/net/HttpURLConnection:getResponseCode	()I
      //   47: sipush 200
      //   50: if_icmpne +54 -> 104
      //   53: new 60	java/io/ByteArrayOutputStream
      //   56: dup
      //   57: invokespecial 61	java/io/ByteArrayOutputStream:<init>	()V
      //   60: astore 9
      //   62: sipush 1024
      //   65: newarray byte
      //   67: astore 10
      //   69: aload 7
      //   71: aload 10
      //   73: invokevirtual 67	java/io/InputStream:read	([B)I
      //   76: istore 11
      //   78: iload 11
      //   80: iconst_m1
      //   81: if_icmpeq +59 -> 140
      //   84: aload 9
      //   86: aload 10
      //   88: iconst_0
      //   89: iload 11
      //   91: invokevirtual 71	java/io/ByteArrayOutputStream:write	([BII)V
      //   94: goto -25 -> 69
      //   97: astore 8
      //   99: aload 8
      //   101: invokevirtual 74	java/io/IOException:printStackTrace	()V
      //   104: aconst_null
      //   105: areturn
      //   106: astore 13
      //   108: aload 13
      //   110: invokevirtual 75	java/net/MalformedURLException:printStackTrace	()V
      //   113: aconst_null
      //   114: areturn
      //   115: astore_3
      //   116: aload_3
      //   117: invokevirtual 74	java/io/IOException:printStackTrace	()V
      //   120: aconst_null
      //   121: areturn
      //   122: astore 5
      //   124: aload 5
      //   126: invokevirtual 76	java/net/ProtocolException:printStackTrace	()V
      //   129: aconst_null
      //   130: areturn
      //   131: astore 6
      //   133: aload 6
      //   135: invokevirtual 74	java/io/IOException:printStackTrace	()V
      //   138: aconst_null
      //   139: areturn
      //   140: aload 9
      //   142: invokevirtual 79	java/io/ByteArrayOutputStream:close	()V
      //   145: aload 7
      //   147: invokevirtual 80	java/io/InputStream:close	()V
      //   150: aload 9
      //   152: invokevirtual 84	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   155: astore 12
      //   157: aload 12
      //   159: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   42	69	97	java/io/IOException
      //   69	78	97	java/io/IOException
      //   84	94	97	java/io/IOException
      //   140	157	97	java/io/IOException
      //   0	11	106	java/net/MalformedURLException
      //   11	20	115	java/io/IOException
      //   28	35	122	java/net/ProtocolException
      //   35	42	131	java/io/IOException
    }
  }

  private static abstract interface b
  {
    public abstract void a(byte[] paramArrayOfByte);
  }

  private class c extends WebChromeClient
  {
    private c()
    {
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      AppbarActivity.this.mTitleBar.setTitle(paramString);
    }
  }

  private class d extends WebViewClient
  {
    private d()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      AppbarActivity.this.setSupportZoom(true);
      AppbarActivity.this.jsBridge.ready();
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      AppbarActivity.this.setSupportZoom(false);
      if ((!paramString.startsWith("http")) && (paramString.startsWith("https")));
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      int i = 1;
      d.b("openSDK_LOG", "-->(AppbarDialog)shouldOverrideUrlLoading : url = " + paramString);
      if (TextUtils.isEmpty(paramString));
      do
      {
        return false;
        if ((paramString.startsWith("http")) || (paramString.startsWith("https")))
          return super.shouldOverrideUrlLoading(paramWebView, paramString);
        if (!paramString.startsWith("jsb://"))
          continue;
        AppbarActivity.this.jsBridge.invoke(paramString);
        return i;
      }
      while ((!paramString.equals("about:blank;")) && (!paramString.equals("about:blank")));
      if (Build.VERSION.SDK_INT < 11);
      while (true)
      {
        return i;
        i = 0;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.AppbarActivity
 * JD-Core Version:    0.6.0
 */