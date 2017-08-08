package com.pay.ui.payWeb;

import android.graphics.Bitmap;
import android.webkit.WebView;

public abstract interface IAPWebResponse
{
  public abstract void WebLoadingCancel();

  public abstract void WebPageFinished(WebView paramWebView, String paramString);

  public abstract void WebPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap);

  public abstract void WebReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2);

  public abstract void WebShouldUrlLoading(WebView paramWebView, String paramString);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.IAPWebResponse
 * JD-Core Version:    0.6.0
 */