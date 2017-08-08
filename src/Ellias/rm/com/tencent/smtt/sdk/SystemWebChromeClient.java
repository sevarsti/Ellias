package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.QuotaUpdater;

class SystemWebChromeClient extends android.webkit.WebChromeClient
{
  private WebChromeClient mChromeClient;
  private WebView mWebView;

  SystemWebChromeClient(WebView paramWebView, WebChromeClient paramWebChromeClient)
  {
    this.mWebView = paramWebView;
    this.mChromeClient = paramWebChromeClient;
  }

  @TargetApi(7)
  public Bitmap getDefaultVideoPoster()
  {
    return this.mChromeClient.getDefaultVideoPoster();
  }

  @TargetApi(7)
  public View getVideoLoadingProgressView()
  {
    return this.mChromeClient.getVideoLoadingProgressView();
  }

  public void getVisitedHistory(ValueCallback<String[]> paramValueCallback)
  {
    this.mChromeClient.getVisitedHistory(paramValueCallback);
  }

  public void onCloseWindow(android.webkit.WebView paramWebView)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mChromeClient.onCloseWindow(this.mWebView);
  }

  public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
  {
    this.mChromeClient.onConsoleMessage(new ConsoleMessageImpl(paramString1, paramString2, paramInt));
  }

  public boolean onConsoleMessage(android.webkit.ConsoleMessage paramConsoleMessage)
  {
    return this.mChromeClient.onConsoleMessage(new ConsoleMessageImpl(paramConsoleMessage));
  }

  public boolean onCreateWindow(android.webkit.WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
  {
    WebView localWebView = this.mWebView;
    localWebView.getClass();
    WebView.WebViewTransport localWebViewTransport = new WebView.WebViewTransport(localWebView);
    Message localMessage = Message.obtain(paramMessage.getTarget(), new Runnable(localWebViewTransport, paramMessage)
    {
      public void run()
      {
        WebView localWebView = this.val$transport.getWebView();
        if (localWebView != null)
          ((android.webkit.WebView.WebViewTransport)this.val$resultMsg.obj).setWebView(localWebView.getSysWebView());
        this.val$resultMsg.sendToTarget();
      }
    });
    localMessage.obj = localWebViewTransport;
    return this.mChromeClient.onCreateWindow(this.mWebView, paramBoolean1, paramBoolean2, localMessage);
  }

  @Deprecated
  @TargetApi(5)
  public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    this.mChromeClient.onExceededDatabaseQuota(paramString1, paramString2, paramLong1, paramLong2, paramLong3, new QuotaUpdaterImpl(paramQuotaUpdater));
  }

  @TargetApi(5)
  public void onGeolocationPermissionsHidePrompt()
  {
    this.mChromeClient.onGeolocationPermissionsHidePrompt();
  }

  @TargetApi(5)
  public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
  {
    this.mChromeClient.onGeolocationPermissionsShowPrompt(paramString, new GeolocationPermissionsCallbackImpl(paramCallback));
  }

  @TargetApi(7)
  public void onHideCustomView()
  {
    this.mChromeClient.onHideCustomView();
  }

  public boolean onJsAlert(android.webkit.WebView paramWebView, String paramString1, String paramString2, android.webkit.JsResult paramJsResult)
  {
    this.mWebView.setSysWebView(paramWebView);
    return this.mChromeClient.onJsAlert(this.mWebView, paramString1, paramString2, new JsResultImpl(paramJsResult));
  }

  public boolean onJsBeforeUnload(android.webkit.WebView paramWebView, String paramString1, String paramString2, android.webkit.JsResult paramJsResult)
  {
    this.mWebView.setSysWebView(paramWebView);
    return this.mChromeClient.onJsBeforeUnload(this.mWebView, paramString1, paramString2, new JsResultImpl(paramJsResult));
  }

  public boolean onJsConfirm(android.webkit.WebView paramWebView, String paramString1, String paramString2, android.webkit.JsResult paramJsResult)
  {
    this.mWebView.setSysWebView(paramWebView);
    return this.mChromeClient.onJsConfirm(this.mWebView, paramString1, paramString2, new JsResultImpl(paramJsResult));
  }

  public boolean onJsPrompt(android.webkit.WebView paramWebView, String paramString1, String paramString2, String paramString3, android.webkit.JsPromptResult paramJsPromptResult)
  {
    this.mWebView.setSysWebView(paramWebView);
    return this.mChromeClient.onJsPrompt(this.mWebView, paramString1, paramString2, paramString3, new JsPromptResultImpl(paramJsPromptResult));
  }

  @TargetApi(7)
  public boolean onJsTimeout()
  {
    return this.mChromeClient.onJsTimeout();
  }

  public void onProgressChanged(android.webkit.WebView paramWebView, int paramInt)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mChromeClient.onProgressChanged(this.mWebView, paramInt);
  }

  @Deprecated
  @TargetApi(7)
  public void onReachedMaxAppCacheSize(long paramLong1, long paramLong2, WebStorage.QuotaUpdater paramQuotaUpdater)
  {
    this.mChromeClient.onReachedMaxAppCacheSize(paramLong1, paramLong2, new QuotaUpdaterImpl(paramQuotaUpdater));
  }

  public void onReceivedIcon(android.webkit.WebView paramWebView, Bitmap paramBitmap)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mChromeClient.onReceivedIcon(this.mWebView, paramBitmap);
  }

  public void onReceivedTitle(android.webkit.WebView paramWebView, String paramString)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mChromeClient.onReceivedTitle(this.mWebView, paramString);
  }

  @TargetApi(7)
  public void onReceivedTouchIconUrl(android.webkit.WebView paramWebView, String paramString, boolean paramBoolean)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mChromeClient.onReceivedTouchIconUrl(this.mWebView, paramString, paramBoolean);
  }

  public void onRequestFocus(android.webkit.WebView paramWebView)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mChromeClient.onRequestFocus(this.mWebView);
  }

  @Deprecated
  @TargetApi(14)
  public void onShowCustomView(View paramView, int paramInt, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.mChromeClient.onShowCustomView(paramView, paramInt, new CustomViewCallbackImpl(paramCustomViewCallback));
  }

  @TargetApi(7)
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.mChromeClient.onShowCustomView(paramView, new CustomViewCallbackImpl(paramCustomViewCallback));
  }

  public void openFileChooser(ValueCallback<Uri> paramValueCallback)
  {
    openFileChooser(paramValueCallback, null, null);
  }

  public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString)
  {
    openFileChooser(paramValueCallback, paramString, null);
  }

  public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
  {
    this.mChromeClient.openFileChooser(paramValueCallback, paramString1, paramString2);
  }

  public void setupAutoFill(Message paramMessage)
  {
  }

  private static class ConsoleMessageImpl
    implements com.tencent.smtt.export.external.interfaces.ConsoleMessage
  {
    private int mLineNumber;
    private String mMessage;
    private com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel mMessageLevel;
    private String mSourceId;

    ConsoleMessageImpl(android.webkit.ConsoleMessage paramConsoleMessage)
    {
      this.mMessageLevel = com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel.valueOf(paramConsoleMessage.messageLevel().name());
      this.mMessage = paramConsoleMessage.message();
      this.mSourceId = paramConsoleMessage.sourceId();
      this.mLineNumber = paramConsoleMessage.lineNumber();
    }

    ConsoleMessageImpl(String paramString1, String paramString2, int paramInt)
    {
      this.mMessageLevel = com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel.LOG;
      this.mMessage = paramString1;
      this.mSourceId = paramString2;
      this.mLineNumber = paramInt;
    }

    public int lineNumber()
    {
      return this.mLineNumber;
    }

    public String message()
    {
      return this.mMessage;
    }

    public com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel messageLevel()
    {
      return this.mMessageLevel;
    }

    public String sourceId()
    {
      return this.mSourceId;
    }
  }

  class CustomViewCallbackImpl
    implements IX5WebChromeClient.CustomViewCallback
  {
    WebChromeClient.CustomViewCallback mCustomViewCallback;

    CustomViewCallbackImpl(WebChromeClient.CustomViewCallback arg2)
    {
      Object localObject;
      this.mCustomViewCallback = localObject;
    }

    public void onCustomViewHidden()
    {
      this.mCustomViewCallback.onCustomViewHidden();
    }
  }

  class GeolocationPermissionsCallbackImpl
    implements GeolocationPermissionsCallback
  {
    GeolocationPermissions.Callback mCallback;

    GeolocationPermissionsCallbackImpl(GeolocationPermissions.Callback arg2)
    {
      Object localObject;
      this.mCallback = localObject;
    }

    public void invoke(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.mCallback.invoke(paramString, paramBoolean1, paramBoolean2);
    }
  }

  private class JsPromptResultImpl
    implements com.tencent.smtt.export.external.interfaces.JsPromptResult
  {
    android.webkit.JsPromptResult mJsPromptResult;

    JsPromptResultImpl(android.webkit.JsPromptResult arg2)
    {
      Object localObject;
      this.mJsPromptResult = localObject;
    }

    public void cancel()
    {
      this.mJsPromptResult.cancel();
    }

    public void confirm()
    {
      this.mJsPromptResult.confirm();
    }

    public void confirm(String paramString)
    {
      this.mJsPromptResult.confirm(paramString);
    }
  }

  private class JsResultImpl
    implements com.tencent.smtt.export.external.interfaces.JsResult
  {
    android.webkit.JsResult mJsResult;

    JsResultImpl(android.webkit.JsResult arg2)
    {
      Object localObject;
      this.mJsResult = localObject;
    }

    public void cancel()
    {
      this.mJsResult.cancel();
    }

    public void confirm()
    {
      this.mJsResult.confirm();
    }
  }

  class QuotaUpdaterImpl
    implements QuotaUpdater
  {
    WebStorage.QuotaUpdater mQuotaUpdater;

    QuotaUpdaterImpl(WebStorage.QuotaUpdater arg2)
    {
      Object localObject;
      this.mQuotaUpdater = localObject;
    }

    public void updateQuota(long paramLong)
    {
      this.mQuotaUpdater.updateQuota(paramLong);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.SystemWebChromeClient
 * JD-Core Version:    0.6.0
 */