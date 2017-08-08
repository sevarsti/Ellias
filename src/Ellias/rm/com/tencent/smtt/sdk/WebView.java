package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslCertificate;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView.FindListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.tencent.smtt.export.external.WebViewWizardBase;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebSettingsExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase.FindListener;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase.HitTestResult;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase.PictureListener;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.sdk.stat.HttpUtils;
import com.tencent.smtt.sdk.tips.TipsManager;
import com.tencent.smtt.utils.ReflectionUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class WebView extends FrameLayout
{
  public static final int NIGHT_MODE_ALPHA = 128;
  public static final int NIGHT_MODE_COLOR = -16777216;
  public static final int NORMAL_MODE_ALPHA = 255;
  public static final String SCHEME_GEO = "geo:0,0?q=";
  public static final String SCHEME_MAILTO = "mailto:";
  public static final String SCHEME_TEL = "tel:";
  private static int X5Version = 0;
  private static boolean mIsDayMode;
  private static Paint mNightModePaint = null;
  private boolean isX5Core = false;
  private Context mContext = null;
  private boolean mIsReported = false;
  int mPv = 0;
  ImageView mSplashLogo;
  private SystemWebView mSysWebView;
  private WebSettings mWebSettings = null;
  private IX5WebViewBase mX5WebView;

  static
  {
    mIsDayMode = true;
  }

  public WebView(Context paramContext)
  {
    this(paramContext, null);
  }

  public WebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public WebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, false);
  }

  public WebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, Map<String, Object> paramMap, boolean paramBoolean)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (paramContext == null)
      throw new IllegalArgumentException("Invalid context argument");
    init(paramContext);
    this.mContext = paramContext;
    if (this.isX5Core)
    {
      this.mX5WebView = SDKEngine.getInstance(true).wizard().createSDKWebview(paramContext);
      if ((this.mX5WebView == null) || (this.mX5WebView.getView() == null))
      {
        this.mX5WebView = null;
        this.isX5Core = false;
        QbSdk.forceSysWebView(true);
        init(paramContext);
        this.mSysWebView = new SystemWebView(paramContext);
        this.mSysWebView.setFocusableInTouchMode(true);
        addView(this.mSysWebView, new FrameLayout.LayoutParams(-1, -1));
      }
      do
      {
        return;
        this.mX5WebView.getView().setFocusableInTouchMode(true);
        addView(this.mX5WebView.getView(), new FrameLayout.LayoutParams(-1, -1));
        showSplashLogo();
        this.mX5WebView.setDownloadListener(new DownLoadListenerAdapter(this, null, this.isX5Core));
        this.mX5WebView.getX5WebViewExtension().setWebViewClientExtension(new X5ProxyWebViewClientExtension(SDKEngine.getInstance(false).wizard())
        {
          public void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
          {
            super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
            WebView.this.onScrollChanged(paramInt3, paramInt4, paramInt1, paramInt2);
          }
        });
      }
      while (X5Version != 0);
      X5Version = this.mX5WebView.getX5WebViewExtension().getQQBrowserVersion();
      return;
    }
    this.mSysWebView = new SystemWebView(paramContext);
    this.mSysWebView.setFocusableInTouchMode(true);
    addView(this.mSysWebView, new FrameLayout.LayoutParams(-1, -1));
  }

  public WebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean)
  {
    this(paramContext, paramAttributeSet, paramInt, null, paramBoolean);
  }

  @Deprecated
  public static void disablePlatformNotifications()
  {
    if ((SDKEngine.getInstance(false) == null) || (!SDKEngine.getInstance(false).isX5Core()))
      ReflectionUtils.invokeStatic("android.webkit.WebView", "disablePlatformNotifications");
  }

  @Deprecated
  public static void enablePlatformNotifications()
  {
    if ((SDKEngine.getInstance(false) == null) || (!SDKEngine.getInstance(false).isX5Core()))
      ReflectionUtils.invokeStatic("android.webkit.WebView", "enablePlatformNotifications");
  }

  public static String findAddress(String paramString)
  {
    if (!SDKEngine.getInstance(false).isX5Core())
      return android.webkit.WebView.findAddress(paramString);
    return null;
  }

  @Deprecated
  public static Object getPluginList()
  {
    monitorenter;
    try
    {
      if ((SDKEngine.getInstance(false) != null) && (!SDKEngine.getInstance(false).isX5Core()))
      {
        Object localObject3 = ReflectionUtils.invokeStatic("android.webkit.WebView", "getPluginList");
        localObject2 = localObject3;
        return localObject2;
      }
      Object localObject2 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public static int getQQBrowserCoreVersion()
  {
    return SDKEngine.getQQBrowserCoreVersion();
  }

  public static int getQQBrowserVersion()
  {
    return X5Version;
  }

  private void init(Context paramContext)
  {
    SDKEngine localSDKEngine = SDKEngine.getInstance(true);
    localSDKEngine.init(paramContext);
    this.isX5Core = localSDKEngine.isX5Core();
  }

  public static void setSysDayOrNight(boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = mIsDayMode;
        if (paramBoolean == bool)
          return;
        mIsDayMode = paramBoolean;
        if (mNightModePaint != null)
          continue;
        mNightModePaint = new Paint();
        mNightModePaint.setColor(-16777216);
        if (!paramBoolean)
        {
          if (mNightModePaint.getAlpha() == 128)
            continue;
          mNightModePaint.setAlpha(128);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (mNightModePaint.getAlpha() == 255)
        continue;
      mNightModePaint.setAlpha(255);
    }
  }

  private void showSplashLogo()
  {
    try
    {
      Context localContext = getContext().createPackageContext("com.tencent.mtt", 2);
      int i = localContext.getResources().getIdentifier("thrdcall_window_bg_normal", "drawable", "com.tencent.mtt");
      try
      {
        Bitmap localBitmap2 = BitmapFactory.decodeResource(localContext.getResources(), i, null);
        localBitmap1 = localBitmap2;
        if (localBitmap1 != null)
        {
          this.mSplashLogo = new ImageView(getContext());
          this.mSplashLogo.setImageBitmap(localBitmap1);
          FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
          localLayoutParams.gravity = 17;
          addView(this.mSplashLogo, localLayoutParams);
          return;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        while (true)
        {
          localOutOfMemoryError.printStackTrace();
          Bitmap localBitmap1 = null;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void addJavascriptInterface(Object paramObject, String paramString)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.addJavascriptInterface(paramObject, paramString);
      return;
    }
    this.mX5WebView.addJavascriptInterface(paramObject, paramString);
  }

  public boolean canGoBack()
  {
    if (!this.isX5Core)
      return this.mSysWebView.canGoBack();
    return this.mX5WebView.canGoBack();
  }

  public boolean canGoBackOrForward(int paramInt)
  {
    if (!this.isX5Core)
      return this.mSysWebView.canGoBackOrForward(paramInt);
    return this.mX5WebView.canGoBackOrForward(paramInt);
  }

  public boolean canGoForward()
  {
    if (!this.isX5Core)
      return this.mSysWebView.canGoForward();
    return this.mX5WebView.canGoForward();
  }

  @Deprecated
  public boolean canZoomIn()
  {
    if (!this.isX5Core)
    {
      Object localObject;
      if (Build.VERSION.SDK_INT >= 11)
      {
        localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "canZoomIn");
        if (localObject != null);
      }
      else
      {
        return false;
      }
      return ((Boolean)localObject).booleanValue();
    }
    return this.mX5WebView.canZoomIn();
  }

  @Deprecated
  public boolean canZoomOut()
  {
    if (!this.isX5Core)
    {
      Object localObject;
      if (Build.VERSION.SDK_INT >= 11)
      {
        localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "canZoomOut");
        if (localObject != null);
      }
      else
      {
        return false;
      }
      return ((Boolean)localObject).booleanValue();
    }
    return this.mX5WebView.canZoomOut();
  }

  @Deprecated
  public Picture capturePicture()
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "capturePicture");
      if (localObject == null)
        return null;
      return (Picture)localObject;
    }
    return this.mX5WebView.capturePicture();
  }

  public void clearCache(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.clearCache(paramBoolean);
      return;
    }
    this.mX5WebView.clearCache(paramBoolean);
  }

  public void clearFormData()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.clearFormData();
      return;
    }
    this.mX5WebView.clearFormData();
  }

  public void clearHistory()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.clearHistory();
      return;
    }
    this.mX5WebView.clearHistory();
  }

  @TargetApi(3)
  public void clearMatches()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.clearMatches();
      return;
    }
    this.mX5WebView.clearMatches();
  }

  public void clearSslPreferences()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.clearSslPreferences();
      return;
    }
    this.mX5WebView.clearSslPreferences();
  }

  @Deprecated
  public void clearView()
  {
    if (!this.isX5Core)
    {
      ReflectionUtils.invokeInstance(this.mSysWebView, "clearView");
      return;
    }
    this.mX5WebView.clearView();
  }

  public void computeScroll()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.computeScroll();
      return;
    }
    this.mX5WebView.computeScroll();
  }

  public WebBackForwardList copyBackForwardList()
  {
    if (this.isX5Core)
      return WebBackForwardList.wrap(this.mX5WebView.copyBackForwardList());
    return WebBackForwardList.wrap(this.mSysWebView.copyBackForwardList());
  }

  public void destroy()
  {
    TipsManager.destroy();
    if (!this.mIsReported)
    {
      String str1 = "";
      String str2 = "";
      String str3 = "";
      if (this.isX5Core)
      {
        Bundle localBundle = this.mX5WebView.getX5WebViewExtension().getSdkQBStatisticsInfo();
        if (localBundle != null)
        {
          str1 = localBundle.getString("guid");
          str2 = localBundle.getString("qua");
          str3 = localBundle.getString("lc");
        }
      }
      HttpUtils.doReport(this.mContext, str1, str2, str3, this.mPv, this.isX5Core);
      this.mIsReported = true;
    }
    if (!this.isX5Core);
    try
    {
      Class localClass1 = Class.forName("android.webkit.WebViewClassic");
      Method localMethod = localClass1.getMethod("fromWebView", new Class[] { android.webkit.WebView.class });
      localMethod.setAccessible(true);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mSysWebView;
      Object localObject3 = localMethod.invoke(null, arrayOfObject);
      if (localObject3 != null)
      {
        Field localField3 = localClass1.getDeclaredField("mListBoxDialog");
        localField3.setAccessible(true);
        Object localObject4 = localField3.get(localObject3);
        if (localObject4 != null)
        {
          Dialog localDialog = (Dialog)localObject4;
          localDialog.setOnCancelListener(null);
          Class localClass2 = Class.forName("android.app.Dialog");
          Field localField4 = localClass2.getDeclaredField("CANCEL");
          localField4.setAccessible(true);
          int i = ((Integer)localField4.get(localDialog)).intValue();
          Field localField5 = localClass2.getDeclaredField("mListenersHandler");
          localField5.setAccessible(true);
          ((Handler)localField5.get(localDialog)).removeMessages(i);
        }
      }
      label291: this.mSysWebView.destroy();
      try
      {
        Field localField1 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
        localField1.setAccessible(true);
        ComponentCallbacks localComponentCallbacks = (ComponentCallbacks)localField1.get(null);
        if (localComponentCallbacks == null)
          break label413;
        localField1.set(null, null);
        Field localField2 = Class.forName("android.view.ViewRoot").getDeclaredField("sConfigCallbacks");
        localField2.setAccessible(true);
        Object localObject1 = localField2.get(null);
        if (localObject1 == null)
          break label413;
        synchronized ((List)localObject1)
        {
          ???.remove(localComponentCallbacks);
          return;
        }
      }
      catch (Exception localException2)
      {
        return;
      }
      this.mX5WebView.destroy();
      label413: return;
    }
    catch (Exception localException1)
    {
      break label291;
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (onTouchEvent(paramMotionEvent))
      return true;
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void documentHasImages(Message paramMessage)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.documentHasImages(paramMessage);
      return;
    }
    this.mX5WebView.documentHasImages(paramMessage);
  }

  public void dumpViewHierarchyWithProperties(BufferedWriter paramBufferedWriter, int paramInt)
  {
    if (!this.isX5Core)
    {
      SystemWebView localSystemWebView = this.mSysWebView;
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = BufferedWriter.class;
      arrayOfClass[1] = Integer.TYPE;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramBufferedWriter;
      arrayOfObject[1] = Integer.valueOf(paramInt);
      ReflectionUtils.invokeInstance(localSystemWebView, "dumpViewHierarchyWithProperties", arrayOfClass, arrayOfObject);
      return;
    }
    this.mX5WebView.dumpViewHierarchyWithProperties(paramBufferedWriter, paramInt);
  }

  @Deprecated
  public int findAll(String paramString)
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "findAll", new Class[] { String.class }, new Object[] { paramString });
      if (localObject == null)
        return 0;
      return ((Integer)localObject).intValue();
    }
    return this.mX5WebView.findAll(paramString);
  }

  @TargetApi(16)
  public void findAllAsync(String paramString)
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 16)
        this.mSysWebView.findAllAsync(paramString);
      return;
    }
    this.mX5WebView.findAllAsync(paramString);
  }

  public View findHierarchyView(String paramString, int paramInt)
  {
    if (!this.isX5Core)
    {
      SystemWebView localSystemWebView = this.mSysWebView;
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = String.class;
      arrayOfClass[1] = Integer.TYPE;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = Integer.valueOf(paramInt);
      return (View)ReflectionUtils.invokeInstance(localSystemWebView, "findHierarchyView", arrayOfClass, arrayOfObject);
    }
    return this.mX5WebView.findHierarchyView(paramString, paramInt);
  }

  @TargetApi(3)
  public void findNext(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.findNext(paramBoolean);
      return;
    }
    this.mX5WebView.findNext(paramBoolean);
  }

  public void flingScroll(int paramInt1, int paramInt2)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.flingScroll(paramInt1, paramInt2);
      return;
    }
    this.mX5WebView.flingScroll(paramInt1, paramInt2);
  }

  @Deprecated
  public void freeMemory()
  {
    if (!this.isX5Core)
    {
      ReflectionUtils.invokeInstance(this.mSysWebView, "freeMemory");
      return;
    }
    this.mX5WebView.freeMemory();
  }

  public SslCertificate getCertificate()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getCertificate();
    return this.mX5WebView.getCertificate();
  }

  public int getContentHeight()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getContentHeight();
    return this.mX5WebView.getContentHeight();
  }

  public int getContentWidth()
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "getContentWidth");
      if (localObject == null)
        return 0;
      return ((Integer)localObject).intValue();
    }
    return this.mX5WebView.getContentWidth();
  }

  public Bitmap getFavicon()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getFavicon();
    return this.mX5WebView.getFavicon();
  }

  public HitTestResult getHitTestResult()
  {
    if (!this.isX5Core)
      return new HitTestResult(this.mSysWebView.getHitTestResult());
    return new HitTestResult(this.mX5WebView.getHitTestResult());
  }

  public String[] getHttpAuthUsernamePassword(String paramString1, String paramString2)
  {
    if (!this.isX5Core)
      return this.mSysWebView.getHttpAuthUsernamePassword(paramString1, paramString2);
    return this.mX5WebView.getHttpAuthUsernamePassword(paramString1, paramString2);
  }

  @TargetApi(3)
  public String getOriginalUrl()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getOriginalUrl();
    return this.mX5WebView.getOriginalUrl();
  }

  public int getProgress()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getProgress();
    return this.mX5WebView.getProgress();
  }

  @Deprecated
  public float getScale()
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "getScale");
      if (localObject == null);
      for (Float localFloat = null; ; localFloat = (Float)localObject)
        return localFloat.floatValue();
    }
    return this.mX5WebView.getScale();
  }

  public WebSettings getSettings()
  {
    if (this.mWebSettings != null)
      return this.mWebSettings;
    if (this.isX5Core)
    {
      WebSettings localWebSettings1 = new WebSettings(this.mX5WebView.getSettings());
      this.mWebSettings = localWebSettings1;
      return localWebSettings1;
    }
    WebSettings localWebSettings2 = new WebSettings(this.mSysWebView.getSettings());
    this.mWebSettings = localWebSettings2;
    return localWebSettings2;
  }

  public IX5WebSettingsExtension getSettingsExtension()
  {
    if (!this.isX5Core)
      return null;
    return this.mX5WebView.getX5WebViewExtension().getSettingsExtension();
  }

  android.webkit.WebView getSysWebView()
  {
    if (!this.isX5Core)
      return this.mSysWebView;
    return null;
  }

  public String getTitle()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getTitle();
    return this.mX5WebView.getTitle();
  }

  public String getUrl()
  {
    if (!this.isX5Core)
      return this.mSysWebView.getUrl();
    return this.mX5WebView.getUrl();
  }

  public View getView()
  {
    if (!this.isX5Core)
      return this.mSysWebView;
    return this.mX5WebView.getView();
  }

  public int getVisibleTitleHeight()
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "getVisibleTitleHeight");
      if (localObject == null)
        return 0;
      return ((Integer)localObject).intValue();
    }
    return this.mX5WebView.getVisibleTitleHeight();
  }

  public IX5WebChromeClientExtension getWebChromeClientExtension()
  {
    if (!this.isX5Core)
      return null;
    return this.mX5WebView.getX5WebViewExtension().getWebChromeClientExtension();
  }

  public int getWebScrollX()
  {
    if (this.isX5Core)
      return this.mX5WebView.getView().getScrollX();
    return this.mSysWebView.getScrollX();
  }

  public int getWebScrollY()
  {
    if (this.isX5Core)
      return this.mX5WebView.getView().getScrollY();
    return this.mSysWebView.getScrollY();
  }

  public IX5WebViewClientExtension getWebViewClientExtension()
  {
    if (!this.isX5Core)
      return null;
    return this.mX5WebView.getX5WebViewExtension().getWebViewClientExtension();
  }

  IX5WebViewBase getX5WebView()
  {
    return this.mX5WebView;
  }

  public IX5WebViewExtension getX5WebViewExtension()
  {
    if (!this.isX5Core)
      return null;
    return this.mX5WebView.getX5WebViewExtension();
  }

  @Deprecated
  public View getZoomControls()
  {
    if (!this.isX5Core)
      return (View)ReflectionUtils.invokeInstance(this.mSysWebView, "getZoomControls");
    return this.mX5WebView.getZoomControls();
  }

  public void goBack()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.goBack();
      return;
    }
    this.mX5WebView.goBack();
  }

  public void goBackOrForward(int paramInt)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.goBackOrForward(paramInt);
      return;
    }
    this.mX5WebView.goBackOrForward(paramInt);
  }

  public void goForward()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.goForward();
      return;
    }
    this.mX5WebView.goForward();
  }

  public void hideSplashLogo()
  {
    if ((this.mSplashLogo != null) && (this.mSplashLogo.getVisibility() == 0))
    {
      this.mSplashLogo.setVisibility(8);
      this.mSplashLogo = null;
    }
  }

  public void invokeZoomPicker()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.invokeZoomPicker();
      return;
    }
    this.mX5WebView.invokeZoomPicker();
  }

  @TargetApi(11)
  public boolean isPrivateBrowsingEnable()
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 11)
        return this.mSysWebView.isPrivateBrowsingEnabled();
      return false;
    }
    return this.mX5WebView.isPrivateBrowsingEnable();
  }

  public void loadData(String paramString1, String paramString2, String paramString3)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.loadData(paramString1, paramString2, paramString3);
      return;
    }
    this.mX5WebView.loadData(paramString1, paramString2, paramString3);
  }

  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
      return;
    }
    this.mX5WebView.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
  }

  public void loadUrl(String paramString)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.loadUrl(paramString);
      return;
    }
    this.mX5WebView.loadUrl(paramString);
  }

  @TargetApi(8)
  public void loadUrl(String paramString, Map<String, String> paramMap)
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 8)
        this.mSysWebView.loadUrl(paramString, paramMap);
      return;
    }
    this.mX5WebView.loadUrl(paramString, paramMap);
  }

  protected void onDetachedFromWindow()
  {
    if (!this.mIsReported)
    {
      String str1 = "";
      String str2 = "";
      String str3 = "";
      if (this.isX5Core)
      {
        Bundle localBundle = this.mX5WebView.getX5WebViewExtension().getSdkQBStatisticsInfo();
        if (localBundle != null)
        {
          str1 = localBundle.getString("guid");
          str2 = localBundle.getString("qua");
          str3 = localBundle.getString("lc");
        }
      }
      HttpUtils.doReport(this.mContext, str1, str2, str3, this.mPv, this.isX5Core);
      this.mIsReported = true;
    }
    super.onDetachedFromWindow();
  }

  public void onPause()
  {
    if (!this.isX5Core)
    {
      ReflectionUtils.invokeInstance(this.mSysWebView, "onPause");
      return;
    }
    this.mX5WebView.onPause();
  }

  public void onResume()
  {
    if (!this.isX5Core)
    {
      ReflectionUtils.invokeInstance(this.mSysWebView, "onResume");
      return;
    }
    this.mX5WebView.onResume();
  }

  public boolean overlayHorizontalScrollbar()
  {
    if (!this.isX5Core)
      return this.mSysWebView.overlayHorizontalScrollbar();
    return this.mX5WebView.overlayHorizontalScrollbar();
  }

  public boolean overlayVerticalScrollbar()
  {
    if (this.isX5Core)
      return this.mX5WebView.overlayVerticalScrollbar();
    return this.mSysWebView.overlayVerticalScrollbar();
  }

  public boolean pageDown(boolean paramBoolean)
  {
    if (!this.isX5Core)
      return this.mSysWebView.pageDown(paramBoolean);
    return this.mX5WebView.pageDown(paramBoolean, -1);
  }

  public boolean pageUp(boolean paramBoolean)
  {
    if (!this.isX5Core)
      return this.mSysWebView.pageUp(paramBoolean);
    return this.mX5WebView.pageUp(paramBoolean, -1);
  }

  public void pauseTimers()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.pauseTimers();
      return;
    }
    this.mX5WebView.pauseTimers();
  }

  @TargetApi(5)
  public void postUrl(String paramString, byte[] paramArrayOfByte)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.postUrl(paramString, paramArrayOfByte);
      return;
    }
    this.mX5WebView.postUrl(paramString, paramArrayOfByte);
  }

  @Deprecated
  public void refreshPlugins(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      SystemWebView localSystemWebView = this.mSysWebView;
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      ReflectionUtils.invokeInstance(localSystemWebView, "refreshPlugins", arrayOfClass, arrayOfObject);
      return;
    }
    this.mX5WebView.refreshPlugins(paramBoolean);
  }

  public void reload()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.reload();
      return;
    }
    this.mX5WebView.reload();
  }

  @TargetApi(11)
  public void removeJavascriptInterface(String paramString)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.removeJavascriptInterface(paramString);
      return;
    }
    this.mX5WebView.removeJavascriptInterface(paramString);
  }

  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    if (this.isX5Core)
    {
      View localView = this.mX5WebView.getView();
      if ((localView instanceof ViewGroup))
      {
        ViewGroup localViewGroup = (ViewGroup)localView;
        if (paramView == this);
        while (true)
        {
          return localViewGroup.requestChildRectangleOnScreen(localView, paramRect, paramBoolean);
          localView = paramView;
        }
      }
      return false;
    }
    SystemWebView localSystemWebView = this.mSysWebView;
    if (paramView == this)
      paramView = this.mSysWebView;
    return localSystemWebView.requestChildRectangleOnScreen(paramView, paramRect, paramBoolean);
  }

  public void requestFocusNodeHref(Message paramMessage)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.requestFocusNodeHref(paramMessage);
      return;
    }
    this.mX5WebView.requestFocusNodeHref(paramMessage);
  }

  public void requestImageRef(Message paramMessage)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.requestImageRef(paramMessage);
      return;
    }
    this.mX5WebView.requestImageRef(paramMessage);
  }

  @Deprecated
  public boolean restorePicture(Bundle paramBundle, File paramFile)
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "restorePicture", new Class[] { Bundle.class, File.class }, new Object[] { paramBundle, paramFile });
      if (localObject == null)
        return false;
      return ((Boolean)localObject).booleanValue();
    }
    return this.mX5WebView.restorePicture(paramBundle, paramFile);
  }

  public WebBackForwardList restoreState(Bundle paramBundle)
  {
    if (!this.isX5Core)
      return WebBackForwardList.wrap(this.mSysWebView.restoreState(paramBundle));
    return WebBackForwardList.wrap(this.mX5WebView.restoreState(paramBundle));
  }

  public void resumeTimers()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.resumeTimers();
      return;
    }
    this.mX5WebView.resumeTimers();
  }

  @Deprecated
  public void savePassword(String paramString1, String paramString2, String paramString3)
  {
    if (!this.isX5Core)
    {
      ReflectionUtils.invokeInstance(this.mSysWebView, "savePassword", new Class[] { String.class, String.class, String.class }, new Object[] { paramString1, paramString2, paramString3 });
      return;
    }
    this.mX5WebView.savePassword(paramString1, paramString2, paramString3);
  }

  @Deprecated
  public boolean savePicture(Bundle paramBundle, File paramFile)
  {
    if (!this.isX5Core)
    {
      Object localObject = ReflectionUtils.invokeInstance(this.mSysWebView, "savePicture", new Class[] { Bundle.class, File.class }, new Object[] { paramBundle, paramFile });
      if (localObject == null)
        return false;
      return ((Boolean)localObject).booleanValue();
    }
    return this.mX5WebView.savePicture(paramBundle, paramFile);
  }

  public WebBackForwardList saveState(Bundle paramBundle)
  {
    if (!this.isX5Core)
      return WebBackForwardList.wrap(this.mSysWebView.saveState(paramBundle));
    return WebBackForwardList.wrap(this.mX5WebView.saveState(paramBundle));
  }

  @TargetApi(11)
  public void saveWebArchive(String paramString)
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 11)
        this.mSysWebView.saveWebArchive(paramString);
      return;
    }
    this.mX5WebView.saveWebArchive(paramString);
  }

  @TargetApi(11)
  public void saveWebArchive(String paramString, boolean paramBoolean, ValueCallback<String> paramValueCallback)
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 11)
        this.mSysWebView.saveWebArchive(paramString, paramBoolean, paramValueCallback);
      return;
    }
    this.mX5WebView.saveWebArchive(paramString, paramBoolean, paramValueCallback);
  }

  public void setBackgroundColor(int paramInt)
  {
    if (!this.isX5Core)
      this.mSysWebView.setBackgroundColor(paramInt);
    while (true)
    {
      super.setBackgroundColor(paramInt);
      return;
      this.mX5WebView.setBackgroundColor(paramInt);
    }
  }

  @Deprecated
  public void setCertificate(SslCertificate paramSslCertificate)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.setCertificate(paramSslCertificate);
      return;
    }
    this.mX5WebView.setCertificate(paramSslCertificate);
  }

  public void setDayOrNight(boolean paramBoolean)
  {
    try
    {
      if (this.isX5Core)
        getSettingsExtension().setDayOrNight(paramBoolean);
      setSysDayOrNight(paramBoolean);
      postInvalidate();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  public void setDownloadListener(DownloadListener paramDownloadListener)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.setDownloadListener(new android.webkit.DownloadListener(paramDownloadListener)
      {
        public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
        {
          if (TipsManager.onDownloadStart(paramString1, paramString2, paramString3, paramString4, paramLong))
            return;
          this.val$listener.onDownloadStart(paramString1, paramString2, paramString3, paramString4, paramLong);
        }
      });
      return;
    }
    this.mX5WebView.setDownloadListener(new DownLoadListenerAdapter(this, paramDownloadListener, this.isX5Core));
  }

  @TargetApi(16)
  public void setFindListener(IX5WebViewBase.FindListener paramFindListener)
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 16)
        this.mSysWebView.setFindListener(new WebView.FindListener(paramFindListener)
        {
          public void onFindResultReceived(int paramInt1, int paramInt2, boolean paramBoolean)
          {
            this.val$listener.onFindResultReceived(paramInt1, paramInt2, paramBoolean);
          }
        });
      return;
    }
    this.mX5WebView.setFindListener(paramFindListener);
  }

  public void setHorizontalScrollbarOverlay(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.setHorizontalScrollbarOverlay(paramBoolean);
      return;
    }
    this.mX5WebView.setHorizontalScrollbarOverlay(paramBoolean);
  }

  public void setHttpAuthUsernamePassword(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.setHttpAuthUsernamePassword(paramString1, paramString2, paramString3, paramString4);
      return;
    }
    this.mX5WebView.setHttpAuthUsernamePassword(paramString1, paramString2, paramString3, paramString4);
  }

  public void setInitialScale(int paramInt)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.setInitialScale(paramInt);
      return;
    }
    this.mX5WebView.setInitialScale(paramInt);
  }

  @Deprecated
  public void setMapTrackballToArrowKeys(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      SystemWebView localSystemWebView = this.mSysWebView;
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      ReflectionUtils.invokeInstance(localSystemWebView, "setMapTrackballToArrowKeys", arrayOfClass, arrayOfObject);
      return;
    }
    this.mX5WebView.setMapTrackballToArrowKeys(paramBoolean);
  }

  @TargetApi(3)
  public void setNetworkAvailable(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      if (Build.VERSION.SDK_INT >= 3)
        this.mSysWebView.setNetworkAvailable(paramBoolean);
      return;
    }
    this.mX5WebView.setNetworkAvailable(paramBoolean);
  }

  public void setPictureListener(PictureListener paramPictureListener)
  {
    if (!this.isX5Core)
    {
      if (paramPictureListener == null)
      {
        this.mSysWebView.setPictureListener(null);
        return;
      }
      this.mSysWebView.setPictureListener(new android.webkit.WebView.PictureListener(paramPictureListener)
      {
        public void onNewPicture(android.webkit.WebView paramWebView, Picture paramPicture)
        {
          WebView.this.setSysWebView(paramWebView);
          this.val$listner.onNewPicture(WebView.this, paramPicture);
        }
      });
      return;
    }
    if (paramPictureListener == null)
    {
      this.mX5WebView.setPictureListener(null);
      return;
    }
    this.mX5WebView.setPictureListener(new IX5WebViewBase.PictureListener(paramPictureListener)
    {
      public void onNewPicture(IX5WebViewBase paramIX5WebViewBase, Picture paramPicture, boolean paramBoolean)
      {
        WebView.this.setX5WebView(paramIX5WebViewBase);
        this.val$listner.onNewPicture(WebView.this, paramPicture);
      }

      public void onNewPictureIfHaveContent(IX5WebViewBase paramIX5WebViewBase, Picture paramPicture)
      {
      }
    });
  }

  public void setScrollBarStyle(int paramInt)
  {
    if (this.isX5Core)
    {
      this.mX5WebView.getView().setScrollBarStyle(paramInt);
      return;
    }
    this.mSysWebView.setScrollBarStyle(paramInt);
  }

  void setSysWebView(android.webkit.WebView paramWebView)
  {
    if (!this.isX5Core);
  }

  public void setVerticalScrollbarOverlay(boolean paramBoolean)
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.setVerticalScrollbarOverlay(paramBoolean);
      return;
    }
    this.mX5WebView.setVerticalScrollbarOverlay(paramBoolean);
  }

  public void setWebChromeClient(WebChromeClient paramWebChromeClient)
  {
    if (this.isX5Core)
    {
      IX5WebViewBase localIX5WebViewBase = this.mX5WebView;
      Object localObject2 = null;
      if (paramWebChromeClient == null);
      while (true)
      {
        localIX5WebViewBase.setWebChromeClient((IX5WebChromeClient)localObject2);
        return;
        localObject2 = new SmttWebChromeClient(SDKEngine.getInstance(true).wizard(), this, paramWebChromeClient);
      }
    }
    SystemWebView localSystemWebView = this.mSysWebView;
    Object localObject1 = null;
    if (paramWebChromeClient == null);
    while (true)
    {
      localSystemWebView.setWebChromeClient((android.webkit.WebChromeClient)localObject1);
      return;
      localObject1 = new SystemWebChromeClient(this, paramWebChromeClient);
    }
  }

  public void setWebChromeClientExtension(IX5WebChromeClientExtension paramIX5WebChromeClientExtension)
  {
    if (!this.isX5Core)
      return;
    this.mX5WebView.getX5WebViewExtension().setWebChromeClientExtension(paramIX5WebChromeClientExtension);
  }

  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    if (this.isX5Core)
    {
      IX5WebViewBase localIX5WebViewBase = this.mX5WebView;
      Object localObject2 = null;
      if (paramWebViewClient == null);
      while (true)
      {
        localIX5WebViewBase.setWebViewClient((IX5WebViewClient)localObject2);
        return;
        localObject2 = new SmttWebViewClient(SDKEngine.getInstance(true).wizard(), this, paramWebViewClient);
      }
    }
    SystemWebView localSystemWebView = this.mSysWebView;
    Object localObject1 = null;
    if (paramWebViewClient == null);
    while (true)
    {
      localSystemWebView.setWebViewClient((android.webkit.WebViewClient)localObject1);
      return;
      localObject1 = new SystemWebViewClient(this, paramWebViewClient);
    }
  }

  public void setWebViewClientExtension(IX5WebViewClientExtension paramIX5WebViewClientExtension)
  {
    if (!this.isX5Core)
      return;
    this.mX5WebView.getX5WebViewExtension().setWebViewClientExtension(paramIX5WebViewClientExtension);
  }

  void setX5WebView(IX5WebViewBase paramIX5WebViewBase)
  {
    this.mX5WebView = paramIX5WebViewBase;
  }

  public boolean showFindDialog(String paramString, boolean paramBoolean)
  {
    return false;
  }

  public void stopLoading()
  {
    if (!this.isX5Core)
    {
      this.mSysWebView.stopLoading();
      return;
    }
    this.mX5WebView.stopLoading();
  }

  public boolean zoomIn()
  {
    if (!this.isX5Core)
      return this.mSysWebView.zoomIn();
    return this.mX5WebView.zoomIn();
  }

  public boolean zoomOut()
  {
    if (!this.isX5Core)
      return this.mSysWebView.zoomOut();
    return this.mX5WebView.zoomOut();
  }

  public static class HitTestResult
  {

    @Deprecated
    public static final int ANCHOR_TYPE = 1;
    public static final int EDIT_TEXT_TYPE = 9;
    public static final int EMAIL_TYPE = 4;
    public static final int GEO_TYPE = 3;

    @Deprecated
    public static final int IMAGE_ANCHOR_TYPE = 6;
    public static final int IMAGE_TYPE = 5;
    public static final int PHONE_TYPE = 2;
    public static final int SRC_ANCHOR_TYPE = 7;
    public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
    public static final int UNKNOWN_TYPE;
    private IX5WebViewBase.HitTestResult mHitTestResultImpl;
    private android.webkit.WebView.HitTestResult mSysHitTestResult = null;

    public HitTestResult()
    {
      this.mHitTestResultImpl = null;
      this.mSysHitTestResult = null;
    }

    public HitTestResult(android.webkit.WebView.HitTestResult paramHitTestResult)
    {
      this.mHitTestResultImpl = null;
      this.mSysHitTestResult = paramHitTestResult;
    }

    public HitTestResult(IX5WebViewBase.HitTestResult paramHitTestResult)
    {
      this.mHitTestResultImpl = paramHitTestResult;
      this.mSysHitTestResult = null;
    }

    public String getExtra()
    {
      String str = "";
      if (this.mHitTestResultImpl != null)
        str = this.mHitTestResultImpl.getExtra();
      do
        return str;
      while (this.mSysHitTestResult == null);
      return this.mSysHitTestResult.getExtra();
    }

    public int getType()
    {
      int i;
      if (this.mHitTestResultImpl != null)
        i = this.mHitTestResultImpl.getType();
      android.webkit.WebView.HitTestResult localHitTestResult;
      do
      {
        return i;
        localHitTestResult = this.mSysHitTestResult;
        i = 0;
      }
      while (localHitTestResult == null);
      return this.mSysHitTestResult.getType();
    }

    public void setExtra(String paramString)
    {
      if (this.mHitTestResultImpl != null)
        this.mHitTestResultImpl.setExtra(paramString);
    }

    public void setType(int paramInt)
    {
      if (this.mHitTestResultImpl != null)
        this.mHitTestResultImpl.setType(paramInt);
    }
  }

  public static abstract interface PictureListener
  {
    public abstract void onNewPicture(WebView paramWebView, Picture paramPicture);
  }

  private class SystemWebView extends android.webkit.WebView
  {
    public SystemWebView(Context arg2)
    {
      super();
      CookieSyncManager.createInstance(WebView.this.mContext).startSync();
      try
      {
        Method localMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
        localMethod.setAccessible(true);
        ((Handler)localMethod.invoke(null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new SQLiteUncaughtExceptionHandler());
        return;
      }
      catch (Exception localException)
      {
      }
    }

    protected void dispatchDraw(Canvas paramCanvas)
    {
      try
      {
        super.dispatchDraw(paramCanvas);
        if ((!WebView.mIsDayMode) && (WebView.mNightModePaint != null))
        {
          paramCanvas.save();
          paramCanvas.drawPaint(WebView.mNightModePaint);
          paramCanvas.restore();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }

    protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
      WebView.this.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if (!hasFocus())
        requestFocus();
      try
      {
        boolean bool = super.onTouchEvent(paramMotionEvent);
        return bool;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return false;
    }
  }

  public class WebViewTransport
  {
    private WebView mWebview;

    public WebViewTransport()
    {
    }

    public WebView getWebView()
    {
      monitorenter;
      try
      {
        WebView localWebView = this.mWebview;
        monitorexit;
        return localWebView;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }

    public void setWebView(WebView paramWebView)
    {
      monitorenter;
      try
      {
        this.mWebview = paramWebView;
        monitorexit;
        return;
      }
      finally
      {
        localObject = finally;
        monitorexit;
      }
      throw localObject;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.WebView
 * JD-Core Version:    0.6.0
 */