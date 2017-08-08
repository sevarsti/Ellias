package com.pay.ui.payWeb;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.pay.common.tool.APLog;

final class b extends WebViewClient
{
  b(APWebActivity paramAPWebActivity)
  {
  }

  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    APLog.i("APWebActivity", "onPageFinished");
    this.a.mWebView.setVisibility(0);
    APWebActivity.a(this.a, false);
    APWebActivity.b(this.a).dismiss();
    this.a.apWebData.webResponseCallBack.WebPageFinished(paramWebView, paramString);
  }

  public final void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    APLog.i("APWebActivity", "onPageStarted");
    if (!APWebActivity.a(this.a))
      this.a.apWebData.webResponseCallBack.WebPageStarted(paramWebView, paramString, paramBitmap);
  }

  public final void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    APLog.i("APWebActivity", "onReceivedError errorCode,description ==" + paramInt + "," + paramString1);
    if (APWebActivity.b(this.a) != null)
      APWebActivity.b(this.a).dismiss();
    APWebActivity.a(this.a, false);
    this.a.apWebData.webResponseCallBack.WebReceivedError(paramWebView, paramInt, paramString1, paramString2);
  }

  public final void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
  {
    super.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler, paramString1, paramString2);
  }

  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    APLog.i("APWebActivity", "shouldOverrideUrlLoading");
    paramWebView.stopLoading();
    this.a.apWebData.webResponseCallBack.WebShouldUrlLoading(paramWebView, paramString);
    APWebActivity.a(this.a, true);
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.b
 * JD-Core Version:    0.6.0
 */