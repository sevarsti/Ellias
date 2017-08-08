package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslCertificate;
import android.os.Build.VERSION;
import android.os.Message;
import android.view.KeyEvent;
import com.tencent.smtt.sdk.tips.TipsManager;

class SystemWebViewClient extends android.webkit.WebViewClient
{
  private WebViewClient mClient;
  private WebView mWebView;

  SystemWebViewClient(WebView paramWebView, WebViewClient paramWebViewClient)
  {
    this.mWebView = paramWebView;
    this.mClient = paramWebViewClient;
  }

  public void doUpdateVisitedHistory(android.webkit.WebView paramWebView, String paramString, boolean paramBoolean)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.doUpdateVisitedHistory(this.mWebView, paramString, paramBoolean);
  }

  public void onFormResubmission(android.webkit.WebView paramWebView, Message paramMessage1, Message paramMessage2)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onFormResubmission(this.mWebView, paramMessage1, paramMessage2);
  }

  public void onLoadResource(android.webkit.WebView paramWebView, String paramString)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onLoadResource(this.mWebView, paramString);
  }

  public void onPageFinished(android.webkit.WebView paramWebView, String paramString)
  {
    this.mWebView.setSysWebView(paramWebView);
    WebView localWebView = this.mWebView;
    localWebView.mPv = (1 + localWebView.mPv);
    this.mClient.onPageFinished(this.mWebView, paramString);
    TipsManager.insertRecommand(this.mWebView);
  }

  public void onPageStarted(android.webkit.WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onPageStarted(this.mWebView, paramString, paramBitmap);
  }

  public void onReceivedError(android.webkit.WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onReceivedError(this.mWebView, paramInt, paramString1, paramString2);
  }

  public void onReceivedHttpAuthRequest(android.webkit.WebView paramWebView, android.webkit.HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onReceivedHttpAuthRequest(this.mWebView, new HttpAuthHandlerImpl(paramHttpAuthHandler), paramString1, paramString2);
  }

  @TargetApi(12)
  public void onReceivedLoginRequest(android.webkit.WebView paramWebView, String paramString1, String paramString2, String paramString3)
  {
    if (Build.VERSION.SDK_INT >= 12)
    {
      this.mWebView.setSysWebView(paramWebView);
      this.mClient.onReceivedLoginRequest(this.mWebView, paramString1, paramString2, paramString3);
    }
  }

  @TargetApi(8)
  public void onReceivedSslError(android.webkit.WebView paramWebView, android.webkit.SslErrorHandler paramSslErrorHandler, android.net.http.SslError paramSslError)
  {
    if (Build.VERSION.SDK_INT >= 8)
    {
      this.mWebView.setSysWebView(paramWebView);
      this.mClient.onReceivedSslError(this.mWebView, new SslErrorHandlerImpl(paramSslErrorHandler), new SslErrorImpl(paramSslError));
    }
  }

  public void onScaleChanged(android.webkit.WebView paramWebView, float paramFloat1, float paramFloat2)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onScaleChanged(this.mWebView, paramFloat1, paramFloat2);
  }

  public void onTooManyRedirects(android.webkit.WebView paramWebView, Message paramMessage1, Message paramMessage2)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onTooManyRedirects(this.mWebView, paramMessage1, paramMessage2);
  }

  public void onUnhandledKeyEvent(android.webkit.WebView paramWebView, KeyEvent paramKeyEvent)
  {
    this.mWebView.setSysWebView(paramWebView);
    this.mClient.onUnhandledKeyEvent(this.mWebView, paramKeyEvent);
  }

  public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView paramWebView, String paramString)
  {
    com.tencent.smtt.export.external.interfaces.WebResourceResponse localWebResourceResponse = this.mClient.shouldInterceptRequest(this.mWebView, paramString);
    if (localWebResourceResponse == null)
      return null;
    return new android.webkit.WebResourceResponse(localWebResourceResponse.getMimeType(), localWebResourceResponse.getEncoding(), localWebResourceResponse.getData());
  }

  public boolean shouldOverrideKeyEvent(android.webkit.WebView paramWebView, KeyEvent paramKeyEvent)
  {
    this.mWebView.setSysWebView(paramWebView);
    return this.mClient.shouldOverrideKeyEvent(this.mWebView, paramKeyEvent);
  }

  public boolean shouldOverrideUrlLoading(android.webkit.WebView paramWebView, String paramString)
  {
    this.mWebView.setSysWebView(paramWebView);
    if (TipsManager.shouldOverrideUrlLoading(paramString))
      return TipsManager.onDownloadStart(paramString, null, null, null, 0L);
    return this.mClient.shouldOverrideUrlLoading(this.mWebView, paramString);
  }

  private static class HttpAuthHandlerImpl
    implements com.tencent.smtt.export.external.interfaces.HttpAuthHandler
  {
    private android.webkit.HttpAuthHandler mHandler;

    HttpAuthHandlerImpl(android.webkit.HttpAuthHandler paramHttpAuthHandler)
    {
      this.mHandler = paramHttpAuthHandler;
    }

    public void cancel()
    {
      this.mHandler.cancel();
    }

    public void proceed(String paramString1, String paramString2)
    {
      this.mHandler.proceed(paramString1, paramString2);
    }

    public boolean useHttpAuthUsernamePassword()
    {
      return this.mHandler.useHttpAuthUsernamePassword();
    }
  }

  private static class SslErrorHandlerImpl
    implements com.tencent.smtt.export.external.interfaces.SslErrorHandler
  {
    android.webkit.SslErrorHandler mSslErrorHandler;

    SslErrorHandlerImpl(android.webkit.SslErrorHandler paramSslErrorHandler)
    {
      this.mSslErrorHandler = paramSslErrorHandler;
    }

    public void cancel()
    {
      this.mSslErrorHandler.cancel();
    }

    public void proceed()
    {
      this.mSslErrorHandler.proceed();
    }
  }

  private static class SslErrorImpl
    implements com.tencent.smtt.export.external.interfaces.SslError
  {
    android.net.http.SslError mSslError;

    SslErrorImpl(android.net.http.SslError paramSslError)
    {
      this.mSslError = paramSslError;
    }

    public boolean addError(int paramInt)
    {
      return this.mSslError.addError(paramInt);
    }

    public SslCertificate getCertificate()
    {
      return this.mSslError.getCertificate();
    }

    public int getPrimaryError()
    {
      return this.mSslError.getPrimaryError();
    }

    public boolean hasError(int paramInt)
    {
      return this.mSslError.hasError(paramInt);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.SystemWebViewClient
 * JD-Core Version:    0.6.0
 */