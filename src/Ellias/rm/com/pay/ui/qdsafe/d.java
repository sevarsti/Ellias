package com.pay.ui.qdsafe;

import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;

final class d extends WebChromeClient
{
  d(APSafeCenterWebActivity paramAPSafeCenterWebActivity)
  {
  }

  public final boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
  {
    WebView localWebView = new WebView(paramWebView.getContext());
    APSafeCenterWebActivity.a(this.a, localWebView);
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.setWebChromeClient(this);
    localWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    ((WebView.WebViewTransport)paramMessage.obj).setWebView(localWebView);
    paramMessage.sendToTarget();
    return true;
  }

  public final void onReceivedTitle(WebView paramWebView, String paramString)
  {
    this.a.SetTitle(paramWebView.getTitle().toString());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.d
 * JD-Core Version:    0.6.0
 */