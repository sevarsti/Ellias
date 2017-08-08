package com.pay.ui.payWeb;

import android.graphics.Bitmap;
import android.webkit.WebView;
import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.ui.common.APUICommonMethod;

final class c
  implements IAPWebResponse
{
  c(APWebBuyActivity paramAPWebBuyActivity)
  {
  }

  public final void WebLoadingCancel()
  {
    if (APWebBuyActivity.loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_LIST))
    {
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(2, "支付取消");
    }
    do
      return;
    while ((APWebBuyActivity.loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_INPUT)) || (!APWebBuyActivity.loadWebPage.equals(APWebProtocol.WEBPAGE_PAYGAME_RESULT)));
    APUICommonMethod.popActivity();
    APCommMethod.paySuccCallBack(APDataInterface.singleton().getOrderInfo().payChannel, 0, -1);
  }

  public final void WebPageFinished(WebView paramWebView, String paramString)
  {
    APLog.i("APWebBuyActivity", "WebPageFinished");
  }

  public final void WebPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    APLog.i("APWebBuyActivity", "WebPageStarted");
    if (paramString.startsWith("http://unipay.sdk.android/?"))
    {
      paramWebView.goBack();
      APWebBuyActivity.a(this.a, paramString);
    }
  }

  public final void WebReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    APLog.i("APWebBuyActivity", "WebReceivedError");
    APWebBuyActivity.a(this.a);
  }

  public final void WebShouldUrlLoading(WebView paramWebView, String paramString)
  {
    APLog.i("APWebBuyActivity", "WebShouldUrlLoading");
    if (paramString.startsWith("http://unipay.sdk.android/?"))
    {
      paramWebView.goBack();
      APWebBuyActivity.a(this.a, paramString);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payWeb.c
 * JD-Core Version:    0.6.0
 */