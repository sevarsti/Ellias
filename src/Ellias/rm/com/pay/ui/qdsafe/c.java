package com.pay.ui.qdsafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.pay.tool.APPassWordTools.passChannel;
import com.pay.ui.common.APUICommonMethod;

final class c extends WebViewClient
{
  c(APSafeCenterWebActivity paramAPSafeCenterWebActivity)
  {
  }

  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    APSafeCenterWebActivity.a(this.a).dismiss();
    super.onPageFinished(paramWebView, paramString);
  }

  public final void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    APSafeCenterWebActivity.a(this.a, paramWebView);
    String str = "";
    if (paramString.startsWith("http://unipay.mibaocheck"));
    try
    {
      str = paramString.split("=")[1].split("&")[0];
      Intent localIntent = new Intent();
      localIntent.putExtra("channel", APPassWordTools.passChannel.H5);
      localIntent.putExtra("sig", str);
      this.a.setResult(10, localIntent);
      this.a.finish();
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        APUICommonMethod.showToast(this.a, "网络错误，请稍后重试");
        paramWebView.stopLoading();
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.c
 * JD-Core Version:    0.6.0
 */